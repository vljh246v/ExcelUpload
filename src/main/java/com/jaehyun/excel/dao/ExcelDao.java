package com.jaehyun.excel.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("excelDao")
public class ExcelDao extends AbstractDAO{
	public void insertExcel(String queryId, Map<String, Object> parameters) {
		insert(queryId, parameters);
	}

}
