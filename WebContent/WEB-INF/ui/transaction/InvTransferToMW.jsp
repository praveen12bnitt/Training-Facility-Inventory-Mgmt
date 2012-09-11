


<%@ include file="/WEB-INF/ui/commoninclude.jsp"%>
<%@ page language="java"
	import="java.util.*,com.smartworks.invtmgmt.core.domain.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.smartworks.platform.AppContextUtil"%>
<%@page import="java.util.Collections"%>

<html>
<head>
<title>Transfer Inventory to main warehouse</title>
<style>
.error {
	color: red;
}
</style>
<link rel="stylesheet" type="text/css" 	href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/dropdown.css" />'/>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/jqgrid/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" 	href='<c:url value="/choosen/chosen.css" />'/>

<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.6.2.min.js" 	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/grid.locale-en.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.json-2.3.js" type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/jquery.dropdown.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/hoverIntent.js" />' type="text/javascript"></script>
<script src='<c:url value="/choosen/chosen.jquery.js" />' type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/invt-common.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function($) {
	$('#tran-result-error-div').hide();
	$('#tran-result-success-div').hide();
	
	$('.form-button').hover(
			function(){ 
				$(this).addClass("ui-state-hover"); 
			},
			function(){ 
				$(this).removeClass("ui-state-hover"); 
			}
		);
	
	 $("#itemCombo").chosen();
	  
	 $('#item-add-btn').click(function(){
		var itemName = $("#itemCombo").val();
		var rowCount = $('#tblTransactionForm >tbody >tr').length;
		addItem('${pageContext.request.contextPath}',itemName,rowCount);
	 });
	 
	var responseReceived = true;
	
	$('#submit-form').click(function(){	 
		$('#tran-result-error-div').hide();
		$('#tran-result-success-div').hide();
		var formData =  $('#issueSkuForm').serialize();	 
		if(responseReceived) {
			$.ajax({
			    type: "POST",
			    url: "${pageContext.request.contextPath}/inbound/transferToMW.form",
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

</script>

</head>

<body class="body-class" >	
<form:form method="post" commandName="issueSkuForm">
<form:hidden path="transactionType" />
<form:hidden path="locationId" />

	<div id="main-content" class="ui-widget main-content" style="background: white;">
	<%@ include file="/WEB-INF/ui/header.jsp" %>
	<div id="top-navigation" class="top-navigation">
		<%@ include file="/WEB-INF/ui/menu.jsp" %>
	</div>
		<br/> <a id="goback" href="javascript:history.go(-1)" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; " >Go back</a> <br/>
	<br />
	<%@ include file="/WEB-INF/ui/transaction-result.jsp" %>

		<div id="heading" class="ui-widget-header">Transaction Details</div>
		<div id="header-contents" class="ui-widget-content header-contents" style="padding: 10px;">
		<table id="transDetails" class="ui-widget item-table trans-details">				
			<tbody class="ui-widget-content trans-details" >
			<tr>
					<td>Transaction Description</td><td>Move Inventory </td>
			</tr>
			<tr>
					<td>From Location</td>
					<td> 				
					${issueSkuForm.locationName}
     				</td>
				</tr>					
				<tr>
					<td>To Location</td>
					<td>Warehouse</td>			
			</tbody>
		</table>		
		</div>

		<br />

		<div id="heading" class="ui-widget-header">Inventory Details</div>
		
		<div id="content" class="ui-widget-content" style="padding: 10px;">	
			
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
				<a id="submit-form" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Transfer</a>   
			</div>			
		</div>	
	
	</div>
	<br>	
	</form:form>
</body>
</html>