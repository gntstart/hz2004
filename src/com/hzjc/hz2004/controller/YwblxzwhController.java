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
@RequestMapping("/gl/xtkzgl/ywblxzwh")
public class YwblxzwhController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/xtkzgl/ywblxzwh";
	}
	
	@RequestMapping(value = { "/getXtywbllxInfo" }, method = RequestMethod.POST)
	public ModelAndView getXtywbllxInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getXtywbllxInfo(params));
	}
	@RequestMapping(value = { "/getYwblxzwhInfo" }, method = RequestMethod.POST)
	public ModelAndView getXxxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getYwblxzwhInfo(params));
	}
	@RequestMapping(value = { "/addYwblxzwhInfo" }, method = RequestMethod.POST)
	public ModelAndView addYwblxzwhInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.addYwblxzwhInfo(params));
	}	
	@RequestMapping(value = { "/modifyYwblxzwhInfo" }, method = RequestMethod.POST)
	public ModelAndView modifyYwblxzwhInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.modifyYwblxzwhInfo(params));
	}
	@RequestMapping(value = { "/delYwblxzwhInfo" }, method = RequestMethod.POST)
	public ModelAndView delYwblxzwhInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.delYwblxzwhInfo(params));
	}
	@RequestMapping(value = { "/resumeYwblxzwhInfo" }, method = RequestMethod.POST)
	public ModelAndView resumeYwblxzwhInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.resumeYwblxzwhInfo(params));
	}
}
