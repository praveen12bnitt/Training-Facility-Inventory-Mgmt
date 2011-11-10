package com.smartworks.invtmgmt.web.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.converter.UIDomainConverter;
import com.smartworks.invtmgmt.core.dao.LocationDao;
import com.smartworks.invtmgmt.core.dao.TransactionTypeDao;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.exception.InventoryAllocationException;
import com.smartworks.invtmgmt.core.manager.InvtTransManager;
import com.smartworks.invtmgmt.core.manager.ItemMgr;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;
import com.smartworks.invtmgmt.web.ui.form.IssueSkuForm;


@Controller
@RequestMapping("/inbound")
public class InboundFormController {
	
	@Autowired
	ItemMgr itemMgr = null;
	@Autowired
	TransactionTypeDao transactionTypeDao = null;
	@Autowired
	InvtTransManager invtTransMgr = null;
	@Autowired
	LocationDao locDao = null;
  
	@RequestMapping(value="/transfer.form", method=RequestMethod.GET)
	public ModelAndView displayTransaction() {
		List<Item> items = itemMgr.getAllItems();
		
		IssueSkuForm issueSkuForm = new IssueSkuForm();		
		issueSkuForm.setLocationId(1);
		issueSkuForm.setTransactionType(TransactionTypeEnum.TRANSFER_INVENTORY);		
		issueSkuForm.setItems(items);
				
        ModelAndView mav = new ModelAndView("transaction/InvMovement");
        mav.addObject("issueSkuForm", issueSkuForm);
        
		List<Location> listLocations = locDao.loadAll();
		mav.addObject("locationList", listLocations);
		return mav;
	}
	
	
	@RequestMapping(value="/receive.form", method=RequestMethod.GET)
	public ModelAndView displayInventory(HttpServletRequest request, HttpServletResponse response) {
		
		List<Item> items = itemMgr.getAllItems();
		
		IssueSkuForm issueSkuForm = new IssueSkuForm();		
		issueSkuForm.setTransactionType(TransactionTypeEnum.TRANSFER_INVENTORY);		
		issueSkuForm.setItems(items);
				
        ModelAndView mav = new ModelAndView("transaction/ReceiveInventory");
        mav.addObject("issueSkuForm", issueSkuForm);
        
		return mav;
	}
	@RequestMapping(value="/transferToMW.form", method=RequestMethod.GET)
	public ModelAndView displayInventoryToMW(HttpServletRequest request, HttpServletResponse response) {
		List<Item> items = itemMgr.getAllItems();
		
		IssueSkuForm issueSkuForm = new IssueSkuForm();		
		issueSkuForm.setTransactionType(TransactionTypeEnum.TRANSFER_INVENTORY);		
		issueSkuForm.setItems(items);
				
        ModelAndView mav = new ModelAndView("transaction/InvTransferToMW");
        mav.addObject("issueSkuForm", issueSkuForm);
        List<Location> listLocations = locDao.loadAll();
		mav.addObject("locationList", listLocations);
        
		return mav;
	}	
	
	
	@RequestMapping(value="/outbound.form", method=RequestMethod.GET)
	public ModelAndView displayInventoryToOutbound(HttpServletRequest request, HttpServletResponse response) {
		List<Item> items = itemMgr.getAllItems();
		
		IssueSkuForm issueSkuForm = new IssueSkuForm();		
		issueSkuForm.setTransactionType(TransactionTypeEnum.TRANSFER_INVENTORY);		
		issueSkuForm.setItems(items);
				
        ModelAndView mav = new ModelAndView("transaction/OutboundInventory");
        mav.addObject("issueSkuForm", issueSkuForm);
        
		return mav;
	}
	@RequestMapping(value="/transfer.form", method=RequestMethod.POST)
	public ModelAndView transferSKU(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm) 
	{
		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(issueSkuForm);
		transDetailsHolder.setSrcLocationId(4);
		InventoryAllocationException ex = null;
		try {
			invtTransMgr.processInventoryChange(transDetailsHolder);
		} catch(InventoryAllocationException iae) {
			ex = iae;
		}		
		
		List<Item> items =   itemMgr.getAllItems();
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/InvMovement");
		List<Location> listLocations = locDao.loadAll();
		mav.addObject("locationList", listLocations);
		mav.addObject("issueSkuForm", issueSkuForm);
		if(ex != null) {
			mav.addObject("exception", ex);
		} else {
			mav.addObject("success", "success");
		}
		mav.addObject("transactionFormMessage","Issued Successfully");
		return mav;
	}
	
	
	@RequestMapping(value="/transferToMW.form", method=RequestMethod.POST)
	public ModelAndView transferToMW(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm) 
	{
		
		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(issueSkuForm);
		transDetailsHolder.setLocationId(4);
		transDetailsHolder.setSrcLocationId(issueSkuForm.getLocationId());
		InventoryAllocationException ex = null;
		try {
			invtTransMgr.processInventoryChange(transDetailsHolder);
		} catch (InventoryAllocationException iae) {
			ex = iae;
		}
				
		List<Item> items =   itemMgr.getAllItems();
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/InvTransferToMW");
		mav.addObject("issueSkuForm", issueSkuForm);
		List<Location> listLocations = locDao.loadAll();
		mav.addObject("locationList", listLocations);
		if(ex != null) {
			mav.addObject("exception", ex);
		} else {
			mav.addObject("success", "success");
		}
		mav.addObject("transactionFormMessage","Issued Successfully");
		return mav;

	}
	
	
	@RequestMapping(value="/receive.form",method=RequestMethod.POST)
	public ModelAndView receiveInventory(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm) 
	{
		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(issueSkuForm);
		transDetailsHolder.setSrcLocationId(-1);
		transDetailsHolder.setLocationId(4);
		
		InventoryAllocationException ex = null;
		try {
			invtTransMgr.processInventoryChange(transDetailsHolder);
		} catch (InventoryAllocationException iae) {
			ex = iae;
		}		
		
		List<Item> items =   itemMgr.getAllItems();
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/ReceiveInventory");
		mav.addObject("issueSkuForm", issueSkuForm);
		if(ex != null) {
			mav.addObject("exception", ex);
		} else {
			mav.addObject("success", "success");
		}
		mav.addObject("transactionFormMessage","Issued Successfully");
		return mav;
	}
	

	@RequestMapping(value="/outbound.form",method=RequestMethod.POST)
	public ModelAndView outboundInventory(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm) 
	{
		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(issueSkuForm);
		transDetailsHolder.setSrcLocationId(4);
		transDetailsHolder.setLocationId(-1); //To Vendor
		InventoryAllocationException ex = null;
		try {
			invtTransMgr.processInventoryChange(transDetailsHolder);
		} catch (InventoryAllocationException iae) {
			ex = iae;
		}		
		List<Item> items =   itemMgr.getAllItems();
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/OutboundInventory");
		mav.addObject("issueSkuForm", issueSkuForm);
		if(ex != null) {
			mav.addObject("exception", ex);
		} else {
			mav.addObject("success", "success");
		}
		mav.addObject("transactionFormMessage","Issued Successfully");
		return mav;
	}


}