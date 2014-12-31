package com.schneider.seapp.listview;

import com.schneider.seapp.bean.CategoryBean;

public class CategoryListItem extends CategoryBean{
	
	private int product1Id;
	
	private int product2Id;
	
	private String product1Name;
	
	private String product2Name;
	
	private String product1PriceUnit;
	
	private String product2PriceUnit;
	
	private double product1Price;
	
	private double product2Price;
	
	private int product1Image;
	
	private int product2Image;
	
	public int getProduct1Id() {
		return product1Id;
	}

	public void setProduct1Id(int product1Id) {
		this.product1Id = product1Id;
	}

	public int getProduct2Id() {
		return product2Id;
	}

	public void setProduct2Id(int product2Id) {
		this.product2Id = product2Id;
	}

	public String getProduct1Name() {
		return product1Name;
	}

	public void setProduct1Name(String product1Name) {
		this.product1Name = product1Name;
	}

	public String getProduct2Name() {
		return product2Name;
	}

	public void setProduct2Name(String product2Name) {
		this.product2Name = product2Name;
	}

	public double getProduct1Price() {
		return product1Price;
	}

	public void setProduct1Price(double product1Price) {
		this.product1Price = product1Price;
	}

	public double getProduct2Price() {
		return product2Price;
	}

	public void setProduct2Price(double product2Price) {
		this.product2Price = product2Price;
	}

	public int getProduct1Image() {
		return product1Image;
	}

	public void setProduct1Image(int product1Image) {
		this.product1Image = product1Image;
	}

	public int getProduct2Image() {
		return product2Image;
	}

	public void setProduct2Image(int product2Image) {
		this.product2Image = product2Image;
	}
	
	public String getProduct1PriceUnit() {
		return product1PriceUnit;
	}

	public void setProduct1PriceUnit(String product1PriceUnit) {
		this.product1PriceUnit = product1PriceUnit;
	}

	public String getProduct2PriceUnit() {
		return product2PriceUnit;
	}

	public void setProduct2PriceUnit(String product2PriceUnit) {
		this.product2PriceUnit = product2PriceUnit;
	}

	public CategoryListItem(){
		
	}
	
}
