package com.pal.test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.ItemAttribute;
import com.smartworks.invtmgmt.core.domain.ItemAttributeValue;
import com.smartworks.invtmgmt.core.domain.TransactionType;
import com.smartworks.invtmgmt.core.manager.InvtTransManager;
import com.smartworks.invtmgmt.core.manager.ItemMgr;
import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;
import com.smartworks.platform.AppContextUtil;

public class TestClass {

	public void testTransLoading() {
		InvtTransManager inventoryMgr = AppContextUtil.getBean("invtTransMgr");
		List<TransactionType> transTypes = inventoryMgr.getAllTrans();
		displayTransList(transTypes);

	}

	public void testTransLocation() {
		InvtTransManager inventoryMgr = AppContextUtil.getBean("invtTransMgr");
		List<TransactionType> transTypes = inventoryMgr
				.getTransactionsForLocation(new Integer(2));
		displayTransList(transTypes);
	}

	public void displayTransList(List<TransactionType> transTypes) {
		for (TransactionType transactionType : transTypes) {
			System.out.println(transactionType.getTransType());
			System.out.println(transactionType.getLocation().getLocationName());
			System.out.println(transactionType.getTransactionDesc());
		}
	}

	public void displayItems(List<Item> items) {
		for (Item item : items) {
			String desc1 = item.getDesc();
			int id1 = item.getId();
			String name1 = item.getName();

			System.out.println("id:" + id1);
			System.out.println(" name:" + name1);
			System.out.println(" desc:" + desc1);

			System.out
					.println("*****************************************************");

			// Get the attribute details
			Map<ItemAttribute, List<ItemAttributeValue>> itemAttrDetails;
			itemAttrDetails = item.getAttributeDetails();
			Set<ItemAttribute> attrs;
			attrs = itemAttrDetails.keySet();

			for (ItemAttribute itemAttribute : attrs) {
				System.out.println("Attribute Name:"
						+ itemAttribute.getAttributeName());
				List<ItemAttributeValue> values = itemAttrDetails
						.get(itemAttribute);
				Collections.sort(values);
				System.out.println("<br/>Attribute Values:");
				for (ItemAttributeValue itemAttributeValue : values) {
					System.out.println(itemAttributeValue.getAttributeValue()
							+ ",");
				}
				System.out.println("");
			}
		}
	}

	public void getItemsForTransactions() {
		ItemMgr itemMgr = AppContextUtil.getBean("itemMgr");
		// Get transactionType Obj
		InvtTransManager inventoryMgr = AppContextUtil.getBean("invtTransMgr");
		TransactionType issueUniform = inventoryMgr
				.getTransType(TransactionTypeEnum.ISSUE_UNIFORM_STUDENT);
		List<Item> items = itemMgr.getItemsForTransaction(issueUniform);
		
		displayItems(items);

	}

}
