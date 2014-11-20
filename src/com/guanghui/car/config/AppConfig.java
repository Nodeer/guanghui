package com.guanghui.car.config;

import android.os.Environment;


/**
 * 程序配置类
 * 该类都是静态数据
 * @author zhangyun
 *
 */
public class AppConfig {

	/**
	 * SD卡的缓存文件夹名字
	 */
	public static String CacheDir = Environment.getExternalStorageDirectory() + "/" + "广汇换车宝";
	
	/**
	 * 设置下载的图片是否缓存在内存中
	 */
	public static boolean cacheInMemory = true;
	
	/**
	 * 设置下载的图片是否缓存在SD卡中
	 */
	public static boolean cacheOnDisc = true;
	
	
	/**
	 * 与服务器发生数据交互的连接超时时间
	 */
	public static int soapTimeout = 30000;
	
}
