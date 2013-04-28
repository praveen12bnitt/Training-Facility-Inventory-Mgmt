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

loadOpenTrans = function(traineeStaffId, transactionType, locationId) {
	jQuery("#list3").jqGrid('clearGridData','clearfooter');
	
	jQuery("#list3").jqGrid({
	   	url:'${pageContext.request.contextPath}/inventory/opentransactions.form?traineeStaffId='+traineeStaffId+'&transactionTypeEnum='+transactionType+'&locationId='+locationId,
		datatype: "json",
	   	colNames:['Transaction Id', 'Location Name', 'Created Time'],
	   	colModel:[
	   		{name:'transactionId',index:'transactionId', align:'center', width:200},
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
	    width: 500,
	    onSelectRow: function(rowId){	    	
	    	var rowData = $("#list3").jqGrid('getGridParam','selrow');
	    	var transactionId = $("#list3").jqGrid('getCell',rowId,0);
	    	transactionId = transactionId.substring(transactionId.indexOf('>')+1, transactionId.lastIndexOf('<'));
	    	var targetForm = '${targetForm}';
	    	if(rowData){
	    		$(location).attr('href','${pageContext.request.contextPath}/inventory/'+targetForm+'?transactionId='+transactionId);	
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

	$("#list3").setGridParam({url:'${pageContext.request.contextPath}/inventory/opentransactions.form?traineeStaffId='+traineeStaffId+'&transactionTypeEnum='+transactionType+'&locationId='+locationId}).trigger('reloadGrid');
    
  };
  


  $(document).ready(function() {
	  
	  
	  <c:choose>
	  	<c:when test='${transactionTypeEnum.staffTransaction}'>
		  	jQuery("#list2").jqGrid({
	    	   	url:'${pageContext.request.contextPath}/common/list-active-staff.form',
	    		datatype: "json",
	    	   	colNames:['Id','First Name', 'Last Name', 'Middle Name', 'Division', 'Extension'],
	    	   	colModel:[
	    			{name:'staffId',index:'staffId', align:'center', width:200},
	    	   		{name:'firstName',index:'firstName', align:'center', width:200},
	    	   		{name:'lastName',index:'lastName', align:'center', width:200},
	    	   		{name:'middleName',index:'middleName', align:'center', width:200},
	    	   		{name:'division',index:'classNumber', align:'center'},
	    	   		{name:'extension',index:'extension', align:'center',width:90},
	    	   	],
	    	   	rowNum:10,
	    	   	rowList:[10,20,30],
	    	   	pager: '#pager2',
	    	   	sortname: 'firstName',
	    	    viewrecords: true,
	    	    sortorder: "desc",
	    	    loadonce: true,
	    	    caption: "Staff List",
	    	    ignoreCase:true,
	    	    ignoreCase:true,
	    	    height: 125,
	    	    width: 750,
	    	    onSelectRow: function(rowId){
			    	
			    	var traineeStaffId = jQuery("#list2").jqGrid('getCell',rowId,0);
			    	var transType='${transactionTypeEnum}';
			    	var locationId=${locationId};
			    	loadOpenTrans(traineeStaffId,transType,locationId);
			       	
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
	  	</c:when>
	    <c:otherwise>
	    jQuery("#list2").jqGrid({
		   	url:'${pageContext.request.contextPath}/common/list-active-trainees.form',
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
		    ignoreCase:true,
		    caption: "Trainee List",
		    height: 200,
		    width: 750,
		    onSelectRow: function(rowId){
		    	
		    	var traineeStaffId = jQuery("#list2").jqGrid('getCell',rowId,0);
		    	var transType='${transactionTypeEnum}';
		    	var locationId=${locationId};
		    	loadOpenTrans(traineeStaffId,transType,locationId);
		       	
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
	    </c:otherwise>
		</c:choose>
	  
	$("#list2").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false});
	  
	
	$('.form-button').hover(
			function(){ 
				$(this).addClass("ui-state-hover"); 
			},
			function(){ 
				$(this).removeClass("ui-state-hover"); 
			}
		);	
});

</script>
</head>
<body class="body-class" >	
	<div id="main-content" class="ui-widget main-content" style="background: white;">
	<%@ include file="/WEB-INF/ui/header.jsp" %>
	<div id="top-navigation" class="top-navigation">
		<%@ include file="/WEB-INF/ui/menu.jsp" %>
	</div>
		<br/> <a id="goback" href="javascript:history.go(-1)" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; " >Go back</a> <br/>
	<br />
	<div style="clear: both;"></div>
	<div id="heading" class="ui-widget-header">Transaction Details</div>
		<div id="header-contents" class="ui-widget-content" align="left" style="padding: 10px;">
		
		<table id="transDetails" class="ui-widget item-table trans-details">				
			<tbody class="ui-widget-content trans-details" >
				<tr>
					<td>Transaction Description</td><td>${transactionDetail}</td>
				</tr>				
			</tbody>
		</table>					
		</div>
		
		<br />
		
		
		<div id="content" class="ui-widget-content" style="padding: 10px;">	
			<table>
				<tr>
					<td>
						Select trainee/staff from the list below :
						<table id="list2" class="trans-details"></table>
						<div id="pager2"></div>
					</td>
					<td style="padding-left: 10px;">
						Select a transaction from list below :
						<table id="list3" class="trans-details"></table>
						<div id="pager3"></div>
					</td>
				</tr>
			</table>
			
		</div>				
	</div>
</body>
</html>