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

<div>
	${transactionFormMessage }
</div>

<form:form method="post" commandName="issueSkuForm" >
	<form:hidden path="transactionType" />
	<form:input type="hidden" path="locationId" value="2" />
	<form:hidden path="trainee.traineeId" />
	<table width="65%" class="reference">
	<tr>
		<th colspan="2" align="left">Transaction Type:</th>
		<td colspan="2" align="left">
			${issueSkuForm.transactionDescription}
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
   
     <c:forEach items="${issueSkuForm.items}" var="item" varStatus="itemRow">
      	<tr>
     		<td>     
     		    <a id="plusgif" href="#" class="addR"><img src="<c:url value='/images/plus.gif' />" height="10px" width="10px" /></a>
      			&nbsp;			
     			<form:input type="hidden" path="itemSkus[${itemRow.index}].item.id" value="${item.id}"/>${item.desc}
     		 </td>
     		 
     		 <td>
     		 <c:forEach var="attribute" items="${item.attributeDetails}" varStatus="i">
     		 	${attribute.key.attributeName}
     		 	<form:hidden path="itemSkus[${itemRow.index}].itemAttributeDtls[${i.index}].itemAttribute.attibuteId" value="${attribute.key.attibuteId}" />
     		 	<form:select path="itemSkus[${itemRow.index}].itemAttributeDtls[${i.index}].itemAttributeValue.attributeValueId">
     					<c:forEach items="${attribute.value}" var="attributeValue">
     						<form:option value="${attributeValue.attributeValueId}">
     							${attributeValue.attributeValue}
     						</form:option>
     					</c:forEach>
     			</form:select>
     		 </c:forEach>     		     		 	
     		
     		</td>     		
     		<td><form:input type="text" path="itemSkus[${itemRow.index}].quantity" value=""/>
     	</tr>
     	
     </c:forEach>
    
    </table>
 <input type="submit" name="submit" />
</form:form>
</div>

<script>
$('.addR').click(function(){	    
	   
	var rowIndex = $(this).parent().parent().parent().children()
						.index($(this).parent().parent());
				var tableObj = document.getElementById("tblTransactionForm");
				var refRow = tableObj.rows[rowIndex];
				var newRow = tableObj.insertRow(rowIndex + 1);
				var oldHTML = refRow.innerHTML;
				//alert(rowIndex-1);
				//alert(oldHTML);  		
				var searchString = new RegExp('itemSkus\\[*[0-9]*\\]',
						"gi");
				//alert(searchString);
				newRow.innerHTML = oldHTML.replace(searchString,
						'itemSkus[' + (tableObj.rows.length - 1) + ']');
				//alert(newRow.innerHTML);

			});
</script>

</body>
</html>