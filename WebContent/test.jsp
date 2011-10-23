<%@page import="com.smartworks.invtmgmt.core.manager.ItemMgr"%>
<%@page import="com.smartworks.invtmgmt.core.domain.Item"%>
<%@page import="com.smartworks.invtmgmt.core.dao.ItemDao"%>
<%@page import="com.smartworks.test.TestBean"%>
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
TestBean bean = AppContextUtil.getBean("testBean");
out.println(bean.sayHello());

ItemMgr mgr = AppContextUtil.getBean("itemMgr");
Item i = mgr.getItem(new Integer(1));

String desc = i.getDesc();
int id = i.getId();
String name = i.getName();

out.println("id:"+id);
out.println("name:"+name);
out.println("desc:"+desc);


%>
</body>
</html>