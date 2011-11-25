package com.smartworks.invtmgmt.web.ui.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ValidationUtil {

	public static List<String> getErrorMsgs(MessageSource msgSrc , BindingResult result) {
		List<String> errorMsgs = new ArrayList<String>();
		for (ObjectError object : result.getAllErrors()) {
			String m  = msgSrc.getMessage(object, null);
			errorMsgs.add(m);
		}		
		return errorMsgs;
	}
}
