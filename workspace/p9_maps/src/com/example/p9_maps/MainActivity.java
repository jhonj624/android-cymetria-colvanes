package com.example.p9_maps;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends ActionBarActivity {

	private GoogleMap map;
	
	private final LatLng BOGOTA = new LatLng(4, -74);
	private final LatLng TUNJA = new LatLng(5.6,-73.4);
	
	Marker bogMarker;
	
	OnMarkerDragListener markerLongListener = new OnMarkerDragListener() {
		
		@Override
		public void onMarkerDragStart(Marker marker) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onMarkerDragEnd(Marker marker) {
			// TODO Auto-generated method stub
			//marker.remove();
			Geocoder coder = new Geocoder(getApplicationContext(), Locale.getDefault());
			
			List<Address> addresses = null;
			String title = "";
			try {
				addresses = coder.getFromLocation(marker.getPosition().latitude, marker.getPosition().longitude, 2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				title = "No hay dirección";
			}
			if(addresses != null && addresses.size()>0){
				Address first = addresses.get(0);
				title = String.format("%s, %s, %s",
						first.getMaxAddressLineIndex() > 0 ? first.getAddressLine(0) : "-",
						first.getLocality(),
						first.getCountryName());
			}else{
				title = "No resultados";
			}
			
			marker.setTitle(title);
		}
		
		@Override
		public void onMarkerDrag(Marker marker) {
			// TODO Auto-generated method stub
			marker.setSnippet(marker.getPosition().toString());
			marker.showInfoWindow();
		}
	};
	
	OnMapClickListener clickListener = new OnMapClickListener() {
		
		@Override
		public void onMapClick(LatLng point) {
			Marker newMarker = map.addMarker(new MarkerOptions().position(point).title("Agregado"));
			newMarker.setDraggable(true);
		}
	};
	
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
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		
		bogMarker = map.addMarker(new MarkerOptions().position(BOGOTA).title("Bogotá").snippet("La capital"));
		
		Marker tunMarker = map.addMarker(new MarkerOptions().position(TUNJA).title("Tunja").snippet("Snippet 2"));
		
		map.addMarker(new MarkerOptions().position(BOGOTA));
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(BOGOTA,12));
		
		map.setOnMapClickListener(clickListener);
		map.setOnMarkerDragListener(markerLongListener);
		
		map.setMyLocationEnabled(true);
		
		LocationManager myManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		LocationListener myListener = new LocationListener() {
			
			@Override
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLocationChanged(Location arg0) {
				// TODO Auto-generated method stub
				bogMarker.setPosition(new LatLng(
						arg0.getLatitude(), 
						arg0.getLongitude()));
			}
		}; 
		
		myManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,myListener);
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
}
