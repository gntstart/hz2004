package com.hzjc.hz2004.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.service.LssfzService;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.wsstruts.vo.CdsVO;

@Controller
@RequestMapping("/cx/lzcx")
public class LzcxController extends BaseController{
	
	@Resource
	private QueryService queryService;
	@Resource
	private LssfzService lssfzService;
	
	
	//临证身份证信息
		@RequestMapping(value = { "/sfzxxch", "" }, method = RequestMethod.GET)
		public String index() {
			
			return "/cx/lsjmsfzxx/sfzxxcx";
		}
		
		@RequestMapping(value = { "/querySfzxxcx"}, method = RequestMethod.POST)
		public ModelAndView querySfzxxcx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			Page p = queryService.querySfzxxcx(params);
			String daochuFlag = params.getString("daochuFlag");
			if(daochuFlag!=null) {
				params.put("pageIndex", 1);
				params.put("pageSize", p.getTotalCount());
				BaseContext.getContext().getSession().setAttribute(daochuFlag, params);
			}
			return toJson(p);
		}
		
		//证件作废
		@RequestMapping(value = { "/updateSfzxxcx"}, method = RequestMethod.POST)
		public ModelAndView updateSfzxxcx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			CdsVO psCdsVo[] =  new CdsVO[1];
			CdsVO cdsvo =  new CdsVO();
			Map<Object, Object> newmap = new HashMap<>();
			newmap.put("lsslid", params.getString("lsslid"));
			cdsvo.setNewmap(newmap);
			psCdsVo[0]=cdsvo;
			return toJson(lssfzService.LsslxxZf(psCdsVo));
		}
		//证件修改
		@RequestMapping(value = { "/khxg"}, method = RequestMethod.POST)
		public ModelAndView khxg() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			CdsVO psCdsVo[] =  new CdsVO[1];
			CdsVO cdsvo =  new CdsVO();
			Map<Object, Object> newmap = new HashMap<>();
			newmap.put("lsslid", params.getString("lsslid"));
			newmap.put("lsjmsfzkh", params.getString("lsjmsfzkh"));
			cdsvo.setNewmap(newmap);
			psCdsVo[0]=cdsvo;
			return toJson(lssfzService.LsslxxXg(psCdsVo));
		}


}
