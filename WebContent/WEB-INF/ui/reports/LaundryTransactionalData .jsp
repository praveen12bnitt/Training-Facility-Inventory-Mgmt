<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adjust Inventory</title>
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
<script>
$(document).ready(
		function() {
			$('#fromDate').datepicker();
			$('#toDate').datepicker();
			
			$('#showReport').click(
					function() {
						
					}
			);
		}
);
</script>


</head>
<body class="body-class" >	
<form method="post" action='<c:url value="/reports/laundryTransactionalDatareport-page.form"/>'>
	
	<div id="main-content" class="ui-widget main-content" style="background: white;">
	<%@ include file="/WEB-INF/ui/header.jsp" %>
	<div id="top-navigation" class="top-navigation">
	<%@ include file="/WEB-INF/ui/menu.jsp" %>
	</div>
	
		<br/> <a id="goback" href="javascript:history.go(-1)" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; " >Go back</a> <br/>
	
	<br />
	<div id="heading12" class="ui-widget-header">Laundry Transactional Data Input</div>
	<div style="clear: both;"></div>	
	
		<div id="content" class="ui-widget ui-widget-content" style="padding: 10px;">	
			
			<br/>
			<br/>
			<table id="transDetails" class="ui-widget item-table trans-details">				
			<tbody class="ui-widget-content trans-details" >
				<tr>
					<td>Report Inputs</td><td></td>
				</tr>
				<tr>
					<td>From Date</td><td><input type="text" name="fromDate" id="fromDate" value="" /></td>
				</tr>
				<tr>
					<td>To Date</td> <td><input type="text" name="toDate" id="toDate" value=""/></td>  
				</tr>
				
			</tbody>
		</table>		
			<div id="actions" align="center" class="actions">
				<button id="showReport" type="submit" class="ui-state-default ui-corner-all form-button">Show Report</button>
			</div>
			<div id="pager3"></div>
		</div>
	</div>	
	
	<br>
	</form>
</body>

</html>