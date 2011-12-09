package com.smartworks.invtmgmt.core.dao;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.ReasonCode;
import com.smartworks.invtmgmt.core.transaction.ReasonCodeEnum;

public interface ReasonCodeDao {

	public ReasonCode load(ReasonCodeEnum reasonCode);
	public List<ReasonCode> loadAll() ;
}
