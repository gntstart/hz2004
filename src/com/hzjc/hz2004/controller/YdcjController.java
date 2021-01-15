package com.hzjc.hz2004.controller;

import java.io.OutputStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.po.PoHJXX_ZPLSB;
import com.hzjc.hz2004.service.CommonService;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;

@Controller
@RequestMapping("/yw/ydcjgl")
public class YdcjController extends BaseController{
	@Resource
	private QueryService queryService;
	
	@Resource
	private CommonService commonService;
	
	@RequestMapping(value = { "/ydcj", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/yw/ydcjgl/ydcj";
	}
	
	@RequestMapping(value = { "/ydcjcx", "" }, method = RequestMethod.GET)
	public String index2() {
		
		return "/yw/ydcjgl/ydcjcx";
	}
	
	//获取照片信息
	@RequestMapping(value = { "/getzpxx"}, method = RequestMethod.POST)
	public ModelAndView getzpxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.getzpxx(params));
	}
	
	@RequestMapping(value = {"/img/render/{zplsid}"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
	@ResponseBody
	public String showImg(@PathVariable("zplsid") Long zplsid) {
	    // img为图片的二进制流: 3401000001001467693
		try{
			PoHJXX_ZPLSB zp = commonService.getByID(PoHJXX_ZPLSB.class, zplsid);
		    if(zp!=null && zp.getZp()!=null){
			    BaseContext.getContext().getResponse().setContentType("image/png");
			    OutputStream os =  BaseContext.getContext().getResponse().getOutputStream();
			    os.write(zp.getZp());
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
