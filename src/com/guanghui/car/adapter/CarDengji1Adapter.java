package com.guanghui.car.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.guanghui.car.R;
import com.guanghui.car.communications.SoapAction;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 车辆登记界面1的数据集
 * @author zhangyun
 *
 */
public class CarDengji1Adapter extends BaseAdapter{

	private Context context;
	private List<HashMap> data;
	public CarDengji1Adapter(Context context,List<HashMap> data)
	{
		this.context = context;
		this.data = data;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		
	    if(convertView == null)
        {
	        convertView = LayoutInflater.from(context).inflate(R.layout.car_dengji1_item, null);
        }
	    
  	    TextView car_dengji1_item_char = (TextView)convertView.findViewById(R.id.car_dengji1_item_char);
  		TextView car_dengji1_item_name = (TextView)convertView.findViewById(R.id.car_dengji1_item_name);
  		car_dengji1_item_char.setText(data.get(position).get("FirstLetter").toString());
  		car_dengji1_item_name.setText(data.get(position).get("Name").toString());
  		ImageView img = (ImageView)convertView.findViewById(R.id.car_dengji1_item_img);
        ImageLoader.getInstance().displayImage(SoapAction.host+data.get(position).get("Logo").toString(), img);
        
	    convertView.setTag(data.get(position).get("Id").toString()+ "," +data.get(position).get("Name").toString() ) ;
  		
  		return convertView;		
	}
		
	
	
}
