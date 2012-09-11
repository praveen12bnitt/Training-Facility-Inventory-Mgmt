package com.smartworks.invtmgmt.core.manager;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.smartworks.invtmgmt.business.ItemSku;
import com.smartworks.invtmgmt.converter.ItemSkuConverter;
import com.smartworks.invtmgmt.core.dao.ItemAttributeDao;
import com.smartworks.invtmgmt.core.dao.ItemAttributeValueDao;
import com.smartworks.invtmgmt.core.dao.ItemDao;
import com.smartworks.invtmgmt.core.dao.LocationDao;
import com.smartworks.invtmgmt.core.dao.impl.ProductDaoImpl;
import com.smartworks.invtmgmt.core.domain.Item;
import com.smartworks.invtmgmt.core.domain.Location;
import com.smartworks.invtmgmt.core.domain.Product;
import com.smartworks.invtmgmt.core.domain.ProductDetails;

@Transactional
@Service
public class ProductMgrImpl implements ProductMgr {

	@Autowired
	private ProductDaoImpl productDao;
	
	@Autowired
	private LocationDao locationDao;
	
	@Autowired
	private ItemSkuConverter itemSkuConverter;;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private ItemAttributeDao itemAttriuAttributeDao;
	
	@Autowired
	private ItemAttributeValueDao attributeValueDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveOrUpdate(Product product, List<ItemSku> itemSkus, MultipartFile kitFile) { 	
		if(product.getProductId() != null)
			productDao.removeAllProductDetails(product.getProductId());
		
		for(ItemSku sku : itemSkus) {  			
			String itemSkuCode = itemSkuConverter.getItemSkuCode(sku); 			
			Integer quantity = sku.getQuantity();  			
			if(quantity == null || quantity < 1 ) continue; 					
			ProductDetails pd = new ProductDetails();
			pd.setQuantity(quantity);
			pd.setSkuCode(itemSkuCode); 
			product.addProductDetails(pd); 			
		}
		if(kitFile != null && kitFile.getSize() > 0){
			try {
				processKitDetails(kitFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		productDao.saveOrUpdate(product);
		
	}
	
	
	private void processKitDetails(MultipartFile kitFile) throws IOException {
		
		HSSFWorkbook workBook = new HSSFWorkbook(kitFile.getInputStream());
		HSSFSheet sheet = workBook.getSheetAt(0); 		
		Iterator<Row> rows = sheet.rowIterator();
		boolean firstRow = true;
		while(rows.hasNext()) {
			if(firstRow)  {
				rows.next();
				firstRow = false;
				continue; 				
			}
			Row row = rows.next();
			Product product = new Product();
			
			String kitName = row.getCell(0).getStringCellValue();
			if(StringUtils.isNotEmpty(kitName)) { 			
				product.setProductName(kitName);
				product.setProductDesc(row.getCell(1).getStringCellValue());
				Location location = locationDao.findByLocationName(row.getCell(2).getStringCellValue());
				product.setLocation(location);
				String[] prodDetailValues = row.getCell(3).getStringCellValue().split("\n");
				Set<ProductDetails> prodDetails = new HashSet<ProductDetails>();
				for(String productDetails : prodDetailValues){
					String prodItemName = productDetails.substring(0,productDetails.indexOf("["));
					Item itemByName = itemDao.getItemByName(prodItemName);
					StringBuffer skuCode = new StringBuffer();
					skuCode.append(itemByName.getId());
					
					String[] prodAttrs = productDetails.substring(productDetails.indexOf("[")+1, productDetails.indexOf("]")).split(",");
					for(String productAttr : prodAttrs){
						skuCode.append("-").append(itemAttriuAttributeDao.findByAttributeName(productAttr.split(":")[0]).getAttibuteId());
						skuCode.append(":").append(attributeValueDao.findByAttributeValue(productAttr.split(":")[1]).getAttributeValueId());
					}
					
					ProductDetails productDetail = new ProductDetails();
					productDetail.setProduct(product);
					productDetail.setSkuCode(skuCode.toString());
					productDetail.setQuantity(Integer.parseInt(productDetails.split("=")[1]));
					prodDetails.add(productDetail);
				}
				product.setProductDetails(prodDetails);
				productDao.saveOrUpdate(product);
			}
		}
		
	}


	public List<Product> getAllProducts() {
		return productDao.loadAll();
	}


	@Override
	public void saveOrUpdate(MultipartFile kitFile) {
		try {
			processKitDetails(kitFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
