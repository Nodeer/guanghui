package com.guanghui.car.jingmicheck.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.guanghui.car.R;
import com.guanghui.car.jingmicheck.JingMiCarCheckActivity;
import com.guanghui.car.jingmicheck.Constans;
import com.guanghui.car.jingmicheck.model.DefectLevelItemModel;
import com.guanghui.car.jingmicheck.util.StringUtil;

public class DefectLevelViewItem extends FrameLayout implements OnCheckedChangeListener, OnClickListener {
    private DefectLevelView mFatherView;
    public DefectLevelItemModel mDefectLevelItemModel;

    private RadioButton btn;
    private Button photoBtn;

    public DefectLevelViewItem(Context context) {
        super(context);
    }

    public DefectLevelViewItem(Context context, DefectLevelItemModel mDefectLevelModel) {
        super(context);
        this.mDefectLevelItemModel = mDefectLevelModel;
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.defect_level_item_layout, null);
        btn = (RadioButton) view.findViewById(R.id.level_radiobtn);
        if(mDefectLevelItemModel.isNeedImg){
            SpannableString str = new SpannableString(mDefectLevelModel.text+"a");
            Drawable able = getContext().getResources().getDrawable(R.drawable.camera);
            able.setBounds(0,0,25,17);
            ImageSpan span = new ImageSpan(able,ImageSpan.ALIGN_BASELINE);
            str.setSpan(span,str.length()-1,str.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            btn.setText(str);
        }else{
            btn.setText(mDefectLevelModel.text);
        }
        if (mDefectLevelModel.isChecked) {
            btn.setChecked(true);
        }
        btn.setOnCheckedChangeListener(this);
        photoBtn = (Button) view.findViewById(R.id.photo_btn);
        photoBtn.setOnClickListener(this);
        refreshImageBtn();
        addView(view, Constans.fillParentPa);
    }

    public void setChecked(boolean checked) {
        btn.setChecked(checked);
        mDefectLevelItemModel.clear();
        refreshImageBtn();;
    }

    private void refreshImageBtn() {
        if (mDefectLevelItemModel.isNeedImg && !StringUtil.emptyOrNull(mDefectLevelItemModel.imgPath)) {
            photoBtn.setVisibility(View.VISIBLE);
        } else {
            photoBtn.setVisibility(View.GONE);
        }
    }

    public void setFatherView(DefectLevelView view) {
        this.mFatherView = view;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mDefectLevelItemModel.isChecked = true;
            mFatherView.setSelectModel(this);
            if (mDefectLevelItemModel.isNeedImg) {
                ((JingMiCarCheckActivity) getContext()).takePhote((Button) photoBtn, mDefectLevelItemModel);
            }
        }
    }

    @Override
    public void onClick(View v) {
        ((JingMiCarCheckActivity) getContext()).showPhoto(mDefectLevelItemModel);
    }

}
