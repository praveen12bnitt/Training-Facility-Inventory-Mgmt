package com.smartworks.invtmgmt.converter;

import java.util.ArrayList;
import java.util.List;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.web.ui.form.IssueSkuForm;

public class UIDomainConverter {
	
	public static TransactionDetailsHolder transferToTransactionDetailsHolder(IssueSkuForm issueSkuForm) {
		TransactionDetailsHolder transDetails = new TransactionDetailsHolder();
		
		List<ItemSku> itemSkus = issueSkuForm.getItemSkus();		
		List<ItemSku> removeList = new ArrayList<ItemSku>();
		
		for(ItemSku sku : issueSkuForm.getItemSkus() ) {
			if(sku.getQuantity() == null || sku.getQuantity() < 1) {
				removeList.add(sku);
			}
		}		
		for (ItemSku removeItemSku : removeList) {
			itemSkus.remove(removeItemSku);
		}
		
		transDetails.setItemSkus(itemSkus);		
		transDetails.setLocationId(issueSkuForm.getLocationId());
		transDetails.setUserId(100);
		if(issueSkuForm.getTrainee() != null) {
			transDetails.setTraineeId(issueSkuForm.getTrainee().getTraineeId());
		}		
		transDetails.setTransactionType(issueSkuForm.getTransactionType());
		transDetails.setRefTransactionId(issueSkuForm.getRefTransactionId());
		return transDetails;
	}
	
}
