package com.smartworks.invtmgmt.web.ui.form;

import javax.validation.Valid;

import com.smartworks.invtmgmt.core.domain.LaundryTracking;

public class LaundryTrackingForm {
	
	@Valid LaundryTracking laundryTracking;

	public LaundryTracking getLaundryTracking() {
		return laundryTracking;
	}

	public void setLaundryTracking(LaundryTracking laundryTracking) {
		this.laundryTracking = laundryTracking;
	}

}
