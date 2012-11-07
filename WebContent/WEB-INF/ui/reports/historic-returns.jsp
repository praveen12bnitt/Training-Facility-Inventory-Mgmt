<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Historic Returns</title>
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
	
	jQuery("#list3").jqGrid({
	   	url:'${pageContext.request.contextPath}/reports/allhistreturns.form',
		datatype: "json", 
		colNames:['Return Id', 'Date', 'Return Qty', 'Lost Qty', 'Addnsn', 'SSN', 'Last Name', 'First Name', 'Site Name', 'Lost Reason', 'Materielid', 'Site Id', 'User Id', 'Unit Name', 'Repair Qty'],
	   	colModel:[
	   		{name:'returnId',index:'returnId',sorttype:'int'},
	   		{name:'dateAsStr',index:'dateAsStr',sorttype:"date", formatter: 'date', formatoptions: { srcformat: 'm/d/Y', newformat: 'm/d/Y' },searchoptions : { sopt: ['eq'] }},
	   		{name:'returnQty',index:'returnQty',sorttype:'int'},
	   		{name:'lostQty',index:'lostQty',sorttype:'int'},
	   		{name:'addNsn',index:'addNsn',sorttype:'int'},
	   		{name:'ssn',index:'ssn',sorttype:'int'},
	   		{name:'lastName',index:'lastName'},
	   		{name:'firstName',index:'firstName'},
	   		{name:'siteName',index:'siteName'},
	   		{name:'lostReason',index:'lostReason'},
	   		{name:'materielid',index:'materielid',sorttype:'int'},
	   		{name:'site',index:'site',sorttype:'int'},
	   		{name:'userId',index:'userId'},
	   		{name:'unitname',index:'unitname'},
	   		{name:'repairQty',index:'repairQty',sorttype:'int'}
	   	],
	   	rowNum:50,
	   	rowList:[50,100,200],
	   	pager: '#pager3',
	   	sortname: 'returnId',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: true,
	    caption: "Historic Returns",
	    width: 1250,
	    height: 1200,
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
	
	$("#list3").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false});
	
	
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
	
		
		
		
		<div id="content" class="ui-widget ui-widget-content" style="padding: 10px;">	
			<a href="${pageContext.request.contextPath}/data/historic-returns.xlsx"> Download Historic Returns</a>
 			<br/>
			<table id="list3" class="trans-details"></table>
			<div id="pager3"></div>
		</div>
		
			
		</div>	
	
	<br>
</body>
</html>