<%@ include file="/ui/commoninclude.jsp" %>
<%@ page language="java" import="java.util.*,com.smartworks.invtmgmt.core.domain.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
  <title><fmt:message key="title"/></title>
  <style>
    .error { color: red; }
  </style>  
</head>



<body>
<h1><fmt:message key="Transaction Form"/></h1>

<form:form method="post" commandName="transactionForm" commandClass="com.smartworks.invtmgmt.web.ui.form.TransactionForm" >

    <table width="95%" bgcolor="f8f8ff" border="1" cellspacing="1" cellpadding="1">
    	<tr>
    		<td>
    	</tr>
    </table>

  <table width="95%" bgcolor="f8f8ff" border="1" cellspacing="1" cellpadding="1">
    <tr>
    
      <td align="left" width="20%">Item</td>
      <td align="left" width="20%">Quantity</td>
    </tr>
    
   <c:forEach items="${model.itemList}" var="itemObject">
   <c:set var="itemName" value="${itemObject.name}"/>
   <c:set var="itemId" value="${itemObject.id}"/>
   <c:set var ="itemDetails" value="${itemObject.attributeDetails}" />
   <c:set var="evalId" value="${itemId}"/>
   <tr>
   	<td>
   	<c:out value="${itemName}" /> &nbsp; 
   	<c:forEach items="${itemDetails}" var="mapEntry">
   		<c:set var="itemAttribute" value="${ mapEntry.key }"/>
   		 <c:set var="skuId" value="{itemName}"/>
   		<c:out value="${ itemAttribute.attributeName }" />
   		<c:set var="evalId" value="${evalId},itemAttributes:${itemId}:${itemAttribute.attributeName}"/>
   		<select path="" name="itemAttributes:${itemId}:${itemAttribute.attributeName}">
   				<c:forEach items="${mapEntry.value}" var="mapEntryValues">
   				    <c:set var="itemAttributeValue" value="${ mapEntryValues }"/>
   					<option value="${ itemAttributeValue.attributeValueId }"><c:out value="${ itemAttributeValue.attributeValue }" /> </option>
   				</c:forEach>
   				
   		</select>
   	</c:forEach>
   	  
   	</td>
   	<td>
   		
   		<input type="text" name="qty" value="${evalId}" />
   		
   	</td>
  	<tr>
    </c:forEach>
  </table>
  <br>
 <c:set var ="j"  value="0" />
 
  <c:forEach items="${ transactionForm.dummyStrings }" var="dummyString">
   	<form:input type="text" path="dummyStrings[${j}].firstName" value="" />
   		<c:set var ="j"  value='${j+1}' />
 </c:forEach>
  <input type="submit" align="center" value="Execute">
</form:form>

<c:out value="test" />
</body>
</html>