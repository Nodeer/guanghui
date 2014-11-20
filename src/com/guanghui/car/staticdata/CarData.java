package com.guanghui.car.staticdata;

import java.util.HashMap;


/**
 * 汽车静态数据表
 * 用来根据id号得到对应的配置字符串
 * @author zhangyun
 *
 */
public class CarData {

	/**
	 * 发动机排量
	 * @param id   id号
	 * @return
	 */
	public static String Emission(String id)
	{
		HashMap map = new HashMap();
		map.put("10", "1");
		map.put("11", "1.1");
		map.put("12", "1.2");
		map.put("13", "1.3");
		map.put("14", "1.4");
		map.put("15", "1.5");
		map.put("16", "1.6");
		map.put("18", "1.8");
		map.put("20", "2");
		map.put("21", "2.1");
		map.put("22", "2.2");
		map.put("23", "2.3");
		map.put("24", "2.4");
		map.put("25", "2.5");
		map.put("26", "2.6");
		map.put("28", "2.8");
		map.put("30", "3");
		map.put("32", "3.2");
		map.put("33", "3.3");
		map.put("34", "3.4");
		map.put("35", "3.5");
		map.put("36", "3.6");
		map.put("38", "3.8");
		map.put("40", "4");
		map.put("42", "4.2");
		map.put("43", "4.3");
		map.put("45", "4.5");
		map.put("46", "4.6");
		map.put("48", "4.8");
		map.put("50", "5");
		map.put("55", "5.5");
		map.put("56", "5.6");
		map.put("58", "5.8");
		map.put("60", "6");
		if(map.get(id) == null)
		{
			return "";
		}
		else
		{
			return map.get(id).toString();
		}
		
	}
	
	/**
	 * 获取变速箱形式
	 * @param id   id号
	 * @return
	 */
	public static String TransType(String id)
	{
		HashMap map = new HashMap();
		map.put("501", "手动 5速");
		map.put("601", "手动 6速");
		map.put("502", "自动 5速");
		map.put("602", "自动 6速");
		map.put("802", "自动 8速");
		map.put("603", "手自一体 6速");
		map.put("803", "手自一体 8速");
		map.put("612", "双离合 自动 6速");
		map.put("712", "双离合 自动 7速");
		map.put("812", "双离合 自动 8速");
		if(map.get(id) == null)
		{
			return "";
		}
		else
		{
			return map.get(id).toString();
		}
	}
	
	/**
	 * 获取车厢箱形式
	 * @param id   id号
	 * @return
	 */
	public static String Carriage(String id)
	{
		HashMap map = new HashMap();
		map.put("1", "3箱轿车");
		map.put("2", "2箱轿车");
		map.put("3", "SUV");
		map.put("4", "MPV");
		map.put("5", "客车");
		map.put("6", "货车");
		map.put("7", "掀背轿车");
		if(map.get(id) == null)
		{
			return "";
		}
		else
		{
			return map.get(id).toString();
		}
	}
	
	/**
	 * 获取驱动形式
	 * @param id   id号
	 * @return
	 */
	public static String Drive(String id)
	{
		HashMap map = new HashMap();
		map.put("201", "2轮驱动 直喷 自然吸气");
		map.put("202", "2轮驱动 直喷 涡轮增压");
		map.put("203", "2轮驱动 直喷 机械增压");
		map.put("401", "4轮驱动 直喷 自然吸气");
		map.put("402", "4轮驱动 直喷 涡轮增压");
		map.put("403", "4轮驱动 直喷 机械增压");
		if(map.get(id) == null)
		{
			return "";
		}
		else
		{
			return map.get(id).toString();
		}
	}
	
	/**
	 * 获取座位形式
	 * @param id   id号
	 * @return
	 */
	public static String SeatNum(String id)
	{
		HashMap map = new HashMap();
		map.put("45", "四门5座");
		map.put("54", "五门4座");
		map.put("55", "五门5座");
		map.put("57", "五门7座");
		map.put("22", "两门2座");
		map.put("24", "两门4座");
		map.put("34", "三门4座");
		map.put("35", "三门5座");
		map.put("11", "11座");
		map.put("19", "19座");
		if(map.get(id) == null)
		{
			return "";
		}
		else
		{
			return map.get(id).toString();
		}
	}
	
	/**
	 * 获取座位形式
	 * @param id   id号
	 * @return
	 */
	public static String Fuel(String id)
	{
		HashMap map = new HashMap();
		map.put("1", "汽油");
		map.put("2", "柴油");
		map.put("3", "油电混合");
		map.put("4", "纯电动");
		if(map.get(id) == null)
		{
			return "";
		}
		else
		{
			return map.get(id).toString();
		}
	}
}
