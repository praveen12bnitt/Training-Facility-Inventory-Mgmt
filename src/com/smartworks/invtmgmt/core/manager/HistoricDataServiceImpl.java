package com.smartworks.invtmgmt.core.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.core.dao.HistoricIssuesDao;
import com.smartworks.invtmgmt.core.domain.HistoricIssues;

@Service
@Transactional(readOnly = false)
public class HistoricDataServiceImpl implements HistoricDataService {
	
	@Autowired
	private HistoricIssuesDao historicDao;
	
	@Override
	public List<HistoricIssues> getAllHistoricIsssues() {
		return historicDao.getAllIssues();
	}
	
	@Override
	public void importHistoricIssues(String issueFilePath) {
		List<HistoricIssues> hissuesList = new ArrayList<HistoricIssues>();
		
		try {
			InputStream is = new FileInputStream(new File(issueFilePath));
			Workbook workBook = new XSSFWorkbook(is);
			Sheet sheet = workBook.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			// Skip the first row
			rows.next();
			while(rows.hasNext()) {
				HistoricIssues hissues = new HistoricIssues();
				Row row = null;
				try {
					int i = 0;
					row = rows.next(); 				
					Date date = row.getCell(i++).getDateCellValue();
					hissues.setIssueDate(new Timestamp(date.getTime()));
					
					String zone = row.getCell(i++).getStringCellValue();
					hissues.setZone(zone);
					
					String className = row.getCell(i++).getStringCellValue();
					hissues.setClassName(className);
					
					String classNumber = null;
					try {
						Double d = row.getCell(i++).getNumericCellValue();
						try {
							classNumber = String.valueOf(d.intValue());
						} catch(Exception e) {
							classNumber = d.toString();
						}
						
					} catch(IllegalStateException ise) {
						classNumber = row.getCell(i-1).getStringCellValue();
					} 					
					hissues.setClassNumber(classNumber);
					
					String issueType = row.getCell(i++).getStringCellValue();
					hissues.setIssueType(StringUtils.trim(issueType));
					
					String custType = row.getCell(i++).getStringCellValue();
					hissues.setCustomerType(custType);
					
					Date dueDate = null;
					
					try {
						dueDate = row.getCell(i++).getDateCellValue();
						hissues.setDueDate(new Timestamp(dueDate.getTime())); 						
					} catch(IllegalStateException e) {
						// Ignore the value
					} 					
					Double issueId = row.getCell(i++).getNumericCellValue();
					hissues.setIssueId(issueId.intValue());
					
					String status = row.getCell(i++).getStringCellValue();
					hissues.setStatus(status);
					
					String firstName = row.getCell(i++).getStringCellValue();
					hissues.setFirstName(firstName);
					
					String lastName = row.getCell(i++).getStringCellValue();
					hissues.setLastName(lastName);
					
					String initial;
					
					try {
						initial = row.getCell(i++).getStringCellValue();
						hissues.setInitial(initial);
					} catch(IllegalStateException e){
						Double temp = row.getCell(i-1).getNumericCellValue();
						hissues.setInitial(temp.toString());
					}
					
					String gender = row.getCell(i++).getStringCellValue();
					hissues.setGender(gender);
					
					Double nsn = row.getCell(i++).getNumericCellValue();
					hissues.setNsn(nsn.longValue());
					
					String nom = row.getCell(i++).getStringCellValue();
					hissues.setNomenclature(nom);
					
					Double qty = row.getCell(i++).getNumericCellValue();
					hissues.setQty(qty.intValue());
					
					hissuesList.add(hissues);
					
				} catch(Exception e) {
//					System.out.println(row);
					e.printStackTrace();
				} 			
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Size of proessed items"+hissuesList.size());
		
		historicDao.insertHistoricIssues(hissuesList);
		
		
		
	}
	
	public static void main(String[] args) {
		HistoricDataServiceImpl hServ = new HistoricDataServiceImpl();
		hServ.importHistoricIssues("C:/all-issues.xlsx");
//		hServ.importHistoricIssues("C:/a.xlsx");
		
		System.out.println("Completed");
	}
	
}
