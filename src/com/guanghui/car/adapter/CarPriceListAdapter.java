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

public class CarPriceListAdapter extends BaseAdapter{
	private Context mContext;
	private List<HashMap> list;
	public CarPriceListAdapter(Context context,List<HashMap> list){
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
		convertView = layoutInflater.inflate(R.layout.car_price_list_item, null);
		TextView ModelText = (TextView) convertView.findViewById(R.id.car_price_list_item_txt1);
		TextView GuidePrice = (TextView) convertView.findViewById(R.id.car_price_list_item_txt3);
		TextView Price = (TextView) convertView.findViewById(R.id.car_price_list_item_txt5);
		
		ModelText.setText(list.get(position).get("ModelText").toString());
		GuidePrice.setText(list.get(position).get("GuidePrice").toString());
		Price.setText(list.get(position).get("Price").toString());
		
		return convertView;
	}
}
