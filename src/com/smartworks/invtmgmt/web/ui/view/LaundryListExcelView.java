package com.smartworks.invtmgmt.web.ui.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.smartworks.invtmgmt.core.domain.LaundryTracking;
import com.smartworks.invtmgmt.core.service.DateUtil;

public class LaundryListExcelView extends AbstractExcelView {
	
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
		  response.setHeader("Content-disposition", "attachment; filename=LaundryReport.xls");
		  
		  List<LaundryTracking> laundryList = (List<LaundryTracking>) model.get("laundryList");
		  List laundryListTotal = (List) model.get("laundryListTotal");
		 
			//create a wordsheet
			HSSFSheet sheet = workbook.createSheet("Laundry Report");
	 
			HSSFRow title = sheet.createRow(0);
			title.createCell(0).setCellValue("Laundry Weigh-Ins by Date Range - FLETCGA");
			HSSFRow header = sheet.createRow(1);
			header.createCell(0).setCellValue("Created Time");
			header.createCell(1).setCellValue("Washer No");
			header.createCell(2).setCellValue("TSE Room");
			header.createCell(3).setCellValue("Towel Set");
			header.createCell(4).setCellValue("Gym Clothing Set");
			header.createCell(5).setCellValue("Jock Sack Bra Set");
			header.createCell(6).setCellValue("Uniform Set");
			header.createCell(7).setCellValue("Reg Laundry");
			header.createCell(8).setCellValue("DMD 0006-G");
			header.createCell(9).setCellValue("FAD 0006-E");
			header.createCell(10).setCellValue("CTD 0006-D");
			header.createCell(11).setCellValue("ATF SABT 0006-H");
			header.createCell(12).setCellValue("PTD 0006-F");
			header.createCell(13).setCellValue("USBOPB 0006-B");
			header.createCell(14).setCellValue("FPS-0006C");
		
			int rowNum = 2;
			long totalTowel=0L;
			long totalGym = 0L;
			//int  totalTowel = 0;
			//int totalGym = 0;
			for(LaundryTracking laundryTracking : laundryList) {
				//create the row data
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(1).setCellValue(laundryTracking.getWashingMachineNo());
				row.createCell(2).setCellValue(laundryTracking.getTseRoom());
				row.createCell(3).setCellValue(laundryTracking.getTowels());
				totalTowel = totalTowel+laundryTracking.getTowels();
				row.createCell(4).setCellValue(laundryTracking.getGymClothings());
				totalGym = totalGym+laundryTracking.getGymClothings();
				row.createCell(5).setCellValue(laundryTracking.getJockSocksBras());
				row.createCell(6).setCellValue(laundryTracking.getUniforms());
				row.createCell(7).setCellValue(laundryTracking.getRegLaundry());
				row.createCell(8).setCellValue(laundryTracking.getDMD0006G());
				row.createCell(9).setCellValue(laundryTracking.getFAD0006E());
				row.createCell(10).setCellValue(laundryTracking.getCTD0006D());
				row.createCell(11).setCellValue(laundryTracking.getATFSABT0006H());
				row.createCell(12).setCellValue(laundryTracking.getPTD0006F());
				row.createCell(13).setCellValue(laundryTracking.getUSBOPB0006B());
				row.createCell(14).setCellValue(laundryTracking.getFPS0006C());
	           }
			HSSFRow trow = sheet.createRow(rowNum++);
			trow.createCell(0).setCellValue("Total");
			if(laundryListTotal.size()>0) {
				Object[] objects = (Object[]) laundryListTotal.get(0);
				for(int k=0 ; k<objects.length ; k++){
				//trow.createCell(3).setCellValue((Long)objects[0]);
				trow.createCell(k+3).setCellValue((Long)objects[k]);
				}
			}
			rowNum++;
			rowNum++;
			HSSFRow drHeader = sheet.createRow(rowNum++);
			drHeader.createCell(0).setCellValue("Created Time");
			drHeader.createCell(1).setCellValue("Dryer No");
			drHeader.createCell(2).setCellValue("Weight with Buggy");
			drHeader.createCell(3).setCellValue("Weight of Buggy");
			drHeader.createCell(4).setCellValue("Total weight");
			
			long ttlWeightWithBuggy = 0L;
			long ttlWeightBuggy = 0L;
			
			for(LaundryTracking laundryTracking : laundryList) {
				
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(1).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(2).setCellValue(laundryTracking.getWeightWithBuggy());
				row.createCell(3).setCellValue(laundryTracking.getWeightBuggy());
				row.createCell(4).setCellValue(laundryTracking.getWeightWithBuggy()-laundryTracking.getWeightBuggy());
				ttlWeightWithBuggy= ttlWeightWithBuggy+laundryTracking.getWeightWithBuggy();
				ttlWeightBuggy= ttlWeightBuggy+laundryTracking.getWeightBuggy();
				
				
			}
			
			HSSFRow drow = sheet.createRow(rowNum++);
			drow.createCell(0).setCellValue("Total");
			drow.createCell(2).setCellValue(ttlWeightWithBuggy);
			drow.createCell(3).setCellValue(ttlWeightBuggy);
			drow.createCell(4).setCellValue(ttlWeightWithBuggy-ttlWeightBuggy);		
			
	}

}
