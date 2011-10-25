package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.core.dao.TransactionTypeDao;
import com.smartworks.invtmgmt.core.domain.TransactionType;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

public class InvtTransMgrImpl implements InvtTransManager {

	TransactionTypeDao tranTypeDao;
	
	@Override
	public TransactionType getTransType(TransactionTypeEnum transType) {
		return tranTypeDao.load(transType);
	}
	
	public List<TransactionType> getAllTrans() {
		return tranTypeDao.loadAll();
	}

	@Override
	public List<TransactionType> getTransactionsForLocation(Integer locationId) {
		return tranTypeDao.getTransactionsForLocation(locationId);
	}
	
	public TransactionTypeDao getTranTypeDao() {
		return tranTypeDao;
	}

	public void setTranTypeDao(TransactionTypeDao tranTypeDao) {
		this.tranTypeDao = tranTypeDao;
	}

	
	

}
