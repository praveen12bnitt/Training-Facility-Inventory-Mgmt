package com.smartworks.invtmgmt.web.ui.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFBorderFormatting;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.smartworks.invtmgmt.core.domain.Laundry;
import com.smartworks.invtmgmt.core.service.DateUtil;

public class LaundryReportExcelView extends AbstractExcelView {
	
		
	
	protected void buildExcelDocument(Map model,
            HSSFWorkbook workbook,
            HttpServletRequest request,
            HttpServletResponse response)
	  {
		  response.setContentType("application/vnd.ms-excel");
		  response.setHeader("Content-disposition", "attachment; filename=LaundryReport.xls");
		  
		  List<Laundry> laundryList = (List<Laundry>) model.get("laundryList");
		  String laundryType = (String)model.get("laundryType");
		  
		  Map<String, String> laundryLabels = new HashMap<String, String>();
		  laundryLabels.put("W.Time", "Time of Washing");
		  laundryLabels.put("W.UnitNo", "Washer No");
		  laundryLabels.put("W.Weight", "Dirty Weight");
		  
		  laundryLabels.put("D.Time", "Time of Drying");
		  laundryLabels.put("D.UnitNo", "Dryer No");
		  laundryLabels.put("D.Weight", "Clean Weight");
		  
		  HSSFSheet sheet = workbook.createSheet("Laundry Report");
		  
		  /********************************************************/
		    Font font = workbook.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(font);
			cellStyle.setBorderRight(HSSFBorderFormatting.BORDER_THIN);
			cellStyle.setBorderLeft(HSSFBorderFormatting.BORDER_THIN);
			cellStyle.setBorderTop(HSSFBorderFormatting.BORDER_THIN);
			cellStyle.setBorderBottom(HSSFBorderFormatting.BORDER_THIN);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
		  /********************************************************/
			 
			HSSFRow title = sheet.createRow(0);
			HSSFCell cell = title.createCell(0);
			cell.setCellValue("Laundry Weigh-Ins by Date Range - FLETCGA");
			
			cell.setCellStyle(cellStyle);
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));
			
			HSSFRow header = sheet.createRow(1);
			
			cell = header.createCell(0);
			cell.setCellValue(laundryLabels.get(laundryType+".UnitNo"));
			cell.setCellStyle(cellStyle);
			
			cell = header.createCell(1);
			cell.setCellValue(laundryLabels.get(laundryType+".Time"));
			cell.setCellStyle(cellStyle);
			
			cell = header.createCell(2);
			cell.setCellValue("Client Info");
			cell.setCellStyle(cellStyle);
			
			cell = header.createCell(3);
			cell.setCellValue(laundryLabels.get(laundryType+".Weight"));
			cell.setCellStyle(cellStyle);
			
			cell = header.createCell(4);
			cell.setCellValue("Buggy Weight");
			cell.setCellStyle(cellStyle);
			
			cell = header.createCell(5);
			cell.setCellValue("Total Weight");
			cell.setCellStyle(cellStyle);
			
			cell = header.createCell(6);
			cell.setCellValue("UOM");
			cell.setCellStyle(cellStyle);
			
			
			
			int rowNum = 2;
			
			for(Laundry laundry: laundryList) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(laundry.getUnitNo());
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundry.getCreatedDttm()));
				String subGroup = "";
				if(laundry.getClientSubGroup() != null) subGroup = laundry.getClientSubGroup();
				row.createCell(2).setCellValue(laundry.getClientGroup()+"-"+subGroup);
				row.createCell(3).setCellValue(laundry.getTotalWeight());
				row.createCell(4).setCellValue(laundry.getBuggyWeight());
				row.createCell(5).setCellValue(laundry.getWeight());
				row.createCell(6).setCellValue("LB");
			}
			
			/**for(LaundryTracking laundryTracking : laundryList) {
				if(laundryTracking.getGymClothings()!=null) {
				HSSFRow row = sheet.createRow(rowNum++);
				
				row.createCell(0).setCellValue(MessageFormat.format(customerCodeFormat, "GYM"));
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("GYM CLOTHS");
				//if(laundryTracking.getGymClothings()!=null) {
					row.createCell(4).setCellValue(laundryTracking.getGymClothings()+buggyWeight);
					row.createCell(6).setCellValue(laundryTracking.getGymClothings());
				//}
				row.createCell(5).setCellValue(buggyWeight);
				
				row.createCell(7).setCellValue("LB");
				}
			}
			for(LaundryTracking laundryTracking : laundryList) {
				if(laundryTracking.getTseRoom()!=null) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(MessageFormat.format(customerCodeFormat, "TSE"));
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("WHITES");
				//if(laundryTracking.getTseRoom()!=null) {
					row.createCell(4).setCellValue(laundryTracking.getTseRoom()+buggyWeight);
					row.createCell(6).setCellValue(laundryTracking.getTseRoom());
				//}
				row.createCell(5).setCellValue(buggyWeight);
				
				row.createCell(7).setCellValue("LB");
				}
			}
			for(LaundryTracking laundryTracking : laundryList) {
				if(laundryTracking.getTowels()!=null) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(MessageFormat.format(customerCodeFormat, "Towels"));
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("TOWELS");
				//if(laundryTracking.getTowels()!=null) {
					row.createCell(4).setCellValue(laundryTracking.getTowels()+buggyWeight);
					row.createCell(6).setCellValue(laundryTracking.getTowels());
				//}
				row.createCell(5).setCellValue(buggyWeight);
				
				row.createCell(7).setCellValue("LB");
				}
			}
			for(LaundryTracking laundryTracking : laundryList) {
				if(laundryTracking.getRegLaundry()!=null) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(MessageFormat.format(customerCodeFormat, "Reg Laundry"));
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("REG LAUNDRY");
				//if(laundryTracking.getRegLaundry()!=null) {
					row.createCell(4).setCellValue(laundryTracking.getRegLaundry()+buggyWeight);
					row.createCell(6).setCellValue(laundryTracking.getRegLaundry());
				//}
				row.createCell(5).setCellValue(buggyWeight);
				
				row.createCell(7).setCellValue("LB");
				}
			}
			for(LaundryTracking laundryTracking : laundryList) {
				if(laundryTracking.getUniforms()!=null) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(MessageFormat.format(customerCodeFormat, "Uniforms"));
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("UNIFORMS");
				//if(laundryTracking.getUniforms()!=null) {
					row.createCell(4).setCellValue(laundryTracking.getUniforms()+buggyWeight);
					row.createCell(6).setCellValue(laundryTracking.getUniforms());
				//}
				row.createCell(5).setCellValue(buggyWeight);
				
				row.createCell(7).setCellValue("LB");
				}
			}
			for(LaundryTracking laundryTracking : laundryList) {
				if(laundryTracking.getDMD0006G()!=null) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue("DMD 0006-G");
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("DMD_0006_G");
				//if(laundryTracking.getDMD0006G()!=null) {
					row.createCell(4).setCellValue(laundryTracking.getDMD0006G()+buggyWeight);
					row.createCell(6).setCellValue(laundryTracking.getDMD0006G());
				//}
				row.createCell(5).setCellValue(buggyWeight);
				
				row.createCell(7).setCellValue("LB");
				}
			}
			for(LaundryTracking laundryTracking : laundryList) {
				if(laundryTracking.getFAD0006E()!=null) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue("FAD 0006-E");
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("FAD_0006_E");
				//if(laundryTracking.getFAD0006E()!=null) {
					row.createCell(4).setCellValue(laundryTracking.getFAD0006E()+buggyWeight);
					row.createCell(6).setCellValue(laundryTracking.getFAD0006E());
				//}
				row.createCell(5).setCellValue(buggyWeight);
				
				row.createCell(7).setCellValue("LB");
				}
			}
			
			for(LaundryTracking laundryTracking : laundryList) {
				if(laundryTracking.getCTD0006D()!=null) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue("CTD 0006-D");
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("CTD_0006_D");
				//if(laundryTracking.getCTD0006D()!=null) {
					row.createCell(4).setCellValue(laundryTracking.getCTD0006D()+buggyWeight);
					row.createCell(6).setCellValue(laundryTracking.getCTD0006D());
				//}
				row.createCell(5).setCellValue(buggyWeight);
				
				row.createCell(7).setCellValue("LB");
				}
			}
			for(LaundryTracking laundryTracking : laundryList) {
				if(laundryTracking.getATFSABT0006H()!=null) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue("ATFSABT0006-H");
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("ATF_SABT_0006_H");
				//if(laundryTracking.getATFSABT0006H()!=null) {
					row.createCell(4).setCellValue(laundryTracking.getATFSABT0006H()+buggyWeight);
					row.createCell(6).setCellValue(laundryTracking.getATFSABT0006H());
				//}
				row.createCell(5).setCellValue(buggyWeight);
				
				row.createCell(7).setCellValue("LB");
				}
			}
			for(LaundryTracking laundryTracking : laundryList) {
				if(laundryTracking.getPTD0006F()!=null) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue("PTD0006-F");
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("PTD_0006_F");
				//if(laundryTracking.getPTD0006F()!=null) {
					row.createCell(4).setCellValue(laundryTracking.getPTD0006F()+buggyWeight);
					row.createCell(6).setCellValue(laundryTracking.getPTD0006F());
				//}
				row.createCell(5).setCellValue(buggyWeight);
				
				row.createCell(7).setCellValue("LB");
				}
			}
			for(LaundryTracking laundryTracking : laundryList) {
				if(laundryTracking.getUSBOPB0006B()!=null) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue("USBOPB0006-B");
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("USBOPB_0006_B");
				//if(laundryTracking.getUSBOPB0006B()!=null) {
					row.createCell(4).setCellValue(laundryTracking.getUSBOPB0006B()+buggyWeight);
					row.createCell(6).setCellValue(laundryTracking.getUSBOPB0006B());
				//}
				row.createCell(5).setCellValue(buggyWeight);
				
				row.createCell(7).setCellValue("LB");
				}
			}
			for(LaundryTracking laundryTracking : laundryList) {
				if(laundryTracking.getFPS0006C()!=null) {
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue("FPS0006-C");
				row.createCell(1).setCellValue(DateUtil.getExpandedTimeStamp(laundryTracking.getCreatedDttm()));
				row.createCell(2).setCellValue(laundryTracking.getDryerMachineNo());
				row.createCell(3).setCellValue("FPS_0006_C");
				//if(laundryTracking.getFPS0006C()!=null) {
					row.createCell(4).setCellValue(laundryTracking.getFPS0006C()+buggyWeight);
					row.createCell(6).setCellValue(laundryTracking.getFPS0006C());
				//}
				row.createCell(5).setCellValue(buggyWeight);
				
				row.createCell(7).setCellValue("LB");
				}
			}**/
			for(int k=0; k<7; k++) {
				sheet.autoSizeColumn(k);
			}
			
	  }
}
