<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<html>
<head>
	<title>User List</title>
	<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/styles.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/jqgrid/ui.jqgrid.css" />' />
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/memu-0.1.css" />'/>
<script src='<c:url value="/js/jquery/jquery-1.6.2.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery/jquery-ui-1.8.16.custom.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/grid.locale-en.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/jquery.jqGrid.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.json-2.3.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.memu-0.1.min.js" />' type="text/javascript"></script>
</head>
</html>

<body>
     user List
	<table id="grps"></table>
	<div id="pgrps"></div>
</body>
<script>
$(document).ready(function() {
jQuery("#grps").jqGrid({
   	url:'<c:url value="/common/listusers.form" />',
	datatype: "json",
   	colNames:['User Id', 'UserName', 'Enabled'],
   	colModel:[
   		{name:'userid',	key : true,	index:'userid',	width:55},
   		{name:'userName',index:'userName', width:90},
   		{name:'enabled', index:'enabled',	width:100}
   		
   	],
   	rowNum:10,
    width:400,
   	rowList:[10,20,30],
   	pager: '#pgrps',
   	sortname: 'userName',
    viewrecords: true,
    sortorder: "desc",
	jsonReader: {
		root: "rows",
        page: "page",
        total: "total",
        records: "records",
        repeatitems: false,
        cell: "cell",
        id: "id"
	},
	caption: "Complex search",
	height: '200'
});

});
</script>
</html>