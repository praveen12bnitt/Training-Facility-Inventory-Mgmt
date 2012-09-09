<%@ include file="/WEB-INF/ui/commoninclude.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kits</title>
<style>
.error {
	color: red;
}
</style>
<link rel="stylesheet" type="text/css" 	href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/dropdown.css" />'/>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/jqgrid/ui.jqgrid.css" />
<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.6.2.min.js" 	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/grid.locale-en.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.json-2.3.js" type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/jquery.dropdown.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/hoverIntent.js" />' type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/invt-common.js" type="text/javascript"></script>
<script>

$(document).ready(function($) {
	
	$('#tran-result-error-div').hide();
	$('#tran-result-success-div').hide();
	$('#transactionDiv').hide();

	jQuery("#kit-list").jqGrid({
		url:'${pageContext.request.contextPath}/kits/list-all-kits.form',
		datatype: "json",
	   	colNames:['Kit Id','Kit Name', 'Kit Desc', 'Location'],
	   	colModel:[ 			
			{name:'productId',index:'productId', align:'center', width:200},
	   		{name:'productName',index:'productName', align:'center', width:200},
	   		{name:'productDesc',index:'productDesc', align:'center', width:200},
	   		{name:'location.locationName',index:'location.locationName', align:'center', width:200}	   		 		
	   	],
	   	rowNum:10,
	   	rowList:[10,20,30],
	   	pager: '#kit-list-pager',
	   	sortname: 'productName',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: false,
	    caption: "Kit List",
	    height: 400,
	    width: 750,
	    onSelectRow: function(rowId){	    	
	    	var rowData = jQuery("#kit-list").jqGrid('getGridParam','selrow');
	    	var kitId = jQuery("#kit-list").jqGrid('getCell',rowId,0);
	    	if(rowData){
				$(location).attr('href','${pageContext.request.contextPath}/kits/edit-kit.form?kitId='+kitId);
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
});


</script>
</head>
	<body class="body-class" >
		
			<div id="main-content" class="ui-widget main-content" style="background: white;">
				<%@ include file="/WEB-INF/ui/header.jsp" %>
				<div id="top-navigation" class="top-navigation">
					<%@ include file="/WEB-INF/ui/menu.jsp" %>
				</div>
				<br />
				<div style="clear: both;"></div>	
			
				<%@ include file="/WEB-INF/ui/transaction-result.jsp" %>
			
				<div id="heading12" class="ui-widget-header">Class List</div>
				
				<div id="content" class="ui-widget-content" style="padding: 10px;">	
				
				<div id="create-trainee-div">
					<a id="add-kits" href="<c:url value='/kits/add-kit.form' />" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em;">Create New Kit</a>
				</div>
				
				<br/>    
				<table id="kit-list" class="trans-details"></table>
					<div id="kit-list-pager"></div>
				</div>
				
			</div>
	</body>
</html>