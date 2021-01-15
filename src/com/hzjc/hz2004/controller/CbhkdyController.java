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
import com.hzjc.hz2004.vo.VoBggzxxEx;
import com.hzjc.hz2004.vo.VoBggzywfhxx;
import com.hzjc.hz2004.vo.VoSbjbxx;

@Controller
@RequestMapping("/yw/bbdy/cbhkdy")
public class CbhkdyController extends BaseController{
	@Autowired
	private HjService hjService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/bbdy/cbhkdy";
	}
	
	@RequestMapping(value = { "/processBggzyw" }, method = RequestMethod.POST)
	public ModelAndView processBggzyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String sbjbxx = params.getString("sbjbxx");
		String bggzxx = params.getString("bggzxx");
		
		VoSbjbxx voSbjbxx = null;
		
		if(CommonUtil.isNotEmpty(sbjbxx)){
			voSbjbxx = JSONUtil.getJsonData(sbjbxx, "yyyyMMdd", VoSbjbxx.class);
		}
		
		List<VoBggzxxEx> voBggzxxEx = CommonUtil.getVoBggzxxEx(bggzxx);
		
		VoBggzywfhxx fk = null;
		if(voBggzxxEx != null) {
			fk = hjService.processBggzyw(voSbjbxx, voBggzxxEx.toArray(new VoBggzxxEx[]{}));
		}else {
			fk = hjService.processBggzyw(voSbjbxx, null);
		}
		
		return toJson(fk);
	}
}
