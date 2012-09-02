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

import com.smartworks.invtmgmt.core.service.DateUtil;


@Entity
@Table(name="STAFF")
@Proxy(lazy=false)

public class Staff implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="STAFF_ID")
	@GeneratedValue
	Integer staffId;
	
	@Column(name="LAST_NAME", length=100)
	String lastName;
	@Column(name="FIRST_NAME", length=100)
	String firstName;
	@Column(name="MIDDLE_NAME", length=50)
	String middleName;
	@Column(name="DIVISION", length=50)
	String division;	
	@Column(name="OFFICE_EXTENSION", length=50)
	String extension;
	
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
	
	
	
	public Staff() {
		super();
	}

	public Staff(Integer staffId) {
		super();
		this.staffId = staffId;
	}

	public Integer getStaffId() {
		return staffId;
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

	public String getDivision() {
		return division;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public Timestamp getCreatedDttm() {
		return createdDttm;
	}

	public Timestamp getLastUpdateDttm() {
		return lastUpdateDttm;
	}

	public String getCreatedDttmStr() {
		return DateUtil.getExpandedTimeStamp(createdDttm);
	}

	public String getLastUpdateDttmStr() {
		return DateUtil.getExpandedTimeStamp(lastUpdateDttm);
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
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

	public void setDivision(String division) {
		this.division = division;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setCreatedDttm(Timestamp createdDttm) {
		this.createdDttm = createdDttm;
	}

	public void setLastUpdateDttm(Timestamp lastUpdateDttm) {
		this.lastUpdateDttm = lastUpdateDttm;
	}

	public void setCreatedDttmStr(String createdDttmStr) {
		this.createdDttmStr = createdDttmStr;
	}

	public void setLastUpdateDttmStr(String lastUpdateDttmStr) {
		this.lastUpdateDttmStr = lastUpdateDttmStr;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public String displayName() {
		return this.lastName +" , " 
				+ this.firstName;
	}
	
	
}
