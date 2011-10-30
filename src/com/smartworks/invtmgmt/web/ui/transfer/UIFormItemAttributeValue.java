package com.smartworks.invtmgmt.web.ui.transfer;

import java.io.Serializable;

public class UIFormItemAttributeValue implements Serializable {
	
	private Integer itemId;
	private Integer itemAttributeId;
	private Integer itemAttributeValueId;
	private String itemAttributeValue;
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getItemAttributeId() {
		return itemAttributeId;
	}
	public void setItemAttributeId(Integer itemAttributeId) {
		this.itemAttributeId = itemAttributeId;
	}

	public Integer getItemAttributeValueId() {
		return itemAttributeValueId;
	}
	public void setItemAttributeValueId(Integer itemAttributeValueId) {
		this.itemAttributeValueId = itemAttributeValueId;
	}
	public String getItemAttributeValue() {
		return itemAttributeValue;
	}
	public void setItemAttributeValue(String itemAttributeValue) {
		this.itemAttributeValue = itemAttributeValue;
	}
	
	
}
