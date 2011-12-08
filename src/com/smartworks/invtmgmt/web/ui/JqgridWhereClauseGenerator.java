package com.smartworks.invtmgmt.web.ui;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import com.smartworks.invtmgmt.core.domain.Trainee;

public class JqgridWhereClauseGenerator {

	public static String getWhereClause(HttpServletRequest requestObj, Class domainClass) {
		StringBuilder whereClause = new StringBuilder();
		Field[] fields = domainClass.getDeclaredFields();
		for (Field field : fields) {
			Class fieldType = field.getType();
			String fieldName = field.getName();
			String fieldSValue = requestObj.getParameter(fieldName);
			if(fieldSValue!= null) {
				if(fieldType.equals(String.class)) {
					whereClause.append(" AND "+fieldName+" LIKE '%"+fieldSValue+"%'");
				} else if(fieldType.equals(Integer.class)) {
					whereClause.append(" AND str("+fieldName+") LIKE '%"+fieldSValue+"%'");
				} else if(fieldType.equals(Boolean.class)) {
					if(Boolean.valueOf(fieldSValue))
						whereClause.append(" AND "+fieldName+" = true");
					else
						whereClause.append(" AND "+fieldName+" = false");
				}
			}
		}
		
		return whereClause.toString();
	}
	
	public static void main(String[] args) {
		Class clz = Trainee.class;
		Field[] fields = clz.getDeclaredFields();
		System.out.println(fields);
	}
}
