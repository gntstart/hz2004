package com.hzjc.hz2004.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hzjc.hz2004.base.controller.BaseController;

@Controller
@RequestMapping("/index")
public class IndexController  extends BaseController{
	@RequestMapping(value = { "/", ""}, method = RequestMethod.GET)
	public String index() {
		return "index";
	}
}
