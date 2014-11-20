package com.guanghui.car.jingmicheck.widget;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.guanghui.car.R;
import com.guanghui.car.jingmicheck.Constans;
import com.guanghui.car.jingmicheck.model.DefectLevelItemModel;
import com.guanghui.car.jingmicheck.model.DefectLevelModel;

public class DefectLevelView extends LinearLayout {
    private DefectLevelModel mDefectLevelModel;
    private RadioButton mRadioButton;
    private Button imgBtn;
    private ArrayList<DefectLevelViewItem> itemList = new ArrayList<DefectLevelViewItem>();

    public DefectLevelView(Context context) {
        super(context);
    }

    public DefectLevelView(Context context, DefectLevelModel model,int index) {
        super(context);
        mDefectLevelModel = model;
        setOrientation(LinearLayout.VERTICAL);
        ArrayList<DefectLevelItemModel> levelList = model.levelList;
        LinearLayout levelview = (LinearLayout) ((Activity)getContext()).getLayoutInflater().inflate(R.layout.defect_level_layout, null);
        TextView title = (TextView) levelview.findViewById(R.id.title);
        if(index==-1){
            title.setBackgroundResource(R.drawable.defect_level_title_bg);
            title.setText("缺陷程度");
            title.setPadding(29,0,0,0);
        }else{
            if(index==0){
                title.setBackgroundResource(R.drawable.defect_level_title_bg);
                title.setText("缺陷程度1");
                title.setPadding(29,0,0,0);
            }else{
                title.setBackgroundResource(R.drawable.defect_level_title_bg_2);
                title.setText("缺陷程度"+(index+1));
                title.setLayoutParams(Constans.levelitempa);
            }
        }
        int size = levelList.size();
        for (int i = 0; i < size; i++) {
            DefectLevelViewItem item = new DefectLevelViewItem(context, levelList.get(i));
            item.setFatherView(this);
            itemList.add(item);
            levelview.addView(item, Constans.levelitempa);
        }
        addView(levelview);
    }

    public void clear() {
        mDefectLevelModel.clear();
        for (DefectLevelViewItem item : itemList) {
            item.setChecked(false);
        }
    }


    public void setSelectModel(DefectLevelViewItem index) {
        mDefectLevelModel.mCheckedLevelItem = mDefectLevelModel.levelList.indexOf(index.mDefectLevelItemModel);
        int size = itemList.size();
        for (int i = 0; i < size; i++) {
            DefectLevelViewItem item = itemList.get(i);
            if (item != index) {
                item.setChecked(false);
            }
        }
    }

}
