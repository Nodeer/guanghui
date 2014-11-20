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
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.guanghui.car.adapter.CarKeyCofigurationAdapter;
import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.staticdata.CarConfigData;
import com.guanghui.car.staticdata.CarData;

/**
 * 车型登记4
 * 
 * @author zhangyun
 * 
 */
public class CarDengji4 extends Activity {

	private Context context = this;

	private Button returnBtn;
	private Button car_dengji4_config_btn1;
	private Button car_dengji4_config_btn2;
	private Button car_dengji4_page1;
	private Button car_dengji4_page2;
	private LinearLayout car_dengji3_ly;
	private TextView car_dengji4_text;
	private TextView car_dengji4_config_txt1;
	private TextView car_dengji4_config_txt2;
	private String series_id;
	private Spinner Emission_spinner;
	private Spinner TransType_spinner;
	private Spinner Carriage_spinner;
	private Spinner Drive_spinner;
	private Spinner SeatNum_spinner;
	private Spinner Fuel_spinner;
	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	private LinearLayout car_dengji4_ly;
	private ListView car_dengji4_listview;
	private Button car_dengji4_search;
	private String KeyCofiguration;
	private String OptionCofiguration;
	private String ModCofiguration;
	private int ModelCount;
	private List<List<HashMap>> listCarInfo;
	//当前显示的Index号
	private int CurrIndex;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.car_dengji4);
		new BuildTab(context,1) ;
		returnBtn = (Button) findViewById(R.id.car_dengji4_btn_back);
		car_dengji4_page1 = (Button) findViewById(R.id.car_dengji4_page1);
		car_dengji4_page2 = (Button) findViewById(R.id.car_dengji4_page2);
		car_dengji4_page1.setOnClickListener(mListener);
		car_dengji4_page2.setOnClickListener(mListener);
		car_dengji3_ly = (LinearLayout) findViewById(R.id.car_dengji3_ly);
		car_dengji3_ly.setVisibility(View.GONE);
		
		car_dengji4_text = (TextView) findViewById(R.id.car_dengji4_txt);
		car_dengji4_text.setText(getIntent().getExtras()
				.getString("title_name"));
		
		car_dengji4_config_txt1 = (TextView) findViewById(R.id.car_dengji4_config_txt1);
		car_dengji4_config_txt2 = (TextView) findViewById(R.id.car_dengji4_config_txt2);
		series_id = getIntent().getExtras().getString("SeriesId");
		car_dengji4_config_btn1 = (Button) findViewById(R.id.car_dengji4_config_btn1);
		car_dengji4_config_btn2 = (Button) findViewById(R.id.car_dengji4_config_btn2);
		car_dengji4_ly = (LinearLayout) findViewById(R.id.car_dengji4_ly);
		car_dengji4_ly.setVisibility(View.GONE);
		
		Emission_spinner = (Spinner) findViewById(R.id.Emission_spinner);
		TransType_spinner = (Spinner) findViewById(R.id.TransType_spinner);
		Carriage_spinner = (Spinner) findViewById(R.id.Carriage_spinner);
		Drive_spinner = (Spinner) findViewById(R.id.Drive_spinner);
		SeatNum_spinner = (Spinner) findViewById(R.id.SeatNum_spinner);
		Fuel_spinner = (Spinner) findViewById(R.id.Fuel_spinner);
		car_dengji4_listview = (ListView) findViewById(R.id.car_dengji4_listview);
		car_dengji4_search = (Button) findViewById(R.id.car_dengji4_search);
		car_dengji4_search.setOnClickListener(mListener);
		car_dengji4_search.setEnabled(false);
		car_dengji4_config_btn1.setOnClickListener(mListener);
		car_dengji4_config_btn2.setOnClickListener(mListener);
		returnBtn.setOnClickListener(mListener);
		
		
		
		initTitle();
		
		//获取下拉框数据
		getSpinnerData();
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

	private OnClickListener mListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.car_dengji4_btn_back:
				finish();
				break;
			case R.id.car_dengji4_config_btn2:
				Intent intent1 = new Intent();
				intent1.setClass(context, CarConfigurationActivity.class);
				intent1.putExtra("Tag", v.getTag().toString());
				startActivity(intent1);
				break;
			case R.id.car_dengji4_config_btn1:
				Intent intent2 = new Intent();
				intent2.setClass(context, CarConfigurationActivity.class);
				intent2.putExtra("Tag", v.getTag().toString());
				startActivity(intent2);
				break;
			case R.id.car_dengji4_search:
				//
				String EmissionID = Emission_spinner.getTag().toString().split(",")[Emission_spinner.getSelectedItemPosition()];
				String TransTypeID = TransType_spinner.getTag().toString().split(",")[TransType_spinner.getSelectedItemPosition()];
				String CarriageID = Carriage_spinner.getTag().toString().split(",")[Carriage_spinner.getSelectedItemPosition()];
				String DriveID = Drive_spinner.getTag().toString().split(",")[Drive_spinner.getSelectedItemPosition()];
				String SeatNumID = SeatNum_spinner.getTag().toString().split(",")[SeatNum_spinner.getSelectedItemPosition()];
				String FuelID = Fuel_spinner.getTag().toString().split(",")[Fuel_spinner.getSelectedItemPosition()];
				GetModels(series_id,EmissionID,TransTypeID,CarriageID,DriveID,SeatNumID,FuelID);
				break;		
			case R.id.car_dengji4_page1:
				//上一页
				
				if(CurrIndex > 1)
				{
					CurrIndex = CurrIndex - 1;
					
					car_dengji4_config_txt1.setVisibility(View.VISIBLE);
					car_dengji4_config_btn1.setVisibility(View.VISIBLE);
					car_dengji4_config_txt2.setVisibility(View.VISIBLE);
					car_dengji4_config_btn2.setVisibility(View.VISIBLE);
					CarKeyCofigurationAdapter adapter = new CarKeyCofigurationAdapter(context,listCarInfo, CurrIndex*2-2, CurrIndex*2-1);
					//
					car_dengji4_config_txt1.setText(listCarInfo.get(CurrIndex*2-2).get(0).get("XinghaoName").toString());
					car_dengji4_config_btn1.setTag(listCarInfo.get(CurrIndex*2-2).get(0).get("XinghaoID").toString()
							+"<and>"+listCarInfo.get(CurrIndex*2-2).get(0).get("KeyCofiguration").toString()
							+"<and>"+listCarInfo.get(CurrIndex*2-2).get(0).get("OptionCofiguration").toString()
							+"<and>"+listCarInfo.get(CurrIndex*2-2).get(0).get("ModCofiguration").toString()	
							);
					//
					car_dengji4_config_txt2.setText(listCarInfo.get(CurrIndex*2-1).get(0).get("XinghaoName").toString());
					car_dengji4_config_btn2.setTag(listCarInfo.get(CurrIndex*2-1).get(0).get("XinghaoID").toString()
							+"<and>"+listCarInfo.get(CurrIndex*2-1).get(0).get("KeyCofiguration").toString()
							+"<and>"+listCarInfo.get(CurrIndex*2-1).get(0).get("OptionCofiguration").toString()
							+"<and>"+listCarInfo.get(CurrIndex*2-1).get(0).get("ModCofiguration").toString()	
							);
					
					car_dengji4_listview.setAdapter(adapter);
					car_dengji3_ly.setVisibility(View.VISIBLE);					
					
				}
				else
				{
					Toast.makeText(context, "已是第一页了", Toast.LENGTH_SHORT).show();
				}
				
				break;
			case R.id.car_dengji4_page2:
				int maxpage = listCarInfo.size();
				
				//下一页
				int a = maxpage % 2 ;
				if(a > 0)
				{
					maxpage = maxpage / 2 + 1 ;
				}
				else
				{
					maxpage = maxpage / 2 ;
				}
				
				if( CurrIndex < maxpage )
				{
					CurrIndex ++ ;
					
					car_dengji4_config_txt1.setVisibility(View.VISIBLE);
					car_dengji4_config_btn1.setVisibility(View.VISIBLE);
					car_dengji4_config_txt2.setVisibility(View.VISIBLE);
					car_dengji4_config_btn2.setVisibility(View.VISIBLE);
					CarKeyCofigurationAdapter adapter;
					if(CurrIndex*2-1 > listCarInfo.size()-1)
					{
						adapter = new CarKeyCofigurationAdapter(context,listCarInfo, CurrIndex*2-2, -1);
					}
					else
					{
						adapter = new CarKeyCofigurationAdapter(context,listCarInfo, CurrIndex*2-2, CurrIndex*2-1);
					}
					
					//
					car_dengji4_config_txt1.setText(listCarInfo.get(CurrIndex*2-2).get(0).get("XinghaoName").toString());
					car_dengji4_config_btn1.setTag(listCarInfo.get(CurrIndex*2-2).get(0).get("XinghaoID").toString()
							+"<and>"+listCarInfo.get(CurrIndex*2-2).get(0).get("KeyCofiguration").toString()
							+"<and>"+listCarInfo.get(CurrIndex*2-2).get(0).get("OptionCofiguration").toString()
							+"<and>"+listCarInfo.get(CurrIndex*2-2).get(0).get("ModCofiguration").toString()	
							);
					//
					if(CurrIndex*2-1 > listCarInfo.size()-1)
					{
						car_dengji4_config_txt2.setVisibility(View.INVISIBLE);
						car_dengji4_config_btn2.setVisibility(View.INVISIBLE);
						car_dengji3_ly.setVisibility(View.VISIBLE);		
					}
					else
					{
						car_dengji4_config_txt2.setText(listCarInfo.get(CurrIndex*2-1).get(0).get("XinghaoName").toString());
						car_dengji4_config_btn2.setTag(listCarInfo.get(CurrIndex*2-1).get(0).get("XinghaoID").toString()
								+"<and>"+listCarInfo.get(CurrIndex*2-1).get(0).get("KeyCofiguration").toString()
								+"<and>"+listCarInfo.get(CurrIndex*2-1).get(0).get("OptionCofiguration").toString()
								+"<and>"+listCarInfo.get(CurrIndex*2-1).get(0).get("ModCofiguration").toString()	
								);
						car_dengji3_ly.setVisibility(View.VISIBLE);		
					}
					
					car_dengji4_listview.setAdapter(adapter);
					
				}
				else
				{
					Toast.makeText(context, "已是最后一页了", Toast.LENGTH_SHORT).show();
				}
				
				
				break;
			default:
				break;
			}
		}
	};

	
	
	
	
	
	
	/**
     * 获取下拉框数据
     */
    private void getSpinnerData()
    {		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetSeries");
		soapParameter.addProperty("ID", series_id);				
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
						car_dengji4_search.setEnabled(true);
						
						//获得该用户信息
						String Emission = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("Series")).getProperty("Emission").toString();
						String TransType = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("Series")).getProperty("TransType").toString();
						String Carriage = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("Series")).getProperty("Carriage").toString();
						String Drive = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("Series")).getProperty("Drive").toString();
						String SeatNum = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("Series")).getProperty("SeatNum").toString();
						String Fuel = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("Series")).getProperty("Fuel").toString();
						KeyCofiguration = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("Series")).getProperty("KeyCofiguration").toString();
						CarConfigData.KeyCofiguration = KeyCofiguration;
						OptionCofiguration = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("Series")).getProperty("OptionCofiguration").toString();
						CarConfigData.OptionCofiguration = OptionCofiguration;
						ModCofiguration = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("Series")).getProperty("ModCofiguration").toString();
						CarConfigData.ModCofiguration = ModCofiguration;						
						
						
						List<String> Emissions = new ArrayList<String>();
						for(String id :Emission.split(","))
						{
							Emissions.add(CarData.Emission(id));
						}		
						ArrayAdapter adapter1 = new ArrayAdapter(context,android.R.layout.simple_spinner_item,Emissions);
						adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						Emission_spinner.setAdapter(adapter1);
						Emission_spinner.setTag(Emission);
						
						List<String> TransTypes = new ArrayList<String>();
						for(String id :TransType.split(","))
						{
							TransTypes.add(CarData.TransType(id));
						}
						ArrayAdapter adapter2 = new ArrayAdapter(context,android.R.layout.simple_spinner_item,TransTypes);
						adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						TransType_spinner.setAdapter(adapter2);						
						TransType_spinner.setTag(TransType);
						
						List<String> Carriages = new ArrayList<String>();
						for(String id :Carriage.split(","))
						{
							Carriages.add(CarData.Carriage(id));
						}
						ArrayAdapter adapter3 = new ArrayAdapter(context,android.R.layout.simple_spinner_item,Carriages);
						adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						Carriage_spinner.setAdapter(adapter3);		
						Carriage_spinner.setTag(Carriage);
						
						List<String> Drives = new ArrayList<String>();
						for(String id :Drive.split(","))
						{
							Drives.add(CarData.Drive(id));
						}
						ArrayAdapter adapter4 = new ArrayAdapter(context,android.R.layout.simple_spinner_item,Drives);
						adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						Drive_spinner.setAdapter(adapter4);
						Drive_spinner.setTag(Drive);
						
						List<String> SeatNums = new ArrayList<String>();
						for(String id :SeatNum.split(","))
						{
							SeatNums.add(CarData.SeatNum(id));
						}
						ArrayAdapter adapter5 = new ArrayAdapter(context,android.R.layout.simple_spinner_item,SeatNums);
						adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						SeatNum_spinner.setAdapter(adapter5);
						SeatNum_spinner.setTag(SeatNum);
						
						List<String> Fuels = new ArrayList<String>();
						for(String id :Fuel.split(","))
						{
							Fuels.add(CarData.Fuel(id));
						}
						ArrayAdapter adapter6 = new ArrayAdapter(context,android.R.layout.simple_spinner_item,Fuels);
						adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						Fuel_spinner.setAdapter(adapter6);
						Fuel_spinner.setTag(Fuel);
						
						
						
						if(Emission.trim().equals("anyType{}")
								||TransType.trim().equals("anyType{}")
								||Carriage.trim().equals("anyType{}")
								||Drive.trim().equals("anyType{}")
								||SeatNum.trim().equals("anyType{}")
								||Fuel.trim().equals("anyType{}")
								)
						{
							final MineAlert alert = new MineAlert(context);
							alert.createAlertOneButton(false,"服务器返回该车型的筛选信息不完整!", 
									new View.OnClickListener() {								
										@Override
										public void onClick(View v) {
											alert.dimiss();
										}
									});
							car_dengji4_search.setEnabled(false);
							
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
						SoapAction.GetSeries , 
						"GetSeries",
						soapParameter,
						myhandler,
						true,
						false
				);
    }
    
    
    
    /**
     * 根据筛选条件检索具体车型
     */
    private void GetModels(String SeriesID,String Emission,String TransType,String Carriage,String Drive,String SeatNum,String Fuel)
    {		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetModels");
		soapParameter.addProperty("SeriesID", SeriesID);
		soapParameter.addProperty("Emission", Emission);
		soapParameter.addProperty("TransType", TransType);
		soapParameter.addProperty("Carriage", Carriage);
		soapParameter.addProperty("Drive", Drive);
		soapParameter.addProperty("SeatNum", SeatNum);
		soapParameter.addProperty("Fuel", Fuel);
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

						SoapObject Items = ((SoapObject) ((SoapObject) backSoapObject
								.getProperty(0)).getProperty("Items"));
						ModelCount = Items.getPropertyCount();
						if(Items.getPropertyCount()!=0)
						{
							car_dengji4_ly.setVisibility(View.VISIBLE);
							String[] KeyCofigurations = KeyCofiguration.split(",");							
							//把所有数据封装起来
							listCarInfo = new ArrayList<List<HashMap>>();
							for (int i = 0; i < Items.getPropertyCount(); i++) {								
								//关键配置信息
								String CarKeyCofiguration = ((SoapObject) Items.getProperty(i))
										.getProperty("KeyCofiguration").toString();
								//型号名
								String xinghaoName = ((SoapObject) Items.getProperty(i))
										.getProperty("Name").toString();
								//型号ID
								String xinghaoId = ((SoapObject) Items.getProperty(i))
										.getProperty("Id").toString();
								//选装配置
								String OptionCofiguration = ((SoapObject) Items.getProperty(i))
										.getProperty("OptionCofiguration").toString();
								//改装配置
								String ModCofiguration = ((SoapObject) Items.getProperty(i))
										.getProperty("ModCofiguration").toString();
								
								String[] CarKeyCofigurations = CarKeyCofiguration.split(",");
								//格式   key  为关键配置id号   value  0,1,2
								List<HashMap> listMap = new ArrayList<HashMap>();
								//遍历所有的关键配置项
								for(String info : KeyCofigurations)
								{
									boolean isExists = false;
									String id="";
									
									
									if(!CarKeyCofiguration.equals("") && !CarKeyCofiguration.equals("anyType{}"))
									{
										//关键配置不为空的情况
										//遍历model中的关键配置项									
										for(String car: CarKeyCofigurations)
										{
											id = car.substring(0, car.indexOf(":"));
											String infoid= info.substring(0, info.indexOf(":"));
											//匹配配置信息中是否存在相同的id号
											if(infoid.equals(id))
											{											
												isExists = true;
												if(car.substring(car.indexOf(":")+1,car.length()).equals("0"))
												{
													//无
													HashMap map = new HashMap();
													map.put("ConfigID", car.substring(0, car.indexOf(":")));
													map.put("ConfigValue", 0);
													map.put("XinghaoID", xinghaoId);
													map.put("XinghaoName",xinghaoName);
													map.put("KeyCofiguration",CarKeyCofiguration);
													map.put("OptionCofiguration",OptionCofiguration);
													map.put("ModCofiguration",ModCofiguration);
													listMap.add(map);
												}
												if(car.substring(car.indexOf(":")+1,car.length()).equals("1"))
												{
													//有
													HashMap map = new HashMap();
													map.put("ConfigID", car.substring(0, car.indexOf(":")));
													map.put("ConfigValue", 1);
													map.put("XinghaoID", xinghaoId);
													map.put("XinghaoName",xinghaoName);
													map.put("KeyCofiguration",CarKeyCofiguration);
													map.put("OptionCofiguration",OptionCofiguration);
													map.put("ModCofiguration",ModCofiguration);
													listMap.add(map);
												}
												if(car.substring(car.indexOf(":")+1,car.length()).equals("2"))
												{
													//选装
													HashMap map = new HashMap();
													map.put("ConfigID", car.substring(0, car.indexOf(":")));
													map.put("ConfigValue", 2);
													map.put("XinghaoID", xinghaoId);
													map.put("XinghaoName",xinghaoName);
													map.put("KeyCofiguration",CarKeyCofiguration);
													map.put("OptionCofiguration",OptionCofiguration);
													map.put("ModCofiguration",ModCofiguration);
													listMap.add(map);
												}
												break;
											}
											
										}	
										if(!isExists)
										{
											//不存在该ID显示为无的状态 ,也就是0
											HashMap map = new HashMap();
											map.put("ConfigID", id);
											map.put("ConfigValue", 0);
											map.put("XinghaoID", xinghaoId);
											map.put("XinghaoName",xinghaoName);
											map.put("KeyCofiguration",CarKeyCofiguration);
											map.put("OptionCofiguration",OptionCofiguration);
											map.put("ModCofiguration",ModCofiguration);
											listMap.add(map);
										}
									}
									else
									{
										//关键配置为空的情况
										HashMap map = new HashMap();
										map.put("ConfigID", "0");
										map.put("ConfigValue", 0);
										map.put("XinghaoID", xinghaoId);
										map.put("XinghaoName",xinghaoName);
										map.put("KeyCofiguration","-");
										if(OptionCofiguration.equals("") || OptionCofiguration.equals("anyType{}"))
										{
											map.put("OptionCofiguration","-");
										}
										else
										{
											map.put("OptionCofiguration",OptionCofiguration);
										}
										if(ModCofiguration.equals("") || ModCofiguration.equals("anyType{}"))
										{
											map.put("ModCofiguration","-");
										}
										else
										{
											map.put("ModCofiguration",ModCofiguration);
										}
										listMap.add(map);
									}
									
									
									
								}								
								listCarInfo.add(listMap);			
							}
							CarKeyCofigurationAdapter adapter = null;
							//-1代表不显示该列信息
							if(ModelCount == 1)
							{
								adapter = new CarKeyCofigurationAdapter(context,listCarInfo,0,-1);
								car_dengji4_config_txt2.setVisibility(View.INVISIBLE);
								car_dengji4_config_btn2.setVisibility(View.INVISIBLE);
								//
								car_dengji4_config_txt1.setText(listCarInfo.get(0).get(0).get("XinghaoName").toString());
								car_dengji4_config_btn1.setTag(listCarInfo.get(0).get(0).get("XinghaoID").toString()
										+"<and>"+listCarInfo.get(0).get(0).get("KeyCofiguration").toString()
										+"<and>"+listCarInfo.get(0).get(0).get("OptionCofiguration").toString()
										+"<and>"+listCarInfo.get(0).get(0).get("ModCofiguration").toString()										
										);
								car_dengji3_ly.setVisibility(View.GONE);
							}
							if(ModelCount == 2)
							{
								car_dengji4_config_txt2.setVisibility(View.VISIBLE);
								car_dengji4_config_btn2.setVisibility(View.VISIBLE);
								adapter = new CarKeyCofigurationAdapter(context,listCarInfo,0,1);
								//
								car_dengji4_config_txt1.setText(listCarInfo.get(0).get(0).get("XinghaoName").toString());
								car_dengji4_config_btn1.setTag(listCarInfo.get(0).get(0).get("XinghaoID").toString()
										+"<and>"+listCarInfo.get(0).get(0).get("KeyCofiguration").toString()
										+"<and>"+listCarInfo.get(0).get(0).get("OptionCofiguration").toString()
										+"<and>"+listCarInfo.get(0).get(0).get("ModCofiguration").toString()	
										);
								//
								car_dengji4_config_txt2.setText(listCarInfo.get(1).get(0).get("XinghaoName").toString());
								car_dengji4_config_btn2.setTag(listCarInfo.get(1).get(0).get("XinghaoID").toString()
										+"<and>"+listCarInfo.get(1).get(0).get("KeyCofiguration").toString()
										+"<and>"+listCarInfo.get(1).get(0).get("OptionCofiguration").toString()
										+"<and>"+listCarInfo.get(1).get(0).get("ModCofiguration").toString()	
										);
								car_dengji3_ly.setVisibility(View.GONE);								
								
							}	
							if(ModelCount>2)
							{
								car_dengji4_config_txt2.setVisibility(View.VISIBLE);
								car_dengji4_config_btn2.setVisibility(View.VISIBLE);
								adapter = new CarKeyCofigurationAdapter(context,listCarInfo,0,1);
								//
								car_dengji4_config_txt1.setText(listCarInfo.get(0).get(0).get("XinghaoName").toString());
								car_dengji4_config_btn1.setTag(listCarInfo.get(0).get(0).get("XinghaoID").toString()
										+"<and>"+listCarInfo.get(0).get(0).get("KeyCofiguration").toString()
										+"<and>"+listCarInfo.get(0).get(0).get("OptionCofiguration").toString()
										+"<and>"+listCarInfo.get(0).get(0).get("ModCofiguration").toString()	
										);
								//
								car_dengji4_config_txt2.setText(listCarInfo.get(1).get(0).get("XinghaoName").toString());
								car_dengji4_config_btn2.setTag(listCarInfo.get(1).get(0).get("XinghaoID").toString()
										+"<and>"+listCarInfo.get(1).get(0).get("KeyCofiguration").toString()
										+"<and>"+listCarInfo.get(1).get(0).get("OptionCofiguration").toString()
										+"<and>"+listCarInfo.get(1).get(0).get("ModCofiguration").toString()	
										);
								car_dengji3_ly.setVisibility(View.VISIBLE);
								
								CurrIndex = 1;
							}
							car_dengji4_listview.setAdapter(adapter);
							
						}
						else
						{
							car_dengji4_ly.setVisibility(View.GONE);
							final MineAlert alert = new MineAlert(context);
							alert.createAlertOneButton(false,"不存在满足筛选条件的车辆！", 
									new View.OnClickListener() {								
										@Override
										public void onClick(View v) {
											alert.dimiss();
										}
									});
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
						SoapAction.GetModels , 
						"GetModels",
						soapParameter,
						myhandler,
						true,
						false
				);
    }
    
}
