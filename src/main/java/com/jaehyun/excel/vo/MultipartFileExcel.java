package com.jaehyun.excel.vo;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileExcel {
	private String fname;
	
	    private MultipartFile uploadFile;
	
	    public MultipartFileExcel() {
	
	    }
	
	    public String getFname() {
	
	        return fname;
	
	    }
	
	    public void setFname(String fname) {
	
	        this.fname = fname;
	
	    }
	
	    public MultipartFile getuploadFile() {
	
	        return uploadFile;
	
	    }
	
	    public void setuploadFile(MultipartFile uploadFile) {
	
	        this.uploadFile = uploadFile;
	
	    }
	
}

