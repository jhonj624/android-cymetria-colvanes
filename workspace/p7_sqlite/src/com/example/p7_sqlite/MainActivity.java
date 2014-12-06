Ayupackage com.example.p7_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.p7_sqlite.DataBaseContract.Users;

public class MainActivity extends ActionBarActivity {
	
	// Instanciar una DB
	EnviaDataBaseHelper dbHelper;
	
	// Las db
	SQLiteDatabase dbWritter;
	SQLiteDatabase dbReader;

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

	public void createDataBase(View view){
		// Instanciar la DB
		dbHelper = new EnviaDataBaseHelper(getApplicationContext());
		
		// La db en la que se puede escribir (proceso puede ser largo, debería estar en otro hilo)
		dbWritter = dbHelper.getWritableDatabase();
		dbReader = dbHelper.getReadableDatabase();
	}
	
	public void insertRecord(View view){
		
		ContentValues values = new ContentValues();
		values.put(Users.COLUMN_NAME_USER_CC, 123456);
		values.put(Users.COLUMN_NAME_NAME, "Juan");
		values.put(Users.COLUMN_NAME_EMAIL, "juan@perez.com");
		
		long idInserted = dbWritter.insert(Users.TABLE_NAME, Users.COLUMN_NULLABLE_COLUMN, values);
		Toast.makeText(getApplicationContext(), String.valueOf(idInserted), Toast.LENGTH_SHORT).show();
	}
	
	public void showRecords(View view){
		
		String[] columnsToUse = {
				Users._ID,
				Users.COLUMN_NAME_NAME,
				Users.COLUMN_NAME_EMAIL
		};
		
		String order = Users._ID + " ASC";
		
		Cursor results = dbReader.query(Users.TABLE_NAME, columnsToUse, null, null, null, null, order);
		
		String toShow = "";
		
		results.moveToFirst();
		while(!results.isAfterLast()){
			toShow += results.getString(results.getColumnIndex(Users._ID));
			toShow += "|"+results.getString(results.getColumnIndex(Users.COLUMN_NAME_NAME));
			toShow += "|"+results.getString(results.getColumnIndex(Users.COLUMN_NAME_EMAIL))+"\n";
			results.moveToNext();
		}
		
		Toast.makeText(getApplicationContext(), toShow, Toast.LENGTH_SHORT).show();
	}
	
	public void restartDatabase(View view){
		String selection = Users.COLUMN_NAME_NAME+" LIKE ?";
		String[] selectionArgs = {"%"};
		
		dbReader.delete(Users.TABLE_NAME, selection, selectionArgs);
		
		createDataBase(view);
	}
}
