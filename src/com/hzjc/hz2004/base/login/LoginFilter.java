package com.hzjc.hz2004.base.login;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.hzjc.hz2004.base.Constants;
import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.base.SystemConfig;
import com.hzjc.hz2004.bean.SQLCallBean;
import com.hzjc.hz2004.service.CommonService;
import com.hzjc.hz2004.service.LoginService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.ExceptionUtil;
import com.hzjc.util.DBLogUtils;
import com.hzjc.util.StringUtils;
import com.hzjc.wsstruts.KDSActionProxy;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@WebFilter(filterName="loginFilter",urlPatterns={"/yw/*","/login/*", "/*"})
public class LoginFilter implements Filter {
	public static String loginFlag = "sessionid";
	public static String CharSet = "UTF-8";
	
	static Logger logger = Logger.getLogger(LoginFilter.class);

	public void init(FilterConfig arg0) throws ServletException {
		logger.info("初始化过滤器...");
	}
	
	public void destroy() {
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		// 如果是多级代理，那么取第一个ip为客户ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteHost();
		}
		return ip;
	}
	
	/**
	 * 判断需要跳转的URL是不是本地地址
	 * 
	 * @param request
	 * @param gotourl
	 * @return
	 */
	public static boolean isLocationURL(HttpServletRequest request,
			String gotourl) {
		if ("".equals(gotourl))
			return true;

		String baseURL = getBaseURL(request) + request.getContextPath();
		return gotourl.startsWith(baseURL);
	}

	/**
	 * 获取一个不带上下文的请求地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getJSP(HttpServletRequest request) {
		String jsp = request.getRequestURI();
		String contextPath = request.getContextPath();
		if ("/".equals(contextPath))
			contextPath = "";

		if (!"".equals(contextPath)) {
			int seek = jsp.indexOf(contextPath);
			if (seek == 0)
				jsp = jsp.substring(contextPath.length());
		}
		return jsp;
	}

	/**
	 * 获取一个基本URL
	 * 
	 * @param request
	 * @return
	 */
	public static String getBaseURL(HttpServletRequest request) {
		int port = request.getServerPort();
		String url = "";
		if (port == 80) {
			url = request.getScheme() + "://" + request.getServerName();
		} else {
			url = request.getScheme() + "://" + request.getServerName() + ":"
					+ request.getServerPort();
		}

		return url;
	}

	/**
	 * 获取一个完整的URL
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestURL(HttpServletRequest request) {
		String url = getBaseURL(request) + request.getRequestURI();

		Map<String,?> map = request.getParameterMap();
		if (map != null && map.size() > 0) {
			url += "?";
			for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
				String name = (String) it.next();
				String value = ((String[]) map.get(name))[0];
				// sessionid过滤
				if (name.equals(loginFlag) || name.equals("errmsg"))
					continue;

				url += name + "=" + value + "&";
			}
		}
		return url;
	}
	
	public String getParameter(HttpServletRequest httpRequest,String name){
		String value = httpRequest.getParameter(name);
		if(value==null || "".equals(value))
			value = (String)httpRequest.getAttribute(name);
		
		return value;
	}

	public static String getRequestPath(HttpServletRequest request) {
		String jsp = request.getRequestURI();
		String contextPath = request.getContextPath();
		if ("/".equals(contextPath))
			contextPath = "";

		if (!"".equals(contextPath)) {
			int seek = jsp.indexOf(contextPath);
			if (seek == 0)
				jsp = jsp.substring(contextPath.length());
		}
		return jsp;
	}
	
	public String getParamsByISO(ServletRequest request, String name){
		String str = null;
		try{
			String value = request.getParameter(name);
			if(value==null)
				return value;
			
			str = new String(value.getBytes("iso-8859-1"),"UTF-8");
		}catch(Exception e){
			;
		}
		
		return str;
	}
	
	static private String contextPath = null;
	static public String getContextPath() {
		//无所谓锁
		if(contextPath!=null)
			return contextPath;
		
		HttpServletRequest request = BaseContext.getContext().getRequest();
		String path = request.getContextPath();
		if(path.endsWith("/"))
			path = contextPath.substring(0,contextPath.length()-1);
		
		contextPath = path;
		
		return path;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest httpRequest = (HttpServletRequest) (request);
		HttpServletResponse httpResponse = (HttpServletResponse) (response);
		String contentType = request.getContentType();
		String log_code = httpRequest.getParameter("log_code");
		try {
			httpRequest.setCharacterEncoding(CharSet);
			httpResponse.setCharacterEncoding(CharSet);
		} catch (Exception e) {
			logger.info("设置输出字符集失败，encoding=" + CharSet, e);
		}finally{
			BaseContext.setCurrentContext(httpRequest, httpResponse);
			DBLogUtils.reSet(log_code);
		}

		String strCzkssj = StringUtils.formateDateTime();
		long l1 = System.currentTimeMillis();
		
		AuthToken user = null;
		try {
			String contextPath = getContextPath();
			String uri = httpRequest.getRequestURI();
			long time1 = System.currentTimeMillis();
			boolean isjsp = !(uri.startsWith(getContextPath() + "/static/") || uri.startsWith(getContextPath() +"/js")
					|| uri.startsWith(getContextPath() +"/images/") || uri.startsWith(getContextPath() +"/css/")
					|| uri.startsWith(getContextPath() +"/login/") || uri.startsWith(getContextPath() +"/services/")
					|| uri.startsWith(getContextPath() +"/gl/hyym")|| uri.startsWith(getContextPath() +"/echarts/echartsZlNew")
					|| uri.startsWith(getContextPath() +"/yw/common/img/dwrender")||uri.startsWith(getContextPath() +"/gl/ggUrlTrans")
					|| uri.startsWith(getContextPath() +"/yw/common/uploadZp")|| uri.startsWith(getContextPath() +"/yw/common/uploadQdZp")
					|| uri.startsWith(getContextPath() +"/gl/xtmbgl/dwxxwh/addDwDm")|| uri.startsWith(getContextPath() +"/gl/xtmbgl/dwxxwh/modifyDwDm"));
//			boolean isjsp = !(!uri.startsWith(getContextPath() + "/zptest/")||uri.startsWith(getContextPath() + "/static/") || uri.startsWith(getContextPath() +"/js")
//							|| uri.startsWith(getContextPath() +"/images/") || uri.startsWith(getContextPath() +"/css/") || uri.startsWith(getContextPath() +"/login/"));
//
			if (isjsp) {
				//优先第三方登录
				String tokey = httpRequest.getParameter("tokey");
				String yhdlm = httpRequest.getParameter("yhdlm");
				String dqbm = httpRequest.getParameter("dqbm");
				
				String autoLogin = httpRequest.getParameter("autoLogin");
				
				if(CommonUtil.isNotEmpty(tokey) || (autoLogin!=null && "1".equals(autoLogin))){
					if("1".equals(autoLogin)){
						tokey = CommonUtil.getTokey();
						yhdlm = "HZADMIN";
						dqbm = KDSActionProxy.APP_CONFIG_JSON.getString("kdsqyLocalDQBM");
					}
					
					//完成自动登录
					LoginService loginService = (LoginService)SpringContextHolder.getBean("loginService");
					try{
						if(CommonUtil.isEmpty(yhdlm) || CommonUtil.isEmpty(dqbm)){
							throw new RuntimeException("必须提供yhdlm和dqbm参数！");
						}
						
						//参数yhdlm在GET方式提交之前，必须做2次urlencode
						yhdlm = java.net.URLDecoder.decode(yhdlm, "UTF-8");
						
						//校验
						String TokeyMD5 = CommonUtil.getTokey();
						
						if(!TokeyMD5.equals(tokey)) {
							//口令不正确
							throw new RuntimeException("tokey错误！");
						}
						
						user = loginService.loginAuthToken(yhdlm, dqbm);
					}catch(Exception e){
						response.setContentType("text/html;charset=UTF-8");
						response.getWriter().print(ExceptionUtil.getMessage(e));
						response.getWriter().close();
						loginService.logout();
						return;
					}
					
					//自动登录，额外需要做的动作，写入cookies
					if(user.getUser().getYhdlm().equalsIgnoreCase("hzadmin")){
						user.setAdmin(true);
			    	}
			    	HSession.saveUser(user);
				}else{
					//非自动登录，按普通登录流程
					user = BaseContext.getBaseUser();
				}
				
				if(user!=null){
					BaseContext.setUser(user);
					request.setAttribute(Constants.USER_ATTRNAME, user);
				}
				
				//如果用户登录超时，并且是ajax请求(JsonStore查询请求)
				String method = httpRequest.getParameter("method");
				if(method==null)
					method = "";
	
				//如果已经登录，哪么返回
				if(user!=null){
					chain.doFilter(request, response);
					System.out.println(uri + "\t" + (System.currentTimeMillis()-time1)  + "毫秒");
					return;
				}
				
				/**
				 * 如果用户没有登陆，或者需要重新登陆 如果上下文中的sessionid参数不为空，那么表示需要本地重新登陆
				 * 如果上下文中的sessionid参数为空，并且session也没有该对象，那么表示需要到统一登陆页面登录
				 */
				String urlpath = getJSP(httpRequest);
				if (user==null
						&& !SystemConfig.exclude_url.contains(urlpath) && !urlpath.startsWith("/druid/")) {
	
					String url = getRequestURL(httpRequest);
					String errmsg = "";
	
					url = java.net.URLEncoder.encode(url, "UTF-8");
					String loginurl = SystemConfig.getSystemConfig("login_url","/login");
					if(!loginurl.startsWith("http://")){
						loginurl = getContextPath() + loginurl;
					}
					
					if(loginurl.indexOf("?")<0)
						loginurl += "?";
					else
						loginurl += "&";
					
					url = loginurl + "gotourl="
							+ url + "&errmsg="
							+ java.net.URLEncoder.encode(errmsg, "UTF-8") + "&_dc=" + new Date().getTime();
					
					httpResponse.sendRedirect(url);
					return;
				}
			}
			//说明是文件上传
			if(null != contentType && contentType.contains("multipart/form-data")){

				CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
				MultipartHttpServletRequest resolveMultipart = commonsMultipartResolver.resolveMultipart(httpRequest);
				boolean b = true;
				Map<String, MultipartFile> fileMap = resolveMultipart.getFileMap();
				for (String key : fileMap.keySet()) {
					MultipartFile multipartFile = fileMap.get(key);
					InputStream is = multipartFile.getInputStream();
					//你的过滤逻辑
				}
				if(b){
					chain.doFilter(resolveMultipart, response);
					return;
				}
			}
	
			chain.doFilter(request, response);
			System.out.println(uri + "\t" + (System.currentTimeMillis()-time1)  + "毫秒");
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			//保存日志
			SQLCallBean b = DBLogUtils.getSQLCallBean();
			if(user!=null && user.getUser()!=null && user.getOrganize()!=null && b!=null && CommonUtil.isNotEmpty(b.getLog_code())) {
				String logcode = b.getLog_code();
				
				String str = "";
				for(int index=0;index<b.getSqlList().size();index++) {
					String sqlString = b.getSqlList().get(index);
					String tmpString =sqlString.toLowerCase();
					
					//简化SQL，将字段列表去掉
					int seek = tmpString.indexOf("select");
					int seek2 = tmpString.indexOf("from");
					if(seek>=0 && seek2>0 && seek2>seek) {
						sqlString = sqlString.substring(0,seek+6) + " * " + sqlString.substring(seek2);
					}
					str += index + "：" + sqlString + "\n";
				}
				DBLogUtils.setNull();

				if(str!=null && !str.equals("")) {
					CommonService commonService = (CommonService)SpringContextHolder.getBean("commonService");
					if(commonService!=null)
						commonService.saveRzxx(user, logcode, str, l1, strCzkssj);
				}
			}else {
				DBLogUtils.setNull();
			}
		}
	}
}
