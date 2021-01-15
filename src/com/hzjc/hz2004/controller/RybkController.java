package com.hzjc.hz2004.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.dict.DictData;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.exception.ActionException;
import com.hzjc.hz2004.po.PoHJYW_RKBKXXB;
import com.hzjc.hz2004.service.RybkService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.DateHelper;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.util.ObjectUtil;

@Controller
@RequestMapping("/gl/bk")
public class RybkController extends BaseController{
	@Autowired
	private RybkService rybkService;
	
	@RequestMapping(value = { "/rywh", "" }, method = RequestMethod.GET)
	public String rywh() {
		return "/gl/bk/rywh";
	}
	
	@RequestMapping(value = { "/rygjcx", "" }, method = RequestMethod.GET)
	public String rygjcx() {
		return "/gl/bk/rygjcx";
	}
	
	@RequestMapping(value = { "/delRybk" }, method = RequestMethod.POST)
	public ModelAndView delRybk() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		rybkService.delRybk(params);
		return toJson();
	}
	
	@RequestMapping(value = { "/saveRybk" }, method = RequestMethod.POST)
	public ModelAndView saveRybk() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		PoHJYW_RKBKXXB ry = ObjectUtil.copyInfoByMap(PoHJYW_RKBKXXB.class, params);
		String labString = DictData.getCodeName("CS_BKLX", ry.getBklx());
		ry.setBklxmc(labString);
		
		if(CommonUtil.isEmpty(ry.getYwid())) {
			AuthToken user = BaseContext.getBaseUser();
			if(user==null) {
				throw new ActionException("请先登录！");
			}
			ry.setBksj(DateHelper.formateDate("yyyyMMddHHmmss"));
			ry.setBkmjxm(user.getUser().getYhxm());
		}
		
		ry = rybkService.saveRybk(ry);
		
		return toJson(ry);
	}
	
	@RequestMapping(value = { "/queryRybk" }, method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView queryRybk() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		Page fk = rybkService.queryRybk(params);
		return toJson(fk);
	}
	
	@RequestMapping(value = { "/queryRybkgjxx" }, method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView queryRybkgjxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		Page fk = rybkService.queryRybkgjxx(params);
		return toJson(fk);
	}
}
