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
@RequestMapping("/gl/xtmbgl/jwzrqxxwh")
public class JwzrqxxwhController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/xtmbgl/jwzrqxxwh";
	}
	

	@RequestMapping(value = { "/getJwzrqxxInfo" }, method = RequestMethod.POST)
	public ModelAndView getDwxxInfo() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getJwzrqxxInfo(params));
	}
	@RequestMapping(value = { "/modifyJwzrq" }, method = RequestMethod.POST)
	public ModelAndView modifyDwDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.modifyJwzrq(params));
	}
	
	@RequestMapping(value = { "/addJwzrq" }, method = RequestMethod.POST)
	public ModelAndView addDwDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.addJwzrq(params));
	}
	@RequestMapping(value = { "/delJwzrq" }, method = RequestMethod.POST)
	public ModelAndView delDwDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.delJwzrq(params));
	}
	@RequestMapping(value = { "/resumeJwzrq" }, method = RequestMethod.POST)
	public ModelAndView resumeJwzrq() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.resumeJwzrq(params));
	}
	
}
