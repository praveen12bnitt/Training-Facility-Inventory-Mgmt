package com.smartworks.invtmgmt.web.ui.datatransfer;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.smartworks.invtmgmt.business.ItemAttributeDetails;
import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.converter.ItemSkuConverter;
import com.smartworks.invtmgmt.core.dao.ItemDao;
import com.smartworks.invtmgmt.core.domain.Inventory;
import com.smartworks.invtmgmt.core.domain.Item;

public class InventoryReport extends AbstractExcelView {
	
	 @Autowired
	 private ItemSkuConverter itemSkuConvertor;  
	 
	 @Autowired
	 private ItemDao itemDao;
	 
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
		  
		  List<Inventory> invtList = (List<Inventory>) model.get("inventoryList");
		 
			//create a wordsheet
			HSSFSheet sheet = workbook.createSheet("Inventory Report");
	 
			HSSFRow header = sheet.createRow(0);
			header.createCell(0).setCellValue("Item Name");
			header.createCell(1).setCellValue("Item Description");
			header.createCell(2).setCellValue("Attributes");
			header.createCell(3).setCellValue("Item Number");
			header.createCell(4).setCellValue("Price");
			header.createCell(5).setCellValue("Available Qty");
			header.createCell(6).setCellValue("Issued Qty");
			header.createCell(7).setCellValue("Unusable Qty");
			header.createCell(8).setCellValue("Location");
		
			int rowNum = 1;
			for(Inventory inventory : invtList) {
				ItemSku itemSku = itemSkuConvertor.getItemSku(inventory.getSkuCode());
				//create the row data
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(itemSku.getItem().getName());
				row.createCell(1).setCellValue(itemSku.getItem().getDesc());
				List<ItemAttributeDetails> itemAttributeDtls = itemSku.getItemAttributeDtls();
				int j=1;
				String attributes="";
				Item item = itemDao.load(itemSku.getItem().getId());
				for(ItemAttributeDetails itemAttributeDetail: itemAttributeDtls) {
					
					attributes = attributes + itemAttributeDetail.getItemAttribute().getAttributeName() +":"+itemAttributeDetail.getItemAttributeValue().getAttributeValue()+"\n";
					
				}
				HSSFCellStyle cs = workbook.createCellStyle();
				cs.setWrapText(true);
				HSSFCell cell = row.createCell(2);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(cs);
				
				cell.setCellValue(attributes);
				row.createCell(3).setCellValue(item.getItemNumber());
				row.createCell(4).setCellValue(item.getPrice());
				row.createCell(5).setCellValue(inventory.getAvailableQty());
				row.createCell(6).setCellValue(inventory.getIssueQty());
				row.createCell(7).setCellValue(inventory.getUnusableQty());
				row.createCell(8).setCellValue(inventory.getLocation().getLocationName());
	        }
			
	}

}
