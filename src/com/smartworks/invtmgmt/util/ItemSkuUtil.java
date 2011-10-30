package com.smartworks.invtmgmt.util;

import java.util.Map;

import com.smartworks.invtmgmt.business.ItemSku;

/**
 * Utility class to convert item into itemSkuCode and vice versa
 * @author Palanivel Rajan B
 *
 */
public class ItemSkuUtil {
	public static String SKU_DELIMITER = "-";
	public static String ATTR_DELIMITER = ":";
	
	/**
	 * Calculates skuCode from {@link ItemSku} 
	 * @param itemSku
	 * @return sku code
	 */
	public static String getItemSkuCode(ItemSku itemSku) {
		StringBuilder sb = new StringBuilder();
		sb.append(itemSku.getItemId());
		if(itemSku.getItemAttribute().size() > 0) {
			sb.append(SKU_DELIMITER);
			for (Integer attributeId : itemSku.getItemAttribute().keySet()) {
				Integer attrValue = itemSku.getItemAttribute().get(attributeId);
				sb.append(attributeId+ATTR_DELIMITER+attrValue+SKU_DELIMITER);			
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
	public static ItemSku getItemSku(String itemSkuCode) {
		ItemSku itemSku = new ItemSku();
		Map<Integer, Integer> attDetails = itemSku.getItemAttribute();
		
		String[] skuSplit = itemSkuCode.split("-");
		//First entry is always the items
		String itemIdStr = skuSplit[0];
		itemSku.setItemId(Integer.valueOf(itemIdStr));
		
		if(skuSplit.length > 1) {
			for(int i = 1 ; i < skuSplit.length ; i++) {
				String[] attrDetails = skuSplit[i].split(ATTR_DELIMITER);
				String attKey = attrDetails[0];
				String attVal = attrDetails[1];
				attDetails.put(Integer.valueOf(attKey), Integer.valueOf(attVal));
			}
		}		
		return itemSku;
	}
	
	public static void main(String[] args) {
		String itemCodeStrSample = "1-6:3-4:5";
		getItemSku(itemCodeStrSample);
		
		ItemSku sku = new ItemSku();
		sku.setItemId(10);
		Map<Integer, Integer> attDetails = sku.getItemAttribute();
		attDetails.put(1, 2);
		attDetails.put(3, 4);
		System.out.println(getItemSkuCode(sku));
		
		
	}
}
