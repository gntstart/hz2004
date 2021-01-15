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
@RequestMapping("/yw/ydbz/dblkz")
public class DblkzController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/yw/ydbz/dblkz";
	}
	

	@RequestMapping(value = { "/getDblkzxxInfo" }, method = RequestMethod.POST)
	public ModelAndView getDblkzxxInfo() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getDblkzxxInfo(params));
	}
	@RequestMapping(value = { "/modifyDblkzxx" }, method = RequestMethod.POST)
	public ModelAndView modifyDblkzxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.modifyDblkzxx(params));
	}
	
	@RequestMapping(value = { "/addDblkzxx" }, method = RequestMethod.POST)
	public ModelAndView addDblkzxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.addDblkzxx(params));
	}
	@RequestMapping(value = { "/delDblkzxx" }, method = RequestMethod.POST)
	public ModelAndView delDblkzxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.delDblkzxx(params));
	}
	
}
