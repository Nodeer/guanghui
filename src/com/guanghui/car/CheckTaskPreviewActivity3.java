package com.guanghui.car;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;
import com.guanghui.car.db.ChekuangDengjiDB;
import com.guanghui.car.db.ChexingDengjiDB;
import com.guanghui.car.db.ShouxuDengjiDB;

public class CheckTaskPreviewActivity3 extends Activity {
	private Context context = this;
	private EditText check_task_price;
	private RadioButton check_task_radiobutton2,check_task_radiobutton3,check_task_radiobutton4,check_task_radiobutton5,check_task_radiobutton6,check_task_radiobutton7;
	private Button saveButton;
	private Button commitButton;
	private EditText check_task_reson;
	private int chengjiao = 1;
	private Button check_task_preview_btn_back;
	private Button check_task_preview3_btn_price_select;
	
	private TextView check_task_preview3_txt1,check_task_preview3_txt2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_task_preview3);
		new BuildTab(context,1) ;
		
		
		
		check_task_preview_btn_back = (Button) findViewById(R.id.check_task_preview_btn_back);
		check_task_preview3_btn_price_select = (Button) findViewById(R.id.check_task_preview3_btn_price_select);
		check_task_preview_btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.setClass(context, ActivityMain.class);
				startActivity(intent);
			}
		});
		
		check_task_preview3_btn_price_select.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(context, BaoJiaListActivity.class);
				startActivity(intent);
			}
		});
		
		
		check_task_price = (EditText) findViewById(R.id.check_task_price);
		check_task_radiobutton2 = (RadioButton) findViewById(R.id.check_task_radiobutton2);
		check_task_radiobutton3 = (RadioButton) findViewById(R.id.check_task_radiobutton3);
		check_task_radiobutton4 = (RadioButton) findViewById(R.id.check_task_radiobutton4);
		check_task_radiobutton5 = (RadioButton) findViewById(R.id.check_task_radiobutton5);
		check_task_radiobutton6 = (RadioButton) findViewById(R.id.check_task_radiobutton6);
		check_task_radiobutton7 = (RadioButton) findViewById(R.id.check_task_radiobutton7);
		check_task_preview3_txt1 = (TextView) findViewById(R.id.check_task_preview3_txt1);
		check_task_preview3_txt2 = (TextView) findViewById(R.id.check_task_preview3_txt2);
		check_task_radiobutton2.setChecked(true);
		check_task_reson = (EditText) findViewById(R.id.check_task_reson);
		check_task_radiobutton2.setOnClickListener(mListener);
		check_task_radiobutton3.setOnClickListener(mListener);
		check_task_radiobutton4.setOnClickListener(mListener);
		check_task_radiobutton5.setOnClickListener(mListener);
		check_task_radiobutton6.setOnClickListener(mListener);
		check_task_radiobutton7.setOnClickListener(mListener);
		
		initBottom();
		
		
		initUIDataFromServer();
	}
	
	private OnClickListener mListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.check_task_radiobutton2:
				chengjiao = 1;
				break;
			case R.id.check_task_radiobutton3:
				chengjiao = 2;
				break;
			case R.id.check_task_radiobutton4:
				chengjiao = 3;
				break;
			case R.id.check_task_radiobutton5:
				chengjiao = 4;
				break;
			case R.id.check_task_radiobutton6:
				chengjiao = 5;
				break;
			case R.id.check_task_radiobutton7:
				chengjiao = 6;
				break;
			default:
				break;
			}
		}
	};

	private void initBottom() {
		saveButton = (Button) findViewById(R.id.stop_save);
		saveButton.setVisibility(View.GONE);
		commitButton = (Button) findViewById(R.id.comfirm_communit);
		commitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if(check_task_reson.getText().toString().trim().equals(""))
				{
					if(check_task_price.getText().toString().trim().equals(""))
					{
						ToastUtilMgr.TextToast(context,
								"请输入车辆收购价格",
								ToastUtilMgr.LENGTH_LONG);
					}
					else
					{
						saveCarInfo(2,check_task_price.getText().toString().trim().equals("")?"0":check_task_price.getText().toString().trim(), String.valueOf(chengjiao), check_task_reson.getText().toString().trim());
						
					}
				}
				else
				{
					saveCarInfo(2,check_task_price.getText().toString().trim().equals("")?"0":check_task_price.getText().toString().trim(), String.valueOf(chengjiao), check_task_reson.getText().toString().trim());
					
				}
				
			}
		});
	}

	
	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	
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
					//600代表数据填写不完整
					if(Retcode.equals("600"))
					{
						//填写不完整的序号
						String NullFields = ((SoapObject)backSoapObject.getProperty(0)).getProperty("NullFields").toString();
						Intent intent = new Intent();
						intent.setClass(context, ShouXuDengJiActivity2.class);
						intent.putExtra("NullFields", NullFields);
						startActivity(intent);
						
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
    
    
    
    
    /**
	 * 读取该型号的相关信息，初始化控件
	 */
	private void initUIDataFromServer()
	{
		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "QueryResourceCount");
		soapParameter.addProperty("VIN", new AppPreference(context).getVIN());
		
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
						String Price = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Price").toString();
						String Count = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Count").toString();
						
						check_task_preview3_txt1.setText("￥"+Price);
						check_task_preview3_txt2.setText(Count+"次");
						
						
					}
					else
					{						
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"获取该车型价格失败", 
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
						SoapAction.QueryResourceCount , 
						"QueryResourceCount",
						soapParameter,
						myhandler,
						true,
						false
				);
	} 

}
