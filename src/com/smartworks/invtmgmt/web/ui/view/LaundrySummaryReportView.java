package com.smartworks.invtmgmt.web.ui.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
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
		  response.setHeader("Content-disposition", "attachment; filename=LaundryReport.xls");
		  
		  List laundryListTotal = (List) model.get("laundryListTotal");
		  HSSFSheet sheet = workbook.createSheet("Laundry summary Report");
		  		  
		  /****************************************************************/
			HSSFCellStyle headerStyle = workbook.createCellStyle();
			//HSSFFont headerFont = workbook.createFont();
			//headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	
			//headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			headerStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
			//headerStyle.setFillBackgroundColor(HSSFColor.WHITE.index);
			//headerStyle.setFont(headerFont);
		
		   /****************************************************************/
			 
			HSSFRow title = sheet.createRow(0);
			
			title.createCell(0).setCellStyle(headerStyle);
	      	title.createCell(0).setCellValue("Laundry Summary Report- FLETCGA");
			HSSFRow header = sheet.createRow(1);
			header.createCell(0).setCellValue("Customer");
			header.createCell(1).setCellValue("Total Weight");
			header.createCell(2).setCellValue("Total Buggy Weight");
			header.createCell(3).setCellValue("Item Weight");
			header.createCell(4).setCellValue("Unit");
					
			
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
					row.createCell(3).setCellValue(
							(totalbuggyWeight + (Long) objects[k])- buggyWeight);
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
	  }
}
