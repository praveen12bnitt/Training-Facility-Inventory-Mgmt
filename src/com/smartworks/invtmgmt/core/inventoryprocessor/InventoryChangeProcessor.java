package com.smartworks.invtmgmt.core.inventoryprocessor;

import java.util.List;

import com.smartworks.invtmgmt.business.ItemSku;

public interface InventoryChangeProcessor {

	public void process(Integer locationId, List<ItemSku> skus) ;
}
