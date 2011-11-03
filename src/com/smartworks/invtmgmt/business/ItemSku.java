package com.smartworks.invtmgmt.business;

import java.util.HashMap;
import java.util.Map;

import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.ItemAttribute;
import com.smartworks.invtmgmt.core.domain.ItemAttributeValue;

public class ItemSku {
	Item item;
	Map<ItemAttribute,ItemAttributeValue> itemAttributeDetails = new HashMap<ItemAttribute, ItemAttributeValue>();
	Integer quantity;
	
	public Integer getQuantity() {
		return quantity;
	}
		
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Map<ItemAttribute, ItemAttributeValue> getItemAttributeDetails() {
		return itemAttributeDetails;
	}

	public void setItemAttributeDetails(
			Map<ItemAttribute, ItemAttributeValue> itemAttributeDetails) {
		this.itemAttributeDetails = itemAttributeDetails;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}	
}
