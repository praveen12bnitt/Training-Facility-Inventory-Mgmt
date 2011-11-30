package com.smartworks.invtmgmt.web.ui.form;

import javax.validation.Valid;

import com.smartworks.invtmgmt.core.domain.Staff;

public class StaffForm {

	@Valid Staff staff;	
	boolean edit = false;

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
}
