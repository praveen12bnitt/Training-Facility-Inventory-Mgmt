package com.smartworks.invtmgmt.business;

import java.util.Map;
import java.util.TreeMap;

public class ItemSku {
	Integer itemId;
	Boolean reqProcessing = false;
	Map<Integer, Integer> itemAttribute = new TreeMap<Integer, Integer>();
	Integer quantity;
	
	public Integer getItemId() {
		return itemId;
	}
	public Map<Integer, Integer> getItemAttribute() {
		return itemAttribute;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public void setItemAttribute(Map<Integer, Integer> itemAttribute) {
		this.itemAttribute = itemAttribute;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Boolean getReqProcessing() {
		return reqProcessing;
	}
	public void setReqProcessing(Boolean reqProcessing) {
		this.reqProcessing = reqProcessing;
	}
	
	
}
