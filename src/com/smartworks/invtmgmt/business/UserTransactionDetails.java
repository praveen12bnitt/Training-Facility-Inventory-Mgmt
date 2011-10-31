package com.smartworks.invtmgmt.business;

import java.sql.Timestamp;

public class UserTransactionDetails {
	private Integer trasactionId;
	private Timestamp timeStamp;
	
	public Integer getTrasactionId() {
		return trasactionId;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTrasactionId(Integer trasactionId) {
		this.trasactionId = trasactionId;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
}
