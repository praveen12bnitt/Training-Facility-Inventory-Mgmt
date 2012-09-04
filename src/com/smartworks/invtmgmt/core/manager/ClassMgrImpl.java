package com.smartworks.invtmgmt.core.manager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.core.dao.ClassDao;
import com.smartworks.invtmgmt.core.dao.ProductDao;
import com.smartworks.invtmgmt.core.domain.Class;
import com.smartworks.invtmgmt.core.domain.Product;

@Transactional
@Service
public class ClassMgrImpl implements ClassMgr{
	
	@Autowired
	ClassDao classDao;
	
	@Autowired
	private ProductDao productDao;
	
	public ClassDao getClassDao() {
		return classDao;
	}

	public void setClassDao(ClassDao classDao) {
		this.classDao = classDao;
	}

	@Override
	public void add(Class t) {
		classDao.save(t);
	}

	@Override
	public void update(Class t) {
		classDao.update(t);
		
	}

	@Override
	public Class load(String className) {
		Class classes = classDao.load(className);
		return classes;
	}

	@Override
	public List<Class> loadAll() {
		List<Class> classes = classDao.loadAll();
		return classes;
	}
	
	@Override
	public List<String> getItemMaps(String name) {
			
		List<String> itemMap = classDao.getItemsByNameLike(name);
		
		return itemMap;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public void saveClass(Class clazz, Integer[] selectedProducts) { 
		Set<Product> pds = new HashSet<Product>();
		for(Integer productId : selectedProducts) {
			Product product = productDao.load(productId);
			pds.add(product);
		} 		
		clazz.setProducts(pds);		
		classDao.saveOrUpdate(clazz) ;	
	}

}
