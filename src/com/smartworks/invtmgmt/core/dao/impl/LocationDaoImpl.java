package com.smartworks.invtmgmt.core.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.LocationDao;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.TransactionItemMapping;
import com.smartworks.invtmgmt.core.domain.TransactionType;

public class LocationDaoImpl extends HibernateDaoSupport implements LocationDao {

	public Location load(Integer location_id) {
		return getHibernateTemplate().load(Location.class, location_id);
	}

	@Override
	public List<Location> loadAll() {
		String query = "from Location";		
		List<Location> locations = getHibernateTemplate().find(query);
		return locations;
	}
	//SELECT * FROM `invtmgmt`.`location` where location_name NOT LIKE 'Main Warehouse' ;
	
	public List<Location> loadSecondaryLocations() {	
		String query = "from Location where (LOCATION_NAME NOT LIKE 'Main Warehouse' AND LOCATION_NAME NOT LIKE 'Laundry' AND LOCATION_NAME NOT LIKE 'External Location') order by LOCATION_NAME" ;
		List<Location> locations = getHibernateTemplate().find(query);
		return locations;
	}

	@Override
	public Location findByLocationName(String locationName) {
		String query = "from Location where location_name=:locationName";
		List<Location> locations = getHibernateTemplate().findByNamedParam(query, "locationName", locationName);
		if(locations.size()==0) {
			return null;
		} else {
			return locations.get(0);
		}
		
	}
}
