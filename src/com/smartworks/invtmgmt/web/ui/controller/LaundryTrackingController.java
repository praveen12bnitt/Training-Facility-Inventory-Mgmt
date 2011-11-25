package com.smartworks.invtmgmt.web.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartworks.invtmgmt.converter.LaundryLoadConverter;
import com.smartworks.invtmgmt.core.domain.LaundryTracking;
import com.smartworks.invtmgmt.core.manager.LaundryMgr;
import com.smartworks.invtmgmt.web.ui.controller.util.ValidationUtil;
import com.smartworks.invtmgmt.web.ui.form.LaundryTrackingForm;
import com.smartworks.invtmgmt.web.ui.transfer.UILaundryLoad;
import com.smartworks.invtmgmt.web.ui.transfer.inventory.ReportDetailsResponse;

@Controller
@RequestMapping("/laundry")
public class LaundryTrackingController {
	
	@Autowired
	LaundryMgr laundryMgr;
	
	@Autowired
	LaundryLoadConverter laundryLoadConverter;
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value="/create-load.form", method=RequestMethod.GET)
	public ModelAndView displayTransaction() {
		ModelAndView mav = new ModelAndView("laundry/laundry-create");
		LaundryTrackingForm laundryTrackingForm = new LaundryTrackingForm();
		mav.addObject("laundryTrackingForm", laundryTrackingForm);
		return mav;
	}
		
	@RequestMapping(value="/create-load.form", method=RequestMethod.POST)
	public String saveLaundryLoad(HttpServletRequest request, @ModelAttribute("laundryTracking") LaundryTrackingForm laundryTrackingForm) {
		laundryMgr.createLaundryLoad(laundryTrackingForm.getLaundryTracking());
		return "redirect:list-laundry.form";
	}
	
	
	@RequestMapping(value="/edit-load.form", method=RequestMethod.GET)
	public ModelAndView editLaundry(@RequestParam Integer loadId) {
		ModelAndView mav = new ModelAndView("laundry/laundry-load-edit");
		LaundryTrackingForm laundryTrackingForm = new LaundryTrackingForm();
		laundryTrackingForm.setLaundryTracking(laundryMgr.load(loadId));
		mav.addObject("laundryTrackingForm", laundryTrackingForm);
		return mav;
	}
	
	@RequestMapping(value="/close-load.form", method=RequestMethod.POST)
	public String closeLaundry(HttpServletRequest request, @ModelAttribute("laundryTracking") LaundryTrackingForm laundryTrackingForm) {
		LaundryTracking laundryTracking = laundryMgr.load(laundryTrackingForm.getLaundryTracking().getLaundryTrankingId());		
		LaundryTracking uiLaundryTracking = laundryTrackingForm.getLaundryTracking();		
		modifyUpdatedFields(laundryTracking, uiLaundryTracking);		
		laundryMgr.closeLoad(laundryTracking);	
		return "redirect:list-laundry.form";
	}
	
	@RequestMapping(value="/edit-load.form", method=RequestMethod.POST)
	public ModelAndView editLoady(HttpServletRequest request, @ModelAttribute("laundryTracking") @Valid LaundryTrackingForm laundryTrackingForm,BindingResult result) {		
		// Load load from db and modify the updated fields.
		ModelAndView mav = new ModelAndView();
		
		if (result.hasErrors()) {  
			List<String> errormsgs = ValidationUtil.getErrorMsgs(messageSource, result);
			mav.setViewName("laundry/laundry-load-edit");
			mav.addObject("validationErrors",errormsgs);
			mav.addObject("laundryTrackingForm", laundryTrackingForm);
			return mav;
        }  
		LaundryTracking laundryTracking = laundryMgr.load(laundryTrackingForm.getLaundryTracking().getLaundryTrankingId());		
		LaundryTracking uiLaundryTracking = laundryTrackingForm.getLaundryTracking();		
		modifyUpdatedFields(laundryTracking, uiLaundryTracking);		
		laundryMgr.save(laundryTracking);
		mav.setViewName("redirect:list-laundry.form");
		return mav;
	}
	
	@RequestMapping(value="/list-laundry.form", method=RequestMethod.GET)
	public ModelAndView listLaundry() {
		ModelAndView mav = new ModelAndView("laundry/list-laundry");
		return mav;
	}
	
	@RequestMapping(value = "/list-open-laundry.form", method = RequestMethod.GET)
	public @ResponseBody ReportDetailsResponse openLaundryLoads() {		
		ReportDetailsResponse response = new ReportDetailsResponse();
		
		List<LaundryTracking> laundryTrackingList = laundryMgr.loadAllOpenLoads();
		
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
	
	private void modifyUpdatedFields(LaundryTracking laundryTrackingDb, LaundryTracking uiLaundryTracking) {
		laundryTrackingDb.setATFSABT0006H(uiLaundryTracking.getATFSABT0006H());
		laundryTrackingDb.setCleanedFilter(uiLaundryTracking.getCleanedFilter());
		laundryTrackingDb.setCode(uiLaundryTracking.getCode());
		laundryTrackingDb.setCTD0006D(uiLaundryTracking.getCTD0006D());
		laundryTrackingDb.setDMD0006G(uiLaundryTracking.getDMD0006G());
		laundryTrackingDb.setDryerMachineNo(uiLaundryTracking.getDryerMachineNo());
		laundryTrackingDb.setDryerTempSetting(uiLaundryTracking.getDryerTempSetting());
		laundryTrackingDb.setFAD0006E(uiLaundryTracking.getFAD0006E());
		laundryTrackingDb.setGymClothings(uiLaundryTracking.getGymClothings());
		laundryTrackingDb.setJockSocksBras(uiLaundryTracking.getJockSocksBras());
		laundryTrackingDb.setPTD0006F(uiLaundryTracking.getPTD0006F());
		laundryTrackingDb.setRegLaundry(uiLaundryTracking.getRegLaundry());
		laundryTrackingDb.setTowels(uiLaundryTracking.getTowels());
		laundryTrackingDb.setTseRoom(uiLaundryTracking.getTseRoom());
		laundryTrackingDb.setUniforms(uiLaundryTracking.getUniforms());
		laundryTrackingDb.setUSBOPB0006B(uiLaundryTracking.getUSBOPB0006B());
		laundryTrackingDb.setWashingMachineNo(uiLaundryTracking.getWashingMachineNo());
		laundryTrackingDb.setWeightBuggy(uiLaundryTracking.getWeightBuggy());
		laundryTrackingDb.setWeightWithBuggy(uiLaundryTracking.getWeightWithBuggy());
	}
	
}

