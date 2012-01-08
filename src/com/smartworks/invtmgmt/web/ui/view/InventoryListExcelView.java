package com.smartworks.invtmgmt.web.ui.view;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.smartworks.invtmgmt.web.ui.transfer.inventory.UIInventory;

public class InventoryListExcelView extends AbstractExcelView {
	
	public static final String WIDGET_LIST_KEY = "widgetList";
	  protected static final short WIDGET_NAME_COLUMN = 0;
	  protected static final short WIDGET_SIZE_COLUMN = 1;
	  @Override
	  protected void buildExcelDocument(Map model,
              HSSFWorkbook workbook,
              HttpServletRequest request,
              HttpServletResponse response)
	  {
		  
		  response.setContentType("application/vnd.ms-excel");
		  response.setHeader("Content-disposition", "attachment; filename=Report.xls");
		  
		  List<UIInventory> invtList = (List<UIInventory>) model.get("widgetList");
		 
			//create a wordsheet
			HSSFSheet sheet = workbook.createSheet("Inventory Report");
	 
			HSSFRow header = sheet.createRow(0);
			header.createCell(0).setCellValue("Item Name");
			header.createCell(1).setCellValue("Item Specification");
			header.createCell(2).setCellValue("Available Qty");
			header.createCell(3).setCellValue("Issued Qty");
			header.createCell(4).setCellValue("Unusable Qty");
			header.createCell(5).setCellValue("Location");
		
			int rowNum = 1;
			for(UIInventory inventory : invtList) {
				//create the row data
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(inventory.getItemDesc());
				row.createCell(1).setCellValue(inventory.getItemAttributeDetails());
				row.createCell(2).setCellValue(inventory.getAvailableQty());
				row.createCell(3).setCellValue(inventory.getIssuedQty());
				row.createCell(4).setCellValue(inventory.getUnusableQty());
				row.createCell(5).setCellValue(inventory.getLocation());
	           }
			
	}

}
