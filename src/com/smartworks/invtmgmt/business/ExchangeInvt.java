package com.smartworks.invtmgmt.business;

public class ExchangeInvt {

	private ItemSku issuedSku = new ItemSku();
	private ItemSku exchangedSku = new ItemSku();
	
	public ItemSku getIssuedSku() {
		return issuedSku;
	}
	public void setIssuedSku(ItemSku issuedSku) {
		this.issuedSku = issuedSku;
	}
	public ItemSku getExchangedSku() {
		return exchangedSku;
	}
	public void setExchangedSku(ItemSku exchangedSku) {
		this.exchangedSku = exchangedSku;
	}
	
	
	
}
