package com.hzjc.hz2004.base.login;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hzjc.hz2004.base.Constants;

public class BaseContext {
	static public String SESSION_USER_ID = "sessionid";

	private static ThreadLocal<BaseContext> a  = new ThreadLocal<BaseContext>();
	private static ThreadLocal<AuthToken> u  = new ThreadLocal<AuthToken>();
	
	static synchronized public void setCurrentContext(
			HttpServletRequest request,HttpServletResponse response) 
	{
		BaseContext c = getContext();
		if (c == null) {
			c = new BaseContext(request, response);
			a.set(c);
			u.set(null);
		} else {
			c.setRequest(request);
			c.setResponse(response);
			u.set(null);
		}
	}
	
	public static BaseContext getContext() {
		return (BaseContext) a.get();
	}

	public static void clearUser() {
		u.set(null);
	}
	
	public static void setUser(AuthToken user) {
		u.set(user);
	}
	
	public static AuthToken getUser() {
		return getBaseUser();
	}
	
	public static void unregisterContext() {
		a.set(null);
		u.set(null);
	}
	
	private HttpServletRequest request;
	private HttpServletResponse response;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	private BaseContext(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public static AuthToken getBaseUser(){
		try{
			//上下文
			AuthToken us = (AuthToken)BaseContext.getContext().getRequest().getAttribute(Constants.USER_ATTRNAME);
			
			//本地缓存
			if(us==null)
				us = u.get();
			
			//服务器上取
			if(us==null){
				us= HSession.getBaseUser();
				if(us!=null){
					//压入线程缓存
					setUser(us);
				}
			}
			
			return us;
		}catch(Exception e){
				return null;
		}
	}
	
	public static String getUserCode() {
		AuthToken u = getBaseUser();
		if(u==null)
			return null;
		
		return u.getUser().getYhdlm();
	}

	public Object getSessionAttribute(String name) {
		if(request==null || request.getSession()==null)
			return null;
		
		return request.getSession().getAttribute(name);
	}

	public void setSessionAttribute(String name, Object obj) {
		request.getSession().setAttribute(name, obj);
	}

	public void removeSessionAttribute(String name) {
		request.getSession().removeAttribute(name);
	}

    public ServletContext getServletContext() {
        HttpSession session = this.getSession();
        return session.getServletContext();
    }

    public HttpSession getSession() {
        return request.getSession();
    }
    
    public Object getContextAttribute(String attr) {
        ServletContext sc = this.getServletContext();
        return sc.getAttribute(attr);
    }

    public void setContextAttribute(String attr, Object value) {
        ServletContext sc = this.getServletContext();
        sc.setAttribute(attr, value);
    }

    public Object getRequestAttribute(String attr) {
        return request.getAttribute(attr);
    }

    public void setRequestAttribute(String attr, Object value) {
        request.setAttribute(attr, value);
    }
}