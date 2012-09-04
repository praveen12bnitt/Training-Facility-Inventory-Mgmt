package com.smartworks.invtmgmt.core.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smartworks.invtmgmt.business.ItemAttributeDetails;
import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.converter.ItemSkuConverter;
import com.smartworks.invtmgmt.core.dao.ClassDao;
import com.smartworks.invtmgmt.core.dao.InventoryDao;
import com.smartworks.invtmgmt.core.dao.ItemAttributeDao;
import com.smartworks.invtmgmt.core.dao.ItemAttributeValueDao;
import com.smartworks.invtmgmt.core.dao.ItemDao;
import com.smartworks.invtmgmt.core.dao.LocationDao;
import com.smartworks.invtmgmt.core.dao.ProductDao;
import com.smartworks.invtmgmt.core.dao.TraineeDao;
import com.smartworks.invtmgmt.core.dao.impl.LocationDaoImpl;
import com.smartworks.invtmgmt.core.domain.Inventory;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.ItemAttribute;
import com.smartworks.invtmgmt.core.domain.ItemAttributeMapping;
import com.smartworks.invtmgmt.core.domain.ItemAttributeValue;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.Product;
import com.smartworks.invtmgmt.core.domain.ProductItem;
import com.smartworks.invtmgmt.core.domain.Trainee;
import com.smartworks.invtmgmt.core.domain.pk.InventoryPk;
import com.smartworks.invtmgmt.core.manager.CommonTransactionMgr;
import com.smartworks.invtmgmt.core.manager.InventoryManager;
import com.smartworks.invtmgmt.core.manager.ItemMgr;
import com.smartworks.invtmgmt.core.manager.ItemMgrImpl;
import com.smartworks.invtmgmt.core.manager.TraineeMgr;
import com.smartworks.invtmgmt.web.ui.datatransfer.DataTransferListener;

@Service
public class DataTransferService  {
	
	@Autowired
	private InventoryDao inventoryDao;
	
	@Autowired
	private LocationDao locationDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private TraineeMgr traineeManager;
	
	@Autowired
	private ItemAttributeDao itemAttributeDao;
	
	@Autowired
	private ItemAttributeValueDao itemAttributeValueDao;
	
	@Autowired
	private ItemSkuConverter itemSkuConvertor;
	
	@Autowired
	private InventoryManager inventoryManager;
	
	@Autowired
	private CommonTransactionMgr cmnTransMgr;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ClassDao classDao;
	
	@Autowired
	private TraineeDao traineeDao;
	
	@SuppressWarnings("unused")
	public boolean downloadIntentory(Integer locationId) {	
		Location location = locationDao.load(locationId);
		List<Inventory> inventoryList = inventoryDao.loadAll();
		
		for(Inventory inventory: inventoryList){
			ItemSku itemSku = itemSkuConvertor.getItemSku(inventory.getSkuCode());
			System.out.print(itemSku.getItem().getDesc());
			
			List<ItemAttributeDetails> itemAttributeDtls = itemSku.getItemAttributeDtls();
			for(ItemAttributeDetails itemAttributeDetail: itemAttributeDtls) {
				System.out.println("11::"+itemAttributeDetail.getItemAttribute().getAttributeName());
				System.out.println("22::"+itemAttributeDetail.getItemAttributeValue().getAttributeValue());
			}
		}
		
		
		return true;
	}
	
	@Deprecated
	public void syncInventoryData(String file) throws Exception {
		FileInputStream fin = new FileInputStream(file);
		POIFSFileSystem poifs = new POIFSFileSystem(fin);
		InputStream din = poifs.createDocumentInputStream("Workbook");
		HSSFRequest req = new HSSFRequest();
		req.addListenerForAllRecords(new DataTransferListener());
		HSSFEventFactory factory = new HSSFEventFactory();
		factory.processEvents(req, din);
		fin.close();
		din.close();
		System.out.println("done.");
	}
	
	public void syncInventory(File file) throws Exception {
		FileInputStream fin = new FileInputStream(file);
		try {
			HSSFWorkbook workBook = new HSSFWorkbook(fin);
			HSSFSheet sheet = workBook.getSheetAt(0);
			processSheet(sheet);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			fin.close();
		}		
	}

	private void processSheet(HSSFSheet sheet) {
		Iterator<Row> rows = sheet.rowIterator();
		ItemAttribute itemAttributeColor = itemAttributeDao.findByAttributeName("COLOR");
		ItemAttribute itemAttributeSize = itemAttributeDao.findByAttributeName("SIZE");
		if(rows.hasNext()) {
			rows.next(); // skip the first row, as it is heading
		}
		while(rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cells = row.cellIterator();
			int k=-1;
			Item item = new Item();
			Inventory inventory = new Inventory();
			
			ItemAttributeValue itemAttributeValueColor = null;;
			ItemAttributeValue itemAttributeValueSize = null;
			ItemAttributeDetails itemAttributeDetailsColor = new ItemAttributeDetails();
			ItemAttributeDetails itemAttributeDetailsSize = new ItemAttributeDetails();
			InventoryPk inventoryPK = new InventoryPk();
			inventory.setSkuLocation(inventoryPK);
			String attributeColor = null;
			String attributeSize = null;
		
			while(cells.hasNext()) {
				k++;
				Cell cell = cells.next();
				switch(k) {
					case 0:
						List<Item> itemList = itemDao.getItemsByName(cell.getStringCellValue());
						if(itemList !=null && itemList.size()>0) {
							item = itemList.get(0);
						} else {
							item = new Item();
							item.setName(cell.getStringCellValue());
						}
						break;
					case 1:
						item.setDesc(cell.getStringCellValue());
						break;
					case 2:
						attributeColor = cell.getStringCellValue();
						itemAttributeValueColor = itemAttributeValueDao.findByAttributeValue(attributeColor);
						break;
					case 3:
						attributeSize = cell.getStringCellValue();
						itemAttributeValueSize = itemAttributeValueDao.findByAttributeValue(attributeSize);
						break;
					case 4:
						inventory.setAvailableQty((int)cell.getNumericCellValue());
						break;
					case 5:
						inventory.setIssueQty((int)cell.getNumericCellValue());
						break;
					case 6:
						inventory.setUnusableQty((int)cell.getNumericCellValue());
						break;
					case 7:
						inventory.setLocation(locationDao.findByLocationName(cell.getStringCellValue()));
						break;
				}
			}
			
			if(itemAttributeValueColor==null) {
				itemAttributeValueColor = new ItemAttributeValue();
				Integer itemAttrValueId = itemAttributeValueDao.getNextItemAttributeValueId();
				itemAttributeValueColor.setAttributeValueId(itemAttrValueId);
				itemAttributeValueColor.setAttributeValue(attributeColor);
				itemAttributeValueDao.save(itemAttributeValueColor);
			}
			
			if(itemAttributeValueSize==null) {
				itemAttributeValueSize = new ItemAttributeValue();
				Integer itemAttrValueId = itemAttributeValueDao.getNextItemAttributeValueId();
				itemAttributeValueSize.setAttributeValueId(itemAttrValueId);
				itemAttributeValueSize.setAttributeValue(attributeSize);
				itemAttributeValueDao.save(itemAttributeValueSize);
			}
			
			
				
			itemAttributeDetailsColor.setItemAttribute(itemAttributeColor);
			itemAttributeDetailsColor.setItemAttributeValue(itemAttributeValueColor);
			
			itemAttributeDetailsSize.setItemAttribute(itemAttributeSize);
			itemAttributeDetailsSize.setItemAttributeValue(itemAttributeValueSize);
			
			List<ItemAttributeDetails> itemAttributeDetails = new ArrayList<ItemAttributeDetails>();
			itemAttributeDetails.add(itemAttributeDetailsColor);
			itemAttributeDetails.add(itemAttributeDetailsSize);
			
			ItemSku itemSku = new ItemSku(item, itemAttributeDetails);
			if(item.getId() == null) {
				//item.setAttributeDetails(attributeDetails);
				int mappingIdColor = itemDao.getNextMappingId();
				int mappingIdSize = itemDao.getNextMappingId() + 1;
				ItemAttributeMapping itemAttributeMappingColor = new ItemAttributeMapping();
				itemAttributeMappingColor.setAttribute(itemAttributeColor);
				itemAttributeMappingColor.setAttributeValue(itemAttributeValueColor);
				itemAttributeMappingColor.setMappingId(mappingIdColor);
				
				ItemAttributeMapping itemAttributeMappingSize = new ItemAttributeMapping();
				itemAttributeMappingSize.setAttribute(itemAttributeSize);
				itemAttributeMappingSize.setAttributeValue(itemAttributeValueSize);
				itemAttributeMappingSize.setMappingId(mappingIdSize);
				
				HashSet<ItemAttributeMapping> attributeMappings = new HashSet<ItemAttributeMapping>();
				attributeMappings.add(itemAttributeMappingColor);
				attributeMappings.add(itemAttributeMappingSize);
				item.setAttributeMappings(attributeMappings);
				item.setId(itemDao.getNextItemId());
				itemDao.save(item);
			}
			System.out.println("Next Id=="+itemAttributeDao.getNextAttributeId());
			inventory.setItemSku(itemSku);
			inventoryManager.updateInventory(inventory);
		}
		
	}

	public void syncKits(MultipartFile file) throws Exception {
		try {
			HSSFWorkbook workBook = new HSSFWorkbook(file.getInputStream());
			HSSFSheet sheet = workBook.getSheetAt(0);
			processKitsSheet(sheet);
		} catch(Exception e) {
			e.printStackTrace();
		} 		
	}
	public void syncTrainee(MultipartFile file) throws Exception {
		try {
			HSSFWorkbook workBook = new HSSFWorkbook(file.getInputStream());
			HSSFSheet sheet = workBook.getSheetAt(0);
			processTraineeSheet(sheet);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			file.getInputStream().close();
		}		
	}
	private void processTraineeSheet(HSSFSheet sheet) {
		Iterator<Row> rows = sheet.rowIterator();
		
		if(rows.hasNext()) {
			rows.next(); // skip the first row, as it is heading
		}
		while(rows.hasNext()){
			int k = -1;
			Row row = rows.next();
			Trainee trainee = new Trainee();
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()){
				k++;
				Cell cell = cellIterator.next();
				switch( k ){
				case 0:
					trainee.setTraineeId((int)cell.getNumericCellValue());
					break;
				case 1:
					trainee.setFirstName(cell.getStringCellValue());
					break;
				case 2:
					trainee.setLastName(cell.getStringCellValue());
					break;
				case 3:
					trainee.setMiddleName(cell.getStringCellValue());
					break;
				case 4:
					trainee.setCls(new com.smartworks.invtmgmt.core.domain.Class());
					break;
				
				}
			}
			traineeManager.add(trainee);
		}
		
	}

	private void processKitsSheet(HSSFSheet sheet) {
		Iterator<Row> rows = sheet.rowIterator();
		if(rows.hasNext()) {
			rows.next(); // skip the first row, as it is heading
		}
		while(rows.hasNext()){
			Row row = rows.next();
			Product product = new Product();
			Iterator<Cell> cellIterator = row.cellIterator();
			String[] items;
			int k = -1;
			while(cellIterator.hasNext()){
				k++;
				Cell cell = cellIterator.next();
				switch( k ){
					case 0 :
						product.setProductName(cell.getStringCellValue());
						break;
					case 1 :
						product.setProductDesc(cell.getStringCellValue()!=null?cell.getStringCellValue():"");
						break;
					case 2 :
						Location location = locationDao.findByLocationName("Equipment");
						product.setLocation(location);
						break;
					case 3:
						items = cell.getStringCellValue().split("\n");
						List<ProductItem> productItem = new ArrayList<ProductItem>();
						for(String item : items){
							Item prItem = itemDao.getItemByName(item);
							ProductItem prdItem = new ProductItem();
							prdItem.setItemId(prItem.getId());
							prdItem.setProduct(product);
							productItem.add(prdItem);
						}
						product.setItemList(productItem);
						break;
				}
			}
			cmnTransMgr.save(product);
			
			
		}
		
	}

	public void syncClass(MultipartFile file) throws Exception {
		try {
			HSSFWorkbook workBook = new HSSFWorkbook(file.getInputStream());
			HSSFSheet sheet = workBook.getSheetAt(0);
			processUserSheet(sheet);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			file.getInputStream().close();
		}	
		
	}

	private void processUserSheet(HSSFSheet sheet) {
		Iterator<Row> rows = sheet.rowIterator();
		if(rows.hasNext()) {
			rows.next(); // skip the first row, as it is heading
		}
		while(rows.hasNext()){
			Row row = rows.next();
			com.smartworks.invtmgmt.core.domain.Class cls = new com.smartworks.invtmgmt.core.domain.Class();
			Iterator<Cell> cellIterator = row.cellIterator();
			int k = -1;
			while(cellIterator.hasNext()){
				k++;
				Cell cell = cellIterator.next();
				switch( k ){
					case 1:
						cls.setClassName(cell.getStringCellValue());
						break;
					case 2:
						cls.setClassDesc(cell.getStringCellValue());
						break;
					case 3:
						String kitName = cell.getStringCellValue();
						Product product = productDao.findByName(kitName);
						break;
					case 4:
						String traineeName = cell.getStringCellValue();
						Trainee trainee = traineeDao.findByName(traineeName);
//						cls.setTraineeId(trainee.getTraineeId());
						break;
				}
			}
			classDao.saveOrUpdate(cls);
			
		
		}
	
	}
}
