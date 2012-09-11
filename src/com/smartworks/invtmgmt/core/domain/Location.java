package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="LOCATION")
@Proxy(lazy=false)
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="LOCATION_ID")
	Integer locationId;
	
	@Column(name="LOCATION_NAME")
	String locationName;
	
	public Location() {		
	}

	public Location(Integer locationId) {
		super();
		this.locationId = locationId;
	}
	public Location(String name) {
		super();
		this.locationName = name;
	}
	public Integer getLocationId() {
		return locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
		result = prime * result + ((locationName == null) ? 0 : locationName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (locationId == null) {
			if (other.locationId != null)
				return false;
		} else if (!locationId.equals(other.locationId))
			return false;
		if (locationName == null) {
			if (other.locationName != null)
				return false;
		} else if (!locationName.equals(other.locationName))
			return false;
		return true;
	}	
	
	
	
}
