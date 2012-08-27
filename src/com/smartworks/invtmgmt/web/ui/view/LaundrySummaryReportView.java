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
	
	private static List<String> custList = new ArrayList<String>();
	private static String customerCodeFormat="1006a {0}";
	private static final Double buggyWeight=48d; // Constant per suresh
	private static final Double totalbuggyWeight=288d; // Constant per suresh
	private static final Map<Integer, String> customerMap = new HashMap<Integer, String>();
	static {
		customerMap.put(1, "1006a");
		customerMap.put(2, "1006g");
		customerMap.put(3, "1006e");
		customerMap.put(4, "1006d");
		customerMap.put(5, "1006h");
		customerMap.put(6, "1006f");
		customerMap.put(7, "1006b");
		customerMap.put(8, "1006c");
	}
	
	protected void buildExcelDocument(Map model,
            HSSFWorkbook workbook,
            HttpServletRequest request,
            HttpServletResponse response)
	  {
		  response.setContentType("application/vnd.ms-excel");
		  response.setHeader("Content-disposition", "attachment; filename=LaundrySummaryReport.xls");
		  
		  List laundryListTotal = (List) model.get("laundryListTotal");
		  HSSFSheet sheet = workbook.createSheet("Laundry summary Report");
		  		  
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
			cell.setCellValue("Laundry Summary Report- FLETCGA");
			cell.setCellStyle(cellStyle);
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
			
			HSSFRow header = sheet.createRow(1);
			
			cell = header.createCell(0);
			cell.setCellValue("Customer");
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
			   Object[] objects = (Object[]) laundryListTotal.get(0);
			   for (int k = 0; k < objects.length; k++) {
				 HSSFRow row = sheet.createRow(rowNum++);
				 row.createCell(0).setCellValue(customerMap.get(k + 1));
				 row.createCell(1).setCellValue(
				 totalbuggyWeight + (Long) objects[k]);
				 if (customerMap.get(k + 1) == "1006-A") {
					row.createCell(2).setCellValue(totalbuggyWeight); // buggyweigth
															// * 6
				} else {
					row.createCell(2).setCellValue(buggyWeight);
				}

				if (customerMap.get(k + 1) != "1006-A") {
					row.createCell(3).setCellValue((totalbuggyWeight + (Long) objects[k])- buggyWeight);
				} else {

					row.createCell(3).setCellValue((Long) objects[k]);
				}
				row.createCell(4).setCellValue("Washer");

				row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(customerMap.get(k + 1));
				row.createCell(1).setCellValue(totalbuggyWeight + (Long) objects[k]);
				if (customerMap.get(k + 1) == "1006-A") {
					row.createCell(2).setCellValue(totalbuggyWeight); // buggyweigth
																		// * 6
				} else {
					row.createCell(2).setCellValue(buggyWeight);
				}
				if (customerMap.get(k + 1) != "1006-A") {
					row.createCell(3).setCellValue(
							(totalbuggyWeight + (Long) objects[k])- buggyWeight);
				} else {
					row.createCell(3).setCellValue((Long) objects[k]);
				}
				row.createCell(4).setCellValue("Dryer");

			}

		}
		    
		for(int k=0; k<5; k++) {
			sheet.autoSizeColumn(k);
		}
	}
}
