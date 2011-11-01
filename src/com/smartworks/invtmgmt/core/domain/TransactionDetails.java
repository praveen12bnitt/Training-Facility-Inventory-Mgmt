package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="TRANSACTION_DETAILS")
@Proxy(lazy=false)
public class TransactionDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="TRANSACTION_DETAILS_ID")
	private Integer transactionDetailsId;
	
	@Column(name="SKU_CODE")
	private String skuCode;
	
	@Column(name="QUANTITY")
	private Integer quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRANSACTION_ID")
	private TransactionTrace trasactionTrace;

	public Integer getTransactionDetailsId() {
		return transactionDetailsId;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public TransactionTrace getTrasactionTrace() {
		return trasactionTrace;
	}

	public void setTransactionDetailsId(Integer transactionDetailsId) {
		this.transactionDetailsId = transactionDetailsId;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setTrasactionTrace(TransactionTrace trasactionTrace) {
		this.trasactionTrace = trasactionTrace;
	}

}
