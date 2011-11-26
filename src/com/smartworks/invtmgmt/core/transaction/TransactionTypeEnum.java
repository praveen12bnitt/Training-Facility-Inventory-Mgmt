package com.smartworks.invtmgmt.core.transaction;

public enum TransactionTypeEnum {

	ISSUE_UNIFORM_STUDENT,
	ISSUE_UNIFORM_STAFF,
	ISSUE_EQUIPMENT_STUDENT,
	ISSUE_EQUIPMENT_STAFF,
	ISSUE_GYM_STUDENT,
	ISSUE_GYM_STAFF,
	
	
	
	REPORT_MISSING_UNIFORM_STAFF,
	REPORT_MISSING_UNIFORM_STUDENT,
	
	RETURN_UNIFORM_STUDENT,
	RETURN_UNIFORM_STAFF,
	RETURN_EQUIPMENT_STUDENT,
	RETURN_EQUIPMENT_STAFF,
	RETURN_GYM_STUDENT,
	RETURN_GYM_STAFF,
	
	ACCEPT_FROM_LAUNDRY_UNIFORM,
	ACCEPT_FROM_LAUNDRY_EQUIPMENT,
	ACCEPT_FROM_LAUNDRY_GYM,	
	
	TRANSFER_INVENTORY;
	
	public static TransactionTypeEnum getReturnTransaction(TransactionTypeEnum transTypeEnum)
	{
		TransactionTypeEnum returnEnum = null;
		switch(transTypeEnum) {
			case ISSUE_UNIFORM_STUDENT:
				returnEnum = RETURN_UNIFORM_STUDENT;
				break;
			
			case ISSUE_UNIFORM_STAFF:
				returnEnum = RETURN_UNIFORM_STAFF;
				break;
				
			case ISSUE_EQUIPMENT_STUDENT:
				returnEnum = RETURN_EQUIPMENT_STUDENT;
				break;
			case ISSUE_EQUIPMENT_STAFF:
				returnEnum = RETURN_EQUIPMENT_STAFF;
				break;
				
			case ISSUE_GYM_STUDENT:
				returnEnum = RETURN_GYM_STUDENT;
				break;
				
			case ISSUE_GYM_STAFF:
				returnEnum = RETURN_GYM_STAFF;
				break;
		}
		
		return returnEnum;
			
	}
	
	public static TransactionTypeEnum getLaundryReturnTrans(Integer locationId) {		
		if(locationId == 1  ) {
			return ACCEPT_FROM_LAUNDRY_EQUIPMENT;
		} else if(locationId == 2) {
			return ACCEPT_FROM_LAUNDRY_UNIFORM;			
		} else {
			return ACCEPT_FROM_LAUNDRY_GYM;
		}
	}
	
}