package com.example.p7_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.p7_sqlite.DataBaseContract.*;

public class EnviaDataBaseHelper extends SQLiteOpenHelper {
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA = ",";
	private static final String SQL_CREATE_USERS =
	    "CREATE TABLE " + Users.TABLE_NAME + " (" +
	    Users._ID + " INTEGER PRIMARY KEY," +
	    Users.COLUMN_NAME_USER_CC + TEXT_TYPE + COMMA +
	    Users.COLUMN_NAME_NAME + TEXT_TYPE + COMMA + 
	    Users.COLUMN_NAME_EMAIL + TEXT_TYPE +
	    " )";

	private static final String SQL_DELETE_USERS =
	    "DROP TABLE IF EXISTS " + Users.TABLE_NAME;
	
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Envia.db";

	public EnviaDataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_CREATE_USERS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_DELETE_USERS);
		onCreate(db);
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		onDowngrade(db, oldVersion, newVersion);
	}
	
	public void clearUsers(SQLiteDatabase db){
		db.execSQL(SQL_DELETE_USERS);
	}

}
