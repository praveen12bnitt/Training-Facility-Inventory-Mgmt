<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Issue</title>
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/styles.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/jqgrid/ui.jqgrid.css" />' />
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/dropdown.css" />'/>
<link rel="stylesheet" type="text/css" 	href='<c:url value="/choosen/chosen.css" />'/>
<script src='<c:url value="/js/jquery/jquery-1.6.2.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery/jquery-ui-1.8.16.custom.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/grid.locale-en.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/jquery.jqGrid.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.json-2.3.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/jquery.dropdown.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/hoverIntent.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/invt-common.js" />' type="text/javascript"></script>
<script src='<c:url value="/choosen/chosen.jquery.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/sign.js" />' type="text/javascript"></script>
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
		<c:if test='${issueFromPreIssue == true }'>
			var signObj = document.getElementById("sign");
			isSigned = signObj.isSigned();
			if(isSigned == true)
			{
				x = signObj.getString();
				pngstr=signObj.getPNGString(x,125,60,true);		
				$("#userSign").val(pngstr);
			} else {
				alert("Before Submit user must be signed");
				return;
			} 			
		</c:if>
			
		
		if(responseReceived) {
			$.ajax({
			    type: "POST",
			    url: "${pageContext.request.contextPath}/inventory/${targetPage}",
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
		<br/> <a id="goback" href="javascript:history.go(-1)" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; " >Go back</a> <br/>
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
						<tr>
							<td>MiddleName Name</td><td>${issueSkuForm.trainee.middleName}</td>
						</tr>
						
				  	</c:otherwise>
			  	</c:choose>
			  	<c:if test="${issueFromPreIssue == true }" >
			  		<tr>
					<td>User Sign</td>
					<td>				
						<OBJECT  classid=clsid:E634B267-B8E7-406C-A308-988636B7D7E1 NAME=websignsup width=0 height=0 codebase=websignsup.cab#Version=10,2,0,1>
							<param name=useslibrary value=websignsup>
							<param name="useslibrarycodebase" value=websignsup.cab>
							<param name="useslibraryversion" value=10,2,0,1>
						</OBJECT><br/>
					 <!--[if !IE]>-->
				      <object id="sign" name="Sign" width="125" height="65" classid="java:integrisign.webclient.WebSign.class" 
				              type="application/x-java-applet"
				              archive="websignsunjvm.jar">				       
				        <param name="archive" value="websignsunjvm.jar" />
				        <param name="scriptable" value="true">
				        <param name="cache_option" value="Plugin">
				        <param name="cache_archive" value="websignsunjvm.jar">
				        <param name="cache_version" value="10.5.0.1">
				        <param name="MAYSCRIPT" value="true">
				        <param name="borderstyle" value="1">			        
				        
				      <!--<![endif]-->
				        <object id="sign" name="Sign" width="125" height="65" classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"> 
					        <param name="archive" value="websignsunjvm.jar" />
					        <param name="code" value="integrisign.webclient.WebSign">
					        <param name="scriptable" value="true">
					        <param name="cache_option" value="Plugin">
					        <param name="cache_archive" value="websignsunjvm.jar">
					        <param name="cache_version" value="10.5.0.1">
					        <param name="MAYSCRIPT" value="true">
					        <param name="borderstyle" value="1">	
				        </object> 
				      <!--[if !IE]>-->
				      Java Plugin not enabled</object>
				      <!--<![endif]-->		

						
						<br/>
						<input type="button" value="Sign Now" name="B1" onClick="signNow()">
						<input type="hidden" name="pngstr">
					</td>
				</tr>				
			  	</c:if>
			  	
			</tbody>
		</table>					
		</div>
				

		<br />

		<div id="heading" class="ui-widget-header">Inventory Details</div>
		
		<div style="padding: 10px;">
			<label class="ui-widget">
        		<span> Item Name: </span>
        		<select id="itemCombo" data-placeholder="Select Item to add..." style="width:350px;">
					<c:forEach var="itemName" items="${issueSkuForm.itemNames}" varStatus="i">
						<option value="${itemName}">${itemName}</option>
					</c:forEach>
				</select>
        		<a id="item-add-btn" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Add</a>                                
			</label>	
		</div>
		
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
     		<td>
     			<form:input type="text"  path="itemSkus[${itemSkuRow.index}].quantity"  value="${ itemSku.quantity }" />
     		</td>     		
     		</tr>
     		</c:forEach>
			</tbody>
			</table>
			<div id="actions" align="center" class="actions">
					<a id="submit-form" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Save</a> 
				</div>			
		</div>	
	
	</div>
	<br>
	</form:form>	
</body>
</html>