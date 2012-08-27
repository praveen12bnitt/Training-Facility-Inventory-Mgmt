package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.core.dao.LaundryDao;
import com.smartworks.invtmgmt.core.domain.Laundry;

@Transactional
public class LaundryTransMgrImpl implements LaundryTransMgr {
	
	private LaundryDao laundryDao;

	@Transactional(readOnly=false,propagation=Propagation.SUPPORTS)
	public Laundry load(Integer loadId) {
		return laundryDao.load(loadId);
	}

	@Transactional(readOnly=false,propagation=Propagation.SUPPORTS)
	public List<Laundry> loadAll() {
		return laundryDao.loadAll();
	}
	
	@Transactional(readOnly=false,propagation=Propagation.SUPPORTS)
	public List<Laundry> loadAll(String laundryType, String fromDate, String toDate) {
		return laundryDao.loadAll(laundryType, fromDate, toDate);
	}
	
	public void save(Laundry laundry) {
		laundryDao.save(laundry);
	}
	
	public void update(Laundry laundry) {
		laundryDao.update(laundry);		
	}

	public LaundryDao getLaundryDao() {
		return laundryDao;
	}

	public void setLaundryDao(LaundryDao laundryDao) {
		this.laundryDao = laundryDao;
	}
	
}
