package com.smartworks.invtmgmt.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.manager.ItemMgr;
import com.smartworks.invtmgmt.web.ui.controller.ItemIssueFormController;

@ContextConfiguration(locations = {"file:WebContent/WEB-INF/config/appCtx.xml","file:WebContent/WEB-INF/config/spring-security.xml"})
public class TestVelocityHtmlData extends AbstractTestNGSpringContextTests {

	@Autowired
	ItemMgr itemMgr;
	
	@Autowired
	ItemIssueFormController controller;
	
	@Test
	public void f() {
		System.out.println("hello");
		List<Item> items = itemMgr.getItemsByName("ATF Student  Investigator Jacket");	
		Integer index = 1;
		
//		String str = controller.getItemExchangeRowData(items.get(0), index);
		
		System.out.println("");
		
	}
}
