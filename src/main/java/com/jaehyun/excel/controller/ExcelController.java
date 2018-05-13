package com.jaehyun.excel.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jaehyun.excel.common.ExcelPOIHelper;
import com.jaehyun.excel.service.ExcelService;
import com.jaehyun.excel.vo.MultipartFileExcel;
import com.jaehyun.excel.vo.MyCell;

@Controller
public class ExcelController {

	@Autowired
	private ExcelPOIHelper excelPOIHelper;
	
	@Autowired
	private ExcelService excelService;

	private String fileLocation;

	@RequestMapping(value = "/uploadExcelFile", method = RequestMethod.POST)
	public String uploadFileAndInsertDB(Model model,  @RequestParam("xlfile") MultipartFile file) throws IOException {

		return excelService.uploadFileAndInsertDB(model, file).toString();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/readPOI")
	public String readPOI(Model model) throws IOException {

		if (fileLocation != null) {
			if (fileLocation.endsWith(".xlsx") || fileLocation.endsWith(".xls")) {
				Map<Integer, List<MyCell>> data = excelPOIHelper.readExcel(fileLocation);
				model.addAttribute("data", data);
			} else {
				model.addAttribute("message", "Not a valid excel file!");
			}
		} else {
			model.addAttribute("message", "File missing! Please upload an excel file.");
		}
		return "excel";
	}
}
