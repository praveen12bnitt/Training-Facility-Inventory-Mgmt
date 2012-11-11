package com.smartworks.invtmgmt.core.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartworks.invtmgmt.core.dao.HistoricIssuesDao;
import com.smartworks.invtmgmt.core.dao.HistoricReturnsDao;
import com.smartworks.invtmgmt.core.domain.HistoricIssues;
import com.smartworks.invtmgmt.core.domain.HistoricReturns;

@Service
@Transactional(readOnly = false)
public class HistoricDataServiceImpl implements HistoricDataService {
	
	@Autowired
	private HistoricIssuesDao historicDao;
	
	@Autowired
	private HistoricReturnsDao hReturnsDao;
	
	private SimpleDateFormat dateFormatHistoricReturns = new SimpleDateFormat("MM/dd/yy");
	
	@Override
	public List<HistoricIssues> getAllHistoricIsssues() {
		return historicDao.getAllIssues();
	}
	
	@Override
	public List<HistoricReturns> getAllHistoricReturns() {
		return hReturnsDao.getAllReturns();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void persisHistoricReturns(List<HistoricReturns> hissuesReturns) 
	{
		hReturnsDao.insertHistoricReturns(hissuesReturns);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public void importHistoricReturns1(String returnsFilePath)  {

		try {
			BufferedReader readbuffer = new BufferedReader(new FileReader(returnsFilePath));
			String strRead;
			List<HistoricReturns> hissuesReturns = new ArrayList<HistoricReturns>();
			while ((strRead = readbuffer.readLine()) != null) { 
				HistoricReturns hReturns = new HistoricReturns();
				String splitarray[] = strRead.split("\t");
				
				Integer returnId = Integer.valueOf(splitarray[0].trim());
				hReturns.setReturnId(returnId);			
				
				Date date = dateFormatHistoricReturns.parse(splitarray[1].trim());
				hReturns.setDate(new Timestamp(date.getTime()));
				
				Integer returnQty = Integer.valueOf(splitarray[2].trim());
				hReturns.setReturnQty(returnQty);
				
				Integer lostQty = Integer.parseInt(splitarray[3].trim());
				hReturns.setLostQty(lostQty);
				
				Integer addNsn = Integer.valueOf(splitarray[4].trim());
				hReturns.setAddNsn(addNsn);
				
				try {
					Long ssn = Long.valueOf(splitarray[5].trim());
					hReturns.setSsn(ssn);
				} catch(Exception e) {
					
				}
				
				
				String lastName = splitarray[6].trim();
				hReturns.setLastName(lastName);
				
				String firstName = splitarray[7].trim();
				hReturns.setFirstName(firstName);
				
				String siteName = splitarray[8].trim();
				hReturns.setSiteName(siteName);
				
				String lostReason = splitarray[9].trim();
				hReturns.setLostReason(lostReason);
				
				Integer materielid = Integer.valueOf(splitarray[10].trim());
				hReturns.setMaterielid(materielid);
				
				Integer siteId = Integer.valueOf(splitarray[11].trim());
				hReturns.setSite(siteId);
				
				String username = splitarray[12].trim();
				hReturns.setUserId(username);
				
				String unitName = splitarray[13].trim();
				hReturns.setUnitname(unitName);
				
				Integer repairQty = Integer.valueOf(splitarray[14].trim());
				hReturns.setRepairQty(repairQty); 	 
				
				hissuesReturns.add(hReturns);
						
			}
			persisHistoricReturns(hissuesReturns); 			
		} catch(Exception e) {
			e.printStackTrace();
		} 

	}

	public void importHistoricReturns(String returnsFilePath) {
		List<HistoricReturns> hissuesList = new ArrayList<HistoricReturns>();
		
		try {
			InputStream is = new FileInputStream(new File(returnsFilePath));
			Workbook workBook = new XSSFWorkbook(is);
			Sheet sheet = workBook.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			// Skip the first row
			rows.next();
			while(rows.hasNext()) {
				HistoricReturns hReturns = new HistoricReturns();
				Row row = null;
				try {
					
					int i = 0;
					row = rows.next(); 
					Double returnId = row.getCell(i++).getNumericCellValue();
					hReturns.setReturnId(returnId.intValue());
					
					Date date = row.getCell(i++).getDateCellValue();
					hReturns.setDate(new Timestamp(date.getTime()));
					
					Double returnQty = row.getCell(i++).getNumericCellValue();
					hReturns.setReturnQty(returnQty.intValue());
					
					Double lostQty = row.getCell(i++).getNumericCellValue();
					hReturns.setLostQty(lostQty.intValue()); 					
					
					Double addNsn = row.getCell(i++).getNumericCellValue();
					hReturns.setAddNsn(addNsn.intValue());
					
					Double ssn = row.getCell(i++).getNumericCellValue();
					hReturns.setSsn(ssn.longValue());
					
					String firstName = row.getCell(i++).getStringCellValue();
					hReturns.setFirstName(firstName);

					String siteName = row.getCell(i++).getStringCellValue();
					hReturns.setSiteName(siteName);
					
					String lostReason = row.getCell(i++).getStringCellValue();
					hReturns.setLostReason(lostReason);
					
					Double materielid = row.getCell(i++).getNumericCellValue();
					hReturns.setMaterielid(materielid.intValue());
					
					Double siteId = row.getCell(i++).getNumericCellValue();
					hReturns.setSite(siteId.intValue());
					
					String userId =  row.getCell(i++).getStringCellValue();
					hReturns.setUserId(userId);
					
					String unitname = row.getCell(i++).getStringCellValue();
					hReturns.setUnitname(unitname);
					
					Double repairQty = row.getCell(i++).getNumericCellValue();
					hReturns.setRepairQty(repairQty.intValue());
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
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
	
	public static void main(String[] args) throws IOException, ParseException {
		HistoricDataServiceImpl hServ = new HistoricDataServiceImpl();
//		hServ.importHistoricIssues("C:/all-issues.xlsx");
//		hServ.importHistoricIssues("C:/missing-issues.xlsx");
		
//		hServ.importHistoricReturns("C:\\temp\\srm\\VAM - Transactions RETURNS 110112 5PM.xlsx");
		
		hServ.importHistoricReturns1("C:\\temp\\srm\\5.txt");
		
		
		System.out.println("Completed");
	}
	
}
