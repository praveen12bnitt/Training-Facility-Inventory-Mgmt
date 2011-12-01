package com.smartworks.invtmgmt.converter;

import java.util.List;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.web.ui.form.IssueSkuForm;

public class UIDomainConverter {
	
	public static TransactionDetailsHolder transferToTransactionDetailsHolder(IssueSkuForm issueSkuForm) {
		TransactionDetailsHolder transDetails = new TransactionDetailsHolder();		
		List<ItemSku> itemSkus = issueSkuForm.getItemSkus();		
		transDetails.setItemSkus(itemSkus);		
		transDetails.setLocationId(issueSkuForm.getLocationId());
		transDetails.setUserId(100);
		if(issueSkuForm.getTransactionType().isStaffTransaction()) {
			transDetails.setStaffId(issueSkuForm.getStaff().getStaffId());
		} else {
			if(issueSkuForm.getTrainee() != null)
				transDetails.setTraineeId(issueSkuForm.getTrainee().getTraineeId());
		}		
		transDetails.setTransactionType(issueSkuForm.getTransactionType());
		transDetails.setRefTransactionId(issueSkuForm.getRefTransactionId());
		return transDetails;
	}
	
}
