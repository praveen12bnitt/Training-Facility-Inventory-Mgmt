package com.smartworks.invtmgmt.core.dao;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.TransactionItemMapping;
import com.smartworks.invtmgmt.core.domain.TransactionType;

public interface TransactionItemMappingDao {

	public List<TransactionItemMapping> loadMappingForTransaction(TransactionType transactionType);
	
}
