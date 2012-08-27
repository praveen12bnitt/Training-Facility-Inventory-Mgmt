package com.smartworks.invtmgmt.core.dao;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

public interface TransactionTraceDao {

	public void save(TransactionTrace transTrace);
	public TransactionTrace load(Integer transactionTraceId);
	public void update(TransactionTrace transTrace);
	public List<TransactionTrace> loadAllOpenTrans(Integer locationId,Integer traineeId,TransactionTypeEnum transType);
	public List<TransactionTrace> loadAllOpenTransStaff(Integer locationId,Integer staffId,TransactionTypeEnum transType);
	public void markTransactionClosed(Integer transactionId);
	public List<TransactionTrace> loadAllClosedTransactions();
	
}
