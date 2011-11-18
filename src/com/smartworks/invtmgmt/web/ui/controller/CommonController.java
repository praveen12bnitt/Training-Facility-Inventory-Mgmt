package com.smartworks.invtmgmt.web.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartworks.invtmgmt.core.dao.TraineeDao;
import com.smartworks.invtmgmt.core.dao.UserDao;
import com.smartworks.invtmgmt.core.domain.Product;
import com.smartworks.invtmgmt.core.domain.Trainee;
import com.smartworks.invtmgmt.core.domain.User;
import com.smartworks.invtmgmt.core.manager.CommonTransactionMgr;
import com.smartworks.invtmgmt.web.ui.transfer.inventory.ReportDetailsResponse;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	private TraineeDao traineeDao;
	
	@Autowired
	private UserDao userDao; 
	
	@Autowired
	private CommonTransactionMgr commonTransactionMgr;
	
	protected static Logger logger = Logger
			.getLogger(CommonController.class);
	
	@RequestMapping(value = "/listtrainees.form", method = RequestMethod.GET)
	public @ResponseBody
	ReportDetailsResponse getAllTrainess(HttpServletRequest request) {
		logger.info("Get All Trainees<>");
		
		List<Trainee> trainees = new ArrayList<Trainee>();

		trainees = traineeDao.loadAll();
		ReportDetailsResponse response = new ReportDetailsResponse();
		
		response.setRows(trainees);
		response.setPage("1");
		response.setTotal("10");
		response.setRecords(String.valueOf(trainees.size()));
		return response;
	}
	
	
	@RequestMapping(value = "/listusers.form", method = RequestMethod.GET)
	public @ResponseBody
	ReportDetailsResponse getAllUsers(HttpServletRequest request) {
		logger.info("Get All Users<>");
		
		List<User> users = new ArrayList<User>();

		users = userDao.loadAll();
		ReportDetailsResponse response = new ReportDetailsResponse();
		
		response.setRows(users);
		response.setPage("1");
		response.setTotal("10");
		response.setRecords(String.valueOf(users.size()));
		return response;
	}
	
	@RequestMapping(value = "/users.form", method = RequestMethod.GET)
	public ModelAndView displayUsers() {
		ModelAndView mav = new ModelAndView("userList");
		return mav;
	}
	
	
	@RequestMapping(value = "/createproduct.form", method = RequestMethod.GET)
	public ModelAndView createProduct() {
		ModelAndView mav = new ModelAndView("common/createproduct");
		return mav;
	}
	
	@RequestMapping(value = "/saveproduct.form", method = RequestMethod.POST)
	public ModelAndView saveProduct(@ModelAttribute Product product) {
		logger.info("uiProduct:::"+product);
		commonTransactionMgr.save(product);
		ModelAndView mav = new ModelAndView("common/createproduct");
		return mav;
	}
	
	@RequestMapping(value = "/deleteproduct.form", method = RequestMethod.GET)
	public @ResponseBody String deleteProduct(@RequestParam Integer productId) {
		logger.info("deleting productId:::"+productId);
		commonTransactionMgr.delete(productId);
		return "deleted";
	}
	
	@RequestMapping(value = "/listproducts.form", method = RequestMethod.GET)
	public @ResponseBody
	ReportDetailsResponse getAllProducts(HttpServletRequest request) {
		logger.info("Get All Products<>");
		
		List<Product> products = new ArrayList<Product>();
			
		products = commonTransactionMgr.getAllProducts();
		ReportDetailsResponse response = new ReportDetailsResponse();
		
		response.setRows(products);
		response.setPage("1");
		response.setTotal("10");
		response.setRecords(String.valueOf(products.size()));
		return response;
	}
	
	@RequestMapping(value = "/getItemsByProductId.form", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer,String> getItemsByProductId(@RequestParam Integer productId) {
		Map itemMap = commonTransactionMgr.getItemsByProductId(productId);
		return itemMap;
	}
	
	@RequestMapping(value = "/findByProductNameLike.form", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer,String> findByProductNameLike(@RequestParam String name){
		
		return commonTransactionMgr.findByProductNameLike(name);
	}
	
	
	
}
