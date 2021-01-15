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
@RequestMapping("/gl/xtkzgl/splwh")
public class SplwhController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/xtkzgl/splwh";
	}
	

	@RequestMapping(value = { "/getSpmbInfo" }, method = RequestMethod.POST)
	public ModelAndView getXxxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getSpmbInfo(params));
	}
	@RequestMapping(value = { "/getSpdzInfo" }, method = RequestMethod.POST)
	public ModelAndView getSpdzInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getSpdzInfo(params));
	}
	@RequestMapping(value = { "/getMbsplcInfo" }, method = RequestMethod.POST)
	public ModelAndView getMbsplcInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getMbsplcInfo(params));
	}
	@RequestMapping(value = { "/addSpmb" }, method = RequestMethod.POST)
	public ModelAndView addSpmb() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.addSpmb(params));
	}
	@RequestMapping(value = { "/modifySpmb" }, method = RequestMethod.POST)
	public ModelAndView modifySpmb() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.modifySpmb(params));
	}
	@RequestMapping(value = { "/resumeSpmb" }, method = RequestMethod.POST)
	public ModelAndView resumeSpmb() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.resumeSpmb(params));
	}
	@RequestMapping(value = { "/forbitSpmb" }, method = RequestMethod.POST)
	public ModelAndView forbitSpmb() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.forbitSpmb(params));
	}
	@RequestMapping(value = { "/addSpdzl" }, method = RequestMethod.POST)
	public ModelAndView addSpdzl() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.addSpdzl(params));
	}
	@RequestMapping(value = { "/removeSpdzl" }, method = RequestMethod.POST)
	public ModelAndView removeSpdzl() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.removeSpdzl(params));
	}	
	@RequestMapping(value = { "/removeAllSpdzl" }, method = RequestMethod.POST)
	public ModelAndView removeAllSpdzl() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.removeAllSpdzl(params));
	}
}
