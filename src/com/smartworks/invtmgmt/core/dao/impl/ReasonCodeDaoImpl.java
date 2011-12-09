package com.smartworks.invtmgmt.core.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.ReasonCodeDao;
import com.smartworks.invtmgmt.core.domain.ReasonCode;
import com.smartworks.invtmgmt.core.transaction.ReasonCodeEnum;


public class ReasonCodeDaoImpl extends HibernateDaoSupport implements ReasonCodeDao {

	@Override
	public ReasonCode load(ReasonCodeEnum reasonCode) {
		return getHibernateTemplate().load(ReasonCode.class, reasonCode);
	}

	@Override
	public List<ReasonCode> loadAll() {
		String qStr = "from ReasonCode";
		List<ReasonCode> reasonCodes = getHibernateTemplate().find(qStr);
		return reasonCodes;
	}
	

}
	