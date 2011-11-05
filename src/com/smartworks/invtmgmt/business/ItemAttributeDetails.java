package com.smartworks.invtmgmt.business;

import com.smartworks.invtmgmt.core.domain.ItemAttribute;
import com.smartworks.invtmgmt.core.domain.ItemAttributeValue;

public class ItemAttributeDetails {

	ItemAttribute itemAttribute = new ItemAttribute();
	ItemAttributeValue itemAttributeValue = new ItemAttributeValue();
	
	public ItemAttribute getItemAttribute() {
		return itemAttribute;
	}
	public ItemAttributeValue getItemAttributeValue() {
		return itemAttributeValue;
	}
	public void setItemAttribute(ItemAttribute itemAttribute) {
		this.itemAttribute = itemAttribute;
	}
	public void setItemAttributeValue(ItemAttributeValue itemAttributeValue) {
		this.itemAttributeValue = itemAttributeValue;
	}
}
