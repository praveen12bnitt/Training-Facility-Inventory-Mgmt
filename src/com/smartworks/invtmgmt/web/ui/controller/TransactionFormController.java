package com.smartworks.invtmgmt.web.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/createtransaction")
@Deprecated
public class TransactionFormController {
	
//	
//	@Autowired
//	ItemMgr itemMgr = null;
//	@Autowired
//	TransactionTypeDao transactionTypeDao = null;
//	@Autowired
//	InvtTransManager invtTransMgr = null;
//	@Autowired
//	LocationDao locationDao = null;
//  
//	@RequestMapping(value="/issue.form", method=RequestMethod.GET)
//	public ModelAndView displayTransaction(HttpServletRequest request, HttpServletResponse response, @RequestParam TransactionTypeEnum transactionTypeId, 
//			@RequestParam(required = false) Integer locationId) {
//		
//		
//		TransactionType transactionType = transactionTypeDao.load(transactionTypeId);
//		
//        TransactionForm transactionForm = populateUIFormObjects(transactionType, request);
//        //request.setAttribute("transactionForm", transactionForm);
//		ModelAndView mav = new ModelAndView("transaction/createtransaction");
//		mav.addObject("transactionForm", transactionForm);
//		if(locationId != null) {
//			mav.addObject("location", locationDao.load(locationId));
//		}
//		return mav;
//	}
//	
//	
//	@RequestMapping(value="/login.form", method=RequestMethod.GET)
//	public ModelAndView getItemPrice(HttpServletResponse response) throws IOException{		
//		ModelAndView mav = new ModelAndView("transaction/login");
//		return mav;
//	}
//	
//	
//	@RequestMapping(method=RequestMethod.POST)
//	public ModelAndView createTransaction(HttpServletRequest request,
//			HttpServletResponse response, @ModelAttribute("transactionForm") TransactionForm transactionForm) 
//	{
//		TransactionDetailsHolder transDetailsHolder = UIDomainConverter.transferToTransactionDetailsHolder(transactionForm);
//		boolean qtyIssued = false;
//		try {
//			qtyIssued =	invtTransMgr.processInventoryChange(transDetailsHolder);
//		} catch(Exception ex) {
//			ex.printStackTrace();
//		}
//		ModelAndView mav = new ModelAndView("transaction/createtransaction");
//		mav.addObject("transactionForm", transactionForm);
//		mav.addObject("location", locationDao.load(transactionForm.getTargetLocation()));
//		String transactionFormMessage = (qtyIssued)?"Quantity Issue Succeeded":"Quantity Issue Failed";
//		mav.addObject("transactionFormMessage", transactionFormMessage);
//		return mav;
//		
//	}
//	
//	private TransactionForm populateUIFormObjects(TransactionType transactionType,HttpServletRequest request) {
//			
//		List<Item> items = itemMgr.getItemsForTransaction(transactionType);
//		TransactionForm transactionForm = new TransactionForm();
//		transactionForm.setTransactionType(transactionType.getTransactionDesc());
//		List<UIFormItem> uiFormItems = new ArrayList<UIFormItem>();
//		
//		for(Item item: items) {
//			UIFormItem uiFormItem = new UIFormItem();
//			uiFormItem.setItemId(item.getId());
//			uiFormItem.setItemName(item.getName());
//			
//			Map<ItemAttribute, List<ItemAttributeValue>> itemAttributeMapping = item.getAttributeDetails();
//			List<UIFormItemAttribute> uiFormItemAttributes = new ArrayList<UIFormItemAttribute>();
//
//			for(ItemAttribute itemAttribute: itemAttributeMapping.keySet()) {
//				UIFormItemAttribute uiFormItemAttribute = new UIFormItemAttribute();
//				uiFormItemAttribute.setItemId(item.getId());
//				uiFormItemAttribute.setItemAttributeId(itemAttribute.getAttibuteId());
//				uiFormItemAttribute.setItemAttributeName(itemAttribute.getAttributeName());
//				
//				List<UIFormItemAttributeValue> uiFormItemAttributeValues = new ArrayList<UIFormItemAttributeValue>();
//				for(ItemAttributeValue itemAttributeValue: itemAttributeMapping.get(itemAttribute)) {
//					UIFormItemAttributeValue uiFormItemAttributeValue = new UIFormItemAttributeValue();
//					uiFormItemAttributeValue.setItemId(item.getId());
//					uiFormItemAttributeValue.setItemAttributeId(itemAttribute.getAttibuteId());
//					uiFormItemAttributeValue.setItemAttributeValueId(itemAttributeValue.getAttributeValueId());
//					uiFormItemAttributeValue.setItemAttributeValue(itemAttributeValue.getAttributeValue());
//					uiFormItemAttributeValues.add(uiFormItemAttributeValue);
//				}
//				
//				uiFormItemAttribute.setItemAttributeValues(uiFormItemAttributeValues);
//				uiFormItemAttributes.add(uiFormItemAttribute);
//			}
//			uiFormItem.setUiFormItemAttributes(uiFormItemAttributes);
//			uiFormItems.add(uiFormItem);
//		}
//		transactionForm.setTargetLocation(2);
//		transactionForm.setListUIFormItems(uiFormItems);
//		return transactionForm;
//	}
//	

}