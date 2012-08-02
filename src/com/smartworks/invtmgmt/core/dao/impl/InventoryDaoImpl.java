package com.smartworks.invtmgmt.core.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.converter.ItemSkuConverter;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.domain.Inventory;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;
import com.smartworks.invtmgmt.core.exception.NoInventoryException;
import com.smartworks.invtmgmt.core.exception.NoUnusableInventoryException;
import com.smartworks.invtmgmt.core.exception.NotEnoughInventoryException;
import com.smartworks.invtmgmt.core.transaction.ReasonCodeEnum;

public class InventoryDaoImpl extends HibernateDaoSupport implements InventoryDao {

	ItemSkuConverter itemSkuConverter;
	
	public Inventory load(InventoryPk skuLocation) {
		Inventory inventory = null;
		try {
			inventory = getHibernateTemplate().load(Inventory.class, skuLocation);
			populateItemSku(inventory);			
		} catch(HibernateObjectRetrievalFailureException objNotFound) {
			System.out.println("Object not found with skuCode:"+skuLocation.getSkuCode()+",location:"+skuLocation.getLocation().getLocationId());
			System.out.println(objNotFound.getMessage());
			//objNotFound.printStackTrace();
		}		
		return inventory;
	}
	
	public void saveOrUpdate(Inventory inventory) {
		populateItemSkuCode(inventory);
		getHibernateTemplate().saveOrUpdate(inventory);
	}
	
	public Inventory save(Inventory inventory) {
		populateItemSkuCode(inventory);
		return (Inventory) getHibernateTemplate().save(inventory);
	}
	
	public List<Inventory> loadAll() {
		List<Inventory> inventoryList = getHibernateTemplate().loadAll(Inventory.class);
		for (Inventory inventory : inventoryList) {
			populateItemSku(inventory);
		}
		return inventoryList;
	}
	
	public List<Inventory> loadAllInventory(Location location) {
		String query = "from Inventory where location_id = :location";
		List<Inventory> inventoryList = getHibernateTemplate().findByNamedParam(query,"location", location.getLocationId());
		return inventoryList;
	}
	
	public void reduceAvailableInventory(InventoryPk skuLocation, Integer qty) {
		Inventory inventory = load(skuLocation);
		if(inventory == null) {
			throw new NoInventoryException(skuLocation.getSkuCode(), qty,skuLocation.getLocation().getLocationName());			
		}		
		Integer avaQty = inventory.getAvailableQty();
		if(avaQty < qty) {
			throw new NotEnoughInventoryException(skuLocation.getSkuCode(), qty, skuLocation.getLocation().getLocationName(), avaQty);
		}
		avaQty = avaQty-qty;
		inventory.setAvailableQty(avaQty);
		saveOrUpdate(inventory);	
	}
	
	public void addMissingDamagedInventory(InventoryPk skuLocation, Integer qty, ReasonCodeEnum reasonCode) {		
		Inventory inventory = load(skuLocation);
		if(reasonCode == ReasonCodeEnum.ITEM_MISSING) {
			Integer missingQty = inventory.getMissingQty();
			missingQty += qty;
			inventory.setMissingQty(missingQty);
		} else if(reasonCode == ReasonCodeEnum.ITEM_DAMAGED) {
			Integer damagedQty = inventory.getDamagedQty();
			damagedQty += qty;
			inventory.setMissingQty(damagedQty);
		}
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
			inventory.setIssueQty(0);
		}		
		Integer avaQty = inventory.getAvailableQty();
		avaQty = avaQty+qty;
		inventory.setAvailableQty(avaQty);
		saveOrUpdate(inventory);
	}
	
	public void addUnusableInventory(InventoryPk skuLocation, Integer qty) {
		Inventory inventory = load(skuLocation);
		Integer unusableQty = inventory.getUnusableQty();
		unusableQty = unusableQty+qty;
		inventory.setUnusableQty(unusableQty);
		saveOrUpdate(inventory);
	}
	
	public void reduceUnusableInventory(InventoryPk skuLocation, Integer qty) {
		Inventory inventory = load(skuLocation);
		Integer unusableQty = inventory.getUnusableQty();
		if(unusableQty < qty) {
			throw new NoUnusableInventoryException(skuLocation.getSkuCode(),qty,skuLocation.getLocation().getLocationName(),unusableQty);
		}
		unusableQty = unusableQty-qty;
		inventory.setUnusableQty(unusableQty);
		saveOrUpdate(inventory);
	}
	
	public void addIssuedInventory(InventoryPk skuLocation, Integer qty) {
		Inventory inventory = load(skuLocation);
		Integer issuedInventory = inventory.getIssueQty();
		issuedInventory = issuedInventory+qty;
		inventory.setIssueQty(issuedInventory);
		saveOrUpdate(inventory);
	}
	
	public void reduceIssuedInventory(InventoryPk skuLocation, Integer qty) {
		Inventory inventory = load(skuLocation);
		Integer issuedInventory = inventory.getIssueQty();
		issuedInventory = issuedInventory-qty;
		inventory.setIssueQty(issuedInventory);
		saveOrUpdate(inventory);
	}

	public ItemSkuConverter getItemSkuConverter() {
		return itemSkuConverter;
	}

	public void setItemSkuConverter(ItemSkuConverter itemSkuConverter) {
		this.itemSkuConverter = itemSkuConverter;
	}
	
	private void populateItemSku(Inventory inventory) {
		ItemSku itemSku = itemSkuConverter.getItemSku(inventory.getSkuCode());
		inventory.setItemSku(itemSku);
	}
	
	private void populateItemSkuCode(Inventory inventory) {
		if(inventory.getSkuCode() == null || inventory.getSkuCode().length() == 0) {
			if(inventory.getItemSku() != null) {
				inventory.setSkuCode(itemSkuConverter.getItemSkuCode(inventory.getItemSku()));
			}
		}
	}
	
	
	
	
}
