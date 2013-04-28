package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

@Entity
@Table(name="TRANSACTION_TRACE")
@Proxy(lazy=false)
public class TransactionTrace implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="TRANSACTION_ID")
	private Integer trasactionId;
	
	@Column(name="CREATED_DTTM")
	private Timestamp createdDttm;
	
	@Column(name="LOCATION_ID")
	private Integer locationId;
	
	@Column(name="TRANSACTION_TYPE")
	@Enumerated(EnumType.STRING)
	private TransactionTypeEnum transType;
	
	@Column(name="USER_ID")
	private Integer userId;
	
	@Column(name="TRAINEE_ID")
	private Integer traineeId;
	
	@Column(name="STAFF_ID")
	private Integer staffId;
	
	@Column(name="SOURCE_LOCATION_ID")
	private Integer srcLocationId;
	
	@Column(name="REFERENCE_TRANSACTION_ID")
	private Integer refTransactionId;
	
	@Type(type="yes_no")
	@Column(name="CLOSED")
	private Boolean closed = false;
	
	@Lob
	@Column(name="SIGNATURE")
	private String sign;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "trasactionTrace", orphanRemoval=true )
	List<TransactionDetails> transDetails;
	

	
	

	public TransactionTrace() {
		super();
	}

	public TransactionTrace(Integer trasactionId) {
		super();
		this.trasactionId = trasactionId;
	}

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

	public List<TransactionDetails> getTransDetails() {
		return transDetails;
	}

	public void setTransDetails(List<TransactionDetails> transDetails) {
		this.transDetails = transDetails;
	}

	public Integer getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	

}
