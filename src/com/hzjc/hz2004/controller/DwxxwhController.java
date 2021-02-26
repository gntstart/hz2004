package com.hzjc.hz2004.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.exception.ServiceException;
import com.hzjc.hz2004.po.PoHJXX_HXXB;
import com.hzjc.hz2004.po.PoHJXX_MLPXXXXB;
import com.hzjc.hz2004.po.PoV_DWXXWH;
import com.hzjc.hz2004.po.PoV_HJ_HDXXB;
import com.hzjc.hz2004.po.PoXT_JLXXXB;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.wsstruts.exception.WSErrCode;

import sun.misc.BASE64Encoder;

@Controller
@RequestMapping("/gl/xtmbgl/dwxxwh")
public class DwxxwhController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/gl/xtmbgl/dwxxwh";
	}
	

	@RequestMapping(value = { "/getDwxxInfo" }, method = RequestMethod.POST)
	public ModelAndView getDwxxInfo() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getDwxxInfo(params));
	}
	@RequestMapping(value = { "/modifyDwDm" }, method = RequestMethod.POST)
	@ResponseBody
	public void modifyDwDm(MultipartHttpServletRequest logoFile) throws IOException {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		params.put("qhdm",logoFile.getParameter("qhdm"));
		params.put("dm",logoFile.getParameter("dm"));
		params.put("mc",logoFile.getParameter("mc"));
		/*
		 * add by zjm 
		 * 20210220
		 * 接收单位表新增传入电话号码字段
		 */
		params.put("dhhm",logoFile.getParameter("dhhm"));
		params.put("zwpy",logoFile.getParameter("zwpy"));
		params.put("wbpy",logoFile.getParameter("wbpy"));
		params.put("dwjgdm",logoFile.getParameter("dwjgdm"));
		params.put("dwjb",logoFile.getParameter("dwjb"));
		params.put("fjjgdm",logoFile.getParameter("fjjgdm"));
		params.put("fjjgmc",logoFile.getParameter("fjjgmc"));
		params.put("dz",logoFile.getParameter("dz"));
		params.put("bz",logoFile.getParameter("bz"));
		HttpServletResponse response = BaseContext.getContext().getResponse();
		try {
			queryService.modifyDwDm(logoFile,params);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("{success:true}");
		} catch (Exception e) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("{success:false}");
			response.getWriter().close();
		}
	}
	
	@RequestMapping(value = { "/addDwDm" }, method = RequestMethod.POST)
	@ResponseBody
	public void addDwDm(MultipartHttpServletRequest logoFile) throws IOException{
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		params.put("qhdm",logoFile.getParameter("qhdm"));
		params.put("dm",logoFile.getParameter("dm"));
		params.put("mc",logoFile.getParameter("mc"));
		/*
		 * add by zjm 
		 * 20210220
		 * 接收单位表新增传入电话号码字段
		 */
		params.put("dhhm",logoFile.getParameter("dhhm"));
		params.put("zwpy",logoFile.getParameter("zwpy"));
		params.put("wbpy",logoFile.getParameter("wbpy"));
		params.put("dwjgdm",logoFile.getParameter("dwjgdm"));
		params.put("dwjb",logoFile.getParameter("dwjb"));
		params.put("fjjgdm",logoFile.getParameter("fjjgdm"));
		params.put("fjjgmc",logoFile.getParameter("fjjgmc"));
		params.put("dz",logoFile.getParameter("dz"));
		params.put("bz",logoFile.getParameter("bz"));
		HttpServletResponse response = BaseContext.getContext().getResponse();
		try {
			queryService.addDwDm(logoFile,params);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("{success:true}");
		}catch (Exception e) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(e.getMessage());
			response.getWriter().close();
		}
	}
	@RequestMapping(value = { "/delDwDm" }, method = RequestMethod.POST)
	public ModelAndView delDwDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.delDwDm(params));
	}
	@RequestMapping(value = { "/resumeDwDm" }, method = RequestMethod.POST)
	public ModelAndView resumeDwDm() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.resumeDwDm(params));
	}	
	
}
