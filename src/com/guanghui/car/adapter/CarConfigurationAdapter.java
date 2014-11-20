package com.guanghui.car.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.guanghui.car.R;
import com.guanghui.car.staticdata.CarConfigData;

public class CarConfigurationAdapter extends BaseAdapter{

	private Context context;
	private List<HashMap> data;
	private int type = 1;
	private String oc="";
	
	//1选装配置   2改装配置
	public CarConfigurationAdapter(Context context,List<HashMap> data,int type)
	{
		oc="";
		this.context = context;
		this.data = data;
		this.type = type;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		
	    if(convertView ==null)
        {
	        convertView = LayoutInflater.from(context).inflate(R.layout.car_configuration_item, null);
        }
	    CheckBox chkBox = (CheckBox)convertView.findViewById(R.id.car_configuration_item_checkbox);    
	    
	    chkBox.setTag(data.get(position).get("id").toString());	  
	    
	    
	    chkBox.setOnCheckedChangeListener(new OnCheckedChangeListener()
	    {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if(arg1)
				{					
					data.get(position).put("value", "1");					
					if(oc.indexOf("," + arg0.getTag().toString())== -1)
					{
						oc = oc + "," + arg0.getTag().toString();	
					}							
				}
				else
				{					
					data.get(position).put("value", "0");
					if(oc.indexOf("," + arg0.getTag().toString())!= -1)
					{
						oc = oc.replace(","+arg0.getTag().toString(), "");
					}
					
				}
			}
	    	
	    });
	    
	    
	    
	    if(type == 1)
	    {
	    	//选装配置
	    	chkBox.setText(new  CarConfigData().getOptionCofigurationById(chkBox.getTag().toString()));
	    }
	    else
	    {
	    	//2改装配置
	    	chkBox.setText(new  CarConfigData().getModCofigurationById(chkBox.getTag().toString()));
	    }	   
	    
	    if(data.get(position).get("value").toString().trim().equals("1"))
	    {
	    	chkBox.setChecked(true);
	    }
	    else
	    {
	    	chkBox.setChecked(false);
	    }
	    
		return convertView;
	}
	
	/**
	 * 返回点击的配置选项数据
	 * @return  配置选项数据
	 */
	public String getOc()
	{
		return oc;
	}
	
}

