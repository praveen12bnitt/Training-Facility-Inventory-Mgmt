package com.smartworks.invtmgmt.web.ui.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartworks.invtmgmt.converter.InventoryConverter;
import com.smartworks.invtmgmt.converter.LaundryLoadConverter;
import com.smartworks.invtmgmt.core.domain.Inventory;
import com.smartworks.invtmgmt.core.domain.LaundryTracking;
import com.smartworks.invtmgmt.core.manager.InventoryManager;
import com.smartworks.invtmgmt.core.manager.InvtTransManager;
import com.smartworks.invtmgmt.core.manager.LaundryMgr;
import com.smartworks.invtmgmt.core.service.DataTransferService;
import com.smartworks.invtmgmt.core.service.DateUtil;
import com.smartworks.invtmgmt.web.ui.transfer.UILaundryLoad;
import com.smartworks.invtmgmt.web.ui.transfer.inventory.ReportDetailsResponse;
import com.smartworks.invtmgmt.web.ui.transfer.inventory.UIInventory;

@Controller
@RequestMapping("/reports")
public class InventoryDetailsController {
	@Autowired
	InventoryManager inventoryManager;
	
	@Autowired
	InvtTransManager invtTransMgr;
	
	@Autowired
	LaundryMgr laundryMgr;
	
	@Autowired
	LaundryLoadConverter laundryLoadConverter;
	
	@Autowired
	InventoryConverter inventoryConverter;
	
	@Autowired
	DataTransferService dataTransferService;

	protected static Logger logger = Logger
			.getLogger(InventoryDetailsController.class);


	@RequestMapping(value = "/inventory-all.form", method = RequestMethod.GET)
	public ModelAndView getAllInventory() {
		logger.error("Received request to show all inventory");
		ModelAndView mav = new ModelAndView("reports/inventory-all");
		return mav;
	}
	
	@RequestMapping(value = "/inventory-locn.form", method = RequestMethod.GET)
	public ModelAndView getInventoryByLocn() {
		logger.error("Received request to show all inventory");
		ModelAndView mav = new ModelAndView("reports/inventory-locn");
		return mav;
	}
	
	@RequestMapping(value = "/laundryreport-in.form", method = RequestMethod.GET)
	public ModelAndView showLaundryReportInput() {
		logger.error("Received request to show all Laundry Report Input");
		ModelAndView mav = new ModelAndView("reports/laundryreport-in");
		return mav;
	}
	
	@RequestMapping(value = "/laundryreport-page.form", method = RequestMethod.POST)
	public ModelAndView showLaundryReportPage() {
		logger.error("Received request to show all Laundry Report Input");
		ModelAndView mav = new ModelAndView("reports/laundry-report");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/laundryreport.form", method = RequestMethod.POST)
	public ReportDetailsResponse getLaundryReport(@RequestParam(value="fromDate") String fromDate,
			@RequestParam(value="toDate") String toDate
			) {
		logger.error("Received request to show all Laundry Report Input");
		
		Date dtFromDate  = DateUtil.getDate(fromDate);
		Date dtToDate  = DateUtil.getDate(toDate);
		System.out.println("From Date::"+dtFromDate);
		System.out.println("To Date::"+dtToDate);
		List<LaundryTracking> laundryTrackingList = laundryMgr.loadAllLoads(dtFromDate, dtToDate);
		ReportDetailsResponse response = new ReportDetailsResponse();
		
		List<UILaundryLoad> uiLaundryLoads = new ArrayList<UILaundryLoad>();
				
		for(LaundryTracking laundryTracking : laundryTrackingList) {
			uiLaundryLoads.add(laundryLoadConverter.getUILaundryLoad(laundryTracking));
		}		
		response.setRows(uiLaundryLoads);
		response.setPage("1");
		response.setTotal("10");
		response.setRecords(String.valueOf(uiLaundryLoads.size()));
		
		return response;
	}
	

	@RequestMapping(value="/exportLaundry.form", method=RequestMethod.GET)
	public ModelAndView exportLaundry(@RequestParam(value="fromDate") String fromDate,
			@RequestParam(value="toDate") String toDate, @RequestParam(value="reportName") String reportName) throws IOException {
		Map model = new HashMap();
		Date dtFromDate  = DateUtil.getDate(fromDate);
		Date dtToDate  = DateUtil.getDate(toDate);
		List<LaundryTracking> laundryTrackingList = laundryMgr.loadAllLoads(dtFromDate, dtToDate);
		List<Long> laundryTrackingListTotal = laundryMgr.loadAllLoadsTotal(dtFromDate, dtToDate);
		model.put("laundryList", laundryTrackingList);
		model.put("laundryListTotal", laundryTrackingListTotal);
		if("transactional".equals(reportName))
			return new ModelAndView("LaundryReportExcelView", model);
		else
			return new ModelAndView("LaundrySummaryReportView", model);
	}


	@RequestMapping(value = "/allinvt.form", method = RequestMethod.GET)
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
	
	
	@RequestMapping(value="/exportInv.form", method=RequestMethod.GET)
	public ModelAndView receiveInventoryFromLaundry(HttpServletRequest request, HttpServletResponse response
			) throws IOException {
		Map model = new HashMap();
		List<Inventory> invtList = inventoryManager.getAllInventory();
		List<UIInventory> uiInvtList = new ArrayList<UIInventory>();	
		for (Inventory inventory : invtList) {
			uiInvtList.add(inventoryConverter.getUIInventory(inventory));			
		}
		model.put("widgetList", uiInvtList);
		ModelAndView mav = new ModelAndView("reports/exportInv");
		return new ModelAndView("InventoryListExcelView", model);
	}
	
	@RequestMapping(value="/inventoryReport.form", method=RequestMethod.GET)
	public ModelAndView inventoryReportByLocation(HttpServletRequest request, HttpServletResponse response, Integer locationId
			) throws IOException {
		Map model = new HashMap();
		List<Inventory> invtList = inventoryManager.getInventoryByLocn(locationId);
		
		model.put("inventoryList", invtList);
		ModelAndView mav = new ModelAndView("reports/inventoryReport");
		return new ModelAndView("inventoryReport", model);
	}
	
	
	@RequestMapping(value = "/filetransfer.form", method = RequestMethod.GET)
	public ModelAndView showExportImport() {
		logger.error("Received request to show all export import");
		ModelAndView mav = new ModelAndView("reports/filetransfer");
		return mav;
	}
	
	@RequestMapping(value = "/processfile.form", method = RequestMethod.GET)
	public ModelAndView processfile() throws Exception {
		logger.error("Received request to show all export import");
		String	path = System.getProperty("uploaddirectory", "d:/Hari/temp");
		 File folder = new File(path);
		  File[] listOfFiles = folder.listFiles(); 
		 
		  for (int i = 0; i < listOfFiles.length; i++) 
		  {
		 
		   if (listOfFiles[i].isFile()) 
		   {
			  String files = listOfFiles[i].getName();
			  File fileObj = new File(path+"/"+files);
			  dataTransferService.syncInventory(fileObj);
			  fileObj.delete();
		      }
		  }

		
		ModelAndView mav = new ModelAndView("reports/inventory-all");
		return mav;
	}
	
}
