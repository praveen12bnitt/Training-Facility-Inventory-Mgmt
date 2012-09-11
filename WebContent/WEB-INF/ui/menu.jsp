			<ul class="dropdown">
				<security:authorize access="hasRole('ROLE_WHSE')">
				<li >
					<a href="#">Warehouse</a>
					<ul class="sub_menu">						
						<li><a href='<c:url value="/inbound/receive.form"/>'>Receive Inventory from Vendor</a></li>						
						<li><a href='<c:url value="/inbound/outbound.form"/>'>Move Inventory to Vendor</a></li>
						<li><a href='<c:url value="/inbound/transfer.form"/>'>Transfer Inventory</a></li>
					</ul>
				</li>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_UNIFORM')">
				<li>
					<a href="#">Uniform</a>
					<ul class="sub_menu">
						<li><a href='<c:url value="/inventory/issue.form?transactionTypeEnum=ISSUE_UNIFORM_STUDENT&locationId=2" />'>Trainee Issue</a></li>
						<li><a href='<c:url value="/inventory/issue.form?transactionTypeEnum=ISSUE_UNIFORM_STAFF&locationId=2" />'>Staff Issue</a></li>
						<li><a href='<c:url value="/inventory/listopentrans-exchange.form?transactionTypeEnum=ISSUE_UNIFORM_STUDENT&userId=100&locationId=2&exchange=true" />'>Student Exchange</a></li>
						<li><a href='<c:url value="/inventory/listopentrans.form?transactionTypeEnum=ISSUE_UNIFORM_STUDENT&userId=100&locationId=2" />'>Trainee Return</a></li>
						<li><a href='<c:url value="/inventory/listopentrans.form?transactionTypeEnum=ISSUE_UNIFORM_STAFF&userId=100&locationId=2" />'>Staff Return</a></li>
						<li><a href='<c:url value="/inventory/receive-laundry.form?locationId=2" />'>Receive From Laundry</a></li>
						<li><a href='<c:url value="/inbound/transferToMW.form?locationId=2" />'>Transfer to Main Warehouse</a></li>
					</ul>
				</li>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_TEG')">
				<li >
					<a href="#">TEG</a>
					<ul class="sub_menu">
						<li><a href='<c:url value="/inventory/issue.form?transactionTypeEnum=ISSUE_EQUIPMENT_STUDENT&locationId=1" />'>Trainee Issue</a></li>
						<li><a href='<c:url value="/inventory/issue.form?transactionTypeEnum=ISSUE_EQUIPMENT_STAFF&locationId=1" />'>Staff Issue</a></li>
						<li><a href='<c:url value="/inventory/listopentrans.form?transactionTypeEnum=ISSUE_EQUIPMENT_STUDENT&userId=100&locationId=1" />'>Trainee Return</a></li>
						<li><a href='<c:url value="/inventory/listopentrans.form?transactionTypeEnum=ISSUE_EQUIPMENT_STAFF&userId=100&locationId=1" />'>Staff Return</a></li>
						<li><a href='<c:url value="/inventory/receive-laundry.form?locationId=1" />'>Receive From Laundry</a></li>
						<li><a href='<c:url value="/inbound/transferToMW.form?locationId=1" />'>Transfer to Main Warehouse</a></li>
					</ul>
				</li>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_GYM')">
				<li >
					<a href="#">Gym</a>
					<ul class="sub_menu">
						<li><a href='<c:url value="/inventory/issue.form?transactionTypeEnum=ISSUE_GYM_STUDENT&locationId=3" />'>Trainee Issue</a></li>
						<li><a href='<c:url value="/inventory/issue.form?transactionTypeEnum=ISSUE_GYM_STAFF&locationId=3" />'>Staff Issue</a></li>
						<li><a href='<c:url value="/inventory/listopentrans.form?transactionTypeEnum=ISSUE_GYM_STUDENT&userId=100&locationId=3" />'>Trainee Return</a></li>
						<li><a href='<c:url value="/inventory/listopentrans.form?transactionTypeEnum=ISSUE_GYM_STAFF&userId=100&locationId=3" />'>Staff Return</a></li>
						<li><a href='<c:url value="/inventory/receive-laundry.form?locationId=3" />'>Receive From Laundry</a></li>
						<li><a href='<c:url value="/inbound/transferToMW.form?locationId=3" />'>Transfer to Main Warehouse</a></li>
					</ul>
				</li>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_LAUNDRY')">
				<li >
					<a href="#">Laundry</a>
					<ul class="sub_menu">
						<li><a href='<c:url value="/newlaundry/create-load.form?laundryType=W" />'>Create New Washer Load</a></li>
						<li><a href='<c:url value="/newlaundry/create-load.form?laundryType=D" />'>Create New Dryer Load</a></li>
						<li><a href='<c:url value="/newlaundry/laundrylist.form?laundryType=W" />'>Washer List</a></li>
						<li><a href='<c:url value="/newlaundry/laundrylist.form?laundryType=D" />'>Dryer List</a></li>
					</ul>
				</li>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<li >
					<a href="#">Administration</a>
					<ul class="sub_menu">
						<li><a href='<c:url value="/kits/list-kit.form" />'>Kit Management</a></li>
						<li><a href='<c:url value="/common/list-all-trainee.form" />'>Trainee Management</a></li>
						<li><a href='<c:url value="/common/list-all-staff.form" />'>Staff Management</a></li>
						<li><a href='<c:url value="/common/list-all-user.form" />'>User Management</a></li>
						<li><a href='<c:url value="/class/class.form" />'>Class Management</a></li>
						<li><a href='<c:url value="/reports/filetransfer.form" />'>Sync Inventory from Excel</a></li>
						<li><a href='<c:url value="/Inventory/AdjustInventory.form" />'>Adjust Inventory</a></li>
					</ul>
					
				</li>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_REPORT')">
					<li >
					<a href="#">Report</a>
					<ul class="sub_menu">
						
						<li><a href='<c:url value="/reports/inventory-locn.form" />'>View Inventory Across Locations</a></li>
						<li><a href='<c:url value="/reports/filetransfer.form" />'>Inventory Inventory Report</a></li>
						<!-- <li><a href='<c:url value="/reports/inventory-all.form" />'>Inventory - Misc</a></li>	 -->								
						<li><a href='<c:url value="/reports/laundryreport-in.form" />'>Laundry Report</a></li>
						<li><a href='<c:url value="/reports/issue-in.form" />'>Issue/Return Report</a></li>
						<li><a href='<c:url value="/reports/exchange-in.form" />'>Exchange Report</a></li>
						</ul>
				</li>
				</security:authorize>
					<li >
					<a href="<c:url value='/j_spring_security_logout' />">Logout</a>
					</li>
			</ul>
			
			
