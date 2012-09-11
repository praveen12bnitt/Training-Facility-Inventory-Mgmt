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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDttm == null) ? 0 : createdDttm.hashCode());
		result = prime * result + ((createdDttmStr == null) ? 0 : createdDttmStr.hashCode());
		result = prime * result + ((division == null) ? 0 : division.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((extension == null) ? 0 : extension.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((lastUpdateDttm == null) ? 0 : lastUpdateDttm.hashCode());
		result = prime * result + ((lastUpdateDttmStr == null) ? 0 : lastUpdateDttmStr.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((staffId == null) ? 0 : staffId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Staff other = (Staff) obj;
		if (createdDttm == null) {
			if (other.createdDttm != null)
				return false;
		} else if (!createdDttm.equals(other.createdDttm))
			return false;
		if (createdDttmStr == null) {
			if (other.createdDttmStr != null)
				return false;
		} else if (!createdDttmStr.equals(other.createdDttmStr))
			return false;
		if (division == null) {
			if (other.division != null)
				return false;
		} else if (!division.equals(other.division))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (extension == null) {
			if (other.extension != null)
				return false;
		} else if (!extension.equals(other.extension))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (lastUpdateDttm == null) {
			if (other.lastUpdateDttm != null)
				return false;
		} else if (!lastUpdateDttm.equals(other.lastUpdateDttm))
			return false;
		if (lastUpdateDttmStr == null) {
			if (other.lastUpdateDttmStr != null)
				return false;
		} else if (!lastUpdateDttmStr.equals(other.lastUpdateDttmStr))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (staffId == null) {
			if (other.staffId != null)
				return false;
		} else if (!staffId.equals(other.staffId))
			return false;
		return true;
	}

	
	
	
}
