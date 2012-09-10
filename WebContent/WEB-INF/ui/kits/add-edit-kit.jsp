<%@page import="com.smartworks.invtmgmt.web.ui.form.ProductMultiSelectData"%>
<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Edit Kits</title>
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/styles.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/jqgrid/ui.jqgrid.css" />' />
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/dropdown.css" />'/>
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/ui.multiselect.css" />'/>
<link rel="stylesheet" type="text/css" 	href='<c:url value="/choosen/chosen.css" />'/>

<script src='<c:url value="/js/jquery/jquery-1.6.2.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery/jquery-ui-1.8.16.custom.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/grid.locale-en.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/jquery.jqGrid.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.json-2.3.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/jquery.dropdown.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/hoverIntent.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/ui_multiselect/ui.multiselect.js" />' type="text/javascript"></script>
<script src='<c:url value="/choosen/chosen.jquery.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/invt-common.js" />' type="text/javascript"></script>




<script type="text/javascript">

  $(document).ready(function() {
	
	  $("#itemCombo").chosen();
	  
	  $('#item-add-btn').click(function(){
			var itemName = $("#itemCombo").val();
			var rowCount = $('#tblTransactionForm >tbody >tr').length;
			addItem('${pageContext.request.contextPath}',itemName,rowCount);
	 });
	  
	  
	  $(document).delegate("a.deleteRow", "click", function(){ alert("Goodbye!"); })
	  
	 
	 
	    
});

</script>
</head>
<body class="body-class" >	
<form:form method="post" commandName="kitForm" id="kitForm" enctype="multipart/form-data">
<div id="main-content" class="ui-widget main-content" style="background: white;">
	<%@ include file="/WEB-INF/ui/header.jsp" %>
	<div id="top-navigation" class="top-navigation">
		<%@ include file="/WEB-INF/ui/menu.jsp" %>
	</div>
	<a id="goback" href="javascript:history.go(-1)" onMouseOver="self.status=document.referrer;return true">Go back</a>
	<br />
	<div style="clear: both;"></div>
		
		<div id="heading" class="ui-widget-header">Kit Information</div>
		<div id="content" class="ui-widget ui-widget-content" style="padding: 10px;">	
				<table class="kit-info">
				<c:if test="${editMode == true}">
					<tr>
						<td>Kit ID</td> <td><form:input type="text" path="product.productId" readonly="${editMode}"/></td>
					</tr>
				</c:if> 				
				<tr>
					<td>Kit Name</td> <td><form:input type="text" path="product.productName"/></td>
				</tr>				
				<tr>
					<td>Kit Description</td> <td><form:input type="text" path="product.productDesc" /></td>
				</tr>
				<tr>
					<td>Location </td> <td>
					<form:select path="selectedLocationId">
   						 <form:options items="${kitForm.locationOptions}" />
					</form:select></td>
				</tr>
				</table> 				
		</div>
		<br/>
		
		<div id="heading" class="ui-widget-header">Kit Details</div>
		<div id="content" class="ui-widget ui-widget-content" style="padding: 10px">	
		
		<label class="ui-widget">
        		<span> Item Name </span>
        		<select id="itemCombo" data-placeholder="Select Item to add..." style="width:350px;">
					<c:forEach var="itemName" items="${kitForm.itemNames}" varStatus="i">
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
					<c:if test="${editMode == true}">
						<c:forEach items="${kitForm.itemSkus}" var="itemSku" varStatus="itemSkuRow">
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
				     		<td>
				     		<form:input type="text" path="itemSkus[${itemSkuRow.index}].quantity" value="${ itemSku.quantity }" />
				     		</td> 			     		
	     				</tr>
	     				</c:forEach>
     				</c:if>
				</tbody>
		</table> 		
		</div>
		
		<div id="heading" class="ui-widget-header">Import Kits</div>
		<div id="content" class="ui-widget ui-widget-content" style="padding: 10px;">
			<form:input path="kitFile" type="file"/>
		</div>
		
		<div id="actions" align="left" class="actions">
			<button id="save" type="submit" class="ui-state-default ui-corner-all form-button" style="width: 50px;height: 25px; ">Save</button>
		</div>	
			
</div>
</form:form>
</body>
</html>