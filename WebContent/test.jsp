<%@page import="com.smartworks.invtmgmt.core.manager.HistoricDataService"%>
<%@page import="com.smartworks.invtmgmt.core.dao.impl.ProductDaoImpl"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Set"%>
<%@page import="com.smartworks.invtmgmt.core.domain.ItemAttributeValue"%>
<%@page import="java.util.List"%>
<%@page import="com.smartworks.invtmgmt.core.domain.ItemAttribute"%>
<%@page import="java.util.Map"%>
<%@page import="com.smartworks.invtmgmt.core.manager.ItemMgr"%>
<%@page import="com.smartworks.invtmgmt.core.domain.Item"%>
<%@page import="com.smartworks.invtmgmt.core.dao.ItemDao"%>

<%@page import="com.smartworks.platform.AppContextUtil"%>
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

HistoricDataService hService = AppContextUtil.getBean(HistoricDataService.class); 
hService.importHistoricIssues("C:/all-issues.xlsx");

%>
</body>
</html>