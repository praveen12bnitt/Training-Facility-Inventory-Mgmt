package com.smartworks.invtmgmt.core.util;

import org.springframework.util.AutoPopulatingList.ElementFactory;
import org.springframework.util.AutoPopulatingList.ElementInstantiationException;

import com.smartworks.invtmgmt.web.ui.transfer.UIProduct;

public class ProductElementFactory implements ElementFactory<UIProduct> {

	@Override
	public UIProduct createElement(int arg0)
			throws ElementInstantiationException {
		UIProduct uiProduct = new UIProduct();
		return uiProduct;
	}
	
}
