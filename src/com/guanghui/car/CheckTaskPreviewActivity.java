package com.guanghui.car;

import java.io.File;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.guanghui.car.application.MyApplication;
import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;
import com.guanghui.car.db.ChekuangDengjiDB;
import com.guanghui.car.db.ChexingDengjiDB;
import com.guanghui.car.db.ShouxuDengjiDB;
import com.guanghui.car.jingmicheck.AllDefectShowActivity;
import com.guanghui.car.jingmicheck.JingMiCarCheckActivity;
import com.guanghui.car.staticdata.CarConfigData;
import com.guanghui.car.staticdata.JingMiCheckData;
import com.nostra13.universalimageloader.core.ImageLoader;

public class CheckTaskPreviewActivity extends Activity {
	
	private Button saveButton;
	private Button commitButton;
	// 客户信息
	private TextView check_task_preview_user_name;
	private TextView check_task_preview_user_mobile;
	private TextView check_task_preview_user_shangjilaiyuan;
	private TextView check_task_preview_user_xiaoshouguwen;
	private TextView check_task_preview_user_quyu;
	private TextView check_task_preview_user_mendian;
	private TextView check_task_preview_user_pinggushi;
	private TextView check_task_preview_user_yuyuejianceshijian;
	// 车辆手续信息
	private TextView check_task_preview_shouxu_carNumber;
	private TextView check_task_preview_shouxu_carLocation;
	private TextView check_task_preview_shouxu_cheliangshibiema;
	private TextView check_task_preview_shouxu_brandModel;
	private TextView check_task_preview_shouxu_dengjiTime;
	private TextView check_task_preview_shouxu_shiyongxingzhi;
	private TextView check_task_preview_shouxu_banzhengzhuangtai;
	private TextView check_task_preview_shouxu_paizhaoedu;
	
	//外观评级
	private TextView check_task_preview_waiguan_text;
	//内饰评级
	private TextView check_task_preview_neishi_text;
	//内饰评级
	private TextView check_task_preview_shigu_text;
	//内饰评级
	private TextView check_task_preview_teshu_text;
	//车况评估信息
	private TextView check_task_preview_zonghe_text;
	//车况描述
	private TextView check_task_preview_miaoshu_text;
	// 车型信息
	private TextView check_task_preview_carModel_carModel;
	private TextView check_task_preview_carModel_carSeries;
	private TextView check_task_preview_carModel_detail_model;
	private TextView check_task_preview_carModel_color;
	private TextView check_task_preview_carModel_keyConfiguration;
	private TextView check_task_preview_carModel_choiceConfiguration;
	private TextView check_task_preview_carModel_changeConfiguration;
	private TextView check_task_preview_carModel_distence;
	private Context context = this;
	private Button check_task_preview_btn_back;
	private SoapObject backSoapObject2;
	// 是否广汇认证的二手车
	private TextView check_task_preview_guanghui_renzheng_text;
	
	
	//手续登记
	private String ly3txt1;
	private String ly3txt2;
	private String ly3txt3;
	private String ly3txt4;
	private String ly3txt5;
	private String ly3txt6;
	private String ly3txt7;
	private String ly3txt8;
	private String ly3txt9;
	private String ly3txt10;
	private String ly3txt11;
	private String ly3txt12;
	private String ly3txt13;
	private String ly3txt14;
	private String ly3txt15;
	private String ly3txt16;
	private String ly3txt17;
	private String ly3txt18;
	private String ly3txt19;
	private String ly3txt20;
	private String ly3txt21;
	private String ly3txt22;
	private String ly3txt23;
	private String ly3txt24;
	//
	String CarModel;
	String CarCategory;
	String Transmission;
	String Emission;
	String EnginePower;	
	String Color;
	String KeyConfiguration;
	String OptionConfiguration;
	String ModConfiguration;
	String DisplayKm;
	String InstrumentIsChanged;
	String Photo1;
	String Photo2;
	String Photo3;
	String Photo4;
	String Photo5;
	String Photo6;
	String Photo7;
	String Photo8;
	String Photo9;
	String Photo10;
	String Photo11;
	String Photo12;
	String Photo13;
	String Photo14;
	String Photo15;
	String Photo16;
	String Photo17;
	String Photo18;


	String GuidePrice;
	String LowestPrice;
	String UsedPriceLow;
	String UsedPriceHigh;							
	String AppearanceScore;
	String InteriorScore;
	String AccidentLevel;
	String SpecialCar;	
	String Comments;	
	String ComprehensiveScore;
	String Price;
	String SuccessOption;
	String FailureReason;
	String jingmi1,jingmi2,jingmi3,jingmi4;
	
	//详情和修改的按钮
	//详情  1  修改2
	private Button check_task_preview_btn_2_1,check_task_preview_btn_2_2;
	private Button check_task_preview_btn_3_1,check_task_preview_btn_3_2;
	private Button check_task_preview_btn_4_1,check_task_preview_btn_4_2;
	private View.OnClickListener listener;
	
	
	
	
	
	
	
	public static int IsGHAuth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_task_preview);
		new BuildTab(context,1) ;
		
		IsGHAuth = this.getIntent().getIntExtra("IsGHAuth", 1);
		// 客户
		check_task_preview_user_name = (TextView) findViewById(R.id.check_task_preview_user_name);
		check_task_preview_user_mobile = (TextView) findViewById(R.id.check_task_preview_user_mobile);
		check_task_preview_user_mendian = (TextView) findViewById(R.id.check_task_preview_user_mendian);
		check_task_preview_user_quyu = (TextView) findViewById(R.id.check_task_preview_user_quyu);
		check_task_preview_user_xiaoshouguwen = (TextView) findViewById(R.id.check_task_preview_user_xiaoshouguwen);
		check_task_preview_user_pinggushi = (TextView) findViewById(R.id.check_task_preview_user_pinggushi);
		check_task_preview_user_shangjilaiyuan = (TextView) findViewById(R.id.check_task_preview_user_laiyuan);
		check_task_preview_user_yuyuejianceshijian = (TextView) findViewById(R.id.check_task_preview_user_yuyuejiance);
		// 车辆手续
		check_task_preview_shouxu_banzhengzhuangtai = (TextView) findViewById(R.id.check_task_preview_shouxu_banzhengzhuangtai);
		check_task_preview_shouxu_brandModel = (TextView) findViewById(R.id.check_task_preview_shouxu_brandModel);
		check_task_preview_shouxu_carLocation = (TextView) findViewById(R.id.check_task_preview_shouxu_carLocation);
		check_task_preview_shouxu_carNumber = (TextView) findViewById(R.id.check_task_preview_shouxu_carNumber);
		check_task_preview_shouxu_cheliangshibiema = (TextView) findViewById(R.id.check_task_preview_shouxu_cheliangshibiema);
		check_task_preview_shouxu_dengjiTime = (TextView) findViewById(R.id.check_task_preview_shouxu_dengjiTime);
		check_task_preview_shouxu_paizhaoedu = (TextView) findViewById(R.id.check_task_preview_shouxu_paizhaoedu);
		check_task_preview_shouxu_shiyongxingzhi = (TextView) findViewById(R.id.check_task_preview_shouxu_shiyongxingzhi);
		// 车型信息
		check_task_preview_carModel_carModel = (TextView) findViewById(R.id.check_task_preview_carModel_carModel);
		check_task_preview_carModel_carSeries = (TextView) findViewById(R.id.check_task_preview_carModel_carSeries);
		check_task_preview_carModel_changeConfiguration = (TextView) findViewById(R.id.check_task_preview_carModel_changeConfiguration);
		check_task_preview_carModel_choiceConfiguration = (TextView) findViewById(R.id.check_task_preview_carModel_choiceConfiguration);
		check_task_preview_carModel_color = (TextView) findViewById(R.id.check_task_preview_carModel_color);
		check_task_preview_carModel_detail_model = (TextView) findViewById(R.id.check_task_preview_carModel_detail_model);
		check_task_preview_carModel_keyConfiguration = (TextView) findViewById(R.id.check_task_preview_carModel_keyConfiguration);
		check_task_preview_carModel_distence = (TextView) findViewById(R.id.check_task_preview_carModel_distence);
		// 综合评级
		check_task_preview_zonghe_text = (TextView) findViewById(R.id.check_task_preview_zonghe_text);
		check_task_preview_waiguan_text = (TextView) findViewById(R.id.check_task_preview_waiguan_text);
		check_task_preview_neishi_text = (TextView) findViewById(R.id.check_task_preview_neishi_text);
		check_task_preview_shigu_text = (TextView) findViewById(R.id.check_task_preview_shigu_text);
		check_task_preview_teshu_text = (TextView) findViewById(R.id.check_task_preview_teshu_text);
		check_task_preview_miaoshu_text = (TextView) findViewById(R.id.check_task_preview_miaoshu_text);
		check_task_preview_guanghui_renzheng_text = (TextView) findViewById(R.id.check_task_preview_guanghui_renzheng_text);
		//详情和修改按钮
		check_task_preview_btn_2_1 = (Button) findViewById(R.id.check_task_preview_btn_2_1);
		check_task_preview_btn_2_2 = (Button) findViewById(R.id.check_task_preview_btn_2_2);
		check_task_preview_btn_3_1 = (Button) findViewById(R.id.check_task_preview_btn_3_1);
		check_task_preview_btn_3_2 = (Button) findViewById(R.id.check_task_preview_btn_3_2);
		check_task_preview_btn_4_1 = (Button) findViewById(R.id.check_task_preview_btn_4_1);
		check_task_preview_btn_4_2 = (Button) findViewById(R.id.check_task_preview_btn_4_2);
		
		
		setListener();
		
		check_task_preview_btn_2_1.setOnClickListener(listener);
		check_task_preview_btn_2_2.setOnClickListener(listener);
		check_task_preview_btn_3_1.setOnClickListener(listener);
		check_task_preview_btn_3_2.setOnClickListener(listener);
		check_task_preview_btn_4_1.setOnClickListener(listener);
		check_task_preview_btn_4_2.setOnClickListener(listener);
		
				
				
				
		check_task_preview_btn_back = (Button) findViewById(R.id.check_task_preview_btn_back);
		check_task_preview_btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();				
			}
		});
		
		
		initBottom();
		
		
		
		
		
	}
	
	private void initBottom() {
		saveButton = (Button) findViewById(R.id.stop_save);
		saveButton.setVisibility(View.GONE);
		commitButton = (Button) findViewById(R.id.comfirm_communit);
		commitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SetResourceStatus(new AppPreference(context).getTaskId(),"4");
			}
		});
	}

	Handler mHandler = new Handler() {

		public void handleMessage(Message msg) {

			switch (msg.what) {
			// 请求客户信息成功
			case UserSucessCaseOK:
				getUseInfo();
				break;
			case UserOK:
				getshouxuInfo();
				break;
			// 请求手续信息成功
			case shouxuSucessCase:
				getcarModelInfo();
				break;
			// 获取车型信息成功
			case carModelSucessCase:
				getcarStateInfo();
				break;
			// 车辆状态
			case carStateSucessCase:				
				
				break;
		
				
			}
		}
	};
	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	private static final int UserSucessCase = 1;

	private static final int UserFailCase = -1;

	/**
	 * 获取用户的基本信息
	 */
	private void getUseInfo() {
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace,
				"GetResource");
		soapParameter.addProperty("ID", new AppPreference(context).getTaskId());
		myhandler = new MyHandler() {
			@Override
			public void success(Message msg) {
				backSoapObject = mgr.getServiceBackSoapObject();
				if (backSoapObject != null) {
					String Retcode = ((SoapObject) backSoapObject
							.getProperty(0)).getProperty("Retcode").toString();
					// 0成功，1失败
					if (Retcode.equals("0")) {
						
						SoapObject Items = ((SoapObject) ((SoapObject) backSoapObject
								.getProperty(0)).getProperty("Resource"));
						check_task_preview_user_name.setText(
							((SoapObject) Items).getProperty(
										"CustomerName").toString());
						check_task_preview_user_mobile.setText(
								((SoapObject) Items).getProperty(
										"CustomerMobile").toString());
						check_task_preview_user_xiaoshouguwen.setText(
							 ((SoapObject) Items).getProperty("ProviderPerson")
										.toString().equals("anyType{}")?"-": ((SoapObject) Items).getProperty("ProviderPerson")
												.toString());
						check_task_preview_user_shangjilaiyuan.setText(
								 ((SoapObject) Items)
										.getProperty("SourceName").toString().equals("anyType{}")?"-": ((SoapObject) Items).getProperty("SourceName")
												.toString());
						check_task_preview_user_quyu.setText(
								((SoapObject) Items)
										.getProperty("RegionName").toString().equals("anyTpye{}")?"":((SoapObject) Items)
												.getProperty("RegionName").toString());
						check_task_preview_user_mendian.setText(
								 ((SoapObject) Items).getProperty("ShopName")
										.toString().equals("anyTpye{}")?"":((SoapObject) Items)
												.getProperty("ShopName").toString());
						check_task_preview_user_pinggushi.setText(new AppPreference(context).getLoginUserName());
						String time=((SoapObject) Items).getProperty(
								"AppointmentTime").toString();
						time = time.replace("T", "  ");
	                    if(time.indexOf(".")!=-1)
	                    {
	                    	time = time.substring(0,time.indexOf("."));
	                    }
						check_task_preview_user_yuyuejianceshijian.setText(time );
						
						mHandler.sendEmptyMessage(UserOK);
						
					} else {
						ToastUtilMgr.TextToast(context,
								getString(R.string.server_error1),
								ToastUtilMgr.LENGTH_LONG);
					}
				}
			}

			@Override
			public void failed(Message msg) {
				ToastUtilMgr.TextToast(context,"获取客户联系信息失败",ToastUtilMgr.LENGTH_SHORT);
			}
		};
		mgr = new SoapMgr(context, null, null, SoapAction.GetResource,
				"GetResource", soapParameter, myhandler, true, false);
	}

	private MyHandler shoxumyhandler;
	private SoapMgr shouxu_mgr;
	private SoapObject shouxu_backSoapObject;
	private static final int shouxuSucessCase = 2;
	private static final int UserSucessCaseOK = 6;
	private static final int UserOK = 8;
	
	
	
	private static final int shouxuFailCase = -2;

	/**
	 * 获取手续等级信息
	 */
	private void getshouxuInfo() {
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace,
				"GetEnrolment");
		soapParameter.addProperty("ResourceID", new AppPreference(context).getTaskId());
		//soapParameter.addProperty("ResourceID", "2");
		shoxumyhandler = new MyHandler() {
			@Override
			public void success(Message msg) {
				shouxu_backSoapObject = shouxu_mgr.getServiceBackSoapObject();
				if (shouxu_backSoapObject != null) {
					String Retcode = ((SoapObject) shouxu_backSoapObject
							.getProperty(0)).getProperty("Retcode").toString();
					// 0成功，1失败
					if (Retcode.equals("0")) {
						
						SoapObject Items = ((SoapObject) ((SoapObject) shouxu_backSoapObject
								.getProperty(0)).getProperty("Enrolment"));
						check_task_preview_shouxu_carNumber.setText(((SoapObject) Items).getProperty("License")
										.toString());
						check_task_preview_shouxu_carLocation.setText(((SoapObject) Items).getProperty(
										"BelongingTo").toString());
						check_task_preview_shouxu_cheliangshibiema
								.setText(((SoapObject) Items).getProperty(
												"IdentificationCode")
												.toString());
						check_task_preview_shouxu_brandModel.setText(((SoapObject) Items).getProperty("BrandModel").toString());
						
						if(((SoapObject) Items).getProperty("FirstEnrolmentDate")!=null) 
						{
							check_task_preview_shouxu_dengjiTime.setText(((SoapObject) Items).getProperty("FirstEnrolmentDate").toString());
						}
						else
						{
							check_task_preview_shouxu_dengjiTime.setText("-");
						}
						
						
						check_task_preview_shouxu_shiyongxingzhi
								.setText(((SoapObject) Items).getProperty(
												"Nature").toString());
						String paizhaoedu = ((SoapObject) Items).getProperty(
								"LicenceLines").toString();
						if(paizhaoedu.equals("1"))
						{
							check_task_preview_shouxu_paizhaoedu.setText("不含额度出售");
						}
						if(paizhaoedu.equals("0"))
						{
							check_task_preview_shouxu_paizhaoedu.setText("含额度出售");
						}
						
						String RegistrationStatus = ((SoapObject) Items).getProperty(
								"RegistrationStatus")
								.toString();
						if(RegistrationStatus.equals("0"))
						{
							check_task_preview_shouxu_banzhengzhuangtai.setText("可过户转籍");
						}
						if(RegistrationStatus.equals("1"))
						{
							check_task_preview_shouxu_banzhengzhuangtai.setText("只能过户");
						}
						if(RegistrationStatus.equals("2"))
						{
							check_task_preview_shouxu_banzhengzhuangtai.setText("过户转籍有次数限制");
						}
						if(RegistrationStatus.equals("3"))
						{
							check_task_preview_shouxu_banzhengzhuangtai.setText("不能过户转籍");
						}

						mHandler.sendEmptyMessage(shouxuSucessCase);
						
					} else {
						ToastUtilMgr.TextToast(context,
								getString(R.string.server_error1),
								ToastUtilMgr.LENGTH_LONG);
					}
				}
			}

			@Override
			public void failed(Message msg) {
				mHandler.sendEmptyMessage(shouxuFailCase);
				ToastUtilMgr.TextToast(context,"获取车辆手续信息失败",ToastUtilMgr.LENGTH_SHORT);
			}
		};
		shouxu_mgr = new SoapMgr(context, null, null, SoapAction.GetEnrolment,
				"GetEnrolment", soapParameter, shoxumyhandler, true, false);
	}

	private MyHandler car_statemyhandler;
	private SoapMgr car_state_mgr;
	private SoapObject car_state_backSoapObject;
	private static final int carStateSucessCase = 4;

	private static final int carStateFailCase = -4;

	/**
	 * 获取综合等级
	 */
	private void getcarStateInfo() {
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace,
				"GetCarState");
		soapParameter.addProperty("ResourceID", new AppPreference(context).getTaskId());
		car_statemyhandler = new MyHandler() {
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
						String AppearanceScoreName = ((SoapObject) Items)
								.getProperty("AppearanceScoreName").toString();						
						String InteriorScoreName = ((SoapObject) Items)
								.getProperty("InteriorScoreName").toString();
						String AccidentLevelName = ((SoapObject) Items)
								.getProperty("AccidentLevelName").toString();
						String SpecialCarName = ((SoapObject) Items)
								.getProperty("SpecialCarName").toString();
						String ComprehensiveScoreName = ((SoapObject) Items)
								.getProperty("ComprehensiveScoreName").toString();	
						String Comments = ((SoapObject) Items)
								.getProperty("Comments").toString();	
						String IsGHAuthCar = ((SoapObject) Items)
								.getProperty("IsGHAuth").toString();
						
						check_task_preview_waiguan_text.setText(AppearanceScoreName.equals("anyType{}")?"-":AppearanceScoreName);
						check_task_preview_neishi_text.setText(InteriorScoreName.equals("anyType{}")?"-":InteriorScoreName);
						check_task_preview_shigu_text.setText(AccidentLevelName.equals("anyType{}")?"-":AccidentLevelName);
						check_task_preview_teshu_text.setText(SpecialCarName.equals("anyType{}")?"-":SpecialCarName);
						check_task_preview_zonghe_text.setText(ComprehensiveScoreName.equals("anyType{}")?"-":ComprehensiveScoreName);
						check_task_preview_miaoshu_text.setText(Comments.equals("anyType{}")?"-":Comments);
						
						String renzheng = "";
						if(IsGHAuthCar.equals("1"))
						{
							renzheng = "是";
						}
						else
						{
							renzheng = "否";
						}
						check_task_preview_guanghui_renzheng_text.setText(renzheng);
						
						mHandler.sendEmptyMessage(carStateSucessCase);
					} else {
						ToastUtilMgr.TextToast(context,
								getString(R.string.server_error1),
								ToastUtilMgr.LENGTH_LONG);
					}
				}
			}

			@Override
			public void failed(Message msg) {
				mHandler.sendEmptyMessage(carStateFailCase);
				ToastUtilMgr.TextToast(context,"获取车况评估信息失败",ToastUtilMgr.LENGTH_SHORT);
			}
		};
		car_state_mgr = new SoapMgr(context, null, null,
				SoapAction.GetCarState, "GetCarState", soapParameter,
				car_statemyhandler, true, false);
	}

	private MyHandler car_modelmyhandler;
	private SoapMgr car_model_mgr;
	private SoapObject car_model_backSoapObject;
	private static final int carModelSucessCase = 3;

	private static final int carModelFailCase = -3;

	/**
	 * 获取车型信息
	 */
	private void getcarModelInfo() {
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace,
				"GetResourceModel");
		soapParameter.addProperty("ResourceID", new AppPreference(context).getTaskId());
		car_modelmyhandler = new MyHandler() {
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
						String Catalog = ((SoapObject) Items).getProperty(
								"Catalog").toString();
						check_task_preview_carModel_carModel.setText(
								CarCategory.equals("anyType{}")?"-":CarCategory);
						String Series = ((SoapObject) Items).getProperty(
								"Series").toString();
						check_task_preview_carModel_carSeries.setText(
								Series.equals("anyType{}")?"-":Series);
						String Model = ((SoapObject) Items).getProperty(
								"Model").toString();
						check_task_preview_carModel_detail_model.setText(
								CarModel.equals("anyType{}")?"-":CarModel);
						
						
						String color =((SoapObject) Items).getProperty(
								"Color").toString();
						check_task_preview_carModel_color.setText(Color);
						check_task_preview_carModel_distence.setText(((SoapObject) Items).getProperty(
								"DisplayKm").toString()+"KM");
						String ModConfiguration=((SoapObject) Items).getProperty(
								"ModConfiguration").toString();
						check_task_preview_carModel_changeConfiguration.setText(ModConfiguration.equals("anyType{}")?"-":ModConfiguration);						
						String OptionConfiguration=((SoapObject) Items).getProperty("OptionConfiguration").toString();
						check_task_preview_carModel_choiceConfiguration.setText(OptionConfiguration.equals("anyType{}")?"-":OptionConfiguration);
						String KeyConfiguration=((SoapObject) Items).getProperty("KeyConfiguration").toString();
						check_task_preview_carModel_keyConfiguration.setText(KeyConfiguration.equals("anyType{}")?"-":KeyConfiguration);

						mHandler.sendEmptyMessage(carModelSucessCase);
						
					} else {
						ToastUtilMgr.TextToast(context,
								getString(R.string.server_error1),
								ToastUtilMgr.LENGTH_LONG);
					}
				}
			}

			@Override
			public void failed(Message msg) {
				mHandler.sendEmptyMessage(carModelFailCase);
				ToastUtilMgr.TextToast(context,"获取车型信息失败",ToastUtilMgr.LENGTH_SHORT);
			}
		};
		car_model_mgr = new SoapMgr(context, null, null,
				SoapAction.GetResourceModel, "GetResourceModel", soapParameter,
				car_modelmyhandler, true, false);
	}

	
	/**
     * 提交数据   暂存1  提交2
     */
    private void saveCarInfo(final int type,String Price,String SuccessOption,String FailureReason)
    {		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "DetectionFinish");
		
		if(!FailureReason.trim().equals(""))
		{
			SuccessOption = "0";
		}
		
		
		soapParameter.addProperty("ResourceID", new AppPreference(context).getTaskId());
		soapParameter.addProperty("Price", Price.trim().equals("") ? "0" : Price.trim());
		soapParameter.addProperty("SuccessOption", SuccessOption);	
		soapParameter.addProperty("FailureReason", FailureReason);
		myhandler =  new MyHandler()
		{
			@Override
			public void success(Message msg) {
				backSoapObject = mgr.getServiceBackSoapObject();						
				if(backSoapObject!=null)
				{
					String Retcode = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Retcode").toString();
					//0成功，1失败
					if(Retcode.equals("0"))
					{
						
						ToastUtilMgr.TextToast(context,
								"数据已成功提交",
								ToastUtilMgr.LENGTH_SHORT);
						
						//删除之前保存的对应的数据
						
						ShouxuDengjiDB db1 = new ShouxuDengjiDB(context);
						db1.deletetByID(new AppPreference(context).getTaskId());
						db1.close();
						ChexingDengjiDB db2 = new ChexingDengjiDB(context);
						db2.deletetByID(new AppPreference(context).getTaskId());
						db2.close();
						ChekuangDengjiDB db3 = new ChekuangDengjiDB(context);
						db3.deletetByID(new AppPreference(context).getTaskId());
						db3.close();
						
						
						
//						if(type == 2)
//						{
							Intent intent = new Intent();
							intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							intent.setClass(context, ActivityMain.class);
							startActivity(intent);
							finish();
//						}
						
						
					}
				}
			}
			@Override
			public void failed(Message msg) {				
				
			}
		};
				mgr = new SoapMgr(context,
						null, 
						null, 
						SoapAction.DetectionFinish , 
						"DetectionFinish",
						soapParameter,
						myhandler,
						true,
						false
				);
    }
    
    
    //获取选装配置
    private String getOptionConfiguration(String OptionConfiguration)
    {
    	
    	String[] OptionConfigurations=OptionConfiguration.split(",");
    	String[] results=new String[OptionConfigurations.length];
    	if(OptionConfigurations.length >0)
    	{
    		CarConfigData carConfigData = new CarConfigData();
    		for (int i =0 ; i < OptionConfigurations.length;i++) {
    			String string=carConfigData.getOptionCofigurationById(OptionConfigurations[i]);
    			results[i]=string+" ";
    		}	
    	}
    	
    	StringBuffer sb= new StringBuffer();
    	if(results.length >0)
    	{	
    		for(int j = 0;j<results.length;j++)
    		{
    			sb.append(results[j]);
    		}    		
    	}
    	return sb.toString();
    }
    //获取改装配置
    private String getModConfiguration(String ModConfiguration)
    {    	
    	String[] ModConfigurations=ModConfiguration.split(",");
    	String[] results=new String[ModConfigurations.length];
    	if(ModConfigurations.length >0)
    	{
    		CarConfigData carConfigData = new CarConfigData();
    		for (int i =0 ; i < ModConfigurations.length;i++) {
    			String string=carConfigData.getModCofigurationById(ModConfigurations[i]);
    			results[i]=string+" ";
    		}	
    	}
    	
    	StringBuffer sb= new StringBuffer();
    	if(results.length >0)
    	{	
    		for(int j = 0;j<results.length;j++)
    		{
    			sb.append(results[j]);
    		}    		
    	}    	
    	return sb.toString();
    }
    //获取关键配置
    private String getKeyConfiguration(String KeyConfiguration)
    {
    	
    	String[] KeyConfigurations=KeyConfiguration.split("|");
    	
    	StringBuffer sb= new StringBuffer();
    	if(KeyConfigurations.length >0)
    	{	
    		for(int j = 0;j<KeyConfigurations.length;j++)
    		{
    			sb.append(KeyConfigurations[j]+" ");
    		}
    		
    	}
    	return sb.toString();
    }
    
    
    
    
    
    
    
    private SoapMgr mgr2;
    /**
     * 更改任务状态
     */
    private void SetResourceStatus(String ID,String status)
    {		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "SetResourceStatus");
		soapParameter.addProperty("ID", ID);
		soapParameter.addProperty("status", status);				
		MyHandler myhandler =  new MyHandler()
		{
			@Override
			public void success(Message msg) {
				SoapObject backSoapObject = mgr2.getServiceBackSoapObject();						
				if(backSoapObject!=null)
				{
					String Retcode = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Retcode").toString();
					//0成功，1失败
					if(Retcode.equals("0"))
					{						
						//删除本地的精密检测json.txt文件
						try
						{
							String sdCardPath = Environment.getExternalStorageDirectory().getPath();
					        File file = new File(sdCardPath + "/carcheck/json.txt");
							if(file.exists())
							{
								file.delete();
							}
						}
						catch(Exception ex)
						{
							Log.e("CheckTaskPreviewActivity。java error:", "delete json.txt error");
						}
						
						
						
						
						
						
						Intent intent = new Intent();
						intent.setClass(context, CheckTaskPreviewActivity3.class);
						startActivity(intent);
					}
					else
					{						
						ToastUtilMgr.TextToast(context, getString(R.string.service_back_error), ToastUtilMgr.LENGTH_LONG);
					}					
				}
			}
			@Override
			public void failed(Message msg) {				
				
			}
		};
		mgr2 = new SoapMgr(context,
						null, 
						null, 
						SoapAction.SetResourceStatus , 
						"SetResourceStatus",
						soapParameter,
						myhandler,
						true,
						false
				);
    }
    
    
    /**
     * 设置按钮监听
     */
    private void setListener()
    {
    	listener = new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				switch(v.getId())
				{
					//1详情  2修改
					case R.id.check_task_preview_btn_2_1:						
						//车辆手续详情
						show2_1();
						break;
					case R.id.check_task_preview_btn_2_2:
						//车辆手续修改
						Intent intent = new Intent();
						intent.setClass(context, ShouXuDengJiAcitivity.class);
						intent.putExtra("edit", true);
						startActivity(intent);						
						break;
					case R.id.check_task_preview_btn_3_1:
						//车型信息详情
						show3_1();
						break;
					case R.id.check_task_preview_btn_3_2:
						//车型信息修改
						Intent intent2 = new Intent();
						intent2.setClass(context, CarDengji1.class);
						intent2.putExtra("edit", true);
						intent2.putExtra("VIN", ly3txt3);
						startActivity(intent2);		
						break;
					case R.id.check_task_preview_btn_4_1:
						//车况评级详情
						show4_1();
						break;
					case R.id.check_task_preview_btn_4_2:
						//车况评级修改
						Intent intent3 = new Intent();
						intent3.setClass(context, JingMiCarCheckActivity.class);
						intent3.putExtra("edit", true);
						startActivity(intent3);		
						break;
				}
				
			}
		};
    }
    
    
    
    private void show2_1()
    {
    	AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.show();
		dialog.getWindow().setContentView(R.layout.dialog_shouxu_dengji);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt1)).setText(ly3txt1.equals("anyType{}")?"":ly3txt1);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt2)).setText(ly3txt2.equals("anyType{}")?"":ly3txt2);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt3)).setText(ly3txt3.equals("anyType{}")?"":ly3txt3);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt4)).setText(ly3txt4.equals("anyType{}")?"":ly3txt4);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt5)).setText(ly3txt5.equals("anyType{}")?"":ly3txt5);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt6)).setText(ly3txt6.equals("anyType{}")?"":ly3txt6);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt7)).setText(ly3txt7.equals("anyType{}")?"":ly3txt7);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt8)).setText(ly3txt8.equals("anyType{}")?"":ly3txt8);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt9)).setText(ly3txt9.equals("anyType{}")?"":ly3txt9);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt10)).setText(ly3txt10.equals("anyType{}")?"":ly3txt10);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt11)).setText(ly3txt11.equals("anyType{}")?"":ly3txt11);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt12)).setText(ly3txt12.equals("anyType{}")?"":ly3txt12);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt13)).setText(ly3txt13.equals("anyType{}")?"":ly3txt13);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt14)).setText(ly3txt14.equals("anyType{}")?"":ly3txt14);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt15)).setText(ly3txt15.equals("anyType{}")?"":ly3txt15);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt16)).setText(ly3txt16.equals("anyType{}")?"":ly3txt16);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt17)).setText(ly3txt17.equals("anyType{}")?"":ly3txt17);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt18)).setText(ly3txt18.equals("anyType{}")?"":ly3txt18);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt19)).setText(ly3txt19.equals("anyType{}")?"":ly3txt19);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt20)).setText(ly3txt20.equals("anyType{}")?"":ly3txt20);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt21)).setText(ly3txt21.equals("anyType{}")?"":ly3txt21);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt22)).setText(ly3txt22.equals("anyType{}")?"":ly3txt22);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt23)).setText(ly3txt23.equals("anyType{}")?"":ly3txt23);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly3_txt24)).setText(ly3txt24.equals("anyType{}")?"":ly3txt24);
    }
    
    
    
    private void show3_1()
    {
    	AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.show();
		dialog.getWindow().setContentView(R.layout.dialog_chexing_xinxi);		
		//车型信息
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly4_txt1)).setText(CarModel.equals("anyType{}")?"":CarModel);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly4_txt2)).setText(CarCategory.equals("anyType{}")?"":CarCategory);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly4_txt3)).setText(Color.equals("anyType{}")?"":Color);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly4_txt4)).setText(Transmission.equals("anyType{}")?"":Transmission);
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly4_txt5)).setText(Emission.equals("anyType{}")?"":Emission+"L");
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly4_txt6)).setText(EnginePower.equals("anyType{}")?"":EnginePower+"KW");
		((TextView) dialog.findViewById(R.id.check_task_preview2_ly4_txt7)).setText(InstrumentIsChanged.equals("anyType{}")?"":InstrumentIsChanged);
		((TextView) dialog.findViewById(R.id.check_task_preview2_carModel_keyConfiguration)).setText(KeyConfiguration.equals("anyType{}")?"":KeyConfiguration);
		((TextView) dialog.findViewById(R.id.check_task_preview2_carModel_choiceConfiguration)).setText(OptionConfiguration.equals("anyType{}")?"":OptionConfiguration);
		((TextView) dialog.findViewById(R.id.check_task_preview2_carModel_changeConfiguration)).setText(ModConfiguration.equals("anyType{}")?"":ModConfiguration);
		((TextView) dialog.findViewById(R.id.check_task_preview2_carModel_distence)).setText(DisplayKm.equals("anyType{}")?"":DisplayKm);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo1, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img1)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo2, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img2)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo3, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img3)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo4, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img4)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo5, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img5)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo6, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img6)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo7, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img7)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo8, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img8)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo9, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img9)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo10, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img10)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo11, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img11)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo12, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img12)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo13, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img13)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo14, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img14)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo15, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img15)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo16, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img16)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo17, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img17)),MyApplication.options);
		ImageLoader.getInstance().displayImage(SoapAction.host+Photo18, ((ImageView) dialog.findViewById(R.id.check_task_preview2_img18)),MyApplication.options);
    }
    
    private void show4_1()
    {
    	if(jingmi1.trim().equals("")&&jingmi2.trim().equals("")&&jingmi3.trim().equals("")&&jingmi4.trim().equals(""))
		{
			final MineAlert alert = new MineAlert(context);
			alert.createAlertOneButton(false,"暂无精密检测数据", 
					new View.OnClickListener() {								
						@Override
						public void onClick(View v) {
							alert.dimiss();
						}
					});
		}
		else
		{
			Intent intent = new Intent();
			intent.setClass(context, AllDefectShowActivity.class);

            JingMiCheckData.waiguan = jingmi1;
            JingMiCheckData.neishi = jingmi2;
            JingMiCheckData.gujia = jingmi3;
            JingMiCheckData.zhuangzhi = jingmi4;

//
//			intent.putExtra("waiguan", jingmi1);
//			intent.putExtra("neishi", jingmi2);
//			intent.putExtra("gujia", jingmi3);
//			intent.putExtra("zhuangzhi", jingmi4);
			
			String renzheng = "";
			if(IsGHAuth == 1)
			{
				renzheng = "是";
			}
			else
			{
				renzheng = "否";
			}
			
			intent.putExtra("renzheng", renzheng);
			startActivity(intent);
		}    	
    }
    
    
    
    /**
     * 获取用户的基本信息
     */
    private void GetResourceDetail(String ResourceID)
    {		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetResourceDetail");
		soapParameter.addProperty("ResourceID", ResourceID);		
		myhandler =  new MyHandler()
		{
			@Override
			public void success(Message msg) {
				backSoapObject2 = mgr.getServiceBackSoapObject();						
				if(backSoapObject2!=null)
				{
					String Retcode = ((SoapObject)backSoapObject2.getProperty(0)).getProperty("Retcode").toString();
					//0成功，1失败
					if(Retcode.equals("0"))
					{
						String ResourceID = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("ResourceID").toString();
						String CustomerName = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("CustomerName").toString();
						String CustomerMobile = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("CustomerMobile").toString();
						String Source = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Source").toString();
						String Intention = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Intention").toString();
						//任务信息						
						String ly2txt1 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("ProviderRegion").toString();
						String ly2txt2 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("ProviderShop").toString();
						String ly2txt3 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("ProviderDepartment").toString();
						String ly2txt4 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("ProviderPerson").toString();
						String ly2txt5 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("AllocTime").toString();
						String ly2txt6 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Region").toString();
						String ly2txt7 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Shop").toString();
						String ly2txt8 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("AppointmentTime").toString();
						String ly2txt9 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("User").toString();
						String ly2txt10 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("AcceptTime").toString();
						String ly2txt11 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("DetectionTime").toString();
						String ly2txt12 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("FinishTime").toString();
						//车辆手续信息
						ly3txt1 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("License").toString();
						ly3txt2 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("BelongingTo").toString();
						ly3txt3 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("IdentificationCode").toString();
						ly3txt4 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Nature").toString();
						ly3txt5 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Category").toString();
						ly3txt6 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("BrandModel").toString();
						ly3txt7 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("EngineSerialNum").toString();
						ly3txt8 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("ManufactureDate").toString();
						ly3txt9 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("FirstEnrolmentDate").toString();
						ly3txt10 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("ScrapDate").toString();
						ly3txt11 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("OwnerType").toString();
						ly3txt12 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("EmissionStandards").toString();
						ly3txt13 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("RegistrationStatus").toString();
						ly3txt14 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("ChangedNum").toString().equals("-1")?"0":((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("ChangedNum").toString()+"次";
						ly3txt15 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("PurchaseTax").toString();
						ly3txt16 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("InsuranceValidity").toString();
						ly3txt17 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("AnnualInspectionValidity").toString();
						ly3txt18 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("TollsValidity").toString();
						ly3txt19 = "扣"+ (((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("IllegalScore").toString().equals("-1")?"0":((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("IllegalScore").toString()) +"分"+
								 "罚款"+(((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("IllegalMoney").toString().equals("-1")?"0":((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("IllegalMoney").toString())+"元" ;
						ly3txt20 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("LicenceLines").toString();
						ly3txt21 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("OriginalKeys").toString();
						
						String miss = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("IsMissingTool").toString().equals("anyType{}")?"":((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("IsMissingTool").toString();
						String misstool = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("MissingTools").toString().equals("anyType{}")?"":((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("MissingTools").toString();
						
						ly3txt22 = miss + " "+ misstool;
						
						ly3txt23 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("HasInstruction").toString();
						ly3txt24 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("HasMaintenanceManual").toString();
						
						//车型信息
						CarModel = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("CarModel").toString();
						CarCategory = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("CarCategory").toString();
						Transmission = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Transmission").toString();
						Emission = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Emission").toString();
						EnginePower = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("EnginePower").toString();
						
						Color = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Color").toString();
						KeyConfiguration = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("KeyConfiguration").toString();
						OptionConfiguration = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("OptionConfiguration").toString();
						ModConfiguration = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("ModConfiguration").toString();
						DisplayKm = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("DisplayKm").toString();
						InstrumentIsChanged = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("InstrumentIsChanged").toString();
						Photo1 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo1").toString();
						Photo2 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo2").toString();
						Photo3 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo3").toString();
						Photo4 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo4").toString();
						Photo5 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo5").toString();
						Photo6 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo6").toString();
						Photo7 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo7").toString();
						Photo8 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo8").toString();
						Photo9 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo9").toString();
						Photo10 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo10").toString();
						Photo11 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo11").toString();
						Photo12 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo12").toString();
						Photo13 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo13").toString();
						Photo14 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo14").toString();
						Photo15 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo15").toString();
						Photo16 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo16").toString();
						Photo17 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo17").toString();
						Photo18 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo18").toString();
						GuidePrice = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("GuidePrice").toString();
						LowestPrice = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("LowestPrice").toString();
						UsedPriceLow = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("UsedPriceLow").toString();
						UsedPriceHigh = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("UsedPriceHigh").toString();
												
						AppearanceScore = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("AppearanceScore").toString();
						InteriorScore = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("InteriorScore").toString();
						AccidentLevel = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("AccidentLevel").toString();
						SpecialCar = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("SpecialCar").toString();
						
						Comments = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Comments").toString();
						
						jingmi1 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("AppearanceText").toString();
						jingmi2 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("InteriorText").toString();
						jingmi3 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("FrameworkText").toString();
						jingmi4 = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("InstallationText").toString();
						
						//
						ComprehensiveScore = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("ComprehensiveScore").toString();
						Price = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("Price").toString();
						SuccessOption = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("SuccessOption").toString();
						FailureReason = ((SoapObject)((SoapObject)backSoapObject2.getProperty(0)).getProperty("ResourceDetail")).getProperty("FailureReason").toString();
						
						
						
						//-----------------------------------------------------------------------
	
						
						

//						
//						
//						check_task_preview2_zonghe_text.setText(ComprehensiveScore.equals("anyType{}")?"":ComprehensiveScore);
//						check_task_preview2_shougou_price.setText(Price.equals("anyType{}")?"":Price);
//						check_task_preview2_shougou_chengjiao_info.setText(SuccessOption.equals("anyType{}")?"":SuccessOption);
//						check_task_preview2_shougou_weichengjiao_info.setText(FailureReason.equals("anyType{}")?"成交":"未成交原因 "+FailureReason);
//						
//												
//						check_task_preview2_waiguan_text.setText(AppearanceScore.equals("anyType{}")?"-":AppearanceScore);
//						check_task_preview2_neishi_text.setText(InteriorScore.equals("anyType{}")?"-":InteriorScore);
//						check_task_preview2_shigu_text.setText(AccidentLevel.equals("anyType{}")?"-":AccidentLevel);
//						check_task_preview2_teshu_text.setText(SpecialCar.equals("anyType{}")?"-":SpecialCar);
//						check_task_preview2_miaoshu_text.setText(Comments.equals("anyType{}")?"-":Comments);
//						check_task_preview2_guanghuirenzheng_text.setText(IsGHAuth.equals("anyType{}")?"-":IsGHAuth);
						
						
						
						mHandler.sendEmptyMessage(UserSucessCaseOK);
					}
					else
					{
							final MineAlert alert = new MineAlert(context);
							alert.createAlertOneButton(false,"服务器获取数据失败!", 
									new View.OnClickListener() {								
										@Override
										public void onClick(View v) {
											alert.dimiss();											
										}
									});
					}					
				}
			}
			@Override
			public void failed(Message msg) {				
				
			}
		};
				mgr = new SoapMgr(context,
						null, 
						null, 
						SoapAction.GetResourceDetail , 
						"GetResourceDetail",
						soapParameter,
						myhandler,
						true,
						false
				);
    }

	@Override
	protected void onResume() {
		
		
		
		GetResourceDetail(new AppPreference(context).getTaskId());
		super.onResume();
	}

    
    
    
    
    
}
