package com.smartworks.platform;

import org.springframework.context.ApplicationContext;

public class AppContextUtil {

	public static <T> T getBean(String beanName) {
		return (T) AppContextHolder.getAppCtx().getBean(beanName);
	}
	
	public static <T> T getBean(Class beanClass) {
		return (T) AppContextHolder.getAppCtx().getBean(beanClass);
	}
	
	public static ApplicationContext getApplicationContext() {
		return AppContextHolder.getAppCtx();
	}
}
