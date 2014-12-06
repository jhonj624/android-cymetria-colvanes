package com.example.p6_files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

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

		OnClickListener buttonController;
		
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			Button newFileButton = (Button)rootView.findViewById(R.id.new_file_button);
			buttonController = new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					EditText fileName = (EditText)getActivity().findViewById(R.id.file_name_text);
					String fileNameStr = fileName.getText().toString();
					EditText fileContents = (EditText)getActivity().findViewById(R.id.new_file_content);
					String fileContentsStr = fileContents.getText().toString();
					
					if(fileNameStr.isEmpty()){
						Toast.makeText(getActivity(), getResources().getString(R.string.empty_name_toast), Toast.LENGTH_SHORT).show();
					}else{
						writeFile(getActivity(), fileNameStr+".txt", fileContentsStr);
					}
					
				}
			};
			newFileButton.setOnClickListener(buttonController);
			
			return rootView;
		}
		
		public void writeFile(Context context, String fileNameStr, String fileContents){
			
			// Para el directorio privado
			FileOutputStream writer;
			try{
				writer = context.openFileOutput(fileNameStr, Context.MODE_PRIVATE);
				writer.write(fileContents.getBytes());
				writer.close();
				Toast.makeText(context, getResources().getString(R.string.written_internal), Toast.LENGTH_SHORT).show();
			}catch(Exception e){
				Toast.makeText(context, getResources().getString(R.string.error_writting), Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
			
			// Para el directorio cache
			try{
				File cacheFile = File.createTempFile(fileNameStr,null,context.getCacheDir());
				Writer writer2 = new BufferedWriter(new FileWriter(cacheFile));
				writer2.write(fileContents);
				writer2.close();
				Toast.makeText(context, getResources().getString(R.string.written_cache), Toast.LENGTH_SHORT).show();
			}catch(Exception e){
				Toast.makeText(context, getResources().getString(R.string.error_writting), Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
			
			String state = Environment.getExternalStorageState();
		    if (Environment.MEDIA_MOUNTED.equals(state)) {
		    	
		    	// Para el directorio externo publico
				try{
					File publicFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/"+getResources().getString(R.string.app_name));
					if(publicFileDir.mkdirs()){
						Toast.makeText(context, getResources().getString(R.string.new_directory)+publicFileDir.getPath(), Toast.LENGTH_SHORT).show();
					}
					File publicFile = new File(publicFileDir, fileNameStr);
					Writer writer3 = new BufferedWriter(new FileWriter(publicFile));
					writer3.write(fileContents);
					writer3.close();
					Toast.makeText(context, getResources().getString(R.string.written_external), Toast.LENGTH_SHORT).show();
				}catch(Exception e){
					Toast.makeText(context, getResources().getString(R.string.error_writting), Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
				
				// Para el directorio externo privado
				try{
					File privateFileDir = new File(Environment.getExternalStorageDirectory()+"/"+getResources().getString(R.string.app_name));
					if(privateFileDir.mkdirs()){
						Toast.makeText(context, getResources().getString(R.string.new_directory)+privateFileDir.getPath(), Toast.LENGTH_SHORT).show();
					}
					File privateFile = new File(privateFileDir, fileNameStr);
					Writer writer4 = new BufferedWriter(new FileWriter(privateFile));
					writer4.write(fileContents);
					writer4.close();
					Toast.makeText(context, getResources().getString(R.string.written_external_private), Toast.LENGTH_SHORT).show();
				}catch(Exception e){
					Toast.makeText(context, getResources().getString(R.string.error_writting), Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
		    }else{
		    	Toast.makeText(context, getResources().getString(R.string.no_mounted), Toast.LENGTH_SHORT).show();
		    }
		}
	}

}
