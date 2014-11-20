package com.guanghui.car.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public  class DateTime {
	static SimpleDateFormat sDateFormatdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat sDateFormatdate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	static Date nowtime;
	
    Calendar cal = Calendar.getInstance(); 
    
    public static String getNowDate()
    {
    	nowtime = new Date(System.currentTimeMillis()); 
    	String now= sDateFormatdate.format(nowtime);
    	return now;
    }

    
    public static String getNowDate2()
    {
    	nowtime = new Date(System.currentTimeMillis()); 
    	String now= sDateFormatdate2.format(nowtime);
    	return now;
    }

}
