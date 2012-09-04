package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.Class;

public interface ClassMgr {
	
	public void add(Class t);
	public void update(Class t);
	public Class load(String className);
	public List<Class> loadAll();
	public List<String> getItemMaps(String name);
	
	public void saveClass(Class clazz, Integer[] selectedProducts);

}
