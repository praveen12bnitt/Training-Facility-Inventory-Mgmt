package com.smartworks.invtmgmt.core.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.business.UserTransactionDetails;
import com.smartworks.invtmgmt.converter.TransactionTraceObjectConverter;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.dao.TransactionTraceDao;
import com.smartworks.invtmgmt.core.dao.TransactionTypeDao;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.domain.TransactionType;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;
import com.smartworks.invtmgmt.core.inventoryprocessor.InventoryChangeProcessor;
import com.smartworks.invtmgmt.core.inventoryprocessor.InventoryProcessorFactory;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;
import com.smartworks.invtmgmt.util.ItemSkuUtil;

@Transactional
public class InvtTransMgrImpl implements InvtTransManager {

	TransactionTypeDao tranTypeDao;
	InventoryDao inventoryDao;
	InventoryProcessorFactory invProcessorFactory;
	TransactionTraceDao transactionTraceDao;
	
	@Override
	public TransactionType getTransType(TransactionTypeEnum transType) {
		return tranTypeDao.load(transType);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<TransactionType> getAllTrans() {
		return tranTypeDao.loadAll();
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<TransactionType> getTransactionsForLocation(Integer locationId) {
		return tranTypeDao.getTransactionsForLocation(locationId);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public boolean receiveInventory(Integer locationId,List<ItemSku> skus) {
		boolean returnVal = false;		
		for (ItemSku itemSku : skus) {
			InventoryPk inventoryPk = new InventoryPk();
			Location loc = new Location(locationId);
			inventoryPk.setLocation(loc);
			loc.setLocationId(locationId);
			String itemSkuCode = ItemSkuUtil.getItemSkuCode(itemSku);
			inventoryPk.setSkuCode(itemSkuCode);
			inventoryDao.addAvailableInventory(inventoryPk, itemSku.getQuantity());
		}
		return returnVal;
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public boolean transferInventory(Integer sourceLoc,Integer targetLoc,List<ItemSku> skus) {		
		for (ItemSku itemSku : skus) {
			InventoryPk srcInventoryPk = new InventoryPk();
			InventoryPk targetInventoryPk = new InventoryPk();
			
			srcInventoryPk.setLocation(new Location(sourceLoc));
			srcInventoryPk.setSkuCode(ItemSkuUtil.getItemSkuCode(itemSku));
			
			targetInventoryPk.setLocation(new Location(targetLoc));
			targetInventoryPk.setSkuCode(ItemSkuUtil.getItemSkuCode(itemSku));
						
			inventoryDao.reduceAvailableInventory(srcInventoryPk, itemSku.getQuantity());			
			inventoryDao.addAvailableInventory(targetInventoryPk, itemSku.getQuantity());			
		}
		return true;		
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public boolean processInventoryChange(TransactionDetailsHolder transDetails) {
		// Find out the appropriate Processor
		InventoryChangeProcessor processor = invProcessorFactory.getInventoryProcessor(transDetails.getTransactionType());
		processor.process(transDetails);
		return true;
	}
	
	public List<TransactionDetailsHolder> loadAllOpenTrans(Integer locationId,Integer userId,TransactionTypeEnum transType) {
		List<TransactionDetailsHolder> transDetails = new ArrayList<TransactionDetailsHolder>();
		List<TransactionTrace> transTraceList =  transactionTraceDao.loadAllOpenTrans(locationId, userId, transType);
		
		for (TransactionTrace transactionTrace : transTraceList) {
			transDetails.add(TransactionTraceObjectConverter.getTransactionDetailsHolder(transactionTrace));
		}		
		return transDetails;
	}
	
	public List<UserTransactionDetails> getAllOpenTransactionForUser(Integer locationId,Integer traineeId,TransactionTypeEnum transType) {		
		List<UserTransactionDetails> userTransDetails = new ArrayList<UserTransactionDetails>();
		List<TransactionTrace> transTraceList =  transactionTraceDao.loadAllOpenTrans(locationId, traineeId, transType);
		for (TransactionTrace transactionTrace : transTraceList) {
			userTransDetails.add(TransactionTraceObjectConverter.getUserTransactions(transactionTrace));			
		}
		return userTransDetails;
	}
	
	public TransactionDetailsHolder getTransDetails(Integer transId) {
		TransactionTrace tranTrace = transactionTraceDao.load(transId);
		return TransactionTraceObjectConverter.getTransactionDetailsHolder(tranTrace);
	}	
	
	public TransactionTypeDao getTranTypeDao() {
		return tranTypeDao;
	}

	public void setTranTypeDao(TransactionTypeDao tranTypeDao) {
		this.tranTypeDao = tranTypeDao;
	}

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	public InventoryProcessorFactory getInvProcessorFactory() {
		return invProcessorFactory;
	}

	public void setInvProcessorFactory(InventoryProcessorFactory invProcessorFactory) {
		this.invProcessorFactory = invProcessorFactory;
	}

	public TransactionTraceDao getTransactionTraceDao() {
		return transactionTraceDao;
	}

	public void setTransactionTraceDao(TransactionTraceDao transactionTraceDao) {
		this.transactionTraceDao = transactionTraceDao;
	}

}
