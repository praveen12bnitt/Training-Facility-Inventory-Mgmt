package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name="PRODUCT_ITEMS")
@Proxy(lazy=false)
public class ProductItem implements Serializable {


	/**
	 * default serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue
	@Column(name="PRODUCT_ITEM_ID")
	private Integer productItemId;
	
	public Integer getProductItemId() {
		return productItemId;
	}

	public void setProductItemId(Integer productItemId) {
		this.productItemId = productItemId;
	}

	@Column(name="ITEM_ID")
	private Integer itemId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PRODUCT_ID")
	@JsonIgnore
	private Product product;

	

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	@JsonIgnore
	public Product getProduct() {
		return product;
	}
	@JsonIgnore
	public void setProduct(Product product) {
		this.product = product;
	}

}
