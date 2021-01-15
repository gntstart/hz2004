package com.hzjc.hz2004.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.Constants;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.SimpleJson;
import com.hzjc.hz2004.base.SystemConfig;
import com.hzjc.hz2004.base.bean.SjpzBean;
import com.hzjc.hz2004.base.bean.SysCode;
import com.hzjc.hz2004.base.bean.TreeNode;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.dict.DictData;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.service.DictService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.ExceptionUtil;
import com.hzjc.hz2004.util.MemcachedUtil;

@Controller
@RequestMapping("/dict/utils")
public class DictController extends BaseController{
	static{
		try{
			DictData.reLoad(null);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	@Autowired
	DictService dictService;
	
	/**
	 * 自定义字典，获取业务地区列表
	 * @param user
	 * @param cform
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/listYwDqbm" })
	public List<SysCode> listYwDqbm() {
		String str = SystemConfig.getSystemConfig("dqlist");
		List<SysCode> list = new ArrayList<SysCode>();
		if(str!=null){
			if(str.startsWith("\"")) str = str.substring(1);
			if(str.endsWith("\"")) str = str.substring(0, str.length()-1);
			
			String[] s = str.split("&");
			for(String tmp:s){
				String[] dict = tmp.split("=");
				if(dict.length==2){
					SysCode code = new SysCode();
					code.setCode(dict[0]);
					code.setCodename(dict[1]);
					code.setVisiontype(BaseContext.getContext().getRequest().getParameter("versionType"));
					list.add(code);
				}
			}
		}
		return list;
	}
	
	@RequestMapping(value = { "/loadDcit" })
	public ModelAndView loadDcit(){
		SimpleJson json = new SimpleJson();
		try{
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			
			Map<String,List<SysCode>> map = new HashMap<String,List<SysCode>>();
			
			String str = params.getString("visiontypes");
			for(String visiontype:str.split(",")){
				List<SysCode> list = DictData.getSysCodeList(visiontype.trim().toUpperCase());
				map.put(visiontype.trim(), list);
			}
			json.setDictMap(map);
			json.setSuccess(true);
		}catch(Exception e){
			json.setSuccess(false);
			json.setMessage(ExceptionUtil.getExceptionStrace(e));
		}
		
		return toJsonObject(json);
	}
	
	@RequestMapping(value = { "/getDictRemoteLabel" })
	public ModelAndView getDictRemoteLabel(){
		SimpleJson json = new SimpleJson();
		try{
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			String visiontype =  params.getString("visiontype");
			String code = params.getString("code");
			
			List<SysCode> list = new ArrayList<>();
			String returnKey = Constants.MEMCACHED_ATTR.DICT_REMOTEDICT_ATTR  + "_" + visiontype + "_" + code;
			SysCode obj = (SysCode)MemcachedUtil.get(returnKey);
			if(obj!=null){
				list.add(obj);
			}else{
				//数据库读取
				obj = dictService.getRemoteDictItem(visiontype, code);
				if(obj!=null){
					MemcachedUtil.set(returnKey, obj);
					list.add(obj);
				}
			}
			json.setList(list);
			json.setSuccess(true);
		}catch(Exception e){
			//请求比较频繁，避免客户端alert
			json.setSuccess(true);
			json.setMessage(ExceptionUtil.getExceptionStrace(e));
		}
		
		return toJsonObject(json);
	}
	
	@RequestMapping(value = { "/querySjpzb" })
	public ModelAndView querySjpzb() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		SjpzBean  bean = dictService.querySjpzb(params);
		return toJson(bean);
	}
	
	@RequestMapping(value = { "/querySjpzbGz" })
	public ModelAndView querySjpzbGz() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		//SjpzBean  bean = dictService.querySjpzbGz(params);
		return toJson(dictService.querySjpzbGz(params));
	}
	
	@RequestMapping(value = { "/searchJlx" })
	public ModelAndView searchJlx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String search_code = params.getString("search_code");
		if(search_code!=null)
			params.put("search_code", search_code.trim().toUpperCase());
		return toJsonObject(dictService.searchJlx(params));
	}
	@RequestMapping(value = { "/searchJlxfromSumbit" })
	public ModelAndView searchJlxfromSumbit() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String search_code = params.getString("search_code");
		if(search_code!=null)
			params.put("search_code", search_code.trim().toUpperCase());
		
		return toJson(dictService.searchJlx(params));
	}
	@RequestMapping(value = { "/searchJwh" })
	public ModelAndView searchJwh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String search_code = params.getString("search_code");
		if(search_code!=null)
			params.put("search_code", search_code.trim().toUpperCase());
		
		return toJsonObject(dictService.searchJwh(params));
	}
	@RequestMapping(value = { "/searchJwhPcsXzjd" })
	public ModelAndView searchJwhPcsXzjd() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String search_code = params.getString("search_code");
		if(search_code!=null)
			params.put("search_code", search_code.trim().toUpperCase());
		
		return toJsonObject(dictService.searchJwhPcsXzjd(params));
	}
	@RequestMapping(value = { "/searchPcs" })
	public ModelAndView searchPcs() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String search_code = params.getString("search_code");
		if(search_code!=null)
			params.put("search_code", search_code.trim().toUpperCase());
		
		return toJsonObject(dictService.searchPcs(params));
	}
	/**
	 * 行政区划检索
	 * @param user
	 * @param cform
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/searchXzqh" })
	public ModelAndView searchXzqh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(dictService.searchXzqh(params));
	}
	
	@RequestMapping(value = { "/searchXxb" })
	public ModelAndView searchXxb() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(dictService.searchXxb(params));
	}
	
	/**
	 * 树节点加载
	 * @param user
	 * @param cform
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/searchTreeXzqh" })
	public ModelAndView searchTreeXzqh() {
		String pid = BaseContext.getContext().getRequest().getParameter("pid");
		List<TreeNode> list  = dictService.getTreeXzqh(pid);
		//Gson gson = new GsonBuilder().create();
		//String msg = gson.toJson(list, new TypeToken<List<TreeNode>>() {}.getType());
		return toJsonObject(list);
	}
	
	/**
	 * 获取指定配置
	 * @param user
	 * @param cform
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/queryYsjpzbJS" })
	public ModelAndView queryYsjpzbJS() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String pzlb = params.getString("pzlb");
		String returnKey = Constants.MEMCACHED_ATTR.DICT_SJPZB_ATTR  + "_js_" + pzlb;
		
		Object obj = MemcachedUtil.get(returnKey);
		if(obj!=null){
			return toJsonObject(obj);
		}else{
			return toJsonObject(dictService.queryYsjpzbJS(params));
		}
	}
	/**
	 * 下拉限制类型
	 * 20181218
	 * zjm
	 * @return
	 */
	@RequestMapping(value = { "/searchXzlx" })
	public ModelAndView searchXzlx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(dictService.searchXzlx(params));
	}
	/**
	 * 下拉审批动作
	 * 20181218
	 * zjm
	 * @return
	 */
	@RequestMapping(value = { "/searchSpdz" })
	public ModelAndView searchSpdz() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(dictService.searchSpdz(params));
	}
	/**
	 * 用户
	 * 20181220
	 * zjm
	 * @return
	 */
	@RequestMapping(value = { "/searchYhxx" })
	public ModelAndView searchYhxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(dictService.searchYhxx(params));
	}
	@RequestMapping(value = { "/searchXzqhNew" })
	public ModelAndView searchXzqhNew() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(dictService.searchXzqhNew(params));
	}
}
