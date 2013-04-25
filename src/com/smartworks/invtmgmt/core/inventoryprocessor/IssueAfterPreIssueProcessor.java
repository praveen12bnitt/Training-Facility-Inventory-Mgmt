package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.service.PreissueService;

public class IssueAfterPreIssueProcessor extends DispatchInventoryProcessor {
	
	private PreissueService preissueService;

	@Override
	protected void saveTransactionTrace(TransactionDetailsHolder transDetails) { 		
		// Load the transaction trace
		TransactionTrace transactionTrace = preissueService.alterTransactionTrace(transDetails.getItemSkus(), transDetails.getRefTransactionId());
		transactionTrace.setTransType(transDetails.getTransactionType());
		transactionTraceDao.update(transactionTrace);		
	}
}
