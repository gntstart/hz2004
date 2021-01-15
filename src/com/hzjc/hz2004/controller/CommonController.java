package com.hzjc.hz2004.controller;

import com.alibaba.fastjson.JSONObject;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.SimpleJson;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.controller.JSONModel;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.exception.ServiceException;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.service.CommonService;
import com.hzjc.hz2004.service.GyService;
import com.hzjc.hz2004.service.SpService;
import com.hzjc.hz2004.service.Zj2Service;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.DateHelper;
import com.hzjc.hz2004.util.ExceptionUtil;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.EchartsTask;
import com.hzjc.wsstruts.KDSActionProxy;
import com.hzjc.wsstruts.KDSTimerTask;
import com.hzjc.wsstruts.XtpzcsbTimerTask;
import com.hzjc.wsstruts.vo.VoPage;
import com.hzjc.wsstruts.vo.VoQueryResult;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 户籍业务通用操作，主要是查询
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/yw/common")
public class CommonController extends BaseController{
	  /**
	   * 跨地市迁移数据同步
	   */
	  static public final Timer timer = new Timer();
	  static{
		 Integer i = KDSActionProxy.APP_CONFIG_JSON.getInteger("kdsqyTimerSkip");
		 if(i==null)
			 i = 60000;
		 
	    timer.schedule(new KDSTimerTask(), 60000, i);
	    timer.schedule(new XtpzcsbTimerTask(), 15000, i);
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, 01);
	    calendar.set(Calendar.MINUTE, 00);
	    calendar.set(Calendar.SECOND, 00);
	    Date time = calendar.getTime();
	    //timer.schedule(new EchartsTask(), time,24 * 60 * 60 * 1000);
	    //timer.schedule(new EchartsTask(), 5000,100000);
	    timer.schedule(new EchartsTask(), 5000,24 * 60 * 60 * 1000);
	  }
	  
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private GyService gyService;
	
	@Autowired
	private Zj2Service zj2Service;
	
	@Autowired
	private SpService spService;
	
	//查询人信息
	@RequestMapping(value = { "/queryRyxx"}, method = RequestMethod.POST)
	public ModelAndView queryPoHJXX_CZRKJBXXB() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(commonService.queryPoHJXX_CZRKJBXXB(params));
	}
	@RequestMapping(value = { "/queryRyxx1"}, method = RequestMethod.POST)
	public ModelAndView queryPoHJXX_CZRKJBXXB1() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(commonService.queryPoHJXX_CZRKJBXXB1(params));
	}
	@RequestMapping(value = { "/queryHcy"}, method = RequestMethod.POST)
	public ModelAndView queryPoHJXX_CZRKJBXXB6() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(commonService.queryPoHJXX_CZRKJBXXB6(params));
	}
	//查询准迁人员列表
	@RequestMapping(value = { "/queryZqzList"}, method = RequestMethod.POST)
	public ModelAndView queryZqzList() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(commonService.queryZqzList(params));
	}
	//查询迁移人员列表
	@RequestMapping(value = { "/queryQyzList"}, method = RequestMethod.POST)
	public ModelAndView queryQyzList() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		//List list= commonService.queryQyzList(params).getList();
		//return toJson(list);
		VoQyzdyxxHqFhxx voQczxryxx=commonService.queryQyzList(params);
		List list = new ArrayList<>();
		if(voQczxryxx.getVoQczxryxx()!=null&&voQczxryxx.getVoZzbdryxx()==null) {
			list = Arrays.asList(voQczxryxx.getVoQczxryxx());
			Collections.reverse(list);
			if(list.size()>0) {
				VoQczxryxx voQczxryxxTemp = (VoQczxryxx) list.get(0);
				voQczxryxxTemp.setSbrjtgx("01");
			}
			
		}else if(voQczxryxx.getVoZzbdryxx()!=null&&voQczxryxx.getVoQczxryxx()==null) {
			list = Arrays.asList(voQczxryxx.getVoZzbdryxx());
			Collections.reverse(list);
		}
		
		return toJson(list);
	}		
	//套打功能
	@RequestMapping(value = { "/lodop"}, method = RequestMethod.POST)
	public ModelAndView queryPoHJXX_LODOP() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		List list= commonService.queryPoHJXX_LODOP(params).getList();
		return toJson(list);
	}
	
	//查询人信息
	@RequestMapping(value = { "/queryHjxx"}, method = RequestMethod.POST)
	public ModelAndView queryPoHJXX_HXXB() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(commonService.queryPoHJXX_HXXB(params));
	}
	
	//查询人信息
	@RequestMapping(value = { "/queryXzdxx"}, method = RequestMethod.POST)
	public ModelAndView queryPoHJXX_RHFLXXB() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(commonService.queryPoHJXX_RHFLXXB(params));
	}
	
	
	
	//查询信息
	@RequestMapping(value = { "/queryPageByConf"}, method = RequestMethod.POST)
	public ModelAndView queryPageByConf() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(commonService.queryPageByConf(params));
	}
	
	//查询户籍审批业务表
	@RequestMapping(value = { "/queryHjspyw"}, method = RequestMethod.POST)
	public ModelAndView queryPoHJSP_HJSPSQB() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(commonService.queryPoHJSP_HJSPSQB(params));
	}
	
	//查询户籍审批业务字表
	@RequestMapping(value = { "/checkHjspywzb"}, method = RequestMethod.POST)
	public ModelAndView checkHjspywzb() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String kdqqy = params.getString("kdqqy");
		
		List<?> list =  commonService.queryPoHJSP_HJSPZB(params);
		if(kdqqy!=null && "1".equals(kdqqy) && list.size()>0){
			PoHJSP_HJSPZB zb = (PoHJSP_HJSPZB)list.get(0);
			PoHJSP_HJSPSQB sq = commonService.getByID(PoHJSP_HJSPSQB.class, zb.getSpywid());
			if(sq!=null && CommonUtil.isNotEmpty(sq.getKdqqy_qcdqbm()) 
					&& (sq.getKdqqy_fkzt()==null || sq.getKdqqy_fkzt().equals(""))
					 || "0".equals(sq.getKdqqy_fkzt()) || "1".equals(sq.getKdqqy_fkzt())){
//					 || sq.getKdqqy_fkzt().equals("0")  || sq.getKdqqy_fkzt().equals("1")){
				//throw new ServiceException("跨地区迁移未反馈，无法迁入！");
				// modify by zjm 20190325 后台抛出的提示，改为前台提示，并有确认框，点进去跳转迁入业务
				VoKdqTips voKdqTips= new VoKdqTips();
				voKdqTips.setKdqqy_fkzt_sqb(sq.getKdqqy_fkzt());
				voKdqTips.setKdqqy_qcdqbm_sqb(sq.getKdqqy_qcdqbm());
				voKdqTips.setPoHJSP_HJSPZB(zb);
				zb.setKdqqy_fkzt(sq.getKdqqy_fkzt());
				zb.setKdqqy_qcdqbm(sq.getKdqqy_fkzt());
				return toJson(zb);
			}
		}
		
		return toJson(list);
	}
	//查询户籍审批业务字表
	@RequestMapping(value = { "/queryHjspywzb"}, method = RequestMethod.POST)
	public ModelAndView queryPoHJSP_HJSPZB() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String kdqqy = params.getString("kdqqy");
		
		List<?> list =  commonService.queryPoHJSP_HJSPZB(params);
		return toJson(list);
	}	
	@RequestMapping(value = { "/queryTable"}, method = RequestMethod.POST)
	public ModelAndView queryTable() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String tn = params.getString("tn");
		String strHQL = params.getString("strHQL");
		VoPage voPage = super.toVoPage(params);
		VoQueryResult re = gyService.queryTable(tn, strHQL, voPage);
		return toJson(super.toPage(re));
	}
	
	/**
	 * 获取户籍信息的详细地址
	 * @return
	 */
	@RequestMapping(value = { "/queryHdxx"}, method = RequestMethod.POST)
	public ModelAndView queryHdxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		Long hhnbid = params.getLong("hhnbid");
		List list = gyService.queryHdxx(hhnbid);
		if(list.size()>0){
			VoHdxxHqFhxx  fh = (VoHdxxHqFhxx)list.get(0);
			ExtMap<String, Object> p = new ExtMap<String, Object>();
			p.put("ssxq", fh.getSsxq());
			p.put("jlx", fh.getJlx());
			p.put("mlph", fh.getMlph());
			p.put("mlxz", fh.getMlxz());
			p.put("pcs", fh.getPcs());
			p.put("zrq", fh.getZrq());
			p.put("xzjd", fh.getXzjd());
			p.put("jcwh", fh.getJcwh());
		
			Object obj = commonService.getDzxz(p);
			list.add(obj);
		}else{
			throw new ServiceException("没有找到户地信息！");
		}
		
		return toJson(list);
	}
	
	@RequestMapping(value = { "/getDzxz"}, method = RequestMethod.POST)
	public ModelAndView getDzxz() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		Object obj = commonService.getDzxz(params);
		
		JSONModel model = new JSONModel();
	    model.setSuccess(true);
	    model.setEntity(obj);
	    
		return toJsonObject(model);
	}
	
	private List<?> queryKzcsb(String kzcs){
		String hql = "from PoXT_XTKZCSB";
		if(CommonUtil.isNotEmpty(kzcs)){
			String[] pz = kzcs.split(",");
			int count=0;
			for(String pzlb:pz){
				if(CommonUtil.isEmpty(pzlb))
					continue;
				
				if(count==0){
					hql += " where kzlb='" + pzlb + "'";
				}else{
					hql += " or kzlb='" + pzlb + "'";
				}
				
				count++;
			}
		}
		
		List<?> list = commonService.queryAll(hql);
		
		return list;
	}
	
	@RequestMapping(value = { "/queryKzcsb"}, method = RequestMethod.POST)
	public ModelAndView queryKzcsb() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String kzcs = params.getString("kzcs");
		List<?> obj = queryKzcsb(kzcs);
		
		JSONModel model = new JSONModel();
	    model.setSuccess(true);
	    model.setEntity(obj);
	    model.setOpmap(getUserMap());

		return toJsonObject(model);
	}
	
	@RequestMapping(value = { "/getUserInfo"}, method = RequestMethod.POST)
	public ModelAndView getUserInfo() {
		JSONModel model = new JSONModel();
		model.setOpmap(getUserMap());
	    model.setSuccess(true);
	    
		return toJsonObject(model);
	}
	
	private Map<String,String> getUserMap(){
		AuthToken u = BaseContext.getBaseUser();
		if(u!=null){
			Map<String,String> p = new HashMap<String,String>();
			p.put("gmsfhm", u.getUser().getGmsfhm());
			p.put("yhdlm",  u.getUser().getYhdlm());
			p.put("yhid",  u.getUser().getYhid().toString());
			p.put("dwdm", u.getUser().getDwdm());
			p.put("xm", u.getUser().getYhxm());
			p.put("dwmc", u.getOrganize().getMc());
			p.put("authToken", u.getAuthToken());
			p.put("tokey", CommonUtil.getTokey());
			p.put("ip", u.getIp());
			System.out.println("--------------"+u.getAuthToken()+"--------");
			return p;
		}
		return null;
	}
	
	//户政业务处理()
	@RequestMapping(value = { "/queryHzywAndZB"}, method = RequestMethod.POST)
	public ModelAndView queryHzywAndZB() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		params.put("config_key", "queryPoHZ_ZJ_SB");
		
		Page page = commonService.queryPageByConf(params);
		params.put("config_key", "queryPoHZ_ZJ_SBZB");
		
		Page page2 = commonService.queryPageByConf(params);
		if(page2.getList()!=null){
			Map<String,Map<String,String>> map = new HashMap<>();
			for(Object obj:page2.getList()){
				PoHZ_ZJ_SBZB zb = (PoHZ_ZJ_SBZB)obj;
				Map<String,String> child = map.get(zb.getSfzh());
				if(child==null){
					child = new HashMap<String,String>();
					map.put(zb.getSfzh(), child);
				}
				child.put(zb.getPname(), zb.getPvalue());
			}
			
			if(page.getList()!=null){
				for(Object obj: page.getList()){
					PoHZ_ZJ_SB zb = (PoHZ_ZJ_SB)obj;
					if(map.containsKey(zb.getBsqrsfz())){
						zb.setZbmap(map.get(zb.getBsqrsfz()));
					}
					if(zb.getRkzp()!=null&&zb.getRkzp().length>0){
						BASE64Encoder encode = new BASE64Encoder();
						String base64 = encode.encode(zb.getRkzp());
						Pattern p = Pattern.compile("\\s*|\t|\r|\n");
					    Matcher m = p.matcher(base64);
						zb.setRkzpBase64(m.replaceAll(""));
					}
				}
			}
		}
		
		return toJson(page);
	}
	
	@RequestMapping(value = { "/executeRmoterJSON"}, method = RequestMethod.POST)
	public ModelAndView executeRmoterJSON() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String remoteUrl = params.getString("url");
		String params_str = "";
		int seek = remoteUrl.indexOf("?");
		if(seek>0){
			params_str = remoteUrl.substring(seek+1);
			if(params_str.startsWith("&"))
				params_str = params_str.substring(1);
			
			remoteUrl = remoteUrl.substring(0, seek);
		}
		String returnString = KDSActionProxy.POST(remoteUrl, params_str, "UTF-8").trim();
		
		JSONModel model = new JSONModel();
		model.setEntity(returnString);
	    model.setSuccess(true);
	    
		return toJsonObject(model);
	}
	
	@RequestMapping(value = { "/queryXt_bssqb"}, method = RequestMethod.POST)
	public ModelAndView queryXt_bssqb() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		List list= commonService.queryXt_bssqb(params);
		
		return toJson(list);
	}
	
	@RequestMapping(value = { "/getZp"}, method = RequestMethod.POST)
	public ModelAndView getZp() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		List list= commonService.getZp(params);
		if(list.size()>0){
			PoHJXX_RYZPXXB zp = (PoHJXX_RYZPXXB)list.get(0);
			zp.setZpstr(new String(com.hzjc.hz2004.base.encode.Base64.encode(zp.getZp())));
			zp.setZp(null);
		}
		
		return toJson(list);
	}
	@RequestMapping(value = { "/getYdZp"}, method = RequestMethod.POST)
	public ModelAndView getYdZp() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		List list= commonService.getYdZp(params);
		if(list.size()>0){
			PoHJXX_ZPLSB zp = (PoHJXX_ZPLSB)list.get(0);
			zp.setZpstr(new String(com.hzjc.hz2004.base.encode.Base64.encode(zp.getZp())));
			zp.setZp(null);
		}
		
		return toJson(list);
	}	
	/**
	 * 查询户籍户信息
	 * @return
	 */
	@RequestMapping(value = { "/queryHxx"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView queryHxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(commonService.queryHxx(params));
	}
	
	/**
	 * 跨地区查询户政业务信息
	 * @return
	 */
	@RequestMapping(value = { "/getKDSQY_CzrkAndDqbm"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getKDSQY_CzrkAndDqbm() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		SimpleJson sj = new SimpleJson();
		Map<String,String> p = new HashMap<>();
		try{
			zj2Service.getKDSQY_CzrkAndDqbm(params, sj);
		}catch(Exception e){
			
		}
		return toJson(null);
	}
	
	/**
	 * 跨地区查询户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/getKDSQY_dqbmAndHxx"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getKDSQY_dqbmAndHxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String gmsfhm = params.getString("gmsfhm");
		if(CommonUtil.isEmpty(gmsfhm))
			throw new ServiceException("参数gmsfhm不能为空！");
		
		//调用权限系统
        String dqbm = KDSActionProxy.getDqbm(gmsfhm);
        if(dqbm==null || dqbm.equals("") || dqbm.equals("null")){
          throw new ServiceException("此身份证不属于本省户籍！请关闭此窗口直接录入！");
        }else{
        	JSONObject json = KDSActionProxy.getRemoteConfig(dqbm);
        	
        	//{"hz2004":"http://127.0.0.1:7001/hz2004axis","dqbm":"3407","dqmc":"芜湖"}
            //特殊格式身份认证，那么使用HZADMIN进行登录，替换原来的用户令牌
        	String isnew = json.getString("isnew");
        	if(CommonUtil.isEmpty(isnew))
        		isnew = "0";
        	
        	String dqmc = json.getString("dqmc");
        	String msg = null;
        	
        	if(isnew.equals("1")){
        		//新版本
	        	String tokey = CommonUtil.getTokey();
	        	String url = json.get("hz2004").toString() + "/yw/common/queryRyxx.json";
	        	String params_str = "tokey=" + tokey + "&yhdlm=HZADMIN&dqbm=" + dqbm + "&gmsfhm=" + gmsfhm;
	        	msg = KDSActionProxy.POST(url, params_str, "UTF-8");
        	}else{
        		//老版本兼容
        		String url = json.get("hz2004").toString() + "/util/other/service?serviceName=OLD_KDS&methodName=queryRyxx&autoLogin=1";
        		String params_str =  "gmsfhm=" + gmsfhm;
	        	msg = KDSActionProxy.POST(url, params_str, "UTF-8");
        	}
        	
        	if(msg!=null){
        		int seek = msg.indexOf("{");
        		if(seek>=0){
        			String dqmsg = "\"dqbm\":\"" + dqbm + "\",\"dqmc\":\"" + dqmc + "\",";
        			msg = "{" + dqmsg + msg.substring(seek+1);
        		}
        	}
        	System.out.println(msg);
        	
        	return toJsonObject(msg);
        }
	}

	/**
	 * 跨地区查询户成员信息
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/getKDSQY_dqbmAndHcy"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getKDSQY_dqbmAndHcy() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String hhnbid = params.getString("hhnbid");
		if(CommonUtil.isEmpty(hhnbid))
			throw new ServiceException("参数hhnbid不能为空！");
		
		String dqbm = params.getString("dqbm");
		if(CommonUtil.isEmpty(dqbm))
			throw new ServiceException("参数dqbm不能为空！");
		
		String gmsfhm = params.getString("kdq_gmsfhm");
		
		//调用权限系统
       	JSONObject json = KDSActionProxy.getRemoteConfig(dqbm);
       	String isnew = json.getString("isnew");
    	if(CommonUtil.isEmpty(isnew))
    		isnew = "0";
    	
       	//{"hz2004":"http://127.0.0.1:7001/hz2004axis","dqbm":"3407","dqmc":"芜湖"}
       	//特殊格式身份认证，那么使用HZADMIN进行登录，替换原来的用户令牌
       	//{jccz=1, pzlb=10019, xm=王红, pageIndex=1, dqbm=3407, authToken=com.hzjc.hz2004.base.login.AuthToken@70b7bb82, start=0, limit=40, pageSize=40, hhnbid=3407000001000387376}
       	String dqmc = json.getString("dqmc");
       	
    	String msg = null;
    	if(isnew.equals("1")){
    		//新版本
	       	String tokey = CommonUtil.getTokey();
	       	String url = json.get("hz2004").toString() + "/yw/common/queryRyxx.json";
	       	String params_str = "tokey=" + tokey + "&yhdlm=HZADMIN&dqbm=" + dqbm + "&hhnbid=" + hhnbid + "&kdq_gmsfhm=" + gmsfhm ;
	       	
	       	/**
	       		2018.10.24
	       		新增	刁杰
	       		BugFree 205
	       		全省人口查询,户成员列表分页功能不对,每页的人员信息都一致
	       		缺少了页码及页数
	       	 */
	       	if(params.containsKey("pageIndex")) {
	       		params_str += "&pageIndex=" + params.getInteger("pageIndex");
	       	}
	       	
	       	if(params.containsKey("pageSize")) {
	       		params_str += "&pageSize=" + params.getInteger("pageSize");
	       	}
	       	
	       	
	       	String jccz = params.getString("jccz");
	       	String xm = params.getString("xm");
	       	
	       	if(CommonUtil.isNotEmpty(jccz))
	       		params_str += "&jccz=" + jccz;
	       	
	       	if(CommonUtil.isNotEmpty(xm))
	       		params_str += "&xm=" + xm;
	       	
	       	msg = KDSActionProxy.POST(url, params_str, "UTF-8");
    	}else{
    		//老版本兼容
    		String url = json.get("hz2004").toString() + "/util/other/service?serviceName=OLD_KDS&methodName=queryHcyxx&autoLogin=1";
    		
    		//精确查询
    		String params_str =  "hhnbid=" + hhnbid;
    		
    		String jccz = params.getString("jccz");
	    	if(CommonUtil.isNotEmpty(jccz)){
	    		if(CommonUtil.isNotEmpty(gmsfhm))
		       		params_str += "&gmsfhm=" + gmsfhm;
	    		
	    		String xm = params.getString("xm");
		       	if(CommonUtil.isNotEmpty(xm))
		       		params_str += "&xm=" + xm;
    		}
    		
        	msg = KDSActionProxy.POST(url, params_str, "UTF-8");
    	}
    	
       	if(msg!=null){
       		int seek = msg.indexOf("{");
       		if(seek>=0){
       			String dqmsg = "\"dqbm\":\"" + dqbm + "\",\"dqmc\":\"" + dqmc + "\",";
       			msg = "{" + dqmsg + msg.substring(seek+1);
       		}
       	}
        	
       	return toJsonObject(msg);
	}
	
	/**
	 * 查询本地跨地区迁出业务，用于本地处理异地迁出
	 * @return
	 */
	@RequestMapping(value = { "/queryKDQHjspyw"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView queryKDQHjspyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return toJson(commonService.queryKDQHjspyw(params));
	}
	
	//http://127.0.0.1:8080/hz2004/yw/common/img/render/11111
	@RequestMapping(value = {"/img/render/{zpid}"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
	@ResponseBody
	public String showImg(@PathVariable("zpid") Long zpid) {
	    // img为图片的二进制流: 3401000001001467693
		try{
		    PoHJXX_RYZPXXB zp = commonService.getByID(PoHJXX_RYZPXXB.class, zpid);
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
	@RequestMapping(value = {"/img/jwhrender/{zpid}"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
	@ResponseBody
	public String showJwhImg(@PathVariable("zpid") Long zpid) {
	    // img为图片的二进制流: 3401000001001467693
		try{
			PoHJXX_JWHZPLSB zp = commonService.getByID(PoHJXX_JWHZPLSB.class, zpid);
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
	@RequestMapping(value = {"/img/sfRender/{sfxxbid}"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
	@ResponseBody
	public String showSfImg(@PathVariable("sfxxbid") Long sfxxbid) {
	    // img为图片的二进制流: 3401000001001467693
		try{
			String strHQL = "from PoSFJFFJB where sfxxbid='" + sfxxbid + "' ";
			List sfjfxxbList = commonService.queryAll(strHQL);
			if(sfjfxxbList.size()>0) {
				PoSFJFFJB zp = (PoSFJFFJB) sfjfxxbList.get(0);
			    if(zp!=null && zp.getSfqd()!=null){
				    BaseContext.getContext().getResponse().setContentType("image/png");
				    OutputStream os =  BaseContext.getContext().getResponse().getOutputStream();
				    os.write(zp.getSfqd());
				    os.flush();
				    os.close();
			    }
			}
		    return "success";
		}catch(Exception e){
			
		}finally{
			;
		}
		
		return "error";
	}
	@RequestMapping(value = {"/img/jfRender/{sfxxbid}"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
	@ResponseBody
	public String showJfImg(@PathVariable("sfxxbid") Long sfxxbid) {
	    // img为图片的二进制流: 3401000001001467693
		try{
			String strHQL = "from PoSFJFFJB where sfxxbid='" + sfxxbid + "' ";
			List sfjfxxbList = commonService.queryAll(strHQL);
			if(sfjfxxbList.size()>0) {
				PoSFJFFJB zp = (PoSFJFFJB) sfjfxxbList.get(0);
			    if(zp!=null && zp.getJfqd()!=null){
				    BaseContext.getContext().getResponse().setContentType("image/png");
				    OutputStream os =  BaseContext.getContext().getResponse().getOutputStream();
				    os.write(zp.getJfqd());
				    os.flush();
				    os.close();
			    }
			}
		    return "success";
		}catch(Exception e){
			
		}finally{
			;
		}
		
		return "error";
	}
	@RequestMapping(value = {"/img/dwrender/{dm}"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
	@ResponseBody
	public String showDwImg(@PathVariable("dm") String dw) {
	    // img为图片的二进制流: 3401000001001467693
		try{
			PoXT_DWXXB zp = commonService.getByID(PoXT_DWXXB.class, dw);
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
	@RequestMapping(value = { "/queryMessage" }, method = RequestMethod.POST)
	public ModelAndView queryMessage() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(commonService.queryMessage(params));
	}

	@RequestMapping(value = { "/deleteMessage" }, method = RequestMethod.POST)
	public ModelAndView deleteMessage() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(commonService.deleteMessage(params));
	}
	
	@RequestMapping(value = { "/xxhf" }, method = RequestMethod.POST)
	public ModelAndView xxhf() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(commonService.xxhf(params));
	}
	
	@RequestMapping(value = { "/updateMessage" }, method = RequestMethod.POST)
	public ModelAndView updateMessage() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(commonService.updateMessage(params));
	}
	
	@RequestMapping(value = { "/checkUnReadMessage" }, method = RequestMethod.POST)
	public ModelAndView checkUnReadMessage() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(commonService.checkUnReadMessage(params));
	}
	@RequestMapping(value = { "/queryDyBh"}, method = RequestMethod.POST)
	public ModelAndView queryDyBh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		AuthToken u = BaseContext.getBaseUser();
		String hql = "from PoPERSON_DY_SET where yhid ='"+u.getUser().getYhid()+"'";
		List<?> list = commonService.queryAll(hql);

		return toJson(list);
	}
	@RequestMapping(value = { "/queryRhfl"}, method = RequestMethod.POST)
	public ModelAndView queryRhfl() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(commonService.queryRhfl(params));
	}	
	@RequestMapping(value = { "/addRhfl"}, method = RequestMethod.POST)
	public ModelAndView addRhfl() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(commonService.addRhfl(params));
	}
	@RequestMapping(value = { "/delRhfl"}, method = RequestMethod.POST)
	public ModelAndView delRhfl() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(commonService.deleteRhfl(params));
	}
	@RequestMapping(value = { "/insertSfxxb"}, method = RequestMethod.POST)
	public ModelAndView insertSfxxb() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String sfxxb = params.getString("sfxxb");
		VoSfxxb vosfxxb = null;
		if(CommonUtil.isNotEmpty(sfxxb)){
			vosfxxb = JSONUtil.getJsonData(sfxxb, "yyyyMMdd", VoSfxxb.class);
		}
		return toJson(commonService.insertSfxxb(vosfxxb));
	}
	@RequestMapping(value = { "/bjfyySave"}, method = RequestMethod.POST)
	public ModelAndView bjfyySave() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(commonService.bjfyySave(params));
	}
	@RequestMapping(value = { "/queryDycsByhh"}, method = RequestMethod.POST)
	public ModelAndView queryDycsByhh() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(commonService.queryDycsByhh(params));
	}
	@RequestMapping(value = { "/getPersonDyset"}, method = RequestMethod.POST)
	public ModelAndView getPersonDyset() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(commonService.getPersonDyset(params));
	}
	@RequestMapping(value = { "/validHhid"}, method = RequestMethod.POST)
	public ModelAndView validHhid() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(commonService.validHhid(params));
	}
	@RequestMapping(value = { "/uploadZp"}, method = RequestMethod.POST)
	@ResponseBody
	public void uploadZp(MultipartHttpServletRequest logoFile) throws IOException {
		HttpServletResponse response = BaseContext.getContext().getResponse();
		try {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			commonService.uploadZp(logoFile);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("{success:true}");
		} catch (Exception e) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("{success:false}");
			response.getWriter().close();
		}
	}
	@RequestMapping(value = { "/uploadQdZp"}, method = RequestMethod.POST)
	@ResponseBody
	public void uploadQdZp(MultipartHttpServletRequest logoFile) throws IOException {
		HttpServletResponse response = BaseContext.getContext().getResponse();
		try {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
			commonService.uploadQdZp(logoFile);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("{success:true}");
		} catch (Exception e) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("{success:false}");
			response.getWriter().close();
		}
	}	
	@RequestMapping(value = { "/checkDjJth"}, method = RequestMethod.POST)
	public ModelAndView checkDjJth() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(commonService.checkDjJth(params));
	}
	@RequestMapping(value = { "/tyuploadZp" }, method = RequestMethod.POST)
	@ResponseBody
	public void addDwDm(MultipartHttpServletRequest logoFile) throws IOException{
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		HttpServletResponse response = BaseContext.getContext().getResponse();
		try {
			//commonService.commonUploadZp(logoFile);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("{success:true}");
		}catch (Exception e) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(e.getMessage());
			response.getWriter().close();
		}
	}
	@RequestMapping(value = { "/downZp"}, method = RequestMethod.GET)
	public ModelAndView downZp() throws IOException,	ServletException{
		HttpServletRequest req = BaseContext.getContext().getRequest();
		HttpServletResponse rep = BaseContext.getContext().getResponse();
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		commonService.downZp(req,rep,params);
		return null;
	}
	
	@RequestMapping(value = { "/downExcelZip"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
	public ModelAndView downExcelZip() throws IOException,	ServletException{
		HttpServletRequest req = BaseContext.getContext().getRequest();
		HttpServletResponse rep = BaseContext.getContext().getResponse();
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		ZipOutputStream out = new ZipOutputStream(rep.getOutputStream());
		//out.putNextEntry(new ZipEntry(zipname));
		out.setEncoding("GBK");
		
		commonService.downExcelZip(req,rep,params);
		return null;
	}
}
