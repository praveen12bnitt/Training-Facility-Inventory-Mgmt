package com.smartworks.invtmgmt.core.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.ItemDao;
import com.smartworks.invtmgmt.core.domain.Item;

public class ItemDaoImpl  extends HibernateDaoSupport implements ItemDao {

	public Item load(Integer id) {
		return getHibernateTemplate().load(Item.class, id);
	}

	@Override
	public List<Item> loadAll() {
		String query = "from Item";		
		List<Item> items = getHibernateTemplate().find(query);
		return items;
	}
	
	public List<String> getItemNamesLike(String likeStr) {		
		String query = "select name from Item where name like :name" ;				
		List<String> itemNames = getHibernateTemplate().findByNamedParam(query, "name", "%"+likeStr+"%");
		return itemNames;
	}
	
	public List<Item> getItemsByName(String name) {
		String query = "from Item where name =  :name" ;
		List<Item> items = getHibernateTemplate().findByNamedParam(query, "name", name);
		return items;
		
	}
	
}
