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
@RequestMapping("/gl/jsgl")
public class JsglController extends BaseController{
	
	@Resource
	private QueryService queryService;
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/jsgl";
	}	
	
	@RequestMapping(value = { "/queryJs"}, method = RequestMethod.POST)
	public ModelAndView querytfbdcx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.queryJs(params));
	}
	@RequestMapping(value = { "/addJs"}, method = RequestMethod.POST)
	public ModelAndView addJs() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.addJs(params));
	} 
	@RequestMapping(value = { "/modifyJs"}, method = RequestMethod.POST)
	public ModelAndView modifyJs() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.modifyJs(params));
	} 
	@RequestMapping(value = { "/delJs"}, method = RequestMethod.POST)
	public ModelAndView delJs() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.delJs(params));
	}  
	
}
