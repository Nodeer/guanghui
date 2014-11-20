package com.guanghui.car;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.ksoap2.serialization.SoapObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.guanghui.car.common.PictureUtil;
import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppConfig;
import com.guanghui.car.config.AppPreference;
import com.guanghui.car.db.ChekuangDengjiDB;
import com.guanghui.car.db.ChexingDengjiDB;
import com.guanghui.car.db.NewChexingDengjiDB;




/**
 * 手续登记后根据HistoryResourceID来初始化其他界面
 * 车型识别以及车况检测
 * 这里将得到的数据插入到数据库中
 * @author zhangyun
 *
 */
public class ShouXuDengJiHistory {
	
	private Context context;
	private MyHandler car_model_handler;
	private SoapMgr car_model_mgr;
	private SoapObject car_model_backSoapObject;
	
	private MyHandler car_jingmi_handler;
	private SoapMgr car_jingmi_mgr;
	private SoapObject car_jingmi_backSoapObject;
	
	private MyHandler car_state_handler;
	private SoapMgr car_state_mgr;
	private SoapObject car_state_backSoapObject;
	
	private String HistoryResourceID;
	private Handler handler;
	
	

	public ShouXuDengJiHistory(Context context,String HistoryResourceID,Handler handler)
	{
		this.context = context;
		this.HistoryResourceID = HistoryResourceID;
		this.handler = handler;
		getcarModelInfo();
	}
	
	
	/**
	 * 获取车型登记信息
	 */
	private void getcarModelInfo() {
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace,
				"GetResourceModel");
		soapParameter.addProperty("ResourceID",  HistoryResourceID );
		car_model_handler = new MyHandler() {
			@Override
			public void success(Message msg) {
					car_model_backSoapObject = car_model_mgr
						.getServiceBackSoapObject();
					if (car_model_backSoapObject != null) {
						String Retcode = ((SoapObject) car_model_backSoapObject
								.getProperty(0)).getProperty("Retcode").toString();
						// 0成功，1失败
						if (Retcode.equals("0")) {
							SoapObject Items = ((SoapObject) ((SoapObject) car_model_backSoapObject
									.getProperty(0)).getProperty("ResourceModel"));
							
							//---------------------初始化车型识别信息----------------------------
							
							
							String Id = ((SoapObject) Items).getProperty("Id").toString();
							String VIN = ((SoapObject) Items).getProperty("VIN").toString();
							String Category = ((SoapObject) Items).getProperty("Category").toString();
							String Brand = ((SoapObject) Items).getProperty("Brand").toString();
							
							String ModelType = ((SoapObject) Items).getProperty("ModelType").toString();
							
							String Model = ((SoapObject) Items).getProperty("Model").toString();
							String BodyForm = ((SoapObject) Items).getProperty("BodyForm").toString();
							String DoorNum = ((SoapObject) Items).getProperty("DoorNum").toString();
							String SeatNum = ((SoapObject) Items).getProperty("SeatNum").toString();
							String Fuel = ((SoapObject) Items).getProperty("Fuel").toString();
							String OilSupplyForm = ((SoapObject) Items).getProperty("OilSupplyForm").toString();
							String AirIntakeForm = ((SoapObject) Items).getProperty("AirIntakeForm").toString();
							String Drive = ((SoapObject) Items).getProperty("Drive").toString();
							String Transmission = ((SoapObject) Items).getProperty("Transmission").toString();
							
							String Emission = ((SoapObject) Items).getProperty("Emission").toString();
							String EnginePower = ((SoapObject) Items).getProperty("EnginePower").toString();
							String KeyConfiguration = ((SoapObject) Items).getProperty("KeyConfiguration").toString();
							String OptionConfiguration = ((SoapObject) Items).getProperty("OptionConfiguration").toString();
							String ModConfiguration = ((SoapObject) Items).getProperty("ModConfiguration").toString();
							
							String Color = ((SoapObject) Items).getProperty("Color").toString();
							String DisplayKm = ((SoapObject) Items).getProperty("DisplayKm").toString();
							String DisplayMiles = ((SoapObject) Items).getProperty("DisplayMiles").toString();
							String InstrumentIsChanged = ((SoapObject) Items).getProperty("InstrumentIsChanged").toString();
							String Photo1 = ((SoapObject) Items).getProperty("Photo1").toString();
							String Photo2 = ((SoapObject) Items).getProperty("Photo2").toString();
							String Photo3 = ((SoapObject) Items).getProperty("Photo3").toString();
							String Photo4 = ((SoapObject) Items).getProperty("Photo4").toString();
							
							
							String Photo1Base64 = ((SoapObject) Items).getProperty("Photo1Base64").toString();
							String Photo2Base64 = ((SoapObject) Items).getProperty("Photo2Base64").toString();
							String Photo3Base64 = ((SoapObject) Items).getProperty("Photo3Base64").toString();
							String Photo4Base64 = ((SoapObject) Items).getProperty("Photo4Base64").toString();
							String Photo5Base64 = ((SoapObject) Items).getProperty("Photo5Base64").toString();
							String Photo6Base64 = ((SoapObject) Items).getProperty("Photo6Base64").toString();
							String Photo7Base64 = ((SoapObject) Items).getProperty("Photo7Base64").toString();
							String Photo8Base64 = ((SoapObject) Items).getProperty("Photo8Base64").toString();
							String Photo9Base64 = ((SoapObject) Items).getProperty("Photo9Base64").toString();
							String Photo10Base64 = ((SoapObject) Items).getProperty("Photo10Base64").toString();
							String Photo11Base64 = ((SoapObject) Items).getProperty("Photo11Base64").toString();
							String Photo12Base64 = ((SoapObject) Items).getProperty("Photo12Base64").toString();
							String Photo13Base64 = ((SoapObject) Items).getProperty("Photo13Base64").toString();
							String Photo14Base64 = ((SoapObject) Items).getProperty("Photo14Base64").toString();
							String Photo15Base64 = ((SoapObject) Items).getProperty("Photo15Base64").toString();
							String Photo16Base64 = ((SoapObject) Items).getProperty("Photo16Base64").toString();
							String Photo17Base64 = ((SoapObject) Items).getProperty("Photo17Base64").toString();
							String Photo18Base64 = ((SoapObject) Items).getProperty("Photo18Base64").toString();

							
							//燃料
							String radio_ranliao = "";
							//钥匙
							String radio_yaoshi = "";
							//ABS
							String radio_abs = "";
							//助力转向
							String radio_zhuli = "";
							//轮圈
							String radio_lunquan = "";
							//安全气囊/气帘
							String check_qinang = "";
							String radio_qinang = "";
							//天窗
							String radio_tianchuang = "";
							//空调
							String radio_kongtiao = "";
							String check_kongtiao = "";
							//门窗
							String radio_menchuang = "";
							//后视镜
							String radio_houshijing = "";
							//座椅材质
							String radio_zuoyi_caizhi = "";
							//座椅功能
							String check_zuoyi = "";
							//方向盘		
							String radio_fangxiangpan = "";
							String check_fangxiangpan = "";
							//大灯
							String radio_dadeng = "";
							//CD/DVD
							String radio_dvd = "";
							//液晶屏
							String radio_yejingpin = "";		
							String check_yejingpin = "";
							//其他配置
							String radio_other_peizhi = "";		
							String check_other_peizhi = "";							
							
							//1.燃料
							if(KeyConfiguration.indexOf("燃料类型:汽油")!=-1)
							{
								radio_ranliao = "1";
							}
							if(KeyConfiguration.indexOf("燃料类型:柴油")!=-1)
							{
								radio_ranliao = "2";
							}
							if(KeyConfiguration.indexOf("燃料类型:液化气")!=-1)
							{
								radio_ranliao = "3";
							}
							if(KeyConfiguration.indexOf("燃料类型:天然气")!=-1)
							{
								radio_ranliao = "4";
							}
							if(KeyConfiguration.indexOf("燃料类型:纯电动")!=-1)
							{
								radio_ranliao = "5";
							}							
							if(KeyConfiguration.indexOf("燃料类型:油电混合")!=-1)
							{
								radio_ranliao = "6";
							}
							if(KeyConfiguration.indexOf("燃料类型:油气混合")!=-1)
							{
								radio_ranliao = "7";
							}
							
							//2.钥匙
							if(KeyConfiguration.indexOf("钥匙:普通")!=-1)
							{
								radio_yaoshi = "1";
							}
							if(KeyConfiguration.indexOf("钥匙:遥控")!=-1)
							{
								radio_yaoshi = "2";
							}
							if(KeyConfiguration.indexOf("钥匙:智能")!=-1)
							{
								radio_yaoshi = "3";
							}
							
							//3.ABS
							if(KeyConfiguration.indexOf("ABS:有")!=-1)
							{
								radio_abs = "1";
							}
							if(KeyConfiguration.indexOf("ABS:无")!=-1)
							{
								radio_abs = "2";
							}
							
							//4.助力转向
							if(KeyConfiguration.indexOf("助力转向:有")!=-1)
							{
								radio_zhuli = "1";
							}
							if(KeyConfiguration.indexOf("助力转向:无")!=-1)
							{
								radio_zhuli = "2";
							}
							
							//5.轮圈
							if(KeyConfiguration.indexOf("轮圈:钢")!=-1)
							{
								radio_lunquan = "1";
							}
							if(KeyConfiguration.indexOf("轮圈:合金")!=-1)
							{
								radio_lunquan = "2";
							}
							if(KeyConfiguration.indexOf("轮圈:碳纤维")!=-1)
							{
								radio_lunquan = "3";
							}
							
							//6.安全气囊/气帘							
							if(KeyConfiguration.indexOf("驾驶员气囊")!=-1)
							{
								check_qinang = check_qinang+"1,";
							}
							if(KeyConfiguration.indexOf("副驾驶气囊")!=-1)
							{
								check_qinang = check_qinang+"2,";
							}
							if(KeyConfiguration.indexOf("前排侧气囊")!=-1)
							{
								check_qinang = check_qinang+"3,";
							}
							if(KeyConfiguration.indexOf("后排侧气囊")!=-1)
							{
								check_qinang = check_qinang+"4,";
							}
							if(KeyConfiguration.indexOf("前排头部气囊/气帘")!=-1)
							{
								check_qinang = check_qinang+"5,";
							}
							if(KeyConfiguration.indexOf("后排头部气囊/气帘")!=-1)
							{
								check_qinang = check_qinang+"6,";
							}
							if(KeyConfiguration.indexOf("膝部气囊")!=-1)
							{
								check_qinang = check_qinang+"7,";
							}
							if(check_qinang.length() > 0)
							{
								check_qinang = check_qinang.substring(0,check_qinang.length()-1);
							}
							if(check_qinang.trim().equals(""))
							{
								radio_qinang = "1";
							}
							if(!check_qinang.trim().equals(""))
							{
								radio_qinang = "2";
							}
														
							//7.天窗
							if(KeyConfiguration.indexOf("天窗:无")!=-1)
							{
								radio_tianchuang = "1";
							}
							if(KeyConfiguration.indexOf("天窗:手动")!=-1)
							{
								radio_tianchuang = "2";
							}
							if(KeyConfiguration.indexOf("天窗:电动")!=-1)
							{
								radio_tianchuang = "3";
							}
							if(KeyConfiguration.indexOf("天窗:全景")!=-1)
							{
								radio_tianchuang = "4";
							}
							
							//8.空调							
							if(KeyConfiguration.indexOf("手动控制")!=-1)
							{
								check_kongtiao = check_kongtiao+"1,";
							}
							if(KeyConfiguration.indexOf("自动控制")!=-1)
							{
								check_kongtiao = check_kongtiao+"2,";
							}
							if(KeyConfiguration.indexOf("后排独立空调")!=-1)
							{
								check_kongtiao = check_kongtiao+"3,";
							}
							if(check_kongtiao.length()>0)
							{
								check_kongtiao = check_kongtiao.substring(0,check_kongtiao.length()-1);
							}
							if(check_kongtiao.trim().equals(""))
							{
								radio_kongtiao = "1";
							}
							if(!check_kongtiao.trim().equals(""))
							{
								radio_kongtiao = "2";
							}
							
							//9.门窗
							if(KeyConfiguration.indexOf("门窗:手动")!=-1)
							{
								radio_menchuang = "1";
							}
							if(KeyConfiguration.indexOf("门窗:前门电动")!=-1)
							{
								radio_menchuang = "2";
							}
							if(KeyConfiguration.indexOf("门窗:四门电动")!=-1)
							{
								radio_menchuang = "3";
							}
							
							//10.后视镜
							if(KeyConfiguration.indexOf("后视镜:手动调节")!=-1)
							{
								radio_houshijing = "1";
							}
							if(KeyConfiguration.indexOf("后视镜:电动调节")!=-1)
							{
								radio_houshijing = "2";
							}
							if(KeyConfiguration.indexOf("后视镜:电动折叠")!=-1)
							{
								radio_houshijing = "3";
							}
							if(KeyConfiguration.indexOf("后视镜:电动记忆")!=-1)
							{
								radio_houshijing = "4";
							}
							
							//11 座椅材质							
							if(KeyConfiguration.indexOf("座椅材质:织物")!=-1)
							{
								radio_zuoyi_caizhi = "1";
							}
							if(KeyConfiguration.indexOf("座椅材质:真皮")!=-1)
							{
								radio_zuoyi_caizhi = "2";
							}
							
							//12 座椅功能				
							if(KeyConfiguration.indexOf("主驾座椅电动调节")!=-1)
							{
								check_zuoyi = check_zuoyi + "<1>,";
							}
							if(KeyConfiguration.indexOf("副驾座椅电动调节")!=-1)
							{
								check_zuoyi = check_zuoyi + "<2>,";
							}
							if(KeyConfiguration.indexOf("后排座椅电动调节")!=-1)
							{
								check_zuoyi = check_zuoyi + "<3>,";
							}
							if(KeyConfiguration.indexOf("电动座椅记忆")!=-1)
							{
								check_zuoyi = check_zuoyi + "<4>,";
							}
							if(KeyConfiguration.indexOf("前排座椅加热")!=-1)
							{
								check_zuoyi = check_zuoyi + "<5>,";
							}
							if(KeyConfiguration.indexOf("后排座椅加热")!=-1)
							{
								check_zuoyi = check_zuoyi + "<6>,";
							}
							if(KeyConfiguration.indexOf("前排座椅通风")!=-1)
							{
								check_zuoyi = check_zuoyi + "<7>,";
							}
							if(KeyConfiguration.indexOf("后排座椅通风")!=-1)
							{
								check_zuoyi = check_zuoyi + "<8>,";
							}
							if(KeyConfiguration.indexOf("前排座椅按摩")!=-1)
							{
								check_zuoyi = check_zuoyi + "<9>,";
							}
							if(KeyConfiguration.indexOf("后排座椅按摩")!=-1)
							{
								check_zuoyi = check_zuoyi + "<10>,";
							}
							if(check_zuoyi.length()>0)
							{
								check_zuoyi = check_zuoyi.substring(0,check_zuoyi.length()-1);
							}
							
							//13方向盘							
							if(KeyConfiguration.indexOf("音响控制")!=-1)
							{
								check_fangxiangpan = check_fangxiangpan + "1,";
							}
							if(KeyConfiguration.indexOf("定速巡航")!=-1)
							{
								check_fangxiangpan = check_fangxiangpan + "2,";
							}
							if(KeyConfiguration.indexOf("方向盘换挡")!=-1)
							{
								check_fangxiangpan = check_fangxiangpan + "3,";
							}
							if(KeyConfiguration.indexOf("方向盘电动调节")!=-1)
							{
								check_fangxiangpan = check_fangxiangpan + "4,";
							}
							if(KeyConfiguration.indexOf("方向盘加热")!=-1)
							{
								check_fangxiangpan = check_fangxiangpan + "5,";
							}
							if(check_fangxiangpan.length()>0)
							{
								check_fangxiangpan = check_fangxiangpan.substring(0,check_fangxiangpan.length()-1);
							}
							if(check_fangxiangpan.trim().equals(""))
							{
								radio_fangxiangpan = "1";			
							}
							if(!check_fangxiangpan.trim().equals(""))
							{
								radio_fangxiangpan = "2";			
							}
							
							//14 大灯
							if(KeyConfiguration.indexOf("大灯:卤素")!=-1)
							{
								radio_dadeng = "1";
							}
							if(KeyConfiguration.indexOf("大灯:其他")!=-1)
							{
								radio_dadeng = "2";
							}
							
							//15 CD/DVD
							if(KeyConfiguration.indexOf("CD/DVD:无")!=-1)
							{
								radio_dvd = "1";
							}
							if(KeyConfiguration.indexOf("CD/DVD:单碟CD")!=-1)
							{
								radio_dvd = "2";
							}
							if(KeyConfiguration.indexOf("CD/DVD:多碟CD")!=-1)
							{
								radio_dvd = "3";
							}
							if(KeyConfiguration.indexOf("CD/DVD:单碟DVD")!=-1)
							{
								radio_dvd = "4";
							}
							if(KeyConfiguration.indexOf("CD/DVD:多碟DVD")!=-1)
							{
								radio_dvd = "5";
							}
							
							//16 液晶屏
							if(KeyConfiguration.indexOf("中控台液晶屏")!=-1)
							{
								check_yejingpin = check_yejingpin + "1,";
							}
							if(KeyConfiguration.indexOf("后排液晶屏")!=-1)
							{
								check_yejingpin = check_yejingpin + "2,";
							}
							if(KeyConfiguration.indexOf("导航")!=-1)
							{
								check_yejingpin = check_yejingpin + "3,";
							}
							if(KeyConfiguration.indexOf("人体交互系统")!=-1)
							{
								check_yejingpin = check_yejingpin + "4,";
							}
							if(check_yejingpin.length()>0)
							{
								check_yejingpin = check_yejingpin.substring(0,check_yejingpin.length()-1);
							}	
							if(check_yejingpin.trim().equals(""))
							{
								radio_yejingpin = "1";
							}
							if(!check_yejingpin.trim().equals(""))
							{
								radio_yejingpin = "2";
							}
							
							//18 其他配置
							if(OptionConfiguration.indexOf("胎压检测器")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<1>,";
							}
							if(OptionConfiguration.indexOf("车身稳定控制")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<2>,";
							}
							if(OptionConfiguration.indexOf("自动驻车")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<3>,";
							}
							if(OptionConfiguration.indexOf("陡坡缓降")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<4>,";
							}
							if(OptionConfiguration.indexOf("底盘升降")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<5>,";
							}
							if(OptionConfiguration.indexOf("电动吸合门")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<6>,";
							}
							if(OptionConfiguration.indexOf("电动侧滑门")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<7>,";
							}
							if(OptionConfiguration.indexOf("电动后备箱")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<8>,";
							}
							if(OptionConfiguration.indexOf("倒车影像")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<9>,";
							}
							if(OptionConfiguration.indexOf("抬头数字显示")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<10>,";
							}
							if(OptionConfiguration.indexOf("定位互动服务")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<11>,";
							}
							if(OptionConfiguration.indexOf("蓝牙/车载电话")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<12>,";
							}
							if(OptionConfiguration.indexOf("车载电视")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<13>,";
							}
							if(OptionConfiguration.indexOf("遮阳帘")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<14>,";
							}
							if(OptionConfiguration.indexOf("感应雨刷")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<15>,";
							}
							if(OptionConfiguration.indexOf("主动泊车")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<16>,";
							}
							if(OptionConfiguration.indexOf("并线辅助")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<17>,";
							}
							if(OptionConfiguration.indexOf("发动机启停技术")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<18>,";
							}
							if(OptionConfiguration.indexOf("车道偏离预警系统")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<19>,";
							}
							if(OptionConfiguration.indexOf("主动刹车")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<20>,";
							}
							if(OptionConfiguration.indexOf("主动巡航")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<21>,";
							}
							if(OptionConfiguration.indexOf("全景摄像头")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<22>,";
							}
							if(OptionConfiguration.indexOf("夜视系统")!=-1)
							{
								check_other_peizhi = check_other_peizhi + "<23>,";
							}
							if(check_other_peizhi.length()>0)
							{
								check_other_peizhi = check_other_peizhi.substring(0,check_other_peizhi.length()-1);
							}	
							if(check_other_peizhi.trim().equals(""))
							{
								radio_other_peizhi = "1";			
							}
							if(!check_other_peizhi.trim().equals(""))
							{
								radio_other_peizhi = "2";			
							}							
							
							NewChexingDengjiDB db = new NewChexingDengjiDB(context);
							
							
							if(!db.isExistById(HistoryResourceID))
							{
								long rowid = db.insert(
										HistoryResourceID, 
										VIN, 
										Category,
										"1:比克,2:奥迪", 
										"1:通用汽车,2:通用汽车(进口)",
										"CTS", 
										"2012", 
										"3.0手动",
										ModelType, 
										Brand,  
										Model, 
										BodyForm, 
										DoorNum, 
										SeatNum, 
										Fuel, 
										OilSupplyForm,
										AirIntakeForm, 
										Drive, 
										Transmission, 
										Emission, 
										EnginePower, 
										radio_ranliao, 
										radio_yaoshi, 
										radio_abs,
										radio_zhuli, 
										radio_lunquan, 
										radio_qinang, 
										check_qinang, 
										radio_tianchuang, 
										radio_kongtiao, 
										check_kongtiao, 
										radio_menchuang,
										radio_houshijing, 
										radio_zuoyi_caizhi,
										check_zuoyi, 
										radio_fangxiangpan,
										check_fangxiangpan,
										radio_dadeng, 
										radio_dvd,
										radio_yejingpin, 
										check_yejingpin,
										radio_other_peizhi, 
										check_other_peizhi, 
										ModConfiguration);
										
								db.close();
								
								
								ChexingDengjiDB db2 = new ChexingDengjiDB(context);
								long rowID = db2.insert(HistoryResourceID, 
										Id, 
										KeyConfiguration, 
										OptionConfiguration, 
										Color.equals("anyType{}")?"":Color, 
										DisplayKm.equals("anyType{}")?"":DisplayKm, 
										DisplayMiles.equals("anyType{}")?"":DisplayMiles, 
										InstrumentIsChanged.equals("anyType{}")?"":InstrumentIsChanged, 
										"0", 
										"0",
										"0",
										"0",
										Photo1Base64.equals("anyType{}")?"":Photo1Base64, 
										Photo2Base64.equals("anyType{}")?"":Photo2Base64, 
										Photo3Base64.equals("anyType{}")?"":Photo3Base64, 
										Photo4Base64.equals("anyType{}")?"":Photo4Base64,
										Photo5Base64.equals("anyType{}")?"":Photo5Base64,
										Photo6Base64.equals("anyType{}")?"":Photo6Base64,
										Photo7Base64.equals("anyType{}")?"":Photo7Base64,
										Photo8Base64.equals("anyType{}")?"":Photo8Base64,
										Photo9Base64.equals("anyType{}")?"":Photo9Base64,
										Photo10Base64.equals("anyType{}")?"":Photo10Base64,
										Photo11Base64.equals("anyType{}")?"":Photo11Base64,
										Photo12Base64.equals("anyType{}")?"":Photo12Base64,
										Photo13Base64.equals("anyType{}")?"":Photo13Base64,
										Photo14Base64.equals("anyType{}")?"":Photo14Base64,
										Photo15Base64.equals("anyType{}")?"":Photo15Base64,
										Photo16Base64.equals("anyType{}")?"":Photo16Base64,
										Photo17Base64.equals("anyType{}")?"":Photo17Base64,
										Photo18Base64.equals("anyType{}")?"":Photo18Base64
                                        );
								db2.close();
							}
							
							
							
							
							
							
							
							
							GetResourceDetail();
						}
						else{
							ToastUtilMgr.TextToast(context,"获取车型信息失败",ToastUtilMgr.LENGTH_SHORT);
							GetResourceDetail();
						}
					}
					
				}
				@Override
				public void failed(Message msg) {				
					ToastUtilMgr.TextToast(context,"获取车型信息失败",ToastUtilMgr.LENGTH_SHORT);
					GetResourceDetail();
				}
			};
			car_model_mgr = new SoapMgr(context, null, null,
					SoapAction.GetResourceModel, "GetResourceModel", soapParameter,
					car_model_handler, true, false);
		}
	
	
	
	
	
	
	/**
	 * 获取精密检测登记信息
	 */
	private void GetResourceDetail() {
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace,
				"GetResourceDetail");
		soapParameter.addProperty("ResourceID", HistoryResourceID );
		car_jingmi_handler = new MyHandler() {
			@Override
			public void success(Message msg) {
					car_jingmi_backSoapObject = car_jingmi_mgr
						.getServiceBackSoapObject();
					if (car_jingmi_backSoapObject != null) {
						String Retcode = ((SoapObject) car_jingmi_backSoapObject
								.getProperty(0)).getProperty("Retcode").toString();
						// 0成功，1失败
						if (Retcode.equals("0")) {							
							String AppearanceText = ((SoapObject)((SoapObject)car_jingmi_backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("AppearanceText").toString();
							
							if(!AppearanceText.equals("anyType{}") && !AppearanceText.equals("[]") )
							{
								boolean sdCardExist = Environment.getExternalStorageState() 
						                .equals(android.os.Environment.MEDIA_MOUNTED);
								
								if(sdCardExist)
								{
										
										String sdcardPath = Environment.getExternalStorageDirectory().getPath();
							            File dir= new File(sdcardPath+ "/carcheck"); 
							            if(!dir.exists())
							            {
							            	dir.mkdir();
							            }
							            File josnfile = new File(sdcardPath+ "/carcheck/json.txt");
							            try { 
							            	// false 覆盖    true追加
							                FileOutputStream fout = new FileOutputStream(josnfile, false); 
							                byte[] bytes = AppearanceText.getBytes(); 
							                fout.write(bytes); 
							                fout.flush();
							                fout.close(); 
							            } catch (Exception e) { 
							                e.printStackTrace(); 
							            } 
													            
								}
							}
							
							
							GetCarState();
						}
						else{
							ToastUtilMgr.TextToast(context,"获取精密检测信息失败",ToastUtilMgr.LENGTH_SHORT);
							GetCarState();
						}
					}
				}
				@Override
				public void failed(Message msg) {				
					ToastUtilMgr.TextToast(context,"获取精密检测信息失败",ToastUtilMgr.LENGTH_SHORT);
					GetCarState();
				}
			};
			car_jingmi_mgr = new SoapMgr(context, null, null,
					SoapAction.GetResourceDetail, "GetResourceDetail", soapParameter,
					car_jingmi_handler, true, false);
	}
	
	
	
	/**
	 * 获取车况检测信息
	 */
	private void GetCarState() {
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace,
				"GetCarState");
		soapParameter.addProperty("ResourceID", HistoryResourceID );
		car_state_handler = new MyHandler() {
			@Override
			public void success(Message msg) {
				car_state_backSoapObject = car_state_mgr
						.getServiceBackSoapObject();
					if (car_state_backSoapObject != null) {
						String Retcode = ((SoapObject) car_state_backSoapObject
								.getProperty(0)).getProperty("Retcode").toString();
						// 0成功，1失败
						if (Retcode.equals("0")) {							
							SoapObject Items = ((SoapObject) ((SoapObject) car_state_backSoapObject
									.getProperty(0)).getProperty("CarState"));
						
							
							String AppearanceScore = ((SoapObject) Items).getProperty("AppearanceScore").toString();
							String InteriorScore = ((SoapObject) Items).getProperty("InteriorScore").toString();
							String AccidentLlevel = ((SoapObject) Items).getProperty("AccidentLlevel").toString();
							String SpecialCar = ((SoapObject) Items).getProperty("SpecialCar").toString();
							String ComprehensiveScore = ((SoapObject) Items).getProperty("ComprehensiveScore").toString();
							String Comments = ((SoapObject) Items).getProperty("Comments").toString();
							
							if(AppearanceScore.equals("anyType{}") || AppearanceScore.equals(""))
							{
								AppearanceScore = "0";
							}
							if(InteriorScore.equals("anyType{}") || InteriorScore.equals(""))
							{
								InteriorScore = "0";
							}
							if(AccidentLlevel.equals("anyType{}") || AccidentLlevel.equals(""))
							{
								AccidentLlevel = "0";
							}
							if(SpecialCar.equals("anyType{}") || SpecialCar.equals(""))
							{
								SpecialCar = "0";
							}
							if(Comments.equals("anyType{}"))
							{
								Comments = "";
							}
							if(ComprehensiveScore.equals("anyType{}"))
							{
								ComprehensiveScore = "";
							}
							
							//获得数据		
							ChekuangDengjiDB db = new ChekuangDengjiDB(context);
							db.insert(new AppPreference(context).getTaskId(), 
									AppearanceScore+"", 
									InteriorScore+"", 
									AccidentLlevel+"", 
									SpecialCar+"", 
									ComprehensiveScore+"", 
									Comments);
							db.close();							
							
							
							
							handler.sendEmptyMessage(0);
							
						}
						else{
							ToastUtilMgr.TextToast(context,"获取车况信息失败",ToastUtilMgr.LENGTH_SHORT);
							handler.sendEmptyMessage(0);
						}
					}
				}
				@Override
				public void failed(Message msg) {				
					ToastUtilMgr.TextToast(context,"获取车况信息失败",ToastUtilMgr.LENGTH_SHORT);
					handler.sendEmptyMessage(0);
				}
			};
			car_state_mgr = new SoapMgr(context, null, null,
					SoapAction.GetCarState, "GetCarState", soapParameter,
					car_state_handler, true, false);
	}


}
