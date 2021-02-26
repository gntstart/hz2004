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
@RequestMapping("/cx/spcx/hjblspcx")
public class HjblspcxController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/cx/spcx/hjblspcxMain";
	}
	
	@RequestMapping(value = { "/getHjblsp" }, method = RequestMethod.POST)
	public ModelAndView getQrsp() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		Page p = queryService.queryHjblsp(params);
		String daochuFlag = params.getString("daochuFlag");
		if(daochuFlag!=null) {
			params.put("pageIndex", 1);
			params.put("pageSize", p.getTotalCount());
			BaseContext.getContext().getSession().setAttribute(daochuFlag, params);
		}
		return toJson(p);
	}
	
	@RequestMapping(value = { "/getHjblsplc" }, method = RequestMethod.POST)
	public ModelAndView getQrsplc() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.queryHjblsplc(params));
	}
	@RequestMapping(value = { "/getHjspHjbl" }, method = RequestMethod.POST)
	public ModelAndView getHjspHjbl() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.getHjspHjbl(params));
	}
	
}
