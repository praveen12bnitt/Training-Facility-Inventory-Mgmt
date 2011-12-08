package com.smartworks.invtmgmt.core.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.domain.Inventory;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;

@Transactional
public class InventoryManagerImpl implements InventoryManager {

	InventoryDao inventoryDao;
	
	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<Inventory> getAllInventory() {
		return inventoryDao.loadAll();
	}

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}
	
	@Override
	public void updateInventory(Integer updatedQty,Location location, String skuCode) {
		InventoryPk skuLocation=new InventoryPk();
		skuLocation.setLocation(location);
		skuLocation.setSkuCode(skuCode);
		Inventory inv =inventoryDao.load(skuLocation);
		if (inv != null) {
			inv.setAvailableQty(updatedQty);
			inventoryDao.saveOrUpdate(inv);
		}
	
	}

	@Override
	public List<Inventory> getInventoryByLocn(Integer locationId) {
		List <Inventory> invList = new ArrayList<Inventory>();
		InventoryPk skuLocation=new InventoryPk();
		Location location = new Location(locationId);
		skuLocation.setLocation(location);
		invList =inventoryDao.loadAllInventory(location);
		return invList;
	}

	
}
