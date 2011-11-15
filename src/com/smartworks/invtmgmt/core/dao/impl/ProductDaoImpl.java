package com.smartworks.invtmgmt.core.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.ProductDao;
import com.smartworks.invtmgmt.core.domain.Product;

public class ProductDaoImpl  extends HibernateDaoSupport implements ProductDao{

	@Override
	public void saveOrUpdate(Product product) {
		getHibernateTemplate().saveOrUpdate(product);
	}
	
	@Override
	public Product save(Product product) {
		return (Product)getHibernateTemplate().save(product);
	}

	@Override
	public List<Product> loadAll() {
		List<Product> products = getHibernateTemplate().loadAll(Product.class);
		return products;
	}

	@Override
	public Product load(Integer productId) {
		Product product = (Product)getHibernateTemplate().load(Product.class, productId);
		return product;
	}

}
