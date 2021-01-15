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
@RequestMapping("/cx/bdcx")
public class BdcxController extends BaseController{
	
	@Resource
	private QueryService queryService;
	
	
	        //逃犯比对
			@RequestMapping(value = { "/tfbdxxcx", "" }, method = RequestMethod.GET)
			public String index() {
				
				return "/cx/bdxx/tfbdxxcx";
			}
			
			//读卡信息日志
			@RequestMapping(value = { "/dkxxrzcx", "" }, method = RequestMethod.GET)
			public String index2() {
				
				return "/cx/bdxx/dkxxrzcx";
			}
			
			//写卡信息日志
			@RequestMapping(value = { "/xkxxrzcx", "" }, method = RequestMethod.GET)
			public String index3() {
				
				return "/cx/bdxx/xkxxrzcx";
			}
			
	
	    //逃犯比对信息查询
		@RequestMapping(value = { "/querytfbdcx"}, method = RequestMethod.POST)
		public ModelAndView querytfbdcx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			Page p = queryService.querytfbdcx(params);
			String daochuFlag = params.getString("daochuFlag");
			if(daochuFlag!=null) {
				params.put("pageSize", p.getTotalCount());
				BaseContext.getContext().getSession().setAttribute(daochuFlag, params);
			}
			return toJson(p);
		}
		
		//读卡信息日志查询
		@RequestMapping(value = { "/querydkxxrzcx"}, method = RequestMethod.POST)
		public ModelAndView querydkxxrzcx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
			return toJson(queryService.querydkxxrzcx(params));
		}
		
		//写卡信息日志查询
		@RequestMapping(value = { "/queryxkxxrzcx"}, method = RequestMethod.POST)
		public ModelAndView queryxkxxrzcx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
			return toJson(queryService.queryxkxxrzcx(params));
		}
  
}
