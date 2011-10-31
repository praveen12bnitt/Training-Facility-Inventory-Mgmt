package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;
import com.smartworks.invtmgmt.util.ItemSkuUtil;

public class MissingInventoryProcessor extends InventoryChangeProcessor {

	public void process(TransactionDetailsHolder transDetails) {
		// First reduce the inventory
		InventoryPk inventoryPk = new InventoryPk();
		Location loc = new Location();
		loc.setLocationId(transDetails.getLocationId());
		inventoryPk.setLocation(loc);

		for (ItemSku itemSku : transDetails.getItemSkus()) {
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
