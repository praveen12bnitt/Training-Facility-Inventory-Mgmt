package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;
import com.smartworks.invtmgmt.core.exception.NoItemsForTransactionException;

public class DispatchInventoryProcessor extends InventoryChangeProcessor {
	
	public void process(TransactionDetailsHolder transDetails) {
		// First reduce the inventory 				
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
			inventoryDao.reduceAvailableInventory(inventoryPk, itemSku.getQuantity());
			inventoryDao.addIssuedInventory(inventoryPk, itemSku.getQuantity());
			
		}
		
		if(!hasInventory) {
			throw new NoItemsForTransactionException("No items to issue");
		}
		
		saveTransactionTrace(transDetails);
	}
	
	protected void saveTransactionTrace(TransactionDetailsHolder transDetails) 
	{
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
