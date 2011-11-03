package com.smartworks.invtmgmt.core.inventoryprocessor;

import com.smartworks.invtmgmt.core.transaction.TransactionTypeEnum;

public class InventoryProcessorFactory {
	DispatchInventoryProcessor dispatchInventoryProcessor;
	ReturnsInventoryProcessor returnsInventoryProcessor;
	MissingInventoryProcessor missingInventoryProcessor;
	LaundryReturnsProcessor laundryReturnsProcessor;
	TransferInventoryProcessor transferInventoryProcessor;
	
	public InventoryChangeProcessor getInventoryProcessor(
			TransactionTypeEnum tranType) {

		InventoryChangeProcessor processor = null;

		switch (tranType) {
		case ISSUE_UNIFORM_STUDENT:
		case ISSUE_UNIFORM_STAFF:
			processor = dispatchInventoryProcessor;
			break;
		case RETURN_UNIFORM_STAFF:
		case RETURN_UNIFORM_STUDENT:
			processor = returnsInventoryProcessor;
			break;
		case REPORT_MISSING_UNIFORM_STAFF:
		case REPORT_MISSING_UNIFORM_STUDENT:
			processor = missingInventoryProcessor;
			break;
		case ACCEPT_UNIFORM_FROM_LAUNDRY:
			processor = laundryReturnsProcessor;
			break;
		case TRANSFER_INVENTORY:
			processor = transferInventoryProcessor;
			break;
		}

		return processor;
	}

	public DispatchInventoryProcessor getDispatchInventoryProcessor() {
		return dispatchInventoryProcessor;
	}

	public ReturnsInventoryProcessor getReturnsInventoryProcessor() {
		return returnsInventoryProcessor;
	}

	public MissingInventoryProcessor getMissingInventoryProcessor() {
		return missingInventoryProcessor;
	}

	public void setDispatchInventoryProcessor(
			DispatchInventoryProcessor dispatchInventoryProcessor) {
		this.dispatchInventoryProcessor = dispatchInventoryProcessor;
	}

	public void setReturnsInventoryProcessor(
			ReturnsInventoryProcessor returnsInventoryProcessor) {
		this.returnsInventoryProcessor = returnsInventoryProcessor;
	}

	public void setMissingInventoryProcessor(
			MissingInventoryProcessor missingInventoryProcessor) {
		this.missingInventoryProcessor = missingInventoryProcessor;
	}

	public LaundryReturnsProcessor getLaundryReturnsProcessor() {
		return laundryReturnsProcessor;
	}

	public TransferInventoryProcessor getTransferInventoryProcessor() {
		return transferInventoryProcessor;
	}

	public void setLaundryReturnsProcessor(
			LaundryReturnsProcessor laundryReturnsProcessor) {
		this.laundryReturnsProcessor = laundryReturnsProcessor;
	}

	public void setTransferInventoryProcessor(
			TransferInventoryProcessor transferInventoryProcessor) {
		this.transferInventoryProcessor = transferInventoryProcessor;
	}
	
	
}
