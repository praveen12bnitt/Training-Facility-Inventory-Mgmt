package com.smartworks.invtmgmt.web.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartworks.invtmgmt.converter.InventoryConverter;
import com.smartworks.invtmgmt.core.domain.Inventory;
import com.smartworks.invtmgmt.core.manager.InventoryManager;
import com.smartworks.invtmgmt.web.ui.transfer.inventory.InventoryDetailsResponse;
import com.smartworks.invtmgmt.web.ui.transfer.inventory.UIInventory;

@Controller
@RequestMapping("/reports")
public class InventoryDetailsController {
	@Autowired
	InventoryManager inventoryManager;

	@Autowired
	InventoryConverter inventoryConverter;

	protected static Logger logger = Logger
			.getLogger(InventoryDetailsController.class);

	@RequestMapping(value = "/inventory-all.form", method = RequestMethod.GET)
	public ModelAndView getAllInventory() {
		logger.error("Received request to show all inventory");
		ModelAndView mav = new ModelAndView("reports/inventory-all");
		return mav;
	}

	@RequestMapping(value = "/allinvt.form", method = RequestMethod.GET)
	public @ResponseBody
	InventoryDetailsResponse getAll() {
		InventoryDetailsResponse response = new InventoryDetailsResponse();

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

}
