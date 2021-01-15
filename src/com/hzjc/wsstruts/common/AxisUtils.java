package com.hzjc.wsstruts.common;

import javax.servlet.http.*;
import org.apache.axis.*;
import org.apache.axis.transport.http.*;
import org.apache.commons.logging.*;

/**
 * Axis SOAP引擎公用类
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public class AxisUtils {
  //日志处理
  private static Log _log = LogFactory.getLog(AxisUtils.class);

  private AxisUtils() {
  }

  /**
   * 得到SOAP远程调用的请求Request
   * @return
   */
  public static HttpServletRequest getRequest() {
    MessageContext mc = MessageContext.getCurrentContext();
    HttpServletRequest request = (HttpServletRequest) mc.getProperty(
        HTTPConstants.MC_HTTP_SERVLETREQUEST);
    return request;
  }

  /**
   * 得到当前用户的HttpSession
   * @return
   */
  public static HttpSession getHttpSession() {
    HttpSession session = null;
    HttpServletRequest request = getRequest();
    if (request != null) {
      session = request.getSession();
    }
    return session;
  }

  /**
   * 得到远程客户端IP
   * @return
   */
  public static String getRemoteIP() {
    String strIp = null;
    try{
      HttpServletRequest request = getRequest();
      if (request != null) {
        strIp = getRequest().getRemoteAddr();
        _log.info("请求的远程IP地址：" + strIp);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return strIp;
  }

}
