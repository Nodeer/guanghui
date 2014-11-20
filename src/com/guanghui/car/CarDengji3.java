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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.guanghui.car.adapter.CarDengji1Adapter;
import com.guanghui.car.adapter.CarDengji3Adapter;
import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;

/**
 * 车型登记3
 * @author zhangyun
 *
 */
public class CarDengji3 extends Activity{

	private Context context = this;
	private Button back;
	private ListView listview;
	private TextView car_dengji3_txt;
	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	private String dengji3_id;
	private String dengji3_fullName;
	private String dengji3_BrandName;
	private String dengji3_Name;
	private ArrayList<HashMap> ListInfo;
	private String SeriesName;
	private String SeriesId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.car_dengji3);	
		new BuildTab(context,1) ;
		back = (Button)this.findViewById(R.id.car_dengji3_btn_back);
		listview = (ListView)this.findViewById(R.id.car_dengji3_listview);
		car_dengji3_txt = (TextView)this.findViewById(R.id.car_dengji3_txt);
		dengji3_id=getIntent().getExtras().getString("Id");
		dengji3_fullName =getIntent().getExtras().getString("FullName");
		dengji3_BrandName = getIntent().getExtras().getString("BrandName");
		dengji3_Name = getIntent().getExtras().getString("Name");
		
		car_dengji3_txt.setText(dengji3_fullName);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				//intent.setClass(context, MainActivity.class);
				//intent.setClass(context, ShouXuDeng1JiAcitivity.class);
				SeriesName = arg1.getTag().toString().split(",")[1];
				SeriesId= arg1.getTag().toString().split(",")[0];
				intent.putExtra("title_name", dengji3_fullName+"-"+SeriesName);
				intent.putExtra("SeriesId", SeriesId);
				intent.setClass(context, CarDengji4.class);
				startActivity(intent);
			}
		});
		
		initTitle();
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		getDataInfo(dengji3_id);
	}

	private void initTitle()
	{
		((TextView) findViewById(R.id.title_renwu_btn1)).setBackgroundDrawable(context.getResources().getDrawable(R.drawable.title_bg));
		((TextView) findViewById(R.id.title_renwu_btn2)).setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg));
		((TextView) findViewById(R.id.title_renwu_btn3)).setBackgroundDrawable(context.getResources().getDrawable(R.drawable.title_bg));
	}
	
	/**
	 * 获取车辆系列信息
	 */
	private void getDataInfo(String CategoryId) {
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace,
				"GetSeriesList");		
		soapParameter.addProperty("catalogID", CategoryId);
		myhandler = new MyHandler() {
			@Override
			public void success(Message msg) {
				backSoapObject = mgr.getServiceBackSoapObject();
				if (backSoapObject != null) {
					String Retcode = ((SoapObject) backSoapObject
							.getProperty(0)).getProperty("Retcode").toString();
					// 0成功，1失败
					if (Retcode.equals("0")) {

						ListInfo = new ArrayList<HashMap>();
						SoapObject Items = ((SoapObject) ((SoapObject) backSoapObject
								.getProperty(0)).getProperty("Items"));
						
						
						if(Items.getPropertyCount() == 0)
						{
							//没有数据
							final MineAlert alert = new MineAlert(context);
							alert.createAlertOneButton(false,"服务器没有相关的数据资源", 
									new View.OnClickListener() {								
										@Override
										public void onClick(View v) {
											alert.dimiss();
										}
									});
							return;
						}
						
						
						
						for (int i = 0; i < Items.getPropertyCount(); i++) {
							// 获得品牌信息
							String Id = ((SoapObject) Items.getProperty(i))
									.getProperty("Id").toString();
							String CatalogID = ((SoapObject) Items
									.getProperty(i)).getProperty(
									"CatalogID").toString();
							String Name = ((SoapObject) Items
									.getProperty(i)).getProperty("Name")
									.toString();
							String Emission = ((SoapObject) Items
									.getProperty(i)).getProperty("Emission")
									.toString();
							String TransType = ((SoapObject) Items
									.getProperty(i)).getProperty("TransType")
									.toString();
							String Carriage = ((SoapObject) Items
									.getProperty(i)).getProperty("Carriage")
									.toString();
							String Drive = ((SoapObject) Items
									.getProperty(i)).getProperty("Drive")
									.toString();
							String SeatNum=((SoapObject) Items
									.getProperty(i)).getProperty("SeatNum")
									.toString();
							String Fuel=((SoapObject) Items
									.getProperty(i)).getProperty("Fuel")
									.toString();
							String Comments=((SoapObject) Items
									.getProperty(i)).getProperty("Comments")
									.toString();
							String image1_url = ((SoapObject) Items
									.getProperty(i)).getProperty("Img1")
									.toString();
							String image2_url= ((SoapObject) Items
									.getProperty(i)).getProperty("Img2")
									.toString();
							String image3_url= ((SoapObject) Items
									.getProperty(i)).getProperty("Img3")
									.toString();
							String image4_url= ((SoapObject) Items
									.getProperty(i)).getProperty("Img4")
									.toString();
							HashMap Info = new HashMap();
							Info.put("Id", Id);
							Info.put("CatalogID", CatalogID);
							Info.put("Name", Name);
							Info.put("Drive", Drive);
							Info.put("SeatNum", SeatNum);
							Info.put("Fuel", Fuel);
							Info.put("Comments", Comments);
							Info.put("Image1", image1_url);
							Info.put("Image2", image2_url);
							Info.put("Image3", image3_url);
							Info.put("Image4", image4_url);
							Info.put("TransType", TransType);
							Info.put("Emission",Emission);
							Info.put("Carriage",Carriage);
							ListInfo.add(Info);
						}

						listview.setAdapter(new CarDengji3Adapter(context,
								ListInfo));

					} else {
						ToastUtilMgr.TextToast(context,
								getString(R.string.server_error1),
								ToastUtilMgr.LENGTH_LONG);
					}
				}
			}

			@Override
			public void failed(Message msg) {

			}
		};
		mgr = new SoapMgr(context, null, null, SoapAction.GetSeriesList,
				"GetSeriesList", soapParameter, myhandler, true, false);
	}
	
}
