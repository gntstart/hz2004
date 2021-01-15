package com.hzjc.hz2004.controller;

import java.util.List;

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
@RequestMapping("/gl/jttdwh")
public class JttdwhController extends BaseController{
	
	@Resource
	private QueryService queryService;
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/jttdwh";
	}	
	@RequestMapping(value = { "/queryJttdHxx"}, method = RequestMethod.POST)
	public ModelAndView queryJttdHxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJsonObject(queryService.queryJttdHxx(params));
	}
	@RequestMapping(value = { "/queryJttdHxxDaochu"}, method = RequestMethod.POST)
	public ModelAndView queryJttdHxxDaochu() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.queryJttdHxxDaochu(params));
	}
	
	@RequestMapping(value = { "/updateJttdbz1"}, method = RequestMethod.POST)
	public void updateJttdbz1() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		queryService.updateJttdbz1(params);
	}
	@RequestMapping(value = { "/updateJttdbz2"}, method = RequestMethod.POST)
	public void updateJttdbz2() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		queryService.updateJttdbz2(params);
	}
}
