package com.smartworks.invtmgmt.core.util;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class VelocityTemplateUtil {
	static VelocityEngine ve;
	static {
		ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		ve.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
		ve.init();		
	}

	public static VelocityEngine getVelocityEngine() {
		return ve;
	}

	public static String getData(VelocityContext context, Template t) {
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}

}