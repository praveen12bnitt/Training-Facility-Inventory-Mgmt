package com.smartworks.invtmgmt.core.dao;


import java.util.List;

import com.smartworks.invtmgmt.core.domain.Location;

public interface LocationDao {
	
	public Location load(Integer location_id);	
	public List<Location> loadAll();
	public List<Location> loadSecondaryLocations();
	public Location findByLocationName(String locationName);
}
