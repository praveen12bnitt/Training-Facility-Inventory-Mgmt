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
	   	url:'${pageContext.request.contextPath}/common/list-all-classes.form',
		datatype: "json",
	   	colNames:['Class Name', 'Class Description', 'Trainer'],
	   	colModel:[
	   		{name:'className',index:'className', align:'center', width:200},
	   		{name:'classDesc',index:'classDesc', align:'center', width:200},
	   		{name:'traineeId',index:'traineeId', align:'center', width:200}
	   	],
	   	rowNum:10,
	   	rowList:[10,20,30],
	   	pager: '#pager2',
	   	sortname: 'firstName',
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
	    		//alert("test");
				jQuery("#list2").jqGrid('GridToForm',rowData,"#command");
				$(location).attr('href','${pageContext.request.contextPath}/common/edit-class.form?className='+className);
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

/*
function processResponse(data) {
	alert("Data :: " + data);
	$.each(data, function(key, val) {
		var innerHtml = $("#rowtemplate").html();
		
		innerHtml = innerHtml.replace("hdnVal",key);
		innerHtml = innerHtml.replace("hdnName",val);
		
		$("#itemDetails > tbody").append("<tr><td>"+innerHtml+"</td></tr>");
		
		$('#itemDetails >tbody >tr >td img.delete').click(function(){
			  $(this).parent().parent().remove();
		});
	});
}


 $(document).ready(function() {
	
	  $('.form-button').hover(
				function(){ 
					$(this).addClass("ui-state-hover"); 
				},
				function(){ 
					$(this).removeClass("ui-state-hover"); 
				}
			);
	  
	  var mygrid =  jQuery("#trainee-list").jqGrid({
		  	url:'${pageContext.request.contextPath}/common/list-all-classes.form',
			datatype: "json",
		   	colNames:['Class Name', 'Class Description', 'Trainer'],
		   	colModel:[
		   		{name:'className',index:'className', align:'center', width:200},
		   		{name:'classDesc',index:'classDesc', align:'center', width:200},
		   		{name:'traineeId',index:'traineeId', align:'center', width:200}
		   	],
		   	rowNum:20,
		   	rowList:[20,40,60],
		   	pager: '#pager3',
		   	sortname: 'className',
		   	gridview : true,
		    viewrecords: true,
		    sortorder: "desc",
		    caption: "Class List",
		    height: 'auto',
		    width: 'auto',
		    loadonce: true,
		    onSelectRow: function(rowId){	    	
		    	var rowData = $("#trainee-list").jqGrid('getGridParam','selrow');
		    	var traineeId = $("#trainee-list").jqGrid('getCell',rowId,0);
		    	if(rowData){
		    		$(location).attr('href','${pageContext.request.contextPath}/common/edit-class.form?classId='+classId);	
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
	  
	  jQuery("#trainee-list").jqGrid('navGrid','#pager3',{edit:false,add:false,del:false,search:false,refresh:false});
	  jQuery("#trainee-list").jqGrid('navButtonAdd',"#pager3",{caption:"Toggle",title:"Toggle Search Toolbar", buttonicon :'ui-icon-pin-s',
	  	onClickButton:function(){
	  		mygrid[0].toggleToolbar()
	  	} 
	  });
	  jQuery("#trainee-list").jqGrid('navButtonAdd',"#pager3",{caption:"Clear",title:"Clear Search",buttonicon :'ui-icon-refresh',
	  	onClickButton:function(){
	  		mygrid[0].clearToolbar()
	  	} 
	  });
	  jQuery("#trainee-list").jqGrid('filterToolbar',{searchOnEnter : false});
	 
	 
	  
	
});*/
</script>
</head>
	<body class="body-class" >
		<form action="${pageContext.request.contextPath}/uploadfile/uploadClass.form" method="post" enctype="multipart/form-data">
			<div id="main-content" class="ui-widget main-content" style="background: white;">
				<%@ include file="/WEB-INF/ui/header.jsp" %>
				<div id="top-navigation" class="top-navigation">
					<%@ include file="/WEB-INF/ui/menu.jsp" %>
				</div>
				<br />
				<div style="clear: both;"></div>	
			
				<%@ include file="/WEB-INF/ui/transaction-result.jsp" %>
			
				<div id="heading12" class="ui-widget-header">Transaction Details</div>
				
				<div id="content" class="ui-widget-content" style="padding: 10px;">	
				
				<div id="create-trainee-div">
					<a id="create-trainee" href="<c:url value='/common/add-class.form' />" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em;">Create New Class</a>
					<!--<c:choose>
		    			<c:when test='${issueSkuForm.transactionType.staffTransaction}'>
		    				<a id="create-staff" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em;">Create New Staff</a>
		    			</c:when>
				    	 <c:otherwise>
				    	 	<a id="create-trainee" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em;">Create New Trainee</a>
				    	 </c:otherwise>
		   			 </c:choose>-->
				</div>
				
				<br/>    
				<table id="list2" class="trans-details"></table>
					<div id="pager3"></div>
				</div>
				
					<div id="header-contents" class="ui-widget-content" align="left" style="padding: 10px;">
				
				
					<!--<input type="file" name="file" class="fileUpload" >
					<input id="px-submit" type="submit" value="Upload"/>-->
					
				
					<br />
					<table id="transDetails" class="ui-widget item-table trans-details">				
						<tbody class="ui-widget-content trans-details" >
							<tr>
								<td>Class Name</td><td><input type="text" name="className" value="" /></td>
							</tr>
							<tr>
								<td>Class Description</td><td><input type="text" name="classDesc" value="" /></td>
							</tr>
							<tr>
								<td>Trainer Name</td><td><input type="text" name="traineeId" value="" /></td>
							</tr>
							<!--<c:choose>
								<c:when test='${issueSkuForm.transactionType.staffTransaction}'>
			    				<tr>
									<td>Division</td><td><input type="text" name="division" value="" readOnly="true" /></td>
								</tr>
								<tr>
									<td>Extension</td><td><input type="text" name="extension" value="" readOnly="true" /></td>
								</tr>
			    				</c:when>
			    				<c:otherwise>
			    				<tr>
									<td>Class</td><td><input type="text" name="classNumber" value="" readOnly="true" /></td>
								</tr>
			    				</c:otherwise>
							</c:choose>
							-->
						</tbody>
					</table>					
				</div>
			</div>
		</form>
		<div id="rowtemplate" align="left" class="actions" style="visibility:hidden" >
		
			<img class="delete" src="<c:url value='/images/delete.jpg' />" />&nbsp;&nbsp;
		   	<input type="hidden" id="itemId"  value="hdnVal">hdnName
				
		</div>
	</body>
</html>