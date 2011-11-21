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

import com.smartworks.invtmgmt.core.manager.ItemMgr;

@Controller
@RequestMapping("/itemlookup")
public class ItemLookupController {
	
	@Autowired
	ItemMgr itemMgr = null;
	
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
	

	public ItemMgr getItemMgr() {
		return itemMgr;
	}

	public void setItemMgr(ItemMgr itemMgr) {
		this.itemMgr = itemMgr;
	}

	
	
	
}
