package com.hzjc.hz2004.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
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
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.po.PoHJYW_HJBLXXB;
import com.hzjc.hz2004.po.PoV_HJ_HDXXB;
import com.hzjc.hz2004.service.HjService;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.service.RybkService;
import com.hzjc.hz2004.service.SpService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.DateHelper;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoBggzxxEx;
import com.hzjc.hz2004.vo.VoChxx;
import com.hzjc.hz2004.vo.VoHjblspdjxx;
import com.hzjc.hz2004.vo.VoHjblspdjywfhxx;
import com.hzjc.hz2004.vo.VoHjblspspxx;
import com.hzjc.hz2004.vo.VoHjblspspywfhxx;
import com.hzjc.hz2004.vo.VoHjblxx;
import com.hzjc.hz2004.vo.VoHjblywfhxx;
import com.hzjc.hz2004.vo.VoLhhdxx;
import com.hzjc.hz2004.vo.VoRhhdxx;
import com.hzjc.hz2004.vo.VoSbjbxx;
import com.hzjc.hz2004.vo.VoSpfdclxx;
import com.hzjc.util.StringUtils;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.WSErrCode;

@Controller
@RequestMapping("/yw/hjyw/hjbl")
public class HjblController extends BaseController{
	@Autowired
	private RybkService rybkService;
	
	@Autowired
	private HjService hjService;
	
	@Autowired
	private SpService spService;
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/hjyw/hjbl";
	}
	
	//查询人信息
	@RequestMapping(value = { "/queryBlxx"}, method = RequestMethod.POST)
	public ModelAndView queryBlxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		PoHJYW_HJBLXXB bl = new PoHJYW_HJBLXXB();
		if(params.getLong("hhnbid") != null) {
			bl.setHhnbid(params.getLong("hhnbid"));
		}
		return toJson(bl);
	}
	
	@RequestMapping(value = { "/processHjblyw" }, method = RequestMethod.POST)
	public ModelAndView processHjblyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String lhhdxx = params.getString("lhhdxx");
		String rhhdxx = params.getString("rhhdxx");
		String sbjbxx = params.getString("sbjbxx");
		String bggzxx = params.getString("bggzxx");
		String blList = params.getString("blList");
		String chList = params.getString("chList");

		List<VoHjblxx> voBlxx = new ArrayList<VoHjblxx>();
		List<VoChxx> voChxx = new ArrayList<VoChxx>();
		VoSbjbxx voSbjbxx = null;
		VoLhhdxx voLhhdxx = null;
		VoRhhdxx voRhhdxx = null;
		String sfzString = "";
		if(CommonUtil.isNotEmpty(lhhdxx)){
			voLhhdxx = JSONUtil.getJsonData(lhhdxx, "yyyyMMdd", VoLhhdxx.class);
		}
		
		if(CommonUtil.isNotEmpty(rhhdxx)){
			voRhhdxx = JSONUtil.getJsonData(rhhdxx, "yyyyMMdd", VoRhhdxx.class);
		}
		
		if(CommonUtil.isNotEmpty(sbjbxx)){
			voSbjbxx = JSONUtil.getJsonData(sbjbxx, "yyyyMMdd", VoSbjbxx.class);
			if(CommonUtil.isEmpty(voSbjbxx.getSbsj())) {
				voSbjbxx.setSbsj(StringUtils.getServiceDate());
			}
		}
		
		if(CommonUtil.isNotEmpty(blList)){
			TypeToken<List<VoHjblxx>> typeToken = new TypeToken<List<VoHjblxx>>(){};
			voBlxx = JSONUtil.getJsonData(typeToken, blList);
			for (Iterator<VoHjblxx> iterator = voBlxx.iterator(); iterator.hasNext();) {
				VoHjblxx vo = (VoHjblxx) iterator.next();
				if(CommonUtil.isNotEmpty(vo.getGmsfhm())) {
					sfzString += vo.getGmsfhm() + ",";
				}
			}
			if(CommonUtil.isNotEmpty(sfzString)) {
				String s = this.rybkService.saveRybkgjToString("2", "户籍补录业务", sfzString);
				if(CommonUtil.isNotEmpty(s)) {
					throw new ServiceException(s);
				}
			}
		}
		
		if(CommonUtil.isNotEmpty(chList)){
			TypeToken<List<VoChxx>> typeToken = new TypeToken<List<VoChxx>>(){};
			voChxx = JSONUtil.getJsonData(typeToken, chList);
		}
		
		List<VoBggzxxEx> voBggzxxEx = CommonUtil.getVoBggzxxEx(bggzxx);
		
		/**
			立户(voLhhdxx)与入户(voRhhdxx)有其一即可
		 */
		if(voSbjbxx==null){
			voSbjbxx = new VoSbjbxx();
		}

		VoHjblywfhxx fk = null;
		if(voBggzxxEx != null) {
			fk = hjService.processHjblyw(voRhhdxx, voLhhdxx, voSbjbxx, 
					voBlxx.toArray(new VoHjblxx[]{}), voChxx.toArray(new VoChxx[]{}), voBggzxxEx.toArray(new VoBggzxxEx[]{}));
		}else {
			fk = hjService.processHjblyw(voRhhdxx, voLhhdxx, voSbjbxx, 
					voBlxx.toArray(new VoHjblxx[]{}), voChxx.toArray(new VoChxx[]{}), null);
		}
		
		return toJson(fk);
	}
	
	@RequestMapping(value = { "/processHjblspdjyw" }, method = RequestMethod.POST)
	public ModelAndView processHjblspdjyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
		String blList = params.getString("blList");
		String lhhdxx = params.getString("lhhdxx");
		String rhhdxx = params.getString("rhhdxx");
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
		
		VoLhhdxx voLhhdxx = null;
		VoRhhdxx voRhhdxx = null;
		if(CommonUtil.isNotEmpty(lhhdxx)){
			voLhhdxx = JSONUtil.getJsonData(lhhdxx, "yyyyMMdd", VoLhhdxx.class);
		}
		
		if(CommonUtil.isNotEmpty(rhhdxx)){
			voRhhdxx = JSONUtil.getJsonData(rhhdxx, "yyyyMMdd", VoRhhdxx.class);
		}
		
		List<VoHjblspdjxx> voHjblspdjxx = new ArrayList<VoHjblspdjxx>();
		List<VoHjblxx > voHjblxx  = new ArrayList<VoHjblxx >();
		if(CommonUtil.isNotEmpty(blList)){
			TypeToken<List<VoHjblxx >> typeToken = new TypeToken<List<VoHjblxx >>(){};
			voHjblxx = JSONUtil.getJsonData(typeToken, blList);
		}
		
		String hzywid = params.getString("hzywid");
		String sfzString = "";
		if(voHjblxx != null && voHjblxx.size() > 0) {
			for (Iterator<VoHjblxx> iterator = voHjblxx.iterator(); iterator.hasNext();) {
				VoHjblxx vo = (VoHjblxx) iterator.next();
				if(CommonUtil.isNotEmpty(vo.getGmsfhm())) {
					sfzString += vo.getGmsfhm() + ",";
				}
				
				VoHjblspdjxx djxx = new VoHjblspdjxx();
				try {
					BeanUtils.copyProperties(djxx, vo);
				} catch (IllegalAccessException | InvocationTargetException e) {
				}
				if(voSbjbxx!=null){
					djxx.setSbrgmsfhm(voSbjbxx.getSbrgmsfhm());
					djxx.setSbsj(voSbjbxx.getSbsj());
					djxx.setSbryxm(voSbjbxx.getSbryxm());
				}
				djxx.setHzywid(hzywid);
				
				voHjblspdjxx.add(djxx);
			}
		}
		
		if(CommonUtil.isNotEmpty(sfzString)) {
			String s = this.rybkService.saveRybkgjToString("2", "户籍补录业务", sfzString);
			if(CommonUtil.isNotEmpty(s)) {
				throw new ServiceException(s);
			}
		}
		
		if (voRhhdxx != null && voRhhdxx.getHhnbid() != null) {
			params.put("hhid", voRhhdxx.getHhnbid());
			
			Page p = queryService.queryHdxx(params);
			if(p != null && p.getList() != null && p.getList().size() > 0) {
				PoV_HJ_HDXXB hdxx = (PoV_HJ_HDXXB) p.getList().get(0);
				for (Iterator<VoHjblspdjxx> iterator = voHjblspdjxx.iterator(); iterator.hasNext();) {
					VoHjblspdjxx vo = (VoHjblspdjxx) iterator.next();
					
					vo.setSsssxq(hdxx.getSsxq());
					vo.setSspcs(hdxx.getPcs());
					vo.setSsjwh(hdxx.getJcwh());
					vo.setSsjlx(hdxx.getJlx());
					
					/**
						18.11.29
						注释	刁杰
						HJSP_HJBLSPSQB 表  Sshhid 字段存放的是HHID
						但是户信息表中可能会有多条相同HHID记录
						导致户籍补录处理时获取错误
					vo.setSshhid(hdxx.getHhid());
					
						2019.1014 sshhid 再度改为hhid
					 */
					vo.setSshhid(hdxx.getHhid());
					
					vo.setSsxzjd(hdxx.getXzjd());
					vo.setSszrq(hdxx.getZrq());
					vo.setSsmlph(hdxx.getMlph());
					vo.setSsxz(hdxx.getMlxz());
					vo.setSshh(hdxx.getHh());
					vo.setSshlx(hdxx.getHlx());
					
				}
			}
			
		}else {
			for (Iterator<VoHjblspdjxx> iterator = voHjblspdjxx.iterator(); iterator.hasNext();) {
				VoHjblspdjxx vo = (VoHjblspdjxx) iterator.next();
				vo.setSsssxq(voLhhdxx.getSsxq());
				vo.setSspcs(voLhhdxx.getPcs());
				vo.setSsjwh(voLhhdxx.getJcwh());
				vo.setSsjlx(voLhhdxx.getJlx());
//				vo.setSshhid(voLhhdxx.getHhid());
				
//				vo.setSsxzjd(voLhhdxx.getXzjd());
				vo.setSszrq(voLhhdxx.getZrq());
				vo.setSsmlph(voLhhdxx.getMlph());
				vo.setSsxz(voLhhdxx.getMlxz());
//				vo.setSshh(voLhhdxx.getHh());
				vo.setSshlx(voLhhdxx.getHlx());
				
			}
		}
		
		VoSpfdclxx voSpfdclxx[] = null;
		if(CommonUtil.isNotEmpty(spcl)){
			TypeToken<List<VoSpfdclxx>> typeToken = new TypeToken<List<VoSpfdclxx>>(){};
			List<VoSpfdclxx> list  = JSONUtil.getJsonData(typeToken, blList);
			voSpfdclxx = list.toArray(new VoSpfdclxx[]{});
		}
		

		
		VoHjblspdjywfhxx fk = spService.processHjblspdjyw(spmbid, spsm, voHjblspdjxx.toArray(new VoHjblspdjxx[] {}), voSpfdclxx);
		return toJson(fk);
	 }
	
	/**
	 * 户籍补录结果处理业务
	 * @return
	 */
	@RequestMapping(value = { "/processHjblspspyw" }, method = RequestMethod.POST)
	public ModelAndView processHjblspspyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		long spywid = params.getLong("spywid");
		VoHjblspspxx cl = new VoHjblspspxx();
		cl.setSpywid(spywid);
		
		VoHjblspspywfhxx  re = spService.processHjblspspyw(new VoHjblspspxx[] {cl});
		
		return toJson(re);
	}
	
	
}
