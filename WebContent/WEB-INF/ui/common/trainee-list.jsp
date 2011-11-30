<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Trainee Management</title>
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
	  
	  var mygrid =  jQuery("#trainee-list").jqGrid({
		   	url:'${pageContext.request.contextPath}/common/listtrainees.form',
			datatype: "json",
		   	colNames:['Id','First Name', 'Last Name', 'Middle Name', 'Class','Active','Created Time','Last Updated Time'],
		   	colModel:[
				{name:'traineeId',index:'traineeId', align:'center',width:100},
		   		{name:'firstName',index:'firstName', align:'center'},
		   		{name:'lastName',index:'lastName', align:'center'},
		   		{name:'middleName',index:'middleName', align:'center'},
		   		{name:'classNumber',index:'classNumber', align:'center'},
		   		{name:'enabled',index:'enabled', align:'center', stype:'select', editoptions:{value:":All;true:true;false:false"},width:80},
		   		{name:'createdDttmStr',index:'createdDttm', align:'center',search:false},
		   		{name:'lastUpdateDttmStr',index:'lastUpdateDttm', align:'center',search:false}
		   	],
		   	rowNum:20,
		   	mtype: "POST",
		   	rowList:[20,40,60],
		   	pager: '#pager3',
		   	sortname: 'firstName',
		   	gridview : true,
		    viewrecords: true,
		    sortorder: "desc",
		    caption: "Trainee List",
		    height: 'auto',
		    width: 'auto',
		    onSelectRow: function(rowId){	    	
		    	var rowData = $("#trainee-list").jqGrid('getGridParam','selrow');
		    	var traineeId = $("#trainee-list").jqGrid('getCell',rowId,0);
		    	if(rowData){
		    		$(location).attr('href','${pageContext.request.contextPath}/common/edit-trainee.form?traineeId='+traineeId);	
				}	    	
		    	
		    },
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
	  
	  jQuery("#trainee-list").jqGrid('navGrid','#pager3',{edit:false,add:false,del:false,search:false,refresh:false});
	  jQuery("#trainee-list").jqGrid('navButtonAdd',"#pager3",{caption:"Toggle",title:"Toggle Search Toolbar", buttonicon :'ui-icon-pin-s',
	  	onClickButton:function(){
	  		mygrid[0].toggleToolbar()
	  	} 
	  });
	  jQuery("#trainee-list").jqGrid('navButtonAdd',"#pager3",{caption:"Clear",title:"Clear Search",buttonicon :'ui-icon-refresh',
	  	onClickButton:function(){
	  		mygrid[0].clearToolbar()
	  	} 
	  });
	  jQuery("#trainee-list").jqGrid('filterToolbar');
	 
	 
	  
	
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
		<div style="padding: 10px;">
			<a id="trainee-add-btn" href="${pageContext.request.contextPath}/common/add-trainee.form" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Add Trainee</a>
		</div>
		<div id="content" class="ui-widget ui-widget-content" style="padding: 10px;">	
			<table id="trainee-list" class="trans-details" style="font-size: 95%;"></table>
			<div id="pager3"></div>
		</div>
		<br/>
			
</div>

</body>
</html>