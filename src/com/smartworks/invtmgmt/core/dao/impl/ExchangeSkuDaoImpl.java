package com.smartworks.invtmgmt.core.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.domain.ExchangeSkuRecord;

public class ExchangeSkuDaoImpl extends HibernateDaoSupport {

	public Integer save(ExchangeSkuRecord exchangeSkuRecord) {
		return (Integer) getHibernateTemplate().save(exchangeSkuRecord);
	}
}
