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
@RequestMapping("/gl/yhgl")
public class YhglController extends BaseController{
	
	@Resource
	private QueryService queryService;
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/yhgl";
	}	
	
	@RequestMapping(value = { "/queryYh"}, method = RequestMethod.POST)
	public ModelAndView querytfbdcx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.queryYh(params));
	}
	@RequestMapping(value = { "/addYh"}, method = RequestMethod.POST)
	public ModelAndView addYh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.addYh(params));
	} 
	@RequestMapping(value = { "/modifyYh"}, method = RequestMethod.POST)
	public ModelAndView modifyYh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.modifyYh(params));
	} 
	@RequestMapping(value = { "/delYh"}, method = RequestMethod.POST)
	public ModelAndView delYh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.delYh(params));
	}  	
	@RequestMapping(value = { "/getXtyhjsxx"}, method = RequestMethod.POST)
	public ModelAndView getXtyhjsxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getXtyhjsxx(params));
	}  	
	@RequestMapping(value = { "/getXtyhsjfwxx"}, method = RequestMethod.POST)
	public ModelAndView getXtyhsjfwxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getXtyhsjfwxx(params));
	} 	
	@RequestMapping(value = { "/getXtyhdtqxxx"}, method = RequestMethod.POST)
	public ModelAndView getXtyhdtqxxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getXtyhdtqxxx(params));
	} 	
	@RequestMapping(value = { "/getXtyhdzqxxx"}, method = RequestMethod.POST)
	public ModelAndView getXtyhdzqxxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getXtyhdzqxxx(params));
	}
	@RequestMapping(value = { "/addYhdzqx"}, method = RequestMethod.POST)
	public ModelAndView addYhdzqx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.addYhdzqx(params));
	} 
	@RequestMapping(value = { "/delYhdzqxInfo"}, method = RequestMethod.POST)
	public ModelAndView delYhdzqxInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.delYhdzqxInfo(params));
	}
}
