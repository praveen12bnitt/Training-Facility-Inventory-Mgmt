package com.smartworks.invtmgmt.web.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.converter.UIDomainConverter;
import com.smartworks.invtmgmt.core.dao.TransactionTypeDao;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.Trainee;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.domain.TransactionType;
import com.smartworks.invtmgmt.core.manager.InvtTransManager;
import com.smartworks.invtmgmt.core.manager.ItemMgr;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;
import com.smartworks.invtmgmt.web.ui.form.IssueSkuForm;

import com.smartworks.invtmgmt.web.ui.transfer.UITransactionTrace;
import com.smartworks.invtmgmt.web.ui.transfer.inventory.ReportDetailsResponse;


@Controller
@RequestMapping("/inventory")
public class ItemIssueFormController {
	@Autowired
	ItemMgr itemMgr = null;
	
	@Autowired
	TransactionTypeDao transactionTypeDao = null;
	
	@Autowired
	InvtTransManager invtTransMgr = null;
	
	
	protected static Logger logger = Logger
			.getLogger(ItemIssueFormController.class);

	
	@RequestMapping(value="/issue.form", method=RequestMethod.GET)
	public ModelAndView displayTransaction(HttpServletRequest request, HttpServletResponse response, @RequestParam TransactionTypeEnum transactionTypeEnum) {		
		TransactionType transactionType = transactionTypeDao.load(transactionTypeEnum);		
		List<Item> items =   itemMgr.getItemsForTransaction(transactionType);
		
		Trainee trainee = new Trainee();
		trainee.setFirstName("Palanivel");
		trainee.setLastName("rajan");
		trainee.setTraineeId(1);
		
		
		IssueSkuForm issueSkuForm = new IssueSkuForm();		
		issueSkuForm.setTrainee(trainee);
		issueSkuForm.setLocationId(1);
		issueSkuForm.setLocationName("My Location");
		issueSkuForm.setTransactionDescription("My description");
		issueSkuForm.setTransactionType(TransactionTypeEnum.ISSUE_UNIFORM_STUDENT);		
		issueSkuForm.setItems(items);
		
		ModelAndView mav = new ModelAndView("transaction/issueSku");
		mav.addObject("issueSkuForm", issueSkuForm);
		return mav;
	}
	
	@RequestMapping(value="/issue.form", method=RequestMethod.POST)
	public ModelAndView handle(HttpServletRequest request, @ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm) {
		
		logger.info("process Incevntory change");
		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(issueSkuForm);
		boolean issueSkuSucceded = invtTransMgr.processInventoryChange(transDetailsHolder);
		
		TransactionType transactionType = transactionTypeDao.load(issueSkuForm.getTransactionType());
		List<Item> items =   itemMgr.getItemsForTransaction(transactionType);
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/issueSku");
		mav.addObject("issueSkuForm", issueSkuForm);
		mav.addObject("transactionFormMessage","Issued Successfully");
		return mav;
	}
	
	
	@RequestMapping(value = "/listopentrans.form", method = RequestMethod.GET)
	public ModelAndView openReceive(@RequestParam int userId, @RequestParam TransactionTypeEnum transactionTypeEnum,@RequestParam int locationId) {
		logger.info("Received request to show all received transactions");
		ModelAndView mav = new ModelAndView("transaction/opentransactions");
		mav.addObject("userId",userId);
		mav.addObject("transactionTypeEnum",transactionTypeEnum);
		mav.addObject("locationId",locationId);
		return mav;
	}

	
	@RequestMapping(value = "/opentransactions.form", method = RequestMethod.GET)
	public @ResponseBody
	ReportDetailsResponse getAllOpenTransactions(HttpServletRequest request, @RequestParam int userId,
			@RequestParam TransactionTypeEnum transactionTypeEnum, @RequestParam int locationId) {
		logger.info("getAllOpenTransactions for user<>");
		String contextPath = request.getContextPath();
		ReportDetailsResponse response = new ReportDetailsResponse();
		
		List<TransactionTrace> transTraceList = invtTransMgr.getOpenTransactionsForUser(locationId, userId, transactionTypeEnum);

		List<UITransactionTrace> uiTransTraceList = new ArrayList<UITransactionTrace>();

		
		for (TransactionTrace transTrace : transTraceList) {
			uiTransTraceList.add(UITransactionTrace.extractFromUserTransactionTrace(transTrace, contextPath));
		}
		response.setRows(uiTransTraceList);
		response.setPage("1");
		response.setTotal("10");
		response.setRecords(String.valueOf(uiTransTraceList.size()));
		return response;
	}
	
	
	@RequestMapping(value="/receive.form", method=RequestMethod.GET)
	public ModelAndView displayTransaction(HttpServletRequest request, HttpServletResponse response, @RequestParam int transactionId) {		
		
		TransactionDetailsHolder transDetails = invtTransMgr.getTransDetails(transactionId);
		Trainee trainee = new Trainee();
		trainee.setFirstName("Palanivel");
		trainee.setLastName("rajan");
		trainee.setTraineeId(1);
		logger.info(transDetails.getItemSkus());
		IssueSkuForm issueSkuForm = new IssueSkuForm();
		TransactionTypeEnum transactionType = TransactionTypeEnum.getReturnTransaction(transDetails.getTransactionType());
		issueSkuForm.setTransactionType(transactionType);
		issueSkuForm.setTrainee(trainee);
		issueSkuForm.setRefTransactionId(transactionId);
		ModelAndView mav = new ModelAndView("transaction/receiveSku");
		mav.addObject("transDetails", transDetails);
		mav.addObject("issueSkuForm", issueSkuForm);
		return mav;
	}
	
	
	@RequestMapping(value="/receive.form", method=RequestMethod.POST)
	public ModelAndView receiveInventory(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm) {		
		
		logger.info("process Incevntory change");
		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(issueSkuForm);
		boolean issueSkuSucceded = invtTransMgr.processInventoryChange(transDetailsHolder);
		
		TransactionType transactionType = transactionTypeDao.load(issueSkuForm.getTransactionType());
		List<Item> items =   itemMgr.getItemsForTransaction(transactionType);
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/issueSku");
		mav.addObject("issueSkuForm", issueSkuForm);
		mav.addObject("transactionFormMessage","Issued Successfully");
		return mav;
	
	}
	
	
}
