package com.smartworks.invtmgmt.web.ui.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartworks.invtmgmt.converter.LaundryConverter;
import com.smartworks.invtmgmt.core.domain.Laundry;
import com.smartworks.invtmgmt.core.manager.LaundryTransMgr;
import com.smartworks.invtmgmt.web.ui.transfer.UILaundry;
import com.smartworks.invtmgmt.web.ui.transfer.inventory.ReportDetailsResponse;


@Controller
@RequestMapping("/newlaundry")
public class LaundryController {
	
	@Autowired
	LaundryTransMgr laundryTransMgr;
	
	@RequestMapping(value="/create-load.form", method=RequestMethod.GET)
	public ModelAndView displayTransaction(@RequestParam(value="laundryType") String laundryType) {
		Map<String, String> fieldsMap = new HashMap<String, String>();
		if("W".equals(laundryType)) {
			fieldsMap.put("Header", "Washer Details");
			fieldsMap.put("Unit", "Washing Machine No");
			fieldsMap.put("Weight", "Total Weight");
			fieldsMap.put("Time", "Time of Washing");
			fieldsMap.put("Items", "Items Washed");
		} else {
			fieldsMap.put("Header", "Dryer Details");
			fieldsMap.put("Unit", "Dryer No");
			fieldsMap.put("Weight", "Total Weight");
			fieldsMap.put("Time", "Time of Drying");
			fieldsMap.put("Items", "Items Dried");
		}
		fieldsMap.put("LaundryType", laundryType);
		ModelAndView mav = new ModelAndView("newlaundry/laundry-create");
		mav.addObject("fieldsMap",fieldsMap);
		return mav;
	}
	
	/*******************************************************************
	@RequestMapping(value = "/createAppl.form",method = RequestMethod.GET)
	public ModelAndView mytran(@RequestParam(value="laundryType") String laundryType){
	
		Map<String,String> itemMap = new HashMap<String,String>();
		
		if("W".equals(laundryType)){
			itemMap.put("header", "Machine Details");
			itemMap.put("Unit","Machine No" );
			itemMap.put("weight","firstWeight" );
			itemMap.put("Time","washing time");
		}else{
			itemMap.put("header", "dry details");
			itemMap.put("Unit", "machineno");
			itemMap.put("weight", "weight of the cloth");
			itemMap.put("time", "dryer Time");
		}
		itemMap.put("LaundryType","laundryType");
		ModelAndView mav =  new ModelAndView("mylaundry/washload");
		mav.addObject("itemMap", itemMap);
		return mav;
	}
	
	item.put("LaundyrType","laundryType");
	ModelandView = mcc =  new ModelAndView("mylaundy/wload");
	mcc.addObject("itemMap",itemMap);
	mcc.addObject("itemMap",itemMap);
	mcc.addObject("itemMap",itemMap);
	mcc.addObject("itemMap",itenMap);
	mcc.addObject("itemMap",itemmap);
	
	
	*******************************************************************/
	
	/***********************************************************************/
	
	
	/***********************************************************************/
	
	@ResponseBody
	@RequestMapping(value="/save-load.form", method=RequestMethod.POST)
	public String saveLaundry(@RequestBody UILaundry uiLaundry) {
		Laundry laundry = LaundryConverter.convertFromUILaundry(uiLaundry);
		laundryTransMgr.save(laundry);
		return "success";
		
	}
	
	
	@RequestMapping(value="/laundrylist.form", method=RequestMethod.GET)
	public ModelAndView listLaundry(@RequestParam(value="laundryType") String laundryType) {
		ModelAndView mav = new ModelAndView("newlaundry/laundrylist-in");
		return mav;
	}
	
	@RequestMapping(value="/laundrylist-page.form", method=RequestMethod.GET)
	public ModelAndView listLaundryQuery(@RequestParam(value="laundryType") String laundryType,
			@RequestParam(value="fromDate") String fromDate,
			@RequestParam(value="toDate") String toDate
			) {
		
		ModelAndView mav = new ModelAndView("newlaundry/laundry-list");
		return mav;
	}
	//
	
	@ResponseBody
	@RequestMapping(value="/laundrylist-page.form", method=RequestMethod.POST)
	public ReportDetailsResponse showLaundryList(@RequestParam(value="laundryType") String laundryType,
			@RequestParam(value="fromDate") String fromDate,
			@RequestParam(value="toDate") String toDate
			) {
		ReportDetailsResponse response = new ReportDetailsResponse();
		List<Laundry> laundryList = laundryTransMgr.loadAll(laundryType, fromDate, toDate);
		List<UILaundry> uiLaundryList = new ArrayList<UILaundry>();
		for(Laundry laundry: laundryList) {
			uiLaundryList.add(LaundryConverter.convertToUILaundry(laundry));
		}
		response.setRows(uiLaundryList);
		response.setPage("1");
		response.setTotal("10");
		response.setRecords(String.valueOf(uiLaundryList.size()));
		
		return response;
	}
}



	
	

