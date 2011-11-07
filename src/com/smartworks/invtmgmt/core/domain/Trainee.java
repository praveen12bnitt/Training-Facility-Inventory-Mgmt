package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;


@Entity
@Table(name="trainee")
@Proxy(lazy=false)

public class Trainee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="trainee_id")
	Integer traineeId;
	
	@Column(name="last_name", length=100)
	String lastName;
	@Column(name="first_name", length=100)
	String firstName;
	@Column(name="middle_name", length=50)
	String middleName;
	@Column(name="class_number", length=50)
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
