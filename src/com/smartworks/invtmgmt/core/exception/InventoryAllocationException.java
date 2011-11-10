package com.smartworks.invtmgmt.core.exception;

import com.smartworks.invtmgmt.converter.ItemSkuConverter;
import com.smartworks.platform.AppContextUtil;

public class InventoryAllocationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	String skuCode;
	Integer quantity;
	String location;
	
	ItemSkuConverter itemSkuConverter = AppContextUtil.getBean("itemSkuConverter");
	
	public InventoryAllocationException(String skuCode, Integer quantity, String location) {
		super();
		this.skuCode = skuCode;
		this.quantity = quantity;
		this.location = location;
	}
	
	protected String getItemSkuAsStr() {
		return itemSkuConverter.getItemSkuAsStr(skuCode);
	}

	@Override
	public String getMessage() {
		return "Unable to allocate "+getItemSkuAsStr()+" at "+location+" Quantity:"+quantity;
	}
	
	public String getSkuCode() {
		return skuCode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
