package com.smartworks.invtmgmt.core.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.ItemAttributeValueDao;
import com.smartworks.invtmgmt.core.domain.ItemAttribute;
import com.smartworks.invtmgmt.core.domain.ItemAttributeValue;

public class ItemAttributeValueDaoImpl extends HibernateDaoSupport implements
		ItemAttributeValueDao {

	@Override
	public ItemAttributeValue load(Integer itemAttrValueId) {
		return getHibernateTemplate().load(ItemAttributeValue.class, itemAttrValueId);
	}
	
	@Override
	public ItemAttributeValue findByAttributeValue(String attributeValue)  {
		String query = "from ItemAttributeValue where ATTR_VALUE=:attributeValue";
		List<ItemAttributeValue> itemAttributeValues = getHibernateTemplate().findByNamedParam(query, "attributeValue", attributeValue);
		if(itemAttributeValues.size()==0) {
			return null;
		} else {
			return itemAttributeValues.get(0);
		}
		
	}
	
	@Override
	public Integer getNextItemAttributeValueId() {
		String query = "select max(ia.attributeValueId) from ItemAttributeValue ia";
		List<Integer> attrValueId = getHibernateTemplate().find(query);
		if(attrValueId !=null && attrValueId.size()>0) {
			return attrValueId.get(0)+1;
		} else {
			return 1;
		}
	}
	
	@Override
	public void save(ItemAttributeValue itemAttributeValue) {
		getHibernateTemplate().save(itemAttributeValue);
		
	}

}
