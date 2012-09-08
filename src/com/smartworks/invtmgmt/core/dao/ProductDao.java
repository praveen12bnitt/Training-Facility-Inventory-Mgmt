package com.smartworks.invtmgmt.core.dao;

import java.util.List;
import java.util.Map;

import com.smartworks.invtmgmt.core.domain.Product;
import com.smartworks.invtmgmt.core.domain.ProductItem;

public interface ProductDao {
	public void saveOrUpdate(Product product);
	public void save(Product product);
	public List<Product> loadAll();
	public Product load(Integer productId);
	public List<Product> loadAllOnlyProducts();
	public void delete(Integer productId);
	public Map<Integer,String> findByProductNameLike(String name, Integer locationId);
	public Product findByName(String productName);
	public List<ProductItem> getProductItemByProductId(Integer Id);
	public List<ProductItem> getProductItemsByProduct(Product product);
	
}
