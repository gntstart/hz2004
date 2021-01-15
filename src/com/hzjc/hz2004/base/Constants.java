package com.hzjc.hz2004.base;

public interface Constants {
	/**
	 * 跟节点行政区划
	 */
	static final public String ROOT_XZQH = "_ROOT";
	static final public String ROOT_XZQH_GJ = "_ROOT_GJ";
	
	public class MEMCACHED_ATTR{
		static final public String SID_ATTR = "_SID_";
		static final public String DICT_SEARCH_ATTR = "_DICT_SEARCH_";
		static final public String DICT_SJPZB_ATTR = "_DICT_SJPZB_";
		static final public String DICT_REMOTEDICT_ATTR = "_DICT_REMOTEDICT_";
		static final public String DICT_SEARCH_ALL_ATTR = "_DICT_SEARCH_ALL";
	}
	
	public static final String CHARSET_UTF8 = "UTF-8";
	
	/**
	 * 身份标识在上下文中的名称
	 */
	static final public String COOKIE_SID = "memsid";
	
	/**
	 * 上下文的用户KEY
	 */
	static final public String USER_ATTRNAME = "_user";
	
	/**
	 * SID的有效期设置cookie有效期是一天
	 */
	static final public int cookieMaxAge = 60 * 60 * 24;
	
}
