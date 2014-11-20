package com.guanghui.car.application;

import android.app.Application;

import com.guanghui.car.R;
import com.guanghui.car.config.AppConfig;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * 给程序级的变量初始化
 * @author zhangyun
 *
 */
public class MyApplication extends Application{
	
	
	
	
	private static MyApplication sAppInstance;
	
	
	/**
	 * 下载图片的选项
	 */
	public static DisplayImageOptions options;

	@Override
	public void onCreate() {
		
		super.onCreate();
		
		sAppInstance = this;
		
		
		//初始化ImageLoader				
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
		.threadPriority(Thread.NORM_PRIORITY - 2)
		.denyCacheImageMultipleSizesInMemory()
		.discCacheFileNameGenerator(new Md5FileNameGenerator())
		.tasksProcessingOrder(QueueProcessingType.LIFO)
		.writeDebugLogs()		
		.build();		
		ImageLoader.getInstance().init(config);
		
		
		
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.ic_stub)			// 设置图片下载期间显示的图片
		.showImageForEmptyUri(0)	// 设置图片Uri为空或是错误的时候显示的图片
		.showImageOnFail(0)		// 设置图片加载或解码过程中发生错误显示的图片
		.cacheInMemory(AppConfig.cacheInMemory)		// 设置下载的图片是否缓存在内存中
		.cacheOnDisc(AppConfig.cacheOnDisc)			// 设置下载的图片是否缓存在SD卡中
		.displayer(new RoundedBitmapDisplayer(10))	// 设置成圆角图片
		.build();									// 创建配置过得DisplayImageOption对象
		 
	}

	
	public static MyApplication getInstance() {
		return sAppInstance;
	}
	
	
	
	
}
