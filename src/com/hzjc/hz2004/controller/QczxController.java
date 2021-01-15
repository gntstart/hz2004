package com.hzjc.hz2004.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
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
import com.hzjc.hz2004.po.PoHJYW_QCZXXXB;
import com.hzjc.hz2004.service.CommonService;
import com.hzjc.hz2004.service.HjService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.DateHelper;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoBggzxxEx;
import com.hzjc.hz2004.vo.VoQczxxx;
import com.hzjc.hz2004.vo.VoQczxywfhxx;
import com.hzjc.hz2004.vo.VoSbjbxx;
import com.hzjc.util.StringUtils;

@Controller
@RequestMapping("/yw/hjyw/qczx")
public class QczxController extends BaseController{
	@Autowired
	private HjService hjService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/hjyw/qczx";
	}
	
	@RequestMapping(value = { "/processQczxyw" }, method = RequestMethod.POST)
	public ModelAndView processQczxyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());

		String sbjbxx = params.getString("sbjbxx");
		String bggzxx = params.getString("bggzxx");
		String qczxxx = params.getString("qczxxx");
		
		List<VoQczxxx> voQczxxx = new ArrayList<VoQczxxx>();
		VoSbjbxx voSbjbxx = null;
		
		if(CommonUtil.isNotEmpty(sbjbxx)){
			voSbjbxx = JSONUtil.getJsonData(sbjbxx, "yyyyMMdd", VoSbjbxx.class);
			if(CommonUtil.isEmpty(voSbjbxx.getSbsj())) {
				voSbjbxx.setSbsj(StringUtils.getServiceDate());
			}
		}
		
		if(CommonUtil.isNotEmpty(qczxxx)){
			TypeToken<List<VoQczxxx>> typeToken = new TypeToken<List<VoQczxxx>>(){};
			voQczxxx = JSONUtil.getJsonData(typeToken, qczxxx);
		}
		
		List<VoBggzxxEx> voBggzxxEx = CommonUtil.getVoBggzxxEx(bggzxx);
		
		VoQczxywfhxx fk = null;
		if(voBggzxxEx != null) {
			fk = hjService.processQczxyw(voSbjbxx, voQczxxx.toArray(new VoQczxxx[]{}), voBggzxxEx.toArray(new VoBggzxxEx[]{}));
		}else {
			fk = hjService.processQczxyw(voSbjbxx, voQczxxx.toArray(new VoQczxxx[]{}), null);
		}
		
		return toJson(fk);
	}
	
	@RequestMapping(value = { "/updateQczx" }, method = RequestMethod.POST)
	public ModelAndView updateQczx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		Long qczxid = params.getLong("qczxid");
		String qczxxx = params.getString("qczxxx");
		
		PoHJYW_QCZXXXB qczx = commonService.getByID(PoHJYW_QCZXXXB.class, qczxid);
		
		VoQczxxx voQczxxx = null;
		if(CommonUtil.isNotEmpty(qczxxx)){
			voQczxxx = JSONUtil.getJsonData(qczxxx, "yyyyMMdd", VoQczxxx.class);
			
			if(qczx != null && voQczxxx != null) {
				if(CommonUtil.isNotBlank(voQczxxx.getQclb())) {
					qczx.setQclb(voQczxxx.getQclb());
				}
				
				if(CommonUtil.isNotBlank(voQczxxx.getQcrq())) {
					qczx.setQcrq(voQczxxx.getQcrq());
				}
				
				if(CommonUtil.isNotBlank(voQczxxx.getQwdssxq())) {
					qczx.setQwdssxq(voQczxxx.getQwdssxq());
				}
				
				if(CommonUtil.isNotBlank(voQczxxx.getQwdxz())) {
					qczx.setQwdxz(voQczxxx.getQwdxz());
				}
				
				if(CommonUtil.isNotBlank(voQczxxx.getBdfw())) {
					qczx.setBdfw(voQczxxx.getBdfw());
				}
				
				if(CommonUtil.isNotBlank(voQczxxx.getZqzbh())) {
					qczx.setZqzbh(voQczxxx.getZqzbh());
				}
				
				if(CommonUtil.isNotBlank(voQczxxx.getQyzbh())) {
					qczx.setQyzbh(voQczxxx.getQyzbh());
				}
				
			}
		}
		
		/**
			不能使用copyProperties方法
			会使得cxfldm为空
		try {
			BeanUtils.copyProperties(qczx, voQczxxx);
		} catch (IllegalAccessException | InvocationTargetException e) {
		}
		 */
		
		commonService.updateObject(qczx);
		
		return toJson(qczx);
	}
	
}
