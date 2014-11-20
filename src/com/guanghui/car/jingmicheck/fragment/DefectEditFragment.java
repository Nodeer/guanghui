package com.guanghui.car.jingmicheck.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.guanghui.car.R;
import com.guanghui.car.jingmicheck.Constans;
import com.guanghui.car.jingmicheck.model.DefectDepartModel;
import com.guanghui.car.jingmicheck.model.DefectLevelModel;
import com.guanghui.car.jingmicheck.model.DefectNameModel;
import com.guanghui.car.jingmicheck.widget.DefectLevelView;
import com.guanghui.car.jingmicheck.widget.DefectNameView;
import com.guanghui.car.jingmicheck.widget.DefectTypeView;

/**
 * 点击热区后的弹框
 */
public class DefectEditFragment extends Fragment implements OnClickListener {
    // public LinearLayout history_layout;
    // public LinearLayout currentDefect_layout;
    public LinearLayout leftLayout;
    public TextView mDefectDepartInfo;
    public DefectDepartModel mCurrentDefectDepartModel;
    private LinearLayout mRightLayout;
    private DefectTypeView mCurrentTypeOfRightView;
    private LinearLayout.LayoutParams pa = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.defect_edit_layout, null);
        View left = rootview.findViewById(R.id.left_layout);
        left.setOnClickListener(this);
        mDefectDepartInfo = (TextView) rootview.findViewById(R.id.defect_depart_info);
        leftLayout = (LinearLayout) rootview.findViewById(R.id.left_layout_content);
        mCurrentDefectDepartModel = (DefectDepartModel) getArguments().getSerializable("model");
        mRightLayout = (LinearLayout) rootview.findViewById(R.id.right_layout);
        mRightLayout.setOnClickListener(this);

        mDefectDepartInfo.setText(mCurrentDefectDepartModel.name);
        if (mCurrentDefectDepartModel.historyDefectModel != null) {
            DefectTypeView typeView1 = new DefectTypeView(getActivity(), mCurrentDefectDepartModel.historyDefectModel);
            typeView1.setFatherView(this);
            leftLayout.addView(typeView1);
        }
        if (mCurrentDefectDepartModel.currentDefectModel != null) {
            DefectTypeView typeView2 = new DefectTypeView(getActivity(), mCurrentDefectDepartModel.currentDefectModel);
            typeView2.setFatherView(this);
            leftLayout.addView(typeView2);
        }
        rootview.setOnClickListener(this);

        return rootview;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.left_layout || v.getId() == R.id.right_layout) {
        } else {
            touchQuiet();
        }
    }

    private void touchQuiet() {
        if (checkDefect()) {
            ((BaseCheckFragment) getTargetFragment()).saveLastDefect();
            getFragmentManager().popBackStack();
        } else {
            Toast to = Toast.makeText(getActivity(), "缺陷没有选择完全.", Toast.LENGTH_SHORT);
            to.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 150);
            to.show();
        }
    }

    public void showDefecLevelViewWithName(DefectNameView nameview, DefectTypeView mCurrentTypeOfRightView) {
        mRightLayout.removeAllViews();
        this.mCurrentTypeOfRightView = mCurrentTypeOfRightView;
        ArrayList<DefectLevelModel> levelList = nameview.mNameModel.defectLevelList;
        if (levelList == null || levelList.size() == 0) {
            removeDefectLevelView();
        } else {
            LinearLayout levelview = new LinearLayout(getActivity());
            levelview.setOrientation(LinearLayout.VERTICAL);
            int size = levelList.size();
            if (size == 1) {
                DefectLevelModel model = levelList.get(0);
                DefectLevelView view = new DefectLevelView(getActivity(), model, -1);
                levelview.addView(view);
            } else {
                for (int i = 0; i < size; i++) {
                    DefectLevelModel model = levelList.get(i);
                    DefectLevelView view = new DefectLevelView(getActivity(), model, i);
                    levelview.addView(view);
                }
            }
            pa.setMargins(0, nameview.getTop() + nameview.getFatherView().getTop() + ((View) nameview.getFatherView().getParent()).getTop(), 0, 0);
            mRightLayout.setLayoutParams(pa);
            mRightLayout.addView(levelview, Constans.fillParentPa);
        }
    }

    private void removeDefectLevelView() {
        mRightLayout.removeAllViews();
    }

    public void removeDefectLevelView(DefectTypeView mCurrentTypeOfRightView) {
        if (this.mCurrentTypeOfRightView == mCurrentTypeOfRightView) {
            mRightLayout.removeAllViews();

        }
    }

    public boolean checkDefect() {
        if (mCurrentDefectDepartModel.historyDefectModel != null) {
            if (mCurrentDefectDepartModel.historyDefectModel.isChecked) {
                int defectnum = 0;
                for (DefectNameModel nameModel : mCurrentDefectDepartModel.historyDefectModel.mDefectNameList) {
                    if (nameModel.isChecked) {
//                        if (!nameModel.isMutilCheck) {// 如果是单选框...那就检查的很多了....
                        if (nameModel.defectLevelList != null) {
                            for (DefectLevelModel levelmodel : nameModel.defectLevelList) {
                                if (levelmodel.mCheckedLevelItem == -1) {
                                    return false;
                                }
                            }
                        }
                        defectnum++;
//                        } else {
//                            defectnum++;// 如果有一个多选框选中了.就ok.
//                        }
                    }
                }
                if (defectnum == 0) {// 遍历完了依然没有发现选中的子项...
                    return false;
                }
            }
        }
        if (mCurrentDefectDepartModel.currentDefectModel != null) {
            if (mCurrentDefectDepartModel.currentDefectModel.isChecked) {
                int defectnum = 0;
                for (DefectNameModel nameModel : mCurrentDefectDepartModel.currentDefectModel.mDefectNameList) {
                    if (nameModel.isChecked) {
//                        if (!nameModel.isMutilCheck) {// 如果是单选框...那就检查的很多了....
                        if (nameModel.defectLevelList != null) {
                            for (DefectLevelModel levelmodel : nameModel.defectLevelList) {
                                if (levelmodel.mCheckedLevelItem == -1) {
                                    return false;
                                }
                            }
                        }
                        defectnum++;
//                        } else {
//                            defectnum++;// 如果有一个多选框选中了.就ok.
//                        }
                    }
                }
                if (defectnum == 0) {// 遍历完了依然没有发现选中的子项...
                    return false;
                }
            }
        }
        return true;
    }

}
