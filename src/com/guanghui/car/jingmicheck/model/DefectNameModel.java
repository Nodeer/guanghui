package com.guanghui.car.jingmicheck.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import com.guanghui.car.jingmicheck.util.SavePhotoModelInterface;

/**
 * 对应项目检测第四集--缺陷名称
 * 
 * @author Administrator
 * 
 */
public class DefectNameModel implements Serializable, SavePhotoModelInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6513148659233051247L;

	/** 是否需要照片 */
	public boolean isNeedImg = false;

	/** 照片路径,若null则表示未拍照 */
	public String imgPath = "";

	/** 缺陷名称 */
	public String text = "";

	/** 是否是个多选类型的名称 */
	public boolean isMutilCheck = false;

	/** 是否选中 */
	public boolean isChecked = false;

	/** 可选缺陷程度列表 */
	public ArrayList<DefectLevelModel> defectLevelList = new ArrayList<DefectLevelModel>();

    /**
     * 是否是广汇认证检测项
     */
    public boolean isAuthDefect = false;

    public DefectNameModel setIsAuthDefect(){
        isAuthDefect = true;
        return this;
    }

    /**
     * 是否有认证缺陷被选中
     * @return
     */
    public boolean isHasAuthDefect(){
        if(!isChecked){
            return false;
        }else{
            if(isAuthDefect){
                return true;
            }else{
                if(defectLevelList!=null){
                    for(DefectLevelModel model:defectLevelList){
                        if(model.isHasAuthDefect()){
                            return true;
                        }
                    }
                }
                return false;
            }
        }
    }

	/**
	 * 
	 * @param isNeedImg 是否需要照片
	 * @param text 名称描述
	 * @param defectLevelList 缺陷程度model列表
	 */
	public DefectNameModel(boolean isNeedImg, String text, ArrayList<DefectLevelModel> defectLevelList) {
		super();
		this.isNeedImg = isNeedImg;
		this.text = text;
		this.defectLevelList = defectLevelList;
	}

    /**
     *
     * @param isNeedImg 是否需要照片
     * @param text  名称描述
     * @param isMutilCheck  是否是个多选缺陷
     * @param defectLevelList   缺陷程度model列表
     */
	public DefectNameModel(boolean isNeedImg, String text, boolean isMutilCheck, ArrayList<DefectLevelModel> defectLevelList) {
		super();
		this.isNeedImg = isNeedImg;
		this.text = text;
		this.isMutilCheck = isMutilCheck;
		this.defectLevelList = defectLevelList;
	}

	@Override
	public void setImgPath(String path) {
		imgPath = path;
	}

	@Override
	public String getImgPath() {
		return imgPath;
	}

    /**清除所有信息*/
    public void clear() {
        isChecked = false;
        if(isNeedImg&&imgPath!=null){
            File f = new File(imgPath);
            if(f.exists()){
                f.delete();
            }
            imgPath = null;
        }
        if(defectLevelList!=null){
            int size = defectLevelList.size();
            for(int i=0;i<size;i++){
                defectLevelList.get(i).clear();
            }
        }
    }
}
