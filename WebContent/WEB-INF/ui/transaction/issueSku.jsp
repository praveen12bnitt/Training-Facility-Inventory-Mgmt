<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Issue</title>
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
<script src='<c:url value="/js/invt-common.js" />' type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function($) {
	
	$('#tran-result-error-div').hide();
	$('#tran-result-success-div').hide();
	$('#transactionDiv').hide();
	
	
	$('.form-button').hover(
			function(){ 
				$(this).addClass("ui-state-hover"); 
			},
			function(){ 
				$(this).removeClass("ui-state-hover"); 
			}
		);
	
	<c:choose>
    	<c:when test='${issueSkuForm.transactionType.staffTransaction}'>
	    	jQuery("#list2").jqGrid({
	    	   	url:'${pageContext.request.contextPath}/common/list-active-staff.form',
	    		datatype: "json",
	    	   	colNames:['Id','First Name', 'Last Name', 'Middle Name', 'Division', 'Extension'],
	    	   	colModel:[
	    			{name:'staffId',index:'staffId', align:'center', width:200},
	    	   		{name:'firstName',index:'firstName', align:'center', width:200},
	    	   		{name:'lastName',index:'lastName', align:'center', width:200},
	    	   		{name:'middleName',index:'middleName', align:'center', width:200},
	    	   		{name:'division',index:'classNumber', align:'center'},
	    	   		{name:'extension',index:'extension', align:'center',width:90},
	    	   	],
	    	   	rowNum:10,
	    	   	rowList:[10,20,30],
	    	   	pager: '#pager2',
	    	   	sortname: 'firstName',
	    	    viewrecords: true,
	    	    sortorder: "desc",
	    	    loadonce: true,
	    	    caption: "Staff List",
	    	    ignoreCase:true,
	    	    height: 125,
	    	    width: 750,
	    	    onSelectRow: function(rowId){	    	
	    	    	var rowData = jQuery("#list2").jqGrid('getGridParam','selrow');
	    	    	var traineeId = jQuery("#list2").jqGrid('getCell',rowId,0);
	    	    	if(rowData){
	    				jQuery("#list2").jqGrid('GridToForm',rowData,"#issueSkuForm");
	    			}	    	
	    	    	$('input[name="staff.staffId"]').val(traineeId);	
	    	    	$('#transactionDiv').fadeIn('slow');
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
    	</c:when>
    <c:otherwise>
	    jQuery("#list2").jqGrid({
		   	url:'${pageContext.request.contextPath}/common/list-active-trainees.form',
			datatype: "json",
		   	colNames:['Trainee Id','First Name', 'Last Name', 'Middle Name', 'Class'],
		   	colModel:[
				{name:'traineeId',index:'traineeId', align:'center', width:200},
		   		{name:'firstName',index:'firstName', align:'center', width:200},
		   		{name:'lastName',index:'lastName', align:'center', width:200},
		   		{name:'middleName',index:'middleName', align:'center', width:200},
		   		{name:'classNumber',index:'classNumber', align:'center', width:200}
		   	],
		   	rowNum:10,
		   	rowList:[10,20,30],
		   	pager: '#pager2',
		   	sortname: 'firstName',
		    viewrecords: true,
		    sortorder: "desc",
		    loadonce: true,
		    caption: "Trainee List",
		    ignoreCase:true,
		    height: 125,
		    width: 750,
		    onSelectRow: function(rowId){	    	
		    	var rowData = jQuery("#list2").jqGrid('getGridParam','selrow');
		    	var traineeId = jQuery("#list2").jqGrid('getCell',rowId,0);
		    	if(rowData){
					jQuery("#list2").jqGrid('GridToForm',rowData,"#issueSkuForm");
				}	    	
		    	$('input[name="trainee.traineeId"]').val(traineeId);	
		    	$('#transactionDiv').fadeIn('slow');
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
    </c:otherwise>
	</c:choose>
	$("#list2").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false});
	
	$("#kitName").autocomplete({
        source: function(request, response) {
         jQuery.ajax({
                   url : '${pageContext.request.contextPath}/common/findByProductNameLike.form',
                   dataType : 'json',
                   data : {
                       name : request.term
                   },
                   success : function(data) {
                	    response(jQuery.map(data, function(key,val) {
                            return {
                               label: key,
                               value: key,
                               optval: val
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
        },
        
        select: function(event,ui) {
        	$('input[name="selectedProductId"]').val(ui.item.optval);
        }
   });
	
	$("#itemName").autocomplete({
        source: function(request, response) {
         jQuery.ajax({
                   url : '${pageContext.request.contextPath}/itemlookup/name.form',
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
	
	
	$('#kit-add-btn').click(function(){
		var prdId =$('input[name="selectedProductId"]').val();
		jQuery.ajax({
        url : '${pageContext.request.contextPath}/inventory/loadProductItems.form',
        data : {
            productId : prdId,
            rowNum : $('#tblTransactionForm >tbody >tr').size()
        },
        success : processProductItemsResponse
 		})
		}
	);
	
	 $('#item-add-btn').click(function(){
			var addBtn = $(this);
			var itemName = addBtn.prev().val();	
			var rowCount = $('#tblTransactionForm >tbody >tr').length;
			addItem('${pageContext.request.contextPath}',itemName,rowCount);
		 }); 
	 
	 $('#create-trainee').click(function(){
		 $(location).attr('href','${pageContext.request.contextPath}/common/add-trainee.form');		 
	}); 
	 
	 $('#create-staff').click(function(){
		 $(location).attr('href','${pageContext.request.contextPath}/common/add-staff.form');		 
	});
	 
	 $('#create-kit').click(function(){
		 $(location).attr('href','${pageContext.request.contextPath}/common/createproduct.form');		 
	});
	 
	var responseReceived = true;
	
	$('#submit-form').click(function(){	 
		if(responseReceived) {
			$('#tran-result-error-div').hide();
			$('#tran-result-success-div').hide();
			var formData =  $('#issueSkuForm').serialize();	 
			 $.ajax({
				    type: "POST",
				    url: "${pageContext.request.contextPath}/inventory/issue.form",
				    data: formData,
				    beforeSend: function() {
				    	responseReceived = false;		            
			        },
			        complete: function() {
			        	responseReceived = true;
			        },
				    success: function() {
				    	$('#tran-success').html("Transaction Successfull");
				    	$('#tran-result-success-div').show();
				    	$('#issueSkuForm')[0].reset();
				    },
				    error: function(xhr, status, error) {
				    	var x = xhr.responseText;
				    	var msg = $.trim(x);
				    	$('#tran-error').html(msg);
				    	$('#tran-result-error-div').show();
				    }
				  });
		} else {
			alert("Processing previous request. Please wait");
		}				
	 }); 
	
	});
	
	function processProductItemsResponse(data) {
		$('#tblTransactionForm >tbody').append(data);
	}
	


</script>
</head>
<body class="body-class" >	
	<form:form method="post" commandName="issueSkuForm" >
	<form:hidden path="transactionType" />
	<form:input type="hidden" path="locationId"/>
	<c:choose>
    	<c:when test='${issueSkuForm.transactionType.staffTransaction}'>
    		<form:hidden path="staff.staffId" />
    	</c:when>
    	 <c:otherwise>
    	 	<form:hidden path="trainee.traineeId" />
    	 </c:otherwise>
    </c:choose>
		
	<input type="hidden" id="selectedProductId" name="selectedProductId" value=""/>
	<div id="main-content" class="ui-widget main-content" style="background: white;">
	<%@ include file="/WEB-INF/ui/header.jsp" %>
	<div id="top-navigation" class="top-navigation">
	<%@ include file="/WEB-INF/ui/menu.jsp" %>
	</div>
	<br />
	<div style="clear: both;"></div>	
	
	<%@ include file="/WEB-INF/ui/transaction-result.jsp" %>
	
		<div id="heading12" class="ui-widget-header">Transaction Details</div>
		
		<div id="content" class="ui-widget-content" style="padding: 10px;">	
		
		<div id="create-trainee-div">
			<c:choose>
    			<c:when test='${issueSkuForm.transactionType.staffTransaction}'>
    			<a id="create-staff" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em;">Create New Staff</a>
    			</c:when>
		    	 <c:otherwise>
		    	 	<a id="create-trainee" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em;">Create New Trainee</a>
		    	 </c:otherwise>
   			 </c:choose>
		</div>
		
		<br/>    
		<table id="list2" class="trans-details"></table>
			<div id="pager2"></div>
		</div>
		
		  
		
		<div id="header-contents" class="ui-widget-content" align="left" style="padding: 10px;">
		
		<table id="transDetails" class="ui-widget item-table trans-details">				
			<tbody class="ui-widget-content trans-details" >
				<tr>
					<td>Transaction Description</td><td>${issueSkuForm.transactionDescription}</td>
				</tr>
				<tr>
					<td>Last Name</td><td><input type="text" name="lastName" value="" readOnly="true"/></td>
				</tr>
				<tr>
					<td>First Name</td><td><input type="text" name="firstName" value=""  readOnly="true" /></td>
				</tr>
				<tr>
					<td>MiddleName Name</td><td><input type="text" name="middleName" value="" readOnly="true" /></td>
				</tr>
				<c:choose>
					<c:when test='${issueSkuForm.transactionType.staffTransaction}'>
    				<tr>
						<td>Division</td><td><input type="text" name="division" value="" readOnly="true" /></td>
					</tr>
					<tr>
						<td>Extension</td><td><input type="text" name="extension" value="" readOnly="true" /></td>
					</tr>
    				</c:when>
    				<c:otherwise>
    				<tr>
						<td>Class</td><td><input type="text" name="classNumber" value="" readOnly="true" /></td>
					</tr>
    				</c:otherwise>
				</c:choose>
				
			</tbody>
		</table>					
		</div>
				

		<br />
		<div id="transactionDiv">
		<div id="heading" class="ui-widget-header">Inventory Details</div>
		<div style="padding: 10px;">
		
			<label class="ui-widget">
        		<span> Kit Name &nbsp;&nbsp;</span>
        		<input type="text" id="kitName" name="kitName" size="70"  />   
        		<a id="kit-add-btn" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Add</a>   
        		<a id="create-kit" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Create New Kit</a>                                
			</label>
			<br/>
			<br/>
			<label class="ui-widget">
        		<span> Item Name </span>
        		<input type="text" id="itemName" name="itemName" size="70" />   
        		<a id="item-add-btn" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Add</a>                                
			</label>
		</div>
		<div id="content" class="ui-widget-content" style="padding: 10px; ">	
			<table id="tblTransactionForm" class="ui-widget item-table">
				<thead class="ui-state-default item-table-header">
					<tr id="rowx">
						<th>Item</th>
						<th>Item Specification</th>
						<th>Quantity</th>
					</tr>				
				</thead>
				<tbody class="ui-widget-content" >
				<c:forEach items="${issueSkuForm.items}" var="item" varStatus="itemRow">
      			<tr>
     			<td style="width: 400px;">     
     		    <a id="plusgif" href="#" class="addR" tabindex="-1"><img src="<c:url value='/images/plus.gif' />" height="10px" width="10px" /></a>
      			&nbsp;			
     			<form:input type="hidden" path="itemSkus[${itemRow.index}].item.id" value="${item.id}"/>${item.desc}
     		 </td>
     		 
     		 <td style="width: 300px;">
     		 <c:forEach var="attribute" items="${item.attributeDetails}" varStatus="i">
     		 	${attribute.key.attributeName}
     		 	<form:hidden path="itemSkus[${itemRow.index}].itemAttributeDtls[${i.index}].itemAttribute.attibuteId" value="${attribute.key.attibuteId}" />
     		 	<form:select path="itemSkus[${itemRow.index}].itemAttributeDtls[${i.index}].itemAttributeValue.attributeValueId">
     					<c:forEach items="${attribute.value}" var="attributeValue">
     						<form:option value="${attributeValue.attributeValueId}">
     							${attributeValue.attributeValue}
     						</form:option>
     					</c:forEach>
     			</form:select>
     		 </c:forEach>     		     		 	
     		
     		</td>     		
     		<td><form:input type="text" path="itemSkus[${itemRow.index}].quantity" size="10" />
     		</tr>
     		</c:forEach>
			</tbody>
			</table>
			<div id="actions" align="center" class="actions">
					<a id="submit-form" href="#" class="form-button ui-state-default ui-corner-all" style="padding: .2em 1em; ">Issue</a> 
				</div>			
		</div>	
	
	</div>
	</div>
	<br>
	
	</form:form>
	
	
	
	 
</body>
</html>