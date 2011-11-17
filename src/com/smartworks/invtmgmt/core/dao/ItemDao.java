package com.smartworks.invtmgmt.core.dao;

import java.util.List;
import java.util.Map;

import com.smartworks.invtmgmt.core.domain.Item;

public interface ItemDao {	
	public Item load(Integer id);	
	public List<Item> loadAll();
	public List<String> getItemNamesLike(String likeStr) ;
	public Map<Integer, String> getItemsByNameLike(String likeStr);
	public Map<Integer, String> getItemNamesByIds(List itemIds);
	public List<Item> getItemsByName(String name) ;
}
