<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>
<%@ page language="java" import="java.util.*,com.smartworks.invtmgmt.core.domain.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
  <title>Federal Law Enforcement Training Center</title>
  <style>
    .error { color: red; }
  </style> 
 <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />" />
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
 </head>



<body>

<div id="portal-header">

<a id="portal-logo" accesskey="1" href="http://www.fletc.gov">

    <img src="<c:url value='/images/logo.jpg' />" alt="" title="logo.gif" height="78" width="345"></a>



</div>
 
 
<div class="panel panel-b login">
<div class="panel-head">
<h2>Welcome to your FLETC Account</h2>
<p>Please enter your User Id and password to login.</p>
</div>
<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>

	<form name='f' action="<c:url value='/auth/j_spring_security_check' />"
		method='POST'>

	<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" />
				</td>
			</tr>
			<tr>
				<td colspan='2'>
				</td>
			</tr>
		</table>
	</form> 
	<div class="input checkbox"><a href="users/forgot">I forgot my password.</a></div>
</body>
</html>