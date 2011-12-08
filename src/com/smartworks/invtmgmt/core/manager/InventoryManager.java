package com.smartworks.invtmgmt.core.manager;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.Inventory;
import com.smartworks.invtmgmt.core.domain.Location;

public interface InventoryManager {

	public List<Inventory> getAllInventory();
	public List<Inventory> getInventoryByLocn(Integer locationId);
	public void updateInventory(Integer updatedQty,Location location, String skuCode);
}
