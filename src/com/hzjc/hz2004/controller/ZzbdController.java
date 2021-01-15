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
import com.hzjc.hz2004.vo.VoBggzxxEx;
import com.hzjc.hz2004.vo.VoLhhdxx;
import com.hzjc.hz2004.vo.VoRhhdxx;
import com.hzjc.hz2004.vo.VoSbjbxx;
import com.hzjc.hz2004.vo.VoZzbdxx;
import com.hzjc.hz2004.vo.VoZzbdywfhxx;
import com.hzjc.util.StringUtils;

@Controller
@RequestMapping("/yw/hjyw/zzbd")
public class ZzbdController extends BaseController{
	@Autowired
	private HjService hjService;
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/hjyw/zzbd";
	}
	
	//查询人信息
	@RequestMapping(value = { "/queryHxx"}, method = RequestMethod.POST)
	public ModelAndView queryHxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.queryVoHxxHqFhxx(params));
	}
	
	@RequestMapping(value = { "/processZzbdyw" }, method = RequestMethod.POST)
	public ModelAndView processZzbdyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String sbjbxx = params.getString("sbjbxx");
		String lhhdxx = params.getString("lhhdxx");
		String rhhdxx = params.getString("rhhdxx");
		String bggzxx = params.getString("bggzxx");
		String zzbdxx = params.getString("zzbdxx");
		
		List<VoZzbdxx> VoZzbdxx = new ArrayList<VoZzbdxx>();
		VoSbjbxx voSbjbxx = null;
		VoLhhdxx voLhhdxx = null;
		VoRhhdxx voRhhdxx = null;
		
		if(CommonUtil.isNotEmpty(sbjbxx)){
			voSbjbxx = JSONUtil.getJsonData(sbjbxx, "yyyyMMdd", VoSbjbxx.class);
			if(CommonUtil.isEmpty(voSbjbxx.getSbsj())) {
				voSbjbxx.setSbsj(StringUtils.getServiceDate());
			}
		}
		
		if(CommonUtil.isNotEmpty(lhhdxx)){
			voLhhdxx = JSONUtil.getJsonData(lhhdxx, "yyyyMMdd", VoLhhdxx.class);
		}
		
		if(CommonUtil.isNotEmpty(rhhdxx)){
			voRhhdxx = JSONUtil.getJsonData(rhhdxx, "yyyyMMdd", VoRhhdxx.class);
		}
		
		if(CommonUtil.isNotEmpty(zzbdxx)){
			TypeToken<List<VoZzbdxx>> typeToken = new TypeToken<List<VoZzbdxx>>(){};
			VoZzbdxx = JSONUtil.getJsonData(typeToken, zzbdxx);
		}
		
		//通用处理变更更正信息:注意业务组装格式
		List<VoBggzxxEx> voBggzxxEx = CommonUtil.getVoBggzxxEx(bggzxx);
		
		/**
			立户(voLhhdxx)与入户(voRhhdxx)有其一即可
		 */
		VoZzbdywfhxx fk = null;
		if(voBggzxxEx != null) {
			fk = hjService.processZzbdyw(voLhhdxx, voRhhdxx, voSbjbxx, VoZzbdxx.toArray(new VoZzbdxx[]{}), voBggzxxEx.toArray(new VoBggzxxEx[]{}));
		}else {
			fk = hjService.processZzbdyw(voLhhdxx, voRhhdxx, voSbjbxx, VoZzbdxx.toArray(new VoZzbdxx[]{}), null);
		}
		
		return toJson(fk);
	}
}
