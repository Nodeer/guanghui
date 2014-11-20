package com.guanghui.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.guanghui.car.adapter.CarPriceListAdapter;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;

/**
 * 二手车价格列表
 * @author zhangyun
 *
 */
public class CarPriceListActivity extends Activity{

	private Button car_reply_return;
	private ListView car_price_list_listview;
	private List<HashMap> data;

	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	private Context context = this;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.car_price_list);
		new BuildTab(context,1) ;
		
		car_reply_return = (Button)this.findViewById(R.id.car_reply_return);
		car_price_list_listview = (ListView)this.findViewById(R.id.car_price_list_listview);
		
		
		car_reply_return.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
		
		
		GetCarPrice();
		
		
	}
	
	
	
	
	/**
	 * 获取当地最低价
	 */
	private void GetCarPrice()
	{
		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetCarPrice");
		soapParameter.addProperty("UserID", new AppPreference(context).getLoginUserID());
		soapParameter.addProperty("ResourceID", new AppPreference(context).getTaskId());
		//soapParameter.addProperty("ResourceID", 97);
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
						
						data = new ArrayList<HashMap>();
						SoapObject Items = (( SoapObject ) (( SoapObject ) backSoapObject.getProperty(0)).getProperty("Items")) ;
						
						for ( int i = 0 ; i < Items.getPropertyCount() ; i++ )
	                    {
							// 获得信息
                            String ModelText = (( SoapObject ) Items.getProperty(i)).getProperty("ModelText").toString() ;
                            String GuidePrice = (( SoapObject ) Items.getProperty(i)).getProperty("GuidePrice").toString() ;
                            String Price = (( SoapObject ) Items.getProperty(i)).getProperty("Price").toString() ;
                            
                            HashMap map = new HashMap();
                            map.put("ModelText", ModelText);
                            map.put("GuidePrice", GuidePrice);
                            map.put("Price", Price);
                            
                            data.add(map);
                            
						}
						
						
						CarPriceListAdapter adapter = new CarPriceListAdapter(context,data);
						
						car_price_list_listview.setAdapter(adapter);
					}
					else
					{						
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"获取价格失败", 
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
						SoapAction.GetCarPrice , 
						"GetCarPrice",
						soapParameter,
						myhandler,
						true,
						false
				);
	}

	

}
