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
@Table(name="PRODUCT")
@Proxy(lazy=false)
public class Product implements Serializable {

	/**
	 * default serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="product_id")
	private Integer productId;
	
	@Column(name="product_name", length=100)
	private String productName;
	
	@Column(name="product_desc", length=250)
	private String productDesc;
	
	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "PRODUCT_ITEMS", 
	joinColumns = { @JoinColumn (name = "PRODUCT_ID") },
	inverseJoinColumns = { @JoinColumn(name = "ITEM_ID") }
	)
	private Set<Item> itemSet = new HashSet<Item>();

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Set<Item> getItemSet() {
		return itemSet;
	}

	public void setItemSet(Set<Item> itemSet) {
		this.itemSet = itemSet;
	}
	
}
