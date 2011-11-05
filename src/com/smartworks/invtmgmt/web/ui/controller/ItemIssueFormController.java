package com.smartworks.invtmgmt.web.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.smartworks.invtmgmt.core.dao.TransactionTypeDao;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.Trainee;
import com.smartworks.invtmgmt.core.domain.TransactionType;
import com.smartworks.invtmgmt.core.manager.InvtTransManager;
import com.smartworks.invtmgmt.core.manager.ItemMgr;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;
import com.smartworks.invtmgmt.web.ui.form.IssueSkuForm;

@Controller
@RequestMapping("/inventory")
public class ItemIssueFormController {
	@Autowired
	ItemMgr itemMgr = null;
	
	@Autowired
	TransactionTypeDao transactionTypeDao = null;
	
	@Autowired
	InvtTransManager invtTransMgr = null;
	
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
}
