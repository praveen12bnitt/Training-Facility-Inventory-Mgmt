package com.smartworks.invtmgmt.web.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.converter.ItemSkuConverter;
import com.smartworks.invtmgmt.core.dao.ItemDao;
import com.smartworks.invtmgmt.core.dao.LocationDao;
import com.smartworks.invtmgmt.core.dao.impl.ProductDaoImpl;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.Product;
import com.smartworks.invtmgmt.core.domain.ProductDetails;
import com.smartworks.invtmgmt.core.manager.ProductMgr;
import com.smartworks.invtmgmt.core.service.VelocityTemplateUtil;
import com.smartworks.invtmgmt.web.ui.form.ProductForm;
import com.smartworks.invtmgmt.web.ui.transfer.inventory.ReportDetailsResponse;

@Controller
@RequestMapping("/kits")
public class ProductController {

	@Autowired
	private LocationDao locationDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private ProductDaoImpl productDao;
	
	@Autowired
	private ProductMgr productMgr;
	
	@Autowired
	private ItemSkuConverter itemSkuConverter;

	protected static Logger logger = Logger.getLogger(ClassController.class);
	
	
	@RequestMapping(value="/list-kit.form", method=RequestMethod.GET)
	public ModelAndView viewKits() {
		ModelAndView mav = new ModelAndView("kits/kits-list");
		return mav;
	}
	
	
	@RequestMapping(value="/list-all-kits.form", method = RequestMethod.GET)
	public @ResponseBody
	ReportDetailsResponse getAllKits(HttpServletRequest request){
		List<Product> products = new ArrayList<Product>();
		products = productDao.loadAll();
		ReportDetailsResponse response = new ReportDetailsResponse();
		
		response.setRows(products);
		response.setPage("1");
		response.setTotal("10");
		response.setRecords(String.valueOf(products.size()));
		return response;
		
	}
	
	@RequestMapping(value="/add-kit.form", method=RequestMethod.GET)
	public ModelAndView addKit() { 
		return addEditKit(null,false); 		
	}
	
	
	@RequestMapping(value="/edit-kit.form", method=RequestMethod.GET)
	public ModelAndView editKit(@RequestParam Integer kitId) { 
		return addEditKit(kitId,true); 		
	}
	
	
	@RequestMapping(value="/add-kit.form", method=RequestMethod.POST)
	public ModelAndView addKit(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("kitForm") ProductForm productForm) {  		
		return addEditKit(productForm);
	}
	
	@RequestMapping(value="/edit-kit.form", method=RequestMethod.POST)
	public ModelAndView editKit(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("kitForm") ProductForm productForm) {  		
		return addEditKit(productForm);
	}
	
	public ModelAndView addEditKit(ProductForm productForm) {
		Product product = productForm.getProduct();
		Location loc = locationDao.load(productForm.getSelectedLocationId());
		product.setLocation(loc); 		
		productMgr.saveOrUpdate(product, productForm.getItemSkus()); 		
		return viewKits(); 		
	}
	
	public ModelAndView addEditKit(Integer productId, boolean edit) { 
		ModelAndView mav = new ModelAndView("kits/add-edit-kit"); 		
		ProductForm pForm = new ProductForm();
		
		if(edit) {
			Product product = productDao.load(productId);
			pForm.setProduct(product);
			if(product.getLocation() != null) {
				pForm.setSelectedLocationId(product.getLocation().getLocationId());
			} 	
			
			
			for(ProductDetails pd : product.getProductDetails()) {
				String skuCode = pd.getSkuCode();
				Integer quantity = pd.getQuantity();
				ItemSku itemSku = itemSkuConverter.getItemSku(skuCode);
				itemSku.setQuantity(quantity);  				
				pForm.getItemSkus().add(itemSku); 				
			}
		}
		
		List<Location> locs =  locationDao.loadSecondaryLocations();
		
		for(Location l : locs) {
			pForm.getLocationOptions().put(l.getLocationId(), l.getLocationName());
		} 
		
		List<String> itemNames= itemDao.getItemNames();
		pForm.setItemNames(itemNames);
		
		mav.addObject("kitForm", pForm);
		mav.addObject("editMode", edit);
		return mav;
	}
	
	
	@RequestMapping(value="/kits-class-loc-options.form", method=RequestMethod.GET)	
	public @ResponseBody String kitsForClassLoc(HttpServletRequest request,@RequestParam String className, @RequestParam Integer locationId) { 		
		Map<Integer, String> kits = productDao.productNames(className, locationId);
		StringBuilder sb = new StringBuilder();
		for(Integer kitId : kits.keySet()) {
			sb.append("<option value=\"").append(kitId).append("\">").append(kits.get(kitId)).append("</option>"); 			
		}
		return sb.toString();
	}
	
	@RequestMapping(value="/kits-loc-options.form", method=RequestMethod.GET)	
	public @ResponseBody String kitsForClassLoc(HttpServletRequest request,@RequestParam Integer locationId) { 		
		List<Product> pds = productDao.getProductsByLocation(locationId);
		StringBuilder sb = new StringBuilder();
		for(Product p : pds) {
			sb.append("<option value=\"").append(p.getProductId()).append("\">").append(p.getProductName()).append("</option>"); 		
		}
		return sb.toString();
	}
	
	@RequestMapping(value="/items-html.form", method=RequestMethod.GET)	
	public @ResponseBody String getItemsHtml(HttpServletRequest request,@RequestParam Integer productId, @RequestParam Integer rowNum) { 			
		Product product = productDao.load(productId); 		
		List<ItemSku> itemSkus = new ArrayList<ItemSku>(); 		
		for(ProductDetails pd : product.getProductDetails()) {
			ItemSku itemSku = itemSkuConverter.getItemSku(pd.getSkuCode());
			itemSku.setQuantity(pd.getQuantity());
			itemSkus.add(itemSku);
		} 		
		String htmlData = getItemsRowsForKit(itemSkus, rowNum);
		return htmlData;
	}
	
	
	private String getItemsRowsForKit(List<ItemSku> itemSkus, Integer index) {
		String htmlData = null;	
		VelocityEngine ve = VelocityTemplateUtil.getVelocityEngine();
		Template t = ve.getTemplate( "com/smartworks/invtmgmt/web/ui/template/kitrows.vm" );
		VelocityContext context = new VelocityContext();
        context.put("itemSkus", itemSkus);
        context.put("index", index);      
        htmlData = VelocityTemplateUtil.getData(context, t);
		return htmlData;
	}
	
}
