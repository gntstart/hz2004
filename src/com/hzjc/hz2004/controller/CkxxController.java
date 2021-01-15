package com.hzjc.hz2004.controller;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.service.HjService;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.vo.VoZpscxx;
import com.hzjc.wsstruts.vo.VoPage;

@Controller
@RequestMapping("/cx/hjjbxx/ckxx")
public class CkxxController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private HjService hjService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/cx/hjjbxx/czrkMain";
	}
	
	/**
	 * 查询详细信息
	 * @return
	 */
	@RequestMapping(value = { "/queryXxxx" }, method = RequestMethod.GET)
	public String queryXxxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return "/cx/hjjbxx/" + params.getString("goto");
	}
	
	@RequestMapping(value = { "/getXxxx" }, method = RequestMethod.POST)
	public ModelAndView getXxxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		Page p = queryService.queryXxxx(params);
		String daochuFlag = params.getString("daochuFlag");
		if(daochuFlag!=null) {
			params.put("pageSize", p.getTotalCount());
			BaseContext.getContext().getSession().setAttribute(daochuFlag, params);
		}
		
		return toJson(p);
	}
	@RequestMapping(value = { "/getRkxx" }, method = RequestMethod.POST)
	public ModelAndView getRkxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		Page p = queryService.queryRkxx(params);
		String daochuFlag = params.getString("daochuFlag");
		if(daochuFlag!=null) {
			params.put("pageSize", p.getTotalCount());
			BaseContext.getContext().getSession().setAttribute(daochuFlag, params);
		}
		return toJson(p);
	}
	
	@RequestMapping(value = { "/getHdxx" }, method = RequestMethod.POST)
	public ModelAndView getHdxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.queryHdxx(params));
	}
	
	@RequestMapping(value = { "/processCzrkzt" }, method = RequestMethod.POST)
	public ModelAndView processCzrkzt() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(hjService.processCzrkzt(params));
	}
	
	@RequestMapping(value = { "/queryZp" }, method = RequestMethod.POST)
	public ModelAndView queryZp() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String strHQL = " HJXX_RYZPXXB.ryid = " + params.getLong("ryid");
		strHQL += " order by HJXX_RYZPXXB.lrrq desc,HJXX_RYZPXXB.zpid";
		
		return toJson(hjService.queryRyzpxx(strHQL, new VoPage()).getPagelist());
	}
	
	@RequestMapping(value = { "/processZpscyw" }, method = RequestMethod.POST)
	public ModelAndView processZpscyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		VoZpscxx zp = new VoZpscxx();
		zp.setZpid(params.getLong("zpid"));
		
		return toJson(hjService.processZpscyw(new VoZpscxx[]{zp}));
	}
	
	
	
}
