package com.smartworks.invtmgmt.core.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.ItemAttributeDao;
import com.smartworks.invtmgmt.core.domain.ItemAttribute;
import com.smartworks.invtmgmt.core.domain.Location;

public class ItemAttributeDaoImpl extends HibernateDaoSupport implements ItemAttributeDao {

	@Override
	public ItemAttribute load(Integer itemAttrId) {
		return getHibernateTemplate().load(ItemAttribute.class, itemAttrId);
	}
	
	@Override
	public ItemAttribute findByAttributeName(String attributeName) {
		String query = "from ItemAttribute where ATTR_NAME=:attributeName";
		List<ItemAttribute> itemAttributes = getHibernateTemplate().findByNamedParam(query, "attributeName", attributeName);
		if(itemAttributes.size()==0) {
			return null;
		} else {
			return itemAttributes.get(0);
		}
		
	}

	@Override
	public Integer getNextAttributeId() {
		String query = "select max(ia.attibuteId) from ItemAttribute ia";
		List<Integer> attrId = getHibernateTemplate().find(query);
		if(attrId !=null && attrId.size()>0) {
			return attrId.get(0)+1;
		} else {
			return 1;
		}
	}

	@Override
	public void save(ItemAttribute itemAttribute) {
		getHibernateTemplate().save(itemAttribute);
		
	}
	
	

}
