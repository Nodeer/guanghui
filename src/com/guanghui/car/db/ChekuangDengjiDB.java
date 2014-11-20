package com.guanghui.car.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 车况登记数据库
 * @author zhangyun
 *
 */
public class ChekuangDengjiDB extends SQLiteOpenHelper {

	private final static String DATABASE_NAME = "ChekuangDengjiDB";
	private final static int DATABASE_VERSION = 1;
	private final static String TABLE_NAME = "ChekuangDengjiTable";
	private final static String ResourceID = "ResourceID";
	private final static String AppearanceScore = "AppearanceScore";
	private final static String InteriorScore = "InteriorScore";
	private final static String AccidentLlevel = "AccidentLlevel";
	private final static String SpecialCar = "SpecialCar";
	private final static String ComprehensiveScore = "ComprehensiveScore";
	private final static String Comments = "Comments";
	
	public ChekuangDengjiDB(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABLE_NAME +
		 " (" + ResourceID + " TEXT,"
		 + " " + AppearanceScore + " TEXT,"
		 + " " + InteriorScore + " TEXT,"
		 + " " + AccidentLlevel + " TEXT,"
		 + " " + SpecialCar + " TEXT,"
		 + " " + ComprehensiveScore + " TEXT,"
		 + " " + Comments + " TEXT)";
		db.execSQL(sql);		
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}
	

	/**
	 * 插入一条车况检测信息
	 * @param resourceID    资源ID号
	 * @param appearanceScore   外观评级
	 * @param interiorScore     内饰评级
	 * @param accidentLlevel    事故评级
	 * @param specialCar        特殊车评级
	 * @param comprehensiveScore   综合评级
	 * @param comments    备注
	 * @return        ROW ID
	 */
	public long insert(String resourceID,
			String appearanceScore,
			String interiorScore,
			String accidentLlevel,
			String specialCar,
			String comprehensiveScore,
			String comments)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		if(isExistById(resourceID))
		{
			deletetByID(resourceID);
		}		
		//不存在该ID号，则进行插入操作
	    ContentValues cv = new ContentValues();
        cv.put(ResourceID, resourceID);
        cv.put(AppearanceScore, appearanceScore);
        cv.put(InteriorScore, interiorScore);
        cv.put(AccidentLlevel, accidentLlevel);
        cv.put(SpecialCar, specialCar);
        cv.put(ComprehensiveScore, comprehensiveScore);
        cv.put(Comments, comments);
        return db.insert(TABLE_NAME, null, cv);	  
	}
	
	/**
	 * 根据资源ID查找所有数据
	 * @param rid   资源ID
	 * @return
	 */
	public Cursor selectById(String rid)
	{
		SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, ResourceID +"= ? ", new String[]{ rid }, null, null, null);
        return cursor;
	}
	
	/**
	 * 是否存在该资源ID号的数据
	 * @return   true 存在  false 不存在
	 */
	public boolean isExistById(String rid)
	{
	    SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] {ResourceID}, ResourceID +"= ? " , new String[]{ rid }, null, null, null);
        if(cursor.getCount()!=0)
        {
            cursor.close();
            return true;
        }
        else
        {
            cursor.close();
            return false;
        }
	}
	
	
	/**
	 * 根据该资源ID号删除对应的记录
	 * @rid 资源ID
	 */
	public void deletetByID(String rid)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		String where = ResourceID +"= ? ";
		String[] whereValue ={ rid };
		db.delete(TABLE_NAME, where, whereValue);
	}
	
	
	/**
	 * 删除所有的记录
	 */
	public void deletetALL()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NAME, null, null);
	}	

	
	public void close()
    {       
        this.getWritableDatabase().close();
    }
	
}

