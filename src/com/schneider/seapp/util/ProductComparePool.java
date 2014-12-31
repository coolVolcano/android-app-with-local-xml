package com.schneider.seapp.util;

import java.util.ArrayList;
import java.util.List;

import com.schneider.seapp.bean.ProductBean;

public class ProductComparePool {

	private static List<ProductBean> pool = new ArrayList<ProductBean>();
	
	public static List<ProductBean> getComparePool(){
		return pool;
	}
}
