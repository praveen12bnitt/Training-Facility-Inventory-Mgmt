package com.smartworks.invtmgmt.core.domain.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.smartworks.invtmgmt.core.domain.Location;

@Embeddable
public class InventoryPk implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="SKU_CODE")
	String skuCode;
	
	@JoinColumn(name="LOCATION_ID")
	@ManyToOne
	Location location;
	
	public String getSkuCode() {
		return skuCode;
	}
	public Location getLocation() {
		return location;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
}
