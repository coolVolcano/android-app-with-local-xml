package com.schneider.seapp.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.res.AssetManager;

import com.schneider.seapp.bean.CategoryBean;
import com.schneider.seapp.bean.ProductBean;

public class CategoryHelper {
	
	private static HashMap<String, CategoryBean> categoryCache = new HashMap<String, CategoryBean>();
	private static int[] categoryIds = new int[100];

	public static List<CategoryBean> read(AssetManager am, String fileName){
		
		List<CategoryBean> categories = null;
		List<ProductBean> products = null;
		CategoryBean category = null;
		ProductBean product = null;
		int index = 0;
		
		int categoryCount = categoryCache.size();
		
		if(categoryCount>categoryIds.length){
			categoryIds = Arrays.copyOf(categoryIds, categoryIds.length * 2);
		}
			
		//read cache
		if(categoryCount>0){
			categories = new ArrayList<CategoryBean>();
			for(int i=0;i<categoryCount;i++){
				String key = String.valueOf(categoryIds[i]);
				category = categoryCache.get(key);
				if(category!=null){
					categories.add(category);
					category = null;
				}
					
			}
			return categories;
		}
		
		//if cache is empty
		try {
			InputStream istr = am.open(fileName);
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance(); 
			factory.setNamespaceAware(true); 
			XmlPullParser xrp = factory.newPullParser(); 
			xrp.setInput(istr, "UTF-8");
			int eventType = xrp.getEventType();
			
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String nodeName=xrp.getName();
	        	switch (eventType) {
	        	case XmlPullParser.START_DOCUMENT:
	        		 categories=new ArrayList<CategoryBean>();
	        		 break;
	        	 case XmlPullParser.START_TAG:
	        		 if(nodeName.equals("category")){
	        			 category = new CategoryBean();
	        		 }else if(nodeName.equals("category_id")){
	        			 category.setCategoryId(Integer.parseInt(xrp.nextText()));
	        		 }else if(nodeName.equals("category_name")){
	        			 category.setCategoryName(xrp.nextText());
	        		 }else if(nodeName.equals("product_list")){
	        			 products = new ArrayList<ProductBean>();
	        		 }else if(nodeName.equals("product")){
	        			 product = new ProductBean();
	        		 }else if(nodeName.equals("product_id")){
	        			 product.setProductId(Integer.parseInt(xrp.nextText()));
	        		 }else if(nodeName.equals("product_name")){
	        			 product.setProductName(xrp.nextText());
	        		 }else if(nodeName.equals("product_price")){
	        			 product.setPrice(Double.parseDouble(xrp.nextText()));
	        		 }else if(nodeName.equals("price_unit")){
	        			 product.setPriceUnit(xrp.nextText());
	        		 }
	        		 break;
	        	 case XmlPullParser.END_TAG:
                    if(nodeName.equals("category")){
                    	String cacheKey = String.valueOf(category.getCategoryId());
                    	categoryCache.put(cacheKey, category);
                    	categoryIds[index++] = category.getCategoryId();
                    	
                        categories.add(category);
                        category=null;
                    }
                    if(nodeName.equals("product")){
                        products.add(product);
                        product=null;
                    }
                    if(nodeName.equals("product_list")){
                    	category.setProducts(products);
                    	products=null;
                    }
                    break;
                default:
                    break;
	        	}
	        	eventType = xrp.next();
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return categories;
	}
	
//	public static CategoryBean getCategoryById(AssetManager am, String fileName, int categoryId){
//		CategoryBean category = null;
//		
//		String cacheKey = new StringBuilder("category_").append(categoryId).toString();
//		
//		if(categoryCache.get(cacheKey)==null)
//			read(am, fileName);
//		
//		category = categoryCache.get(cacheKey);
//		
//		return category;
//	}
	
}
