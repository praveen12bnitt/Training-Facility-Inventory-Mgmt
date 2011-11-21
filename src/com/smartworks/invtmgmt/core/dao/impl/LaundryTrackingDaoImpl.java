package com.smartworks.invtmgmt.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.LaundryTrackingDao;
import com.smartworks.invtmgmt.core.db.util.DateUtil;
import com.smartworks.invtmgmt.core.domain.LaundryTracking;

public class LaundryTrackingDaoImpl extends HibernateDaoSupport implements LaundryTrackingDao  {

	DateUtil dateUtil;
	
	public LaundryTracking load(Integer loadId) {
		return getHibernateTemplate().load(LaundryTracking.class, loadId);
	}
	
	public List<LaundryTracking> loadAll() {
		List<LaundryTracking> laundryTrackingList = new ArrayList<LaundryTracking>();
		laundryTrackingList = getHibernateTemplate().loadAll(LaundryTracking.class);
		return laundryTrackingList;
	}
	
	public List<LaundryTracking> loadOpenLaundryLoads() {
		List<LaundryTracking> laundryTrackingList = new ArrayList<LaundryTracking>();
		String query = "from LaundryTracking where isOpen=true";
		laundryTrackingList = getHibernateTemplate().find(query);
		return laundryTrackingList;
	}
	
	public void save(LaundryTracking laundryTracking) {
		laundryTracking.setCreatedDttm(dateUtil.getCurrentDBTimeStamp());
		getHibernateTemplate().saveOrUpdate(laundryTracking);
	}
	
	public void update(LaundryTracking laundryTracking) {
		getHibernateTemplate().update(laundryTracking);
	}
	
	public void closeLoad(LaundryTracking laundryTracking) {
		laundryTracking.setClosedDttm(dateUtil.getCurrentDBTimeStamp());
		laundryTracking.setIsOpen(false);
		getHibernateTemplate().update(laundryTracking);
	}

	public DateUtil getDateUtil() {
		return dateUtil;
	}

	public void setDateUtil(DateUtil dateUtil) {
		this.dateUtil = dateUtil;
	}
	
	
}
