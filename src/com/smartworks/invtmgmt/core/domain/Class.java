package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;
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

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="CLASS")
@Proxy(lazy=false)
public class Class implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3454681505877948346L;
	@Id
	@Column(name="CLASS_NAME", length=50)
	String className;
	
	@Column(name="CLASS_DESC",length=100)
	String classDesc;
	

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "CLASS_PRODUCT", joinColumns = { @JoinColumn(name = "CLASS_NAME") }, inverseJoinColumns = { @JoinColumn(name = "PRODUCT_ID") })
	private Set<Product> products =  new HashSet<Product>(0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "CLASS_TRAINEE", joinColumns = { @JoinColumn(name = "CLASS_NAME") }, inverseJoinColumns = { @JoinColumn(name = "TRAINEE_ID") })
	private Set<Trainee> trainees =  new HashSet<Trainee>(0);
	
	
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
	
	
}
