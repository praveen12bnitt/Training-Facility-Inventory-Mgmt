<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adjust Inventory</title>
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/styles.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/jqgrid/ui.jqgrid.css" />' />
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/dropdown.css" />'/>
<script src='<c:url value="/js/jquery/jquery-1.6.2.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery/jquery-ui-1.8.16.custom.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/grid.locale-en.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/jquery.jqGrid.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.json-2.3.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/jquery.dropdown.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/hoverIntent.js" />' type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function($) {
	$('#tran-result-error-div').hide();
	$('#tran-result-success-div').hide();
	jQuery("#list3").jqGrid({
	   	url:'${pageContext.request.contextPath}/Inventory/getAllInventory.form',
		datatype: "json",
	  	colNames:['Id', 'Item Name', 'Item Specification',  'Location','Available Qty','SkuCode','locationId'],
	   	colModel:[
	   		{name:'itemId',index:'itemId', width:30, sorttype:"int",hidden:true},
	   		{name:'itemDesc',index:'itemDesc', width:180},
	   		{name:'itemAttributeDetails',index:'itemAttributeDetails', width:170},
	   		{name:'location',index:'location', width:100, align:"left"},
	   		{name:'availableQty',index:'availableQty', width:70, align:"left",sorttype:"int"},
	   		{name:'skuCode',index:'skuCode', width:70, align:"left",sorttype:"int",hidden:true},
	   		{name:'locationId',index:'skuCode', width:70, align:"left",sorttype:"int",hidden:true}
	    	],
	   	rowNum:50,
	   	rowList:[50,100,150],
	   	pager: '#pager3',
	   	sortname: 'itemId',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: true,
	    caption: "Available Inventory Details",
	    height: 'auto',
	    width: 1050,
	    onSelectRow: function(rowId){	    	
	    	var rowData = jQuery("#list3").jqGrid('getGridParam','selrow');
	    	//var locationId = jQuery("#list3").jqGrid('getCell',rowId,0);
	    	if(rowData){
				jQuery("#list3").jqGrid('GridToForm',rowData,"#inventoryDetailsForm");
			}	    	
	    	  
	    },
	    jsonReader : {
	          root: "rows",
	          page: "page",
	          total: "total",
	          records: "records",
	          repeatitems: false,
	          cell: "cell",
	          id: "id"
	      }
	});
	
	 $('#Edit').click(function() {
		  $('#AdjustInventory.form').submit();
	  });	
	});

</script>
</head>
<body class="body-class" >	
<form:form method="post" commandName="inventoryDetailsForm">
	<form:hidden path="locationId"/>
	<form:hidden path="skuCode"/>
	<div id="main-content" class="ui-widget main-content" style="background: white;">
	<%@ include file="/WEB-INF/ui/header.jsp" %>
	<div id="top-navigation" class="top-navigation">
	<%@ include file="/WEB-INF/ui/menu.jsp" %>
	</div>
		<br/> <a id="goback" href="javascript:history.go(-1)" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; " >Go back</a> <br/>
	<br />
	<div style="clear: both;"></div>	
	<%@ include file="/WEB-INF/ui/transaction-result.jsp" %>
		<div id="content" class="ui-widget ui-widget-content" style="padding: 10px;">	
			<table id="list3" class="trans-details"></table>
			<br/>
			<br/>
			<table id="transDetails" class="ui-widget item-table trans-details">				
			<tbody class="ui-widget-content trans-details" >
				<tr>
					<td>Inventory Details</td><td></td>
				</tr>
				<tr>
					<td>Location</td><td><form:input type="text" path="location" name="location" value="" readOnly="true"/></td>
				</tr>
				<tr>
					<td>Available Qty</td> <td><form:input path="availableQty" type="text" name="availableQty" value=""/></td>  
				</tr>
				
			</tbody>
		</table>		
			<div id="actions" align="center" class="actions">
				<button id="Edit" type="submit" class="ui-state-default ui-corner-all form-button">Save Quantity</button>
			</div>
			<div id="pager3"></div>
		</div>
	</div>	
	
	<br>
	</form:form>
</body>

</html>