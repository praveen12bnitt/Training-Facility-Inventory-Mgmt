package com.smartworks.invtmgmt.core.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.domain.Inventory;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;

public class InventoryDaoImpl extends HibernateDaoSupport implements InventoryDao {

	private Log log = LogFactory.getLog(InventoryDaoImpl.class);
	
	public Inventory load(InventoryPk skuLocation) {
		Inventory inventory = null;
		try {
			inventory = getHibernateTemplate().load(Inventory.class, skuLocation);
		} catch(HibernateObjectRetrievalFailureException objNotFound) {
			System.out.println("Object not found with skuCode:"+skuLocation.getSkuCode()+",location:"+skuLocation.getLocation().getLocationId());
			System.out.println(objNotFound.getMessage());
			//objNotFound.printStackTrace();
		}		
		return inventory;
	}
	
	public void saveOrUpdate(Inventory inventory) {
		getHibernateTemplate().saveOrUpdate(inventory);
	}
	
	public Inventory save(Inventory inventory) {
		return (Inventory) getHibernateTemplate().save(inventory);
	}
	
	public List<Inventory> loadAll() {
		List<Inventory> inventoryList = getHibernateTemplate().loadAll(Inventory.class);
		return inventoryList;
	}
	
	public List<Inventory> loadAllInventory(Location location) {
		String query = "from Inventory where location = :location";
		List<Inventory> inventoryList = getHibernateTemplate().find(query,location);
		return inventoryList;
	}
	
	public void reduceAvailableInventory(InventoryPk skuLocation, Integer qty) {
		Inventory inventory = load(skuLocation);
		if(inventory == null) {
			// No inventory available. So create a new one
			inventory = new Inventory();
			inventory.setSkuLocation(skuLocation);			
		}		
		Integer avaQty = inventory.getAvailableQty();
		avaQty = avaQty-qty;
		inventory.setAvailableQty(avaQty);
		saveOrUpdate(inventory);	
	}
	
	public void addAvailableInventory(InventoryPk skuLocation, Integer qty) {
		Inventory inventory = load(skuLocation);
		if(inventory == null) {
			// No inventory available. So create a new one
			inventory = new Inventory();
			inventory.setSkuLocation(skuLocation);	
			inventory.setAvailableQty(0);
			inventory.setUnusableQty(0);
		}		
		Integer avaQty = inventory.getAvailableQty();
		avaQty = avaQty+qty;
		inventory.setAvailableQty(avaQty);
		saveOrUpdate(inventory);
	}
	
	public void addUnusableInventory(InventoryPk skuLocation, Integer qty) {
		Inventory inventory = load(skuLocation);
		if(inventory == null) {
			// No inventory available. So create a new one
			inventory = new Inventory();
			inventory.setSkuLocation(skuLocation);	
			inventory.setAvailableQty(0);
			inventory.setUnusableQty(0);
		}		
		Integer unusableQty = inventory.getUnusableQty();
		unusableQty = unusableQty+qty;
		inventory.setUnusableQty(unusableQty);
		saveOrUpdate(inventory);
	}
	
	public void reduceUnusableInventory(InventoryPk skuLocation, Integer qty) {
		Inventory inventory = load(skuLocation);
		if(inventory == null) {
			// No inventory available. So create a new one
			inventory = new Inventory();
			inventory.setSkuLocation(skuLocation);
			inventory.setAvailableQty(0);
			inventory.setUnusableQty(0);
		}		
		Integer unusableQty = inventory.getUnusableQty();
		unusableQty = unusableQty-qty;
		inventory.setUnusableQty(unusableQty);
		saveOrUpdate(inventory);
	}
	
	
	
	
}
