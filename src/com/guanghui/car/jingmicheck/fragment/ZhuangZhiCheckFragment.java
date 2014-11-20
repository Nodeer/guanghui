package com.guanghui.car.jingmicheck.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guanghui.car.R;
import com.guanghui.car.jingmicheck.Constans;
import com.guanghui.car.jingmicheck.model.DefectDepartModel;
import com.guanghui.car.jingmicheck.util.DefectBuildUtil;
import com.guanghui.car.jingmicheck.widget.DefectListItemView;

public class ZhuangZhiCheckFragment extends BaseCheckFragment implements OnItemClickListener {
    private int[] itemImgIds = new int[]{R.drawable.ico0001, R.drawable.ico0002, R.drawable.ico0003, R.drawable.ico0004, R.drawable.ico0005, R.drawable.ico0006, R.drawable.ico0007,
            R.drawable.ico0008, R.drawable.ico0009, R.drawable.ico0010, R.drawable.ico0011, R.drawable.ico0012, R.drawable.ico0013, R.drawable.ico0014, R.drawable.ico0015, R.drawable.ico0016,
            R.drawable.ico0017, R.drawable.ico0018, R.drawable.ico0019, R.drawable.ico0020, R.drawable.ico0021, R.drawable.ico0022, R.drawable.ico0023, R.drawable.ico0024, R.drawable.ico0025,
            R.drawable.ico0026, R.drawable.ico0027, R.drawable.ico0028, R.drawable.ico0029, R.drawable.ico0030, R.drawable.ico0031, R.drawable.ico0032, R.drawable.ico0033};
    private String[] itemText = new String[]{"前避震器", "仪表盘", "座椅", "大灯清洁器", "驱动系统", "车窗", "悬挂系统", "转向系统", "显示屏", "安全气囊/气帘", "音响系统", "起动系统", "内/后视镜", "发动机", "排气系统", "锁止装置", "抬头数字显示", "空调系统", "底盘升降",
            "变速器", "进气系统", "电动门/盖", "遮阳帘", "方向盘", "人机交互系统", "后避震器", "天窗", "雨刮系统", "灯光系统", "制动系统", "冷却系统", "自动吸合门", "折叠车顶"};
    private int itemwidth = Constans.sScreenWidth / 6;

    private GridAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.zhuangzhi_fragment_layout, null);

        mShowListView = (LinearLayout) rootView.findViewById(R.id.show_layout);

        initDefectModels();

        GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
        gridview.setOnItemClickListener(this);
        mAdapter = new GridAdapter();
        gridview.setAdapter(mAdapter);

        return rootView;
    }

    private class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return itemImgIds.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.zhuangzhi_item_layout, null);
            }
            ImageView img = (ImageView) convertView.findViewById(R.id.item_img);
            img.setImageResource(itemImgIds[position]);
            ImageView typeImg = (ImageView) convertView.findViewById(R.id.defect_type_img);
            if (position < defectModelList.size()) {
                DefectDepartModel model = defectModelList.get(position);
                if (model.hasDefect) {
                    typeImg.setImageResource(R.drawable.icon_hasdefect);
                } else {
                    typeImg.setImageResource(R.drawable.icon_nocheck);
                }
            }

            TextView text = (TextView) convertView.findViewById(R.id.item_text);
            text.setText(itemText[position]);
            convertView.setLayoutParams(new GridView.LayoutParams(itemwidth, itemwidth - 5));

            return convertView;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position < defectModelList.size()) {
            DefectDepartModel model = defectModelList.get(position);
            mSelectCheckItemIndex = position;
            showeditPage(model);
        }
    }

    @Override
    public void refreshCheckView() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshDefectViews(int mSelectCheckItemIndex2) {
        ((DefectListItemView) mShowListView.getChildAt(mSelectCheckItemIndex2)).initView();
    }

    @Override
    public void initCheckItems() {

    }

    @Override
    public void initDefectModels() {
        DefectListItemView view;
        DefectDepartModel model;
        defectModelList = new ArrayList<DefectDepartModel>();
        for (int i = 0; i < 33; i++) {
            model = DefectBuildUtil.makeDefectDepart(3, i);
            defectModelList.add(model);
            view = new DefectListItemView(getActivity(), model);
            mShowListView.addView(view);
        }
    }
}
