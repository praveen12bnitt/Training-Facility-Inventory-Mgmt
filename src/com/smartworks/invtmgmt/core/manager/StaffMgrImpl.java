package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.core.dao.StaffDao;
import com.smartworks.invtmgmt.core.domain.Staff;

@Transactional
public class StaffMgrImpl implements StaffMgr {

	StaffDao staffDao;
	
	@Override
	public void add(Staff staff) {
		staffDao.save(staff);		
	}
	
	@Override
	public void update(Staff staff) {
		staffDao.update(staff);		
	}
	
	@Override
	public Staff load(Integer staffId) {
		return staffDao.load(staffId);
	}
	
	@Override
	public List<Staff> loadAll() {
		return staffDao.loadAll();
	}
	
	@Override
	public List<Staff> loadActiveStaff() {
		return staffDao.loadActiveStaff();
	}
	
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<Staff> getStaff(Integer firstResult,Integer maxResults,String orderByField,String orderByType,String whereClause) {
		return staffDao.getStaff(firstResult, maxResults, orderByField, orderByType, whereClause);
	}
	
	
	public Long getStaffTotalCount(String orderByField,String orderByType,String whereClause) {
		return staffDao.getStaffTotalCount(orderByField,orderByType,whereClause);
	}

	public StaffDao getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}

	


	
	
	
}
