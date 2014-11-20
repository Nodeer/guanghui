package com.guanghui.car;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 通知新闻详细界面
 * @author zhangyun
 *
 */
public class TongZhiInfoActivity extends Activity{

	private Context context = this;
	private TextView activity_tongzhi_info_txt;
	private Button activity_tongzhi_info_btn_back;
	private TextView activity_tongzhi_info_title;
	private TextView activity_tongzhi_info_date;
	private TextView activity_tongzhi_info_info;
	private ImageView activity_tongzhi_info_img;
	
	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	private String Id;
	private String Type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_tongzhi_info);
		new BuildTab(context,2) ;
		
		
		Id = getIntent().getExtras().getString("id");
		Type = getIntent().getExtras().getString("Type");
		
		activity_tongzhi_info_txt = (TextView)this.findViewById(R.id.activity_tongzhi_info_txt);
		activity_tongzhi_info_btn_back = (Button)this.findViewById(R.id.activity_tongzhi_info_btn_back);
		activity_tongzhi_info_title = (TextView)this.findViewById(R.id.activity_tongzhi_info_title);
		activity_tongzhi_info_date = (TextView)this.findViewById(R.id.activity_tongzhi_info_date);
		activity_tongzhi_info_info = (TextView)this.findViewById(R.id.activity_tongzhi_info_info);
		activity_tongzhi_info_img = (ImageView)this.findViewById(R.id.activity_tongzhi_info_img);
		
	
		
		activity_tongzhi_info_btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		//通知
		if(Type.equals("1"))
		{
			GetMessage();
		}
		//新闻
		if(Type.equals("2"))
		{
			GetNews();
		}
	}
	
	/**
     * 获取新闻信息
     */
    private void GetNews()
    {		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetNews");
		soapParameter.addProperty("ID", Id);
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
						new AppPreference(context).setSuoDingState("0"); 
						//获得该新闻信息
						String Id = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("News")).getProperty("Id").toString();
						String Time = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("News")).getProperty("Time").toString();
						String Title = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("News")).getProperty("Title").toString();
						String Abstract = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("News")).getProperty("Abstract").toString();
						String Content = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("News")).getProperty("Content").toString();
						String Image = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("News")).getProperty("Image").toString();
						String Comments = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("News")).getProperty("Comments").toString();
						
						
						if(Type.equals("1"))
						{
							activity_tongzhi_info_img.setVisibility(View.GONE);
							activity_tongzhi_info_txt.setText("通知");
						}
						
						activity_tongzhi_info_img.setVisibility(View.VISIBLE);
						activity_tongzhi_info_txt.setText("新闻动态");
						ImageLoader.getInstance().displayImage(SoapAction.host+Image, activity_tongzhi_info_img);
						
						
						
						activity_tongzhi_info_title.setText(Title);
						activity_tongzhi_info_date.setText(Time.substring(0, Time.indexOf("T")));
						activity_tongzhi_info_info.setText(Html.fromHtml(Content));
												
					}
					else
					{
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"获取新闻失败!", 
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
						SoapAction.GetNews , 
						"GetNews",
						soapParameter,
						myhandler,
						true,
						false
				);
    }
	
	
    /**
     * 获取通知信息
     */
    private void GetMessage()
    {		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetMessage");
		soapParameter.addProperty("ID", Id);			
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
						new AppPreference(context).setSuoDingState("0"); 
						//获得该新闻信息
						String Id = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("Message")).getProperty("Id").toString();
						String Time = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("Message")).getProperty("Time").toString();
						String Title = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("Message")).getProperty("Title").toString();
						String Content = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("Message")).getProperty("Content").toString();
						String Comments = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("Message")).getProperty("Comments").toString();
						String Image = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("Message")).getProperty("Image").toString();
						
						//activity_tongzhi_info_img.setVisibility(View.GONE);
						ImageLoader.getInstance().displayImage(SoapAction.host+Image, activity_tongzhi_info_img);
						
						activity_tongzhi_info_txt.setText("通知");						
						
						activity_tongzhi_info_title.setText(Title);
						activity_tongzhi_info_date.setText(Time.substring(0, Time.indexOf("T")));
						activity_tongzhi_info_info.setText(Html.fromHtml(Content));
												
					}
					else
					{
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"获取通知失败!", 
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
						SoapAction.GetMessage , 
						"GetMessage",
						soapParameter,
						myhandler,
						true,
						false
				);
    }
    
	
}
