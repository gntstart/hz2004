package com.hzjc.hz2004.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/gl/ywbb")
public class YwbbwhController extends BaseController{
	@Resource
	private QueryService queryService;
	
	    //业务报表维护
		@RequestMapping(value = { "/ywbbwh", "" }, method = RequestMethod.GET)
		public String index() {
			
			return "/gl/bbgl/ywbbwh";
		}
		
		@RequestMapping(value = { "/querybblb"}, method = RequestMethod.POST)
		public ModelAndView querybblb() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
			return toJson(queryService.querybblb(params));
		}
		
		@RequestMapping(value = { "/querybblbxx"}, method = RequestMethod.POST)
		public ModelAndView querybblbxx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			return toJson(queryService.querybblbxx(params));
		}
		
		
		
		
		@RequestMapping(value = { "/delbblbxx"}, method = RequestMethod.POST)
		public ModelAndView delbblbxx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
			return toJson(queryService.delbblbxx(params));
		}
		
		@RequestMapping(value = { "/addbblbxx"}, method = RequestMethod.POST)
		public ModelAndView addbblbxx(HttpServletRequest request) {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
			return toJson(queryService.addbblbxx(params));
		}
		
		@RequestMapping(value = { "/updatebblbxx"}, method = RequestMethod.POST)
		public ModelAndView updatebblbxx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			return toJson(queryService.updatebblbxx(params));
		}
}
