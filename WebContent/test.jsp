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

ItemMgr mgr = AppContextUtil.getBean("itemMgr");

out.println("Loading a single item<br/>");

Item i = mgr.getItem(new Integer(1));

String desc = i.getDesc();
int id = i.getId();
String name = i.getName();

out.println("id:"+id);
out.println(" name:"+name);
out.println(" desc:"+desc);

out.println("<br/>*****************************************************<br/>");

// Get the attribute details

Map<ItemAttribute, List<ItemAttributeValue>> itemAttrDetails = i.getAttributeDetails();

Set<ItemAttribute> attrs = itemAttrDetails.keySet();

for(ItemAttribute itemAttribute : attrs) {
	out.println("Attribute Name:"+itemAttribute.getAttributeName());
	List<ItemAttributeValue> values = itemAttrDetails.get(itemAttribute);	
	Collections.sort(values);
	out.println("<br/>Attribute Values:");
	for(ItemAttributeValue itemAttributeValue : values) {
		out.println(itemAttributeValue.getAttributeValue()+",");
	}	
	out.println("<br/>");
}

out.println("<br/><br/><br/>Loading all items based on transactions......<br/>");

List<Item> items = mgr.getItemsForTransaction(1);

for(Item item : items) {
	String desc1 = item.getDesc();
	int id1 = item.getId();
	String name1 = item.getName();

	out.println("id:"+id1);
	out.println(" name:"+name1);
	out.println(" desc:"+desc1);

	out.println("<br/>*****************************************************<br/>");

	// Get the attribute details

	itemAttrDetails = item.getAttributeDetails();

	attrs = itemAttrDetails.keySet();

	for(ItemAttribute itemAttribute : attrs) {
		out.println("Attribute Name:"+itemAttribute.getAttributeName());
		List<ItemAttributeValue> values = itemAttrDetails.get(itemAttribute);	
		Collections.sort(values);
		out.println("<br/>Attribute Values:");
		for(ItemAttributeValue itemAttributeValue : values) {
			out.println(itemAttributeValue.getAttributeValue()+",");
		}	
		out.println("<br/><br/>");
	}
}


%>
</body>
</html>