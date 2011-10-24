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
}
