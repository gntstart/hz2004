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
import com.hzjc.hz2004.service.CommonService;
import com.hzjc.hz2004.service.RybkService;
import com.hzjc.hz2004.service.SpService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoQrspdjxx;
import com.hzjc.hz2004.vo.VoQrspdjywfhxx;
import com.hzjc.hz2004.vo.VoQrspdjzxx;
import com.hzjc.hz2004.vo.VoSpfdclxx;
import com.hzjc.wsstruts.exception.ServiceException;
@Controller
@RequestMapping("/yw/spgl/qrsp")
public class QrspController extends BaseController{
	@Autowired
	private RybkService rybkService;
	
	@Autowired
	private SpService spService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/spgl/qrsp";
	}

	/**
	 * 迁入审批核查
	 * @return
	 */
	@RequestMapping(value = { "/checkQrspdjyw" }, method = RequestMethod.POST)
	public ModelAndView checkQrspdjyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(commonService.checkQrspdjyw(params));
	}
		
	@RequestMapping(value = { "/processQrspdjyw" }, method = RequestMethod.POST)
	public ModelAndView processQrspdjyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String spsm = params.getString("spsm");
		String s_voQrspdjxx = params.getString("voQrspdjxx");
		String s_voQrspdjzxx = params.getString("voQrspdjzxx");
		String s_voSpfdclxx = params.getString("voSpfdclxx");

		VoQrspdjxx voQrspdjxx = JSONUtil.getJsonData(s_voQrspdjxx, "yyyyMMdd", VoQrspdjxx.class);
        List<VoQrspdjzxx> voQrspdjzxx = new ArrayList<VoQrspdjzxx>();
        List<VoSpfdclxx> voSpfdclxx = new ArrayList<VoSpfdclxx>();
 
		if(CommonUtil.isNotEmpty(s_voQrspdjzxx)){
			TypeToken<List<VoQrspdjzxx>> typeToken = new TypeToken<List<VoQrspdjzxx>>(){};
			voQrspdjzxx = JSONUtil.getJsonData(typeToken, s_voQrspdjzxx);
		}
		
		if(CommonUtil.isNotEmpty(s_voSpfdclxx)){
			TypeToken<List<VoSpfdclxx>> typeToken = new TypeToken<List<VoSpfdclxx>>(){};
			voSpfdclxx = JSONUtil.getJsonData(typeToken, s_voSpfdclxx);
		}

		String sfzString = "";
		if(CommonUtil.isNotEmpty(voQrspdjxx.getGmsfhm())) {
			sfzString = voQrspdjxx.getGmsfhm();
		}
		for(int i=0;i<voQrspdjzxx.size();i++) {
			VoQrspdjzxx vo = voQrspdjzxx.get(i);
			if(CommonUtil.isNotEmpty(vo.getGmsfhm()))
				sfzString += "," + vo.getGmsfhm();
		}
		if(CommonUtil.isNotEmpty(sfzString)) {
			String s = this.rybkService.saveRybkgjToString("1", "迁入审批业务", sfzString);
			if(CommonUtil.isNotEmpty(s)) {
				throw new ServiceException(s);
			}
		}
		
		VoQrspdjywfhxx fk = spService.processQrspdjyw(spsm, voQrspdjxx, voQrspdjzxx.toArray(new VoQrspdjzxx[]{}), voSpfdclxx.toArray(new VoSpfdclxx[]{}));
		return toJson(fk);
	}
}
