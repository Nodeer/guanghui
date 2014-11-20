package com.guanghui.car.jingmicheck.model;

import java.io.Serializable;

/**
 * 对应项目检测第二级--缺陷部件
 * 
 * @author Administrator
 * 
 */
public class DefectDepartModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2589020987812509453L;

	/**
	 * 缺陷部件名称
	 */
	public String name = "";

	/**
	 * 属于哪个视图(骨架/外观/内饰/装置)
	 */
	public int areaIndex;

	/** 是否有缺陷 */
	public boolean hasDefect = false;

	/**
	 * 在视图中属于第几个
	 */
	public int departIndex;

	/** 历史缺陷model */
	public DefectTypeModel historyDefectModel;
	/** 当前缺陷model */
	public DefectTypeModel currentDefectModel;

    /**
     * 是否有认证缺陷被选中
     * @return
     */
    public boolean isHasAuthDefect(){
        if(historyDefectModel!=null){
            if(historyDefectModel.isHasAuthDefect()){
                return true;
            }
        }
        if(currentDefectModel!=null){
            if(currentDefectModel.isHasAuthDefect()){
                return true;
            }
        }
        return false;
    }
	
	

	public DefectDepartModel(int areaIndex, int departIndex) {
		super();
		this.areaIndex = areaIndex;
		this.departIndex = departIndex;
	}



	public void refreshDefectType() {
		hasDefect = false;
		if (historyDefectModel != null && historyDefectModel.isChecked) {
			hasDefect = true;
		}
		if (currentDefectModel != null && currentDefectModel.isChecked) {
			hasDefect = true;
		}
	}

}
