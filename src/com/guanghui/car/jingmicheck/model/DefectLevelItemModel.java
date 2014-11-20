package com.guanghui.car.jingmicheck.model;

import java.io.File;
import java.io.Serializable;

import com.guanghui.car.jingmicheck.util.SavePhotoModelInterface;

/**
 * Created by Administrator on 14-4-5.
 * 单个缺陷程度
 */
public class DefectLevelItemModel implements Serializable, SavePhotoModelInterface {
    public static final long serialVersionUID = 8068057845648369927L;
    /**
     * 是否需要照片
     */
    public boolean isNeedImg = false;

    /**
     * 照片路径,若null则表示未拍照
     */
    public String imgPath = "";

    /**
     * 缺陷程度名称
     */
    public String text = "";

    /**
     * 是否是广汇认证检测项
     */
    public boolean isAuthDefect = false;


    /**
     * 是否选中
     */
    public boolean isChecked = false;

    @Override
    public void setImgPath(String path) {
        imgPath = path;
    }

    public DefectLevelItemModel(boolean isNeedImg, String text) {
        this.isNeedImg = isNeedImg;
        this.text = text;
    }

    public DefectLevelItemModel setIsAuthDefect(){
        isAuthDefect = true;
        return this;
    }

    @Override
    public String getImgPath() {
        return imgPath;
    }

    public void clear() {
        if (isNeedImg && imgPath != null) {
            File f = new File(imgPath);
            if (f.exists()) {
                f.delete();
            }
            imgPath = null;
        }
        isChecked = false;
    }
}
