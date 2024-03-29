<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Staff</title>
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/styles.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/jqgrid/ui.jqgrid.css" />' />
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/dropdown.css" />'/>
<script src='<c:url value="/js/jquery/jquery-1.6.2.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery/jquery-ui-1.8.16.custom.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/grid.locale-en.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/jquery.jqGrid.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.json-2.3.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/jquery.dropdown.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/hoverIntent.js" />' type="text/javascript"></script>

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
	  	
});

</script>
</head>
<body class="body-class" >	
<div id="main-content" class="ui-widget main-content" style="background: white;">
	<%@ include file="/WEB-INF/ui/header.jsp" %>
	<div id="top-navigation" class="top-navigation">
		<%@ include file="/WEB-INF/ui/menu.jsp" %>
	</div>
	
		<br/> <a id="goback" href="javascript:history.go(-1)" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; " >Go back</a> <br/>
	
	<br />
	<div style="clear: both;"></div>
		
		<div id="heading" class="ui-widget-header">Add Staff</div>
		<div id="content" class="ui-widget ui-widget-content" style="padding: 10px;">	
			<form:form method="post" commandName="staffForm" id="staffForm">
				<form:hidden path="edit"/>
				<table class="staff-add">
				<c:if test="${staffForm.edit}">
					<form:hidden path="staff.createdDttm"/>
					<form:hidden path="staff.lastUpdateDttm"/>
					<tr>
					<td>Staff Id</td> <td><form:input type="text" path="staff.staffId" readonly="true"/></td>
					</tr>
				</c:if>
				<tr>
					<td>First Name</td> <td><form:input type="text" path="staff.firstName" /></td>
				</tr>
				
				<tr>
					<td>Middle Name</td> <td><form:input type="text" path="staff.middleName" /></td>
				</tr>
				
				<tr>
					<td>Last Name</td> <td><form:input type="text" path="staff.lastName" /></td>
				</tr>
				
				<tr>
					<td>Division</td> <td><form:input type="text" path="staff.division" /></td>
				</tr>
				
				<tr>
					<td>Extension</td> <td><form:input type="text" path="staff.extension" /></td>
				</tr>
				
				<tr>
					<td>Active</td> <td><form:checkbox path="staff.enabled" /></td>
				</tr>
				
				</table>
				
				<div id="actions" align="center" class="actions">
					<button id="save" type="submit" class="ui-state-default ui-corner-all form-button" style="width: 50px;height: 25px; ">Save</button>
				</div>	
		
			</form:form>
		</div>
		<br/>
			
</div>

</body>
</html>