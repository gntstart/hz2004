package com.hzjc.hz2004.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.reflect.TypeToken;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.exception.ActionException;
import com.hzjc.hz2004.po.PoHJXX_CZRKJBXXB;
import com.hzjc.hz2004.service.HjService;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.DateHelper;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoBggzxxEx;
import com.hzjc.hz2004.vo.VoHbbgxx;
import com.hzjc.hz2004.vo.VoHbbgywfhxx;
import com.hzjc.hz2004.vo.VoSbjbxx;
import com.hzjc.hz2004.vo.VoSwzxxx;
import com.hzjc.hz2004.vo.VoSwzxywfhxx;

@Controller
@RequestMapping("/yw/hjyw/cphbbg")
public class CphbbgController extends BaseController{
	@Autowired
	private HjService hjService;
	
	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "/yw/hjyw/cphbbg";
	}
	
	@RequestMapping(value = { "/processHbbgyw" }, method = RequestMethod.POST)
	public ModelAndView processHbbgyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		//单个变动参数
		String ryxx = params.getString("ryxx");
		//集体变更条件
		String ryxxQueryParams = params.getString("ryxxQueryParams");
		
		String bgfs = params.getString("bgfs");			//1 单个，2 集体
		String bdhhb = params.getString("bdhhb");	//变更后户别
		String jthzl = params.getString("jthzl");			//集体户种类
		String bdyy = params.getString("bdyy");		//变更原因（类别）
		
		if(CommonUtil.isEmpty(bgfs))
			throw new ActionException("必须提交变更方式");
		
		VoHbbgywfhxx fk = null;
		VoSbjbxx sb = new VoSbjbxx();
		
		VoHbbgxx hb = new VoHbbgxx();
		hb.setBdfw("11");
		hb.setBghhb(bdhhb);
		hb.setJthzl(jthzl);
		hb.setHbbglb(bdyy);
		hb.setHbbgrq(DateHelper.formateDate("yyyyMMdd"));
		
		if(bgfs.equals("1")){
			PoHJXX_CZRKJBXXB po = JSONUtil.getJsonData(ryxx, "yyyyMMdd", PoHJXX_CZRKJBXXB.class);
			hb.setRynbid(po.getRynbid());
			hb.setSbhjywid(po.getCjhjywid());
			
			VoHbbgxx[] hblist = new VoHbbgxx[]{hb};
			fk = hjService.processHbbgyw(sb, hblist, null);
		}else{
			Map<?,?> map = JSONUtil.getJsonData(ryxxQueryParams, "yyyyMMdd", Map.class);
			ExtMap<String,Object> queryParams = new ExtMap<String,Object>();
			for(Object key:map.keySet()){
				String strkey = (String)key;
				Object val = map.get(strkey);
				queryParams.put(strkey, val);
			}
			queryParams.put("_queryAll", "1");
			Page page = this.queryService.queryXxxx(queryParams);
			for(Object obj: page.getList()){
				PoHJXX_CZRKJBXXB po = (PoHJXX_CZRKJBXXB)obj;
				
				hb.setRynbid(po.getRynbid());
				hb.setSbhjywid(po.getCjhjywid());
				
				VoHbbgxx[] hblist = new VoHbbgxx[]{hb};
				fk = hjService.processHbbgyw(sb, hblist, null);
			}
		}

		
		
		return toJson(fk);
	}
}
