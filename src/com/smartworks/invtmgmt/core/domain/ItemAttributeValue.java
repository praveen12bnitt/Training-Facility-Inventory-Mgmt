package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="ITEM_ATTRIBUTE_VALUES")
@Proxy(lazy=false)
public class ItemAttributeValue implements Serializable,Comparable<ItemAttributeValue> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ATTR_VALUE_ID")
	Integer attributeValueId;
	
	@Column(name="ATTR_VALUE")
	String attributeValue;

	public ItemAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public ItemAttributeValue() {
		// TODO Auto-generated constructor stub
	}

	public Integer getAttributeValueId() {
		return attributeValueId;
	}

	public void setAttributeValueId(Integer attributeValueId) {
		this.attributeValueId = attributeValueId;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	
	@Override
	public boolean equals(Object obj) {
		return attributeValueId.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return attributeValueId.hashCode();
	}

	@Override
	public int compareTo(ItemAttributeValue o) {
		return attributeValueId.compareTo(o.getAttributeValueId());
	}
	
	@Override
	public String toString() {
		return "attributeValueId:"+attributeValueId+", attributeValue:"+attributeValue;
	}
	

}
