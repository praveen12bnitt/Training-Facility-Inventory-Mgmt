package com.smartworks.invtmgmt.core.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.domain.ExchangeSkuRecord;
import com.smartworks.invtmgmt.core.domain.Laundry;
import com.smartworks.invtmgmt.core.service.DateUtil;

public class ExchangeSkuDaoImpl extends HibernateDaoSupport {

	public Integer save(ExchangeSkuRecord exchangeSkuRecord) {
		return (Integer) getHibernateTemplate().save(exchangeSkuRecord);
	}
	
	public List<ExchangeSkuRecord> loadAll(String fromDate, String toDate) {
		
		String query = "from ExchangeSkuRecord where  " +
				"dttm between :fromDate and :toDate";
		String[] params = {"fromDate", "toDate"};
		Date fromTimeDtamp = DateUtil.getDate(fromDate);
		Date toTimeDtamp = DateUtil.getDate(toDate);
		Object[] values = { new Timestamp(fromTimeDtamp.getTime()), new Timestamp(toTimeDtamp.getTime())};
		
		List<ExchangeSkuRecord> exchangeList = new ArrayList<ExchangeSkuRecord>();
		exchangeList = getHibernateTemplate().findByNamedParam(query,params, values);
		
		return exchangeList;
	}
}
