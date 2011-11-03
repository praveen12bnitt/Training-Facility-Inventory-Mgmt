<%@ include file="/ui/commoninclude.jsp" %>
<%@ page language="java" import="java.util.*,com.smartworks.invtmgmt.core.domain.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
  <title><fmt:message key="title"/></title>
  <style>
    .error { color: red; }
  </style> 
 <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />" />
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
 </head>



<body>

<div id="portal-header">

<a id="portal-logo" accesskey="1" href="http://www.fletc.gov">

    <img src="<c:url value='/images/logo.jpg' />" alt="" title="logo.gif" height="78" width="345"></a>



</div>





<form:form method="post" commandName="transactionForm" >

<table width="65%" class="reference">
<tr>
	<th colspan="2" align="left">Transaction Type:</th>
	<td colspan="2" align="left">
		<c:out value="${transactionForm.transactionType}" />
	</td>

</tr>
</table>
<br>
</br>

    <table width="65%" class="reference" >
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
  <table  id="tblTransactionForm" class="reference" width="80%">
    <tr id="rowx">
      <th align="left" width="20%">Item</th>
      <th align="left" width="20%">Attributes</th>
      <th align="left" width="20%">Quantity</th>
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