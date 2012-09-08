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
@Table(name="PRODUCT_ATTR")
@Proxy(lazy=false)
public class ProductItemMapping implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2795289362542139987L;
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer Id;
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PRODUCT_ID")
	@JsonIgnore
	private ProductItem productItem;
	
	public ProductItem productItem() {
		return productItem;
	}

	public void productItem(ProductItem productItem) {
		this.productItem = productItem;
	}

	public ItemAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(ItemAttribute attribute) {
		this.attribute = attribute;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ITEM_ATTR_ID")
	@JsonIgnore
	private ItemAttribute attribute;	

}
