package com.smartworks.invtmgmt.web.ui.form;

import java.util.List;

import javax.validation.Valid;

import com.smartworks.invtmgmt.core.domain.Class;
import com.smartworks.invtmgmt.core.domain.Trainee;

public class ClassForm {
	
	@Valid Class cls;
	@Valid List<Trainee> trainee;
	
	public List<Trainee> getTrainee() {
		return trainee;
	}
	public void setTrainee(List<Trainee> traineeLsit) {
		this.trainee = traineeLsit;
	}
	boolean edit = false;
	
	public Class getCls() {
		return cls;
	}
	public void setCls(Class cls) {
		this.cls = cls;
	}
	public boolean isEdit() {
		return edit;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	

}
