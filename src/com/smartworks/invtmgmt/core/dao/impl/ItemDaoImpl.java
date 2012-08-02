package com.smartworks.invtmgmt.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;
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
	
	public Map<Integer, String> getItemsByNameLike(String likeStr) {		
		String query = "select id, name from Item where name like :name" ;				
		List<Object[]> itemNames = getHibernateTemplate().findByNamedParam(query, "name", "%"+likeStr+"%");
		Map<Integer,String> itemsMap = new HashMap<Integer, String>();
		for(Object[] items: itemNames) {
			itemsMap.put((Integer)items[0],(String)items[1]);
		}
		return itemsMap;
	}
	
	public List<Item> getItemsByName(String name) {
		String query = "from Item where name =  :name" ;
		List<Item> items = getHibernateTemplate().findByNamedParam(query, "name", name);
		return items;
		
	}

	@Override
	public Map<Integer, String> getItemNamesByIds(List itemIds) {
		
		String hql = "select id,name from Item where id in (:listParam)";
		String[] params = { "listParam" };
		Object[] values = { itemIds };
		List<Object[]> itemNames = getHibernateTemplate().findByNamedParam(hql, params, values);
		Map<Integer,String> itemsMap = new HashMap<Integer, String>();
		for(Object[] items: itemNames) {
			itemsMap.put((Integer)items[0],(String)items[1]);
		}
		return itemsMap;
	}

	@Override
	public List<Item> loadSelectedItems(List<Integer> itemIds) {
		String hql = "from Item where id in (:listParam)";
		String[] params = { "listParam" };
		Object[] values = { itemIds };
		
		List<Item> items = getHibernateTemplate().findByNamedParam(hql, params, values);
		return items;
		
	}

	@Override
	public void save(Item item) {
		getHibernateTemplate().save(item);		
	}
	
	@Override
	public Integer getNextItemId() {
		String query = "select max(ia.id) from Item ia";
		List<Integer> itemId = getHibernateTemplate().find(query);
		if(itemId !=null && itemId.size()>0) {
			return itemId.get(0)+1;
		} else {
			return 1;
		}
	}
	
	
	@Override
	public Integer getNextMappingId() {
		String query = "select max(ia.mappingId) from ItemAttributeMapping ia";
		List<Integer> mappingId = getHibernateTemplate().find(query);
		if(mappingId !=null && mappingId.size()>0) {
			return mappingId.get(0)+1;
		} else {
			return 1;
		}
	}
}
