package com.smartworks.invtmgmt.web.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.smartworks.invtmgmt.core.dao.ClassDao;
import com.smartworks.invtmgmt.core.dao.ProductDao;
import com.smartworks.invtmgmt.core.dao.TraineeDao;
import com.smartworks.invtmgmt.core.domain.Class;
import com.smartworks.invtmgmt.core.domain.Product;
import com.smartworks.invtmgmt.core.domain.Trainee;
import com.smartworks.invtmgmt.core.manager.ClassMgr;
import com.smartworks.invtmgmt.core.service.DataTransferService;
import com.smartworks.invtmgmt.web.ui.form.ClassForm;
import com.smartworks.invtmgmt.web.ui.form.ProductMultiSelectData;
import com.smartworks.invtmgmt.web.ui.transfer.inventory.ReportDetailsResponse;

@Controller
@RequestMapping("/class")
public class ClassController {
	
	@Autowired
	DataTransferService dataTransferService;
	
	@Autowired
	ClassDao classIntObj;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ClassDao classDao;
	
	@Autowired
	private ClassMgr classMgr;
	
	@Autowired
	private TraineeDao traineeDao;
	
	protected static Logger logger = Logger
			.getLogger(ClassController.class);
	
	@RequestMapping(value = "/class.form", method = RequestMethod.GET)
	public ModelAndView getAllInventory(HttpServletRequest request) {
		logger.error("Received request to list class");
		ModelAndView mav = new ModelAndView("class/list-all-classes");
		return mav;
	}
	
	
	@RequestMapping(value="/list-all-classes.form", method = RequestMethod.GET)
	public @ResponseBody
	ReportDetailsResponse getAllClass(HttpServletRequest request){
		logger.info("Entered into class log ::: Commoncontroller");
		List<Class> classes = new ArrayList<Class>();
		classes = classDao.loadAll();
		ReportDetailsResponse response = new ReportDetailsResponse();
		
		response.setRows(classes);
		response.setPage("1");
		response.setTotal("10");
		response.setRecords(String.valueOf(classes.size()));
		return response;
		
	}
	
	@RequestMapping(value = "/add-class.form", method = RequestMethod.GET)
	public ModelAndView addClass() {
		return addEdit(new Class(),false);
	}
	
	@RequestMapping(value = "/edit-class.form", method = RequestMethod.GET)
	public ModelAndView editClass(@RequestParam String className) {
		Class clazz = classMgr.load(className);
		return addEdit(clazz,true);
	}
	
	public ModelAndView addEdit(Class clazz, boolean edit) {
		ModelAndView mav = new ModelAndView("class/add-edit-class");
		ClassForm clsForm = new ClassForm();
		clsForm.setCls(clazz);
		List<ProductMultiSelectData> multiSelect = createProductMultiSelectData(clazz);
		List<Trainee> traineeList = createTraineeList(clazz);
		mav.addObject("clsForm", clsForm);
		mav.addObject("productData", multiSelect);
		mav.addObject("traineeList", traineeList);
		mav.addObject("editMode", edit);
		return mav;
	}
	
	
	private List<Trainee> createTraineeList(Class clazz) {
		List<Trainee> trainees = traineeDao.loadAll();
		return trainees;
	}


	@RequestMapping(value = "/add-class.form", method = RequestMethod.POST)
	public ModelAndView saveClass(HttpServletRequest request, @ModelAttribute("classForm") ClassForm classForm, @RequestParam Integer[] selectedKits) {
		return saveMethod(classForm, selectedKits, request);
	}
	
	@RequestMapping(value = "/edit-class.form", method = RequestMethod.POST)
	public ModelAndView saveClass1(HttpServletRequest request, @ModelAttribute("classForm") ClassForm classForm, @RequestParam Integer[] selectedKits) { 	
		return saveMethod(classForm, selectedKits, request);
	}
	
	public ModelAndView saveMethod(ClassForm classForm, Integer[] selectedKits, HttpServletRequest request ) {
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	   	final Map<String, MultipartFile> files = multiRequest.getFileMap();
	   	List<Integer> processExcelFile = null;
	   	for (MultipartFile file : files.values()) {
	   		System.out.println("file name uploaded::"+file.getName());
	   		try {
	   			processExcelFile = processExcelFile(file,"trainee");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   	}
	   	
	   	classMgr.saveClass(classForm.getCls(), selectedKits, (Integer[]) processExcelFile.toArray());
		ModelAndView mav = new ModelAndView("class/list-all-classes");
		return mav;
	}
	
	private List<ProductMultiSelectData> createProductMultiSelectData(Class clazz) {
		List<Product> products = productDao.loadAll();
		Set<Product> selectedProducts = clazz.getProducts();
		
		products.removeAll(selectedProducts);
		
		List<ProductMultiSelectData> multiSelect = new ArrayList<ProductMultiSelectData>(); 
		
		for(Product p : products) {
			ProductMultiSelectData multiSelectData = new ProductMultiSelectData();
			multiSelectData.setProductId(p.getProductId());
			multiSelectData.setProductName(p.getProductName());
			multiSelectData.setSelected(false);
			multiSelect.add(multiSelectData);
		}
		
		for(Product p : selectedProducts) {
			ProductMultiSelectData multiSelectData = new ProductMultiSelectData();
			multiSelectData.setProductId(p.getProductId());
			multiSelectData.setProductName(p.getProductName());
			multiSelectData.setSelected(true);
			multiSelect.add(multiSelectData);
		}
		
		return multiSelect;
	}
	
	private List<Integer> processExcelFile(MultipartFile file, String type) throws Exception {
    	if(type.equalsIgnoreCase("kits"))
    		dataTransferService.syncKits(file);
    	else if(type.equalsIgnoreCase("trainee")) {
			List<Integer> syncTrainee = dataTransferService.syncTrainee(file);
			return syncTrainee;
		} else if(type.equalsIgnoreCase("class"))
    		dataTransferService.syncClass(file);
		return null;
	}
	
}
