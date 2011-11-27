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
						<li><a href='<c:url value="/inventory/issue.form?transactionTypeEnum=ISSUE_UNIFORM_STUDENT&locationId=2" />'>Student Issue</a></li>
						<li><a href='#'>Staff Issue</a></li>
						<li><a href='<c:url value="/inventory/listopentrans.form?transactionTypeEnum=ISSUE_UNIFORM_STUDENT&userId=100&locationId=2" />'>Student Return</a></li>
						<li><a href='#'>Staff Return</a></li>
						<li><a href='<c:url value="/inventory/receive-laundry.form?locationId=2" />'>Receive From Laundry</a></li>
					</ul>
				</li>
				<li >
					<a href="#">TEG</a>
					<ul class="sub_menu">
						<li><a href='<c:url value="/inventory/issue.form?transactionTypeEnum=ISSUE_EQUIPMENT_STUDENT&locationId=1" />'>Student Issue</a></li>
						<li><a href='#'>Staff Issue</a></li>
						<li><a href='<c:url value="/inventory/listopentrans.form?transactionTypeEnum=ISSUE_EQUIPMENT_STUDENT&userId=100&locationId=1" />'>Student Return</a></li>
						<li><a href='#'>Staff Return</a></li>
						<li><a href='<c:url value="/inventory/receive-laundry.form?locationId=1" />'>Receive From Laundry</a></li>
					</ul>
				</li>
				<li >
					<a href="#">Gym</a>
					<ul class="sub_menu">
						<li><a href='<c:url value="/inventory/issue.form?transactionTypeEnum=ISSUE_GYM_STUDENT&locationId=3" />'>Student Issue</a></li>
						<li><a href='#'>Staff Issue</a></li>
						<li><a href='<c:url value="/inventory/listopentrans.form?transactionTypeEnum=ISSUE_GYM_STUDENT&userId=100&locationId=3" />'>Student Return</a></li>
						<li><a href='#'>Staff Return</a></li>
						<li><a href='<c:url value="/inventory/receive-laundry.form?locationId=3" />'>Receive From Laundry</a></li>
					</ul>
				</li>
					<li >
					<a href="#">Laundry</a>
					<ul class="sub_menu">
						<li><a href='<c:url value="/laundry/list-laundry.form?type=open" />'>List Loads</a></li>
						<li><a href='<c:url value="/laundry/create-load.form" />'>Create New Load</a></li>
						<li><a href='<c:url value="/laundry/list-laundry.form" />'>List All Loads</a></li>
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

