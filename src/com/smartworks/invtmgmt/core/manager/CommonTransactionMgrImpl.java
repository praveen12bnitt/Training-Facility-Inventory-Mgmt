package com.smartworks.invtmgmt.core.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.core.dao.ItemDao;
import com.smartworks.invtmgmt.core.dao.ProductDao;
import com.smartworks.invtmgmt.core.domain.Product;
import com.smartworks.invtmgmt.core.domain.ProductItem;

@Transactional
public class CommonTransactionMgrImpl implements CommonTransactionMgr {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		products = productDao.loadAll();
		for(Product product: products) {
			product.getItemList();
		}
		return products;
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void save(Product product) {
		for(ProductItem productItem: product.getItemList()) {
			productItem.setProduct(product);
		}
		productDao.save(product);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Map<Integer, String> getItemsByProductId(Integer productId) {
		Product product = productDao.load(productId);
		return itemDao.getItemNamesByIds(product.getItemsIds());
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void delete(Integer productId) {
		productDao.delete(productId);		
	}
	
	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Map<Integer,String> findByProductNameLike(String name){
		return productDao.findByProductNameLike(name);
	}

}
