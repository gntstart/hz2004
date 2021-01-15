package com.hzjc.hz2004.controller;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.SimpleJson;
import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.exception.ServiceException;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.ExceptionUtil;
import com.hzjc.wsstruts.KDSActionProxy;

/**
 * 为了兼容老版本的跨地区反馈消息
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/util/other/service")
public class KDQQYController extends BaseController{
	@RequestMapping(value = { "/queryConfig"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView queryConfig() {
		return toJson(KDSActionProxy.APP_CONFIG_JSON);
	}
	
	/**
	 * 通用调用
	 * @return
	 */
	@RequestMapping(value = { "/", "", "run" }, method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView index() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String serviceName = params.getString("serviceName");
		String methodName = params.getString("methodName");
		
		serviceName = serviceName.substring(0,1).toLowerCase() + serviceName.substring(1);
		
		Object service = null;
		try{
			service = SpringContextHolder.getBean(serviceName);
		}catch(Exception e){
			serviceName = serviceName + "Impl";
			service = SpringContextHolder.getBean(serviceName);
		}
		
		Method m = null;
		try {
            m = service.getClass().getDeclaredMethod(methodName,
                new Class[] {Map.class, SimpleJson.class});
          }
          catch (Exception e) {
            throw new RuntimeException(serviceName + "的方法" + methodName +
                                       "(Map,SimpleJson)没有定义！");
          }

          if (m == null)
            throw new ServiceException("指定的方法不存在！");
          
          SimpleJson sj = new SimpleJson();
          sj.setSuccess(true);
          
          try{
	          Object obj = m.invoke(service, new Object[] {params, sj});

	          if (obj != null && obj instanceof List)
	            sj.setList( (List<?>) obj);
	          else
	            sj.setOther(obj);
          }catch(Exception e){
        	  sj.setSuccess(false);
        	  sj.setMessage(ExceptionUtil.getExceptionStrace(e));
          }

		return toJsonObject(sj);
	}
}
