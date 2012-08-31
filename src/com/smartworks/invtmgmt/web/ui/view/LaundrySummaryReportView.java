package com.smartworks.invtmgmt.web.ui.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFBorderFormatting;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class LaundrySummaryReportView extends AbstractExcelView {
	
	
	
	protected void buildExcelDocument(Map model,
            HSSFWorkbook workbook,
            HttpServletRequest request,
            HttpServletResponse response)
	  {
		  response.setContentType("application/vnd.ms-excel");
		  response.setHeader("Content-disposition", "attachment; filename=LaundrySummaryReport.xls");
		  
		  List laundryListTotal = (List) model.get("laundrySummaryList");
		  HSSFSheet sheet = workbook.createSheet("Laundry summary Report");
		  String laundryType = (String) model.get("laundryType");
		  		  
		  /****************************************************************/
		  Font font = workbook.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(font);
			cellStyle.setBorderRight(HSSFBorderFormatting.BORDER_THIN);
			cellStyle.setBorderLeft(HSSFBorderFormatting.BORDER_THIN);
			cellStyle.setBorderTop(HSSFBorderFormatting.BORDER_THIN);
			cellStyle.setBorderBottom(HSSFBorderFormatting.BORDER_THIN);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		   /****************************************************************/
			 
			HSSFRow title = sheet.createRow(0);
			HSSFCell cell = title.createCell(0);
			if("D".equals(laundryType)) {
				cell.setCellValue("Dryer Summary Report- FLETCGA");
			} else {
				cell.setCellValue("Washer Summary Report- FLETCGA");
			}
			cell.setCellStyle(cellStyle);
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
			
			HSSFRow header = sheet.createRow(1);
			
			cell = header.createCell(0);
			cell.setCellValue("Client Info");
			cell.setCellStyle(cellStyle);
			
			cell= header.createCell(1);
			cell.setCellValue("Total Weight");
			cell.setCellStyle(cellStyle);
			
			cell= header.createCell(2);
			cell.setCellValue("Total Buggy Weight");
			cell.setCellStyle(cellStyle);
			
			cell= header.createCell(3);
			cell.setCellValue("Item Weight");
			cell.setCellStyle(cellStyle);
			
			cell= header.createCell(4);
			cell.setCellValue("Unit");
			cell.setCellStyle(cellStyle);
					
			
			int rowNum = 2;
		    if (laundryListTotal.size() > 0) {
		    	for(int j=0; j<laundryListTotal.size(); j++) {
				  Object[] objects = (Object[]) laundryListTotal.get(j);
				  
					   HSSFRow row = sheet.createRow(rowNum++);
					   row.createCell(0).setCellValue((String)objects[0]);
					   row.createCell(1).setCellValue((Long)objects[1]);
					   row.createCell(2).setCellValue((Long)objects[2]);
					   row.createCell(3).setCellValue((Long)objects[3]);
					   row.createCell(4).setCellValue("LB");
					
		    	}

		}
		    
		for(int k=0; k<5; k++) {
			sheet.autoSizeColumn(k);
		}
	}
}
