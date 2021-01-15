package com.hzjc.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hzjc.hz2004.base.SystemConfig;
import com.hzjc.hz2004.bean.SQLCallBean;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.util.ObjectUtil;

public class DBLogUtils {
	private final static boolean isSaveLog = SystemConfig.getSystemConfig("isSaveLog", "true").equals("true")?true:false;
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(DBLogUtils.class);
	private static List<String> exclude_log_pojoList = new ArrayList<>();
	static {
		String[] strings = SystemConfig.getSystemConfig("exclude_log_pojo", "").split(",");
		for(int i=0;i<strings.length;i++) {
			if(CommonUtil.isNotEmpty(strings[i])) {
				exclude_log_pojoList.add(strings[i]);
			}
		}
	}
	
	/**
	 * 当前线程
	 */
	private static ThreadLocal<SQLCallBean> a = new ThreadLocal<SQLCallBean>();
	
	public static void reSet(String log_code) {
		a.set(null);
		
		SQLCallBean bean = new SQLCallBean();
		bean.setLog_code(log_code);
		a.set(bean);
	}
	
	public static void setNull() {
		a.set(null);
	}
	
	public static SQLCallBean getSQLCallBean() {
		if(!DBLogUtils.isSaveLog)
			return null;
		
		return a.get();
	}
	
	//获取错误详细信息
	static public String getStackTrace(Throwable cause){
		if(cause==null)
			return null;
		
		StackTraceElement[] se = cause.getStackTrace();
		StringBuffer msg = new StringBuffer();
		msg.append(cause.getMessage());

		if(se!=null && se.length>0){
			for(int i=0;i<se.length;i++){
				StackTraceElement e=se[i];
				String classname = e.getClassName();
				String filename = e.getFileName();
				String linenumber = String.valueOf(e.getLineNumber());
				String methodname = e.getMethodName();
				String moreMsg="";
				if(filename!=null && !"-1".equals(linenumber))
					moreMsg = "(" + filename + "第" + linenumber + "行)";
				
				msg.append("\r\n").append(classname).append(".").append(methodname).append(" ").append(moreMsg);
			}
		}
	
		return msg.toString();
	}
	
	static public void deleteObjectLog(Object obj){
		if(!isSaveLog)
			return;
		
		DBLogUtils.createLog("delete " + obj.getClass().getSimpleName() + ": " + JSONUtil.toJSON(obj), null);
	}
	
	static public void createOrUpdateObjectLog(Object obj){
		if(!isSaveLog)
			return;
		
		DBLogUtils.createLog("save " + obj.getClass().getSimpleName() + ": " + JSONUtil.toJSON(obj), null);
	}
	
	static public void createLog(String sql,Object[] params){
		if(!isSaveLog)
			return;
		
		if(sql==null || a.get()==null || CommonUtil.isEmpty(a.get().getLog_code()))
			return;
		
		//求和SQL不记录
		if(sql.indexOf("count")>0)
			return;
		
		for(int i=0;i<exclude_log_pojoList.size();i++){
			String tString = exclude_log_pojoList.get(i);
			if(sql.indexOf(tString)>=0)
				return;
		}
		
		String oldsqlString = sql;
		
		sql = sql.replace('\r', ' ').replace('\n', ' ').replace('\t', ' ').replaceAll("  ", "");
	
		int seek = sql.indexOf("/*");
		if(seek>=0)
			sql = sql.substring(seek+2);
		
		seek = sql.indexOf("*/");
		if(seek>0) {
			if(sql.indexOf("dynamic")>=0) {
				seek = oldsqlString.indexOf("*/");
				sql = oldsqlString.substring(seek+2);
			}else
				sql = sql.substring(0,seek);
		}
		
		sql = sql.trim();
		
		//2张系统日志表过滤
		if(sql.indexOf("PoXT_XTRZB")>=0)
			return;
		
		if(params!=null && params.length>0)
			sql = "SQL=" + sql + ", 参数[" + getParams(params) + "]";
		
		a.get().getSqlList().add(sql);
	}
	
	static public  String getParams(Object[] params){
		if(params==null)
			return "";
		
		String paramsString = "";
		for(int index=0;index<params.length;index++){
			Object obj = params[index];
			String value = ObjectUtil.getObjectToString(obj);
			
			if(paramsString.equals(""))
				paramsString = index + "=>" + value;
			else
				paramsString += "," + index + "=>" + value;
		}
		
		return paramsString;
	}
}
