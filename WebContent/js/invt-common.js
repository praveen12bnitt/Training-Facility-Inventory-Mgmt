function addItem(contextPath,itemName,romNum) {
	$.ajax({
		  url: contextPath+'/inventory/itemHtmlEl.form',
		  data: {
			  itemName : itemName,
			  rowNum : romNum			  
		  },
		  success: function(html){
			  $('#tblTransactionForm').append(html);
			  //Clear the search textbox
			  $('#itemName').val("");
			  return true;
		  }		  
		});
	
}