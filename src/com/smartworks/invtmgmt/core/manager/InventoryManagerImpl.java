package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.domain.Inventory;

public class InventoryManagerImpl implements InventoryManager {

	InventoryDao inventoryDao;
	
	@Override
	public List<Inventory> getAllInventory() {
		return inventoryDao.loadAll();
	}

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}
	
	

	
}
