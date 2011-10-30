package com.smartworks.invtmgmt.web.ui.transfer;

import java.io.Serializable;
import java.util.List;

public class UIFormItem implements Serializable {
	
	private Integer itemId;
	private String itemName;
	private Integer itemQty;
	private List<UIFormItemAttribute> uiFormItemAttributes;
	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public Integer getItemQty() {
		return itemQty;
	}

	public void setItemQty(Integer itemQty) {
		this.itemQty = itemQty;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public List<UIFormItemAttribute> getUiFormItemAttributes() {
		return uiFormItemAttributes;
	}

	public void setUiFormItemAttributes(
			List<UIFormItemAttribute> uiFormItemAttributes) {
		this.uiFormItemAttributes = uiFormItemAttributes;
	}

	

}
