<%@page import="com.smartworks.invtmgmt.web.ui.form.ProductMultiSelectData"%>
<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Trainee List</title>
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/styles.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/jqgrid/ui.jqgrid.css" />' />
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/dropdown.css" />'/>
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/ui.multiselect.css" />'/>

<script src='<c:url value="/js/jquery/jquery-1.6.2.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery/jquery-ui-1.8.16.custom.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/grid.locale-en.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/jquery.jqGrid.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.json-2.3.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/jquery.dropdown.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/hoverIntent.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/ui_multiselect/ui.multiselect.js" />' type="text/javascript"></script>

<script type="text/javascript">

  $(document).ready(function() {
	
	  $('.form-button').hover(
				function(){ 
					$(this).addClass("ui-state-hover"); 
				},
				function(){ 
					$(this).removeClass("ui-state-hover"); 
				}
			); 
	  
	  $("#kits").multiselect();
	  $("#staffs").multiselect();
	  
	  <c:if test="${editMode == true}">
	  	
	  jQuery("#traineeList").jqGrid({
		   	url:'${pageContext.request.contextPath}/common/list-class-trainees.form?className=${clsForm.cls.className}',
			datatype: "json",
		   	colNames:['Trainee Id','First Name', 'Last Name', 'Middle Name'],
		   	colModel:[
				{name:'traineeId',index:'traineeId', align:'center', width:200},
		   		{name:'firstName',index:'firstName', align:'center', width:200},
		   		{name:'lastName',index:'lastName', align:'center', width:200},
		   		{name:'middleName',index:'middleName', align:'center', width:200},
		   	],
		   	rowNum:10,
		   	rowList:[10,20,30],
		   	pager: '#traineePage',
		   	sortname: 'firstName',
		    viewrecords: true,
		    sortorder: "desc",
		    loadonce: true,
		    caption: "Trainee List",
		    ignoreCase:true,
		    height: 125,
		    width: 750,
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
	  
	  $("#traineeList").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false});
	  
	  </c:if>
	  
	  
	  	
});

</script>
</head>
<body class="body-class" >	
<form:form method="post" commandName="clsForm" id="classForm" enctype="multipart/form-data">
<div id="main-content" class="ui-widget main-content" style="background: white;">
	<%@ include file="/WEB-INF/ui/header.jsp" %>
	<div id="top-navigation" class="top-navigation">
		<%@ include file="/WEB-INF/ui/menu.jsp" %>
	</div>
	
		<br/> <a id="goback" href="javascript:history.go(-1)" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; " >Go back</a> <br/>
	
	<br />
	<div style="clear: both;"></div>
		
		<div id="heading" class="ui-widget-header">Class Information</div>
		<div id="content" class="ui-widget ui-widget-content" style="padding: 10px;">	
				<table class="trainee-add">
				<tr>
					<td>Class Name</td> <td><form:input type="text" path="cls.className" readonly="${editMode}"/></td>
				</tr>				
				<tr>
					<td>Class Description</td> <td><form:input type="text" path="cls.classDesc" /></td>
				</tr>
				</table> 				
		</div>
		<br/>
		
		<div id="heading" class="ui-widget-header">Kits</div>
		<div id="content" class="ui-widget ui-widget-content" style="padding: 10px">	
		  <select id="kits"  multiple="multiple" name="selectedKits" style="width: 450px; height: 180px; display: none; " >
			 <c:forEach var="p" items="${productData}">
			 	 <c:if test="${p.selected == true}">
			 	 	<option value="${p.productId}" selected="selected">${p.productName}</option>
			 	 </c:if>
			 	 <c:if test="${p.selected == false}">
			 	 	<option value="${p.productId}">${p.productName}</option>
			 	 </c:if>
			  </c:forEach>
			</select> 			
		</div>
		<div id="heading" class="ui-widget-header">Staffs</div>
		<div id="content" class="ui-widget ui-widget-content" style="padding: 10px">	
		<select id="staffs"  multiple="multiple" name="selectedStaffs" style="width: 450px; height: 180px; display: none; " >
			 <c:forEach var="s" items="${staffData}">
			 	 <c:if test="${s.selected == true}">
			 	 	<option value="${s.staffId}" selected="selected">${s.staffName}</option>
			 	 </c:if>
			 	 <c:if test="${s.selected == false}">
			 	 	<option value="${s.staffId}">${s.staffName}</option>
			 	 </c:if>
			  </c:forEach>
			</select>	
		
		</div>
		<div id="heading" class="ui-widget-header">Trainee List</div>
		<div id="content" class="ui-widget ui-widget-content" style="padding: 10px;"> 		
			Upload Trainee's : <form:input path="traineeFile" type="file"/>
		</div>
		
		<table id="traineeList" class="trans-details"></table>
		<div id="traineePage"></div>
		
		
		<div id="actions" align="left" class="actions">
			<button id="save" type="submit" class="ui-state-default ui-corner-all form-button" style="width: 50px;height: 25px; ">Save</button>
		</div>	
			
</div>
</form:form>
</body>
</html>