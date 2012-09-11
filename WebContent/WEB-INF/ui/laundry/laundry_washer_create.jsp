<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Open User Transation</title>
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
<form:form method="post" commandName="laundryTrackingForm" >
<div id="main-content" class="ui-widget main-content" style="background: white;">
	<%@ include file="/WEB-INF/ui/header.jsp" %>
	<div id="top-navigation" class="top-navigation">
		<%@ include file="/WEB-INF/ui/menu.jsp" %>
	</div>
		<br/> <a id="goback" href="javascript:history.go(-1)" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; " >Go back</a> <br/>
	<br />
	<div style="clear: both;"></div>
		<table>					
			<tr>
				<td>Code</td>
				<td>
				<form:input type="text" path="laundryTracking.code" size="1" />
				</td>
			</tr>
		</table>
		 
		<div id="heading" class="ui-widget-header">Washing Tracking</div>
		<div id="content" class="ui-widget-content" style="padding: 10px;">	
			Washing MC No : <form:input type="text" path="laundryTracking.washingMachineNo" size="2" />
			&nbsp;
			Washer Buggy Weight : <form:input type="text" path="laundryTracking.washerWeightBuggy" size="4" />
			<table class="laundry">			
			<!-- tr>
				<td>TSE Room</td>
				<td><form:input type="text" path="laundryTracking.tseRoom" size="2" /> </td>
				<td>Towels Set</td>
				<td><form:input type="text" path="laundryTracking.towels" size="2" /></td>
				<td>Gym Clothing set</td>
				<td><form:input type="text" path="laundryTracking.gymClothings" size="2" /></td>
				
			</tr>
			
			<tr>
				<td>Jock Sock Bras Set</td>
				<td><form:input type="text" path="laundryTracking.jockSocksBras" size="2" /></td>
				<td>Uniforms set</td>
				<td><form:input type="text" path="laundryTracking.uniforms" size="2" /></td>
				<td>Reg Laundry</td>
				<td><form:input type="text" path="laundryTracking.regLaundry" size="2" /></td>
			</tr>
			
			<tr>
				<td>DMD 0006-G</td>
				<td><form:input type="text" path="laundryTracking.DMD0006G" size="2" /></td>
				<td>FAD 0006-E</td>
				<td><form:input type="text" path="laundryTracking.FAD0006E" size="2" /></td>
				<td>CTD 0006-D</td>
				<td><form:input type="text" path="laundryTracking.CTD0006D" size="2" /></td>
				
			</tr>
			
			<tr>
				<td>ATF SABT 0006-H</td>
				<td><form:input type="text" path="laundryTracking.ATFSABT0006H" size="2" /></td>
				<td>PTD 0006-F</td>
				<td><form:input type="text" path="laundryTracking.PTD0006F" size="2" /></td>
				<td>USBOPB 0006-B</td>
				<td><form:input type="text" path="laundryTracking.USBOPB0006B" size="2" /></td>
			</tr>
			
			<tr>
				<td>FPS 0006-C</td>
				<td><form:input type="text" path="laundryTracking.FPS0006C" size="2" /></td>
				
			</tr-->
			<tr> 
			<select>
			
			
			</select>
			</tr>
		</table>
		</div>
		<br/>
		
</form:form>
</body>
</html>