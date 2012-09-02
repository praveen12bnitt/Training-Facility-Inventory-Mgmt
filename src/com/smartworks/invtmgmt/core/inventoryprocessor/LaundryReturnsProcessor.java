package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;
import com.smartworks.invtmgmt.core.exception.NoItemsForTransactionException;
import com.smartworks.invtmgmt.core.exception.NotALaundryItemException;

public class LaundryReturnsProcessor extends InventoryChangeProcessor {

	@Override
	public void process(TransactionDetailsHolder transDetails) {
		boolean hasInventory = false;
		for (ItemSku itemSku : transDetails.getItemSkus()) {
			
			if(itemSku.getQuantity() == null || itemSku.getQuantity() < 1) {
				continue;
			}	
			hasInventory = true;			
			InventoryPk inventoryPk = new InventoryPk();
			Location loc = locationDao.load(transDetails.getLocationId());
			inventoryPk.setLocation(loc);			
			String itemSkuCode = itemSkuConverter.getItemSkuCode(itemSku);
			inventoryPk.setSkuCode(itemSkuCode);
			boolean processingReq = itemSku.getItem().getRequiresProcessing();
			if(processingReq) {
				inventoryDao.addAvailableInventory(inventoryPk, itemSku.getQuantity());	
				inventoryDao.reduceUnusableInventory(inventoryPk, itemSku.getQuantity());					
			} else {
				throw new NotALaundryItemException(itemSkuCode, itemSku.getQuantity(), loc.getLocationName());
			}
		}	
		
		if(!hasInventory) {
			throw new NoItemsForTransactionException("No items to return");
		}
		
		TransactionTrace transTrace = transactionTraceObjectConverter.getTransactionTrace(transDetails);
		transactionTraceDao.save(transTrace);			
		
	}

}
