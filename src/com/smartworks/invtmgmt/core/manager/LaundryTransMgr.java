package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.Laundry;

public interface LaundryTransMgr {
	
	public Laundry load(Integer loadId);
	public List<Laundry> loadAll();
	public void save(Laundry laundry);
	public void update(Laundry laundry);
	public List<Laundry> loadAll(String laundryType, String fromDate, String toDate);
	public List<Object[]> summaryAll(String laundryType, String fromDate, String toDate);
}

