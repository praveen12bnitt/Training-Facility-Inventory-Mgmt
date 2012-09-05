package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.Trainee;

public interface TraineeMgr {

	public Integer add(Trainee t);
	public void update(Trainee t);
	public Trainee load(Integer traineeId);
	public List<Trainee> loadActiveTrainee();
	public List<Trainee> loadAll();
	public List<Trainee> getTrainee(Integer firstResult,Integer maxResults,String orderByField,String orderByType,String whereClause) ;
	public Long getTraineeTotalCount(String orderByField,String orderByType,String whereClause);
	public List<Trainee> getByClassName(String name);
	public List<Trainee> getByName(String name);
}
