package com.smartworks.invtmgmt.core.manager;

import java.util.List;
import java.util.Map;

import com.smartworks.invtmgmt.core.domain.Product;

public interface CommonTransactionMgr {
	public List<Product> getAllProducts();
	public void save(Product product);
	public void delete(Integer productId);
	public Map<Integer,String> getItemsByProductId(Integer productId);
	public Map<Integer,String> findByProductNameLike(String name);
}
