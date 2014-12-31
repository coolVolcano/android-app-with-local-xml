package com.schneider.seapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductBean implements Parcelable{
	
	private int productId;
	
	private String productName;
	
	private double price;
	
	private String priceUnit;
	
	private String model;
	
	private String function;
	
	private double dimension;
	
	private String attachment;
	
	private String spec;
	
	private double current;
	
	private String currentUnit;
	
	private String images;


	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public double getDimension() {
		return dimension;
	}

	public void setDimension(double dimension) {
		this.dimension = dimension;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public double getCurrent() {
		return current;
	}

	public void setCurrent(double current) {
		this.current = current;
	}

	public String getCurrentUnit() {
		return currentUnit;
	}

	public void setCurrentUnit(String currentUnit) {
		this.currentUnit = currentUnit;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(productId);
		dest.writeString(productName);
		dest.writeDouble(price);
		dest.writeString(priceUnit);
		dest.writeString(model);
		dest.writeString(function);
		dest.writeDouble(dimension);
		dest.writeString(attachment);
		dest.writeString(spec);
		dest.writeDouble(current);
		dest.writeString(currentUnit);
		dest.writeString(images);
	}
	
	private ProductBean(Parcel source){
		productId = source.readInt();
		productName = source.readString();
		price = source.readDouble();
		priceUnit = source.readString();
		model = source.readString();
		function = source.readString();
		dimension = source.readDouble();
		attachment = source.readString();
		spec = source.readString();
		current = source.readDouble();
    	currentUnit = source.readString();
    	images = source.readString();
	}
	
	public ProductBean(){
		
	}
	
	public String getPriceTxt() {
		String prodcutPrice = Double.toString(this.getPrice());		
		String prodcutPriceUnit = this.getPriceUnit().toString();
		return new StringBuilder(prodcutPriceUnit).append(prodcutPrice).toString();
	}
	
	public String getCurrentTxt() {
		String productCurrent = Double.toString(this.getCurrent());
		String productCurrentUnit = this.getCurrentUnit();
		return new StringBuilder(productCurrent).append(productCurrentUnit).toString();
	}
	
	public String[] getImageList() {
		return this.images.split(",");
		
	}
	
	public static final Parcelable.Creator<ProductBean> CREATOR = new Creator<ProductBean>() {  
        @Override  
        public ProductBean createFromParcel(Parcel source) {  
            return new ProductBean(source);  
        }  
        
        @Override  
        public ProductBean[] newArray(int size) {  
            return new ProductBean[size];  
        }  
    };  
}
