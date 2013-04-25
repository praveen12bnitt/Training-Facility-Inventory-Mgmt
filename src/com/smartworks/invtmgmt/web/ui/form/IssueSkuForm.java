package com.smartworks.invtmgmt.web.ui.form;

import java.util.List;

import org.springframework.util.AutoPopulatingList;

import com.smartworks.invtmgmt.business.ExchangeInvt;
import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.Staff;
import com.smartworks.invtmgmt.core.domain.Trainee;
import com.smartworks.invtmgmt.core.service.ExchangeInvElementFactory;
import com.smartworks.invtmgmt.core.service.ItemSkuElementFactory;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

public class IssueSkuForm {

	TransactionTypeEnum transactionType;
	String transactionDescription;
	Integer locationId;
	String locationName;
	String itemNumber;
	
	
	Integer refTransactionId;
	
	Trainee trainee;
	
	Staff staff;
	
	@Deprecated
	private List<Item> items;	
	
	private List<String> itemNames;
	
			
	private List<ItemSku> itemSkus = new AutoPopulatingList<ItemSku>(new ItemSkuElementFactory());
	
	private List<ExchangeInvt> exchangeInvt = new AutoPopulatingList<ExchangeInvt>(new ExchangeInvElementFactory());
	

	public TransactionTypeEnum getTransactionType() {
		return transactionType;
	}

	public Integer getRefTransactionId() {
		return refTransactionId;
	}

	public void setRefTransactionId(Integer refTransactionId) {
		this.refTransactionId = refTransactionId;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTransactionType(TransactionTypeEnum transactionType) {
		this.transactionType = transactionType;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<ItemSku> getItemSkus() {
		return itemSkus;
	}

	public void setItemSkus(List<ItemSku> itemSkus) {
		this.itemSkus = itemSkus;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public List<ExchangeInvt> getExchangeInvt() {
		return exchangeInvt;
	}

	public void setExchangeInvt(List<ExchangeInvt> exchangeInvt) {
		this.exchangeInvt = exchangeInvt;
	}

	public List<String> getItemNames() {
		return itemNames;
	}

	public void setItemNames(List<String> itemNames) {
		this.itemNames = itemNames;
	}
	
	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	
	
}
