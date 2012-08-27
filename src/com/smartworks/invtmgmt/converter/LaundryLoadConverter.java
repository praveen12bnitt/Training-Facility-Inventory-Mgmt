package com.smartworks.invtmgmt.converter;

import com.smartworks.invtmgmt.core.domain.LaundryTracking;
import com.smartworks.invtmgmt.core.service.DateUtil;
import com.smartworks.invtmgmt.web.ui.transfer.UILaundryLoad;

public class LaundryLoadConverter {

	public UILaundryLoad getUILaundryLoad(LaundryTracking laundryTracking) {
		
		UILaundryLoad uiLaundryLoad = new UILaundryLoad();
		
		uiLaundryLoad.setLoadId(laundryTracking.getLaundryTrankingId());
		if(laundryTracking.getIsOpen() != null)
			uiLaundryLoad.setOpen(laundryTracking.getIsOpen());
		if(laundryTracking.getCode() != null)
			uiLaundryLoad.setCode(laundryTracking.getCode());
		if(laundryTracking.getWashingMachineNo() != null)
			uiLaundryLoad.setWashingMachineNo(laundryTracking.getWashingMachineNo());
		
		uiLaundryLoad.setCreateDttm(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
		uiLaundryLoad.setLastUpdateDttm(DateUtil.getExpandedTimeStamp(laundryTracking.getLastUpdateDttm()));
		uiLaundryLoad.setClosedDttm(DateUtil.getExpandedTimeStamp(laundryTracking.getClosedDttm()));
		uiLaundryLoad.setWasherBuggyWeight(laundryTracking.getWeightWithBuggy());
		
		uiLaundryLoad.setLaundryDetails(getLaundryDetailsStr(laundryTracking));
		if(laundryTracking.getDryerMachineNo() != null) {
			uiLaundryLoad.setDryerMachineNo(laundryTracking.getDryerMachineNo());	
		}
		
		
		
		StringBuilder dryerWtDetails = new StringBuilder();
		if(laundryTracking.getWeightWithBuggy() != null) {
			dryerWtDetails.append("Weight with Buggy : "+laundryTracking.getWeightWithBuggy());
			dryerWtDetails.append("</br>");
		}
		
		if(laundryTracking.getWeightBuggy() != null) {
			dryerWtDetails.append("Weight of Buggy : "+laundryTracking.getWeightBuggy());
			dryerWtDetails.append("</br>");
		}
		
		if(laundryTracking.getWasherWeightBuggy() != null) {
			dryerWtDetails.append("Washer Buggy Weight : "+laundryTracking.getWasherWeightBuggy());
		}
			
		uiLaundryLoad.setDryerWeightDetails(dryerWtDetails.toString());
		if(laundryTracking.getDryerTempSetting() != null)
			uiLaundryLoad.setDryerTempSettings(laundryTracking.getDryerTempSetting().toString());
		if(laundryTracking.getCleanedFilter() != null)
			uiLaundryLoad.setFilterCleaned(laundryTracking.getCleanedFilter());
		
		return uiLaundryLoad;
		
	}
	
	
	
	private String getLaundryDetailsStr(LaundryTracking laundryTracking) {
		StringBuilder laundryDetails = new StringBuilder();
		
		laundryDetails.append("<table class=\"laundry-items\">");
		laundryDetails.append("<tr>");
		
		if(laundryTracking.getTseRoom() != null) {
			laundryDetails.append("<td>");
			laundryDetails.append("TSE Room:");
			laundryDetails.append(laundryTracking.getTseRoom());
			laundryDetails.append("</td>");
		}
		
		if(laundryTracking.getTowels() != null) {
			laundryDetails.append("<td>");
			laundryDetails.append("Towels Set:");
			laundryDetails.append(laundryTracking.getTowels());
			laundryDetails.append("</td>");
		}
		
		if(laundryTracking.getGymClothings() != null) {
			laundryDetails.append("<td>");
			laundryDetails.append("Gym Clothing set:");
			laundryDetails.append(laundryTracking.getGymClothings());
			laundryDetails.append("</td>");
			
		}	
		
		laundryDetails.append("</tr>");
		
		laundryDetails.append("<tr>");
		
		if(laundryTracking.getJockSocksBras() != null) {
			laundryDetails.append("<td>");
			laundryDetails.append("Jock Sock Bras Set:");
			laundryDetails.append(laundryTracking.getJockSocksBras());
			laundryDetails.append("</td>");
		}
		
		if(laundryTracking.getUniforms() != null) {
			laundryDetails.append("<td>");
			laundryDetails.append("Uniforms set:");
			laundryDetails.append(laundryTracking.getUniforms());
			laundryDetails.append("</td>");
		}
		
		if(laundryTracking.getRegLaundry() != null) {
			laundryDetails.append("<td>");
			laundryDetails.append("Reg Laundry:");
			laundryDetails.append(laundryTracking.getRegLaundry());
			laundryDetails.append("</td>");
		}
		
		
		
		laundryDetails.append("</tr>");
		
		laundryDetails.append("<tr>");
		
		if(laundryTracking.getDMD0006G() != null) {
			laundryDetails.append("<td>");
			laundryDetails.append("DMD 0006-G:");
			laundryDetails.append(laundryTracking.getDMD0006G());
			laundryDetails.append("</td>");
		}
		
		if(laundryTracking.getFAD0006E() != null) {
			laundryDetails.append("<td>");
			laundryDetails.append("FAD 0006-E:");
			laundryDetails.append(laundryTracking.getFAD0006E());
			laundryDetails.append("</td>");
		}
		
		if(laundryTracking.getCTD0006D() != null) {
			laundryDetails.append("<td>");
			laundryDetails.append("CTD 0006-D:");
			laundryDetails.append(laundryTracking.getCTD0006D());
			laundryDetails.append("</td>");
		}
		
		
		
		laundryDetails.append("</tr>");
		
		laundryDetails.append("<tr>");
		
		if(laundryTracking.getATFSABT0006H() != null) {
			laundryDetails.append("<td>");
			laundryDetails.append("ATF SABT 0006-H:");
			laundryDetails.append(laundryTracking.getATFSABT0006H());
			laundryDetails.append("</td>");
		}
		
		if(laundryTracking.getPTD0006F() != null) {
			laundryDetails.append("<td>");
			laundryDetails.append("PTD 0006-F:");
			laundryDetails.append(laundryTracking.getPTD0006F());
			laundryDetails.append("</td>");
		}
		
		if(laundryTracking.getUSBOPB0006B() != null) {
			laundryDetails.append("<td>");
			laundryDetails.append("USBOPB 0006-B:");
			laundryDetails.append(laundryTracking.getUSBOPB0006B());
			laundryDetails.append("</td>");
		}		
		
		laundryDetails.append("</tr>");
		laundryDetails.append("<tr>");
		
		if(laundryTracking.getFPS0006C() != null) {
			laundryDetails.append("<td>");
			laundryDetails.append("FPS 0006-C:");
			laundryDetails.append(laundryTracking.getFPS0006C());
			laundryDetails.append("</td>");
		}
		laundryDetails.append("</tr>");
		laundryDetails.append("</table>");
		
		return laundryDetails.toString();
	}
}
