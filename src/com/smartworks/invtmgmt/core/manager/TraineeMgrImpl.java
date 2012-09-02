package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.core.dao.TraineeDao;
import com.smartworks.invtmgmt.core.domain.Trainee;

@Transactional
public class TraineeMgrImpl implements TraineeMgr {

	TraineeDao traineeDao;
	
	@Override
	public void add(Trainee t) {
		traineeDao.save(t);		
	}
	
	@Override
	public void update(Trainee t) {
		traineeDao.update(t);		
	}
	
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public Trainee load(Integer traineeId) {
		return traineeDao.load(traineeId);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<Trainee> loadActiveTrainee() {
		return traineeDao.loadActiveTrainee();
	}
	
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<Trainee> loadAll() {
		return traineeDao.loadAll();
	}
	
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<Trainee> getTrainee(Integer firstResult,Integer maxResults,String orderByField,String orderByType,String whereClause) {
		return traineeDao.getTrainee(firstResult, maxResults, orderByField, orderByType, whereClause);
	}
	
	
	public Long getTraineeTotalCount(String orderByField,String orderByType,String whereClause) {
		return traineeDao.getTraineeTotalCount(orderByField,orderByType,whereClause);
	}

	public TraineeDao getTraineeDao() {
		return traineeDao;
	}

	public void setTraineeDao(TraineeDao traineeDao) {
		this.traineeDao = traineeDao;
	}

	@Override
	public List<Trainee> getByClassName(String name) {
		return traineeDao.findByClass(name);
	}

	@Override
	public List<Trainee> getByName(String name) {
		return traineeDao.getByName(name);
	}

	
}
