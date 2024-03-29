package com.smartworks.invtmgmt.core.dao;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.Trainee;

public interface TraineeDao {
	
	public Integer save(Trainee t);
	public Trainee load(Integer id);	
	public void update(Trainee t);
	public List<Trainee> loadAll();
	public List<Trainee> loadActiveTrainee();
	public List<Trainee> getTrainee(Integer firstResult,Integer maxResults,String orderByField,String orderByType,String whereClause);
	public Long getTraineeTotalCount(String orderByField,String orderByType,String whereClause);
	public Trainee findByName(String name);
	public List<Trainee> findByClass(String clsName);
	public List<Trainee> getByName(String name);

}
