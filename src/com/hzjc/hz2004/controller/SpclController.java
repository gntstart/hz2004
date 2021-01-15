package com.hzjc.hz2004.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.service.CommonService;
import com.hzjc.hz2004.service.SpService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoBgspjgclxx;
import com.hzjc.hz2004.vo.VoBgspjgclywfhxx;
import com.hzjc.hz2004.vo.VoCzrkdjbHqFhxx;
import com.hzjc.hz2004.vo.VoHjscspspxx;
import com.hzjc.hz2004.vo.VoHjscspxxHqFhxx;
import com.hzjc.hz2004.vo.VoSbjbxx;
import com.hzjc.wsstruts.vo.VoPage;
import com.hzjc.wsstruts.vo.VoQueryResult;
@Controller
@RequestMapping("/yw/spgl/spcl")
public class SpclController extends BaseController{
	@Autowired
	private SpService spService;
	
	@Autowired
	private CommonService commonService;
	@RequestMapping(value = { "/type1"}, method = RequestMethod.GET)
	public String index1() {
		return "/yw/spgl/spcl";
	}
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = { "/LoadIndex" }, method = RequestMethod.GET)
	public String queryXxxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		return "/" + params.getString("goto");
	}	
	/**
	 * 变更更正审批入口
	 * @return
	 */
	@RequestMapping(value = { "/bggzsp" }, method = RequestMethod.GET)
	public String bggzsp() {
		return "/yw/spgl/spcl_bggzsp";
	}
	
	/**
	 * 户别变更审批入口
	 * @return
	 */
	@RequestMapping(value = { "/hbbgsp" }, method = RequestMethod.GET)
	public String hbbgsp() {
		return "/yw/spgl/spcl_hbbgsp";
	}
	
	/**
	 * 户籍补录审批入口
	 * @return
	 */
	@RequestMapping(value = { "/hjblsp" }, method = RequestMethod.GET)
	public String hjblsp() {
		return "/yw/spgl/spcl_hjblsp";
	}
	
	/**
	 * 户籍删除审批入口
	 * @return
	 */
	@RequestMapping(value = { "/hjscsp" }, method = RequestMethod.GET)
	public String hjscsp() {
		return "/yw/spgl/spcl_hjscsp";
	}
	
	/**
	 *	变更更正审批子信息查询
	 * @return
	 */
	@RequestMapping(value = { "/queryBgspxx" }, method = RequestMethod.POST)
	public ModelAndView queryBgspxx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());

			String xm = params.getString("xm");
			String gmsfhm = params.getString("gmsfhm");
			String hh = params.getString("hh");
			String csrq = params.getString("csrq");
			
			/*
			 *  (  HJSP_BGSPXXB.spjg ='1'  and HJSP_BGSPXXB.lsbz = '0' and 1=1 ) 
		 (((HJXX_CZRKJBXXB.gmsfhm='430521197306235736')) 
				 and ((HJXX_CZRKJBXXB.xm='张强'))  and ((hh='1'))  
				 and ((HJXX_CZRKJBXXB.csrq='20180630')) 
				 and HJSP_BGSPXXB.spjg is  null and 1=1 )   
			 */
			
			String strHQL  = "  (HJSP_BGSPXXB.spjg ='1'  and HJSP_BGSPXXB.lsbz = '0'  ";
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
		
			VoQueryResult re = spService.queryBgspxx(strHQL, voPage);
			
			Page p = this.toPage(re);

			return toJson(p);
	}
	
	/**
	 * 户别审批信息查询
	 * @return
	 */
	@RequestMapping(value = { "/queryHbbgspxx" }, method = RequestMethod.POST)
	public ModelAndView queryHbbgspxx() {
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
			
			String strHQL  = "  (HJSP_HBBGSPSQB.spjg ='1'  and HJSP_HBBGSPSQB.lsbz = '0'  ";
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
		
			VoQueryResult re = spService.queryHbbgspxx(strHQL, voPage);
			
			Page p = this.toPage(re);

			return toJson(p);
	}
	
	/**
	 * 户籍删除审批信息查询
	 * @return
	 */
	@RequestMapping(value = { "/queryHjscspxx" }, method = RequestMethod.POST)
	public ModelAndView queryHjscspxx() {
			ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());

			String xm = params.getString("xm");
			String gmsfhm = params.getString("gmsfhm");
			String hh = params.getString("hh");
			String csrq = params.getString("csrq");
			String rynbid = params.getString("rynbid");
			
			/*
 (((HJXX_CZRKJBXXB.gmsfhm='11010819611001231X'))  
 and ((HJXX_CZRKJBXXB.xm='放大师傅'))  and ((hh='1'))  
 and ((HJXX_CZRKJBXXB.csrq='20180621'))  and HJSP_HJSCSPSQB.spjg is  null and 1=1 )    
			 */
			
			String strHQL  = "  (HJSP_HJSCSPSQB.spjg ='1'  and HJSP_HJSCSPSQB.lsbz = '0' ";
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
			if(CommonUtil.isNotEmpty(rynbid)){
				strHQL += " and HJXX_CZRKJBXXB.rynbid='" + rynbid + "' ";
			}
			strHQL += ")";
			
			VoPage voPage = this.toVoPage(params);
		
			VoQueryResult re = spService.queryHjscspxx(strHQL, voPage);
			
			Page p = this.toPage(re);

			return toJson(p);
	}
	
	
	

	/**
	 * 户籍补录信息查询
	 * @return
	 */
	@RequestMapping(value = { "/queryHjblspxx" }, method = RequestMethod.POST)
	public ModelAndView queryHjblspxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());

		String xm = params.getString("xm");
		String gmsfhm = params.getString("gmsfhm");
		String _end_slrq = params.getString("_end_djsj");
		String _start_slrq = params.getString("_start_djsj");
		String ywxl = params.getString("ywxl");
		String sshhid = params.getString("sshhid");
		String csrq = params.getString("csrq");
		
		String strHQL  = "  (HJSP_HJBLSPSQB.spjg ='1'  and HJSP_HJBLSPSQB.lsbz = '0'  ";
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
			strHQL += " and (HJSP_HJBLSPSQB.ywxl=" + ywxl + ")";
		}
		
		if(CommonUtil.isNotEmpty(sshhid)){
			strHQL += " and sshhid='" + sshhid + "' ";
		}
		
		if(CommonUtil.isNotEmpty(csrq)){
			strHQL += " and csrq=" + csrq + " ";
		}
		
		strHQL += ")";
		
		VoPage voPage = this.toVoPage(params);
	
		VoQueryResult re = spService.queryHjblspxx(strHQL, voPage);
		
		Page p = this.toPage(re);

		return toJson(p);
	}
	
	/**
	 * 变更审批子信息查询
	 * @return
	 */
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
	
	/**
	 * 变更审批结果处理业务
	 * @return
	 */
	@RequestMapping(value = { "/processBgspjgclyw" }, method = RequestMethod.POST)
	public ModelAndView processBgspjgclyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		long spywid = params.getLong("spywid");
		VoBgspjgclxx cl = new VoBgspjgclxx();
		cl.setSpywid(spywid);
		
		VoBgspjgclywfhxx re = spService.processBgspjgclyw(new VoBgspjgclxx[]{cl});
		
		return toJson(re);
	}
	
	/**
	 * 变更审批结果处理业务
	 * @return
	 */
	@RequestMapping(value = { "/processHjscspclyw" }, method = RequestMethod.POST)
	public ModelAndView processHjscspclyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String spywInfo = params.getString("spywInfo");
		VoHjscspxxHqFhxx sq = JSONUtil.getJsonData(spywInfo, "yyyyMMdd", VoHjscspxxHqFhxx.class);
		PoXT_YHXXB yh = commonService.getByID(PoXT_YHXXB.class, sq.getSqyhid());
		
		VoHjscspspxx spxx = JSONUtil.getJsonData(spywInfo, "yyyyMMdd", VoHjscspspxx.class);
		
		//申报人
		VoSbjbxx sbxx = new VoSbjbxx();
		sbxx.setSbsj(sq.getSqsj());
		sbxx.setSbryxm(yh.getYhxm());
		
		//spService.processHjscspspyw(voHjscspspxx)
		return toJson(null);
	}
	
	@RequestMapping(value = { "/setNewHz" }, method = RequestMethod.POST)
	public ModelAndView setNewHz() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		
		String xhzxx = params.getString("xhzxx");
		VoCzrkdjbHqFhxx voCzrk = null;
		if(CommonUtil.isNotEmpty(xhzxx)){
			voCzrk = JSONUtil.getJsonData(xhzxx, "yyyyMMdd", VoCzrkdjbHqFhxx.class);
		}
		
		return toJson(spService.setNewHz(voCzrk));
		
	}
	
	
	
}
