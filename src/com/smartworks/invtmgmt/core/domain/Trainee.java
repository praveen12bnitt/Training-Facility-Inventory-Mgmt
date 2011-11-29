package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

import com.smartworks.invtmgmt.core.util.DateUtil;


@Entity
@Table(name="TRAINEE")
@Proxy(lazy=false)

public class Trainee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="TRAINEE_ID")
	@GeneratedValue
	Integer traineeId;
	
	@Column(name="LAST_NAME", length=100)
	String lastName;
	@Column(name="FIRST_NAME", length=100)
	String firstName;
	@Column(name="MIDDLE_NAME", length=50)
	String middleName;
	@Column(name="CLASS_NUMBER", length=50)
	String classNumber;
	
	@Type(type="yes_no")
	@Column(name="ENABLED")
	Boolean enabled = true;
	
	@Column(name="CREATEDDTTM")
	Timestamp createdDttm;
		
	@Version
	@Column(name="LASTUPDATEDTTM")
	Timestamp lastUpdateDttm;
	
	@Transient
	String createdDttmStr;
	
	@Transient
	String lastUpdateDttmStr;
	
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
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Timestamp getCreatedDttm() {
		return createdDttm;
	}
	public Timestamp getLastUpdateDttm() {
		return lastUpdateDttm;
	}
	public void setCreatedDttm(Timestamp createdDttm) {
		this.createdDttm = createdDttm;		
	}
	public void setLastUpdateDttm(Timestamp lastUpdateDttm) {
		this.lastUpdateDttm = lastUpdateDttm;
	}
	public String getCreatedDttmStr() {
		return DateUtil.getExpandedTimeStamp(createdDttm); 
	}
	public String getLastUpdateDttmStr() {
		return DateUtil.getExpandedTimeStamp(lastUpdateDttm);
	}
	
	
}
