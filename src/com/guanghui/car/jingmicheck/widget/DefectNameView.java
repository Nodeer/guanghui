package com.guanghui.car.jingmicheck.widget;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.guanghui.car.R;
import com.guanghui.car.jingmicheck.JingMiCarCheckActivity;
import com.guanghui.car.jingmicheck.Constans;
import com.guanghui.car.jingmicheck.model.DefectNameModel;
import com.guanghui.car.jingmicheck.util.StringUtil;

public class DefectNameView extends LinearLayout implements OnCheckedChangeListener, OnClickListener {
    private DefectTypeView mFatherView;
    private Activity mActivity;
    private CompoundButton mCheckBtn;
    public DefectNameModel mNameModel;
    // private Button imgBtn;
    private RadioGroup radioGroup, levelGroup;
    private LinearLayout nameLayout, levelLayout;
    private ArrayList<DefectLevelView> mLevelViewList = new ArrayList<DefectLevelView>();
    private DefectLevelView mSelectedLevelView;
    private Button photoBtn;

    public DefectNameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefectNameView(Context context, DefectNameModel model) {
        super(context);
        mActivity = (Activity) context;
        this.mNameModel = model;
        View view = mActivity.getLayoutInflater().inflate(R.layout.defect_name_layout, null);
        if (model.isMutilCheck) {
            CheckBox box = (CheckBox) view.findViewById(R.id.name_checkbox);
            if (mNameModel.isChecked) {
                box.setChecked(true);
            }
//            box.setText(model.text);
            box.setOnCheckedChangeListener(this);
            box.setVisibility(View.VISIBLE);
            mCheckBtn = box;
        } else {
            RadioButton radio = (RadioButton) view.findViewById(R.id.name_radiobtn);
//            radio.setText(model.text);
            if (mNameModel.isChecked) {
                radio.setChecked(true);
            }
            radio.setOnCheckedChangeListener(this);
            radio.setOnClickListener(this);
            radio.setVisibility(View.VISIBLE);
            mCheckBtn = radio;
        }

        if(mNameModel.isNeedImg){
            SpannableString str = new SpannableString(model.text+"a");
            Drawable able = getContext().getResources().getDrawable(R.drawable.camera);
            able.setBounds(0,0,25,17);
            ImageSpan span = new ImageSpan(able,ImageSpan.ALIGN_BASELINE);
            str.setSpan(span,str.length()-1,str.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            mCheckBtn.setText(str);
        }else{
            mCheckBtn.setText(model.text);
        }

        photoBtn = (Button) view.findViewById(R.id.photo_btn);
        photoBtn.setOnClickListener(this);
        refreshPhotoBtn();

        addView(view, Constans.fillParentPa);
    }


    public void refreshPhotoBtn() {
        if (mNameModel.isNeedImg && !StringUtil.emptyOrNull(mNameModel.imgPath)) {
            photoBtn.setVisibility(View.VISIBLE);
        } else {
            photoBtn.setVisibility(View.GONE);

        }
    }

    public void setFatherView(DefectTypeView view) {
        mFatherView = view;
    }

    public DefectTypeView getFatherView() {
        return mFatherView;
    }

    public void setChecked(boolean isCheck) {
        mCheckBtn.setChecked(isCheck);
        refreshPhotoBtn();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) {
            mNameModel.clear();
        } else {
            mNameModel.isChecked = true;
            if (mNameModel.isNeedImg) {
                ((JingMiCarCheckActivity) getContext()).takePhote((Button) photoBtn, mNameModel);
            }
        }
        if (buttonView instanceof RadioButton) {
            if (isChecked) {
                mFatherView.onRadioNameChange(this);
            }
        } else {
            mFatherView.onCheckNameChange(this, isChecked);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo_btn:
                ((JingMiCarCheckActivity) getContext()).showPhoto(mNameModel);
                break;
            case R.id.name_radiobtn:
                if (mCheckBtn.isChecked()) {
                    mFatherView.onRadioNameChange(this);
                }
                break;

            default:
                break;
        }
    }

}
