package com.smartworks.invtmgmt.web.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartworks.invtmgmt.core.domain.Trainee;
import com.smartworks.invtmgmt.core.manager.ClassMgr;
import com.smartworks.invtmgmt.core.manager.ItemMgr;
import com.smartworks.invtmgmt.core.manager.TraineeMgr;

@Controller
@RequestMapping("/itemlookup")
public class ItemLookupController {
	
	@Autowired
	ItemMgr itemMgr = null;
	
	@Autowired
	ClassMgr clsMgr;
	
	@Autowired
	TraineeMgr traineeMgr;
	
	@RequestMapping(value = "/name.form", method = RequestMethod.GET)
	public @ResponseBody
	List<String> getAllOpenTransactions(HttpServletRequest request, @RequestParam String name) {
		List<String> itemNames = new ArrayList<String>();
		itemNames = itemMgr.getItemNamesLike(name);
		return itemNames;	
	}
	
	
	@RequestMapping(value = "/itemNames.form", method = RequestMethod.GET)
	public @ResponseBody
	List<String> getItemMaps(HttpServletRequest request, @RequestParam String name) {
		Map<Integer, String> itemMaps =  itemMgr.getItemMaps(name);
		List<String> itemNames = new ArrayList();
		for(Integer id : itemMaps.keySet()) {
			itemNames.add(itemMaps.get(id)+"("+id+")");
		}
		return itemNames;
	}
	
	@RequestMapping(value="/getProductName.form", method = RequestMethod.GET)
	public @ResponseBody
	List<String> getProductName(HttpServletRequest request, @RequestParam String name){
		List<String> itemMaps =  clsMgr.getItemMaps(name);
		return itemMaps;
	}
	
	@RequestMapping(value="/getTraineeName.form", method = RequestMethod.GET)
	public @ResponseBody
	List<String> getTraineeName(HttpServletRequest request, @RequestParam String name){
		List<Trainee> itemMaps =  traineeMgr.getByName(name);
		List<String> items = new ArrayList<String>();
		for(Trainee trainee : itemMaps){
			items.add(trainee.getFirstName());
		}
		return items;
	}

	public ItemMgr getItemMgr() {
		return itemMgr;
	}

	public void setItemMgr(ItemMgr itemMgr) {
		this.itemMgr = itemMgr;
	}

	
	
	
}
