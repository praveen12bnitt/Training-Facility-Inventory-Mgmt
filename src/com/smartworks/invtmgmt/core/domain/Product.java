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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((productDesc == null) ? 0 : productDesc.hashCode());
		result = prime * result + ((productDetails == null) ? 0 : productDetails.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (productDesc == null) {
			if (other.productDesc != null)
				return false;
		} else if (!productDesc.equals(other.productDesc))
			return false;
		if (productDetails == null) {
			if (other.productDetails != null)
				return false;
		} else if (!productDetails.equals(other.productDetails))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
}
