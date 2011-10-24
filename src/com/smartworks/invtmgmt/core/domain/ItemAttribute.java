package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item_attribute")
public class ItemAttribute implements Serializable,Comparable<ItemAttribute> {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="attr_id")
	Integer attibuteId;
	
	@Column(name="attr_name")
	String attributeName;
	
	public Integer getAttibuteId() {
		return attibuteId;
	}

	public void setAttibuteId(Integer attibuteId) {
		this.attibuteId = attibuteId;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
	@Override
	public boolean equals(Object obj) {
		return attibuteId.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return attibuteId.hashCode();
	}

	@Override
	public int compareTo(ItemAttribute o) {
		return attibuteId.compareTo(o.getAttibuteId());
	}

	
	
	
}
