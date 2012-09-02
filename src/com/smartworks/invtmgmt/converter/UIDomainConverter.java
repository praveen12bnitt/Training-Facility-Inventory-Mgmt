package com.smartworks.invtmgmt.converter;

import java.util.ArrayList;
import java.util.List;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;
import com.smartworks.invtmgmt.web.ui.form.IssueSkuForm;

public class UIDomainConverter {
	
	public static TransactionDetailsHolder transferToTransactionDetailsHolder(IssueSkuForm issueSkuForm) {
		TransactionDetailsHolder transDetails = new TransactionDetailsHolder();		
		List<ItemSku> itemSkus = issueSkuForm.getItemSkus();		
		transDetails.setItemSkus(itemSkus);	
		transDetails.setExchangeInvt(issueSkuForm.getExchangeInvt());
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
	
	
	public static TransactionDetailsHolder getMissingTransactionDetailsHolder(IssueSkuForm issueSkuForm) {
		List<ItemSku> itemSkus = issueSkuForm.getItemSkus();
		List<ItemSku> itemSkusClone =new ArrayList<ItemSku>();
			
		for(ItemSku itemSku : itemSkus) {
			if(itemSku.getReasonCode() != null && (itemSku.getOrginalQty() - itemSku.getQuantity()>0))
			{
				
				//itemSku.setQuantity(itemSku.getOrginalQty() - itemSku.getQuantity());
				ItemSku itemSkuClone = new ItemSku();
				itemSkuClone.setItem(itemSku.getItem());
				itemSkuClone.setItemAttributeDtls(itemSku.getItemAttributeDtls());
				itemSkuClone.setItemSkuConverter(itemSku.getItemSkuConverter());
				itemSkuClone.setQuantity(new Integer(itemSku.getOrginalQty() - itemSku.getQuantity()));
				itemSkuClone.setReasonCode(itemSku.getReasonCode());
				itemSkusClone.add(itemSkuClone);
				
				
			}
		}
		if(itemSkusClone != null && !itemSkusClone.isEmpty()) {
			TransactionDetailsHolder missingTransDetails = new TransactionDetailsHolder();
			missingTransDetails.setTransactionType(TransactionTypeEnum.REPORT_MISSING_UNIFORM_STUDENT);
			missingTransDetails.setLocationId(issueSkuForm.getLocationId());
			missingTransDetails.setUserId(100);
			if(issueSkuForm.getTrainee() != null) {
				missingTransDetails.setTraineeId(issueSkuForm.getTrainee().getTraineeId());
			}	
			
			missingTransDetails.setItemSkus(itemSkusClone);
			missingTransDetails.setRefTransactionId(issueSkuForm.getRefTransactionId());
		return missingTransDetails;
		} else {
			return null;
		}
	}

	
}
