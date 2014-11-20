package com.guanghui.car.jingmicheck.util;

public class StringUtil {
	public static boolean emptyOrNull(String str) {
		if (str == null || str.equals("")) {
			return true;
		} else {
			return false;
		}
	}
}
