package com.smartworks.invtmgmt.core.manager;

import java.util.Set;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.core.dao.ItemDao;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.ItemAttributeMapping;

@Transactional
public class ItemMgrImpl implements ItemMgr {

	ItemDao dao; 
	
	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public Item getItem(Integer itemId) {
		Item i = dao.load(itemId);
		
		String desc = i.getDesc();
//		int id = i.getId();
//		String name = i.getName();

//		System.out.println("id:"+id);
//		System.out.println("name:"+name);
		System.out.println("desc:"+desc);
		Set<ItemAttributeMapping> set = i.getAttributeMappings();
		for (ItemAttributeMapping itemAttributeMapping : set) {
			System.out.println(itemAttributeMapping.getAttribute());
		}
		return i;
	}

	public ItemDao getDao() {
		return dao;
	}

	public void setDao(ItemDao dao) {
		this.dao = dao;
	}

}
