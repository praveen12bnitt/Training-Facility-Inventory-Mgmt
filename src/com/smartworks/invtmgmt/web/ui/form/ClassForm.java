package com.smartworks.invtmgmt.web.ui.form;

import org.springframework.web.multipart.MultipartFile;

import com.smartworks.invtmgmt.core.domain.Class;

public class ClassForm {
	
	private Class cls;

	private MultipartFile traineeFile;
		
	public Class getCls() {
		return cls;
	}
	public void setCls(Class cls) {
		this.cls = cls;
	}
	public MultipartFile getTraineeFile() {
		return traineeFile;
	}
	public void setTraineeFile(MultipartFile traineeFile) {
		this.traineeFile = traineeFile;
	}
	

}
