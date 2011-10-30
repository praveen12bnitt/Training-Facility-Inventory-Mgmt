package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.core.domain.TransactionType;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

public interface InvtTransManager {

	public TransactionType getTransType(TransactionTypeEnum transType) ;
	
	public List<TransactionType> getAllTrans();
	
	public List<TransactionType> getTransactionsForLocation(Integer locationId);
	
	public boolean transferInventory(Integer sourceLoc,Integer targetLoc,List<ItemSku> skus);
	
	public boolean processInventoryChange(TransactionTypeEnum transType,Integer locationId,List<ItemSku> skus);
	
	public boolean receiveInventory(Integer locationId,List<ItemSku> skus) ;
}
