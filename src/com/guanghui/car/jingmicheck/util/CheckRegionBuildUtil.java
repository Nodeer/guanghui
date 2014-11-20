package com.guanghui.car.jingmicheck.util;

import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;

import com.guanghui.car.jingmicheck.model.CheckItem;
import com.guanghui.car.jingmicheck.model.DefectDepartModel;

/**
 * 创建热区的工具类
 */
public class CheckRegionBuildUtil {
    /**以下6个尺寸对应检测页的布局xml文件中,checkview的大小.如果因为页面调整改变了checkview的大小,一定要把下面的进行对应修改*/
    public static final int neishi_width = 780;
    public static final int neishi_height = 732;
    public static final int gujia_width = 780;
    public static final int gujia_height = 463;
    public static final int waiguan_width = 600;
    public static final int waiguan_height = 785;


    public static CheckItem makeCheckItem(DefectDepartModel model) {

        CheckItem item = new CheckItem(model.areaIndex, model.departIndex, model);
        switch (model.areaIndex) {
            case 0:
                createWaiguanCheckItem(model.departIndex, item);
                break;
            case 1:
                createNeiShiCheckItem(model.departIndex, item);
                break;
            case 2:
                createNeiShiCheckItem(model.departIndex, item);
                break;
            case 3:
                createNeiShiCheckItem(model.departIndex, item);
                break;
            default:
                break;
        }

        return item;
    }

    public static CheckItem makeGujiaCheckItem(DefectDepartModel model, boolean isLeft) {
        CheckItem item = new CheckItem(model.areaIndex, model.departIndex, model);
        if (isLeft) {
            createGujiaLeftCheckItem(model.departIndex, item);
        } else {
            createGujiaRightCheckItem(model.departIndex, item);
        }
        return item;
    }


    private static void createNeiShiCheckItem(int departIndex, CheckItem item) {
        Path p = new Path();
        Region r;
        RectF f = new RectF();
        switch (departIndex) {
            case 0://仪表台
                p.moveTo(getNeishi_width(0.310f), getNeishi_height(0.090f));
                p.lineTo(getNeishi_width(0.315f), getNeishi_height(0.197f));
                p.lineTo(getNeishi_width(0.681f), getNeishi_height(0.197f));
                p.lineTo(getNeishi_width(0.688f), getNeishi_height(0.079f));
                p.lineTo(getNeishi_width(0.610f), getNeishi_height(0.029f));
                p.lineTo(getNeishi_width(0.436f), getNeishi_height(0.019f));
                item.imgX = getNeishi_width(0.644f);
                item.imgY = getNeishi_height(0.096f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 1:// 中控台
                p.moveTo(getNeishi_width(0.467f), getNeishi_height(0.053f));
                p.lineTo(getNeishi_width(0.465f), getNeishi_height(0.190f));
                p.lineTo(getNeishi_width(0.532f), getNeishi_height(0.187f));
                p.lineTo(getNeishi_width(0.531f), getNeishi_height(0.055f));
                item.imgX = getNeishi_width(0.497f);
                item.imgY = getNeishi_height(0.117f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 2:// 方向盘
                p.moveTo(getNeishi_width(0.345f), getNeishi_height(0.199f));
                p.lineTo(getNeishi_width(0.346f), getNeishi_height(0.276f));
                p.lineTo(getNeishi_width(0.433f), getNeishi_height(0.277f));
                p.lineTo(getNeishi_width(0.433f), getNeishi_height(0.199f));
                item.imgX = getNeishi_width(0.392f);
                item.imgY = getNeishi_height(0.235f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 3:// 变速杆
                p.moveTo(getNeishi_width(0.474f), getNeishi_height(0.348f));
                p.lineTo(getNeishi_width(0.474f), getNeishi_height(0.430f));
                p.lineTo(getNeishi_width(0.523f), getNeishi_height(0.426f));
                p.lineTo(getNeishi_width(0.526f), getNeishi_height(0.348f));
                item.imgX = getNeishi_width(0.499f);
                item.imgY = getNeishi_height(0.395f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 4://储物箱
                p.moveTo(getNeishi_width(0.553f), getNeishi_height(0.183f));
                p.lineTo(getNeishi_width(0.587f), getNeishi_height(0.131f));
                p.lineTo(getNeishi_width(0.637f), getNeishi_height(0.133f));
                p.lineTo(getNeishi_width(0.673f), getNeishi_height(0.183f));
                item.imgX = getNeishi_width(0.610f);
                item.imgY = getNeishi_height(0.152f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 5://主驾座椅
                p.moveTo(getNeishi_width(0.322f), getNeishi_height(0.292f));
                p.lineTo(getNeishi_width(0.322f), getNeishi_height(0.571f));
                p.lineTo(getNeishi_width(0.460f), getNeishi_height(0.574f));
                p.lineTo(getNeishi_width(0.460f), getNeishi_height(0.292f));
                item.imgX = getNeishi_width(0.395f);
                item.imgY = getNeishi_height(0.358f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 6://副驾座椅
                p.moveTo(getNeishi_width(0.537f), getNeishi_height(0.292f));
                p.lineTo(getNeishi_width(0.540f), getNeishi_height(0.571f));
                p.lineTo(getNeishi_width(0.679f), getNeishi_height(0.571f));
                p.lineTo(getNeishi_width(0.681f), getNeishi_height(0.292f));
                item.imgX = getNeishi_width(0.612f);
                item.imgY = getNeishi_height(0.358f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 7://后排座椅
                p.moveTo(getNeishi_width(0.322f), getNeishi_height(0.628f));
                p.lineTo(getNeishi_width(0.336f), getNeishi_height(0.900f));
                p.lineTo(getNeishi_width(0.671f), getNeishi_height(0.898f));
                p.lineTo(getNeishi_width(0.678f), getNeishi_height(0.622f));
                item.imgX = getNeishi_width(0.585f);
                item.imgY = getNeishi_height(0.709f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 8://左前门内饰
                p.moveTo(getNeishi_width(0.019f), getNeishi_height(0.107f));
                p.lineTo(getNeishi_width(0.049f), getNeishi_height(0.342f));
                p.lineTo(getNeishi_width(0.212f), getNeishi_height(0.321f));
                p.lineTo(getNeishi_width(0.217f), getNeishi_height(0.214f));
                p.lineTo(getNeishi_width(0.123f), getNeishi_height(0.115f));
                item.imgX = getNeishi_width(0.097f);
                item.imgY = getNeishi_height(0.180f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 9://右前门内饰
                p.moveTo(getNeishi_width(0.860f), getNeishi_height(0.112f));
                p.lineTo(getNeishi_width(0.760f), getNeishi_height(0.221f));
                p.lineTo(getNeishi_width(0.762f), getNeishi_height(0.332f));
                p.lineTo(getNeishi_width(0.936f), getNeishi_height(0.342f));
                p.lineTo(getNeishi_width(0.962f), getNeishi_height(0.107f));
                item.imgX = getNeishi_width(0.872f);
                item.imgY = getNeishi_height(0.169f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 10://左后门内饰
                p.moveTo(getNeishi_width(0.060f), getNeishi_height(0.459f));
                p.lineTo(getNeishi_width(0.013f), getNeishi_height(0.577f));
                p.lineTo(getNeishi_width(0.132f), getNeishi_height(0.706f));
                p.lineTo(getNeishi_width(0.219f), getNeishi_height(0.684f));
                p.lineTo(getNeishi_width(0.204f), getNeishi_height(0.458f));
                item.imgX = getNeishi_width(0.147f);
                item.imgY = getNeishi_height(0.527f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 11://右后门内饰
                p.moveTo(getNeishi_width(0.778f), getNeishi_height(0.459f));
                p.lineTo(getNeishi_width(0.769f), getNeishi_height(0.690f));
                p.lineTo(getNeishi_width(0.856f), getNeishi_height(0.708f));
                p.lineTo(getNeishi_width(0.967f), getNeishi_height(0.581f));
                p.lineTo(getNeishi_width(0.926f), getNeishi_height(0.454f));
                item.imgX = getNeishi_width(0.838f);
                item.imgY = getNeishi_height(0.514f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 12://车顶内饰
                p.moveTo(getNeishi_width(0.051f), getNeishi_height(0.858f));
                p.lineTo(getNeishi_width(0.050f), getNeishi_height(0.988f));
                p.lineTo(getNeishi_width(0.227f), getNeishi_height(0.985f));
                p.lineTo(getNeishi_width(0.224f), getNeishi_height(0.854f));
                item.imgX = getNeishi_width(0.191f);
                item.imgY = getNeishi_height(0.926f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 13://左A柱内饰
                p.moveTo(getNeishi_width(0.038f), getNeishi_height(0.011f));
                p.lineTo(getNeishi_width(0.068f), getNeishi_height(0.087f));
                p.lineTo(getNeishi_width(0.201f), getNeishi_height(0.046f));
                p.lineTo(getNeishi_width(0.181f), getNeishi_height(0.007f));
                item.imgX = getNeishi_width(0.105f);
                item.imgY = getNeishi_height(0.038f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 14://右A柱内饰
                p.moveTo(getNeishi_width(0.797f), getNeishi_height(0.005f));
                p.lineTo(getNeishi_width(0.785f), getNeishi_height(0.042f));
                p.lineTo(getNeishi_width(0.909f), getNeishi_height(0.083f));
                p.lineTo(getNeishi_width(0.945f), getNeishi_height(0.011f));
                item.imgX = getNeishi_width(0.869f);
                item.imgY = getNeishi_height(0.031f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 15://左B柱内饰
                p.moveTo(getNeishi_width(0.044f), getNeishi_height(0.363f));
                p.lineTo(getNeishi_width(0.072f), getNeishi_height(0.447f));
                p.lineTo(getNeishi_width(0.195f), getNeishi_height(0.418f));
                p.lineTo(getNeishi_width(0.174f), getNeishi_height(0.372f));
                item.imgX = getNeishi_width(0.083f);
                item.imgY = getNeishi_height(0.410f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 16://右B柱内饰
                p.moveTo(getNeishi_width(0.794f), getNeishi_height(0.378f));
                p.lineTo(getNeishi_width(0.794f), getNeishi_height(0.418f));
                p.lineTo(getNeishi_width(0.909f), getNeishi_height(0.443f));
                p.lineTo(getNeishi_width(0.935f), getNeishi_height(0.366f));
                item.imgX = getNeishi_width(0.883f);
                item.imgY = getNeishi_height(0.404f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 17://左C/D柱内饰
                p.moveTo(getNeishi_width(0.047f), getNeishi_height(0.742f));
                p.lineTo(getNeishi_width(0.090f), getNeishi_height(0.822f));
                p.lineTo(getNeishi_width(0.181f), getNeishi_height(0.809f));
                p.lineTo(getNeishi_width(0.192f), getNeishi_height(0.747f));
                item.imgX = getNeishi_width(0.122f);
                item.imgY = getNeishi_height(0.788f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 18://右C/D柱内饰
                p.moveTo(getNeishi_width(0.787f), getNeishi_height(0.731f));
                p.lineTo(getNeishi_width(0.813f), getNeishi_height(0.788f));
                p.lineTo(getNeishi_width(0.894f), getNeishi_height(0.799f));
                p.lineTo(getNeishi_width(0.938f), getNeishi_height(0.720f));
                item.imgX = getNeishi_width(0.860f);
                item.imgY = getNeishi_height(0.757f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 19://尾箱底板内饰
                p.moveTo(getNeishi_width(0.760f), getNeishi_height(0.831f));
                p.lineTo(getNeishi_width(0.754f), getNeishi_height(0.982f));
                p.lineTo(getNeishi_width(0.946f), getNeishi_height(0.981f));
                p.lineTo(getNeishi_width(0.932f), getNeishi_height(0.835f));
                item.imgX = getNeishi_width(0.910f);
                item.imgY = getNeishi_height(0.910f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 20://后窗台内饰
                p.moveTo(getNeishi_width(0.319f), getNeishi_height(0.892f));
                p.lineTo(getNeishi_width(0.387f), getNeishi_height(0.977f));
                p.lineTo(getNeishi_width(0.613f), getNeishi_height(0.974f));
                p.lineTo(getNeishi_width(0.685f), getNeishi_height(0.889f));
                item.imgX = getNeishi_width(0.497f);
                item.imgY = getNeishi_height(0.940f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 21://乘员舱底板内饰
                p.moveTo(getNeishi_width(0.540f), getNeishi_height(0.202f));
                p.lineTo(getNeishi_width(0.528f), getNeishi_height(0.295f));
                p.lineTo(getNeishi_width(0.687f), getNeishi_height(0.276f));
                p.lineTo(getNeishi_width(0.687f), getNeishi_height(0.201f));
                item.imgX = getNeishi_width(0.612f);
                item.imgY = getNeishi_height(0.240f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 22://前座中央扶手
                p.moveTo(getNeishi_width(0.467f), getNeishi_height(0.439f));
                p.lineTo(getNeishi_width(0.469f), getNeishi_height(0.577f));
                p.lineTo(getNeishi_width(0.532f), getNeishi_height(0.575f));
                p.lineTo(getNeishi_width(0.531f), getNeishi_height(0.440f));
                item.imgX = getNeishi_width(0.500f);
                item.imgY = getNeishi_height(0.507f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 23://后座中央扶手
                p.moveTo(getNeishi_width(0.469f), getNeishi_height(0.628f));
                p.lineTo(getNeishi_width(0.469f), getNeishi_height(0.777f));
                p.lineTo(getNeishi_width(0.533f), getNeishi_height(0.775f));
                p.lineTo(getNeishi_width(0.531f), getNeishi_height(0.628f));
                item.imgX = getNeishi_width(0.500f);
                item.imgY = getNeishi_height(0.704f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 24://内视镜
                p.moveTo(getNeishi_width(0.460f), getNeishi_height(0.198f));
                p.lineTo(getNeishi_width(0.469f), getNeishi_height(0.250f));
                p.lineTo(getNeishi_width(0.527f), getNeishi_height(0.250f));
                p.lineTo(getNeishi_width(0.537f), getNeishi_height(0.202f));
                item.imgX = getNeishi_width(0.499f);
                item.imgY = getNeishi_height(0.219f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 25://地控台
                p.moveTo(getNeishi_width(0.474f), getNeishi_height(0.260f));
                p.lineTo(getNeishi_width(0.474f), getNeishi_height(0.347f));
                p.lineTo(getNeishi_width(0.524f), getNeishi_height(0.346f));
                p.lineTo(getNeishi_width(0.526f), getNeishi_height(0.262f));
                item.imgX = getNeishi_width(0.499f);
                item.imgY = getNeishi_height(0.303f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;

            default:
                break;
        }

    }

    private static void createGujiaLeftCheckItem(int departIndex, CheckItem item) {
        Path p = new Path();
        Region r;
        RectF f = new RectF();
        switch (departIndex) {
            case 0://左前灯架
                p.moveTo(getGujia_width(0.277f), getGujia_height(0.644f));
                p.lineTo(getGujia_width(0.264f), getGujia_height(0.801f));
                p.lineTo(getGujia_width(0.335f), getGujia_height(0.838f));
                p.lineTo(getGujia_width(0.385f), getGujia_height(0.793f));
                p.lineTo(getGujia_width(0.387f), getGujia_height(0.654f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.326f);
                item.imgY = getGujia_height(0.734f);
                item.path = p;
                item.region = r;
                break;
            case 1://右前灯架
                p.moveTo(getGujia_width(0.067f), getGujia_height(0.492f));
                p.lineTo(getGujia_width(0.031f), getGujia_height(0.525f));
                p.lineTo(getGujia_width(0.031f), getGujia_height(0.646f));
                p.lineTo(getGujia_width(0.065f), getGujia_height(0.685f));
                p.lineTo(getGujia_width(0.076f), getGujia_height(0.551f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.053f);
                item.imgY = getGujia_height(0.585f);
                item.path = p;
                item.region = r;
                break;
            case 4://前横梁
                p.moveTo(getGujia_width(0.005f), getGujia_height(0.683f));
                p.lineTo(getGujia_width(0.005f), getGujia_height(0.765f));
                p.lineTo(getGujia_width(0.141f), getGujia_height(0.903f));
                p.lineTo(getGujia_width(0.306f), getGujia_height(0.965f));
                p.lineTo(getGujia_width(0.335f), getGujia_height(0.870f));
                p.lineTo(getGujia_width(0.173f), getGujia_height(0.797f));
                p.lineTo(getGujia_width(0.091f), getGujia_height(0.739f));
                p.lineTo(getGujia_width(0.033f), getGujia_height(0.676f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.153f);
                item.imgY = getGujia_height(0.847f);
                item.path = p;
                item.region = r;
                break;
            case 6://水箱框架
                p.moveTo(getGujia_width(0.094f), getGujia_height(0.538f));
                p.lineTo(getGujia_width(0.068f), getGujia_height(0.672f));
                p.lineTo(getGujia_width(0.101f), getGujia_height(0.680f));
                p.lineTo(getGujia_width(0.114f), getGujia_height(0.611f));
                p.lineTo(getGujia_width(0.205f), getGujia_height(0.667f));
                p.lineTo(getGujia_width(0.260f), getGujia_height(0.795f));
                p.lineTo(getGujia_width(0.255f), getGujia_height(0.635f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.173f);
                item.imgY = getGujia_height(0.620f);
                item.path = p;
                item.region = r;
                break;
            case 7://左前翼子内板
                p.moveTo(getGujia_width(0.388f), getGujia_height(0.611f));
                p.lineTo(getGujia_width(0.392f), getGujia_height(0.771f));
                p.lineTo(getGujia_width(0.527f), getGujia_height(0.663f));
                p.lineTo(getGujia_width(0.576f), getGujia_height(0.780f));
                p.lineTo(getGujia_width(0.618f), getGujia_height(0.518f));
                p.lineTo(getGujia_width(0.583f), getGujia_height(0.499f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.517f);
                item.imgY = getGujia_height(0.605f);
                item.path = p;
                item.region = r;
                break;
            case 8://右前翼子内板
                p.moveTo(getGujia_width(0.074f), getGujia_height(0.486f));
                p.lineTo(getGujia_width(0.106f), getGujia_height(0.542f));
                p.lineTo(getGujia_width(0.210f), getGujia_height(0.562f));
                p.lineTo(getGujia_width(0.246f), getGujia_height(0.542f));
                p.lineTo(getGujia_width(0.244f), getGujia_height(0.371f));
                p.lineTo(getGujia_width(0.221f), getGujia_height(0.359f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.172f);
                item.imgY = getGujia_height(0.451f);
                item.path = p;
                item.region = r;
                break;
            case 11://左前纵梁
                p.moveTo(getGujia_width(0.337f), getGujia_height(0.836f));
                p.lineTo(getGujia_width(0.340f), getGujia_height(0.935f));
                p.lineTo(getGujia_width(0.559f), getGujia_height(0.760f));
                p.lineTo(getGujia_width(0.532f), getGujia_height(0.667f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.458f);
                item.imgY = getGujia_height(0.780f);
                item.path = p;
                item.region = r;
                break;
            case 12://右前纵梁
                p.moveTo(getGujia_width(0.115f), getGujia_height(0.624f));
                p.lineTo(getGujia_width(0.101f), getGujia_height(0.726f));
                p.lineTo(getGujia_width(0.295f), getGujia_height(0.613f));
                p.lineTo(getGujia_width(0.295f), getGujia_height(0.551f));
                p.lineTo(getGujia_width(0.260f), getGujia_height(0.538f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.258f);
                item.imgY = getGujia_height(0.581f);
                item.path = p;
                item.region = r;
                break;
            case 15://左上边梁
                p.moveTo(getGujia_width(0.688f), getGujia_height(0.123f));
                p.lineTo(getGujia_width(0.714f), getGujia_height(0.171f));
                p.lineTo(getGujia_width(0.853f), getGujia_height(0.102f));
                p.lineTo(getGujia_width(0.855f), getGujia_height(0.076f));
                p.lineTo(getGujia_width(0.771f), getGujia_height(0.076f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.779f);
                item.imgY = getGujia_height(0.097f);
                item.path = p;
                item.region = r;
                break;
            case 17://左下边梁
                p.moveTo(getGujia_width(0.646f), getGujia_height(0.695f));
                p.lineTo(getGujia_width(0.644f), getGujia_height(0.743f));
                p.lineTo(getGujia_width(0.891f), getGujia_height(0.546f));
                p.lineTo(getGujia_width(0.869f), getGujia_height(0.499f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.714f);
                item.imgY = getGujia_height(0.667f);
                item.path = p;
                item.region = r;
                break;
            case 19://乘员舱底板梁
                p.moveTo(getGujia_width(0.644f), getGujia_height(0.529f));
                p.lineTo(getGujia_width(0.645f), getGujia_height(0.603f));
                p.lineTo(getGujia_width(0.850f), getGujia_height(0.434f));
                p.lineTo(getGujia_width(0.800f), getGujia_height(0.410f));
                item.imgX = getGujia_width(0.702f);
                item.imgY = getGujia_height(0.496f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 20://左A柱
                p.moveTo(getGujia_width(0.678f), getGujia_height(0.123f));
                p.lineTo(getGujia_width(0.603f), getGujia_height(0.505f));
                p.lineTo(getGujia_width(0.645f), getGujia_height(0.538f));
                p.lineTo(getGujia_width(0.708f), getGujia_height(0.168f));
                item.imgX = getGujia_width(0.655f);
                item.imgY = getGujia_height(0.317f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 22://左B柱
                p.moveTo(getGujia_width(0.750f), getGujia_height(0.143f));
                p.lineTo(getGujia_width(0.756f), getGujia_height(0.603f));
                p.lineTo(getGujia_width(0.800f), getGujia_height(0.592f));
                p.lineTo(getGujia_width(0.792f), getGujia_height(0.121f));
                item.imgX = getGujia_width(0.778f);
                item.imgY = getGujia_height(0.359f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 24://左C柱
                p.moveTo(getGujia_width(0.844f), getGujia_height(0.110f));
                p.lineTo(getGujia_width(0.900f), getGujia_height(0.313f));
                p.lineTo(getGujia_width(0.871f), getGujia_height(0.508f));
                p.lineTo(getGujia_width(0.909f), getGujia_height(0.527f));
                p.lineTo(getGujia_width(0.944f), getGujia_height(0.413f));
                p.lineTo(getGujia_width(0.936f), getGujia_height(0.289f));
                p.lineTo(getGujia_width(0.873f), getGujia_height(0.089f));
                item.imgX = getGujia_width(0.922f);
                item.imgY = getGujia_height(0.326f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 30://前防火墙
                p.moveTo(getGujia_width(0.246f), getGujia_height(0.350f));
                p.lineTo(getGujia_width(0.249f), getGujia_height(0.544f));
                p.lineTo(getGujia_width(0.367f), getGujia_height(0.633f));
                p.lineTo(getGujia_width(0.588f), getGujia_height(0.495f));
                item.imgX = getGujia_width(0.371f);
                item.imgY = getGujia_height(0.512f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 33://乘员舱底板
                p.moveTo(getGujia_width(0.688f), getGujia_height(0.389f));
                p.lineTo(getGujia_width(0.646f), getGujia_height(0.499f));
                p.lineTo(getGujia_width(0.646f), getGujia_height(0.698f));
                p.lineTo(getGujia_width(0.871f), getGujia_height(0.508f));
                p.lineTo(getGujia_width(0.871f), getGujia_height(0.436f));
                item.imgX = getGujia_width(0.704f);
                item.imgY = getGujia_height(0.605f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;

            default:
                break;
        }

    }

    private static void createGujiaRightCheckItem(int departIndex, CheckItem item) {

        Path p = new Path();
        Region r;
        RectF f = new RectF();
        switch (departIndex) {
            case 2://左尾灯架
                p.moveTo(getGujia_width(0.074f), getGujia_height(0.432f));
                p.lineTo(getGujia_width(0.042f), getGujia_height(0.460f));
                p.lineTo(getGujia_width(0.050f), getGujia_height(0.587f));
                p.lineTo(getGujia_width(0.085f), getGujia_height(0.620f));
                p.lineTo(getGujia_width(0.097f), getGujia_height(0.592f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.073f);
                item.imgY = getGujia_height(0.536f);
                item.path = p;
                item.region = r;
                break;
            case 3://右尾灯架
                p.moveTo(getGujia_width(0.337f), getGujia_height(0.644f));
                p.lineTo(getGujia_width(0.308f), getGujia_height(0.771f));
                p.lineTo(getGujia_width(0.372f), getGujia_height(0.825f));
                p.lineTo(getGujia_width(0.449f), getGujia_height(0.793f));
                p.lineTo(getGujia_width(0.453f), getGujia_height(0.644f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.388f);
                item.imgY = getGujia_height(0.728f);
                item.path = p;
                item.region = r;
                break;
            case 5://后横梁
                p.moveTo(getGujia_width(0.042f), getGujia_height(0.739f));
                p.lineTo(getGujia_width(0.022f), getGujia_height(0.810f));
                p.lineTo(getGujia_width(0.247f), getGujia_height(0.978f));
                p.lineTo(getGujia_width(0.267f), getGujia_height(0.905f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.141f);
                item.imgY = getGujia_height(0.855f);
                item.path = p;
                item.region = r;
                break;
            case 9://左后翼子内板
                p.moveTo(getGujia_width(0.094f), getGujia_height(0.486f));
                p.lineTo(getGujia_width(0.105f), getGujia_height(0.594f));
                p.lineTo(getGujia_width(0.149f), getGujia_height(0.626f));
                p.lineTo(getGujia_width(0.242f), getGujia_height(0.549f));
                p.lineTo(getGujia_width(0.177f), getGujia_height(0.436f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.168f);
                item.imgY = getGujia_height(0.525f);
                item.path = p;
                item.region = r;
                break;
            case 10://右后翼子内板
                p.moveTo(getGujia_width(0.458f), getGujia_height(0.609f));
                p.lineTo(getGujia_width(0.459f), getGujia_height(0.786f));
                p.lineTo(getGujia_width(0.509f), getGujia_height(0.778f));
                p.lineTo(getGujia_width(0.555f), getGujia_height(0.639f));
                p.lineTo(getGujia_width(0.600f), getGujia_height(0.622f));
                p.lineTo(getGujia_width(0.585f), getGujia_height(0.538f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.529f);
                item.imgY = getGujia_height(0.603f);
                item.path = p;
                item.region = r;
                break;
            case 13://左后纵梁
                p.moveTo(getGujia_width(0.046f), getGujia_height(0.734f));
                p.lineTo(getGujia_width(0.112f), getGujia_height(0.788f));
                p.lineTo(getGujia_width(0.158f), getGujia_height(0.754f));
                p.lineTo(getGujia_width(0.090f), getGujia_height(0.713f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.104f);
                item.imgY = getGujia_height(0.752f);
                item.path = p;
                item.region = r;
                break;
            case 14://右后纵梁
                p.moveTo(getGujia_width(0.238f), getGujia_height(0.870f));
                p.lineTo(getGujia_width(0.271f), getGujia_height(0.948f));
                p.lineTo(getGujia_width(0.450f), getGujia_height(0.795f));
                p.lineTo(getGujia_width(0.333f), getGujia_height(0.790f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.341f);
                item.imgY = getGujia_height(0.847f);
                item.path = p;
                item.region = r;
                break;
            case 16://右上边梁
                p.moveTo(getGujia_width(0.578f), getGujia_height(0.216f));
                p.lineTo(getGujia_width(0.595f), getGujia_height(0.171f));
                p.lineTo(getGujia_width(0.735f), getGujia_height(0.076f));
                p.lineTo(getGujia_width(0.773f), getGujia_height(0.097f));
                p.lineTo(getGujia_width(0.774f), getGujia_height(0.123f));
                p.lineTo(getGujia_width(0.653f), getGujia_height(0.184f));
                p.lineTo(getGujia_width(0.624f), getGujia_height(0.231f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.676f);
                item.imgY = getGujia_height(0.132f);
                item.path = p;
                item.region = r;
                break;
            case 18://右下边梁
                p.moveTo(getGujia_width(0.638f), getGujia_height(0.676f));
                p.lineTo(getGujia_width(0.642f), getGujia_height(0.758f));
                p.lineTo(getGujia_width(0.887f), getGujia_height(0.501f));
                p.lineTo(getGujia_width(0.867f), getGujia_height(0.475f));
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.imgX = getGujia_width(0.727f);
                item.imgY = getGujia_height(0.629f);
                item.path = p;
                item.region = r;
                break;
            case 21://右A柱
                p.moveTo(getGujia_width(0.779f), getGujia_height(0.114f));
                p.lineTo(getGujia_width(0.877f), getGujia_height(0.292f));
                p.lineTo(getGujia_width(0.871f), getGujia_height(0.469f));
                p.lineTo(getGujia_width(0.903f), getGujia_height(0.482f));
                p.lineTo(getGujia_width(0.915f), getGujia_height(0.298f));
                p.lineTo(getGujia_width(0.800f), getGujia_height(0.099f));
                item.imgX = getGujia_width(0.892f);
                item.imgY = getGujia_height(0.371f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 23://右B柱
                p.moveTo(getGujia_width(0.690f), getGujia_height(0.158f));
                p.lineTo(getGujia_width(0.759f), getGujia_height(0.557f));
                p.lineTo(getGujia_width(0.799f), getGujia_height(0.566f));
                p.lineTo(getGujia_width(0.726f), getGujia_height(0.149f));
                item.imgX = getGujia_width(0.750f);
                item.imgY = getGujia_height(0.348f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 25://右C柱
                p.moveTo(getGujia_width(0.579f), getGujia_height(0.212f));
                p.lineTo(getGujia_width(0.550f), getGujia_height(0.512f));
                p.lineTo(getGujia_width(0.617f), getGujia_height(0.564f));
                p.lineTo(getGujia_width(0.626f), getGujia_height(0.227f));
                item.imgX = getGujia_width(0.588f);
                item.imgY = getGujia_height(0.374f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 26://左D柱
                p.moveTo(getGujia_width(0.067f), getGujia_height(0.451f));
                p.lineTo(getGujia_width(0.090f), getGujia_height(0.484f));
                p.lineTo(getGujia_width(0.246f), getGujia_height(0.363f));
                p.lineTo(getGujia_width(0.213f), getGujia_height(0.343f));
                item.imgX = getGujia_width(0.132f);
                item.imgY = getGujia_height(0.425f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 27://右D柱
                p.moveTo(getGujia_width(0.355f), getGujia_height(0.629f));
                p.lineTo(getGujia_width(0.382f), getGujia_height(0.659f));
                p.lineTo(getGujia_width(0.569f), getGujia_height(0.536f));
                p.lineTo(getGujia_width(0.521f), getGujia_height(0.510f));
                item.imgX = getGujia_width(0.473f);
                item.imgY = getGujia_height(0.566f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 28://尾箱底板
                p.moveTo(getGujia_width(0.153f), getGujia_height(0.631f));
                p.lineTo(getGujia_width(0.314f), getGujia_height(0.737f));
                p.lineTo(getGujia_width(0.364f), getGujia_height(0.620f));
                p.lineTo(getGujia_width(0.250f), getGujia_height(0.549f));
                item.imgX = getGujia_width(0.260f);
                item.imgY = getGujia_height(0.635f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 29://后围板
                p.moveTo(getGujia_width(0.100f), getGujia_height(0.600f));
                p.lineTo(getGujia_width(0.085f), getGujia_height(0.704f));
                p.lineTo(getGujia_width(0.305f), getGujia_height(0.862f));
                p.lineTo(getGujia_width(0.315f), getGujia_height(0.737f));
                item.imgX = getGujia_width(0.195f);
                item.imgY = getGujia_height(0.721f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 31://后防火墙
                p.moveTo(getGujia_width(0.244f), getGujia_height(0.469f));
                p.lineTo(getGujia_width(0.244f), getGujia_height(0.553f));
                p.lineTo(getGujia_width(0.364f), getGujia_height(0.629f));
                p.lineTo(getGujia_width(0.438f), getGujia_height(0.570f));
                item.imgX = getGujia_width(0.312f);
                item.imgY = getGujia_height(0.544f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 32://后窗台
                p.moveTo(getGujia_width(0.249f), getGujia_height(0.365f));
                p.lineTo(getGujia_width(0.176f), getGujia_height(0.417f));
                p.lineTo(getGujia_width(0.440f), getGujia_height(0.570f));
                p.lineTo(getGujia_width(0.522f), getGujia_height(0.512f));
                item.imgX = getGujia_width(0.342f);
                item.imgY = getGujia_height(0.467f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;

            default:
                break;
        }

    }

    private static void createWaiguanCheckItem(int departIndex, CheckItem item) {
        Path p = new Path();
        Region r;
        RectF f = new RectF();
        //大轮胎半径为高度的0.06,小轮圈半径为高度的0.03
        switch (departIndex) {
            case 0:
                p.addCircle(getWaiguan_width(0.112f), getWaiguan_height(0.264f), getWaiguan_height(0.06f), Path.Direction.CW);
                item.imgX = getWaiguan_width(0.160f);
                item.imgY = getWaiguan_height(0.264f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 1:
                p.addCircle(getWaiguan_width(0.888f), getWaiguan_height(0.264f), getWaiguan_height(0.06f), Path.Direction.CW);
                item.imgX = getWaiguan_width(0.940f);
                item.imgY = getWaiguan_height(0.264f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 2:
                p.addCircle(getWaiguan_width(0.112f), getWaiguan_height(0.755f), getWaiguan_height(0.06f), Path.Direction.CW);
                item.imgX = getWaiguan_width(0.160f);
                item.imgY = getWaiguan_height(0.755f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 3:
                p.addCircle(getWaiguan_width(0.892f), getWaiguan_height(0.757f), getWaiguan_height(0.06f), Path.Direction.CW);
                item.imgX = getWaiguan_width(0.940f);
                item.imgY = getWaiguan_height(0.757f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 4:
                p.addCircle(getWaiguan_width(0.498f), getWaiguan_height(0.791f), getWaiguan_height(0.06f), Path.Direction.CW);
                item.imgX = getWaiguan_width(0.550f);
                item.imgY = getWaiguan_height(0.791f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 5:
                p.addCircle(getWaiguan_width(0.112f), getWaiguan_height(0.264f), getWaiguan_height(0.03f), Path.Direction.CW);
                item.imgX = getWaiguan_width(0.112f);
                item.imgY = getWaiguan_height(0.264f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 6:
                p.addCircle(getWaiguan_width(0.888f), getWaiguan_height(0.262f), getWaiguan_height(0.03f), Path.Direction.CW);
                item.imgX = getWaiguan_width(0.888f);
                item.imgY = getWaiguan_height(0.262f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 7:
                p.addCircle(getWaiguan_width(0.112f), getWaiguan_height(0.755f), getWaiguan_height(0.03f), Path.Direction.CW);
                item.imgX = getWaiguan_width(0.112f);
                item.imgY = getWaiguan_height(0.755f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 8:
                p.addCircle(getWaiguan_width(0.888f), getWaiguan_height(0.757f), getWaiguan_height(0.03f), Path.Direction.CW);
                item.imgX = getWaiguan_width(0.888f);
                item.imgY = getWaiguan_height(0.757f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 9:
                p.addCircle(getWaiguan_width(0.502f), getWaiguan_height(0.792f), getWaiguan_height(0.03f), Path.Direction.CW);
                item.imgX = getWaiguan_width(0.502f);
                item.imgY = getWaiguan_height(0.792f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 10:
                p.moveTo(getWaiguan_width(0.318f), getWaiguan_height(0.101f));
                p.lineTo(getWaiguan_width(0.295f), getWaiguan_height(0.127f));
                p.lineTo(getWaiguan_width(0.298f), getWaiguan_height(0.143f));
                p.lineTo(getWaiguan_width(0.387f), getWaiguan_height(0.118f));
                p.lineTo(getWaiguan_width(0.405f), getWaiguan_height(0.087f));
                item.imgX = getWaiguan_width(0.342f);
                item.imgY = getWaiguan_height(0.112f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 11:
                p.moveTo(getWaiguan_width(0.597f), getWaiguan_height(0.085f));
                p.lineTo(getWaiguan_width(0.598f), getWaiguan_height(0.110f));
                p.lineTo(getWaiguan_width(0.693f), getWaiguan_height(0.140f));
                p.lineTo(getWaiguan_width(0.702f), getWaiguan_height(0.122f));
                p.lineTo(getWaiguan_width(0.685f), getWaiguan_height(0.099f));
                item.imgX = getWaiguan_width(0.660f);
                item.imgY = getWaiguan_height(0.115f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 12:
                p.moveTo(getWaiguan_width(0.297f), getWaiguan_height(0.047f));
                p.lineTo(getWaiguan_width(0.268f), getWaiguan_height(0.068f));
                p.lineTo(getWaiguan_width(0.270f), getWaiguan_height(0.092f));
                p.lineTo(getWaiguan_width(0.318f), getWaiguan_height(0.080f));
                p.lineTo(getWaiguan_width(0.328f), getWaiguan_height(0.047f));
                item.imgX = getWaiguan_width(0.295f);
                item.imgY = getWaiguan_height(0.070f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 13:
                p.moveTo(getWaiguan_width(0.668f), getWaiguan_height(0.038f));
                p.lineTo(getWaiguan_width(0.683f), getWaiguan_height(0.076f));
                p.lineTo(getWaiguan_width(0.730f), getWaiguan_height(0.098f));
                p.lineTo(getWaiguan_width(0.723f), getWaiguan_height(0.060f));
                p.lineTo(getWaiguan_width(0.682f), getWaiguan_height(0.034f));
                item.imgX = getWaiguan_width(0.702f);
                item.imgY = getWaiguan_height(0.068f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 14:
                p.moveTo(getWaiguan_width(0.322f), getWaiguan_height(0.842f));
                p.lineTo(getWaiguan_width(0.305f), getWaiguan_height(0.873f));
                p.lineTo(getWaiguan_width(0.368f), getWaiguan_height(0.889f));
                p.lineTo(getWaiguan_width(0.383f), getWaiguan_height(0.856f));
                item.imgX = getWaiguan_width(0.342f);
                item.imgY = getWaiguan_height(0.864f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 15:
                p.moveTo(getWaiguan_width(0.620f), getWaiguan_height(0.855f));
                p.lineTo(getWaiguan_width(0.633f), getWaiguan_height(0.887f));
                p.lineTo(getWaiguan_width(0.693f), getWaiguan_height(0.870f));
                p.lineTo(getWaiguan_width(0.687f), getWaiguan_height(0.839f));
                item.imgX = getWaiguan_width(0.657f);
                item.imgY = getWaiguan_height(0.864f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 16:
                p.moveTo(getWaiguan_width(0.433f), getWaiguan_height(0.925f));
                p.lineTo(getWaiguan_width(0.440f), getWaiguan_height(0.958f));
                p.lineTo(getWaiguan_width(0.560f), getWaiguan_height(0.957f));
                p.lineTo(getWaiguan_width(0.567f), getWaiguan_height(0.925f));
                item.imgX = getWaiguan_width(0.498f);
                item.imgY = getWaiguan_height(0.944f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 17:
                p.moveTo(getWaiguan_width(0.457f), getWaiguan_height(0.698f));
                p.lineTo(getWaiguan_width(0.460f), getWaiguan_height(0.721f));
                p.lineTo(getWaiguan_width(0.545f), getWaiguan_height(0.721f));
                p.lineTo(getWaiguan_width(0.547f), getWaiguan_height(0.698f));
                item.imgX = getWaiguan_width(0.498f);
                item.imgY = getWaiguan_height(0.710f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 18:
                p.moveTo(getWaiguan_width(0.307f), getWaiguan_height(0.311f));
                p.lineTo(getWaiguan_width(0.390f), getWaiguan_height(0.390f));
                p.lineTo(getWaiguan_width(0.620f), getWaiguan_height(0.391f));
                p.lineTo(getWaiguan_width(0.700f), getWaiguan_height(0.312f));
                p.lineTo(getWaiguan_width(0.500f), getWaiguan_height(0.278f));
                item.imgX = getWaiguan_width(0.523f);
                item.imgY = getWaiguan_height(0.336f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 19:
                p.moveTo(getWaiguan_width(0.380f), getWaiguan_height(0.634f));
                p.lineTo(getWaiguan_width(0.302f), getWaiguan_height(0.713f));
                p.lineTo(getWaiguan_width(0.498f), getWaiguan_height(0.730f));
                p.lineTo(getWaiguan_width(0.690f), getWaiguan_height(0.713f));
                p.lineTo(getWaiguan_width(0.627f), getWaiguan_height(0.634f));
                item.imgX = getWaiguan_width(0.498f);
                item.imgY = getWaiguan_height(0.666f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 20:
                p.moveTo(getWaiguan_width(0.295f), getWaiguan_height(0.321f));
                p.lineTo(getWaiguan_width(0.297f), getWaiguan_height(0.507f));
                p.lineTo(getWaiguan_width(0.383f), getWaiguan_height(0.503f));
                p.lineTo(getWaiguan_width(0.380f), getWaiguan_height(0.390f));
                item.imgX = getWaiguan_width(0.337f);
                item.imgY = getWaiguan_height(0.439f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 21:
                p.moveTo(getWaiguan_width(0.620f), getWaiguan_height(0.390f));
                p.lineTo(getWaiguan_width(0.618f), getWaiguan_height(0.512f));
                p.lineTo(getWaiguan_width(0.700f), getWaiguan_height(0.506f));
                p.lineTo(getWaiguan_width(0.702f), getWaiguan_height(0.318f));
                item.imgX = getWaiguan_width(0.662f);
                item.imgY = getWaiguan_height(0.437f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 22:
                p.moveTo(getWaiguan_width(0.295f), getWaiguan_height(0.532f));
                p.lineTo(getWaiguan_width(0.297f), getWaiguan_height(0.720f));
                p.lineTo(getWaiguan_width(0.387f), getWaiguan_height(0.633f));
                p.lineTo(getWaiguan_width(0.385f), getWaiguan_height(0.534f));
                item.imgX = getWaiguan_width(0.342f);
                item.imgY = getWaiguan_height(0.594f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 23:
                p.moveTo(getWaiguan_width(0.620f), getWaiguan_height(0.535f));
                p.lineTo(getWaiguan_width(0.623f), getWaiguan_height(0.634f));
                p.lineTo(getWaiguan_width(0.698f), getWaiguan_height(0.713f));
                p.lineTo(getWaiguan_width(0.700f), getWaiguan_height(0.534f));
                item.imgX = getWaiguan_width(0.662f);
                item.imgY = getWaiguan_height(0.592f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 24:
                p.moveTo(getWaiguan_width(0.418f), getWaiguan_height(0.422f));
                p.lineTo(getWaiguan_width(0.417f), getWaiguan_height(0.485f));
                p.lineTo(getWaiguan_width(0.583f), getWaiguan_height(0.485f));
                p.lineTo(getWaiguan_width(0.585f), getWaiguan_height(0.424f));
                item.imgX = getWaiguan_width(0.498f);
                item.imgY = getWaiguan_height(0.450f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 25:
                p.moveTo(getWaiguan_width(0.240f), getWaiguan_height(0.312f));
                p.lineTo(getWaiguan_width(0.237f), getWaiguan_height(0.362f));
                p.lineTo(getWaiguan_width(0.292f), getWaiguan_height(0.361f));
                p.lineTo(getWaiguan_width(0.280f), getWaiguan_height(0.310f));
                item.imgX = getWaiguan_width(0.262f);
                item.imgY = getWaiguan_height(0.338f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 26:
                p.moveTo(getWaiguan_width(0.720f), getWaiguan_height(0.312f));
                p.lineTo(getWaiguan_width(0.712f), getWaiguan_height(0.362f));
                p.lineTo(getWaiguan_width(0.758f), getWaiguan_height(0.357f));
                p.lineTo(getWaiguan_width(0.762f), getWaiguan_height(0.316f));
                item.imgX = getWaiguan_width(0.735f);
                item.imgY = getWaiguan_height(0.340f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 27:
                p.moveTo(getWaiguan_width(0.357f), getWaiguan_height(0.045f));
                p.lineTo(getWaiguan_width(0.277f), getWaiguan_height(0.107f));
                p.lineTo(getWaiguan_width(0.498f), getWaiguan_height(0.074f));
                p.lineTo(getWaiguan_width(0.723f), getWaiguan_height(0.104f));
                p.lineTo(getWaiguan_width(0.655f), getWaiguan_height(0.052f));
                p.lineTo(getWaiguan_width(0.507f), getWaiguan_height(0.029f));
                item.imgX = getWaiguan_width(0.492f);
                item.imgY = getWaiguan_height(0.055f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 28:
                p.moveTo(getWaiguan_width(0.277f), getWaiguan_height(0.894f));
                p.lineTo(getWaiguan_width(0.280f), getWaiguan_height(0.934f));
                p.lineTo(getWaiguan_width(0.408f), getWaiguan_height(0.963f));
                p.lineTo(getWaiguan_width(0.585f), getWaiguan_height(0.963f));
                p.lineTo(getWaiguan_width(0.727f), getWaiguan_height(0.940f));
                p.lineTo(getWaiguan_width(0.727f), getWaiguan_height(0.892f));
                p.lineTo(getWaiguan_width(0.565f), getWaiguan_height(0.917f));
                p.lineTo(getWaiguan_width(0.422f), getWaiguan_height(0.918f));
                item.imgX = getWaiguan_width(0.357f);
                item.imgY = getWaiguan_height(0.934f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 29:
                p.moveTo(getWaiguan_width(0.295f), getWaiguan_height(0.161f));
                p.lineTo(getWaiguan_width(0.300f), getWaiguan_height(0.306f));
                p.lineTo(getWaiguan_width(0.502f), getWaiguan_height(0.268f));
                p.lineTo(getWaiguan_width(0.702f), getWaiguan_height(0.301f));
                p.lineTo(getWaiguan_width(0.705f), getWaiguan_height(0.162f));
                p.lineTo(getWaiguan_width(0.507f), getWaiguan_height(0.139f));
                item.imgX = getWaiguan_width(0.505f);
                item.imgY = getWaiguan_height(0.208f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 30:
                p.moveTo(getWaiguan_width(0.297f), getWaiguan_height(0.716f));
                p.lineTo(getWaiguan_width(0.298f), getWaiguan_height(0.825f));
                p.lineTo(getWaiguan_width(0.502f), getWaiguan_height(0.859f));
                p.lineTo(getWaiguan_width(0.705f), getWaiguan_height(0.828f));
                p.lineTo(getWaiguan_width(0.703f), getWaiguan_height(0.718f));
                p.lineTo(getWaiguan_width(0.505f), getWaiguan_height(0.727f));
                item.imgX = getWaiguan_width(0.367f);
                item.imgY = getWaiguan_height(0.783f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 31:
                p.moveTo(getWaiguan_width(0.132f), getWaiguan_height(0.367f));
                p.lineTo(getWaiguan_width(0.130f), getWaiguan_height(0.522f));
                p.lineTo(getWaiguan_width(0.298f), getWaiguan_height(0.522f));
                p.lineTo(getWaiguan_width(0.297f), getWaiguan_height(0.367f));
                item.imgX = getWaiguan_width(0.203f);
                item.imgY = getWaiguan_height(0.434f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 32:
                p.moveTo(getWaiguan_width(0.700f), getWaiguan_height(0.368f));
                p.lineTo(getWaiguan_width(0.707f), getWaiguan_height(0.522f));
                p.lineTo(getWaiguan_width(0.868f), getWaiguan_height(0.522f));
                p.lineTo(getWaiguan_width(0.870f), getWaiguan_height(0.367f));
                item.imgX = getWaiguan_width(0.788f);
                item.imgY = getWaiguan_height(0.446f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 33:
                p.moveTo(getWaiguan_width(0.132f), getWaiguan_height(0.521f));
                p.lineTo(getWaiguan_width(0.128f), getWaiguan_height(0.676f));
                p.lineTo(getWaiguan_width(0.303f), getWaiguan_height(0.678f));
                p.lineTo(getWaiguan_width(0.293f), getWaiguan_height(0.521f));
                item.imgX = getWaiguan_width(0.208f);
                item.imgY = getWaiguan_height(0.596f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 34:
                p.moveTo(getWaiguan_width(0.705f), getWaiguan_height(0.524f));
                p.lineTo(getWaiguan_width(0.707f), getWaiguan_height(0.679f));
                p.lineTo(getWaiguan_width(0.868f), getWaiguan_height(0.676f));
                p.lineTo(getWaiguan_width(0.870f), getWaiguan_height(0.525f));
                item.imgX = getWaiguan_width(0.785f);
                item.imgY = getWaiguan_height(0.599f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 35:
                p.moveTo(getWaiguan_width(0.180f), getWaiguan_height(0.222f));
                p.lineTo(getWaiguan_width(0.203f), getWaiguan_height(0.271f));
                p.lineTo(getWaiguan_width(0.188f), getWaiguan_height(0.311f));
                p.lineTo(getWaiguan_width(0.133f), getWaiguan_height(0.334f));
                p.lineTo(getWaiguan_width(0.133f), getWaiguan_height(0.368f));
                p.lineTo(getWaiguan_width(0.302f), getWaiguan_height(0.368f));
                p.lineTo(getWaiguan_width(0.298f), getWaiguan_height(0.327f));
                p.lineTo(getWaiguan_width(0.265f), getWaiguan_height(0.222f));
                item.imgX = getWaiguan_width(0.237f);
                item.imgY = getWaiguan_height(0.264f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 36:
                p.moveTo(getWaiguan_width(0.732f), getWaiguan_height(0.219f));
                p.lineTo(getWaiguan_width(0.705f), getWaiguan_height(0.336f));
                p.lineTo(getWaiguan_width(0.700f), getWaiguan_height(0.375f));
                p.lineTo(getWaiguan_width(0.870f), getWaiguan_height(0.369f));
                p.lineTo(getWaiguan_width(0.868f), getWaiguan_height(0.332f));
                p.lineTo(getWaiguan_width(0.817f), getWaiguan_height(0.306f));
                p.lineTo(getWaiguan_width(0.800f), getWaiguan_height(0.261f));
                p.lineTo(getWaiguan_width(0.823f), getWaiguan_height(0.222f));
                item.imgX = getWaiguan_width(0.762f);
                item.imgY = getWaiguan_height(0.268f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 37:
                p.moveTo(getWaiguan_width(0.133f), getWaiguan_height(0.692f));
                p.lineTo(getWaiguan_width(0.195f), getWaiguan_height(0.726f));
                p.lineTo(getWaiguan_width(0.198f), getWaiguan_height(0.783f));
                p.lineTo(getWaiguan_width(0.268f), getWaiguan_height(0.787f));
                p.lineTo(getWaiguan_width(0.298f), getWaiguan_height(0.676f));
                p.lineTo(getWaiguan_width(0.135f), getWaiguan_height(0.676f));
                item.imgX = getWaiguan_width(0.237f);
                item.imgY = getWaiguan_height(0.731f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 38:
                p.moveTo(getWaiguan_width(0.700f), getWaiguan_height(0.675f));
                p.lineTo(getWaiguan_width(0.735f), getWaiguan_height(0.796f));
                p.lineTo(getWaiguan_width(0.820f), getWaiguan_height(0.791f));
                p.lineTo(getWaiguan_width(0.797f), getWaiguan_height(0.741f));
                p.lineTo(getWaiguan_width(0.860f), getWaiguan_height(0.694f));
                p.lineTo(getWaiguan_width(0.870f), getWaiguan_height(0.674f));
                item.imgX = getWaiguan_width(0.765f);
                item.imgY = getWaiguan_height(0.726f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 39:
                p.moveTo(getWaiguan_width(0.380f), getWaiguan_height(0.392f));
                p.lineTo(getWaiguan_width(0.387f), getWaiguan_height(0.633f));
                p.lineTo(getWaiguan_width(0.625f), getWaiguan_height(0.634f));
                p.lineTo(getWaiguan_width(0.617f), getWaiguan_height(0.395f));
                item.imgX = getWaiguan_width(0.500f);
                item.imgY = getWaiguan_height(0.555f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;
            case 40:
                p.moveTo(getWaiguan_width(0.295f), getWaiguan_height(0.141f));
                p.lineTo(getWaiguan_width(0.408f), getWaiguan_height(0.110f));
                p.lineTo(getWaiguan_width(0.412f), getWaiguan_height(0.090f));
                p.lineTo(getWaiguan_width(0.592f), getWaiguan_height(0.085f));
                p.lineTo(getWaiguan_width(0.600f), getWaiguan_height(0.113f));
                p.lineTo(getWaiguan_width(0.698f), getWaiguan_height(0.143f));
                p.lineTo(getWaiguan_width(0.697f), getWaiguan_height(0.163f));
                p.lineTo(getWaiguan_width(0.580f), getWaiguan_height(0.132f));
                p.lineTo(getWaiguan_width(0.430f), getWaiguan_height(0.140f));
                p.lineTo(getWaiguan_width(0.292f), getWaiguan_height(0.158f));
                item.imgX = getWaiguan_width(0.503f);
                item.imgY = getWaiguan_height(0.110f);
                p.close();
                p.computeBounds(f, true);
                r = new Region((int) f.left, (int) f.top, (int) f.right, (int) f.bottom);
                r.setPath(p, r);
                item.path = p;
                item.region = r;
                break;

            default:
                break;
        }
    }

    private static int getNeishi_width(float f) {
        return (int) (f * neishi_width);
    }

    private static int getNeishi_height(float f) {
        return (int) (f * neishi_height);
    }

    private static int getGujia_width(float f) {
        return (int) (f * gujia_width);
    }

    private static int getGujia_height(float f) {
        return (int) (f * gujia_height);
    }

    private static int getWaiguan_width(float f) {
        return (int) (f * waiguan_width);
    }

    private static int getWaiguan_height(float f) {
        return (int) (f * waiguan_height
        );
    }
}
