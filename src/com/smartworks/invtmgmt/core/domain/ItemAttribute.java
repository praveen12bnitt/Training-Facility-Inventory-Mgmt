package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "item_attribute")
public class ItemAttribute implements Serializable {
	
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
}
