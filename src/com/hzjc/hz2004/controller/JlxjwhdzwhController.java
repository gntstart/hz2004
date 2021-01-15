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
@RequestMapping("/gl/xtmbgl/jlxjwhdzwh")
public class JlxjwhdzwhController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/xtmbgl/jlxjwhdzwh";
	}
	

	@RequestMapping(value = { "/getJlxjwhdzInfo" }, method = RequestMethod.POST)
	public ModelAndView getDwxxInfo() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getJlxjwhdzInfo(params));
	}
	@RequestMapping(value = { "/modifyJlxjwhdz" }, method = RequestMethod.POST)
	public ModelAndView modifyDwDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.modifyJlxjwhdz(params));
	}
	
	@RequestMapping(value = { "/addJlxjwhdz" }, method = RequestMethod.POST)
	public ModelAndView addDwDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.addJlxjwhdz(params));
	}
	@RequestMapping(value = { "/delJlxjwhdz" }, method = RequestMethod.POST)
	public ModelAndView delDwDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.delJlxjwhdz(params));
	}
	@RequestMapping(value = { "/resumeJlxjwhdz" }, method = RequestMethod.POST)
	public ModelAndView resumeJlxjwhdz() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.resumeJlxjwhdz(params));
	}
	
}
