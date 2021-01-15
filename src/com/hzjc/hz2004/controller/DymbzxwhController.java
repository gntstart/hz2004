package com.hzjc.hz2004.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.SimpleJson;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.service.DymbwhService;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;

@Controller
@RequestMapping("/gl/dymbzxwh")
public class DymbzxwhController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	@Autowired
	private DymbwhService dymbwhService;
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		AuthToken user = BaseContext.getUser();
		if(user.isAdmin()) {
			return "/gl/dymbzxwh";
		}else {
			return "/gl/noRight";
		}
		
	}
	
	@RequestMapping(value = { "/queryDymbcl" }, method = RequestMethod.POST)
	public ModelAndView queryDymbcl() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.queryDymbcl(params));
	}
	
	@RequestMapping(value = { "/saveDymb" }, method = RequestMethod.POST)
	public void saveDymb() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		SimpleJson json = new SimpleJson();
		dymbwhService.updateLodop(params, json);
		return;
	}
	@RequestMapping(value = { "/addDymb" }, method = RequestMethod.POST)
	public void addDymb() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		SimpleJson json = new SimpleJson();
		dymbwhService.insertLodop(params, json);
		return;
	}	
	@RequestMapping(value = { "/delDymb" }, method = RequestMethod.POST)
	public void dealHzyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		dymbwhService.dealLodop(params);
		return;
	}
	
}
