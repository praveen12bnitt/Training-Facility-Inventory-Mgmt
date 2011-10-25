package com.smartworks.invtmgmt.core.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.TransactionTypeDao;
import com.smartworks.invtmgmt.core.domain.TransactionType;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

public class TransactionTypeDaoImpl extends HibernateDaoSupport implements TransactionTypeDao {

	@Override
	public TransactionType load(TransactionTypeEnum transType) {
		return getHibernateTemplate().load(TransactionType.class, transType);
	}

	@Override
	public List<TransactionType> loadAll() {
		String qStr = "from TransactionType";
		List<TransactionType> transTypes = getHibernateTemplate().find(qStr);
		return transTypes;
	}
	
	public List<TransactionType> getTransactionsForLocation(Integer locationId) {
		String qStr = "from TransactionType where location.locationId="+locationId;
		List<TransactionType> transTypes = getHibernateTemplate().find(qStr);
		return transTypes;
	}

}
