package com.jaehyun.excel.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jaehyun.excel.HomeController;

public class AbstractDAO {

	private static final Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

	@Autowired
	private SqlSessionTemplate sqlSession;

	protected void printQueryId(String queryId) {
		if (logger.isDebugEnabled()) {
			logger.debug("\t QueryId  \t:  " + queryId);
		}
	}

	public Object insert(String queryId, Map<String, Object> parameters) {
		printQueryId(queryId);
		return sqlSession.insert(queryId, parameters);
	}
}
