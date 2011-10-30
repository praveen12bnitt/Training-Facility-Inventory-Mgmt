package com.smartworks.invtmgmt.core.inventoryprocessor;

import java.util.List;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;
import com.smartworks.invtmgmt.util.ItemSkuUtil;

public class MissingInventoryProcessor implements InventoryChangeProcessor {
	
	InventoryDao inventoryDao;

	public void process(Integer locationId, List<ItemSku> skus) {
		// First reduce the inventory
		InventoryPk inventoryPk = new InventoryPk();
		Location loc = new Location();
		loc.setLocationId(locationId);
		inventoryPk.setLocation(loc);

		for (ItemSku itemSku : skus) {
			String itemSkuCode = ItemSkuUtil.getItemSkuCode(itemSku);
			inventoryPk.setSkuCode(itemSkuCode);
			// Do nothing... Just log it in transaction trace table
		}
	}

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}
}
