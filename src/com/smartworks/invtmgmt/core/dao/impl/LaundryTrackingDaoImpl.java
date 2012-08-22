package com.smartworks.invtmgmt.core.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.LaundryTrackingDao;
import com.smartworks.invtmgmt.core.db.util.DateUtil;
import com.smartworks.invtmgmt.core.domain.Inventory;
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
	
	public List<LaundryTracking> loadAllLoads(Date fromDate, Date toDate) {
		String query = "from LaundryTracking where createdDttm between :fromDate and :toDate";
		String[] params = {"fromDate", "toDate"};
		Object[] values = {new Timestamp(fromDate.getTime()), new Timestamp(toDate.getTime()),};
		List<LaundryTracking> laundryList = getHibernateTemplate().findByNamedParam(query,params, values);
		return laundryList;
	}
	
	public List loadAllLoadsTotal(Date fromDate, Date toDate) {
		String query = "select (sum(tseRoom)+sum(towels)+sum(gymClothings)+sum(jockSocksBras)+sum(uniforms)+sum(regLaundry)), " +
				" sum(DMD0006G),sum(FAD0006E),sum(CTD0006D),sum(ATFSABT0006H),sum(PTD0006F),sum(USBOPB0006B), sum(FPS0006C)" +
				"  from LaundryTracking where createdDttm between :fromDate and :toDate";
		String[] params = {"fromDate", "toDate"};
		Object[] values = {new Timestamp(fromDate.getTime()), new Timestamp(toDate.getTime()),};
		List laundryList = getHibernateTemplate().findByNamedParam(query,params, values);
		return laundryList;
	}


	public DateUtil getDateUtil() {
		return dateUtil;
	}

	public void setDateUtil(DateUtil dateUtil) {
		this.dateUtil = dateUtil;
	}
	
	
}
