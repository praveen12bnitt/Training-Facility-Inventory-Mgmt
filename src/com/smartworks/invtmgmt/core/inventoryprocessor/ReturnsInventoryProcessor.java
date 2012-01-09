package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;
import com.smartworks.invtmgmt.core.exception.NoItemsForTransactionException;
import com.smartworks.invtmgmt.core.transaction.ReasonCodeEnum;

public class ReturnsInventoryProcessor extends InventoryChangeProcessor {
	
	public void process(TransactionDetailsHolder transDetails) {
		// First reduce the inventory
		//TODO : Throw exception if refTransactionId is null
		boolean hasInventory = false;
		for (ItemSku itemSku : transDetails.getItemSkus()) {			
			
			if(itemSku.getQuantity() == null || itemSku.getQuantity() < 1) {
				continue;
			}
			hasInventory = true;
			// Reason code not required for return
			itemSku.setReasonCode((ReasonCodeEnum)null);
			
			InventoryPk inventoryPk = new InventoryPk();
			Location loc = new Location(transDetails.getLocationId());
			inventoryPk.setLocation(loc);
			
			String itemSkuCode = itemSkuConverter.getItemSkuCode(itemSku);
			inventoryPk.setSkuCode(itemSkuCode);
			Boolean processingReq = itemSku.getItem().getRequiresProcessing();
			if(processingReq != null && processingReq.booleanValue()) {
				inventoryDao.addUnusableInventory(inventoryPk, itemSku.getQuantity());				
			} else {
				inventoryDao.addAvailableInventory(inventoryPk, itemSku.getQuantity());
			}
			
			inventoryDao.reduceIssuedInventory(inventoryPk, itemSku.getQuantity());
			
		}
		
		if(!hasInventory) {
			throw new NoItemsForTransactionException("No items to return");
		}
		
		TransactionTrace transTrace = transactionTraceObjectConverter.getTransactionTrace(transDetails);
		transactionTraceDao.save(transTrace);			
		// Load the original transaction and mark it as closed.
		transactionTraceDao.markTransactionClosed(transTrace.getRefTransactionId());
		
//		TransactionTrace issuedTrace = transactionTraceDao.load(transTrace.getRefTransactionId());		
//		TransactionTrace missingTrace = findMissingItems(issuedTrace,transTrace);		
//		if(missingTrace != null) {
//			for(TransactionDetails d : missingTrace.getTransDetails()) {
//				d.setTrasactionTrace(missingTrace);
//			}
//			transactionTraceDao.save(missingTrace);
//		}
			
	}
	
	/**
	private TransactionTrace findMissingItems(TransactionTrace issued, TransactionTrace returned ){
		List<TransactionDetails> issuedDetails = issued.getTransDetails();
		List<TransactionDetails> returnedDetails = returned.getTransDetails();
		Map<String, Integer> issuedDetailsMap = new HashMap<String, Integer>();
		Map<String, Integer> returnedDetailsMap = new HashMap<String, Integer>();
		
		List<TransactionDetails> missingItems = new ArrayList<TransactionDetails>();
		
		for (TransactionDetails transactionDetails : issuedDetails) {
			issuedDetailsMap.put(transactionDetails.getSkuCode(), transactionDetails.getQuantity());
		}
		
		for (TransactionDetails transactionDetails : returnedDetails) {
			returnedDetailsMap.put(transactionDetails.getSkuCode(), transactionDetails.getQuantity());
		}
		
		for(String skuCode : issuedDetailsMap.keySet()) {
			Integer issuedQty = issuedDetailsMap.get(skuCode);
			Integer returnedQty = returnedDetailsMap.get(skuCode);
			if(issuedQty > returnedQty) {
				TransactionDetails missingTransDetails = new TransactionDetails();
				missingTransDetails.setSkuCode(skuCode);
				missingTransDetails.setQuantity(issuedQty-returnedQty);
				missingItems.add(missingTransDetails);
			}
		}
		
		if(missingItems.size() > 0) {
			TransactionTrace missingTrace = new TransactionTrace();
			
			missingTrace.setClosed(true);
			missingTrace.setCreatedDttm(returned.getCreatedDttm());
			missingTrace.setTraineeId(returned.getTraineeId());
			missingTrace.setLocationId(returned.getLocationId());
			missingTrace.setRefTransactionId(returned.getRefTransactionId() );
			missingTrace.setSrcLocationId(returned.getSrcLocationId());
			missingTrace.setStaffId(returned.getStaffId());
			missingTrace.setTransDetails(missingItems);
			missingTrace.setUserId(returned.getUserId());	
			missingTrace.setTransType(TransactionTypeEnum.REPORT_MISSING_UNIFORM);
			return missingTrace;
		} else {
			return null;
		}		
	}
	
	**/

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}
}
