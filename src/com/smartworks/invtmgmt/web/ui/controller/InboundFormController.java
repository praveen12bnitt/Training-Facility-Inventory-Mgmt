package com.smartworks.invtmgmt.web.ui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.core.dao.TransactionTypeDao;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.ItemAttribute;
import com.smartworks.invtmgmt.core.domain.ItemAttributeValue;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.manager.InvtTransManager;
import com.smartworks.invtmgmt.core.manager.ItemMgr;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;
import com.smartworks.invtmgmt.web.ui.form.TransactionForm;
import com.smartworks.invtmgmt.web.ui.transfer.UIFormItem;
import com.smartworks.invtmgmt.web.ui.transfer.UIFormItemAttribute;
import com.smartworks.invtmgmt.web.ui.transfer.UIFormItemAttributeValue;
import com.smartworks.invtmgmt.core.dao.LocationDao;
import com.smartworks.invtmgmt.web.ui.transfer.UIFormLocation;


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
	public ModelAndView displayTransaction(HttpServletRequest request, HttpServletResponse response, @RequestParam TransactionTypeEnum transactionTypeId) {
		List<Item> items = itemMgr.getAllItems();
		ModelMap myModel = new ModelMap();
        myModel.put("itemList", items);
        TransactionForm transactionForm = populateUIFormObjects();
        ModelAndView mav = new ModelAndView("transaction/InvMovement","model",myModel);
		mav.addObject("transactionForm", transactionForm);
		return mav;
	}
	@RequestMapping(value="/receive.form", method=RequestMethod.GET)
	public ModelAndView displayInventory(HttpServletRequest request, HttpServletResponse response, @RequestParam TransactionTypeEnum transactionTypeId) {
		List<Item> items = itemMgr.getAllItems();
		ModelMap myModel = new ModelMap();
        myModel.put("itemList", items);
        TransactionForm transactionForm = populateUIFormObjects();
        ModelAndView mav = new ModelAndView("transaction/ReceiveInventory","model",myModel);
		mav.addObject("receiveForm", transactionForm);
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView transferSKU(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("transactionForm") TransactionForm transactionForm) 
	{
		boolean error = false;
		try {
			
			List<UIFormItem> uiFormItems = transactionForm.getListUIFormItems();
			List<UIFormLocation> uiFormLocations = transactionForm.getLocationList();
			
			List<ItemSku> skus = new ArrayList<ItemSku>();
			int fromLocn = 4; // Master Warehouse
			int toLocn = -1;
			for (UIFormLocation uiFormLocation: uiFormLocations) {
				toLocn = Integer.parseInt(uiFormLocation.getSelectedValue());
			}
			
			for (UIFormItem uiformItem: uiFormItems) {
				if(uiformItem.getItemQty() !=null && uiformItem.getItemQty() > 0) {
					ItemSku sku = new ItemSku();
					Item item = new Item(uiformItem.getItemId());
					sku.setQuantity(uiformItem.getItemQty());
					sku.setItem(item);
					
					Map<ItemAttribute, ItemAttributeValue> itemAttributeDetails = new HashMap<ItemAttribute, ItemAttributeValue>();
					List<UIFormItemAttribute> uiFormItemAttributes = uiformItem.getUiFormItemAttributes();
					if(uiFormItemAttributes != null){
						for (UIFormItemAttribute uiFormItemAttribute: uiFormItemAttributes) {
							ItemAttribute itemAttrib = new ItemAttribute();
							ItemAttributeValue itemAttributeVal = new ItemAttributeValue();
							itemAttrib.setAttibuteId(uiFormItemAttribute.getItemAttributeId());
							itemAttributeVal.setAttributeValueId(Integer.parseInt(uiFormItemAttribute.getSelectedAttributeValue()));
							System.out.println("Name"+uiFormItemAttribute.getItemAttributeId());
							System.out.println("Value"+uiFormItemAttribute.getSelectedAttributeValue());
							itemAttributeDetails.put(itemAttrib, itemAttributeVal);
						}
						sku.setItemAttributeDetails(itemAttributeDetails);
						uiFormItemAttributes = null;
					}
					skus.add(sku);
				 }
				}
			
			boolean retval= invtTransMgr.transferInventory(fromLocn,toLocn,skus);
			} catch (Exception e) {
				// TODO: handle exception
				error = true;
			}
	
		
		List<Item> items = itemMgr.getAllItems();
		ModelMap myModel = new ModelMap();
        myModel.put("itemList", items);
        transactionForm = populateUIFormObjects();
        if(error) {
        	transactionForm.getLocationList().get(0).setError("Failed to transfer");
        } else {
        	transactionForm.getLocationList().get(0).setError("success");
        }
       
		ModelAndView mav = new ModelAndView("transaction/InvMovement","model",myModel);
		mav.addObject("transactionForm", transactionForm);
		
		return mav;
	}
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView receiveInventory(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("receiveForm") TransactionForm transactionForm) 
	{
		boolean error = false;
		try {
			
			List<UIFormItem> uiFormItems = transactionForm.getListUIFormItems();
			
			List<ItemSku> skus = new ArrayList<ItemSku>();
			int toLocation = 4; // Master Warehouse
			
			for (UIFormItem uiformItem: uiFormItems) {
				if(uiformItem.getItemQty() !=null && uiformItem.getItemQty() > 0) {
					ItemSku sku = new ItemSku();
					Item item = new Item(uiformItem.getItemId());
					sku.setQuantity(uiformItem.getItemQty());
					sku.setItem(item);
					
					Map<ItemAttribute, ItemAttributeValue> itemAttributeDetails = new HashMap<ItemAttribute, ItemAttributeValue>();
					List<UIFormItemAttribute> uiFormItemAttributes = uiformItem.getUiFormItemAttributes();
					if(uiFormItemAttributes != null){
						for (UIFormItemAttribute uiFormItemAttribute: uiFormItemAttributes) {
							ItemAttribute itemAttrib = new ItemAttribute();
							ItemAttributeValue itemAttributeVal = new ItemAttributeValue();
							itemAttrib.setAttibuteId(uiFormItemAttribute.getItemAttributeId());
							itemAttributeVal.setAttributeValueId(Integer.parseInt(uiFormItemAttribute.getSelectedAttributeValue()));
							System.out.println("Name"+uiFormItemAttribute.getItemAttributeId());
							System.out.println("Value"+uiFormItemAttribute.getSelectedAttributeValue());
							itemAttributeDetails.put(itemAttrib, itemAttributeVal);
						}
						sku.setItemAttributeDetails(itemAttributeDetails);
						uiFormItemAttributes = null;
					}
					skus.add(sku);
				 }
				}
			
			boolean retval= invtTransMgr.receiveInventory(toLocation, skus);
			} catch (Exception e) {
				// TODO: handle exception
				error = true;
			}
	
		
		List<Item> items = itemMgr.getAllItems();
		ModelMap myModel = new ModelMap();
        myModel.put("itemList", items);
        transactionForm = populateUIFormObjects();
        if(error) {
        	transactionForm.getLocationList().get(0).setError("Failed to transfer");
        } else {
        	transactionForm.getLocationList().get(0).setError("success");
        }
       
		ModelAndView mav = new ModelAndView("transaction/ReceiveInventory","model",myModel);
		mav.addObject("receiveForm", transactionForm);
		
		return mav;
	}
private List<UIFormLocation> populateUIFormLocations() {

	List <Location> locationList =  locDao.loadSecondaryLocations();
	List<UIFormLocation> uiFormLocations = new ArrayList<UIFormLocation>();
	for(Location loc: locationList) {
		UIFormLocation uiFormLocation = new UIFormLocation();
		uiFormLocation.setLocationId(loc.getLocationId());
		uiFormLocation.setLocationName(loc.getLocationName());
		uiFormLocations.add(uiFormLocation);
	
	}
	
	return uiFormLocations;
	
}
private TransactionForm populateUIFormObjects() {
	
	List<Item> items = itemMgr.getAllItems();
	TransactionForm transactionForm = new TransactionForm();
	List<UIFormItem> uiFormItems = new ArrayList<UIFormItem>();
	for(Item item: items) {
		UIFormItem uiFormItem = new UIFormItem();
		uiFormItem.setItemId(item.getId());
		uiFormItem.setItemName(item.getName());
		
		Map<ItemAttribute, List<ItemAttributeValue>> itemAttributeMapping = item.getAttributeDetails();
		List<UIFormItemAttribute> uiFormItemAttributes = new ArrayList<UIFormItemAttribute>();

		for(ItemAttribute itemAttribute: itemAttributeMapping.keySet()) {
			UIFormItemAttribute uiFormItemAttribute = new UIFormItemAttribute();
			uiFormItemAttribute.setItemId(item.getId());
			uiFormItemAttribute.setItemAttributeId(itemAttribute.getAttibuteId());
			uiFormItemAttribute.setItemAttributeName(itemAttribute.getAttributeName());
			
			List<UIFormItemAttributeValue> uiFormItemAttributeValues = new ArrayList<UIFormItemAttributeValue>();
			for(ItemAttributeValue itemAttributeValue: itemAttributeMapping.get(itemAttribute)) {
				UIFormItemAttributeValue uiFormItemAttributeValue = new UIFormItemAttributeValue();
				uiFormItemAttributeValue.setItemId(item.getId());
				uiFormItemAttributeValue.setItemAttributeId(itemAttribute.getAttibuteId());
				uiFormItemAttributeValue.setItemAttributeValueId(itemAttributeValue.getAttributeValueId());
				uiFormItemAttributeValue.setItemAttributeValue(itemAttributeValue.getAttributeValue());
				uiFormItemAttributeValues.add(uiFormItemAttributeValue);
			}
			
			uiFormItemAttribute.setItemAttributeValues(uiFormItemAttributeValues);
			uiFormItemAttributes.add(uiFormItemAttribute);
		}
		uiFormItem.setUiFormItemAttributes(uiFormItemAttributes);
		uiFormItems.add(uiFormItem);
	}
	transactionForm.setLocationList(populateUIFormLocations());
	transactionForm.setListUIFormItems(uiFormItems);
	return transactionForm;
}

}