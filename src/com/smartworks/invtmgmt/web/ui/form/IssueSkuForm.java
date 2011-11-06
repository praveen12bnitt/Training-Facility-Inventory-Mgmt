package com.smartworks.invtmgmt.web.ui.form;

import java.util.List;

import org.springframework.util.AutoPopulatingList;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.Trainee;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;
import com.smartworks.invtmgmt.core.util.ItemSkuElementFactory;

public class IssueSkuForm {

	TransactionTypeEnum transactionType;
	String transactionDescription;
	Integer locationId;
	String locationName;
	
	Integer refTransactionId;
	
	Trainee trainee;
	
	private List<Item> items;	
	
			
	private List<ItemSku> itemSkus = new AutoPopulatingList<ItemSku>(new ItemSkuElementFactory());
	

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
	
	
	
	
}
