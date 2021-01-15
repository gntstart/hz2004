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
@RequestMapping("/gl/edzsz")
public class EdzszController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	@Autowired
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/gl/edzsz";
	}
	@RequestMapping(value = { "/saveEdzDysz" }, method = RequestMethod.POST)
	public void saveDymb() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		queryService.saveEdzDysz(params);
		return;
	}
}
