package com.smartworks.invtmgmt.web.ui.transfer.inventory;

public class UIInventory {
	Integer itemId;
	String itemDesc;
	String location;
	String itemAttributeDetails;
	Integer quantity;
	
	public Integer getItemId() {
		return itemId;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public String getLocation() {
		return location;
	}
	public String getItemAttributeDetails() {
		return itemAttributeDetails;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setItemAttributeDetails(String itemAttributeDetails) {
		this.itemAttributeDetails = itemAttributeDetails;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
