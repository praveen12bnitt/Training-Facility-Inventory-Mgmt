package com.smartworks.invtmgmt.core.dao;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.TransactionType;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

public interface TransactionTypeDao {

	public TransactionType load(TransactionTypeEnum transType) ;
	public List<TransactionType> loadAll() ;
	public List<TransactionType> getTransactionsForLocation(Integer locationId);
}
