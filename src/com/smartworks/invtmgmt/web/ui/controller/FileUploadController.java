package com.smartworks.invtmgmt.web.ui.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/uploadfile")

public class FileUploadController {
	
	
	 
	 @Async
	 public void print() {
 		System.out.println("file name uploaded"); 
	 }

    @RequestMapping(method = RequestMethod.POST, value="/upload.form")
     public ModelAndView handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
    	final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
    	final Map<String, MultipartFile> files = multiRequest.getFileMap();
    	for (MultipartFile file : files.values()) {
    		System.out.println("file name uploaded::"+file.getName());
    		file.transferTo(new File(new File("d:/Hari/temp"), file.getOriginalFilename()));
    	}
    			
    	return new ModelAndView("/reports/filetransfer");
    }

    
    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
    	binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }
}
