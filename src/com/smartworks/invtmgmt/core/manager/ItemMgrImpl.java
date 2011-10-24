package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.core.dao.ItemDao;
import com.smartworks.invtmgmt.core.domain.Item;

@Transactional
public class ItemMgrImpl implements ItemMgr {

	ItemDao dao; 
	
	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public Item getItem(Integer itemId) {
		Item i = dao.load(itemId);	
		return i;
	}
	
	public List<Item> getItemsForTransaction(Integer transactionId) {
		List<Item> items = dao.loadAll();
		return items;
	}

	public ItemDao getDao() {
		return dao;
	}

	public void setDao(ItemDao dao) {
		this.dao = dao;
	}

}
