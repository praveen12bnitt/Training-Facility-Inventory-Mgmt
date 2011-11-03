package com.smartworks.invtmgmt.core.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.ItemAttributeDao;
import com.smartworks.invtmgmt.core.domain.ItemAttribute;

public class ItemAttributeDaoImpl extends HibernateDaoSupport implements ItemAttributeDao {

	@Override
	public ItemAttribute load(Integer itemAttrId) {
		return getHibernateTemplate().load(ItemAttribute.class, itemAttrId);
	}

}
