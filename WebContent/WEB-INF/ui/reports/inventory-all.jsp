<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/jqgrid/ui.jqgrid.css" />' />
<script src='<c:url value="/js/jquery/jquery-1.6.2.min.js" />' type="text/javascript" />></script>
<script src='<c:url value="/js/jquery/jquery-ui-1.8.16.custom.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/grid.locale-en.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/jquery.jqGrid.min.js" />' type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css" />' />

<script type="text/javascript">
$(document).ready(function() {
	jQuery("#list3").jqGrid({
	   	url:'${pageContext.request.contextPath}/reports/allinvt.form',
		datatype: "json",
	   	colNames:['Id', 'Item Name', 'Item Specification', 'Quantity' , 'Location'],
	   	colModel:[
	   		{name:'itemId',index:'itemId', width:60, sorttype:"int"},
	   		{name:'itemDesc',index:'itemDesc', width:200},
	   		{name:'itemAttributeDetails',index:'itemAttributeDetails', width:200},
	   		{name:'quantity',index:'quantity', width:80, align:"right",sorttype:"int"},
	   		{name:'location',index:'location', width:150, align:"right"}
	   	],
	   	rowNum:50,
	   	rowList:[50,100,150],
	   	pager: '#pager3',
	   	sortname: 'itemId',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: true,
	    caption: "Item Inventory Details",
	    height: 500,
	    width: 1000,
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
<body>
<div id="portal-header">

<a id="portal-logo" accesskey="1" href="http://www.fletc.gov">
    <img src="<c:url value='/images/logo.jpg' />" alt="" title="logo.gif" height="78" width="345"></a>
</div>

<br/>
<br/>
<div align="center" style="width: 100%" >
<table id="list3"></table>
<div id="pager3"></div>
</div>


</body>
</html>