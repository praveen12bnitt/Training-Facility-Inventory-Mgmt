	<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/memu-0.1.css" />

<div id="portal-header">

<a id="portal-logo" accesskey="1" href="http://www.fletc.gov">

    <img src="<c:url value='/images/logo.jpg' />" alt="" title="logo.gif" height="78" width="345"></a>
</div>

		<div class="menu-container">
			<ul class="memu js-enabled">
				<li class="memu-root">
					<a href="#">Home</a>
					</li>
				<li class="memu-root">
					<a href="#">Warehouse</a>
					<ul>						
						<li><a href="${pageContext.request.contextPath}/inbound/receive.form?transactionTypeId="><div class="memu-icon sprite-folder"></div>Receive</a></li>
						<li><a href="${pageContext.request.contextPath}/"><div class="memu-icon sprite-folder"></div>Outbound</a></li>
						<li><a href="${pageContext.request.contextPath}/inbound/transfer.form?transactionTypeId="><div class="memu-icon sprite-disk"></div>Transfer Inventory</a></li>
					</ul>
				</li>
				<li class="memu-root">
					<a href="#">Uniform</a>
					<ul>
						<li><a href="#">Issue</a></li>
						<li><a href="#">Return</a></li>
						<li><a href="#">Transfer Inventory</a></li>
											</ul>
				</li>
				<li class="memu-root">
					<a href="#">TEG</a>
					<ul>
						<li><a href="#">Issue</a></li>
						<li><a href="#">Return</a></li>
						<li><a href="#">Transfer Inventory</a></li>
											</ul>
				</li>
				<li class="memu-root">
					<a href="#">Gym</a>
					<ul>
						<li><a href="#">Issue</a></li>
						<li><a href="#">Return</a></li>
						<li><a href="#">Transfer Inventory</a></li>
											</ul>
				</li>
					<li class="memu-root">
					<a href="#">Laundry</a>
					<ul>
						<li><a href="#">Issue</a></li>
						<li><a href="#">Return</a></li>
						<li><a href="#">Transfer Inventory</a></li>
											</ul>
				</li>
					<li class="memu-root">
					<a href="#">Report</a>
					<ul>
						
						<li><a href="#">Location</a></li>
						<li><a href="#">Item Inventory</a></li>
						<li><a href="${pageContext.request.contextPath}/reports/inventory-all.form">Inventory - Misc</a></li>						
						</ul>
				</li>
					<li class="memu-root">
					<a href="<c:url value='/j_spring_security_logout' />">Logout</a>
					</li>				
			</ul>
		</div>
	<br />	
	<br />
	<div ></div>
		<script type="text/javascript" src="http://www.google.com/jsapi"></script>
		
		<script type="text/javascript">
		  google.load("jquery", "1.4.4");
		  google.setOnLoadCallback(function() {
			$('.js-enabled').memu({ 
				icon: {
					inset: true,
					margin: {
						top: 4,
						right: 10
					}
				},
				width: 250,
				rootWidth: 100,
				height: 25
			});
		  });
		</script>
		<script src="${pageContext.request.contextPath}/js/jquery/jquery.memu-0.1.min.js" type="text/javascript"></script>
		
	