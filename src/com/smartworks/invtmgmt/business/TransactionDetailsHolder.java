package com.smartworks.invtmgmt.business;

import java.sql.Timestamp;
import java.util.List;

import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

public class TransactionDetailsHolder {
	TransactionTypeEnum transactionType;
	List<ItemSku> itemSkus;	
	Integer userId;
	Integer traineeId;
	Integer staffId;
	Timestamp dttm;
	Integer locationId;
	Integer srcLocationId;
	Integer refTransactionId;
	boolean staffIssue = false;
	
	List<ExchangeInvt> exchangeInvt;
	
	public TransactionTypeEnum getTransactionType() {
		return transactionType;
	}
	public List<ItemSku> getItemSkus() {
		return itemSkus;
	}
	public void setTransactionType(TransactionTypeEnum transactionType) {
		this.transactionType = transactionType;
	}
	public void setItemSkus(List<ItemSku> itemSkus) {
		this.itemSkus = itemSkus;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public Timestamp getDttm() {
		return dttm;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public Integer getSrcLocationId() {
		return srcLocationId;
	}
	public Integer getRefTransactionId() {
		return refTransactionId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public void setDttm(Timestamp dttm) {
		this.dttm = dttm;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public void setSrcLocationId(Integer srcLocationId) {
		this.srcLocationId = srcLocationId;
	}
	public void setRefTransactionId(Integer refTransactionId) {
		this.refTransactionId = refTransactionId;
	}
	public Integer getTraineeId() {
		return traineeId;
	}
	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}
	public boolean isStaffIssue() {
		return staffIssue;
	}
	public void setStaffIssue(boolean staffIssue) {
		this.staffIssue = staffIssue;
	}
	public List<ExchangeInvt> getExchangeInvt() {
		return exchangeInvt;
	}
	public void setExchangeInvt(List<ExchangeInvt> exchangeInvt) {
		this.exchangeInvt = exchangeInvt;
	}
	
}
