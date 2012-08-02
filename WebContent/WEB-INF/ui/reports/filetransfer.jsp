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
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/dropdown.css" />'/>
<script src='<c:url value="/js/jquery/jquery-1.6.2.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery/jquery-ui-1.8.16.custom.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/grid.locale-en.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/jquery.jqGrid.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.json-2.3.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/jquery.dropdown.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/hoverIntent.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery/fileuploader.js" />' type="text/javascript"></script>
<script type="text/javascript">

jQuery(function($){
	$('.fileUpload').fileUploader();
});

var options = [];
$.getJSON("${pageContext.request.contextPath}/common/getlocations.form", function(data) {
	jQuery.map(data, function(val, key) { 
		options.push("<option value="+key+">"+val+"</option>");
	});
	$("#locations").html(options.join(''));
});
$(document).ready(function($) {
$('#download').click(function(){
	 $(location).attr('href','${pageContext.request.contextPath}/reports/inventoryReport.form?locationId='+$("select").val() );		 
}); 

$('#start-trigger').click(function(){
	 $(location).attr('href','${pageContext.request.contextPath}/reports/processfile.form' );		 
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
	
		
		
		
		<div id="content" class="ui-widget ui-widget-content" style="padding: 10px;">	
			<table id="list3" class="trans-details">
				<tr><td>Select a Location</td></tr>
				<tr><td></td></tr>
				<tr><td><select id="locations" name="locations"></select>
				
				</td></tr>
			</table>
			
			<div id="actions" align="left" class="actions">
				<a id="download" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Download</a>
				</div>
				<br>
			<form action="${pageContext.request.contextPath}/uploadfile/upload.form" method="post" enctype="multipart/form-data">
			<input type="file" name="file" class="fileUpload" >
		 
			<button id="px-submit" type="submit">Upload</button>
			<button id="px-clear" type="reset">Clear</button>
			
		</form>
			
			
		</div>
		<div id="start-trigger" align="left" class="actions">
				<a id="start-trigger" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Start</a>
				</div>
			
		</div>	
		
	
	<br>
</body>
</html>