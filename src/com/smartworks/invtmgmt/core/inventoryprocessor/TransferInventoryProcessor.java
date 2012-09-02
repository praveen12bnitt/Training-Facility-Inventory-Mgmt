package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;
import com.smartworks.invtmgmt.core.exception.NoItemsForTransactionException;

public class TransferInventoryProcessor extends InventoryChangeProcessor {

	@Override
	public void process(TransactionDetailsHolder transDetails) {
		boolean hasInventory = false;
		for (ItemSku itemSku : transDetails.getItemSkus()) {
			
			if(itemSku.getQuantity() == null || itemSku.getQuantity() < 1) {
				continue;
			}
			hasInventory = true;
			InventoryPk srcInventoryPk = new InventoryPk();
			InventoryPk targetInventoryPk = new InventoryPk();
			
			srcInventoryPk.setLocation(locationDao.load(transDetails.getSrcLocationId()));
			srcInventoryPk.setSkuCode(itemSkuConverter.getItemSkuCode(itemSku));
			
			targetInventoryPk.setLocation(locationDao.load(transDetails.getLocationId()));
			targetInventoryPk.setSkuCode(itemSkuConverter.getItemSkuCode(itemSku));
			
			// -1 means no existent location. May be the inventory is sent out of our system. So no reduce requried.
			if(transDetails.getSrcLocationId() != -1) {
				inventoryDao.reduceAvailableInventory(srcInventoryPk, itemSku.getQuantity());	
			}
			
			if(transDetails.getLocationId() != -1) {
				inventoryDao.addAvailableInventory(targetInventoryPk, itemSku.getQuantity());
			}					
		}
		
		if(!hasInventory) {
			throw new NoItemsForTransactionException("No items to transfer");
		}
		
		TransactionTrace transTrace = transactionTraceObjectConverter.getTransactionTrace(transDetails);
		transactionTraceDao.save(transTrace);		
	}

}
