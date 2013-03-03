package com.smartworks.invtmgmt.core.service;

import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.converter.ItemSkuConverter;
import com.smartworks.invtmgmt.core.dao.TransactionTraceDao;
import com.smartworks.invtmgmt.core.domain.TransactionDetails;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.web.ui.form.IssueSkuForm;

@Transactional
public class PreissueServiceImpl implements PreissueService {

	private TransactionTraceDao transactionTraceDao;

	ItemSkuConverter itemSkuConverter = new ItemSkuConverter();

	@Override
	public void updatePreIssue(IssueSkuForm issueSkuForm) {
		Integer transactionId = issueSkuForm.getRefTransactionId();
		TransactionTrace transactionTrace = transactionTraceDao.load(transactionId);
		for (ItemSku itemSku : issueSkuForm.getItemSkus()) { 
			if (itemSku.getQuantity() == null || itemSku.getQuantity() < 0) {
				continue;
			}
			String itemSkuCode = itemSkuConverter.getItemSkuCode(itemSku);
			TransactionDetails transactionDetails = getTransactionDetails(transactionTrace, itemSkuCode);
			if (itemSku.getQuantity() == null || itemSku.getQuantity() < 1 ) {
				if(transactionDetails != null) 
				{
					transactionTrace.getTransDetails().remove(transactionDetails);
					transactionDetails.setTrasactionTrace(null);					
				}				
				continue;
			}
			transactionDetails.setQuantity(itemSku.getQuantity());
			if(transactionDetails.getTransactionDetailsId() == null)
			{
				transactionTraceDao.save(transactionDetails);
			}
		}		
		transactionTraceDao.update(transactionTrace);
	}

	public TransactionDetails getTransactionDetails(TransactionTrace transactionTrace, String skuCode) {
		for (TransactionDetails transactionDetails : transactionTrace.getTransDetails()) {
			if (transactionDetails.getSkuCode().equals(skuCode)) {
				return transactionDetails;
			}
		}
		TransactionDetails transactionDetails = new TransactionDetails();
		transactionTrace.getTransDetails().add(transactionDetails);
		transactionDetails.setTrasactionTrace(transactionTrace);
		transactionDetails.setSkuCode(skuCode);
		return transactionDetails;
	}

	public TransactionTraceDao getTransactionTraceDao() {
		return transactionTraceDao;
	}

	public void setTransactionTraceDao(TransactionTraceDao transactionTraceDao) {
		this.transactionTraceDao = transactionTraceDao;
	}

}
