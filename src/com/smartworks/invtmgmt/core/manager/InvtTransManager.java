package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.business.UserTransactionDetails;
import com.smartworks.invtmgmt.core.domain.TransactionType;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

public interface InvtTransManager {

	public TransactionType getTransType(TransactionTypeEnum transType) ;
	
	public List<TransactionType> getAllTrans();
	
	public List<TransactionType> getTransactionsForLocation(Integer locationId);
	
	public boolean transferInventory(Integer sourceLoc,Integer targetLoc,List<ItemSku> skus);
	
	public boolean processInventoryChange(TransactionDetailsHolder transDetails);
	
	public boolean receiveInventory(Integer locationId,List<ItemSku> skus) ;
	
	public List<TransactionDetailsHolder> loadAllOpenTrans(Integer locationId,Integer userId,TransactionTypeEnum transType) ;
	
	public List<UserTransactionDetails> getAllOpenTransactionForUser(Integer locationId,Integer userId,TransactionTypeEnum transType) ;
	
	public TransactionDetailsHolder getTransDetails(Integer transId);
}
