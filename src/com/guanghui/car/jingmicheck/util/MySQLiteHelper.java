package com.guanghui.car.jingmicheck.util;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
	public static final String TABLE_NAME = "defect_info";
	public static final String DB_NAME = "defect.db";

	public MySQLiteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	public MySQLiteHelper(Context context, String name) {
		super(context, name, null, 1);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("SQLiteDatabase onCreate");
		db.execSQL("create table if not exists " + TABLE_NAME + "(" + "id integer primary key,areaindex integer,departindex integer,token integer,object BLOB" + ")");
		db.execSQL("create unique index idx on "+TABLE_NAME+"(areaindex,departindex,token)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
