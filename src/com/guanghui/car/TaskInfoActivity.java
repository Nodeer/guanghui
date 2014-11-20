

package com.guanghui.car ;


import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.guanghui.car.adapter.MytaskAdapter;
import com.guanghui.car.adapter.TaskInfoAdapter;
import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;

/**
 * 任务详细界面   继续操作
 * @author 田大魁
 * */
public class TaskInfoActivity extends Activity
{

    private Context mContext = TaskInfoActivity.this ;

    private static final String tag = "TaskInfoActivity" ;

    private ListView listView = null ;

    private String sourceId = null;
    private Button back_btn , confirm_accept_btn ;
    
    private boolean getData = false;
    
    private String taskstate ;

    @ Override
    protected void onCreate(Bundle savedInstanceState)
    {

        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_taskinfo) ;
        init() ;
        new BuildTab(mContext,1) ;
    }

    /**
     * 初始化控件
     * */
    private void init()
    {

        back_btn = ( Button ) findViewById(R.id.activity_mytasks_btn_shuaxin) ;
        confirm_accept_btn = ( Button ) findViewById(R.id.confirm_accept_btn) ;
        back_btn.setOnClickListener(clickListener) ;
        confirm_accept_btn.setOnClickListener(clickListener) ;
        listView = ( ListView ) findViewById(R.id.taskinfo_list) ;
        Intent intent = getIntent() ;
        String taskID = intent.getStringExtra(MytaskAdapter.TASKID) ;
        taskstate = intent.getStringExtra(MytaskAdapter.STATE) ;
        getTaskInfo(taskID) ;
    }

    OnClickListener clickListener = new OnClickListener()
    {

        @ Override
        public void onClick(View view)
        {

            switch ( view.getId() )
            {
                case R.id.activity_mytasks_btn_shuaxin :
                    // 结束当前页面
                    finish() ;
                    break ;
                case R.id.confirm_accept_btn :
                	if(getData)
	                {
                		//0未接受 1已接受未检测 2正在检测 3检测完成
                		if(taskstate.equals("0"))
                		{
                			SetResourceStatus(getIntent().getStringExtra(MytaskAdapter.TASKID),"1"); 
                		}
                		else
                		{
                			Intent intent = new Intent(mContext, CheckActivity.class) ;
    	                    startActivity(intent);
                		}
                	}
                	else
                	{
                		Toast.makeText(mContext, "暂未获得任务数据，无法继续", Toast.LENGTH_SHORT).show();
                	}                	                  
                    break ;
                default :
                    break ;
            }

        }
    } ;

    /**
     * 
     * 请求任务信息
     * */
    private SoapObject taskInfoSoapObj ;

    private SoapMgr taskInfoSoapMgr ;

    private void getTaskInfo(final String taskID)
    {

        taskInfoSoapObj = new SoapObject(SoapAction.nameSpace, SoapAction.GetResource) ;
        taskInfoSoapObj.addProperty("ID", taskID) ;
        MyHandler getShopsHandler = new MyHandler()
        {

            @ Override
            public void success(Message msg)
            {

                SoapObject backSoapObject = taskInfoSoapMgr.getServiceBackSoapObject() ;
                String retcode = (( SoapObject ) backSoapObject.getProperty(0)).getProperty("Retcode").toString() ;
                // 0成功，1失败
                if (retcode.equals("0"))
                {
                	getData = true;
                    SoapObject items = (( SoapObject ) (( SoapObject ) backSoapObject.getProperty(0)).getProperty("Resource")) ;
                    List < String > taskInfoList = new ArrayList < String >() ;

                    String id = items.getProperty("Id").toString() ;
                    sourceId = id;

                                        
                    
                    
                    taskInfoList.add("任务ID："+ "<br>" + taskID ) ;

                    String time = items.getProperty("Time").toString() ;
                    
                    //更改时间格式2012-12-12T12:12为2012-12-12 12:12
                    time = time.replace("T", "  ");
                    if(time.indexOf(".")!=-1)
                    {
                    	time = time.substring(0,time.indexOf("."));
                    }
                    
                    taskInfoList.add("任务登记时间："+ "<br>" + (time.equals("anyType{}")?"-":time)) ;

                    String sourceName = items.getProperty("SourceName").toString() ;
                    taskInfoList.add("任务来源："+ "<br>" + (sourceName.equals("anyType{}")?"-":sourceName)) ;

                    String regionName = items.getProperty("RegionName").toString() ;
                    taskInfoList.add("检测区域："+ "<br>" + (regionName.equals("anyType{}")?"-":regionName)) ;


                    String shopName = items.getProperty("ShopName").toString() ;
                    taskInfoList.add("检测门店："+ "<br>" + (shopName.equals("anyType{}")?"-":shopName)) ;

                    String seller = items.getProperty("ProviderPerson").toString() ;
                    taskInfoList.add("任务提供人："+ "<br>" + (seller.equals("anyType{}")?"-":seller)) ;

                    String customerName = items.getProperty("CustomerName").toString() ;
                    taskInfoList.add("客户姓名："+ "<br>" + (customerName.equals("anyType{}")?"-":customerName)) ;

                    String customerMobile = items.getProperty("CustomerMobile").toString() ;
                    taskInfoList.add("客户联系电话："+ "<br>" + (customerMobile.equals("anyType{}")?"-":customerMobile)) ;

                    String carCatalog = items.getProperty("CarCatalog").toString() ;
                    taskInfoList.add("车型："+ "<br>" + (carCatalog.equals("anyType{}")?"-":carCatalog)) ;

                    String carLicense = items.getProperty("CarLicense").toString() ;
                    taskInfoList.add("牌照："+ "<br>" + (carLicense.equals("anyType{}")?"-":carLicense)) ;

                    String appointmentTime = items.getProperty("AppointmentTime").toString() ;
                    //更改时间格式2012-12-12T12:12为2012-12-12 12:12
                    appointmentTime = appointmentTime.replace("T", "  ");
                    if(appointmentTime.indexOf(".")!=-1)
                    {
                    	appointmentTime = appointmentTime.substring(0,appointmentTime.indexOf("."));
                    }
                    taskInfoList.add("预约检测时间："+ "<br>" + (appointmentTime.equals("anyType{}")?"-":appointmentTime)) ;

                    String appointmentAddress = items.getProperty("AppointmentAddress").toString() ;
                    taskInfoList.add("预约检测地点："+ "<br>" + (appointmentAddress.equals("anyType{}")?"-":appointmentAddress)) ;
                    listView.setAdapter(new TaskInfoAdapter(TaskInfoActivity.this, taskInfoList)) ;
                }
                else
                {
                    ToastUtilMgr.TextToast(mContext, getString(R.string.server_error1), ToastUtilMgr.LENGTH_LONG) ;
                }
            }

            @ Override
            public void failed(Message msg)
            {

                // TODO Auto-generated method stub
            }
        } ;
        taskInfoSoapMgr = new SoapMgr(mContext, null, null, SoapAction.GetResource, SoapAction.GetResource, taskInfoSoapObj, getShopsHandler, true, false) ;
    }
    
    
    private SoapMgr mgr2;
    /**
     * 更改任务状态
     */
    private void SetResourceStatus(String ID,String status)
    {		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "SetResourceStatus");
		soapParameter.addProperty("ID", ID);
		soapParameter.addProperty("status", status);				
		MyHandler myhandler =  new MyHandler()
		{
			@Override
			public void success(Message msg) {
				SoapObject backSoapObject = mgr2.getServiceBackSoapObject();						
				if(backSoapObject!=null)
				{
					String Retcode = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Retcode").toString();
					//0成功，1失败
					if(Retcode.equals("0"))
					{						
						//确认受理	                	
	                	Intent intent = new Intent(mContext, CheckActivity2.class) ;
	                    startActivity(intent);
					}
					else
					{						
						ToastUtilMgr.TextToast(mContext, getString(R.string.service_back_error), ToastUtilMgr.LENGTH_LONG);
					}					
				}
			}
			@Override
			public void failed(Message msg) {				
				
			}
		};
		mgr2 = new SoapMgr(mContext,
						null, 
						null, 
						SoapAction.SetResourceStatus , 
						"SetResourceStatus",
						soapParameter,
						myhandler,
						true,
						false
				);
    }
}
