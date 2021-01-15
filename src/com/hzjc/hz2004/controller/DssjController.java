package com.hzjc.hz2004.controller;

import javax.annotation.Resource;

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
@RequestMapping("/gl/bbgl/dssj")
public class DssjController extends BaseController{
	@Resource
	private QueryService queryService;
	
	@RequestMapping(value = { "/dssjsj", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/bbgl/dssjsj";
	}
	
	@RequestMapping(value = { "/dssjcx", "" }, method = RequestMethod.GET)
	public String index2() {
		
		return "/gl/bbgl/dssjcx";
	}
	
	
	@RequestMapping(value = { "/queryTjbbsjb"}, method = RequestMethod.POST)
	public ModelAndView queryTjbbsjb() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.queryTjbbsjb(params));
	}
	
	//底数生成
	@RequestMapping(value = { "/queryDssc"}, method = RequestMethod.POST)
	public ModelAndView queryDssc() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.queryDssc(params));
	}
}
