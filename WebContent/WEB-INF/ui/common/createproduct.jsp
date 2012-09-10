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
<script src='<c:url value="/js/form/jquery.form.js" />' type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function($) {
	$('.form-button').hover(
			function(){ 
				$(this).addClass("ui-state-hover"); 
			},
			function(){ 
				$(this).removeClass("ui-state-hover"); 
			}
		);
	
	$('#transactionResponse').hide();
	
	
	jQuery("#list2").jqGrid({
		url:'${pageContext.request.contextPath}/common/listproducts.form',
		datatype: "json",
	   	colNames:['Kit Id','Kit Name', 'Kit Desc', 'Location', 'Location Id'],
	   	colModel:[
			
			{name:'productId',index:'productId', align:'center', width:200},
	   		{name:'productName',index:'productName', align:'center', width:200},
	   		{name:'productDesc',index:'productDesc', align:'center', width:200},
	   		{name:'location.locationName',index:'location.locationName', align:'center', width:200},
	   		{name:'location.locationId',index:'location.locationId', align:'center', width:200, hidden:true},
	   		 		
	   	],
	   	rowNum:10,
	   	rowList:[10,20,30],
	   	pager: '#pager2',
	   	sortname: 'productName',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: false,
	    caption: "Kit List",
	    height: 125,
	    width: 750,
	    onSelectRow: function(rowId){
	    	var rowData = jQuery("#list2").jqGrid('getGridParam','selrow');
	    	if(rowData){
	    		//alert("test");
				jQuery("#list2").jqGrid('GridToForm',rowData,"#command");
			}
	    	var productId = jQuery("#list2").jqGrid('getCell',rowId,0);
	    	$("#itemDetails").find("tr").remove();
	    	jQuery.ajax(
	    			{
	    				url: "<c:url value='/common/getItemsByProductId.form' />",
	    				dataType: 'json',
	    				data: {
	    					productId: productId
	    				},
	    				success:processResponse
	    			}		
	    		); 	
	    	
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
	
	
	jQuery('#command').ajaxForm(
		{
			beforeSubmit: print
		}		
	); 	
	
	jQuery('#command').submit(function() { 
	    // submit the form 
	    jQuery(this).ajaxSubmit({
	    	url: '${pageContext.request.contextPath}/common/saveproduct.form',
	    	
	    	success: showTransactionStatus
	    }
	    ); 
	    // return false to prevent normal browser submit and page navigation 
	    return false; 
	});
	
	
	jQuery("#itemName").autocomplete({
        source: function(request, response) {
         jQuery.ajax({
                   url : '${pageContext.request.contextPath}/itemlookup/itemNames.form',
                   dataType : 'json',
                   data : {
                       name : request.term
                   },
                   success : function(data) {
                	    response(jQuery.map(data, function(item) {
                            return {
                               label: item,
                               value: item
                            }
                       }))
                   }
            })
        },
        minLength : 2,
        open: function() {          	
        	jQuery(this).removeClass("ui-corner-all").addClass("ui-corner-top");
        },
        
        close: function() {
           jQuery(this).removeClass("ui-corner-top").addClass("ui-corner-all");
        }
  });
	
	$('#item-add-btn').click(function(){
		var addBtn = $(this);
		var itemName = addBtn.prev().val();	
		var actualName = itemName.substring(0,itemName.lastIndexOf("("));
		var itemId = itemName.substring(itemName.lastIndexOf("(")+1,itemName.lastIndexOf(")"));
		
		var innerHtml = $("#rowtemplate").html();
		
		innerHtml = innerHtml.replace("hdnVal",itemId);
		innerHtml = innerHtml.replace("hdnName",actualName);
		
		$("#itemDetails > tbody").append("<tr><td>"+innerHtml+"</td></tr>");
		
		$('#itemDetails >tbody >tr >td img.delete').click(function(){
			  $(this).parent().parent().remove();
		});
		
	 }); 
		
	$('#deletekit').click(function(){	
	 	jQuery.ajax(
				{
					url: "<c:url value='/common/deleteproduct.form' />",
					dataType: 'json',
					
					data: {
						productId: $('input[name="productId"]').val()
					},
					success: showTransactionStatus,
					error: showTransactionStatus
				}		
			); 	
		});
	});

function print() {
	jQuery('#itemDetails >tbody >tr').each(
			function(index, tr) {
				$(this).find('td').each(function(){
					var itemName = "itemList["+index+"].itemId";
			        $(this).find('#itemId').attr('name',itemName);
			    })
			}
	);
}

function processResponse(data) {
	$.each(data, function(key, val) {
		var innerHtml = $("#rowtemplate").html();
		
		innerHtml = innerHtml.replace("hdnVal",key);
		innerHtml = innerHtml.replace("hdnName",val);
		
		$("#itemDetails > tbody").append("<tr><td>"+innerHtml+"</td></tr>");
		
		$('#itemDetails >tbody >tr >td img.delete').click(function(){
			  $(this).parent().parent().remove();
		});
	});
}

function showTransactionStatus() {
	$('#transactionResponse').show();
	$("#list2").trigger("reloadGrid");
	$('#transactionResponse').fadeOut(4000);
}

</script>
</head>
<body class="body-class" >	
	
	
	
	<div id="main-content" class="ui-widget main-content" style="background: white;">
	<%@ include file="/WEB-INF/ui/header.jsp" %>
	<div id="top-navigation" class="top-navigation">
	<%@ include file="/WEB-INF/ui/menu.jsp" %>
	</div>
	
	<a id="goback" href="javascript:history.go(-1)" onMouseOver="self.status=document.referrer;return true">Go back</a>
	
	<br />
	<div style="clear: both;"></div>	
	
	    
		<div id="heading" class="ui-widget-header">Kit Management</div>
		
		<div id="content" class="ui-widget-content" style="padding: 10px;">	
		<table id="list2" class="trans-details"></table>
			<div id="pager2"></div>
		</div>
		<div id="content" class="ui-widget-content" style="padding: 10px;">
		<form:form method="post" name="productForm" action="#" >
		
			
			<br>
				<table id="transDetails" class="ui-widget item-table trans-details">				
			<tbody class="ui-widget-content trans-details" >
				<tr>
					<td>Kit Name</td><td><input type=hidden name="productId" value="" /><input type="text" name="productName" value="" /></td>
				</tr>
				<tr>
					<td>Kit Desc</td><td><input type="text" name="productDesc" value=""  /></td>
				</tr>
				
				<tr>
					<td>Location</td>
									<td>
										<select name="location.locationId">
											<c:forEach var="location" items="${locations}">
												<option value="${location.locationId}">${location.locationName} </option>
											</c:forEach>
										</select>
									</td>
				</tr>
				
			</tbody>
		  </table>	
		  <BR>
		  <label class="ui-widget">
        		<span> Item Name: </span>
        		<input type="text" id="itemName" name="itemName" size="70"  />   
        		<a id="item-add-btn" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Add</a>                                
			</label>
			
		  <br>
		 <table id="itemDetails" class="ui-widget item-table trans-details"  width="60%">
		 <tbody class="ui-widget-content trans-details" >
		 
		  </tbody>
		  </table>
		  
		  <div id="actions" align="left" class="actions" >
		  			<input type="reset" class="ui-state-default ui-corner-all form-button" alt="Create new" value="new" onclick="productForm.productId.value=''">
					<input type="submit" class="ui-state-default ui-corner-all form-button" alt="save or update the kit" value="save">
					<input type="button" id="deletekit" class="ui-state-default ui-corner-all form-button" alt="save or update the kit" value="delete">
				</div>	
		   	
		 <div id="transactionResponse" class="ui-corner-all ui-state-highlight">
		<p>
			<span class="ui-icon ui-icon-info"
				style="float: left; margin-left: 1em;"></span> <strong>Success
			</strong> Transaction successful
			
		</p>
	</div>
			
		</form:form>
		</div>		

		<br />
	
	</div>
	<br>
	
	<div id="rowtemplate" align="left" class="actions" style="visibility:hidden" >
		
			<img class="delete" src="<c:url value='/images/delete.jpg' />" />&nbsp;&nbsp;
		   	<input type="hidden" id="itemId"  value="hdnVal">hdnName
				
	</div>	
	
</body>
</html>