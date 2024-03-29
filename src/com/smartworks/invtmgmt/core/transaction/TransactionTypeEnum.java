package com.smartworks.invtmgmt.core.transaction;

public enum TransactionTypeEnum {
	
	PREISSUE_UNIFORM_STUDENT,
	PREISSUE_UNIFORM_STAFF,
	PREISSUE_EQUIPMENT_STUDENT,
	PREISSUE_EQUIPMENT_STAFF,	

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
	
	
	EXCHANGE_UNIFORM_STUDENT,
	EXCHANGE_UNIFORM_STAFF,
		
	ACCEPT_FROM_LAUNDRY_UNIFORM,
	ACCEPT_FROM_LAUNDRY_EQUIPMENT,
	ACCEPT_FROM_LAUNDRY_GYM,	
	
	TRANSFER_INVENTORY;
	
	public static TransactionTypeEnum getExchangeTransaction(TransactionTypeEnum transTypeEnum) {
		TransactionTypeEnum returnEnum = null;
		switch(transTypeEnum) {
			case ISSUE_UNIFORM_STUDENT:
				returnEnum = EXCHANGE_UNIFORM_STUDENT;
				break;
				
			case ISSUE_UNIFORM_STAFF:
				returnEnum = EXCHANGE_UNIFORM_STAFF;
				break;
				
			default:
				break;
			
		}		
		return returnEnum;
	}
	
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
				
			case PREISSUE_UNIFORM_STUDENT:
				returnEnum = PREISSUE_UNIFORM_STUDENT;
				break;
				
			case PREISSUE_UNIFORM_STAFF:
				returnEnum = PREISSUE_UNIFORM_STUDENT;
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
	
	public boolean isStaffTransaction() {
		if(this.equals(ISSUE_EQUIPMENT_STAFF) || this.equals(ISSUE_GYM_STAFF) || this.equals(ISSUE_UNIFORM_STAFF) || 
				this.equals(RETURN_UNIFORM_STAFF) || this.equals(RETURN_GYM_STAFF) || this.equals(RETURN_EQUIPMENT_STAFF) || this.equals(PREISSUE_UNIFORM_STAFF) || this.equals(PREISSUE_EQUIPMENT_STAFF)) return true;
		else return false;
	}
	
	public boolean isOfTypePreissue() 
	{
		if(this == PREISSUE_EQUIPMENT_STAFF || this == PREISSUE_EQUIPMENT_STUDENT || this == PREISSUE_UNIFORM_STAFF || this == PREISSUE_UNIFORM_STUDENT) return true;
		else return false;
	}
	
	public TransactionTypeEnum getIssueTrans()
	{
		switch(this){
		case PREISSUE_EQUIPMENT_STAFF:
			return ISSUE_EQUIPMENT_STAFF;
		case PREISSUE_EQUIPMENT_STUDENT:
			return ISSUE_EQUIPMENT_STUDENT;
		case PREISSUE_UNIFORM_STAFF:
			return ISSUE_UNIFORM_STAFF;
		case PREISSUE_UNIFORM_STUDENT:
			return ISSUE_UNIFORM_STUDENT;
		default:
			throw new RuntimeException("Unsupported transaction type");		
		}
			
	}
	
}