package com.smartworks.invtmgmt.core.manager;

import java.util.List;

public interface ClassMgr {
	
	public void add(com.smartworks.invtmgmt.core.domain.Class t);
	public void update(com.smartworks.invtmgmt.core.domain.Class t);
	public com.smartworks.invtmgmt.core.domain.Class load(String className);
	public List<com.smartworks.invtmgmt.core.domain.Class> loadAll();
	public List<String> getItemMaps(String name);

}
