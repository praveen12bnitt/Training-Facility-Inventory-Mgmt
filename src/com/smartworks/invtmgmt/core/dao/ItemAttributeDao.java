package com.smartworks.invtmgmt.core.dao;

import com.smartworks.invtmgmt.core.domain.ItemAttribute;

public interface ItemAttributeDao {
	public ItemAttribute load(Integer itemAttrId) ;
	public ItemAttribute findByAttributeName(String attributeName) ;
	public Integer getNextAttributeId();
	public void save(ItemAttribute itemAttribute) ;
}
