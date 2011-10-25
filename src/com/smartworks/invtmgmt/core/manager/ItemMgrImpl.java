package com.smartworks.invtmgmt.core.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.core.dao.ItemDao;
import com.smartworks.invtmgmt.core.dao.TransactionItemMappingDao;
import com.smartworks.invtmgmt.core.dao.impl.TransactionItemMappingDaoImpl;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.TransactionItemMapping;
import com.smartworks.invtmgmt.core.domain.TransactionType;

@Transactional
public class ItemMgrImpl implements ItemMgr {

	ItemDao itemDao; 
	TransactionItemMappingDao transactionItemMappingDao;
	
	
	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public Item getItem(Integer itemId) {
		Item i = itemDao.load(itemId);	
		return i;
	}
	
	public List<Item> getItemsForTransaction(TransactionType transactionType) {
		
		// Get the list of UI items for this transactions		
		List<Item> itemList = new ArrayList<Item>();		
		List<TransactionItemMapping> mappings = transactionItemMappingDao.loadMappingForTransaction(transactionType);		
		for(TransactionItemMapping mapping : mappings) {
			itemList.add(mapping.getTransItemMappingPk().getItem());
		}
		
		return itemList;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public TransactionItemMappingDao getTransactionItemMappingDao() {
		return transactionItemMappingDao;
	}

	public void setTransactionItemMappingDao(
			TransactionItemMappingDao transactionItemMappingDao) {
		this.transactionItemMappingDao = transactionItemMappingDao;
	}

	

}
