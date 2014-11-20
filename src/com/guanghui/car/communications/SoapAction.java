package com.guanghui.car.communications;

/**
 * Soap用到的访问soap地址
 * 
 * @author 张云
 * 
 */
public class SoapAction {
	/**
	 * 服务器地址
	 */
	public static String host = "http://u.cgacar.com:66/";
	/**
	 * 访问服务地址
	 */
	public static String url = host + "/api.asmx";
	/**
	 * 访问服务的命名空间
	 */
	public static String nameSpace = "http://tempuri.org/";
	/**
	 * 用户登录的方法
	 */
	public static String Login = "Login";
	/**
	 * 获得用户任务的方法
	 */
	public static String GetResources = "GetResources";
	/**
	 * 获得品牌的方法
	 */
	public static String GetBrands = "GetBrands";
	/**
	 * 获得车型的方法
	 */
	public static String GetCatalogs = "GetCatalogs";
	/**
	 * 获得车型系列的方法
	 */
	public static String GetSeriesList = "GetSeriesList";
	/**
	 * 获得车型具体系列的方法
	 */
	public static String GetSeries = "GetSeries";
	/**
	 * 获取门店列表的方法
	 * */
	public static String GetShopsList = "GetShops";
	/**
	 * 获取区域列表的方法
	 * */
	public static String GetRegionsList = "GetRegions";
	/**
	 * 获取来源列表的方法
	 * */
	public static String GetResourceSources = "GetResourceSources";
	/**
	 * 新建任务的方法
	 * */
	public static String NewResource = "NewResource";
	/**
	 * 获取商机资源信息的方法
	 * */
	public static String GetResource = "GetResource";
	/**
	 * 保存车况检测信息
	 * **/
	public static String SaveCarState = "SaveCarState";
	/**
	 * 获取客户信息
	 * */
	public static String GetCustomer = "GetCustomer";
	/**
	 * 根据筛选条件定位到具体的车型集合
	 * */
	public static String GetModels = "GetModels";
	/**
	 * 根据筛选条件定位到具体的车型
	 * */
	public static String GetModel = "GetModel";
	/**
	 * 发送无可选项信息
	 * */
	public static String NewNoOptions = "NewNoOptions";
	/**
	 * 获取手续登记信息
	 */
	public static String GetEnrolment = "GetEnrolment";
	/**
	 * 获取综合评级信息
	 */
	public static String GetCarState = "GetCarState";
	/**
	 * 获取车型信息
	 */
	public static String GetResourceModel = "GetResourceModel";
	/**
	 * 测完毕状态更新
	 */
	public static String DetectionFinish = "DetectionFinish";	
	/**
	 * 保存车型登记信息
	 */
	public static String SaveResourceModel = "SaveResourceModel";	
	
	/**
	 * 保存车型登记的部分信息
	 */
	public static String SaveResourceModel2 = "SaveResourceModel2";	
	
	/**
	 * 保存手续等级信息
	 * */
	public static String SaveEnrolment = "SaveEnrolment";
	
	
	/**
	 * 补全手续等级信息
	 * */
	public static String SaveEnrolment2 = "SaveEnrolment2";
	
	/**
	 * 设置任务状态
	 */
	public static String SetResourceStatus = "SetResourceStatus";
	/**
	 * 获得车牌归属地
	 */
	public static String GetLicenseAttribution = "GetLicenseAttribution";
	/**
	 * 获得强制报废年限
	 */
	public static String GetDiscardedLimit = "GetDiscardedLimit";
	/**
	 * 拍照后图片ocr接口
	 */
	public static String LicenseRecognition = "LicenseRecognition";
	
	/**
	 * 查询车价接口
	 */
	public static String GetCarPrice = "GetCarPrice";
	
	/**
	 * 修改用户密码
	 */
	public static String ModifyPassword = "ModifyPassword";

	/**
	 * 版本升级
	 */
	public static String CheckVersion = "CheckVersion";
	
	/**
	 * 获取新闻列表
	 */
	public static String GetNewsList = "GetNewsList";
	
	/**
	 * 获取新闻信息
	 */
	public static String GetNews = "GetNews";
	
	/**
	 * 获取通知列表
	 */
	public static String GetMessages = "GetMessages";
	
	/**
	 * 获取通知信息
	 */
	public static String GetMessage = "GetMessage";
	
	/**
	 * 获取资源信息
	 */
	public static String GetResourceDetail = "GetResourceDetail";
	
	/**
	 * 获取二手车价格区间信息
	 */
	public static String GetUsedCarPrice = "GetUsedCarPrice";
	
	/**
	 * 获取排放标准
	 */
	public static String GetEmission = "GetEmission";
	
	/**
	 * 发送心跳包，检测用户是否在线
	 */
	public static String Heartbeat = "Heartbeat";
	
	/**
	 * 解析VIN码
	 */
	public static String VINParse = "VINParse";
	
	
	public static String GetManufacturers2 = "GetManufacturers2";
	
	public static String GetCatalogs2 = "GetCatalogs2";
	
	public static String GetSeriesList2 = "GetSeriesList2";
	
	public static String GetModels2 = "GetModels2";
	
	/**
	 * 取消检测任务
	 */
	public static String CancelDetection = "CancelDetection";
	
	/**
	 * 根据VIN码获取最新收购价格 和次数
	 */
	public static String QueryResourceCount = "QueryResourceCount";
	
	
	/**
	 * 上传精密检测信息的接口
	 */
	public static String SavePrecision = "SavePrecision";
	
	
	/**
	 * 获取门店报价
	 */
	public static String QueryQuoteShop = "QueryQuoteShop";
	
	
	
}
