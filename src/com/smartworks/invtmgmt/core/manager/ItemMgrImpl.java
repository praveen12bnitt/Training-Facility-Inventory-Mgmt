package com.smartworks.invtmgmt.core.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.core.dao.ItemAttributeDao;
import com.smartworks.invtmgmt.core.dao.ItemAttributeValueDao;
import com.smartworks.invtmgmt.core.dao.ItemDao;
import com.smartworks.invtmgmt.core.dao.ProductDao;
import com.smartworks.invtmgmt.core.dao.TransactionItemMappingDao;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.Product;
import com.smartworks.invtmgmt.core.domain.TransactionItemMapping;
import com.smartworks.invtmgmt.core.domain.TransactionType;

@Transactional
public class ItemMgrImpl implements ItemMgr {

	ItemDao itemDao; 
	ItemAttributeDao itemAttributeDao;
	ItemAttributeValueDao itemAttributeValueDao;
	TransactionItemMappingDao transactionItemMappingDao;
	
	@Autowired
	ProductDao productDao;
	
	
	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public Item getItem(Integer itemId) {
		Item i = itemDao.load(itemId);
		//Explicitly load attributes
		i.getAttributeDetails();
		return i;
	}
	
	public List<Item> getItemsForTransaction(TransactionType transactionType) {		
		// Get the list of UI items for this transactions		
		List<Item> itemList = new ArrayList<Item>();		
		List<TransactionItemMapping> mappings = transactionItemMappingDao.loadMappingForTransaction(transactionType);		
		for(TransactionItemMapping mapping : mappings) {
			Item i = mapping.getTransItemMappingPk().getItem();
			// Explicitly load attributes
			i.getAttributeDetails();
			itemList.add(i);
		}		
		return itemList;
	}
	
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<Item> getItemsByProductId(Integer productId) {
		Product product = productDao.load(productId);
		List<Integer> itemds = product.getItemsIds();
		
		List<Item> items = itemDao.loadSelectedItems(itemds);
		return items;
		
	}
	
	@Override
	public List<Item> getAllItems() {
		List<Item> itemList =  itemDao.loadAll();
		for (Item item : itemList) {
			item.getAttributeDetails();
		}
		return itemList;
	}	
	
	public List<Item> getItemsByName(String name) {
		List<Item> itemList = itemDao.getItemsByName(name);
		for (Item item : itemList) {
			item.getAttributeDetails();
		}		
		return itemList;
	}
	
	
	@Override
	public Map<Integer, String> getItemMaps(String name) {
			
		Map<Integer, String> itemMap = itemDao.getItemsByNameLike(name);
		
		return itemMap;
	}	
	
	public List<String> getItemNamesLike(String name) {
		return itemDao.getItemNamesLike(name);
	}
	
	public List<Item> getAllItemsWithoutAttribute() {
		return itemDao.loadAll();
	}
	
	public Item getItemWithoutAttribute(Integer itemId) {
		return itemDao.load(itemId);
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

	public ItemAttributeDao getItemAttributeDao() {
		return itemAttributeDao;
	}

	public ItemAttributeValueDao getItemAttributeValueDao() {
		return itemAttributeValueDao;
	}

	public void setItemAttributeDao(ItemAttributeDao itemAttributeDao) {
		this.itemAttributeDao = itemAttributeDao;
	}

	public void setItemAttributeValueDao(ItemAttributeValueDao itemAttributeValueDao) {
		this.itemAttributeValueDao = itemAttributeValueDao;
	}

	

}
