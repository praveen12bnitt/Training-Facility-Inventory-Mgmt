<%@ include file="/WEB-INF/ui/commoninclude.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kits</title>
<style>
.error {
	color: red;
}
</style>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css"
	href='<c:url value="/css/dropdown.css" />' />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/jqgrid/ui.jqgrid.css" />
<script
	src="${pageContext.request.contextPath}/js/jquery/jquery-1.6.2.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/jquery/jquery-ui-1.8.16.custom.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/jqgrid/grid.locale-en.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/jqgrid/jquery.jqGrid.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.json-2.3.js"
	type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/jquery.dropdown.js" />'
	type="text/javascript"></script>
<script src='<c:url value="/js/dropdown/hoverIntent.js" />'
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/invt-common.js"
	type="text/javascript"></script>
<body class="body-class">
<form:form method="post" commandName="prodForm" id="kitForm" enctype="multipart/form-data">
	<div id="main-content" class="ui-widget main-content"
		style="background: white;">
		<%@ include file="/WEB-INF/ui/header.jsp"%>
		<div id="top-navigation" class="top-navigation">
			<%@ include file="/WEB-INF/ui/menu.jsp"%>
		</div>
		<br /> <a id="goback" href="javascript:history.go(-1)"
			class="form-button ui-state-default ui-corner-all"
			style="padding: .2em 1em;">Go back</a> <br /> <br />
		<div style="clear: both;"></div>

		<%@ include file="/WEB-INF/ui/transaction-result.jsp"%>

		<div id="heading" class="ui-widget-header">Import Kits</div>
		<div id="content" class="ui-widget ui-widget-content"
			style="padding: 10px;">
			<form:input path="kitFile" type="file" />
		</div>
		 <div id="actions" align="left" class="actions">
			<button id="save" type="submit" class="ui-state-default ui-corner-all form-button" style="width: 50px;height: 25px; ">Save</button>
		</div>
	</div>
	</form:form>
</body>