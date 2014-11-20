package com.guanghui.car.staticdata;

import java.util.HashMap;

/**
 * 汽车关键配置数据
 * @author zhangyun
 *
 */
public class CarConfigData {
	
	public static String Brand;
	
	
	public static String Catalog;
	

	/**
	 * 汽车关键配置
	 * 保存成程序级变量，方便以后通过id号来索引对应的配置字符串
	 */
	public static String KeyCofiguration;
	
	
	/**
	 * 汽车选装配置
	 * 保存成程序级变量，方便以后通过id号来索引对应的配置字符串
	 */
	public static String OptionCofiguration;
	
	
	/**
	 * 汽车改装配置
	 * 保存成程序级变量，方便以后通过id号来索引对应的配置字符串
	 */
	public static String ModCofiguration;
	
	
	
	private HashMap map;
	private String[] configs;
	
	
	private HashMap map2;
	private String[] configs2;
	
	
	private HashMap map3;
	private String[] configs3;
	
	public CarConfigData()
	{
		if(!KeyCofiguration.trim().equals("anyType{}"))
		{
			configs = KeyCofiguration.split(",");
			map = new HashMap();
			for(String config: configs)
			{
				String id = config.substring(0, config.indexOf(":"));
				String value = config.substring(config.indexOf(":")+1,config.length());
				map.put(id, value);
			}
		}
		
		if(!OptionCofiguration.trim().equals("anyType{}"))
		{
			configs2 = OptionCofiguration.split(",");
			map2 = new HashMap();
			for(String config: configs2)
			{
				String id = config.substring(0, config.indexOf(":"));
				String value = config.substring(config.indexOf(":")+1,config.length());
				map2.put(id, value);
			}
		}
		
		if(!ModCofiguration.trim().equals("anyType{}"))
		{
			configs3 = ModCofiguration.split(",");
			map3 = new HashMap();
			for(String config: configs3)
			{
				String id = config.substring(0, config.indexOf(":"));
				String value = config.substring(config.indexOf(":")+1,config.length());
				map3.put(id, value);
			}
		}
	}
	
	/**
	 * 根据索引号 从0开始    检索对应的关键配置文本
	 * @param index
	 * @return
	 */
	public String getConfigNameByIndex(int index)
	{
		String configName;
		try
		{
			configName = configs[index];
			
		}
		catch(Exception ex)
		{
			configName="";
		}
		return configName;
	}
	
	/**
	 * 根据ID号获得对应的关键配置名字
	 * @param id   id号
	 * @return     配置名字
	 */
	public String getConfigNameById(String id)
	{
		if(id!=null)
		{
			if(map!=null)
			{
				if(map.get(id)!=null)
				{
					return map.get(id).toString();
				}
				else
				{
					return "";
				}
			}
			else
			{
				return "";
			}
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * 根据ID号获得对应的选装配置名字
	 * @param id   id号
	 * @return     配置名字
	 */
	public String getOptionCofigurationById(String id)
	{
		if(id!=null)
		{
			if(map2!=null)
			{
				if(map2.get(id)!=null)
				{
					return map2.get(id).toString();
				}
				else
				{
					return "";
				}
			}
			else
			{
				return "";
			}
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * 根据ID号获得对应的改装配置名字
	 * @param id   id号
	 * @return     配置名字
	 */
	public String getModCofigurationById(String id)
	{
		if(id!=null)
		{
			if(map3!=null)
			{
				if(map3.get(id)!=null)
				{
					return map3.get(id).toString();
				}
				else
				{
					return "";
				}
			}
			else
			{
				return "";
			}
		}
		else
		{
			return "";
		}
			
	}
	
	
	
	
	
}
