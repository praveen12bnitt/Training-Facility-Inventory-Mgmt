package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;

public class LaundryReturnsProcessor extends InventoryChangeProcessor {

	@Override
	public void process(TransactionDetailsHolder transDetails) {
		
		for (ItemSku itemSku : transDetails.getItemSkus()) {
			InventoryPk inventoryPk = new InventoryPk();
			Location loc = new Location(transDetails.getLocationId());
			inventoryPk.setLocation(loc);
			
			String itemSkuCode = itemSkuConverter.getItemSkuCode(itemSku);
			inventoryPk.setSkuCode(itemSkuCode);
			boolean processingReq = itemSku.getItem().getRequiresProcessing();
			if(processingReq) {
				inventoryDao.reduceUnusableInventory(inventoryPk, itemSku.getQuantity());		
			} else {
				inventoryDao.addAvailableInventory(inventoryPk, itemSku.getQuantity());
			}
		}
		
		TransactionTrace transTrace = transactionTraceObjectConverter.getTransactionTrace(transDetails);
		transactionTraceDao.save(transTrace);			
		
	}

}
