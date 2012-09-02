package com.smartworks.invtmgmt.core.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="EXCHANGE_SKU_RECORD")
@Proxy(lazy=false)
public class ExchangeSkuRecord {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	
	@Column(name="ISSUED_SKU")
	private String issuedSku;
	
	@Column(name="EXCHANGED_SKU")
	private String exchangedSku;
	
	@Version
	@Column(name="DTTM")
	private Timestamp dttm;
	
	@JoinColumn(name="TRANSACTION_DETAILS_ID")
	@ManyToOne
	private TransactionDetails transactionDetails;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIssuedSku() {
		return issuedSku;
	}

	public void setIssuedSku(String issuedSku) {
		this.issuedSku = issuedSku;
	}

	public String getExchangedSku() {
		return exchangedSku;
	}

	public void setExchangedSku(String exchangedSku) {
		this.exchangedSku = exchangedSku;
	}

	public Timestamp getDttm() {
		return dttm;
	}

	public void setDttm(Timestamp dttm) {
		this.dttm = dttm;
	}

	public TransactionDetails getTransactionDetails() {
		return transactionDetails;
	}

	public void setTransactionDetails(TransactionDetails transactionDetails) {
		this.transactionDetails = transactionDetails;
	}
	
}
