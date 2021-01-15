package com.hzjc.hz2004.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.service.XtsjzdService;
import com.hzjc.hz2004.util.CommonUtil;

/**
 * 冲在缓存控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/gl/czhc")
public class CzhcController extends BaseController{
	@Autowired
	private XtsjzdService xtsjzdService;
		
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/gl/czhc";
	}
	
	@RequestMapping(value = { "/reload" }, method = RequestMethod.POST)
	public ModelAndView queryDymbcl() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(xtsjzdService.reload(params));
	}
	
	
}
