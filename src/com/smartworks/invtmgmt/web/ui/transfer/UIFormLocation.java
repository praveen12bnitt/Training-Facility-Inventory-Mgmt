package com.smartworks.invtmgmt.web.ui.transfer;

import java.io.Serializable;
import java.util.List;

public class UIFormLocation  implements Serializable {
	
	private Integer location_id;
	private String locationName;
	private String selectedValue;
	private String error;
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	List <UIFormLocation> locationList;
	
	public Integer getLocation_id() {
		return location_id;
	}

	public void setLocationId(Integer locationId) {
		this.location_id = locationId;
	}

	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locName) {
		this.locationName = locName;
	}
	

	public void setListUIFormLocations(List<UIFormLocation> listUIFormlocs) {
		this.locationList = listUIFormlocs;
	}
	public List<UIFormLocation> getListUIFormLocations() {
		return locationList;
	}
	
}

