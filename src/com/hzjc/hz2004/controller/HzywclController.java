package com.hzjc.hz2004.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.SimpleJson;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.service.Zj2Service;
import com.hzjc.hz2004.util.CommonUtil;

@Controller
@RequestMapping("/yw/hjyw/hzywcl")
public class HzywclController extends BaseController{
	@Autowired
	private Zj2Service zj2Service;

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
			return "/yw/hjyw/hzywcl";
	}
	
	@RequestMapping(value = { "/queryHzywcl" }, method = RequestMethod.POST)
	public ModelAndView queryHzywcl() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		SimpleJson json = new SimpleJson();

		return toJson(zj2Service.queryPoHZ_ZJ_SB(params, json));
	}
	
	@RequestMapping(value = { "/updateHzyw" }, method = RequestMethod.POST)
	public ModelAndView updateHzyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		SimpleJson json = new SimpleJson();
		
		long count = zj2Service.updateHzyw(params, json);
		
		return toJson(new Long(count));
	}
	@RequestMapping(value = { "/updateHzywBj" }, method = RequestMethod.POST)
	public ModelAndView updateHzywBj() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		long count = zj2Service.updateHzywBj(params);
		
		return toJson(new Long(count));
	}
	@RequestMapping(value = { "/updateHzywPj" }, method = RequestMethod.POST)
	public ModelAndView updateHzywPj() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		long count = zj2Service.updateHzywPj(params);
		
		return toJson(new Long(count));
	}
	
	@RequestMapping(value = { "/dealHzyw" }, method = RequestMethod.POST)
	public ModelAndView dealHzyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		long count = zj2Service.dealHzyw(params);
		
		return toJson(new Long(count));
	}
	
}
