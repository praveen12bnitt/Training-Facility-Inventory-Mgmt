package com.smartworks.invtmgmt.web.ui.transfer;

import com.smartworks.invtmgmt.core.dao.LocationDao;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.platform.AppContextUtil;


public class UITransactionTrace {
	private String transactionId;
	private String createdDttm;
	private String locationName;
	
	public String getCreatedDttm() {
		return createdDttm;
	}
	public void setCreatedDttm(String createdDttm) {
		this.createdDttm = createdDttm;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	
	public static UITransactionTrace extractFromUserTransactionTrace(TransactionTrace transactionTrace, String contextPath) {
		UITransactionTrace uiTransactionTrace = new UITransactionTrace();
		
		uiTransactionTrace.setTransactionId("<u>"+transactionTrace.getTrasactionId().toString()+"</u>");
	
		LocationDao locationDao = AppContextUtil.getBean("locationDao");
		uiTransactionTrace.setLocationName(locationDao.load(transactionTrace.getLocationId()).getLocationName());
		uiTransactionTrace.setCreatedDttm(com.smartworks.invtmgmt.core.service.DateUtil.getExpandedTimeStamp(transactionTrace.getCreatedDttm()));
		
		return uiTransactionTrace;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
}
