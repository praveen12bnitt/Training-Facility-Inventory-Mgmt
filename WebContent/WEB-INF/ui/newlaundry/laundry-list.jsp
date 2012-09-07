<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Laundry List</title>
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

var laundryLabels = new Object();
laundryLabels['W.unitNo']='Washing Machine No';
laundryLabels['D.unitNo']='Dryer No';
laundryLabels['W.time']='Time of Washing';
laundryLabels['D.time']='Time of Drying';
laundryLabels['W.weight']='Dirty Weight';
laundryLabels['D.weight']='Clean Weight';
laundryLabels['W.caption']='Washer Details';
laundryLabels['D.caption']='Dryer Details';

  $(document).ready(function() {
	
	  $('.form-button').hover(
				function(){ 
					$(this).addClass("ui-state-hover"); 
				},
				function(){ 
					$(this).removeClass("ui-state-hover"); 
				}
			);	  
 
	  jQuery("#open-laundry").jqGrid({
		   	url:'${pageContext.request.contextPath}/newlaundry/laundrylist-page.form',
		   	postData: {
		   		fromDate: function() {return $('#fromDate').val()},
		   		toDate: function() {return $('#toDate').val()},
		   		laundryType: function() {return $('#laundryType').val()}
		   	}, 
		   	datatype: "json",
		   	colNames:[laundryLabels[$('#laundryType').val()+ '.unitNo'],laundryLabels[$('#laundryType').val()+ '.time'] ,
		   	          'Client Info',laundryLabels[$('#laundryType').val()+ '.weight'],'Buggy Weight', 'Weight'],
		   	colModel:[
		   		{name:'unitNo',index:'unitNo', width:12,align:"center"},
		   		{name:'time',index:'createDttm', width:12,align:"center"},
		   		{name:'clientInfo',index:'laundryDetails', width:23, align:"center"},
		   		{name:'weight',index:'totalWeight', width:7, align:"center"},
		   		{name:'buggyWeight',index:'buggyWeight', width:7, align:"center"},
		   		{name:'itemWeight',index:'weight', width:7, align:"center"}
		   		
		   	],
		   	rowNum:50,
		   	rowList:[50,100,150],
		   	pager: '#pager3',
		   	sortname: 'id',
		    viewrecords: true,
		    sortorder: "desc",
		    loadonce: true,
		    caption: laundryLabels[$('#laundryType').val()+ '.caption'],
		    width: 1000,
		    height: 'auto',
		    mtype: 'POST', 
		    
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
		<input type="hidden" id="fromDate" value="<%= request.getParameter("fromDate") %>" />
		<input type="hidden" id="toDate" value="<%= request.getParameter("toDate") %>" />
		<input type="hidden" id="laundryType" value="<%= request.getParameter("laundryType") %>" />
		<div id="heading" class="ui-widget-header"><%= "W".equals(request.getParameter("laundryType"))?"Washing":"Drying" %></div>
		<div id="content" class="ui-widget ui-widget-content" style="padding: 10px;">	
			
			<table id="open-laundry" class="trans-details" style="font-size: 95%;"></table>
			<div id="pager3"></div>
		</div>
		<br/>
			
</div>

</body>
</html>