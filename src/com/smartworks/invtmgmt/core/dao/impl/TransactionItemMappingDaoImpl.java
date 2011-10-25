package com.smartworks.invtmgmt.core.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.TransactionItemMappingDao;
import com.smartworks.invtmgmt.core.domain.TransactionItemMapping;
import com.smartworks.invtmgmt.core.domain.TransactionType;

public class TransactionItemMappingDaoImpl extends HibernateDaoSupport implements TransactionItemMappingDao {

	public List<TransactionItemMapping> loadMappingForTransaction(TransactionType transactionType) {
		String query = "from TransactionItemMapping where transItemMappingPk.transactionType=?" ;
		List<TransactionItemMapping> mappings = getHibernateTemplate().find(query, transactionType);
		return mappings;
	}
}
