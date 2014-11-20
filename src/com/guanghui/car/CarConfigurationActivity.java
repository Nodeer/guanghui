package com.guanghui.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.guanghui.car.adapter.CarConfigurationAdapter;
import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;
import com.guanghui.car.staticdata.CarConfigData;

public class CarConfigurationActivity extends Activity {

	private Context context = this;
	private Button returnBtn;	
	private ListView car_choice;
	private ListView car_key;
	private Button saveButton;
	private Button commitButton;
	private String Tag;
	//型号id
	private String xinghaoID;
	//关键配置
	private String KeyCofiguration;
	//选装配置
	private String OptionCofiguration;
	//改装配置
	private String ModCofiguration;
	private TextView car_configuration_keycofiguration_txt;
	
	private CarConfigurationAdapter adapter;
	private CarConfigurationAdapter adapter2;
	
	
	//选装配置信息
	private String oc="";
	//改装配置信息
	private String mc="";
	
	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_configuration);
		new BuildTab(context,1) ;
		returnBtn = (Button) findViewById(R.id.car_configuration_return);
		car_choice = (ListView) findViewById(R.id.check_task_preview_car_modelinfo);
		car_key = (ListView) findViewById(R.id.check_task_preview_car_test);
		car_configuration_keycofiguration_txt = (TextView) findViewById(R.id.car_configuration_keycofiguration_txt);
		
		
		Tag = this.getIntent().getStringExtra("Tag");
		
		
		xinghaoID = Tag.split("<and>")[0];
		KeyCofiguration = Tag.split("<and>")[1];
		OptionCofiguration = Tag.split("<and>")[2];
		ModCofiguration = Tag.split("<and>")[3];		
		
		//初始化关键配置
		String txtKeyCofigurations="";		
		String[] KeyCofigurations = KeyCofiguration.split(",");
		if(!KeyCofiguration.trim().equals("anyType{}") && !KeyCofiguration.trim().equals("") && !KeyCofiguration.trim().equals("-"))
		{
			for(String KeyCofiguration : KeyCofigurations)
			{
					String id = KeyCofiguration.substring(0, KeyCofiguration.indexOf(":"));
					String value = KeyCofiguration.substring(KeyCofiguration.indexOf(":")+1,KeyCofiguration.length());
					if(value.equals("1"))
					{
						txtKeyCofigurations = txtKeyCofigurations +" | "+new CarConfigData().getConfigNameById(id);
					}
			}		
			car_configuration_keycofiguration_txt.setText(txtKeyCofigurations);
		}
		
		new AppPreference(context).setKeyCofiguration(txtKeyCofigurations);
		
		//初始化选装配置
		String[] OptionCofigurations = OptionCofiguration.split(",");
		List<HashMap> OptionData = new ArrayList<HashMap>();
		if(!OptionCofiguration.trim().equals("anyType{}") && !OptionCofiguration.trim().equals("")&& !OptionCofiguration.trim().equals("-"))
		{
			for(String OptionCofiguration : OptionCofigurations)
			{
				
				String id = OptionCofiguration.substring(0, OptionCofiguration.indexOf(":"));
				String value = OptionCofiguration.substring(OptionCofiguration.indexOf(":")+1,OptionCofiguration.length());
				
				HashMap map = new HashMap();
				map.put("id", id);
				map.put("value", value);
				OptionData.add(map);
				
			}
		}
		adapter = new CarConfigurationAdapter(context,OptionData,1);
		car_choice.setAdapter(adapter);
		
		
		
		
		//初始化改装配置
		String[] ModCofigurations = ModCofiguration.split(",");
		List<HashMap> ModData = new ArrayList<HashMap>();
		if(!ModCofiguration.trim().equals("anyType{}") && !ModCofiguration.trim().equals("") && !ModCofiguration.trim().equals("-"))
		{
			for(String ModCofiguration : ModCofigurations)
			{
				String id = ModCofiguration.substring(0, ModCofiguration.indexOf(":"));
				String value = ModCofiguration.substring(ModCofiguration.indexOf(":")+1,ModCofiguration.length());
						
				HashMap map = new HashMap();
				map.put("id", id);
				map.put("value", value);
				ModData.add(map);
			}
					
			adapter2 = new CarConfigurationAdapter(context,ModData,2);
			car_key.setAdapter(adapter2);
		}
		
				
		initTitle();
		initBottom();
	}

	
	private void initTitle()
	{
		((TextView) findViewById(R.id.title_renwu_btn1)).setBackgroundDrawable(context.getResources().getDrawable(R.drawable.title_bg));
		((TextView) findViewById(R.id.title_renwu_btn2)).setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg));
		((TextView) findViewById(R.id.title_renwu_btn3)).setBackgroundDrawable(context.getResources().getDrawable(R.drawable.title_bg));
	}
		
	private void initBottom()
	{
		saveButton = (Button) findViewById(R.id.stop_save);
		commitButton = (Button) findViewById(R.id.comfirm_communit);
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SaveResourceModel(1);
			}
		});
		commitButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SaveResourceModel(2);				
			}
		});
		returnBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	/**
	 * 保存车辆登记信息
	 * @param type   1暂存   2发送提交
	 */
	private void SaveResourceModel(final int type)
	{
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "SaveResourceModel");
		soapParameter.addProperty("ResourceID", new AppPreference(context).getTaskId());
		soapParameter.addProperty("ModelID", xinghaoID);
		
		
		if(!OptionCofiguration.equals("") && !OptionCofiguration.equals("anyType{}") && !OptionCofiguration.equals("-"))
		{
			oc = ((CarConfigurationAdapter)car_choice.getAdapter()).getOc();
			oc = oc.substring(oc.indexOf(",")+1, oc.length());
		}
		if(!ModCofiguration.equals("") && !ModCofiguration.equals("anyType{}") && !ModCofiguration.equals("-"))
		{			
			mc = ((CarConfigurationAdapter)car_key.getAdapter()).getOc();
			mc = mc.substring(mc.indexOf(",")+1, mc.length());
		}
		
		soapParameter.addProperty("OptionConfiguration", oc);
		soapParameter.addProperty("ModConfiguration", mc);
		soapParameter.addProperty("Color", 0);
		soapParameter.addProperty("DisplayKm", 0);
		soapParameter.addProperty("DisplayMiles", 0);
		soapParameter.addProperty("InstrumentIsChanged", 0);
		soapParameter.addProperty("GuidePrice", 0);
		soapParameter.addProperty("LowestPrice", 0);
		soapParameter.addProperty("UsedPriceLow", 0);
		soapParameter.addProperty("UsedPriceHigh", 0);
		soapParameter.addProperty("Photo1", "");
		soapParameter.addProperty("Photo2", "");
		soapParameter.addProperty("Photo3", "");
		soapParameter.addProperty("Photo4", "");
		
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
						if(type == 2)
						{
							Intent intent = new Intent();
							intent.setClass(context, CarConfigurationDetailActivity.class);
							//型号id
							intent.putExtra("xinghaoID", xinghaoID);
							//选装配置信息
							intent.putExtra("oc", oc);
							//改装配置信息
							intent.putExtra("mc", mc);
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
}
