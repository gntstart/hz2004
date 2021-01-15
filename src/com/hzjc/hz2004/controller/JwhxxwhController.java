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
@RequestMapping("/gl/xtmbgl/jwhxxwh")
public class JwhxxwhController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/xtmbgl/jwhxxwh";
	}
	

	@RequestMapping(value = { "/getJwhxxInfo" }, method = RequestMethod.POST)
	public ModelAndView getDwxxInfo() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getJwhxxInfo(params));
	}
	@RequestMapping(value = { "/modifyJwh" }, method = RequestMethod.POST)
	public ModelAndView modifyDwDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.modifyJwh(params));
	}
	
	@RequestMapping(value = { "/addJwh" }, method = RequestMethod.POST)
	public ModelAndView addDwDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.addJwh(params));
	}
	@RequestMapping(value = { "/delJwh" }, method = RequestMethod.POST)
	public ModelAndView delDwDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.delJwh(params));
	}
	
	@RequestMapping(value = { "/getJlxByJwhdm" }, method = RequestMethod.POST)
	public ModelAndView getJlxByJwhdm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getJlxByJwhdm(params));
	}
	@RequestMapping(value = { "/modifyjlx" }, method = RequestMethod.POST)
	public ModelAndView modifyjlx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.modifyjlx(params));
	}
	@RequestMapping(value = { "/addJlx" }, method = RequestMethod.POST)
	public ModelAndView addJlx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.addJlx(params));
	}
	@RequestMapping(value = { "/resumeJwh" }, method = RequestMethod.POST)
	public ModelAndView resumeJwh() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.resumeJwh(params));
	}	
	
}
