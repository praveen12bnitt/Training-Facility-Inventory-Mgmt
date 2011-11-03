package com.smartworks.invtmgmt.web.ui.form;
import java.util.ArrayList;
import java.util.List;

import com.pal.test.DummyString;
import com.smartworks.invtmgmt.core.domain.Inventory;
import com.smartworks.invtmgmt.web.ui.transfer.UIFormItem;
import com.smartworks.invtmgmt.web.ui.transfer.UIFormLocation;
import com.smartworks.invtmgmt.web.ui.transfer.UIFormTransactionReceiver;

public class TransactionForm {
	private List<UIFormItem> listUIFormItems;
	
	private List<DummyString> dummyStrings;
	
	private UIFormTransactionReceiver uiTransactionReceiver;
	
	private String transactionType;
	
	private List <UIFormLocation> locationList;
	
	public List<UIFormLocation> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<UIFormLocation> locationList) {
		this.locationList = locationList;
	}
	
	public TransactionForm() {
		uiTransactionReceiver = new UIFormTransactionReceiver();
		dummyStrings = new ArrayList<DummyString>();
		dummyStrings.add(new DummyString());
		dummyStrings.add(new DummyString());
		dummyStrings.add(new DummyString());
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public List<DummyString> getDummyStrings() {
		return dummyStrings;
	}

	public void setDummyStrings(List<DummyString> dummyStrings) {
		this.dummyStrings = dummyStrings;
	}

	public List<UIFormItem> getListUIFormItems() {
		return listUIFormItems;
	}

	public void setListUIFormItems(List<UIFormItem> listUIFormItems) {
		this.listUIFormItems = listUIFormItems;
	}

	public UIFormTransactionReceiver getUiTransactionReceiver() {
		return uiTransactionReceiver;
	}

	public void setUiTransactionReceiver(
			UIFormTransactionReceiver uiTransactionReceiver) {
		this.uiTransactionReceiver = uiTransactionReceiver;
	}
	
	
}
