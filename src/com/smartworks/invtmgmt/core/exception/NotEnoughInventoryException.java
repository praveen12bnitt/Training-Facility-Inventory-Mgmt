package com.smartworks.invtmgmt.core.exception;

public class NotEnoughInventoryException extends InventoryAllocationException {
	
	private static final long serialVersionUID = 1L;
	
	Integer availableQuantity;
	
	public NotEnoughInventoryException(String skuCode, Integer quantity,String location,Integer availableQuantity) {
		super(skuCode, quantity,location);
		this.availableQuantity = availableQuantity;
	}
	
	@Override
	public String getMessage() {		
		StringBuilder sb = new StringBuilder();		
		sb.append("Not enought ");
		sb.append(getItemSkuAsStr());
		sb.append(" at ");
		sb.append(getLocation());
		sb.append("to allocate.");
		sb.append("Requested:"+getQuantity());
		sb.append(" Available:"+availableQuantity);
		
		return sb.toString();
	}
}
