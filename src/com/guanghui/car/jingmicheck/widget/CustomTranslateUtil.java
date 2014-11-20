package com.guanghui.car.jingmicheck.widget;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;

public class CustomTranslateUtil {
	public static final int TOP_TO_DOWN = 1;
	public static final int DOWN_TO_TOP = 2;
	private static CustomTranslateUtil sInstance = new CustomTranslateUtil();
	public static CustomTranslateUtil getInstance() {
		if (sInstance == null) {
			sInstance = new CustomTranslateUtil();
		}
		return sInstance;
	}

	public void showView(View view, int direction, int duration) {
		view.setVisibility(View.VISIBLE);
		TranslateAnimation ani;
		if (direction == TOP_TO_DOWN) {
			ani = new TranslateAnimation(0, 0, -view.getMeasuredHeight(), 0);
		} else if (direction == DOWN_TO_TOP) {
			ani = new TranslateAnimation(0, 0, view.getMeasuredHeight(), 0);
		} else {
			ani = new TranslateAnimation(0, 0, -view.getMeasuredHeight(), 0);
		}
		ani.setDuration(duration);
		view.startAnimation(ani);

	}
	public void hideView(final View view, int direction, int duration) {

		TranslateAnimation ani;
		if (direction == DOWN_TO_TOP) {
			ani = new TranslateAnimation(0, 0, 0, -view.getMeasuredHeight());
		} else if (direction == TOP_TO_DOWN) {
			ani = new TranslateAnimation(0, 0, 0, view.getMeasuredHeight());
		} else {
			ani = new TranslateAnimation(0, 0, 0, -view.getMeasuredHeight());
		}
		ani.setDuration(duration);
		ani.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				view.setVisibility(View.GONE);
			}
		});
		view.startAnimation(ani);
	}

}
