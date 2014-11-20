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

public class BaoJiaListAdapter extends BaseAdapter{
	private Context mContext;
	private List<HashMap> list;
	public BaoJiaListAdapter(Context context,List<HashMap> list){
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
		convertView = layoutInflater.inflate(R.layout.baojia_list_item, null);
		TextView RegionName = (TextView) convertView.findViewById(R.id.baojia_list_item_txt1);
		TextView ShopName = (TextView) convertView.findViewById(R.id.baojia_list_item_txt2);
		TextView UserName = (TextView) convertView.findViewById(R.id.baojia_list_item_txt3);
		TextView FinishTimeText = (TextView) convertView.findViewById(R.id.baojia_list_item_txt4);
		TextView Price = (TextView) convertView.findViewById(R.id.baojia_list_item_txt5);
		
		RegionName.setText(list.get(position).get("RegionName").toString().equals("anyType{}")?"":list.get(position).get("RegionName").toString());
		ShopName.setText(list.get(position).get("ShopName").toString().equals("anyType{}")?"":list.get(position).get("ShopName").toString());
		UserName.setText(list.get(position).get("UserName").toString().equals("anyType{}")?"":list.get(position).get("UserName").toString());
		FinishTimeText.setText(list.get(position).get("FinishTimeText").toString().equals("anyType{}")?"":list.get(position).get("FinishTimeText").toString());
		Price.setText(list.get(position).get("Price").toString().equals("anyType{}")?"":list.get(position).get("Price").toString());
		
		return convertView;
	}
}
