package com.hzjc.hz2004.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hzjc.hz2004.base.controller.BaseController;

@Controller
@RequestMapping("/yw/hjyw/qxbg")
public class QxbgController extends BaseController{
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/hjyw/qxbg";
	}
	
}
