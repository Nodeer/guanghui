package com.guanghui.car.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.guanghui.car.R;
import com.guanghui.car.staticdata.CarConfigData;

/**
 * 关键配置的数据集
 * @author zhangyun
 *
 */
public class CarKeyCofigurationAdapter extends BaseAdapter{

	private Context context;
	private List<List<HashMap>> listCarKeyConfiguration;
	private int firstIndex;
	private int SecondIndex;
	public CarKeyCofigurationAdapter(Context context,List<List<HashMap>> listCarKeyConfiguration,int firstIndex,int SecondIndex)
	{
		this.context = context;
		this.listCarKeyConfiguration = listCarKeyConfiguration;
		this.firstIndex = firstIndex;
		this.SecondIndex = SecondIndex;
	}
	
	@Override
	public int getCount() {
		return listCarKeyConfiguration.get(0).size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null)
	    {
		   convertView = LayoutInflater.from(context).inflate(R.layout.car_dengji4_item, null);
	    }
		TextView txt = (TextView)convertView.findViewById(R.id.car_dengji4_item_name);
		ImageView img1 = (ImageView)convertView.findViewById(R.id.car_dengji4_item_img1);
		ImageView img2 = (ImageView)convertView.findViewById(R.id.car_dengji4_item_img2);
	
		if(firstIndex!=-1)
		{
			String id = listCarKeyConfiguration.get(firstIndex).get(position).get("ConfigID").toString();
			String value = listCarKeyConfiguration.get(firstIndex).get(position).get("ConfigValue").toString();
			String info = new CarConfigData().getConfigNameByIndex(position);
			String txtv = info.substring(info.indexOf(":")+1, info.length());
			txt.setText(txtv.equals("")?"-":txtv);
			
			if(value.equals("0"))
			{
				//无
				img1.setBackgroundResource(R.drawable.car_dengji4_3);
			}
			if(value.equals("1"))
			{
				//有
				img1.setBackgroundResource(R.drawable.car_dengji4_2);
			}
			if(value.equals("2"))
			{
				//选装
				img1.setBackgroundResource(R.drawable.car_dengji4_1);
			}
		}
		else
		{
			img1.setVisibility(View.GONE);
		}
		
		if(SecondIndex!=-1)
		{
			String id = listCarKeyConfiguration.get(SecondIndex).get(position).get("ConfigID").toString();
			String value = listCarKeyConfiguration.get(SecondIndex).get(position).get("ConfigValue").toString();
			if(value.equals("0"))
			{
				//无
				img2.setBackgroundResource(R.drawable.car_dengji4_3);
			}
			if(value.equals("1"))
			{
				//有
				img2.setBackgroundResource(R.drawable.car_dengji4_2);
			}
			if(value.equals("2"))
			{
				//选装
				img2.setBackgroundResource(R.drawable.car_dengji4_1);
			}
		}
		else
		{
			img2.setVisibility(View.GONE);
		}
		
				
		
		
		return convertView;
	}

	

}
