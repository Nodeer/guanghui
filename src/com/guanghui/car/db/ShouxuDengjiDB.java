package com.guanghui.car.db;

import android.content.ContentValues ;
import android.content.Context ;
import android.database.Cursor ;
import android.database.sqlite.SQLiteDatabase ;
import android.database.sqlite.SQLiteOpenHelper ;

/**
 * 手续登记数据库
 * @author zhangyun
 *
 */
public class ShouxuDengjiDB extends SQLiteOpenHelper {

	private final static String DATABASE_NAME = "ShouxuDengjiDB";
	private final static int DATABASE_VERSION = 1;
	private final static String TABLE_NAME = "ShouxuDengjiTable";
	private final static String ResourceID = "ResourceID";
	private final static String License = "License";
	private final static String BelongingTo  = "BelongingTo";
	private final static String IdentificationCode  = "IdentificationCode";
	private final static String Nature  = "Nature";
	private final static String Category  = "Category";
	private final static String BrandModel  = "BrandModel";
	private final static String EngineSerialNum  = "EngineSerialNum";
	private final static String ManufactureDate  = "ManufactureDate";
	private final static String FirstEnrolmentDate  = "FirstEnrolmentDate";
	private final static String ScrapDate  = "ScrapDate";
	private final static String OwnerType  = "OwnerType";
	private final static String EmissionStandards  = "EmissionStandards";
	private final static String RegistrationStatus  = "RegistrationStatus";
	private final static String ChangedNum  = "ChangedNum";
	private final static String ChangeType  = "ChangeType";
	private final static String PurchaseTax  = "PurchaseTax";
	private final static String InsuranceValidity  = "InsuranceValidity";
	private final static String AnnualInspectionValidity  = "AnnualInspectionValidity";
	private final static String TollsValidity  = "TollsValidity";
	private final static String IllegalScore  = "IllegalScore";
	private final static String IllegalMoney  = "IllegalMoney";
	private final static String LicenceLines  = "LicenceLines";
	private final static String OriginalKeys  = "OriginalKeys";
	private final static String IsMissingTool  = "IsMissingTool";
	private final static String MissingTools  = "MissingTools";
	private final static String HasInstruction  = "HasInstruction";
	private final static String HasMaintenanceManual  = "HasMaintenanceManual";
	private final static String LicensePhoto  = "LicensePhoto";
	
	
	public ShouxuDengjiDB(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABLE_NAME +
		 " (" + ResourceID + " TEXT,"
		 + " " + License + " TEXT,"
		 + " " + BelongingTo + " TEXT,"
		 + " " + IdentificationCode + " TEXT,"
		 + " " + Nature + " TEXT,"
		 + " " + Category + " TEXT,"
		 + " " + BrandModel + " TEXT,"
		 + " " + EngineSerialNum + " TEXT,"
		 + " " + ManufactureDate + " TEXT,"
		 + " " + FirstEnrolmentDate + " TEXT,"
		 + " " + ScrapDate + " TEXT,"
		 + " " + OwnerType + " TEXT,"
		 + " " + EmissionStandards + " TEXT,"
		 + " " + RegistrationStatus + " TEXT,"
		 + " " + ChangedNum + " TEXT,"
		 + " " + ChangeType + " TEXT,"
		 + " " + PurchaseTax + " TEXT,"
		 + " " + InsuranceValidity + " TEXT,"
		 + " " + AnnualInspectionValidity + " TEXT,"
		 + " " + TollsValidity + " TEXT,"
		 + " " + IllegalScore + " TEXT,"
		 + " " + IllegalMoney + " TEXT,"
		 + " " + LicenceLines + " TEXT,"
		 + " " + OriginalKeys + " TEXT,"
		 + " " + IsMissingTool + " TEXT,"
		 + " " + MissingTools + " TEXT,"
		 + " " + HasInstruction + " TEXT,"
		 + " " + HasMaintenanceManual + " TEXT,"
		 + " " + LicensePhoto + " TEXT)";
		db.execSQL(sql);		
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}
	
	/**
	 * 插入新的手续登记数据
	 * @param resourceID  资源ID
	 * @param license     车牌号码
	 * @param belongingTo  车牌归属地
	 * @param identificationCode 车辆识别代码
	 * @param nature  使用性质
	 * @param category 车辆类型
	 * @param brandModel 品牌型号
	 * @param engineSerialNum 发动机号码
	 * @param manufactureDate 出厂日期
	 * @param firstEnrolmentDate 首次注册登记日期
	 * @param scrapDate 强制报废年限
	 * @param ownerType 所有人（0私人 1公司 2）
	 * @param emissionStandards 排放标准（0国一 1国二 2国三 3国四 4国五）
	 * @param registrationStatus 办证状态（0可过户转籍 1只能过户 2过户转籍有次数限制 3不能过户转籍）
	 * @param changedNum 变更次数
	 * @param changeType 变更类型（1颜色变更2发动机变更3车架变更，多个用逗号隔开）
	 * @param purchaseTax 购置税
	 * @param insuranceValidity 交强险有效期
	 * @param annualInspectionValidity 年检有效期
	 * @param tollsValidity 通行费有效期
	 * @param illegalScore 违章未处理扣分
	 * @param illegalMoney 违章未处理罚款
	 * @param licenceLines 牌照额度（0含额度出售 1不含额度出售）
	 * @param originalKeys 原厂钥匙（0表示无原厂钥匙）
	 * @param isMissingTool 随车工具是否缺失（0无缺失 1缺失）
	 * @param missingTools 缺失的随车工具
	 * @param hasInstruction 车辆使用说明书（0无 1有）
	 * @param hasMaintenanceManual 车辆保养手册（0无 1有）
	 * @param licensePhoto  行驶证照片
	 */
	public long insert(String resourceID,String license,String belongingTo,String identificationCode,String nature
			,String category,String brandModel,String engineSerialNum,String manufactureDate,String firstEnrolmentDate
			,String scrapDate,String ownerType,String emissionStandards,String registrationStatus,String changedNum
			,String changeType,String purchaseTax,String insuranceValidity,String annualInspectionValidity,String tollsValidity
			,String illegalScore,String illegalMoney,String licenceLines,String originalKeys,String isMissingTool
			,String missingTools,String hasInstruction,String hasMaintenanceManual,String licensePhoto			
			) {
		SQLiteDatabase db = this.getWritableDatabase();
		if(isExistById(resourceID))
		{
			deletetByID(resourceID);
		}		
		//不存在该ID号，则进行插入操作
	    ContentValues cv = new ContentValues();
        cv.put(ResourceID, resourceID);
        cv.put(License, license);
        cv.put(BelongingTo, belongingTo);
        cv.put(IdentificationCode, identificationCode);
        cv.put(Nature, nature);
        cv.put(Category, category);
        cv.put(BrandModel, brandModel);
        cv.put(EngineSerialNum, engineSerialNum);
        cv.put(ManufactureDate, manufactureDate);
        cv.put(FirstEnrolmentDate, firstEnrolmentDate);
        cv.put(ScrapDate, scrapDate);
        cv.put(OwnerType, ownerType);
        cv.put(EmissionStandards, emissionStandards);
        cv.put(RegistrationStatus, registrationStatus);
        cv.put(ChangedNum, changedNum);
        cv.put(ChangeType, changeType);
        cv.put(PurchaseTax, purchaseTax);
        cv.put(InsuranceValidity, insuranceValidity);
        cv.put(AnnualInspectionValidity, annualInspectionValidity);
        cv.put(TollsValidity, tollsValidity);
        cv.put(IllegalScore, illegalScore);
        cv.put(IllegalMoney, illegalMoney);
        cv.put(LicenceLines, licenceLines);
        cv.put(OriginalKeys, originalKeys);
        cv.put(IsMissingTool, isMissingTool);
        cv.put(MissingTools, missingTools);
        cv.put(HasInstruction, hasInstruction);
        cv.put(HasMaintenanceManual, hasMaintenanceManual);
        cv.put(LicensePhoto, licensePhoto);
        return db.insert(TABLE_NAME, null, cv);	  
	}
	
	/**
	 * 根据资源ID查找所有数据
	 * @param rid
	 * @return
	 */
	public Cursor selectById(String rid)
	{
		SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, ResourceID +"= ?" , new String[]{ rid }, null, null, null);
        return cursor;
	}
	
	
	
	/**
	 * 是否存在该资源ID号的数据
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
	 * 根据资源ID删除对应的记录
	 * @id 资源ID
	 */
	public void deletetByID(String id)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		String where = ResourceID + "= ?";
		String[] whereValue ={ id };
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

