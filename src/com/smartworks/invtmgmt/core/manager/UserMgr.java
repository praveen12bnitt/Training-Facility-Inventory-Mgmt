package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.User;
import com.smartworks.invtmgmt.core.domain.Role;
import com.smartworks.invtmgmt.core.domain.UserRole;

public interface UserMgr {
	public void add(User t);
	public void update(User t);
	public User load(Integer userId);
	public List<User> loadAll();
	public List<User> loadActiveUser();	
	public List<User> getUser(Integer firstResult,Integer maxResults,String orderByField,String orderByType,String whereClause) ;
	public Long getUserTotalCount(String whereClause);
	public List<Role> getAllRoles();
	public void clearRoles(User t);
	public List<UserRole> getUserRoles(Integer userId);
}
