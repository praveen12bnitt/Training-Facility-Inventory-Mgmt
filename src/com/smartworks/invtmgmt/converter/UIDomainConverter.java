package com.smartworks.invtmgmt.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.ItemAttribute;
import com.smartworks.invtmgmt.core.domain.ItemAttributeValue;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;
import com.smartworks.invtmgmt.web.ui.form.IssueSkuForm;
import com.smartworks.invtmgmt.web.ui.form.TransactionForm;
import com.smartworks.invtmgmt.web.ui.transfer.UIFormItem;
import com.smartworks.invtmgmt.web.ui.transfer.UIFormItemAttribute;

public class UIDomainConverter {
	
	public static TransactionForm transferFromDomain(Object obj) {
		return null;
	
	}
	public static TransactionDetailsHolder transferToTransactionDetailsHolder(TransactionForm transactionForm) {
		List<UIFormItem> uiFormItems = transactionForm.getListUIFormItems();
			
		List<ItemSku> skus = new ArrayList<ItemSku>();
		
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
					//sku.setItemAttributeDetails(itemAttributeDetails);
					uiFormItemAttributes = null;
				}
				skus.add(sku);
			 }
			}
		
		
		TransactionDetailsHolder transDetails = new TransactionDetailsHolder();
		transDetails.setItemSkus(skus);
		transDetails.setLocationId(transactionForm.getTargetLocation());
		transDetails.setUserId(100);
		transDetails.setTransactionType(TransactionTypeEnum.ISSUE_UNIFORM_STUDENT);
		return transDetails;
	}
	
	public static TransactionDetailsHolder transferToTransactionDetailsHolder(IssueSkuForm issueSkuForm) {
		TransactionDetailsHolder transDetails = new TransactionDetailsHolder();
		transDetails.setItemSkus(issueSkuForm.getItemSkus());
		transDetails.setLocationId(issueSkuForm.getLocationId());
		transDetails.setUserId(100);
		transDetails.setTransactionType(issueSkuForm.getTransactionType());
		return transDetails;
	}
	
}
