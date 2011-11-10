package com.smartworks.invtmgmt.core.exception;

public class NoUnusableInventoryException extends InventoryAllocationException {

	private static final long serialVersionUID = 1L;
	
	Integer availableUnusableQty;
	
	public NoUnusableInventoryException(String skuCode, Integer quantity,
			String location,Integer availableUnusableQty) {
		super(skuCode, quantity, location);
		this.availableUnusableQty = availableUnusableQty;
	}
	
	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder("Unable to reclaim unusable item");
		sb.append("Available unusable qty:"+availableUnusableQty);
		sb.append("Requested reclaim:"+getQuantity());
		return sb.toString();
	}

	

}
