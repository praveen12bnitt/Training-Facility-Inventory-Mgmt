package com.smartworks.invtmgmt.core.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="LAUNDRY")
@Proxy(lazy=false)
public class Laundry {
	@Id
	@GeneratedValue
	@Column(name="LAUNDRY_ID")	
	Integer laundryId;
	
	@Column(name="CREATEDDTTM")
	Timestamp createdDttm;
	
	@Version
	@Column(name="LASTUPDATEDTTM")
	Timestamp lastUpdateDttm;
	
	@Column(name="UNIT_NO")
	String unitNo;	
	
	@Column(name="LAUNDRY_TYPE")
	String laundryType;	

	@Column(name="TOTAL_WEIGHT")	
	Integer totalWeight;
	
	@Column(name="BUGGY_WEIGHT")	
	Integer buggyWeight;
	
	@Column(name="WEIGHT")	
	Integer weight;
	
	@Column(name="CLIENT_GROUP")	
	String clientGroup;
	
	@Column(name="CLIENT_SUB_GROUP")	
	String clientSubGroup;

	public Integer getLaundryId() {
		return laundryId;
	}

	public void setLaundryId(Integer laundryId) {
		this.laundryId = laundryId;
	}

	public Timestamp getCreatedDttm() {
		return createdDttm;
	}

	public void setCreatedDttm(Timestamp createdDttm) {
		this.createdDttm = createdDttm;
	}

	public Timestamp getLastUpdateDttm() {
		return lastUpdateDttm;
	}

	public void setLastUpdateDttm(Timestamp lastUpdateDttm) {
		this.lastUpdateDttm = lastUpdateDttm;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public Integer getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Integer totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Integer getBuggyWeight() {
		return buggyWeight;
	}

	public void setBuggyWeight(Integer buggyWeight) {
		this.buggyWeight = buggyWeight;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getClientGroup() {
		return clientGroup;
	}

	public void setClientGroup(String clientGroup) {
		this.clientGroup = clientGroup;
	}

	public String getClientSubGroup() {
		return clientSubGroup;
	}

	public void setClientSubGroup(String clientSubGroup) {
		this.clientSubGroup = clientSubGroup;
	}

	public String getLaundryType() {
		return laundryType;
	}

	public void setLaundryType(String laundryType) {
		this.laundryType = laundryType;
	}
	
}
