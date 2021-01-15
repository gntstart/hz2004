package com.hzjc.hz2004.controller;



import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.base.login.LoginFilter;
import com.hzjc.hz2004.service.LodopService;
import com.hzjc.hz2004.util.CommonUtil;

@Controller
@RequestMapping("/yw/lodop")
public class LodopController extends BaseController{
	@Autowired
	private LodopService lodopService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/lodop";
	}
	@RequestMapping(value = { "/lodop" }, method = RequestMethod.POST)
	public ModelAndView processLodopModal() throws UnsupportedEncodingException {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String lodopId=params.getString("lodopId");
		String id=params.getString("id");
		String fk = lodopService.queryLodopById(lodopId,id);
		
		return toJson(fk);
	}	
	@RequestMapping(value = { "/lodop1" }, method = RequestMethod.POST)
	public ModelAndView processBggzyw() throws UnsupportedEncodingException {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String  flag=params.getString("flag");
		String lodopId=params.getString("lodopId");
		String jsonStr = params.getString("jsonStr");
		String index = params.getString("index");
		String baseUrl = LoginFilter.getBaseURL(BaseContext.getContext().getRequest());
		String fk = lodopService.processLodop(flag,lodopId,jsonStr,index,baseUrl);
		return toJson(fk);
	}
	@RequestMapping(value = { "/validZqzbh" }, method = RequestMethod.POST)
	public ModelAndView processValidZqzbh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String  spywid=params.getString("spywid");
		String zqzbh=params.getString("zqzbh");
		String fk = lodopService.processValidZqzbh(spywid,zqzbh);
		return toJson(fk);
	}
	@RequestMapping(value = { "/validQyzbh" }, method = RequestMethod.POST)
	public ModelAndView processValidQyzbh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String  qyzbh=params.getString("qyzbh");
		String yznf=params.getString("yznf");
		String fk = lodopService.processValidQyzbh(qyzbh,yznf);
		return toJson(fk);
	}
}
