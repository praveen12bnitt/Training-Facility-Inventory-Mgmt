package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.converter.ItemSkuConverter;
import com.smartworks.invtmgmt.core.dao.impl.ProductDaoImpl;
import com.smartworks.invtmgmt.core.domain.Product;
import com.smartworks.invtmgmt.core.domain.ProductDetails;

@Transactional
@Service
public class ProductMgrImpl implements ProductMgr {

	@Autowired
	private ProductDaoImpl productDao;
	
	@Autowired
	private ItemSkuConverter itemSkuConverter;;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveOrUpdate(Product product, List<ItemSku> itemSkus) { 	
		if(product.getProductId() != null)
			productDao.removeAllProductDetails(product.getProductId());
		
		for(ItemSku sku : itemSkus) {  			
			String itemSkuCode = itemSkuConverter.getItemSkuCode(sku); 			
			Integer quantity = sku.getQuantity();  			
			if(quantity == null || quantity < 1 ) continue; 					
			ProductDetails pd = new ProductDetails();
			pd.setQuantity(quantity);
			pd.setSkuCode(itemSkuCode); 
			product.addProductDetails(pd); 			
		} 		
		productDao.saveOrUpdate(product);
	}
	
	
	public List<Product> getAllProducts() {
		return productDao.loadAll();
	}
	
}
