package com.smartworks.invtmgmt.web.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartworks.invtmgmt.core.dao.TraineeDao;
import com.smartworks.invtmgmt.core.domain.Trainee;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;
import com.smartworks.invtmgmt.web.ui.transfer.inventory.ReportDetailsResponse;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	private TraineeDao traineeDao;
	
	protected static Logger logger = Logger
			.getLogger(CommonController.class);
	
	@RequestMapping(value = "/listtrainees.form", method = RequestMethod.GET)
	public @ResponseBody
	ReportDetailsResponse getAllTrainess(HttpServletRequest request) {
		logger.info("Get All Trainees<>");
		
		List<Trainee> trainees = new ArrayList<Trainee>();

		trainees = traineeDao.loadAll();
		ReportDetailsResponse response = new ReportDetailsResponse();
		
		response.setRows(trainees);
		response.setPage("1");
		response.setTotal("10");
		response.setRecords(String.valueOf(trainees.size()));
		return response;
	}
	
}
