package com.guanghui.car.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 车型登记数据库
 * @author zhangyun
 *
 */
public class ChexingDengjiDB extends SQLiteOpenHelper {

	private final static String DATABASE_NAME = "CheXingDengjiDB";
	private final static int DATABASE_VERSION = 1;
	private final static String TABLE_NAME = "ShouxuDengjiTable";
	private final static String ResourceID = "ResourceID";
	private final static String ModelID = "ModelID";
	private final static String OptionConfiguration = "OptionConfiguration";
	private final static String ModConfiguration = "ModConfiguration";
	private final static String Color = "Color";
	private final static String DisplayKm = "DisplayKm";
	private final static String DisplayMiles = "DisplayMiles";
	private final static String InstrumentIsChanged = "InstrumentIsChanged";
	private final static String GuidePrice = "GuidePrice";
	private final static String LowestPrice = "LowestPrice";
	private final static String UsedPriceLow = "UsedPriceLow";
	private final static String UsedPriceHigh = "UsedPriceHigh";
	private final static String Photo1 = "Photo1";
	private final static String Photo2 = "Photo2";
	private final static String Photo3 = "Photo3";
	private final static String Photo4 = "Photo4";
	private final static String Photo5 = "Photo5";
	private final static String Photo6 = "Photo6";
	private final static String Photo7 = "Photo7";
	private final static String Photo8 = "Photo8";
	private final static String Photo9 = "Photo9";
	private final static String Photo10 = "Photo10";
	private final static String Photo11 = "Photo11";
	private final static String Photo12 = "Photo12";
	private final static String Photo13 = "Photo13";
	private final static String Photo14 = "Photo14";
	private final static String Photo15 = "Photo15";
	private final static String Photo16 = "Photo16";
	private final static String Photo17 = "Photo17";
	private final static String Photo18 = "Photo18";

	public ChexingDengjiDB(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABLE_NAME +
		 " (" + ResourceID + " TEXT,"
		 + " " + ModelID + " TEXT,"
		 + " " + OptionConfiguration + " TEXT,"
		 + " " + ModConfiguration + " TEXT,"
		 + " " + Color + " TEXT,"
		 + " " + DisplayKm + " TEXT,"
		 + " " + DisplayMiles + " TEXT,"
		 + " " + InstrumentIsChanged + " TEXT,"
		 + " " + GuidePrice + " TEXT,"
		 + " " + LowestPrice + " TEXT,"
		 + " " + UsedPriceLow + " TEXT,"
		 + " " + UsedPriceHigh + " TEXT,"
		 + " " + Photo1 + " TEXT,"
		 + " " + Photo2 + " TEXT,"
		 + " " + Photo3 + " TEXT,"
		 + " " + Photo4 + " TEXT,"
		 + " " + Photo5 + " TEXT,"
		 + " " + Photo6 + " TEXT,"
		 + " " + Photo7 + " TEXT,"
		 + " " + Photo8 + " TEXT,"
		 + " " + Photo9 + " TEXT,"
		 + " " + Photo10 + " TEXT,"
		 + " " + Photo11 + " TEXT,"
		 + " " + Photo12 + " TEXT,"
		 + " " + Photo13 + " TEXT,"
		 + " " + Photo14 + " TEXT,"
		 + " " + Photo15 + " TEXT,"
		 + " " + Photo16 + " TEXT,"
		 + " " + Photo17 + " TEXT,"
		 + " " + Photo18 + " TEXT)";
		db.execSQL(sql);		
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}
	
	/**
	 * 插入新的车型登记记录
	 * @param resourceID   商机资源ID
	 * @param modelID  	   型号ID
	 * @param optionConfiguration   选装配置
	 * @param modConfiguration      改装配置
	 * @param color		车辆颜色      0 红 1绿 2蓝 3黑 4白 5灰 6黄 7橙 8棕 9紫 100其他
	 * @param displayKm  表显里程公里
	 * @param displayMiles   表显路程公里
	 * @param instrumentIsChanged    码表改动   0 无改动 1 改动
	 * @param guidePrice   厂商指导价
	 * @param lowestPrice   当地最低价
	 * @param usedPriceLow   二手车价格下限
	 * @param usedPriceHigh   二手车价格上限
	 * @param photo1   	车头左前方照片
	 * @param photo2 	车尾右后方照片
	 * @param photo3	中控台内饰照片	
	 * @param photo4	仪表盘照片
	 * @return  插入成功后的Row ID
	 */
	public long insert(String resourceID,String modelID,String optionConfiguration,String modConfiguration,String color,String displayKm,
			String displayMiles,String instrumentIsChanged,String guidePrice,String lowestPrice,String usedPriceLow,String usedPriceHigh,
			String photo1,String photo2,String photo3,String photo4,String photo5,String photo6,String photo7,String photo8,String photo9,String photo10,String photo11,String photo12,String photo13,String photo14,String photo15,String photo16,String photo17,String photo18)
	{
		SQLiteDatabase db = this.getWritableDatabase();
//		if(isExistById(resourceID,modelID))
//		{
//			deletetByID(resourceID,modelID);
//		}
		
		if(isExistById(resourceID))
		{
			deletetByID(resourceID);
		}
		
		//不存在该ID号，则进行插入操作
	    ContentValues cv = new ContentValues();
        cv.put(ResourceID, resourceID);
        cv.put(ModelID, modelID);
        cv.put(OptionConfiguration, optionConfiguration);
        cv.put(ModConfiguration, modConfiguration);
        cv.put(Color, color);
        cv.put(DisplayKm, displayKm);
        cv.put(DisplayMiles, displayMiles);
        cv.put(InstrumentIsChanged, instrumentIsChanged);
        cv.put(GuidePrice, guidePrice);
        cv.put(LowestPrice, lowestPrice);
        cv.put(UsedPriceLow, usedPriceLow);
        cv.put(UsedPriceHigh, usedPriceHigh);
        cv.put(Photo1, photo1);
        cv.put(Photo2, photo2);
        cv.put(Photo3, photo3);
        cv.put(Photo4, photo4);
        cv.put(Photo5, photo5);
        cv.put(Photo6, photo6);
        cv.put(Photo7, photo7);
        cv.put(Photo8, photo8);
        cv.put(Photo9, photo9);
        cv.put(Photo10, photo10);
        cv.put(Photo11, photo11);
        cv.put(Photo12, photo12);
        cv.put(Photo13, photo13);
        cv.put(Photo14, photo14);
        cv.put(Photo15, photo15);
        cv.put(Photo16, photo16);
        cv.put(Photo17, photo17);
        cv.put(Photo18, photo18);
        return db.insert(TABLE_NAME, null, cv);
	}
	
	/**
	 * 根据资源ID和型号ID查找所有数据
	 * @param rid   资源ID
	 * @return
	 */
	public Cursor selectById(String rid)
	{
		SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, ResourceID +"= ?", new String[]{ rid}, null, null, null);
        return cursor;
	}
	
	/**
	 * 是否存在该资源ID号和型号ID的数据
	 * @return   true 存在  false 不存在
	 */
	public boolean isExistById(String rid,String modelID)
	{
	    SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] {ResourceID}, ResourceID +"= ? and "+ ModelID + "= ?" , new String[]{ rid, modelID }, null, null, null);
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
	 * 是否存在该资源ID号
	 * @return   true 存在  false 不存在
	 */
	public boolean isExistById(String rid)
	{
	    SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] {ResourceID}, ResourceID +"= ?" , new String[]{ rid }, null, null, null);
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
	 * 根据该资源ID号和型号ID删除对应的记录
	 * @rid 资源ID
	 * @modelID 型号ID
	 */
	public void deletetByID1(String rid,String modelID)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		String where = ResourceID +"= ? and "+ ModelID + "= ?";
		String[] whereValue ={ rid,modelID };
		db.delete(TABLE_NAME, where, whereValue);
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

