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
@RequestMapping("/cx/spcx/hjbgspcx")
public class HjbgspcxController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/cx/spcx/hjbgspcxMain";
	}
	
	@RequestMapping(value = { "/getQrsp" }, method = RequestMethod.POST)
	public ModelAndView getQrsp() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.queryQrsp(params));
	}
	
	@RequestMapping(value = { "/getQrspzb" }, method = RequestMethod.POST)
	public ModelAndView getQrspzb() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.queryQrspzb(params));
	}
	
	@RequestMapping(value = { "/getQrsplc" }, method = RequestMethod.POST)
	public ModelAndView getQrsplc() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.queryQrsplc(params));
	}
	
}
