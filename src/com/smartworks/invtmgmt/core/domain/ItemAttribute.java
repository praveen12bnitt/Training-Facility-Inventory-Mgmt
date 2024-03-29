package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "ITEM_ATTRIBUTE")
@Proxy(lazy=false)
public class ItemAttribute implements Serializable,Comparable<ItemAttribute> {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ATTR_ID")
	@JsonProperty
	Integer attibuteId;
	
	@Column(name="ATTR_NAME")
	@JsonProperty
	String attributeName;
	
	public ItemAttribute() {
		
	}

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attibuteId == null) ? 0 : attibuteId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemAttribute other = (ItemAttribute) obj;
		if (attibuteId == null) {
			if (other.attibuteId != null)
				return false;
		} else if (!attibuteId.equals(other.attibuteId))
			return false;
		return true;
	}

	@Override
	public int compareTo(ItemAttribute o) {
		return attibuteId.compareTo(o.getAttibuteId());
	}
	
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		try {
			mapper.writeValue(strWriter, this);
		} catch(Exception ex) {
			System.out.println(ex);
			//return "{attributeId:"+attibuteId+", attributeName:"+attributeName+"}";
		}
		return strWriter.toString();
		
	}
	
	
	
}
