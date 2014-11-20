package com.guanghui.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.guanghui.car.adapter.CarDengjiAdapter;
import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;
import com.guanghui.car.db.NewChexingDengjiDB;

/**
 * 新的车型登记新界面
 * 
 * @author zhangyun
 * 
 */
public class CarDengji extends Activity {

	private Context context = this;
	private String guanjianpeizhi;
	private String qitapeizhi;
	private String gaizhuangpeizhi;
	private String xinghao;
	private Button shuaxin_car_chexing;
	private boolean isjiexi = false;
	//VIN码
	private EditText edt_vin;
	private Button btn_jiexi1;
	//车辆类型
	private Spinner spineer_car_leixing;
	//车型
	private Spinner spineer_car_chexing1;
	private Spinner spineer_car_chexing2;
	private Spinner spineer_car_chexing3;
	private Spinner spineer_car_chexing4;
	private Spinner spineer_car_chexing5;
	//其他
	private CheckBox checkbox_other;
	private Spinner spineer_other1;
	private EditText spineer_other2;
	private Spinner spineer_other3;
	private Spinner spineer_other4;
	private Spinner spineer_other5;
	private Spinner spineer_other6;
	private Spinner spineer_other7;
	private Spinner spineer_other8;
	private Spinner spineer_other9;
	//变速箱类型
	private Spinner spineer_car_biansuxiang;
	private TextView txt_car_biansuxiang_tip;
	//排量
	private EditText edt_pailiang;
	//发动机功率
	private EditText edt_gonglv;
	//--------------------------------------------
	//------------关键配置--------------------------
	//燃料类型
	private RadioButton radio_ranliao1;
	private RadioButton radio_ranliao2;
	private RadioButton radio_ranliao3;
	private RadioButton radio_ranliao4;
	private RadioButton radio_ranliao5;
	private RadioButton radio_ranliao6;
	private RadioButton radio_ranliao7;
	//钥匙
	private RadioButton radio_yaoshi1;
	private RadioButton radio_yaoshi2;
	private RadioButton radio_yaoshi3;
	//ABS
	private RadioButton radio_abs1;
	private RadioButton radio_abs2;
	//助力转向
	private RadioButton radio_zhuli1;
	private RadioButton radio_zhuli2;
	//轮圈
	private RadioButton radio_lunquan1;
	private RadioButton radio_lunquan2;
	private RadioButton radio_lunquan3;
	//安全气囊
	private RadioButton radio_qinang1;
	private RadioButton radio_qinang2;
	private CheckBox check_qinang1;
	private CheckBox check_qinang2;
	private CheckBox check_qinang3;
	private CheckBox check_qinang4;
	private CheckBox check_qinang5;
	private CheckBox check_qinang6;
	private CheckBox check_qinang7;	
	//天窗
	private RadioButton radio_tianchuang1;
	private RadioButton radio_tianchuang2;
	private RadioButton radio_tianchuang3;
	private RadioButton radio_tianchuang4;
	//空调
	private RadioButton radio_kongtiao1;
	private RadioButton radio_kongtiao2;
	private CheckBox check_kongtiao1;
	private CheckBox check_kongtiao2;
	private CheckBox check_kongtiao3;
	//门窗
	private RadioButton radio_menchuang1;
	private RadioButton radio_menchuang2;
	private RadioButton radio_menchuang3;
	//后视镜
	private RadioButton radio_houshijing1;
	private RadioButton radio_houshijing2;
	private RadioButton radio_houshijing3;
	private RadioButton radio_houshijing4;
	//座椅材质
	private RadioButton radio_zuoyi_caizhi1;
	private RadioButton radio_zuoyi_caizhi2;
	//座椅功能
	private CheckBox check_zuoyi1;
	private CheckBox check_zuoyi2;
	private CheckBox check_zuoyi3;
	private CheckBox check_zuoyi4;
	private CheckBox check_zuoyi5;
	private CheckBox check_zuoyi6;
	private CheckBox check_zuoyi7;
	private CheckBox check_zuoyi8;
	private CheckBox check_zuoyi9;
	private CheckBox check_zuoyi10;
	//方向盘
	private RadioButton radio_fangxiangpan1;
	private RadioButton radio_fangxiangpan2;
	private CheckBox check_fangxiangpan1;
	private CheckBox check_fangxiangpan2;
	private CheckBox check_fangxiangpan3;
	private CheckBox check_fangxiangpan4;
	private CheckBox check_fangxiangpan5;
	//大灯
	private RadioButton radio_dadeng1;
	private RadioButton radio_dadeng2;
	//CD/DVD
	private RadioButton radio_dvd1;
	private RadioButton radio_dvd2;
	private RadioButton radio_dvd3;
	private RadioButton radio_dvd4;
	private RadioButton radio_dvd5;
	//液晶屏
	private RadioButton radio_yejingpin1;
	private RadioButton radio_yejingpin2;
	private CheckBox check_yejingpin1;
	private CheckBox check_yejingpin2;
	private CheckBox check_yejingpin3;
	private CheckBox check_yejingpin4;
	//其他配置
	private RadioButton radio_other_peizhi1;
	private RadioButton radio_other_peizhi2;
	private CheckBox check_other_peizhi1;
	private CheckBox check_other_peizhi2;
	private CheckBox check_other_peizhi3;
	private CheckBox check_other_peizhi4;
	private CheckBox check_other_peizhi5;
	private CheckBox check_other_peizhi6;
	private CheckBox check_other_peizhi7;
	private CheckBox check_other_peizhi8;
	private CheckBox check_other_peizhi9;
	private CheckBox check_other_peizhi10;
	private CheckBox check_other_peizhi11;
	private CheckBox check_other_peizhi12;
	private CheckBox check_other_peizhi13;
	private CheckBox check_other_peizhi14;
	private CheckBox check_other_peizhi15;
	private CheckBox check_other_peizhi16;
	private CheckBox check_other_peizhi17;
	private CheckBox check_other_peizhi18;
	private CheckBox check_other_peizhi19;
	private CheckBox check_other_peizhi20;
	private CheckBox check_other_peizhi21;
	private CheckBox check_other_peizhi22;
	private CheckBox check_other_peizhi23;
	//选装配置
	private EditText edt_xuanzhuang_peizhi;
	//服务器交互用
	private MyHandler car_modelmyhandler;
	private SoapMgr car_model_mgr;
	private SoapObject car_model_backSoapObject;
	
	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	
	private boolean edit;
	private String HistoryResourceID;
	private List<HashMap> listmap1,listmap7,listmap8,listmap9,listmap10,listmap11,listmap12,listmap13,listmap14,listmap15;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.car_dengji);
		
		edit = this.getIntent().getBooleanExtra("edit", false);
		
		
		new BuildTab(context,1) ;
		initUI();
		initTitle();
		initBottom();
		
		
		String VIN = this.getIntent().getStringExtra("VIN").trim();
		HistoryResourceID = new AppPreference(context).getHistoryResourceID();
		
		
		edt_vin.setText(VIN.equals("anyType{}")?"":VIN);
		
		
		spineer_other1.setVisibility(View.GONE);
		spineer_other2.setVisibility(View.GONE);
		spineer_other3.setVisibility(View.GONE);
		spineer_other4.setVisibility(View.GONE);
		spineer_other5.setVisibility(View.GONE);
		spineer_other6.setVisibility(View.GONE);
		spineer_other7.setVisibility(View.GONE);
		spineer_other8.setVisibility(View.GONE);
		spineer_other9.setVisibility(View.GONE);
		
		
		
		
		
		shuaxin_car_chexing.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				getcarModelInfo();
				
			}
		});
		
		
		
		btn_jiexi1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//解析VIN码
				if(!edt_vin.getText().toString().trim().equals(""))
				{
					isjiexi = true;
					VINParse();
					
				}
				else
				{
					isjiexi = false;
					final MineAlert alert = new MineAlert(context);
					alert.createAlertOneButton(false,"VIN码不能为空", 
							new View.OnClickListener() {								
								@Override
								public void onClick(View v) {
									alert.dimiss();
								}
							});
				}
			}
		});
		
		checkbox_other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(isChecked)
				{
					spineer_other1.setVisibility(View.VISIBLE);
					spineer_other2.setVisibility(View.VISIBLE);
					spineer_other3.setVisibility(View.VISIBLE);
					spineer_other4.setVisibility(View.VISIBLE);
					spineer_other5.setVisibility(View.VISIBLE);
					spineer_other6.setVisibility(View.VISIBLE);
					spineer_other7.setVisibility(View.VISIBLE);
					spineer_other8.setVisibility(View.VISIBLE);
					spineer_other9.setVisibility(View.VISIBLE);
				}
				else
				{
					spineer_other1.setVisibility(View.GONE);
					spineer_other2.setVisibility(View.GONE);
					spineer_other3.setVisibility(View.GONE);
					spineer_other4.setVisibility(View.GONE);
					spineer_other5.setVisibility(View.GONE);
					spineer_other6.setVisibility(View.GONE);
					spineer_other7.setVisibility(View.GONE);
					spineer_other8.setVisibility(View.GONE);
					spineer_other9.setVisibility(View.GONE);
				}				
			}
			
		});
		
		
		
		
		
		spineer_car_chexing1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Log.e("spineer_car_chexing1.setOnItemSelectedListener", isjiexi+"");
				if(!isjiexi)
				{					
					try
					{
						GetManufacturers2(spineer_car_chexing1.getSelectedView().getTag().toString());		
					}
					catch(Exception ex)
					{
						isjiexi = false;
						spineer_car_chexing2.setAdapter(new CarDengjiAdapter(context,new ArrayList<HashMap>() ));
					}
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {				
			}
			
		});
		
		spineer_car_chexing2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Log.e("spineer_car_chexing2.setOnItemSelectedListener", isjiexi+"");
				if(!isjiexi)
				{
					try
					{
						GetCatalogs2(spineer_car_chexing1.getSelectedView().getTag().toString(),spineer_car_chexing2.getSelectedView().getTag().toString());						
					}
					catch(Exception ex)
					{
						isjiexi = false;
						spineer_car_chexing3.setAdapter(new CarDengjiAdapter(context,new ArrayList<HashMap>() ));
					}
					
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {				
			}
			
		});
		
		spineer_car_chexing3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Log.e("spineer_car_chexing3.setOnItemSelectedListener", isjiexi+"");
				if(!isjiexi)
				{
					try
					{
						GetSeriesList2(spineer_car_chexing3.getSelectedView().getTag().toString());
					}
					catch(Exception ex)
					{
						isjiexi = false;
						spineer_car_chexing4.setAdapter(new CarDengjiAdapter(context,new ArrayList<HashMap>() ));
					}
					
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {				
			}
			
		});
		
		spineer_car_chexing4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				isjiexi = false;
				
				Log.e("spineer_car_chexing4.setOnItemSelectedListener", isjiexi+"");
				if(!isjiexi)
				{
					try
					{
						GetModels2(spineer_car_chexing3.getSelectedView().getTag().toString(),spineer_car_chexing4.getSelectedView().getTag().toString());
					}
					catch(Exception ex)
					{
						isjiexi = false;
						spineer_car_chexing5.setAdapter(new CarDengjiAdapter(context,new ArrayList<HashMap>() ));
					}
					
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {				
			}
			
		});
		
		
		
		getcarModelInfo();
		
	}
	
	private void initTitle() {
		((TextView) findViewById(R.id.title_renwu_btn1))
		.setBackgroundDrawable(context.getResources().getDrawable(
				R.drawable.title_bg));
		((TextView) findViewById(R.id.title_renwu_btn2))
		.setBackgroundDrawable(context.getResources().getDrawable(
				R.drawable.bg));
		((TextView) findViewById(R.id.title_renwu_btn3))
		.setBackgroundDrawable(context.getResources().getDrawable(
				R.drawable.title_bg));
	}
	
	
	private void initBottom() {
		((Button) findViewById(R.id.stop_save))
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if(saveData())
						{
							final MineAlert alert = new MineAlert(context);
							alert.createAlertOneButton(false,"已成功保存", 
									new View.OnClickListener() {								
										@Override
										public void onClick(View v) {
											alert.dimiss();
										}
									});
						}
						else
						{
							final MineAlert alert = new MineAlert(context);
							alert.createAlertOneButton(false,"保存失败", 
									new View.OnClickListener() {								
										@Override
										public void onClick(View v) {
											alert.dimiss();
										}
									});
						}
					}
				});
		((Button) findViewById(R.id.comfirm_communit))
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						sendEnrolment();
						
					}
				});
	}
	
	private void sendEnrolment() {

		saveData();
		String vin = edt_vin.getText().toString().trim();
		String pailiang = edt_pailiang.getText().toString().trim();
		String gonglv = edt_gonglv.getText().toString().trim();
		if(vin.equals("") || pailiang.equals("") || gonglv.equals(""))
		{
			final MineAlert alert = new MineAlert(context);
			alert.createAlertOneButton(false,"VIN码,排量,发动机功率不能为空!", 
					new View.OnClickListener() {								
						@Override
						public void onClick(View v) {
							alert.dimiss();
						}
					});
			return;
		}
		if(edt_xuanzhuang_peizhi.getText().toString().trim().equals("") )
		{
			final MineAlert alert = new MineAlert(context);
			alert.createAlertOneButton(false,"改装配置信息不能为空!", 
					new View.OnClickListener() {								
						@Override
						public void onClick(View v) {
							alert.dimiss();
						}
					});
			return;
		}
		if(!checkbox_other.isChecked())
		{
			Log.e("spineer_car_chexing1.getSelectedView().getTag()", spineer_car_chexing1.getSelectedView().getTag()+"");
			if(spineer_car_chexing1.getSelectedView()== null)
			{
				final MineAlert alert = new MineAlert(context);
				alert.createAlertOneButton(false,"请刷新并选择品牌!", 
						new View.OnClickListener() {								
							@Override
							public void onClick(View v) {
								alert.dimiss();
							}
						});
				return;
			}
			if(spineer_car_chexing1.getSelectedItem().toString().equals("请选择品牌"))
			{
				final MineAlert alert = new MineAlert(context);
				alert.createAlertOneButton(false,"请选择品牌!", 
						new View.OnClickListener() {								
							@Override
							public void onClick(View v) {
								alert.dimiss();
							}
						});
				return;
			}
			if(spineer_car_chexing2.getSelectedView()== null)
			{
				final MineAlert alert = new MineAlert(context);
				alert.createAlertOneButton(false,"请选择厂家!", 
						new View.OnClickListener() {								
							@Override
							public void onClick(View v) {
								alert.dimiss();
							}
						});
				return;
			}
			if(spineer_car_chexing2.getSelectedItem().toString().equals("请选择厂家"))
			{
				final MineAlert alert = new MineAlert(context);
				alert.createAlertOneButton(false,"请选择厂家!", 
						new View.OnClickListener() {								
							@Override
							public void onClick(View v) {
								alert.dimiss();
							}
						});
				return;
			}
			if(spineer_car_chexing3.getSelectedView() == null)
			{
				final MineAlert alert = new MineAlert(context);
				alert.createAlertOneButton(false,"请选择车型!", 
						new View.OnClickListener() {								
							@Override
							public void onClick(View v) {
								alert.dimiss();
							}
						});
				return;
			}
			if(spineer_car_chexing3.getSelectedItem().toString().equals("请选择车型"))
			{
				final MineAlert alert = new MineAlert(context);
				alert.createAlertOneButton(false,"请选择车型!", 
						new View.OnClickListener() {								
							@Override
							public void onClick(View v) {
								alert.dimiss();
							}
						});
				return;
			}
			if(spineer_car_chexing4.getSelectedView() == null)
			{
				final MineAlert alert = new MineAlert(context);
				alert.createAlertOneButton(false,"请选择年款!", 
						new View.OnClickListener() {								
							@Override
							public void onClick(View v) {
								alert.dimiss();
							}
						});
				return;
			}
			if(spineer_car_chexing4.getSelectedItem().toString().equals("请选择年款"))
			{
				final MineAlert alert = new MineAlert(context);
				alert.createAlertOneButton(false,"请选择年款!", 
						new View.OnClickListener() {								
							@Override
							public void onClick(View v) {
								alert.dimiss();
							}
						});
				return;
			}
			if(spineer_car_chexing5.getSelectedView() == null)
			{
				final MineAlert alert = new MineAlert(context);
				alert.createAlertOneButton(false,"请选择型号!", 
						new View.OnClickListener() {								
							@Override
							public void onClick(View v) {
								alert.dimiss();
							}
						});
				return;
			}
			if(spineer_car_chexing5.getSelectedItem().toString().equals("请选择型号"))
			{
				final MineAlert alert = new MineAlert(context);
				alert.createAlertOneButton(false,"请选择型号!", 
						new View.OnClickListener() {								
							@Override
							public void onClick(View v) {
								alert.dimiss();
							}
						});
				return;
			}
		}
		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "SaveResourceModel");
		soapParameter.addProperty("ResourceID", new AppPreference(context).getTaskId());
		soapParameter.addProperty("VIN", vin);
		soapParameter.addProperty("Category", spineer_car_leixing.getSelectedView().getTag().toString());
		soapParameter.addProperty("ModelType", checkbox_other.isChecked()?"1":"0");
		
		
		if(checkbox_other.isChecked())
		{
			
			if(spineer_other1.getSelectedItem().toString().equals("请选择品牌"))
			{
				Toast.makeText(context, "请选择品牌", Toast.LENGTH_SHORT).show();
				return;
			}
			else
			{
				soapParameter.addProperty("Brand", spineer_other1.getSelectedItem().toString());
			}
		}
		else
		{
			soapParameter.addProperty("Brand", spineer_car_chexing1.getSelectedItem().toString());
		}
		
		
		if(checkbox_other.isChecked())
		{
			soapParameter.addProperty("Manufacturer", "");
		}
		else
		{
			if(spineer_car_chexing2.getSelectedView()==null)
			{
				soapParameter.addProperty("Manufacturer", "");
			}
			else
			{
				soapParameter.addProperty("Manufacturer", spineer_car_chexing2.getSelectedItem().toString());
			}
		}
		
		
		if(checkbox_other.isChecked())
		{
			soapParameter.addProperty("Catalog", "");
		}
		else
		{
			if(spineer_car_chexing3.getSelectedView()==null)
			{
				soapParameter.addProperty("Catalog", "");
			}
			else
			{
				soapParameter.addProperty("Catalog", spineer_car_chexing3.getSelectedItem().toString());
			}
		}
		
		if(checkbox_other.isChecked())
		{
			soapParameter.addProperty("Series", "");
		}
		else
		{
			if(spineer_car_chexing4.getSelectedView()==null)
			{
				soapParameter.addProperty("Series", "");
			}
			else
			{
				soapParameter.addProperty("Series", spineer_car_chexing4.getSelectedItem().toString());
			}
		}
		
		//如果其他框选了  优先其他		
		if(checkbox_other.isChecked())
		{
			soapParameter.addProperty("Model", spineer_other2.getText().toString().trim());
			xinghao = "-999999";
		}
		else
		{
			if(spineer_car_chexing5.getSelectedView()==null)
			{
				soapParameter.addProperty("Model", "");
				xinghao = "-999999";
			}
			else
			{
				soapParameter.addProperty("Model", spineer_car_chexing5.getSelectedItem().toString());
				xinghao = spineer_car_chexing5.getSelectedView().getTag().toString();
			}
		}		
		
		
		if(checkbox_other.isChecked())
		{
			soapParameter.addProperty("BodyForm", spineer_other3.getSelectedView().getTag().toString());
			soapParameter.addProperty("DoorNum", spineer_other4.getSelectedView().getTag().toString());
			soapParameter.addProperty("SeatNum", spineer_other5.getSelectedView().getTag().toString());
			soapParameter.addProperty("Fuel", spineer_other6.getSelectedView().getTag().toString());
			soapParameter.addProperty("OilSupplyForm", spineer_other7.getSelectedView().getTag().toString());
			soapParameter.addProperty("AirIntakeForm", spineer_other8.getSelectedView().getTag().toString());
			soapParameter.addProperty("Drive", spineer_other9.getSelectedView().getTag().toString());
		}
		else
		{
			soapParameter.addProperty("BodyForm", "-999999");
			soapParameter.addProperty("DoorNum", "-999999");
			soapParameter.addProperty("SeatNum", "-999999");
			soapParameter.addProperty("Fuel", "-999999");
			soapParameter.addProperty("OilSupplyForm", "-999999");
			soapParameter.addProperty("AirIntakeForm", "-999999");
			soapParameter.addProperty("Drive", "-999999");
		}
		
		soapParameter.addProperty("Transmission", spineer_car_biansuxiang.getSelectedView().getTag().toString());
		soapParameter.addProperty("Emission", pailiang);
		soapParameter.addProperty("EnginePower", gonglv);
		soapParameter.addProperty("KeyConfiguration", guanjianpeizhi);
		soapParameter.addProperty("OptionConfiguration", qitapeizhi);
		soapParameter.addProperty("ModConfiguration", gaizhuangpeizhi);		
		
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
						ToastUtilMgr.TextToast(context, "数据提交成功", ToastUtilMgr.LENGTH_SHORT);
						
						
						
						if(edit)
						{
							Intent intent = new Intent();
							intent.setClass(context, CarConfigurationDetailActivity.class);
							intent.putExtra("edit", edit);
							intent.putExtra("xinghaoID", xinghao);
							startActivityForResult(intent, 1);
						}
						else
						{
							Intent intent = new Intent();
							intent.setClass(context, CarConfigurationDetailActivity.class);
							intent.putExtra("xinghaoID", xinghao);
							startActivity(intent);
						}
							
					}
					else
					{
						ToastUtilMgr.TextToast(context, getString(R.string.server_error1), ToastUtilMgr.LENGTH_LONG);
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
						SoapAction.SaveResourceModel , 
						"SaveResourceModel",
						soapParameter,
						myhandler,
						true,
						false
				);


	}
	
		
	/**
	 * 从本地数据库中获取数据显示到控件上
	 */
	public void initData(String taskID)
	{
		
		
		Log.e("获取车型登记数据:", taskID);	
		
		
		
		
		NewChexingDengjiDB db = new NewChexingDengjiDB(context);
		Cursor cur = db.selectById(taskID);
		if(cur.moveToFirst() == false)
		{			
			return;
		}
		edt_vin.setText(cur.getString(1).equals("anyType{}")?"":cur.getString(1));
		//车辆类型
		for(int i = 0; i < listmap1.size(); i++)
		{
			int id = 0;
			if(listmap1.get(i).get("key").toString().equals(cur.getString(2)))
			{
				id = i;
				spineer_car_leixing.setSelection(id);
			}
		}
		
		
		Log.e("车辆类型2", cur.getString(2));
		
		//车型
		//其他
		if(cur.getString(8).equals("1"))
		{
			checkbox_other.setChecked(true);
			spineer_other1.setVisibility(View.VISIBLE);
			spineer_other2.setVisibility(View.VISIBLE);
			spineer_other3.setVisibility(View.VISIBLE);
			spineer_other4.setVisibility(View.VISIBLE);
			spineer_other5.setVisibility(View.VISIBLE);
			spineer_other6.setVisibility(View.VISIBLE);
			spineer_other7.setVisibility(View.VISIBLE);
			spineer_other8.setVisibility(View.VISIBLE);
			spineer_other9.setVisibility(View.VISIBLE);
		}
		else
		{
			checkbox_other.setChecked(false);
			spineer_other1.setVisibility(View.GONE);
			spineer_other2.setVisibility(View.GONE);
			spineer_other3.setVisibility(View.GONE);
			spineer_other4.setVisibility(View.GONE);
			spineer_other5.setVisibility(View.GONE);
			spineer_other6.setVisibility(View.GONE);
			spineer_other7.setVisibility(View.GONE);
			spineer_other8.setVisibility(View.GONE);
			spineer_other9.setVisibility(View.GONE);
		}
		
		for(int i = 0; i < listmap7.size(); i++)
		{
			int id = 0;
			if(listmap7.get(i).get("value").toString().equals(cur.getString(9)))
			{
				id = i;
				spineer_other1.setSelection(id);
			}
		}
		
		Log.e("other1_1", cur.getString(9));
		
		spineer_other2.setText(cur.getString(10).equals("anyType{}")?"":cur.getString(10));
		
		for(int i = 0; i < listmap8.size(); i++)
		{
			int id = 0;
			if(listmap8.get(i).get("key").toString().equals(cur.getString(11)))
			{
				id = i;
				spineer_other3.setSelection(id);
			}
		}
		
		Log.e("other3_3", cur.getString(11));
		
		for(int i = 0; i < listmap9.size(); i++)
		{
			int id = 0;
			if(listmap9.get(i).get("key").toString().equals(cur.getString(12)))
			{
				id = i;
				spineer_other4.setSelection(id);
			}
		}
		
		for(int i = 0; i < listmap10.size(); i++)
		{
			int id = 0;
			if(listmap10.get(i).get("key").toString().equals(cur.getString(13)))
			{
				id = i;
				spineer_other5.setSelection(id);
			}
		}
		
		for(int i = 0; i < listmap11.size(); i++)
		{
			int id = 0;
			if(listmap11.get(i).get("key").toString().equals(cur.getString(14)))
			{
				id = i;
				spineer_other6.setSelection(id);
			}
		}
		
		for(int i = 0; i < listmap12.size(); i++)
		{
			int id = 0;
			if(listmap12.get(i).get("key").toString().equals(cur.getString(15)))
			{
				id = i;
				spineer_other7.setSelection(id);
			}
		}
		
		for(int i = 0; i < listmap13.size(); i++)
		{
			int id = 0;
			if(listmap13.get(i).get("key").toString().equals(cur.getString(16)))
			{
				id = i;
				spineer_other8.setSelection(id);
			}
		}
		
		for(int i = 0; i < listmap14.size(); i++)
		{
			int id = 0;
			if(listmap14.get(i).get("key").toString().equals(cur.getString(17)))
			{
				id = i;
				spineer_other9.setSelection(id);
			}
		}
		
		//变速箱类型
		for(int i = 0; i < listmap15.size(); i++)
		{
			int id = 0;
			if(listmap15.get(i).get("key").toString().equals(cur.getString(18)))
			{
				id = i;
				spineer_car_biansuxiang.setSelection(id);
			}
		}
		
		
		edt_pailiang.setText(cur.getString(19).equals("anyType{}")?"":cur.getString(19));
		edt_gonglv.setText(cur.getString(20).equals("anyType{}")?"":cur.getString(20));
		//燃料
		String ranliao = cur.getString(21);
		if(ranliao.equals("1"))
		{
			radio_ranliao1.setChecked(true);
		}
		if(ranliao.equals("2"))
		{
			radio_ranliao2.setChecked(true);
		}
		if(ranliao.equals("3"))
		{
			radio_ranliao3.setChecked(true);
		}
		if(ranliao.equals("4"))
		{
			radio_ranliao4.setChecked(true);
		}
		if(ranliao.equals("5"))
		{
			radio_ranliao5.setChecked(true);
		}
		if(ranliao.equals("6"))
		{
			radio_ranliao6.setChecked(true);
		}
		if(ranliao.equals("7"))
		{
			radio_ranliao7.setChecked(true);
		}
		//钥匙
		String yaoshi = cur.getString(22);
		if(yaoshi.equals("1"))
		{
			radio_yaoshi1.setChecked(true);
		}
		if(yaoshi.equals("2"))
		{
			radio_yaoshi2.setChecked(true);
		}
		if(yaoshi.equals("3"))
		{
			radio_yaoshi3.setChecked(true);
		}
		//ABS
		String abs = cur.getString(23);
		if(abs.equals("1"))
		{
			radio_abs1.setChecked(true);
		}
		if(abs.equals("2"))
		{
			radio_abs2.setChecked(true);
		}
		//助力转向
		String radio_zhuli = cur.getString(24);
		if(radio_zhuli.equals("1"))
		{
			radio_zhuli1.setChecked(true);
		}
		if(radio_zhuli.equals("2"))
		{
			radio_zhuli2.setChecked(true);
		}
		//轮圈
		String radio_lunquan = cur.getString(25);
		if(radio_lunquan.equals("1"))
		{
			radio_lunquan1.setChecked(true);
		}
		if(radio_lunquan.equals("2"))
		{
			radio_lunquan2.setChecked(true);
		}
		if(radio_lunquan.equals("3"))
		{
			radio_lunquan3.setChecked(true);
		}
		//安全气囊/气帘
		String radio_qinang = cur.getString(26);
		if(radio_qinang.equals("1"))
		{
			radio_qinang1.setChecked(true);
		}
		if(radio_qinang.equals("2"))
		{
			radio_qinang2.setChecked(true);
		}
		String check_qinang = cur.getString(27);
		if(check_qinang.indexOf("1")!=-1)
		{
			check_qinang1.setChecked(true);
		}
		if(check_qinang.indexOf("2")!=-1)
		{
			check_qinang2.setChecked(true);
		}
		if(check_qinang.indexOf("3")!=-1)
		{
			check_qinang3.setChecked(true);
		}
		if(check_qinang.indexOf("4")!=-1)
		{
			check_qinang4.setChecked(true);
		}
		if(check_qinang.indexOf("5")!=-1)
		{
			check_qinang5.setChecked(true);
		}
		if(check_qinang.indexOf("6")!=-1)
		{
			check_qinang6.setChecked(true);
		}
		if(check_qinang.equals("7"))
		{
			check_qinang7.setChecked(true);
		}
		//天窗
		String radio_tianchuang = cur.getString(28);
		if(radio_tianchuang.equals("1"))
		{
			radio_tianchuang1.setChecked(true);
		}
		if(radio_tianchuang.equals("2"))
		{
			radio_tianchuang2.setChecked(true);
		}
		if(radio_tianchuang.equals("3"))
		{
			radio_tianchuang3.setChecked(true);
		}
		if(radio_tianchuang.equals("4"))
		{
			radio_tianchuang4.setChecked(true);
		}
		//空调
		String radio_kongtiao = cur.getString(29);
		if(radio_kongtiao.equals("1"))
		{
			radio_kongtiao1.setChecked(true);
		}
		if(radio_kongtiao.equals("2"))
		{
			radio_kongtiao2.setChecked(true);
		}
		String check_kongtiao = cur.getString(30);
		if(check_kongtiao.indexOf("1")!=-1)
		{
			check_kongtiao1.setChecked(true);
		}
		if(check_kongtiao.indexOf("2")!=-1)
		{
			check_kongtiao2.setChecked(true);
		}
		if(check_kongtiao.indexOf("3")!=-1)
		{
			check_kongtiao3.setChecked(true);
		}
		//门窗
		String radio_menchuang = cur.getString(31);
		if(radio_menchuang.equals("1"))
		{
			radio_menchuang1.setChecked(true);
		}
		if(radio_menchuang.equals("2"))
		{
			radio_menchuang2.setChecked(true);
		}
		if(radio_menchuang.equals("3"))
		{
			radio_menchuang3.setChecked(true);
		}
		//后视镜
		String radio_houshijing = cur.getString(32);
		if(radio_houshijing.equals("1"))
		{
			radio_houshijing1.setChecked(true);
		}
		if(radio_houshijing.equals("2"))
		{
			radio_houshijing2.setChecked(true);
		}
		if(radio_houshijing.equals("3"))
		{
			radio_houshijing3.setChecked(true);
		}
		if(radio_houshijing.equals("4"))
		{
			radio_houshijing4.setChecked(true);
		}
		//座椅材质
		String radio_zuoyi_caizhi = cur.getString(33);
		if(radio_zuoyi_caizhi.equals("1"))
		{
			radio_zuoyi_caizhi1.setChecked(true);
		}
		if(radio_zuoyi_caizhi.equals("2"))
		{
			radio_zuoyi_caizhi2.setChecked(true);
		}
		//座椅功能
		String check_zuoyi = cur.getString(34);
		if(check_zuoyi.indexOf("<1>")!=-1)
		{
			check_zuoyi1.setChecked(true);
		}
		if(check_zuoyi.indexOf("<2>")!=-1)
		{
			check_zuoyi2.setChecked(true);
		}
		if(check_zuoyi.indexOf("<3>")!=-1)
		{
			check_zuoyi3.setChecked(true);
		}
		if(check_zuoyi.indexOf("<4>")!=-1)
		{
			check_zuoyi4.setChecked(true);
		}
		if(check_zuoyi.indexOf("<5>")!=-1)
		{
			check_zuoyi5.setChecked(true);
		}
		if(check_zuoyi.indexOf("<6>")!=-1)
		{
			check_zuoyi6.setChecked(true);
		}
		if(check_zuoyi.indexOf("<7>")!=-1)
		{
			check_zuoyi7.setChecked(true);
		}
		if(check_zuoyi.indexOf("<8>")!=-1)
		{
			check_zuoyi8.setChecked(true);
		}
		if(check_zuoyi.indexOf("<9>")!=-1)
		{
			check_zuoyi9.setChecked(true);
		}
		if(check_zuoyi.indexOf("<10>")!=-1)
		{
			check_zuoyi10.setChecked(true);
		}
		//方向盘
		String radio_fangxiangpan = cur.getString(35);
		if(radio_fangxiangpan.equals("1"))
		{
			radio_fangxiangpan1.setChecked(true);
		}
		if(radio_fangxiangpan.equals("2"))
		{
			radio_fangxiangpan2.setChecked(true);
		}
		String check_fangxiangpan = cur.getString(36);
		if(check_fangxiangpan.indexOf("1")!=-1)
		{
			check_fangxiangpan1.setChecked(true);
		}
		if(check_fangxiangpan.indexOf("2")!=-1)
		{
			check_fangxiangpan2.setChecked(true);
		}
		if(check_fangxiangpan.indexOf("3")!=-1)
		{
			check_fangxiangpan3.setChecked(true);
		}
		if(check_fangxiangpan.indexOf("4")!=-1)
		{
			check_fangxiangpan4.setChecked(true);
		}
		if(check_fangxiangpan.indexOf("5")!=-1)
		{
			check_fangxiangpan5.setChecked(true);
		}
		//大灯
		String radio_dadeng = cur.getString(37);
		if(radio_dadeng.equals("1"))
		{
			radio_dadeng1.setChecked(true);
		}
		if(radio_dadeng.equals("2"))
		{
			radio_dadeng2.setChecked(true);
		}
		//CD/DVD
		String radio_dvd = cur.getString(38);
		if(radio_dvd.equals("1"))
		{
			radio_dvd1.setChecked(true);
		}
		if(radio_dvd.equals("2"))
		{
			radio_dvd2.setChecked(true);
		}
		if(radio_dvd.equals("3"))
		{
			radio_dvd3.setChecked(true);
		}
		if(radio_dvd.equals("4"))
		{
			radio_dvd4.setChecked(true);
		}
		if(radio_dvd.equals("5"))
		{
			radio_dvd5.setChecked(true);
		}
		//液晶屏
		String radio_yejingpin = cur.getString(39);
		if(radio_yejingpin.equals("1"))
		{
			radio_yejingpin1.setChecked(true);
		}
		if(radio_yejingpin.equals("2"))
		{
			radio_yejingpin2.setChecked(true);
		}
		String check_yejingpin = cur.getString(40);
		if(check_yejingpin.indexOf("1")!=-1)
		{
			check_yejingpin1.setChecked(true);
		}
		if(check_yejingpin.indexOf("2")!=-1)
		{
			check_yejingpin2.setChecked(true);
		}
		if(check_yejingpin.indexOf("3")!=-1)
		{
			check_yejingpin3.setChecked(true);
		}
		if(check_yejingpin.indexOf("4")!=-1)
		{
			check_yejingpin4.setChecked(true);
		}
		//其他配置
		String radio_other_peizhi = cur.getString(41);
		if(radio_other_peizhi1.equals("1"))
		{
			radio_other_peizhi1.setChecked(true);
		}
		if(radio_other_peizhi.equals("2"))
		{
			radio_other_peizhi2.setChecked(true);
		}
		String check_other_peizhi = cur.getString(42);
		if(check_other_peizhi.indexOf("<1>")!=-1)
		{
			check_other_peizhi1.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<2>")!=-1)
		{
			check_other_peizhi2.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<3>")!=-1)
		{
			check_other_peizhi3.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<4>")!=-1)
		{
			check_other_peizhi4.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<5>")!=-1)
		{
			check_other_peizhi5.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<6>")!=-1)
		{
			check_other_peizhi6.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<7>")!=-1)
		{
			check_other_peizhi7.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<8>")!=-1)
		{
			check_other_peizhi8.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<9>")!=-1)
		{
			check_other_peizhi9.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<10>")!=-1)
		{
			check_other_peizhi10.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<11>")!=-1)
		{
			check_other_peizhi11.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<12>")!=-1)
		{
			check_other_peizhi12.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<13>")!=-1)
		{
			check_other_peizhi13.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<14>")!=-1)
		{
			check_other_peizhi14.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<15>")!=-1)
		{
			check_other_peizhi15.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<16>")!=-1)
		{
			check_other_peizhi16.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<17>")!=-1)
		{
			check_other_peizhi17.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<18>")!=-1)
		{
			check_other_peizhi18.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<19>")!=-1)
		{
			check_other_peizhi19.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<20>")!=-1)
		{
			check_other_peizhi20.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<21>")!=-1)
		{
			check_other_peizhi21.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<22>")!=-1)
		{
			check_other_peizhi22.setChecked(true);
		}
		if(check_other_peizhi.indexOf("<23>")!=-1)
		{
			check_other_peizhi23.setChecked(true);
		}
		//改装配置
		edt_xuanzhuang_peizhi.setText(cur.getString(43).equals("anyType{}")?"":cur.getString(43));
		cur.close();
		db.close();
	}
	
	
	/**
	 * 保存数据到本地数据库
	 * @return  是否成功
	 */
	public boolean saveData()
	{
		//燃料
		guanjianpeizhi = "";
		String radio_ranliao = "";
		if(radio_ranliao1.isChecked())
		{
			radio_ranliao = "1";
			guanjianpeizhi += "燃料类型:汽油";
		}
		if(radio_ranliao2.isChecked())
		{
			radio_ranliao = "2";
			guanjianpeizhi += "燃料类型:柴油";
		}
		if(radio_ranliao3.isChecked())
		{
			radio_ranliao = "3";
			guanjianpeizhi += "燃料类型:液化气";
		}
		if(radio_ranliao4.isChecked())
		{
			radio_ranliao = "4";
			guanjianpeizhi += "燃料类型:天然气";
		}
		if(radio_ranliao5.isChecked())
		{
			radio_ranliao = "5";
			guanjianpeizhi += "燃料类型:纯电动";
		}
		if(radio_ranliao6.isChecked())
		{
			radio_ranliao = "6";
			guanjianpeizhi += "燃料类型:油电混合";
		}
		if(radio_ranliao7.isChecked())
		{
			radio_ranliao = "7";
			guanjianpeizhi += "燃料类型:油气混合";
		}
		//钥匙
		String radio_yaoshi = "";
		if(radio_yaoshi1.isChecked())
		{
			radio_yaoshi = "1";
			guanjianpeizhi += "|钥匙:普通";
		}
		if(radio_yaoshi2.isChecked())
		{
			radio_yaoshi = "2";
			guanjianpeizhi += "|钥匙:遥控";
		}
		if(radio_yaoshi3.isChecked())
		{
			radio_yaoshi = "3";
			guanjianpeizhi += "|钥匙:智能";
		}
		//ABS
		String radio_abs = "";
		if(radio_abs1.isChecked())
		{
			radio_abs = "1";
			guanjianpeizhi += "|ABS:有";
		}
		if(radio_abs2.isChecked())
		{
			radio_abs = "2";
			guanjianpeizhi += "|ABS:无";
		}
		//助力转向
		String radio_zhuli = "";
		if(radio_zhuli1.isChecked())
		{
			radio_zhuli = "1";
			guanjianpeizhi += "|助力转向:有";
		}
		if(radio_zhuli2.isChecked())
		{
			radio_zhuli = "2";
			guanjianpeizhi += "|助力转向:无";
		}
		//轮圈
		String radio_lunquan = "";
		if(radio_lunquan1.isChecked())
		{
			radio_lunquan = "1";
			guanjianpeizhi += "|轮圈:钢";
		}
		if(radio_lunquan2.isChecked())
		{
			radio_lunquan = "2";
			guanjianpeizhi += "|轮圈:合金";
		}
		if(radio_lunquan3.isChecked())
		{
			radio_lunquan = "3";
			guanjianpeizhi += "|轮圈:碳纤维";
		}
		//安全气囊/气帘
		String radio_qinang = "";
		String check_qinang = "";
		String qinang_info = "";
		if(check_qinang1.isChecked())
		{
			check_qinang = check_qinang+"1,";
			qinang_info += "驾驶员气囊,";
		}
		if(check_qinang2.isChecked())
		{
			check_qinang = check_qinang+"2,";
			qinang_info += "副驾驶气囊,";
		}
		if(check_qinang3.isChecked())
		{
			check_qinang = check_qinang+"3,";
			qinang_info += "前排侧气囊,";
		}
		if(check_qinang4.isChecked())
		{
			check_qinang = check_qinang+"4,";
			qinang_info += "后排侧气囊,";
		}
		if(check_qinang5.isChecked())
		{
			check_qinang = check_qinang+"5,";
			qinang_info += "前排头部气囊/气帘,";
		}
		if(check_qinang6.isChecked())
		{
			check_qinang = check_qinang+"6,";
			qinang_info += "后排头部气囊/气帘,";
		}
		if(check_qinang7.isChecked())
		{
			check_qinang = check_qinang+"7,";
			qinang_info += "膝部气囊,";
		}
		if(qinang_info.length() > 0)
		{
			qinang_info = qinang_info.substring(0,qinang_info.length()-1);
		}
		if(radio_qinang1.isChecked())
		{
			radio_qinang = "1";
			
		}
		if(radio_qinang2.isChecked())
		{
			radio_qinang = "2";
			
		}
		if(qinang_info.trim().equals(""))
		{
			guanjianpeizhi += "|安全气囊/气帘:无";
		}
		if(!qinang_info.trim().equals(""))
		{
			guanjianpeizhi += "|安全气囊/气帘:(有)";
			guanjianpeizhi += qinang_info;
		}
		
		if(check_qinang.length() > 0)
		{
			check_qinang = check_qinang.substring(0,check_qinang.length()-1);
		}		
		//天窗
		String radio_tianchuang = "";
		if(radio_tianchuang1.isChecked())
		{
			radio_tianchuang = "1";
			guanjianpeizhi += "|天窗:无";
		}
		if(radio_tianchuang2.isChecked())
		{
			radio_tianchuang = "2";
			guanjianpeizhi += "|天窗:手动";
		}
		if(radio_tianchuang3.isChecked())
		{
			radio_tianchuang = "3";
			guanjianpeizhi += "|天窗:电动";
		}
		if(radio_tianchuang4.isChecked())
		{
			radio_tianchuang = "4";
			guanjianpeizhi += "|天窗:全景";
		}
		//空调
		String radio_kongtiao = "";
		String check_kongtiao = "";
		String kongtiao_info = "";
		if(check_kongtiao1.isChecked())
		{
			check_kongtiao = check_kongtiao + "1,";
			kongtiao_info += "手动控制,";
		}
		if(check_kongtiao2.isChecked())
		{
			check_kongtiao = check_kongtiao + "2,";
			kongtiao_info += "自动控制,";
		}
		if(check_kongtiao3.isChecked())
		{
			check_kongtiao = check_kongtiao + "3,";
			kongtiao_info += "后排独立空调,";
		}
		if(kongtiao_info.length()>0)
		{
			kongtiao_info = kongtiao_info.substring(0,kongtiao_info.length()-1); 
		}		
		if(radio_kongtiao1.isChecked())
		{
			radio_kongtiao = "1";
		}
		if(radio_kongtiao2.isChecked())
		{
			radio_kongtiao = "2";			
		}
		if(kongtiao_info.trim().equals(""))
		{
			guanjianpeizhi += "|空调:无";
		}
		if(!kongtiao_info.trim().equals(""))
		{
			guanjianpeizhi += "|空调:(有)";
			guanjianpeizhi += kongtiao_info;
		}
		if(check_kongtiao.length()>0)
		{
			check_kongtiao = check_kongtiao.substring(0,check_kongtiao.length()-1);
		}		
		//门窗
		String radio_menchuang = "";
		if(radio_menchuang1.isChecked())
		{
			radio_menchuang = "1";
			guanjianpeizhi += "|门窗:手动";
		}
		if(radio_menchuang2.isChecked())
		{
			radio_menchuang = "2";
			guanjianpeizhi += "|门窗:前门电动";
		}
		if(radio_menchuang3.isChecked())
		{
			radio_menchuang = "3";
			guanjianpeizhi += "|门窗:四门电动";
		}
		//后视镜
		String radio_houshijing = "";
		if(radio_houshijing1.isChecked())
		{
			radio_houshijing = "1";
			guanjianpeizhi += "|后视镜:手动调节";
		}
		if(radio_houshijing2.isChecked())
		{
			radio_houshijing = "2";
			guanjianpeizhi += "|后视镜:电动调节";
		}
		if(radio_houshijing3.isChecked())
		{
			radio_houshijing = "3";
			guanjianpeizhi += "|后视镜:电动折叠";
		}
		if(radio_houshijing4.isChecked())
		{
			radio_houshijing = "4";
			guanjianpeizhi += "|后视镜:电动记忆";
		}
		//座椅材质
		String radio_zuoyi_caizhi = "";
		if(radio_zuoyi_caizhi1.isChecked())
		{
			radio_zuoyi_caizhi = "1";
			guanjianpeizhi += "|座椅材质:织物";
		}
		if(radio_zuoyi_caizhi2.isChecked())
		{
			radio_zuoyi_caizhi = "2";
			guanjianpeizhi += "|座椅材质:真皮";
		}
		//座椅功能
		boolean checked_zuoyi = false;
		String zuoyi_peizhi="";
		String check_zuoyi = "";
		if(check_zuoyi1.isChecked())
		{
			check_zuoyi = check_zuoyi + "<1>,";
			zuoyi_peizhi += ",主驾座椅电动调节";
			checked_zuoyi = true;
		}
		if(check_zuoyi2.isChecked())
		{
			check_zuoyi = check_zuoyi + "<2>,";
			zuoyi_peizhi += ",副驾座椅电动调节";
			checked_zuoyi = true;
		}
		if(check_zuoyi3.isChecked())
		{
			check_zuoyi = check_zuoyi + "<3>,";
			zuoyi_peizhi += ",后排座椅电动调节";
			checked_zuoyi = true;
		}
		if(check_zuoyi4.isChecked())
		{
			check_zuoyi = check_zuoyi + "<4>,";
			zuoyi_peizhi += ",电动座椅记忆";
			checked_zuoyi = true;
		}
		if(check_zuoyi5.isChecked())
		{
			check_zuoyi = check_zuoyi + "<5>,";
			zuoyi_peizhi += ",前排座椅加热";
			checked_zuoyi = true;
		}
		if(check_zuoyi6.isChecked())
		{
			check_zuoyi = check_zuoyi + "<6>,";
			zuoyi_peizhi += ",后排座椅加热";
			checked_zuoyi = true;
		}
		if(check_zuoyi7.isChecked())
		{
			check_zuoyi = check_zuoyi + "<7>,";
			zuoyi_peizhi += ",前排座椅通风";
			checked_zuoyi = true;
		}
		if(check_zuoyi8.isChecked())
		{
			check_zuoyi = check_zuoyi + "<8>,";
			zuoyi_peizhi += ",后排座椅通风";
			checked_zuoyi = true;
		}
		if(check_zuoyi9.isChecked())
		{
			check_zuoyi = check_zuoyi + "<9>,";
			zuoyi_peizhi += ",前排座椅按摩";
			checked_zuoyi = true;
		}
		if(check_zuoyi10.isChecked())
		{
			check_zuoyi = check_zuoyi + "<10>,";
			zuoyi_peizhi += ",后排座椅按摩";
			checked_zuoyi = true;
		}
		if(checked_zuoyi)
		{
			zuoyi_peizhi = zuoyi_peizhi.substring(1, zuoyi_peizhi.length());
			guanjianpeizhi += "|座椅功能:"+zuoyi_peizhi;
		}
		if(check_zuoyi.length()>0)
		{
			check_zuoyi = check_zuoyi.substring(0,check_zuoyi.length()-1);
		}		
		//方向盘		
		String radio_fangxiangpan = "";
		String fangxiangpan_info="";
		String check_fangxiangpan = "";
		if(check_fangxiangpan1.isChecked())
		{
			check_fangxiangpan = check_fangxiangpan + "1,";
			fangxiangpan_info += "音响控制,";
		}
		if(check_fangxiangpan2.isChecked())
		{
			check_fangxiangpan = check_fangxiangpan + "2,";
			fangxiangpan_info += "定速巡航,";
		}
		if(check_fangxiangpan3.isChecked())
		{
			check_fangxiangpan = check_fangxiangpan + "3,";
			fangxiangpan_info += "方向盘换挡,";
		}
		if(check_fangxiangpan4.isChecked())
		{
			check_fangxiangpan = check_fangxiangpan + "4,";
			fangxiangpan_info += "方向盘电动调节,";
		}
		if(check_fangxiangpan5.isChecked())
		{
			check_fangxiangpan = check_fangxiangpan + "5,";
			fangxiangpan_info += "方向盘加热,";
		}
		if(fangxiangpan_info.length()>0)
		{
			fangxiangpan_info = fangxiangpan_info.substring(0,fangxiangpan_info.length()-1);
		}		
		if(radio_fangxiangpan1.isChecked())
		{
			radio_fangxiangpan = "1";			
		}
		if(radio_fangxiangpan2.isChecked())
		{
			radio_fangxiangpan = "2";			
		}
		if(fangxiangpan_info.trim().equals(""))
		{
			guanjianpeizhi += "|方向盘:普通";
		}
		if(!fangxiangpan_info.trim().equals(""))
		{
			guanjianpeizhi += "|方向盘:(多功能)";
			guanjianpeizhi += fangxiangpan_info;
		}
		if(check_fangxiangpan.length()>0)
		{
			check_fangxiangpan = check_fangxiangpan.substring(0,check_fangxiangpan.length()-1);
		}		
		//大灯
		String radio_dadeng = "";
		if(radio_dadeng1.isChecked())
		{
			radio_dadeng = "1";
			guanjianpeizhi += "|大灯:卤素";
		}
		if(radio_dadeng2.isChecked())
		{
			radio_dadeng = "2";
			guanjianpeizhi += "|大灯:其他";
		}
		//CD/DVD
		String radio_dvd = "";
		if(radio_dvd1.isChecked())
		{
			radio_dvd = "1";
			guanjianpeizhi += "|CD/DVD:无";
		}
		if(radio_dvd2.isChecked())
		{
			radio_dvd = "2";
			guanjianpeizhi += "|CD/DVD:单碟CD";
		}
		if(radio_dvd3.isChecked())
		{
			radio_dvd = "3";
			guanjianpeizhi += "|CD/DVD:多碟CD";
		}
		if(radio_dvd4.isChecked())
		{
			radio_dvd = "4";
			guanjianpeizhi += "|CD/DVD:单碟DVD";
		}
		if(radio_dvd5.isChecked())
		{
			radio_dvd = "5";
			guanjianpeizhi += "|CD/DVD:多碟DVD";
		}
		//液晶屏
		String radio_yejingpin = "";		
		String check_yejingpin = "";
		String yejingpin_info = "";
		if(check_yejingpin1.isChecked())
		{
			check_yejingpin = check_yejingpin + "1,";
			yejingpin_info += "中控台液晶屏,";
		}
		if(check_yejingpin2.isChecked())
		{
			check_yejingpin = check_yejingpin + "2,";
			yejingpin_info += "后排液晶屏,";
		}
		if(check_yejingpin3.isChecked())
		{
			check_yejingpin = check_yejingpin + "3,";
			yejingpin_info += "导航,";
		}
		if(check_yejingpin4.isChecked())
		{
			check_yejingpin = check_yejingpin + "4,";
			yejingpin_info += "人体交互系统,";
		}
		if(yejingpin_info.length()>0)
		{
			yejingpin_info = yejingpin_info.substring(0,yejingpin_info.length()-1);
		}		
		if(radio_yejingpin1.isChecked())
		{
			radio_yejingpin = "1";
		}
		if(radio_yejingpin2.isChecked())
		{
			radio_yejingpin = "2";
			
		}
		if(yejingpin_info.trim().equals(""))
		{
			guanjianpeizhi += "|液晶屏:无";
		}
		if(!yejingpin_info.trim().equals(""))
		{
			guanjianpeizhi += "|液晶屏:(有)";
			guanjianpeizhi += yejingpin_info;
		}
		if(check_yejingpin.length()>0)
		{
			check_yejingpin = check_yejingpin.substring(0,check_yejingpin.length()-1);
		}		
		//其他配置
		qitapeizhi = "";
		String radio_other_peizhi = "";		
		String check_other_peizhi = "";
		String check_other_peizhi_info = "";
		if(check_other_peizhi1.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<1>,";
			check_other_peizhi_info += "胎压检测器,";
		}
		if(check_other_peizhi2.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<2>,";
			check_other_peizhi_info += "车身稳定控制,";
		}
		if(check_other_peizhi3.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<3>,";
			check_other_peizhi_info += "自动驻车,";
		}
		if(check_other_peizhi4.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<4>,";
			check_other_peizhi_info += "陡坡缓降,";
		}
		if(check_other_peizhi5.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<5>,";
			check_other_peizhi_info += "底盘升降,";
		}
		if(check_other_peizhi6.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<6>,";
			check_other_peizhi_info += "电动吸合门,";
		}
		if(check_other_peizhi7.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<7>,";
			check_other_peizhi_info += "电动侧滑门,";
		}
		if(check_other_peizhi8.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<8>,";
			check_other_peizhi_info += "电动后备箱,";
		}
		if(check_other_peizhi9.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<9>,";
			check_other_peizhi_info += "倒车影像,";
		}
		if(check_other_peizhi10.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<10>,";
			check_other_peizhi_info += "抬头数字显示,";
		}
		if(check_other_peizhi11.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<11>,";
			check_other_peizhi_info += "定位互动服务,";
		}
		if(check_other_peizhi12.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<12>,";
			check_other_peizhi_info += "蓝牙/车载电话,";
		}
		if(check_other_peizhi13.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<13>,";
			check_other_peizhi_info += "车载电视,";
		}
		if(check_other_peizhi14.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<14>,";
			check_other_peizhi_info += "遮阳帘,";
		}
		if(check_other_peizhi15.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<15>,";
			check_other_peizhi_info += "感应雨刷,";
		}
		if(check_other_peizhi16.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<16>,";
			check_other_peizhi_info += "主动泊车,";
		}
		if(check_other_peizhi17.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<17>,";
			check_other_peizhi_info += "并线辅助,";
		}
		if(check_other_peizhi18.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<18>,";
			check_other_peizhi_info += "发动机启停技术,";
		}
		if(check_other_peizhi19.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<19>,";
			check_other_peizhi_info += "车道偏离预警系统,";
		}
		if(check_other_peizhi20.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<20>,";
			check_other_peizhi_info += "主动刹车,";
		}
		if(check_other_peizhi21.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<21>,";
			check_other_peizhi_info += "主动巡航,";
		}
		if(check_other_peizhi22.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<22>,";
			check_other_peizhi_info += "全景摄像头,";
		}
		if(check_other_peizhi23.isChecked())
		{
			check_other_peizhi = check_other_peizhi + "<23>,";
			check_other_peizhi_info += "夜视系统,";
		}		
		if(check_other_peizhi_info.length()>0)
		{
			check_other_peizhi_info = check_other_peizhi_info.substring(0,check_other_peizhi_info.length()-1);
		}		
		if(radio_other_peizhi1.isChecked())
		{
			radio_other_peizhi = "1";			
		}
		if(radio_other_peizhi2.isChecked())
		{
			radio_other_peizhi = "2";			
		}
		if(check_other_peizhi_info.trim().equals(""))
		{
			qitapeizhi += "无";
		}
		if(!check_other_peizhi_info.trim().equals(""))
		{
			qitapeizhi = "";
			qitapeizhi += check_other_peizhi_info;
		}
		if(check_other_peizhi.length()>0)
		{
			check_other_peizhi = check_other_peizhi.substring(0,check_other_peizhi.length()-1);
		}		
		//改装配置
		String xuanzhuang_peizhi;
		xuanzhuang_peizhi = edt_xuanzhuang_peizhi.getText().toString().trim();
		if(xuanzhuang_peizhi.trim().equals(""))
		{
			gaizhuangpeizhi = "无";
		}
		else
		{
			gaizhuangpeizhi = ""+ xuanzhuang_peizhi;
		}
		
		
		Log.e("保存车型登记信息1界面 TaskID: ", new AppPreference(context).getTaskId());
		
		Log.e("车辆类型",spineer_car_leixing.getSelectedView().getTag().toString());
		Log.e("other1",spineer_other1.getSelectedItem().toString());
		Log.e("other3",listmap8.get(spineer_other3.getSelectedItemPosition()).get("key").toString());
		
		NewChexingDengjiDB db = new NewChexingDengjiDB(context);
		long rowid = db.insert(
				new AppPreference(context).getTaskId(), 
				edt_vin.getText().toString().trim(), 
				spineer_car_leixing.getSelectedView().getTag().toString(),
				"1:别克,2:奥迪", 
				"1:通用汽车,2:通用汽车(进口)",
				"CTS", 
				"2012", 
				"3.0手动",
				checkbox_other.isChecked()?"1":"0", 
				spineer_other1.getSelectedItem().toString(),  
				spineer_other2.getText().toString().trim(), 
				listmap8.get(spineer_other3.getSelectedItemPosition()).get("key").toString(),				
				listmap9.get(spineer_other4.getSelectedItemPosition()).get("key").toString(), 
				listmap10.get(spineer_other5.getSelectedItemPosition()).get("key").toString(), 
				listmap11.get(spineer_other6.getSelectedItemPosition()).get("key").toString(), 
				listmap12.get(spineer_other7.getSelectedItemPosition()).get("key").toString(),
				listmap13.get(spineer_other8.getSelectedItemPosition()).get("key").toString(), 
				listmap14.get(spineer_other9.getSelectedItemPosition()).get("key").toString(), 
				listmap15.get(spineer_car_biansuxiang.getSelectedItemPosition()).get("key").toString(), 
				edt_pailiang.getText().toString().trim(), 
				edt_gonglv.getText().toString().trim(), 
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
				xuanzhuang_peizhi);
		db.close();
	
		
		Log.e("保存车型登记信息1界面 rowid: ", rowid+"");
		
		
	
		
		if(rowid > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	private void initUI()
	{
		edt_vin = (EditText)this.findViewById(R.id.edt_vin);
		btn_jiexi1 = (Button)this.findViewById(R.id.btn_jiexi1);
		shuaxin_car_chexing = (Button)this.findViewById(R.id.shuaxin_car_chexing);
		spineer_car_leixing = (Spinner)this.findViewById(R.id.spineer_car_leixing);
		spineer_car_chexing1 = (Spinner)this.findViewById(R.id.spineer_car_chexing1);
		spineer_car_chexing2 = (Spinner)this.findViewById(R.id.spineer_car_chexing2);
		spineer_car_chexing3 = (Spinner)this.findViewById(R.id.spineer_car_chexing3);
		spineer_car_chexing4 = (Spinner)this.findViewById(R.id.spineer_car_chexing4);
		spineer_car_chexing5 = (Spinner)this.findViewById(R.id.spineer_car_chexing5);
		checkbox_other = (CheckBox)this.findViewById(R.id.checkbox_other);
		spineer_other1 = (Spinner)this.findViewById(R.id.spineer_other1);
		spineer_other2 = (EditText)this.findViewById(R.id.spineer_other2);
		spineer_other3 = (Spinner)this.findViewById(R.id.spineer_other3);
		spineer_other4 = (Spinner)this.findViewById(R.id.spineer_other4);
		spineer_other5 = (Spinner)this.findViewById(R.id.spineer_other5);
		spineer_other6 = (Spinner)this.findViewById(R.id.spineer_other6);
		spineer_other7 = (Spinner)this.findViewById(R.id.spineer_other7);
		spineer_other8 = (Spinner)this.findViewById(R.id.spineer_other8);
		spineer_other9 = (Spinner)this.findViewById(R.id.spineer_other9);
		spineer_car_biansuxiang = (Spinner)this.findViewById(R.id.spineer_car_biansuxiang);
		txt_car_biansuxiang_tip = (TextView)this.findViewById(R.id.txt_car_biansuxiang_tip);
		edt_pailiang = (EditText)this.findViewById(R.id.edt_pailiang);
		edt_gonglv = (EditText)this.findViewById(R.id.edt_gonglv);
		radio_ranliao1 = (RadioButton)this.findViewById(R.id.radio_ranliao1);
		radio_ranliao2 = (RadioButton)this.findViewById(R.id.radio_ranliao2);
		radio_ranliao3 = (RadioButton)this.findViewById(R.id.radio_ranliao3);
		radio_ranliao4 = (RadioButton)this.findViewById(R.id.radio_ranliao4);
		radio_ranliao5 = (RadioButton)this.findViewById(R.id.radio_ranliao5);
		radio_ranliao6 = (RadioButton)this.findViewById(R.id.radio_ranliao6);
		radio_ranliao7 = (RadioButton)this.findViewById(R.id.radio_ranliao7);
		radio_yaoshi1 = (RadioButton)this.findViewById(R.id.radio_yaoshi1);
		radio_yaoshi2 = (RadioButton)this.findViewById(R.id.radio_yaoshi2);
		radio_yaoshi3 = (RadioButton)this.findViewById(R.id.radio_yaoshi3);
		radio_abs1 = (RadioButton)this.findViewById(R.id.radio_abs1);
		radio_abs2 = (RadioButton)this.findViewById(R.id.radio_abs2);
		radio_zhuli1 = (RadioButton)this.findViewById(R.id.radio_zhuli1);
		radio_zhuli2 = (RadioButton)this.findViewById(R.id.radio_zhuli2);
		radio_lunquan1 = (RadioButton)this.findViewById(R.id.radio_lunquan1);
		radio_lunquan2 = (RadioButton)this.findViewById(R.id.radio_lunquan2);
		radio_lunquan3 = (RadioButton)this.findViewById(R.id.radio_lunquan3);
		radio_qinang1 = (RadioButton)this.findViewById(R.id.radio_qinang1);
		radio_qinang2 = (RadioButton)this.findViewById(R.id.radio_qinang2);
		check_qinang1 = (CheckBox)this.findViewById(R.id.check_qinang1);
		check_qinang2 = (CheckBox)this.findViewById(R.id.check_qinang2);
		check_qinang3 = (CheckBox)this.findViewById(R.id.check_qinang3);
		check_qinang4 = (CheckBox)this.findViewById(R.id.check_qinang4);
		check_qinang5 = (CheckBox)this.findViewById(R.id.check_qinang5);
		check_qinang6 = (CheckBox)this.findViewById(R.id.check_qinang6);
		check_qinang7 = (CheckBox)this.findViewById(R.id.check_qinang7);
		radio_tianchuang1 = (RadioButton)this.findViewById(R.id.radio_tianchuang1);
		radio_tianchuang2 = (RadioButton)this.findViewById(R.id.radio_tianchuang2);
		radio_tianchuang3 = (RadioButton)this.findViewById(R.id.radio_tianchuang3);
		radio_tianchuang4 = (RadioButton)this.findViewById(R.id.radio_tianchuang4);
		radio_kongtiao1 = (RadioButton)this.findViewById(R.id.radio_kongtiao1);
		radio_kongtiao2 = (RadioButton)this.findViewById(R.id.radio_kongtiao2);
		check_kongtiao1 = (CheckBox)this.findViewById(R.id.check_kongtiao1);
		check_kongtiao2 = (CheckBox)this.findViewById(R.id.check_kongtiao2);
		check_kongtiao3 = (CheckBox)this.findViewById(R.id.check_kongtiao3);
		radio_menchuang1 = (RadioButton)this.findViewById(R.id.radio_menchuang1);
		radio_menchuang2 = (RadioButton)this.findViewById(R.id.radio_menchuang2);
		radio_menchuang3 = (RadioButton)this.findViewById(R.id.radio_menchuang3);
		radio_houshijing1 = (RadioButton)this.findViewById(R.id.radio_houshijing1);
		radio_houshijing2 = (RadioButton)this.findViewById(R.id.radio_houshijing2);
		radio_houshijing3 = (RadioButton)this.findViewById(R.id.radio_houshijing3);
		radio_houshijing4 = (RadioButton)this.findViewById(R.id.radio_houshijing4);
		radio_zuoyi_caizhi1 = (RadioButton)this.findViewById(R.id.radio_zuoyi_caizhi1);
		radio_zuoyi_caizhi2 = (RadioButton)this.findViewById(R.id.radio_zuoyi_caizhi2);
		check_zuoyi1 = (CheckBox)this.findViewById(R.id.check_zuoyi1);
		check_zuoyi2 = (CheckBox)this.findViewById(R.id.check_zuoyi2);
		check_zuoyi3 = (CheckBox)this.findViewById(R.id.check_zuoyi3);
		check_zuoyi4 = (CheckBox)this.findViewById(R.id.check_zuoyi4);
		check_zuoyi5 = (CheckBox)this.findViewById(R.id.check_zuoyi5);
		check_zuoyi6 = (CheckBox)this.findViewById(R.id.check_zuoyi6);
		check_zuoyi7 = (CheckBox)this.findViewById(R.id.check_zuoyi7);
		check_zuoyi8 = (CheckBox)this.findViewById(R.id.check_zuoyi8);
		check_zuoyi9 = (CheckBox)this.findViewById(R.id.check_zuoyi9);
		check_zuoyi10 = (CheckBox)this.findViewById(R.id.check_zuoyi10);
		radio_fangxiangpan1 = (RadioButton)this.findViewById(R.id.radio_fangxiangpan1);
		radio_fangxiangpan2 = (RadioButton)this.findViewById(R.id.radio_fangxiangpan2);
		check_fangxiangpan1 = (CheckBox)this.findViewById(R.id.check_fangxiangpan1);
		check_fangxiangpan2 = (CheckBox)this.findViewById(R.id.check_fangxiangpan2);
		check_fangxiangpan3 = (CheckBox)this.findViewById(R.id.check_fangxiangpan3);
		check_fangxiangpan4 = (CheckBox)this.findViewById(R.id.check_fangxiangpan4);
		check_fangxiangpan5 = (CheckBox)this.findViewById(R.id.check_fangxiangpan5);
		radio_dadeng1 = (RadioButton)this.findViewById(R.id.radio_dadeng1);
		radio_dadeng2 = (RadioButton)this.findViewById(R.id.radio_dadeng2);
		radio_dvd1 = (RadioButton)this.findViewById(R.id.radio_dvd1);
		radio_dvd2 = (RadioButton)this.findViewById(R.id.radio_dvd2);
		radio_dvd3 = (RadioButton)this.findViewById(R.id.radio_dvd3);
		radio_dvd4 = (RadioButton)this.findViewById(R.id.radio_dvd4);
		radio_dvd5 = (RadioButton)this.findViewById(R.id.radio_dvd5);
		radio_yejingpin1 = (RadioButton)this.findViewById(R.id.radio_yejingpin1);
		radio_yejingpin2 = (RadioButton)this.findViewById(R.id.radio_yejingpin2);
		check_yejingpin1 = (CheckBox)this.findViewById(R.id.check_yejingpin1);
		check_yejingpin2 = (CheckBox)this.findViewById(R.id.check_yejingpin2);
		check_yejingpin3 = (CheckBox)this.findViewById(R.id.check_yejingpin3);
		check_yejingpin4 = (CheckBox)this.findViewById(R.id.check_yejingpin4);		
		radio_other_peizhi1 = (RadioButton)this.findViewById(R.id.radio_other_peizhi1);
		radio_other_peizhi2 = (RadioButton)this.findViewById(R.id.radio_other_peizhi2);
		check_other_peizhi1 = (CheckBox)this.findViewById(R.id.check_other_peizhi1);
		check_other_peizhi2 = (CheckBox)this.findViewById(R.id.check_other_peizhi2);
		check_other_peizhi3 = (CheckBox)this.findViewById(R.id.check_other_peizhi3);
		check_other_peizhi4 = (CheckBox)this.findViewById(R.id.check_other_peizhi4);
		check_other_peizhi5 = (CheckBox)this.findViewById(R.id.check_other_peizhi5);
		check_other_peizhi6 = (CheckBox)this.findViewById(R.id.check_other_peizhi6);
		check_other_peizhi7 = (CheckBox)this.findViewById(R.id.check_other_peizhi7);
		check_other_peizhi8 = (CheckBox)this.findViewById(R.id.check_other_peizhi8);
		check_other_peizhi9 = (CheckBox)this.findViewById(R.id.check_other_peizhi9);
		check_other_peizhi10 = (CheckBox)this.findViewById(R.id.check_other_peizhi10);
		check_other_peizhi11 = (CheckBox)this.findViewById(R.id.check_other_peizhi11);
		check_other_peizhi12 = (CheckBox)this.findViewById(R.id.check_other_peizhi12);
		check_other_peizhi13 = (CheckBox)this.findViewById(R.id.check_other_peizhi13);
		check_other_peizhi14 = (CheckBox)this.findViewById(R.id.check_other_peizhi14);
		check_other_peizhi15 = (CheckBox)this.findViewById(R.id.check_other_peizhi15);
		check_other_peizhi16 = (CheckBox)this.findViewById(R.id.check_other_peizhi16);
		check_other_peizhi17 = (CheckBox)this.findViewById(R.id.check_other_peizhi17);
		check_other_peizhi18 = (CheckBox)this.findViewById(R.id.check_other_peizhi18);
		check_other_peizhi19 = (CheckBox)this.findViewById(R.id.check_other_peizhi19);
		check_other_peizhi20 = (CheckBox)this.findViewById(R.id.check_other_peizhi20);
		check_other_peizhi21 = (CheckBox)this.findViewById(R.id.check_other_peizhi21);
		check_other_peizhi22 = (CheckBox)this.findViewById(R.id.check_other_peizhi22);
		check_other_peizhi23 = (CheckBox)this.findViewById(R.id.check_other_peizhi23);
		edt_xuanzhuang_peizhi = (EditText)this.findViewById(R.id.edt_xuanzhuang_peizhi);		
	}
	
	/**
	 * 获取车型登记信息，载入该界面的时候初始化下拉选择框
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
						//车辆类型
						String CategoryOptions = ((SoapObject) Items).getProperty("CategoryOptions").toString();
						//------------------------------------------------------
						//品牌
						String BrandOptions = ((SoapObject) Items).getProperty("BrandOptions").toString();
						//厂商
						String ManufacturerOptions = ((SoapObject) Items).getProperty("ManufacturerOptions").toString();
						//车型
						String CatalogOptions = ((SoapObject) Items).getProperty("CatalogOptions").toString();
						//年款
						String SeriesOptions = ((SoapObject) Items).getProperty("SeriesOptions").toString();
						//具体型号
						String ModelOptions = ((SoapObject) Items).getProperty("ModelOptions").toString();
						//-------------------其他------------------------
						//车身形式
						String BodyFormOptions = ((SoapObject) Items).getProperty("BodyFormOptions").toString();
						//车门数
						String DoorNumOptions = ((SoapObject) Items).getProperty("DoorNumOptions").toString();
						//座位数
						String SeatNumOptions = ((SoapObject) Items).getProperty("SeatNumOptions").toString();
						//燃油种类
						String FuelOptions = ((SoapObject) Items).getProperty("FuelOptions").toString();
						//供油形式
						String OilSupplyFormOptions = ((SoapObject) Items).getProperty("OilSupplyFormOptions").toString();
						//进气形式
						String AirIntakeFormOptions = ((SoapObject) Items).getProperty("AirIntakeFormOptions").toString();
						//驱动方式
						String DriveOptions = ((SoapObject) Items).getProperty("DriveOptions").toString();
						//----------------------------------------------------------
						//变速箱类型
						String TransmissionOptions = ((SoapObject) Items).getProperty("TransmissionOptions").toString();
						
						
						
						
						
						if(!CategoryOptions.equals("anyType{}"))
						{
							String[] mCategoryOptions = CategoryOptions.split(",");
							listmap1 = new ArrayList<HashMap>();						
							for(String str : mCategoryOptions)
							{
								HashMap map = new HashMap();
								map.put("key", str.split(":")[0]);
								map.put("value", str.split(":")[1]);
								listmap1.add(map);
							}	
							CarDengjiAdapter adapter1 = new CarDengjiAdapter(context,listmap1);
							spineer_car_leixing.setAdapter(adapter1);
						}
						
						
						if(!BrandOptions.equals("anyType{}"))
						{
							String[] mBrandOptions = BrandOptions.split(",");
							List<HashMap> listmap2 = new ArrayList<HashMap>();
							
						
							
							HashMap hash1 = new HashMap();
							hash1.put("key", "-1");
							hash1.put("value", "请选择品牌");
							listmap2.add(hash1);
							
							for(String str : mBrandOptions)
							{
								HashMap map = new HashMap();
								map.put("key", str.split(":")[0]);
								map.put("value", str.split(":")[1]);
								listmap2.add(map);
							}		
							CarDengjiAdapter adapter2 = new CarDengjiAdapter(context,listmap2);
							spineer_car_chexing1.setAdapter(adapter2);
							
							
							GetManufacturers2(listmap2.get(spineer_car_chexing1.getSelectedItemPosition()).get("key").toString());
						}	
						
						
						List<HashMap> a2 = new ArrayList<HashMap>();						
						HashMap hash2 = new HashMap();
						hash2.put("key", "-1");
						hash2.put("value", "请选择厂家");
						a2.add(hash2);
						spineer_car_chexing2.setAdapter(new CarDengjiAdapter(context,a2));
						List<HashMap> a3 = new ArrayList<HashMap>();						
						HashMap hash3 = new HashMap();
						hash3.put("key", "-1");
						hash3.put("value", "请选择车型");
						a3.add(hash3);
						spineer_car_chexing3.setAdapter(new CarDengjiAdapter(context,a3));
						List<HashMap> a4 = new ArrayList<HashMap>();						
						HashMap hash4 = new HashMap();
						hash4.put("key", "-1");
						hash4.put("value", "请选择年款");
						a4.add(hash4);						
						spineer_car_chexing4.setAdapter(new CarDengjiAdapter(context,a4));
						List<HashMap> a5 = new ArrayList<HashMap>();						
						HashMap hash5 = new HashMap();
						hash5.put("key", "-1");
						hash5.put("value", "请选择型号");
						a5.add(hash5);		
						spineer_car_chexing5.setAdapter(new CarDengjiAdapter(context,a5));
						
						
//						if(!ManufacturerOptions.equals("anyType{}"))
//						{						
//							String[] mManufacturerOptions = ManufacturerOptions.split(",");
//							List<HashMap> listmap3 = new ArrayList<HashMap>();
//							for(String str : mManufacturerOptions)
//							{
//								HashMap map = new HashMap();
//								map.put("key", str.split(",")[0]);
//								map.put("value", str.split(",")[0]);
//								listmap3.add(map);
//							}						
//							CarDengjiAdapter adapter3 = new CarDengjiAdapter(context,listmap3);
//							spineer_car_chexing2.setAdapter(adapter3);
//						}
//						if(!CatalogOptions.equals("anyType{}"))
//						{
//							String[] mCatalogOptions = CatalogOptions.split(",");
//							List<HashMap> listmap4 = new ArrayList<HashMap>();
//							for(String str : mCatalogOptions)
//							{
//								HashMap map = new HashMap();
//								map.put("key", str.split(",")[0]);
//								map.put("value", str.split(",")[0]);
//								listmap4.add(map);
//							}						
//							CarDengjiAdapter adapter4 = new CarDengjiAdapter(context,listmap4);
//							spineer_car_chexing3.setAdapter(adapter4);
//						}
//						if(!SeriesOptions.equals("anyType{}"))
//						{
//							String[] mSeriesOptions = SeriesOptions.split(",");
//							List<HashMap> listmap5 = new ArrayList<HashMap>();
//							for(String str : mSeriesOptions)
//							{
//								HashMap map = new HashMap();
//								map.put("key", str.split(",")[0]);
//								map.put("value", str.split(",")[0]);
//								listmap5.add(map);
//							}						
//							CarDengjiAdapter adapter5 = new CarDengjiAdapter(context,listmap5);
//							spineer_car_chexing4.setAdapter(adapter5);
//						}
//						if(!ModelOptions.equals("anyType{}"))
//						{
//							String[] mModelOptions = ModelOptions.split(",");
//							List<HashMap> listmap6 = new ArrayList<HashMap>();
//							for(String str : mModelOptions)
//							{
//								HashMap map = new HashMap();
//								map.put("key", str.split(",")[0]);
//								map.put("value", str.split(",")[0]);
//								listmap6.add(map);
//							}						
//							CarDengjiAdapter adapter6 = new CarDengjiAdapter(context,listmap6);
//							spineer_car_chexing5.setAdapter(adapter6);
//						}
						//-----------其他----------------
						if(!BrandOptions.equals("anyType{}"))
						{
							String[] mBrandOptions = BrandOptions.split(",");
							listmap7 = new ArrayList<HashMap>();
							
							HashMap hash1 = new HashMap();
							hash1.put("key", "-1");
							hash1.put("value", "请选择品牌");
							listmap7.add(hash1);
							
							for(String str : mBrandOptions)
							{
								HashMap map = new HashMap();
								map.put("key", str.split(":")[0]);
								map.put("value", str.split(":")[1]);
								listmap7.add(map);
							}		
							
							CarDengjiAdapter adapter7 = new CarDengjiAdapter(context,listmap7);
							spineer_other1.setAdapter(adapter7);
						}
						if(!BodyFormOptions.equals("anyType{}"))
						{
							String[] mBodyFormOptions = BodyFormOptions.split(",");
							listmap8 = new ArrayList<HashMap>();
							for(String str : mBodyFormOptions)
							{
								HashMap map = new HashMap();
								map.put("key", str.split(":")[0]);
								map.put("value", str.split(":")[1]);
								listmap8.add(map);
							}						
							CarDengjiAdapter adapter8 = new CarDengjiAdapter(context,listmap8);
							spineer_other3.setAdapter(adapter8);
						}
						if(!DoorNumOptions.equals("anyType{}"))
						{
							String[] mDoorNumOptions = DoorNumOptions.split(",");
							listmap9 = new ArrayList<HashMap>();
							for(String str : mDoorNumOptions)
							{
								HashMap map = new HashMap();
								map.put("key", str.split(":")[0]);
								map.put("value", str.split(":")[1]);
								listmap9.add(map);
							}						
							CarDengjiAdapter adapter9 = new CarDengjiAdapter(context,listmap9);
							spineer_other4.setAdapter(adapter9);
						}
						if(!SeatNumOptions.equals("anyType{}"))
						{
							String[] mSeatNumOptions = SeatNumOptions.split(",");
							listmap10 = new ArrayList<HashMap>();
							for(String str : mSeatNumOptions)
							{
								HashMap map = new HashMap();
								map.put("key", str.split(":")[0]);
								map.put("value", str.split(":")[1]);
								listmap10.add(map);
							}						
							CarDengjiAdapter adapter10 = new CarDengjiAdapter(context,listmap10);
							spineer_other5.setAdapter(adapter10);
						}
						if(!FuelOptions.equals("anyType{}"))
						{
							String[] mFuelOptions = FuelOptions.split(",");
							listmap11 = new ArrayList<HashMap>();
							for(String str : mFuelOptions)
							{
								HashMap map = new HashMap();
								map.put("key", str.split(":")[0]);
								map.put("value", str.split(":")[1]);
								listmap11.add(map);
							}						
							CarDengjiAdapter adapter11 = new CarDengjiAdapter(context,listmap11);
							spineer_other6.setAdapter(adapter11);
						}
						if(!OilSupplyFormOptions.equals("anyType{}"))
						{
							String[] mOilSupplyFormOptions = OilSupplyFormOptions.split(",");
							listmap12 = new ArrayList<HashMap>();
							for(String str : mOilSupplyFormOptions)
							{
								HashMap map = new HashMap();
								map.put("key", str.split(":")[0]);
								map.put("value", str.split(":")[1]);
								listmap12.add(map);
							}						
							CarDengjiAdapter adapter12 = new CarDengjiAdapter(context,listmap12);
							spineer_other7.setAdapter(adapter12);
						}
						if(!AirIntakeFormOptions.equals("anyType{}"))
						{
							String[] mAirIntakeFormOptions = AirIntakeFormOptions.split(",");
							listmap13 = new ArrayList<HashMap>();
							for(String str : mAirIntakeFormOptions)
							{
								HashMap map = new HashMap();
								map.put("key", str.split(":")[0]);
								map.put("value", str.split(":")[1]);
								listmap13.add(map);
							}						
							CarDengjiAdapter adapter13 = new CarDengjiAdapter(context,listmap13);
							spineer_other8.setAdapter(adapter13);
						}
						if(!DriveOptions.equals("anyType{}"))
						{
							String[] mDriveOptions = DriveOptions.split(",");
							listmap14 = new ArrayList<HashMap>();
							for(String str : mDriveOptions)
							{
								HashMap map = new HashMap();
								map.put("key", str.split(":")[0]);
								map.put("value", str.split(":")[1]);
								listmap14.add(map);
							}						
							CarDengjiAdapter adapter14 = new CarDengjiAdapter(context,listmap14);
							spineer_other9.setAdapter(adapter14);
						}
						//--------------------------------------
						if(!TransmissionOptions.equals("anyType{}"))
						{
							String[] mTransmissionOptions = TransmissionOptions.split(",");
							listmap15 = new ArrayList<HashMap>();
							for(String str : mTransmissionOptions)
							{
								HashMap map = new HashMap();
								map.put("key", str.split(":")[0]);
								map.put("value", str.split(":")[1]);
								listmap15.add(map);
							}						
							CarDengjiAdapter adapter15 = new CarDengjiAdapter(context,listmap15);
							spineer_car_biansuxiang.setAdapter(adapter15);
						}
						
						
						
						
						
						
						if(!HistoryResourceID.equals("0"))
						{
							NewChexingDengjiDB db = new NewChexingDengjiDB(context);
							Cursor cur = db.selectById(new AppPreference(context).getTaskId());
							if(cur.moveToFirst() == false)
							{			
								Log.e("cardengji ", new AppPreference(context).getTaskId()+"不存在该任务");
								//当前任务数据为空，则说明是第一次进入该任务				
								initData(HistoryResourceID);
							}
							else
							{
								initData(new AppPreference(context).getTaskId());
							}
							cur.close();
							db.close();
						}
						else
						{			
							Log.e("cardengji ", "获取任务为 "+new AppPreference(context).getTaskId()+"的数据");
							initData(new AppPreference(context).getTaskId());
						}
						
					} else {
						ToastUtilMgr.TextToast(context,
								getString(R.string.server_error1),
								ToastUtilMgr.LENGTH_LONG);
					}
				}
			}

			@Override
			public void failed(Message msg) {				
				ToastUtilMgr.TextToast(context,"获取车型信息失败",ToastUtilMgr.LENGTH_SHORT);
			}
		};
		car_model_mgr = new SoapMgr(context, null, null,
				SoapAction.GetResourceModel, "GetResourceModel", soapParameter,
				car_modelmyhandler, true, false);
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {		
		 if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 &&event.getAction() == KeyEvent.ACTION_DOWN) {	        	
			 	saveData();
	        }
		 return super.dispatchKeyEvent(event); 
	}
	
	/**
	 * VIN码解析
	 */
	private void VINParse()
	{
	
//		spineer_car_chexing1.setAdapter(new CarDengjiAdapter(context,new ArrayList<HashMap>()) );
//		spineer_car_chexing2.setAdapter(new CarDengjiAdapter(context,new ArrayList<HashMap>()) );
//		spineer_car_chexing3.setAdapter(new CarDengjiAdapter(context,new ArrayList<HashMap>()) );
//		spineer_car_chexing4.setAdapter(new CarDengjiAdapter(context,new ArrayList<HashMap>()) );
//		spineer_car_chexing5.setAdapter(new CarDengjiAdapter(context,new ArrayList<HashMap>()) );
//		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace,"VINParse");
		soapParameter.addProperty("VIN", edt_vin.getText().toString().trim());
		myhandler = new MyHandler() {
			@Override
			public void success(Message msg) {
				backSoapObject = mgr.getServiceBackSoapObject();
				if (backSoapObject != null) {
					String Retcode = ((SoapObject) backSoapObject
							.getProperty(0)).getProperty("Retcode").toString();
					// 0成功，1失败
					if (Retcode.equals("0")) {
						String Brand = ((SoapObject) backSoapObject.getProperty(0)).getProperty("Brand").toString();
						String BrandOptions = ((SoapObject) backSoapObject.getProperty(0)).getProperty("BrandOptions").toString();
						String Manufacturer = ((SoapObject) backSoapObject.getProperty(0)).getProperty("Manufacturer").toString();
						String ManufacturerOptions = ((SoapObject) backSoapObject.getProperty(0)).getProperty("ManufacturerOptions").toString();
						String Catalog = ((SoapObject) backSoapObject.getProperty(0)).getProperty("Catalog").toString();
						String CatalogOptions = ((SoapObject) backSoapObject.getProperty(0)).getProperty("CatalogOptions").toString();
						String Series = ((SoapObject) backSoapObject.getProperty(0)).getProperty("Series").toString();
						String SeriesOptions = ((SoapObject) backSoapObject.getProperty(0)).getProperty("SeriesOptions").toString();
						String Model = ((SoapObject) backSoapObject.getProperty(0)).getProperty("Model").toString();
						String ModelOptions = ((SoapObject) backSoapObject.getProperty(0)).getProperty("ModelOptions").toString();

						String Transmission = ((SoapObject) backSoapObject.getProperty(0)).getProperty("Transmission").toString();
						String Emission = ((SoapObject) backSoapObject.getProperty(0)).getProperty("Emission").toString();

						//spineer_car_biansuxiang;
						
						if(!Transmission.equals("anyType{}"))
						{
							txt_car_biansuxiang_tip.setText("VIN解析参考:"+Transmission);
						}
						else
						{
							txt_car_biansuxiang_tip.setText("");
						}
						
						
						//排量
						edt_pailiang.setText(Emission.equals("anyType{}")?"":Emission);
						
						
						if(BrandOptions.equals("anyType{}") || ManufacturerOptions.equals("anyType{}") || CatalogOptions.equals("anyType{}") || SeriesOptions.equals("anyType{}") || ModelOptions.equals("anyType{}"))
						{
							final MineAlert alert = new MineAlert(context);
							alert.createAlertOneButton(false,"解析不完整，请勾选其他按钮，手动输入，选择车型！", 
									new View.OnClickListener() {								
										@Override
										public void onClick(View v) {
											isjiexi = false;
											alert.dimiss();
										}
									});
							return;
						}
						
						
						
						
						
						
						
						
						
						//厂商
						String[] mBrandOptions = BrandOptions.split(",");
						List<HashMap> listmap1 = new ArrayList<HashMap>();
						if(!BrandOptions.equals("anyType{}"))
						{
							int position = 0;
							boolean exits = false;
						
							for(String str: mBrandOptions)
							{
								HashMap map = new HashMap();
								map.put("key", str.split(":")[0]);
								map.put("value", str.split(":")[1]);
								listmap1.add(map);
								
								if(str.split(":")[1].equals(Brand))
								{
									exits = true;
								}
								if(!exits)
								{
									position++;
								}
								
							}
							CarDengjiAdapter adapter1 = new CarDengjiAdapter(context,listmap1);						
							spineer_car_chexing1.setAdapter(adapter1);
							
					
							if(!Brand.equals("anyType{}") && BrandOptions.indexOf(Brand)!=-1)
							{
								
								spineer_car_chexing1.setSelection(position);
							}
							
						}
//						if(BrandOptions.equals("anyType{}"))
//						{
//							final MineAlert alert = new MineAlert(context);
//							alert.createAlertOneButton(false,"解析不完整，请勾选其他按钮，手动输入，选择车型！", 
//									new View.OnClickListener() {								
//										@Override
//										public void onClick(View v) {
//											alert.dimiss();
//										}
//									});
//							return;
//						}
						
						
						Log.e("isjiexi1 ", isjiexi+"");
						String[] mManufacturerOptions = ManufacturerOptions.split(",");
						List<HashMap> listmap2 = new ArrayList<HashMap>();
						if(!ManufacturerOptions.equals("anyType{}"))
						{
							int position = 0;
							boolean exits = false;
							for(String str: mManufacturerOptions)
							{
								HashMap map = new HashMap();
								map.put("key", str.split(":")[0]);
								map.put("value", str.split(":")[1]);
								listmap2.add(map);
								
								if(str.split(":")[1].equals(Manufacturer))
								{
									exits = true;
								}
								if(!exits)
								{
									position++;
								}
								
							}
							CarDengjiAdapter adapter2 = new CarDengjiAdapter(context,listmap2);						
							spineer_car_chexing2.setAdapter(adapter2);		
							if(!Manufacturer.equals("anyType{}") && ManufacturerOptions.indexOf(Manufacturer)!=-1)
							{
								spineer_car_chexing2.setSelection(position);
							}
						}
//						if(ManufacturerOptions.equals("anyType{}"))
//						{
//							final MineAlert alert = new MineAlert(context);
//							alert.createAlertOneButton(false,"解析不完整，请勾选其他按钮，手动输入，选择车型！", 
//									new View.OnClickListener() {								
//										@Override
//										public void onClick(View v) {
//											alert.dimiss();
//										}
//									});
//							return;
//						}
						
						Log.e("isjiexi2 ", isjiexi+"");
						String[] mCatalogOptions = CatalogOptions.split(",");
						List<HashMap> listmap3 = new ArrayList<HashMap>();
						if(!CatalogOptions.equals("anyType{}"))
						{
							int position = 0;
							boolean exits = false;
							for(String str: mCatalogOptions)
							{
								HashMap map = new HashMap();
								map.put("key", str.split(":")[0]);
								map.put("value", str.split(":")[1]);
								listmap3.add(map);
								
								if(str.split(":")[1].equals(Catalog))
								{
									exits = true;
								}
								if(!exits)
								{
									position++;
								}
								
							}
							CarDengjiAdapter adapter3 = new CarDengjiAdapter(context,listmap3);						
							spineer_car_chexing3.setAdapter(adapter3);	
							if(!Catalog.equals("anyType{}")  && CatalogOptions.indexOf(Catalog)!=-1)
							{
								spineer_car_chexing3.setSelection(position);
							}
						}
//						if(CatalogOptions.equals("anyType{}"))
//						{
//							final MineAlert alert = new MineAlert(context);
//							alert.createAlertOneButton(false,"解析不完整，请勾选其他按钮，手动输入，选择车型！", 
//									new View.OnClickListener() {								
//										@Override
//										public void onClick(View v) {
//											alert.dimiss();
//										}
//									});
//							return;
//						}
						
						Log.e("isjiexi3 ", isjiexi+"");
						String[] mSeriesOptions = SeriesOptions.split(",");
						List<HashMap> listmap4 = new ArrayList<HashMap>();
						if(!SeriesOptions.equals("anyType{}"))
						{
							int position = 0;
							boolean exits = false;
							for(String str: mSeriesOptions)
							{
								HashMap map = new HashMap();
								map.put("key", str.split(":")[0]);
								map.put("value", str.split(":")[1]);
								listmap4.add(map);
								
								if(str.split(":")[1].equals(Series))
								{
									exits = true;
								}
								if(!exits)
								{
									position++;
								}
								
							}
							CarDengjiAdapter adapter4 = new CarDengjiAdapter(context,listmap4);						
							spineer_car_chexing4.setAdapter(adapter4);		
							if(!Series.equals("anyType{}")  && SeriesOptions.indexOf(Series)!=-1)
							{
								spineer_car_chexing4.setSelection(position);
							}
						}
					
//						if(SeriesOptions.equals("anyType{}"))
//						{
//							final MineAlert alert = new MineAlert(context);
//							alert.createAlertOneButton(false,"解析不完整，请勾选其他按钮，手动输入，选择车型！", 
//									new View.OnClickListener() {								
//										@Override
//										public void onClick(View v) {
//											alert.dimiss();
//										}
//									});
//							return;
//						}
						
						Log.e("isjiexi4 ", isjiexi+"");
						String[] mModelOptions = ModelOptions.split(",");
						List<HashMap> listmap5 = new ArrayList<HashMap>();
						if(!ModelOptions.equals("anyType{}"))
						{	
							Log.e("isjiexi5 ", isjiexi+"");
							int position = 0;
							boolean exits = false;
							for(String str: mModelOptions)
							{
								HashMap map = new HashMap();
								map.put("key", str.split(":")[0]);
								map.put("value", str.split(":")[1]);
								listmap5.add(map);
								
								if(str.split(":")[1].equals(Model))
								{
									exits = true;
								}
								if(!exits)
								{
									position++;
								}
								
							}		
							Log.e("isjiexi6 ", isjiexi+"");
							CarDengjiAdapter adapter5 = new CarDengjiAdapter(context,listmap5);						
							spineer_car_chexing5.setAdapter(adapter5);
							if(!Model.equals("anyType{}")  && ModelOptions.indexOf(Model)!=-1)
							{
								spineer_car_chexing5.setSelection(position);
							}	
							Log.e("isjiexi7 ", isjiexi+"");
						}
//						if(ModelOptions.equals("anyType{}"))
//						{
//							Log.e("isjiexi8 ", isjiexi+"");
//							final MineAlert alert = new MineAlert(context);
//							alert.createAlertOneButton(false,"解析不完整，请勾选其他按钮，手动输入，选择车型！", 
//									new View.OnClickListener() {								
//										@Override
//										public void onClick(View v) {
//											alert.dimiss();
//										}
//									});
//							Log.e("isjiexi9 ", isjiexi+"");
//							return;
//						}
						
					} else {
					
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"解析失败，请勾选其他按钮，手动输入，选择车型！", 
								new View.OnClickListener() {								
									@Override
									public void onClick(View v) {
										isjiexi = false;
										alert.dimiss();
									}
								});
					}
				}
				
				
			}

			@Override
			public void failed(Message msg) {
			
				isjiexi = false;
			}
		};
		mgr = new SoapMgr(context, null, null, SoapAction.VINParse,
				"VINParse", soapParameter, myhandler, true, false);
	}
	
	/**
	 * 获取厂商列表
	 */
	private void GetManufacturers2(final String BrandID)
	{
		Log.e("GetManufacturers2", "GetManufacturers2");
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetManufacturers2");
		soapParameter.addProperty("BrandID", BrandID);
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
						String info = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Info").toString();
						if(!info.equals("anyType{}"))
						{						
								String[] infos = info.split(",");
								List<HashMap> map = new ArrayList<HashMap>();
								
								HashMap hash1 = new HashMap();
								hash1.put("key", "-1");
								hash1.put("value", "请选择厂家");
								map.add(hash1);
								
								for(String str : infos)
								{
									HashMap hash = new HashMap();
									hash.put("key", str.split(":")[0]);
									hash.put("value", str.split(":")[1]);
									map.add(hash);
								}
								CarDengjiAdapter adapter = new CarDengjiAdapter(context,map);
								spineer_car_chexing2.setAdapter(adapter);
								GetCatalogs2(BrandID,map.get(spineer_car_chexing2.getSelectedItemPosition()).get("key").toString());
						}
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
						SoapAction.GetManufacturers2 , 
						"GetManufacturers2",
						soapParameter,
						myhandler,
						true,
						false
				);
	}
	
	/**
	 * 获取车型列表2
	 */
	private void GetCatalogs2(String BrandID,String ManufacturerID)
	{
		Log.e("GetCatalogs2", "GetCatalogs2");
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetCatalogs2");
		soapParameter.addProperty("BrandID", BrandID);
		soapParameter.addProperty("ManufacturerID", ManufacturerID);
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
						String info = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Info").toString();
						if(!info.equals("anyType{}"))
						{						
								String[] infos = info.split(",");
								List<HashMap> map = new ArrayList<HashMap>();
								
								HashMap hash1 = new HashMap();
								hash1.put("key", "-1");
								hash1.put("value", "请选择车型");
								map.add(hash1);
								
								for(String str : infos)
								{
									HashMap hash = new HashMap();
									hash.put("key", str.split(":")[0]);
									hash.put("value", str.split(":")[1]);
									map.add(hash);
								}						
								CarDengjiAdapter adapter = new CarDengjiAdapter(context,map);
								spineer_car_chexing3.setAdapter(adapter);
								
								GetSeriesList2(map.get(spineer_car_chexing3.getSelectedItemPosition()).get("key").toString());
						}
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
						SoapAction.GetCatalogs2 , 
						"GetCatalogs2",
						soapParameter,
						myhandler,
						true,
						false
				);
	}
	
	/**
	 * 年款列表2
	 */
	private void GetSeriesList2(final String CatalogID)
	{
		Log.e("GetSeriesList2", "GetSeriesList2");
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetSeriesList2");
		soapParameter.addProperty("CatalogID", CatalogID);
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
						String info = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Info").toString();
						if(!info.equals("anyType{}"))
						{						
								String[] infos = info.split(",");
								List<HashMap> map = new ArrayList<HashMap>();
								
								HashMap hash1 = new HashMap();
								hash1.put("key", "-1");
								hash1.put("value", "请选择年款");
								map.add(hash1);
								
								for(String str : infos)
								{
									HashMap hash = new HashMap();
									hash.put("key", str.split(":")[0]);
									hash.put("value", str.split(":")[1]);
									map.add(hash);
								}						
								CarDengjiAdapter adapter = new CarDengjiAdapter(context,map);
								spineer_car_chexing4.setAdapter(adapter);
								
								
								GetModels2(CatalogID,map.get(spineer_car_chexing4.getSelectedItemPosition()).get("key").toString());
						}
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
						SoapAction.GetSeriesList2 , 
						"GetSeriesList2",
						soapParameter,
						myhandler,
						true,
						false
				);
	}
	
	/**
	 * 获取型号列表2
	 */
	private void GetModels2(String CatalogID,String SeriesID)
	{
		Log.e("GetModels2", "GetModels2");
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetModels2");
		soapParameter.addProperty("CatalogID", CatalogID);
		soapParameter.addProperty("SeriesID", SeriesID);
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
						String info = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Info").toString();
						if(!info.equals("anyType{}"))
						{						
								String[] infos = info.split(",");
								List<HashMap> map = new ArrayList<HashMap>();
								
								HashMap hash1 = new HashMap();
								hash1.put("key", "-1");
								hash1.put("value", "请选择型号");
								map.add(hash1);
								
								
								for(String str : infos)
								{
									HashMap hash = new HashMap();
									hash.put("key", str.split(":")[0]);
									hash.put("value", str.split(":")[1]);
									map.add(hash);
								}						
								CarDengjiAdapter adapter = new CarDengjiAdapter(context,map);
								spineer_car_chexing5.setAdapter(adapter);
						}
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
						SoapAction.GetModels2 , 
						"GetModels2",
						soapParameter,
						myhandler,
						true,
						false
				);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		
		if(requestCode == 1)
		{
			if(edit)
			{
				finish();
			}
			
		}
		
		
	}
	
	
	
	
	
	
	
}
