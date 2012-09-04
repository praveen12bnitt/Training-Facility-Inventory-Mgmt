package com.smartworks.invtmgmt.converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartworks.invtmgmt.business.ItemAttributeDetails;
import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.core.dao.ItemDao;
import com.smartworks.invtmgmt.core.domain.Inventory;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.web.ui.transfer.inventory.UIInventory;

public class InventoryConverter {

	@Autowired
	private ItemDao itemDao;
	
	public UIInventory getUIInventory(Inventory inventory) {
		UIInventory uiInventory = new UIInventory();		
		uiInventory.setLocation(inventory.getSkuLocation().getLocation().getLocationName());
		ItemSku itemSku = inventory.getItemSku();
		uiInventory.setItemId(itemSku.getItem().getId());
		uiInventory.setItemDesc(itemSku.getItem().getName());
		uiInventory.setSkuCode(itemSku.getSkuCode());
		uiInventory.setAvailableQty(inventory.getAvailableQty());
		uiInventory.setIssuedQty(inventory.getIssueQty());
		uiInventory.setUnusableQty(inventory.getUnusableQty());
		uiInventory.setLocationId(inventory.getLocation().getLocationId());
		
		Item item = itemDao.load(itemSku.getItem().getId());
		uiInventory.setItemNumber(item.getItemNumber());
		uiInventory.setPrice(item.getPrice());
		
		StringBuilder sb = new StringBuilder();	
		
		for(ItemAttributeDetails itemAttributeDetails : itemSku.getItemAttributeDtls()) {
			String attrName = itemAttributeDetails.getItemAttribute().getAttributeName();			
			String attrVal = itemAttributeDetails.getItemAttributeValue().getAttributeValue();
			sb.append(attrName);
			sb.append(":");
			sb.append(attrVal);
			sb.append("  ");
		}		
		uiInventory.setItemAttributeDetails(sb.toString());		
		return uiInventory;
	}

	
}
