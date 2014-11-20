package com.guanghui.car.jingmicheck.util;

import java.util.ArrayList;

import com.guanghui.car.jingmicheck.model.DefectDepartModel;
import com.guanghui.car.jingmicheck.model.DefectLevelItemModel;
import com.guanghui.car.jingmicheck.model.DefectLevelModel;
import com.guanghui.car.jingmicheck.model.DefectNameModel;
import com.guanghui.car.jingmicheck.model.DefectTypeModel;

/**
 * 创建检测项的工具类
 */
public class DefectBuildUtil {
    /**
     * 根据一级index,和二级index,设置缺陷中包含的信息项目,返回缺陷
     */
    public static DefectDepartModel makeDefectDepart(int x, int y) {
        DefectDepartModel model = DateBaseUtil.getInstance().getDefectModel(String.valueOf(x), String.valueOf(y));
        if (model == null) {
            model = new DefectDepartModel(x, y);
        } else {
            return model;
        }
        switch (x) {
            case 0:// 外观
                makeWaiguanDefect(model, y);
                break;
            case 1:// 内饰
                makeNeishiDefect(model, y);
                break;
            case 2:// 骨架
                makeGujiaDefect(model, y);
                break;
            case 3:// 装置
                makeZhuangZhiDefect(model, y);
                break;

            default:
                break;
        }
        return model;
    }

    private static void makeNeishiDefect(DefectDepartModel model, int y) {

        DefectTypeModel current, history;
        DefectNameModel nameModel;
        DefectLevelModel levelModel;
        ArrayList<DefectLevelItemModel> levellist;
        ArrayList<DefectLevelModel> levelModelList;
        switch (y) {
            case 0:// 仪表台
                model.name = "仪表台";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);


                current = new DefectTypeModel("现有缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);


                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 1:// 中控台
                model.name = "中控台";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", true, null);
                history.mDefectNameList.add(nameModel);


                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);


                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 2:// 方向盘
                model.name = "方向盘";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);


                current = new DefectTypeModel("现有缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);


                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 3:// 变速杆
                model.name = "变速杆";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", true, null);
                history.mDefectNameList.add(nameModel);


                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);


                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 4://储物箱
                model.name = "储物箱";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", true, null);
                history.mDefectNameList.add(nameModel);


                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);


                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 5://主驾座椅
                model.name = "主驾座椅";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);


                current = new DefectTypeModel("现有缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);


                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 6://副驾座椅
                model.name = "副驾座椅";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);


                current = new DefectTypeModel("现有缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);


                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 7://后排座椅
                model.name = "后排座椅";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 8://左前门内饰
                model.name = "左前门内饰";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 9://右前门内饰
                model.name = "右前门内饰";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);


                current = new DefectTypeModel("现有缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);


                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 10://左后门内饰
                model.name = "左后门内饰";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 11://右后门内饰
                model.name = "右后门内饰";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 12://车顶内饰
                model.name = "车顶内饰";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 13://左A柱内饰
                model.name = "左A柱内饰";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 14://右A柱内饰
                model.name = "右A柱内饰";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 15://左B柱内饰
                model.name = "左B柱内饰";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 16://右B柱内饰
                model.name = "右B柱内饰";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 17://左C/D柱内饰
                model.name = "左C/D柱内饰";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 18://右C/D柱内饰
                model.name = "右C/D柱内饰";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 19://尾箱底板内饰
                model.name = "尾箱底板内饰";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 20://后窗台内饰
                model.name = "后窗台内饰";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 21://乘员舱底板内饰
                model.name = "乘员舱底板内饰";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 22://前座中央扶手
                model.name = "前座中央扶手";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 23://后座中央扶手
                model.name = "后座中央扶手";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 24://内视镜
                model.name = "内视镜";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺失：应当装备的总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 25://地控台
                model.name = "地控台";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "污渍：部件有污痕/日晒痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "一级：1CM之内≤2处。"));
                levellist.add(new DefectLevelItemModel(false, "二级：1CM之内在3～5处。"));
                levellist.add(new DefectLevelItemModel(false, "三级：1CM以上/1CM之内＞5处。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "烧焦：部件有烧过痕迹的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;

            default:
                model.name = "数据待添加";
                current = new DefectTypeModel("数据待添加");
                break;
        }

    }

    private static void makeWaiguanDefect(DefectDepartModel model, int y) {
        DefectTypeModel current, history;
        DefectNameModel nameModel;
        DefectLevelModel levelModel;
        ArrayList<DefectLevelItemModel> levellist;
        ArrayList<DefectLevelModel> levelModelList;
        switch (y) {
            case 0:// 左前轮胎
                model.name = "左前轮胎";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "轮胎漏气", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "露出胎面磨损标记", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "胎面单边磨损", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "胎侧鼓包", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "胎侧撕裂", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 1:// 右前轮胎
                model.name = "右前轮胎";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "轮胎漏气", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "露出胎面磨损标记", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "胎面单边磨损", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "胎侧鼓包", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "胎侧撕裂", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 2:// 左后轮胎
                model.name = "左后轮胎";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "轮胎漏气", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "露出胎面磨损标记", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "胎面单边磨损", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "胎侧鼓包", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "胎侧撕裂", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 3://右后轮胎
                model.name = "右后轮胎";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "轮胎漏气", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "露出胎面磨损标记", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "胎面单边磨损", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "胎侧鼓包", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "胎侧撕裂", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;

                break;
            case 4://备用轮胎
                model.name = "备用轮胎";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "轮胎漏气", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "露出胎面磨损标记", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "胎面单边磨损", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "胎侧鼓包", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "胎侧撕裂", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 5://左前轮圈
                model.name = "左前轮圈";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "轮圈非变形损伤", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "轮圈变形损伤", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 6://右前轮圈
                model.name = "右前轮圈";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "轮圈非变形损伤", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "轮圈变形损伤", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 7://左后轮圈
                model.name = "左后轮圈";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "轮圈非变形损伤", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "轮圈变形损伤", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 8://右后轮圈
                model.name = "右后轮圈";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "轮圈非变形损伤", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "轮圈变形损伤", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 9://备用轮圈
                model.name = "备用轮圈";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "轮圈非变形损伤", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "轮圈变形损伤", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 10://左前灯
                model.name = "左前灯";
                history = new DefectTypeModel("历史缺陷");

                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "灯罩泛黄", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "灯罩水气", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 11://右前灯
                model.name = "右前灯";
                history = new DefectTypeModel("历史缺陷");

                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "灯罩泛黄", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "灯罩水气", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 12://左前杠灯
                model.name = "左前杠灯";
                history = new DefectTypeModel("历史缺陷");

                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "灯罩泛黄", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "灯罩水气", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺失", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 13://右前杠灯
                model.name = "右前杠灯";
                history = new DefectTypeModel("历史缺陷");

                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "灯罩泛黄", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "灯罩水气", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺失", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 14://左尾灯
                model.name = "左尾灯";
                history = new DefectTypeModel("历史缺陷");

                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "灯罩泛黄", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "灯罩水气", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 15://右尾灯
                model.name = "右尾灯";
                history = new DefectTypeModel("历史缺陷");

                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "灯罩泛黄", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "灯罩水气", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 16://后杠灯
                model.name = "后杠灯";
                history = new DefectTypeModel("历史缺陷");

                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "灯罩泛黄", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "灯罩水气", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺失", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 17://高位刹车灯
                model.name = "高位刹车灯";
                history = new DefectTypeModel("历史缺陷");

                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "灯罩泛黄", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "灯罩水气", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 18://前档玻璃
                model.name = "前档玻璃";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃密封条变形", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃上不显眼的牛眼", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃上显眼的牛眼", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 19://后档玻璃
                model.name = "后档玻璃";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃密封条变形", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃上不显眼的牛眼", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃上显眼的牛眼", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 20://左前门玻璃
                model.name = "左前门玻璃";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃密封条变形", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃上不显眼的牛眼", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃上显眼的牛眼", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 21://右前门玻璃
                model.name = "右前门玻璃";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃密封条变形", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃上不显眼的牛眼", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃上显眼的牛眼", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 22://左后门玻璃
                model.name = "左后门玻璃";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃密封条变形", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃上不显眼的牛眼", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃上显眼的牛眼", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 23://右后门玻璃
                model.name = "右后门玻璃";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃密封条变形", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃上不显眼的牛眼", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃上显眼的牛眼", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 24://天窗玻璃
                model.name = "天窗玻璃";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃密封条变形", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃上不显眼的牛眼", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "玻璃上显眼的牛眼", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 25://左后视镜
                model.name = "左后视镜";
                history = new DefectTypeModel("历史缺陷");

                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 26://右后视镜
                model.name = "右后视镜";
                history = new DefectTypeModel("历史缺陷");

                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 27://前保险杠
                model.name = "前保险杠";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "凹陷：部件有变形的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤和凹陷：部件有划伤和凹陷共同存在的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "腐蚀：部件氧化，一直生锈到部件内部的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 28://后保险杠
                model.name = "后保险杠";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "凹陷：部件有变形的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤和凹陷：部件有划伤和凹陷共同存在的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "腐蚀：部件氧化，一直生锈到部件内部的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 29://前盖
                model.name = "前盖";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "凹陷：部件有变形的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤和凹陷：部件有划伤和凹陷共同存在的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "腐蚀：部件氧化，一直生锈到部件内部的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 30://后盖
                model.name = "后盖";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "凹陷：部件有变形的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤和凹陷：部件有划伤和凹陷共同存在的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "腐蚀：部件氧化，一直生锈到部件内部的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 31://左前门
                model.name = "左前门";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "凹陷：部件有变形的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤和凹陷：部件有划伤和凹陷共同存在的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "腐蚀：部件氧化，一直生锈到部件内部的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 32://右前门
                model.name = "右前门";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "凹陷：部件有变形的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤和凹陷：部件有划伤和凹陷共同存在的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "腐蚀：部件氧化，一直生锈到部件内部的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 33://左后门
                model.name = "左后门";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "凹陷：部件有变形的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤和凹陷：部件有划伤和凹陷共同存在的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "腐蚀：部件氧化，一直生锈到部件内部的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 34://右后门
                model.name = "右后门";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "凹陷：部件有变形的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤和凹陷：部件有划伤和凹陷共同存在的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "腐蚀：部件氧化，一直生锈到部件内部的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 35://左前翼子外板
                model.name = "左前翼子外板";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "凹陷：部件有变形的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤和凹陷：部件有划伤和凹陷共同存在的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "腐蚀：部件氧化，一直生锈到部件内部的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 36://右前翼子外板
                model.name = "右前翼子外板";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "凹陷：部件有变形的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤和凹陷：部件有划伤和凹陷共同存在的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "腐蚀：部件氧化，一直生锈到部件内部的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 37://左后翼子外板
                model.name = "左后翼子外板";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "凹陷：部件有变形的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤和凹陷：部件有划伤和凹陷共同存在的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "腐蚀：部件氧化，一直生锈到部件内部的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 38://右后翼子外板
                model.name = "右后翼子外板";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "凹陷：部件有变形的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤和凹陷：部件有划伤和凹陷共同存在的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "腐蚀：部件氧化，一直生锈到部件内部的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 39://车顶
                model.name = "车顶";
                history = new DefectTypeModel("历史缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "修复好：修复部件与出厂标准一致。"));
                levellist.add(new DefectLevelItemModel(true, "修复差：修复部件有颜色模糊/色差/无光泽/金属颗粒度不一致/橘皮/砂眼/爆漆/飞漆/挂流的痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾做漆：部件存在重新喷漆/重新电镀的油漆维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤：有无法抛光去除的部件表面脱落/被划伤/用指甲一碰就脱落/生锈/磨损/锭线的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "凹陷：部件有变形的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "划伤和凹陷：部件有划伤和凹陷共同存在的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "腐蚀：部件氧化，一直生锈到部件内部的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 40://中网
                model.name = "中网";
                history = new DefectTypeModel("历史缺陷");

                nameModel = new DefectNameModel(false, "曾修复", true, null);
                history.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "原厂件：由汽车生产厂家直接生产或者授权委托企业生产的配件。"));
                levellist.add(new DefectLevelItemModel(false, "副厂件：没有得到汽车生产厂家授权许可的企业所生产的配件。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需修复：不更换部件总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "安装不良：部件安装间隙与出厂标准不同/松懈的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "破裂：部件有破碎/裂缝/穿洞的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺失", levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;

            default:
                model.name = "数据待添加";
                break;
        }

    }

    private static void makeZhuangZhiDefect(DefectDepartModel model, int y) {
        DefectTypeModel current, history;
        DefectNameModel nameModel;
        DefectLevelModel levelModel;
        ArrayList<DefectLevelItemModel> levellist;
        ArrayList<DefectLevelModel> levelModelList;
        switch (y) {
            case 0:// 前避震器
                model.name = "前避震器";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "漏油：部件上有因渗油形成的油垢/漏油的情况。", null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "异响：部件工作时发出不正常声音的情况。", null);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;

            case 1://仪表盘
                model.name = "仪表盘";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "显示残缺：部件显示的图形/字母/文字有残缺的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 2://座椅
                model.name = "座椅";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", true, levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 3://大灯清洗器
                model.name = "大灯清洗器";
                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", true, levelModelList);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 4://驱动系统
                model.name = "驱动系统";

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(true, "漏油：部件上有因渗油形成的油垢/漏油的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "异响：部件工作时发出不正常声音的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 5://车窗
                model.name = "车窗";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", true, levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 6://悬挂系统
                model.name = "悬挂系统";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "异响：部件工作时发出不正常声音的情况。", null);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 7://转向系统
                model.name = "转向系统";
                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(true, "漏油：部件上有因渗油形成的油垢/漏油的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "异响：部件工作时发出不正常声音的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "转向自由行程过大：在转向轮不动的前提下，方向盘旋转角度过大的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "转向沉重：在无转向助力的前提下，方向盘旋转沉重的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "转向振动：方向盘旋转时有明显振动的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 8://显示屏
                model.name = "显示屏";

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "显示残缺：部件显示的图形/字母/文字有残缺的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 9://安全气囊/气帘
                model.name = "安全气囊/气帘";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "气囊/气帘警告灯亮：在发动机运转中，气囊/气帘警告灯点亮的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "需更换：更换总成的维修工艺。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺失：应当装备的总成件欠缺的情况。", true, levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 10://音响系统
                model.name = "音响系统";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", null);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 11:// 起动系统
                model.name = "起动系统";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "起动困难：不能一次成功起动发动机/需踩油门辅助才能成功起动发动机的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "马达空转：接通起动开关后，只有起动马达快速旋转而发动机曲轴不转的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "马达常转：即使发动机正常起动运转后，起动马达也会常转不停的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "飞轮啮合异响：起动马达齿轮与飞轮齿圈啮合时发出不正常声音的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "锁芯坏：因锁芯原因造成装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "充电警告灯亮：在发动机运转中，充电警告灯点亮的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 12://后/内视镜
                model.name = "后/内视镜";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "显示残缺：部件显示的图形/字母/文字有残缺的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", true, levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 13:// 发动机
                model.name = "发动机";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(false, "曾开缸：发动机存在缸盖螺丝拆装/缸盖密封胶重打的机修记录。", null);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(true, "漏油：部件上有因渗油形成的油垢/漏油的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "漏水：部件有漏水的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "怠速不稳：发动机转速表指针出现抖动的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "怠速过高：热车时发动机转速高于正常怠速值的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "发动机警告灯亮：在发动机运转中，发动机警告灯点亮的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "机油压力警告灯亮：在发动机运转中，机油压力警告灯点亮的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "皮带轮组件异响：在发动机运转中，发动机各组皮带轮之间发出不正常声音的情况。", null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "配气机构异响：在发动机运转中，配气机构发出不正常声音的情况。", null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "缸体内部异响：在发动机运转中，缸体内部发出不正常声音的情况。", null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "烧机油：在发动机运转中，排气管冒蓝烟的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "燃烧不充分：在发动机运转中，排气管冒黑烟的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "急加速无力：在发动机运转中，急踩油门但发动机转速上升反映迟钝的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "汽缸床坏：在发动机运转中打开水箱盖有喷水/气泡并且排气管冒白烟的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "窜气：在发动机运转中打开加机油口盖，有大量气体冒出的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "缺缸：在发动机怠速状态下车身明显抖动，同时听到排气管放炮声音的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 14://排气系统
                model.name = "排气系统";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "漏气：部件有漏气的情况。", null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "三元催化器坏：加速时部件有金属颗粒互相撞击声音的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "消声器坏：在未改装排气系统的前提下，排气管发出噪音的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "排气管坏：部件有破裂/腐蚀的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;

            case 15://锁止装置
                model.name = "锁止装置";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "锁芯坏：因锁芯原因造成装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "钥匙坏：部件有损伤/不工作的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", true, levelModelList);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 16://抬头数字显示
                model.name = "抬头数字显示";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", true, levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 17://空调系统
                model.name = "空调系统";

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "异响：部件工作时发出不正常声音的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "不制冷：打开汽车空调和温度调节最冷时，出风口温度与外界温度一致的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "无暖风：热车状态打开鼓风机和温度调节最热时，出风口温度与外界温度一致的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "不出风：打开鼓风机时，出风口无风的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "压缩机坏：部件有异响/不工作的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 18://底盘升降
                model.name = "底盘升降";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", true, levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 19://变速器
                model.name = "变速器";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(true, "曾修复：部件存在修复的机修记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(true, "漏油：部件上有因渗油形成的油垢/漏油的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "异响：部件工作时发出不正常声音的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "跳档：汽车在行驶中变速杆自动跳回空档位置的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "乱档：变速杆不能挂入所需要的档位/挂入后不能摘档的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "变速杆松旷：变速杆有松旷的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "换档困难：变速箱挡位不能顺利切换的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "入档冲击：启动引擎、怠速、踩住刹车、入档有强烈冲击感觉的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "入档延迟：启动引擎、怠速、入档、放开刹车踏板，超过2秒钟车辆未起步的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "不能换档：变速箱挡位不能切换的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 20://进气系统
                model.name = "进气系统";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "漏气：部件有漏气的情况。", null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "管路破裂：油管/气管/水管有破裂的情况。", null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "节气门警告灯亮：在发动机运转中，节气门警告灯点亮的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "预热警告灯亮：在发动机热车运转中，预热警告灯点亮的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "增压器坏：部件有漏油/异响/不工作的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;


            case 21://电动门/盖
                model.name = "电动门/盖";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", true, levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 22://遮阳帘
                model.name = "遮阳帘";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", true, levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 23://方向盘
                model.name = "方向盘";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", true, levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 24://人机交互系统
                model.name = "人机交互系统";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", true, levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 25:// 后避震器
                model.name = "后避震器";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "漏油：部件上有因渗油形成的油垢/漏油的情况。", null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "异响：部件工作时发出不正常声音的情况。", null);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 26://天窗
                model.name = "天窗";
                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", true, levelModelList);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 27://雨刮系统
                model.name = "雨刮系统";
                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", true, levelModelList);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 28://灯光系统
                model.name = "灯光系统";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", null);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;

            case 29://制动系统
                model.name = "制动系统";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(true, "曾改装：部件存在与出厂标准不同的改装记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(true, "漏油：部件上有因渗油形成的油垢/漏油的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "制动警告灯亮：在发动机运转中，制动警告灯点亮的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "ABS警告灯亮：在发动机运转中，ABS警告灯点亮的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "制动自由行程过大：在制动片与制动盘/鼓未接触的前提下，制动踏板踩踏行程过大的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "踩踏无力：踩下踏板时脚上感觉无阻力的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "踩踏过硬：踩下踏板时脚上感觉无法踩到底的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "踏板不回位：踩下踏板并放开时，踏板保持不动的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "踏板自动下移：踩下踏板并维持时，踏板自动下移的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "驻车失效：使用驻车装置时，车辆有溜坡的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "制动器严重磨损：制动片/制动盘严重磨损的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 30://冷却系统
                model.name = "冷却系统";
                history = new DefectTypeModel("历史缺陷");
                nameModel = new DefectNameModel(true, "曾修复：部件存在修复的机修记录。", null);
                history.mDefectNameList.add(nameModel);

                current = new DefectTypeModel("现有缺陷");
                nameModel = new DefectNameModel(true, "漏水：部件有漏水的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "水温过高：发动机水温高于出厂标准的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "水泵坏：部件有异响/漏水/不工作的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "风扇坏：部件有异响/不工作的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 31://自动吸合门
                model.name = "自动吸合门";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", true, levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;
            case 32://折叠车顶
                model.name = "折叠车顶";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不影响：不影响使用功能。"));
                levellist.add(new DefectLevelItemModel(true, "有影响：影响使用功能。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "缺附件：应当装备的分总成件欠缺的情况。", true, levelModelList);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(false, "动作不良：部件装置功能失效的情况。", true, null);
                current.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "破裂：部件有破碎/裂缝/穿洞的情况。", true, null);
                current.mDefectNameList.add(nameModel);

                model.currentDefectModel = current;
                break;

            default:
                model.name = "数据待添加";
                current = new DefectTypeModel("数据待添加");
                break;
        }

    }

    private static void makeGujiaDefect(DefectDepartModel model, int y) {
        DefectTypeModel current, history;
        DefectNameModel nameModel;
        DefectLevelModel levelModel;
        ArrayList<DefectLevelItemModel> levellist;
        ArrayList<DefectLevelModel> levelModelList;
        switch (y) {
            case 0:// 左前灯架
                model.name = "左前灯架";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 1:// 右前灯架
                model.name = "右前灯架";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 2:// 左尾灯架
                model.name = "左尾灯架";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 3:// 右尾灯架
                model.name = "右尾灯架";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 4:// 前横梁
                model.name = "前横梁";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 5:// 后横梁
                model.name = "后横梁";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 6:// 水箱框架
                model.name = "水箱框架";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 7:// 左前翼子内板
                model.name = "左前翼子内板";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 8:// 右前翼子内板
                model.name = "右前翼子内板";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 9:// 左后翼子内板
                model.name = "左后翼子内板";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 10:// 右后翼子内板
                model.name = "右后翼子内板";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 11:// 左前纵梁
                model.name = "左前纵梁";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 12:// 右前纵梁
                model.name = "右前纵梁";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 13:// 左后纵梁
                model.name = "左后纵梁";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 14:// 右后纵梁
                model.name = "右后纵梁";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 15:// 左上边梁
                model.name = "左上边梁";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 16:// 右上边梁
                model.name = "右上边梁";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 17:// 左下边梁
                model.name = "左下边梁";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 18:// 右下边梁
                model.name = "右下边梁";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 19:// 乘员舱底板梁
                model.name = "乘员舱底板梁";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 20:// 左A柱
                model.name = "左A柱";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 21:// 右A柱
                model.name = "右A柱";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 22:// 左B柱
                model.name = "左B柱";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 23:// 右B柱
                model.name = "右B柱";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 24:// 左C柱
                model.name = "左C柱";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 25:// 右C柱
                model.name = "右C柱";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 26:// 左D柱
                model.name = "左D柱";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 27:// 右D柱
                model.name = "右D柱";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 28:// 尾箱底板
                model.name = "尾箱底板";
                current = new DefectTypeModel("现有缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "腐蚀：部件氧化，一直生锈到部件内部的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 29:// 后围板
                model.name = "后围板";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 30:// 前防火墙
                model.name = "前防火墙";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;

                break;
            case 31:// 后防火墙
                model.name = "后防火墙";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 32:// 后窗台
                model.name = "后窗台";
                current = new DefectTypeModel("现有缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;
            case 33:// 乘员舱底板
                model.name = "乘员舱底板";
                current = new DefectTypeModel("现有缺陷");

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "腐蚀：部件氧化，一直生锈到部件内部的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "轻度：叠加区域在拇指大小以下。"));
                levellist.add(new DefectLevelItemModel(false, "中度：叠加区域在拇指大小～拳头大小。"));
                levellist.add(new DefectLevelItemModel(false, "重度：叠加区域在拳头大小以上。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "变形：部件内部有相对移位/密封胶开裂/密封胶缺失的情况。", levelModelList);
                current.mDefectNameList.add(nameModel);

                history = new DefectTypeModel("历史缺陷");
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(true, "不切割整形：部件有锤击/拉伸/校正固定/筋线不一致/安装不良的钣金痕迹。"));
                levellist.add(new DefectLevelItemModel(true, "切割整形：部件有密封胶重打/密封胶修补/某些焊点与原厂焊点不一致/焊补的钣金痕迹。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList = new ArrayList<DefectLevelModel>();
                levelModelList.add(levelModel);
                levellist = new ArrayList<DefectLevelItemModel>();
                levellist.add(new DefectLevelItemModel(false, "A级：未到牛腿/未到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "B级：已到牛腿/已到备胎窟中部。"));
                levellist.add(new DefectLevelItemModel(false, "C级：已到前防火墙/已到后防火墙。"));
                levelModel = new DefectLevelModel(levellist);
                levelModelList.add(levelModel);
                nameModel = new DefectNameModel(false, "曾整形：部件存在钣金维修记录。", false, levelModelList);
                history.mDefectNameList.add(nameModel);
                nameModel = new DefectNameModel(true, "曾更换：部件存在更换记录。", null);
                history.mDefectNameList.add(nameModel);

                model.historyDefectModel = history;
                model.currentDefectModel = current;
                break;

            default:
                break;
        }
    }
}
