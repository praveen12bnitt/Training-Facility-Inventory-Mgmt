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
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/memu-0.1.css" />' />
<script src='<c:url value="/js/jquery/jquery-1.6.2.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery/jquery-ui-1.8.16.custom.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/grid.locale-en.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/jquery.jqGrid.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.json-2.3.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.memu-0.1.min.js" />' type="text/javascript"></script>
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

</script>
</head>
<body class="body-class" >	
	<form:form method="post" commandName="issueSkuForm" >
	<form:hidden path="transactionType" />
	<form:input type="hidden" path="locationId" value="2" />
	<form:hidden path="trainee.traineeId" />	
	<form:hidden path="refTransactionId" value="${issueSkuForm.refTransactionId }" />
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
					<td>Transaction Description</td><td>${issueSkuForm.transactionDescription}</td>
				</tr>
				<tr>
					<td>Last Name</td><td>${issueSkuForm.trainee.lastName}</td>
				</tr>
				<tr>
					<td>First Name</td><td>${issueSkuForm.trainee.firstName}</td>
				</tr>
				<tr>
					<td>MiddleName Name</td><td>${issueSkuForm.trainee.middleName}</td>
				</tr>
				<tr>
					<td>Class</td><td>${issueSkuForm.trainee.classNumber}</td>
				</tr>
			</tbody>
		</table>					
		</div>
				

		<br />

		<div id="heading" class="ui-widget-header">Inventory Details</div>
		
		<div id="content" class="ui-widget-content" style="padding: 10px;">	
			<table id="tblTransactionForm" class="ui-widget item-table">
				<thead class="ui-state-default item-table-header">
					<tr id="rowx">
						<th>Item</th>
						<th>Item Specification</th>
						<th>Quantity</th>
					</tr>				
				</thead>
				<tbody class="ui-widget-content" >
				<c:forEach items="${transDetails.itemSkus}" var="itemSku" varStatus="itemSkuRow">
      			<tr>
     			<td style="width: 400px;">     
     		    <form:input type="hidden" path="itemSkus[${itemSkuRow.index}].item.id" value="${itemSku.item.id}"/>${itemSku.item.desc}
     		 </td>
     		 
     		 <td style="width: 300px;">
     		 <c:forEach var="itemAttributeDetails" items="${itemSku.itemAttributeDtls}" varStatus="itemAttributeRow">
     		 		<form:input type="hidden" path="itemSkus[${itemSkuRow.index}].itemAttributeDtls[${itemAttributeRow.index }].itemAttribute.attibuteId"
     		 			value="${itemAttributeDetails.itemAttribute.attibuteId}" /> ${ itemAttributeDetails.itemAttribute.attributeName }
     		 		:
     		 		<form:input type="hidden" path="itemSkus[${itemSkuRow.index}].itemAttributeDtls[${itemAttributeRow.index }].itemAttributeValue.attributeValueId"
     		 			value="${itemAttributeDetails.itemAttributeValue.attributeValueId}" /> ${ itemAttributeDetails.itemAttributeValue.attributeValue}
     		 	</c:forEach>   		     		 	
     		
     		</td>     		
     		<td><form:input type="text" path="itemSkus[${itemSkuRow.index}].quantity" value="${ itemSku.quantity }"/>
     		</tr>
     		</c:forEach>
			</tbody>
			</table>
			<div id="actions" align="center" class="actions">
					<button type="submit" class="ui-state-default ui-corner-all form-button">Return</button>
				</div>			
		</div>	
	
	</div>
	<br>
	</form:form>	
</body>
</html>