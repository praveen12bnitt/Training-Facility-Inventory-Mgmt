package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.core.domain.Product;

public interface ProductMgr {

	public void saveOrUpdate(Product product, List<ItemSku> itemSkus, MultipartFile kitFile);
	
	public void saveOrUpdate(MultipartFile kitFile);
	
	public List<Product> getAllProducts();

}
