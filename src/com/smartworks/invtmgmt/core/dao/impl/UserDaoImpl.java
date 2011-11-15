package com.smartworks.invtmgmt.core.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.UserDao;
import com.smartworks.invtmgmt.core.domain.User;

public class UserDaoImpl  extends HibernateDaoSupport
implements UserDao {
	public User load(Integer id) {
		return getHibernateTemplate().load(User.class, id);
	}

	@Override
	public List<User> loadAll() {
		String query = "from User";		
		List<User> users = getHibernateTemplate().find(query);
		return users;
	}
}
