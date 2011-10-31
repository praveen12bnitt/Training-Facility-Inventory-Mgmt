package com.smartworks.invtmgmt.web.ui.transfer;

import java.io.Serializable;
import java.util.List;

import com.smartworks.invtmgmt.core.domain.ItemAttributeValue;

public class UIFormItemAttribute implements Serializable{
	private Integer itemId;
	private Integer itemAttributeId;
	private String  itemAttributeName;
	private List<UIFormItemAttributeValue> itemAttributeValues;
	private String selectedAttributeValue;
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getItemAttributeId() {
		return itemAttributeId;
	}
	public void setItemAttributeId(Integer itemAttributeId) {
		this.itemAttributeId = itemAttributeId;
	}
	public String getItemAttributeName() {
		return itemAttributeName;
	}
	public void setItemAttributeName(String itemAttributeName) {
		this.itemAttributeName = itemAttributeName;
	}
	public List<UIFormItemAttributeValue> getItemAttributeValues() {
		return itemAttributeValues;
	}
	public void setItemAttributeValues(
			List<UIFormItemAttributeValue> itemAttributeValues) {
		this.itemAttributeValues = itemAttributeValues;
	}
	public String getSelectedAttributeValue() {
		return selectedAttributeValue;
	}
	public void setSelectedAttributeValue(String selectedAttributeValue) {
		this.selectedAttributeValue = selectedAttributeValue;
	}
	
	
	
}
