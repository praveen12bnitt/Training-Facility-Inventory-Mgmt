			<ul class="dropdown">
				<li >
					<a href="#">Home</a>
				</li>
				<li >
					<a href="#">Warehouse</a>
					<ul class="sub_menu">						
						<li><a href='<c:url value="/inbound/receive.form"/>'>Receive</a></li>						
						<li><a href='<c:url value="/inbound/outbound.form"/>'>Outbound</a></li>
						<li><a href='<c:url value="/inbound/transfer.form"/>'>Transfer Inventory</a></li>
						<li><a href='<c:url value="/inbound/transferToMW.form" />'>Transfer to WM</a></li>
					</ul>
				</li>
				<li >
					<a href="#">Uniform</a>
					<ul class="sub_menu">
						<li><a href='<c:url value="/inventory/issue.form?transactionTypeEnum=ISSUE_UNIFORM_STUDENT" />'>Issue</a></li>
						<li><a href='<c:url value="/inventory/listopentrans.form?transactionTypeEnum=ISSUE_UNIFORM_STUDENT&userId=100&locationId=2" />'>Return</a></li>
						<li><a href="#">Transfer Inventory</a></li>
											</ul>
				</li>
				<li >
					<a href="#">TEG</a>
					<ul class="sub_menu">
						<li><a href="#">Issue</a></li>
						<li><a href="#">Return</a></li>
						<li><a href="#">Transfer Inventory</a></li>
											</ul>
				</li>
				<li >
					<a href="#">Gym</a>
					<ul class="sub_menu">
						<li><a href="#">Issue</a></li>
						<li><a href="#">Return</a></li>
						<li><a href="#">Transfer Inventory</a></li>
											</ul>
				</li>
					<li >
					<a href="#">Laundry</a>
					<ul class="sub_menu">
						<li><a href='<c:url value="/laundry/list-laundry.form" />'>List Loads</a></li>
						<li><a href='<c:url value="/laundry/create-load.form" />'>Create New Load</a></li>
					</ul>
				</li>
					<li >
					<a href="#">&nbsp;Kit&nbsp;</a>
					<ul class="sub_menu">
						<li><a href='<c:url value="/common/createproduct.form" />'>Create</a></li>
						</ul>
				</li>
					<li >
					<a href="#">Report</a>
					<ul class="sub_menu">
						
						<li><a href="#">Location</a></li>
						<li><a href="#">Item Inventory</a></li>
						<li><a href='<c:url value="/reports/inventory-all.form" />'>Inventory - Misc</a></li>						
						</ul>
				</li>
					<li >
					<a href="<c:url value='/j_spring_security_logout' />">Logout</a>
					</li>				
			</ul>

