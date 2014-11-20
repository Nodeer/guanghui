package com.guanghui.car.jingmicheck.widget;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guanghui.car.R;
import com.guanghui.car.jingmicheck.model.DefectDepartModel;
import com.guanghui.car.jingmicheck.model.DefectLevelModel;
import com.guanghui.car.jingmicheck.model.DefectNameModel;

public class DefectListItemView extends LinearLayout {
    private DefectDepartModel model;

    public DefectListItemView(Context context, DefectDepartModel model) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
        this.model = model;
        initView();
    }

    public void initView() {
        removeAllViews();
        int i = 0;
        if (model.currentDefectModel != null && model.currentDefectModel.isChecked) {
            i++;
        }

        if (model.historyDefectModel != null && model.historyDefectModel.isChecked) {
            i++;
        }
        if (i == 0) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);
            if (model.currentDefectModel != null && model.currentDefectModel.isChecked) {
                ArrayList<DefectNameModel> nameModelList = model.currentDefectModel.mDefectNameList;
                for (DefectNameModel nameModel : nameModelList) {
                    if (nameModel.isMutilCheck&&nameModel.isChecked) {
                        View rootview = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.defect_list_item_layout, null);
                        setItemContent(model.currentDefectModel.typeName, rootview, nameModel);
                        addView(rootview);
                    }
                }
                if (model.currentDefectModel.checkName != -1) {
                    View rootview = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.defect_list_item_layout, null);
                    setItemContent(model.currentDefectModel.typeName, rootview, model.currentDefectModel.mDefectNameList.get(model.currentDefectModel.checkName));
                    addView(rootview);
                }
            }
            if (model.historyDefectModel != null && model.historyDefectModel.isChecked) {
                ArrayList<DefectNameModel> nameModelList = model.historyDefectModel.mDefectNameList;
                for (DefectNameModel nameModel : nameModelList) {
                    if (nameModel.isMutilCheck&&nameModel.isChecked) {
                        View rootview = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.defect_list_item_layout, null);
                        setItemContent(model.historyDefectModel.typeName, rootview, nameModel);
                        addView(rootview);
                    }
                }
                if (model.historyDefectModel.checkName != -1) {
                    View rootview = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.defect_list_item_layout, null);
                    setItemContent(model.historyDefectModel.typeName, rootview, model.historyDefectModel.mDefectNameList.get(model.historyDefectModel.checkName));
                    addView(rootview);
                }
            }
        }

    }

    private void setItemContent(String typeName, View rootview, DefectNameModel nameModel) {
        TextView name = (TextView) rootview.findViewById(R.id.depart_name);
        TextView type = (TextView) rootview.findViewById(R.id.depart_type);
        TextView defectname = (TextView) rootview.findViewById(R.id.defect_name);
        TextView defectlevel = (TextView) rootview.findViewById(R.id.defect_level);
//        if (nameModel.isMutilCheck) {
//            name.setText(model.name);
//            type.setText(typeName);
//
//            String[] namearr = nameModel.text.split("：");
//            defectname.setText(namearr[0]);
//        } else {
            name.setText(model.name);
            type.setText(typeName);

            String[] namearr = nameModel.text.split("：");
            defectname.setText(namearr[0]);
            if (nameModel.defectLevelList != null) {
                StringBuilder builder = new StringBuilder();
                int size = nameModel.defectLevelList.size();
                for (int i = 0; i < size; i++) {
                    if (i != 0) {
                        builder.append(",");
                    }
                    DefectLevelModel levelmodel = nameModel.defectLevelList.get(i);
                    if(levelmodel.mCheckedLevelItem!=-1){
                        String s = levelmodel.levelList.get(levelmodel.mCheckedLevelItem).text;
                        String[] arr = s.split("：");
                        builder.append(arr[0]);
                    }
                }
                defectlevel.setText(builder.toString());
            }
//        }

    }
}
