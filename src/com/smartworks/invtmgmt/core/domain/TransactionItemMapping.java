package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smartworks.invtmgmt.core.domain.pk.TransItemMappingPk;

@Entity
@Table(name="TRANSACTION_ITEM_MAPPING")
public class TransactionItemMapping implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private TransItemMappingPk transItemMappingPk;
	
	@Column(name="UI_WEIGHT")
	private Integer uiWeight;

	public TransItemMappingPk getTransItemMappingPk() {
		return transItemMappingPk;
	}

	public Integer getUiWeight() {
		return uiWeight;
	}

	public void setTransItemMappingPk(TransItemMappingPk transItemMappingPk) {
		this.transItemMappingPk = transItemMappingPk;
	}

	public void setUiWeight(Integer uiWeight) {
		this.uiWeight = uiWeight;
	}
	
	
}
