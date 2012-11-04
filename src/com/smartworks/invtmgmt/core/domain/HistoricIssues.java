package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;
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
@Table(name="HISTORIC_ISSUES")
public class HistoricIssues implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	
	@Column(name="ISSUE_DATE")
	private Timestamp issueDate;
	
	@Transient
	private String issueDateStr;
	
	@Column(name="ZONE")
	private String zone;
	
	@Column(name="CLASS_NAME")
	private String className;
	
	@Column(name="CLASS_NUMBER")
	private String classNumber;
	
	@Column(name="ISSUE_TYPE")
	private String issueType;
	
	@Column(name="CUSTOMER_TYPE")
	private String customerType;
	
	@Column(name="DUE_DATE")
	private Timestamp dueDate;
	
	@Transient
	private String dueDateStr;
	
	@Column(name="ISSUE_ID")
	private Integer issueId;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="INITIAL")
	private String initial;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="NSN")
	private Long nsn;
	
	@Column(name="NOMENCLATURE")
	private String nomenclature;
	
	@Column(name="QUANTITY")
	private Integer qty;
	
	public Timestamp getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Timestamp issueDate) {
		this.issueDate = issueDate;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassNumber() {
		return classNumber;
	}
	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public Timestamp getDueDate() {
		return dueDate;
	}
	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}
	public Integer getIssueId() {
		return issueId;
	}
	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getNsn() {
		return nsn;
	}
	public void setNsn(Long nsn) {
		this.nsn = nsn;
	}
	public String getNomenclature() {
		return nomenclature;
	}
	public void setNomenclature(String nomenclature) {
		this.nomenclature = nomenclature;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getIssueDateStr() {
		return getDateAsStr(issueDate);
	}
	public String getDueDateStr() {
		return getDateAsStr(dueDate);
	}
	
	public String getDateAsStr(Timestamp ts) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").format(ts);
		} catch (Exception e) {

		}
		return StringUtils.EMPTY;
	}
	

}
