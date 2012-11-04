package com.smartworks.invtmgmt.core.dao;

import java.util.List;

import org.hibernate.StatelessSession;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.domain.HistoricIssues;

public class HistoricIssuesDao extends HibernateDaoSupport {

	public void insertHistoricIssues(List<HistoricIssues> historicIssues) {
		StatelessSession session = getSessionFactory().openStatelessSession();
		for(HistoricIssues historicIssue:  historicIssues) {
			session.insert(historicIssue);
		}		
	}
	
	public List<HistoricIssues> getAllIssues() {
		return getHibernateTemplate().loadAll(HistoricIssues.class);
	}
	
}
