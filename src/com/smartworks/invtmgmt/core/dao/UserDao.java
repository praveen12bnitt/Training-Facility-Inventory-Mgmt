package com.smartworks.invtmgmt.core.dao;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.User;

public interface UserDao {
		
	public User load(Integer id);	
	public List<User> loadAll();

}
