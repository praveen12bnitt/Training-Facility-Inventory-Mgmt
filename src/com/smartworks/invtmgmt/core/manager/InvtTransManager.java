package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.TransactionType;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

public interface InvtTransManager {

	public TransactionType getTransType(TransactionTypeEnum transType) ;
	
	public List<TransactionType> getAllTrans();
	
	public List<TransactionType> getTransactionsForLocation(Integer locationId);
}
