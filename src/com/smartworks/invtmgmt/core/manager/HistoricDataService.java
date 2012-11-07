package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.HistoricIssues;
import com.smartworks.invtmgmt.core.domain.HistoricReturns;

public interface HistoricDataService {

	public void importHistoricIssues(String issueFilePath);

	public List<HistoricIssues> getAllHistoricIsssues();

	public void importHistoricReturns1(String returnsFilePath);

	public List<HistoricReturns> getAllHistoricReturns();

}
