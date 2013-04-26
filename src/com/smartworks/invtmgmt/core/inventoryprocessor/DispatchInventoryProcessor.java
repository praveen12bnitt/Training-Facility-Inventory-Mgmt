package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;
import com.smartworks.invtmgmt.core.exception.NoItemsForTransactionException;
import com.smartworks.invtmgmt.core.service.PreissueService;

public class DispatchInventoryProcessor extends InventoryChangeProcessor {
	
	private PreissueService preissueService;
	
	public void process(TransactionDetailsHolder transDetails) {
		// First reduce the inventory 				
		boolean hasInventory = false;
		for (ItemSku itemSku : transDetails.getItemSkus()) {
			
			if(itemSku.getQuantity() == null || itemSku.getQuantity() < 1) {
				continue;
			}
						
			hasInventory = true;			
			InventoryPk inventoryPk = new InventoryPk();
			Location loc = locationDao.load(transDetails.getLocationId());
			inventoryPk.setLocation(loc);
			String itemSkuCode = itemSkuConverter.getItemSkuCode(itemSku);
			inventoryPk.setSkuCode(itemSkuCode);
			inventoryDao.reduceAvailableInventory(inventoryPk, itemSku.getQuantity());
			inventoryDao.addIssuedInventory(inventoryPk, itemSku.getQuantity());
			
		}
		
		if(!hasInventory) {
			throw new NoItemsForTransactionException("No items to issue");
		}
		
		if(transDetails.getRefTransactionId() != null)
		{
			saveTransactionTracePreIssue(transDetails);
		}
		else
		{
			saveTransactionTraceDirectIssue(transDetails);
		}
		
	}
	
	protected void saveTransactionTracePreIssue(TransactionDetailsHolder transDetails) 
	{
		TransactionTrace transactionTrace = preissueService.alterTransactionTrace(transDetails.getItemSkus(), transDetails.getRefTransactionId());
		transactionTrace.setTransType(transDetails.getTransactionType());
		transactionTraceDao.update(transactionTrace);		
	}
	
	protected void saveTransactionTraceDirectIssue(TransactionDetailsHolder transDetails) 
	{
		TransactionTrace transTrace = transactionTraceObjectConverter.getTransactionTrace(transDetails);
		transactionTraceDao.save(transTrace);		
	}

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	public PreissueService getPreissueService() {
		return preissueService;
	}

	public void setPreissueService(PreissueService preissueService) {
		this.preissueService = preissueService;
	}
}
