package com.smartworks.invtmgmt.converter;

import java.sql.Timestamp;
import java.util.Date;

import com.smartworks.invtmgmt.core.domain.Laundry;
import com.smartworks.invtmgmt.core.service.DateUtil;
import com.smartworks.invtmgmt.web.ui.transfer.UILaundry;

public class LaundryConverter {
	public static Laundry convertFromUILaundry(UILaundry uiLaundry) {
		Laundry laundry = new Laundry();
		laundry.setLaundryType(uiLaundry.getLaundryType());
		laundry.setUnitNo(uiLaundry.getUnitNo());
		String clientInfo = uiLaundry.getClientInfo();
		if(clientInfo != null) {
			clientInfo = clientInfo.trim();
			String clientGroup = clientInfo;
			String clientSubGroup = null;
			if(clientInfo.indexOf('-') != -1) {
				clientGroup = clientInfo.substring(0, clientInfo.indexOf('-')).trim();
				clientSubGroup = clientInfo.substring( clientInfo.indexOf('-')+1).trim();
			}
			laundry.setClientGroup(clientGroup);
			laundry.setClientSubGroup(clientSubGroup);
		}
		
		Integer totalWeight = Integer.valueOf(uiLaundry.getWeight());
		Integer buggyWeight = Integer.valueOf(uiLaundry.getBuggyWeight());
		laundry.setTotalWeight(totalWeight);
		laundry.setBuggyWeight(buggyWeight);
		laundry.setItemsWashed(uiLaundry.getItemsWashed());
		laundry.setComments(uiLaundry.getComments());
		laundry.setWeight(totalWeight-buggyWeight);
		Date timestamp = DateUtil.convertToTimeStamp(uiLaundry.getTime());
		laundry.setCreatedDttm(new Timestamp(timestamp.getTime()));
		laundry.setLastUpdateDttm(new Timestamp(timestamp.getTime()));
		return laundry;
	}
	/***********************************************************************/

	
	/***********************************************************************/
	
	public static UILaundry convertToUILaundry(Laundry laundry) {
		UILaundry uiLaundry = new UILaundry();
		uiLaundry.setUnitNo(laundry.getUnitNo());
		uiLaundry.setTime(DateUtil.convertFromTimeStamp(laundry.getCreatedDttm()));
		uiLaundry.setClientInfo(laundry.getClientGroup()+" - "+laundry.getClientSubGroup());
		uiLaundry.setWeight(String.valueOf(laundry.getTotalWeight()));
		uiLaundry.setBuggyWeight(String.valueOf(laundry.getBuggyWeight()));
		uiLaundry.setItemWeight(String.valueOf(laundry.getBuggyWeight()));
		return uiLaundry;
	}
}
