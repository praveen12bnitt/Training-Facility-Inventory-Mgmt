package com.smartworks.invtmgmt.core.dao;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.Item;

public interface ItemDao {	
	public Item load(Integer id);	
	public List<Item> loadAll();
	public List<String> getItemNamesLike(String likeStr) ;
	public List<Item> getItemsByName(String name) ;
}
