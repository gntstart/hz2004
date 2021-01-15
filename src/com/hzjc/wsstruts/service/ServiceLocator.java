/* Generated by Together */

package com.hzjc.wsstruts.service;

import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.wsstruts.common.*;
import org.apache.commons.logging.*;
import com.hzjc.wsstruts.exception.*;
/**
 * 得到业务接口定位类
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */
public class ServiceLocator {
  //日志处理
  protected static Log _log = LogFactory.getLog(ServiceLocator.class);
  private static String SERVICE_DEFAULTSERVICE = "DefaultService";

  //=========================================================================
  //add by kgb 2004-12-08
  private static String SERVICE_WSCONTROLLER = "WSController";
  private static String SERVICE_WSLOGIN = "WSLogin";

  /**
   * 保护构造子，避免被实例化
   */
  protected ServiceLocator() {

  }

  public Object getService(String service_name){
	  if(service_name==null)
		  return null;
	  
	  return SpringContextHolder.getBean(service_name);
  }
}
