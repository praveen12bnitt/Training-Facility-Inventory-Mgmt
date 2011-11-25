<%@ include file="/WEB-INF/ui/commoninclude.jsp"%>
<c:if test="${not empty exception}">
	<div class="ui-widget">
		<div class="ui-corner-all ui-state-error">
			<p>
				<span class="ui-icon ui-icon-alert"
					style="float: left; margin-left: 1em;"></span> <strong>Error
					: </strong>${exception.message}
			</p>
		</div>
	</div>
	<br />
</c:if>
<c:if test="${not empty success}">
	<div class="ui-corner-all ui-state-highlight">
		<p>
			<span class="ui-icon ui-icon-info"
				style="float: left; margin-left: 1em;"></span> <strong>Success
			</strong> Transaction successful
			<script type="text/javascript">
				$('#tblTransactionForm :input').attr("disabled", true);
			</script>
		</p>
	</div>
	<br />
</c:if>
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

