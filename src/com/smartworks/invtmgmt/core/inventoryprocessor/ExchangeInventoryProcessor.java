package com.smartworks.invtmgmt.core.inventoryprocessor;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.smartworks.invtmgmt.business.ExchangeInvt;
import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.dao.impl.ExchangeSkuDaoImpl;
import com.smartworks.invtmgmt.core.dao.impl.TransctionDetailsDaoImpl;
import com.smartworks.invtmgmt.core.domain.ExchangeSkuRecord;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.TransactionDetails;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;
import com.smartworks.invtmgmt.core.transaction.ReasonCodeEnum;
import com.smartworks.invtmgmt.web.ui.controller.InventoryDetailsController;

public class ExchangeInventoryProcessor extends InventoryChangeProcessor {

	protected static Logger logger = Logger.getLogger(InventoryDetailsController.class);
	
	private TransctionDetailsDaoImpl transctionDetailsDao;	
	private ExchangeSkuDaoImpl exchangeSkuDao; 
	
	@Override
	public void process(TransactionDetailsHolder transDetails) { 		
		try {
			TransactionTrace transTrace = transactionTraceObjectConverter.createTransTrace(transDetails);
			List<TransactionDetails> details = new ArrayList<TransactionDetails>();
			transTrace.setTransDetails(details);
			
			for(ExchangeInvt exchangeInvt : transDetails.getExchangeInvt()) { 		
				
				ItemSku exSku = exchangeInvt.getExchangedSku();		
				String exchangedItemSkuCode = itemSkuConverter.getItemSkuCode(exSku);
				ItemSku issueSku = exchangeInvt.getIssuedSku();		
				String issuedItemSkuCode = itemSkuConverter.getItemSkuCode(issueSku);				
				
				if(exchangedItemSkuCode.equals(issuedItemSkuCode)) {
					logger.debug("Both issues sku and exchanged sku is same. So not doing anything.");
					continue;
				}
				
				// Load a Transaction Details object which has this skuCode
				TransactionDetails tdtls = transctionDetailsDao.getTransDetails(transDetails.getRefTransactionId(), issuedItemSkuCode);
				// Set the reason code to EXCHANGED
				tdtls.setReasonCode(ReasonCodeEnum.EXCHANGED);
				tdtls.setSkuCode(exchangedItemSkuCode);
				transctionDetailsDao.save(tdtls);
				
				InventoryPk inventoryPk = new InventoryPk();
				Location loc = locationDao.load(transDetails.getLocationId());
				inventoryPk.setLocation(loc);
				// Add the inventory back to the system.
				inventoryPk.setSkuCode(issuedItemSkuCode);
				inventoryDao.addAvailableInventory(inventoryPk, issueSku.getQuantity());
				inventoryDao.reduceIssuedInventory(inventoryPk, issueSku.getQuantity());
				
				
				InventoryPk inventoryPk1 = new InventoryPk();
				inventoryPk1.setLocation(locationDao.load(transDetails.getLocationId()));
				// Reduce the inventory for the new item
				inventoryPk1.setSkuCode(exchangedItemSkuCode);
				inventoryDao.reduceAvailableInventory(inventoryPk1, issueSku.getQuantity());
				inventoryDao.addIssuedInventory(inventoryPk1, issueSku.getQuantity());
				
				ExchangeSkuRecord exchangeSkuRecord = new ExchangeSkuRecord();
				exchangeSkuRecord.setIssuedSku(issuedItemSkuCode);
				exchangeSkuRecord.setExchangedSku(exchangedItemSkuCode);
				exchangeSkuRecord.setTransactionDetails(tdtls);
				
				exchangeSkuDao.save(exchangeSkuRecord);
				
			}
		} catch(Exception e) {
			logger.error("Exception processing inventory exchange ", e);
			throw new RuntimeException(e);
		}
		
		
		
	}

	public TransctionDetailsDaoImpl getTransctionDetailsDao() {
		return transctionDetailsDao;
	}

	public void setTransctionDetailsDao(TransctionDetailsDaoImpl transctionDetailsDao) {
		this.transctionDetailsDao = transctionDetailsDao;
	}

	public ExchangeSkuDaoImpl getExchangeSkuDao() {
		return exchangeSkuDao;
	}

	public void setExchangeSkuDao(ExchangeSkuDaoImpl exchangeSkuDao) {
		this.exchangeSkuDao = exchangeSkuDao;
	}


}
