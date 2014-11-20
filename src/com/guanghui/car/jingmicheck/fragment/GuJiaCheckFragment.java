package com.guanghui.car.jingmicheck.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.guanghui.car.R;
import com.guanghui.car.jingmicheck.model.CheckItem;
import com.guanghui.car.jingmicheck.model.DefectDepartModel;
import com.guanghui.car.jingmicheck.util.CheckRegionBuildUtil;
import com.guanghui.car.jingmicheck.util.DefectBuildUtil;
import com.guanghui.car.jingmicheck.widget.CheckView;
import com.guanghui.car.jingmicheck.widget.CheckView.CheckStateListener;
import com.guanghui.car.jingmicheck.widget.DefectListItemView;

public class GuJiaCheckFragment extends BaseCheckFragment implements CheckStateListener, OnCheckedChangeListener, OnClickListener {

    private CheckView mCheckView;
    private ArrayList<CheckItem> leftItemList, rightItemList;
    private RadioGroup cameraRadioGroup;
    private boolean isLeft = true;
    private Button addAreaBtn, addPointBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.gujia_check_fragment_layout, null);

        mCheckView = (CheckView) rootView.findViewById(R.id.check_view);
        mCheckView.setBackgroundResource(R.drawable.gujia_left);
        cameraRadioGroup = (RadioGroup) rootView.findViewById(R.id.camera_radio_group);
        cameraRadioGroup.setVisibility(View.VISIBLE);
        cameraRadioGroup.setOnCheckedChangeListener(this);

//        addAreaBtn = (Button) rootView.findViewById(R.id.add_area);
//        addAreaBtn.setOnClickListener(this);
//        addPointBtn = (Button) rootView.findViewById(R.id.add_point);
//        addPointBtn.setOnClickListener(this);
//        mCheckView.setAddModel();
        View addview = rootView.findViewById(R.id.change_layout);
        addview.setVisibility(View.GONE);

        mShowListView = (LinearLayout) rootView.findViewById(R.id.show_layout);

        initDefectModels();
        initCheckItems();
        return rootView;
    }


    public void initDefectModels() {
        DefectDepartModel model = DefectBuildUtil.makeDefectDepart(2, 0);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 1);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 2);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 3);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 4);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 5);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 6);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 7);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 8);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 9);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 10);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 11);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 12);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 13);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 14);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 15);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 16);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 17);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 18);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 19);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 20);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 21);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 22);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 23);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 24);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 25);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 26);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 27);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 28);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 29);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 30);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 31);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 32);
        defectModelList.add(model);
        model = DefectBuildUtil.makeDefectDepart(2, 33);
        defectModelList.add(model);

    }

    @Override
    public void onItemSelect(CheckItem item) {
        showeditPage(item.model);
        mSelectCheckItemIndex = defectModelList.indexOf(item.model);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_area:
                mCheckView.addPathAndClear();
                break;
            case R.id.add_point:
                mCheckView.startAddPoint();
                break;
            default:

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.left_front:
                showLeftFrontView();
                break;
            case R.id.right_back:
                showRightBackView();
                break;

            default:
                break;
        }
    }

    private void showRightBackView() {
        isLeft = false;
        mCheckView.setBackgroundResource(R.drawable.gujia_right);
        mCheckView.setItems(rightItemList);
    }

    private void showLeftFrontView() {
        isLeft = true;
        mCheckView.setBackgroundResource(R.drawable.gujia_left);
        mCheckView.setItems(leftItemList);
    }

    @Override
    public void refreshCheckView() {
        mCheckView.invalidate();
    }

    @Override
    public void refreshDefectViews(int mSelectCheckItemIndex2) {
        ((DefectListItemView) mShowListView.getChildAt(mSelectCheckItemIndex2)).initView();
    }

    @Override
    public void initCheckItems() {
        leftItemList = new ArrayList<CheckItem>();
        rightItemList = new ArrayList<CheckItem>();
        DefectDepartModel model = defectModelList.get(30);
        CheckItem item = CheckRegionBuildUtil.makeGujiaCheckItem(model, true);
        leftItemList.add(item);
        model = defectModelList.get(8);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, true);
        leftItemList.add(item);
        model = defectModelList.get(1);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, true);
        leftItemList.add(item);
        model = defectModelList.get(4);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, true);
        leftItemList.add(item);
        model = defectModelList.get(0);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, true);
        leftItemList.add(item);
        model = defectModelList.get(11);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, true);
        leftItemList.add(item);
        model = defectModelList.get(7);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, true);
        leftItemList.add(item);
        model = defectModelList.get(17);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, true);
        leftItemList.add(item);
        model = defectModelList.get(22);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, true);
        leftItemList.add(item);
        model = defectModelList.get(24);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, true);
        leftItemList.add(item);
        model = defectModelList.get(15);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, true);
        leftItemList.add(item);
        model = defectModelList.get(20);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, true);
        leftItemList.add(item);

        //以下热区有重叠,越底层的放在越后
        model = defectModelList.get(19);//乘员舱底板梁
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, true);
        leftItemList.add(item);
        model = defectModelList.get(33);//乘员舱底板
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, true);
        leftItemList.add(item);
        model = defectModelList.get(6);//水箱框架
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, true);
        leftItemList.add(item);
        model = defectModelList.get(12);//右前纵梁
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, true);
        leftItemList.add(item);

        //右后视图
        model = defectModelList.get(32);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(26);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(9);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(2);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(13);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(5);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(28);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(29);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(14);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(3);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(31);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(10);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(27);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(25);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(23);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(18);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(21);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        model = defectModelList.get(16);
        item = CheckRegionBuildUtil.makeGujiaCheckItem(model, false);
        rightItemList.add(item);
        mCheckView.setItems(leftItemList);
        mCheckView.setCheckListener(this);
        initDefectViews();

    }

    public void initDefectViews() {

        for (DefectDepartModel item : defectModelList) {
            DefectListItemView view = new DefectListItemView(getActivity(), item);
            mShowListView.addView(view);
        }
    }

}
