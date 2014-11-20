package com.guanghui.car.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * 保存程序级数据
 * 
 * @author zhangyun
 *
 */
public class AppPreference {

	private SharedPreferences prefs;
	/**
	 * 构造方法
	 * @param context   Context对象
	 */
	public AppPreference(Context context)
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(context);	
	}
	private String LoginUserID = "LoginUserID";
	/**
	 * 获取登录用户的ID号
	 */
	public String getLoginUserID() {		
		return prefs.getString(LoginUserID, " ");
	}
	/**
	 * 设置登录用户的ID号
	 */
	public void setLoginUserID(String strUserID) {
		Editor editor = prefs.edit();     
		editor.putString(LoginUserID,strUserID);
		editor.commit();
	}	
	private String LoginUserName = "LoginUserName";
	/**
	 * 获取登录用户的名字
	 */
	public String getLoginUserName() {		
		return prefs.getString(LoginUserName, " ");
	}
	/**
	 * 设置登录用户的名字
	 */
	public void setLoginUserName(String name) {
		Editor editor = prefs.edit();     
		editor.putString(LoginUserName,name);
		editor.commit();
	}	
	private String tag_unreceivedCount = "state_unreceivedCount";	
	/**
	 * 获取待检测任务条数
	 * */
	public String getCount_unreceived(){
	    return prefs.getString(tag_unreceivedCount, "0");
	}
	/**
	 * 设置待检测任务条数
	 * */
	public void setCount_unreceived(String unreceivedcount){
	    Editor editor = prefs.edit();     
        editor.putString(tag_unreceivedCount,unreceivedcount);
        editor.commit();
	}	
	private String tag_checkingCount = "state_checkingCount";
	/**
	 * 获取检测中任务条数
	 * */
	public String getCount_checkingCount(){
	    return prefs.getString(tag_checkingCount, "0");
	}
	/**
	 * 设置检测中任务条数
	 * */
	public void setCount_checking(String checkingCount){
	    Editor editor = prefs.edit();     
        editor.putString(tag_checkingCount,checkingCount);
        editor.commit();
	}
	private String task_ID="task_id";
	/**
	 * 获取当前任务TaskID
	 * */
	public String getTaskId(){
	    return prefs.getString(task_ID, "0");
	}
	/**
	 * 设置当前任务TaskID
	 * */
	public void setTaskId(String task_id){
	    Editor editor = prefs.edit();     
        editor.putString(task_ID,task_id);
        editor.commit();
	}
	
	private String KeyCofiguration="KeyCofiguration";
	/**
	 * 获取关键配置信息
	 * */
	public String getKeyCofiguration(){
	    return prefs.getString(KeyCofiguration, "");
	}
	/**
	 * 设置关键配置信息
	 * */
	public void setKeyCofiguration(String info){
	    Editor editor = prefs.edit();     
        editor.putString(KeyCofiguration,info);
        editor.commit();
	}
	private String SuoDingState="SuoDingState";
	/**
	 * 获取锁定信息   1锁定  0未锁定
	 * */
	public String getSuoDingState(){
	    return prefs.getString(SuoDingState, "0");
	}
	/**
	 * 设置锁定信息   1锁定  0未锁定
	 * */
	public void setSuoDingState(String State){
	    Editor editor = prefs.edit();     
        editor.putString(SuoDingState,State);
        editor.commit();
	}
	private String RegionName="RegionName";
	/**
	 * 获取当前用户所属的区域
	 * */
	public String getRegionName(){
	    return prefs.getString(RegionName, "");
	}
	/**
	 * 设置当前用户所属的区域
	 * */
	public void setRegionName(String name){
	    Editor editor = prefs.edit();     
        editor.putString(RegionName,name);
        editor.commit();
	}
	private String RegionID="RegionID";
	/**
	 * 获取当前用户所属的区域ID
	 * */
	public String getRegionID(){
	    return prefs.getString(RegionID, "");
	}
	/**
	 * 设置当前用户所属的区域ID
	 * */
	public void setRegionID(String id){
	    Editor editor = prefs.edit();     
        editor.putString(RegionID,id);
        editor.commit();
	}
	private String ShopName="ShopName";
	/**
	 * 获取当前用户所属的门店
	 * */
	public String getShopName(){
	    return prefs.getString(ShopName, "");
	}	
	/**
	 * 设置当前用户所属的门店
	 * */
	public void setShopName(String name){
	    Editor editor = prefs.edit();     
        editor.putString(ShopName,name);
        editor.commit();
	}	
	private String ShopID="ShopID";
	/**
	 * 获取当前用户所属的门店ID
	 * */
	public String getShopID(){
	    return prefs.getString(ShopID, "");
	}	
	/**
	 * 设置当前用户所属的门店ID
	 * */
	public void setShopID(String id){
	    Editor editor = prefs.edit();     
        editor.putString(ShopID,id);
        editor.commit();
	}
	
	private String VIN="VIN";
	/**
	 * 获取当前用户的VIN
	 * */
	public String getVIN(){
	    return prefs.getString(VIN, "");
	}	
	/**
	 * 设置当前用户的VIN
	 * */
	public void setVIN(String vin){
	    Editor editor = prefs.edit();     
        editor.putString(VIN,vin);
        editor.commit();
	}
	
	private String TotalSize="TotalSize";
	/**
	 * 获取当前用户的任务总数
	 * */
	public String getTotalSize(){
	    return prefs.getString(TotalSize, "0");
	}	
	/**
	 * 设置当前用户的任务总数
	 * */
	public void setTotalSize(String size){
	    Editor editor = prefs.edit();     
        editor.putString(TotalSize,size);
        editor.commit();
	}
	
	
	private String daiJieShou="daiJieShou";
	/**
	 * 获取待接收任务总数
	 * */
	public String getDaiJieShou(){
	    return prefs.getString(daiJieShou, "0");
	}	
	/**
	 * 设置待接收任务总数
	 * */
	public void setDaiJieShou(String num){
	    Editor editor = prefs.edit();     
        editor.putString(daiJieShou,num);
        editor.commit();
	}
	
	
	
	private String HistoryResourceID="HistoryResourceID";
	/**
	 * 获取手续登记后的历史任务ID
	 * 获取不到则为0
	 * */
	public String getHistoryResourceID(){
	    return prefs.getString(HistoryResourceID, "0");
	}	
	/**
	 * 设置手续登记后的历史任务ID
	 * */
	public void setHistoryResourceID(String ID){
	    Editor editor = prefs.edit();     
        editor.putString(HistoryResourceID,ID);
        editor.commit();
	}
}
