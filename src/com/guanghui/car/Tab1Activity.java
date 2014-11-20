

package com.guanghui.car ;


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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import com.guanghui.car.adapter.MytaskAdapter;
import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;
import com.guanghui.car.ui.DateTimePickDialogUtil;
import com.guanghui.car.ui.datepicker.DateTimeDialog;

/**
 * 我的任务分页界面
 * 
 * @author 田大魁
 * */
public class Tab1Activity extends Activity
{

    private static final String tag = "MyTaskActivity" ;

    private Context mContext = Tab1Activity.this ;

    private ListView listView = null ;

    private Button buildButton ;

    private MyHandler myhandler ;

    private SoapMgr mgr ;

    private SoapObject backSoapObject ;
    
    private Button activity_mytasks_btn_shuaxin;
    
    private Button tab1_search_btn;
    private EditText edt_startdate;
    private EditText edt_enddate;
    private RadioButton tab1_radio1;
    private RadioButton tab1_radio2;
    private RadioButton tab1_radio3;
    private RadioButton tab1_radio4;
    private RadioButton tab1_radio5;
    private String StartTime;
    private String EndTime;
    private String Status;
    
    private MyBroadcastReciver re ;
    
    @ Override
    protected void onCreate(Bundle savedInstanceState)
    {

        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_tab1) ;
        // 创建标题栏和分页栏
        //new BuildTab(mContext) ;

        init() ;
        
        re = new MyBroadcastReciver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.guanghui.car.CancelDetection");
        this.registerReceiver(re, intentFilter);

    }

    private void init()
    {

    	tab1_search_btn = ( Button ) findViewById(R.id.tab1_search_btn) ;
    	edt_startdate = ( EditText ) findViewById(R.id.edt_startdate) ;
    	edt_enddate = ( EditText ) findViewById(R.id.edt_enddate) ;
    	tab1_radio1 = ( RadioButton ) findViewById(R.id.tab1_radio1) ;
    	tab1_radio2 = ( RadioButton ) findViewById(R.id.tab1_radio2) ;
    	tab1_radio3 = ( RadioButton ) findViewById(R.id.tab1_radio3) ;
    	tab1_radio4 = ( RadioButton ) findViewById(R.id.tab1_radio4) ;
    	tab1_radio5 = ( RadioButton ) findViewById(R.id.tab1_radio5) ;
        buildButton = ( Button ) findViewById(R.id.buildtask_btn) ;
        buildButton.setOnClickListener(new View.OnClickListener()
        {

            @ Override
            public void onClick(View v)
            {

                Intent intent = new Intent(mContext, BuildTaskActivity.class) ;
                startActivityForResult(intent, 100) ;
            }
        }) ;
        listView = ( ListView ) findViewById(R.id.task_listView) ;
        getDataInfo() ;
        
        
        activity_mytasks_btn_shuaxin = (Button)this.findViewById(R.id.activity_mytasks_btn_shuaxin);
        activity_mytasks_btn_shuaxin.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				getDataInfo() ;				
			}
		});
        
        
        tab1_search_btn.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				getDataInfo() ;
			}
		});
        
        
        
        edt_startdate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(Tab1Activity.this,edt_startdate,false) ;
				//new DateTimeDialog(Tab1Activity.this,edt_startdate) ; 
			}
		});
        edt_enddate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(Tab1Activity.this,edt_enddate,false) ;
				
				//new DateTimeDialog(Tab1Activity.this,edt_enddate) ;
			}
		});
        
    }
    
    

    @ Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {    	
    	
    	getDataInfo() ;
//        if (requestCode == 100 && resultCode == 200)
//        {
//            Log.d(tag, "从新建任务界面返回") ;
//            
//            
//        }
        super.onActivityResult(requestCode, resultCode, data) ;
    }
    
    
    private void checkSearch()
    {
    	StartTime = edt_startdate.getText().toString().trim();
    	EndTime = edt_enddate.getText().toString().trim();
    	if(tab1_radio1.isChecked())
    	{
    		//Status = "0,1,2";
    		Status = "";
    	}
    	if(tab1_radio2.isChecked())
    	{
    		Status = "0";
    	}
    	if(tab1_radio3.isChecked())
    	{
    		Status = "1";
    	}
    	if(tab1_radio4.isChecked())
    	{
    		Status = "2";
    	}
    	if(tab1_radio5.isChecked())
    	{
    		Status = "4";
    	}
    }
    
    

    /**
     * 获取该用户的任务信息
     */
    private void getDataInfo()
    {
    	checkSearch();
        final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetResources") ;
        soapParameter.addProperty("userID", new AppPreference(mContext).getLoginUserID()) ;
        soapParameter.addProperty("Status", Status) ;
        soapParameter.addProperty("Region", "") ;
        soapParameter.addProperty("Shop", "") ;
        soapParameter.addProperty("Owner", "") ;
        soapParameter.addProperty("StartTime", StartTime) ;
        soapParameter.addProperty("EndTime", EndTime) ;
        
        
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
                        Log.d(tag, "item count:" + Items.getPropertyCount()) ;
                        
                        

                    	//new AppPreference(mContext).setTotalSize(Items.getPropertyCount()+"");
                        
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
                            
                            String VIN= (( SoapObject ) Items.getProperty(i)).getProperty("VIN").toString() ;
                            String FinishTime= (( SoapObject ) Items.getProperty(i)).getProperty("FinishTime").toString() ;
                            FinishTime = FinishTime.replace("T", "  ") ;                            
                            if(FinishTime.indexOf(".")!=-1)
                            {
                            	FinishTime = FinishTime.substring(0,FinishTime.indexOf("."));
                            }                            
                            
                            
                            String Result = (( SoapObject ) Items.getProperty(i)).getProperty("Result").toString() ;

                            HashMap < String , String > clubInfo = new HashMap < String , String >() ;

                            clubInfo.put("flag", "1") ;
                            
                            clubInfo.put(MytaskAdapter.TASKID, Id) ;
                            clubInfo.put(MytaskAdapter.USERNAME, CustomerName.equals("anyType{}")?"-":CustomerName) ;
                            clubInfo.put(MytaskAdapter.TELEPHONE, CustomerMobile.equals("anyType{}")?"-":CustomerMobile) ;
                            clubInfo.put(MytaskAdapter.AUTOTYPE, CarCatalog.equals("anyType{}")?"-":CarCatalog) ;
                            clubInfo.put(MytaskAdapter.AUTOPLATE, CarLicense.equals("anyType{}")?"-":CarLicense) ;
                            clubInfo.put(MytaskAdapter.OUTLET, ShopName.equals("anyType{}")?"-":ShopName) ;
                            clubInfo.put(MytaskAdapter.AREA, RegionName.equals("anyType{}")?"-":RegionName) ;
                            clubInfo.put(MytaskAdapter.MEETTIME, AppointmentTime.equals("anyType{}")?"-":AppointmentTime) ;
                            clubInfo.put(MytaskAdapter.VIN, VIN) ;
                            clubInfo.put(MytaskAdapter.STATE, Status) ;
                            clubInfo.put(MytaskAdapter.USERID, UserID) ;
                            //3代表已结束的任务
                            if(!Status.equals("3"))
                            {
                            	clubInfo.put(MytaskAdapter.FINISHTIME, "-") ;
                            }
                            else
                            {
                            	clubInfo.put(MytaskAdapter.FINISHTIME, FinishTime.equals("anyType{}")?"-":FinishTime) ;
                            }
                            
                            
                            ListInfo.add(clubInfo) ;
                        }
                                               
                        
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
