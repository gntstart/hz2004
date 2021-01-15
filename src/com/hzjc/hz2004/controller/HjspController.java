package com.hzjc.hz2004.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.hzjc.hz2004.service.CommonService;
import com.hzjc.hz2004.service.SpService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoBgspspxx;
import com.hzjc.hz2004.vo.VoBgspspywfhxx;
import com.hzjc.hz2004.vo.VoBgspzfxx;
import com.hzjc.hz2004.vo.VoBgspzfywfhxx;
import com.hzjc.hz2004.vo.VoHbbgspspxx;
import com.hzjc.hz2004.vo.VoHbbgspspywfhxx;
import com.hzjc.hz2004.vo.VoHbbgspzfxx;
import com.hzjc.hz2004.vo.VoHbbgspzfywfhxx;
import com.hzjc.hz2004.vo.VoHjblspspxx;
import com.hzjc.hz2004.vo.VoHjblspspywfhxx;
import com.hzjc.hz2004.vo.VoHjblspzfxx;
import com.hzjc.hz2004.vo.VoHjblspzfywfhxx;
import com.hzjc.hz2004.vo.VoHjscspspxx;
import com.hzjc.hz2004.vo.VoHjscspspywfhxx;
import com.hzjc.hz2004.vo.VoHjscspzfxx;
import com.hzjc.hz2004.vo.VoHjscspzfywfhxx;
import com.hzjc.hz2004.vo.VoQrspspxx;
import com.hzjc.hz2004.vo.VoQrspspywfhxx;
import com.hzjc.hz2004.vo.VoQrspzfxx;
import com.hzjc.hz2004.vo.VoQrspzfywfhxx;
import com.hzjc.wsstruts.vo.VoPage;
import com.hzjc.wsstruts.vo.VoQueryResult;
@Controller
@RequestMapping("/yw/spgl/hjsp")
public class HjspController extends BaseController{
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private SpService spService;

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	/**
	 * 户籍审批入口
	 * @return
	 */
	@RequestMapping(value = { "/type1"}, method = RequestMethod.GET)
	public String index1() {
		return "/yw/spgl/hjsp";
	}

	
	
	/**
	 * 迁入审批入口
	 * @return
	 */
	@RequestMapping(value = { "/qrsp" }, method = RequestMethod.GET)
	public String qrsp() {
		return "/yw/spgl/hjsp_qrsp";
	}
	
	/**
	 * 变更更正审批入口
	 * @return
	 */
	@RequestMapping(value = { "/bggzsp" }, method = RequestMethod.GET)
	public String bggzsp() {
		return "/yw/spgl/hjsp_bggzsp";
	}
	
	/**
	 * 户别变更审批入口
	 * @return
	 */
	@RequestMapping(value = { "/hbbgsp" }, method = RequestMethod.GET)
	public String hbbgsp() {
		return "/yw/spgl/hjsp_hbbgsp";
	}
	
	/**
	 * 户籍补录审批入口
	 * @return
	 */
	@RequestMapping(value = { "/hjblsp" }, method = RequestMethod.GET)
	public String hjblsp() {
		return "/yw/spgl/hjsp_hjblsp";
	}
	
	/**
	 * 户籍删除审批入口
	 * @return
	 */
	@RequestMapping(value = { "/hjscsp" }, method = RequestMethod.GET)
	public String hjscsp() {
		return "/yw/spgl/hjsp_hjscsp";
	}
	
	@RequestMapping(value = { "/querySpcl" }, method = RequestMethod.POST)
	public ModelAndView querySpcl() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String hql = "from PoHJSP_HJSPFDCLB where spywid=" + params.getString("spywid") + " and splx='" + params.getString("splx") + "'";
		List<?> list = commonService.queryAll(hql);
		return toJson(list);
	}
	
	/**
	 * 户别审批信息查询
	 * @return
	 */
	@RequestMapping(value = { "/queryHbbgspxxByUser" }, method = RequestMethod.POST)
	public ModelAndView queryHbbgspxxByUser() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());

			String xm = params.getString("xm");
			String gmsfhm = params.getString("gmsfhm");
			String hh = params.getString("hh");
			String csrq = params.getString("csrq");
			
			/*
		 (((HJXX_CZRKJBXXB.gmsfhm='430521197306235736')) 
				 and ((HJXX_CZRKJBXXB.xm='张强'))  and ((hh='1'))  
				 and ((HJXX_CZRKJBXXB.csrq='20180630')) 
				 and HJSP_BGSPXXB.spjg is  null and 1=1 )   
			 */
			
			String strHQL  = "  (HJSP_HBBGSPSQB.spjg is null  ";
			if(CommonUtil.isNotEmpty(xm)){
				strHQL += " and HJXX_CZRKJBXXB.xm='" + xm + "' ";
			}
			if(CommonUtil.isNotEmpty(gmsfhm)){
				strHQL += " and HJXX_CZRKJBXXB.gmsfhm='" + gmsfhm + "' ";
			}
			if(CommonUtil.isNotEmpty(csrq)){
				strHQL += " and HJXX_CZRKJBXXB.csrq='" + csrq + "' ";
			}
			if(CommonUtil.isNotEmpty(hh)){
				strHQL += " and HJXX_CZRKJBXXB.hh='" + hh + "' ";
			}
			strHQL += ")";
			
			VoPage voPage = this.toVoPage(params);
		
			VoQueryResult re = spService.queryHbbgspxxByUser(strHQL, voPage);
			
			Page p = this.toPage(re);

			return toJson(p);
	}
	
	/**
	 * 户籍删除审批信息查询
	 * @return
	 */
	@RequestMapping(value = { "/queryHjscspxxByUser" }, method = RequestMethod.POST)
	public ModelAndView queryHjscspxxByUser() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());

			String xm = params.getString("xm");
			String gmsfhm = params.getString("gmsfhm");
			String hh = params.getString("hh");
			String csrq = params.getString("csrq");
			
			/*
 (((HJXX_CZRKJBXXB.gmsfhm='11010819611001231X'))  
 and ((HJXX_CZRKJBXXB.xm='放大师傅'))  and ((hh='1'))  
 and ((HJXX_CZRKJBXXB.csrq='20180621'))  and HJSP_HJSCSPSQB.spjg is  null and 1=1 )    
			 */
			
			String strHQL  = "  (HJSP_HJSCSPSQB.spjg is null  ";
			if(CommonUtil.isNotEmpty(xm)){
				strHQL += " and HJXX_CZRKJBXXB.xm='" + xm + "' ";
			}
			if(CommonUtil.isNotEmpty(gmsfhm)){
				strHQL += " and HJXX_CZRKJBXXB.gmsfhm='" + gmsfhm + "' ";
			}
			if(CommonUtil.isNotEmpty(csrq)){
				strHQL += " and HJXX_CZRKJBXXB.csrq='" + csrq + "' ";
			}
			if(CommonUtil.isNotEmpty(hh)){
				strHQL += " and HJXX_CZRKJBXXB.hh='" + hh + "' ";
			}
			strHQL += ")";
			
			VoPage voPage = this.toVoPage(params);
		
			VoQueryResult re = spService.queryHjscspxxByUser(strHQL, voPage);
			
			Page p = this.toPage(re);

			return toJson(p);
	}
	
	/**
	 * 户籍补录审批信息查询
	 * @return
	 */
	@RequestMapping(value = { "/queryBgspxxByUser" }, method = RequestMethod.POST)
	public ModelAndView queryBgspxxByUser() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());

			String xm = params.getString("xm");
			String gmsfhm = params.getString("gmsfhm");
			String hh = params.getString("hh");
			String csrq = params.getString("csrq");
			
			/*
		 (((HJXX_CZRKJBXXB.gmsfhm='430521197306235736')) 
				 and ((HJXX_CZRKJBXXB.xm='张强'))  and ((hh='1'))  
				 and ((HJXX_CZRKJBXXB.csrq='20180630')) 
				 and HJSP_BGSPXXB.spjg is  null and 1=1 )   
			 */
			
			String strHQL  = "  (HJSP_BGSPXXB.spjg is null  ";
			if(CommonUtil.isNotEmpty(xm)){
				strHQL += " and HJXX_CZRKJBXXB.xm='" + xm + "' ";
			}
			if(CommonUtil.isNotEmpty(gmsfhm)){
				strHQL += " and HJXX_CZRKJBXXB.gmsfhm='" + gmsfhm + "' ";
			}
			if(CommonUtil.isNotEmpty(csrq)){
				strHQL += " and HJXX_CZRKJBXXB.csrq='" + csrq + "' ";
			}
			if(CommonUtil.isNotEmpty(hh)){
				strHQL += " and HJXX_CZRKJBXXB.hh='" + hh + "' ";
			}
			strHQL += ")";
			
			VoPage voPage = this.toVoPage(params);
		
			VoQueryResult re = spService.queryBgspxxByUser(strHQL, voPage);
			
			Page p = this.toPage(re);

			return toJson(p);
	}
	
	
	@RequestMapping(value = { "/queryBgspzxx" }, method = RequestMethod.POST)
	public ModelAndView queryBgspzxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		long spywid = params.getLong("spywid");

		VoPage voPage = this.toVoPage(params);
		voPage.setRecordcount(0);
		VoQueryResult re = spService.queryBgspzxx(spywid, voPage);
		
		Page p = this.toPage(re);

		return toJson(p);
	}
	
	@RequestMapping(value = { "/queryQrspxxByUser" }, method = RequestMethod.POST)
	public ModelAndView queryQrspxxByUser() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String xm = params.getString("xm");
		String gmsfhm = params.getString("gmsfhm");
		String _end_slrq = params.getString("_end_slrq");
		String _start_slrq = params.getString("_start_slrq");
		
		/*
		 (((HJSP_HJSPSQB.xm='姓名测')) 
				 and ((HJSP_HJSPSQB.gmsfhm='430521199310101019')) 
				 and ((HJSP_HJSPSQB.slrq>='20180630'))  and ((HJSP_HJSPSQB.slrq<'20180630'))  
				 and HJSP_HJSPSQB.spjg is null and  HJSP_HJSPSQB.lsbz = '0' and 1=1 )   
		 */
		
		String strHQL  = "  (HJSP_HJSPSQB.spjg is null and  HJSP_HJSPSQB.lsbz = '0' ";
		if(CommonUtil.isNotEmpty(xm)){
			strHQL += " and HJSP_HJSPSQB.xm='" + xm + "' ";
		}
		if(CommonUtil.isNotEmpty(gmsfhm)){
			strHQL += " and HJSP_HJSPSQB.gmsfhm='" + gmsfhm + "' ";
		}
		if(CommonUtil.isNotEmpty(_start_slrq)){
			strHQL += " and HJSP_HJSPSQB.slrq>='" + _start_slrq + "' ";
		}
		if(CommonUtil.isNotEmpty(_end_slrq)){
			strHQL += " and HJSP_HJSPSQB.slrq<='" + _end_slrq + "' ";
		}
		strHQL += ")";
		
		VoPage voPage = this.toVoPage(params);
	
		VoQueryResult re = spService.queryQrspxxByUser(strHQL, voPage);
		
		Page p = this.toPage(re);

		return toJson(p);
	}
	
	@RequestMapping(value = { "/queryHjblspxxByUser" }, method = RequestMethod.POST)
	public ModelAndView queryHjblspxxByUser() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String xm = params.getString("xm");
		String gmsfhm = params.getString("gmsfhm");
		String _end_slrq = params.getString("_end_djsj");
		String _start_slrq = params.getString("_start_djsj");
		//String spywid = params.getString("spywid");
		String ywxl = params.getString("ywxl");
		
		/*
 (((xm='知道'))  and ((gmsfhm='11010819611001231X'))  
 and ((djsj<'20180601152508'))  and ((djsj>='20180629152511'))  
 and HJSP_HJBLSPSQB.spjg is  null and 1=1 )   
		 */
		
		String strHQL  = "  (HJSP_HJBLSPSQB.spjg is null ";
		if(CommonUtil.isNotEmpty(xm)){
			strHQL += " and xm='" + xm + "' ";
		}
		if(CommonUtil.isNotEmpty(gmsfhm)){
			strHQL += " and gmsfhm='" + gmsfhm + "' ";
		}
		if(CommonUtil.isNotEmpty(_start_slrq)){
			strHQL += " and djsj>='" + _start_slrq + "' ";
		}
		if(CommonUtil.isNotEmpty(_end_slrq)){
			strHQL += " and djsj<='" + _end_slrq + "' ";
		}
		if(CommonUtil.isNotEmpty(ywxl)){
			strHQL += " and HJSP_HJBLSPSQB.ywxl=" + ywxl + " ";
		}
		
		strHQL += ")";
		
		VoPage voPage = this.toVoPage(params);
	
		VoQueryResult re = spService.queryHjblspxxByUser(strHQL, voPage);
		
		Page p = this.toPage(re);

		return toJson(p);
	}
	
	/**
	 * 迁入审批，审批业务
	 * @return
	 */
	@RequestMapping(value = { "/processQrspspyw" }, method = RequestMethod.POST)
	public ModelAndView processQrspspyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String s_voQrspspxx = params.getString("voQrspspxx");
		List<VoQrspspxx> voQrspspxx = new ArrayList<VoQrspspxx>();
		
		if(CommonUtil.isNotEmpty(s_voQrspspxx)){
			TypeToken<List<VoQrspspxx>> typeToken = new TypeToken<List<VoQrspspxx>>(){};
			voQrspspxx = JSONUtil.getJsonData(typeToken, s_voQrspspxx);
		}
		
		VoQrspspywfhxx fh = spService.processQrspspyw(voQrspspxx.toArray(new VoQrspspxx[]{}));
		
		return toJson(fh);
	}
	
	/**
	 * 迁入审批，迁入审批作废业务
	 * @return
	 */
	@RequestMapping(value = { "/processQrspzfyw" }, method = RequestMethod.POST)
	public ModelAndView processQrspzfyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		Long spywid = params.getLong("spywid");
		VoQrspzfxx vo = new VoQrspzfxx();
		vo.setSpywid(spywid);
		
		VoQrspzfywfhxx fh = spService.processQrspzfyw(new VoQrspzfxx[] {vo});
		
		return toJson(fh);
	}
	
	/**
	 * 处理变更审批审批业务
	 * @return
	 */
	@RequestMapping(value = { "/processBgspspyw" }, method = RequestMethod.POST)
	public ModelAndView processBgspspyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String s_voBgspspxx = params.getString("voBgspspxx");
        List<VoBgspspxx> voBgspspxx = new ArrayList<VoBgspspxx>();
    
		if(CommonUtil.isNotEmpty(s_voBgspspxx)){
			TypeToken<List<VoBgspspxx>> typeToken = new TypeToken<List<VoBgspspxx>>(){};
			voBgspspxx = JSONUtil.getJsonData(typeToken, s_voBgspspxx);
		}
		
		VoBgspspywfhxx fh = spService.processBgspspyw(voBgspspxx.toArray(new VoBgspspxx[]{}));
		
		return toJson(fh);
	}
	
	/**
	 * 迁入审批，变更审批作废业务
	 * @return
	 */
	@RequestMapping(value = { "/processBgspzfyw" }, method = RequestMethod.POST)
	public ModelAndView processBgspzfyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		Long spywid = params.getLong("spywid");
		VoBgspzfxx vo = new VoBgspzfxx();
		vo.setSpywid(spywid);
		
		VoBgspzfywfhxx fh = spService.processBgspzfyw(new VoBgspzfxx[] {vo});
		
		return toJson(fh);
	}
	
	/**
	 * 处理户别审批审批业务
	 * @return
	 */
	@RequestMapping(value = { "/processHbbgspspyw" }, method = RequestMethod.POST)
	public ModelAndView processHbbgspspyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String s_voHbbgspspxx = params.getString("voHbbgspspxx");
        List<VoHbbgspspxx> voHbbgspspxx = new ArrayList<VoHbbgspspxx>();
    
		if(CommonUtil.isNotEmpty(s_voHbbgspspxx)){
			TypeToken<List<VoHbbgspspxx>> typeToken = new TypeToken<List<VoHbbgspspxx>>(){};
			voHbbgspspxx = JSONUtil.getJsonData(typeToken, s_voHbbgspspxx);
		}
		
		VoHbbgspspywfhxx fh = spService.processHbbgspspyw(voHbbgspspxx.toArray(new VoHbbgspspxx[]{}));
		
		return toJson(fh);
	}
	
	/**
	 * 迁入审批，户别变更审批作废业务
	 * @return
	 */
	@RequestMapping(value = { "/processHbbgspzfyw" }, method = RequestMethod.POST)
	public ModelAndView processHbbgspzfyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		Long spywid = params.getLong("spywid");
		VoHbbgspzfxx vo = new VoHbbgspzfxx();
		vo.setSpywid(spywid);
		
		VoHbbgspzfywfhxx fh = spService.processHbbgspzfyw(new VoHbbgspzfxx[] {vo});
		
		return toJson(fh);
	}
	
	/**
	 * 处理户别审批审批业务
	 * @return
	 */
	@RequestMapping(value = { "/processHjblspspyw" }, method = RequestMethod.POST)
	public ModelAndView processHjblspspyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String s_voHjblspspxx = params.getString("voHjblspspxx");
        List<VoHjblspspxx> voHjblspspxx = new ArrayList<VoHjblspspxx>();
    
		if(CommonUtil.isNotEmpty(s_voHjblspspxx)){
			TypeToken<List<VoHjblspspxx>> typeToken = new TypeToken<List<VoHjblspspxx>>(){};
			voHjblspspxx = JSONUtil.getJsonData(typeToken, s_voHjblspspxx);
		}
		
		VoHjblspspywfhxx fh = spService.processHjblspspyw(voHjblspspxx.toArray(new VoHjblspspxx[]{}));
		
		return toJson(fh);
	}
	
	/**
	 * 迁入审批，户籍补录审批作废业务
	 * @return
	 */
	@RequestMapping(value = { "/processHjblspzfyw" }, method = RequestMethod.POST)
	public ModelAndView processHjblspzfyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		Long spywid = params.getLong("spywid");
		VoHjblspzfxx vo = new VoHjblspzfxx();
		vo.setSpywid(spywid);
		
		VoHjblspzfywfhxx fh = spService.processHjblspzfyw(new VoHjblspzfxx[] {vo});
		
		return toJson(fh);
	}
	
	/**
	 * 处理户籍删除审批审批业务
	 * @return
	 */
	@RequestMapping(value = { "/processHjscspspyw" }, method = RequestMethod.POST)
	public ModelAndView processHjscspspyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String s_voHjscspspxx = params.getString("voHjscspspxx");
        List<VoHjscspspxx> voHjscspspxx = new ArrayList<VoHjscspspxx>();
    
		if(CommonUtil.isNotEmpty(s_voHjscspspxx)){
			TypeToken<List<VoHjscspspxx>> typeToken = new TypeToken<List<VoHjscspspxx>>(){};
			voHjscspspxx = JSONUtil.getJsonData(typeToken, s_voHjscspspxx);
		}
		
		VoHjscspspywfhxx fh = spService.processHjscspspyw(voHjscspspxx.toArray(new VoHjscspspxx[]{}));
		
		return toJson(fh);
	}
	
	/**
	 * 迁入审批，户籍删除审批作废业务
	 * @return
	 */
	@RequestMapping(value = { "/processHjscspzfyw" }, method = RequestMethod.POST)
	public ModelAndView processHjscspzfyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		Long spywid = params.getLong("spywid");
		VoHjscspzfxx vo = new VoHjscspzfxx();
		vo.setSpywid(spywid);
		
		VoHjscspzfywfhxx fh = spService.processHjscspzfyw(new VoHjscspzfxx[] {vo});
		
		return toJson(fh);
	}
	
}
