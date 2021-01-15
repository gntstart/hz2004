package com.hzjc.hz2004.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.po.PoHJXX_MLPXXXXB;
import com.hzjc.hz2004.service.DzService;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoQhtzxx;
import com.hzjc.hz2004.vo.VoQhtzywfhxx;

/**
 * 区划调整
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/yw/dzgl/qhtz")
public class QhtzController extends BaseController{
	@Autowired
	private DzService dzService;
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/dzgl/qhtz";
	}
	
	/**
	 * 查询详细信息
	 * @return
	 */
	@RequestMapping(value = { "/queryXxxx" }, method = RequestMethod.GET)
	public String queryXxxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return "yw/dzgl/" + params.getString("goto");
	}
	
	@RequestMapping(value = { "/queryDztzxx" }, method = RequestMethod.POST)
	public ModelAndView queryQcclxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.queryXxxx(params));
		
	}
	
	@RequestMapping(value = { "/processQhtzyw" }, method = RequestMethod.POST)
	public ModelAndView processQhtzyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String qhtzxx = params.getString("voQhtzxx");
		
		VoQhtzxx voQhtzxx = null;
		if(CommonUtil.isNotEmpty(qhtzxx)){
			voQhtzxx = JSONUtil.getJsonData(qhtzxx, "yyyyMMdd", VoQhtzxx.class);
		}
		
		VoQhtzywfhxx fk = dzService.processQhtzyw(new VoQhtzxx[]{voQhtzxx});
		
		return toJson(fk);
	}
	
	
	@RequestMapping(value = { "/processQhtzywQbgg" }, method = RequestMethod.POST)
	public ModelAndView processQhtzywQbgg() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String querydata = params.getString("querydata");
		String bgdata = params.getString("bgdata");
		ExtMap<String, Object> queryMlpPo1 = JSONUtil.getJsonData(querydata, "yyyyMMdd", new ExtMap<String, Object>().getClass());
		PoHJXX_MLPXXXXB bgMlpPo = new PoHJXX_MLPXXXXB();
		if(CommonUtil.isNotEmpty(bgdata)){
			bgMlpPo = JSONUtil.getJsonData(bgdata, "yyyyMMdd", PoHJXX_MLPXXXXB.class);
		}
		int[] bgFlagarr = {params.getInteger("c11"),params.getInteger("c12"),params.getInteger("c13")
				,params.getInteger("c14"),params.getInteger("c15")};
		return toJson(queryService.processQhtzywQbgg(queryMlpPo1,bgMlpPo,bgFlagarr));
	}
	
	@RequestMapping(value = { "/queryJwhByssxq" }, method = RequestMethod.POST)
	public ModelAndView queryJwhByssxq() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.queryJwhByssxq(params));
		
	}	
	
}
