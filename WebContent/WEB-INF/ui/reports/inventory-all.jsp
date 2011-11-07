<%@ include file="/WEB-INF/ui/commoninclude.jsp"%>
<%@ page language="java"
	import="java.util.*,com.smartworks.invtmgmt.core.domain.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.smartworks.platform.AppContextUtil"%>
<%@page import="java.util.Collections"%>

<html>
<head>
<title><fmt:message key="title" /></title>
<style>
.error {
	color: red;
}
</style>
<link rel="stylesheet" type="text/css" 	href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" 	href="${pageContext.request.contextPath}/css/memu-0.1.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/jqgrid/ui.jqgrid.css" />
<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.6.2.min.js" 	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/grid.locale-en.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.json-2.3.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.memu-0.1.min.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function($) {
	$('.form-button').hover(
			function(){ 
				$(this).addClass("ui-state-hover"); 
			},
			function(){ 
				$(this).removeClass("ui-state-hover"); 
			}
		);
	
	$('.js-enabled').memu({ 
		icon: {
			inset: true,
			margin: {
				top: 4,
				right: 10
			}
		},
		width: 150,
		rootWidth: 75,
		height: 25
	});
	
	
	
	});

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
	    width: 850,
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
		
		<table id="list3" class="ui-widget item-table"></table>
<div id="pager3"></div>
		

		<br />


</body>
</html>
