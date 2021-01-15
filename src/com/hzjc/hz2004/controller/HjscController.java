package com.hzjc.hz2004.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
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
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoBggzxxEx;
import com.hzjc.hz2004.vo.VoCzrkdjbHqFhxx;
import com.hzjc.hz2004.vo.VoHjscspdjxx;
import com.hzjc.hz2004.vo.VoHjscspdjywfhxx;
import com.hzjc.hz2004.vo.VoHjscxx;
import com.hzjc.hz2004.vo.VoHjscywfhxx;
import com.hzjc.hz2004.vo.VoSbjbxx;
import com.hzjc.hz2004.vo.VoSpfdclxx;
import com.hzjc.util.StringUtils;

@Controller
@RequestMapping("/yw/hjyw/hjsc")
public class HjscController extends BaseController{
	@Autowired
	private HjService hjService;
	
	@Autowired
	private SpService spService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/hjyw/hjsc";
	}
	
	@RequestMapping(value = { "/processHjscyw" }, method = RequestMethod.POST)
	public ModelAndView processHjscyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String sbjbxx = params.getString("sbjbxx");
		String bggzxx = params.getString("bggzxx");
		String hjscxx = params.getString("hjscxx");
		
		List<VoHjscxx> VoHjscxx = new ArrayList<VoHjscxx>();
		VoSbjbxx voSbjbxx = null;
		
		if(CommonUtil.isNotEmpty(sbjbxx)){
			voSbjbxx = JSONUtil.getJsonData(sbjbxx, "yyyyMMdd", VoSbjbxx.class);
			if(CommonUtil.isEmpty(voSbjbxx.getSbsj())) {
				voSbjbxx.setSbsj(StringUtils.getServiceDate());
			}
		}else{
			voSbjbxx = new VoSbjbxx();
		}
		
		if(CommonUtil.isNotEmpty(hjscxx)){
			TypeToken<List<VoHjscxx>> typeToken = new TypeToken<List<VoHjscxx>>(){};
			VoHjscxx = JSONUtil.getJsonData(typeToken, hjscxx);
		}
		
		List<VoBggzxxEx> voBggzxxEx = CommonUtil.getVoBggzxxEx(bggzxx);
		
		VoHjscywfhxx fk = null;
		if(voBggzxxEx != null) {
			fk = hjService.processHjscyw(voSbjbxx, VoHjscxx.toArray(new VoHjscxx[]{}), voBggzxxEx.toArray(new VoBggzxxEx[]{}));
		}else {
			fk = hjService.processHjscyw(voSbjbxx, VoHjscxx.toArray(new VoHjscxx[]{}), null);
		}
		
		return toJson(fk);
	}
	
	@RequestMapping(value = { "/processHjscspdjyw" }, method = RequestMethod.POST)
	public ModelAndView processHjscspdjyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String hjscxx = params.getString("hjscxx");
		String spcl = params.getString("cl");
		Long spmbid = params.getLong("spmbid");
		String spsm = params.getString("fy");
		
		String sbjbxx = params.getString("sbjbxx");
		VoSbjbxx voSbjbxx = null;
		if(CommonUtil.isNotEmpty(sbjbxx)){
			voSbjbxx = JSONUtil.getJsonData(sbjbxx, "yyyyMMdd", VoSbjbxx.class);
			if(CommonUtil.isEmpty(voSbjbxx.getSbsj())) {
				voSbjbxx.setSbsj(StringUtils.getServiceDate());
			}
		}
		
		List<VoHjscspdjxx> voHjscspdjxx = new ArrayList<VoHjscspdjxx>();
		List<VoHjscxx > voHjscxx  = new ArrayList<VoHjscxx >();
		if(CommonUtil.isNotEmpty(hjscxx)){
			TypeToken<List<VoHjscxx >> typeToken = new TypeToken<List<VoHjscxx >>(){};
			voHjscxx = JSONUtil.getJsonData(typeToken, hjscxx);
		}
		
		if(voHjscxx != null && voHjscxx.size() > 0) {
			for (Iterator<VoHjscxx> iterator = voHjscxx.iterator(); iterator.hasNext();) {
				VoHjscxx vo = (VoHjscxx) iterator.next();
				VoHjscspdjxx djxx = new VoHjscspdjxx();
				try {
					BeanUtils.copyProperties(djxx, vo);
				} catch (IllegalAccessException | InvocationTargetException e) {
				}
				
				if(voSbjbxx!=null){
					djxx.setSbrgmsfhm(voSbjbxx.getSbrgmsfhm());
					djxx.setSbryxm(voSbjbxx.getSbryxm());
					djxx.setSbsj(voSbjbxx.getSbsj());
					djxx.setHzywid(voSbjbxx.getHzywid());
				}
				
				voHjscspdjxx.add(djxx);
			}
		}
		
		VoSpfdclxx voSpfdclxx[] = null;
		if(CommonUtil.isNotEmpty(spcl)){
			TypeToken<List<VoSpfdclxx>> typeToken = new TypeToken<List<VoSpfdclxx>>(){};
			List<VoSpfdclxx> list  = JSONUtil.getJsonData(typeToken, hjscxx);
			voSpfdclxx = list.toArray(new VoSpfdclxx[]{});
		}

		VoHjscspdjywfhxx fk = spService.processHjscspdjyw(spmbid, spsm, voHjscspdjxx.toArray(new VoHjscspdjxx[] {}), voSpfdclxx);
		
		return toJson(fk);
	 }
	
}
