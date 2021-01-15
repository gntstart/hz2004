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
@RequestMapping("/gl/xtkzgl/spdzwh")
public class SpdzwhController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/xtkzgl/spdzwh";
	}
	@RequestMapping(value = { "/getSpdzInfo" }, method = RequestMethod.POST)
	public ModelAndView getSpdzInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getSpdzInfo(params));
	}
	@RequestMapping(value = { "/addSpdz" }, method = RequestMethod.POST)
	public ModelAndView addSpdz() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.addSpdz(params));
	}
	@RequestMapping(value = { "/modifySpdz" }, method = RequestMethod.POST)
	public ModelAndView modifySpmb() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.modifySpdz(params));
	}
	@RequestMapping(value = { "/deleteSpzd" }, method = RequestMethod.POST)
	public ModelAndView deleteSpzd() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.deleteSpzd(params));
	}
	@RequestMapping(value = { "/resumeSpdz" }, method = RequestMethod.POST)
	public ModelAndView resumeSpmb() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.resumeSpdz(params));
	}
}
