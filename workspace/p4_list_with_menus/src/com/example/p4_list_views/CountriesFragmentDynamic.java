package com.example.p4_list_views;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CountriesFragmentDynamic extends ListFragment {

	private String[] countries = {};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.list_fragment, container, false);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		Toast itemClicked = Toast.makeText(getActivity().getApplicationContext(), "Click dinámico en p:"+position+" e:"+countries[position], Toast.LENGTH_SHORT);
		itemClicked.show();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, countries));
	}

	public void updateList(String country){

		List<String> countriesList = new LinkedList<String>();
		if(Arrays.asList(countries).contains(country)){
			for(String element : countries){
				if(!country.equals(element)){
					countriesList.add(element);
				}
			}
		}else{
			for(String element : countries){
				countriesList.add(element);
			}
			countriesList.add(country);
		}

		countries = (String[]) countriesList.toArray(new String[countriesList.size()]);
		
		setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, countries));
	}
}
