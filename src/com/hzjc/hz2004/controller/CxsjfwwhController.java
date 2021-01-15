package com.hzjc.hz2004.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/gl/xtkzgl/cxsjfwwh")
public class CxsjfwwhController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/xtkzgl/cxsjfwwh";
	}
	@RequestMapping(value = { "/getYwqxInfo" }, method = RequestMethod.POST)
	public ModelAndView getXxxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getYwqxInfo(params));
	}
	@RequestMapping(value = { "/modifyYwqxInfo" }, method = RequestMethod.POST)
	public ModelAndView modifyYwqxInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.modifyYwqxInfo(params));
	}
	@RequestMapping(value = { "/getUserInfo" }, method = RequestMethod.POST)
	public ModelAndView getUserInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getUserInfo(params));
	}
	@RequestMapping(value = { "/getYhsjfwInfo" }, method = RequestMethod.POST)
	public ModelAndView getYhsjfwInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getYhsjfwInfo(params));
	}
	@RequestMapping(value = { "/addYhsjfwInfo" }, method = RequestMethod.POST)
	public ModelAndView addYhsjfwInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.addYhsjfwInfo(params));
	}
	@RequestMapping(value = { "/delYhsjfwInfo" }, method = RequestMethod.POST)
	public ModelAndView modifyYhsjfwInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.delYhsjfwInfo(params));
	}
	@RequestMapping(value = { "/getYhdtqxInfo" }, method = RequestMethod.POST)
	public ModelAndView getYhdtqxInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getYhdtqxInfo(params));
	}
	@RequestMapping(value = { "/addYhdtqxInfo" }, method = RequestMethod.POST)
	public ModelAndView addYhdtqxInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.addYhdtqxInfo(params));
	}
	@RequestMapping(value = { "/delYhdtqxInfo" }, method = RequestMethod.POST)
	public ModelAndView delYhdtqxInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.delYhdtqxInfo(params));
	}
}
