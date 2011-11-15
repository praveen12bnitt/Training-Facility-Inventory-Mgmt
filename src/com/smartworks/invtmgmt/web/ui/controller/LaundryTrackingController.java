package com.smartworks.invtmgmt.web.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.smartworks.invtmgmt.core.manager.LaundryMgr;
import com.smartworks.invtmgmt.web.ui.form.LaundryTrackingForm;

@Controller
@RequestMapping("/laundry")
public class LaundryTrackingController {
	
	@Autowired
	LaundryMgr laundryMgr;

	@RequestMapping(value="/create-load.form", method=RequestMethod.GET)
	public ModelAndView displayTransaction() {
		ModelAndView mav = new ModelAndView("laundry/laundry-create");
		LaundryTrackingForm laundryTrackingForm = new LaundryTrackingForm();
		mav.addObject("laundryTracking", laundryTrackingForm);
		return mav;
	}
		
	@RequestMapping(value="/create-load.form", method=RequestMethod.POST)
	public ModelAndView saveLaundryLoad(HttpServletRequest request, @ModelAttribute("laundryTracking") LaundryTrackingForm laundryTrackingForm) {
		laundryMgr.createLaundryLoad(laundryTrackingForm.getLaundryTracking());
		return null;
	}
	
}

