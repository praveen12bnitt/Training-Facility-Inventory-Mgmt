<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	   	url:'${pageContext.request.contextPath}/reports/allinvt.form',
		datatype: "json",
	   	colNames:['Id', 'Item Name', 'Item Specification', 'Available Qty' ,'Issued Qty', 'Unusable Qty', 'Location'],
	   	colModel:[
	   		{name:'itemId',index:'itemId', width:30, sorttype:"int",hidden:true},
	   		{name:'itemDesc',index:'itemDesc', width:180},
	   		{name:'itemAttributeDetails',index:'itemAttributeDetails', width:170},
	   		{name:'availableQty',index:'availableQty', width:70, align:"left",sorttype:"int"},
	   		{name:'issuedQty',index:'issuedQty', width:70, align:"left",sorttype:"int"},
	   		{name:'unusableQty',index:'unusableQty', width:70, align:"left",sorttype:"int"},
	   		{name:'location',index:'location', width:100, align:"left"}
	   	],
	   	rowNum:50,
	   	rowList:[50,100,150],
	   	pager: '#pager3',
	   	sortname: 'itemId',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: true,
	    grouping:true,
	   	groupingView : {
	   		groupField : ['location'],
	   		groupColumnShow : [false],
	   		groupText : ['<b>{0} - {1} Item(s)</b>']
	   	},
	    caption: "Item Inventory by Location",
	    height: 500,
	    width: 1050,
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

	$("#list3").jqGrid('navGrid', '#pager3',{view:true, del:false, add:false, edit:false, excel:true})
    .navButtonAdd('#pager3',{
                    caption:"Export to Excel", 
                    buttonicon:"ui-icon-save", 
                    onClickButton: function(){ 
                    	
                   		 $(location).attr('href','${pageContext.request.contextPath}/reports/exportInv.form');		 

                    }, 
                    position:"last"
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
	
		
		
		
		<div id="content" class="ui-widget ui-widget-content" style="padding: 10px;">	
			<table id="list3" class="trans-details"></table>
			<div id="pager3"></div>
		</div>
		
			
		</div>	
	
	<br>
</body>
</html>