<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Trainee List</title>
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

	  jQuery("#itemName").autocomplete({
	        source: function(request, response) {
	         jQuery.ajax({
	                   url : '${pageContext.request.contextPath}/itemlookup/getProductName.form',
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
	  jQuery("#traineeName").autocomplete({
	        source: function(request, response) {
	         jQuery.ajax({
	                   url : '${pageContext.request.contextPath}/itemlookup/getTraineeName.form',
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
			var actualName = itemName;
			var innerHtml = $("#rowtemplate").html();
			
			
			innerHtml = innerHtml.replace("hdnVal",actualName);
			innerHtml = innerHtml.replace("hdnName",actualName);
			innerHtml = innerHtml.replace("hndId","hndId_"+actualName);
			$("#itemDetails > tbody").append("<tr><td>"+innerHtml+"</td></tr>");
			
			$('#itemDetails >tbody >tr >td img.delete').click(function(){
				  $(this).parent().parent().remove();
			});
			
		 });
	  $('#trainee-add-btn').click(function(){
			var addBtn = $(this);
			var itemName = addBtn.prev().val();	
			var actualName = itemName;
			var innerHtml = $("#rowtemplate").html();
			
			
			innerHtml = innerHtml.replace("hdnVal",actualName);
			innerHtml = innerHtml.replace("hdnName",actualName);
			innerHtml = innerHtml.replace("hndId","hndId_"+actualName);
			$("#traineeDetails > tbody").append("<tr><td>"+innerHtml+"</td></tr>");
			
			$('#traineeDetails >tbody >tr >td img.delete').click(function(){
				  $(this).parent().parent().remove();
			});
			
		 });
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
		
		<div id="heading" class="ui-widget-header">Add Class</div>
		<div id="content" class="ui-widget ui-widget-content" style="padding: 10px;">	
			<form:form method="post" commandName="clsForm" id="classForm">
				<form:hidden path="edit"/>
				<table class="trainee-add">
				<tr>
					<td>Class Name</td> <td><form:input type="text" path="cls.className" /></td>
				</tr>
				
				<tr>
					<td>Class Description</td> <td><form:input type="text" path="cls.classDesc" /></td>
				</tr>
				</table>
				${trainee.size()}
				${clsForm.trainee.size()}
				<label class="ui-widget">
        			<span> Kit Name: </span>
        			<input type="text" id="itemName" name="itemName" size="70"  />   
        			<a id="item-add-btn" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Add</a>                                
				</label>
				<table id="itemDetails" class="ui-widget item-table trans-details"  width="60%">
		 			<tbody class="ui-widget-content trans-details" >
		 
		  			</tbody>
		  		</table>
		  		<label class="ui-widget">
        			<span> Trainee Name: </span>
        			<input type="text" id="traineeName" name="traineeName" size="70"  />   
        			<a id="trainee-add-btn" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Add</a>                                
				</label>
		  		<table id="traineeDetails" class="ui-widget item-table trans-details"  width="60%">
		 			<tbody class="ui-widget-content trans-details" >
		 
		  			</tbody>
		  		</table>
				<div id="actions" align="center" class="actions">
					<button id="save" type="submit" class="ui-state-default ui-corner-all form-button" style="width: 50px;height: 25px; ">Save</button>
				</div>	
				
			</form:form>
			
		</div>
		<br/>
			
</div>
<div id="rowtemplate" align="left" class="actions" style="visibility:hidden" >
		
			<img class="delete" src="<c:url value='/images/delete.jpg' />" />&nbsp;&nbsp;
		   	<input type="hidden" id="hndId"  value="hdnVal">hdnName
				
	</div>

</body>
</html>