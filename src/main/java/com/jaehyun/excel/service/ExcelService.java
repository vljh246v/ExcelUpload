package com.jaehyun.excel.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.jaehyun.excel.common.ExcelPOIHelper;
import com.jaehyun.excel.dao.ExcelDao;
import com.jaehyun.excel.vo.MyCell;
import com.mysql.fabric.xmlrpc.base.Array;

@Service
public class ExcelService {
	

	private String fileLocation;
	private int maxRowLength;

	@Autowired
	private ExcelPOIHelper excelPOIHelper;
	
	@Autowired
	private ExcelDao excelDao;
	
	public Map<Integer, List<MyCell>> uploadFileAndInsertDB(Model model,  MultipartFile file) throws IOException {
		InputStream in = file.getInputStream();
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
		FileOutputStream f = new FileOutputStream(fileLocation);
		System.out.println(fileLocation);
		int ch = 0;
		while ((ch = in.read()) != -1) {
			f.write(ch);
		}
		f.flush();
		f.close();
		model.addAttribute("message", "File: " + file.getOriginalFilename() + " has been uploaded successfully!");
		Map<Integer, List<MyCell>> data = excelPOIHelper.readExcel(fileLocation);
		maxRowLength = chechEmptyCell(data);
		
		List<String> columns = new ArrayList<String>();
		
		
		for(int i= 0; i<data.size(); i++) {
			for(int j=0; j<data.get(i).size(); j++) {
				System.out.print(data.get(i).get(j).getContent());
				System.out.print(" ");
			}
			System.out.println();
		}
		
		for(int i= 0; i<data.size(); i++) {
			List<String> values = new ArrayList<String>();
			
			if(data.get(i).size() < maxRowLength) {
				while(data.get(i).size() < maxRowLength) {
					data.get(i).add(new MyCell(""));
				}
			}
				
			for(int j=0; j<data.get(i).size(); j++) {
				if(i == 0) {
					columns.add(data.get(i).get(j).getContent());
				}else {
					if(data.get(i).get(j).getContent() == "") {
						values.add("-");
					}else {
						values.add(data.get(i).get(j).getContent());
					}
				}
			}
			if(i == 0)
				continue;
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("KEY", columns);
			parameters.put("VALUE", values);
			excelDao.insertExcel("insertExcel", parameters);
		}
		
		
		return data;
	}
	
	private int chechEmptyCell(Map<Integer, List<MyCell>> data){
		
		int maxLength = 0;
		for(int i=0; i<data.size(); i++) {
			if(maxLength < data.get(i).size()) {
				maxLength = data.get(i).size();
			}
		}
		return maxLength;
	}
}
