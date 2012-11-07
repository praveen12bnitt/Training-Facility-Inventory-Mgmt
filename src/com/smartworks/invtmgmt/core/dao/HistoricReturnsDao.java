package com.smartworks.invtmgmt.core.dao;

import java.util.List;

import org.hibernate.StatelessSession;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.domain.HistoricReturns;

public class HistoricReturnsDao extends HibernateDaoSupport {

	public void insertHistoricReturns(List<HistoricReturns> historicReturns) {
		StatelessSession session = getSessionFactory().openStatelessSession();
		for(HistoricReturns historicReturn:  historicReturns) {
			session.insert(historicReturn);
		}		
	}
	
	public List<HistoricReturns> getAllReturns() {
		return getHibernateTemplate().loadAll(HistoricReturns.class);
	}
	
}
