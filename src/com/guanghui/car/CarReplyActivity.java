package com.guanghui.car;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;

public class CarReplyActivity extends Activity {

	private Button returnBtn;
	
	private RadioButton car_reply_car_card_radiobutton;
	private RadioButton car_reply_car_model_radiobutton;
	private EditText car_reply_now_card_title_edittext; 
	private EditText car_reply_sheng_edittext; 
	private EditText car_reply_city_eidtext; 
	private EditText car_reply_car_brand_edittext;
	private EditText car_reply_car_factory_edittext; 
	private EditText car_reply_car_model_edittext;
	private EditText car_reply_car_xilie_edittext;
	private EditText car_reply_car_xinghao_edittext;
	private Button saveButton;
	private Button commitButton;
	private Context context = this;
	
	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_reply);
		returnBtn = (Button) findViewById(R.id.car_reply_return);
		new BuildTab(context,1) ;
		car_reply_car_card_radiobutton = (RadioButton) findViewById(R.id.car_reply_car_card_radiobutton);
		car_reply_car_model_radiobutton = (RadioButton) findViewById(R.id.car_reply_car_model_radiobutton);
		car_reply_now_card_title_edittext = (EditText) findViewById(R.id.car_reply_now_card_title_edittext);
		car_reply_sheng_edittext = (EditText) findViewById(R.id.car_reply_sheng_edittext);
		car_reply_city_eidtext = (EditText) findViewById(R.id.car_reply_city_eidtext);
		car_reply_car_brand_edittext = (EditText) findViewById(R.id.car_reply_car_brand_edittext);
		car_reply_car_factory_edittext = (EditText) findViewById(R.id.car_reply_car_factory_edittext);
		car_reply_car_model_edittext = (EditText) findViewById(R.id.car_reply_car_model_edittext);
		car_reply_car_xilie_edittext = (EditText) findViewById(R.id.car_reply_car_xilie_edittext);
		car_reply_car_xinghao_edittext = (EditText) findViewById(R.id.car_reply_car_xinghao_edittext);
		initBottom();
		returnBtn.setOnClickListener(mListener);
		
		
		car_reply_now_card_title_edittext.setNextFocusDownId(R.id.car_reply_sheng_edittext);
		car_reply_sheng_edittext.setNextFocusDownId(R.id.car_reply_city_eidtext);
		car_reply_city_eidtext.setNextFocusDownId(R.id.car_reply_car_brand_edittext);
		car_reply_car_brand_edittext.setNextFocusDownId(R.id.car_reply_car_factory_edittext);
		car_reply_car_factory_edittext.setNextFocusDownId(R.id.car_reply_car_model_edittext);
		car_reply_car_model_edittext.setNextFocusDownId(R.id.car_reply_car_xilie_edittext);
		car_reply_car_xilie_edittext.setNextFocusDownId(R.id.car_reply_car_xinghao_edittext);
		
		
		
		
	}
	
	private OnClickListener mListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.car_reply_return:
				finish();
				break;
			default:
				break;
			}
		}
	};
	private void initBottom()
	{
		saveButton = (Button) findViewById(R.id.stop_save);
		commitButton = (Button) findViewById(R.id.comfirm_communit);
		saveButton.setVisibility(View.GONE);
		commitButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int Problem = 1;
				if(car_reply_car_card_radiobutton.isChecked())
				{
					Problem = 1;
				}
				if(car_reply_car_model_radiobutton.isChecked())
				{
					Problem = 2;
				}
				String chepai = car_reply_now_card_title_edittext.getText().toString().trim();
				String sheng = car_reply_sheng_edittext.getText().toString().trim();
				String city = car_reply_city_eidtext.getText().toString().trim();
				String Brand = car_reply_car_brand_edittext.getText().toString().trim();
				String Manufacturer = car_reply_car_factory_edittext.getText().toString().trim();
				String Catalog = car_reply_car_model_edittext.getText().toString().trim();
				String Series = car_reply_car_xilie_edittext.getText().toString().trim();
				String Model = car_reply_car_xinghao_edittext.getText().toString().trim();
				
				if(!chepai.equals("")  && !sheng.equals("")  && !city.equals("") && !Brand.equals("") && !Manufacturer.equals("") && !Catalog.equals("") && !Series.equals("") && !Model.equals(""))
				{
					SendData(Problem+"",chepai,sheng,city,Brand,Manufacturer,Catalog,Series,Model);
				}
				else
				{
					final MineAlert alert = new MineAlert(context);
					alert.createAlertOneButton(false,"请输入完整信息", 
							new View.OnClickListener() {								
								@Override
								public void onClick(View v) {
									alert.dimiss();
								}
							});
				}
				
				
				
			}
		});
	}
	
	/**
     * 发送无可选项信息
     */
    private void SendData(String Problem,String chepai,String sheng,String city,String Brand,String Manufacturer,String Catalog,String Series,String Model)
    {		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "NewNoOptions");
		soapParameter.addProperty("UserID", new AppPreference(context).getLoginUserID());
		
		soapParameter.addProperty("Problem", Problem);		
		soapParameter.addProperty("LicensePrefix", chepai);
		soapParameter.addProperty("Province", sheng);
		soapParameter.addProperty("City", city);
		soapParameter.addProperty("Brand", Brand);
		soapParameter.addProperty("Manufacturer", Manufacturer);
		soapParameter.addProperty("Catalog", Catalog);
		soapParameter.addProperty("Series", Series);
		soapParameter.addProperty("Model", Model);
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
						ToastUtilMgr.TextToast(context, "数据成功提交", ToastUtilMgr.LENGTH_LONG);
						finish();
						
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
						SoapAction.NewNoOptions , 
						"NewNoOptions",
						soapParameter,
						myhandler,
						true,
						false
				);
    }
	
	
	
}
