package com.smartworks.invtmgmt.web.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.smartworks.invtmgmt.core.dao.ClassDao;
import com.smartworks.invtmgmt.core.domain.Class;

@Controller
@RequestMapping("/class")
public class ClassController {
	
	@Autowired
	ClassDao classIntObj;
	
	protected static Logger logger = Logger
			.getLogger(ClassController.class);
	
	@RequestMapping(value = "/class.form", method = RequestMethod.GET)
	public ModelAndView getAllInventory(HttpServletRequest request) {
		logger.error("Received request to list class");
		ModelAndView mav = new ModelAndView("class/list-all-classes");
		List<Class> classes = classIntObj.loadAll();
		mav.addObject("classes", classes);
		return mav;
	}

}
