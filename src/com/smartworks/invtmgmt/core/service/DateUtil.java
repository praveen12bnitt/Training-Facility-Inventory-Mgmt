package com.smartworks.invtmgmt.core.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtil {

	static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	
	public static String getExpandedTimeStamp(Timestamp timeStamp) {		
		if(timeStamp != null)
			return sdf.format(timeStamp);
		else
			return "";
	}
	
}
