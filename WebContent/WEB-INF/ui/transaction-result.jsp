<%@ include file="/WEB-INF/ui/commoninclude.jsp"%>
<div id="tran-result-error-div" class="ui-corner-all ui-state-error" >
	<p id="tran-error"></p>
</div>
	
<div id="tran-result-success-div" class="ui-corner-all ui-state-highlight">	
	<p id="tran-success"></p>
</div>	
<c:if test="${not empty validationErrors}">
<div class="ui-widget">
<div class="ui-corner-all ui-state-error">
<p>
<c:forEach var="error" items="${validationErrors}">      
<span class="ui-icon ui-icon-alert"
	style="float: left; margin-left: 1em;"></span> <strong>${error} </strong>			
</c:forEach>
</p>
</div>
</div> 
</c:if>

