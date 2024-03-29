package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;

public class PreIssueInventoryProcessor extends InventoryChangeProcessor {
	
	@Override
	public void process(TransactionDetailsHolder transDetails) {
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
