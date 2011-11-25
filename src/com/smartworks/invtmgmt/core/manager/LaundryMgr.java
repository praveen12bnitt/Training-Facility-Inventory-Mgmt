package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.LaundryTracking;

public interface LaundryMgr {

	public LaundryTracking load(Integer loadId);
	public void createLaundryLoad(LaundryTracking laundryTracking);
	public void save(LaundryTracking laundryTracking);
	public List<LaundryTracking> loadAllOpenLoads();
	public void closeLoad(LaundryTracking laundryTracking) ;
	
}