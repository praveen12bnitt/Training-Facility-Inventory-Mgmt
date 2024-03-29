package com.smartworks.invtmgmt.converter;

import java.util.ArrayList;
import java.util.List;

import com.smartworks.invtmgmt.business.ExchangeInvt;
import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.business.UserTransactionDetails;
import com.smartworks.invtmgmt.core.domain.TransactionDetails;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;

public class TransactionTraceObjectConverter {
	
	ItemSkuConverter itemSkuConverter = new ItemSkuConverter();
	
	public TransactionTrace getTransactionTrace(TransactionDetailsHolder transDetails) {		
		TransactionTrace transTrace = createTransTrace(transDetails);
				
		// Now create trans details object from itemSku
		List<TransactionDetails> details = new ArrayList<TransactionDetails>();
		for (ItemSku itemSku : transDetails.getItemSkus()) {
			
			if(itemSku.getQuantity() == null || itemSku.getQuantity() < 0) {
				continue;
			}
			
			TransactionDetails detail = new TransactionDetails();
			detail.setQuantity(itemSku.getQuantity());
			String itemSkuCode = itemSkuConverter.getItemSkuCode(itemSku);
			detail.setReasonCode(itemSku.getReasonCode());
			detail.setSkuCode(itemSkuCode);
			detail.setTrasactionTrace(transTrace);
			details.add(detail);
		}			
		transTrace.setTransDetails(details);
		return transTrace;
	}
	
	public TransactionTrace createTransTrace(TransactionDetailsHolder transDetails) {
		TransactionTrace transTrace = new TransactionTrace();
		transTrace.setTransType(transDetails.getTransactionType());
		transTrace.setTraineeId(transDetails.getTraineeId());
		transTrace.setLocationId(transDetails.getLocationId());
		transTrace.setSrcLocationId(transDetails.getSrcLocationId());
		transTrace.setRefTransactionId(transDetails.getRefTransactionId());
		transTrace.setStaffId(transDetails.getStaffId());
		transTrace.setUserId(transDetails.getUserId());
		transTrace.setSign(transDetails.getUserSign());
		
		return transTrace;
	}
	
	public TransactionDetailsHolder getTransactionDetailsHolder(TransactionTrace transTrace) {
		TransactionDetailsHolder holder = createTransDtlsHold(transTrace) ;
		
		// Look through the details object and create ItemSku's 		
		List<ItemSku> itemSkus = new ArrayList<ItemSku>();
		
		for(TransactionDetails details : transTrace.getTransDetails() ) {
			ItemSku sku;
			sku = itemSkuConverter.getItemSku(details.getSkuCode());
			sku.setQuantity(details.getQuantity());
			itemSkus.add(sku);			
		}		
		holder.setItemSkus(itemSkus);
		return holder;		
	}
	
	private TransactionDetailsHolder createTransDtlsHold(TransactionTrace transTrace) {
		TransactionDetailsHolder holder = new TransactionDetailsHolder();
		holder.setDttm(transTrace.getCreatedDttm());
		holder.setTraineeId(transTrace.getTraineeId());
		holder.setLocationId(transTrace.getLocationId());
		holder.setRefTransactionId(transTrace.getRefTransactionId());
		holder.setSrcLocationId(transTrace.getSrcLocationId());
		holder.setStaffId(transTrace.getStaffId());
		holder.setTransactionType(transTrace.getTransType());
		holder.setUserId(transTrace.getUserId());
		holder.setUserSign(transTrace.getSign());
		return holder;
	}
	
	public UserTransactionDetails getUserTransactions(TransactionTrace transTrace) {
		UserTransactionDetails details = new UserTransactionDetails();
		details.setTimeStamp(transTrace.getCreatedDttm());
		details.setTrasactionId(transTrace.getTrasactionId());
		return details;
	}

	public ItemSkuConverter getItemSkuConverter() {
		return itemSkuConverter;
	}

	public void setItemSkuConverter(ItemSkuConverter itemSkuConverter) {
		this.itemSkuConverter = itemSkuConverter;
	}

	public TransactionDetailsHolder getIssuedTransactionDetailsHolder(TransactionTrace transTrace) {
		TransactionDetailsHolder holder = createTransDtlsHold(transTrace) ;
		List<ExchangeInvt> exchangeInvts = new ArrayList<ExchangeInvt>();
		for(TransactionDetails details : transTrace.getTransDetails() ) {
			ExchangeInvt exchangeInvt = new ExchangeInvt();
			ItemSku sku;
			sku = itemSkuConverter.getItemSku(details.getSkuCode());
			sku.setQuantity(details.getQuantity());
			exchangeInvt.setIssuedSku(sku);	
			exchangeInvts.add(exchangeInvt);			
		}		
		holder.setExchangeInvt(exchangeInvts); 		
		return holder;		
	}
}
