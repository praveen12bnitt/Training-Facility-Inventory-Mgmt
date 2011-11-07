package com.smartworks.invtmgmt.core.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.TraineeDao;
import com.smartworks.invtmgmt.core.domain.Trainee;

public class TraineeDaoImpl extends HibernateDaoSupport
 implements TraineeDao {
	public Trainee load(Integer id) {
		return getHibernateTemplate().load(Trainee.class, id);
	}

	@Override
	public List<Trainee> loadAll() {
		String query = "from Trainee";		
		List<Trainee> trainees = getHibernateTemplate().find(query);
		return trainees;
	}
	
}
