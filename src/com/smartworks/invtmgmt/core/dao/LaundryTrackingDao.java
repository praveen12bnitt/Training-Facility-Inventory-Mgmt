package com.smartworks.invtmgmt.core.dao;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.LaundryTracking;

public interface LaundryTrackingDao {
	public List<LaundryTracking> loadAll();
	public List<LaundryTracking> loadOpenLaundryLoads();
	public void save(LaundryTracking laundryTracking);
}
