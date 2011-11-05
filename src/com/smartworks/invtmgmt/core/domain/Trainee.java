package com.smartworks.invtmgmt.core.domain;

public class Trainee {

	Integer traineeId;
	String lastName;
	String firstName;
	String middleName;
	String classNumber;
	
	public Integer getTraineeId() {
		return traineeId;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public String getClassNumber() {
		return classNumber;
	}
	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}
	
	
}
