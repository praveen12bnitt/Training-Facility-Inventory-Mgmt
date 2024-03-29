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
import com.smartworks.invtmgmt.core.dao.impl.ItemDaoImpl;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.Staff;
import com.smartworks.invtmgmt.core.domain.Trainee;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.domain.TransactionType;
import com.smartworks.invtmgmt.core.exception.InventoryAllocationException;
import com.smartworks.invtmgmt.core.manager.InvtTransManager;
import com.smartworks.invtmgmt.core.manager.ItemMgr;
import com.smartworks.invtmgmt.core.service.PreissueService;
import com.smartworks.invtmgmt.core.service.VelocityTemplateUtil;
import com.smartworks.invtmgmt.core.transaction.ReasonCodeEnum;
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

	@Autowired
	TraineeDao traineeDao = null;

	@Autowired
	StaffDao staffDao = null;

	@Autowired
	LocationDao locationDao = null;

	@Autowired
	private ItemDaoImpl itemDao;

	@Autowired
	private PreissueService preissueService;

	protected static Logger logger = Logger.getLogger(ItemIssueFormController.class);

	@RequestMapping(value = "/preissue.form", method = RequestMethod.GET)
	public ModelAndView showPreIssue(HttpServletRequest request, HttpServletResponse response, @RequestParam TransactionTypeEnum transactionTypeEnum,
			@RequestParam Integer locationId) {
		TransactionType transactionType = transactionTypeDao.load(transactionTypeEnum);

		Location location = locationDao.load(locationId);
		IssueSkuForm issueSkuForm = new IssueSkuForm();
		issueSkuForm.setLocationId(location.getLocationId());
		issueSkuForm.setLocationName(location.getLocationName());
		issueSkuForm.setTransactionDescription(transactionType.getTransactionDesc());
		issueSkuForm.setTransactionType(transactionTypeEnum);

		List<String> itemNames = itemDao.getItemNames();
		issueSkuForm.setItemNames(itemNames);

		ModelAndView mav = new ModelAndView("transaction/preIssue");
		mav.addObject("issueSkuForm", issueSkuForm);
		return mav;
	}

	@RequestMapping(value = "/preissue.form", method = RequestMethod.POST)
	public ModelAndView savePreissue(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm)
			throws IOException {

		logger.info("process Incevntory change");
		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(issueSkuForm);

		InventoryAllocationException ex = null;
		try {
			invtTransMgr.processInventoryChange(transDetailsHolder);
		} catch (InventoryAllocationException iae) {
			ex = iae;
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			return null;

		}

		TransactionType transactionType = transactionTypeDao.load(issueSkuForm.getTransactionType());
		List<Item> items = itemMgr.getItemsForTransaction(transactionType);
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/issueSku");
		mav.addObject("issueSkuForm", issueSkuForm);
		mav.addObject("transactionFormMessage", "Issued Successfully");
		return mav;
	}

	@RequestMapping(value = "/preIssueEdit.form", method = RequestMethod.GET)
	public ModelAndView preIssueEdit(HttpServletRequest request, HttpServletResponse response, @RequestParam int transactionId) {

		TransactionDetailsHolder transDetails = invtTransMgr.getTransDetails(transactionId);
		if(!transDetails.getTransactionType().isOfTypePreissue()) 
		{
			throw new RuntimeException("Transaction is not in PRE_ISSUE state");
		}
		Trainee trainee = null;
		Staff staff = null;
		if (transDetails.getTransactionType().isStaffTransaction()) {
			staff = staffDao.load(transDetails.getStaffId());
		} else {
			trainee = traineeDao.load(transDetails.getTraineeId());
		}

		logger.info(transDetails.getItemSkus());
		IssueSkuForm issueSkuForm = new IssueSkuForm();
		TransactionTypeEnum transactionTypeEnum = transDetails.getTransactionType();
		issueSkuForm.setTransactionType(transactionTypeEnum);
		TransactionType transactionType = transactionTypeDao.load(transactionTypeEnum);
		issueSkuForm.setTransactionDescription(transactionType.getTransactionDesc());
		if (transDetails.getTransactionType().isStaffTransaction()) {
			issueSkuForm.setStaff(staff);
		} else {
			issueSkuForm.setTrainee(trainee);
		}

		issueSkuForm.setRefTransactionId(transactionId);
		issueSkuForm.setLocationId(transDetails.getLocationId());

		List<String> itemNames = itemDao.getItemNames();
		issueSkuForm.setItemNames(itemNames);

		ModelAndView mav = new ModelAndView("transaction/preIssueEdit");
		mav.addObject("transDetails", transDetails);
		mav.addObject("issueSkuForm", issueSkuForm);
		mav.addObject("targetPage","preIssueEdit.form");
		
		return mav;
	}

	@RequestMapping(value = "/preIssueEdit.form", method = RequestMethod.POST)
	public ModelAndView updatePreIssue(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm)
			throws IOException {
		InventoryAllocationException ex = null;
		try {
			preissueService.updatePreIssue(issueSkuForm);
		} catch (InventoryAllocationException iae) {
			ex = iae;
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			return null;
		}
		return preIssueEdit(request, response, issueSkuForm.getRefTransactionId());
	}
	
	@RequestMapping(value = "/issueSkuFromPreIssue.form", method = RequestMethod.GET)
	public ModelAndView issueFromPreIssueSave(HttpServletRequest request, HttpServletResponse response, @RequestParam int transactionId) {

		TransactionDetailsHolder transDetails = invtTransMgr.getTransDetails(transactionId);
		Trainee trainee = null;
		Staff staff = null;
		if (transDetails.getTransactionType().isStaffTransaction()) {
			staff = staffDao.load(transDetails.getStaffId());
		} else {
			trainee = traineeDao.load(transDetails.getTraineeId());
		}

		logger.info(transDetails.getItemSkus());
		IssueSkuForm issueSkuForm = new IssueSkuForm();
		TransactionTypeEnum transactionTypeEnum = transDetails.getTransactionType().getIssueTrans();
		issueSkuForm.setTransactionType(transactionTypeEnum);
		TransactionType transactionType = transactionTypeDao.load(transactionTypeEnum);
		issueSkuForm.setTransactionDescription(transactionType.getTransactionDesc());
		if (transDetails.getTransactionType().isStaffTransaction()) {
			issueSkuForm.setStaff(staff);
		} else {
			issueSkuForm.setTrainee(trainee);
		} 		
		issueSkuForm.setLocationId(transDetails.getLocationId());
		issueSkuForm.setRefTransactionId(transactionId);

		List<String> itemNames = itemDao.getItemNames();
		issueSkuForm.setItemNames(itemNames);

		ModelAndView mav = new ModelAndView("transaction/preIssueEdit");
		mav.addObject("transDetails", transDetails);
		mav.addObject("issueFromPreIssue", true);
		mav.addObject("issueSkuForm", issueSkuForm);
		mav.addObject("targetPage","issue.form");
		
		return mav;
	}
	
	@RequestMapping(value = "/issue.form", method = RequestMethod.GET)
	public ModelAndView displayTransaction(HttpServletRequest request, HttpServletResponse response, @RequestParam TransactionTypeEnum transactionTypeEnum,
			@RequestParam Integer locationId) {
		TransactionType transactionType = transactionTypeDao.load(transactionTypeEnum);

		Location location = locationDao.load(locationId);
		IssueSkuForm issueSkuForm = new IssueSkuForm();
		issueSkuForm.setLocationId(location.getLocationId());
		issueSkuForm.setLocationName(location.getLocationName());
		issueSkuForm.setTransactionDescription(transactionType.getTransactionDesc());
		issueSkuForm.setTransactionType(transactionTypeEnum);

		List<String> itemNames = itemDao.getItemNames();
		issueSkuForm.setItemNames(itemNames);

		ModelAndView mav = new ModelAndView("transaction/issueSku");
		mav.addObject("issueSkuForm", issueSkuForm);
		return mav;
	}

	@RequestMapping(value = "/issue.form", method = RequestMethod.POST)
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm)
			throws IOException {

		logger.info("process Incevntory change");
		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(issueSkuForm);

		InventoryAllocationException ex = null;
		try {
			invtTransMgr.processInventoryChange(transDetailsHolder);
		} catch (InventoryAllocationException iae) {
			ex = iae;
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			return null;

		}

		TransactionType transactionType = transactionTypeDao.load(issueSkuForm.getTransactionType());
		List<Item> items = itemMgr.getItemsForTransaction(transactionType);
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/issueSku");
		mav.addObject("issueSkuForm", issueSkuForm);
		mav.addObject("transactionFormMessage", "Issued Successfully");
		return mav;
	}

	@RequestMapping(value = "/listopentrans.form", method = RequestMethod.GET)
	public ModelAndView openReceive(@RequestParam TransactionTypeEnum transactionTypeEnum, @RequestParam int locationId) {
		return openTransactionsUI(transactionTypeEnum, locationId, "receive.form");
	}

	@RequestMapping(value = "/listopentrans-exchange.form", method = RequestMethod.GET)
	public ModelAndView listOpenTrans(@RequestParam TransactionTypeEnum transactionTypeEnum, @RequestParam int locationId) {
		return openTransactionsUI(transactionTypeEnum, locationId, "exchange.form");
	}
	
	@RequestMapping(value = "/listopentrans-preissue.form", method = RequestMethod.GET)
	public ModelAndView listOpenTransPreIssue(@RequestParam TransactionTypeEnum transactionTypeEnum, @RequestParam int locationId) {
		return openTransactionsUI(transactionTypeEnum, locationId, "issueSkuFromPreIssue.form");
	}
	
	@RequestMapping(value = "/edit-preissues.form", method = RequestMethod.GET)
	public ModelAndView editPreIssues(@RequestParam TransactionTypeEnum transactionTypeEnum, @RequestParam int locationId) {
		return openTransactionsUI(transactionTypeEnum, locationId, "preIssueEdit.form");
	}
	
	@RequestMapping(value = "/show-issues.form", method = RequestMethod.GET)
	public  ModelAndView showIssue (@RequestParam TransactionTypeEnum transactionTypeEnum, @RequestParam int locationId) {
		return openTransactionsUI(transactionTypeEnum, locationId, "show-issue.form");
	}
	
	public ModelAndView openTransactionsUI(@RequestParam TransactionTypeEnum transactionTypeEnum, @RequestParam int locationId, String targetForm)
	{
		logger.info("Received request to show all received transactions");
		ModelAndView mav = new ModelAndView("transaction/opentransactions");
		mav.addObject("transactionTypeEnum", transactionTypeEnum);
		TransactionType returnTrans = transactionTypeDao.load(TransactionTypeEnum.getReturnTransaction(transactionTypeEnum));
		mav.addObject("transactionDetail", returnTrans.getTransactionDesc());
		mav.addObject("locationId", locationId);
		mav.addObject("targetForm", targetForm);
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
		if (transactionTypeEnum.isStaffTransaction()) {
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
	
	
	@RequestMapping(value = "/show-issue.form", method = RequestMethod.GET)
	public ModelAndView displayReadOnlyTransaction(HttpServletRequest request, HttpServletResponse response, @RequestParam int transactionId) {
		return displayTransaction(request, response, transactionId, true);
	}
	
	@RequestMapping(value = "/receive.form", method = RequestMethod.GET)
	public ModelAndView displayReceiveTransaction(HttpServletRequest request, HttpServletResponse response, @RequestParam int transactionId) {
		return displayTransaction(request, response, transactionId, false);
	}	
	
	public ModelAndView displayTransaction(HttpServletRequest request, HttpServletResponse response, @RequestParam int transactionId, Boolean readonly) {

		TransactionDetailsHolder transDetails = invtTransMgr.getTransDetails(transactionId);
		Trainee trainee = null;
		Staff staff = null;
		if (transDetails.getTransactionType().isStaffTransaction()) {
			staff = staffDao.load(transDetails.getStaffId());
		} else {
			trainee = traineeDao.load(transDetails.getTraineeId());
		}

		logger.info(transDetails.getItemSkus());
		IssueSkuForm issueSkuForm = new IssueSkuForm();
		TransactionTypeEnum transactionTypeEnum = TransactionTypeEnum.getReturnTransaction(transDetails.getTransactionType());
		issueSkuForm.setTransactionType(transactionTypeEnum);
		TransactionType transactionType = transactionTypeDao.load(transactionTypeEnum);
		issueSkuForm.setTransactionDescription(transactionType.getTransactionDesc());
		if (transDetails.getTransactionType().isStaffTransaction()) {
			issueSkuForm.setStaff(staff);
		} else {
			issueSkuForm.setTrainee(trainee);
		}

		issueSkuForm.setRefTransactionId(transactionId);
		issueSkuForm.setLocationId(transDetails.getLocationId());
		issueSkuForm.setUserSign(transDetails.getUserSign());

		// Reason Code
		List<String> reasonCodeList = ReasonCodeEnum.getReasonCodeList();

		ModelAndView mav = new ModelAndView("transaction/receiveSku");
		mav.addObject("transDetails", transDetails);
		mav.addObject("issueSkuForm", issueSkuForm);
		mav.addObject("readOnly", readonly.booleanValue());
		mav.addObject("reasonCodeList", reasonCodeList);
		return mav;
	}

	@RequestMapping(value = "/receive.form", method = RequestMethod.POST)
	public ModelAndView receiveInventory(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm)
			throws IOException {

		logger.info("process Incevntory change");
		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(issueSkuForm);
		TransactionDetailsHolder missingTransDetailsHolder = UIDomainConverter.getMissingTransactionDetailsHolder(issueSkuForm);

		InventoryAllocationException ex = null;
		try {
			invtTransMgr.processInventoryChange(transDetailsHolder);
			if (missingTransDetailsHolder != null) {
				invtTransMgr.processInventoryChange(missingTransDetailsHolder);
			}
		} catch (InventoryAllocationException iae) {
			ex = iae;
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			return null;
		}

		TransactionType transactionType = transactionTypeDao.load(issueSkuForm.getTransactionType());
		List<Item> items = itemMgr.getItemsForTransaction(transactionType);
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/issueSku");
		mav.addObject("issueSkuForm", issueSkuForm);
		mav.addObject("transactionFormMessage", "Issued Successfully");
		return mav;

	}

	@RequestMapping(value = "/exchange.form", method = RequestMethod.GET)
	public ModelAndView displayExchangeTrans(HttpServletRequest request, HttpServletResponse response, @RequestParam int transactionId) {

		TransactionDetailsHolder transDetails = invtTransMgr.getTransDetailsForExhange(transactionId);
		Trainee trainee = null;
		Staff staff = null;
		if (transDetails.getTransactionType().isStaffTransaction()) {
			staff = staffDao.load(transDetails.getStaffId());
		} else {
			trainee = traineeDao.load(transDetails.getTraineeId());
		}

		logger.info(transDetails.getItemSkus());
		IssueSkuForm issueSkuForm = new IssueSkuForm();
		TransactionTypeEnum transactionTypeEnum = TransactionTypeEnum.getExchangeTransaction(transDetails.getTransactionType());
		issueSkuForm.setTransactionType(transactionTypeEnum);
		TransactionType transactionType = transactionTypeDao.load(transactionTypeEnum);
		issueSkuForm.setTransactionDescription(transactionType.getTransactionDesc());
		if (transDetails.getTransactionType().isStaffTransaction()) {
			issueSkuForm.setStaff(staff);
		} else {
			issueSkuForm.setTrainee(trainee);
		}

		issueSkuForm.setRefTransactionId(transactionId);
		issueSkuForm.setLocationId(transDetails.getLocationId());

		ModelAndView mav = new ModelAndView("transaction/exchangeSku");
		mav.addObject("transDetails", transDetails);
		mav.addObject("issueSkuForm", issueSkuForm);
		return mav;
	}

	@RequestMapping(value = "/exchange.form", method = RequestMethod.POST)
	public ModelAndView displayExchangeTrans(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm)
			throws IOException {

		logger.info("Process inventory exchange");

		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(issueSkuForm);

		try {
			invtTransMgr.processInventoryChange(transDetailsHolder);
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			return null;
		}

		TransactionType transactionType = transactionTypeDao.load(issueSkuForm.getTransactionType());
		List<Item> items = itemMgr.getItemsForTransaction(transactionType);
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/issueSku");
		mav.addObject("issueSkuForm", issueSkuForm);
		mav.addObject("transactionFormMessage", "Issued Successfully");
		return mav;

	}

	@RequestMapping(value = "/itemHtmlEl.form", method = RequestMethod.GET)
	public @ResponseBody
	String getItemHtmlData(HttpServletRequest request, @RequestParam String itemName, @RequestParam Integer rowNum, String itemNumber) {
		List<Item> items = null;
		if(itemNumber != null && !itemNumber.isEmpty() && !itemNumber.trim().isEmpty()) {
			 items = itemMgr.getItemsByNumber(itemNumber);
		} else {

		 items = itemMgr.getItemsByName(itemName);
		}
		// Convert this object into html el data
		String htmlData = getTemplateData(request, items, rowNum);
		return htmlData;
	}

	@RequestMapping(value = "/loadProductItems.form", method = RequestMethod.GET)
	public @ResponseBody
	String loadProductItems(HttpServletRequest request, @RequestParam Integer productId, @RequestParam Integer rowNum) {
		List<Item> items = itemMgr.getItemsByProductId(productId);
		String htmlData = getTemplateData(request, items, rowNum);
		return htmlData;
	}

	@RequestMapping(value = "/exchange-html-data.form", method = RequestMethod.GET)
	public @ResponseBody
	String getExchangeItemHtmlData(@RequestParam Integer itemId, Integer exchagneItemIndex) {
		Item item = itemMgr.getItem(itemId);
		if (item == null) {
			logger.error("No item with id " + itemId + "in the db");
			return "";
		}
		return getItemExchangeRowData(item, exchagneItemIndex);
	}

	@RequestMapping(value = "/receive-laundry.form", method = RequestMethod.GET)
	public ModelAndView displayInventory(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer locationId) {
		Location location = locationDao.load(locationId);
		IssueSkuForm issueSkuForm = new IssueSkuForm();
		issueSkuForm.setLocationId(location.getLocationId());
		issueSkuForm.setLocationName(location.getLocationName());
		issueSkuForm.setTransactionType(TransactionTypeEnum.getLaundryReturnTrans(locationId));

		List<String> itemNames = itemDao.getItemNames();
		issueSkuForm.setItemNames(itemNames);

		ModelAndView mav = new ModelAndView("transaction/receive-from-laundry");
		mav.addObject("issueSkuForm", issueSkuForm);
		return mav;
	}

	@RequestMapping(value = "/receive-laundry.form", method = RequestMethod.POST)
	public ModelAndView receiveInventoryFromLaundry(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm) throws IOException {

		logger.info("process Incevntory change");
		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(issueSkuForm);
		transDetailsHolder.setSrcLocationId(5);
		InventoryAllocationException ex = null;
		try {
			invtTransMgr.processInventoryChange(transDetailsHolder);
		} catch (InventoryAllocationException iae) {
			ex = iae;
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			return null;
		}

		TransactionType transactionType = transactionTypeDao.load(issueSkuForm.getTransactionType());
		List<Item> items = itemMgr.getItemsForTransaction(transactionType);
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/receive-from-laundry");
		mav.addObject("issueSkuForm", issueSkuForm);
		mav.addObject("transactionFormMessage", "Issued Successfully");
		return mav;

	}

	private String getTemplateData(HttpServletRequest request, List<Item> items, Integer rowNum) {
		String htmlData = null;
		VelocityEngine ve = VelocityTemplateUtil.getVelocityEngine();
		Template t = ve.getTemplate("com/smartworks/invtmgmt/web/ui/template/itemtablerow.vm");
		VelocityContext context = new VelocityContext();
		context.put("items", items);
		context.put("rowNum", rowNum);
		context.put("contextUrl", request.getContextPath());
		htmlData = VelocityTemplateUtil.getData(context, t);
		return htmlData;
	}

	private String getItemExchangeRowData(Item item, Integer index) {
		String htmlData = null;
		VelocityEngine ve = VelocityTemplateUtil.getVelocityEngine();
		Template t = ve.getTemplate("com/smartworks/invtmgmt/web/ui/template/exchangeitemtablerow.vm");
		VelocityContext context = new VelocityContext();
		context.put("item", item);
		context.put("index", index);
		htmlData = VelocityTemplateUtil.getData(context, t);
		return htmlData;
	}

}
