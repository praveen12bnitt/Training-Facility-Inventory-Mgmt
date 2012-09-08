package com.smartworks.invtmgmt.web.ui.form;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import com.smartworks.invtmgmt.core.domain.Trainee;

public class TraineeForm {

	@Valid Trainee trainee;	
	
	private Map<String,String> availableClasses = new LinkedHashMap<String,String>();
	
	private String selectedClassName ;
	
	boolean edit = false;

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public Map<String, String> getAvailableClasses() {
		return availableClasses;
	}

	public void setAvailableClasses(Map<String, String> availableClasses) {
		this.availableClasses = availableClasses;
	}

	public String getSelectedClassName() {
		return selectedClassName;
	}

	public void setSelectedClassName(String selectedClassName) {
		this.selectedClassName = selectedClassName;
	}
	
	
}
