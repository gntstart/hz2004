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
@RequestMapping("/gl/xtkzgl/ggyxipset")
public class GgyxipsetController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/xtkzgl/ggyxipset";
	}
	

	@RequestMapping(value = { "/getGgyxipsetInfo" }, method = RequestMethod.POST)
	public ModelAndView getXxxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getGgyxipsetInfo(params));
	}
	@RequestMapping(value = { "/addGgyxip" }, method = RequestMethod.POST)
	public ModelAndView addGgyxip() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.addGgyxip(params));
	}
	@RequestMapping(value = { "/modifyGgyxip" }, method = RequestMethod.POST)
	public ModelAndView modifyGgyxip() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.modifyGgyxip(params));
	}
	@RequestMapping(value = { "/delGgyxip" }, method = RequestMethod.POST)
	public ModelAndView delGgyxip() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.delGgyxip(params));
	}	
}
