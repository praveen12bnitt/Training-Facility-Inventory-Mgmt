package com.smartworks.invtmgmt.core.dao;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.Staff;

public interface StaffDao {
	
	public void save(Staff t);
	public Staff load(Integer id);	
	public void update(Staff t);
	public List<Staff> loadAll();
	public List<Staff> loadActiveStaff();
	public List<Staff> getStaff(Integer firstResult,Integer maxResults,String orderByField,String orderByType,String whereClause);
	public Long getStaffTotalCount(String orderByField,String orderByType,String whereClause);
}
