package com.guanghui.car.jingmicheck.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 对应项目检测第五级--缺陷描述/程度
 * 代表一组缺陷程度.一个defectnameModel中可能有几个levelmodel.
 * 
 * @author Administrator
 * 
 */
public class DefectLevelModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8068059887593690927L;

//	/** 照片路径 */
//	public String imgPath;
//	/** 如果没有缺陷程度列表,则应该是拍照 */
//	public boolean isNeedImg;
//	/** 选中的缺陷等级index */
//	public int selectLevelIndex = -1;

	/** 缺陷程度名称列表 */
	public ArrayList<DefectLevelItemModel> levelList = new ArrayList<DefectLevelItemModel>();


    public int mCheckedLevelItem = -1;


	/**
	 * 
	 * @param levelText 缺陷程度列表
	 */
	public DefectLevelModel(ArrayList<DefectLevelItemModel> levelText) {
		super();
		this.levelList = levelText;
	}

    /**
     * 是否有认证缺陷被选中
     * @return
     */
    public boolean isHasAuthDefect(){
        if(mCheckedLevelItem==-1){
            return false;
        }else{
            return levelList.get(mCheckedLevelItem).isAuthDefect;
        }
    }


    public void clear() {
        mCheckedLevelItem = -1;
        for(DefectLevelItemModel item:levelList){
            item.clear();
        }
    }
}
