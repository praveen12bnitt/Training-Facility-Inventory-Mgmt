package com.smartworks.invtmgmt.business;

import java.util.List;

import javax.persistence.EnumType;

public class TransactionDetails {
	EnumType transactionType;
	List<ItemSku> itemSkus;
	
	public EnumType getTransactionType() {
		return transactionType;
	}
	public List<ItemSku> getItemSkus() {
		return itemSkus;
	}
	public void setTransactionType(EnumType transactionType) {
		this.transactionType = transactionType;
	}
	public void setItemSkus(List<ItemSku> itemSkus) {
		this.itemSkus = itemSkus;
	}	
}
