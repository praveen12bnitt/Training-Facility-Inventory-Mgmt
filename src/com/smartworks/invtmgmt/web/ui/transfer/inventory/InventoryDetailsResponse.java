package com.smartworks.invtmgmt.web.ui.transfer.inventory;

import java.util.List;

public class InventoryDetailsResponse {

	/**
	* Current page of the query
	*/
	private String page;
	 
	/**
	* Total pages for the query
	*/
	private String total;
	 
	/**
	* Total number of records for the query
	*/
	private String records;
	 
	/**
	* An array that contains the actual objects
	*/
	private List<UIInventory> rows;
	 
	 
	public InventoryDetailsResponse() {
	}
	 
	public String getPage() {
	return page;
	}
	 
	public void setPage(String page) {
	this.page = page;
	}
	 
	public String getTotal() {
	return total;
	}
	 
	public void setTotal(String total) {
	this.total = total;
	}
	 
	public String getRecords() {
	return records;
	}
	 
	public void setRecords(String records) {
	this.records = records;
	}
	 
	public List<UIInventory> getRows() {
	return rows;
	}
	 
	public void setRows(List<UIInventory> rows) {
	this.rows = rows;
	}
}
