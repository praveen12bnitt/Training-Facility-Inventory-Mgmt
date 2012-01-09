package com.smartworks.invtmgmt.business;

import java.util.List;

import org.springframework.util.AutoPopulatingList;

import com.smartworks.invtmgmt.converter.ItemSkuConverter;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.transaction.ReasonCodeEnum;
import com.smartworks.platform.AppContextUtil;

public class ItemSku {
	
	Item item = new Item();
		
	List<ItemAttributeDetails> itemAttributeDtls = new AutoPopulatingList<ItemAttributeDetails>(ItemAttributeDetails.class);;
	
	String skuCode;		
	Integer quantity;
	Integer orginalQty;
	private	ReasonCodeEnum reasonCode;

	public Integer getOrginalQty() {
		return orginalQty;
	}

	public void setOrginalQty(Integer orginalQty) {
		this.orginalQty = orginalQty;
	}

	ItemSkuConverter itemSkuConverter = AppContextUtil.getBean("itemSkuConverter");
	
	public ItemSku(Item item, List<ItemAttributeDetails> itemAttributeDtls) {
		super();
		this.item = item;
		this.itemAttributeDtls = itemAttributeDtls;
	}
		
	public ItemSku() {
		super();
	}

	public ItemSku(String skuCode) {
		super();
		this.skuCode = skuCode;
	}

	public Item getItem() {
		if(item == null || item.getId() == null) {
			// Check generate item and itemAttributeDetails object if skuCode is not null
			if(skuCode != null) {
				ItemSku s = itemSkuConverter.getItemSku(skuCode);
				item = s.getItem();
				itemAttributeDtls = s.getItemAttributeDtls();
			}
		}
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getSkuCode() {		
		if(skuCode == null) {
			// Check if item is non empty. If yes then generete the value
			if(item != null) {
				skuCode = itemSkuConverter.getItemSkuCode(this);
			}
		} 		
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	public List<ItemAttributeDetails> getItemAttributeDtls() {
		return itemAttributeDtls;
	}



	public void setItemAttributeDtls(List<ItemAttributeDetails> itemAttributeDtls) {
		this.itemAttributeDtls = itemAttributeDtls;
	}



	public ItemSkuConverter getItemSkuConverter() {
		return itemSkuConverter;
	}



	public void setItemSkuConverter(ItemSkuConverter itemSkuConverter) {
		this.itemSkuConverter = itemSkuConverter;
	}

	public ReasonCodeEnum getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(ReasonCodeEnum reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	public void setReasonCode(String reasonCode) {
		this.reasonCode = ReasonCodeEnum.valueOf(reasonCode);;
	}
}
