package com.pal.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.business.TransactionDetailsHolder;
import com.smartworks.invtmgmt.business.UserTransactionDetails;
import com.smartworks.invtmgmt.core.dao.ItemAttributeDao;
import com.smartworks.invtmgmt.core.dao.ItemAttributeValueDao;
import com.smartworks.invtmgmt.core.dao.ItemDao;
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
		ItemDao itemDao = AppContextUtil.getBean("itemDao");
		ItemAttributeDao itemAttributeDao = AppContextUtil.getBean("itemAttributeDao");
		ItemAttributeValueDao itemAttributeValueDao = AppContextUtil.getBean("itemAttributeValueDao");
		List<ItemSku> itemSku = new ArrayList<ItemSku>();
		ItemSku sku = new ItemSku();
		sku.setItem(itemDao.load(1));
		sku.setQuantity(500);
		Map<ItemAttribute,ItemAttributeValue> attrMap = new HashMap<ItemAttribute, ItemAttributeValue>();
		
		attrMap.put(itemAttributeDao.load(1), itemAttributeValueDao.load(1));
		attrMap.put(itemAttributeDao.load(2), itemAttributeValueDao.load(11));
		sku.setItemAttributeDetails(attrMap);
		
		itemSku.add(sku);
		
		sku = new ItemSku();
		sku.setItem(itemDao.load(1));
		sku.setQuantity(600);
		attrMap = new HashMap<ItemAttribute, ItemAttributeValue>();
		attrMap.put(itemAttributeDao.load(1), itemAttributeValueDao.load(2));
		attrMap.put(itemAttributeDao.load(2), itemAttributeValueDao.load(11));
		sku.setItemAttributeDetails(attrMap);
		
		itemSku.add(sku);
		

		InvtTransManager inventoryMgr = AppContextUtil.getBean("invtTransMgr");
		
		TransactionDetailsHolder transDetails = new TransactionDetailsHolder();
		transDetails.setLocationId(2);
		transDetails.setSrcLocationId(1);
		transDetails.setItemSkus(itemSku);
		transDetails.setUserId(100);
		Date date= new Date();
		System.out.println(new Timestamp(date.getTime()));
		transDetails.setDttm(new Timestamp(date.getTime()));
		transDetails.setTransactionType(TransactionTypeEnum.TRANSFER_INVENTORY);
		
		inventoryMgr.processInventoryChange(transDetails);
		
		
	}
	
	public void receiveInventory() {
		
		ItemDao itemDao = AppContextUtil.getBean("itemDao");
		ItemAttributeDao itemAttributeDao = AppContextUtil.getBean("itemAttributeDao");
		ItemAttributeValueDao itemAttributeValueDao = AppContextUtil.getBean("itemAttributeValueDao");
		
		List<ItemSku> itemSku = new ArrayList<ItemSku>();
		ItemSku sku = new ItemSku();
		sku.setItem(itemDao.load(1));
		sku.setQuantity(1000);
		Map<ItemAttribute,ItemAttributeValue> attrMap = new HashMap<ItemAttribute, ItemAttributeValue>();
		attrMap.put(itemAttributeDao.load(1), itemAttributeValueDao.load(1));
		attrMap.put(itemAttributeDao.load(2), itemAttributeValueDao.load(11));
		sku.setItemAttributeDetails(attrMap);
		
		itemSku.add(sku);
		
		sku = new ItemSku();
		sku.setItem(itemDao.load(1));
		sku.setQuantity(1000);
		attrMap = new HashMap<ItemAttribute, ItemAttributeValue>();
		attrMap.put(itemAttributeDao.load(1), itemAttributeValueDao.load(2));
		attrMap.put(itemAttributeDao.load(2), itemAttributeValueDao.load(11));
		sku.setItemAttributeDetails(attrMap);
		
		itemSku.add(sku);
		
		TransactionDetailsHolder transDetails = new TransactionDetailsHolder();
		transDetails.setLocationId(1);
		transDetails.setSrcLocationId(-1);
		transDetails.setItemSkus(itemSku);
		transDetails.setUserId(100);
		Date date= new Date();
		System.out.println(new Timestamp(date.getTime()));
		transDetails.setDttm(new Timestamp(date.getTime()));
		transDetails.setTransactionType(TransactionTypeEnum.TRANSFER_INVENTORY);
		
		InvtTransManager inventoryMgr = AppContextUtil.getBean("invtTransMgr");
		
		inventoryMgr.processInventoryChange(transDetails);
		
		
	}
	
	public void processInvChange() {
		
		ItemDao itemDao = AppContextUtil.getBean("itemDao");
		ItemAttributeDao itemAttributeDao = AppContextUtil.getBean("itemAttributeDao");
		ItemAttributeValueDao itemAttributeValueDao = AppContextUtil.getBean("itemAttributeValueDao");
		
		
		List<ItemSku> itemSku = new ArrayList<ItemSku>();
		ItemSku sku = new ItemSku();
		sku.setItem(itemDao.load(1));
		sku.setQuantity(100);
		Map<ItemAttribute,ItemAttributeValue> attrMap = new HashMap<ItemAttribute, ItemAttributeValue>();
		attrMap.put(itemAttributeDao.load(1), itemAttributeValueDao.load(1));
		attrMap.put(itemAttributeDao.load(2), itemAttributeValueDao.load(11));
		sku.setItemAttributeDetails(attrMap);
		
		itemSku.add(sku);
		
		sku = new ItemSku();
		sku.setItem(itemDao.load(1));
		sku.setQuantity(50);
		attrMap = new HashMap<ItemAttribute, ItemAttributeValue>();
		attrMap.put(itemAttributeDao.load(1), itemAttributeValueDao.load(2));
		attrMap.put(itemAttributeDao.load(2), itemAttributeValueDao.load(11));
		sku.setItemAttributeDetails(attrMap);
		
		itemSku.add(sku);
		
		InvtTransManager inventoryMgr = AppContextUtil.getBean("invtTransMgr");
		
		TransactionDetailsHolder transDetails = new TransactionDetailsHolder();
		transDetails.setLocationId(2);
		transDetails.setItemSkus(itemSku);
		transDetails.setUserId(100);
		transDetails.setTraineeId(1);
		Date date= new Date();
		System.out.println(new Timestamp(date.getTime()));
		transDetails.setDttm(new Timestamp(date.getTime()));
		transDetails.setTransactionType(TransactionTypeEnum.ISSUE_UNIFORM_STUDENT);
		
		inventoryMgr.processInventoryChange(transDetails);
		
	}
	
	
	
	public void getTransactionForUser() {
		InvtTransManager inventoryMgr = AppContextUtil.getBean("invtTransMgr");
		List<UserTransactionDetails> utransDetails = inventoryMgr.getAllOpenTransactionForUser(2, 1, TransactionTypeEnum.ISSUE_UNIFORM_STUDENT);
		System.out.println("Result");
	}
	
	public void getTransDetails() {
		InvtTransManager inventoryMgr = AppContextUtil.getBean("invtTransMgr");
		TransactionDetailsHolder details = inventoryMgr.getTransDetails(8);
		System.out.println("Transaction details retrived");
	}
	
	public void processReturs() {
		
		// Get the transaction for the user..
		InvtTransManager inventoryMgr = AppContextUtil.getBean("invtTransMgr");
		List<UserTransactionDetails> utransDetails = inventoryMgr.getAllOpenTransactionForUser(2, 1, TransactionTypeEnum.ISSUE_UNIFORM_STUDENT);
		
		
		TransactionDetailsHolder details = inventoryMgr.getTransDetails(utransDetails.get(0).getTrasactionId());
		details.setTransactionType(TransactionTypeEnum.RETURN_UNIFORM_STUDENT);
		details.setRefTransactionId(utransDetails.get(0).getTrasactionId());
		System.out.println("Transaction details retrived");
		inventoryMgr.processInventoryChange(details);
	}
	
	public void processRetursWithMissing() {
		
		// Get the transaction for the user..
		InvtTransManager inventoryMgr = AppContextUtil.getBean("invtTransMgr");
		List<UserTransactionDetails> utransDetails = inventoryMgr.getAllOpenTransactionForUser(2, 1, TransactionTypeEnum.ISSUE_UNIFORM_STUDENT);
		
		
		TransactionDetailsHolder details = inventoryMgr.getTransDetails(utransDetails.get(0).getTrasactionId());
		details.setTransactionType(TransactionTypeEnum.RETURN_UNIFORM_STUDENT);
		
		ItemSku s = details.getItemSkus().get(0);
		s.setQuantity(s.getQuantity() - 5);
		
		details.setRefTransactionId(utransDetails.get(0).getTrasactionId());
		System.out.println("Transaction details retrived");
		inventoryMgr.processInventoryChange(details);
	}
	
	public void reportMissingItem() {
		
		ItemAttributeDao itemAttributeDao = AppContextUtil.getBean("itemAttributeDao");
		ItemAttributeValueDao itemAttributeValueDao = AppContextUtil.getBean("itemAttributeValueDao");
		ItemDao itemDao = AppContextUtil.getBean("itemDao");
		
		List<ItemSku> itemSku = new ArrayList<ItemSku>();
		ItemSku sku = new ItemSku();
		sku.setItem(itemDao.load(1));
		sku.setQuantity(10);
		Map<ItemAttribute,ItemAttributeValue> attrMap = new HashMap<ItemAttribute, ItemAttributeValue>();
		attrMap.put(itemAttributeDao.load(1), itemAttributeValueDao.load(1));
		attrMap.put(itemAttributeDao.load(2), itemAttributeValueDao.load(11));
		sku.setItemAttributeDetails(attrMap);
		
		itemSku.add(sku);
		
		sku = new ItemSku();
		sku.setItem(itemDao.load(1));
		sku.setQuantity(5);
		attrMap = new HashMap<ItemAttribute, ItemAttributeValue>();
		attrMap.put(itemAttributeDao.load(1), itemAttributeValueDao.load(2));
		attrMap.put(itemAttributeDao.load(2), itemAttributeValueDao.load(11));
		sku.setItemAttributeDetails(attrMap);
		
		itemSku.add(sku);
		
		InvtTransManager inventoryMgr = AppContextUtil.getBean("invtTransMgr");
		
		TransactionDetailsHolder transDetails = new TransactionDetailsHolder();
		transDetails.setLocationId(2);
		transDetails.setItemSkus(itemSku);
		transDetails.setUserId(100);
		transDetails.setTraineeId(1);
		transDetails.setTransactionType(TransactionTypeEnum.REPORT_MISSING_UNIFORM_STUDENT);
		
		inventoryMgr.processInventoryChange(transDetails);
	}
	
	
	
	

}
