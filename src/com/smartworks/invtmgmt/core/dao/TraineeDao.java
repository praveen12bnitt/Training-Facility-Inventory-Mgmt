package com.smartworks.invtmgmt.core.dao;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.Trainee;

public interface TraineeDao {
	public Trainee load(Integer id);	
	public List<Trainee> loadAll();
}
