package com.guanghui.car.broadCast;

import org.ksoap2.serialization.SoapObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.guanghui.car.R;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;

/**
 * 用户接到广播后发送心跳的类
 * @author cool_yunyun
 *
 */
public class HearbeatBroadCast extends BroadcastReceiver{

	
	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	
	
	private NotificationManager m_NotificationManager; 
	private Intent m_Intent; 
	private PendingIntent m_PendingIntent; 
	// 声明Notification对象 
	private Notification m_Notification; 
	
	@Override
	public void onReceive(Context context, Intent arg1) {
		
		
		sendXinTiao(context);
	}
	
	
	private void sendXinTiao(final Context context)
	{
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "Heartbeat");
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
						Log.e("HeartbeatService.java", "心跳发送成功");	
						
						String TotalSize = ((SoapObject)backSoapObject.getProperty(0)).getProperty("TotalSize").toString();
						//待接收数量
						String WaitAcceptNum = ((SoapObject)backSoapObject.getProperty(0)).getProperty("WaitAcceptNum").toString();
						//待检测数量
						String WaitDetectionNum = ((SoapObject)backSoapObject.getProperty(0)).getProperty("WaitDetectionNum").toString();
						//正在检测数量
						String DetectionNum = ((SoapObject)backSoapObject.getProperty(0)).getProperty("DetectionNum").toString();
						
						//正在检测数量
						new AppPreference(context).setCount_checking(DetectionNum);
						//待检测数量
						new AppPreference(context).setCount_unreceived(WaitDetectionNum);
						//待接收数量
						new AppPreference(context).setDaiJieShou(WaitAcceptNum);
						//获取的任务大于本地的任务总数
						//if(Integer.parseInt(TotalSize) > Integer.parseInt(new AppPreference(context).getTotalSize()))
						
						if(Integer.parseInt(WaitAcceptNum) > 0)
						{
							m_NotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE); 
							m_Intent = new Intent(); 
							m_PendingIntent = PendingIntent.getActivity(context, 0,m_Intent, 0);
							m_Notification = new Notification(); 
							m_Notification.icon = R.drawable.ic_launcher;
							m_Notification.flags = Notification.FLAG_AUTO_CANCEL;
							m_Notification.setLatestEventInfo(context, "温馨提示","您有新的任务，请注意接收！", m_PendingIntent); 
							m_Notification.sound= Uri.parse("android.resource://" + context.getPackageName() + "/" +R.raw.newtask); 
							m_NotificationManager.notify(0, m_Notification); 
							
							
							Toast.makeText(context, "温馨提示，您有新的任务，请注意接收！", Toast.LENGTH_LONG).show();
							
							
							new AppPreference(context).setTotalSize(TotalSize);
						}
						
					}
					else
					{
						Log.e("HeartbeatService.java", "心跳发送失败");
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
						SoapAction.Heartbeat , 
						"Heartbeat",
						soapParameter,
						myhandler,
						false,
						false
				);
	}

}
