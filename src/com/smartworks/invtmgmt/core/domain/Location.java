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
	
}
