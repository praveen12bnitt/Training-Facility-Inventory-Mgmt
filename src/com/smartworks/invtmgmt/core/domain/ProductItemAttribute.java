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
@Table(name="PRODUCT_ITEM_ATTRIBUTES")
@Proxy(lazy=false)

public class ProductItemAttribute implements Serializable {
	/**
	 * default serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue
	@Column(name="PRODUCT_ITEM_ATTR_ID")
	private Integer productItemAttrId;
	
	@Column(name="PRODUCT_ITEM_ATTR_NAME_ID")
	private Integer productItemAttrNameId;
	
	@Column(name="PRODUCT_ITEM_ATTR_VALUE_ID")
	private Integer productItemAttrValueId;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PRODUCT_ITEM_ID")
	@JsonIgnore
	private ProductItem productItem;


	public Integer getProductItemAttrId() {
		return productItemAttrId;
	}

	public void setProductItemAttrId(Integer productItemAttrId) {
		this.productItemAttrId = productItemAttrId;
	}

	public Integer getProductItemAttrNameId() {
		return productItemAttrNameId;
	}

	public void setProductItemAttrNameId(Integer productItemAttrNameId) {
		this.productItemAttrNameId = productItemAttrNameId;
	}

	public Integer getProductItemAttrValueId() {
		return productItemAttrValueId;
	}

	public void setProductItemAttrValueId(Integer productItemAttrValueId) {
		this.productItemAttrValueId = productItemAttrValueId;
	}

	@JsonIgnore
	public ProductItem getProductItem() {
		return productItem;
	}

	@JsonIgnore
	public void setProductItem(ProductItem productItem) {
		this.productItem = productItem;
	}
	
	
}
