package com.guanghui.car.adapter;

import java.util.List ;

import com.guanghui.car.R ;

import android.content.Context ;
import android.view.LayoutInflater ;
import android.view.View ;
import android.view.ViewGroup ;
import android.widget.BaseAdapter ;
import android.widget.TextView ;
/**
 * 任务详细列表适配器
 * */
public class TaskInfoAdapter extends BaseAdapter{
    private List < String > mData;
    private LayoutInflater mLayoutInflater;
    public TaskInfoAdapter( Context context , List < String > data ){ 
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mData = data;
    }
    @ Override
    public int getCount()
    {

        // TODO Auto-generated method stub
        return mData.size() ;
    }

    @ Override
    public Object getItem(int position)
    {

        // TODO Auto-generated method stub
        return mData.get(position) ;
    }

    @ Override
    public long getItemId(int position)
    {

        // TODO Auto-generated method stub
        return position ;
    }

    @ Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        InitWidget initWidget = null;
        if(convertView == null){
            initWidget = new InitWidget();
            convertView = mLayoutInflater.inflate(R.layout.layout_taskinfo_list_item, null);
            initWidget.taskInfo_textV = ( TextView ) convertView.findViewById(R.id.taskInfo_textV);
            initWidget.taskInfo_textV2 = ( TextView ) convertView.findViewById(R.id.taskInfo_textV2);
            convertView.setTag(initWidget);
        }else{
            initWidget = ( InitWidget ) convertView.getTag();
        }
        
        initWidget.taskInfo_textV.setText(mData.get(position).split("<br>")[0]);
        initWidget.taskInfo_textV2.setText(mData.get(position).split("<br>")[1]);
        return convertView ;
    }
    public final class InitWidget{
        public TextView taskInfo_textV;
        public TextView taskInfo_textV2;
    }
}
