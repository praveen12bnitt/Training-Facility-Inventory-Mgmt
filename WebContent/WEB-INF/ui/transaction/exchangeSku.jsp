<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Returns</title>
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
	var responseReceived = true;
	$('#submit-form').click(function(){	 
		$('#tran-result-error-div').hide();
		$('#tran-result-success-div').hide();
		var formData =  $('#issueSkuForm').serialize();	 
		if(responseReceived) {
			$.ajax({
			    type: "POST",
			    url: "${pageContext.request.contextPath}/inventory/exchange.form",
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
	
	
	 $('.enable-exchange-btn').click(function(){
			var btn = $(this);
			var itemId = btn.attr("itemId");
			var index = btn.attr("index");
			$.ajax({
				  url: '${pageContext.request.contextPath}'+'/inventory/exchange-html-data.form',
				  data: {
					  itemId : itemId,
					  exchagneItemIndex : index	
				  },
				  success: function(html){
					  
					  var tabId = "#exchagne-"+index ;
					  $(tabId).append(html);
					  //disable the button
					  btn.hide();
					  return true;
				  }		  
				});
			
		 }); 
	
});

</script>
</head>
<body class="body-class" >	
	<form:form method="post" commandName="issueSkuForm" >
	<form:hidden path="transactionType" />
	<form:input type="hidden" path="locationId" />
	 <c:choose>
	  	<c:when test='${issueSkuForm.transactionType.staffTransaction}'>
	  		<form:hidden path="staff.staffId" />
	  	</c:when>
	  	<c:otherwise>
	  		<form:hidden path="trainee.traineeId" />
	  	</c:otherwise>
	  </c:choose>
		
	<form:hidden path="refTransactionId" value="${issueSkuForm.refTransactionId }" />
	<div id="main-content" class="ui-widget main-content" style="background: white;">
	<%@ include file="/WEB-INF/ui/header.jsp" %>
	<div id="top-navigation" class="top-navigation">
		<%@ include file="/WEB-INF/ui/menu.jsp" %>
	</div>
	<a id="goback" href="javascript:history.go(-1)" onMouseOver="self.status=document.referrer;return true">Go back</a>
	<br />
	<div style="clear: both;"></div>
	
	<%@ include file="/WEB-INF/ui/transaction-result.jsp" %>

		<div id="heading" class="ui-widget-header">Transaction Details</div>
		<div id="header-contents" class="ui-widget-content" align="left" style="padding: 10px;">
		
		<table id="transDetails" class="ui-widget item-table trans-details">				
			<tbody class="ui-widget-content trans-details" >
				<tr>
					<td>Transaction Description</td><td>${issueSkuForm.transactionDescription}</td>
				</tr>
				
				<c:choose>
				  	<c:when test='${issueSkuForm.transactionType.staffTransaction}'>
				  		<tr>
							<td>Last Name</td><td>${issueSkuForm.staff.lastName}</td>
						</tr>
						<tr>
							<td>First Name</td><td>${issueSkuForm.staff.firstName}</td>
						</tr>
						<tr>
							<td>MiddleName Name</td><td>${issueSkuForm.staff.middleName}</td>
						</tr>
						<tr>
							<td>Division</td><td>${issueSkuForm.staff.division}</td>
						</tr>
						<tr>
							<td>Extension</td><td>${issueSkuForm.staff.extension}</td>
						</tr>
				  	</c:when>
				  	<c:otherwise>
				  		<tr>
							<td>Last Name</td><td>${issueSkuForm.trainee.lastName}</td>
						</tr>
						<tr>
							<td>First Name</td><td>${issueSkuForm.trainee.firstName}</td>
						</tr>
						
						
				  	</c:otherwise>
			  	</c:choose>
			</tbody>
		</table>					
		</div>
				

		<br />

		<div id="heading" class="ui-widget-header">Inventory Details</div>
		
		<div id="content" class="ui-widget-content" style="padding: 10px;">	
			<table id="tblTransactionForm" class="ui-widget item-table">
				<thead class="ui-state-default item-table-header">
					<tr id="rowx">
						<th style="width: 400px;">Item</th>
						<th style="width: 300px;">Item Specification</th>
						<th>Issued Quantity</th>
					</tr>				
				</thead>
			</table>
			
			<c:forEach items="${transDetails.exchangeInvt}" var="exchangeItemSku" varStatus="exchangeItemSkuRow">
				<table class="ui-widget item-table" id="exchagne-${exchangeItemSkuRow.index}">
				<tbody class="ui-widget-content" >
      			<tr>
     			<td style="width: 400px;">     
     		    <form:input type="hidden" path="exchangeInvt[${exchangeItemSkuRow.index}].issuedSku.item.id" value="${exchangeItemSku.issuedSku.item.id}"/>${exchangeItemSku.issuedSku.item.desc}
     		    <form:input type="hidden" path="exchangeInvt[${exchangeItemSkuRow.index}].issuedSku.item.requiresProcessing" value="${exchangeItemSku.issuedSku.item.requiresProcessing}"/>
     		 </td>
     		 
     		 <td style="width: 300px;">
     		 <c:forEach var="itemAttributeDetails" items="${exchangeItemSku.issuedSku.itemAttributeDtls}" varStatus="itemAttributeRow">
     		 		<form:input type="hidden" path="exchangeInvt[${exchangeItemSkuRow.index}].issuedSku.itemAttributeDtls[${itemAttributeRow.index }].itemAttribute.attibuteId"
     		 			value="${itemAttributeDetails.itemAttribute.attibuteId}" /> ${ itemAttributeDetails.itemAttribute.attributeName }
     		 		:
     		 		<form:input type="hidden" path="exchangeInvt[${exchangeItemSkuRow.index}].issuedSku.itemAttributeDtls[${itemAttributeRow.index }].itemAttributeValue.attributeValueId"
     		 			value="${itemAttributeDetails.itemAttributeValue.attributeValueId}" /> ${ itemAttributeDetails.itemAttributeValue.attributeValue}
     		 	</c:forEach>   		     		 	
     		
     		</td>     		
     		<td>
     		<form:input type="hidden" path="exchangeInvt[${exchangeItemSkuRow.index}].issuedSku.quantity" value="${exchangeItemSku.issuedSku.quantity }" />
     		<form:input type="text"  path="exchangeInvt[${exchangeItemSkuRow.index}].issuedSku.quantity" disabled="true" value="${exchangeItemSku.issuedSku.quantity }" />
     		</td>
     		<td>
     		<a  index="${exchangeItemSkuRow.index}" itemId="${exchangeItemSku.issuedSku.item.id}"  href="#" class="enable-exchange-btn form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Exchange</a>       
     		</td>
     		</tr>
     		</tbody>
     		</table>
     		<br/>
     		</c:forEach>
			<div id="actions" align="center" class="actions">
					<a id="submit-form" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Exchange</a> 
				</div>			
		</div>	
	
	</div>
	<br>
	</form:form>	
</body>
</html>