package com.hzjc.hz2004.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;

/**
 * @author zjm
 * 2018/10/16
 * 四变打印
 */
@Controller
@RequestMapping("/yw/bbdy/sbdy")
public class sbdyController extends BaseController{
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/bbdy/sbdy";
	}

	@RequestMapping(value = { "/getSbList" }, method = RequestMethod.POST)
	public ModelAndView getQyzList() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.getYhybList(params));
	}
	/**
	 * 查询详细信息
	 * @return
	 */
	@RequestMapping(value = { "/querySbxx" }, method = RequestMethod.GET)
	public String querySbxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return "/yw/bbdy/" + params.getString("goto");
	}	
	@RequestMapping(value = { "/getSbXxxx" }, method = RequestMethod.POST)
	public ModelAndView getSbXxxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		Page p = queryService.queryXxxx(params);
		String daochuFlag = params.getString("daochuFlag");
		if(daochuFlag!=null) {
			params.put("pageIndex", 1);
			params.put("pageSize", p.getTotalCount());
			BaseContext.getContext().getSession().setAttribute(daochuFlag, params);
		}
		return toJson(p);
	}
	@RequestMapping(value = { "/getSbXxxxDaochu" }, method = RequestMethod.POST)
	public ModelAndView getSbXxxxDaochu() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.queryXxxxDaochu(params));
	}
}
