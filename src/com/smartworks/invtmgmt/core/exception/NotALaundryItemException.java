package com.smartworks.invtmgmt.core.exception;

public class NotALaundryItemException extends InventoryAllocationException {

	private static final long serialVersionUID = 1L;

	public NotALaundryItemException(String skuCode, Integer quantity,
			String location) {
		super(skuCode, quantity, location);		
	}
	
	@Override
	public String getMessage() {
		return getItemSkuAsStr()+" cannot be received from Laundry. Its does not require processing";
	}

}
