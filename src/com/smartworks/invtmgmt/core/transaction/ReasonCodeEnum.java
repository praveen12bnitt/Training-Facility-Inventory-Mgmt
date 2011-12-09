package com.smartworks.invtmgmt.core.transaction;

public enum ReasonCodeEnum {

	ITEM_MISSING,
	ITEM_LOST,
	STUDENT_ITEM_MISSING,
	STUDENT_ITEM_LOST;
	
	public static ReasonCodeEnum getReasonCode(ReasonCodeEnum reasonCodeEnum)
	{
		ReasonCodeEnum returnEnum = null;
		switch(reasonCodeEnum) {
			case ITEM_MISSING:
				returnEnum = STUDENT_ITEM_MISSING;
				break;
			
			case ITEM_LOST:
				returnEnum = STUDENT_ITEM_LOST;
				break;
		}
		
		return returnEnum;
			
	}
	
}