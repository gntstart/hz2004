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
import com.hzjc.hz2004.vo.VoSbjbxx;
import com.hzjc.hz2004.vo.VoSfhmfpblxx;
import com.hzjc.hz2004.vo.VoSfhmfpblywfhxx;
import com.hzjc.hz2004.vo.VoSfhmfpscxx;
import com.hzjc.hz2004.vo.VoSfhmfpscywfhxx;

@Controller
@RequestMapping("/yw/hjyw/sfhfpxxbl")
public class SfhfpxxblController extends BaseController{
	@Autowired
	private HjService hjService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/hjyw/sfhfpxxbl";
	}
	
	@RequestMapping(value = { "/processSfhmfpblyw" }, method = RequestMethod.POST)
	public ModelAndView processSfhmfpblyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String sfzblxx = params.getString("sfzblxx");
		
//		List<VoSfhmfpblxx> voSfhmfpblxx = new ArrayList<VoSfhmfpblxx>();
//		if(CommonUtil.isNotEmpty(sfzblxx)){
//			TypeToken<List<VoSfhmfpblxx>> typeToken = new TypeToken<List<VoSfhmfpblxx>>(){};
//			voSfhmfpblxx = JSONUtil.getJsonData(typeToken, sfzblxx);
//		}
		VoSfhmfpblxx voSfhmfpblxx = new VoSfhmfpblxx();
		if(CommonUtil.isNotEmpty(sfzblxx)){
			voSfhmfpblxx = JSONUtil.getJsonData(sfzblxx, "yyyyMMdd", VoSfhmfpblxx.class);
		}
		VoSfhmfpblywfhxx fk = hjService.processSfhmfpblyw(new VoSfhmfpblxx[] {voSfhmfpblxx});
		
		return toJson(fk);
	}
	
	@RequestMapping(value = { "/processSfhmfpscyw" }, method = RequestMethod.POST)
	public ModelAndView processSfhmfpscyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String fpid = params.getString("fpid");
		
		VoSfhmfpscxx scxx = new VoSfhmfpscxx();
		if(CommonUtil.isNotEmpty(fpid)){
			scxx.setFpid(Long.parseLong(fpid));
		}
		
		VoSfhmfpscywfhxx fk = hjService.processSfhmfpscyw(new VoSfhmfpscxx[] {scxx});
		
		return toJson(fk);
		
	}
	
	
	
	
}
