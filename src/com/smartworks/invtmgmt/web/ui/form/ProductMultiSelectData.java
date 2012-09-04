package com.smartworks.invtmgmt.web.ui.form;

public class ProductMultiSelectData {

	private Integer productId;
	private String productName;
	private boolean selected;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	} 
	
}
