package com.smartworks.invtmgmt.core.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.domain.TransactionDetails;

public class TransctionDetailsDaoImpl  extends HibernateDaoSupport  {

	protected static Logger logger = Logger.getLogger(TransctionDetailsDaoImpl.class);
	
	public TransactionDetails getTransDetails(Integer transTraceId, String skuCode) {
		String query = "from TransactionDetails where trasactionTrace.id=:transTraceId and skuCode = :skuCode" ;
		String[] params = {"transTraceId", "skuCode"};
		Object[] val = {transTraceId, skuCode};
		List result = getHibernateTemplate().findByNamedParam(query,params, val);
		
		if(result != null && result.size() > 0) {
			return (TransactionDetails) result.get(0);
		} else {
			logger.error("Query "+query+" either returned 0 entries or more than 1 entry");
			throw new RuntimeException("Query "+query+" either returned 0 entries or more than 1 entry");
		}	
		
	}
	
	public void save(TransactionDetails transactionDetails) {
		getHibernateTemplate().save(transactionDetails);
	}
	
	
}
