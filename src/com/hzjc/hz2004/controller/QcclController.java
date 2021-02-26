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
import com.hzjc.hz2004.base.Page;
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

@Controller
@RequestMapping("/yw/hjyw/qccl")
public class QcclController extends BaseController{
	@Autowired
	private HjService hjService;
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/hjyw/qccl";
	}
	
	@RequestMapping(value = { "/queryQcclxx" }, method = RequestMethod.POST)
	public ModelAndView queryQcclxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		Page p = queryService.queryQcclxx(params);
		String daochuFlag = params.getString("daochuFlag");
		if(daochuFlag!=null) {
			params.put("pageIndex", 1);
			params.put("pageSize", p.getTotalCount());
			BaseContext.getContext().getSession().setAttribute(daochuFlag, params);
		}
		return toJson(p);
		
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
	
	@RequestMapping(value = { "/queryQcclxxDaochu" }, method = RequestMethod.POST)
	public ModelAndView queryQcclxxDaochu() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.queryQcclxxDaochu(params));
		
	}
	
	
}
