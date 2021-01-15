package com.hzjc.hz2004.bean;

import java.util.ArrayList;
import java.util.List;

public class SQLCallBean {
	private List<String> sqlList = new ArrayList<String>();
	private String error;
	private String log_code = null; //日志类型
	
	public String getLog_code() {
		return log_code;
	}

	public void setLog_code(String log_code) {
		this.log_code = log_code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<String> getSqlList() {
		return sqlList;
	}

	public void setSqlList(List<String> sqlList) {
		this.sqlList = sqlList;
	}
}
