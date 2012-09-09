package com.smartworks.invtmgmt.core.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.smartworks.invtmgmt.core.dao.ClassDao;
import com.smartworks.invtmgmt.core.dao.StaffDao;
import com.smartworks.invtmgmt.core.dao.TraineeDao;
import com.smartworks.invtmgmt.core.dao.impl.ProductDaoImpl;
import com.smartworks.invtmgmt.core.domain.Class;
import com.smartworks.invtmgmt.core.domain.Product;
import com.smartworks.invtmgmt.core.domain.Staff;
import com.smartworks.invtmgmt.core.domain.Trainee;

@Transactional
@Service
public class ClassMgrImpl implements ClassMgr{
	
	@Autowired
	ClassDao classDao;
	
	@Autowired
	private ProductDaoImpl productDao;
	
	@Autowired
	private TraineeDao traineeDao;
	
	@Autowired
	private StaffDao staffDao;
	
	public ClassDao getClassDao() {
		return classDao;
	}

	public void setClassDao(ClassDao classDao) {
		this.classDao = classDao;
	}

	@Override
	public void add(Class t) {
		classDao.save(t);
	}

	@Override
	public void update(Class t) {
		classDao.update(t);
		
	}

	@Override
	public Class load(String className) {
		Class classes = classDao.load(className);
		return classes;
	}

	@Override
	public List<Class> loadAll() {
		List<Class> classes = classDao.loadAll();
		return classes;
	}
	
	@Override
	public List<String> getItemMaps(String name) {
			
		List<String> itemMap = classDao.getItemsByNameLike(name);
		
		return itemMap;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public void saveClass(Class clazz, Integer[] selectedProducts, Integer[] selectedStaffs, MultipartFile traineeFile) { 		
		try {
			if(selectedProducts != null) {
				Set<Product> pds = new HashSet<Product>();
				for(Integer productId : selectedProducts) {
					Product product = productDao.load(productId);
					pds.add(product);
				} 		
				clazz.setProducts(pds);	 
			}
			
			
			if(selectedStaffs != null) {
				Set<Staff> staffs = new HashSet<Staff>();
				for(Integer staffId : selectedStaffs) {
					Staff staff = staffDao.load(staffId);
					staffs.add(staff);
				} 		
				clazz.setStaffs(staffs); 	
			} 		
			
			if(traineeFile != null && traineeFile.getSize() > 0) {
				for(Trainee trainee : getTraineeObjFromFile(traineeFile)) {
					clazz.addTrainee(trainee);
				} 			
			} 		
			classDao.saveOrUpdate(clazz) ;	
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	private List<Trainee> getTraineeObjFromFile(MultipartFile file) throws IOException {
		List<Trainee> traineeList = new ArrayList<Trainee>(); 		
		HSSFWorkbook workBook = new HSSFWorkbook(file.getInputStream());
		HSSFSheet sheet = workBook.getSheetAt(0); 		
		Iterator<Row> rows = sheet.rowIterator();
		boolean firstRow = true;
		while(rows.hasNext()) {
			if(firstRow)  {
				firstRow = false;
				// We assume that this is a header.. So ignoring it.
				continue; 				
			}
			Row row = rows.next();
			Trainee trainee = new Trainee();
			String fName = row.getCell(0).getStringCellValue();
			if(StringUtils.isNotEmpty(fName)) { 			
				trainee.setFirstName(fName);
				trainee.setMiddleName(row.getCell(1).getStringCellValue());
				trainee.setLastName(row.getCell(2).getStringCellValue());	
				traineeList.add(trainee); 		
			}
		}		
		return traineeList;
		
		
	}
	

}
