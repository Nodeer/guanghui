package com.guanghui.car;

import com.guanghui.car.service.HeartbeatService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 用来退出整个程序的辅助界面，不显示出来
 * @author zhangyun
 *
 */
public class ExitAcitivity extends Activity{

	private Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.exit_activity);
		
		//关闭服务，心跳服务
		Intent intent = new Intent();
		intent.setClass(context, HeartbeatService.class);
		stopService(intent);
		finish();
	}

	

}
