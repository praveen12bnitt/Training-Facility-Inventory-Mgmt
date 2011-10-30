package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.dao.TransactionTypeDao;
import com.smartworks.invtmgmt.core.domain.Location;
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
			Location loc = new Location();
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
			InventoryPk inventoryPk = new InventoryPk();
			Location srcLoc = new Location();
			srcLoc.setLocationId(sourceLoc);
			Location tarLoc = new Location();
			tarLoc.setLocationId(targetLoc);
			String itemSkuCode = ItemSkuUtil.getItemSkuCode(itemSku);
			inventoryPk.setSkuCode(itemSkuCode);
			inventoryPk.setLocation(srcLoc);
			inventoryDao.reduceAvailableInventory(inventoryPk, itemSku.getQuantity());
			
			inventoryPk.setLocation(tarLoc);
			inventoryDao.addAvailableInventory(inventoryPk, itemSku.getQuantity());			
		}
		return true;		
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public boolean processInventoryChange(TransactionTypeEnum transType,Integer locationId,List<ItemSku> skus) {
		// Find out the appropriate Processor
		InventoryChangeProcessor processor = invProcessorFactory.getInventoryProcessor(transType);
		processor.process(locationId, skus);
		return true;
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

	

	
	

}
