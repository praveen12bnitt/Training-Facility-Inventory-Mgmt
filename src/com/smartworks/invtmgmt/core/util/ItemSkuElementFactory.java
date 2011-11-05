package com.smartworks.invtmgmt.core.util;

import org.springframework.util.AutoPopulatingList.ElementFactory;
import org.springframework.util.AutoPopulatingList.ElementInstantiationException;

import com.smartworks.invtmgmt.business.ItemSku;

public class ItemSkuElementFactory implements ElementFactory<ItemSku> {
	@Override
	public ItemSku createElement(int index)
			throws ElementInstantiationException {
		ItemSku itemSku = new ItemSku();
		return itemSku;
	}
}
