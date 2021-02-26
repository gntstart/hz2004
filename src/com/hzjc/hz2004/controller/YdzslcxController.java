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
@RequestMapping("/cx/ydzzjxx/ydzslcx")
public class YdzslcxController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/cx/ydzzjxx/ydzslMain";
	}
	
	/**
	 * 查询详细信息
	 * @return
	 */
	@RequestMapping(value = { "/queryXxxx" }, method = RequestMethod.GET)
	public String queryXxxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return "/cx/hjjbxx/" + params.getString("goto");
	}
	
	@RequestMapping(value = { "/getXxxx" }, method = RequestMethod.POST)
	public ModelAndView getXxxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.queryXxxx(params));
	}
	
	@RequestMapping(value = { "/getYdzslxx" }, method = RequestMethod.POST)
	public ModelAndView getQcxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		Page p = queryService.getYdzslxx(params);
		String daochuFlag = params.getString("daochuFlag");
		if(daochuFlag!=null) {
			params.put("pageIndex", 1);
			params.put("pageSize", p.getTotalCount());
			BaseContext.getContext().getSession().setAttribute(daochuFlag, params);
		}
		return toJson(p);
	}
	@RequestMapping(value = { "/delBbzslid" }, method = RequestMethod.POST)
	public ModelAndView delYdzslxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		queryService.delBbzslid(params);
		return toJson(null);
	}
	
}
