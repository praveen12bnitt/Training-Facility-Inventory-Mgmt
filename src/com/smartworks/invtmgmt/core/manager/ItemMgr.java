package com.smartworks.invtmgmt.core.manager;

import java.util.List;
import java.util.Map;

import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.TransactionType;

public interface ItemMgr  {
	public Item getItem(Integer itemId) ;
	public Item getItemWithoutAttribute(Integer itemId);
	public List<Item> getItemsForTransaction(TransactionType transactionType);
	public List<Item> getAllItems() ;
	public List<Item> getAllItemsWithoutAttribute();
	public List<String> getItemNamesLike(String name);
	public List<Item> getItemsByName(String name);
	public Map<Integer, String> getItemMaps(String name);
	public List<Item> getItemsByProductId(Integer productId);
	public List<Item> getItemsByNumber(String itemNumber) ;
}
