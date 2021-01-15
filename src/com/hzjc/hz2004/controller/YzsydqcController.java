package com.hzjc.hz2004.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.service.YzsydqcService;
import com.hzjc.hz2004.util.CommonUtil;

/**
 * 一站式异地迁出业务
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/yw/hjyw/yzsydqc")
public class YzsydqcController  extends BaseController{
	@Autowired
	YzsydqcService yzsydqcService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/hjyw/yzsydqc";
	}
	
    @RequestMapping(value = "/queryKDQHjspyw", method = {RequestMethod.POST})
    public ModelAndView queryKDQHjspyw(Model model, String usercode,String pwd){
    	ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
    	return toJson(yzsydqcService.queryKDQHjspyw(params));
    }
}
