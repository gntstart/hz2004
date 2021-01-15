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
@RequestMapping("/gl/zxyhgl")
public class ZxyhglController extends BaseController{
	
	@Resource
	private QueryService queryService;
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/zxyhgl";
	}	
	
	@RequestMapping(value = { "/queryZxyh"}, method = RequestMethod.POST)
	public ModelAndView querytfbdcx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.queryZxyh(params));
	}
	
	@RequestMapping(value = { "/delYhhhid"}, method = RequestMethod.POST)
	public ModelAndView delYhhhid() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.delYhhhid(params));
	}  
	
	@RequestMapping(value = { "/fsxx" }, method = RequestMethod.POST)
	public ModelAndView fsxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.fsxx(params));
	}
}
