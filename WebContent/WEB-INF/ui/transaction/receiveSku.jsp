<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/jqgrid/ui.jqgrid.css" />' />
<script src='<c:url value="/js/jquery/jquery-1.6.2.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery/jquery-ui-1.8.16.custom.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/grid.locale-en.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/jquery.jqGrid.min.js" />' type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css" />' />
</head>
<body>
<%@ include file="/WEB-INF/ui/menu.jsp" %>

<div align="center" style="width: 100%" >


<form:form method="post" commandName="issueSkuForm" >
	<form:hidden path="transactionType" value="${issueSkuForm.transactionType }" />
	<form:input type="hidden" path="locationId" value="2" />
	<form:hidden path="trainee.traineeId" />
	<form:hidden path="refTransactionId" value="${issueSkuForm.refTransactionId }" />
	<table width="65%" class="reference">
	<tr>
		<th colspan="2" align="left">Transaction Type:</th>
		<td colspan="2" align="left">
			${issueSkuForm.transactionType}
		</td>
	</tr>
	</table>
	<br/>
    <table width="65%" class="reference">
    	<tr>
    		<td>Last Name</td>
    		<td>${issueSkuForm.trainee.lastName}</td>
    		<td>First Name</td>
    		<td>${issueSkuForm.trainee.firstName}</td>
    	</tr>
    	<tr>
    		<td>MiddleName Name</td>
    		<td>${issueSkuForm.trainee.middleName}</td>
    		<td>Class</td>
    		<td>${issueSkuForm.trainee.classNumber}</td>
    	</tr>
    </table>  
  <br>
  
   <table  id="tblTransactionForm" class="reference" width="80%">
    <tr id="rowx">
      <th align="left" width="20%">Item</th>
      <th align="left" width="20%">Attributes</th>
      <th align="left" width="20%">Quantity</th>
    </tr>
   
     <c:forEach items="${transDetails.itemSkus}" var="itemSku" varStatus="itemSkuRow">
      	<tr>
     		<td>     
     		    <form:input type="hidden" path="itemSkus[${itemSkuRow.index}].item.id" value="${itemSku.item.id}"/>${itemSku.item.desc}
     		 </td>
     		 
     		 <td>
     		 	<c:forEach var="itemAttributeDetails" items="${itemSku.itemAttributeDtls}" varStatus="itemAttributeRow">
     		 		<form:input type="hidden" path="itemSkus[${itemSkuRow.index}].itemAttributeDtls[${itemAttributeRow.index }].itemAttribute.attibuteId"
     		 			value="${itemAttributeDetails.itemAttribute.attibuteId}" /> ${ itemAttributeDetails.itemAttribute.attributeName }
     		 		:
     		 		<form:input type="hidden" path="itemSkus[${itemSkuRow.index}].itemAttributeDtls[${itemAttributeRow.index }].itemAttributeValue.attributeValueId"
     		 			value="${itemAttributeDetails.itemAttributeValue.attributeValueId}" /> ${ itemAttributeDetails.itemAttributeValue.attributeValue}
     		 	</c:forEach>     		 	
     		
     		</td>     		
     		<td><form:input type="text" path="itemSkus[${itemSkuRow.index}].quantity" value="${ itemSku.quantity }"/> </td>
     	</tr>
     	
     </c:forEach>
    
    </table>
 <input type="submit" name="submit" />
</form:form>
</div>



</body>
</html>