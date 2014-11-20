package com.guanghui.car.staticdata;

/**
 * 精密检测数据的静态变量
 * 精密检测数据量包含图片等信息，数据量过大
 * 如果用Intent传递则有限制，40k
 * 超过会报错，所以用个静态变量存储，界面消失掉的时候情况
 * @author zhangyun
 *
 */
public class JingMiCheckData {

	public static String waiguan;
	
	public static String neishi;
	
	public static String gujia;
	
	public static String zhuangzhi;
	
	public static void clear()
	{
		waiguan = "";
		neishi = "";
		gujia = "";
		zhuangzhi = "";
	}
	
}
