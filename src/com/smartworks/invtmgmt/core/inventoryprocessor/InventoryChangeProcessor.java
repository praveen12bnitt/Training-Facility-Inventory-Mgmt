package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.dao.TransactionTraceDao;

public abstract class InventoryChangeProcessor {
	
	InventoryDao inventoryDao;
	TransactionTraceDao transactionTraceDao;

	public abstract void process(TransactionDetailsHolder transDetails) ;

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	public TransactionTraceDao getTransactionTraceDao() {
		return transactionTraceDao;
	}

	public void setTransactionTraceDao(TransactionTraceDao transactionTraceDao) {
		this.transactionTraceDao = transactionTraceDao;
	}

	
}
