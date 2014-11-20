package com.guanghui.car.jingmicheck.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 对应项目检测列表第三级--缺陷属性
 * 
 * @author Administrator
 * 
 */
public class DefectTypeModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2221859753079528629L;

	/** 缺陷列表 */
	public ArrayList<DefectNameModel> mDefectNameList = new ArrayList<DefectNameModel>();

	/** 选中的缺陷名称 */
//	public DefectNameModel checkName = null;
	public int  checkName = -1;

	/** 是否已经选中 */
	public boolean isChecked = false;

	public String typeName = "";

    /**
     * 是否有认证缺陷被选中
     * @return
     */
    public boolean isHasAuthDefect(){
        if(!isChecked){
            return false;
        } else {
            if(mDefectNameList!=null){
                for (DefectNameModel model : mDefectNameList) {
                    if (model.isHasAuthDefect()) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

	public DefectTypeModel(boolean isChecked, String typeName) {
		super();
		this.isChecked = isChecked;
		this.typeName = typeName;
	}

	public DefectTypeModel(String typeName) {
		super();
		this.typeName = typeName;
	}

    public void clear() {
        checkName = -1;
    }
}
