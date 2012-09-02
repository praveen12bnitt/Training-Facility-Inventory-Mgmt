package com.smartworks.invtmgmt.core.service;

import org.springframework.util.AutoPopulatingList.ElementFactory;
import org.springframework.util.AutoPopulatingList.ElementInstantiationException;

import com.smartworks.invtmgmt.business.ExchangeInvt;

public class ExchangeInvElementFactory implements ElementFactory<ExchangeInvt> {
	@Override
	public ExchangeInvt createElement(int index)
			throws ElementInstantiationException {
		ExchangeInvt exchangeInvt = new ExchangeInvt();
		return exchangeInvt;
	}
}
