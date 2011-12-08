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


import com.smartworks.invtmgmt.converter.InventoryConverter;

import com.smartworks.invtmgmt.core.domain.Inventory;

import com.smartworks.invtmgmt.core.domain.Location;


import com.smartworks.invtmgmt.core.manager.InventoryManager;
import com.smartworks.invtmgmt.core.manager.InvtTransManager;
import com.smartworks.invtmgmt.web.ui.form.InventoryDetailsForm;

import com.smartworks.invtmgmt.web.ui.transfer.inventory.ReportDetailsResponse;
import com.smartworks.invtmgmt.web.ui.transfer.inventory.UIInventory;

@Controller
@RequestMapping("/Inventory")
public class AdjustInventoryController {
	@Autowired
	InventoryManager inventoryManager = null;
	
	@Autowired
	InvtTransManager invtTransMgr = null;
	

	@Autowired
	InventoryConverter inventoryConverter = null;
	
	protected static Logger logger = Logger
			.getLogger(InventoryDetailsController.class);

	@RequestMapping(value = "/AdjustInventory.form", method = RequestMethod.GET)
	public ModelAndView getAllInventory(HttpServletRequest request) {
		logger.error("Received request to show all inventory");
		ModelAndView mav = new ModelAndView("transaction/AdjustInventory");
		InventoryDetailsForm inventoryDetailsForm = new InventoryDetailsForm();
		mav.addObject("inventoryDetailsForm", inventoryDetailsForm);
		return mav;
	}

	@RequestMapping(value = "/getAllInventory.form", method = RequestMethod.GET)
	public @ResponseBody
	ReportDetailsResponse getAll() {
		ReportDetailsResponse response = new ReportDetailsResponse();
		List<Inventory> invtList = inventoryManager.getAllInventory();
		List<UIInventory> uiInvtList = new ArrayList<UIInventory>();	
		for (Inventory inventory : invtList) {
			uiInvtList.add(inventoryConverter.getUIInventory(inventory));			
		}
		response.setRows(uiInvtList);
		response.setPage("1");
		response.setTotal("10");
		response.setRecords(String.valueOf(uiInvtList.size()));
		return response;
	}
	/*
	@RequestMapping(value = "/getInventoryByLocn.form", method = RequestMethod.GET)
	public ModelAndView getInventoryByLocn(HttpServletRequest request) {
		logger.error("Received request to show inventory by locn");
		ModelAndView mav = new ModelAndView("transaction/AdjustInventoryByLocn");
		InventoryDetailsForm inventoryDetailsForm = new InventoryDetailsForm();
		mav.addObject("inventoryDetailsForm", inventoryDetailsForm);
		return mav;
	}
	
	@RequestMapping(value = "/adjustInventoryByLocn.form", method = RequestMethod.GET)
	public @ResponseBody
	ReportDetailsResponse getInvByLocation(@RequestParam Integer locationId){

		ReportDetailsResponse response = new ReportDetailsResponse();

		System.out.println("" + locationId);
		List<Inventory> invtList = inventoryManager.getInventoryByLocn(locationId);
		List<Inventory> invByLocList = new ArrayList<Inventory>();
		List<UIInventory> uiInvtList = new ArrayList<UIInventory>();	
		
		for (Inventory inventory : invtList) {
			
			uiInvtList.add(inventoryConverter.getUIInventory(inventory));			
		}
		response.setRows(uiInvtList);
		response.setPage("1");
		response.setTotal("10");
		response.setRecords(String.valueOf(uiInvtList.size()));
		return response;
	}
	*/
	@RequestMapping(value="/AdjustInventory.form", method=RequestMethod.POST)
	public ModelAndView handle(HttpServletRequest request, @ModelAttribute("inventoryDetailsForm") InventoryDetailsForm inventoryDetailsForm) {
	
	logger.info("Adjust Inventory - POST");
	Integer updatedQty = inventoryDetailsForm.getAvailableQty();
	Integer locnId = inventoryDetailsForm.getLocationId();
	String skuCode = inventoryDetailsForm.getSkuCode();
	Location location = new Location();
	location.setLocationId(locnId);
	inventoryManager.updateInventory(updatedQty, location, skuCode);
	
	ModelAndView mav = new ModelAndView("transaction/AdjustInventory");
	mav.addObject("inventoryDetailsForm", inventoryDetailsForm);
	return mav;
}
}