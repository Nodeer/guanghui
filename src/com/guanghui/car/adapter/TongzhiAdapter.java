package com.guanghui.car.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.guanghui.car.R;

/**
 * 通知的数据集
 * @author zhangyun
 *
 */
public class TongzhiAdapter extends BaseAdapter{
	
	private Context context;
	private List<HashMap> data;
	
	public TongzhiAdapter(Context context,List<HashMap> data)
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
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
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
	        convertView = LayoutInflater.from(context).inflate(R.layout.tongzhi_item, null);
        }
		TextView t1 = (TextView)convertView.findViewById(R.id.tongzhi_item_info);    
		TextView t2 = (TextView)convertView.findViewById(R.id.tongzhi_item_date);    
	    
		t1.setText(data.get(position).get("Title").toString());
		t2.setText(data.get(position).get("Time").toString());
		convertView.setTag(data.get(position).get("Id").toString());
		
		return convertView;
	}

}
