package com.smartworks.invtmgmt.web.ui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
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
import com.smartworks.invtmgmt.core.dao.LocationDao;
import com.smartworks.invtmgmt.core.dao.StaffDao;
import com.smartworks.invtmgmt.core.dao.TraineeDao;
import com.smartworks.invtmgmt.core.dao.TransactionTypeDao;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.Staff;
import com.smartworks.invtmgmt.core.domain.Trainee;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.domain.TransactionType;
import com.smartworks.invtmgmt.core.exception.InventoryAllocationException;
import com.smartworks.invtmgmt.core.manager.InvtTransManager;
import com.smartworks.invtmgmt.core.manager.ItemMgr;
import com.smartworks.invtmgmt.core.transaction.ReasonCodeEnum;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;
import com.smartworks.invtmgmt.core.util.VelocityTemplateUtil;
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
	
	@Autowired
	TraineeDao traineeDao = null;
    
	@Autowired
	StaffDao staffDao = null;
	
	@Autowired
	LocationDao locationDao = null;

	
	protected static Logger logger = Logger
			.getLogger(ItemIssueFormController.class);

	
	@RequestMapping(value="/issue.form", method=RequestMethod.GET)
	public ModelAndView displayTransaction(HttpServletRequest request, HttpServletResponse response, @RequestParam TransactionTypeEnum transactionTypeEnum, 
			@RequestParam Integer locationId) {		
		TransactionType transactionType = transactionTypeDao.load(transactionTypeEnum);		
		List<Item> items =   itemMgr.getItemsForTransaction(transactionType);
		Location location = locationDao.load(locationId);
		IssueSkuForm issueSkuForm = new IssueSkuForm();		
		issueSkuForm.setLocationId(location.getLocationId());
		issueSkuForm.setLocationName(location.getLocationName());
		issueSkuForm.setTransactionDescription(transactionType.getTransactionDesc());
		issueSkuForm.setTransactionType(transactionTypeEnum);		
		issueSkuForm.setItems(items);		
		ModelAndView mav = new ModelAndView("transaction/issueSku");
		mav.addObject("issueSkuForm", issueSkuForm);
		return mav;
	}
	
	@RequestMapping(value="/issue.form", method=RequestMethod.POST)
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm) throws IOException {
		
		logger.info("process Incevntory change");
		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(issueSkuForm);
		
		InventoryAllocationException ex = null;
		try {
			invtTransMgr.processInventoryChange(transDetailsHolder);
		} catch(InventoryAllocationException iae) {
			ex = iae;
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			return null;
			
		}		
		
		TransactionType transactionType = transactionTypeDao.load(issueSkuForm.getTransactionType());
		List<Item> items =   itemMgr.getItemsForTransaction(transactionType);
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/issueSku");
		mav.addObject("issueSkuForm", issueSkuForm);
		mav.addObject("transactionFormMessage","Issued Successfully");
		return mav;
	}
	
	
	@RequestMapping(value = "/listopentrans.form", method = RequestMethod.GET)
	public ModelAndView openReceive(@RequestParam TransactionTypeEnum transactionTypeEnum,@RequestParam int locationId) {
		logger.info("Received request to show all received transactions");
		ModelAndView mav = new ModelAndView("transaction/opentransactions");
		mav.addObject("transactionTypeEnum",transactionTypeEnum);
		TransactionType returnTrans = transactionTypeDao.load(TransactionTypeEnum.getReturnTransaction(transactionTypeEnum));
		mav.addObject("transactionDetail",returnTrans.getTransactionDesc());
		mav.addObject("locationId",locationId);
		return mav;
	}

	
	@RequestMapping(value = "/opentransactions.form", method = RequestMethod.GET)
	public @ResponseBody
	ReportDetailsResponse getAllOpenTransactions(HttpServletRequest request, @RequestParam int traineeStaffId,
			@RequestParam TransactionTypeEnum transactionTypeEnum, @RequestParam int locationId) {
		logger.info("getAllOpenTransactions for user<>");
		String contextPath = request.getContextPath();
		ReportDetailsResponse response = new ReportDetailsResponse();
		List<TransactionTrace> transTraceList = null;
		if(transactionTypeEnum.isStaffTransaction()) {
			transTraceList = invtTransMgr.getOpenTransactionsForStaff(locationId, traineeStaffId, transactionTypeEnum);
		} else {
			transTraceList = invtTransMgr.getOpenTransactionsForUser(locationId, traineeStaffId, transactionTypeEnum);
		}		
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
		Trainee trainee = null;
		Staff staff = null;
		if(transDetails.getTransactionType().isStaffTransaction()) {
			staff = staffDao.load(transDetails.getStaffId());
		} else  {
			trainee = traineeDao.load(transDetails.getTraineeId());
		}
		
		logger.info(transDetails.getItemSkus());
		IssueSkuForm issueSkuForm = new IssueSkuForm();
		TransactionTypeEnum transactionTypeEnum = TransactionTypeEnum.getReturnTransaction(transDetails.getTransactionType());
		issueSkuForm.setTransactionType(transactionTypeEnum);
		TransactionType transactionType = transactionTypeDao.load(transactionTypeEnum);
		issueSkuForm.setTransactionDescription(transactionType.getTransactionDesc());
		if(transDetails.getTransactionType().isStaffTransaction()) {
			issueSkuForm.setStaff(staff);
		} else {
			issueSkuForm.setTrainee(trainee);
		}
		
		issueSkuForm.setRefTransactionId(transactionId);
		issueSkuForm.setLocationId(transDetails.getLocationId());
		
		//Reason Code
		List<String> reasonCodeList = ReasonCodeEnum.getReasonCodeList();
		
		ModelAndView mav = new ModelAndView("transaction/receiveSku");
		mav.addObject("transDetails", transDetails);
		mav.addObject("issueSkuForm", issueSkuForm);
		mav.addObject("reasonCodeList",reasonCodeList);
		return mav;
	}
	
	
	@RequestMapping(value="/receive.form", method=RequestMethod.POST)
	public ModelAndView receiveInventory(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm) throws IOException {		
		
		logger.info("process Incevntory change");
		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(issueSkuForm);
		TransactionDetailsHolder missingTransDetailsHolder = UIDomainConverter.getMissingTransactionDetailsHolder(issueSkuForm);
		
		InventoryAllocationException ex = null;
		try {
			invtTransMgr.processInventoryChange(transDetailsHolder);
			if(missingTransDetailsHolder != null) {
				invtTransMgr.processInventoryChange(missingTransDetailsHolder);
			}
		} catch(InventoryAllocationException iae) {
			ex = iae;
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			return null;
		}		
		
		TransactionType transactionType = transactionTypeDao.load(issueSkuForm.getTransactionType());
		List<Item> items =   itemMgr.getItemsForTransaction(transactionType);
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/issueSku");
		mav.addObject("issueSkuForm", issueSkuForm);
		mav.addObject("transactionFormMessage","Issued Successfully");
		return mav;
	
	}
	
	@RequestMapping(value = "/itemHtmlEl.form", method = RequestMethod.GET)
	public @ResponseBody String getItemHtmlData(HttpServletRequest request, @RequestParam String itemName,@RequestParam Integer rowNum) {
			
		List<Item> items = itemMgr.getItemsByName(itemName);
		// Convert this object into html el data
		String htmlData = getTemplateData(request, items, rowNum);
		return htmlData;
	}
	
	
	@RequestMapping(value="/loadProductItems.form", method=RequestMethod.GET)	
	public @ResponseBody String loadProductItems(HttpServletRequest request,@RequestParam Integer productId, @RequestParam Integer rowNum) {
		
		List<Item> items = itemMgr.getItemsByProductId(productId);
		
		String htmlData = getTemplateData(request, items, rowNum);
		return htmlData;
	}
	
	
	@RequestMapping(value="/receive-laundry.form", method=RequestMethod.GET)
	public ModelAndView displayInventory(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer locationId) {	
		Location location = locationDao.load(locationId);
		List<Item> items = new ArrayList<Item>();
		IssueSkuForm issueSkuForm = new IssueSkuForm();	
		issueSkuForm.setLocationId(location.getLocationId());
		issueSkuForm.setLocationName(location.getLocationName());		
		issueSkuForm.setTransactionType(TransactionTypeEnum.getLaundryReturnTrans(locationId));		
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/receive-from-laundry");
        mav.addObject("issueSkuForm", issueSkuForm);       
		return mav;
	}
	
	@RequestMapping(value="/receive-laundry.form", method=RequestMethod.POST)
	public ModelAndView receiveInventoryFromLaundry(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm) throws IOException {
		
		logger.info("process Incevntory change");
		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(issueSkuForm);
		transDetailsHolder.setSrcLocationId(5);
		InventoryAllocationException ex = null;
		try {
			invtTransMgr.processInventoryChange(transDetailsHolder);
		} catch(InventoryAllocationException iae) {
			ex = iae;
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			return null;
		}		
		
		TransactionType transactionType = transactionTypeDao.load(issueSkuForm.getTransactionType());
		List<Item> items =   itemMgr.getItemsForTransaction(transactionType);
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/receive-from-laundry");
		mav.addObject("issueSkuForm", issueSkuForm);
		mav.addObject("transactionFormMessage","Issued Successfully");
		return mav;
	
	}
	
	
	
	
	private String getTemplateData(HttpServletRequest request, List<Item> items, Integer rowNum) {
		String htmlData = null;	
		VelocityEngine ve = VelocityTemplateUtil.getVelocityEngine();
		Template t = ve.getTemplate( "com/smartworks/invtmgmt/web/ui/template/itemtablerow.vm" );
		VelocityContext context = new VelocityContext();
        context.put("items", items);
        context.put("rowNum", rowNum); 
        context.put("contextUrl", request.getContextPath());        
        htmlData = VelocityTemplateUtil.getData(context, t);
		return htmlData;
	}
	
	
}
