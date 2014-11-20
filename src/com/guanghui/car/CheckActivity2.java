package com.guanghui.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.guanghui.car.adapter.MytaskAdapter;
import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;

public class CheckActivity2 extends Activity {

	private Context mContext = CheckActivity2.this ;

	private ListView listView = null ;
	
	private MyHandler myhandler ;

    private SoapMgr mgr ;

    private SoapObject backSoapObject ;
    
    private MyBroadcastReciver re ;
    
    private Button activity_mytasks_btn_shuaxin;
   
    private Button activity_mytasks_btn_back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check2);
		
		new BuildTab(mContext,1) ;
		
		 activity_mytasks_btn_shuaxin = (Button)this.findViewById(R.id.activity_mytasks_btn_shuaxin);
	        activity_mytasks_btn_shuaxin.setOnClickListener(new View.OnClickListener() {			
				@Override
				public void onClick(View v) {
					getDataInfo() ;				
				}
			});
	        
	     activity_mytasks_btn_back = (Button)this.findViewById(R.id.activity_mytasks_btn_back);
	     activity_mytasks_btn_back.setOnClickListener(new View.OnClickListener() {			
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.setClass(mContext, ActivityMain.class);
					startActivity(intent);
				}
			});
	        
	        
	    listView = ( ListView ) findViewById(R.id.task_listView) ;
	        
		
		re = new MyBroadcastReciver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.guanghui.car.CancelDetection");
        this.registerReceiver(re, intentFilter);
		
        
        getDataInfo();
	}
	
	
    /**
     * 获取该用户的任务信息
     */
    private void getDataInfo()
    {
        final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetResources") ;
        soapParameter.addProperty("userID", new AppPreference(mContext).getLoginUserID()) ;
        //102代表    状态1和2  已接受  未检测   和已接受  正在检测
        soapParameter.addProperty("Status", "102") ;
        soapParameter.addProperty("Region", "") ;
        soapParameter.addProperty("Shop", "") ;
        soapParameter.addProperty("Owner", "") ;
        soapParameter.addProperty("StartTime", "") ;
        soapParameter.addProperty("EndTime", "") ;
        
        
        myhandler = new MyHandler()
        {

            @ Override
            public void success(Message msg)
            {

                backSoapObject = mgr.getServiceBackSoapObject() ;
                if (backSoapObject != null)
                {
                    String Retcode = (( SoapObject ) backSoapObject.getProperty(0)).getProperty("Retcode").toString() ;
                    // 0成功，1失败
                    if (Retcode.equals("0"))
                    {

                        List < HashMap < String , String >> ListInfo = new ArrayList < HashMap < String , String >>() ;
                        SoapObject Items = (( SoapObject ) (( SoapObject ) backSoapObject.getProperty(0)).getProperty("Items")) ;

                        //待检测任务条数
                        int count_unreceivedState = 0;
                        //进行中检测任务条数
                        int count_checkingState = 0;
                        for ( int i = 0 ; i < Items.getPropertyCount() ; i++ )
                        {
                            // 获得任务信息
                            String Id = (( SoapObject ) Items.getProperty(i)).getProperty("Id").toString() ;
                            String Time = (( SoapObject ) Items.getProperty(i)).getProperty("Time").toString();
                            Time = Time.replace("T", "  ") ;
                            if(Time.indexOf(".")!=-1)
                            {
                            	Time = Time.substring(0,Time.indexOf("."));
                            }
                            String RegionName = (( SoapObject ) Items.getProperty(i)).getProperty("RegionName").toString() ;
                            String ShopName = (( SoapObject ) Items.getProperty(i)).getProperty("ShopName").toString() ;
                            String CustomerName = (( SoapObject ) Items.getProperty(i)).getProperty("CustomerName").toString() ;
                            String CustomerMobile = (( SoapObject ) Items.getProperty(i)).getProperty("CustomerMobile").toString() ;
                            String CarCatalog = (( SoapObject ) Items.getProperty(i)).getProperty("CarCatalog").toString() ;
                            String CarLicense = (( SoapObject ) Items.getProperty(i)).getProperty("CarLicense").toString() ;
                            String AppointmentTime = (( SoapObject ) Items.getProperty(i)).getProperty("AppointmentTime").toString() ;
                            AppointmentTime = AppointmentTime.replace("T", "  ") ;
                            if(AppointmentTime.indexOf(".")!=-1)
                            {
                            	AppointmentTime = AppointmentTime.substring(0,AppointmentTime.indexOf("."));
                            }
                            String Comments = (( SoapObject ) Items.getProperty(i)).getProperty("Comments").toString() ;
                            String Status = (( SoapObject ) Items.getProperty(i)).getProperty("Status").toString() ;
                            String UserID= (( SoapObject ) Items.getProperty(i)).getProperty("UserID").toString() ;
                            String FinishTime= (( SoapObject ) Items.getProperty(i)).getProperty("FinishTime").toString() ;
                            FinishTime = FinishTime.replace("T", "  ") ;                            
                            if(FinishTime.indexOf(".")!=-1)
                            {
                            	FinishTime = FinishTime.substring(0,FinishTime.indexOf("."));
                            }                            
                            //统计待检测任务条数和检测中任务条数
                            if(Status.equals("0")){
                                count_unreceivedState ++;
                            }else if(Status.equals("2")){
                                count_checkingState ++;
                            }
                            
                            String Result = (( SoapObject ) Items.getProperty(i)).getProperty("Result").toString() ;

                            HashMap < String , String > clubInfo = new HashMap < String , String >() ;

                            
                            clubInfo.put("flag", "2") ;
                            
                            
                            clubInfo.put(MytaskAdapter.TASKID, Id) ;
                            clubInfo.put(MytaskAdapter.USERNAME, CustomerName.equals("anyType{}")?"-":CustomerName) ;
                            clubInfo.put(MytaskAdapter.TELEPHONE, CustomerMobile.equals("anyType{}")?"-":CustomerMobile) ;
                            clubInfo.put(MytaskAdapter.AUTOTYPE, CarCatalog.equals("anyType{}")?"-":CarCatalog) ;
                            clubInfo.put(MytaskAdapter.AUTOPLATE, CarLicense.equals("anyType{}")?"-":CarLicense) ;
                            clubInfo.put(MytaskAdapter.OUTLET, ShopName.equals("anyType{}")?"-":ShopName) ;
                            clubInfo.put(MytaskAdapter.AREA, RegionName.equals("anyType{}")?"-":RegionName) ;
                            clubInfo.put(MytaskAdapter.MEETTIME, AppointmentTime.equals("anyType{}")?"-":AppointmentTime) ;
                            clubInfo.put(MytaskAdapter.STATE, Status) ;
                            clubInfo.put(MytaskAdapter.USERID, UserID) ;
                            clubInfo.put(MytaskAdapter.FINISHTIME, FinishTime.equals("anyType{}")?"-":FinishTime) ;
                            
                            ListInfo.add(clubInfo) ;
                        }
                        AppPreference appPreference = new AppPreference(mContext);
                        appPreference.setCount_checking(String.valueOf(count_checkingState));
                        appPreference.setCount_unreceived(String.valueOf(count_unreceivedState));
                        
                        
                        
                        
                        listView.setAdapter(new MytaskAdapter(mContext, ListInfo)) ;

                    }
                    else
                    {
                        ToastUtilMgr.TextToast(mContext, getString(R.string.server_error1), ToastUtilMgr.LENGTH_LONG) ;
                    }
                }
            }

            @ Override
            public void failed(Message msg)
            {

            }
        } ;
        mgr = new SoapMgr(mContext, null, null, SoapAction.GetResources, "GetResources", soapParameter, myhandler, true, false) ;
    }
    
	
	private class MyBroadcastReciver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			 if(intent.getAction().equals("com.guanghui.car.CancelDetection")) {				
				 getDataInfo();
			 }			
		}
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if(re!=null)
		{
			unregisterReceiver(re);
		}
		
	}
    
    
}
