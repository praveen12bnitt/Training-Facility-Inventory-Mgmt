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
	 	uiLaundry.itemsWashed=$('#itemsWashed').val();
	 	uiLaundry.comments=$('#comments').val();
	 	
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
		<br/> <a id="goback" href="javascript:history.go(-1)" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; " >Go back</a> <br/>
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
						<option value="TSE">A - TSE </option>
						<option value="Towel">A - Towel </option>
						<option value="Gym">A - Gym </option>
						<option value="Uniform">A - Uniform </option>
						<option value="Regular Laundry">A - Regular Laundry </option>
						<option value="JackSockBra">A - JackSockBra </option>
						<option value="BOP">B - BOP </option>
						<option value="FPS">C - FPS </option>
						<option value="CTD">D - CTD </option>
						<option value="FAD_OTA">E - FAD_OTA </option>
						<option value="PTD">F - PTD </option>
						<option value="DMD">G - DMD </option>
						<option value="ATFSABT">H - ATFSABT </option>
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
			<tr>
				<td>Items to be washed </td>
				<td><input type="text" id="itemsWashed"/></td>
			</tr>
			<tr>
				<td>Comments </td>
				<td><textarea id="comments" ></textarea></td>
			</tr>
		</table>
		
		<div id="actions" align="center" class="actions">
			<button type="button" class="ui-state-default ui-corner-all form-button">Create</button>
		</div>	
			
</div>
</form>
</body>
</html>