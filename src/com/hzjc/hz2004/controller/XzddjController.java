package com.hzjc.hz2004.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.reflect.TypeToken;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.service.HjService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoRhflxx;
import com.hzjc.hz2004.vo.VoRhflywfhxx;

@Controller
@RequestMapping("/yw/hjyw/xzddj")
public class XzddjController extends BaseController{
	@Autowired
	private HjService hjService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/hjyw/xzddj";
	}
	
	@RequestMapping(value = { "/processRhflyw" }, method = RequestMethod.POST)
	public ModelAndView processRhflyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String xzdxx = params.getString("xzdxx");
		
		List<VoRhflxx> voRhflxx = new ArrayList<VoRhflxx>();
		if(CommonUtil.isNotEmpty(xzdxx)){
			TypeToken<List<VoRhflxx>> typeToken = new TypeToken<List<VoRhflxx>>(){};
			voRhflxx = JSONUtil.getJsonData(typeToken, xzdxx);
		}
		
		VoRhflywfhxx fk = hjService.processRhflyw(voRhflxx.toArray(new VoRhflxx[] {}));
		
		return toJson(fk);
	}
}
