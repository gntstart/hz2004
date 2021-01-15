package com.hzjc.hz2004.base.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.hzjc.hz2004.po.PoXT_SJPZB;

public class SjpzBean  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Map<String,List<PoXT_SJPZB>> pzMap;
	private Map<String,List<SysCode>> dictMap;
	
	public Map<String, List<PoXT_SJPZB>> getPzMap() {
		return pzMap;
	}
	public void setPzMap(Map<String, List<PoXT_SJPZB>> pzMap) {
		this.pzMap = pzMap;
	}
	public Map<String, List<SysCode>> getDictMap() {
		return dictMap;
	}
	public void setDictMap(Map<String, List<SysCode>> dictMap) {
		this.dictMap = dictMap;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
