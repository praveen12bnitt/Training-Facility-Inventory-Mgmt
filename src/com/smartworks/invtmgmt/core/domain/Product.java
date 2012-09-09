package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	@Column(name="PRODUCT_ID")
	private Integer productId;
	
	@Column(name="PRODUCT_NAME", length=100)
	private String productName;
	
	@Column(name="PRODUCT_DESC", length=250)
	private String productDesc;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="LOCATION_ID") 	
	private Location location;

	@OneToMany(mappedBy="product", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<ProductDetails> productDetails = new HashSet<ProductDetails>();

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

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<ProductDetails> getProductDetails() {
		return productDetails;
	}

	@JsonIgnore
	public void setProductDetails(Set<ProductDetails> productDetails) {
		this.productDetails = productDetails;
	}
	
	@JsonIgnore
	public void addProductDetails(ProductDetails pd) {
		pd.setProduct(this);
		this.getProductDetails().add(pd);
	}
	
	
}
