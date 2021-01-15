package com.hzjc.hz2004.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hzjc.hz2004.base.bean.Code;
import com.hzjc.hz2004.base.bean.SysCode;
import com.hzjc.hz2004.util.DateHelper;



/**
 * 通用的JSON对象
 * @author Administrator
 *
 */
public class SimpleJson implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private boolean success = true;
	private Map<String,String> errors = null;
	private String result;
	private String message;
	private List<?> list = null;
	private int totalCount;
	private String dateStyle = DateHelper.EXT_PRINT_DATE_STYLE;
    private Object other;
    private Object other2;
    private Object other3;
	private List<Map<String,List<Code>>> dictList; 	//字典翻译记录和list对应，MAP[KEY=字段名，VALUE=选择值列表]
	private Map<String,List<SysCode>> dictMap = null;
	
	public List<Map<String, List<Code>>> getDictList() {
		return dictList;
	}

	public void setDictList(List<Map<String, List<Code>>> dictList) {
		this.dictList = dictList;
	}

	public void setDictMap(Map<String, List<SysCode>> dictMap) {
		this.dictMap = dictMap;
	}

	public Map<String, List<SysCode>> getDictMap() {
		return dictMap;
	}
	
	public Object getOther() {
		return other;
	}

	public void setOther(Object other) {
		this.other = other;
	}

	public Object getOther2() {
		return other2;
	}

	public void setOther2(Object other2) {
		this.other2 = other2;
	}

	public Object getOther3() {
		return other3;
	}

	public void setOther3(Object other3) {
		this.other3 = other3;
	}

	public void setEntity(Object entity){
		if(entity==null){
			this.setList(new ArrayList<Object>());
			this.totalCount = 0;
			return;
		}
		
		if(entity instanceof Page){
			Page page = (Page)entity;
			if(page.getList()!=null)
				this.setTotalCount(page.getTotalCount());
			
			this.setList(page.getList());
			return;
		}
		
		if(entity instanceof List){
			List<?> l = (List<?>)entity;
			this.setList(l);
			this.setTotalCount(l.size());
			return;
		}
		
		List<Object> l = new ArrayList<Object>();
		l.add(entity);
		this.setList(l);
		this.setTotalCount(1);
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public String getDateStyle() {
		return dateStyle;
	}

	public void setDateStyle(String dateStyle) {
		this.dateStyle = dateStyle;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<?> getList() {
		return list;
	}
	
	public void setList(List<?> list) {
		this.list = list;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public void addValidation(String pname,String error){
		if(pname==null || error==null) return;
		if(errors==null) errors = new HashMap<String,String>();
		success = false;
		errors.put(pname, error);
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
