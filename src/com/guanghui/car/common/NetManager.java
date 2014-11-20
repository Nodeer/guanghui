package com.guanghui.car.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * 网络连接管理类
 * @author 张云
 *
 */
public class NetManager {
	
	/**
	 * 判断是否连接上网络
	 * @param context   context对象
	 * @return          true 连上   false 未连上
	 */
	public static boolean isNETConnect(Context context) {		
		try {
			// 获取手机所有连接管理对象（包括对wifi,net等连接的管理）
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// 获取网络连接管理的对象
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// 判断当前网络是否已经连接
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			Log.e("isNETConnect ERROR:", e.getMessage());
		}
		return false;
	}
}