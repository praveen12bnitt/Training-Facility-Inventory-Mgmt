package com.smartworks.invtmgmt.business;

import java.sql.Timestamp;
import java.util.List;

import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

public class TransactionDetailsHolder {
	TransactionTypeEnum transactionType;
	List<ItemSku> itemSkus;
	Integer employeeId;
	Integer userId;
	Integer staffId;
	Timestamp dttm;
	Integer locationId;
	Integer srcLocationId;
	Integer refTransactionId;
	
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
	public Integer getEmployeeId() {
		return employeeId;
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
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
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
}
