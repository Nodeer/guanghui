package com.guanghui.car.common;

import android.os.Handler;
import android.os.HandlerThread;

/**
 * 按两次退出键退出整个程序
 * @author 张云
 *
 */
public class Exit {

	private boolean isExit = false;
	private Runnable task = new Runnable() {
		public void run() {
			isExit = false;
		}
	};

	public void doExitInOneSecond() {
		isExit = true;
		HandlerThread thread = new HandlerThread("doTask");
		thread.start();
		new Handler(thread.getLooper()).postDelayed(task, 1000);
	}

	public boolean isExit() {
		return isExit;
	}

	public void setExit(boolean isExit) {
		this.isExit = isExit;
	}
}
