package com.example.p5_dialog_shared_prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		// TODO Auto-generated method stub
		
//		Button button1 = (Button)findViewById(R.id.button1);
//		Button button2 = (Button)findViewById(R.id.button2);
//		Button button3 = (Button)findViewById(R.id.button3);
//		
//		button1.setOnClickListener(this);
//		button2.setOnClickListener(this);
//		button3.setOnClickListener(this);
//		
//		SharedPreferences prefs = getPreferences(MODE_PRIVATE);
//		
//		String pref1 = prefs.getString("pref1", getResources().getString(R.string.no_value));
//		String pref2 = prefs.getString("pref2", getResources().getString(R.string.no_value));
//		String pref3 = prefs.getString("pref3", getResources().getString(R.string.no_value));
//		
//		TextView prefLabel1 = (TextView)findViewById(R.id.pref1);
//		TextView prefLabel2 = (TextView)findViewById(R.id.pref2);
//		TextView prefLabel3 = (TextView)findViewById(R.id.pref3);
//		
//		prefLabel1.setText(pref1);
//		prefLabel2.setText(pref2);
//		prefLabel3.setText(pref3);
		return super.onCreateView(name, context, attrs);
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

	public void changeData(View v) {
		// TODO Auto-generated method stub
		
		SharedPreferences prefs = this.getPreferences(this.MODE_PRIVATE);
		
		String pref1 = "Valor 1";
		String pref2 = "Valor 2";
		String pref3 = "Valor 3";
		
		TextView prefLabel1 = (TextView)findViewById(R.id.pref1);
		TextView prefLabel2 = (TextView)findViewById(R.id.pref2);
		TextView prefLabel3 = (TextView)findViewById(R.id.pref3);
		
		prefLabel1.setText(pref1);
		prefLabel2.setText(pref2);
		prefLabel3.setText(pref3);
	}

}
