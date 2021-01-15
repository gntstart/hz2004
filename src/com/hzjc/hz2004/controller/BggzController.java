package com.hzjc.hz2004.controller;

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
import com.hzjc.hz2004.vo.VoBggzxx;
import com.hzjc.hz2004.vo.VoBggzxxEx;
import com.hzjc.hz2004.vo.VoBggzywfhxx;
import com.hzjc.hz2004.vo.VoBgspdjywfhxx;
import com.hzjc.hz2004.vo.VoQhbgxx;
import com.hzjc.hz2004.vo.VoQhbgywfhxx;
import com.hzjc.hz2004.vo.VoSbjbxx;
import com.hzjc.hz2004.vo.VoSpfdclxx;
import com.hzjc.util.StringUtils;

@Controller
@RequestMapping("/yw/hjyw/bggz")
public class BggzController extends BaseController{
	@Autowired
	private HjService hjService;
	@Autowired
	private SpService spService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/hjyw/bggz";
	}
	
	/**
	 * 全户变更信息
	 * @return
	 */
	@RequestMapping(value = { "/processQhbgyw" }, method = RequestMethod.POST)
	public ModelAndView processQhbgyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
		String sbjbxx = params.getString("sbjbxx");
		VoSbjbxx voSbjbxx = null;
		if(CommonUtil.isNotEmpty(sbjbxx)){
			voSbjbxx = JSONUtil.getJsonData(sbjbxx, "yyyyMMdd", VoSbjbxx.class);
			/**
				设置申报时间
			 */
			if(CommonUtil.isEmpty(voSbjbxx.getSbsj())) {
				voSbjbxx.setSbsj(StringUtils.getServiceDate());
			}
		}
		
		String hbbgxx = params.getString("hbbgxx");
		VoQhbgxx voSpfdclxx[] = null;
		if(CommonUtil.isNotEmpty(hbbgxx)){
			TypeToken<List<VoQhbgxx>> typeToken = new TypeToken<List<VoQhbgxx>>(){};
			List<VoQhbgxx> list  = JSONUtil.getJsonData(typeToken, hbbgxx);
			voSpfdclxx = list.toArray(new VoQhbgxx[]{});
		}
		
		VoQhbgywfhxx fk = this.hjService.processQhbgyw(voSbjbxx, voSpfdclxx);
		return toJson(fk);
	 }
	
	@RequestMapping(value = { "/processBgspdjyw" }, method = RequestMethod.POST)
	public ModelAndView processBgspdjyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String sbjbxx = params.getString("sbjbxx");
		String bggzxx = params.getString("bggzxx");
		String spcl = params.getString("cl");
		Long spmbid = params.getLong("spmbid");
		String spsm = params.getString("fy");
		
		VoSbjbxx voSbjbxx = null;
		if(CommonUtil.isNotEmpty(sbjbxx)){
			voSbjbxx = JSONUtil.getJsonData(sbjbxx, "yyyyMMdd", VoSbjbxx.class);
			/**
				设置申报时间
			 */
			if(CommonUtil.isEmpty(voSbjbxx.getSbsj())) {
				voSbjbxx.setSbsj(StringUtils.getServiceDate());
			}
		}
		
		//通用处理变更更正信息:注意业务组装格式
		List<VoBggzxxEx> voBggzxxEx = CommonUtil.getVoBggzxxEx(bggzxx);
		if(voBggzxxEx != null && voBggzxxEx.size() > 0) {
			for (Iterator<VoBggzxxEx> iterator = voBggzxxEx.iterator(); iterator.hasNext();) {
				VoBggzxxEx vo = (VoBggzxxEx) iterator.next();
				@SuppressWarnings("unchecked")
				List<VoBggzxx> bggzxxList = vo.getBggzxxList();
				if(bggzxxList != null && bggzxxList.size() > 0) {
					for (Iterator<VoBggzxx> iterator2 = bggzxxList.iterator(); iterator2.hasNext();) {
						VoBggzxx voBggzxx = (VoBggzxx) iterator2.next();
						voBggzxx.setBggzrq(StringUtils.getServiceDate());
					}
				}
			}
		}
		
		VoSpfdclxx voSpfdclxx[] = null;
		if(CommonUtil.isNotEmpty(spcl)){
			TypeToken<List<VoSpfdclxx>> typeToken = new TypeToken<List<VoSpfdclxx>>(){};
			List<VoSpfdclxx> list  = JSONUtil.getJsonData(typeToken, bggzxx);
			voSpfdclxx = list.toArray(new VoSpfdclxx[]{});
		}
		
		VoBgspdjywfhxx fk = this.spService.processBgspdjyw(voSbjbxx, spmbid, spsm, voBggzxxEx.toArray(new VoBggzxxEx[]{}), voSpfdclxx);
		return toJson(fk);
	 }
	
	@RequestMapping(value = { "/processBggzyw" }, method = RequestMethod.POST)
	public ModelAndView processBggzyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String sbjbxx = params.getString("sbjbxx");
		String bggzxx = params.getString("bggzxx");
		VoSbjbxx voSbjbxx = null;
		if(CommonUtil.isNotEmpty(sbjbxx)){
			voSbjbxx = JSONUtil.getJsonData(sbjbxx, "yyyyMMdd", VoSbjbxx.class);
			if(CommonUtil.isEmpty(voSbjbxx.getSbsj())) {
				voSbjbxx.setSbsj(StringUtils.getServiceDate());
			}
		}
		
		//通用处理变更更正信息:注意业务组装格式
		List<VoBggzxxEx> voBggzxxEx = CommonUtil.getVoBggzxxEx(bggzxx);
		
		VoBggzywfhxx fk = null;
		if(voBggzxxEx != null) {
			fk = hjService.processBggzyw(voSbjbxx, voBggzxxEx.toArray(new VoBggzxxEx[]{}));
		}else {
			fk = hjService.processBggzyw(voSbjbxx, null);
		}
		
		return toJson(fk);
	}
	
	@RequestMapping(value = { "/getSfz" }, method = RequestMethod.POST)
	public ModelAndView getSfz() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(hjService.getSfz(params));
	}
	
	
	
	
}
