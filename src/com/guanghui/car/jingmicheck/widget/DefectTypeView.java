package com.guanghui.car.jingmicheck.widget;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.guanghui.car.R;
import com.guanghui.car.jingmicheck.Constans;
import com.guanghui.car.jingmicheck.fragment.DefectEditFragment;
import com.guanghui.car.jingmicheck.model.DefectNameModel;
import com.guanghui.car.jingmicheck.model.DefectTypeModel;

public class DefectTypeView extends FrameLayout implements OnCheckedChangeListener {
	private DefectTypeModel mTypeModel;
	private Activity mActivity;
	private ArrayList<DefectNameView> mRadioNameViewss = new ArrayList<DefectNameView>();
	private ArrayList<DefectNameView> mCheckNameViews = new ArrayList<DefectNameView>();
	private InterceptedLinearLayout defectNameLayout;
	private DefectEditFragment mFatherView;

	public DefectTypeView(Context context) {
		super(context);
		mActivity = (Activity) context;
	}

	/**
	 * @param context
	 * @param model
	 */
	public DefectTypeView(Context context, DefectTypeModel model) {
		super(context);
		mActivity = (Activity) context;
		this.mTypeModel = model;
		if (model == null) {
			return;
		}
		View view = mActivity.getLayoutInflater().inflate(R.layout.defect_type_layout, null);
		CheckBox box = (CheckBox) view.findViewById(R.id.type_name);
		box.setText(model.typeName);
		box.setOnCheckedChangeListener(this);
		defectNameLayout = (InterceptedLinearLayout) view.findViewById(R.id.name_layout);
		addDefectNameViews(defectNameLayout, model.mDefectNameList);
		// defectLevelLayout = (LinearLayout)
		// view.findViewById(R.id.level_layout);
		box.setChecked(model.isChecked);
        defectNameLayout.setIsIntercept(!model.isChecked);
		addView(view, Constans.fillParentPa);
	}

	public void setFatherView(DefectEditFragment fra) {
		this.mFatherView = fra;
	}

	private void addDefectNameViews(LinearLayout defectNameLayout, ArrayList<DefectNameModel> mDefectNameList) {
		int size = mDefectNameList.size();
		for (int i = 0; i < size; i++) {
			DefectNameModel model = mDefectNameList.get(i);
			DefectNameView view = new DefectNameView(mActivity, model);
			view.setFatherView(this);
			defectNameLayout.addView(view);
			if (model.isMutilCheck) {
				mCheckNameViews.add(view);
			} else {
				mRadioNameViewss.add(view);
			}
		}
	}



	public void onCheckNameChange(DefectNameView nameview, boolean isChecked) {
		if (isChecked) {
			mFatherView.showDefecLevelViewWithName(nameview,this);
		} else {
			mFatherView.removeDefectLevelView(this);
		}
	}

	public void onRadioNameChange(DefectNameView nameview) {
		mTypeModel.checkName = mTypeModel.mDefectNameList.indexOf(nameview.mNameModel);
		for (DefectNameView view : mRadioNameViewss) {
			if (view != nameview) {
				view.setChecked(false);
			}
		}
		mFatherView.showDefecLevelViewWithName(nameview,this);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		mTypeModel.isChecked = isChecked;
        defectNameLayout.setIsIntercept(!isChecked);
        if(!isChecked){
            mFatherView.removeDefectLevelView(this);
            mTypeModel.clear();
            for(DefectNameView view:mCheckNameViews){
                view.setChecked(false);
            }
            for(DefectNameView view:mRadioNameViewss){
                view.setChecked(false);
            }
        }
	}

}
