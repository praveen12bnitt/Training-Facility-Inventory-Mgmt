<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Open User Transation</title>
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

loadOpenTrans = function(traineeId, transactionType, locationId) {
	jQuery("#list3").jqGrid('clearGridData','clearfooter');
	
	jQuery("#list3").jqGrid({
	   	url:'${pageContext.request.contextPath}/inventory/opentransactions.form?userId='+traineeId+'&transactionTypeEnum='+transactionType+'&locationId='+locationId,
		datatype: "json",
	   	colNames:['Transaction Id', 'Location Name', 'Issued Time'],
	   	colModel:[
	   		{name:'transactionDetails',index:'transactionDetails', align:'center', width:200},
	   		{name:'locationName',index:'locationName', align:'center', width:200},
	   		{name:'createdDttm',index:'createdDttm', align:'center', width:200}
	   	],
	   	rowNum:10,
	   	rowList:[10,20,30],
	   	pager: '#pager3',
	   	sortname: 'itemId',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: false,
	    caption: "Open User Transactions",
	    height: 200,
	    width: 750,
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
	$("#list3").setGridParam({url:'${pageContext.request.contextPath}/inventory/opentransactions.form?userId='+traineeId+'&transactionTypeEnum='+transactionType+'&locationId='+locationId}).trigger('reloadGrid');
    
  }

  $(document).ready(function() {
	jQuery("#list2").jqGrid({
	   	url:'${pageContext.request.contextPath}/common/listtrainees.form',
		datatype: "json",
	   	colNames:['Trainee Id','First Name', 'Last Name', 'Middle Name', 'Class'],
	   	colModel:[
			{name:'traineeId',index:'traineeId', align:'center', width:200},
	   		{name:'firstName',index:'firstName', align:'center', width:200},
	   		{name:'lastName',index:'lastName', align:'center', width:200},
	   		{name:'middleName',index:'middleName', align:'center', width:200},
	   		{name:'classNumber',index:'classNumber', align:'center', width:200}
	   	],
	   	rowNum:10,
	   	rowList:[10,20,30],
	   	pager: '#pager2',
	   	sortname: 'firstName',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: true,
	    caption: "Trainee List",
	    height: 200,
	    width: 750,
	    onSelectRow: function(rowId){
	    	
	    	var traineeId = jQuery("#list2").jqGrid('getCell',rowId,0);
	    	var transType='${transactionTypeEnum}';
	    	var locationId=${locationId};
	    	loadOpenTrans(traineeId,transType,locationId);
	       	
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
	<div id="heading" class="ui-widget-header">Transaction Details</div>
		<div id="header-contents" class="ui-widget-content" align="left" style="padding: 10px;">
		
		<table id="transDetails" class="ui-widget item-table trans-details">				
			<tbody class="ui-widget-content trans-details" >
				<tr>
					<td>Transaction Description</td><td>Return from Student</td>
				</tr>				
			</tbody>
		</table>					
		</div>
		
		<br />
		
		
		<div id="content" class="ui-widget-content" style="padding: 10px;">	
		<table id="list2" class="trans-details"></table>
			<div id="pager2"></div>
		</div>
		
		
		<div id="content" class="ui-widget-content" style="padding: 10px;">	
		<table id="list3" class="trans-details"></table>
			<div id="pager3"></div>
		</div>
		<br/>
		
			
		</div>

</body>
</html>