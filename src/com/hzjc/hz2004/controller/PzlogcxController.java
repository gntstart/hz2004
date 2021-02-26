package com.hzjc.hz2004.controller;

import javax.annotation.Resource;

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
@RequestMapping("/cx/pzzpxx")
public class PzlogcxController extends BaseController {
	
	@Resource
	private QueryService queryService;
	
	//拍照日志信息
	@RequestMapping(value = { "/pzlogcx", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/cx/pzzpxx/pzlogcx";
	}
	
	
	
	//已处理拍照日志信息
	@RequestMapping(value = { "/pzlogcx_ycl", "" }, method = RequestMethod.GET)
	public String index2() {
		
		return "/cx/pzzpxx/pzlogcx_ycl";
	}		
					
		

	@RequestMapping(value = { "/getPzxx"}, method = RequestMethod.POST)
	public ModelAndView queryPzxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		Page p = queryService.queryPzxx(params);
		String daochuFlag = params.getString("daochuFlag");
		if(daochuFlag!=null) {
			params.put("pageIndex", 1);
			params.put("pageSize", p.getTotalCount());
			BaseContext.getContext().getSession().setAttribute(daochuFlag, params);
		}
		return toJson(p);
	}
	
	//删除拍照日志信息
	@RequestMapping(value = { "/delPzxx"}, method = RequestMethod.POST)
	public ModelAndView delPzxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.deletePzlogxx(params));
	}
	
	
	@RequestMapping(value = { "/getPzxxycl"}, method = RequestMethod.POST)
	public ModelAndView queryPzxxycl() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		Page p = queryService.queryPzxxycl(params);
		String daochuFlag = params.getString("daochuFlag");
		if(daochuFlag!=null) {
			params.put("pageIndex", 1);
			params.put("pageSize", p.getTotalCount());
			BaseContext.getContext().getSession().setAttribute(daochuFlag, params);
		}
		return toJson(p);
	}	
	
}
