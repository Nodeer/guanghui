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
import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;

/**
 * 车型登记1
 * 
 * @author zhangyun
 * 
 */
public class CarDengji1 extends Activity {

	private Context context = this;
	private Button back;
	private Button seach;
	private EditText seachEdt;
	private ListView listview;
	List<HashMap> ListInfo;

	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	
	private String clickId;
	private String clickName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.car_dengji1);
		initTitle();
		new BuildTab(context,1) ;
		back = (Button) this.findViewById(R.id.car_dengji_btn_back);
		seach = (Button) this.findViewById(R.id.car_dengji1_seach);
		seachEdt = (EditText) this.findViewById(R.id.car_dengji1_edt_seach);
		listview = (ListView) this.findViewById(R.id.car_dengji1_listview);

		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		seach.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
				imm.hideSoftInputFromWindow(seachEdt.getWindowToken(), 0);
				if (!seachEdt.getText().toString().trim().equals("")) {
					listview.setAdapter(new CarDengji1Adapter(context, getSearchList(ListInfo,seachEdt.getText().toString())));
				}

				else {
					listview.setAdapter(new CarDengji1Adapter(context, ListInfo));
				}
			}
		});

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				clickId = arg1.getTag().toString().split(",") [0];
				clickName = arg1.getTag().toString().split(",") [1];
				Log.d("###", "id:"+clickId+"name:"+clickName);
				Intent intent = new Intent();
				intent.putExtra("Id", clickId);
				intent.putExtra("Name", clickName);
				intent.setClass(context, CarDengji2.class);
				startActivity(intent);

			}
		});

		getDataInfo();
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

	/**
	 * 获取品牌信息
	 */
	private void getDataInfo() {
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace,
				"GetBrands");
		soapParameter.addProperty("manufacturerID", "0");
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
						for (int i = 0; i < Items.getPropertyCount(); i++) {
							// 获得品牌信息
							String Id = ((SoapObject) Items.getProperty(i))
									.getProperty("Id").toString();
							String ManufacturerID = ((SoapObject) Items
									.getProperty(i)).getProperty(
									"ManufacturerID").toString();
							String Name = ((SoapObject) Items.getProperty(i))
									.getProperty("Name").toString();
							String FirstLetter = ((SoapObject) Items
									.getProperty(i)).getProperty("FirstLetter")
									.toString();
							String Logo = ((SoapObject) Items.getProperty(i))
									.getProperty("Logo").toString();
							String Comments = ((SoapObject) Items
									.getProperty(i)).getProperty("Comments")
									.toString();
							HashMap Info = new HashMap();
							Info.put("Id", Id);
							Info.put("ManufacturerID", ManufacturerID);
							Info.put("Name", Name);
							Info.put("FirstLetter", FirstLetter);
							Info.put("Logo", Logo);
							Info.put("Comments", Comments);
							ListInfo.add(Info);
						}

						listview.setAdapter(new CarDengji1Adapter(context,
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
		mgr = new SoapMgr(context, null, null, SoapAction.GetBrands,
				"GetBrands", soapParameter, myhandler, true, false);
	}

	private ArrayList<HashMap> getSearchList(List<HashMap> list,
			String firstLetter) {
		ArrayList<HashMap> ListInfo = new ArrayList<HashMap>();
		for (int i = 0; i < list.size(); i++) {
			// 获得品牌信息
			if (((String) list.get(i).get("FirstLetter")).equalsIgnoreCase(firstLetter)) {
				String Id = list.get(i).get("Id").toString();
				String ManufacturerID = list.get(i).get("ManufacturerID")
						.toString();
				String Name = list.get(i).get("Name").toString();
				String FirstLetter = list.get(i).get("FirstLetter").toString();
				String Logo = list.get(i).get("Logo").toString();
				String Comments = list.get(i).get("Comments").toString();
				HashMap Info = new HashMap();
				Info.put("Id", Id);
				Info.put("ManufacturerID", ManufacturerID);
				Info.put("Name", Name);
				Info.put("FirstLetter", FirstLetter);
				Info.put("Logo", Logo);
				Info.put("Comments", Comments);
				ListInfo.add(Info);
			}
		}
		return ListInfo;

	}
}
