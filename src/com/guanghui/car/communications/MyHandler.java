package com.guanghui.car.communications;

import android.os.Handler;
import android.os.Message;

/**
 * 自定义的Handler对象
 * 用来简化操作
 * @author 张云
 *
 */
public abstract class MyHandler extends Handler{
	/**
	 * 操作成功
	 */
	public static final int MSG_SUCCESS = 1;
	/**
	 * 操作失败
	 */
	public static final int MSG_FAILED = 0;
	
	@Override
	public void handleMessage(Message msg) {
		switch(msg.what)
		{
			case MSG_SUCCESS:
				success(msg);
				break;
			case MSG_FAILED:
				failed(msg);
				break;
		}
		super.handleMessage(msg);
	}
	/**
	 * 操作成功
	 * @param msg  Message对象
	 */
	public abstract void success(Message msg);
	/**
	 * 操作失败
	 * @param msg   Message对象
	 */
	public abstract void failed(Message msg);
}
