package com.hzjc.hz2004.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.service.Zj2Service;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoZjysxx;

@Controller
@RequestMapping("/yw/sjgl/sjys")
public class SjysController extends BaseController{
	@Autowired
	private Zj2Service zj2Service;
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/yw/sjgl/sjys";
	}
	@RequestMapping(value = { "/querysjslxx" }, method = RequestMethod.POST)
	public ModelAndView addDblkzxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.querySjslxx(params));
	}	
	@RequestMapping(value = { "/processSjys" }, method = RequestMethod.POST)
	public ModelAndView processFjys() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String voZjysxx = params.getString("voZjysxx");
		List<VoZjysxx> voZjysxxList  = new ArrayList<VoZjysxx >();
		VoZjysxx voZjysxxObj =  new VoZjysxx();
		if(CommonUtil.isNotEmpty(voZjysxx)){
			JSONObject jo = JSONObject.parseObject(voZjysxx);
			voZjysxxObj.setCwms(jo.getString("cwms"));
			voZjysxxObj.setNbslid(jo.getLong("nbslid"));
			voZjysxxObj.setZzxxcwlb(jo.getString("zzxxcwlb"));
			voZjysxxList.add(voZjysxxObj);
		}
		return toJson(zj2Service.processDsZjysyw(voZjysxxList.toArray(new VoZjysxx[] {})));
	}
}
