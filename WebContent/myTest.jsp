<%@page import="com.smartworks.platform.AppContextUtil"%>
<%@page import="com.smartworks.invtmgmt.core.service.DataTransferService"%>
<%@page import="com.pal.test.TestClass"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%

//TestClass test = new TestClass();
//test.testTransLoading();

//test.testTransLocation();

//test.getItemsForTransactions();

//test.getItemsWithoutAttributes();

//test.getItemWithoutAttributes();

//test.receiveInventory();

//test.transferInventory();

//test.processInvChange();

//test.processInvChange1();

//test.getTransactionForUser();

//test.getTransDetails();

//test.processReturs();
//test.processRetursWithMissing();

//test.reportMissingItem();

//out.println(test.getUITransactionDetails());

//test.itemLikeQuerySearch();


DataTransferService dataTransferService = AppContextUtil.getBean("dataTransferService");
dataTransferService.downloadIntentory(4);



%>
</body>
</html>