package com.example.p8_async_task;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

	EditText urlField;
	EditText urlContents;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	public void loadUrl(View view){
		
		urlField = (EditText)findViewById(R.id.editText1);
		urlContents = (EditText)findViewById(R.id.editText2);
		
		String stringUrl = urlField.getText().toString();
        ConnectivityManager connMgr = (ConnectivityManager) 
            getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadContentPage().execute(stringUrl);
        } else {
        	urlContents.setText("No hay una conexión disponible.");
        }
	}
	
	public class DownloadContentPage extends AsyncTask<String, Integer, String>{

		public String readInfo(InputStream stream, int length) throws IOException{
			Reader r = null;
			r = new InputStreamReader(stream, "UTF-8");
			char[] buffer = new char[length];
			r.read(buffer);
			return new String(buffer);
		}
		
		public String downloadUrlContent(String url) throws IOException{
			InputStream readerStream = null;
			int length = 500;
			
			try {
				URL myUrl = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) myUrl.openConnection();
				conn.setConnectTimeout(15000);
				conn.setReadTimeout(10000);
				conn.setRequestMethod("GET");
				// Leer la respuesta o ignorarla? Default= true
				conn.setDoInput(true);
				
				conn.connect();
				
				int responseCode = conn.getResponseCode();
				Log.d("AsyncEnvia", "response: "+responseCode);
				
				readerStream = conn.getInputStream();
				
				return readInfo(readerStream,length);
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "La URL está mal ingresada.";
			} finally {
				if(readerStream != null){
					readerStream.close();
				}
			}
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			urlContents.setText("Arrancando la conexión");
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String url = params[0];
			try {
				return downloadUrlContent(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			urlContents.setText("Progreso: "+String.valueOf(values[0])+"%");
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			urlContents.setText("Resultado: "+result);
		}
		
	}
}
