package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.core.dao.UserDao;
import com.smartworks.invtmgmt.core.domain.Role;
import com.smartworks.invtmgmt.core.domain.User;
import com.smartworks.invtmgmt.core.domain.UserRole;

@Transactional
public class UserMgrImpl implements UserMgr {
	
	@Autowired
	UserDao userDao;
	
	@Override
	public void add(User user) {
		userDao.save(user);		
	}
	
	@Override
	public void update(User user) {
		userDao.update(user);		
	}
	

	
	@Override
	public User load(Integer userId) {
		return userDao.load(userId);
	}
	
	@Override
	public List<User> loadAll() {
		return userDao.loadAll();
	}
	
	@Override
	public List<User> loadActiveUser() {
		return userDao.loadActiveUser();
	}
	
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<User> getUser(Integer firstResult,Integer maxResults,String orderByField,String orderByType,String whereClause) {
		return userDao.getUser(firstResult, maxResults, orderByField, orderByType, whereClause);
	}
	
	
	public Long getUserTotalCount(String whereClause) {
		return userDao.getUserTotalCount(whereClause);
	}

	@Override
	public List<Role> getAllRoles() {
		return userDao.getAllRoles();
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void clearRoles(User t) {
		userDao.clearRoles(t);
	}

	@Transactional(readOnly=true,propagation=Propagation.REQUIRES_NEW)
	public List<UserRole> getUserRoles(Integer userId) {
		return userDao.getUserRoles(userId);
	}
	
}
