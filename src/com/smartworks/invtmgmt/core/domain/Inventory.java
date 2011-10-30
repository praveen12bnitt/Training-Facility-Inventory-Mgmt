package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;

/**
 * Hibernate POJO for INVENTORY table
 * @author Palanivel Rajan B
 *
 */
@Entity
@Table(name="INVENTORY")
@Proxy(lazy=false)
public class Inventory implements Serializable {
		
	private static final long serialVersionUID = 1L;

	@Id
	private InventoryPk skuLocation;
	
	@Column(name="available_qty")
	private Integer availableQty;
	
	@Column(name="unusable_qty")
	private Integer unusableQty;

	public Integer getAvailableQty() {
		return availableQty;
	}

	public InventoryPk getSkuLocation() {
		return skuLocation;
	}

	public void setSkuLocation(InventoryPk skuLocation) {
		this.skuLocation = skuLocation;
	}

	public Integer getUnusableQty() {
		return unusableQty;
	}

	public void setAvailableQty(Integer availableQty) {
		this.availableQty = availableQty;
	}

	public void setUnusableQty(Integer unusableQty) {
		this.unusableQty = unusableQty;
	}
	
}
