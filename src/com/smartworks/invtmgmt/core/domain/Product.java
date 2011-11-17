package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
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
	@GeneratedValue
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

	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy="product")
	private List<ProductItem> itemList;

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
	
	public List<ProductItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<ProductItem> itemList) {
		this.itemList = itemList;
	}
	
	@Transient
	public List<Integer> getItemsIds() {
		List<Integer> itemIds=new ArrayList<Integer>();
		if(itemList !=null && itemList.size()>0) {
			for(ProductItem productItem: itemList) {
				itemIds.add(productItem.getItemId());
			}
		}
		return itemIds;
	}
	
}
