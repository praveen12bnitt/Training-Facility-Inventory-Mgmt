package com.smartworks.invtmgmt.core.inventoryprocessor;

import java.util.List;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;
import com.smartworks.invtmgmt.util.ItemSkuUtil;

public class ReturnsInventoryProcessor implements InventoryChangeProcessor {

	InventoryDao inventoryDao;
	
	public void process(Integer locationId, List<ItemSku> skus) {
		// First reduce the inventory	
		for (ItemSku itemSku : skus) {
			InventoryPk inventoryPk = new InventoryPk();
			Location loc = new Location();
			loc.setLocationId(locationId);
			inventoryPk.setLocation(loc);
			
			String itemSkuCode = ItemSkuUtil.getItemSkuCode(itemSku);
			inventoryPk.setSkuCode(itemSkuCode);
			boolean processingReq = itemSku.getReqProcessing();
			if(processingReq) {
				inventoryDao.addUnusableInventory(inventoryPk, itemSku.getQuantity());				
			} else {
				inventoryDao.addAvailableInventory(inventoryPk, itemSku.getQuantity());
			}
		}
	}

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}
}
