package com.smartworks.invtmgmt.core.transaction;

public enum TransactionTypeEnum {

	ISSUE_UNIFORM_STUDENT,
	ISSUE_UNIFORM_STAFF,
	REPORT_MISSING_UNIFORM_STAFF,
	REPORT_MISSING_UNIFORM_STUDENT,
	RETURN_UNIFORM_STUDENT,
	RETURN_UNIFORM_STAFF,
	ACCEPT_UNIFORM_FROM_LAUNDRY,
	
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
		}
		
		return returnEnum;
			
	}
	
}