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
@RequestMapping("/yw/fjgl/qxzjjs")
public class QxzjjsController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/yw/fjgl/qxzjjs";
	}
	
	/**
	 * 查询详细信息
	 * @return
	 */
	@RequestMapping(value = { "/getQxzjjsInfo" }, method = RequestMethod.POST)
	public ModelAndView getXxxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getQxzjjsInfo(params));
	}
	@RequestMapping(value = { "/saveQxzjjsInfo" }, method = RequestMethod.POST)
	public ModelAndView saveInfo() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.saveQxzjjsInfo(params));
	}
}
