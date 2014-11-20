package com.guanghui.car;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.guanghui.car.application.MyApplication;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.jingmicheck.AllDefectShowActivity;
import com.guanghui.car.staticdata.JingMiCheckData;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 任务列表查看的任务信息
 * @author zhangyun
 *
 */
public class CheckTaskPreviewActivity2 extends Activity{
	
	private Context context = this;
	private Button check_task_preview2_jingmi;
	//客户信息
	private TextView check_task_preview2_user_name;
	private TextView check_task_preview2_user_mobile;
	private TextView check_task_preview2_user_shangjilaiyuan;
	private TextView check_task_preview2_user_yixiang;
	//任务信息
	private TextView check_task_preview2_ly2_txt1;
	private TextView check_task_preview2_ly2_txt2;
	private TextView check_task_preview2_ly2_txt3;
	private TextView check_task_preview2_ly2_txt4;
	private TextView check_task_preview2_ly2_txt5;	
	private TextView check_task_preview2_ly2_txt6;
	private TextView check_task_preview2_ly2_txt7;
	private TextView check_task_preview2_ly2_txt8;
	private TextView check_task_preview2_ly2_txt9;
	private TextView check_task_preview2_ly2_txt10;
	private TextView check_task_preview2_ly2_txt11;
	private TextView check_task_preview2_ly2_txt12;	
	// 车辆手续信息
	private TextView check_task_preview2_ly3_txt1;
	private TextView check_task_preview2_ly3_txt2;
	private TextView check_task_preview2_ly3_txt3;
	private TextView check_task_preview2_ly3_txt4;
	private TextView check_task_preview2_ly3_txt5;
	private TextView check_task_preview2_ly3_txt6;
	private TextView check_task_preview2_ly3_txt7;
	private TextView check_task_preview2_ly3_txt8;	
	private TextView check_task_preview2_ly3_txt9;
	private TextView check_task_preview2_ly3_txt10;
	private TextView check_task_preview2_ly3_txt11;
	private TextView check_task_preview2_ly3_txt12;
	private TextView check_task_preview2_ly3_txt13;
	private TextView check_task_preview2_ly3_txt14;
	private TextView check_task_preview2_ly3_txt15;
	private TextView check_task_preview2_ly3_txt16;
	private TextView check_task_preview2_ly3_txt17;
	private TextView check_task_preview2_ly3_txt18;
	private TextView check_task_preview2_ly3_txt19;
	private TextView check_task_preview2_ly3_txt20;	
	private TextView check_task_preview2_ly3_txt21;
	private TextView check_task_preview2_ly3_txt22;
	private TextView check_task_preview2_ly3_txt23;
	private TextView check_task_preview2_ly3_txt24;
	//车辆照片
	private ImageView check_task_preview2_img1;
	private ImageView check_task_preview2_img2;
	private ImageView check_task_preview2_img3;
	private ImageView check_task_preview2_img4;	
	private ImageView check_task_preview2_img5;
	private ImageView check_task_preview2_img6;
	private ImageView check_task_preview2_img7;
	private ImageView check_task_preview2_img8;
	private ImageView check_task_preview2_img9;
	private ImageView check_task_preview2_img10;
	private ImageView check_task_preview2_img11;
	private ImageView check_task_preview2_img12;
	private ImageView check_task_preview2_img13;
	private ImageView check_task_preview2_img14;
	private ImageView check_task_preview2_img15;
	private ImageView check_task_preview2_img16;
	private ImageView check_task_preview2_img17;
	private ImageView check_task_preview2_img18;
	// 车况评估信息
	private TextView check_task_preview2_zonghe_text;
	// 车型信息
	private TextView check_task_preview2_ly4_txt1;
	private TextView check_task_preview2_ly4_txt2;
	private TextView check_task_preview2_ly4_txt3;
	private TextView check_task_preview2_ly4_txt4;
	private TextView check_task_preview2_ly4_txt5;
	private TextView check_task_preview2_ly4_txt6;
	private TextView check_task_preview2_ly4_txt7;
	private TextView check_task_preview2_ly4_txt12;
	private TextView check_task_preview2_ly4_txt13;
	private TextView check_task_preview2_ly4_txt14;
	private TextView check_task_preview2_ly4_txt15;
	

	private TextView check_task_preview2_carModel_keyConfiguration;
	private TextView check_task_preview2_carModel_choiceConfiguration;
	private TextView check_task_preview2_carModel_changeConfiguration;
	private TextView check_task_preview2_carModel_distence;
	private Button check_task_preview2_btn_back;
	
	//成交说明
	private TextView check_task_preview2_shougou_price;
	private TextView check_task_preview2_shougou_chengjiao_info;
	private TextView check_task_preview2_shougou_weichengjiao_info;
	
	
	//外观评级
	private TextView check_task_preview2_waiguan_text;
	//内饰评级
	private TextView check_task_preview2_neishi_text;
	//事故评级
	private TextView check_task_preview2_shigu_text;
	//特殊车
	private TextView check_task_preview2_teshu_text;
	//车况描述
	private TextView check_task_preview2_miaoshu_text;
	//广汇认证车
	private TextView check_task_preview2_guanghuirenzheng_text;
	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	
	
	private String jingmi1;
	private String jingmi2;
	private String jingmi3;
	private String jingmi4;
	private String IsGHAuth;
	
	private Button check_task_preview2_btn_edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.check_task_preview2);
		
		new BuildTab(context,1) ;		

		String rid = this.getIntent().getExtras().getString("taskid");
		
		check_task_preview2_btn_back = (Button) findViewById(R.id.check_task_preview2_btn_back);
		check_task_preview2_btn_edit = (Button) findViewById(R.id.check_task_preview2_btn_edit);
		check_task_preview2_jingmi = (Button) findViewById(R.id.check_task_preview2_jingmi);
		// 客户
		check_task_preview2_user_name = (TextView) findViewById(R.id.check_task_preview2_user_name);
		check_task_preview2_user_mobile = (TextView) findViewById(R.id.check_task_preview2_user_mobile);
		check_task_preview2_user_shangjilaiyuan = (TextView) findViewById(R.id.check_task_preview2_shangjilaiyuan);
		check_task_preview2_user_yixiang = (TextView) findViewById(R.id.check_task_preview2_user_yixiang);
		//任务登记信息
		check_task_preview2_ly2_txt1 = (TextView) findViewById(R.id.check_task_preview2_ly2_txt1);
		check_task_preview2_ly2_txt2 = (TextView) findViewById(R.id.check_task_preview2_ly2_txt2);
		check_task_preview2_ly2_txt3 = (TextView) findViewById(R.id.check_task_preview2_ly2_txt3);
		check_task_preview2_ly2_txt4 = (TextView) findViewById(R.id.check_task_preview2_ly2_txt4);
		check_task_preview2_ly2_txt5 = (TextView) findViewById(R.id.check_task_preview2_ly2_txt5);
		check_task_preview2_ly2_txt6 = (TextView) findViewById(R.id.check_task_preview2_ly2_txt6);
		check_task_preview2_ly2_txt7 = (TextView) findViewById(R.id.check_task_preview2_ly2_txt7);
		check_task_preview2_ly2_txt8 = (TextView) findViewById(R.id.check_task_preview2_ly2_txt8);
		check_task_preview2_ly2_txt9 = (TextView) findViewById(R.id.check_task_preview2_ly2_txt9);
		check_task_preview2_ly2_txt10 = (TextView) findViewById(R.id.check_task_preview2_ly2_txt10);
		check_task_preview2_ly2_txt11 = (TextView) findViewById(R.id.check_task_preview2_ly2_txt11);
		check_task_preview2_ly2_txt12 = (TextView) findViewById(R.id.check_task_preview2_ly2_txt12);
		// 车辆手续信息
		check_task_preview2_ly3_txt1 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt1);
		check_task_preview2_ly3_txt2 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt2);
		check_task_preview2_ly3_txt3 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt3);
		check_task_preview2_ly3_txt4 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt4);
		check_task_preview2_ly3_txt5 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt5);
		check_task_preview2_ly3_txt6 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt6);
		check_task_preview2_ly3_txt7 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt7);
		check_task_preview2_ly3_txt8 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt8);	
		check_task_preview2_ly3_txt9 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt9);
		check_task_preview2_ly3_txt10 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt10);
		check_task_preview2_ly3_txt11 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt11);
		check_task_preview2_ly3_txt12 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt12);
		check_task_preview2_ly3_txt13 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt13);
		check_task_preview2_ly3_txt14 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt14);
		check_task_preview2_ly3_txt15 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt15);
		check_task_preview2_ly3_txt16 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt16);
		check_task_preview2_ly3_txt17 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt17);
		check_task_preview2_ly3_txt18 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt18);
		check_task_preview2_ly3_txt19 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt19);
		check_task_preview2_ly3_txt20 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt20);	
		check_task_preview2_ly3_txt21 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt21);
		check_task_preview2_ly3_txt22 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt22);
		check_task_preview2_ly3_txt23 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt23);
		check_task_preview2_ly3_txt24 = (TextView) findViewById(R.id.check_task_preview2_ly3_txt24);
		//车辆照片
		check_task_preview2_img1 = (ImageView) findViewById(R.id.check_task_preview2_img1);
		check_task_preview2_img2 = (ImageView) findViewById(R.id.check_task_preview2_img2);
		check_task_preview2_img3 = (ImageView) findViewById(R.id.check_task_preview2_img3);
		check_task_preview2_img4 = (ImageView) findViewById(R.id.check_task_preview2_img4);
		check_task_preview2_img5 = (ImageView) findViewById(R.id.check_task_preview2_img5);
		check_task_preview2_img6 = (ImageView) findViewById(R.id.check_task_preview2_img6);
		check_task_preview2_img7 = (ImageView) findViewById(R.id.check_task_preview2_img7);
		check_task_preview2_img8 = (ImageView) findViewById(R.id.check_task_preview2_img8);
		check_task_preview2_img9 = (ImageView) findViewById(R.id.check_task_preview2_img9);
		check_task_preview2_img10 = (ImageView) findViewById(R.id.check_task_preview2_img10);
		check_task_preview2_img11 = (ImageView) findViewById(R.id.check_task_preview2_img11);
		check_task_preview2_img12 = (ImageView) findViewById(R.id.check_task_preview2_img12);
		check_task_preview2_img13 = (ImageView) findViewById(R.id.check_task_preview2_img13);
		check_task_preview2_img14 = (ImageView) findViewById(R.id.check_task_preview2_img14);
		check_task_preview2_img15 = (ImageView) findViewById(R.id.check_task_preview2_img15);
		check_task_preview2_img16 = (ImageView) findViewById(R.id.check_task_preview2_img16);
		check_task_preview2_img17 = (ImageView) findViewById(R.id.check_task_preview2_img17);
		check_task_preview2_img18 = (ImageView) findViewById(R.id.check_task_preview2_img18);
		// 车型信息
		check_task_preview2_carModel_changeConfiguration = (TextView) findViewById(R.id.check_task_preview2_carModel_changeConfiguration);
		check_task_preview2_carModel_choiceConfiguration = (TextView) findViewById(R.id.check_task_preview2_carModel_choiceConfiguration);
		check_task_preview2_carModel_keyConfiguration = (TextView) findViewById(R.id.check_task_preview2_carModel_keyConfiguration);
		check_task_preview2_carModel_distence = (TextView) findViewById(R.id.check_task_preview2_carModel_distence);

		check_task_preview2_ly4_txt1 = (TextView) findViewById(R.id.check_task_preview2_ly4_txt1);
		check_task_preview2_ly4_txt2 = (TextView) findViewById(R.id.check_task_preview2_ly4_txt2);
		check_task_preview2_ly4_txt3 = (TextView) findViewById(R.id.check_task_preview2_ly4_txt3);
		check_task_preview2_ly4_txt4 = (TextView) findViewById(R.id.check_task_preview2_ly4_txt4);
		check_task_preview2_ly4_txt5 = (TextView) findViewById(R.id.check_task_preview2_ly4_txt5);
		check_task_preview2_ly4_txt6 = (TextView) findViewById(R.id.check_task_preview2_ly4_txt6);
		check_task_preview2_ly4_txt7 = (TextView) findViewById(R.id.check_task_preview2_ly4_txt7);
		check_task_preview2_ly4_txt12 = (TextView) findViewById(R.id.check_task_preview2_ly4_txt12);
		check_task_preview2_ly4_txt13 = (TextView) findViewById(R.id.check_task_preview2_ly4_txt13);
		check_task_preview2_ly4_txt14 = (TextView) findViewById(R.id.check_task_preview2_ly4_txt14);
		check_task_preview2_ly4_txt15 = (TextView) findViewById(R.id.check_task_preview2_ly4_txt15);
		
		
		
		
		check_task_preview2_waiguan_text = (TextView) findViewById(R.id.check_task_preview2_waiguan_text);
		check_task_preview2_neishi_text = (TextView) findViewById(R.id.check_task_preview2_neishi_text);
		check_task_preview2_shigu_text = (TextView) findViewById(R.id.check_task_preview2_shigu_text);
		check_task_preview2_teshu_text = (TextView) findViewById(R.id.check_task_preview2_teshu_text);
		check_task_preview2_zonghe_text = (TextView) findViewById(R.id.check_task_preview2_zonghe_text);	
		check_task_preview2_miaoshu_text = (TextView) findViewById(R.id.check_task_preview2_miaoshu_text);
		check_task_preview2_guanghuirenzheng_text = (TextView) findViewById(R.id.check_task_preview2_guanghuirenzheng_text);
		//成交说明
		check_task_preview2_shougou_price = (TextView) findViewById(R.id.check_task_preview2_shougou_price);
		check_task_preview2_shougou_chengjiao_info = (TextView) findViewById(R.id.check_task_preview2_shougou_chengjiao_info);
		check_task_preview2_shougou_weichengjiao_info = (TextView) findViewById(R.id.check_task_preview2_shougou_weichengjiao_info);
		
		
		//编辑任务信息
		check_task_preview2_btn_edit.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(context, EditTaskListActivity.class);
				startActivity(intent);
			}
		});
		
		
		
		check_task_preview2_btn_back.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				finish();				
			}
		});
		check_task_preview2_jingmi.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				
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
					
					intent.putExtra("renzheng", IsGHAuth);					
					startActivity(intent);
				}
				
				
				
				
			}
		});
		GetResourceDetail(rid);
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
				backSoapObject = mgr.getServiceBackSoapObject();						
				if(backSoapObject!=null)
				{
					String Retcode = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Retcode").toString();
					//0成功，1失败
					if(Retcode.equals("0"))
					{
						String ResourceID = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("ResourceID").toString();
						String CustomerName = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("CustomerName").toString();
						String CustomerMobile = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("CustomerMobile").toString();
						String Source = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Source").toString();
						String Intention = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Intention").toString();
						//任务信息						
						String ly2txt1 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("ProviderRegion").toString();
						String ly2txt2 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("ProviderShop").toString();
						String ly2txt3 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("ProviderDepartment").toString();
						String ly2txt4 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("ProviderPerson").toString();
						String ly2txt5 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("AllocTime").toString();
						String ly2txt6 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Region").toString();
						String ly2txt7 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Shop").toString();
						String ly2txt8 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("AppointmentTime").toString();
						String ly2txt9 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("User").toString();
						String ly2txt10 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("AcceptTime").toString();
						String ly2txt11 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("DetectionTime").toString();
						String ly2txt12 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("FinishTime").toString();
						//车辆手续信息
						String ly3txt1 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("License").toString();
						String ly3txt2 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("BelongingTo").toString();
						String ly3txt3 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("IdentificationCode").toString();
						String ly3txt4 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Nature").toString();
						String ly3txt5 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Category").toString();
						String ly3txt6 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("BrandModel").toString();
						String ly3txt7 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("EngineSerialNum").toString();
						String ly3txt8 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("ManufactureDate").toString();
						String ly3txt9 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("FirstEnrolmentDate").toString();
						String ly3txt10 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("ScrapDate").toString();
						String ly3txt11 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("OwnerType").toString();
						String ly3txt12 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("EmissionStandards").toString();
						String ly3txt13 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("RegistrationStatus").toString();
						String ly3txt14 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("ChangedNum").toString()+"次";
						String ly3txt15 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("PurchaseTax").toString();
						String ly3txt16 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("InsuranceValidity").toString();
						String ly3txt17 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("AnnualInspectionValidity").toString();
						String ly3txt18 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("TollsValidity").toString();
						String ly3txt19 = "扣"+((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("IllegalScore").toString() +"分"+
										  "罚款"+((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("IllegalMoney").toString()+"元";
						String ly3txt20 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("LicenceLines").toString();
						String ly3txt21 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("OriginalKeys").toString();
						
						String miss = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("IsMissingTool").toString().equals("anyType{}")?"":((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("IsMissingTool").toString();
						String misstool = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("MissingTools").toString().equals("anyType{}")?"":((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("MissingTools").toString();
						
						String ly3txt22 = miss + " "+ misstool;
						
						String ly3txt23 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("HasInstruction").toString();
						String ly3txt24 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("HasMaintenanceManual").toString();
						
						//车型信息
						String CarModel = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("CarModel").toString();
						String CarCategory = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("CarCategory").toString();
						String Transmission = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Transmission").toString();
						String Emission = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Emission").toString();
						String EnginePower = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("EnginePower").toString();
						
						String Color = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Color").toString();
						String KeyConfiguration = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("KeyConfiguration").toString();
						String OptionConfiguration = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("OptionConfiguration").toString();
						String ModConfiguration = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("ModConfiguration").toString();
						String DisplayKm = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("DisplayKm").toString();
						String InstrumentIsChanged = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("InstrumentIsChanged").toString();
						String Photo1 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo1").toString();
						String Photo2 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo2").toString();
						String Photo3 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo3").toString();
						String Photo4 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo4").toString();
						String Photo5 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo5").toString();
						String Photo6 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo6").toString();
						String Photo7 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo7").toString();
						String Photo8 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo8").toString();
						String Photo9 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo9").toString();
						String Photo10 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo10").toString();
						String Photo11 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo11").toString();
						String Photo12 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo12").toString();
						String Photo13 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo13").toString();
						String Photo14 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo14").toString();
						String Photo15 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo15").toString();
						String Photo16 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo16").toString();
						String Photo17 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo17").toString();
						String Photo18 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Photo18").toString();
						String GuidePrice = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("GuidePrice").toString();
						String LowestPrice = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("LowestPrice").toString();
						String UsedPriceLow = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("UsedPriceLow").toString();
						String UsedPriceHigh = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("UsedPriceHigh").toString();
												
						String AppearanceScore = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("AppearanceScore").toString();
						String InteriorScore = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("InteriorScore").toString();
						String AccidentLevel = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("AccidentLevel").toString();
						String SpecialCar = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("SpecialCar").toString();
						
						String Comments = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Comments").toString();
						IsGHAuth = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("IsGHAuth").toString();
						
						jingmi1 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("AppearanceText").toString();
						jingmi2 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("InteriorText").toString();
						jingmi3 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("FrameworkText").toString();
						jingmi4 = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("InstallationText").toString();
                        System.out.println("jingmi1:"+jingmi1);

                        //
						String ComprehensiveScore = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("ComprehensiveScore").toString();
						String Price = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("Price").toString();
						String SuccessOption = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("SuccessOption").toString();
						String FailureReason = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("ResourceDetail")).getProperty("FailureReason").toString();
						
						
						
						//-----------------------------------------------------------------------
						check_task_preview2_user_name.setText(CustomerName.equals("anyType{}")?"":CustomerName);
						check_task_preview2_user_mobile.setText(CustomerMobile.equals("anyType{}")?"":CustomerMobile);
						check_task_preview2_user_shangjilaiyuan.setText(Source.equals("anyType{}")?"":Source);
						check_task_preview2_user_yixiang.setText(Intention.equals("anyType{}")?"":Intention);
						//任务信息
						check_task_preview2_ly2_txt1.setText(ly2txt1.equals("anyType{}")?"":ly2txt1);
						check_task_preview2_ly2_txt2.setText(ly2txt2.equals("anyType{}")?"":ly2txt2);
						check_task_preview2_ly2_txt3.setText(ly2txt3.equals("anyType{}")?"":ly2txt3);
						check_task_preview2_ly2_txt4.setText(ly2txt4.equals("anyType{}")?"":ly2txt4);
						check_task_preview2_ly2_txt5.setText(ly2txt5.equals("anyType{}")?"":ly2txt5);
						check_task_preview2_ly2_txt6.setText(ly2txt6.equals("anyType{}")?"":ly2txt6);
						check_task_preview2_ly2_txt7.setText(ly2txt7.equals("anyType{}")?"":ly2txt7);
						check_task_preview2_ly2_txt8.setText(ly2txt8.equals("anyType{}")?"":ly2txt8);
						check_task_preview2_ly2_txt9.setText(ly2txt9.equals("anyType{}")?"":ly2txt9);
						check_task_preview2_ly2_txt10.setText(ly2txt10.equals("anyType{}")?"":ly2txt10);
						check_task_preview2_ly2_txt11.setText(ly2txt11.equals("anyType{}")?"":ly2txt11);
						check_task_preview2_ly2_txt12.setText(ly2txt12.equals("anyType{}")?"":ly2txt12);
						// 车辆手续信息
						check_task_preview2_ly3_txt1.setText(ly3txt1.equals("anyType{}")?"":ly3txt1);
						check_task_preview2_ly3_txt2.setText(ly3txt2.equals("anyType{}")?"":ly3txt2);
						check_task_preview2_ly3_txt3.setText(ly3txt3.equals("anyType{}")?"":ly3txt3);
						check_task_preview2_ly3_txt4.setText(ly3txt4.equals("anyType{}")?"":ly3txt4);
						check_task_preview2_ly3_txt5.setText(ly3txt5.equals("anyType{}")?"":ly3txt5);
						check_task_preview2_ly3_txt6.setText(ly3txt6.equals("anyType{}")?"":ly3txt6);
						check_task_preview2_ly3_txt7.setText(ly3txt7.equals("anyType{}")?"":ly3txt7);
						check_task_preview2_ly3_txt8.setText(ly3txt8.equals("anyType{}")?"":ly3txt8);
						check_task_preview2_ly3_txt9.setText(ly3txt9.equals("anyType{}")?"":ly3txt9);
						check_task_preview2_ly3_txt10.setText(ly3txt10.equals("anyType{}")?"":ly3txt10);
						check_task_preview2_ly3_txt11.setText(ly3txt11.equals("anyType{}")?"":ly3txt11);
						check_task_preview2_ly3_txt12.setText(ly3txt12.equals("anyType{}")?"":ly3txt12);
						check_task_preview2_ly3_txt13.setText(ly3txt13.equals("anyType{}")?"":ly3txt13);
						
						
						ly3txt14 = ly3txt14.equals("anyType{}")?"":ly3txt14;
						
						if(ly3txt14.indexOf("-1")!=-1)
						{
							ly3txt14 = ly3txt14.replace("-1", "0");
						}
						
						check_task_preview2_ly3_txt14.setText( ly3txt14 );
						check_task_preview2_ly3_txt15.setText(ly3txt15.equals("anyType{}")?"":ly3txt15);
						check_task_preview2_ly3_txt16.setText(ly3txt16.equals("anyType{}")?"":ly3txt16);
						check_task_preview2_ly3_txt17.setText(ly3txt17.equals("anyType{}")?"":ly3txt17);
						check_task_preview2_ly3_txt18.setText(ly3txt18.equals("anyType{}")?"":ly3txt18);
						
						ly3txt19 = ly3txt19.equals("anyType{}")?"":ly3txt19;
						
						if(ly3txt19.indexOf("-1")!=-1)
						{
							ly3txt19 = ly3txt19.replace("-1", "0");
						}
						
						
						check_task_preview2_ly3_txt19.setText(ly3txt19);
						check_task_preview2_ly3_txt20.setText(ly3txt20.equals("anyType{}")?"":ly3txt20);
						check_task_preview2_ly3_txt21.setText(ly3txt21.equals("anyType{}")?"":ly3txt21);
						check_task_preview2_ly3_txt22.setText(ly3txt22.equals("anyType{}")?"":ly3txt22);
						check_task_preview2_ly3_txt23.setText(ly3txt23.equals("anyType{}")?"":ly3txt23);
						check_task_preview2_ly3_txt24.setText(ly3txt24.equals("anyType{}")?"":ly3txt24);
						
						
						
						//车型信息
						check_task_preview2_ly4_txt1.setText(CarModel.equals("anyType{}")?"":CarModel);
						check_task_preview2_ly4_txt2.setText(CarCategory.equals("anyType{}")?"":CarCategory);
						check_task_preview2_ly4_txt3.setText(Color.equals("anyType{}")?"":Color);
						check_task_preview2_ly4_txt4.setText(Transmission.equals("anyType{}")?"":Transmission);
						check_task_preview2_ly4_txt5.setText(Emission.equals("anyType{}")?"":Emission+"L");
						check_task_preview2_ly4_txt6.setText(EnginePower.equals("anyType{}")?"":EnginePower+"KW");
						check_task_preview2_ly4_txt7.setText(InstrumentIsChanged.equals("anyType{}")?"":InstrumentIsChanged);
						check_task_preview2_ly4_txt12.setText(GuidePrice.equals("anyType{}")?"":GuidePrice+"元");
						check_task_preview2_ly4_txt13.setText(LowestPrice.equals("anyType{}")?"":LowestPrice+"元");
						check_task_preview2_ly4_txt14.setText(UsedPriceLow.equals("anyType{}")?"":UsedPriceLow+"元");
						check_task_preview2_ly4_txt15.setText(UsedPriceHigh.equals("anyType{}")?"":UsedPriceHigh+"元");
						
						
					
						
						
						check_task_preview2_carModel_keyConfiguration.setText(KeyConfiguration.equals("anyType{}")?"":KeyConfiguration);
						check_task_preview2_carModel_choiceConfiguration.setText(OptionConfiguration.equals("anyType{}")?"":OptionConfiguration);
						check_task_preview2_carModel_changeConfiguration.setText(ModConfiguration.equals("anyType{}")?"":ModConfiguration);
						check_task_preview2_carModel_distence.setText(DisplayKm.equals("anyType{}")?"":DisplayKm);
						
						
						
//						check_task_preview2_img1.setVisibility(View.GONE);
//						check_task_preview2_img2.setVisibility(View.GONE);
//						check_task_preview2_img3.setVisibility(View.GONE);
//						check_task_preview2_img4.setVisibility(View.GONE);
						
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo1, check_task_preview2_img1,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo2, check_task_preview2_img2,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo3, check_task_preview2_img3,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo4, check_task_preview2_img4,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo5, check_task_preview2_img5,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo6, check_task_preview2_img6,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo7, check_task_preview2_img7,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo8, check_task_preview2_img8,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo9, check_task_preview2_img9,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo10, check_task_preview2_img10,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo11, check_task_preview2_img11,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo12, check_task_preview2_img12,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo13, check_task_preview2_img13,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo14, check_task_preview2_img14,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo15, check_task_preview2_img15,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo16, check_task_preview2_img16,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo17, check_task_preview2_img17,MyApplication.options);
						ImageLoader.getInstance().displayImage(SoapAction.host+Photo18, check_task_preview2_img18,MyApplication.options);

						
						check_task_preview2_zonghe_text.setText(ComprehensiveScore.equals("anyType{}")?"":ComprehensiveScore);
						check_task_preview2_shougou_price.setText(Price.equals("anyType{}")?"":Price);
						check_task_preview2_shougou_chengjiao_info.setText(SuccessOption.equals("anyType{}")?"":SuccessOption);
						check_task_preview2_shougou_weichengjiao_info.setText(FailureReason.equals("anyType{}")?"成交":"未成交原因 "+FailureReason);
						
												
						check_task_preview2_waiguan_text.setText(AppearanceScore.equals("anyType{}")?"-":AppearanceScore);
						check_task_preview2_neishi_text.setText(InteriorScore.equals("anyType{}")?"-":InteriorScore);
						check_task_preview2_shigu_text.setText(AccidentLevel.equals("anyType{}")?"-":AccidentLevel);
						check_task_preview2_teshu_text.setText(SpecialCar.equals("anyType{}")?"-":SpecialCar);
						check_task_preview2_miaoshu_text.setText(Comments.equals("anyType{}")?"-":Comments);
						check_task_preview2_guanghuirenzheng_text.setText(IsGHAuth.equals("anyType{}")?"-":IsGHAuth);
					}
					else
					{
							final MineAlert alert = new MineAlert(context);
							alert.createAlertOneButton(false,"服务器获取数据失败!", 
									new View.OnClickListener() {								
										@Override
										public void onClick(View v) {
											alert.dimiss();
											CheckTaskPreviewActivity2.this.finish();
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
	protected void onDestroy() {
		super.onDestroy();
		
		JingMiCheckData.clear();
		
	}
}
