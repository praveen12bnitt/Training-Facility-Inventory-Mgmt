package com.smartworks.invtmgmt.web.ui.form;

import java.util.List;

import com.smartworks.invtmgmt.web.ui.transfer.UIFormItem;
import com.smartworks.invtmgmt.web.ui.transfer.UIFormLocation;

public class LocationForm {
		private List<UIFormLocation> listUIFormlocations;
		
		//private UIFormTransactionReceiver uiTransactionReceiver;
		
		private Integer locationId;
		private String locationName;
		
		public Integer getLocationId() {
			return locationId;
		}

		public void setLocationId(Integer locId) {
			this.locationId = locId;
		}
		public String getLocationName() {
			return locationName;
		}

		public void setLocationId(String locName) {
			this.locationName = locName;
		}

		public List<UIFormLocation> getListUIFormLocations() {
			return listUIFormlocations;
		}

		public void setListUIFormLocations(List<UIFormLocation> listUIFormlocns) {
			this.listUIFormlocations = listUIFormlocns;
		}
		
		public List<UIFormLocation> getListUIFormLocations(List<UIFormLocation> listUIFormlocns) {
			return listUIFormlocations;
		}

	
		
	}
