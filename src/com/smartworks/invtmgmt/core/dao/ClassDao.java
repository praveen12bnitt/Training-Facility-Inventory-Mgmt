package com.smartworks.invtmgmt.core.dao;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.Class;

public interface ClassDao {
	public void save(Class cls);
	public void saveOrUpdate(Class t);
	public void update(Class t);
	public com.smartworks.invtmgmt.core.domain.Class load(String className);	
	public List<Class> loadAll();
	public List<String> getItemsByNameLike(String name);

}
