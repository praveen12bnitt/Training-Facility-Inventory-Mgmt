package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name="ITEM_ATTR_MAPPING")
@Proxy(lazy=false)
public class ItemAttributeMapping implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="MAPPING_ID")
	Integer mappingId;
	
	@ManyToOne
	@JoinColumn (name="ITEM_ID")
	Item item;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ATTR_ID")
	@JsonIgnore
	private ItemAttribute attribute;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ATTR_VALUE_ID")
	@JsonIgnore
	private ItemAttributeValue attributeValue;

	@JsonIgnore
	public Item getItem() {
		return item;
	}
	@JsonIgnore
	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getMappingId() {
		return mappingId;
	}

	public void setMappingId(Integer mappingId) {
		this.mappingId = mappingId;
	}
	@JsonIgnore
	public ItemAttribute getAttribute() {
		return attribute;
	}
	@JsonIgnore
	public void setAttribute(ItemAttribute attribute) {
		this.attribute = attribute;
	}
	
	@JsonIgnore
	public ItemAttributeValue getAttributeValue() {
		return attributeValue;
	}
	@JsonIgnore
	public void setAttributeValue(ItemAttributeValue attributeValue) {
		this.attributeValue = attributeValue;
	}

	
}
