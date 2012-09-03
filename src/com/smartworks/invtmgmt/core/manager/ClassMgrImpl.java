package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.core.dao.ClassDao;
import com.smartworks.invtmgmt.core.domain.Class;

public class ClassMgrImpl implements ClassMgr{
	
	ClassDao classDao;
	
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

}
