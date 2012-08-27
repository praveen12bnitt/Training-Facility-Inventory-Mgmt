package com.smartworks.invtmgmt.core.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.LaundryDao;
import com.smartworks.invtmgmt.core.domain.Laundry;
import com.smartworks.invtmgmt.core.service.DateUtil;



public class LaundryDaoImpl  extends HibernateDaoSupport implements LaundryDao {

	
	public Laundry load(Integer loadId) {
		return getHibernateTemplate().load(Laundry.class, loadId);
	}

	
	public List<Laundry> loadAll() {
		List<Laundry> laundryList = new ArrayList<Laundry>();
		laundryList = getHibernateTemplate().loadAll(Laundry.class);
		return laundryList;
	}
	
	public List<Laundry> loadAll(String laundryType, String fromDate, String toDate)  {
		
		String query = "from Laundry where laundryType=:laundryType and " +
				"createdDttm between :fromDate and :toDate";
		String[] params = {"laundryType","fromDate", "toDate"};
		Date fromTimeDtamp = DateUtil.getDate(fromDate);
		Date toTimeDtamp = DateUtil.getDate(toDate);
		Object[] values = {laundryType, new Timestamp(fromTimeDtamp.getTime()), new Timestamp(toTimeDtamp.getTime())};
		
		List<Laundry> laundryList = new ArrayList<Laundry>();
		laundryList = getHibernateTemplate().findByNamedParam(query,params, values);
		return laundryList;
	}

	
	public void save(Laundry laundry) {
		 getHibernateTemplate().save(laundry);	
	}

	public void update(Laundry laundry) {
		getHibernateTemplate().saveOrUpdate(laundry);		
	}

}
