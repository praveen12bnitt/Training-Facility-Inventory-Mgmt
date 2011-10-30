<%@ include file="/ui/commoninclude.jsp" %>
<%@ page language="java" import="java.util.*,com.smartworks.invtmgmt.core.domain.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
  <title><fmt:message key="title"/></title>
  <style>
    .error { color: red; }
  </style>  
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
 </head>



<body>
<h1>Staff Uniform</h1>

<form:form method="post" commandName="transactionForm" >

    <table width="65%" bgcolor="f8f8ff" border="1" cellspacing="1" cellpadding="1">
    	<tr>
    		<td>Last Name</td>
    		<td><form:input type="text" path="uiTransactionReceiver.lastName" /></td>
    		<td>First Name</td>
    		<td><form:input type="text" path="uiTransactionReceiver.firstName" /></td>
    	</tr>
    	<tr>
    		<td>MiddleName Name</td>
    		<td><form:input type="text" path="uiTransactionReceiver.middleName" /></td>
    		<td>Department</td>
    		<td><form:input type="text" path="uiTransactionReceiver.department" /></td>
    	</tr>
    </table>

  
  <br>
  <table  id="tblTransactionForm" width="95%" bgcolor="f8f8ff" border="1" cellspacing="1" cellpadding="1">
    <tr id="rowx">
      <td align="left" width="20%">Item</td>
       <td align="left" width="20%">Attributes</td>
      <td align="left" width="20%">Quantity</td>
    </tr>
   
     <c:forEach items="${transactionForm.listUIFormItems}" var="uiFormItem" varStatus="uifItemRow">
     	<tr id="${ uiFormItem.itemId }">
     		<td>
     			<a id="plusgif" href="#"><img src="<c:url value='/images/plus.gif' />" height="10px" width="10px" /></a>
      			&nbsp;
     			<form:input type="hidden" path="listUIFormItems[${uifItemRow.index}].itemId" value="${ uiFormItem.itemId }"/>${ uiFormItem.itemName}
      			
     		 </td>
     		<td>
     			
     			<c:forEach items="${uiFormItem.uiFormItemAttributes}" var="uiFormItemAttribute" varStatus="uifItemAttrRow">
     				${uiFormItemAttribute.itemAttributeName } &nbsp;
     				
     				<form:select path="listUIFormItems[${uifItemRow.index}].uiFormItemAttributes[${uifItemAttrRow.index }].selectedAttributeValue">
     					<c:forEach items="${uiFormItemAttribute.itemAttributeValues}" var="uiFormItemAttributeValue">
     						<form:option value="${uiFormItemAttributeValue.itemAttributeValueId}">
     							${uiFormItemAttributeValue.itemAttributeValue}
     						</form:option>
     					</c:forEach>
     				</form:select>
     				
     				     				
     			</c:forEach>
     		</td>
     		<td><form:input type="text" path="listUIFormItems[${uifItemRow.index}].itemQty" value="${ uiFormItem.itemQty }"/>
     	</tr>
     	
     </c:forEach>
    
    </table>
  <br>


  <input type="submit" name="submit" value="issue">
  
</form:form>

<script>
$('a').click(function(){	    
	   
	var rowIndex = $(this).parent().parent().parent().children()
						.index($(this).parent().parent());
				var tableObj = document.getElementById("tblTransactionForm");
				var refRow = tableObj.rows[rowIndex];
				var newRow = tableObj.insertRow(rowIndex + 1);
				var oldHTML = refRow.innerHTML;
				//alert(rowIndex-1);
				//alert(oldHTML);  		
				var searchString = new RegExp('listUIFormItems\\[*[0-9]*\\]',
						"gi");
				//alert(searchString);
				newRow.innerHTML = oldHTML.replace(searchString,
						'listUIFormItems[' + (tableObj.rows.length - 1) + ']');
				//alert(newRow.innerHTML);

			});
</script>


</body>
</html>