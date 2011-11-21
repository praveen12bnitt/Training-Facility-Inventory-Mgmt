<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Laundry List</title>
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/styles.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/jqgrid/ui.jqgrid.css" />' />
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/memu-0.1.css" />'/>
<script src='<c:url value="/js/jquery/jquery-1.6.2.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery/jquery-ui-1.8.16.custom.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/grid.locale-en.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/jquery.jqGrid.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.json-2.3.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.memu-0.1.min.js" />' type="text/javascript"></script>

<script type="text/javascript">

  $(document).ready(function() {
	
	  $('.form-button').hover(
				function(){ 
					$(this).addClass("ui-state-hover"); 
				},
				function(){ 
					$(this).removeClass("ui-state-hover"); 
				}
			);
	  
	 
	  jQuery("#open-laundry").jqGrid({
		   	url:'${pageContext.request.contextPath}/laundry/list-open-laundry.form',
			datatype: "json",
		   	colNames:['id', 'Wash Mc', 'code', 'Created Time' ,'Laundry Details'],
		   	colModel:[
		   		{name:'loadId',index:'loadId', width:10, sorttype:"int"},
		   		{name:'washingMachineNo',index:'washingMachineNo', width:30},
		   		{name:'code',index:'code', width:10},
		   		{name:'createDttm',index:'createDttm', width:30, align:"left"},
		   		{name:'laundryDetails',index:'laundryDetails', width:100, align:"left"}
		   	],
		   	rowNum:50,
		   	rowList:[50,100,150],
		   	pager: '#pager3',
		   	sortname: 'id',
		    viewrecords: true,
		    sortorder: "desc",
		    loadonce: true,
		    caption: "Laundry Details",
		    height: 500,
		    width: 1050,
		    onSelectRow: function(rowId){	    	
		    	var rowData = jQuery("#open-laundry").jqGrid('getGridParam','selrow');
		    	var laundryId = jQuery("#open-laundry").jqGrid('getCell',rowId,0);
		    	if(rowData){
		    		$(location).attr('href','${pageContext.request.contextPath}/laundry/edit-load.form?loadId='+laundryId);	
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
		
		<div id="heading" class="ui-widget-header">Washing</div>
		<div id="content" class="ui-widget-content" style="padding: 10px;">	
			<div style="padding: 10px;">
			<a id="laundry-add-btn" href="${pageContext.request.contextPath}/laundry/create-load.form" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Add Load</a>
			</div>
			<table id="open-laundry" class="trans-details"></table>
			<div id="pager3"></div>
		</div>
		<br/>
			
</div>

</body>
</html>