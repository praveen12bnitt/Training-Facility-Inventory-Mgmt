package com.smartworks.invtmgmt.web.ui.form;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.ItemAttribute;
import com.smartworks.invtmgmt.core.domain.Product;
import com.smartworks.invtmgmt.core.domain.ProductItem;
import com.smartworks.invtmgmt.core.domain.ProductItemAttribute;

public class ProductForm {
	
	
	private List<Product> products;
	
	private List<ProductItem> productItems;
	
	private List<ItemAttribute> itemAttribute;
	
	private Item items;
	
	public Item getItems() {
		return items;
	}

	public void setItems(Item items) {
		this.items = items;
	}

	public List<ItemAttribute> getItemAttribute() {
		return itemAttribute;
	}

	public void setItemAttribute(List<ItemAttribute> itemAttribute) {
		this.itemAttribute = itemAttribute;
	}

	public List<ProductItem> getProductItems() {
		return productItems;
	}

	public void setProductItems(List<ProductItem> productItems) {
		this.productItems = productItems;
	}

	public List<ProductItemAttribute> getProductItemAttributes() {
		return productItemAttributes;
	}

	public void setProductItemAttributes(
			List<ProductItemAttribute> productItemAttributes) {
		this.productItemAttributes = productItemAttributes;
	}

	private List<ProductItemAttribute> productItemAttributes;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void loadItemAttributes(){
		
		
	}

}
