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

import com.guanghui.car.adapter.BaoJiaListAdapter;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;

/**
 * 门店报价列表
 * @author zhangyun
 *
 */
public class BaoJiaListActivity extends Activity{

	private Button baojia_list_return;
	private ListView baojia_list_listview;
	private List<HashMap> data;

	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	private Context context = this;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.baojia_list);
		new BuildTab(context,1) ;
		
		baojia_list_return = (Button)this.findViewById(R.id.baojia_list_return);
		baojia_list_listview = (ListView)this.findViewById(R.id.baojia_list_listview);
		
		
		baojia_list_return.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
		
		
		Baojia();
		
		
	}
	
	
	
	
	/**
	 * 获取门店报价
	 */
	private void Baojia()
	{
		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "QueryQuoteShop");
		soapParameter.addProperty("VIN", new AppPreference(context).getVIN());
		
		
		//soapParameter.addProperty("VIN", "lsv134b9801");
		
		
		
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
                            String RegionName = (( SoapObject ) Items.getProperty(i)).getProperty("RegionName").toString() ;
                            String ShopName = (( SoapObject ) Items.getProperty(i)).getProperty("ShopName").toString() ;
                            String UserName = (( SoapObject ) Items.getProperty(i)).getProperty("UserName").toString() ;
                            String FinishTimeText = (( SoapObject ) Items.getProperty(i)).getProperty("FinishTimeText").toString() ;
                            String Price = (( SoapObject ) Items.getProperty(i)).getProperty("Price").toString() ;
                            
                            HashMap map = new HashMap();
                            map.put("RegionName", RegionName);
                            map.put("ShopName", ShopName);
                            map.put("UserName", UserName);
                            map.put("FinishTimeText", FinishTimeText);
                            map.put("Price", Price);
                            
                            data.add(map);
                            
						}
						
						
						BaoJiaListAdapter adapter = new BaoJiaListAdapter(context,data);
						
						baojia_list_listview.setAdapter(adapter);
					}
					else
					{						
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"获取门店报价失败", 
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
						SoapAction.QueryQuoteShop , 
						"QueryQuoteShop",
						soapParameter,
						myhandler,
						true,
						false
				);
	}

	

}
