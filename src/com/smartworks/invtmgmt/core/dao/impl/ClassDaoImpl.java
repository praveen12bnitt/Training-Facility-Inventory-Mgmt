package com.smartworks.invtmgmt.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.converter.UtilityClass;
import com.smartworks.invtmgmt.core.dao.ClassDao;
import com.smartworks.invtmgmt.core.domain.Class;
import com.smartworks.invtmgmt.core.domain.Product;

public class ClassDaoImpl extends HibernateDaoSupport implements ClassDao{

	UtilityClass utilityClass;
	
	@Override
	public void saveOrUpdate(Class cls) {
		getHibernateTemplate().saveOrUpdate(cls);
	}

	@Override
	public Class load(String className) {
		Class cls = getHibernateTemplate().load(Class.class, className);
		return cls;
	}

	@Override
	public List<Class> loadAll() {
		List<Class> classList = getHibernateTemplate().loadAll(Class.class);
		return classList;
	}

	@Override
	public void save(Class cls) {
		getHibernateTemplate().save(cls);
	}

	@Override
	public void update(Class t) {
		getHibernateTemplate().update(t);
	}

	@Override
	public List<String> getItemsByNameLike(String name) {
		String query = "from Product where product_name like :name" ;				
		List<Object> itemNames = getHibernateTemplate().findByNamedParam(query, "name", "%"+name+"%");
		List<String> itemsMap = new ArrayList<String>();
		for(Object items: itemNames) {
			itemsMap.add((((Product) items).getProductName()));
		}
		return itemsMap;
	}


}
