package com.smartworks.invtmgmt.web.ui.form;

import javax.validation.Valid;

import com.smartworks.invtmgmt.core.domain.Trainee;

public class TraineeForm {

	@Valid Trainee trainee;	
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
	
	
}
