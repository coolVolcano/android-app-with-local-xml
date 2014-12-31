package com.schneider.seapp.bean;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryBean implements Parcelable{
	
	private int categoryId;
	
	private String categoryName;
	
	//here have to initialize it. otherwise it will cause a NullPointerException if it implements Parcelable.
	private List<ProductBean> products = new ArrayList<ProductBean>();

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<ProductBean> getProducts() {
		return products;
	}

	public void setProducts(List<ProductBean> products) {
		this.products = products;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(categoryId);
		dest.writeString(categoryName);
		dest.writeTypedList(products);
	}
	
	private CategoryBean(Parcel source){
		categoryId = source.readInt();
		categoryName = source.readString();
		source.readTypedList(products, ProductBean.CREATOR);
	}
	
	public CategoryBean(){
		
	}
	
	public static final Parcelable.Creator<CategoryBean> CREATOR = new Creator<CategoryBean>() {  
        @Override  
        public CategoryBean createFromParcel(Parcel source) {  
            return new CategoryBean(source);  
        }  
        
        @Override  
        public CategoryBean[] newArray(int size) {  
            return new CategoryBean[size];  
        }  
    };  
    
}
