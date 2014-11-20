package com.guanghui.car.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guanghui.car.R;
import com.guanghui.car.communications.SoapAction;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 车辆登记界面3的数据集
 * @author zhangyun
 *
 */
public class CarDengji3Adapter extends BaseAdapter{

	private Context context;
	private List<HashMap> data;
	public CarDengji3Adapter(Context context,List<HashMap> data)
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
		
		
	    if(convertView ==null)
        {
	        convertView = LayoutInflater.from(context).inflate(R.layout.car_dengji3_item, null);
        }
	    
	   
	    TextView car_dengji2_item_txt = (TextView)convertView.findViewById(R.id.car_dengji2_item_txt);
	    ImageView img1 = (ImageView)convertView.findViewById(R.id.car_dengji2_item_img1);
	    ImageView img2 = (ImageView)convertView.findViewById(R.id.car_dengji2_item_img2);
	    ImageView img3 = (ImageView)convertView.findViewById(R.id.car_dengji2_item_img3);
	    ImageView img4 = (ImageView)convertView.findViewById(R.id.car_dengji2_item_img4);
	    ImageLoader.getInstance().displayImage(SoapAction.host+data.get(position).get("Image1"), img1);
	    ImageLoader.getInstance().displayImage(SoapAction.host+data.get(position).get("Image2"), img2);
	    ImageLoader.getInstance().displayImage(SoapAction.host+data.get(position).get("Image3"), img3);
	    ImageLoader.getInstance().displayImage(SoapAction.host+data.get(position).get("Image4"), img4);
	    
	    car_dengji2_item_txt.setText(data.get(position).get("Name").toString());
	    
	    convertView.setTag(data.get(position).get("Id").toString()+","+data.get(position).get("Name").toString()) ;
		
		return convertView;
	}
		
	
	
}
