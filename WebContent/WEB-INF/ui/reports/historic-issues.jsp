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
<script type="text/javascript">
$(document).ready(function($) {
	
	jQuery("#list3").jqGrid({
	   	url:'${pageContext.request.contextPath}/reports/allhistissue.form',
		datatype: "json", 
		colNames:['Issue Date', 'Zone', 'Class Name', 'Class Number', 'Issue Type', 'Customer Type', 'Due Date', 'Issue ID', 'Status', 'First Name', 'Last Name', 'Initial', 'Gender', 'NSN', 'Nomenclature' ,'Qty Open'],
	   	colModel:[
	   		{name:'issueDateStr',index:'issueDateStr',sorttype:"date", formatter: 'date', formatoptions: { srcformat: 'm/d/Y', newformat: 'm/d/Y' },searchoptions : { sopt: ['eq'] } },
	   		{name:'zone',index:'zone'},
	   		{name:'className',index:'className'},
	   		{name:'classNumber',index:'classNumber'},
	   		{name:'issueType',index:'issueType'},
	   		{name:'customerType',index:'customerType'},
	   		{name:'dueDateStr',index:'dueDateStr',sorttype:"date", sorttype:"date", formatter: 'date', formatoptions: { srcformat: 'm/d/Y', newformat: 'm/d/Y' },searchoptions : { sopt: ['eq'] }},
	   		{name:'issueId',index:'issueId'},
	   		{name:'status',index:'status'},
	   		{name:'firstName',index:'firstName'},
	   		{name:'lastName',index:'lastName'},
	   		{name:'initial',index:'initial'},
	   		{name:'gender',index:'gender'},
	   		{name:'nsn',index:'nsn'},
	   		{name:'nomenclature',index:'nomenclature'},
	   		{name:'qty',index:'qty'}
	   	],
	   	rowNum:50,
	   	rowList:[50,100,200],
	   	pager: '#pager3',
	   	sortname: 'issueDate',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: true,
	    caption: "Historic Issues",
	    width: 1250,
	    height: 1200,
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
	
	$("#list3").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false});
	
	
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
			
			<a href="${pageContext.request.contextPath}/data/historic-issues.xlsx"> Download Historic Issues</a>
 			<br/>
			<table id="list3" class="trans-details"></table>
			<div id="pager3"></div>
		</div>
		
			
		</div>	
	
	<br>
</body>
</html>