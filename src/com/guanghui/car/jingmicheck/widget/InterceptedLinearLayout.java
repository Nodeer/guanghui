package com.guanghui.car.jingmicheck.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/** 可设置是否禁用子控件,同时自动改变背景 */
public class InterceptedLinearLayout extends LinearLayout {
	private boolean isIntercept = false;

	public InterceptedLinearLayout(Context context) {
		super(context);
	}

	public InterceptedLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public InterceptedLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (isIntercept) {
			return true;
		} else {
			return super.onInterceptTouchEvent(ev);
		}
	}

	public void setIsIntercept(boolean b) {
		isIntercept = b;
//		if (b) {
//			setBackgroundColor(0xffeeeeee);
//		} else {
//			setBackgroundColor(0x00000000);
//		}
	}

}
