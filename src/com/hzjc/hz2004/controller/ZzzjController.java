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
@RequestMapping("/cx/zzzj")
public class ZzzjController extends BaseController{
	        @Resource
	        private QueryService queryService;
   
	        //操作员信息查询
			@RequestMapping(value = { "/czyxxcx", "" }, method = RequestMethod.GET)
			public String index() {
				
				return "/cx/zzzjxx/czyxxcx";
			}
			
			//操作员信息
			@RequestMapping(value = { "/queryczyxxcx"}, method = RequestMethod.POST)
			public ModelAndView queryczyxxcx() {
				ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
				Page p = queryService.queryczyxxcx(params);
				String daochuFlag = params.getString("daochuFlag");
				if(daochuFlag!=null) {
					params.put("pageIndex", 1);
					params.put("pageSize", p.getTotalCount());
					BaseContext.getContext().getSession().setAttribute(daochuFlag, params);
				}
				return toJson(p);
			}
			
			//追加住址
			@RequestMapping(value = { "/zjadresscx", "" }, method = RequestMethod.GET)
			public String index2() {
				
				return "/cx/zzzjxx/zjadresscx";
			}
			
			//追加住址查询
				@RequestMapping(value = { "/queryzjAdresscx"}, method = RequestMethod.POST)
				public ModelAndView queryzjAdresscx() {
					ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
					Page p = queryService.queryzjAdresscx(params);
					String daochuFlag = params.getString("daochuFlag");
					if(daochuFlag!=null) {
						params.put("pageIndex", 1);
						params.put("pageSize", p.getTotalCount());
						BaseContext.getContext().getSession().setAttribute(daochuFlag, params);
					}
					return toJson(p);
				}
				
				//日志信息
				@RequestMapping(value = { "/zzzjrzxxcx", "" }, method = RequestMethod.GET)
				public String index3() {
					
					return "/cx/zzzjxx/zzzjrzcx";
				}
				
				//日志信息查询
				@RequestMapping(value = { "/queryrzxxcx"}, method = RequestMethod.POST)
				public ModelAndView queryrzxxcx() {
					ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
					Page p = queryService.queryrzxxcx(params);
					String daochuFlag = params.getString("daochuFlag");
					if(daochuFlag!=null) {
						params.put("pageIndex", 1);
						params.put("pageSize", p.getTotalCount());
						BaseContext.getContext().getSession().setAttribute(daochuFlag, params);
					}
					return toJson(p);
				}
}
