package com.smartworks.invtmgmt.core.transaction;

import java.util.ArrayList;
import java.util.List;

public enum ReasonCodeEnum {
	ITEM_MISSING,
	ITEM_DAMAGED;
	
	private static List<String> reasonCodeList;

	public static List<String> getReasonCodeList() {		
		if(reasonCodeList == null) {
			reasonCodeList = new ArrayList<String>();
			reasonCodeList.add(ReasonCodeEnum.ITEM_MISSING.toString());
			reasonCodeList.add(ReasonCodeEnum.ITEM_DAMAGED.toString());
		}		
		return reasonCodeList;
	}
	
	
}