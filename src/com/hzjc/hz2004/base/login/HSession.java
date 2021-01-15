package com.hzjc.hz2004.base.login;

import java.io.Serializable;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzjc.hz2004.base.Constants;
import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.base.SystemConfig;
import com.hzjc.hz2004.exception.ActionException;
import com.hzjc.hz2004.exception.ServiceException;
import com.hzjc.hz2004.service.LoginService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.MemcachedUtil;

/**
 * 回话工具类，封装
 * @author ting_it
 *
 */
public class HSession {
	//保存cookie时的域
    private final static String domain = SystemConfig.getSystemConfig("COOKIES.DOMAIN","127.0.0.1");
    //设置cookie有效期是两个星期
    private final static int cookieMaxAge = 60 * 60 * 24 * 7 * 2;   
    
	static public boolean saveUser(AuthToken user){
		String sid = HSession.getSID();
		user.setSid(sid);
		return setSessionInfo(user);
	}
	
	static public String getSID(){
		HttpServletRequest request = BaseContext.getContext().getRequest();
		HttpServletResponse response = BaseContext.getContext().getResponse();
		
		String sid = request.getParameter(Constants.COOKIE_SID);
		if(CommonUtil.isEmpty(sid)){
			sid = (String)request.getAttribute(Constants.COOKIE_SID);
			if(CommonUtil.isEmpty(sid)){
				//没有，从cookies里面取
				Cookie cookies[] = request.getCookies();
		    	 if(cookies!=null){
		             for(int i=0;i<cookies.length;i++){
		                    if (Constants.COOKIE_SID.equals(cookies[i].getName())) {
		                           sid = cookies[i].getValue();
		                           break;
		                    }
		             }
		    	 }
		    	 
		    	 if(CommonUtil.isEmpty(sid)){
		     		 sid = HSession.createSID();
		     		 Cookie c1 = new Cookie(Constants.COOKIE_SID, sid);
		     		 c1.setPath(LoginFilter.getContextPath());
		     		if(!domain.equals("localhost"))
						c1.setDomain(domain);
					//c1.setMaxAge(cookieMaxAge); 
		     		 response.addCookie(c1);
		     		 request.setAttribute(Constants.COOKIE_SID, sid);
		    	 }
			}
		}
		
    	 return sid;
	}
	
	/**
	 * 创建一个会话随机数SID
	 * @return
	 */
	static public String createSID(){
		String sid = Constants.MEMCACHED_ATTR.SID_ATTR + UUID.randomUUID().toString() + "." + (System.currentTimeMillis());
		return sid;
	}
	
	/**
	 * 获取当前用户信息
	 * @param sid
	 * @return
	 */
	static public AuthToken getCurrentBaseUser(){
		AuthToken u = BaseContext.getBaseUser();
		if(u!=null)
			return u;

		return getBaseUser(null);
	}
	
	static public AuthToken getBaseUser(){
		return getBaseUser(getSID());
	}
	
	/**
	 * 获取指定用户信息
	 * @param sid
	 * @return
	 */
	static public AuthToken getBaseUser(String sid){
		if(CommonUtil.isEmpty(sid))
			sid = getSID();
		
		return getSessionInfo(sid);
	}
	
	/**
	 * 向当前会话中写入键值对
	 * @param key
	 * @param value
	 * @return
	 */
	static public boolean setSessionValue(String sid,  String key,  Serializable value){
		AuthToken session = getSessionInfo(sid);
		if(session==null)
			return false;
		
		session.getSessionMap().put(key, value);
		
		return setSessionInfo(session);
	}
	
	static public String getUserSessionKey(String sid){
		String key = "session:user:" + sid;
		return key;
	}
	
	/**
	 * 向当前会话中写入会话对象
	 * @param session
	 * @return
	 */
	static public boolean setSessionInfo(AuthToken session){
		String key = getUserSessionKey(session.getSid());
		
		boolean b = MemcachedUtil.set(
				key, 
				session,
				Integer.parseInt(SystemConfig.getSystemConfig("session_timeout", "30")));
		
		if(!b)
			throw new ActionException("缓存失败，请检查！");

		BaseContext.clearUser();
		BaseContext.setUser(session);
		
		return b;
	}
	
	/**
	 * 依据用户登录名，获取缓存登录对象
	 * @param yhdlm
	 * @return
	 */
	static public AuthToken getSessionInfoByYhdlm(String yhdlm, String dqbm){
		String key = "_sid_" + yhdlm;
		String sid = (String)MemcachedUtil.get(key);
		if(sid!=null){
			Object obj = MemcachedUtil.get(sid);
			if(obj instanceof AuthToken)
				return (AuthToken)obj;
		}
		
		//为空，那么强制登录
		LoginService loginService = (LoginService)SpringContextHolder.getBean("loginService");
		AuthToken user = loginService.loginAuthToken(yhdlm, dqbm);
		if(user.getUser().getYhdlm().equalsIgnoreCase("hzadmin")){
			user.setAdmin(true);
    	}
    	HSession.saveUser(user);
    	
		return user;
	}
	
	/**
	 * 获取SESSION会话的所有KEY/VALUE值
	 * @param sid
	 * @return
	 */
	static public AuthToken getSessionInfo(String sid){
		String key = getUserSessionKey(sid);
		
		if(sid==null)
			return null;
		
		AuthToken info = (AuthToken)MemcachedUtil.get(key);
		
		if(info==null)
			return null;
		
		//回写新的生命期，防止过期
		boolean b = MemcachedUtil.set(key, info,
				Integer.parseInt(SystemConfig.getSystemConfig("session_timeout", "30")));
		
		if(!b)
			throw new ActionException("缓存失败，请检查！");
		
		//写入身份标识
		info.setSid(sid);
		
		return info;
	}
	
	/**
	 * 检查是否在规定的时间内，重复提交某业务
	 * @param ywlx			业务类型，任何字符串
	 * @param timeout		禁止重复提交的时间延迟，单位：秒
	 */
	static public void checkRepeat(String ywlx, int timeout){
		String key = "checkyw." + HSession.getSID() + "." + ywlx;
		boolean success = MemcachedUtil.addS(key, "1",  timeout);
		if(!success)
			throw new ServiceException("重复提交业务，被取消！");
	}
}
