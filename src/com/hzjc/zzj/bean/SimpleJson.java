package com.hzjc.zzj.bean;

import java.util.List;

/**
 * 通用的JSON对象
 * @author Administrator
 *
 */
public class SimpleJson  implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private boolean success;
	private String message;
	private List list = null;
	private int totalCount;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}