package com.smartworks.invtmgmt.web.ui.transfer;

import javax.validation.constraints.NotNull;

public class UILaundryLoad {

	Integer loadId;
	boolean isOpen;
	String code;
	String createDttm;
	String closedDttm;
	String lastUpdateDttm;
	
	Integer washingMachineNo;
	String laundryDetails;
	
	@NotNull
	Integer dryerMachineNo;
	String dryerWeightDetails;
	String dryerTempSettings;
	boolean filterCleaned;
	
	public Integer getLoadId() {
		return loadId;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public String getCode() {
		return code;
	}
	public String getCreateDttm() {
		return createDttm;
	}
	public String getClosedDttm() {
		return closedDttm;
	}
	public String getLastUpdateDttm() {
		return lastUpdateDttm;
	}
	public Integer getWashingMachineNo() {
		return washingMachineNo;
	}
	public String getLaundryDetails() {
		return laundryDetails;
	}
	public Integer getDryerMachineNo() {
		return dryerMachineNo;
	}
	
	public void setLoadId(Integer loadId) {
		this.loadId = loadId;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setCreateDttm(String createDttm) {
		this.createDttm = createDttm;
	}
	public void setClosedDttm(String closedDttm) {
		this.closedDttm = closedDttm;
	}
	public void setLastUpdateDttm(String lastUpdateDttm) {
		this.lastUpdateDttm = lastUpdateDttm;
	}
	public void setWashingMachineNo(Integer washingMachineNo) {
		this.washingMachineNo = washingMachineNo;
	}
	public void setLaundryDetails(String laundryDetails) {
		this.laundryDetails = laundryDetails;
	}
	public void setDryerMachineNo(Integer dryerMachineNo) {
		this.dryerMachineNo = dryerMachineNo;
	}
	public String getDryerWeightDetails() {
		return dryerWeightDetails;
	}
	public String getDryerTempSettings() {
		return dryerTempSettings;
	}
	public boolean isFilterCleaned() {
		return filterCleaned;
	}
	public void setDryerWeightDetails(String dryerWeightDetails) {
		this.dryerWeightDetails = dryerWeightDetails;
	}
	public void setDryerTempSettings(String dryerTempSettings) {
		this.dryerTempSettings = dryerTempSettings;
	}
	public void setFilterCleaned(boolean filterCleaned) {
		this.filterCleaned = filterCleaned;
	}
	
	
	
	
}
