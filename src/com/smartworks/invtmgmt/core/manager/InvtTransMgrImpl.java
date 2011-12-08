package com.smartworks.invtmgmt.core.manager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.business.UserTransactionDetails;
import com.smartworks.invtmgmt.converter.ItemSkuConverter;
import com.smartworks.invtmgmt.converter.TransactionTraceObjectConverter;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.dao.TransactionTraceDao;
import com.smartworks.invtmgmt.core.dao.TransactionTypeDao;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.domain.TransactionType;
import com.smartworks.invtmgmt.core.inventoryprocessor.InventoryChangeProcessor;
import com.smartworks.invtmgmt.core.inventoryprocessor.InventoryProcessorFactory;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

@Transactional
public class InvtTransMgrImpl implements InvtTransManager {

	TransactionTypeDao tranTypeDao;
	InventoryDao inventoryDao;
	InventoryProcessorFactory invProcessorFactory;
	TransactionTraceDao transactionTraceDao;
	
	TransactionTraceObjectConverter transactionTraceObjectConverter;
	ItemSkuConverter itemSkuConverter;
	
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
	
	/**
	 * @deprecated Use {@link #processInventoryChange(TransactionDetailsHolder)} method directly by setting {@link TransactionTypeEnum} to TRANSFER_INVENTORY and setting the 
	 * srcLocation to -1
	 */
	@Deprecated
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public boolean receiveInventory(Integer locationId,List<ItemSku> skus) {
		
		//TODO : Create dummy TransactionDetailsHolder pojo and delegate the call to processInventoryChange() method. When time permits, remove this method and 
		// delege the call directly to processInventoryChange method		
		TransactionDetailsHolder transDetailsHolder = new TransactionDetailsHolder();
		Date date= new Date();
		System.out.println(new Timestamp(date.getTime()));
		transDetailsHolder.setDttm(new Timestamp(date.getTime()));
		transDetailsHolder.setItemSkus(skus);
		transDetailsHolder.setLocationId(locationId);
		transDetailsHolder.setSrcLocationId(-1);
		transDetailsHolder.setTransactionType(TransactionTypeEnum.TRANSFER_INVENTORY);		
		return processInventoryChange(transDetailsHolder);		
		
	}
	
	/**
	 * @deprecated Use {@link #processInventoryChange(TransactionDetailsHolder)} method directly by setting {@link TransactionTypeEnum} to TRANSFER_INVENTORY and setting the 
	 * locationId and srcLocation.
	 */
	@Deprecated
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public boolean transferInventory(Integer sourceLoc,Integer targetLoc,List<ItemSku> skus) {		
		
		TransactionDetailsHolder transDetailsHolder = new TransactionDetailsHolder();
		Date date= new Date();
		System.out.println(new Timestamp(date.getTime()));
		transDetailsHolder.setDttm(new Timestamp(date.getTime()));
		transDetailsHolder.setItemSkus(skus);
		transDetailsHolder.setLocationId(targetLoc);
		transDetailsHolder.setSrcLocationId(sourceLoc);
		transDetailsHolder.setTransactionType(TransactionTypeEnum.TRANSFER_INVENTORY);		
		return processInventoryChange(transDetailsHolder);	
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
			transDetails.add(transactionTraceObjectConverter.getTransactionDetailsHolder(transactionTrace));
		}		
		return transDetails;
	}
	
	public List<UserTransactionDetails> getAllOpenTransactionForUser(Integer locationId,Integer traineeId,TransactionTypeEnum transType) {		
		List<UserTransactionDetails> userTransDetails = new ArrayList<UserTransactionDetails>();
		List<TransactionTrace> transTraceList =  transactionTraceDao.loadAllOpenTrans(locationId, traineeId, transType);
		for (TransactionTrace transactionTrace : transTraceList) {
			userTransDetails.add(transactionTraceObjectConverter.getUserTransactions(transactionTrace));			
		}
		return userTransDetails;
	}
	
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<TransactionTrace> getOpenTransactionsForUser(Integer locationId,Integer traineeId,TransactionTypeEnum transType) {		
		List<TransactionTrace> transTraceList =  transactionTraceDao.loadAllOpenTrans(locationId, traineeId, transType);
		return transTraceList;
	}
	
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<TransactionTrace> getOpenTransactionsForStaff(Integer locationId,Integer staffId,TransactionTypeEnum transType) {		
		List<TransactionTrace> transTraceList =  transactionTraceDao.loadAllOpenTransStaff(locationId, staffId, transType);
		return transTraceList;
	}
	
	public TransactionDetailsHolder getTransDetails(Integer transId) {
		TransactionTrace tranTrace = transactionTraceDao.load(transId);
		return transactionTraceObjectConverter.getTransactionDetailsHolder(tranTrace);
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

	public TransactionTraceObjectConverter getTransactionTraceObjectConverter() {
		return transactionTraceObjectConverter;
	}

	public ItemSkuConverter getItemSkuConverter() {
		return itemSkuConverter;
	}

	public void setTransactionTraceObjectConverter(
			TransactionTraceObjectConverter transactionTraceObjectConverter) {
		this.transactionTraceObjectConverter = transactionTraceObjectConverter;
	}

	public void setItemSkuConverter(ItemSkuConverter itemSkuConverter) {
		this.itemSkuConverter = itemSkuConverter;
	}

}
