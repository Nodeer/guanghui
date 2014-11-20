package com.guanghui.car.jingmicheck;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class Constans {
    /**区分每一个检测任务*/
    public static String user_token = "0";

	public static int sScreenWidth;
	public static LinearLayout.LayoutParams centerVerticalPa = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	public static ViewGroup.LayoutParams fillParentPa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
	public static ViewGroup.LayoutParams verticalDividerPa = new LayoutParams(1, LayoutParams.MATCH_PARENT);
	public static ViewGroup.LayoutParams horizontalDividerPa = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
	public static LinearLayout.LayoutParams photoBtnPa = new LayoutParams(100, LayoutParams.WRAP_CONTENT);
    public static LinearLayout.LayoutParams levelitempa = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

	public static void initLayoutParams() {
		centerVerticalPa.gravity = Gravity.CENTER_VERTICAL;
		photoBtnPa.leftMargin = 40;
        levelitempa.leftMargin = 9;
	}
}
