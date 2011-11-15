package com.smartworks.invtmgmt.core.manager;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.core.dao.LaundryTrackingDao;
import com.smartworks.invtmgmt.core.domain.LaundryTracking;

@Transactional
public class LaundryMgrImpl implements LaundryMgr {

	LaundryTrackingDao laundryTrackingDao;
	
	@Transactional(readOnly=false,propagation=Propagation.SUPPORTS)
	public void createLaundryLoad(LaundryTracking laundryTracking) {
		laundryTrackingDao.save(laundryTracking);
	}

	public LaundryTrackingDao getLaundryTrackingDao() {
		return laundryTrackingDao;
	}

	public void setLaundryTrackingDao(LaundryTrackingDao laundryTrackingDao) {
		this.laundryTrackingDao = laundryTrackingDao;
	}
}
