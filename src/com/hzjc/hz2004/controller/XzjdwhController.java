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
@RequestMapping("/gl/xtmbgl/xzjdwh")
public class XzjdwhController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/xtmbgl/xzjdwh";
	}
	

	@RequestMapping(value = { "/getXzjdInfo" }, method = RequestMethod.POST)
	public ModelAndView getDwxxInfo() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getXzjdInfo(params));
	}
	@RequestMapping(value = { "/modifyXzjd" }, method = RequestMethod.POST)
	public ModelAndView modifyDwDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.modifyXzjd(params));
	}
	
	@RequestMapping(value = { "/addXzjd" }, method = RequestMethod.POST)
	public ModelAndView addDwDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.addXzjd(params));
	}
	@RequestMapping(value = { "/delXzjd" }, method = RequestMethod.POST)
	public ModelAndView delDwDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.delXzjd(params));
	}
	@RequestMapping(value = { "/resumeXzjd" }, method = RequestMethod.POST)
	public ModelAndView resumeXzjd() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.resumeXzjd(params));
	}
	
}
