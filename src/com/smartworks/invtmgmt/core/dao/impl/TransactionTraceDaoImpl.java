package com.smartworks.invtmgmt.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.TransactionTraceDao;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

public class TransactionTraceDaoImpl extends HibernateDaoSupport implements TransactionTraceDao {
	
	public void save(TransactionTrace transTrace) {
		getHibernateTemplate().save(transTrace);
	}
	
	public TransactionTrace load(Integer transactionTraceId) {
		return getHibernateTemplate().load(TransactionTrace.class, transactionTraceId);
	}
	
	public void update(TransactionTrace transTrace) {
		getHibernateTemplate().update(transTrace);		
	}
	
	public List<TransactionTrace> loadAllOpenTrans(Integer locationId,Integer traineeId,TransactionTypeEnum transType) {		
		String sql = "from TransactionTrace where locationId= ? and traineeId= ?  and transType= ? and closed=false";
		List<TransactionTrace> transList = new ArrayList<TransactionTrace>();
		transList = getHibernateTemplate().find(sql, locationId,traineeId,transType);
		return transList;
	}

	@Override
	public void markTransactionClosed(Integer transactionId) {
		String updateSql = "update TransactionTrace set closed = true where trasactionId="+transactionId;	
		getHibernateTemplate().bulkUpdate(updateSql);		
	}
}
