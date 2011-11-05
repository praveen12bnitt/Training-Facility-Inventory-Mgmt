package com.smartworks.invtmgmt.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.core.dao.ItemAttributeDao;
import com.smartworks.invtmgmt.core.dao.ItemAttributeValueDao;
import com.smartworks.invtmgmt.core.dao.ItemDao;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.ItemAttribute;
import com.smartworks.invtmgmt.core.domain.ItemAttributeValue;

/**
 * Utility class to convert item into itemSkuCode and vice versa
 * @author Palanivel Rajan B
 *
 */
public class ItemSkuConverter {
	public static String SKU_DELIMITER = "-";
	public static String ATTR_DELIMITER = ":";
	
	ItemDao itemDao;
	ItemAttributeDao itemAttributeDao;
	ItemAttributeValueDao itemAttributeValueDao;
	
	
	/**
	 * Calculates skuCode from {@link ItemSku} 
	 * @param itemSku
	 * @return sku code
	 */
	public String getItemSkuCode(ItemSku itemSku) {
		StringBuilder sb = new StringBuilder();
		Set<ItemAttribute> a =  itemSku.getItemAttributeDetails().keySet();
		sb.append(itemSku.getItem().getId());
		if(itemSku.getItemAttributeDetails().size() > 0) {
			sb.append(SKU_DELIMITER);			
			for(ItemAttribute itemAttribute :itemSku.getItemAttributeDetails().keySet() ) {
				Integer attrValue = itemSku.getItemAttributeDetails().get(itemAttribute).getAttributeValueId();
				sb.append(itemAttribute.getAttibuteId()+ATTR_DELIMITER+attrValue+SKU_DELIMITER);		
			}			
		}		
		String skuCode = sb.toString();
		if(skuCode.endsWith(SKU_DELIMITER)) {
			skuCode = skuCode.substring(0,skuCode.length()-1);
		}		
		return skuCode;
	}
	
	/**
	 * Converts skuCode into a {@link ItemSku} object
	 * @param itemSkuCode
	 * @return {@link ItemSku} object
	 */
	public ItemSku getItemSku(String itemSkuCode) {
		Map<ItemAttribute, ItemAttributeValue> attDetails = new HashMap<ItemAttribute, ItemAttributeValue>();
		
		String[] skuSplit = itemSkuCode.split("-");
		//First entry is always the items
		Integer itemId = Integer.valueOf(skuSplit[0]);
		Item item = itemDao.load(itemId);
		
		
		if(skuSplit.length > 1) {
			for(int i = 1 ; i < skuSplit.length ; i++) {
				String[] attrDetails = skuSplit[i].split(ATTR_DELIMITER);
				Integer attKey = Integer.valueOf(attrDetails[0]);
				Integer attVal = Integer.valueOf(attrDetails[1]);
				ItemAttribute attrObj = itemAttributeDao.load(attKey);
				ItemAttributeValue attrValObj = itemAttributeValueDao.load(attVal);
				attDetails.put(attrObj, attrValObj);
			}
		}			
		ItemSku itemSku = new ItemSku(item,attDetails);	
		return itemSku;
	}
	
	public ItemAttributeDao getItemAttributeDao() {
		return itemAttributeDao;
	}

	public ItemAttributeValueDao getItemAttributeValueDao() {
		return itemAttributeValueDao;
	}

	public void setItemAttributeDao(ItemAttributeDao itemAttributeDao) {
		this.itemAttributeDao = itemAttributeDao;
	}

	public void setItemAttributeValueDao(ItemAttributeValueDao itemAttributeValueDao) {
		this.itemAttributeValueDao = itemAttributeValueDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
}
