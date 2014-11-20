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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.guanghui.car.adapter.TongzhiAdapter;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;

/**
 * 通知新闻界面
 * @author zhangyun
 *
 */
public class Tab2Activity extends Activity{

	private Context context = this;
	//通知列表
	private ListView activity_tab2_listview1;
	//新闻列表
	private ListView activity_tab2_listview2;
	private Button tongzhi_more;
	private Button xinwen_more;
	private List<HashMap> ListNews;
	private List<HashMap> ListMessages;
	
	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_tab2);	
		
		
		tongzhi_more = (Button)this.findViewById(R.id.tongzhi_more);
		xinwen_more = (Button)this.findViewById(R.id.xinwen_more);
		activity_tab2_listview1 = (ListView)this.findViewById(R.id.activity_tab2_listview1);
		activity_tab2_listview2 = (ListView)this.findViewById(R.id.activity_tab2_listview2);
		
		tongzhi_more.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				GetMessages(0);
			}
		});
		
		xinwen_more.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				GetNewsList();
			}
		});
		
		
		activity_tab2_listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				intent.setClass(context, TongZhiInfoActivity.class);
				intent.putExtra("id", arg1.getTag().toString());
				intent.putExtra("Type", "1");
				startActivity(intent);
			}
		});
		
		
		activity_tab2_listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				intent.setClass(context, TongZhiInfoActivity.class);
				intent.putExtra("id", arg1.getTag().toString());
				intent.putExtra("Type", "2");
				startActivity(intent);
			}
		});
		
		
		
		GetMessages(1);
	}
	
	
	/**
     * 获取新闻信息
     */
    private void GetNewsList()
    {		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetNewsList");
		soapParameter.addProperty("Page", 1);
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
						ListNews = new ArrayList<HashMap>();	
						SoapObject Items = ((SoapObject) ((SoapObject) backSoapObject.getProperty(0)).getProperty("Items"));
						
						if(Items.getPropertyCount()==0)
						{
							Toast.makeText(context, "新闻信息为空!", Toast.LENGTH_SHORT).show();
						}
						
						
						
						for (int i = 0; i < Items.getPropertyCount(); i++) {							
							String Id = ((SoapObject) Items.getProperty(i))
									.getProperty("Id").toString();
							String Time = ((SoapObject) Items
									.getProperty(i)).getProperty(
									"Time").toString();
							String Title = ((SoapObject) Items.getProperty(i))
									.getProperty("Title").toString();
							String Abstract = ((SoapObject) Items
									.getProperty(i)).getProperty("Abstract")
									.toString();
							String Content = ((SoapObject) Items.getProperty(i))
									.getProperty("Content").toString();
							String Image = ((SoapObject) Items
									.getProperty(i)).getProperty("Image")
									.toString();
							String Comments = ((SoapObject) Items
									.getProperty(i)).getProperty("Comments")
									.toString();
							HashMap Info = new HashMap();
							Info.put("Id", Id);
							Info.put("Time", Time.subSequence(0, Time.indexOf("T")));
							Info.put("Title", Title);
							Info.put("Content", Content);
							Info.put("Image", Image);
							ListNews.add(Info);
						}
							
						TongzhiAdapter adapter2 = new TongzhiAdapter(context,ListNews);		
						activity_tab2_listview2.setAdapter(adapter2);
						
					}
					else
					{	
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"获取新闻信息失败!", 
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
						SoapAction.GetNewsList , 
						"GetNewsList",
						soapParameter,
						myhandler,
						true,
						false
				);
    }
    
    
    /**
     * 获取通知信息
     */
    private void GetMessages(final int flag)
    {		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetMessages");
		soapParameter.addProperty("Page", 1);
		soapParameter.addProperty("UserID", new AppPreference(context).getLoginUserID());
		
		
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
						ListMessages = new ArrayList<HashMap>();	
						SoapObject Items = ((SoapObject) ((SoapObject) backSoapObject.getProperty(0)).getProperty("Items"));
						
						if(Items.getPropertyCount()==0)
						{
							Toast.makeText(context, "通知信息为空!", Toast.LENGTH_SHORT).show();
						}
						
						for (int i = 0; i < Items.getPropertyCount(); i++) {							
							String Id = ((SoapObject) Items.getProperty(i))
									.getProperty("Id").toString();
							String Time = ((SoapObject) Items
									.getProperty(i)).getProperty(
									"Time").toString();
							String Title = ((SoapObject) Items.getProperty(i))
									.getProperty("Title").toString();
							String Content = ((SoapObject) Items.getProperty(i))
									.getProperty("Content").toString();
							String Comments = ((SoapObject) Items
									.getProperty(i)).getProperty("Comments")
									.toString();
							HashMap Info = new HashMap();
							Info.put("Id", Id);
							Info.put("Time", Time.subSequence(0, Time.indexOf("T")));
							Info.put("Title", Title);
							Info.put("Content", Content);
							ListMessages.add(Info);
						}
							
						TongzhiAdapter adapter1 = new TongzhiAdapter(context,ListMessages);		
						activity_tab2_listview1.setAdapter(adapter1);
						
						if(flag == 1)
						{
							GetNewsList();
						}
						
						
					}
					else
					{	
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"获取通知信息失败!", 
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
						SoapAction.GetMessages , 
						"GetMessages",
						soapParameter,
						myhandler,
						true,
						false
				);
    }
	
}
