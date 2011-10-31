package com.smartworks.invtmgmt.web.ui.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import com.smartworks.invtmgmt.core.dao.TransactionTypeDao;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.ItemAttribute;
import com.smartworks.invtmgmt.core.domain.ItemAttributeValue;
import com.smartworks.invtmgmt.core.domain.TransactionType;
import com.smartworks.invtmgmt.core.manager.InvtTransManager;
import com.smartworks.invtmgmt.core.manager.ItemMgr;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;
import com.smartworks.invtmgmt.web.ui.form.TransactionForm;
import com.smartworks.invtmgmt.web.ui.transfer.UIFormItem;
import com.smartworks.invtmgmt.web.ui.transfer.UIFormItemAttribute;
import com.smartworks.invtmgmt.web.ui.transfer.UIFormItemAttributeValue;
import com.smartworks.platform.AppContextUtil;

@Controller
@RequestMapping("/createtransaction")
public class TransactionFormController {
	
	@Autowired
	ItemMgr itemMgr = null;
	@Autowired
	TransactionTypeDao transactionTypeDao = null;
	@Autowired
	InvtTransManager invtTransMgr = null;
  
	@RequestMapping(value="/issue.form", method=RequestMethod.GET)
	public ModelAndView displayTransaction(HttpServletRequest request, HttpServletResponse response, @RequestParam TransactionTypeEnum transactionTypeId) {
		
		
		TransactionType transactionType = transactionTypeDao.load(transactionTypeId);
		List<Item> items = itemMgr.getItemsForTransaction(transactionType);
		
		ModelMap myModel = new ModelMap();
        myModel.put("itemList", items);
        TransactionForm transactionForm = populateUIFormObjects(transactionType);
        //request.setAttribute("transactionForm", transactionForm);
		ModelAndView mav = new ModelAndView("createtransaction","model",myModel);
		mav.addObject("transactionForm", transactionForm);
		
		
		return mav;
	}
	
	@RequestMapping(value="/sayHai.form", method=RequestMethod.GET)
	public void sayHai(@RequestParam String str,  HttpServletResponse response) throws IOException{
		response.getWriter().println("Said Hai to:"+str);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView createTransaction(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("transactionForm") TransactionForm transactionForm) 
	{
		System.out.println(request);
		return null;
	}
	
	private TransactionForm populateUIFormObjects(TransactionType transactionType) {
		

		
		List<Item> items = itemMgr.getItemsForTransaction(transactionType);
		TransactionForm transactionForm = new TransactionForm();
		transactionForm.setTransactionType(transactionType.getTransactionDesc());
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
		transactionForm.setListUIFormItems(uiFormItems);
		return transactionForm;
	}
	
	 
	
	/**public List getInventoryListItems(List<Item> items) {
		Inventory inventory = null;
		for(Item itemObject: items) {
			inventory = new Inventory();
			inventory.setItemId(itemObject.getId());
			
			Map<ItemAttribute, List<ItemAttributeValue>> itemAttributeDetails = itemObject.getAttributeDetails();
			for(ItemAttribute itemAttribute: itemAttributeDetails.keySet()) {
				inventory.set
			}
			
		}
		return null;
	}*/
	


}