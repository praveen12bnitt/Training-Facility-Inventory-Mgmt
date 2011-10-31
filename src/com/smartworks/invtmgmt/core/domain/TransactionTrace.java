package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

public class TransactionTrace implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="TRANSACTION_ID")
	private Integer trasactionId;
	
	@Column(name="TIMESTAMP")
	private Timestamp createdDttm;
	
	@Column(name="LOCATION_ID")
	private Integer locationId;
	
	@Column(name="EMPLOYEE_ID")
	private Integer employeeId;
	
	@Column(name="TRANSACTION_TYPE")
	private TransactionTypeEnum transType;
	
	@Column(name="USER_ID")
	private Integer userId;
	
	@Column(name="STAFF_ID")
	private Integer staffId;
	
	@Column(name="SOURCE_LOCATION_ID")
	private Integer srcLocationId;
	
	@Column(name="REFERENCE_TRANSACTION_ID")
	private Integer refTransactionId;
	
	@Type(type="yes_no")
	@Column(name="CLOSED")
	private Boolean closed;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trasactionTrace")
	Set<TransactionDetails> transDetails;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getTrasactionId() {
		return trasactionId;
	}

	public Timestamp getCreatedDttm() {
		return createdDttm;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public TransactionTypeEnum getTransType() {
		return transType;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public Integer getSrcLocationId() {
		return srcLocationId;
	}

	public Integer getRefTransactionId() {
		return refTransactionId;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setTrasactionId(Integer trasactionId) {
		this.trasactionId = trasactionId;
	}

	public void setCreatedDttm(Timestamp createdDttm) {
		this.createdDttm = createdDttm;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public void setTransType(TransactionTypeEnum transType) {
		this.transType = transType;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public void setSrcLocationId(Integer srcLocationId) {
		this.srcLocationId = srcLocationId;
	}

	public void setRefTransactionId(Integer refTransactionId) {
		this.refTransactionId = refTransactionId;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}
	
	

}
