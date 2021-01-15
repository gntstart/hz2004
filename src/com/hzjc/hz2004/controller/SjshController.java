package com.hzjc.hz2004.controller;

import java.io.OutputStream;

import org.apache.axis.utils.StringUtils;
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
import com.hzjc.hz2004.po.PoHJXX_ZPLSB;
import com.hzjc.hz2004.service.HjService;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.wsstruts.vo.VoPage;

@Controller
@RequestMapping("/yw/sjgl/sjsh")
public class SjshController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	@Autowired
	private HjService hjService;	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/yw/sjgl/sjsh";
	}
	@RequestMapping(value = { "/querySjshxx" }, method = RequestMethod.POST)
	public ModelAndView queryFjshxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.querySjshxx(params));
	}
	@RequestMapping(value = {"/img/render/{zpid}"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
	@ResponseBody
	public String showImg(@PathVariable("zpid") Long zpid) {
	    // img为图片的二进制流: 3401000001001467693
		try{
			PoHJXX_ZPLSB zp = queryService.queryZplsb(zpid);
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
	@RequestMapping(value = { "/queryZp" }, method = RequestMethod.POST)
	public ModelAndView queryZp() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String strHQL = " HJXX_RYZPXXB.ryid in ( " + params.getString("ryid")+ " )";
		if(!StringUtils.isEmpty(params.getString("zpid"))) {
			strHQL +=" and zpid not in ("+params.getString("zpid")+ " )";
		}
		strHQL += " order by HJXX_RYZPXXB.lrrq desc,HJXX_RYZPXXB.zpid";
		
		return toJson(hjService.queryRyzpxx(strHQL, new VoPage()).getPagelist());
	}
	//queryLsshxx
	@RequestMapping(value = { "/queryLsshxx" }, method = RequestMethod.POST)
	public ModelAndView queryLsshxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.queryLsshxx(params));
	}
	@RequestMapping(value = { "/processZjshyw" }, method = RequestMethod.POST)
	public ModelAndView processZjshyw() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.processZjshyw(params));
	}
}
