<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<html>
<head>
	<title>User List</title>
	 <script src="<c:url value='/js/jquery-1.6.4.js' />"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/css/i18n/jquery-ui-1.7.1.custom.css' />" />
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/css/i18n/ui.jgrid.css' />" />
	<script src="<c:url value='/js/i18n/grid.locale-en.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/i18n/jquery.jqGrid.min.js' />" type="text/javascript"></script>
</head>
</html>

<body>
     user List
	<table id="grps"></table>
	<div id="pgrps"></div>
</body>
<script>
jQuery("#grps").jqGrid({
   	url:'server.php?q=4',
	datatype: "json",
   	colNames:['Inv No', 'Date', 'Client', 'Amount','Tax','Total','Notes'],
   	colModel:[
   		{name:'id',	key : true,	index:'id',	width:55},
   		{name:'invdate',index:'invdate', width:90},
   		{name:'name', index:'name',	width:100},
   		{name:'amount',index:'amount', width:80, align:"right"},
   		{name:'tax',index:'tax', width:80, align:"right"},
   		{name:'total',index:'total', width:80,align:"right"},
   		{name:'note',index:'note', width:150, sortable:false}
   	],
   	rowNum:10,
    width:700,
   	rowList:[10,20,30],
   	pager: '#pgrps',
   	sortname: 'invdate',
    viewrecords: true,
    sortorder: "desc",
	jsonReader: {
		repeatitems : false
	},
	caption: "Complex search",
	height: '100%'
});
jQuery("#grps").jqGrid('navGrid','#pgrps',
{edit:false,add:false,del:false},
{},
{},
{},
{multipleSearch:true, multipleGroup:true}
);
</script>
</html>