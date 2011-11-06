<div class="menu-container">
			<ul class="memu js-enabled">
				<li class="memu-root">
					<a href="#">Home</a>
				</li>
				<li class="memu-root">
					<a href="#">Warehouse</a>
					<ul>						
						<li><a href='<c:url value="/inbound/receive.form?transactionTypeId=" />'>Receive</a></li>
						<li><a href='<c:url value="/" />'>Outbound</a></li>
						<li><a href='<c:url value="/inbound/transfer.form?transactionTypeId=" />'>Transfer Inventory</a></li>
					</ul>
				</li>
				<li class="memu-root">
					<a href="#">Uniform</a>
					<ul>
						<li><a href='<c:url value="/inventory/issue.form?transactionTypeEnum=ISSUE_UNIFORM_STUDENT" />'>Issue</a></li>
						<li><a href='<c:url value="/inventory/listopentrans.form?transactionTypeEnum=ISSUE_UNIFORM_STUDENT&userId=100&locationId=2" />'>Return</a></li>
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
						<li><a href='<c:url value="/reports/inventory-all.form" />'>Inventory - Misc</a></li>						
						</ul>
				</li>
					<li class="memu-root">
					<a href="<c:url value='/j_spring_security_logout' />">Logout</a>
					</li>				
			</ul>
		</div>
