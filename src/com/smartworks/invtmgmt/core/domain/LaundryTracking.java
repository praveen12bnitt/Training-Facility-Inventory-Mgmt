package com.smartworks.invtmgmt.core.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

@Entity
@Table(name="LAUNDRY_TRACKING")
@Proxy(lazy=false)
public class LaundryTracking {

	@Id
	@GeneratedValue
	@Column(name="LAUNDRY_TRACKING_ID")	
	Integer laundryTrankingId;
	
	@Type(type="yes_no")
	@Column(name="IS_OPEN")	
	Boolean isOpen = true;
	
	@Column(name="CODE")
	String code;
	
	@Column(name="CREATEDDTTM")
	Timestamp createdDttm;
	
	@Column(name="CLOSEDDTTM")
	Timestamp closedDttm;
	
	@Version
	@Column(name="LASTUPDATEDTTM")
	Timestamp lastUpdateDttm;
	
	@NotNull(message = "Waching machine number cannot be null")
	@Column(name="WAS_MC_NO")
	Integer washingMachineNo;
	
	@Column(name="TSE_ROOM")
	Integer tseRoom;
	
	@Column(name="TOWELS")
	Integer towels;
	
	@Column(name="GYM")
	Integer gymClothings;
	
	@Column(name="JOCK_SOCK_BRA")
	Integer jockSocksBras;
	
	@Column(name="UNIFORM")
	Integer uniforms;
	
	@Column(name="REG_LAUNDRY")
	Integer regLaundry;
	
	@Column(name="DMD_0006_G")
	Integer DMD0006G;
	
	@Column(name="FAD_0006_E")
	Integer FAD0006E;
	
	@Column(name="CTD_0006_D")
	Integer CTD0006D;
	
	@Column(name="ATF_SABT_0006_H")
	Integer ATFSABT0006H;
	
	@Column(name="PTD_0006_F")
	Integer PTD0006F;
	
	@Column(name="USBOPB_0006_B")
	Integer USBOPB0006B;
	
	@Column(name="FPS_0006_C")
	Integer FPS0006C;
	
	@Column(name="DRYER_MC_NO")	
	Integer dryerMachineNo;
	
	@Column(name="WEIGHT_WITH_BUGGY")	
	Integer weightWithBuggy;
	
	@Column(name="WEIGHT_BUGGY")	
	Integer weightBuggy;
	
	@Column(name="DRYER_TEMP")
	Integer dryerTempSetting;
	
	@Column(name="CLEANED_DRYER_FILTER")
	Boolean cleanedFilter;
	
	public Integer getLaundryTrankingId() {
		return laundryTrankingId;
	}
	public String getCode() {
		return code;
	}
	public Timestamp getCreatedDttm() {
		return createdDttm;
	}
	public Timestamp getClosedDttm() {
		return closedDttm;
	}
	public Timestamp getLastUpdateDttm() {
		return lastUpdateDttm;
	}
	public Integer getWashingMachineNo() {
		return washingMachineNo;
	}
	public Integer getTseRoom() {
		return tseRoom;
	}
	public Integer getTowels() {
		return towels;
	}
	public Integer getGymClothings() {
		return gymClothings;
	}
	public Integer getJockSocksBras() {
		return jockSocksBras;
	}
	public Integer getUniforms() {
		return uniforms;
	}
	public Integer getRegLaundry() {
		return regLaundry;
	}
	public Integer getDMD0006G() {
		return DMD0006G;
	}
	public Integer getFAD0006E() {
		return FAD0006E;
	}
	public Integer getCTD0006D() {
		return CTD0006D;
	}
	public Integer getATFSABT0006H() {
		return ATFSABT0006H;
	}
	public Integer getPTD0006F() {
		return PTD0006F;
	}
	public Integer getUSBOPB0006B() {
		return USBOPB0006B;
	}
	public Integer getFPS0006C() {
		return FPS0006C;
	}
	public Integer getDryerMachineNo() {
		return dryerMachineNo;
	}
	public Integer getWeightWithBuggy() {
		return weightWithBuggy;
	}
	public Integer getWeightBuggy() {
		return weightBuggy;
	}
	public Integer getDryerTempSetting() {
		return dryerTempSetting;
	}
	public Boolean getCleanedFilter() {
		return cleanedFilter;
	}
	public void setLaundryTrankingId(Integer laundryTrankingId) {
		this.laundryTrankingId = laundryTrankingId;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setCreatedDttm(Timestamp createdDttm) {
		this.createdDttm = createdDttm;
	}
	public void setClosedDttm(Timestamp closedDttm) {
		this.closedDttm = closedDttm;
	}
	public void setLastUpdateDttm(Timestamp lastUpdateDttm) {
		this.lastUpdateDttm = lastUpdateDttm;
	}
	public void setWashingMachineNo(Integer washingMachineNo) {
		this.washingMachineNo = washingMachineNo;
	}
	public void setTseRoom(Integer tseRoom) {
		this.tseRoom = tseRoom;
	}
	public void setTowels(Integer towels) {
		this.towels = towels;
	}
	public void setGymClothings(Integer gymClothings) {
		this.gymClothings = gymClothings;
	}
	public void setJockSocksBras(Integer jockSocksBras) {
		this.jockSocksBras = jockSocksBras;
	}
	public void setUniforms(Integer uniforms) {
		this.uniforms = uniforms;
	}
	public void setRegLaundry(Integer regLaundry) {
		this.regLaundry = regLaundry;
	}
	public void setDMD0006G(Integer dMD0006G) {
		DMD0006G = dMD0006G;
	}
	public void setFAD0006E(Integer fAD0006E) {
		FAD0006E = fAD0006E;
	}
	public void setCTD0006D(Integer cTD0006D) {
		CTD0006D = cTD0006D;
	}
	public void setATFSABT0006H(Integer aTFSABT0006H) {
		ATFSABT0006H = aTFSABT0006H;
	}
	public void setPTD0006F(Integer pTD0006F) {
		PTD0006F = pTD0006F;
	}
	public void setFPS0006C(Integer fps0006C) {
		FPS0006C = fps0006C;
	}
	public void setUSBOPB0006B(Integer uSBOPB0006B) {
		USBOPB0006B = uSBOPB0006B;
	}
	public void setDryerMachineNo(Integer dryerMachineNo) {
		this.dryerMachineNo = dryerMachineNo;
	}
	public void setWeightWithBuggy(Integer weightWithBuggy) {
		this.weightWithBuggy = weightWithBuggy;
	}
	public void setWeightBuggy(Integer weightBuggy) {
		this.weightBuggy = weightBuggy;
	}
	public void setDryerTempSetting(Integer dyrerTempSetting) {
		this.dryerTempSetting = dyrerTempSetting;
	}
	public void setCleanedFilter(Boolean cleanedFilter) {
		this.cleanedFilter = cleanedFilter;
	}
	public Boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	
}
