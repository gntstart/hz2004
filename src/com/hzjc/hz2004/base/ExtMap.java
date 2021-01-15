package com.hzjc.hz2004.base;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import com.hzjc.hz2004.util.ObjectUtil;

public class ExtMap<K,V> extends HashMap<K,V> {
	private static final long serialVersionUID = 1L;

	public String getString(String key){
		V value = this.get(key);
		if(value==null)
			return null;
		
		return ObjectUtil.getObjectToString(value);
	}
	
	public Long getLong(String key){
		V value = this.get(key);
		if(value==null)
			return null;
		
		return ObjectUtil.getObjectToLong(value);
	}
	
	public Integer getInteger(String key){
		V value = this.get(key);
		if(value==null)
			return null;
		
		return ObjectUtil.getObjectToInteger(value);
	}
	
	public Double getDouble(String key){
		V value = this.get(key);
		if(value==null)
			return null;
		
		return ObjectUtil.getObjectToDouble(value);
	}
	
	public Date getDate(String key){
		V value = this.get(key);
		if(value==null)
			return null;
		
		return ObjectUtil.getObjectToDate(value);
	}
	
	public Timestamp getTimestamp(String key){
		V value = this.get(key);
		if(value==null)
			return null;
		
		return ObjectUtil.getObjectToTimestamp(value);
	}
}
