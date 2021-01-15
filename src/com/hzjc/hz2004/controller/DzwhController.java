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
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoQcclxx;
import com.hzjc.hz2004.vo.VoQcclywfhxx;
import com.hzjc.wsstruts.vo.VoPage;
import com.hzjc.wsstruts.vo.VoQueryResult;

/**
 * 地址维护
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/yw/dzgl/dzwh")
public class DzwhController extends BaseController{
	@Autowired
	private HjService hjService;
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/dzgl/dzwh";
	}

	@RequestMapping(value = { "/dzwhxxcx" }, method = RequestMethod.POST)
	public ModelAndView dzwhxxcx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.dzwhxxcx(params));
		
	}
	
	@RequestMapping(value = { "/queryQcclxx" }, method = RequestMethod.POST)
	public ModelAndView queryQcclxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.queryQcclxx(params));
		
	}
	
	@RequestMapping(value = { "/processQcclyw" }, method = RequestMethod.POST)
	public ModelAndView processQcclyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String qcclxx = params.getString("voQcclxx");
		
		List<VoQcclxx> voQcclxx = new ArrayList<VoQcclxx>();
		if(CommonUtil.isNotEmpty(qcclxx)){
			
			TypeToken<List<VoQcclxx>> typeToken = new TypeToken<List<VoQcclxx>>(){};
			voQcclxx = JSONUtil.getJsonData(typeToken, qcclxx);
			
		}
		
		VoQcclywfhxx fk = hjService.processQcclyw(voQcclxx.toArray(new VoQcclxx[] {}));
		
		return toJson(fk);
	}
	
	@RequestMapping(value = { "/searchJlxByJwh" }, method = RequestMethod.POST)
	public ModelAndView searchJlxByJwh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.searchJlxByJwh(params));
	}	
	
	@RequestMapping(value = { "/insertDzwhxx" }, method = RequestMethod.POST)
	public ModelAndView insertDzwhxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.insertDzwhxx(params));
	}
	@RequestMapping(value = { "/updateDzwhxx" }, method = RequestMethod.POST)
	public ModelAndView updateDzwhxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		if(!params.containsKey("mlph")) {
			params.put("mlph", "");
		}
		if(!params.containsKey("mlxz")) {
			params.put("mlxz", "");
		}
		return toJson(queryService.updateDzwhxx(params));
	}
	
}
