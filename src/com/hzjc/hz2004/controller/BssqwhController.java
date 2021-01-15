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
@RequestMapping("/gl/xtkzgl/bssqwh")
public class BssqwhController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/xtkzgl/bssqwh";
	}
	

	@RequestMapping(value = { "/getBssqwhInfo" }, method = RequestMethod.POST)
	public ModelAndView getXxxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getBssqwhInfo(params));
	}
	@RequestMapping(value = { "/addBssqwh" }, method = RequestMethod.POST)
	public ModelAndView addBssqwh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.addBssqwh(params));
	}
	@RequestMapping(value = { "/modifyBssqwh" }, method = RequestMethod.POST)
	public ModelAndView modifyBssqwh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.modifyBssqwh(params));
	}
	@RequestMapping(value = { "/delBssqwh" }, method = RequestMethod.POST)
	public ModelAndView delBssqwh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.delBssqwh(params));
	}
	@RequestMapping(value = { "/resumeBssqwh" }, method = RequestMethod.POST)
	public ModelAndView resumeBssqwh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.resumeBssqwh(params));
	}
}
