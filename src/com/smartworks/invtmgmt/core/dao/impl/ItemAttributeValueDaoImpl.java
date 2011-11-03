package com.smartworks.invtmgmt.core.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.ItemAttributeValueDao;
import com.smartworks.invtmgmt.core.domain.ItemAttributeValue;

public class ItemAttributeValueDaoImpl extends HibernateDaoSupport implements
		ItemAttributeValueDao {

	@Override
	public ItemAttributeValue load(Integer itemAttrValueId) {
		return getHibernateTemplate().load(ItemAttributeValue.class, itemAttrValueId);
	}

}
