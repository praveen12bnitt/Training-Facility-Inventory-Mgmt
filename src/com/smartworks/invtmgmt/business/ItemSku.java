package com.smartworks.invtmgmt.business;

import java.util.Map;
import java.util.TreeMap;

public class ItemSku {
	Integer itemId;
	Boolean reqProcessing = false;
	Map<Integer, Integer> itemAttribute = new TreeMap<Integer, Integer>();
	Integer quantity;
	
	public Integer getItemId() {
		return itemId;
	}
	public Map<Integer, Integer> getItemAttribute() {
		return itemAttribute;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public void setItemAttribute(Map<Integer, Integer> itemAttribute) {
		this.itemAttribute = itemAttribute;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Boolean getReqProcessing() {
		return reqProcessing;
	}
	public void setReqProcessing(Boolean reqProcessing) {
		this.reqProcessing = reqProcessing;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((itemAttribute == null) ? 0 : itemAttribute.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result
				+ ((reqProcessing == null) ? 0 : reqProcessing.hashCode());
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
		ItemSku other = (ItemSku) obj;
		if (itemAttribute == null) {
			if (other.itemAttribute != null)
				return false;
		} else if (!itemAttribute.equals(other.itemAttribute))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (reqProcessing == null) {
			if (other.reqProcessing != null)
				return false;
		} else if (!reqProcessing.equals(other.reqProcessing))
			return false;
		return true;
	}
	
	
	
	
}
