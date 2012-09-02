package com.smartworks.invtmgmt.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.TraineeDao;
import com.smartworks.invtmgmt.core.db.util.DateUtil;
import com.smartworks.invtmgmt.core.domain.Product;
import com.smartworks.invtmgmt.core.domain.Trainee;

public class TraineeDaoImpl extends HibernateDaoSupport
 implements TraineeDao {
	
	DateUtil dateUtil;
	
	public Trainee load(Integer id) {
		return getHibernateTemplate().load(Trainee.class, id);
	}
	
	public void save(Trainee t) {
		t.setCreatedDttm(dateUtil.getCurrentDBTimeStamp());
		getHibernateTemplate().save(t);
	}
	
	@Override
	public void update(Trainee t) {
		getHibernateTemplate().update(t);		
	}

	@Override
	public List<Trainee> loadAll() {
		String query = "from Trainee";		
		List<Trainee> trainees = getHibernateTemplate().find(query);
		return trainees;
	}
	
	public List<Trainee> loadActiveTrainee() {
		String query = "from Trainee where enabled=true";		
		List<Trainee> trainees = getHibernateTemplate().find(query);
		return trainees;
	}
	
	
	
	public List<Trainee> getTrainee(Integer firstResult,Integer maxResults,String orderByField,String orderByType,String whereClause) {
		List<Trainee> trainees = new ArrayList<Trainee>();
		StringBuilder query = new StringBuilder();
		query.append("from Trainee where 1=1 ").append(whereClause);
		query.append(" order by ").append(orderByField).append(" ").append(orderByType);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		trainees = session.createQuery(query.toString()).setFirstResult(firstResult).setMaxResults(maxResults).list();
		return trainees;
	}
	
	public Long getTraineeTotalCount(String orderByField,String orderByType,String whereClause) {
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

	@Override
	public Trainee findByName(String firstName) {
		String query = "from Trainee where firstName=:firstName";
		List<Trainee> trainees = getHibernateTemplate().findByNamedParam(query, "firstName", firstName);
		if(trainees.size()==0) {
			return null;
		} else {
			return trainees.get(0);
		}
	}

	@Override
	public List<Trainee> findByClass(String clsName) {
		String query = "from Trainee where firstName=:firstName";
		List<Trainee> traineeList = getHibernateTemplate().findByNamedParam(query, "firstName", clsName);
		return traineeList;
	}

	@Override
	public List<Trainee> getByName(String name) {
		String query = "from Trainee where firstName like :firstName";
		List<Trainee> trainees = getHibernateTemplate().findByNamedParam(query, "firstName", "%"+name+"%");
		return trainees;
	}
	
}
