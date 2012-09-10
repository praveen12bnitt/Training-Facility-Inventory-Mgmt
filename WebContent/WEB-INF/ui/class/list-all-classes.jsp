<%@ include file="/WEB-INF/ui/commoninclude.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Class</title>
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

	jQuery("#list2").jqGrid({
	   	url:'${pageContext.request.contextPath}/class/list-all-classes.form',
		datatype: "json",
	   	colNames:['Class Name', 'Class Description'],
	   	colModel:[
	   		{name:'className',index:'className', align:'center', width:200},
	   		{name:'classDesc',index:'classDesc', align:'center', width:200}
	   	],
	   	rowNum:10,
	   	rowList:[10,20,30],
	   	pager: '#pager2',
	   	sortname: 'className',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: true,
	    caption: "Class List",
	    ignoreCase:true,
	    height: 125,
	    width: 750,
	    onSelectRow: function(rowId){	    	
	    	var rowData = jQuery("#list2").jqGrid('getGridParam','selrow');
	    	var className = jQuery("#list2").jqGrid('getCell',rowId,0);
	    	if(rowData){
				$(location).attr('href','${pageContext.request.contextPath}/class/edit-class.form?className='+className);
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


function print() {
	jQuery('#itemDetails >tbody >tr').each(
			function(index, tr) {
				$(this).find('td').each(function(){
					var itemName = "itemList["+index+"].itemId";
			        $(this).find('#itemId').attr('name',itemName);
			    })
			}
	);
}

function processResponse(data) {
	$.each(data, function(key, val) {
		alert("key::" + key +" value ::" + value);
		var innerHtml = $("#rowtemplate").html();
		
		innerHtml = innerHtml.replace("hdnVal",key);
		innerHtml = innerHtml.replace("hdnName",val);
		
		$("#itemDetails > tbody").append("<tr><td>"+innerHtml+"</td></tr>");
		
		$('#itemDetails >tbody >tr >td img.delete').click(function(){
			  $(this).parent().parent().remove();
		});
	});
}

function showTransactionStatus() {
	$('#transactionResponse').show();
	$("#list2").trigger("reloadGrid");
	$('#transactionResponse').fadeOut(4000);
}

</script>
</head>
	<body class="body-class" >
		
			<div id="main-content" class="ui-widget main-content" style="background: white;">
				<%@ include file="/WEB-INF/ui/header.jsp" %>
				<div id="top-navigation" class="top-navigation">
					<%@ include file="/WEB-INF/ui/menu.jsp" %>
				</div>
				
				<a id="goback" href="javascript:history.go(-1)" onMouseOver="self.status=document.referrer;return true">Go back</a>
				
				<br />
				<div style="clear: both;"></div>	
			
				<%@ include file="/WEB-INF/ui/transaction-result.jsp" %>
			
				<div id="heading12" class="ui-widget-header">Class List</div>
				
				<div id="content" class="ui-widget-content" style="padding: 10px;">	
				
				<div id="create-trainee-div">
					<a id="create-trainee" href="<c:url value='/class/add-class.form' />" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em;">Create New Class</a>
				</div>
				
				<br/>    
				<table id="list2" class="trans-details"></table>
					<div id="pager3"></div>
				</div>
				
		</div>
	</body>
</html>