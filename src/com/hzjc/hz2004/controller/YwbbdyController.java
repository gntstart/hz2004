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
@RequestMapping("/yw/ywbbdy")
public class YwbbdyController extends BaseController{
	@Resource
	private QueryService queryService;
	
	    //业务报表维护
		@RequestMapping(value = { "/ywbbdy", "" }, method = RequestMethod.GET)
		public String index() {
			
			return "/yw/bbdy/ywbbdy";
		}
		
		 //制式报表维护
		@RequestMapping(value = { "/zsbbdy", "" }, method = RequestMethod.GET)
		public String index_zs() {
			
			return "/yw/bbdy/zsbbdy";
		}
	    
		
		@RequestMapping(value = { "/querypcs"}, method = RequestMethod.POST)
		public ModelAndView querypcs() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
			return toJson(queryService.querypcs(params));
		}
		
		@RequestMapping(value = { "/querybbsc"}, method = RequestMethod.POST)
		public ModelAndView querybbsc() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
		    return toJson(queryService.querybbsc(params));
			
		}
}
