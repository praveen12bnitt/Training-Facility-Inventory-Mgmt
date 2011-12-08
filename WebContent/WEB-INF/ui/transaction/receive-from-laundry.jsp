


<%@ include file="/WEB-INF/ui/commoninclude.jsp"%>
<%@ page language="java"
	import="java.util.*,com.smartworks.invtmgmt.core.domain.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.smartworks.platform.AppContextUtil"%>
<%@page import="java.util.Collections"%>

<html>
<head>
<title>Accept inventory from Laundry</title>
<style>
.error {
	color: red;
}
</style>
<link rel="stylesheet" type="text/css" 	href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/dropdown.css" />'/>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/jqgrid/ui.jqgrid.css" />
<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.6.2.min.js" 	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/grid.locale-en.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.json-2.3.js" type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/jquery.dropdown.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/hoverIntent.js" />' type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/invt-common.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function() {
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
	
	jQuery("#itemName").autocomplete({
        source: function(request, response) {
         jQuery.ajax({
                   url : '${pageContext.request.contextPath}/itemlookup/name.form',
                   dataType : 'json',
                   data : {
                       name : request.term
                   },
                   success : function(data) {
                       response(jQuery.map(data, function(item) {
                            return {
                               label: item,
                               value: item
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
        }
  });
	
  
 $('#item-add-btn').click(function(){
	var addBtn = $(this);
	var itemName = addBtn.prev().val();	
	var rowCount = $('#tblTransactionForm >tbody >tr').length;
	addItem('${pageContext.request.contextPath}',itemName,rowCount);
 }); 
 
 $('#submit-form').click(function(){	 
		$('#tran-result-error-div').hide();
		$('#tran-result-success-div').hide();
		var formData =  $('#issueSkuForm').serialize();	 
		 $.ajax({
			    type: "POST",
			    url: "${pageContext.request.contextPath}/inventory/receive-laundry.form",
			    data: formData,
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
	<br />
	<div style="clear: both;"></div>
	
	<%@ include file="/WEB-INF/ui/transaction-result.jsp" %>
	
		<div id="heading" class="ui-widget-header">Transaction Details</div>
		<div id="header-contents" class="ui-widget-content header-contents" style="padding: 10px;">
		<table id="transDetails" class="ui-widget item-table trans-details">				
			<tbody class="ui-widget-content trans-details" >
				<tr>
					<td>Transaction Description</td><td>Receive Inventory from Laundry </td>
				</tr>
				
				<tr>
					<td>Location </td><td>${issueSkuForm.locationName}</td>
				</tr>
			
			</tbody>
		</table>		
		</div>

		<br />

		<div id="heading" class="ui-widget-header">Inventory Details</div>
		
		<div id="content" class="ui-widget-content" style="padding: 20px;">	
			<label class="ui-widget">
        		<span> Item Name: </span>
        		<input type="text" id="itemName" name="itemName" size="70" />   
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
				<c:forEach items="${issueSkuForm.items}" var="item" varStatus="itemRow">
      			<tr>
     			<td style="width: 400px;">     
     		    <a id="plusgif" href="#" class="addR" tabindex="-1"><img src="<c:url value='/images/plus.gif' />" height="10px" width="10px" /></a>
      			&nbsp;			
     			<form:input type="hidden" path="itemSkus[${itemRow.index}].item.id" value="${item.id}"/>${item.desc}
     		 </td>
     		 
     		 <td style="width: 300px;">
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
     		<td><form:input type="text" path="itemSkus[${itemRow.index}].quantity" size="10" />
     		</tr>
     		</c:forEach>
			</tbody>
			</table>
			<div id="actions" align="center" class="actions">
				<a id="submit-form" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Receive</a> 	
			</div>			
			</div>		
	</div>
	<br>	
	</form:form>
</body>
</html>