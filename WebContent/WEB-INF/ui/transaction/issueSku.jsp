<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Issue</title>
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/styles.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/jqgrid/ui.jqgrid.css" />' />
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/dropdown.css" />'/>
<link rel="stylesheet" type="text/css" 	href='<c:url value="/choosen/chosen.css" />'/>

<script src='<c:url value="/js/jquery/jquery-1.6.2.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery/jquery-ui-1.8.16.custom.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/grid.locale-en.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/jquery.jqGrid.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.json-2.3.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/jquery.dropdown.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/hoverIntent.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/invt-common.js" />' type="text/javascript"></script>
<script src='<c:url value="/choosen/chosen.jquery.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/sign.js" />' type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function($) {
	
	$('#tran-result-error-div').hide();
	$('#tran-result-success-div').hide();
	$('#transactionDiv').hide();
	
	
	$('.form-button').hover(
			function(){ 
				$(this).addClass("ui-state-hover"); 
			},
			function(){ 
				$(this).removeClass("ui-state-hover"); 
			}
		);
	
	<c:choose>
    	<c:when test='${issueSkuForm.transactionType.staffTransaction}'>
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
	    	    height: 125,
	    	    width: 750,
	    	    onSelectRow: function(rowId){	    	
	    	    	var rowData = jQuery("#list2").jqGrid('getGridParam','selrow');
	    	    	var traineeId = jQuery("#list2").jqGrid('getCell',rowId,0);
	    	    	var location = ${issueSkuForm.locationId};
	    	    	if(rowData){
	    				jQuery("#list2").jqGrid('GridToForm',rowData,"#issueSkuForm");
	    			}	    	
	    	    	$('input[name="staff.staffId"]').val(traineeId);	
	    	    	iniKitLocOptions('${pageContext.request.contextPath}',location);
	    	    	$('#transactionDiv').fadeIn('slow');
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
		   		{name:'cls.className',index:'cls.className', align:'center', width:200}
		   	],
		   	rowNum:10,
		   	rowList:[10,20,30],
		   	pager: '#pager2',
		   	sortname: 'firstName',
		    viewrecords: true,
		    sortorder: "desc",
		    loadonce: true,
		    caption: "Trainee List",
		    ignoreCase:true,
		    height: 125,
		    width: 750,
		    onSelectRow: function(rowId){	    	
		    	var rowData = jQuery("#list2").jqGrid('getGridParam','selrow');
		    	var traineeId = jQuery("#list2").jqGrid('getCell',rowId,0);
		    	var className = jQuery("#list2").jqGrid('getCell',rowId,4);
		    	var location = ${issueSkuForm.locationId};
		    	if(rowData){
					jQuery("#list2").jqGrid('GridToForm',rowData,"#issueSkuForm");
				}	    	
		    	$('input[name="trainee.traineeId"]').val(traineeId);
		    	initializeKitOptions('${pageContext.request.contextPath}',className, location);
		    	$('#transactionDiv').fadeIn('slow');
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
	
	$("#kitName").autocomplete({
        source: function(request, response) {
         jQuery.ajax({
                   url : '${pageContext.request.contextPath}/common/findByProductNameLike.form',
                   dataType : 'json',
                   data : {
                       name : request.term,
                       locationId	: '2'
                   },
                   success : function(data) {
                	    response(jQuery.map(data, function(key,val) {
                            return {
                               label: key,
                               value: key,
                               optval: val
                            }
                       }))
                   }
            })
        },
        minLength : 2,
        open: function() {          	
        	jQuery(this).removeClass("ui-corner-all").addClass("ui-corner-top");
        },
        
        close: function() {
           	jQuery(this).removeClass("ui-corner-top").addClass("ui-corner-all");
        },
        
        select: function(event,ui) {
        	$('input[name="selectedProductId"]').val(ui.item.optval);
        }
   });
	
	
	 $("#itemCombo").chosen();
	  
	 $('#item-add-btn').click(function(){
		  	var itemName = $("#itemCombo").val();
			var rowCount = $('#tblTransactionForm >tbody >tr').length;
			addItem('${pageContext.request.contextPath}',itemName,rowCount,"");
	 });
	  
	
	  
	  $('#itemnumber-add-btn').click(function(){
		 
			var itemNumber = $("#itemNumber").val();
			var rowCount = $('#tblTransactionForm >tbody >tr').length;
			addItem('${pageContext.request.contextPath}',"",rowCount,itemNumber);
	 });
	 
	 
	 
	 $('#kit-add-btn').click(function(){
			var kitId = $("#kitCombo").val();
			var rowCount = $('#tblTransactionForm >tbody >tr').length;
			addKitItems('${pageContext.request.contextPath}',kitId,rowCount);
	  });
	
	$('#create-kit').click(function(){
		 $(location).attr('href','${pageContext.request.contextPath}/common/createproduct.form');		 
	});
	 
	var responseReceived = true;
	
	$('#submit-form').click(function(){	 
		
		var signObj = document.getElementById("sign");
		isSigned = signObj.isSigned();
		if(isSigned == true)
		{
			x = signObj.getString();
			pngstr=signObj.getPNGString(x,125,60,true);		
			$("#userSign").val(pngstr);
		} else {
			alert("Before Submit user must be signed");
			return;
		} 			
		if(responseReceived) {
			$('#tran-result-error-div').hide();
			$('#tran-result-success-div').hide();
			var formData =  $('#issueSkuForm').serialize();	 
			 $.ajax({
				    type: "POST",
				    url: "${pageContext.request.contextPath}/inventory/issue.form",
				    data: formData,
				    beforeSend: function() {
				    	responseReceived = false;		            
			        },
			        complete: function() {
			        	responseReceived = true;
			        },
				    success: function() {
				    	$('#tran-success').html("Transaction Successfull");
				    	$('#tran-result-success-div').show();
				    	$('#issueSkuForm')[0].reset();
				    },
				    error: function(xhr, status, error) {
				    	var x = xhr.responseText;
				    	var msg = $.trim(x);
				    	$('#tran-error').html(msg);
				    	$('#tran-result-error-div').show();
				    }
				  });
		} else {
			alert("Processing previous request. Please wait");
		}				
	 }); 
	
	});
	
	function processProductItemsResponse(data) {
		$('#tblTransactionForm >tbody').append(data);
	}
	
	
	
	


</script>
</head>
<body class="body-class" >	
	<form:form method="post" commandName="issueSkuForm" >
	<form:hidden path="transactionType" />
	<form:hidden path="userSign" />
	<form:input type="hidden" path="locationId"/>
	<c:choose>
    	<c:when test='${issueSkuForm.transactionType.staffTransaction}'>
    		<form:hidden path="staff.staffId" />
    	</c:when>
    	 <c:otherwise>
    	 	<form:hidden path="trainee.traineeId" />
    	 </c:otherwise>
    </c:choose>
		
	<input type="hidden" id="selectedProductId" name="selectedProductId" value=""/>
	<div id="main-content" class="ui-widget main-content" style="background: white;">
	<%@ include file="/WEB-INF/ui/header.jsp" %>
	<div id="top-navigation" class="top-navigation">
	<%@ include file="/WEB-INF/ui/menu.jsp" %>
	</div>
		<br/> <a id="goback" href="javascript:history.go(-1)" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; " >Go back</a> <br/>
	<br />
	<div style="clear: both;"></div>	
	
	<%@ include file="/WEB-INF/ui/transaction-result.jsp" %>
	
		<div id="heading12" class="ui-widget-header">Transaction Details</div>
		
		<div id="content" class="ui-widget-content" style="padding: 10px;">	
		
		<table>
			<tr>
				<td>
					Select a Trainee/Staff from the list below <br/>
					<table id="list2" class="trans-details"></table>
					<div id="pager2"></div>
				</td>
				<td style="padding-left: 10px;">
				Selected trainee/staff :
				<table id="transDetails" class="ui-widget item-table trans-details">				
					<tbody class="ui-widget-content trans-details" >
				<tr>
					<td>Transaction Description</td><td>${issueSkuForm.transactionDescription}</td>
				</tr>				
				<tr>
					<td>Last Name</td><td><input class="lastName" type="text" name="lastName" value="" readOnly="true"/></td>
				</tr>
				<tr>
					<td>First Name</td><td><input class="firstName" type="text" name="firstName" value=""  readOnly="true" /></td>
				</tr>
				<tr>
					<td>MiddleName Name</td><td><input type="text" name="middleName" value="" readOnly="true" /></td>
				</tr>
				
				<c:choose>
					<c:when test='${issueSkuForm.transactionType.staffTransaction}'>
					<tr>
						<td>Staff ID</td><td><input type="text" name="staffId" value="" readOnly="true" /></td>
					</tr>
    				<tr>
						<td>Division</td><td><input type="text" name="division" value="" readOnly="true" /></td>
					</tr>
					<tr>
						<td>Extension</td><td><input type="text" name="extension" value="" readOnly="true" /></td>
					</tr>
    				</c:when>
    				<c:otherwise>
    				<tr>
						<td>Class</td><td><input type="text" name="cls.className" value="" readOnly="true" /></td>
					</tr>
					<tr>
						<td>user ID</td><td><input class="uid" type="text" name="traineeId" value="" readOnly="true" /></td>
					</tr>
    				</c:otherwise>
				</c:choose>
				
				<tr>
					<td>User Sign</td>
					<td>				
						<OBJECT  classid=clsid:E634B267-B8E7-406C-A308-988636B7D7E1 NAME=websignsup width=0 height=0 codebase=websignsup.cab#Version=10,2,0,1>
							<param name=useslibrary value=websignsup>
							<param name="useslibrarycodebase" value=websignsup.cab>
							<param name="useslibraryversion" value=10,2,0,1>
						</OBJECT><br/>
					 <!--[if !IE]>-->
				      <object id="sign" name="Sign" width="125" height="65" classid="java:integrisign.webclient.WebSign.class" 
				              type="application/x-java-applet"
				              archive="websignsunjvm.jar">				       
				        <param name="archive" value="websignsunjvm.jar" />
				        <param name="scriptable" value="true">
				        <param name="cache_option" value="Plugin">
				        <param name="cache_archive" value="websignsunjvm.jar">
				        <param name="cache_version" value="10.5.0.1">
				        <param name="MAYSCRIPT" value="true">
				        <param name="borderstyle" value="1">			        
				        
				      <!--<![endif]-->
				        <object id="sign" name="Sign" width="125" height="65" classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"> 
					        <param name="archive" value="websignsunjvm.jar" />
					        <param name="code" value="integrisign.webclient.WebSign">
					        <param name="scriptable" value="true">
					        <param name="cache_option" value="Plugin">
					        <param name="cache_archive" value="websignsunjvm.jar">
					        <param name="cache_version" value="10.5.0.1">
					        <param name="MAYSCRIPT" value="true">
					        <param name="borderstyle" value="1">	
				        </object> 
				      <!--[if !IE]>-->
				      Java Plugin not enabled</object>
				      <!--<![endif]-->		

						
						<br/>
						<input type="button" value="Sign Now" name="B1" onClick="signNow()">
						<input type="hidden" name="pngstr">
					</td>
				</tr>				
			</tbody>
		</table>				
				</td>
			</tr>			
		</table>			
		</div>
		<br />
		<div id="transactionDiv">
		<div id="heading" class="ui-widget-header">Inventory Details</div>
		<div style="padding: 10px;">
		<!-- 
			<label class="ui-widget">
        		<span> Kit Name &nbsp;&nbsp;</span>
        		<select id="kitCombo" style="width:350px;"> 					
				</select>  
        		<a id="kit-add-btn" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Add</a>                                 
			</label>
			<br/>
			<br/> -->
			<label class="ui-widget">
        		<span> Item Name: </span>
        		<select id="itemCombo" data-placeholder="Select Item to add..." style="width:350px;">
					<c:forEach var="itemName" items="${issueSkuForm.itemNames}" varStatus="i">
						<option value="${itemName}">${itemName}</option>
					</c:forEach>
				</select>
        		<a id="item-add-btn" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Add</a>                                
			</label>
			<br/>
			<br/>
			<label class="ui-widget">
        		<span> Item Number: </span>
        		<input type="text" id="itemNumber" value="" />
        		<a id="itemnumber-add-btn" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Add</a>                                
			</label>	
		</div>
		<div id="content" class="ui-widget-content" style="padding: 10px; ">	
			<table id="tblTransactionForm" class="ui-widget item-table">
				<thead class="ui-state-default item-table-header">
					<tr id="rowx">
						<th>Item</th>
						<th>Item Specification</th>
						<th>Quantity</th>
					</tr>				
				</thead>
				<tbody class="ui-widget-content" >
				
				</tbody>
			</table>
			<div id="actions" align="center" class="actions">
					<a id="submit-form" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Issue</a> 
				</div>			
		</div>	
	
	</div>
	</div>
	<br>
	
	</form:form>
	
	
	
	 
</body>
</html>