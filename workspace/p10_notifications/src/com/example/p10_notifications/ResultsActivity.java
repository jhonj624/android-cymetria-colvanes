package com.example.p10_notifications;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultsActivity extends ActionBarActivity {
	
	Intent myIntent;

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		TextView notificationId = (TextView)findViewById(R.id.notificationId);
		TextView title = (TextView)findViewById(R.id.title);
		TextView content = (TextView)findViewById(R.id.content);
		ImageView icon = (ImageView)findViewById(R.id.notificationIcon);
		
		myIntent = getIntent();
		int notificationIdInt = (int)myIntent.getExtras().get("id");
		notificationId.setText(String.valueOf(notificationIdInt));
		String titleString = (String)myIntent.getExtras().get("title");
		title.setText(titleString);
		String contentString = (String)myIntent.getExtras().get("content");
		content.setText(contentString);
		int iconId = (int)myIntent.getExtras().get("iconId");
		icon.setImageResource(iconId);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.results, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_results,
					container, false);
			return rootView;
		}
	}

}
