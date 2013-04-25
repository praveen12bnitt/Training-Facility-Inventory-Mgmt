package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;

public class PreIssueInventoryProcessor extends DispatchInventoryProcessor {
	
	@Override
	protected void saveTransactionTrace(TransactionDetailsHolder transDetails) {
		TransactionTrace transTrace = transactionTraceObjectConverter.getTransactionTrace(transDetails);
		transTrace.setTransType(transTrace.getTransType().getIssueTrans());
		transTrace.setTrasactionId(transTrace.getRefTransactionId());
		transTrace.setRefTransactionId(null);
		transactionTraceDao.update(transTrace);	
	}

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}
}
