package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

public class Inventory implements Serializable {
	
	private Integer locationId;
	private Integer itemId;
	private Integer itemAttributeId;
	private Integer itemAttributeValueId;
	private Integer qtyOnHand;
	private Integer qtyIssued;
	private Integer qtyReceived;
	

	public Inventory() {
		super();
	}
	
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
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
	public Integer getQtyOnHand() {
		return qtyOnHand;
	}
	public void setQtyOnHand(Integer qtyOnHand) {
		this.qtyOnHand = qtyOnHand;
	}
	public Integer getQtyIssued() {
		return qtyIssued;
	}
	public void setQtyIssued(Integer qtyIssued) {
		this.qtyIssued = qtyIssued;
	}
	public Integer getQtyReceived() {
		return qtyReceived;
	}
	public void setQtyReceived(Integer qtyReceived) {
		this.qtyReceived = qtyReceived;
	}
	

}
