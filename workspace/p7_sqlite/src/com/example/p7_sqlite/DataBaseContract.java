package com.example.p7_sqlite;

import android.provider.BaseColumns;

public final class DataBaseContract {
	public DataBaseContract(){};
	
	public static abstract class Users implements BaseColumns{
		public static final String TABLE_NAME = "users";
		public static final String COLUMN_NULLABLE_COLUMN = "users_cc";
		public static final String COLUMN_NAME_USER_CC = "users_cc";
		public static final String COLUMN_NAME_NAME = "name";
		public static final String COLUMN_NAME_EMAIL = "email";
	}
	
	public static abstract class Package implements BaseColumns{
		public static final String TABLE_NAME = "package";
		public static final String COLUMN_NULLABLE_COLUMN = "package_code";
		public static final String COLUMN_NAME_PACKAGE_CODE = "package_code";
		public static final String COLUMN_NAME_USER = "user";
		public static final String COLUMN_NAME_DATE = "date";
	}
}
