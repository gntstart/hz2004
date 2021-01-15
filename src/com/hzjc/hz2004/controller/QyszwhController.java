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
@RequestMapping("/gl/xtkzgl/qyszwh")
public class QyszwhController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/xtkzgl/qyszwh";
	}
	

	@RequestMapping(value = { "/getQyszwhInfo" }, method = RequestMethod.POST)
	public ModelAndView getXxxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getQyszwhInfo(params));
	}
	@RequestMapping(value = { "/addQyszwh" }, method = RequestMethod.POST)
	public ModelAndView addQyszwh() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.addQyszwh(params));
	}
	@RequestMapping(value = { "/modifyQyszwh" }, method = RequestMethod.POST)
	public ModelAndView modifyQyszwh() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.modifyQyszwh(params));
	}
	@RequestMapping(value = { "/delQyszwh" }, method = RequestMethod.POST)
	public ModelAndView delQyszwh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.delQyszwh(params));
	}
	@RequestMapping(value = { "/resumeQyszwh" }, method = RequestMethod.POST)
	public ModelAndView resumeQyszwh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.resumeQyszwh(params));
	}
}
