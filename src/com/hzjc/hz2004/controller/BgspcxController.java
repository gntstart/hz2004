package com.hzjc.hz2004.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;

@Controller
@RequestMapping("/cx/spcx/bgspcx")
public class BgspcxController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/cx/spcx/bgspcxMain";
	}
	
	@RequestMapping(value = { "/getBgsp" }, method = RequestMethod.POST)
	public ModelAndView getQrsp() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		Page p = queryService.queryBgsp(params);
		String daochuFlag = params.getString("daochuFlag");
		if(daochuFlag!=null) {
			params.put("pageIndex", 1);
			params.put("pageSize", p.getTotalCount());
			BaseContext.getContext().getSession().setAttribute(daochuFlag, params);
		}
		return toJson(p);
	}
	@RequestMapping(value = { "/getHjspBggzsp" }, method = RequestMethod.POST)
	public ModelAndView getHjspBggzsp() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.getHjspBggzsp(params));
	}
		
	@RequestMapping(value = { "/getBgspzb" }, method = RequestMethod.POST)
	public ModelAndView getQrspzb() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.queryBgspzb(params));
	}
	
	@RequestMapping(value = { "/getBgsplc" }, method = RequestMethod.POST)
	public ModelAndView getQrsplc() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.queryBgsplc(params));
	}
	
}
