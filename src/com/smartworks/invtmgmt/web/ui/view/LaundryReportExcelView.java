package com.smartworks.invtmgmt.web.ui.view;

import java.text.MessageFormat;
import java.util.ArrayList;
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

public class LaundryReportExcelView extends AbstractExcelView {
	
	private static List<String> custList = new ArrayList<String>();
	private static String customerCodeFormat="1006a {0}";
	private static final Double buggyWeight=48d; // Constant per suresh
	
	protected void buildExcelDocument(Map model,
            HSSFWorkbook workbook,
            HttpServletRequest request,
            HttpServletResponse response)
	  {
		  response.setContentType("application/vnd.ms-excel");
		  response.setHeader("Content-disposition", "attachment; filename=LaundryReport.xls");
		  
		  List<LaundryTracking> laundryList = (List<LaundryTracking>) model.get("laundryList");
		  List laundryListTotal = (List) model.get("laundryListTotal");
		  
		  HSSFSheet sheet = workbook.createSheet("Laundry Report");
			 
			HSSFRow title = sheet.createRow(0);
			title.createCell(0).setCellValue("Laundry Weigh-Ins by Date Range - FLETCGA");
			HSSFRow header = sheet.createRow(1);
			header.createCell(0).setCellValue("Customer");
			header.createCell(1).setCellValue("Created Time");
			header.createCell(2).setCellValue("Location");
			header.createCell(3).setCellValue("Laundered Item");
			header.createCell(4).setCellValue("Total Weight");
			header.createCell(5).setCellValue("Buggy Weight");
			header.createCell(6).setCellValue("Item Weight");
			header.createCell(7).setCellValue("UOM");
			
			int rowNum = 2;
			for(LaundryTracking laundryTracking : laundryList) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(MessageFormat.format(customerCodeFormat, "GYM"));
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("GYM CLOTHS");
				row.createCell(4).setCellValue(laundryTracking.getGymClothings()+buggyWeight);
				row.createCell(5).setCellValue(buggyWeight);
				row.createCell(6).setCellValue(laundryTracking.getGymClothings());
				row.createCell(7).setCellValue("LB");
			}
			for(LaundryTracking laundryTracking : laundryList) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(MessageFormat.format(customerCodeFormat, "TSE"));
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("WHITES");
				row.createCell(4).setCellValue(laundryTracking.getTseRoom()+buggyWeight);
				row.createCell(5).setCellValue(buggyWeight);
				row.createCell(6).setCellValue(laundryTracking.getTseRoom());
				row.createCell(7).setCellValue("LB");
			}
			for(LaundryTracking laundryTracking : laundryList) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(MessageFormat.format(customerCodeFormat, "Towels"));
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("TOWELS");
				row.createCell(4).setCellValue(laundryTracking.getTowels()+buggyWeight);
				row.createCell(5).setCellValue(buggyWeight);
				row.createCell(6).setCellValue(laundryTracking.getTowels());
				row.createCell(7).setCellValue("LB");
			}
			for(LaundryTracking laundryTracking : laundryList) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(MessageFormat.format(customerCodeFormat, "Reg Laundry"));
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("REG LAUNDRY");
				row.createCell(4).setCellValue(laundryTracking.getRegLaundry()+buggyWeight);
				row.createCell(5).setCellValue(buggyWeight);
				row.createCell(6).setCellValue(laundryTracking.getRegLaundry());
				row.createCell(7).setCellValue("LB");
			}
			for(LaundryTracking laundryTracking : laundryList) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(MessageFormat.format(customerCodeFormat, "Uniforms"));
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("UNIFORMS");
				row.createCell(4).setCellValue(laundryTracking.getUniforms()+buggyWeight);
				row.createCell(5).setCellValue(buggyWeight);
				row.createCell(6).setCellValue(laundryTracking.getUniforms());
				row.createCell(7).setCellValue("LB");
			}
			for(LaundryTracking laundryTracking : laundryList) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue("DMD 0006-G");
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("DMD_0006_G");
				row.createCell(4).setCellValue(laundryTracking.getDMD0006G()+buggyWeight);
				row.createCell(5).setCellValue(buggyWeight);
				row.createCell(6).setCellValue(laundryTracking.getDMD0006G());
				row.createCell(7).setCellValue("LB");
			}
			for(LaundryTracking laundryTracking : laundryList) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue("FAD 0006-E");
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("FAD_0006_E");
				row.createCell(4).setCellValue(laundryTracking.getFAD0006E()+buggyWeight);
				row.createCell(5).setCellValue(buggyWeight);
				row.createCell(6).setCellValue(laundryTracking.getFAD0006E());
				row.createCell(7).setCellValue("LB");
			}
			
			for(LaundryTracking laundryTracking : laundryList) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue("CTD 0006-D");
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("CTD_0006_D");
				row.createCell(4).setCellValue(laundryTracking.getCTD0006D()+buggyWeight);
				row.createCell(5).setCellValue(buggyWeight);
				row.createCell(6).setCellValue(laundryTracking.getCTD0006D());
				row.createCell(7).setCellValue("LB");
			}
			for(LaundryTracking laundryTracking : laundryList) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue("ATFSABT0006-H");
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("ATF_SABT_0006_H");
				row.createCell(4).setCellValue(laundryTracking.getATFSABT0006H()+buggyWeight);
				row.createCell(5).setCellValue(buggyWeight);
				row.createCell(6).setCellValue(laundryTracking.getATFSABT0006H());
				row.createCell(7).setCellValue("LB");
			}
			for(LaundryTracking laundryTracking : laundryList) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue("PTD0006-F");
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("PTD_0006_F");
				row.createCell(4).setCellValue(laundryTracking.getPTD0006F()+buggyWeight);
				row.createCell(5).setCellValue(buggyWeight);
				row.createCell(6).setCellValue(laundryTracking.getPTD0006F());
				row.createCell(7).setCellValue("LB");
			}
			for(LaundryTracking laundryTracking : laundryList) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue("USBOPB0006-B");
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("USBOPB_0006_B");
				row.createCell(4).setCellValue(laundryTracking.getUSBOPB0006B()+buggyWeight);
				row.createCell(5).setCellValue(buggyWeight);
				row.createCell(6).setCellValue(laundryTracking.getUSBOPB0006B());
				row.createCell(7).setCellValue("LB");
			}
			for(LaundryTracking laundryTracking : laundryList) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue("FPS0006-C");
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("FPS_0006_C");
				row.createCell(4).setCellValue(laundryTracking.getFPS0006C()+buggyWeight);
				row.createCell(5).setCellValue(buggyWeight);
				row.createCell(6).setCellValue(laundryTracking.getFPS0006C());
				row.createCell(7).setCellValue("LB");
			}
			
	  }
}
