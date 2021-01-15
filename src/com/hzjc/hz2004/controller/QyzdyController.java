package com.hzjc.hz2004.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.service.QueryService;

/**
 * @author zjm
 * 2018/10/16
 * 迁移证打印
 */
@Controller
@RequestMapping("/yw/bbdy/qyzdy")
public class QyzdyController extends BaseController{
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/bbdy/qyzdy";
	}

}
