package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.Trainee;

public interface TraineeMgr {

	public void add(Trainee t);
	public void update(Trainee t);
	public Trainee load(Integer traineeId);
	public List<Trainee> getTrainee(Integer firstResult,Integer maxResults,String orderByField,String orderByType,String whereClause) ;
	public Long getTraineeTotalCount(String orderByField,String orderByType,String whereClause);
}
