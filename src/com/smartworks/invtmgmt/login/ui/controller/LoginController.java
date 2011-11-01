package com.smartworks.invtmgmt.login.ui.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
@RequestMapping("/auth")
public class LoginController {
 
	@RequestMapping(value="/welcome.form", method = RequestMethod.GET)
	public void printWelcome(ModelMap model, HttpServletResponse response) throws IOException {
 
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = user.getUsername();
	
		response.getWriter().println("user::"+name);
 
	}
 
	@RequestMapping(value="/auth.form", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
 
		ModelAndView mav = new ModelAndView("pages/login");
		return mav;
 
	}
	
	@RequestMapping(value="/authfailed.form", method = RequestMethod.GET)
	public ModelAndView loginerror(ModelMap model) {
		ModelAndView mav = new ModelAndView("pages/login");
		mav.addObject("error", "true");
		return mav;
 
	}
	
	@RequestMapping(value="/logout.form", method = RequestMethod.GET)
	public ModelAndView logout(ModelMap model) {
		ModelAndView mav = new ModelAndView("pages/login");
		return mav;
	}
	
}