package com.guanghui.car.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.guanghui.car.R;

public class ShopsAdapter extends BaseAdapter{
	private Context mContext;
	private List<ShopsInfo> list;
	public ShopsAdapter(Context context, List<ShopsInfo> list){
		this.mContext = context;
		this.list = list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater = LayoutInflater.from(mContext);
		convertView = layoutInflater.inflate(R.layout.spinner_item, null);
		if(convertView != null){
			TextView textView = (TextView) convertView.findViewById(R.id.spinnerText);
			textView.setText(list.get(position).getName());
		}
		return convertView;
	}
	
	
	
	
}
