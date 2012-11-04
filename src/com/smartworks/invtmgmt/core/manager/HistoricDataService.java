package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.HistoricIssues;

public interface HistoricDataService {

	public void importHistoricIssues(String issueFilePath);

	public List<HistoricIssues> getAllHistoricIsssues();

}
