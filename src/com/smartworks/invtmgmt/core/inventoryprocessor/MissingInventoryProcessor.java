package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;

public class MissingInventoryProcessor extends InventoryChangeProcessor {

	public void process(TransactionDetailsHolder transDetails) {
		// First reduce the inventory
		for (ItemSku itemSku : transDetails.getItemSkus()) {
			if(itemSku.getQuantity() == null || itemSku.getQuantity() < 1 || itemSku.getReasonCode() != null) {
				continue;
			}
			
			InventoryPk inventoryPk = new InventoryPk();
			Location loc = locationDao.load(transDetails.getLocationId());
			inventoryPk.setLocation(loc);
			String itemSkuCode = itemSkuConverter.getItemSkuCode(itemSku);
			inventoryPk.setSkuCode(itemSkuCode);
			inventoryDao.reduceAvailableInventory(inventoryPk, itemSku.getQuantity());
			inventoryDao.addMissingDamagedInventory(inventoryPk, itemSku.getQuantity(), itemSku.getReasonCode());
		}
		TransactionTrace transTrace = transactionTraceObjectConverter.getTransactionTrace(transDetails);
		transactionTraceDao.save(transTrace);			
	}

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}
}
