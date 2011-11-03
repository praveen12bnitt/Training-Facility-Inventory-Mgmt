package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;

public class MissingInventoryProcessor extends InventoryChangeProcessor {

	public void process(TransactionDetailsHolder transDetails) {
		// First reduce the inventory
				
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
