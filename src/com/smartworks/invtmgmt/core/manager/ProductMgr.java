package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.core.domain.Product;

public interface ProductMgr {

	public void saveOrUpdate(Product product, List<ItemSku> itemSkus);
	
	public List<Product> getAllProducts();
}
