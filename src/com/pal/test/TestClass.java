package com.pal.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.smartworks.invtmgmt.business.ItemSku;
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
			if(itemAttrDetails != null) {
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
			} else
			{
				System.out.println("Item Attribute is null");
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
	
	public void getItemsWithoutAttributes() {
		ItemMgr itemMgr = AppContextUtil.getBean("itemMgr");
		List<Item> items = itemMgr.getAllItemsWithoutAttribute();
		//displayItems(items);
	}
	
	public void getItemWithoutAttributes() {
		ItemMgr itemMgr = AppContextUtil.getBean("itemMgr");
		Item i = itemMgr.getItemWithoutAttribute(1);
	}
	
	public void transferInventory() {
		List<ItemSku> itemSku = new ArrayList<ItemSku>();
		ItemSku sku = new ItemSku();
		sku.setItemId(1);
		sku.setQuantity(1000);
		sku.setReqProcessing(true);
		Map<Integer,Integer> attrMap = new HashMap<Integer, Integer>();
		attrMap.put(1, 1);
		attrMap.put(2, 11);
		sku.setItemAttribute(attrMap);
		
		itemSku.add(sku);
		
		sku = new ItemSku();
		sku.setItemId(1);
		sku.setQuantity(1000);
		sku.setReqProcessing(true);
		attrMap = new HashMap<Integer, Integer>();
		attrMap.put(1, 2);
		attrMap.put(2, 11);
		sku.setItemAttribute(attrMap);
		
		itemSku.add(sku);
		

		InvtTransManager inventoryMgr = AppContextUtil.getBean("invtTransMgr");
		
		inventoryMgr.transferInventory(1, 2, itemSku);
		
		
	}
	
	public void receiveInventory() {
		List<ItemSku> itemSku = new ArrayList<ItemSku>();
		ItemSku sku = new ItemSku();
		sku.setItemId(2);
		sku.setQuantity(1000);
		sku.setReqProcessing(true);
		Map<Integer,Integer> attrMap = new HashMap<Integer, Integer>();
		attrMap.put(1, 1);
		attrMap.put(2, 11);
		sku.setItemAttribute(attrMap);
		
		itemSku.add(sku);
		
		sku = new ItemSku();
		sku.setItemId(2);
		sku.setQuantity(1000);
		sku.setReqProcessing(true);
		attrMap = new HashMap<Integer, Integer>();
		attrMap.put(1, 2);
		attrMap.put(2, 11);
		sku.setItemAttribute(attrMap);
		
		itemSku.add(sku);
		
		InvtTransManager inventoryMgr = AppContextUtil.getBean("invtTransMgr");
		
		inventoryMgr.receiveInventory(1, itemSku);
		
		
	}
	
	public void processInvChange() {
		List<ItemSku> itemSku = new ArrayList<ItemSku>();
		ItemSku sku = new ItemSku();
		sku.setItemId(2);
		sku.setQuantity(50);
		sku.setReqProcessing(true);
		Map<Integer,Integer> attrMap = new HashMap<Integer, Integer>();
		attrMap.put(1, 1);
		attrMap.put(2, 11);
		sku.setItemAttribute(attrMap);
		
		itemSku.add(sku);
		
		sku = new ItemSku();
		sku.setItemId(2);
		sku.setQuantity(100);
		sku.setReqProcessing(true);
		attrMap = new HashMap<Integer, Integer>();
		attrMap.put(1, 2);
		attrMap.put(2, 11);
		sku.setItemAttribute(attrMap);
		
		itemSku.add(sku);
		
		InvtTransManager inventoryMgr = AppContextUtil.getBean("invtTransMgr");
		inventoryMgr.processInventoryChange(TransactionTypeEnum.ISSUE_UNIFORM_STAFF, 2, itemSku);
		
	}
	
	public void processInvChange1() {
		List<ItemSku> itemSku = new ArrayList<ItemSku>();
		ItemSku sku = new ItemSku();
		sku.setItemId(2);
		sku.setQuantity(50);
		sku.setReqProcessing(true);
		Map<Integer,Integer> attrMap = new HashMap<Integer, Integer>();
		attrMap.put(1, 1);
		attrMap.put(2, 11);
		sku.setItemAttribute(attrMap);
		
		itemSku.add(sku);
		
		sku = new ItemSku();
		sku.setItemId(2);
		sku.setQuantity(100);
		sku.setReqProcessing(true);
		attrMap = new HashMap<Integer, Integer>();
		attrMap.put(1, 2);
		attrMap.put(2, 11);
		sku.setItemAttribute(attrMap);
		
		itemSku.add(sku);
		
		InvtTransManager inventoryMgr = AppContextUtil.getBean("invtTransMgr");
		inventoryMgr.processInventoryChange(TransactionTypeEnum.RETURN_UNIFORM_STUDENT, 2, itemSku);
		
	}
	
	

}
