package com.guanghui.car.adapter;

import java.util.HashMap;
import java.util.List;

import com.guanghui.car.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TaskUserInfoAdapter extends BaseAdapter {

	private Context context;
	private List<HashMap> data;
	public TaskUserInfoAdapter(Context context,List<HashMap> data) {
		super();
		this.context = context;
		this.data = data;
		// TODO Auto-generated constructor stub
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
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 if(convertView ==null)
	        {
		        convertView = LayoutInflater.from(context).inflate(R.layout.car_preview_list_item, null);
	        }
		    TextView car_task_info = (TextView)convertView.findViewById(R.id.task_text);
			
			
		    car_task_info.setText(data.get(position).get("info").toString());
			
			return convertView;
	}

}
