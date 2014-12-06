package com.example.p4_list_views;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p4_list_views.RenameItemDialog.Renamer;

public class CountriesFragment extends ListFragment implements Renamer {
	
	//private String[] countries = {"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};
	private String[] countries;
	List<String> countriesList;
	
	listOfCountries sender;
	
	MyAdapter theAdapter;
	
	public interface listOfCountries{
		public void clickedElement(String element);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.list_fragment, container, false);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
//		Toast itemClicked = Toast.makeText(getActivity().getApplicationContext(), "Click en p:"+position+" e:"+countries[position], Toast.LENGTH_SHORT);
//		itemClicked.show();
		
		sender.clickedElement(countries[position]);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		countries = getResources().getStringArray(R.array.countries_array);
		countriesList = new ArrayList<String>();
		for(int i=0; i<countries.length; i++){
			countriesList.add(countries[i]);
		}
		theAdapter = new MyAdapter(
				getActivity(), 
				android.R.layout.simple_list_item_1, 
				countriesList); 
		
		setListAdapter(theAdapter);
//				new ArrayAdapter<String>(
//						getActivity(),
//						android.R.layout.simple_list_item_1,
//						countries));
	}
	
	public class MyAdapter extends ArrayAdapter<String>{
		private List<String> elements;
		private Context theContext;
		public MyAdapter(Context context, int resource, List<String> objects) {
			super(context, resource, objects);
			theContext = context;
			elements = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) theContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View toReturn = inflater.inflate(R.layout.row_list, parent, false);
			TextView name = (TextView)toReturn.findViewById(R.id.itemNameRow);
			TextView index = (TextView)toReturn.findViewById(R.id.itemIndexRow);
			name.setText(elements.get(position));
			index.setText("elements["+position+"]");
			return toReturn;
		}
		
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		// Una vez haya vista creada, podemos asignar el menu de contexto 
		registerForContextMenu(getListView());
	}

	@Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    
    // This makes sure that the container activity has implemented
    // the callback interface. If not, it throws an exception
    try {
        sender = (listOfCountries) activity;
    } catch (ClassCastException e) {
        throw new ClassCastException(activity.toString()
                + " la clase no ha implementado la interfaz");
    }
  }
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		
		MenuInflater contextMenu = getActivity().getMenuInflater();
		contextMenu.inflate(R.menu.context_menu, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	    final int index = info.position;
		
		switch(item.getItemId()){
		case R.id.do_toast:
			Toast.makeText(getActivity(), "Toast: "+index+"-"+countries[index], Toast.LENGTH_SHORT).show();
			// Ojo con esto, se puede pero no es recomendado...
			// ((MainActivity)getActivity()).clickedElement2(countries[index]);
			break;
		case R.id.do_edit:
//			Toast.makeText(getActivity(), "Edit: "+index+"-"+countries[index], Toast.LENGTH_SHORT).show();
			RenameItemDialog myDialog = new RenameItemDialog();
			myDialog.theItemPosition = index;
			myDialog.theItemName = countries[index];
			myDialog.theFragment = this;
			myDialog.show(getFragmentManager(), "dialog");
			break;
		case R.id.do_delete:
			AlertDialog.Builder builderConfirm = new AlertDialog.Builder(getActivity());
			builderConfirm.setMessage("Seguro que desea eliminar el elemento: "+countries[index]+"?");
			builderConfirm.setNegativeButton(R.string.edit_dialog_negative_button, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getActivity(), "Cancelado", Toast.LENGTH_SHORT).show();
				}
			});
			builderConfirm.setPositiveButton("Eliminar", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					countriesList.remove(index);
					theAdapter.notifyDataSetChanged();
					Toast.makeText(getActivity(), "Eliminado", Toast.LENGTH_SHORT).show();
				}
			});
			builderConfirm.create().show();
			
			break;
		
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void rename(int pos, String newName) {
		this.countriesList.set(pos, newName);
		theAdapter.notifyDataSetChanged();
		Toast.makeText(getActivity(), "Aceptando:"+newName, Toast.LENGTH_SHORT).show();
	}
}
