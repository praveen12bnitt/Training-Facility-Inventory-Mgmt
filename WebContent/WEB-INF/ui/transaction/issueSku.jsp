<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/styles.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/jqgrid/ui.jqgrid.css" />' />
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/memu-0.1.css" />' />
<script src='<c:url value="/js/jquery/jquery-1.6.2.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery/jquery-ui-1.8.16.custom.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/grid.locale-en.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/jquery.jqGrid.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.json-2.3.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.memu-0.1.min.js" />' type="text/javascript"></script>
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
	
	
	jQuery("#list2").jqGrid({
	   	url:'${pageContext.request.contextPath}/common/listtrainees.form',
		datatype: "json",
	   	colNames:['Trainee Id','First Name', 'Last Name', 'Middle Name', 'Class'],
	   	colModel:[
			{name:'traineeId',index:'traineeId', align:'center', width:200},
	   		{name:'firstName',index:'firstName', align:'center', width:200},
	   		{name:'lastName',index:'lastName', align:'center', width:200},
	   		{name:'middleName',index:'middleName', align:'center', width:200},
	   		{name:'classNumber',index:'classNumber', align:'center', width:200}
	   	],
	   	rowNum:10,
	   	rowList:[10,20,30],
	   	pager: '#pager2',
	   	sortname: 'firstName',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: true,
	    caption: "Trainee List",
	    height: 125,
	    width: 750,
	    onSelectRow: function(rowId){
	    	
	    	var rowData = jQuery("#list2").jqGrid('getGridParam','selrow');
	    	var traineeId = jQuery("#list2").jqGrid('getCell',rowId,0);
	    	if(rowData){
				jQuery("#list2").jqGrid('GridToForm',rowData,"#issueSkuForm");
			}
	    	
	    	$('input[name="trainee.traineeId"]').val(traineeId);
	    	
	    	
	  
	    },
	    jsonReader : {
	          root: "rows",
	          page: "page",
	          total: "total",
	          records: "records",
	          repeatitems: false,
	          cell: "cell",
	          id: "id"
	      }
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
	<div style="clear: both;"></div>
	<form:form method="post" commandName="issueSkuForm" >
	<form:hidden path="transactionType" />
	<form:input type="hidden" path="locationId" value="2" />
	
	<form:hidden path="trainee.traineeId" />	
		<div id="heading" class="ui-widget-header">Transaction Details</div>
		
		<div id="content" class="ui-widget-content" style="padding: 10px;">	
		<table id="list2" class="trans-details"></table>
			<div id="pager2"></div>
		</div>
		
		<div id="header-contents" class="ui-widget-content" align="left" style="padding: 10px;">
		
		<table id="transDetails" class="ui-widget item-table trans-details">				
			<tbody class="ui-widget-content trans-details" >
				<tr>
					<td>Transaction Description</td><td>${issueSkuForm.transactionDescription}</td>
				</tr>
				<tr>
					<td>Last Name</td><td><input type="text" name="lastName" value="" readOnly="true"/></td>
				</tr>
				<tr>
					<td>First Name</td><td><input type="text" name="firstName" value=""  readOnly="true" /></td>
				</tr>
				<tr>
					<td>MiddleName Name</td><td><input type="text" name="middleName" value="" readOnly="true" /></td>
				</tr>
				<tr>
					<td>Class</td><td><input type="text" name="classNumber" value="" readOnly="true" /></td>
				</tr>
			</tbody>
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
					<button type="submit" class="ui-state-default ui-corner-all form-button">Issue</button>
				</div>			
		</div>	
	
	</div>
	<br>
	</form:form>	
</body>
</html>