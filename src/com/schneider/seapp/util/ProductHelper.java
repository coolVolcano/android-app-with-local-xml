package com.schneider.seapp.util;

import java.io.InputStream;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.res.AssetManager;

import com.schneider.seapp.bean.ProductBean;

public class ProductHelper {
	
	private static HashMap<String, ProductBean> productCache = new HashMap<String, ProductBean>();
	
	private static ProductBean read(AssetManager am, String fileName){
		
		ProductBean product = null;
		String value = "";
		
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
	        		 product = new ProductBean();
	        		 break;
	        	 case XmlPullParser.START_TAG:
	        		 if(nodeName.equals("product_id")){
	        			 product.setProductId(Integer.parseInt(xrp.nextText()));
	        		 }else if(nodeName.equals("product_name")){
	        			 product.setProductName(xrp.nextText());
	        		 }else if(nodeName.equals("product_price")){
	        			 product.setPrice(Double.parseDouble(xrp.nextText()));
	        		 }else if(nodeName.equals("price_unit")){
	        			 product.setPriceUnit(xrp.nextText());
	        		 }else if(nodeName.equals("model")){
	        			 product.setModel(xrp.nextText());
	        		 }else if(nodeName.equals("function")){
	        			 product.setFunction(xrp.nextText());
	        		 }else if(nodeName.equals("dimension")){
	        			 value = xrp.nextText();
	        			 product.setDimension(value!=null&&value.length()>0?Double.parseDouble(value):0);
	        		 }else if(nodeName.equals("attachment")){
	        			 product.setAttachment(xrp.nextText());
	        		 }else if(nodeName.equals("spec")){
	        			 product.setSpec(xrp.nextText());
	        		 }else if(nodeName.equals("current")){
	        			 product.setCurrent(Double.parseDouble(xrp.nextText()));
	        		 }else if(nodeName.equals("current_unit")){
	        			 product.setCurrentUnit(xrp.nextText());
	        		 }else if(nodeName.equals("product_images")){
	        			 product.setImages(xrp.nextText());
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
		
		return product;
	}
	
	public static ProductBean getProductById(AssetManager am, String fileName, int productId){
		
		ProductBean product = null;
		
		product = productCache.get(String.valueOf(productId));
		
		if(product==null){
			product = read(am, fileName);
			productCache.put(String.valueOf(productId), product);
		}
		
		return product;
	}
}
