package com.guanghui.car.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 新的车型登记界面
 * @author cool_yunyun
 *
 */
public class NewChexingDengjiDB extends SQLiteOpenHelper{

	private final static String DATABASE_NAME = "NewChexingDengjiDB";
	private final static int DATABASE_VERSION = 4;
	private final static String TABLE_NAME = "NewChexingDengjiTable";
	//字段
	private final static String ResourceID = "ResourceID";
	private final static String edt_vin = "edt_vin";
	private final static String spineer_car_leixing = "spineer_car_leixing";
	private final static String spineer_car_chexing1 = "spineer_car_chexing1";
	private final static String spineer_car_chexing2 = "spineer_car_chexing2";
	private final static String spineer_car_chexing3 = "spineer_car_chexing3";
	private final static String spineer_car_chexing4 = "spineer_car_chexing4";
	private final static String spineer_car_chexing5 = "spineer_car_chexing5";
	private final static String checkbox_other = "checkbox_other";
	private final static String spineer_other1 = "spineer_other1";
	private final static String spineer_other2 = "spineer_other2";
	private final static String spineer_other3 = "spineer_other3";
	private final static String spineer_other4 = "spineer_other4";
	private final static String spineer_other5 = "spineer_other5";
	private final static String spineer_other6 = "spineer_other6";
	private final static String spineer_other7 = "spineer_other7";
	private final static String spineer_other8 = "spineer_other8";
	private final static String spineer_other9 = "spineer_other9";
	private final static String spineer_car_biansuxiang = "spineer_car_biansuxiang";
	private final static String edt_pailiang = "edt_pailiang";
	private final static String edt_gonglv = "edt_gonglv";
	private final static String radio_ranliao = "radio_ranliao";
	private final static String radio_yaoshi = "radio_yaoshi";
	private final static String radio_abs = "radio_abs";
	private final static String radio_zhuli = "radio_zhuli";
	private final static String radio_lunquan = "radio_lunquan";
	private final static String radio_qinang = "radio_qinang";
	private final static String check_qinang = "check_qinang";
	private final static String radio_tianchuang = "radio_tianchuang";
	private final static String radio_kongtiao = "radio_kongtiao";
	private final static String check_kongtiao = "check_kongtiao";
	private final static String radio_menchuang = "radio_menchuang";
	private final static String radio_houshijing = "radio_houshijing";
	private final static String radio_zuoyi_caizhi = "radio_zuoyi_caizhi";
	private final static String check_zuoyi = "check_zuoyi";
	private final static String radio_fangxiangpan = "radio_fangxiangpan";
	private final static String check_fangxiangpan = "check_fangxiangpan";
	private final static String radio_dadeng = "radio_dadeng";
	private final static String radio_dvd = "radio_dvd";
	private final static String radio_yejingpin = "radio_yejingpin";
	private final static String check_yejingpin = "check_yejingpin";	
	private final static String radio_other_peizhi = "radio_other_peizhi";
	private final static String check_other_peizhi = "check_other_peizhi";
	private final static String edt_xuanzhuang_peizhi = "edt_xuanzhuang_peizhi";
	
	public NewChexingDengjiDB(Context context, String name,
			CursorFactory factory, int version) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}

	public NewChexingDengjiDB(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABLE_NAME +
				 " (" + ResourceID + " TEXT,"
				 + " " + edt_vin + " TEXT,"
				 + " " + spineer_car_leixing + " TEXT,"
				 + " " + spineer_car_chexing1 + " TEXT,"
				 + " " + spineer_car_chexing2 + " TEXT,"
				 + " " + spineer_car_chexing3 + " TEXT,"
				 + " " + spineer_car_chexing4 + " TEXT,"
				 + " " + spineer_car_chexing5 + " TEXT,"
				 + " " + checkbox_other + " TEXT,"
				 + " " + spineer_other1 + " TEXT,"
				 + " " + spineer_other2 + " TEXT,"
				 + " " + spineer_other3 + " TEXT,"
				 + " " + spineer_other4 + " TEXT,"
				 + " " + spineer_other5 + " TEXT,"
				 + " " + spineer_other6 + " TEXT,"
				 + " " + spineer_other7 + " TEXT,"
				 + " " + spineer_other8 + " TEXT,"
				 + " " + spineer_other9 + " TEXT,"
				 + " " + spineer_car_biansuxiang + " TEXT,"
				 + " " + edt_pailiang + " TEXT,"
				 + " " + edt_gonglv + " TEXT,"
				 + " " + radio_ranliao + " TEXT,"
				 + " " + radio_yaoshi + " TEXT,"
				 + " " + radio_abs + " TEXT,"
				 + " " + radio_zhuli + " TEXT,"
				 + " " + radio_lunquan + " TEXT,"
				 + " " + radio_qinang + " TEXT,"
				 + " " + check_qinang + " TEXT,"
				 + " " + radio_tianchuang + " TEXT,"
				 + " " + radio_kongtiao + " TEXT,"
				 + " " + check_kongtiao + " TEXT,"
				 + " " + radio_menchuang + " TEXT,"
				 + " " + radio_houshijing + " TEXT,"
				 + " " + radio_zuoyi_caizhi + " TEXT,"
				 + " " + check_zuoyi + " TEXT,"
				 + " " + radio_fangxiangpan + " TEXT,"
				 + " " + check_fangxiangpan + " TEXT,"
				 + " " + radio_dadeng + " TEXT,"
				 + " " + radio_dvd + " TEXT,"
				 + " " + radio_yejingpin + " TEXT,"
				 + " " + check_yejingpin + " TEXT,"
				 + " " + radio_other_peizhi + " TEXT,"
				 + " " + check_other_peizhi + " TEXT,"
				 + " " + edt_xuanzhuang_peizhi + " TEXT)";
				db.execSQL(sql);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}	
	
	/**
	 * 插入一条新的车型检测记录
	 * @param resourceID
	 * @param medt_vin
	 * @param mspineer_car_leixing
	 * @param mspineer_car_chexing1
	 * @param mspineer_car_chexing2
	 * @param mspineer_car_chexing3
	 * @param mspineer_car_chexing4
	 * @param mspineer_car_chexing5
	 * @param mcheckbox_other
	 * @param mspineer_other1
	 * @param mspineer_other2
	 * @param mspineer_other3
	 * @param mspineer_other4
	 * @param mspineer_other5
	 * @param mspineer_other6
	 * @param mspineer_other7
	 * @param mspineer_other8
	 * @param mspineer_other9
	 * @param mspineer_car_biansuxiang
	 * @param medt_pailiang
	 * @param medt_gonglv
	 * @param mradio_ranliao
	 * @param mradio_yaoshi
	 * @param mradio_abs
	 * @param mradio_zhuli
	 * @param mradio_lunquan
	 * @param mradio_qinang
	 * @param mcheck_qinang
	 * @param mradio_tianchuang
	 * @param mradio_kongtiao
	 * @param mcheck_kongtiao
	 * @param mradio_menchuang
	 * @param mradio_houshijing
	 * @param mradio_zuoyi_caizhi
	 * @param mcheck_zuoyi
	 * @param mradio_fangxiangpan
	 * @param mcheck_fangxiangpan
	 * @param mradio_dadeng
	 * @param mradio_dvd
	 * @param mradio_yejingpin
	 * @param mcheck_yejingpin
	 * @param mradio_other_peizhi
	 * @param mcheck_other_peizhi
	 * @param medt_xuanzhuang_peizhi
	 * @return 插入成功后的Row ID
	 */
	public long insert(String resourceID,String  medt_vin,String mspineer_car_leixing,String mspineer_car_chexing1,String mspineer_car_chexing2,String mspineer_car_chexing3,String mspineer_car_chexing4,
			String mspineer_car_chexing5,String mcheckbox_other,String mspineer_other1,String mspineer_other2,String mspineer_other3,String mspineer_other4,String mspineer_other5,String mspineer_other6,
			String mspineer_other7,String mspineer_other8,String mspineer_other9,String mspineer_car_biansuxiang,String medt_pailiang,String medt_gonglv,String mradio_ranliao,String mradio_yaoshi,
			String mradio_abs,String mradio_zhuli,String mradio_lunquan,String mradio_qinang,String mcheck_qinang,String mradio_tianchuang,String mradio_kongtiao,String mcheck_kongtiao,
			String mradio_menchuang,String mradio_houshijing,String mradio_zuoyi_caizhi,String mcheck_zuoyi,String mradio_fangxiangpan,String mcheck_fangxiangpan,String mradio_dadeng,String mradio_dvd,
			String mradio_yejingpin,String mcheck_yejingpin,String mradio_other_peizhi,String mcheck_other_peizhi,String medt_xuanzhuang_peizhi)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		if(isExistById(resourceID))
		{
			deletetByID(resourceID);
		}
		//不存在该ID号，则进行插入操作
	    ContentValues cv = new ContentValues();
        cv.put(ResourceID, resourceID);
        cv.put(edt_vin, medt_vin);
        cv.put(spineer_car_leixing, mspineer_car_leixing);
        cv.put(spineer_car_chexing1, mspineer_car_chexing1);
        cv.put(spineer_car_chexing2, mspineer_car_chexing2);
        cv.put(spineer_car_chexing3, mspineer_car_chexing3);
        cv.put(spineer_car_chexing4, mspineer_car_chexing4);
        cv.put(spineer_car_chexing5, mspineer_car_chexing5);
        cv.put(checkbox_other, mcheckbox_other);
        cv.put(spineer_other1, mspineer_other1);
        cv.put(spineer_other2, mspineer_other2);
        cv.put(spineer_other3, mspineer_other3);
        cv.put(spineer_other4, mspineer_other4);
        cv.put(spineer_other5, mspineer_other5);
        cv.put(spineer_other6, mspineer_other6);
        cv.put(spineer_other7, mspineer_other7);
        cv.put(spineer_other8, mspineer_other8);
        cv.put(spineer_other9, mspineer_other9);
        cv.put(spineer_car_biansuxiang, mspineer_car_biansuxiang);
        cv.put(edt_pailiang, medt_pailiang);
        cv.put(edt_gonglv, medt_gonglv);
        cv.put(radio_ranliao, mradio_ranliao);
        cv.put(radio_yaoshi, mradio_yaoshi);
        cv.put(radio_abs, mradio_abs);
        cv.put(radio_zhuli, mradio_zhuli);
        cv.put(radio_lunquan, mradio_lunquan);
        cv.put(radio_qinang, mradio_qinang);
        cv.put(check_qinang, mcheck_qinang);
        cv.put(radio_tianchuang, mradio_tianchuang);
        cv.put(radio_kongtiao, mradio_kongtiao);
        cv.put(check_kongtiao, mcheck_kongtiao);
        cv.put(radio_menchuang, mradio_menchuang);
        cv.put(radio_houshijing, mradio_houshijing);
        cv.put(radio_zuoyi_caizhi, mradio_zuoyi_caizhi);
        cv.put(check_zuoyi, mcheck_zuoyi);
        cv.put(radio_fangxiangpan, mradio_fangxiangpan);
        cv.put(check_fangxiangpan, mcheck_fangxiangpan);
        cv.put(radio_dadeng, mradio_dadeng);
        cv.put(radio_dvd, mradio_dvd);
        cv.put(radio_yejingpin, mradio_yejingpin);
        cv.put(check_yejingpin, mcheck_yejingpin);
        cv.put(radio_other_peizhi, mradio_other_peizhi);        
        cv.put(check_other_peizhi, mcheck_other_peizhi);
        cv.put(edt_xuanzhuang_peizhi, medt_xuanzhuang_peizhi);
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
	 * 是否存在该资源ID的数据
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
