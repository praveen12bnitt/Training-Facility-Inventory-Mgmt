package com.smartworks.invtmgmt.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.UserDao;
import com.smartworks.invtmgmt.core.domain.User;
import com.smartworks.invtmgmt.core.domain.Role;
import com.smartworks.invtmgmt.core.domain.UserRole;

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

	@Override
	public void save(User t) {
		getHibernateTemplate().save(t);
	}

	@Override
	public void update(User t) {
		getHibernateTemplate().saveOrUpdate(t);		
	}

	@Override
	public List<User> loadActiveUser() {
		String query = "from User where enabled=true";		
		List<User> users = getHibernateTemplate().find(query);
		return users;
	}

	@Override
	public List<User> getUser(Integer firstResult, Integer maxResults,
			String orderByField, String orderByType, String whereClause) {
		List<User> users = new ArrayList<User>();
		StringBuilder query = new StringBuilder();
		query.append("from User where 1=1 ").append(whereClause);
		query.append(" order by ").append(orderByField).append(" ").append(orderByType);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		users = session.createQuery(query.toString()).setFirstResult(firstResult).setMaxResults(maxResults).list();
		return users;
	}

	@Override
	public Long getUserTotalCount(String whereClause) {
		StringBuilder query = new StringBuilder();
		query.append("select count(*) from User where 1=1 ").append(whereClause);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long count = (Long) session.createQuery(query.toString()).uniqueResult();
		return count;
	}
	
	public List<Role> getAllRoles() {
		String query = "from Role";		
		List<Role> roles = getHibernateTemplate().find(query);
		return roles;
	}
	
	public void clearRoles(User t) {
		//User loadedUser = load(t.getUserid());
		List<UserRole> roles = getUserRoles(t.getUserid());
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		for(UserRole role: roles) {
			session.delete(role);
		}
		session.evict(t);
		session.flush();
	}
	
	public List<UserRole> getUserRoles(Integer userId) {
		String query = "from UserRole where user.userid = :userid";
		List<UserRole> roles = getHibernateTemplate().findByNamedParam(query, "userid", userId);
		for(UserRole role : roles) {
			getHibernateTemplate().getSessionFactory().getCurrentSession().evict(role);
			getHibernateTemplate().getSessionFactory().getCurrentSession().evict(role.getUser());
		}
		return roles;
	}
	
	
}
