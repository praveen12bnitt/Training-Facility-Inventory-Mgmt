package com.smartworks.invtmgmt.business;

import java.util.HashMap;
import java.util.Map;

import com.smartworks.invtmgmt.converter.ItemSkuConverter;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.ItemAttribute;
import com.smartworks.invtmgmt.core.domain.ItemAttributeValue;
import com.smartworks.platform.AppContextUtil;

public class ItemSku {
	
	Item item;
	Map<ItemAttribute,ItemAttributeValue> itemAttributeDetails = new HashMap<ItemAttribute, ItemAttributeValue>();
	
	String skuCode;		
	Integer quantity;
	
	ItemSkuConverter itemSkuConverter = AppContextUtil.getBean("itemSkuConverter");
	
	public ItemSku(Item item,
			Map<ItemAttribute, ItemAttributeValue> itemAttributeDetails) {
		super();
		this.item = item;
		this.itemAttributeDetails = itemAttributeDetails;
	}
	
	
	
	public ItemSku() {
		super();
	}



	public ItemSku(String skuCode) {
		super();
		this.skuCode = skuCode;
	}

	public Map<ItemAttribute, ItemAttributeValue> getItemAttributeDetails() {
		return itemAttributeDetails;
	}

	public void setItemAttributeDetails(
			Map<ItemAttribute, ItemAttributeValue> itemAttributeDetails) {
		this.itemAttributeDetails = itemAttributeDetails;
	}

	public Item getItem() {
		if(item == null) {
			// Check generate item and itemAttributeDetails object if skuCode is not null
			if(skuCode != null) {
				ItemSku s = itemSkuConverter.getItemSku(skuCode);
				item = s.getItem();
				itemAttributeDetails = s.getItemAttributeDetails();
			}
		}
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getSkuCode() {		
		if(skuCode == null) {
			// Check if item is non empty. If yes then generete the value
			if(item != null) {
				skuCode = itemSkuConverter.getItemSkuCode(this);
			}
		} 		
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
