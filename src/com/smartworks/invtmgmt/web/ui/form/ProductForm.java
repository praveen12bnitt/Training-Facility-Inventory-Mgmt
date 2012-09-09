package com.smartworks.invtmgmt.web.ui.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.AutoPopulatingList;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.core.domain.Product;
import com.smartworks.invtmgmt.core.service.ItemSkuElementFactory;


public class ProductForm {
	
	private Product product;
	
	private Integer selectedLocationId;
	
	private Map<Integer, String> locationOptions = new HashMap<Integer, String>();
	
	private List<ItemSku> itemSkus = new AutoPopulatingList<ItemSku>(new ItemSkuElementFactory());
	
	private List<String> itemNames = new ArrayList<String>();

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getSelectedLocationId() {
		return selectedLocationId;
	}

	public void setSelectedLocationId(Integer selectedLocationId) {
		this.selectedLocationId = selectedLocationId;
	}

	public Map<Integer, String> getLocationOptions() {
		return locationOptions;
	}

	public void setLocationOptions(Map<Integer, String> locationOptions) {
		this.locationOptions = locationOptions;
	}

	public List<ItemSku> getItemSkus() {
		return itemSkus;
	}

	public void setItemSkus(List<ItemSku> itemSkus) {
		this.itemSkus = itemSkus;
	}

	public List<String> getItemNames() {
		return itemNames;
	}

	public void setItemNames(List<String> itemNames) {
		this.itemNames = itemNames;
	}
	
	
	
	
	
}
