package com.smartworks.invtmgmt.web.ui.transfer;

import java.text.SimpleDateFormat;

import com.smartworks.invtmgmt.core.dao.LocationDao;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.platform.AppContextUtil;


public class UITransactionTrace {
	private String transactionDetails;
	private String createdDttm;
	private String locationName;
	
	

	public String getTransactionDetails() {
		return transactionDetails;
	}
	public void setTransactionDetails(String transactionDetails) {
		this.transactionDetails = transactionDetails;
	}
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
		StringBuffer sb = new StringBuffer();
		sb.append("<a href='"+contextPath+"/inventory/receive.form?transactionId=");
		sb.append(transactionTrace.getTrasactionId());
		sb.append("'>");
		sb.append(transactionTrace.getTrasactionId());
		sb.append("</a>");
	
		uiTransactionTrace.setTransactionDetails(sb.toString());
	
		LocationDao locationDao = AppContextUtil.getBean("locationDao");
		uiTransactionTrace.setLocationName(locationDao.load(transactionTrace.getLocationId()).getLocationName());
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		uiTransactionTrace.setCreatedDttm(sdf.format(transactionTrace.getCreatedDttm()));
		
		return uiTransactionTrace;
	}
	
	
}
