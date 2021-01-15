package com.hzjc.hz2004.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.service.CommonService;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.service.Zj2Service;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.vo.VoZjgsxx;


@RequestMapping("/yw/edzjgl")
@Controller
public class EdzzjgsController extends BaseController{
	@Resource
	private QueryService queryService;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private Zj2Service zj2Service;
	
	@RequestMapping(value = { "/zjgs", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/yw/edzzjgl/zjgs";
	}
	
	@RequestMapping(value = { "/queryzjcx"}, method = RequestMethod.POST)
	public ModelAndView queryzjcx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.queryzjcx(params));
	}
	@RequestMapping(value = { "/queryzjgscx"}, method = RequestMethod.POST)
	public ModelAndView queryzjgscx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.queryzjgscx(params));
	}	
	@RequestMapping(value = { "/zjgssave"}, method = RequestMethod.POST)
	public ModelAndView zjgssave() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		VoZjgsxx[] voZjgsxx=new VoZjgsxx[1];
		VoZjgsxx voZjgsxxpo=new VoZjgsxx();
		String gsrgmsfhm=params.getString("gsrgmsfhm");
		voZjgsxxpo.setGsrgmsfhm(gsrgmsfhm);
		voZjgsxxpo.setNbsfzid(Long.valueOf(params.getString("nbsfzid")));
		voZjgsxxpo.setYsdd(params.getString("ysdd"));
		voZjgsxxpo.setYsrq(params.getString("ysrq"));
		voZjgsxxpo.setGsrxm(params.getString("gsrxm"));
		voZjgsxx[0]=voZjgsxxpo;
		return toJson(zj2Service.processZjgsyw(voZjgsxx));
	}
	@RequestMapping(value = { "/zjgsgs"}, method = RequestMethod.POST)
	public ModelAndView zjgsgs() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		VoZjgsxx[] voZjgsxx=new VoZjgsxx[1];
		VoZjgsxx voZjgsxxpo=new VoZjgsxx();
		String gsrgmsfhm=params.getString("gsrgmsfhm");
		voZjgsxxpo.setGsrgmsfhm(gsrgmsfhm);
		voZjgsxxpo.setNbsfzid(Long.valueOf(params.getString("nbsfzid")));
//		voZjgsxxpo.setYsdd(params.getString("ysdd"));
//		voZjgsxxpo.setYsrq(params.getString("ysrq"));
//		voZjgsxxpo.setGsrxm(params.getString("gsrxm"));
		voZjgsxx[0]=voZjgsxxpo;
		return toJson(zj2Service.processZjgsyw(voZjgsxx));
	}
}
