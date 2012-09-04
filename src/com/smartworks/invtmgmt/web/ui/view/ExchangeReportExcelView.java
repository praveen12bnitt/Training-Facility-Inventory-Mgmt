package com.smartworks.invtmgmt.web.ui.view;

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

import com.smartworks.invtmgmt.converter.ItemSkuConverter;
import com.smartworks.invtmgmt.core.dao.StaffDao;
import com.smartworks.invtmgmt.core.dao.TraineeDao;
import com.smartworks.invtmgmt.core.domain.ExchangeSkuRecord;
import com.smartworks.invtmgmt.core.domain.Staff;
import com.smartworks.invtmgmt.core.domain.Trainee;
import com.smartworks.invtmgmt.core.domain.TransactionDetails;
import com.smartworks.invtmgmt.core.domain.TransactionTrace;
import com.smartworks.invtmgmt.core.service.DateUtil;
import com.smartworks.platform.AppContextUtil;

public class ExchangeReportExcelView extends AbstractExcelView {
	

	protected void buildExcelDocument(Map model,
            HSSFWorkbook workbook,
            HttpServletRequest request,
            HttpServletResponse response)
	  {
		  response.setContentType("application/vnd.ms-excel");
		  response.setHeader("Content-disposition", "attachment; filename=ExchangeReport.xls");
		  
		  List<ExchangeSkuRecord> exchangeSkuList = (List<ExchangeSkuRecord>)model.get("exchangeSkuList");
		  
		  HSSFSheet sheet = workbook.createSheet("Exchange Report");
			 
		  	Font font = workbook.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(font);
			cellStyle.setBorderRight(HSSFBorderFormatting.BORDER_THIN);
			cellStyle.setBorderLeft(HSSFBorderFormatting.BORDER_THIN);
			cellStyle.setBorderTop(HSSFBorderFormatting.BORDER_THIN);
			cellStyle.setBorderBottom(HSSFBorderFormatting.BORDER_THIN);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
			
			HSSFRow title = sheet.createRow(0);
			HSSFCell cell = title.createCell(0);
			cell.setCellValue("Exchange Report by Date Range - FLETCGA");
			cell.setCellStyle(cellStyle);
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
			
			
			
			HSSFRow header = sheet.createRow(1);
						
			cell = header.createCell(0);
			cell.setCellValue("Date");
			cell.setCellStyle(cellStyle);
			
			cell = header.createCell(1);
			cell.setCellValue("Name");
			cell.setCellStyle(cellStyle);
			
			cell = header.createCell(2);
			cell.setCellValue("Type");
			cell.setCellStyle(cellStyle);
			
			cell = header.createCell(3);
			cell.setCellValue("Issued Item");
			cell.setCellStyle(cellStyle);			
			
			cell = header.createCell(4);
			cell.setCellValue("Exchanged Item");
			cell.setCellStyle(cellStyle);
			
			cell = header.createCell(5);
			cell.setCellValue("Quantity");
			cell.setCellStyle(cellStyle);
			
			TraineeDao traineeDao = AppContextUtil.getBean("traineeDao");
			StaffDao staffDao = AppContextUtil.getBean("staffDao");
			ItemSkuConverter itemSkuConverter = AppContextUtil.getBean("itemSkuConverter");
			
			int rowNum = 2;
			for(ExchangeSkuRecord exchangeSkuRecord : exchangeSkuList) {
				TransactionDetails transactionDetails = exchangeSkuRecord.getTransactionDetails();
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(DateUtil.getExpandedTimeStamp(exchangeSkuRecord.getDttm()));
				
				TransactionTrace transactionTrace = transactionDetails.getTrasactionTrace();
				if(transactionTrace.getTraineeId() != null) {
					Trainee trainee = traineeDao.load(transactionTrace.getTraineeId());
					row.createCell(1).setCellValue(trainee.displayName());
					row.createCell(2).setCellValue("TRAINEE");
				}
				
				if(transactionTrace.getStaffId() != null) {
					Staff staff = staffDao.load(transactionTrace.getStaffId());
					row.createCell(1).setCellValue(staff.displayName());
					row.createCell(2).setCellValue("STAFF");
				}
				
				row.createCell(3).setCellValue(itemSkuConverter.getItemSkuAsStr(exchangeSkuRecord.getIssuedSku()));
				row.createCell(4).setCellValue(itemSkuConverter.getItemSkuAsStr(exchangeSkuRecord.getExchangedSku()));
				row.createCell(5).setCellValue(transactionDetails.getQuantity());
				
				
			}
			for(int k=0; k<6; k++) {
				sheet.autoSizeColumn(k);
			}
			
	  }

	
}
