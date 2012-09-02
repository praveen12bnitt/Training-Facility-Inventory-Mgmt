package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.converter.ItemSkuConverter;
import com.smartworks.invtmgmt.converter.TransactionTraceObjectConverter;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.dao.LocationDao;
import com.smartworks.invtmgmt.core.dao.TransactionTraceDao;

public abstract class InventoryChangeProcessor {
	
	InventoryDao inventoryDao;
	TransactionTraceDao transactionTraceDao;
	
	ItemSkuConverter itemSkuConverter;
	TransactionTraceObjectConverter transactionTraceObjectConverter;
	
	protected LocationDao locationDao;

	public abstract void process(TransactionDetailsHolder transDetails) ;

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	public TransactionTraceDao getTransactionTraceDao() {
		return transactionTraceDao;
	}

	public void setTransactionTraceDao(TransactionTraceDao transactionTraceDao) {
		this.transactionTraceDao = transactionTraceDao;
	}

	public ItemSkuConverter getItemSkuConverter() {
		return itemSkuConverter;
	}

	public TransactionTraceObjectConverter getTransactionTraceObjectConverter() {
		return transactionTraceObjectConverter;
	}

	public void setItemSkuConverter(ItemSkuConverter itemSkuConverter) {
		this.itemSkuConverter = itemSkuConverter;
	}

	public void setTransactionTraceObjectConverter(
			TransactionTraceObjectConverter transactionTraceObjectConverter) {
		this.transactionTraceObjectConverter = transactionTraceObjectConverter;
	}

	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	
}
