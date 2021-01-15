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
@RequestMapping("/gl/xtmbgl/xzqhwh")
public class XzqhwhController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/xtmbgl/xzqhwh";
	}
	

	@RequestMapping(value = { "/getXzqhInfo" }, method = RequestMethod.POST)
	public ModelAndView getXzqhInfo() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getXzqhInfo(params));
	}
	@RequestMapping(value = { "/modifyXzqhDm" }, method = RequestMethod.POST)
	public ModelAndView modifyXzqhDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.modifyXzqhDm(params));
	}
	
	@RequestMapping(value = { "/addXzqhDm" }, method = RequestMethod.POST)
	public ModelAndView addXzqhDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.addXzqhDm(params));
	}
	@RequestMapping(value = { "/delXzqhDm" }, method = RequestMethod.POST)
	public ModelAndView delXzqhDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.delXzqhDm(params));
	}
	@RequestMapping(value = { "/resumeXzqhDm" }, method = RequestMethod.POST)
	public ModelAndView resumeXzqhDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.resumeXzqhDm(params));
	}
}
