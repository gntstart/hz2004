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
@RequestMapping("/gl/zsbb")
public class ZsbbwhController extends BaseController{
	@Resource
	private QueryService queryService;
	
	    //业务报表维护
		@RequestMapping(value = { "/zsbbwh", "" }, method = RequestMethod.GET)
		public String index() {
			
			return "/gl/bbgl/zsbbwh";
		}
		
		@RequestMapping(value = { "/queryzsbblb"}, method = RequestMethod.POST)
		public ModelAndView queryzsbblb() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
			return toJson(queryService.queryzsbblb(params));
		}
		
		@RequestMapping(value = { "/queryzsbblbxx"}, method = RequestMethod.POST)
		public ModelAndView queryzsbblbxx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
			return toJson(queryService.queryzsbblbxx(params));
		}
		
		
		@RequestMapping(value = { "/delzsbbmbxx"}, method = RequestMethod.POST)
		public ModelAndView delzsbbmbxx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
			return toJson(queryService.delzsbbmbxx(params));
		}
		
		@RequestMapping(value = { "/addzsbbmbxx"}, method = RequestMethod.POST)
		public ModelAndView addzsbbmbxx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
			return toJson(queryService.addzsbbmbxx(params));
		}
		
		@RequestMapping(value = { "/updatezsbbmbxx"}, method = RequestMethod.POST)
		public ModelAndView updatezsbbmbxx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
			return toJson(queryService.updatezsbbmbxx(params));
		}
		
		@RequestMapping(value = { "/queryzsbbxx"}, method = RequestMethod.POST)
		public ModelAndView queryzsbbxx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
			return toJson(queryService.queryzsbbxx(params));
		}
		
		@RequestMapping(value = { "/addzsbbxx"}, method = RequestMethod.POST)
		public ModelAndView addzsbbxx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
			return toJson(queryService.addzsbbxx(params));
		}
		
		@RequestMapping(value = { "/updatezsbbxx"}, method = RequestMethod.POST)
		public ModelAndView updatezsbbxx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
			return toJson(queryService.updatezsbbxx(params));
		}
		
		@RequestMapping(value = { "/delzsbbxx"}, method = RequestMethod.POST)
		public ModelAndView delzsbbxx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
			return toJson(queryService.delzsbbxx(params));
		}
}
