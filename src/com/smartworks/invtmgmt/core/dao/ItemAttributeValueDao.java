package com.smartworks.invtmgmt.core.dao;

import com.smartworks.invtmgmt.core.domain.ItemAttributeValue;

public interface ItemAttributeValueDao {
	public ItemAttributeValue load(Integer itemAttrValueId);
	public ItemAttributeValue findByAttributeValue(String attributeValue) ;
	public Integer getNextItemAttributeValueId();
	public void save(ItemAttributeValue itemAttributeValue);
}
