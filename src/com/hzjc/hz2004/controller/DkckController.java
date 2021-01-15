package com.hzjc.hz2004.controller;

import java.io.OutputStream;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.po.PoYDZJ_SDZP;
import com.hzjc.hz2004.service.CommonService;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;

@Controller
@RequestMapping("/yw/ydzzjgl/dkck")
public class DkckController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	@Resource
	private CommonService commonService;	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/cx/ydzzjxx/ydzslMain";
	}
	
	@RequestMapping(value = { "/querySlxx" }, method = RequestMethod.POST)
	public ModelAndView addDblkzxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.queryZjslxx(params));
	}
}
