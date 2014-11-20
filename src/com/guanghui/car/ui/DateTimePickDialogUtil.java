package com.guanghui.car.ui;

import java.lang.reflect.Field;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.guanghui.car.R;

/**
 * @version 1.0
 */
public class DateTimePickDialogUtil {
	private Activity activity;
	private EditText inputDate;
	private boolean showtime = false;
	private int type;

	
	/**
	 * 显示日期控件
	 * @param activity
	 * @param inputDate
	 * @param type    1  yyyy-mm-dd   2 yyyy-mm
	 */
	public DateTimePickDialogUtil(Activity activity,EditText inputDate,int type) {
		this.activity = activity;
		this.inputDate = inputDate;
		this.showtime = false;
		this.type = type;
		dateTimePicKDialog();
	
	}

	
	public DateTimePickDialogUtil(Activity activity,EditText inputDate,boolean showtime) {
		this.activity = activity;
		this.inputDate = inputDate;
		this.showtime = showtime;
		dateTimePicKDialog();
	
	}

		    

	/**
	 * 弹出日期时间选择框方法
	 * 
	 * @param inputDate
	 *            :为需要设置的日期时间文本编辑框
	 * @return
	 */
	public void dateTimePicKDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity); 
        View view = View.inflate(activity, R.layout.date_time_dialog, null); 
        final TextView date_time_dialog_txt2 = (TextView) view.findViewById(R.id.date_time_dialog_txt2); 
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker); 
        
        final EditText date_time_edt = (EditText) view.findViewById(R.id.date_time_edt); 
        
        
//        Field[] fields = DatePicker.class.getDeclaredFields();  
//      //获取DatePicker中的属性  
//      for(Field field : fields) {  
//          field.setAccessible(true);  
//          if(field.getType().getSimpleName().equals("NumberPicker")) {  
//              try {  
//                  v_month2 = (View)field.get(datePicker2);  
//                  v_month3 = (View)field.get(datePicker3);  
//                  v_month4 = (View)field.get(datePicker4);  
//              } catch (Exception e) {  
//                  Log.e(TAG, e.getMessage());  
//              }   
//          }  
//      } 
//
//        
//      //改变Month的宽度  
//      if(v_month2 != null) {  
//          v_month2.measure(0, 0);  
//          v_month2.getLayoutParams().width = (int) (v_month2.getMeasuredWidth() * 1.6f);  
//      } 
//        
        
        
      try {  
          Class<?> myDatePicker = null;  
          myDatePicker = Class.forName("android.widget.DatePicker");  
          Field my = myDatePicker.getDeclaredField("mMonthSpinner");  
          my.setAccessible(true);  
          NumberPicker np = (NumberPicker)my.get(datePicker);  
          //np.setEnabled(false);  
          np.measure(0, 0);  
          np.getLayoutParams().width = (int) (np.getMeasuredWidth() * 1.3f);
      } catch (Exception e) {  
          e.printStackTrace();  
      }
        
        
        
        
        
        
        
        
        final TimePicker timePicker = (android.widget.TimePicker) view.findViewById(R.id.time_picker); 
        builder.setView(view); 
        Calendar cal = Calendar.getInstance(); 
        cal.setTimeInMillis(System.currentTimeMillis()); 
        
        if(type == 1)
        {
        	//yyyy-mm-dd        	
        	datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
        }
        
        if(type == 2)
        {
        	//yyyy-mm
        	datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
        	((ViewGroup)((ViewGroup)datePicker.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
        }
        timePicker.setIs24HourView(true); 
        timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY)); 
        timePicker.setCurrentMinute(cal.get(Calendar.MINUTE)); 
                
        if(showtime)
        {
        	builder.setTitle("选取时间"); 
        	timePicker.setVisibility(View.VISIBLE);
        }
        else
        {
        	builder.setTitle("选取日期"); 
        	date_time_dialog_txt2.setVisibility(View.GONE);
        	timePicker.setVisibility(View.GONE);
        }
        
            builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {
                @Override 
                public void onClick(DialogInterface dialog, int which) { 
                	
                	date_time_edt.requestFocus();
                	
                	
                    StringBuffer sb; 
//                    sb.append(String.format("%d-%02d-%02d",  
//                            datePicker.getYear(),
//                            datePicker.getMonth() + 1,
//                            datePicker.getDayOfMonth())); 
                    
                    if(type == 2)
                    {
                    	sb = new StringBuffer(); 
                    	sb.append(datePicker.getYear()+"-"+(datePicker.getMonth() + 1)); 
                    	inputDate.setText(sb); 
                        dialog.cancel(); 
                        return;
                    }
                    if(type == 1)
                    {
                    	sb = new StringBuffer(); 
                    	sb.append(datePicker.getYear()+"-"+(datePicker.getMonth() + 1)+"-"+datePicker.getDayOfMonth()); 
                    	inputDate.setText(sb); 
                        dialog.cancel(); 
                        return;
                    }
                    
                    if(showtime)
                    {
                    	sb = new StringBuffer(); 
                    	sb.append(datePicker.getYear()+"-"+(datePicker.getMonth() + 1)+"-"+datePicker.getDayOfMonth()); 
	                    sb.append(" "); 
	                    sb.append(timePicker.getCurrentHour()) 
	                    .append(":").append(timePicker.getCurrentMinute()); 
	                    inputDate.setText(sb); 
	                    dialog.cancel(); 
	                    return;
                    }
                    else
                    {
                    	sb = new StringBuffer(); 
                    	sb.append(datePicker.getYear()+"-"+(datePicker.getMonth() + 1)+"-"+datePicker.getDayOfMonth()); 
                    	inputDate.setText(sb); 
                        dialog.cancel(); 
                        return;
                    }
                    
                } 
            });
            builder.setNegativeButton("取  消", new DialogInterface.OnClickListener() {
                @Override 
                public void onClick(DialogInterface dialog, int which) {                    
                    inputDate.setText(""); 
                    dialog.cancel(); 
                } 
            });
        Dialog dialog = builder.create();
        dialog.show();
	}
}