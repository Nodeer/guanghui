package com.guanghui.car;


import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.guanghui.car.R.drawable;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.service.HeartbeatService;

/**
 * 主界面
 * @author zhangyun
 *
 */
public class ActivityMain extends TabActivity {

	private Context mContext = this;
	private TabHost tabHost;
	//选项按钮
	private TextView txtview1, txtview2,txtview3,txtview4;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();		
		initTab();
		
		new BuildTab(mContext,0) ;
		
		
		tabHost.setCurrentTabByTag("first");
		txtview1.setBackgroundResource(drawable.tab_style_title_on) ;
		txtview2.setBackgroundResource(drawable.tab_style_title_bg) ;
		txtview3.setBackgroundResource(drawable.tab_style_title_bg) ;
		txtview4.setBackgroundResource(drawable.tab_style_title_bg) ;
		
	}

	
	private void init() {
		txtview1 = (TextView) findViewById(R.id.tab1_label_text);
		txtview2 = (TextView) findViewById(R.id.tab2_label_text);		
		txtview3 = (TextView) findViewById(R.id.tab3_label_text);
		txtview4 = (TextView) findViewById(R.id.tab4_label_text);
		//我的任务
		txtview1.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				tabHost.setCurrentTabByTag("first");
				txtview1.setBackgroundResource(drawable.tab_style_title_on) ;
				txtview2.setBackgroundResource(drawable.tab_style_title_bg) ;
				txtview3.setBackgroundResource(drawable.tab_style_title_bg) ;
				txtview4.setBackgroundResource(drawable.tab_style_title_bg) ;
				
				
			}
		});
		//新闻动态
		txtview2.setOnClickListener(new View.OnClickListener() {					
			@Override
			public void onClick(View v) {
                tabHost.setCurrentTabByTag("second");
                txtview1.setBackgroundResource(drawable.tab_style_title_bg) ;
				txtview2.setBackgroundResource(drawable.tab_style_title_on) ;
				txtview3.setBackgroundResource(drawable.tab_style_title_bg) ;
				txtview4.setBackgroundResource(drawable.tab_style_title_bg) ;  
				
			}
		});
		//我的设置
		txtview3.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				tabHost.setCurrentTabByTag("third");
				txtview1.setBackgroundResource(drawable.tab_style_title_bg) ;
				txtview2.setBackgroundResource(drawable.tab_style_title_bg) ;
				txtview3.setBackgroundResource(drawable.tab_style_title_on) ;
				txtview4.setBackgroundResource(drawable.tab_style_title_bg) ;
				
			}
		});
		
		
	}
	
	private void initTab(){
		tabHost=getTabHost();
		tabHost.addTab(tabHost.newTabSpec("first").setIndicator("first").setContent(
				new Intent(this, Tab1Activity.class)));
		tabHost.addTab(tabHost.newTabSpec("second").setIndicator("second").setContent(
				new Intent(this, Tab2Activity.class)));		
		tabHost.addTab(tabHost.newTabSpec("third").setIndicator("third").setContent(
				new Intent(this, Tab3Activity.class)));

	}
	
	
	
	
	
	
	
	

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {		
		 if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 &&event.getAction() == KeyEvent.ACTION_DOWN) {
	        	
	        	final MineAlert alert = new MineAlert(mContext);
				alert.createAlertTwoButton(false, "确定退出程序吗？",
						new View.OnClickListener() {						
							@Override
							public void onClick(View v) {
								alert.dimiss();
								
								Intent intent = new Intent(); 
								intent.setClass(mContext, LoginActivity.class); 
								intent.putExtra("exit", "exit");
								intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置 
								startActivity(intent);
								
								
								
								//关闭服务，心跳服务
								Intent serviceIntent = new Intent();
								serviceIntent.setClass(mContext, HeartbeatService.class);
								stopService(serviceIntent);
								finish();
								
							}
						},
						new View.OnClickListener() {						
							@Override
							public void onClick(View v) {
								alert.dimiss();
							}
						});
	        	return true;
	        }       
	        else 
	        {
	        	return super.dispatchKeyEvent(event);  
	        }
	}


	@Override
	protected void onChildTitleChanged(Activity childActivity,
			CharSequence title) {
		// TODO Auto-generated method stub
		super.onChildTitleChanged(childActivity, title);
		new BuildTab(mContext,0) ;
	}
	

}
