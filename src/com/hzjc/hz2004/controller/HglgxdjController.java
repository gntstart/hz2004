package com.hzjc.hz2004.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.service.DzService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.vo.VoHgxscxx;
import com.hzjc.hz2004.vo.VoHgxscywfhxx;
import com.hzjc.hz2004.vo.VoHgxzjxx;
import com.hzjc.hz2004.vo.VoHgxzjywfhxx;

/**
 * 户关联关系登记控制类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/yw/hjyw/hglgxdj")
public class HglgxdjController extends BaseController{
	@Autowired
	private DzService dzService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/hjyw/hglgxdj";
	}
	
	@RequestMapping(value = { "/processHgxzjyw" }, method = RequestMethod.POST)
	public ModelAndView processHgxzjyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		Long hhid = params.getLong("hhid");
		Long glhhid = params.getLong("glhhid");
		String hglgx = params.getString("hglgx");
		
		VoHgxzjxx vo = new VoHgxzjxx();
		vo.setHhid(hhid);
		vo.setGlhhid(glhhid);
		vo.setGlgx(hglgx);
		
		VoHgxzjywfhxx fk = dzService.processHgxzjyw(new VoHgxzjxx[] {vo});
		
		return toJson(fk);
	}
	
	@RequestMapping(value = { "/processHgxscyw" }, method = RequestMethod.POST)
	public ModelAndView processHgxscyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		Long glid = params.getLong("glid");
		Long hhid = params.getLong("hhid");
		Long glhhid = params.getLong("glhhid");
		
		VoHgxscxx vo = new VoHgxscxx();
		vo.setGlid(glid);
		vo.setHhid(hhid);
		vo.setGlhhid(glhhid);
		
		VoHgxscywfhxx fk = dzService.processHgxscyw(new VoHgxscxx[] {vo});
		
		return toJson(fk);
	}
}
