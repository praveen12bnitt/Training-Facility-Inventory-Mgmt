package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name="CLASS")
@Proxy(lazy=false)
@JsonAutoDetect(fieldVisibility=Visibility.ANY,getterVisibility=Visibility.NONE, isGetterVisibility=Visibility.NONE)
public class Class implements Serializable{
	
	private static final long serialVersionUID = 3454681505877948346L;
	@Id
	@Column(name="CLASS_NAME", length=50)
	String className;
	
	@Column(name="CLASS_DESC",length=100)
	String classDesc;
	

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "CLASS_PRODUCT", joinColumns = { @JoinColumn(name = "CLASS_NAME") }, inverseJoinColumns = { @JoinColumn(name = "PRODUCT_ID") })
	@JsonIgnore
	private Set<Product> products =  new HashSet<Product>(0);
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "CLASS_STAFF", joinColumns = { @JoinColumn(name = "CLASS_NAME") }, inverseJoinColumns = { @JoinColumn(name = "STAFF_ID") })
	@JsonIgnore
	private Set<Staff> staffs =  new HashSet<Staff>(0);
	
	@OneToMany(mappedBy="cls", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Trainee> trainees =  new HashSet<Trainee>(0);
	
	@Column(name="CLASS_START_DATE")
	private String startDate;
	
	@Column(name="CLASS_END_DATE")
	private String endDate;
	
	@Column(name="COMMENT")
	private String comments;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Set<Trainee> getTrainees() {
		return trainees;
	}
	public void setTrainees(Set<Trainee> trainees) {
		this.trainees = trainees;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassDesc() {
		return classDesc;
	}
	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}

	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public Set<Staff> getStaffs() {
		return staffs;
	}
	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}
	
	@JsonIgnore
	public void addTrainee(Trainee trainee) {
		getTrainees().add(trainee);
		trainee.setCls(this);
	} 	
	
}
