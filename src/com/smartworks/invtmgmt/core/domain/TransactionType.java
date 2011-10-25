package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

@Entity
@Table(name="transaction_type")
@Proxy(lazy=false)
public class TransactionType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="transaction_type")
	@Enumerated(EnumType.STRING)
	private TransactionTypeEnum transType;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="location_id")
	private Location location;
	
	@Column(name="transaction_type_desc")
	private String transactionDesc;

	public TransactionTypeEnum getTransType() {
		return transType;
	}

	public Location getLocation() {
		return location;
	}

	public String getTransactionDesc() {
		return transactionDesc;
	}

	public void setTransType(TransactionTypeEnum transType) {
		this.transType = transType;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}

	
	
}
