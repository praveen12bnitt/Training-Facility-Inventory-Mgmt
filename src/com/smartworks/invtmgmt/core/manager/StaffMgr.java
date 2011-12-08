package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.Staff;

public interface StaffMgr {

	public void add(Staff t);
	public void update(Staff t);
	public Staff load(Integer staffId);
	public List<Staff> loadAll();
	public List<Staff> loadActiveStaff();	
	public List<Staff> getStaff(Integer firstResult,Integer maxResults,String orderByField,String orderByType,String whereClause) ;
	public Long getStaffTotalCount(String orderByField,String orderByType,String whereClause);
}
