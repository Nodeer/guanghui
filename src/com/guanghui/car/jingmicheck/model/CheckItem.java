package com.guanghui.car.jingmicheck.model;

import android.graphics.Path;
import android.graphics.Region;

public class CheckItem {

	public int areaIdex;
	public int departIndex;
	public int imgX;
	public int imgY;

	public Region region;
	public Path path;
	public DefectDepartModel model;
	public CheckItem(int areaIdex, int departIndex, int imgX, int imgY, Region region, Path path) {
		super();
		this.areaIdex = areaIdex;
		this.departIndex = departIndex;
		this.imgX = imgX;
		this.imgY = imgY;
		this.region = region;
		this.path = path;
	}
	public CheckItem(int areaIdex, int departIndex, DefectDepartModel model) {
		super();
		this.areaIdex = areaIdex;
		this.departIndex = departIndex;
		this.model = model;
	}

}
