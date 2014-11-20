package com.guanghui.car.jingmicheck.fragment;

import java.util.ArrayList;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.guanghui.car.jingmicheck.Constans;
import com.guanghui.car.jingmicheck.model.DefectDepartModel;
import com.guanghui.car.jingmicheck.util.DateBaseUtil;
import com.guanghui.car.jingmicheck.util.FileUtil;
import com.guanghui.car.jingmicheck.widget.DefectListItemView;

/**
 * 检测页面的基类fragment,包含了一些公有变量,方法
 */
public abstract class BaseCheckFragment extends Fragment {

	public DefectDepartModel mCurrentDefectDepartModel;
	public LinearLayout mShowListView;
	// public ArrayList<CheckItem> mCheckItemList = new ArrayList<CheckItem>();
	public ArrayList<DefectListItemView> mDefectViewList = new ArrayList<DefectListItemView>();
	public int mSelectCheckItemIndex;
	public ArrayList<DefectDepartModel> defectModelList = new ArrayList<DefectDepartModel>();
	private DefectEditFragment mDefectEditFragment;

    /**
     * 显示缺陷编辑页面
     * @param model
     */
	public void showeditPage(DefectDepartModel model) {

		mCurrentDefectDepartModel = model;

		mDefectEditFragment = new DefectEditFragment();
		mDefectEditFragment.setTargetFragment(this, 1);
		Bundle bun = new Bundle();
		bun.putSerializable("model", mCurrentDefectDepartModel);
		mDefectEditFragment.setArguments(bun);

		FragmentTransaction transaction = getFragmentManager().beginTransaction();

		transaction.add(Window.ID_ANDROID_CONTENT, mDefectEditFragment, "edit");
		transaction.addToBackStack("edit");
		transaction.commit();

	}

    /**
     * 保存当前显示的缺陷内容
     */
	public void saveLastDefect() {
		mCurrentDefectDepartModel.refreshDefectType();
		ContentValues values = new ContentValues();
		values.put("areaindex", mCurrentDefectDepartModel.areaIndex);
		values.put("departindex", mCurrentDefectDepartModel.departIndex);
		values.put("token", Constans.user_token);
		byte[] bytes = DateBaseUtil.getInstance().defect2Byte(mCurrentDefectDepartModel);
		values.put("object", bytes);
		DateBaseUtil.getInstance().insertValues(values);
		refreshDefectViews(mSelectCheckItemIndex);
		refreshCheckView();
        Gson gson = new Gson();

//        mCurrentDefectDepartModel =
//        gson.fromJson(FileUtil.readStringFromFIle("json.txt"),
//        DefectDepartModel.class);

        System.out.println("对象转json开始:" + System.currentTimeMillis());
        String str = gson.toJson(mCurrentDefectDepartModel);
        System.out.println("对象转json结束:" + System.currentTimeMillis());
        FileUtil.printToNewFile(str, "json.txt");

	}

    /**
     * 检测缺陷项是否都已选择
     * @return
     */
	public boolean checkDefect(){
		if(mDefectEditFragment!=null&&mDefectEditFragment.isVisible()){
			return mDefectEditFragment.checkDefect();
		}else{
			return true;
		}
	}

    /**
     * 是否处于编辑状态
     * @return
     */
    public boolean isEditting(){
        if(mDefectEditFragment!=null&&mDefectEditFragment.isVisible()){
            return true;
        }else{
            return false;
        }
    }

	/**
	 * 刷新页面上半部
	 */
	public abstract void refreshCheckView();

	/**
	 * 刷新页面下半部
	 * 
	 * @param mSelectCheckItemIndex2
	 */
	public abstract void refreshDefectViews(int mSelectCheckItemIndex2);

    /**
     * 初始化热区
     */
	public abstract void initCheckItems();
    /**
     * 初始化检测项
     */
	public abstract void initDefectModels();



}
