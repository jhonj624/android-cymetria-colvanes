package com.example.may13;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	public static final String MY_FIRST_NAME = "com.example.may13.FIRST_NAME";

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
	
	public void printButton(View view){
		// La vista que recibe el parámetro en este caso es la misma que pedirla directamente por el ID del botón
		//*/
		String textInButton = ((Button)view).getText().toString();
		/*/
		String textInButton = ((Button)findViewById(R.id.sendButton)).getText().toString();
		//*/
		Context appContext = getApplicationContext();
		String messageInToast = "Haz hecho click en el botón "+textInButton;
		
		Toast firstToast = Toast.makeText(appContext, messageInToast, Toast.LENGTH_SHORT);
		firstToast.show();
		
	}

	public void sendData(View view){
		
		String textInEdit = ((EditText)findViewById(R.id.firstName)).getText().toString();
		
		if(textInEdit.length() > 0){
			
			Intent goToSecond = new Intent(this, AnswerActivity.class);
			goToSecond.putExtra(MY_FIRST_NAME, textInEdit);
			
			startActivity(goToSecond);
			
		}else{
			Toast firstToast = Toast.makeText(getApplicationContext(), "No hay nada por enviar.", Toast.LENGTH_SHORT);
			firstToast.show();
		}
		
	}
}
