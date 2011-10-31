package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.converter.TransactionTraceObjectConverter;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;
import com.smartworks.invtmgmt.util.ItemSkuUtil;

public class ReturnsInventoryProcessor extends InventoryChangeProcessor {
	
	public void process(TransactionDetailsHolder transDetails) {
		// First reduce the inventory
		//TODO : Throw exception if refTransactionId is null
		//TODO : Compare this transaction with the original issue transaction and find out missing items
		for (ItemSku itemSku : transDetails.getItemSkus()) {
			InventoryPk inventoryPk = new InventoryPk();
			Location loc = new Location();
			loc.setLocationId(transDetails.getLocationId());
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
		
		TransactionTrace transTrace = TransactionTraceObjectConverter.getTransactionTrace(transDetails);
		transactionTraceDao.save(transTrace);			
		// Load the original transaction and mark it as closed.
		transactionTraceDao.markTransactionClosed(transTrace.getRefTransactionId());
	}

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}
}
