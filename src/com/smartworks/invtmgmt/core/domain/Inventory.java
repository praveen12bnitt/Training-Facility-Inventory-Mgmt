package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

import com.smartworks.invtmgmt.business.ItemSku;
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
	
	@Column(name="AVAILABLE_QTY")
	private Integer availableQty;
	
	@Column(name="UNUSABLE_QTY")
	private Integer unusableQty;
	
	@Column(name="ISSUED_QTY")
	private Integer issueQty;
	
	@Column(name="MISSING_QTY")
	private Integer missingQty;
	
	@Column(name="DAMAGED_QTY")
	private Integer damagedQty;
	
	@Transient
	public ItemSku itemSku;

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
	
	public ItemSku getItemSku() {
		return itemSku;
	}

	public void setItemSku(ItemSku itemSku) {
		this.itemSku = itemSku;
	}
	
	public Location getLocation() {
		return skuLocation.getLocation();
	}
	
	public void setLocation(Location l) {
		skuLocation.setLocation(l);		
	}
	
	public String getSkuCode() {
		return skuLocation.getSkuCode();
	}
	
	public void setSkuCode(String skuCode) {
		skuLocation.setSkuCode(skuCode);
	}

	public Integer getIssueQty() {
		return issueQty;
	}

	public void setIssueQty(Integer issueQty) {
		this.issueQty = issueQty;
	}

	public Integer getMissingQty() {
		return missingQty;
	}

	public Integer getDamagedQty() {
		return damagedQty;
	}

	public void setMissingQty(Integer missingQty) {
		this.missingQty = missingQty;
	}

	public void setDamagedQty(Integer damagedQty) {
		this.damagedQty = damagedQty;
	}
	
}
