package com.smartworks.invtmgmt.core.manager;

import java.util.List;

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
	
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<LaundryTracking> loadAllOpenLoads() {
		return laundryTrackingDao.loadOpenLaundryLoads();
	}
	
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public LaundryTracking load(Integer loadId) {
		return laundryTrackingDao.load(loadId);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.SUPPORTS)
	public void save(LaundryTracking laundryTracking) {
		this.laundryTrackingDao.update(laundryTracking);		
	}
	
	@Transactional(readOnly=false,propagation=Propagation.SUPPORTS)
	public void closeLoad(LaundryTracking laundryTracking) {
		this.laundryTrackingDao.closeLoad(laundryTracking);
	}

	public LaundryTrackingDao getLaundryTrackingDao() {
		return laundryTrackingDao;
	}

	public void setLaundryTrackingDao(LaundryTrackingDao laundryTrackingDao) {
		this.laundryTrackingDao = laundryTrackingDao;
	}

	
}
