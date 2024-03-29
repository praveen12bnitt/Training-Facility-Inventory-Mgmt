package com.smartworks.invtmgmt.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.TransactionTraceDao;
import com.smartworks.invtmgmt.core.db.util.DateUtil;
import com.smartworks.invtmgmt.core.domain.TransactionDetails;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

public class TransactionTraceDaoImpl extends HibernateDaoSupport implements TransactionTraceDao {
	
	DateUtil dateUtil;
	
	public void save(TransactionTrace transTrace) {
		transTrace.setCreatedDttm(dateUtil.getCurrentDBTimeStamp());
		getHibernateTemplate().save(transTrace);
	}
	
	public void save(TransactionDetails transactionDetails) {
		getHibernateTemplate().save(transactionDetails);
	}
	
	public TransactionTrace load(Integer transactionTraceId) {
		return getHibernateTemplate().load(TransactionTrace.class, transactionTraceId);
	}
	
	@Override
	public void update(TransactionTrace transTrace) {
		transTrace.setCreatedDttm(dateUtil.getCurrentDBTimeStamp());
		getHibernateTemplate().update(transTrace);		
	}
	
	public List<TransactionTrace> loadAllOpenTrans(Integer locationId,Integer traineeId,TransactionTypeEnum transType) {		
		String sql = "from TransactionTrace where locationId= ? and traineeId= ?  and transType= ? and closed=false";
		List<TransactionTrace> transList = new ArrayList<TransactionTrace>();
		transList = getHibernateTemplate().find(sql, locationId,traineeId,transType);
		return transList;
	}
	
	public List<TransactionTrace> loadAllOpenTransStaff(Integer locationId,Integer staffId,TransactionTypeEnum transType) {		
		String sql = "from TransactionTrace where locationId= ? and staffId= ?  and transType= ? and closed=false";
		List<TransactionTrace> transList = new ArrayList<TransactionTrace>();
		transList = getHibernateTemplate().find(sql, locationId,staffId,transType);
		return transList;
	}
	
	public List<TransactionTrace> loadAllClosedTransactions() {
		String sql = "from TransactionTrace where transType in (select transType from TransactionType where" +
				" (transType like 'ISSUE%' or transType like 'RETURN%')) ";
		List<TransactionTrace> transList = new ArrayList<TransactionTrace>();
		transList = getHibernateTemplate().find(sql);
		for(TransactionTrace transactionTrace: transList) {
			List transDetails = transactionTrace.getTransDetails();
			transDetails.size();
		}
		return transList;
	}
	
	@Override
	public void markTransactionClosed(Integer transactionId) {
		String updateSql = "update TransactionTrace set closed = true where trasactionId="+transactionId;	
		getHibernateTemplate().bulkUpdate(updateSql);		
	}

	public DateUtil getDateUtil() {
		return dateUtil;
	}

	public void setDateUtil(DateUtil dateUtil) {
		this.dateUtil = dateUtil;
	}
}
