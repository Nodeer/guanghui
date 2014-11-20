package com.guanghui.car.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.guanghui.car.broadCast.HearbeatBroadCast;

/**
 * 定时发送心跳包，每隔20秒发送一次，用来检测用户是否在线
 * @author cool_yunyun
 *
 */
public class HeartbeatService extends Service{

	private int time = 20000;
	private AlarmManager am;
	private PendingIntent pi;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		am = (AlarmManager)getSystemService(ALARM_SERVICE);  
		pi = PendingIntent.getBroadcast(this, 0, new Intent(this, HearbeatBroadCast.class), Intent.FLAG_ACTIVITY_NEW_TASK);  
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
        
        long now = System.currentTimeMillis();  
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, now, time, pi);
		
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		//关闭定时器
		if(am!=null)
		{
			if(pi!=null)
			{
				am.cancel(pi);
				am = null;
				pi = null;
			}
		}		
	}

}
