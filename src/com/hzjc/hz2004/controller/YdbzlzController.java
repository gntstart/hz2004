package com.hzjc.hz2004.controller;

import java.io.OutputStream;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.po.PoYDZJ_SDZP;
import com.hzjc.hz2004.service.CommonService;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;

@Controller
@RequestMapping("/yw/ydbz/ydbzlz")
public class YdbzlzController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	@Resource
	private CommonService commonService;	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/ydbz/ydbzlz";
	}
	
	@RequestMapping(value = { "/queryYdbzlzxx" }, method = RequestMethod.POST)
	public ModelAndView addDblkzxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.queryYdbzlzxx(params));
	}
	@RequestMapping(value = { "/saveLz" }, method = RequestMethod.POST)
	public ModelAndView xxzf() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.saveLz(params));
	}
	@RequestMapping(value = {"/img/render/{zpid}"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
	@ResponseBody
	public String showImg(@PathVariable("zpid") Long zplsid) {
	    // img为图片的二进制流: 3401000001001467693
		try{
			PoYDZJ_SDZP zp = commonService.getByID(PoYDZJ_SDZP.class, zplsid);
		    if(zp!=null && zp.getSdzp()!=null){
			    BaseContext.getContext().getResponse().setContentType("image/png");
			    OutputStream os =  BaseContext.getContext().getResponse().getOutputStream();
			    os.write(zp.getSdzp());
			    os.flush();
			    os.close();
		    }
		    return "success";
		}catch(Exception e){
			
		}finally{
			;
		}
		
		return "error";
	}
}
