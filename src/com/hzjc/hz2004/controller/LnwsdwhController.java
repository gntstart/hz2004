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
@RequestMapping("/gl/xtkzgl/lnwsdwh")
public class LnwsdwhController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/xtkzgl/lnwsdwh";
	}
	@RequestMapping(value = { "/getLnwsdwhInfo" }, method = RequestMethod.POST)
	public ModelAndView getXxxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getLnwsdwhInfo(params));
	}
	@RequestMapping(value = { "/addLnwsdwhInfo" }, method = RequestMethod.POST)
	public ModelAndView addLnwsdwhInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.addLnwsdwhInfo(params));
	}	
	@RequestMapping(value = { "/modifyLnwsdwhInfo" }, method = RequestMethod.POST)
	public ModelAndView modifyLnwsdwhInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.modifyLnwsdwhInfo(params));
	}	
	@RequestMapping(value = { "/delLnwsdwhInfo" }, method = RequestMethod.POST)
	public ModelAndView delLnwsdwhInfo() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.delLnwsdwhInfo(params));
	}	
}
