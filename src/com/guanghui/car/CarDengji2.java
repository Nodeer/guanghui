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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.guanghui.car.adapter.CarDengji1Adapter;
import com.guanghui.car.adapter.CarDengji2Adapter;
import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.staticdata.CarConfigData;

/**
 * 车型登记2
 * @author zhangyun
 *
 */
public class CarDengji2 extends Activity{

	private Context context = this;
	private Button back;
	private Button seach;
	private EditText seachEdt;
	private ListView listview;
	private TextView carType;
	private String deng1_id;
	private String deng1_Name;
	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	List<HashMap> ListInfo;
	private String clickId;
	private String clickFullName;
	private String clickBrandName;
	private String clickName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.car_dengji2);
		new BuildTab(context,1) ;
				
		back = (Button)this.findViewById(R.id.car_dengji2_btn_back);
		seach = (Button)this.findViewById(R.id.car_dengji2_seach);
		seachEdt = (EditText)this.findViewById(R.id.car_dengji2_edt_seach);
		listview = (ListView)this.findViewById(R.id.car_dengji2_listview);
		carType = (TextView)this.findViewById(R.id.car_dengji2_car_type);
		initTitle();
		deng1_id=getIntent().getExtras().getString("Id");
		deng1_Name=getIntent().getExtras().getString("Name");
		carType.setText("品牌："+deng1_Name);
		getDataInfo(deng1_id ,"0");
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				
				clickFullName = arg1.getTag().toString().split(",")[1];
				clickId = arg1.getTag().toString().split(",")[0];
				
				String BrandName = arg1.getTag().toString().split(",")[2];
				String name = arg1.getTag().toString().split(",")[3];
				
				CarConfigData.Brand = BrandName;
				CarConfigData.Catalog = name;
				
				
				intent.putExtra("Id", clickId);
				intent.putExtra("FullName", clickFullName);
				intent.setClass(context, CarDengji3.class);
				startActivity(intent);
			}
		});
		
		
		
		back.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		seach.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
				imm.hideSoftInputFromWindow(seachEdt.getWindowToken(), 0);
				if (!seachEdt.getText().toString().trim().equals("")) {
					listview.setAdapter(new CarDengji2Adapter(context, getSearchList(ListInfo,seachEdt.getText().toString())));
				}
				else {
					listview.setAdapter(new CarDengji2Adapter(context, ListInfo));
				}
			}
		});
	}

	private void initTitle()
	{
		((TextView) findViewById(R.id.title_renwu_btn1)).setBackgroundDrawable(context.getResources().getDrawable(R.drawable.title_bg));
		((TextView) findViewById(R.id.title_renwu_btn2)).setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg));
		((TextView) findViewById(R.id.title_renwu_btn3)).setBackgroundDrawable(context.getResources().getDrawable(R.drawable.title_bg));
	}
	
	/**
	 * 获取品牌信息
	 */
	private void getDataInfo(String BrandID ,String ManufacturerID ) {
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace,
				"GetCatalogs");
		soapParameter.addProperty("brandID", BrandID);
		soapParameter.addProperty("manufacturerID",ManufacturerID);
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
							String ManufacturerID = ((SoapObject) Items
									.getProperty(i)).getProperty(
									"ManufacturerID").toString();
							String ManufacturerName = ((SoapObject) Items
									.getProperty(i)).getProperty(
									"ManufacturerName").toString();
							String BrandID = ((SoapObject) Items
									.getProperty(i)).getProperty("BrandID")
									.toString();
							String BrandName = ((SoapObject) Items
									.getProperty(i)).getProperty("BrandName")
									.toString();
							String Name = ((SoapObject) Items
									.getProperty(i)).getProperty("Name")
									.toString();
							String FullName = ((SoapObject) Items
									.getProperty(i)).getProperty("FullName")
									.toString();
							String Comments = ((SoapObject) Items
									.getProperty(i)).getProperty("Comments")
									.toString();
							String firstLetter=((SoapObject) Items
									.getProperty(i)).getProperty("FirstLetter")
									.toString();
							String firstLetter2=((SoapObject) Items
									.getProperty(i)).getProperty("ManufacturerFirstLetter")
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
							Info.put("ManufacturerID", ManufacturerID);
							Info.put("ManufacturerName", ManufacturerName);
							Info.put("BrandID", BrandID);
							Info.put("BrandName", BrandName);
							Info.put("FullName", FullName);
							Info.put("Name", Name);
							Info.put("Image1", image1_url);
							Info.put("Image2", image2_url);
							Info.put("Image3", image3_url);
							Info.put("Image4", image4_url);
							Info.put("Comments", Comments);
							Info.put("FirstLetter",firstLetter);
							Info.put("FirstLetter2",firstLetter2);							
							ListInfo.add(Info);
						}

						listview.setAdapter(new CarDengji2Adapter(context,
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
		mgr = new SoapMgr(context, null, null, SoapAction.GetCatalogs,
				"GetCatalogs", soapParameter, myhandler, true, false);
	}
	private ArrayList<HashMap> getSearchList(List<HashMap> list,
			String firstLetter) {
		ArrayList<HashMap> ListInfo = new ArrayList<HashMap>();
		for (int i = 0; i < list.size(); i++) {
			// 获得品牌信息
			if (((String) list.get(i).get("FirstLetter2")).equalsIgnoreCase(firstLetter)) {
				String Id = list.get(i).get("Id").toString();
				String ManufacturerID =list.get(i).get(
						"ManufacturerID").toString();
//				String ManufacturerName = list.get(i).get(
//						"ManufacturerName").toString();
				String BrandID = list.get(i).get("BrandID")
						.toString();
				String BrandName = list.get(i).get("BrandName")
						.toString();
				String Name = list.get(i).get("Name")
						.toString();
				String FullName = list.get(i).get("FullName")
						.toString();
				String Comments = list.get(i).get("Comments")
						.toString();
				String image1_url = list.get(i).get("Image1")
						.toString();
				String image2_url= list.get(i).get("Image2")
						.toString();
				String image3_url= list.get(i).get("Image3")
						.toString();
				String image4_url= list.get(i).get("Image4")
						.toString();
				HashMap Info = new HashMap();
				Info.put("Id", Id);
				Info.put("ManufacturerID", ManufacturerID);
//				Info.put("ManufacturerName", ManufacturerName);
				Info.put("BrandID", BrandID);
				Info.put("BrandName", BrandName);
				Info.put("FullName", FullName);
				Info.put("Name", Name);
				Info.put("Image1", image1_url);
				Info.put("Image2", image2_url);
				Info.put("Image3", image3_url);
				Info.put("Image4", image4_url);
				Info.put("Comments", Comments);
				Info.put("firstLetter",firstLetter);
				ListInfo.add(Info);
			}
		}
		return ListInfo;

	}
}
