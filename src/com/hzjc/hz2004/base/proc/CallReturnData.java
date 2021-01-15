package com.hzjc.hz2004.base.proc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CallReturnData implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Map<String,String> data = new HashMap<String,String>();
	private Map<String, List<?>> resultmap = new HashMap<String, List<?>>();
	private Map<String,String> tableschema = new HashMap<String,String>();
	
	//特殊情况下，存放临时数据
	private Object tmp = null;
	
	public Object getTmp() {
		return tmp;
	}
	public void setTmp(Object tmp) {
		this.tmp = tmp;
	}
	public Map<String, List<?>> getResultmap() {
		return resultmap;
	}
	public void setResultmap(Map<String, List<?>> resultmap) {
		this.resultmap = resultmap;
	}

	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	public Map<String, String> getTableschema() {
		return tableschema;
	}
	public void setTableschema(Map<String, String> tableschema) {
		this.tableschema = tableschema;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
