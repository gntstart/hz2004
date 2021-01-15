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
@RequestMapping("/gl/xtkzgl/slhxlbwh")
public class SlhxlbwhController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/xtkzgl/slhxlbwh";
	}
	

	@RequestMapping(value = { "/getSlhxlbwhInfo" }, method = RequestMethod.POST)
	public ModelAndView getXxxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getSlhxlbwhInfo(params));
	}
	@RequestMapping(value = { "/addSlhxlbwh" }, method = RequestMethod.POST)
	public ModelAndView addSlhxlbwh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.addSlhxlbwh(params));
	}
	@RequestMapping(value = { "/modifySlhxlbwh" }, method = RequestMethod.POST)
	public ModelAndView modifySlhxlbwh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.modifySlhxlbwh(params));
	}
	@RequestMapping(value = { "/delSlhxlbwh" }, method = RequestMethod.POST)
	public ModelAndView delSlhxlbwh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.delSlhxlbwh(params));
	}
}
