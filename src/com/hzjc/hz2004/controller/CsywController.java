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
import com.hzjc.hz2004.base.login.HSession;
import com.hzjc.hz2004.service.HjService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoBggzxxEx;
import com.hzjc.hz2004.vo.VoChxx;
import com.hzjc.hz2004.vo.VoCsdjxx;
import com.hzjc.hz2004.vo.VoCsdjywfhxx;
import com.hzjc.hz2004.vo.VoLhhdxx;
import com.hzjc.hz2004.vo.VoRhhdxx;
import com.hzjc.hz2004.vo.VoSbjbxx;
import com.hzjc.util.StringUtils;

@Controller
@RequestMapping("/yw/hjyw/csyw")
public class CsywController extends BaseController{
	@Autowired
	private HjService hjService;
		
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/hjyw/csyw";
	}
	

	@RequestMapping(value = { "/processCsdjyw" }, method = RequestMethod.POST)
	public ModelAndView processCsdjyw() {
		//60秒内，防止重复提交业务
		HSession.checkRepeat("processCsdjyw", 60);
		
		  /**
		   * 处理迁入登记业务。
		   * @param voLhhdxx - 立户户地信息
		   * @param voRhhdxx - 入户户地信息
		   * @param voSbjbxx - 申报基本信息
		   * @param voQrdjxx[] - 迁入登记信息
		   * @param voChxx[] - 重号信息
		   * @param voBggzxx[] - 变更更正信息
		  **/
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());

		//立户户地信息
		String s1 = params.getString("voLhhdxx");
		VoLhhdxx voLhhdxx = null;
		if(CommonUtil.isNotEmpty(s1)){
			voLhhdxx = JSONUtil.getJsonData(s1, "yyyyMMdd", VoLhhdxx.class);
		}
		
		//入户户地信息
		String s2 = params.getString("voRhhdxx");
		VoRhhdxx voRhhdxx = null;
		if(CommonUtil.isNotEmpty(s2)){
			voRhhdxx = JSONUtil.getJsonData(s2, "yyyyMMdd", VoRhhdxx.class);
		}
		
		// 申报基本信息
		String s3 = params.getString("voSbjbxx");
		VoSbjbxx voSbjbxx = null;
		if(CommonUtil.isNotEmpty(s3)){
			voSbjbxx = JSONUtil.getJsonData(s3, "yyyyMMdd", VoSbjbxx.class);
			if(CommonUtil.isEmpty(voSbjbxx.getSbsj())) {
				voSbjbxx.setSbsj(StringUtils.getServiceDate());
			}
		}
		
		//迁入登记信息
		String s4 = params.getString("voCsdjxx");
		List<VoCsdjxx> voCsdjxx = new ArrayList<VoCsdjxx>();
		if(CommonUtil.isNotEmpty(s4)){
			TypeToken<List<VoCsdjxx>> typeToken = new TypeToken<List<VoCsdjxx>>(){};
			voCsdjxx = JSONUtil.getJsonData(typeToken, s4);
		}
		
		//重号信息
		String s5 = params.getString("voChxx");
		List<VoChxx> voChxx = new ArrayList<VoChxx>();
		if(CommonUtil.isNotEmpty(s5)){
			TypeToken<List<VoChxx>> typeToken = new TypeToken<List<VoChxx>>(){};
			voChxx = JSONUtil.getJsonData(typeToken, s5);
		}
		
		//变更更正信息
		String s6 = params.getString("voBggzxxEx");
		List<VoBggzxxEx> voBggzxxEx = CommonUtil.getVoBggzxxEx(s6);
		VoCsdjywfhxx re = null;
		if(voBggzxxEx != null) {
			re = hjService.processCsdjyw(voLhhdxx, voRhhdxx, voSbjbxx, voCsdjxx.toArray(new VoCsdjxx[]{}) , voBggzxxEx.toArray(new VoBggzxxEx[]{}));
		}else {
			re = hjService.processCsdjyw(voLhhdxx, voRhhdxx, voSbjbxx, voCsdjxx.toArray(new VoCsdjxx[]{}) , null);
		}
		
		return toJson(re);
	}
	
}
