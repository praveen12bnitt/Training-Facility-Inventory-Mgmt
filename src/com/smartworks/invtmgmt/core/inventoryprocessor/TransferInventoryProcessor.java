package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;

public class TransferInventoryProcessor extends InventoryChangeProcessor {

	@Override
	public void process(TransactionDetailsHolder transDetails) {
		for (ItemSku itemSku : transDetails.getItemSkus()) {
			
			if(itemSku.getQuantity() == null || itemSku.getQuantity() < 0) {
				continue;
			}
			
			InventoryPk srcInventoryPk = new InventoryPk();
			InventoryPk targetInventoryPk = new InventoryPk();
			
			srcInventoryPk.setLocation(new Location(transDetails.getSrcLocationId()));
			srcInventoryPk.setSkuCode(itemSkuConverter.getItemSkuCode(itemSku));
			
			targetInventoryPk.setLocation(new Location(transDetails.getLocationId()));
			targetInventoryPk.setSkuCode(itemSkuConverter.getItemSkuCode(itemSku));
			
			// -1 means no existent location. May be the inventory is sent out of our system. So no reduce requried.
			if(transDetails.getSrcLocationId() != -1) {
				inventoryDao.reduceAvailableInventory(srcInventoryPk, itemSku.getQuantity());	
			}
			
			if(transDetails.getLocationId() != -1) {
				inventoryDao.addAvailableInventory(targetInventoryPk, itemSku.getQuantity());
			}					
		}		
		TransactionTrace transTrace = transactionTraceObjectConverter.getTransactionTrace(transDetails);
		transactionTraceDao.save(transTrace);		
	}

}
