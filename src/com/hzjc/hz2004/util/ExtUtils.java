package com.hzjc.hz2004.util;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hzjc.hz2004.base.login.LoginFilter;

public class ExtUtils {
	static public final String pageSize = "pageSize";
	static public final String pageIndex = "pageIndex";
	static public final String data = "data";
	
	/**
	 * 处理何处来、何处去
	 * @param map
	 */
	static public void initHclHcq(Map<String,Object> map,String names){
		if(names==null)
			names = "hcl,hcq";
		
		String[] n = names.split(",");
		for(int i=0;i<n.length;i++){
			String code = (String)map.get(n[i]);
			if(CommonUtil.isNotEmpty(code)){
				if(code.endsWith("0000"))
					code = code.substring(0,2);
				else if(code.endsWith("00"))
					code = code.substring(0,4);
				
				map.put(n[i], code);
			}
		}
	}
	
	static public String getMapJsonData(Map<String,String> data){
		Gson gson = new GsonBuilder().serializeNulls().create();
		String str = gson.toJson(data,new TypeToken<Map<String,String>>(){}.getType());
		return str;
	}
	
	static public String getJsonData(Object src,String dateFormat){
		Gson gson = new GsonBuilder().serializeNulls().setDateFormat(dateFormat).create();
		return gson.toJson(src);
	}
	
	static public String getJsonData(Object src){
		return getJsonData(src,"yyyy-MM-dd");
	}
	
	static public Map<String,Object> getAttributeParames(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		for(java.util.Enumeration<?> e = request.getAttributeNames();e.hasMoreElements();){
			String key = (String)e.nextElement();
			Object value = request.getAttribute(key);
			map.put(key, value);
		}
		//((org.apache.struts.action.ActionMessages)map.get("org.apache.struts.action.ACTION_MESSAGE")).get().next()
		return map;
	}
	
	static public Map<String,Object> getRequestParames(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		for(java.util.Enumeration<?> e = request.getParameterNames();e.hasMoreElements();){
			String key = (String)e.nextElement();
			String value = request.getParameter(key);
			if(CommonUtil.isEmpty(value)) continue;
		
			value = value.trim();
			
			map.put(key, value);
		}
		
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		int size,index;
		if(CommonUtil.isNotEmpty(start)&& CommonUtil.isNotEmpty(limit) && !limit.equals("0")){
			size = Integer.parseInt(limit);
			index = Integer.parseInt(start)/size + 1;
		}else{
			size = 20;
			index = 1;
		}
		
		map.put(pageSize,size+"");
		map.put(pageIndex,index+"");
		
		return map;
	}
	
	static public Map<String,String> getRequestParamesString(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		for(java.util.Enumeration<?> e = request.getParameterNames();e.hasMoreElements();){
			String key = (String)e.nextElement();
			String value = request.getParameter(key);
			if(CommonUtil.isEmpty(value)) continue;
		
			value = value.trim();
			
			map.put(key, value);
		}
		
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		int size,index;
		if(CommonUtil.isNotEmpty(start)&& CommonUtil.isNotEmpty(limit) && !limit.equals("0")){
			size = Integer.parseInt(limit);
			index = Integer.parseInt(start)/size + 1;
		}else{
			size = 20;
			index = 1;
		}
		
		map.put(pageSize,size+"");
		map.put(pageIndex,index+"");
		
		return map;
	}
	
	static public Map<String,String> urlDecode(String parm){
		Map<String,String> map = new HashMap<String,String>();
		if(CommonUtil.isEmpty(parm))
			return map;
		
		String[] s = parm.split("&");
		for(int i=0;i<s.length;i++){
			String[] s2 = s[i].split("=");
			if(s2.length==2){
				map.put(s2[0],s2[1]);
			}
		}
		return map;
	}
	
	static public String urlEncode(Map<String,Object> param){
		if(param==null || param.size()==0)
			return "";
		
		String re = "";
		for(String key:param.keySet()){
			if(re.equals("")){
				re += "&";
			}
			
			try{
				re += key + "=" + java.net.URLEncoder.encode(param.get(key).toString(),LoginFilter.CharSet);
			}catch(Exception e){
				re += key + "=" + param.get(key);
			}
		}
		return re;
	}
}
