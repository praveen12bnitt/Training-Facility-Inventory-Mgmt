package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.TransactionType;

public interface ItemMgr  {
	public Item getItem(Integer itemId) ;
	public Item getItemWithoutAttribute(Integer itemId);
	public List<Item> getItemsForTransaction(TransactionType transactionType);
	public List<Item> getAllItems() ;
	public List<Item> getAllItemsWithoutAttribute();
}
