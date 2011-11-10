package com.smartworks.invtmgmt.core.exception;

public class NoInventoryException extends InventoryAllocationException {

	private static final long serialVersionUID = 1L;
	
	public NoInventoryException(String skuCode, Integer quantity,String location) {
		super(skuCode, quantity,location);
	}

	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("No inventory for  ");
		sb.append(getItemSkuAsStr());
		sb.append("at ").append(location);
		sb.append(". Requested Quantity :");
		sb.append(getQuantity());		
		return sb.toString();
	}
}
