package com.smartworks.invtmgmt.core.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.core.dao.ItemDao;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.ItemAttribute;
import com.smartworks.invtmgmt.core.domain.ItemAttributeMapping;
import com.smartworks.invtmgmt.core.domain.ItemAttributeValue;

@Transactional
public class ItemMgrImpl implements ItemMgr {

	ItemDao dao; 
	
	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public Item getItem(Integer itemId) {
		Item i = dao.load(itemId);
		Set<ItemAttributeMapping> set = i.getAttributeMappings();
				
		Map<ItemAttribute, List<ItemAttributeValue>> attributeDetails = new TreeMap<ItemAttribute, List<ItemAttributeValue>>();
		
		for (ItemAttributeMapping itemAttributeMapping : set) {
			
			
			ItemAttribute attribute = itemAttributeMapping.getAttribute();
			ItemAttributeValue attributeVAl = itemAttributeMapping.getAttributeValue();
			
			if(!attributeDetails.containsKey(attribute)) {
				List<ItemAttributeValue> vals = new ArrayList<ItemAttributeValue>();
				vals.add(attributeVAl);
				attributeDetails.put(attribute, vals);
			} else
			{
				List<ItemAttributeValue> vals = attributeDetails.get(attribute);
				vals.add(attributeVAl);
			}		
			
		}
		
		i.setAttributeDetails(attributeDetails);	
		
		return i;
	}

	public ItemDao getDao() {
		return dao;
	}

	public void setDao(ItemDao dao) {
		this.dao = dao;
	}

}
