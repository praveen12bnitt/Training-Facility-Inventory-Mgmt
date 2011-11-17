package com.smartworks.invtmgmt.core.dao;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.Product;

public interface ProductDao {
	public void saveOrUpdate(Product product);
	public void save(Product product);
	public List<Product> loadAll();
	public Product load(Integer productId);
	public List<Product> loadAllOnlyProducts();
	public void delete(Integer productId);
}
