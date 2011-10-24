package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="item_attr_mapping")
public class ItemAttributeMapping implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="mapping_id")
	Integer mappingId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn (name="ITEM_ID")
	Item item;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ATTR_ID")
	private ItemAttribute attribute;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ATTR_VALUE_ID")
	private ItemAttributeValue attributeValue;

	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getMappingId() {
		return mappingId;
	}

	public void setMappingId(Integer mappingId) {
		this.mappingId = mappingId;
	}

	public ItemAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(ItemAttribute attribute) {
		this.attribute = attribute;
	}

	public ItemAttributeValue getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(ItemAttributeValue attributeValue) {
		this.attributeValue = attributeValue;
	}

	
}
