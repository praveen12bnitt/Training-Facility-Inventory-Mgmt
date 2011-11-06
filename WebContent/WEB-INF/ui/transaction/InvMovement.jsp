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
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/redmond/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/jqgrid/ui.jqgrid.css" />
<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.6.2.min.js" 	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/grid.locale-en.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>

<script type="text/javascript">

$('')

</script>

</head>

<body>
	<%@ include file="/WEB-INF/ui/menu.jsp"%>

	<div id="main-content" class="ui-widget main-content" align="center">

		<div id="Header" class="ui-widget-content">Content goes here...
		</div>

		<br />

		<div id="heading" class="ui-widget-header">Inventory Details</div>
		
		<div id="content" class="ui-widget-content">
			<form:form method="post" commandName="transactionForm">
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
				<br>
				<table id="tblTransactionForm" class="ui-widget">
				<thead class="ui-widget-header">					
					<tr id="rowx">
						<th align="left" width="20%">Item</th>
						<th align="left" width="20%">Attributes</th>
						<th align="left" width="20%">Quantity</th>
					</tr>
					</thead>
					<tbody class="ui-widget-content">

					<c:forEach items="${transactionForm.listUIFormItems}"
						var="uiFormItem" varStatus="uifItemRow">
						<tr id="${ uiFormItem.itemId }">
							<td><a id="plusgif" href="#"><img
									src="<c:url value='/images/plus.gif' />" height="10px"
									width="10px" /></a> &nbsp; <form:input type="hidden"
									path="listUIFormItems[${uifItemRow.index}].itemId"
									value="${ uiFormItem.itemId }" />${ uiFormItem.itemName}</td>
							<td><c:forEach items="${uiFormItem.uiFormItemAttributes}"
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
									path="listUIFormItems[${uifItemRow.index}].itemQty"
									value="${ uiFormItem.itemQty }" />
						</tr>

					</c:forEach>
					</tbody>

				</table>



				<button type="submit" class="ui-state-default ui-corner-all">Transfer</button>
			</form:form>
		</div>

	</div>
	<br>

	<script>
		$('a').click(
				function() {

					var rowIndex = $(this).parent().parent().parent()
							.children().index($(this).parent().parent());
					var tableObj = document
							.getElementById("tblTransactionForm");
					var refRow = tableObj.rows[rowIndex];
					var newRow = tableObj.insertRow(rowIndex + 1);
					var oldHTML = refRow.innerHTML;
					//alert(rowIndex-1);
					//alert(oldHTML);  		
					var searchString = new RegExp(
							'listUIFormItems\\[*[0-9]*\\]', "gi");
					//alert(searchString);
					newRow.innerHTML = oldHTML.replace(searchString,
							'listUIFormItems[' + (tableObj.rows.length - 1)
									+ ']');
					//alert(newRow.innerHTML);

				});
	</script>
</body>
</html>