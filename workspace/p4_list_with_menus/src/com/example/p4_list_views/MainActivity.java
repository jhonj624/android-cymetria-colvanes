package com.example.p4_list_views;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity implements CountriesFragment.listOfCountries {

	private CountriesFragment firstList;
	private CountriesFragmentDynamic secondList;
	
	private FragmentManager manager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		manager = getSupportFragmentManager();
		
		if(findViewById(R.id.fragment_1_container) != null){
			firstList = new CountriesFragment();
			
			
			FragmentTransaction transaccion = manager.beginTransaction();
			transaccion = transaccion.add(R.id.fragment_1_container, firstList);
			transaccion.commit();
			
			//getSupportFragmentManager().beginTransaction().add(R.id.fragment_1_container, firstList).commit();
		}
		
		if(findViewById(R.id.fragment_2_container) != null){
			secondList = new CountriesFragmentDynamic();
			
			manager.beginTransaction().add(R.id.fragment_2_container, secondList, "dynamic_fragment").commit();
		}
	}
	
	public void loadFragments(View view){
		if(findViewById(R.id.fragment_1_container) != null){
			firstList = new CountriesFragment();
			
			
			FragmentTransaction transaccion = manager.beginTransaction();
			transaccion = transaccion.add(R.id.fragment_1_container, firstList);
			transaccion.commit();
			
			//getSupportFragmentManager().beginTransaction().add(R.id.fragment_1_container, firstList).commit();
		}
		
		if(findViewById(R.id.fragment_2_container) != null){
			secondList = new CountriesFragmentDynamic();
			
			manager.beginTransaction().add(R.id.fragment_2_container, secondList, "dynamic_fragment").commit();
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

	@Override
	public void clickedElement(String element) {
		// TODO Auto-generated method stub
		
		secondList.updateList(element);
	}

	public void clickedElement2(String element) {
		// TODO Auto-generated method stub
		
		secondList.updateList(element);
	}
}
