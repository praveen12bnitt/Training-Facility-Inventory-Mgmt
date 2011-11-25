package com.smartworks.invtmgmt.core.dao;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.LaundryTracking;

public interface LaundryTrackingDao {
	
	public LaundryTracking load(Integer loadId);
	public List<LaundryTracking> loadAll();
	public List<LaundryTracking> loadOpenLaundryLoads();
	public void save(LaundryTracking laundryTracking);
	public void closeLoad(LaundryTracking laundryTracking);
	public void update(LaundryTracking laundryTracking);
}
