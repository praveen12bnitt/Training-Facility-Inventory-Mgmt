package com.smartworks.invtmgmt.web.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.converter.UIDomainConverter;
import com.smartworks.invtmgmt.core.dao.LocationDao;
import com.smartworks.invtmgmt.core.dao.TransactionTypeDao;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.TransactionType;
import com.smartworks.invtmgmt.core.manager.InvtTransManager;
import com.smartworks.invtmgmt.core.manager.ItemMgr;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;
import com.smartworks.invtmgmt.web.ui.form.IssueSkuForm;


@Controller
@RequestMapping("/inbound")
public class InboundFormController {
	
	@Autowired
	ItemMgr itemMgr = null;
	@Autowired
	TransactionTypeDao transactionTypeDao = null;
	@Autowired
	InvtTransManager invtTransMgr = null;
	@Autowired
	LocationDao locDao = null;
  
	@RequestMapping(value="/transfer.form", method=RequestMethod.GET)
	public ModelAndView displayTransaction() {
		List<Item> items = itemMgr.getAllItems();
		
		IssueSkuForm issueSkuForm = new IssueSkuForm();		
		issueSkuForm.setLocationId(1);
		issueSkuForm.setTransactionType(TransactionTypeEnum.TRANSFER_INVENTORY);		
		issueSkuForm.setItems(items);
				
        ModelAndView mav = new ModelAndView("transaction/InvMovement");
        mav.addObject("issueSkuForm", issueSkuForm);
        
		List<Location> listLocations = locDao.loadAll();
		mav.addObject("locationList", listLocations);
		return mav;
	}
	
	
	@RequestMapping(value="/receive.form", method=RequestMethod.GET)
	public ModelAndView displayInventory(HttpServletRequest request, HttpServletResponse response) {
		
		List<Item> items = itemMgr.getAllItems();
		
		IssueSkuForm issueSkuForm = new IssueSkuForm();		
		issueSkuForm.setTransactionType(TransactionTypeEnum.TRANSFER_INVENTORY);		
		issueSkuForm.setItems(items);
				
        ModelAndView mav = new ModelAndView("transaction/ReceiveInventory");
        mav.addObject("issueSkuForm", issueSkuForm);
        
		return mav;
	}
	@RequestMapping(value="/transferToMW.form", method=RequestMethod.GET)
	public ModelAndView displayInventoryToMW(HttpServletRequest request, HttpServletResponse response, @RequestParam TransactionTypeEnum transactionTypeId) {
//		List<Item> items = itemMgr.getAllItems();
//		ModelMap myModel = new ModelMap();
//        myModel.put("itemList", items);
//        TransactionForm transactionForm = populateUIFormObjects();
//        ModelAndView mav = new ModelAndView("transaction/InvTransferToMW","model",myModel);
//		mav.addObject("transferForm", transactionForm);
//		return mav;
		
		return null;
	}	
	
	
	@RequestMapping(value="/transfer.form", method=RequestMethod.POST)
	public ModelAndView transferSKU(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm) 
	{
		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(issueSkuForm);
		transDetailsHolder.setSrcLocationId(4);
		boolean issueSkuSucceded = invtTransMgr.processInventoryChange(transDetailsHolder);
		
		TransactionType transactionType = transactionTypeDao.load(issueSkuForm.getTransactionType());
		List<Item> items =   itemMgr.getAllItems();
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/InvMovement");
		mav.addObject("issueSkuForm", issueSkuForm);
		mav.addObject("transactionFormMessage","Issued Successfully");
		return mav;
	}
	
	
	@RequestMapping(value="/transferToMW.form", method=RequestMethod.POST)
	public ModelAndView transferToMW(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm) 
	{
		
		return null;
//		boolean error = false;
//		try {
//			
//			List<UIFormItem> uiFormItems = transactionForm.getListUIFormItems();
//			List<UIFormLocation> uiFormLocations = transactionForm.getLocationList();
//			
//			List<ItemSku> skus = new ArrayList<ItemSku>();
//			int fromLocn = 0; 
//			int toLocn = 4; // Master Warehouse
//			for (UIFormLocation uiFormLocation: uiFormLocations) {
//				fromLocn = Integer.parseInt(uiFormLocation.getSelectedValue());
//			}
//			
//			for (UIFormItem uiformItem: uiFormItems) {
//				if(uiformItem.getItemQty() !=null && uiformItem.getItemQty() > 0) {
//					ItemSku sku = new ItemSku();
//					Item item = new Item(uiformItem.getItemId());
//					sku.setQuantity(uiformItem.getItemQty());
//					sku.setItem(item);
//					
//					Map<ItemAttribute, ItemAttributeValue> itemAttributeDetails = new HashMap<ItemAttribute, ItemAttributeValue>();
//					List<UIFormItemAttribute> uiFormItemAttributes = uiformItem.getUiFormItemAttributes();
//					if(uiFormItemAttributes != null){
//						for (UIFormItemAttribute uiFormItemAttribute: uiFormItemAttributes) {
//							ItemAttribute itemAttrib = new ItemAttribute();
//							ItemAttributeValue itemAttributeVal = new ItemAttributeValue();
//							itemAttrib.setAttibuteId(uiFormItemAttribute.getItemAttributeId());
//							itemAttributeVal.setAttributeValueId(Integer.parseInt(uiFormItemAttribute.getSelectedAttributeValue()));
//							System.out.println("Name"+uiFormItemAttribute.getItemAttributeId());
//							System.out.println("Value"+uiFormItemAttribute.getSelectedAttributeValue());
//							itemAttributeDetails.put(itemAttrib, itemAttributeVal);
//						}
//						//sku.setItemAttributeDetails(itemAttributeDetails);
//						uiFormItemAttributes = null;
//					}
//					skus.add(sku);
//				 }
//				}
//			
//			//boolean retval= invtTransMgr.transferInventory(fromLocn,toLocn,skus);
//			
//			TransactionDetailsHolder transDetails = new TransactionDetailsHolder();
//			transDetails.setItemSkus(skus);
//			transDetails.setUserId(100);
//			transDetails.setSrcLocationId(fromLocn);
//			transDetails.setTransactionType(TransactionTypeEnum.TRANSFER_INVENTORY);
//			transDetails.setLocationId(toLocn);
//			boolean retval = invtTransMgr.processInventoryChange(transDetails);
//			} catch (Exception e) {
//				// TODO: handle exception
//				error = true;
//			}
//	
//		
//		List<Item> items = itemMgr.getAllItems();
//		ModelMap myModel = new ModelMap();
//        myModel.put("itemList", items);
//        transactionForm = populateUIFormObjects();
//        if(error) {
//        	transactionForm.getLocationList().get(0).setError("Failed to transfer");
//        } else {
//        	transactionForm.getLocationList().get(0).setError("success");
//        }
//       
//		ModelAndView mav = new ModelAndView("transaction/InvTransferToMW","model",myModel);
//		mav.addObject("transferForm", transactionForm);
//		
//		return mav;
	}
	
	
	@RequestMapping(value="/receive.form",method=RequestMethod.POST)
	public ModelAndView receiveInventory(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("issueSkuForm") IssueSkuForm issueSkuForm) 
	{
		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(issueSkuForm);
		transDetailsHolder.setSrcLocationId(-1);
		transDetailsHolder.setLocationId(4);
				
		boolean issueSkuSucceded = invtTransMgr.processInventoryChange(transDetailsHolder);
		
		TransactionType transactionType = transactionTypeDao.load(issueSkuForm.getTransactionType());
		List<Item> items =   itemMgr.getAllItems();
		issueSkuForm.setItems(items);
		ModelAndView mav = new ModelAndView("transaction/ReceiveInventory");
		mav.addObject("issueSkuForm", issueSkuForm);
		mav.addObject("transactionFormMessage","Issued Successfully");
		return mav;
	}
	
	


}