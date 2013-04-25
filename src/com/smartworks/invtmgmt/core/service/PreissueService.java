package com.smartworks.invtmgmt.core.service;

import java.util.List;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.web.ui.form.IssueSkuForm;

public interface PreissueService {

	public void updatePreIssue(IssueSkuForm issueSkuForm) ;
	public TransactionTrace alterTransactionTrace(List<ItemSku> itemSkus, Integer transactionTraceId);
	
}
