package com.guanghui.car.jingmicheck.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guanghui.car.application.MyApplication;
import com.guanghui.car.jingmicheck.Constans;
import com.guanghui.car.jingmicheck.model.DefectDepartModel;

public class DateBaseUtil {
	public static DateBaseUtil sInstance = new DateBaseUtil();
	private MySQLiteHelper helper;
	public DateBaseUtil() {
		helper = new MySQLiteHelper(MyApplication.getInstance(), MySQLiteHelper.DB_NAME);
	}

	public static DateBaseUtil getInstance() {
		if (sInstance == null) {
			sInstance = new DateBaseUtil();
		}
		return sInstance;
	}

	public void insertValues(ContentValues values) {
		SQLiteDatabase sqliteDatabase = helper.getWritableDatabase();
		// System.out.println("存储字节数组开始:" + System.currentTimeMillis());
		sqliteDatabase.replace(MySQLiteHelper.TABLE_NAME, null, values);
		// System.out.println("存储字节数组结束:" + System.currentTimeMillis());
	}
	public DefectDepartModel getDefectModel(String x, String y) {
		SQLiteDatabase sqliteDatabase = helper.getReadableDatabase();
		Cursor mCursor = sqliteDatabase.query(MySQLiteHelper.TABLE_NAME, new String[]{"areaindex", "departindex", "object"}, "areaindex = ? and departindex = ? and token = ?" , new String[]{x, y,String.valueOf(Constans.user_token)}, null, null, null,
				null);
		boolean have = mCursor.moveToFirst();
		if (have) {
			// System.out.println("读取字节数组开始:" + System.currentTimeMillis());
			byte[] bytes = mCursor.getBlob(mCursor.getColumnIndex("object"));// object字段
			// System.out.println("读取字节数组结束:" + System.currentTimeMillis());
			return byte2Defect(bytes);
		} else {
			return null;
		}

	}
	public byte[] defect2Byte(DefectDepartModel model) {
		byte[] bytes = null;
		try {
			// object to bytearray
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(model);
			// System.out.println("对象转字节数组开始:" + System.currentTimeMillis());
			bytes = bo.toByteArray();
			// System.out.println("对象转字节数组结束:" + System.currentTimeMillis());
			bo.close();
			oo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
	public DefectDepartModel byte2Defect(byte[] bytes) {
		DefectDepartModel model = null;
		try {
			// bytearray to object
			ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
			ObjectInputStream oi = new ObjectInputStream(bi);
			// System.out.println("字节转换对象开始:" + System.currentTimeMillis());
			model = (DefectDepartModel) oi.readObject();
			// System.out.println("字节转换对象结束:" + System.currentTimeMillis());
			bi.close();
			oi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

}
