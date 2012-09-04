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
	
	
	public Class getCls() {
		return cls;
	}
	public void setCls(Class cls) {
		this.cls = cls;
	}
	

}
