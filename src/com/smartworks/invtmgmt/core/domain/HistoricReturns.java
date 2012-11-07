package com.smartworks.invtmgmt.core.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

@Entity
@Table(name="HISTORIC_RETURNS")
public class HistoricReturns {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	
	@Column(name="RETURN_ID")
	private Integer returnId;
	
	@Column(name = "DATE")
	private Timestamp date;
	
	@Transient
	private String dateAsStr;
	
	@Column(name = "RETURN_QTY")
	private Integer returnQty;
	
	@Column(name = "LOST_QTY")
	private Integer lostQty;
	
	@Column(name = "ADD_NSN")
	private Integer addNsn;
	
	@Column(name="SSN")
	private Long ssn;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name = "SITE_NAME")
	private String siteName;
	
	@Column(name = "LOST_REASON")
	private String lostReason;
	
	@Column(name = "MATERIELID")
	private Integer materielid;
	
	@Column(name = "SITE")
	private Integer site;
	
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name= "UNITNAME")
	private String unitname;
	
	@Column(name = "REPAIR_QTY")
	private Integer repairQty;
	
	
	public Integer getReturnId() {
		return returnId;
	}
	public void setReturnId(Integer returnId) {
		this.returnId = returnId;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Integer getReturnQty() {
		return returnQty;
	}
	public void setReturnQty(Integer returnQty) {
		this.returnQty = returnQty;
	}
	public Integer getLostQty() {
		return lostQty;
	}
	public void setLostQty(Integer lostQty) {
		this.lostQty = lostQty;
	}
	public Integer getAddNsn() {
		return addNsn;
	}
	public void setAddNsn(Integer addNsn) {
		this.addNsn = addNsn;
	}
	public Long getSsn() {
		return ssn;
	}
	public void setSsn(Long ssn) {
		this.ssn = ssn;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getLostReason() {
		return lostReason;
	}
	public void setLostReason(String lostReason) {
		this.lostReason = lostReason;
	}
	public Integer getMaterielid() {
		return materielid;
	}
	public void setMaterielid(Integer materielid) {
		this.materielid = materielid;
	}
	public Integer getSite() {
		return site;
	}
	public void setSite(Integer site) {
		this.site = site;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public Integer getRepairQty() {
		return repairQty;
	}
	public void setRepairQty(Integer repairQty) {
		this.repairQty = repairQty;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDateAsStr() {
		return getDateAsStr(date);
	} 
	
	public String getDateAsStr(Timestamp ts) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").format(ts);
		} catch (Exception e) {

		}
		return StringUtils.EMPTY;
	}
	
}
