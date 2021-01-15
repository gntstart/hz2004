package com.hzjc.menu;

public class MenuData implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String url;
	private String code;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
