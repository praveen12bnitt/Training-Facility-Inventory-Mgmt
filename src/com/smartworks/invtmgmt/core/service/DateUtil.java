package com.smartworks.invtmgmt.core.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	
	static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	public static String getExpandedTimeStamp(Timestamp timeStamp) {		
		if(timeStamp != null)
			return sdf.format(timeStamp);
		else
			return "";
	}
	
	public static Date getDate(String date) {
		try {
			return dateFormat.parse(date);
		} catch(ParseException parseEx) {
			return new Date(System.currentTimeMillis());
		}
		
	}
	
}
