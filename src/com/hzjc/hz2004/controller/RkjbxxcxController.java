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
import com.hzjc.hz2004.util.JSONUtil;

@Controller
@RequestMapping("/cx/hjjbxx/rkjbxxcx")
public class RkjbxxcxController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/cx/hjjbxx/rkjbxxcx";
	}
	
	//查询户籍审批业务表
	@RequestMapping(value = { "/getRkjbxx"}, method = RequestMethod.POST)
	public ModelAndView queryPoHJSP_HJSPSQB() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.queryThry(params));
	}
	
	@RequestMapping(value = { "/getRkxx" }, method = RequestMethod.POST)
	public ModelAndView getRkxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		Page p = queryService.queryRkxx(params);
		String daochuFlag = params.getString("daochuFlag");
		if(daochuFlag!=null) {
			params.put("pageSize", p.getTotalCount());
			BaseContext.getContext().getSession().setAttribute(daochuFlag, params);
		}
		
		return toJson(p);
	}
	@RequestMapping(value = { "/queryRzsTree" }, method = RequestMethod.POST)
	public ModelAndView queryRzsTree() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(JSONUtil.toJSON(queryService.queryRzsTree(params)));
	}
}
