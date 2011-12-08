package com.smartworks.invtmgmt.core.exception;

public class NoItemsForTransactionException extends InventoryAllocationException {

	private static final long serialVersionUID = 1L;

	String msg;
	
	public NoItemsForTransactionException(String msg) {
		super(msg);;
		this.msg = msg;	
	}
	
	@Override
	public String getMessage() {		
		return msg;
	}
	
	
}
