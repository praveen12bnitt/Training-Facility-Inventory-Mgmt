package com.smartworks.invtmgmt.web.ui.transfer;

import org.springframework.beans.factory.annotation.Autowired;

public class UIFormTransactionReceiver {
	private String department;
	private String firstName;
	private String lastName;
	private String middleName;
	
	@Autowired
	public UIFormTransactionReceiver() {
		
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	
}
