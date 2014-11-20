

package com.guanghui.car ;


import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.guanghui.car.common.DateTime;
import com.guanghui.car.config.AppPreference;
import com.guanghui.car.service.HeartbeatService;

public class BuildTab
{
    private Context mContext;
    // 标题模块
    TextView title_time_text ,title_username_text, title_check_num_text , title_checking_num_text ,title_check_num_jieshou;

    Button title_lock_but , title_logout_but ;

    // 分页模块
    TextView txtview1 , txtview2 , txtview3 ,txtview4 ,tab_label_text;
    
    int index;

    // 定时刷新时间
    Timer timer = new Timer() ;

    Handler handler = new Handler()
    {

        public void handleMessage(Message msg)
        {

            if (msg.what == 1)
            {
                if (title_time_text != null)
                {
                	
                    title_time_text.setText(DateTime.getNowDate()) ;
                    //待检测数量
                    title_check_num_text.setText(new AppPreference(mContext).getCount_unreceived()) ;
					//正在检测数量
                    title_checking_num_text.setText(new AppPreference(mContext).getCount_checkingCount());
                    //待接收的任务
                    title_check_num_jieshou.setText(new AppPreference(mContext).getDaiJieShou());
                }
            }
        }
    } ;

    TimerTask timerTask = new TimerTask()
    {

        @ Override
        public void run()
        {

            handler.sendEmptyMessage(1) ;
        }
    } ;

    public BuildTab ( Context mContext ,int index)
    {
    	((Activity)mContext).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.mContext = mContext;
        this.index = index;
        initTitle() ;
        timer.schedule(timerTask, 0, 1000) ;
        
        
    }

    /**
     * 初始化标题和分页
     * */
    private void initTitle()
    {
        AppPreference appPreference = new AppPreference(mContext);
        // 标题模块控件初始化
        title_time_text = ( TextView )((Activity)mContext).findViewById(R.id.title_time_text) ;
        title_check_num_text = ( TextView ) ((Activity)mContext).findViewById(R.id.title_check_num_text) ;
        String unreceivedNum = appPreference.getCount_unreceived();
        title_check_num_text.setText(unreceivedNum);
        title_checking_num_text = ( TextView ) ((Activity)mContext).findViewById(R.id.title_checking_num_text) ;
        String checkingNum = appPreference.getCount_checkingCount();
        title_checking_num_text.setText(checkingNum);
        
        
        
        title_check_num_jieshou = ( TextView ) ((Activity)mContext).findViewById(R.id.title_check_num_jieshou) ;
        String daijieshouNum = appPreference.getDaiJieShou();
        title_check_num_jieshou.setText(daijieshouNum);
        
        
        
        title_lock_but = ( Button ) ((Activity)mContext).findViewById(R.id.title_lock_but) ;
        title_logout_but = ( Button ) ((Activity)mContext).findViewById(R.id.title_logout_but) ;
        title_lock_but.setOnClickListener(clickListener) ;
        title_logout_but.setOnClickListener(clickListener) ;
        
        title_username_text = ( TextView )((Activity)mContext).findViewById(R.id.title_username_text) ;
        title_username_text.setText("当前登录 : "+appPreference.getLoginUserName() );
        
        txtview1 = (TextView) ((Activity)mContext).findViewById(R.id.tab1_label_text);
		txtview2 = (TextView) ((Activity)mContext).findViewById(R.id.tab2_label_text);		
		txtview3 = (TextView) ((Activity)mContext).findViewById(R.id.tab3_label_text);
		txtview4 = (TextView) ((Activity)mContext).findViewById(R.id.tab4_label_text);
		tab_label_text = (TextView) ((Activity)mContext).findViewById(R.id.tab_label_text);
        if(index == 0)
        {
        	tab_label_text.setVisibility(View.GONE);
        	txtview1.setVisibility(View.VISIBLE);
        	txtview2.setVisibility(View.VISIBLE);
        	txtview3.setVisibility(View.VISIBLE);
        	txtview4.setVisibility(View.VISIBLE);        	
        }
        if(index == 1)
        {
        	tab_label_text.setText("我的任务");
        	tab_label_text.setVisibility(View.VISIBLE);
        	txtview1.setVisibility(View.GONE);
        	txtview2.setVisibility(View.GONE);
        	txtview3.setVisibility(View.GONE);
        	txtview4.setVisibility(View.GONE);        	
        }
        if(index == 2)
        {
        	tab_label_text.setText("通知新闻");
        	tab_label_text.setVisibility(View.VISIBLE);
        	txtview1.setVisibility(View.GONE);
        	txtview2.setVisibility(View.GONE);
        	txtview3.setVisibility(View.GONE);
        	txtview4.setVisibility(View.GONE);        	
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener()
    {

        @ Override
        public void onClick(View view)
        {

            switch ( view.getId() )
            {
                case R.id.title_lock_but :
                	//1锁定
                	new AppPreference(mContext).setSuoDingState("1");
                    Intent lockIntent = new Intent(mContext, LoginActivity.class) ;
                    lockIntent.putExtra("state", "1") ;
                    lockIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mContext.startActivity(lockIntent) ;
                    //((Activity)mContext).finish() ;
                    break ;
                case R.id.title_logout_but :
                	//关闭服务，心跳服务
					Intent serviceIntent = new Intent();
					serviceIntent.setClass(mContext, HeartbeatService.class);
					((Activity)mContext).stopService(serviceIntent);
                	//0注销
                    Intent logoutIntent = new Intent(mContext, LoginActivity.class) ;
                    logoutIntent.putExtra("state", "0") ;
                    logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mContext.startActivity(logoutIntent) ;
                    ((Activity)mContext).finish() ;
                    break ;
               
                default :
                    break ;
            }

        }
    } ;
}
