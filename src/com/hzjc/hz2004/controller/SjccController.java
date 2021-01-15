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
@RequestMapping("/gl/sjcc")
public class SjccController extends BaseController{
	@Resource
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/sjcc";
	}
	
	/**
	 * 查询错误信息
	 * @return
	 */
	@RequestMapping(value = { "/querycwXx" }, method = RequestMethod.GET)
	public String querycwXx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return "/gl/sjcc/" + params.getString("goto");
	}
	
	@RequestMapping(value = { "/queryyhdghz"}, method = RequestMethod.POST)
	public ModelAndView queryyhdghz() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.queryyhdghz(params));
	}
}
