package com.smartworks.invtmgmt.core.domain.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.TransactionType;

@Embeddable
public class TransItemMappingPk implements Serializable {

	private static final long serialVersionUID = 1L;

	public TransItemMappingPk(TransactionType transactionType, Item item) {
		super();
		this.transactionType = transactionType;
		this.item = item;
	}

	public TransItemMappingPk() {
	};

	@ManyToOne
	@JoinColumn(name = "transaction_type")
	private TransactionType transactionType;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public Item getItem() {
		return item;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}