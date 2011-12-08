package com.smartworks.invtmgmt.web.ui.form;

import java.util.List;

import com.smartworks.invtmgmt.web.ui.transfer.inventory.UIInventory;
public class InventoryDetailsForm {

	Integer locationId;
	String location;
	Integer availableQty;
	String skuCode;	
	
	

	public Integer getLocationId() {
		return locationId;
	}



	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public Integer getAvailableQty() {
		return availableQty;
	}



	public void setAvailableQty(Integer availableQty) {
		this.availableQty = availableQty;
	}



	public String getSkuCode() {
		return skuCode;
	}



	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}



	public List<UIInventory> getInvDetails() {
		return invDetails;
	}



	public void setInvDetails(List<UIInventory> invDetails) {
		this.invDetails = invDetails;
	}



	private List<UIInventory> invDetails;	
	
			
	}
