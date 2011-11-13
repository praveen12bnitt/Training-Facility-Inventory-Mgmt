package com.smartworks.invtmgmt.web.ui.transfer.inventory;

public class UIInventory {
	Integer itemId;
	String itemDesc;
	String location;
	String itemAttributeDetails;
	Integer availableQty;
	Integer issuedQty;
	Integer unusableQty;
	
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
	public Integer getAvailableQty() {
		return availableQty;
	}
	public Integer getIssuedQty() {
		return issuedQty;
	}
	public Integer getUnusableQty() {
		return unusableQty;
	}
	public void setAvailableQty(Integer availableQty) {
		this.availableQty = availableQty;
	}
	public void setIssuedQty(Integer issuedQty) {
		this.issuedQty = issuedQty;
	}
	public void setUnusableQty(Integer unusableQty) {
		this.unusableQty = unusableQty;
	}
	
	
	
}
