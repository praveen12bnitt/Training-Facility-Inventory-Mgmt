<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Laundry Tracking</title>
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/styles.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/jqgrid/ui.jqgrid.css" />' />
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/dropdown.css" />'/>
<script src='<c:url value="/js/jquery/jquery-1.6.2.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery/jquery-ui-1.8.16.custom.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery/jquery-ui-timepicker-addon.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/grid.locale-en.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/jquery.jqGrid.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.json-2.3.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/jquery.dropdown.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/hoverIntent.js" />' type="text/javascript"></script>
<script src='<c:url value="/ui/model/UILaundry.js" />' type="text/javascript"></script>

<script type="text/javascript">

  $(document).ready(function() {
	  $('#transactionResponse').hide();
	  $('.form-button').hover(
				function(){ 
					$(this).addClass("ui-state-hover"); 
				},
				function(){ 
					$(this).removeClass("ui-state-hover"); 
				}
			);
	  
	 $('#time').datetimepicker( {
			ampm: true,
			stepMinute: 5
	 	}
	 );
	 
	 $('#actions').click(function(){	
	 	var uiLaundry = new UILaundry();
	 	uiLaundry.laundryType=$('#laundryType').val();
	 	uiLaundry.unitNo=$('#unitNo').val();
	 	uiLaundry.clientInfo=$('#client').val();
	 	uiLaundry.time=$('#time').val();
	 	uiLaundry.weight=$('#weight').val();
	 	uiLaundry.buggyWeight=$('#buggyWeight').val();
	 	
	 	
	   $.ajax({
              url : '${pageContext.request.contextPath}/newlaundry/save-load.form',
              contentType: 'application/json',
              type: 'post',
              data : JSON.stringify(uiLaundry),
              success : showResponse
	  });
	 	
	 });
});
  
  function showResponse() {
	  $('#transactionResponse').show();
	  $('#transactionResponse').fadeOut(4000);
  }

</script>
</head>
<body class="body-class" >	
<form >
<jsp:useBean id="fieldsMap" scope="request" type="java.util.Map">
</jsp:useBean>
<div id="main-content" class="ui-widget main-content" style="background: white;">
	<%@ include file="/WEB-INF/ui/header.jsp" %>
	<div id="top-navigation" class="top-navigation">
		<%@ include file="/WEB-INF/ui/menu.jsp" %>
	</div>
	<br />
	<div style="clear: both;"></div>
	    <div id="heading" class="ui-widget-header"><%= fieldsMap.get("Header") %></div>
	     <div id="transactionResponse" class="ui-corner-all ui-state-highlight">
		<p>
			<span class="ui-icon ui-icon-info"
				style="float: left; margin-left: 1em;"></span> <strong>Success
			</strong> Transaction successful
			
		</p>
		</div>
	    <input type="hidden" id="laundryType" name="laundryType" value="<%= fieldsMap.get("LaundryType") %>" />
		<table class="laundry">
			<tr>
				<td><%= fieldsMap.get("Unit") %></td>
				<td><input type="text" size="4" id="unitNo"/></td>
			</tr>
			
			<tr>
				<td>Client Info</td>
				<td>
					<select name="client" id="client">
						<option value="1006A-TSE"> 1006A - TSE </option>
						<option value="1006A-TOWEL"> 1006A - Towel </option>
						<option value="1006A-GYM"> 1006A - Gym </option>
						<option value="1006A-Uniform"> 1006A - Uniform </option>
						<option value="1006A-REGLAUNDRY"> 1006A - Regular Laundry </option>
						<option value="1006A-JACKSOCK"> 1006A - JackSockBra </option>
						<option value="1006B-USBOPB0006B"> 1006B - USBOPB0006B </option>
						<option value="1006C-FPS0006C">1006C - FPS0006C </option>
						<option value="1006D-CTD0006D"> 1006D - CTD0006D </option>
						<option value="1006E-FAD0006E">1006E - FAD0006E </option>
						<option value="1006F-PTD0006F">1006F - PTD0006F </option>
						<option value="1006G-DMD0006G"> 1006G - DMD0006G </option>
						<option value="1006F-ATFSABT0006H">1006H - ATFSABT0006H </option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><%= fieldsMap.get("Weight") %></td>
				<td><input type="text" size="4" id="weight"/></td>
			</tr>
			<tr>
				<td><%= fieldsMap.get("Time") %></td>
				<td><input type="text" size="25" id="time"/></td>
			</tr>
			<tr>
				<td>Buggy Weight </td>
				<td><input type="text" size="4" id="buggyWeight" value="48"/> &nbsp; LB</td>
			</tr>
		</table>
		
		<div id="actions" align="center" class="actions">
			<button type="button" class="ui-state-default ui-corner-all form-button">Create</button>
		</div>	
			
</div>
</form>
</body>
</html>