package com.smartworks.invtmgmt.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.domain.Product;

public class ProductDaoImpl  extends HibernateDaoSupport {

	public void saveOrUpdate(Product product) {
		getHibernateTemplate().saveOrUpdate(product);
	}
	
	public void save(Product product) {
		if(product.getProductId() == null) {
			getHibernateTemplate().save(product);
		} else {
			getHibernateTemplate().update(product);
		}
	}
	
	public void removeAllProductDetails(Integer productId) {  		
		getHibernateTemplate().bulkUpdate("delete ProductDetails pd where pd.product.productId = ?", productId) ;
	}

	public List<Product> loadAll() {
		List<Product> products = getHibernateTemplate().loadAll(Product.class);
		return products;
	}
	
	
	public List<Product> loadAllOnlyProducts() {
		List<Product> products = getHibernateTemplate().find("from Product");
		return products;
	}

	public Product load(Integer productId) {
		Product product = (Product)getHibernateTemplate().load(Product.class, productId);
		return product;
	}
	
	public void delete(Integer productId) {
		Product product = load(productId);
		getHibernateTemplate().delete(product);
	}
	
	public Map<Integer, String> productNames(String className, Integer locationId) {
		String query = "select p.productId , p.productName from Class as c left join c.products as p where p.location.locationId = :locationId and c.className = :className" ; 		
		String[] params = {"locationId", "className"};
		Object[] value = {locationId, className} ;		
		Map<Integer, String> returnMap = new HashMap<Integer, String>();
		List list = getHibernateTemplate().findByNamedParam(query, params, value);
		for(Object obj : list) {
			Object[] castObj = (Object[]) obj;
			Integer kitId = (Integer) castObj[0];
			String kitName = (String) castObj[1];
			returnMap.put(kitId,kitName);
		}
		return returnMap;
	}
	
	public List<Product> getProductsByLocation(Integer locationId) {
		String query = "from Product where location.locationId = :locationId";
		List<Product> pds = getHibernateTemplate().findByNamedParam(query, "locationId", locationId);
		return pds;
	}
	
	


	
}
