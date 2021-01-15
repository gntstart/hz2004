package com.hzjc.hz2004.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import com.hzjc.hz2004.service.SpService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.DateHelper;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoBggzxxEx;
import com.hzjc.hz2004.vo.VoHbbgspdjxx;
import com.hzjc.hz2004.vo.VoHbbgspdjywfhxx;
import com.hzjc.hz2004.vo.VoHbbgxx;
import com.hzjc.hz2004.vo.VoHbbgywfhxx;
import com.hzjc.hz2004.vo.VoSbjbxx;
import com.hzjc.hz2004.vo.VoSpfdclxx;
import com.hzjc.util.StringUtils;

@Controller
@RequestMapping("/yw/hjyw/hbbg")
public class HbbgController extends BaseController{
	@Autowired
	private HjService hjService;
	
	@Autowired
	private SpService spService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/hjyw/hbbg";
	}
	
	@RequestMapping(value = { "/processHbbgyw" }, method = RequestMethod.POST)
	public ModelAndView processHbbgyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String sbjbxx = params.getString("sbjbxx");
		String hbbgxx = params.getString("hbbgxx");
		String bggzxx = params.getString("bggzxx");
		
		List<VoHbbgxx> voHbbgxx = new ArrayList<VoHbbgxx>();
		VoSbjbxx voSbjbxx = null;
		
		if(CommonUtil.isNotEmpty(sbjbxx)){
			voSbjbxx = JSONUtil.getJsonData(sbjbxx, "yyyyMMdd", VoSbjbxx.class);
			if(CommonUtil.isEmpty(voSbjbxx.getSbsj())) {
				voSbjbxx.setSbsj(StringUtils.getServiceDate());
			}
		}
		
		if(CommonUtil.isNotEmpty(hbbgxx)){
			TypeToken<List<VoHbbgxx>> typeToken = new TypeToken<List<VoHbbgxx>>(){};
			voHbbgxx = JSONUtil.getJsonData(typeToken, hbbgxx);
		}
		
		//通用处理变更更正信息:注意业务组装格式
		List<VoBggzxxEx> voBggzxxEx = CommonUtil.getVoBggzxxEx(bggzxx);
		
		/**
			立户(voLhhdxx)与入户(voRhhdxx)有其一即可
		 */
		VoHbbgywfhxx fk = null;
		if(voBggzxxEx != null) {
			fk = hjService.processHbbgyw(voSbjbxx, voHbbgxx.toArray(new VoHbbgxx[]{}), voBggzxxEx.toArray(new VoBggzxxEx[]{}));
		}else {
			fk = hjService.processHbbgyw(voSbjbxx, voHbbgxx.toArray(new VoHbbgxx[]{}), null);
		}
		
		return toJson(fk);
	}
	
	@RequestMapping(value = { "/processHbbgspdjyw" }, method = RequestMethod.POST)
	public ModelAndView processHbbgspdjyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
		String sbjbxx = params.getString("sbjbxx");
		String hbbgxx = params.getString("hbbgxx");
		String spcl = params.getString("cl");
		Long spmbid = params.getLong("spmbid");
		String spsm = params.getString("fy");
		
		VoSbjbxx voSbjbxx = null;
		if(CommonUtil.isNotEmpty(sbjbxx)){
			voSbjbxx = JSONUtil.getJsonData(sbjbxx, "yyyyMMdd", VoSbjbxx.class);
			if(CommonUtil.isEmpty(voSbjbxx.getSbsj())) {
				voSbjbxx.setSbsj(StringUtils.getServiceDate());
			}
		}
		
		List<VoHbbgxx> voHbbgxx = new ArrayList<VoHbbgxx>();
		List<VoHbbgspdjxx> voHbbgspdjxx = new ArrayList<VoHbbgspdjxx>();
		if(CommonUtil.isNotEmpty(hbbgxx)){
			TypeToken<List<VoHbbgxx>> typeToken = new TypeToken<List<VoHbbgxx>>(){};
			voHbbgxx = JSONUtil.getJsonData(typeToken, hbbgxx);
			
			/**
			 * 转换voHbbgspdjxx对象
			 */
			if(voHbbgxx != null && voHbbgxx.size() > 0) {
				for (Iterator<VoHbbgxx> iterator = voHbbgxx.iterator(); iterator.hasNext();) {
					VoHbbgxx vo = (VoHbbgxx) iterator.next();
					
					VoHbbgspdjxx result = new VoHbbgspdjxx();
					result.setRynbid(vo.getRynbid());
					result.setHbbglb(vo.getHbbglb());
					result.setBghhb(vo.getBghhb());
					result.setSbsj(voSbjbxx.getSbsj());
					result.setSbryxm(voSbjbxx.getSbryxm());
					result.setSbrgmsfhm(voSbjbxx.getSbrgmsfhm());
					
					voHbbgspdjxx.add(result);
					
				}
			}
			
		}
		
		VoSpfdclxx voSpfdclxx[] = null;
		if(CommonUtil.isNotEmpty(spcl)){
			TypeToken<List<VoSpfdclxx>> typeToken = new TypeToken<List<VoSpfdclxx>>(){};
			List<VoSpfdclxx> list  = JSONUtil.getJsonData(typeToken, hbbgxx);
			voSpfdclxx = list.toArray(new VoSpfdclxx[]{});
		}
		
		VoHbbgspdjywfhxx fk = this.spService.processHbbgspdjyw(spmbid, spsm, voHbbgspdjxx.toArray(new VoHbbgspdjxx[] {}), voSpfdclxx);
		return toJson(fk);
	 }
	
}
