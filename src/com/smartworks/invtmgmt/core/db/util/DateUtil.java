package com.smartworks.invtmgmt.core.db.util;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DateUtil {

	SessionFactory sessionFactory;
	
	public Timestamp getCurrentDBTimeStamp() {
		Session s = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = s.createSQLQuery("select CURRENT_TIMESTAMP");
		List<Timestamp> l = sqlQuery.list();
		return l.get(0);		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
