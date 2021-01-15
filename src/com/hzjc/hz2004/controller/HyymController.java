package com.hzjc.hz2004.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;

@Controller
@RequestMapping("/gl/hyym")
public class HyymController extends BaseController{
	
	@Resource
	private QueryService queryService;
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/hyym";
	}	
	
	@RequestMapping(value = { "/queryZmxx" }, method = RequestMethod.GET)
	public String querySbxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return "/gl/zmxx/" + params.getString("goto");//gl\zmxx\queryBdcxx.jsp
	}	
	@RequestMapping(value = { "/getZmxxxx" }, method = RequestMethod.POST)
	public ModelAndView getSbXxxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.queryZmXx(params));
	}
}
