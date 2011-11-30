package com.smartworks.invtmgmt.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.StaffDao;
import com.smartworks.invtmgmt.core.db.util.DateUtil;
import com.smartworks.invtmgmt.core.domain.Staff;
import com.smartworks.invtmgmt.core.domain.Trainee;

public class StaffDaoImpl extends HibernateDaoSupport
 implements StaffDao {
	
	DateUtil dateUtil;
	
	public Staff load(Integer id) {
		return getHibernateTemplate().load(Staff.class, id);
	}
	
	public void save(Staff t) {
		t.setCreatedDttm(dateUtil.getCurrentDBTimeStamp());
		getHibernateTemplate().save(t);
	}
	
	@Override
	public void update(Staff t) {
		getHibernateTemplate().update(t);		
	}

	@Override
	public List<Staff> loadAll() {
		String query = "from Staff where enabled=true";		
		List<Staff> staff = getHibernateTemplate().find(query);
		return staff;
	}
	
	@Override
	public List<Staff> loadActiveStaff() {
		String query = "from Staff where enabled=true";		
		List<Staff> staff = getHibernateTemplate().find(query);
		return staff;
	}
	
	
	
	public List<Staff> getStaff(Integer firstResult,Integer maxResults,String orderByField,String orderByType,String whereClause) {
		List<Staff> staff = new ArrayList<Staff>();
		StringBuilder query = new StringBuilder();
		query.append("from Staff where 1=1 ").append(whereClause);
		query.append(" order by ").append(orderByField).append(" ").append(orderByType);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		staff = session.createQuery(query.toString()).setFirstResult(firstResult).setMaxResults(maxResults).list();
		return staff;
	}
	
	public Long getStaffTotalCount(String orderByField,String orderByType,String whereClause) {
		StringBuilder query = new StringBuilder();
		query.append("select count(*) from Trainee where 1=1 ").append(whereClause);
		query.append(" order by ").append(orderByField).append(" ").append(orderByType);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long count = (Long) session.createQuery(query.toString()).uniqueResult();
		return count;
	}

	public DateUtil getDateUtil() {
		return dateUtil;
	}

	public void setDateUtil(DateUtil dateUtil) {
		this.dateUtil = dateUtil;
	}
	
}
