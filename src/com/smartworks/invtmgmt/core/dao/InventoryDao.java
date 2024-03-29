package com.smartworks.invtmgmt.core.dao;

import java.util.List;

import com.smartworks.invtmgmt.core.domain.Inventory;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;
import com.smartworks.invtmgmt.core.transaction.ReasonCodeEnum;

public interface InventoryDao {
	public Inventory load(InventoryPk skuLoc);
	public void saveOrUpdate(Inventory inventory) ;
	public Inventory save(Inventory inventory) ;
	public List<Inventory> loadAll() ;
	public List<Inventory> loadAllInventory(Location location);
	public void reduceAvailableInventory(InventoryPk skuLocation, Integer qty);
	public void addAvailableInventory(InventoryPk skuLocation, Integer qty);
	public void addUnusableInventory(InventoryPk skuLocation, Integer qty);
	public void reduceUnusableInventory(InventoryPk skuLocation, Integer qty);
	public void addIssuedInventory(InventoryPk skuLocation, Integer qty);
	public void reduceIssuedInventory(InventoryPk skuLocation, Integer qty);
	public void addMissingDamagedInventory(InventoryPk skuLocation, Integer qty, ReasonCodeEnum reasonCode);
}
