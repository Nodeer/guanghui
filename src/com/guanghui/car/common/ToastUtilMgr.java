package com.guanghui.car.common;

import android.R;
import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 自定义Toast工具类
 * @author 张云
 *
 */
public class ToastUtilMgr {
 private static Toast toast = null;   
    public static int LENGTH_LONG = Toast.LENGTH_LONG;   
    public static int LENGTH_SHORT = Toast.LENGTH_SHORT;
 
    /**  
     * 普通文本消息提示，在顶部显示
     * @param context   context对象
     * @param text      文本信息
     * @param duration  显示时间长短   ToastUtil.LENGTH_LONG | ToastUtil.LENGTH_SHORT
     */   
    public static void TextToast(Context context,CharSequence text,int duration){
    	
    	if(context!=null)
    	{
    		 //创建一个Toast提示消息
            toast = Toast.makeText(context, text, duration);           
            //设置Toast提示消息在屏幕上的位置    
            toast.setGravity(Gravity.BOTTOM, 0, 40);
            //显示消息    
            toast.show();  
    	}
    }   
       
    /**  
     * 带图片消息提示  
     * @param context          context对象
     * @param ImageResourceId  图片资源文件引用  int类型
     * @param text             文本对象
     * @param duration  	      显示时间长短   ToastUtil.LENGTH_LONG | ToastUtil.LENGTH_SHORT
     */   
    public static void ImageToast(Context context,int ImageResourceId,CharSequence text,int duration){   
    	if(context!=null)
    	{
	        //创建一个Toast提示消息    
	        toast = new Toast(context);   
	        //创建一个文本对象
	        TextView mTextView = new TextView(context);  
	        mTextView.setText(text);        
	        //创建一个ImageView    
	        ImageView img = new ImageView(context);   
	        img.setImageResource(ImageResourceId);   
	        //创建一个LineLayout容器    
	        LinearLayout ll = new LinearLayout(context);   
	        //toast_frame就是系统的toast的背景图片 
	        ll.setBackgroundResource(R.drawable.toast_frame);
	        //向LinearLayout中添加ImageView和Toast原有的View    
	        ll.addView(img);   
	        ll.addView(mTextView);   
	        //将LineLayout容器设置为toast的View    
	        toast.setView(ll);  
	        //设置Toast提示消息在屏幕上的位置    
	        toast.setGravity(Gravity.BOTTOM, 0, 40);
	        //设置Toast的停留时间
	        toast.setDuration(duration);
	        //显示消息    
	        toast.show();   
    	}
    }
}
