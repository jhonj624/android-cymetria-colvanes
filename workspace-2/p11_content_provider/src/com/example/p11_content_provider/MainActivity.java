package com.example.p11_content_provider;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	String[] projection;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    public void search(View view){
    	EditText searchInput = (EditText)findViewById(R.id.editTextSearch);
    	String searchStr = searchInput.getText().toString();
    	
    	if(!searchStr.isEmpty()){
    		Toast.makeText(getApplicationContext(), getResources().getString(R.string.searching)+searchStr, Toast.LENGTH_SHORT).show();
    		
    		ListView resultsList = (ListView)findViewById(R.id.listViewResults);
    		
    		String selectionClause = UserDictionary.Words.WORD + " LIKE ?";
    		
    		String[] selectionArgs = {"%"+searchStr+"%"};
    		
    		String sortOrder = UserDictionary.Words.WORD + " ASC";
            
            projection = new String[] {
    			UserDictionary.Words._ID,
    			UserDictionary.Words.WORD,
    			UserDictionary.Words.LOCALE
    		};
    		
    		Cursor resultsCursor = getContentResolver().query(
					    				UserDictionary.Words.CONTENT_URI, 
					    				projection, 
					    				selectionClause, 
					    				selectionArgs, 
					    				sortOrder);

			List<MyWord> words = new ArrayList<MyWord>();
			
    		if(resultsCursor != null && resultsCursor.getCount() > 0){
    			resultsCursor.moveToFirst();
    			
    			int indexId = resultsCursor.getColumnIndex(UserDictionary.Words._ID);
    			int indexName = resultsCursor.getColumnIndex(UserDictionary.Words.WORD);
    			int indexLocale = resultsCursor.getColumnIndex(UserDictionary.Words.LOCALE);
    			do{
    				MyWord word = new MyWord(
    						resultsCursor.getInt(indexId),
    						resultsCursor.getString(indexName),
    						resultsCursor.getString(indexLocale));
    				words.add(word);
    			}while(resultsCursor.moveToNext());
    			
    		}else{
    			MyWord noResults = new MyWord(
    					Integer.valueOf(getResources().getString(R.string.word_id_empty)), 
    					getResources().getString(R.string.word_name_empty),
    					getResources().getString(R.string.word_locale_empty));
    			words.add(noResults);
    		}
    		
    		MyWord[] wordArray = words.toArray(new MyWord[words.size()]);
			MyCustomAdapter myAdapter = new MyCustomAdapter(
					this,
					wordArray);

			resultsList.setAdapter(myAdapter);
    		
    	}else{
    		Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_search_input), Toast.LENGTH_SHORT).show();
    	}
    }
}
