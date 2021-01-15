package com.hzjc.hz2004.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

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
import com.hzjc.hz2004.service.RybkService;
import com.hzjc.hz2004.service.hzpt.ReceiveStateService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.DateHelper;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoBggzxxEx;
import com.hzjc.hz2004.vo.VoChxx;
import com.hzjc.hz2004.vo.VoHjblxx;
import com.hzjc.hz2004.vo.VoLhhdxx;
import com.hzjc.hz2004.vo.VoQrdjxx;
import com.hzjc.hz2004.vo.VoQrdjywfhxx;
import com.hzjc.hz2004.vo.VoRhhdxx;
import com.hzjc.hz2004.vo.VoSbjbxx;
import com.hzjc.util.StringUtils;
import com.hzjc.wsstruts.exception.ServiceException;

@Controller
@RequestMapping("/yw/hjyw/qryw")
public class QrywController extends BaseController{
	@Autowired
	private RybkService rybkService;
	
	@Autowired
	private HjService hjService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/hjyw/qryw";
	}
	
	@RequestMapping(value = { "/processQrdjyw" }, method = RequestMethod.POST)
	public ModelAndView processQrdjyw() {
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
		
		boolean isspyw = false;
		
		//迁入登记信息
		String s4 = params.getString("voQrdjxx");
		List<VoQrdjxx> voQrdjxx = new ArrayList<VoQrdjxx>();
		if(CommonUtil.isNotEmpty(s4)){
			TypeToken<List<VoQrdjxx>> typeToken = new TypeToken<List<VoQrdjxx>>(){};
			voQrdjxx = JSONUtil.getJsonData(typeToken, s4);
			String sfzString = "";
			
			if(voQrdjxx.get(0).getSpywid()!=null) {
				isspyw = true;
			}
			
			for (Iterator<VoQrdjxx> iterator = voQrdjxx.iterator(); iterator.hasNext();) {
				VoQrdjxx vo = iterator.next();
				if(CommonUtil.isNotEmpty(vo.getGmsfhm())) {
					sfzString += vo.getGmsfhm() + ",";
				}
			}
			
			if(CommonUtil.isNotEmpty(sfzString) && !isspyw) {
				String s = this.rybkService.saveRybkgjToString("3", "无须准迁证迁入", sfzString);
				if(CommonUtil.isNotEmpty(s)) {
					throw new ServiceException(s);
				}
			}
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
		
		VoQrdjywfhxx re = null;
		if(voBggzxxEx != null) {
			re = hjService.processQrdjyw(voLhhdxx, voRhhdxx, voSbjbxx, voQrdjxx.toArray(new VoQrdjxx[]{}), voChxx.toArray(new VoChxx[]{}), voBggzxxEx.toArray(new VoBggzxxEx[]{}));
		}else {
			re = hjService.processQrdjyw(voLhhdxx, voRhhdxx, voSbjbxx, voQrdjxx.toArray(new VoQrdjxx[]{}), voChxx.toArray(new VoChxx[]{}),null);
		}
		return toJson(re);
	}
}
