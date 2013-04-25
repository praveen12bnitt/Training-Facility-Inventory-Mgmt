function addItem(contextPath,itemName,romNum, itemNumber) {
	$.ajax({
		  url: contextPath+'/inventory/itemHtmlEl.form',
		  data: {
			  itemName : itemName,
			  rowNum : romNum,
			  itemNumber : itemNumber
			  
		  },
		  success: function(html){
			  $('#tblTransactionForm').append(html);
			  return true;
		  }		  
		});
	
}

function addKitItems(contextPath,kitId,romNum) {
	$.ajax({
		  url: contextPath+'/kits/items-html.form',
		  data: {
			  productId : kitId,
			  rowNum : romNum			  
		  },
		  success: function(html){
			  $('#tblTransactionForm').append(html);
			  return true;
		  }		  
		});
	
}

function initializeKitOptions(contextPath,className, locationId) {
	$.ajax({
		  url: contextPath+'/kits/kits-class-loc-options.form',
		  data: {
			  className : className,
			  locationId : locationId			  
		  },
		  success: function(html){
			  $('#kitCombo option').remove();
			  $('#kitCombo').append(html);
			  return true;
		  }		  
		});
}

function iniKitLocOptions(contextPath,locationId) {
	$.ajax({
		  url: contextPath+'/kits/kits-loc-options.form',
		  data: {
			  locationId : locationId			  
		  },
		  success: function(html){
			  $('#kitCombo option').remove();
			  $('#kitCombo').append(html);
			  return true;
		  }		  
		});
}