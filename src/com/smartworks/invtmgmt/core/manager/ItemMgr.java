package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.Item;

public interface ItemMgr  {
	public Item getItem(Integer itemId) ;
	public List<Item> getItemsForTransaction(Integer transactionId);
	
}
