package com.hzjc.hz2004.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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
import com.hzjc.hz2004.po.PoXT_DWXXB;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.vo.VoWsbbdyxx;

@Controller
@RequestMapping("/yw/wsbdy")
public class WsbdyController extends BaseController{
	
	@Resource
	private QueryService queryService;
	
	@RequestMapping(value = { "/wsbdy", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/yw/bbdy/wsbdy";
	}
	
	
	@RequestMapping(value = { "/querypcs"}, method = RequestMethod.POST)
	public ModelAndView querypcs() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.querypcs_ws(params));
	}
	
	@RequestMapping(value = { "/getmonths"}, method = RequestMethod.POST)
	public ModelAndView getmonths() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String kssj=params.getString("kssj");
		String jssj=params.getString("jssj");
		List<VoWsbbdyxx> result = new ArrayList<VoWsbbdyxx>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");//格式化为年月
         
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
 
		try {
			min.setTime(sdf.parse(kssj));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
 
		try {
			max.setTime(sdf.parse(jssj));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
 
		Calendar curr = min;
		String dwdm=params.getString("dwdm");
		int zdy=params.getInteger("zdy");
		PoXT_DWXXB po=queryService.getpcsxx(dwdm);
		Page page=new Page();
		if(zdy==1){
			String ksd=params.getString("ksd");
			String jsd=params.getString("jsd");
			if(null!=po){
				while (curr.before(max)) {
					VoWsbbdyxx vo=new VoWsbbdyxx();
					vo.setWssj(sdf.format(curr.getTime()).replace("/", "").trim());
					vo.setKsd(ksd);
					vo.setJsd(jsd);
				 result.add(vo);
				 curr.add(Calendar.MONTH, 1);
				}
	            page.setList(result);
	            page.setTotalCount(result.size());
				return toJson(page);
			}else{
				Map map=new HashMap();
				map.put("flag", "不存在");
				return toJson(map);
			}
			
		}else{
			Map map=queryService.getwsd(dwdm);
			while (curr.before(max)) {
				VoWsbbdyxx vo=new VoWsbbdyxx();
				vo.setWssj(sdf.format(curr.getTime()).replace("/", "").trim());
				vo.setKsd(map.get("ksd")==null?null:map.get("ksd").toString());
				vo.setJsd(map.get("jsd")==null?null:map.get("jsd").toString());
			 result.add(vo);
			 curr.add(Calendar.MONTH, 1);
			}
			
	        page.setList(result);
	        page.setTotalCount(result.size());
	        return toJson(page);
		}
		
		
	}
	
	@RequestMapping(value = { "/queryws"}, method = RequestMethod.POST)
	public ModelAndView queryws() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(queryService.gewslist(params));
	}
	
	@RequestMapping(value = { "/getpcsxx"}, method = RequestMethod.POST)
	public ModelAndView getpcsxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String dm=params.getString("dwdm");
		return toJson(queryService.getpcsxx(dm));
	}

}
