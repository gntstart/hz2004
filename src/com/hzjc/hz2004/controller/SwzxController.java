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
import com.hzjc.hz2004.vo.VoSbjbxx;
import com.hzjc.hz2004.vo.VoSwzxxx;
import com.hzjc.hz2004.vo.VoSwzxywfhxx;
import com.hzjc.util.StringUtils;

@Controller
@RequestMapping("/yw/hjyw/swzx")
public class SwzxController extends BaseController{
	@Autowired
	private HjService hjService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/hjyw/swzx";
	}
	
	@RequestMapping(value = { "/processSwzxyw" }, method = RequestMethod.POST)
	public ModelAndView processSwzxyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String sbjbxx = params.getString("sbjbxx");
		String bggzxx = params.getString("bggzxx");
		String swzxxx = params.getString("swzxxx");
		
		List<VoSwzxxx> VoSwzxxx = new ArrayList<VoSwzxxx>();
		VoSbjbxx voSbjbxx = null;
		
		if(CommonUtil.isNotEmpty(sbjbxx)){
			voSbjbxx = JSONUtil.getJsonData(sbjbxx, "yyyyMMdd", VoSbjbxx.class);
			if(CommonUtil.isEmpty(voSbjbxx.getSbsj())) {
				voSbjbxx.setSbsj(StringUtils.getServiceDate());
			}
		}
		
		if(CommonUtil.isNotEmpty(swzxxx)){
			TypeToken<List<VoSwzxxx>> typeToken = new TypeToken<List<VoSwzxxx>>(){};
			VoSwzxxx = JSONUtil.getJsonData(typeToken, swzxxx);
		}
		
		List<VoBggzxxEx> voBggzxxEx = CommonUtil.getVoBggzxxEx(bggzxx);
		
		VoSwzxywfhxx fk = null;
		if(voBggzxxEx != null) {
			fk = hjService.processSwzxyw(voSbjbxx, VoSwzxxx.toArray(new VoSwzxxx[]{}), voBggzxxEx.toArray(new VoBggzxxEx[]{}));
		}else {
			fk = hjService.processSwzxyw(voSbjbxx, VoSwzxxx.toArray(new VoSwzxxx[]{}), null);
		}
		
		return toJson(fk);
	}
}
