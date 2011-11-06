<%@ page errorPage="ErrorPage.jsp"%>


<%@ include file="/WEB-INF/ui/commoninclude.jsp"%>
<%@ page language="java"
	import="java.util.*,com.smartworks.invtmgmt.core.domain.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.smartworks.platform.AppContextUtil"%>
<%@page import="java.util.Collections"%>

<html>
<head>
<title><fmt:message key="title" /></title>
<style>
.error {
	color: red;
}
</style>
<link rel="stylesheet" type="text/css" 	href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" 	href="${pageContext.request.contextPath}/css/memu-0.1.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/jqgrid/ui.jqgrid.css" />
<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.6.2.min.js" 	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/grid.locale-en.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.json-2.3.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.memu-0.1.min.js" type="text/javascript"></script>

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

	<div id="main-content" class="ui-widget main-content" style="background: white;">
	<%@ include file="/WEB-INF/ui/header.jsp" %>
	<div id="top-navigation" class="top-navigation">
		<%@ include file="/WEB-INF/ui/menu.jsp" %>
	</div>
	<br />
	<form:form method="post" commandName="transactionForm">

		<div id="heading" class="ui-widget-header">Transaction Details</div>
		<div id="Header" class="ui-widget-content" align="left">
		<form:errors path="locationList[0].error" cssClass="errors" />	
		<table>
			<tr>
			<td>From Location</td>
			<td>
			<select id="fromlocn">
			<option value="UI">WM</option>
				</select>
				</td>
					</tr>
					<tr>
						<td>To Location</td>
						<td><form:select path="locationList[0].selectedValue"
								id="tolocn">
								<c:forEach items="${transactionForm.locationList}"
									var="uiFormLocation">
									<form:option value="${uiFormLocation.location_id}">
     							${uiFormLocation.locationName}
     						</form:option>

								</c:forEach>
							</form:select></td>
					</tr>
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

					<c:forEach items="${transactionForm.listUIFormItems}"
						var="uiFormItem" varStatus="uifItemRow">
						<tr id="${ uiFormItem.itemId }">
							<td style="width: 400px;"><a id="plusgif" href="#" tabindex="-1"><img
									src="<c:url value='/images/plus.gif' />" height="10px"
									width="10px" /></a> &nbsp; <form:input type="hidden"
									path="listUIFormItems[${uifItemRow.index}].itemId"
									value="${ uiFormItem.itemId }" />${ uiFormItem.itemName}</td>
							<td style="width: 300px;"><c:forEach items="${uiFormItem.uiFormItemAttributes}"
									var="uiFormItemAttribute" varStatus="uifItemAttrRow">
     				${uiFormItemAttribute.itemAttributeName } &nbsp;
     				<form:input type="hidden"
										path="listUIFormItems[${uifItemRow.index}].uiFormItemAttributes[${uifItemAttrRow.index }].itemAttributeId"
										value="${uiFormItemAttribute.itemAttributeId}" />
									<form:select
										path="listUIFormItems[${uifItemRow.index}].uiFormItemAttributes[${uifItemAttrRow.index }].selectedAttributeValue">
										<c:forEach items="${uiFormItemAttribute.itemAttributeValues}"
											var="uiFormItemAttributeValue">
											<form:option
												value="${uiFormItemAttributeValue.itemAttributeValueId}">
     							${uiFormItemAttributeValue.itemAttributeValue}
     						</form:option>
										</c:forEach>
									</form:select>


								</c:forEach></td>
							<td><form:input type="text"
									path="listUIFormItems[${uifItemRow.index}].itemQty" size="10"
									value="${ uiFormItem.itemQty }" />
						</tr>

					</c:forEach>
					</tbody>			
				</table>	
				<div id="actions" align="center" class="actions">
					<button type="submit" class="ui-state-default ui-corner-all form-button">Transfer</button>
				</div>		
		</div>
	</form:form>
	</div>
	<br>	
</body>
</html>