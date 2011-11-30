package com.smartworks.invtmgmt.web.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartworks.invtmgmt.core.dao.UserDao;
import com.smartworks.invtmgmt.core.domain.Product;
import com.smartworks.invtmgmt.core.domain.Staff;
import com.smartworks.invtmgmt.core.domain.Trainee;
import com.smartworks.invtmgmt.core.domain.User;
import com.smartworks.invtmgmt.core.manager.CommonTransactionMgr;
import com.smartworks.invtmgmt.core.manager.StaffMgr;
import com.smartworks.invtmgmt.core.manager.TraineeMgr;
import com.smartworks.invtmgmt.web.ui.JqgridWhereClauseGenerator;
import com.smartworks.invtmgmt.web.ui.controller.util.ValidationUtil;
import com.smartworks.invtmgmt.web.ui.form.StaffForm;
import com.smartworks.invtmgmt.web.ui.form.TraineeForm;
import com.smartworks.invtmgmt.web.ui.transfer.inventory.ReportDetailsResponse;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	private UserDao userDao; 
	
	@Autowired
	private CommonTransactionMgr commonTransactionMgr;
	
	@Autowired
	private TraineeMgr traineeMgr;
	
	@Autowired
	private StaffMgr staffMgr;
	
	@Autowired
	private MessageSource messageSource;
	
	protected static Logger logger = Logger
			.getLogger(CommonController.class);
	
	@RequestMapping(value = "/list-active-trainees.form", method = RequestMethod.GET)
	public @ResponseBody
	ReportDetailsResponse getActiveTrainess(HttpServletRequest request) {
		logger.info("Get All Trainees<>");
		
		List<Trainee> trainees = new ArrayList<Trainee>();

		trainees = traineeMgr.loadActiveTrainee();
		ReportDetailsResponse response = new ReportDetailsResponse();
		
		response.setRows(trainees);
		response.setPage("1");
		response.setTotal("10");
		response.setRecords(String.valueOf(trainees.size()));
		return response;
	}
	
	@RequestMapping(value = "/list-active-staff.form", method = RequestMethod.GET)
	public @ResponseBody
	ReportDetailsResponse getAllStaff(HttpServletRequest request) {
		logger.info("Get All Staff<>");
		
		List<Staff> staff = new ArrayList<Staff>();

		staff = staffMgr.loadActiveStaff();
		ReportDetailsResponse response = new ReportDetailsResponse();
		
		response.setRows(staff);
		response.setPage("1");
		response.setTotal("10");
		response.setRecords(String.valueOf(staff.size()));
		return response;
	}
	
	
	@RequestMapping(value = "/listtrainees.form", method = RequestMethod.POST)
	public @ResponseBody
	ReportDetailsResponse getAllTrainess(HttpServletRequest request,@RequestParam("_search") Boolean searchEnabled, 
			@RequestParam("rows") Integer rowsPerPage, @RequestParam("page") Integer pageNumber, @RequestParam("sidx") String sortField, @RequestParam("sord") String sortType) {
		logger.info("Get All Trainees<>");
		
		List<Trainee> trainees = new ArrayList<Trainee>();
		String whereClause = JqgridWhereClauseGenerator.getWhereClause(request, Trainee.class);
		
		Integer firstResult = (pageNumber-1)*rowsPerPage;	
		
		trainees = traineeMgr.getTrainee(firstResult, rowsPerPage, sortField, sortType, whereClause);
		Long totalCount = traineeMgr.getTraineeTotalCount(sortField, sortType, whereClause);
		ReportDetailsResponse response = new ReportDetailsResponse();
		
		response.setRows(trainees);
		response.setPage(pageNumber.toString());
		Double totalPages = Math.ceil( totalCount * 1.0f / (rowsPerPage) );
		response.setTotal(totalPages.toString());
		response.setRecords(totalCount.toString());
		return response;
	}
	
	@RequestMapping(value = "/liststaffs.form", method = RequestMethod.POST)
	public @ResponseBody
	ReportDetailsResponse getAllStaffs(HttpServletRequest request,@RequestParam("_search") Boolean searchEnabled, 
			@RequestParam("rows") Integer rowsPerPage, @RequestParam("page") Integer pageNumber, @RequestParam("sidx") String sortField, @RequestParam("sord") String sortType) {
		logger.info("Get All Trainees<>");
		
		List<Staff> staff = new ArrayList<Staff>();
		String whereClause = JqgridWhereClauseGenerator.getWhereClause(request, Staff.class);
		
		Integer firstResult = (pageNumber-1)*rowsPerPage;	
		
		staff = staffMgr.getStaff(firstResult, rowsPerPage, sortField, sortType, whereClause);
		Long totalCount = staffMgr.getStaffTotalCount(sortField, sortType, whereClause);
		ReportDetailsResponse response = new ReportDetailsResponse();
		
		response.setRows(staff);
		response.setPage(pageNumber.toString());
		Double totalPages = Math.ceil( totalCount * 1.0f / (rowsPerPage) );
		response.setTotal(totalPages.toString());
		response.setRecords(totalCount.toString());
		return response;
	}
	
	@RequestMapping(value = "/list-all-trainee.form", method = RequestMethod.GET)
	public ModelAndView getAllTrainee() {
		ModelAndView mav = new ModelAndView("common/trainee-list");
		return mav;
	}
	
	@RequestMapping(value = "/list-all-staff.form", method = RequestMethod.GET)
	public ModelAndView getAllStaff() {
		ModelAndView mav = new ModelAndView("common/staff-list");
		return mav;
	}
	
	@RequestMapping(value = "/edit-trainee.form", method = RequestMethod.GET)
	public ModelAndView editTrainee(@RequestParam Integer traineeId) {
		ModelAndView mav = new ModelAndView("common/add-trainee");
		Trainee trainee = traineeMgr.load(traineeId);
		TraineeForm traineeForm = new TraineeForm();
		traineeForm.setTrainee(trainee);
		traineeForm.setEdit(true);
		mav.addObject("traineeForm", traineeForm);
		return mav;
	}
	
	@RequestMapping(value = "/edit-staff.form", method = RequestMethod.GET)
	public ModelAndView editStaff(@RequestParam Integer staffId) {
		ModelAndView mav = new ModelAndView("common/add-staff");
		Staff staff = staffMgr.load(staffId);
		StaffForm staffForm = new StaffForm();
		staffForm.setStaff(staff);
		staffForm.setEdit(true);
		mav.addObject("staffForm", staffForm);
		return mav;
	}
	
	@RequestMapping(value = "/add-trainee.form", method = RequestMethod.GET)
	public ModelAndView addTrainee() {
		ModelAndView mav = new ModelAndView("common/add-trainee");
		TraineeForm traineeForm = new TraineeForm();
		mav.addObject("traineeForm", traineeForm);
		return mav;
	}
	
	@RequestMapping(value = "/add-staff.form", method = RequestMethod.GET)
	public ModelAndView addStaff() {
		ModelAndView mav = new ModelAndView("common/add-staff");
		StaffForm staffForm = new StaffForm();
		mav.addObject("staffForm", staffForm);
		return mav;
	}
	
	@RequestMapping(value = "/add-trainee.form", method = RequestMethod.POST)
	public ModelAndView addEditTrainee(HttpServletRequest request, @ModelAttribute("traineeForm") @Valid TraineeForm traineeForm,BindingResult result) {
		ModelAndView mav = new ModelAndView("redirect:list-all-trainee.form");
		if (result.hasErrors()) {  
			List<String> errormsgs = ValidationUtil.getErrorMsgs(messageSource, result);
			mav.setViewName("common/add-trainee");
			mav.addObject("validationErrors",errormsgs);
			mav.addObject("traineeForm", traineeForm);
			return mav;
        } 
		if(!traineeForm.isEdit())
			traineeMgr.add(traineeForm.getTrainee());
		else
			traineeMgr.update(traineeForm.getTrainee());
		return mav;
	}
	
	@RequestMapping(value = "/add-staff.form", method = RequestMethod.POST)
	public ModelAndView addEditStaff(HttpServletRequest request, @ModelAttribute("staffForm") @Valid StaffForm staffForm,BindingResult result) {
		ModelAndView mav = new ModelAndView("redirect:list-all-staff.form");
		if (result.hasErrors()) {  
			List<String> errormsgs = ValidationUtil.getErrorMsgs(messageSource, result);
			mav.setViewName("common/add-staff");
			mav.addObject("validationErrors",errormsgs);
			mav.addObject("traineeForm", staffForm);
			return mav;
        } 
		if(!staffForm.isEdit())
			staffMgr.add(staffForm.getStaff());
		else
			staffMgr.update(staffForm.getStaff());
		return mav;
	}
	
	@RequestMapping(value = "/edit-trainee.form", method = RequestMethod.POST)
	public ModelAndView editTrainee(HttpServletRequest request, @ModelAttribute("traineeForm") @Valid TraineeForm traineeForm,BindingResult result) {
		return addEditTrainee(request,traineeForm,result);
	}
	
	@RequestMapping(value = "/edit-staff.form", method = RequestMethod.POST)
	public ModelAndView editStaff(HttpServletRequest request, @ModelAttribute("staffForm") @Valid StaffForm staffForm,BindingResult result) {
		return addEditStaff(request,staffForm,result);
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
