package com.hzjc.hz2004.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.reflect.TypeToken;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.ServiceImpl;
import com.hzjc.hz2004.base.bean.Code;
import com.hzjc.hz2004.base.bean.TreeNode;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.base.login.HSession;
import com.hzjc.hz2004.base.proc.CallParameter;
import com.hzjc.hz2004.base.proc.CallParameter.ParameterValueType;
import com.hzjc.hz2004.base.proc.CallProc;
import com.hzjc.hz2004.base.proc.InCallParameter;
import com.hzjc.hz2004.base.proc.OutCallParameter;
import com.hzjc.hz2004.constant.PublicConstant;
import com.hzjc.hz2004.dao.DAOFactory;
import com.hzjc.hz2004.dao.PojoInfo;
import com.hzjc.hz2004.po.PoDW_DY_SET;
import com.hzjc.hz2004.po.PoDZZJ_CZRZB;
import com.hzjc.hz2004.po.PoDZZJ_CZYXXB;
import com.hzjc.hz2004.po.PoECHARTSDATA;
import com.hzjc.hz2004.po.PoHJXX_CZRKJBXXB;
import com.hzjc.hz2004.po.PoHJXX_HXXB;
import com.hzjc.hz2004.po.PoHJXX_MLPXXXXB;
import com.hzjc.hz2004.po.PoHJXX_PZRZB;
import com.hzjc.hz2004.po.PoHJXX_ZPLSB;
import com.hzjc.hz2004.po.PoHJYW_BGGZXXB;
import com.hzjc.hz2004.po.PoHJYW_GMSFHMSXMFPXXB;
import com.hzjc.hz2004.po.PoHZSMK_XKRZ;
import com.hzjc.hz2004.po.PoJTTDLSB;
import com.hzjc.hz2004.po.PoLSSFZ_SLXXB;
import com.hzjc.hz2004.po.PoPERSON_DY_SET;
import com.hzjc.hz2004.po.PoSFJFFJB;
import com.hzjc.hz2004.po.PoSFXXB;
import com.hzjc.hz2004.po.PoSLXXTJ;
import com.hzjc.hz2004.po.PoSSXQQRSFFBB;
import com.hzjc.hz2004.po.PoV_HJSCSPXXB;
import com.hzjc.hz2004.po.PoV_HJXX_CZRKJBXXB;
import com.hzjc.hz2004.po.PoV_HJ_HDXXB;
import com.hzjc.hz2004.po.PoV_ZJ_YDZSLXXB;
import com.hzjc.hz2004.po.PoWW_TFBDJGXXB;
import com.hzjc.hz2004.po.PoXT_DWXXB;
import com.hzjc.hz2004.po.PoXT_JLXXXB;
import com.hzjc.hz2004.po.PoXT_JSCDQXB;
import com.hzjc.hz2004.po.PoXT_JSGNQXB;
import com.hzjc.hz2004.po.PoXT_JSXXB;
import com.hzjc.hz2004.po.PoXT_JSYWBBQXB;
import com.hzjc.hz2004.po.PoXT_JSZSBBQXB;
import com.hzjc.hz2004.po.PoXT_JWHXXB;
import com.hzjc.hz2004.po.PoXT_XTCSB;
import com.hzjc.hz2004.po.PoXT_XTGNB;
import com.hzjc.hz2004.po.PoXT_XTGNCDB;
import com.hzjc.hz2004.po.PoXT_YHHHXXB;
import com.hzjc.hz2004.po.PoXT_YHJSXXB;
import com.hzjc.hz2004.po.PoXT_YHSJFWB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.po.PoXT_YWBBMBXXB;
import com.hzjc.hz2004.po.PoXT_ZSBBMBXXB;
import com.hzjc.hz2004.po.PoYDZJ_DBLKZ;
import com.hzjc.hz2004.po.PoYDZJ_SBXXB;
import com.hzjc.hz2004.po.PoZJYW_SLXXB;
import com.hzjc.hz2004.po.PoZZZJ_DKRZB;
import com.hzjc.hz2004.po.PoZZZJ_ZZZJRZB;
import com.hzjc.hz2004.service.CommonService;
import com.hzjc.hz2004.service.DzService;
import com.hzjc.hz2004.service.HjService;
import com.hzjc.hz2004.service.LssfzService;
import com.hzjc.hz2004.service.MessageService;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.service.XtbbService;
import com.hzjc.hz2004.service.XtbgkzService;
import com.hzjc.hz2004.service.XtbssqService;
import com.hzjc.hz2004.service.XtdwxxService;
import com.hzjc.hz2004.service.XthhxlService;
import com.hzjc.hz2004.service.XtjlxService;
import com.hzjc.hz2004.service.XtjlxjwhdzService;
import com.hzjc.hz2004.service.XtjsService;
import com.hzjc.hz2004.service.XtjwhService;
import com.hzjc.hz2004.service.XtjwzrqService;
import com.hzjc.hz2004.service.XtkzcsService;
import com.hzjc.hz2004.service.XtlnwsdService;
import com.hzjc.hz2004.service.XtqyszService;
import com.hzjc.hz2004.service.XtsjzdService;
import com.hzjc.hz2004.service.XtslhxlService;
import com.hzjc.hz2004.service.XtspdzService;
import com.hzjc.hz2004.service.XtsplService;
import com.hzjc.hz2004.service.XtspmbService;
import com.hzjc.hz2004.service.XtxzjdService;
import com.hzjc.hz2004.service.XtxzqhService;
import com.hzjc.hz2004.service.XtyhService;
import com.hzjc.hz2004.service.XtyhipyxService;
import com.hzjc.hz2004.service.XtywblxzService;
import com.hzjc.hz2004.service.XtywqxService;
import com.hzjc.hz2004.service.Zj1Service;
import com.hzjc.hz2004.service.Zj2Service;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.DateHelper;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoDzzjxx;
import com.hzjc.hz2004.vo.VoDzzjywfhxx;
import com.hzjc.hz2004.vo.VoFh;
import com.hzjc.hz2004.vo.VoFxjsfxxb;
import com.hzjc.hz2004.vo.VoGmsftyr;
import com.hzjc.hz2004.vo.VoHbsxx;
import com.hzjc.hz2004.vo.VoHxxHqFhxx;
import com.hzjc.hz2004.vo.VoLztjxx;
import com.hzjc.hz2004.vo.VoMessageRtxx;
import com.hzjc.hz2004.vo.VoMessagexx;
import com.hzjc.hz2004.vo.VoPzrzxxHqFhxx;
import com.hzjc.hz2004.vo.VoQhtzxx;
import com.hzjc.hz2004.vo.VoQhtzywfhxx;
import com.hzjc.hz2004.vo.VoRyxxHqFhxx;
import com.hzjc.hz2004.vo.VoSjkxx;
import com.hzjc.hz2004.vo.VoXtsjfw;
import com.hzjc.hz2004.vo.VoXtzsbbxx;
import com.hzjc.hz2004.vo.VoYdzZjslxx;
import com.hzjc.hz2004.vo.VoYdzZjslywfhxx;
import com.hzjc.hz2004.vo.VoYdzjlqhqxx;
import com.hzjc.hz2004.vo.VoYdzjsbxx;
import com.hzjc.hz2004.vo.VoZjdbzfxx;
import com.hzjc.hz2004.vo.VoZjdbzfywfhxx;
import com.hzjc.hz2004.vo.VoZjfjtjxx;
import com.hzjc.hz2004.vo.VoZjlqffxx;
import com.hzjc.hz2004.vo.VoZjlqffywfhxx;
import com.hzjc.hz2004.vo.VoZjshfhxx;
import com.hzjc.hz2004.vo.VoZjshxx;
import com.hzjc.hz2004.vo.VoZjshywfhxx;
import com.hzjc.hz2004.vo.VoZjsjxx;
import com.hzjc.hz2004.vo.VoZjsjywfhxx;
import com.hzjc.hz2004.vo.VoZjslxx;
import com.hzjc.hz2004.vo.VoZjslywfhxx;
import com.hzjc.hz2004.vo.VoZjslzfxx;
import com.hzjc.hz2004.vo.VoZjslzfywfhxx;
import com.hzjc.hz2004.vo.VoZjxhxx;
import com.hzjc.hz2004.vo.VoZxqsgx;
import com.hzjc.hz2004.vo.VoZxyhgl;
import com.hzjc.hz2004.vo.VoZzzjRzxx;
import com.hzjc.hz2004.vo.Voywbbwhxx;
import com.hzjc.menu.Menu;
import com.hzjc.menu.MenuData;
import com.hzjc.util.StringUtils;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.WSErrCode;
import com.hzjc.wsstruts.vo.CdsVO;
import com.hzjc.wsstruts.vo.VarVO;
import com.hzjc.wsstruts.vo.VoPage;
import com.hzjc.wsstruts.vo.VoQueryResult;

import sun.misc.BASE64Encoder;

/**
 * 查询类
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */

@Service
public class QueryServiceImpl extends ServiceImpl implements QueryService {
	private PojoInfo Xt_ywbbxxbDao = DAOFactory.createXT_YWBBMBXXBDAO();
	private PojoInfo JTTDLSBDAO = DAOFactory.createJTTDLSBDAO();
	//日志处理
	protected static Log _log = LogFactory.getLog(QueryServiceImpl.class);
	@Autowired
	private XtdwxxService xtdwxxService;
	@Autowired
	private XtsjzdService xtsjzdService;
	@Autowired
	private XtxzqhService xtxzqhService;
	@Autowired
	private XtjwzrqService xtjwzrqService;
	@Autowired
	private XtxzjdService xtxzjdService;
	@Autowired
	private XtjwhService xtjwhService;
	@Autowired
	private XtjlxjwhdzService xtjlxjwhdzService;
	@Autowired
	private XtjlxService xtjlxService;
	@Autowired
	private XtbbService xtbbService;
	@Autowired
	private XtkzcsService xtkzcsService;
	@Autowired
	private XtsplService  xtsplService;
	@Autowired
	private XtspmbService xtspmbService;
	@Autowired
	private XtspdzService xtspdzService;
	@Autowired
	private XtyhipyxService xtyhipyxService;
	@Autowired
	private XtbssqService xtbssqService;
	@Autowired
	private XtqyszService xtqyszService;
	@Autowired
	private XthhxlService xthhxlService;
	@Autowired
	private XtslhxlService xtslhxlService;
	@Autowired
	private XtlnwsdService xtlnwsdService;
	@Autowired
	private XtbgkzService xtbgkzService;
	@Autowired
	private XtywblxzService xtywblxzService;
	@Autowired
	private XtywqxService xtywqxService;
	@Autowired
	private XtyhService xtyhService;
	@Autowired
	private XtjsService xtjsService;
	@Autowired
	private Zj2Service	zj2Service;
	@Autowired
	private HjService	hjService;
	@Autowired
	private Zj1Service	zj1Service;
	@Autowired
	private LssfzService lssfzService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private DzService dzService;
	@Autowired
	private CommonService commonService;
	@Override
	public Page queryRhflxx(ExtMap<String, Object> params) {
		AuthToken u = HSession.getBaseUser();
		if(u != null) {
			String where ="";
			boolean isAdmin = u.isAdmin();	
			if(!isAdmin) {
				List<PoXT_YHSJFWB> list = u.getSjfw();
				Iterator<PoXT_YHSJFWB> it = list.iterator();
				while(it.hasNext()){
				    PoXT_YHSJFWB x = it.next();
				    if(x.getXqlx().equals(PublicConstant.XQLX_XQW)){
				        it.remove();
				    }
				}
				if(CommonUtil.isEmpty(list)) {
					throw new RuntimeException("此用户没有数据范围，无法查询！");
				}
				where = "  ( 0=1 ";
				for (PoXT_YHSJFWB o:list) {
					String sjfwstr = o.getSjfw();
					if(CommonUtil.isNotEmpty(sjfwstr)){
						Set<String> set = new HashSet<String>();
						
						String sjfw[] = sjfwstr.split("\\|");
						for(int i = 0;i<sjfw.length;i++){
							String bm = sjfw[i];
							bm = bm.trim();
							
							if(CommonUtil.isEmpty(bm) || set.contains(bm))
								continue;
							if(sjfw.length==3&&i==2&&bm.length()==12) {
								where += " or a.rhfl_jcwh= '" + bm + "'";
							}
							if(sjfw.length==2&&i==1&&bm.length()==9) {
								where += " or a.rhfl_pcs= '" + bm + "'";
							}
							if(sjfw.length==1&&i==0&&bm.length()==6) {
								where += " or a.rhfl_ssxq = '" + bm + "'";
							}
							set.add(bm);
						}
					}
				}
				where += ")";
			}
			params.put("sjfw", where);
		}
		return super.getPageRecords("/conf/segment/common", "queryRhflxx", params);
	}

	@Override
	public Page queryThry(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryThry", params);
	}

	@Override
	public Page queryMlpxxcx(ExtMap<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Page queryXxxx(ExtMap<String, Object> params) {
		if(params.getString("_start_slsj") != null&&params.getString("_start_slsj").length()==8) {
			params.put("_start_slsj", params.getString("_start_slsj")+"000000");
		}
		if(params.getString("_end_slsj") != null&&params.getString("_end_slsj").length()==8) {
			params.put("_end_slsj", params.getString("_end_slsj")+"000000");
		}
		if(params.getString("_start_zxsj") != null&&params.getString("_start_zxsj").length()==8) {
			params.put("_start_zxsj", params.getString("_start_zxsj")+"000000");
		}
		if(params.getString("_end_zxsj") != null&&params.getString("_end_zxsj").length()==8) {
			params.put("_end_zxsj", params.getString("_end_zxsj")+"000000");
		}

		String allflag = params.getString("_queryAll");
		if(CommonUtil.isNotEmpty(allflag)){
			//成批户别变更使用
			List list = super.getObjectListByHql("/conf/segment/common", params.getString("config_key"), params);
			Page p = new Page();
			p.setList(list);
			return p;
		}else{
			return super.getPageRecords("/conf/segment/common", params.getString("config_key"), params);
		}
	}
	
	@Override
	public Page queryQcclxx(ExtMap<String, Object> params) {
		
		List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().getYhid().toString(), PublicConstant.GNBH_HJ_QCCLXXHQ);
		    if (sjfwList != null) {
		      StringBuffer sjfwHQL = new StringBuffer();
		      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
		        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
		        //居(村)委会
		        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
		          sjfwHQL.append(sjfwHQL.length() > 0 ?
		                         "or a.jcwh_q='" + voXtsjfw.getSjfw().trim() +
		                         "' " :
		                         "a.jcwh_q='" + voXtsjfw.getSjfw().trim() +
		                         "' ");
		        }
		        //派出所
		        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
		          sjfwHQL.append(sjfwHQL.length() > 0 ?
		                         "or a.pcs_q='" + voXtsjfw.getSjfw().trim() +
		                         "' " :
		                         "a.pcs_q='" + voXtsjfw.getSjfw().trim() +
		                         "' ");
		        }
		        //行政区划
		        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
		          sjfwHQL.append(sjfwHQL.length() > 0 ?
		                         "or a.ssxq_q='" + voXtsjfw.getSjfw().trim() +
		                         "' " :
		                         "a.ssxq_q='" +
		                         voXtsjfw.getSjfw().trim() +
		                         "' ");
		        }
		      } //for(int i = 0; i < sjfwList.size(); i++)
		      if (sjfwHQL.length() > 0) {
		    	  params.put("sjfw", "and ( " + sjfwHQL.toString() + ") ");
//		        strFromHQL.append("and ( ")
//		            .append(sjfwHQL.toString())
//		            .append(") ");
		      }
		    }
		
		if(params.containsKey("_start_slsj")) {
			params.put("_start_slsj", params.getString("_start_slsj") + "000000");
		}
		    
		if(params.containsKey("_end_slsj")) {
			params.put("_end_slsj", params.getString("_end_slsj") + "235959");
		}
		
		return super.getPageRecords("/conf/segment/common", params.getString("config_key"), params);
	}
	
	@Override
	public Page queryRkxx(ExtMap<String, Object> params) {
		
		/**
			根据选择的范围拼接查询语句
		 */
		if(params.containsKey("ryfw")) {
			int ryfw = params.getInteger("ryfw");
			AuthToken u = HSession.getBaseUser();
			if(u != null) {
				String sjfw = "";
				boolean isAdmin = u.isAdmin();
				if(1 == ryfw&&!isAdmin) {
					//所属派出所
					List listsjfw = new ArrayList();
					listsjfw = u.getSjfw();
					//XtywqxServiceImpl.getYhsjfw(u.getUser().getYhid().toString(), PublicConstant.XQLX_XQN, listsjfw);
					if (listsjfw.size() > 0) {
						String sjfwPcs = "";
						String sjfwXzqh = "";
						for (int i = 0; i < listsjfw.size(); i++) {
							PoXT_YHSJFWB fw = (PoXT_YHSJFWB) listsjfw.get(i);
							if(fw.getXqlx().equals(PublicConstant.XQLX_XQN)) {
								String sjfw1[] = fw.getSjfw().split("\\|");
								//直接获取派出所范围
								if (sjfw1.length>=2) {
									sjfwPcs += "'" + sjfw1[1]+ "',";
								}
								if (sjfw1.length==1) {
									sjfwXzqh += "'" + sjfw1[0] + "',";
								}
							}else {
								continue;
							}
							
						}
						if(sjfwXzqh.length() > 0) {
							sjfwXzqh = sjfwXzqh.substring(0, sjfwXzqh.length() - 1);
							sjfw = "(substr(a.pcs, 0, 6) in (" + sjfwXzqh;
							sjfw += ")";
							if(sjfwPcs.length() > 0) {
								sjfwPcs = sjfwPcs.substring(0, sjfwPcs.length() - 1);
								sjfw += " or pcs in (" + sjfwPcs;
								sjfw += "))";
							}else {
								sjfw += ")";
							}
						}else {
							if(sjfwPcs.length() > 0) {
								sjfwPcs = sjfwPcs.substring(0, sjfwPcs.length() - 1);
								sjfw = " pcs in (" + sjfwPcs;
								sjfw += ")";
							}
						}
//						if (sjfw.length() > 0) {
//							sjfw = sjfw.substring(0, sjfw.length() - 1);
//						}
//						if(!("").equals(sjfw)){
//							sjfw = "pcs in (" + sjfw;
//							sjfw += ")";
//						}
						
					} else {
						throw new RuntimeException("此用户没有数据范围，无法查询！");
					}
					
					//sjfw = " 1 = 1 ";
					
				}else if(2 == ryfw&&!isAdmin) {
					//所属区县
					
//					sjfw = queryDataRange(PublicConstant.GNBH_HJ_RYXXHQ_CX, u.getUser().getYhid());
					
					
					List listsjfw = new ArrayList();
					XtywqxServiceImpl.getYhsjfw(u.getUser().getYhid().toString(), PublicConstant.XQLX_XQN, listsjfw);
					if (listsjfw.size() > 0) {
						// sjfw += " (";
						for (int i = 0; i < listsjfw.size(); i++) {
							VoXtsjfw fw = (VoXtsjfw) listsjfw.get(i);
							if (PublicConstant.XT_QX_XZQH.equalsIgnoreCase(fw.getSjfwbz())) {
								sjfw += "'" + fw.getSjfw() + "',";
							}
							if (PublicConstant.XT_QX_PCS.equalsIgnoreCase(fw.getSjfwbz())) {
								sjfw += "'" + fw.getSjfw().substring(0, 6) + "',";
							}
							
						}
						if (sjfw.length() > 0) {
							sjfw = sjfw.substring(0, sjfw.length() - 1);
							sjfw = "ssxq in (" + sjfw;
							sjfw += ")";
						}else {
							throw new RuntimeException("此用户没有数据范围，无法查询！");
						}
					} else {
						throw new RuntimeException("此用户没有数据范围，无法查询！");
					}
					
				}
				params.put("cksjfw", sjfw);
			}
		}
		if(params.containsKey("mlpnbid")) {
			params.put("pageSize", 9999);
		}
		
		ParmPlus(params);
//		StringBuffer sqlList = new StringBuffer();
//		StringBuffer sqlCount = new StringBuffer();
//		StringBuffer sql = new StringBuffer();
//		sqlCount.append(" select count(*) ");
//		sqlList.append(" select  ");
//		sqlList.append("  rynbid,ryid,hhnbid,mlpnbid,zpid,gmsfhm,xm,cym,xmpy,cympy,xb,mz,csrq,cssj ")
//		.append(",csdgjdq,csdssxq,csdxz,dhhm,dhhm2,jhryxm,jhrygmsfhm,jhryjhgx,jhrexm,jhregmsfhm,jhrejhgx")
//		.append(",fqxm,fqgmsfhm,mqxm,mqgmsfhm,poxm,pogmsfhm,jggjdq,jgssxq,zjxy,whcd,hyzk,byzk,sg,xx,zy,zylb ")
//		.append(",fwcs,xxjb,hsql,hyql,hgjdqql,hssxqql,hxzql,hslbz,hylbz,hgjdqlbz,hsssqlbz,hxzlbz,swrq,swzxlb ")
//		.append(",qcrq,qczxlb,qwdgjdq,qwdssxq,qwdxz,cszmbh,cszqfrq,hylb,qtssxq,qtzz,rylb,hb,yhzgx,ryzt,rysdzt")
//		.append(",lxdbid,bz,jlbz,ywnr,cjhjywid,cchjywid,qysj,jssj,cxbz,nbsfzid,qfjg,yxqxqsrq,yxqxjzrq,zjlb")
//		.append(",swzxrq,hhid,hh,hlx,mlpid,ssxq,jlx,mlph,mlxz,pcs,zrq,xzjd,jcwh,pxh ")
//		.append(",(select b.xm from hjxx_czrkjbxxb b where b.hhnbid = a.hhnbid and b.ryzt = '0' and b.cxbz = '0' ")
//		.append(" and b.jlbz = '1' and (b.yhzgx = '01' or b.yhzgx = '02' or b.yhzgx = '03'))  hzxm ")
//		.append(",(select b.gmsfhm from hjxx_czrkjbxxb b where b.hhnbid = a.hhnbid and b.ryzt = '0' and b.cxbz = '0' ")
//		.append(" and b.jlbz = '1' and (b.yhzgx = '01' or b.yhzgx = '02' or b.yhzgx = '03'))  hzgmsfhm ")
//		.append(",(select b.xb from hjxx_czrkjbxxb b where b.hhnbid = a.hhnbid and b.ryzt = '0' and b.cxbz = '0' ")
//		.append(" and b.jlbz = '1' and (b.yhzgx = '01' or b.yhzgx = '02' or b.yhzgx = '03'))  hzxb ")
//		.append(",(select b.ryid from hjxx_czrkjbxxb b where b.hhnbid = a.hhnbid and b.ryzt = '0' and b.cxbz = '0' ")
//		.append(" and b.jlbz = '1' and (b.yhzgx = '01' or b.yhzgx = '02' or b.yhzgx = '03'))  hzryid ")
//		.append(",(select b.rynbid from hjxx_czrkjbxxb b where b.hhnbid = a.hhnbid and b.ryzt = '0' and b.cxbz = '0' ")
//		.append(" and b.jlbz = '1' and (b.yhzgx = '01' or b.yhzgx = '02' or b.yhzgx = '03'))  hzrynbid ")
//		.append(",(select count(1) from hjxx_czrkjbxxb b where b.hhnbid = a.hhnbid and b.ryzt = '0' ")
//		.append(" and b.cxbz = '0' and b.jlbz = '1' and b.xb='1') as nahcys ")
//		.append(",(select count(1) from hjxx_czrkjbxxb b where b.hhnbid = a.hhnbid and b.ryzt = '0' ")
//		.append(" and b.cxbz = '0' and b.jlbz = '2' and b.xb='1') as nvhcys ")
//		.append(",xmx,xmm,jgxz,jhryzjzl,jhryzjhm,jhrywwx,jhrywwm,jhrylxdh,jhrezjzl,jhrezjhm,jhrewwx,jhrewwm")
//		.append(",jhrelxdh,fqzjzl,fqzjhm,fqwwx,fqwwm,mqzjzl,mqzjhm,mqwwx,mqwwm,pozjzl,pozjhm,powwx,powwm")
//		.append(",qyldyy,yhkxzmc,yhkxzsj,jthzl ");
//		sqlList.append(" from ");
//		sqlCount.append(" from ");
//		if(params.containsKey("ryzl2")) {
//			sql.append(" V_HJXX_CZRKJBXXB a ");
//		}else {
//			sql.append(" HJXX_CZRKJBXXB a ");
//		}
//		sql.append(" where 1 = 1  ");
//		if(params.containsKey("where")) {
//			sql.append(" and "+params.getString("where"));
//		}
//		if(params.containsKey("xm")) {
//			sql.append(" and a.xm = '"+params.getString("xm")+"'");
//		}
//		if(params.containsKey("gmsfhm")) {
//			sql.append(" and a.gmsfhm = '"+params.getString("gmsfhm")+"'");
//		}
//		if(params.containsKey("jlx")) {
//			sql.append(" and a.jlx = '"+params.getString("jlx")+"'");
//		}
//		if(params.containsKey("mlph")) {
//			sql.append(" and (a.mlph like '%"+CommonUtil.ToDBC(params.getString("mlph"))+"%'"+" or a.mlph like '%"+CommonUtil.ToSBC(params.getString("mlph"))+"%') ");
//		}
//		if(params.containsKey("mlxz")) {
//			sql.append(" and (a.mlxz like '%"+CommonUtil.ToDBC(params.getString("mlxz"))+"%'"+" or a.mlxz like '%"+CommonUtil.ToSBC(params.getString("mlxz"))+"%') ");
//		}
//		if(params.containsKey("jthzl")) {
//			sql.append(" and a.jthzl = '"+params.getString("jthzl")+"'");
//		}
//		if(params.containsKey("pcs")) {
//			sql.append(" and a.pcs = '"+params.getString("pcs")+"'");
//		}
//		if(params.containsKey("xb")) {
//			sql.append(" and a.xb = '"+params.getString("xb")+"'");
//		}
//		if(params.containsKey("jcwh")) {
//			sql.append(" and a.jcwh = '"+params.getString("jcwh")+"'");
//		}
//		if(params.containsKey("rynbid")) {
//			sql.append(" and a.rynbid = '"+params.getString("rynbid")+"'");
//		}
//		if(params.containsKey("ryid")) {
//			sql.append(" and a.ryid = '"+params.getString("ryid")+"'");
//		}
//		if(params.containsKey("startYear")) {
//			sql.append(" and a.csrq <= "+params.getString("startYear"));
//		}
//		if(params.containsKey("endYear")) {
//			sql.append(" and a.csrq >= "+params.getString("endYear"));
//		}
//		if(params.containsKey("editendYear")) {
//			sql.append(" and a.csrq > "+params.getString("editendYear"));
//		}
//		if(params.containsKey("editstartYear")) {
//			sql.append(" and a.csrq <= "+params.getString("editstartYear"));
//		}
//		if(params.containsKey("_end_csrq1")) {
//			sql.append(" and a.csrq <= "+params.getString("_end_csrq1"));
//		}
//		if(params.containsKey("_start_csrq1")) {
//			sql.append(" and a.csrq > "+params.getString("_start_csrq1"));
//		}
//		if(params.containsKey("hh")) {
//			sql.append(" and a.hh = '"+params.getString("hh")+"'");
//		}
//		if(params.containsKey("yhkxzmc")) {
//			sql.append(" and a.yhkxzmc = '"+params.getString("yhkxzmc")+"'");
//		}
//		if(params.containsKey("hhnbid")) {
//			sql.append(" and a.hhnbid = '"+params.getString("hhnbid")+"'");
//		}
//		if(params.containsKey("csrq")) {
//			sql.append(" and a.csrq = "+params.getString("csrq"));
//		}
//		if(params.containsKey("zjlb")) {
//			sql.append(" and a.zjlb = '"+params.getString("zjlb")+"'");
//		}
//		if(params.containsKey("mlpnbid")) {
//			sql.append(" and a.mlpnbid = '"+params.getString("mlpnbid")+"'");
//		}
//		if(params.containsKey("ryfw")) {
//			if(("1".equals(params.getString("rkzt1"))||"2".equals(params.getString("rkzt2")))&&!params.containsKey("cksjfw")) {
//				sql.append(" and "+params.getString("cksjfw"));
//			}
//		}
//		if(!params.containsKey("rkzt3")) {
//			if("1".equals(params.getString("rkzt1"))&&!params.containsKey("rkzt2")&&params.containsKey("ryzl2")) {
//				sql.append(" and (a.ryzt = '0') ");
//			}
//			if("1".equals(params.getString("rkzt1"))&&!params.containsKey("rkzt2")&&!params.containsKey("ryzl2")) {
//				sql.append(" and (a.ryzt = '0' and a.jlbz = '1') ");
//			}
//			if("1".equals(params.getString("rkzt2"))&&!params.containsKey("rkzt1")&&params.containsKey("ryzl2")) {
//				sql.append(" and (a.ryzt = '1') ");
//			}
//			if("1".equals(params.getString("rkzt2"))&&!params.containsKey("rkzt1")&&!params.containsKey("ryzl2")) {
//				sql.append(" and (a.ryzt = '1' and a.jlbz = '1') ");
//			}
//			if("1".equals(params.getString("rkzt1"))&&"1".equals(params.getString("rkzt2"))&&params.containsKey("ryzl2")) {
//				sql.append(" and ((a.ryzt = '0')  or (a.ryzt = '1')) ");
//			}
//			if("1".equals(params.getString("rkzt1"))&&"1".equals(params.getString("rkzt2"))&&!params.containsKey("ryzl2")) {
//				sql.append(" and ((a.ryzt = '0' and a.jlbz = '1')  or (a.ryzt = '1' and a.jlbz = '1')) ");
//			}
//		}else {
//			sql.append(" and ( ");
//			if("1".equals(params.getString("rkzt1"))&&!params.containsKey("rkzt2")&&params.containsKey("ryzl2")) {
//				sql.append(" (a.ryzt = '0') or ");
//			}
//			if("1".equals(params.getString("rkzt1"))&&!params.containsKey("rkzt2")&&!params.containsKey("ryzl2")) {
//				sql.append(" (a.ryzt = '0' and a.jlbz = '1') or ");
//			}
//			if(!params.containsKey("rkzt1")&&"1".equals(params.getString("rkzt2"))&&params.containsKey("ryzl2")) {
//				sql.append(" (a.ryzt = '1') or ");
//			}
//			if(!params.containsKey("rkzt1")&&"1".equals(params.getString("rkzt2"))&&!params.containsKey("ryzl2")) {
//				sql.append(" (a.ryzt = '1' and a.jlbz = '1') or ");
//			}
//			if("1".equals(params.getString("rkzt1"))&&"1".equals(params.getString("rkzt2"))&&params.containsKey("ryzl2")) {
//				sql.append(" (a.ryzt = '0')  or (a.ryzt = '1') or ");
//			}
//			if("1".equals(params.getString("rkzt1"))&&"1".equals(params.getString("rkzt2"))&&!params.containsKey("ryzl2")) {
//				sql.append(" (a.ryzt = '0' and a.jlbz = '1')  or (a.ryzt = '1' and a.jlbz = '1') or ");
//			}
//			if("0".equals(params.getString("rkzt4"))) {
//				sql.append(" (a.ryzt > '1' and a.bdfw >= '21') ");
//			}
//			if("1".equals(params.getString("rkzt4"))) {
//				sql.append(" (a.ryzt > '1' and a.bdfw >= '30') ");
//			}
//			if("2".equals(params.getString("rkzt4"))) {
//				sql.append(" (a.ryzt > '1' and a.bdfw >= '40') ");
//			}
//			if("3".equals(params.getString("rkzt4"))) {
//				sql.append(" (a.ryzt > '1') ");
//			}
//			if("4".equals(params.getString("rkzt4"))) {
//				sql.append(" (a.ryzt > '1' and a.jlbz = '1') ");
//			}
//			sql.append(" ) ");
//		}
//		if(params.containsKey("ryzl1")) {
//			sql.append(" and a.cxbz = 0 ").append(" and a.jlbz='1' ");
//		}else {
//			sql.append(" and a.cxbz = 0 ");
//		}
//		if(params.containsKey("mlpnbid")) {
//			sql.append(" order by a.xm ");
//		}
//		sqlList.append(sql);
//																																																																																																																																																																																																																																																																																																																																																			sqlCount.append(sql);
//		List llist =  super.executeSqlQuery(sqlList.toString(), null, params.getInteger("pageIndex"), params.getInteger("pageSize"));
//		
//		List list = new ArrayList<>();
//		for(Object obj:llist){
//			Object[] objs = (Object[])obj;
//			VoRyxxHqFhxx vo=new VoRyxxHqFhxx();
//			vo.setRynbid(Long.parseLong(objs[0].toString()));
//			vo.setRyid(Long.parseLong(objs[1].toString()));
//			vo.setHhnbid(Long.parseLong(objs[2].toString()));
//			vo.setMlpnbid(Long.parseLong(objs[3].toString()));
//			vo.setZpid(Long.parseLong(objs[4]==null?"0":objs[4].toString()));
//			vo.setGmsfhm(objs[5]==null?" ":objs[5].toString());
//			vo.setXm(objs[6]==null?" ":objs[6].toString());
//			vo.setCym(objs[7]==null?" ":objs[7].toString());
//			vo.setXmpy(objs[8]==null?" ":objs[8].toString());
//			vo.setCympy(objs[9]==null?" ":objs[9].toString());
//			vo.setXb(objs[10]==null?" ":objs[10].toString());
//			vo.setMz(objs[11]==null?" ":objs[11].toString());
//			vo.setCsrq(objs[12]==null?" ":objs[12].toString());
//			vo.setCssj(objs[13]==null?" ":objs[13].toString());
//			vo.setCsdgjdq(objs[14]==null?" ":objs[14].toString());
//			vo.setCsdssxq(objs[15]==null?" ":objs[15].toString());
//			vo.setCsdxz(objs[16]==null?" ":objs[16].toString());
//			vo.setDhhm(objs[17]==null?" ":objs[17].toString());
//			vo.setDhhm2(objs[18]==null?" ":objs[18].toString());
//		    vo.setJhryxm(objs[19]==null?" ":objs[19].toString());
//			vo.setJhrygmsfhm(objs[20]==null?" ":objs[20].toString());
//			vo.setJhryjhgx(objs[21]==null?" ":objs[21].toString());
//			vo.setJhrexm(objs[22]==null?" ":objs[22].toString());
//			vo.setJhregmsfhm(objs[23]==null?" ":objs[23].toString());
//			vo.setJhrejhgx(objs[24]==null?" ":objs[24].toString());
//			vo.setFqxm(objs[25]==null?" ":objs[25].toString());
//			vo.setFqgmsfhm(objs[26]==null?" ":objs[26].toString());
//			vo.setMqxm(objs[27]==null?" ":objs[27].toString());
//			vo.setMqgmsfhm(objs[28]==null?" ":objs[28].toString());
//			vo.setPoxm(objs[29]==null?" ":objs[29].toString());
//			vo.setPogmsfhm(objs[30]==null?" ":objs[30].toString());
//			vo.setJggjdq(objs[31]==null?" ":objs[31].toString());
//			vo.setJgssxq(objs[32]==null?" ":objs[32].toString());
//			vo.setZjxy(objs[33]==null?" ":objs[33].toString());
//			vo.setWhcd(objs[34]==null?" ":objs[34].toString());
//			vo.setHyzk(objs[35]==null?" ":objs[35].toString());
//			vo.setByzk(objs[36]==null?" ":objs[36].toString());
//			vo.setSg(objs[37]==null?" ":objs[37].toString());
//			vo.setXx(objs[38]==null?" ":objs[38].toString());
//			vo.setZy(objs[39]==null?" ":objs[39].toString());
//			vo.setZylb(objs[40]==null?" ":objs[40].toString());
//			vo.setFwcs(objs[41]==null?" ":objs[41].toString());
//			vo.setXxjb(objs[42]==null?" ":objs[42].toString());
//			vo.setHsql(objs[43]==null?" ":objs[43].toString());
//			vo.setHyql(objs[44]==null?" ":objs[44].toString());
//			vo.setHgjdqql(objs[45]==null?" ":objs[45].toString());
//			vo.setHssxqql(objs[46]==null?" ":objs[46].toString());
//			vo.setHxzql(objs[47]==null?" ":objs[47].toString());
//			vo.setHslbz(objs[48]==null?" ":objs[48].toString());
//			vo.setHylbz(objs[49]==null?" ":objs[49].toString());
//			vo.setHgjdqlbz(objs[50]==null?" ":objs[50].toString());
//			vo.setHsssqlbz(objs[51]==null?" ":objs[51].toString());
//			vo.setHxzlbz(objs[52]==null?" ":objs[52].toString());
//			vo.setSwrq(objs[53]==null?" ":objs[53].toString());
//			vo.setSwzxlb(objs[54]==null?" ":objs[54].toString());
//			vo.setQcrq(objs[55]==null?" ":objs[55].toString());
//			vo.setQczxlb(objs[56]==null?" ":objs[56].toString());
//			vo.setQwdgjdq(objs[57]==null?" ":objs[57].toString());
//			vo.setQwdssxq(objs[58]==null?" ":objs[58].toString());
//			vo.setQwdxz(objs[59]==null?" ":objs[59].toString());
//			vo.setCszmbh(objs[60]==null?" ":objs[60].toString());
//			vo.setCszqfrq(objs[61]==null?" ":objs[61].toString());
//			vo.setHylb(objs[62]==null?" ":objs[62].toString());
//			vo.setQtssxq(objs[63]==null?" ":objs[63].toString());
//			vo.setQtzz(objs[64]==null?" ":objs[64].toString());
//			vo.setRylb(objs[65]==null?" ":objs[65].toString());
//			vo.setHb(objs[66]==null?" ":objs[66].toString());
//			vo.setYhzgx(objs[67]==null?" ":objs[67].toString());
//			vo.setRyzt(objs[68]==null?" ":objs[68].toString());
//			vo.setRysdzt(objs[69]==null?" ":objs[69].toString());
//			vo.setLxdbid(Long.parseLong(objs[70]==null?"0":objs[70].toString()));
//			vo.setBz(objs[71]==null?" ":objs[71].toString());
//			vo.setJlbz(objs[72]==null?" ":objs[72].toString());
//			vo.setYwnr(objs[73]==null?" ":objs[73].toString());
//			vo.setCjhjywid(Long.parseLong(objs[74]==null?"0":objs[74].toString()));
//			vo.setCchjywid(Long.parseLong(objs[75]==null?"0":objs[75].toString()));
//			vo.setQysj(objs[76]==null?" ":objs[76].toString());
//			vo.setJssj(objs[77]==null?" ":objs[77].toString());
//			vo.setCxbz(objs[78]==null?" ":objs[78].toString());
//			vo.setNbsfzid(Long.parseLong(objs[79]==null?"0":objs[79].toString()));
//			vo.setQfjg(objs[80]==null?" ":objs[80].toString());
//			vo.setYxqxqsrq(objs[81]==null?" ":objs[81].toString());
//			vo.setYxqxjzrq(objs[82]==null?" ":objs[82].toString());
//			vo.setZjlb(objs[83]==null?" ":objs[83].toString());
//			vo.setSwzxrq(objs[84]==null?" ":objs[84].toString());
//			vo.setHhid(Long.parseLong(objs[85]==null?"0":objs[85].toString()));
//			vo.setHh(objs[86]==null?" ":objs[86].toString());
//			vo.setHlx(objs[87]==null?" ":objs[87].toString());
//			vo.setMlpid(Long.parseLong(objs[88]==null?"0":objs[88].toString()));
//			vo.setSsxq(objs[89]==null?" ":objs[89].toString());
//			vo.setJlx(objs[90]==null?" ":objs[90].toString());
//			vo.setMlph(objs[91]==null?" ":objs[91].toString());
//			vo.setMlxz(objs[92]==null?" ":objs[92].toString());
//			vo.setPcs(objs[93]==null?" ":objs[93].toString());
//			vo.setZrq(objs[94]==null?" ":objs[94].toString());
//			vo.setXzjd(objs[95]==null?" ":objs[95].toString());
//			vo.setJcwh(objs[96]==null?" ":objs[96].toString());
//			vo.setPxh(objs[97]==null?" ":objs[97].toString());
//			vo.setHzxm(objs[98]==null?" ":objs[98].toString());
//			vo.setHzgmsfhm(objs[99]==null?" ":objs[99].toString());
//			vo.setHzxb(objs[100]==null?" ":objs[100].toString());
//			vo.setHzryid(Long.parseLong(objs[101]==null?"0":objs[101].toString()));
//			vo.setHzrynbid(Long.parseLong(objs[102]==null?"0":objs[102].toString()));
//			vo.setNahcys(Long.parseLong(objs[103]==null?"0":objs[103].toString()));
//			vo.setNvhcys(Long.parseLong(objs[104]==null?"0":objs[104].toString()));
//			vo.setXmx(objs[105]==null?" ":objs[105].toString());
//			vo.setXmm(objs[106]==null?" ":objs[106].toString());
//			vo.setJgxz(objs[107]==null?" ":objs[107].toString());
//			vo.setJhryzjzl(objs[108]==null?" ":objs[108].toString());
//			vo.setJhryzjhm(objs[109]==null?" ":objs[109].toString());
//			vo.setJhrywwx(objs[110]==null?" ":objs[110].toString());
//			vo.setJhrywwm(objs[111]==null?" ":objs[111].toString());
//			vo.setJhrylxdh(objs[112]==null?" ":objs[112].toString());
//			vo.setJhrezjzl(objs[113]==null?" ":objs[113].toString());
//			vo.setJhrezjhm(objs[114]==null?" ":objs[114].toString());
//			vo.setJhrewwx(objs[115]==null?" ":objs[115].toString());
//			vo.setJhrewwm(objs[116]==null?" ":objs[116].toString());
//			vo.setJhrelxdh(objs[117]==null?" ":objs[117].toString());
//			vo.setFqzjzl(objs[118]==null?" ":objs[118].toString());
//			vo.setFqzjhm(objs[119]==null?" ":objs[119].toString());
//			vo.setFqwwx(objs[120]==null?" ":objs[120].toString());
//			vo.setFqwwm(objs[121]==null?" ":objs[121].toString());
//			vo.setMqzjzl(objs[122]==null?" ":objs[122].toString());
//			vo.setMqzjhm(objs[123]==null?" ":objs[123].toString());
//			vo.setMqwwx(objs[124]==null?" ":objs[124].toString());
//			vo.setMqwwm(objs[125]==null?" ":objs[125].toString());
//			vo.setPozjzl(objs[126]==null?" ":objs[126].toString());
//			vo.setPozjhm(objs[127]==null?" ":objs[127].toString());
//			vo.setPowwx(objs[128]==null?" ":objs[128].toString());
//			vo.setPowwm(objs[129]==null?" ":objs[129].toString());
//			vo.setQyldyy(objs[130]==null?" ":objs[130].toString());
//			vo.setYhkxzmc(objs[131]==null?" ":objs[131].toString());
//			vo.setYhkxzsj(objs[132]==null?" ":objs[132].toString());
//			vo.setJthzl(objs[133]==null?" ":objs[133].toString());
//			list.add(vo);
//		}
//		Page  p = new Page();
//		p.setTotalCount(Integer.parseInt(super.executeSqlQuery(sqlCount.toString(), null).get(0).toString()));
//		p.setList(list);
		
		//Page p = super.executeSQLPageRecords(sqlList.toString(), null, params.getInteger("pageIndex"), params.getInteger("pageSize"));
		Page p = super.getPageRecords("/conf/segment/common", "queryPoHJXX_CZRKJBXXB3", params);
		String shuxing = params.getString("shuxing");
		//hzxm hzgmsfhm  hzxb  hzryid  hzrynbid  nahcys  nvhcys
		if(shuxing!=null&&(shuxing.contains("hzxm")||shuxing.contains("hzgmsfhm")||shuxing.contains("hzxb")||shuxing.contains("hzryid")||shuxing.contains("hzrynbid")||shuxing.contains("nahcys")||shuxing.contains("nvhcys"))) {
			//后台用子查询不好写，于是将返回的结果集去除，重新组成新的结果集再塞进Page对象中返回前台
			List<PoHJXX_CZRKJBXXB> list = (List<PoHJXX_CZRKJBXXB>) p.getList();
			List<VoRyxxHqFhxx> voRyxxHqFhxxList = new ArrayList<VoRyxxHqFhxx>();
			for(Object f:list) {
				VoRyxxHqFhxx voRyxxHqFhxx = new VoRyxxHqFhxx();
				try {
					Long hhnbid =0l;
					if(CommonUtil.isNotEmpty(params.getString("ryzl2"))) {
						PoV_HJXX_CZRKJBXXB hjxx_czrk = (PoV_HJXX_CZRKJBXXB) f;
						hhnbid = hjxx_czrk.getHhnbid(); 
					}else {
						PoHJXX_CZRKJBXXB v_hjxx_czrk = (PoHJXX_CZRKJBXXB) f;
						hhnbid = v_hjxx_czrk.getHhnbid(); 
					}
					BeanUtils.copyProperties(voRyxxHqFhxx, f);
					String querystr = " from PoHJXX_CZRKJBXXB where yhzgx<'10' and ryzt = '0' and cxbz='0' and jlbz='1' and hhnbid = '"+hhnbid+"'";
					List<PoHJXX_CZRKJBXXB> l = (List<PoHJXX_CZRKJBXXB>) super.findAllByHQL(querystr);
					if(l.size()>0) {
						PoHJXX_CZRKJBXXB hjxx_czrkjbxxb = (PoHJXX_CZRKJBXXB) l.get(0);
						voRyxxHqFhxx.setHzgmsfhm(hjxx_czrkjbxxb.getGmsfhm());
						voRyxxHqFhxx.setHzryid(hjxx_czrkjbxxb.getRyid());
						voRyxxHqFhxx.setHzrynbid(hjxx_czrkjbxxb.getRynbid());
						voRyxxHqFhxx.setHzxb(hjxx_czrkjbxxb.getXb());
						voRyxxHqFhxx.setHzxm(hjxx_czrkjbxxb.getXm());
					}
					String querystrNan = " select count(distinct RYID)  from HJXX_CZRKJBXXB  where xb = '1' and cxbz ='0' and hhnbid ='"+hhnbid+"' and ryzt='0' and jlbz='1' ";
					String querystrNv = " select count(distinct RYID)  from HJXX_CZRKJBXXB  where xb = '2' and cxbz ='0' and hhnbid ='"+hhnbid+"' and ryzt='0' and jlbz='1' ";
					Long nahcys = Long.parseLong(super.executeSqlQuery(querystrNan, null).get(0).toString());
					Long nvhcys = Long.parseLong(super.executeSqlQuery(querystrNv, null).get(0).toString());
					//	 from HJXX_CZRKJBXXB pohjxx_czr0_ where (yhzgx<'10' )and(hhnbid=3407000001000748159 )and(qysj<='20181211162243' )and(cxbz='0' ) order by  qysj desc
//					Hibernate: select count(distinct pohjxx_czr0_.RYID) as x0_0_ from HJXX_CZRKJBXXB pohjxx_czr0_ where (xb='1' )and(cxbz='0' )and(hhnbid=3407000001000748159 )and(ryzt='0' )and(jlbz='1' )
//					Hibernate: select count(distinct pohjxx_czr0_.RYID) as x0_0_ from HJXX_CZRKJBXXB pohjxx_czr0_ where (xb='2' )and(cxbz='0' )and(hhnbid=3407000001000748159 )and(ryzt='0' )and(jlbz='1' )
//					Long nahcys = 0l;
//					Long nvhcys = 0l;
//					//获取户主有关信息 hzrynbid  hzxm  nahcys  nvhcys  hzxb  hzgmsfhm  hzryid  
//					for(int i = 0;i<l.size();i++) {
//						PoHJXX_CZRKJBXXB hjxx_czrkjbxxb = (PoHJXX_CZRKJBXXB) l.get(i);
//						if(hjxx_czrkjbxxb.getYhzgx().equals("01")||hjxx_czrkjbxxb.getYhzgx().equals("02")||hjxx_czrkjbxxb.getYhzgx().equals("03")) {
//							voRyxxHqFhxx.setHzgmsfhm(hjxx_czrkjbxxb.getGmsfhm());
//							voRyxxHqFhxx.setHzryid(hjxx_czrkjbxxb.getRyid());
//							voRyxxHqFhxx.setHzrynbid(hjxx_czrkjbxxb.getRynbid());
//							voRyxxHqFhxx.setHzxb(hjxx_czrkjbxxb.getXb());
//							voRyxxHqFhxx.setHzxm(hjxx_czrkjbxxb.getXm());
//						}
//						if(hjxx_czrkjbxxb.getXb().equals("1")&&hjxx_czrkjbxxb.getRyzt().equals("0")) {
//							nahcys++;
//						}else if(hjxx_czrkjbxxb.getXb().equals("2")&&hjxx_czrkjbxxb.getRyzt().equals("0")) {
//							nvhcys++;
//						}
//					}
					voRyxxHqFhxx.setNahcys(nahcys);
					voRyxxHqFhxx.setNvhcys(nvhcys);
					voRyxxHqFhxxList.add(voRyxxHqFhxx);
					/*--a.cxhjywid,
				       --a.ywblxxid,
				       --a.chrid,
				       --a.sbhjywid,*/
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			p.setList(voRyxxHqFhxxList);
		}
		return p;
	}
	
	private String queryDataRange(String gnbhDcCzrk, long yhid) {

        String strDataRange = "";
        try {
            List lstDataRange = XtywqxServiceImpl.SelectDataRange(yhid+"", gnbhDcCzrk);
            if (lstDataRange != null && !lstDataRange.isEmpty()) {
                StringBuffer strBufDataRange = new StringBuffer();
                //遍历所有的数据范围记录集合，组合成HQL语句
                for (int i = 0; i < lstDataRange.size(); i++) {
                    VoXtsjfw vo = (VoXtsjfw) lstDataRange.get(i);
                    //如果是所有数据范围的话，将有所有权限
                    /*
                               if (vo.getSjfwbz() != null &&
                     vo.getSjfwbz().equalsIgnoreCase(PublicConstant.XT_QX_ALL)) {
                      //strDataRange = "";
                      //break;
                      return "";
                               }
                     */
                    String strSjfwbz = vo.getSjfwbz() == null ? "" :
                                       vo.getSjfwbz().trim();
                    String strSjfw = vo.getSjfw() == null ? "" :
                                     vo.getSjfw().trim();
                    String strBufCheck = strBufDataRange.toString().trim();
                    //如果是省市县（区）
                    if (PublicConstant.XT_QX_XZQH.equalsIgnoreCase(strSjfwbz)) {
                        if (!strBufCheck.equals("")) {
                            strBufDataRange.append(" or ");
                        }
                        strBufDataRange.append(" ssxq ='").append(strSjfw).
                                append("' ");
                    }
                    //派出所
                    else if (PublicConstant.XT_QX_PCS.equalsIgnoreCase(
                            strSjfwbz)) {
                        if (!strBufCheck.equals("")) {
                            strBufDataRange.append(" or ");
                        }
                        strBufDataRange.append(" pcs='").append(strSjfw).append(
                                "' ");
                    }
                    //居（村）委会
                    else if (PublicConstant.XT_QX_JWH.equalsIgnoreCase(
                            strSjfwbz)) {
                        if (!strBufCheck.equals("")) {
                            strBufDataRange.append(" or ");
                        }
                        strBufDataRange.append(" jcwh='").append(strSjfw).
                                append("' ");
                    }
                    //其他情况,不处理
                    else {
                    }
                }
                //得到组合的数据
                strDataRange = strBufDataRange.toString().trim();
                if (!strBufDataRange.toString().trim().equals("")) {
                    strDataRange = "(".concat(strDataRange).concat(")");
                }
            }
        } catch (DAOException ex) {
            throw ex;
        } catch (ServiceException ex) {
            throw ex;
        }
        return strDataRange;
    
	}

	@Override
	public Page queryQcxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoHJYW_QCZXXXB", params);
	}
	
	@Override
	public Page queryQrsp(ExtMap<String, Object> params) {
		ParmPlus(params);
		AuthToken u = HSession.getBaseUser();
		if(u != null) {
			String where ="";
			boolean isAdmin = u.isAdmin();	
			if(!isAdmin) {
				List<PoXT_YHSJFWB> list = u.getSjfw();
				Iterator<PoXT_YHSJFWB> it = list.iterator();
				while(it.hasNext()){
				    PoXT_YHSJFWB x = it.next();
				    if(x.getXqlx().equals(PublicConstant.XQLX_XQW)){
				        it.remove();
				    }
				}
				if(CommonUtil.isEmpty(list)) {
					throw new RuntimeException("此用户没有数据范围，无法查询！");
				}
				where = "  ( 0=1 ";
				for (PoXT_YHSJFWB o:list) {
					String sjfwstr = o.getSjfw();
					if(CommonUtil.isNotEmpty(sjfwstr)){
						Set<String> set = new HashSet<String>();
						
						String sjfw[] = sjfwstr.split("\\|");
						for(int i = 0;i<sjfw.length;i++){
							String bm = sjfw[i];
							bm = bm.trim();
							
							if(CommonUtil.isEmpty(bm) || set.contains(bm))
								continue;
							if(sjfw.length==3&&i==2&&bm.length()==12) {
								where += " or a.qrdjwh= '" + bm + "'";
							}
							if(sjfw.length==2&&i==1&&bm.length()==9) {
								where += " or a.qrdpcs= '" + bm + "'";
							}
							if(sjfw.length==1&&i==0&&bm.length()==6) {
								where += " or a.qrdqx = '" + bm + "'";
							}
							set.add(bm);
						}
					}
				}
				where += ")";
			}
			params.put("sjfw", where);
		}
		return super.getPageRecords("/conf/segment/common", "queryQrsp", params);
	}
	@Override
	public Page queryQrspzb(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryQrspzb", params);
	}
	@Override
	public Page queryQrsplc(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryQrsplc", params);
	}
	@Override
	public Page queryBgsp(ExtMap<String, Object> params) {
		ParmPlus(params);
		
		return super.getPageRecords("/conf/segment/common", "queryBgsp", params);
	}	
	@Override
	public Page queryBgspzb(ExtMap<String, Object> params) {
		Page page = super.getPageRecords("/conf/segment/common", "queryBgspzb", params);
		return page;
	}
	@Override
	public Page queryBgsplc(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryBgsplc", params);
	}
	@Override
	public Page queryHbbgsp(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryHbbgsp", params);
	}
	@Override
	public Page queryHbbgsplc(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryHbbgsplc", params);
	}
	@Override
	public Page queryHjblsp(ExtMap<String, Object> params) {
		ParmPlus(params);
		AuthToken u = HSession.getBaseUser();
		if(u != null) {
			String where ="";
			boolean isAdmin = u.isAdmin();	
			if(!isAdmin) {
				List<PoXT_YHSJFWB> list = BaseContext.getUser().getSjfw();
				Iterator<PoXT_YHSJFWB> it = list.iterator();
				while(it.hasNext()){
				    PoXT_YHSJFWB x = it.next();
				    if(x.getXqlx().equals(PublicConstant.XQLX_XQW)){
				        it.remove();
				    }
				}
				if(CommonUtil.isEmpty(list)) {
					throw new RuntimeException("此用户没有数据范围，无法查询！");
				}
				where = "  ( 0=1 ";
				for (PoXT_YHSJFWB o:list) {
					String sjfwstr = o.getSjfw();
//					if(!o.getXqlx().equals(PublicConstant.XQLX_XQN)) {
//						continue;
//					}
					if(CommonUtil.isNotEmpty(sjfwstr)){
						Set<String> set = new HashSet<String>();
						
						String sjfw[] = sjfwstr.split("\\|");
						for(int i = 0;i<sjfw.length;i++){
							String bm = sjfw[i];
							bm = bm.trim();
							
							if(CommonUtil.isEmpty(bm) || set.contains(bm))
								continue;
							if(sjfw.length==3&&i==2&&bm.length()==12) {
								where += " or a.ssjwh= '" + bm + "'";
							}
							if(sjfw.length==2&&i==1&&bm.length()==9) {
								where += " or a.sspcs= '" + bm + "'";
							}
							if(sjfw.length==1&&i==0&&bm.length()==6) {
								where += " or a.ssssxq = '" + bm + "'";
							}
							set.add(bm);
						}
					}
				}
				where += ")";
			}
			params.put("sjfw", where);
		}
		return super.getPageRecords("/conf/segment/common", "queryHjblsp", params);
	}

	@Override
	public Page queryHjblsplc(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryHjblsplc", params);
	}
	@Override
	public Page queryHjscsp(ExtMap<String, Object> params) {
		ParmPlus(params);
		StringBuffer sql = new StringBuffer();
		sql.append("from " + PoV_HJSCSPXXB.class.getName()+" a where 1=1 ");
		if(params.getString("xm") != null) {
			sql.append("and a.xm = "+params.getString("xm"));
		}
		return super.getPageRecords("/conf/segment/common", "queryHjscsp", params);
//		List<PoV_HJSCSPXXB> temp =super.getPageRecords("/conf/segment/common", "queryHjscsp", params);
//		List<PoV_HJSCSPXXB> result = new ArrayList<>();
//		if(temp.size()>0) {
//			result.add(temp.get(0));
//			for(int i=1;i<temp.size();i++) {
//				PoV_HJSCSPXXB po1 = temp.get(i);
//				boolean flag = false;
//				for(int j=0;j<result.size();j++) {
//					PoV_HJSCSPXXB po2 = result.get(j);
//					if(po2.getSpywid()==po1.getSpywid()) {
//						flag = true;
//						result.remove(po2);
//						break;
//					}
//					flag = true;
//				}
//				if(flag) {
//					result.add(po1);
//				}
//			}
//		}
//		return result;
		 
	}
	/*@Override
	public Page queryHjscsp(ExtMap<String, Object> params) {
		ParmPlus(params);
		
		
		Page page = super.getPageRecords("/conf/segment/common", "queryHjscsp", params);
		
		
		
		List<PoV_HJSCSPXXB> temp =(List<PoV_HJSCSPXXB>) super.getObjectListByHql("/conf/segment/common", "queryHjscsp", params);
		List<PoV_HJSCSPXXB> result = new ArrayList<>();
		if(temp.size()>0) {
			result.add(temp.get(0));
			for(int i=1;i<temp.size();i++) {
				PoV_HJSCSPXXB po1 = temp.get(i);
				boolean flag = false;
				for(int j=0;j<result.size();j++) {
					PoV_HJSCSPXXB po2 = result.get(j);
					if(po2.getSpywid()==po1.getSpywid()) {
						flag = true;
						result.remove(po2);
						break;
					}
					flag = true;
				}
				if(flag) {
					result.add(po1);
				}
			}
		}
		return result;
		
		
		
		return page;
	}
	*/
	@Override
	public Page queryHjscsplc(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryHjscsplc", params);
	}
	/**
		处理年龄段
	 */
	private void ParmPlus(ExtMap<String, Object> params) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if(params.getInteger("_start_csrq") != null) {
			Calendar stcalendar=Calendar.getInstance();   
			stcalendar.setTime(new Date()); 
			int start = params.getInteger("_start_csrq");
			stcalendar.add(1, -start);
			String startYear = sdf.format(stcalendar.getTime());
			params.put("startYear", startYear);
			//params.put("startYear", start+"");
		}
		if(params.getInteger("_end_csrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_end_csrq");
			edcalendar.add(1, -end);
			String endYear = sdf.format(edcalendar.getTime());
			params.put("endYear", endYear);
//			params.put("endYear", end+"");
		}
		if(params.getInteger("_start_csrqedit") != null) {
			Calendar stcalendar=Calendar.getInstance();   
			stcalendar.setTime(new Date()); 
			int start = params.getInteger("_start_csrqedit");
			stcalendar.add(1, -start);
			String startYear = sdf.format(stcalendar.getTime());
			params.put("editstartYear", startYear+"");
		}
		if(params.getInteger("_end_csrqedit") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_end_csrqedit");
			edcalendar.add(1, -end);
			String endYear = sdf.format(edcalendar.getTime());
			params.put("editendYear", endYear+"");
		}
		if(params.getInteger("_start_csrq2") != null) {
			Calendar stcalendar=Calendar.getInstance();   
			stcalendar.setTime(new Date()); 
			int start = params.getInteger("_start_csrq2");
//			stcalendar.add(1, -start);
//			String startYear = sdf.format(stcalendar.getTime());
			params.put("startYear2", start);
		}
		if(params.getInteger("_end_csrq2") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_end_csrq2");
//			edcalendar.add(1, -end);
//			String endYear = sdf.format(edcalendar.getTime());
			params.put("endYear2", end);
		}
		if(params.getInteger("_start_czsj") != null) {
			Calendar stcalendar=Calendar.getInstance();   
			stcalendar.setTime(new Date()); 
			int start = params.getInteger("_start_czsj");
			//stcalendar.add(1, -start);
			//String startYear = sdf.format(stcalendar.getTime());
			params.put("czstartYear", start+"");
		}
		if(params.getInteger("_end_czsj") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_end_czsj");
			//edcalendar.add(1, -end);
			//String endYear = sdf.format(edcalendar.getTime());
			params.put("czendYear", end+"");
		}
		if(params.getInteger("_start_qrrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_start_qrrq");
			edcalendar.add(1, -end);
			String endYear = sdf.format(edcalendar.getTime());
			params.put("qrstartYear", endYear);
		}
		if(params.getInteger("_end_qrrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_end_qrrq");
			edcalendar.add(1, -end);
			String endYear = sdf.format(edcalendar.getTime());
			params.put("qrendYear", endYear);
		}
//		if(params.getInteger("_start_slsj") != null) {
//			Calendar edcalendar=Calendar.getInstance();   
//			edcalendar.setTime(new Date()); 
//			int start = params.getInteger("_start_slsj");
//			edcalendar.add(1, -end);
//			String endYear = sdf.format(edcalendar.getTime());
//			params.put("slstartYear", start+"");
//		}
//		if(params.getInteger("_end_slsj") != null) {
//			Calendar edcalendar=Calendar.getInstance();   
//			edcalendar.setTime(new Date()); 
//			int end = params.getInteger("_end_slsj");
//			edcalendar.add(1, -end);
//			String endYear = sdf.format(edcalendar.getTime());
//			params.put("slendYear", end+"");
//		}
		if(params.getInteger("_start_zzbdrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_start_zzbdrq");
			edcalendar.add(1, -end);
			String endYear = sdf.format(edcalendar.getTime());
			params.put("bdstartYear", endYear);
		}
		if(params.getInteger("_end_zzbdrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_end_zzbdrq");
			edcalendar.add(1, -end);
			String endYear = sdf.format(edcalendar.getTime());
			params.put("bdendYear", endYear);
		}
		if(params.getInteger("_start_bggzrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_start_bggzrq");
			edcalendar.add(1, -end);
			String endYear = sdf.format(edcalendar.getTime());
			params.put("bgstartYear", endYear);
		}
		if(params.getInteger("_end_bggzrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_end_bggzrq");
			edcalendar.add(1, -end);
			String endYear = sdf.format(edcalendar.getTime());
			params.put("bgendYear", endYear);
		}
		if(params.getInteger("_start_hcybdrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_start_hcybdrq");
			edcalendar.add(1, -end);
			String endYear = sdf.format(edcalendar.getTime());
			params.put("hcybdstartYear", endYear);
		}
		if(params.getInteger("_end_hcybdrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_end_hcybdrq");
			edcalendar.add(1, -end);
			String endYear = sdf.format(edcalendar.getTime());
			params.put("hcybdendYear", endYear);
		}
		if(params.getInteger("_start_yxqxqsrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_start_yxqxqsrq");
			//edcalendar.add(1, -end);
			//String endYear = sdf.format(edcalendar.getTime());
			params.put("yxqxqsstartYear", end+"");
		}
		if(params.getInteger("_end_yxqxqsrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_end_yxqxqsrq");
			//edcalendar.add(1, -end);
			//String endYear = sdf.format(edcalendar.getTime());
			params.put("yxqxqsendYear", end+"");
		}
		if(params.getInteger("_start_hksj") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_start_hksj");
			//edcalendar.add(1, -end);
			//String endYear = sdf.format(edcalendar.getTime());
			params.put("hksjstartYear", end+"");
		}
		if(params.getInteger("_end_hksj") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_end_hksj");
			//edcalendar.add(1, -end);
			//String endYear = sdf.format(edcalendar.getTime());
			params.put("hksjsendYear", end+"");
		}
		if(params.getInteger("_start_clrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_start_clrq");
			edcalendar.add(1, -end);
			String endYear = sdf.format(edcalendar.getTime());
			params.put("clrqstartYear", end+"");
		}
		if(params.getInteger("_end_clrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_end_clrq");
			edcalendar.add(1, -end);
			String endYear = sdf.format(edcalendar.getTime());
			params.put("clrqsendYear", end+"");
		}
		if(params.getInteger("_start_qfrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int start = params.getInteger("_start_qfrq");
			params.put("qfrqstartYear", start+"");
		}
		if(params.getInteger("_end_qfrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_end_qfrq");
			params.put("qfrqendYear", end+"");
		}
		if(params.getInteger("_start_yxqxjzrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int start = params.getInteger("_start_yxqxjzrq");
			params.put("yxqxjzrqstartYear", start+"");
		}
		if(params.getInteger("_end_yxqxjzrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_end_yxqxjzrq");
			params.put("yxqxjzrqendYear", end+"");
		}
		if(params.getInteger("_start_shrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_start_shrq");
			edcalendar.add(1, -end);
			String endYear = sdf.format(edcalendar.getTime());
			params.put("shrqstartYear", endYear);
		}
		if(params.getInteger("_end_shrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_end_shrq");
			edcalendar.add(1, -end);
			String endYear = sdf.format(edcalendar.getTime());
			params.put("shrqsendYear", endYear);
		}
		if(params.getInteger("_start_xhrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_start_xhrq");
			edcalendar.add(1, -end);
			String endYear = sdf.format(edcalendar.getTime());
			params.put("xhrqstartYear", endYear);
		}
		if(params.getInteger("_end_xhrq") != null) {
			Calendar edcalendar=Calendar.getInstance();   
			edcalendar.setTime(new Date()); 
			int end = params.getInteger("_end_xhrq");
			edcalendar.add(1, -end);
			String endYear = sdf.format(edcalendar.getTime());
			params.put("xhrqsendYear", endYear);
		}
		
		if(StringUtils.isNotEmpty(params.getString("dqyh"))) {
			params.put("yh", this.getUserInfo().getYhid().toString());
		}
		
		if(StringUtils.isNotEmpty(params.getString("sjfw"))) {
			List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().getYhid().toString(), PublicConstant.GNBH_HJ_QCCLXXHQ);
			if (sjfwList != null) {
				StringBuffer sjfwHQL = new StringBuffer();
				for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
					VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
					//居(村)委会
					if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
						sjfwHQL.append(sjfwHQL.length() > 0 ?
							"or a.jcwh_q='" + voXtsjfw.getSjfw().trim() +  "' " :
							"a.jcwh_q='" + voXtsjfw.getSjfw().trim() + "' ");
					}
					//派出所
					else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
						sjfwHQL.append(sjfwHQL.length() > 0 ?
							"or a.pcs_q='" + voXtsjfw.getSjfw().trim() + "' " :
							"a.pcs_q='" + voXtsjfw.getSjfw().trim() + "' ");
					}
					//行政区划
					else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
						sjfwHQL.append(sjfwHQL.length() > 0 ?
							"or a.ssxq_q='" + voXtsjfw.getSjfw().trim() + "' " :
							"a.ssxq_q='" + voXtsjfw.getSjfw().trim() + "' ");
					}
				} //for(int i = 0; i < sjfwList.size(); i++)
				if (sjfwHQL.length() > 0) {
					params.put("sjfw", " ( " + sjfwHQL.toString() + ") ");
				}
			}
		}
		
		
	}

	@Override
	public Page queryVoHxxHqFhxx(ExtMap<String, Object> params) {
		
		
		List<PoHJXX_HXXB> l1 = (List<PoHJXX_HXXB>) super.getObjectListByHql("/conf/segment/common", "queryPoHJXX_HXXB2", params);
		if(l1 != null && l1.size() > 0) {
			PoHJXX_HXXB hxx = l1.get(0);
			PoHJXX_MLPXXXXB mlp = super.get(PoHJXX_MLPXXXXB.class, hxx.getMlpnbid());
			
			 VoHxxHqFhxx voHxxHqFhxx = new VoHxxHqFhxx(hxx,mlp);
			 
			 List<VoHxxHqFhxx> l3 = new ArrayList<VoHxxHqFhxx>();
			 l3.add(voHxxHqFhxx);
			 Page page = new Page(l3);
			 return page;
		}
		
		
		return null;
	}

	@Override
	public Page getHxx(ExtMap<String, Object> params) {
//		if(params.getInteger("_start_jhsj") != null) {
//			params.put("_start_jhsj", params.getInteger("_start_jhsj")+"000000");
//		}
//		if(params.getInteger("_end_jhsj") != null) {
//			params.put("_end_jhsj", params.getInteger("_end_jhsj")+"000000");
//		}
		if(params.getInteger("_start_chsj") != null) {
			params.put("_start_chsj", params.getInteger("_start_chsj")+"000000");
		}
		if(params.getInteger("_end_chsj") != null) {
			params.put("_end_chsj", params.getInteger("_end_chsj")+"000000");
		}
		
		Page result = new Page();
		Page page = new Page();
		List<PoV_HJ_HDXXB> resultList = new ArrayList<PoV_HJ_HDXXB>();
		if(params.getString("queryXx") != null) {
			page = super.getPageRecords("/conf/segment/common", params.getString("queryXx"), params);
		}else {
			page = super.getPageRecords("/conf/segment/common", "queryhxx", params);
		}
		if(page.getList() != null && page.getList().size() > 0) {
			for(int i=0;i<page.getList().size();i++) {
				Object[] objs = (Object[]) page.getList().get(i);
				PoHJXX_HXXB hxx = (PoHJXX_HXXB) objs[0];
				PoHJXX_MLPXXXXB mlp = (PoHJXX_MLPXXXXB) objs[1];
				
				PoXT_JLXXXB jlx = null ;
				if(objs.length == 3) {
					jlx = (PoXT_JLXXXB) objs[2];
				}
				
				PoV_HJ_HDXXB vo = new PoV_HJ_HDXXB();
				try {
					BeanUtils.copyProperties(vo, hxx);
					BeanUtils.copyProperties(vo, mlp);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
				if(jlx != null) {
					vo.setHmc(jlx.getMc());
				}
				
				vo.setHcjhjywid(hxx.getCjhjywid());
				vo.setHcchjywid(hxx.getCchjywid());
				vo.setHlxdbid(hxx.getLxdbid());
				vo.setHjlbz(hxx.getJlbz());
				vo.setHqysj(hxx.getQysj());
				vo.setHjssj(hxx.getJssj());
				vo.setCxbz(hxx.getCxbz());
				vo.setJhsj(hxx.getJhsj());
				vo.setBdyy(hxx.getBdyy());
				vo.setDcjhjywid(mlp.getCjhjywid());
				vo.setDcchjywid(mlp.getCchjywid());
				vo.setDlxdbid(mlp.getLxdbid());
				vo.setDjlbz(mlp.getJlbz());
				vo.setDqysj(mlp.getQysj());
				vo.setDjssj(mlp.getJssj());
				vo.setHxx(hxx);
				vo.setMlpxx(mlp);
				
				if(objs.length == 3) {
					vo.setJlxxx(jlx);
				}
				
				resultList.add(vo);
			}
		}
		result.setTotalCount(page.getTotalCount());
		result.setList(resultList);
		
		return result;
	}

	@Override
	public Page getMlpxx(ExtMap<String, Object> params) {
		if(params.getString("mlph") != null) {
			params.put("mlphToDBC", CommonUtil.ToDBC(params.getString("mlph")));
			params.put("mlphToSBC", CommonUtil.ToSBC(params.getString("mlph")));
		}
		if(params.getString("mlxz") != null) {
			params.put("mlxzToDBC", CommonUtil.ToDBC(params.getString("mlxz")));
			params.put("mlxzToSBC", CommonUtil.ToSBC(params.getString("mlxz")));
		}
		return super.getPageRecords("/conf/segment/common", "queryMlpxx", params);
	}

	@Override
	public Page queryHdxx(ExtMap<String, Object> params) {
		
		Page result = new Page();
		List<PoV_HJ_HDXXB> resultList = new ArrayList<PoV_HJ_HDXXB>();
		
		Page page = super.getPageRecords("/conf/segment/common", params.getString("queryXx"), params);
		if(page.getList() != null && page.getList().size() > 0) {
			for(int i=0;i<page.getList().size();i++) {
				Object[] objs = (Object[]) page.getList().get(i);
				PoHJXX_HXXB hxx = (PoHJXX_HXXB) objs[0];
				PoHJXX_MLPXXXXB mlp = (PoHJXX_MLPXXXXB) objs[1];
				
				PoXT_JLXXXB jlx = null ;
				if(objs.length == 3) {
					jlx = (PoXT_JLXXXB) objs[2];
				}
				
				PoV_HJ_HDXXB vo = new PoV_HJ_HDXXB();
				try {
					BeanUtils.copyProperties(vo, hxx);
					BeanUtils.copyProperties(vo, mlp);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
				if(jlx != null) {
					vo.setHmc(jlx.getMc());
				}
				
				vo.setHcjhjywid(hxx.getCjhjywid());
				vo.setHcchjywid(hxx.getCchjywid());
				vo.setHlxdbid(hxx.getLxdbid());
				vo.setHjlbz(hxx.getJlbz());
				vo.setHqysj(hxx.getQysj());
				vo.setHjssj(hxx.getJssj());
				vo.setCxbz(hxx.getCxbz());
				vo.setJhsj(hxx.getJhsj());
				vo.setBdyy(hxx.getBdyy());
				vo.setDcjhjywid(mlp.getCjhjywid());
				vo.setDcchjywid(mlp.getCchjywid());
				vo.setDlxdbid(mlp.getLxdbid());
				vo.setDjlbz(mlp.getJlbz());
				vo.setDqysj(mlp.getQysj());
				vo.setDjssj(mlp.getJssj());
				
				vo.setHxx(hxx);
				vo.setMlpxx(mlp);
				
				if(objs.length == 3) {
					vo.setJlxxx(jlx);
				}
				
				resultList.add(vo);
			}
		}
		result.setTotalCount(page.getTotalCount());
		result.setList(resultList);
		
		return result;
	}

	@Override
	public Page queryQrxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoHJYW_QRDJXXB", params);
	}

	@Override
	public Page queryCsdjxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoHJYW_CSDJXXB", params);
	}

	@Override
	public Page getSwzxxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoHJYW_SWZXXXB", params);
	}

	@Override
	public Page getZzbdxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoHJYW_ZZBDXXB", params);
	}

	@Override
	public Page getBggzxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoHJYW_BGGZXXB1", params);
	}

	@Override
	public Page getHbbgxx(ExtMap<String, Object> params) {
		//ParmPlus(params);
//		if(params.getInteger("_start_csrq") != null) {
//			params.put("_start_csrq", moweibuzero(params.getString("_start_csrq")));
//		}
//		if(params.getInteger("_end_csrq") != null) {
//			params.put("_end_csrq", moweibuzero(params.getString("_end_csrq")));
//		}

		return super.getPageRecords("/conf/segment/common", "queryPoHJYW_HBBGXXB", params);
	}

	@Override
	public Page getHjblxx(ExtMap<String, Object> params) {
//		if(params.getInteger("_start_csrq") != null) {
//			params.put("_start_csrq", moweibuzero(params.getString("_start_csrq")));
//		}
//		if(params.getInteger("_end_csrq") != null) {
//			params.put("_end_csrq", moweibuzero(params.getString("_end_csrq")));
//		}
//		if(params.getInteger("_start_slsj") != null) {
//			params.put("_start_slsj",  moweibuzero(params.getString("_start_slsj")));
//		}
//		if(params.getInteger("_end_slsj") != null) {
//			params.put("_end_slsj", moweibuzero(params.getString("_end_slsj")));
//		}	
		return super.getPageRecords("/conf/segment/common", "queryPoHJYW_HJBLXXB", params);
	}

	@Override
	public Page getHjscxx(ExtMap<String, Object> params) {
//		if(params.getInteger("_start_csrq") != null) {
//			params.put("_start_csrq", moweibuzero(params.getString("_start_csrq")));
//		}
//		if(params.getInteger("_end_csrq") != null) {
//			params.put("_end_csrq", moweibuzero(params.getString("_end_csrq")));
//		}
//		if(params.getInteger("_start_slsj") != null) {
//			params.put("_start_slsj",  moweibuzero(params.getString("_start_slsj")));
//		}
//		if(params.getInteger("_end_slsj") != null) {
//			params.put("_end_slsj", moweibuzero(params.getString("_end_slsj")));
//		}
		return super.getPageRecords("/conf/segment/common", "queryPoHJYW_HJSCXXB", params);
	}

	@Override
	public Page getHcybdxx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryPoHJYW_HCYBDXXB1", params);
	}

	@Override
	public Page getBdqkxx(ExtMap<String, Object> params) {
		String ywfw1 = params.getString("ywfw1");
		String ywfw2 = params.getString("ywfw2");
		String ywfw3 = params.getString("ywfw3");
		String ywfw4 = params.getString("ywfw4");
		String ywfw5 = params.getString("ywfw5");
		String ywfw6 = params.getString("ywfw6");
		String ywfw7 = params.getString("ywfw7");
		String ywfw8 = params.getString("ywfw8");
		String bdfw1 = params.getString("bdfw1");
		String bdfw2 = params.getString("bdfw2");
		String bdfw3 = params.getString("bdfw3");
		String bdfw4 = params.getString("bdfw4");
		String bdfw5 = params.getString("bdfw5");
		String bdfw6 = params.getString("bdfw6");
		String[] ywfwKey = new String[]{ywfw1,ywfw2,ywfw3,ywfw4,ywfw5,ywfw6,ywfw7,ywfw8};
		String[] ywfwValue = new String[]{"'103','105'","'101'","'104','106'","'102'","'108'","'109'","'110'","'103','105','101','104','106','102','108','109','110'"};
		String ywfwTemp = "";
		for(int i = ywfwKey.length-1;i>=0;i--) {
			if(ywfwKey[7]!=null) {
				if(i==7) {
					ywfwTemp=ywfwValue[i];
				}else {
					if(ywfwKey[i]!=null) {
						ywfwTemp=ywfwTemp.replace(","+ywfwValue[i],"").replace(ywfwValue[i]+",","").replace(ywfwValue[i],"");
					}
				}
				
			}else {
				if(ywfwKey[i]!=null) {
					ywfwTemp+=ywfwValue[i]+",";
				}
			}
			
		}
		if(ywfwKey[7]==null) {
			ywfwTemp=ywfwTemp.substring(0,ywfwTemp.length()-1);
		}
//		for(int i = 0;i<ywfwKey.length;i++) {
//			if(ywfwKey[i]!=null&&ywfwKey[7]!=null) {
//				ywfwTemp+=ywfwValue[i]+",";
//			}
//		}
		if(CommonUtil.isNotEmpty(ywfwTemp)) {
			params.put("ywfwRange",ywfwTemp);
		}else {
			params.put("ywfwRange","");
		}
		String shijianfw = params.getString("shijianfw");
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1  = new SimpleDateFormat("yyyyMM");
		Date date = new Date();
		if(shijianfw.equals("1")) {
			
		}else if(shijianfw.equals("2")) {
			params.put("dshijianfw",sdf.format(date));
		}else if(shijianfw.equals("3")) {
			params.put("mshijianfws",sdf1.format(date));
			params.put("mshijianfwe",(Integer.parseInt(sdf1.format(date))+1)+"");
		}else if(shijianfw.equals("4")) {
			params.put("zdyshijianfws",CommonUtil.isNotEmpty(params.getString("shijianfw_start"))?params.getString("shijianfw_start")+"000000":"");
			params.put("zdyshijianfwe",CommonUtil.isNotEmpty(params.getString("shijianfw_end"))?params.getString("shijianfw_end")+"235959":"");
		}
//		String shijianfw_start = params.getString("shijianfw_start");
//		String shijianfw_end = params.getString("shijianfw_end");
//		String shujufw1 = params.getString("shujufw1");
//		String shujufw2 = params.getString("shujufw2");
		if(ywfw1 != null&&ywfw2 != null&&ywfw3 != null&&ywfw4 != null&&ywfw5 != null&&ywfw6 != null&&ywfw7 != null&&ywfw8 != null) {
			params.put("ywfw", "1");
		}
		if(bdfw1 != null&&bdfw2 != null&&bdfw3 != null&&bdfw4 != null&&bdfw5 != null&&bdfw6 != null) {
			params.put("bdrange", "1");
		}
		return super.getPageRecords("/conf/segment/common", "queryPoHJYW_RYBDXXB", params);
	}

	@Override
	public Page getDyxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoHJXX_DYXXB1", params);
	}

	@Override
	public Page getEdzslxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		int kzdate =20181017;
		int mzdate =20180903;
		String fw6 = params.getString("fw6");
		if(fw6 != null) {
			params.put("kzdate", (kzdate+params.getInteger("fw7")));	
			params.put("mzdate", (mzdate+params.getInteger("fw8")));	
		}
		return super.getPageRecords("/conf/segment/common", "queryPoZJYW_SLXXB1", params);
	}

	@Override
	public Page getSjxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoZJYW_SJXXB", params);
	}

	@Override
	public Page getYsxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoZJYW_YSXXB", params);
	}

	@Override
	public Page getLqffxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoZJYW_LQFFXXB", params);
	}

	@Override
	public Page getGsxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoZJYW_GSXXB", params);
	}

	@Override
	public Page getTdxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoZJYW_SLXXB3", params);
	}

	@Override
	public Page getZzfkxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoZJYW_ZZHKXXB", params);
	}

	@Override
	public Page getZlfkxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoZJYW_SLXXB2", params);
	}

	@Override
	public Page getFjjgxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoWW_FJJGRZB", params);
	}

	@Override
	public Page getFfjsxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoZJYW_FFJSXXB", params);
	}

	@Override
	public Page getSfzxx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryPoV_ZJ_JMSFZXXB", params);
	}

	@Override
	public Page getChxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoV_HJ_CHCLXXB", params);
	}

	@Override
	public Page getSfhfpxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		AuthToken u = HSession.getBaseUser();
		if(u != null) {
			boolean isAdmin = u.isAdmin();	
			if(!isAdmin) {
				List<PoXT_YHSJFWB> list = u.getSjfw();
				Iterator<PoXT_YHSJFWB> it = list.iterator();
				while(it.hasNext()){
				    PoXT_YHSJFWB x = it.next();
				    if(x.getXqlx().equals(PublicConstant.XQLX_XQW)){
				        it.remove();
				    }
				}
				if(CommonUtil.isEmpty(list)) {
					throw new RuntimeException("此用户没有数据范围，无法查询！");
				}
			}
		}
		return super.getPageRecords("/conf/segment/common", "queryPoHJYW_GMSFHMSXMFPXXB", params);
	}

	@Override
	public Page getQyzxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		AuthToken u = HSession.getBaseUser();
		if(u != null) {
			boolean isAdmin = u.isAdmin();	
			if(!isAdmin) {
				List<PoXT_YHSJFWB> list = u.getSjfw();
				Iterator<PoXT_YHSJFWB> it = list.iterator();
				while(it.hasNext()){
				    PoXT_YHSJFWB x = it.next();
				    if(x.getXqlx().equals(PublicConstant.XQLX_XQW)){
				        it.remove();
				    }
				}
				if(CommonUtil.isEmpty(list)) {
					throw new RuntimeException("此用户没有数据范围，无法查询！");
				}
			}
		}
		return super.getPageRecords("/conf/segment/common", "queryPoHJSP_QYZXXB", params);
	}

	@Override
	public Page getZqzxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		AuthToken u = HSession.getBaseUser();
		if(u != null) {
			boolean isAdmin = u.isAdmin();	
			if(!isAdmin) {
				List<PoXT_YHSJFWB> list = u.getSjfw();
				Iterator<PoXT_YHSJFWB> it = list.iterator();
				while(it.hasNext()){
				    PoXT_YHSJFWB x = it.next();
				    if(x.getXqlx().equals(PublicConstant.XQLX_XQW)){
				        it.remove();
				    }
				}
				if(CommonUtil.isEmpty(list)) {
					throw new RuntimeException("此用户没有数据范围，无法查询！");
				}
			}
		}
		return super.getPageRecords("/conf/segment/common", "queryPoHJSP_ZQZXXB", params);
	}

	@Override
	public Page getYdzslxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoV_ZJ_YDZSLXXB", params);
	}

	@Override
	public Page getShxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoV_ZJ_SHQFXXB", params);
	}

	@Override
	public Page getXhxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryPoV_ZJ_XHXXB", params);
	}

	@Override
	public Page queryDymbcl(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryPoLODOP", params);
	}
	private String moweibuzero(String sjTemp) {
		return String.format("%-14s", sjTemp).replace(' ', '0');
	}

	@Override
	public Page getQhxxFromHhid(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryPoHJXX_HXXB1", params);
	}

	@Override
	public Page getYhybList(ExtMap<String, Object> params) {
		
		Page result = new Page();
		List<PoV_HJ_HDXXB> resultList = new ArrayList<PoV_HJ_HDXXB>();
		
		Page page = super.getPageRecords("/conf/segment/common", "queryYhybList", params);
		if(page.getList() != null && page.getList().size() > 0) {
			for(int i=0;i<page.getList().size();i++) {
				Object[] objs = (Object[]) page.getList().get(i);
				PoHJXX_HXXB hxx = (PoHJXX_HXXB) objs[0];
				PoHJXX_MLPXXXXB mlp = (PoHJXX_MLPXXXXB) objs[1];
				
				PoV_HJ_HDXXB vo = new PoV_HJ_HDXXB();
				try {
					BeanUtils.copyProperties(vo, hxx);
					BeanUtils.copyProperties(vo, mlp);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
				vo.setHcjhjywid(hxx.getCjhjywid());
				vo.setHcchjywid(hxx.getCchjywid());
				vo.setHlxdbid(hxx.getLxdbid());
				vo.setHjlbz(hxx.getJlbz());
				vo.setHqysj(hxx.getQysj());
				vo.setHjssj(hxx.getJssj());
				vo.setCxbz(hxx.getCxbz());
				vo.setDcjhjywid(mlp.getCjhjywid());
				vo.setDcchjywid(mlp.getCchjywid());
				vo.setDlxdbid(mlp.getLxdbid());
				vo.setDjlbz(mlp.getJlbz());
				vo.setDqysj(mlp.getQysj());
				vo.setDjssj(mlp.getJssj());
				
				resultList.add(vo);
			}
		}
		result.setList(resultList);
		
		return result;
	}
	
	@Override
	public Page queryPzxx(ExtMap<String, Object> params) {
		if(params.getInteger("_start_bcsj") != null) {
			params.put("_start_bcsj", params.getInteger("_start_bcsj")+"000000");
		}
		if(params.getInteger("_end_bcsj") != null) {
			params.put("_end_bcsj", params.getInteger("_end_bcsj")+"000000");
		}
//		Page result = new Page();
		AuthToken u = HSession.getBaseUser();
		params.put("yhdw", u.getOrganize().getQhdm()+"%");
//		List<PoHJXX_PZRZB> resultList = new ArrayList<PoHJXX_PZRZB>();
//		
//		Page page = super.getPageRecords("/conf/segment/common", "querypzxx", params);
//		if(page.getList() != null && page.getList().size() > 0) {
//			for(int i=0;i<page.getList().size();i++) {
//				Object obj =  page.getList().get(i);
//				PoHJXX_PZRZB pojo=(PoHJXX_PZRZB) obj;
//				
//				resultList.add(pojo);
//			}
//		}
//		result.setList(resultList);
		
		return super.getPageRecords("/conf/segment/common", "querypzxx", params);
	}

	@Override
	public Page queryPzxxycl(ExtMap<String, Object> params) {
		if(params.getInteger("_start_rksj") != null) {
			params.put("_start_rksj", params.getInteger("_start_rksj")+"000000");
		}
		if(params.getInteger("_end_rksj") != null) {
			params.put("_end_rksj", params.getInteger("_end_rksj")+"000000");
		}
		if(params.getInteger("_start_yxqxqsrq") != null) {
			params.put("_start_yxqxqsrq", params.getInteger("_start_yxqxqsrq")+"000000");
		}
		if(params.getInteger("_end_yxqxqsrq") != null) {
			params.put("_end_yxqxqsrq", params.getInteger("_end_yxqxqsrq")+"000000");
		}
		Page result = new Page();
		List<VoPzrzxxHqFhxx> resultList = new ArrayList<VoPzrzxxHqFhxx>();
		
		Page page = super.getPageRecords("/conf/segment/common", "queryPzxxycl", params);
		if(page.getList() != null && page.getList().size() > 0) {
			for(int i=0;i<page.getList().size();i++) {
				Object[] objs = (Object[]) page.getList().get(i);
				PoHJXX_PZRZB pz = (PoHJXX_PZRZB) objs[0];
				PoZJYW_SLXXB slx = (PoZJYW_SLXXB) objs[1];
				
				VoPzrzxxHqFhxx vo = new VoPzrzxxHqFhxx();
				try {
					BeanUtils.copyProperties(vo, pz);
					BeanUtils.copyProperties(vo, slx);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
				
				
				resultList.add(vo);
			}
		}
		result.setList(resultList);
		
		return result;
	}

	@Override
	public Page querySfzxxcx(ExtMap<String, Object> params) {
		if(params.getInteger("_start_csrq") != null) {
			params.put("_start_csrq", params.getInteger("_start_csrq")+"000000");
		}
		if(params.getInteger("_end_csrq") != null) {
			params.put("_end_csrq", params.getInteger("_end_csrq")+"000000");
		}
		if(params.getInteger("_start_yxqxqsrq") != null) {
			params.put("_start_yxqxqsrq", params.getInteger("_start_yxqxqsrq")+"000000");
		}
		if(params.getInteger("_end_yxqxqsrq") != null) {
			params.put("_end_yxqxqsrq", params.getInteger("_end_yxqxqsrq")+"000000");
		}
		if(params.getInteger("_start_yxqxjzrq") != null) {
			params.put("_start_yxqxjzrq", params.getInteger("_start_yxqxjzrq")+"000000");
		}
		if(params.getInteger("_end_yxqxjzrq") != null) {
			params.put("_end_yxqxjzrq", params.getInteger("_end_yxqxjzrq")+"000000");
		}
		if(params.getInteger("_start_czsj") != null) {
			params.put("_start_czsj", params.getInteger("_start_czsj")+"000000");
		}
		if(params.getInteger("_end_czsj") != null) {
			params.put("_end_czsj", params.getInteger("_end_czsj")+"000000");
		}
		Page result = new Page();
		List<PoLSSFZ_SLXXB> resultList = new ArrayList<PoLSSFZ_SLXXB>();
		
		Page page = super.getPageRecords("/conf/segment/common", "querysfzxx", params);
		if(page.getList() != null && page.getList().size() > 0) {
			for(int i=0;i<page.getList().size();i++) {
				Object obj =  page.getList().get(i);
				PoLSSFZ_SLXXB pojo=(PoLSSFZ_SLXXB) obj;
				
				resultList.add(pojo);
			}
		}
		result.setList(resultList);
		
		return page;
	}

	@Override
	public Page deletePzlogxx(ExtMap<String, Object> params) {
		Page page=queryPzxx(params);
		Page result = new Page();
		List<PoHJXX_PZRZB> list=(List<PoHJXX_PZRZB>) page.getList();
		if(page.getList() != null && page.getList().size() > 0){			
				Object obj=page.getList().get(0);
				try {
					super.delete(obj);
					list.remove(0);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
			
		}
		result.setList(list);
		return result;
	}

	@Override
	public Page updateSfzxx(ExtMap<String, Object> params) {
		Page page=querySfzxxcx(params);
		if(page.getList() != null && page.getList().size() > 0){			
				Object obj=page.getList().get(0);
				PoLSSFZ_SLXXB pojo=(PoLSSFZ_SLXXB) obj;
				pojo.setDybz("2");
				try {
					super.update(pojo);
				} catch (Exception e) {
					e.getMessage();
				}
				
			
		}
		return page;
	}

	@Override
	public Page queryzjAdresscx(ExtMap<String, Object> params) {
		if(params.getInteger("_start_czsj") != null) {
			params.put("_start_czsj", params.getInteger("_start_czsj")+"000000");
		}
		if(params.getInteger("_end_czsj") != null) {
			params.put("_end_czsj", params.getInteger("_end_czsj")+"000000");
		}
		Page result = new Page();
		List<PoZZZJ_ZZZJRZB> resultList = new ArrayList<PoZZZJ_ZZZJRZB>();
		
		Page page = super.getPageRecords("/conf/segment/common", "queryzjadress", params);
		if(page.getList() != null && page.getList().size() > 0) {
			for(int i=0;i<page.getList().size();i++) {
				Object obj =  page.getList().get(i);
				PoZZZJ_ZZZJRZB pojo=(PoZZZJ_ZZZJRZB) obj;
				
				resultList.add(pojo);
			}
		}
		result.setList(resultList);
		
		return result;
	}

	@Override
	public Page queryczyxxcx(ExtMap<String, Object> params) {
		if(params.getInteger("_start_zcsj") != null) {
			params.put("_start_zcsj", params.getInteger("_start_zcsj")+"000000");
		}
		if(params.getInteger("_end_zcsj") != null) {
			params.put("_end_zcsj", params.getInteger("_end_zcsj")+"000000");
		}
		if(params.getInteger("_start_zxsj") != null) {
			params.put("_start_zxsj", params.getInteger("_start_zxsj")+"000000");
		}
		if(params.getInteger("_end_zxsj") != null) {
			params.put("_start_zxsj", params.getInteger("_start_zxsj")+"000000");
		}
		Page result = new Page();
		List<PoDZZJ_CZYXXB> resultList = new ArrayList<PoDZZJ_CZYXXB>();
		
		Page page = super.getPageRecords("/conf/segment/common", "queryczyxxcx", params);
		if(page.getList() != null && page.getList().size() > 0) {
			for(int i=0;i<page.getList().size();i++) {
				Object obj =  page.getList().get(i);
				PoDZZJ_CZYXXB pojo=(PoDZZJ_CZYXXB) obj;
				
				resultList.add(pojo);
			}
		}
		result.setList(resultList);
		
		return result;
	}

	@Override
	public Page queryrzxxcx(ExtMap<String, Object> params) {
		if(params.getInteger("_start_zcsj") != null) {
			params.put("_start_zcsj", params.getInteger("_start_zcsj")+"000000");
		}
		if(params.getInteger("_end_zcsj") != null) {
			params.put("_end_zcsj", params.getInteger("_end_zcsj")+"000000");
		}
		if(params.getInteger("_start_zxsj") != null) {
			params.put("_start_zxsj", params.getInteger("_start_zxsj")+"000000");
		}
		if(params.getInteger("_end_zxsj") != null) {
			params.put("_start_zxsj", params.getInteger("_start_zxsj")+"000000");
		}
		if(params.getInteger("_start_czsj") != null) {
			params.put("_start_czsj", params.getInteger("_start_czsj")+"000000");
		}
		if(params.getInteger("_end_czsj") != null) {
			params.put("_end_czsj", params.getInteger("_end_czsj")+"000000");
		}
		Page result = new Page();
		List<VoZzzjRzxx> resultList = new ArrayList<VoZzzjRzxx>();
		
		Page page = super.getPageRecords("/conf/segment/common", "queryrzxxcx", params);
		if(page.getList() != null && page.getList().size() > 0) {
			for(int i=0;i<page.getList().size();i++) {
				Object[] objs = (Object[]) page.getList().get(i);
				PoDZZJ_CZRZB pz = (PoDZZJ_CZRZB) objs[0];
				PoDZZJ_CZYXXB slx = (PoDZZJ_CZYXXB) objs[1];
				
				VoZzzjRzxx vo = new VoZzzjRzxx();
				try {
					BeanUtils.copyProperties(vo, pz);
					BeanUtils.copyProperties(vo, slx);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
				
				
				resultList.add(vo);
			}
		}
		result.setList(resultList);
		
		return result;
	}

	@Override
	public Page querytfbdcx(ExtMap<String, Object> params) {
		if(params.getInteger("_start_czsj") != null) {
			params.put("_start_czsj", params.getInteger("_start_czsj")+"000000");
		}
		if(params.getInteger("_end_czsj") != null) {
			params.put("_end_czsj", params.getInteger("_end_czsj")+"000000");
		}
		if(params.getInteger("_start_clsj") != null) {
			params.put("_start_clsj", params.getInteger("_start_clsj")+"000000");
		}
		if(params.getInteger("_end_clsj") != null) {
			params.put("_end_clsj", params.getInteger("_end_clsj")+"000000");
		}
		if(params.getInteger("_start_shsj") != null) {
			params.put("_start_shsj", params.getInteger("_start_shsj")+"000000");
		}
		if(params.getInteger("_end_shsj") != null) {
			params.put("_end_shsj", params.getInteger("_end_shsj")+"000000");
		}
		Page result = new Page();
		List<PoWW_TFBDJGXXB> resultList = new ArrayList<PoWW_TFBDJGXXB>();
		
		Page page = super.getPageRecords("/conf/segment/common", "querytfbdcx", params);
//		if(page.getList() != null && page.getList().size() > 0) {
//			for(int i=0;i<page.getList().size();i++) {
//				Object obj =  page.getList().get(i);
//				PoWW_TFBDJGXXB pojo=(PoWW_TFBDJGXXB) obj;
//				
//				resultList.add(pojo);
//			}
//		}
//		result.setList(resultList);
		
		return page;
	}

	@Override
	public Page querydkxxrzcx(ExtMap<String, Object> params) {		
		Page result = new Page();
		List<PoZZZJ_DKRZB> resultList = new ArrayList<PoZZZJ_DKRZB>();
		
		Page page = super.getPageRecords("/conf/segment/common", "querydkxxrzcx", params);
		if(page.getList() != null && page.getList().size() > 0) {
			for(int i=0;i<page.getList().size();i++) {
				Object obj =  page.getList().get(i);
				PoZZZJ_DKRZB pojo=(PoZZZJ_DKRZB) obj;
				
				resultList.add(pojo);
			}
		}
		result.setList(resultList);
		
		return result;
	}

	@Override
	public Page queryxkxxrzcx(ExtMap<String, Object> params) {
		Page result = new Page();
		List<PoHZSMK_XKRZ> resultList = new ArrayList<PoHZSMK_XKRZ>();
		
		Page page = super.getPageRecords("/conf/segment/common", "queryxkxxrzcx", params);
		if(page.getList() != null && page.getList().size() > 0) {
			for(int i=0;i<page.getList().size();i++) {
				Object obj =  page.getList().get(i);
				PoHZSMK_XKRZ pojo=(PoHZSMK_XKRZ) obj;
				
				resultList.add(pojo);
			}
		}
		result.setList(resultList);
		
		return result;
	}

	@Override
	public Page getCbcpList(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryCbcpList", params);
	}

	@Override
	public Page getSlxxtjList(ExtMap<String, Object> params) {
		////select new PoSLXXTJ(a.slzt,count(a.slzt) as num)
		Page result = new Page();
		List<PoSLXXTJ> resultList = new ArrayList<PoSLXXTJ>();
		Page page = super.getPageRecords("/conf/segment/common", "querySlxxInfo", params);
		if(page.getList() != null && page.getList().size() > 0) {
			for(int i=0;i<page.getList().size();i++) {
				Object[] obj =  (Object[]) page.getList().get(i);
				PoSLXXTJ pojo=new PoSLXXTJ();
				pojo.setSlzt(getMcByClsb((String) obj[0]));
				pojo.setNum(new Long((long) obj[1]).intValue());
				resultList.add(pojo);
			}
		}
		result.setList(resultList);
		return result;
	}
	public String getMcByClsb( String dm) {
		String strXt_xtcsb="";
		PoXT_XTCSB xt_xtcsb = null;
		strXt_xtcsb ="from "+PoXT_XTCSB.class.getName()+" where cslb='5011' and dm='"+dm+"'";
		 List xt_xtcsbList=super.findAllByHQL(strXt_xtcsb);
		 if(xt_xtcsbList.size()==0) {
			 return "";
		 }
		 xt_xtcsb = (PoXT_XTCSB) xt_xtcsbList.get(0);
		return xt_xtcsb.getMc();
	}

	@Override
	public Page getEdzslrzxx(ExtMap<String, Object> params) {
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryslrzxx", params);
	}

	@Override
	public Page delYdzslxx(ExtMap<String, Object> params) {
		Page page=getYdzslxx(params);
		Page result = new Page();
		List<PoV_ZJ_YDZSLXXB> list=(List<PoV_ZJ_YDZSLXXB>) page.getList();
		if(page.getList() != null && page.getList().size() > 0){			
				Object obj=page.getList().get(0);
				try {
					super.delete(obj);
					list.remove(0);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
		}
		result.setList(list);
		return result;
	}

	@Override
	public Page getSjzdInfo(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "querySjzdInfo", params);
	}

	@Override
	public List modifySizd(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtsjzdService.setXtsjzdxgxx(vo);
	}

	@Override
	public List addSizd(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtsjzdService.setXtsjzdxx(vo);
	}
	@Override
	public int delSizd(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtsjzdService.setXtsjzdscxx(vo);
	}
	@Override
	public Page getXtcswhInfo(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryXtcsxxByCslb", params);
	}

	@Override
	public Page getXtcsInfoByCslb(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryXtcsxxByCslb", params);
	}

	@Override
	public Page getXzqhInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		VoPage vopage = new VoPage();
		vopage.setPageindex(params.getInteger("pageIndex"));
		vopage.setPagesize(params.getInteger("pageSize"));
		return fromQueryResultToPage(xtxzqhService.getXtxzqhxx(vo,"",vopage));
		//return super.getPageRecords("/conf/segment/common", "queryXzqhInfo", params);
	}

	@Override
	public List modifyXzqhDm(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtxzqhService.setXtxzqhxgxx(vo);
	}

	@Override
	public List addXzqhDm(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtxzqhService.setXtxzqhxx(vo);
	}

	@Override
	public List delXzqhDm(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtxzqhService.setXtxzqhscxx(vo);
	}

	@Override
	public Page getHjspBggzsp(ExtMap<String, Object> params) {
		ParmPlus(params);
		if(params.getString("dqyh")!=null&&params.getInteger("dqyh")==1) {
			 params.put("djrid", this.getUserInfo().getYhid());
		}
		return super.getPageRecords("/conf/segment/common", "getHjspBggz", params);
	}

	@Override
	public Page getHjspHjsc(ExtMap<String, Object> params) {
		ParmPlus(params);
		if(params.getString("dqyh")!=null&&params.getInteger("dqyh")==1) {
			 params.put("djrid", this.getUserInfo().getYhid());
		}
		return super.getPageRecords("/conf/segment/common", "getHjspHjsc", params);
	}

	@Override
	public Page getHjspHjbl(ExtMap<String, Object> params) {
		if(params.getString("dqyh")!=null&&params.getInteger("dqyh")==1) {
			 params.put("djrid", this.getUserInfo().getYhid());
		}
		AuthToken u = HSession.getBaseUser();
		if(u != null) {
			String where ="";
			boolean isAdmin = u.isAdmin();	
			if(!isAdmin) {
				List<PoXT_YHSJFWB> list = BaseContext.getUser().getSjfw();
				Iterator<PoXT_YHSJFWB> it = list.iterator();
				while(it.hasNext()){
				    PoXT_YHSJFWB x = it.next();
				    if(x.getXqlx().equals(PublicConstant.XQLX_XQW)){
				        it.remove();
				    }
				}
				if(CommonUtil.isEmpty(list)) {
					throw new RuntimeException("此用户没有数据范围，无法查询！");
				}
				where = "  ( 0=1 ";
				for (PoXT_YHSJFWB o:list) {
					String sjfwstr = o.getSjfw();
					if(CommonUtil.isNotEmpty(sjfwstr)){
						Set<String> set = new HashSet<String>();
						
						String sjfw[] = sjfwstr.split("\\|");
						for(int i = 0;i<sjfw.length;i++){
							String bm = sjfw[i];
							bm = bm.trim();
							
							if(CommonUtil.isEmpty(bm) || set.contains(bm))
								continue;
							if(sjfw.length==3&&i==2&&bm.length()==12) {
								where += " or a.ssjwh= '" + bm + "'";
							}
							if(sjfw.length==2&&i==1&&bm.length()==9) {
								where += " or a.sspcs= '" + bm + "'";
							}
							if(sjfw.length==1&&i==0&&bm.length()==6) {
								where += " or a.ssssxq = '" + bm + "'";
							}
							set.add(bm);
						}
					}
				}
				where += ")";
			}
			params.put("sjfw", where);
		}
		return super.getPageRecords("/conf/segment/common", "getHjspHjbl", params);
	}

	@Override
	public Page getHjspHbbg(ExtMap<String, Object> params) {
		if(params.getString("dqyh")!=null&&params.getInteger("dqyh")==1) {
			 params.put("djrid", this.getUserInfo().getYhid());
		}
		return super.getPageRecords("/conf/segment/common", "getHjspHbbg", params);
	}

	@Override
	public Page getHjspQrsp(ExtMap<String, Object> params) {
		if(params.getString("dqyh")!=null&&params.getInteger("dqyh")==1) {
			 params.put("djrid", this.getUserInfo().getYhid());
		}
		AuthToken u = HSession.getBaseUser();
		if(u != null) {
			String where ="";
			boolean isAdmin = u.isAdmin();	
			if(!isAdmin) {
				List<PoXT_YHSJFWB> list = BaseContext.getUser().getSjfw();
				Iterator<PoXT_YHSJFWB> it = list.iterator();
				while(it.hasNext()){
				    PoXT_YHSJFWB x = it.next();
				    if(x.getXqlx().equals(PublicConstant.XQLX_XQW)){
				        it.remove();
				    }
				}
				if(CommonUtil.isEmpty(list)) {
					throw new RuntimeException("此用户没有数据范围，无法查询！");
				}
				where = "  ( 0=1 ";
				for (PoXT_YHSJFWB o:list) {
					String sjfwstr = o.getSjfw();
//					if(!o.getXqlx().equals(PublicConstant.XQLX_XQW)) {
//						continue;
//					}
					if(CommonUtil.isNotEmpty(sjfwstr)){
						Set<String> set = new HashSet<String>();
						
						String sjfw[] = sjfwstr.split("\\|");
						for(int i = 0;i<sjfw.length;i++){
							String bm = sjfw[i];
							bm = bm.trim();
							
							if(CommonUtil.isEmpty(bm) || set.contains(bm))
								continue;
							if(sjfw.length==3&&i==2&&bm.length()==12) {
								where += " or a.qrdjwh= '" + bm + "'";
							}
							if(sjfw.length==2&&i==1&&bm.length()==9) {
								where += " or a.qrdpcs= '" + bm + "'";
							}
							if(sjfw.length==1&&i==0&&bm.length()==6) {
								where += " or a.qrdqx = '" + bm + "'";
							}
							set.add(bm);
						}
					}
				}
				where += ")";
			}
			params.put("sjfw", where);
		}
		return super.getPageRecords("/conf/segment/common", "getHjspQrsp", params);
	}

	@Override
	public Page getDwxxInfo(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryDwxx", params);
//		VarVO vo  = new VarVO();
//		vo.setVarmap(params);
//		VoQueryResult voQueryResult = xtdwxxService.getXtdwxx(vo, "", new VoPage());
//		Page p = new Page();
//		p.setList(voQueryResult.getPagelist());
//		return (Page) voQueryResult.getPagelist();
	}
	@Override
	public void modifyDwDm(MultipartHttpServletRequest logoFile,ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		xtdwxxService.setXtdwxgxx(logoFile,vo);
	}

	@Override
	public void addDwDm(MultipartHttpServletRequest logoFile,ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		xtdwxxService.setXtdwxx(logoFile,vo);
	}

	@Override
	public List delDwDm(ExtMap<String, Object> params) {
		//PoXT_DWXXB dwxxb = super.get(PoXT_DWXXB.class,params.getString("dwxxbdm"));
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtdwxxService.setXtdwscxx(vo);
	}
	@Override
	public Page getJwzrqxxInfo(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryPoV_JWZRQXX", params);
	}

	@Override
	public List modifyJwzrq(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjwzrqService.setXtjwzrqxgxx(vo);
	}
	public Page querybblb(ExtMap<String, Object> params) {
		// TODO Auto-generated method stub
		//return super.getPageRecords("/conf/segment/common", "querybblb", params);
		Page result = new Page();
		List<PoXT_XTCSB> resultList = new ArrayList<PoXT_XTCSB>();
		
		Page page = super.getPageRecords("/conf/segment/common", "queryXtcsxxByCslb", params);
		if(page.getList() != null && page.getList().size() > 0) {
			for(int i=0;i<page.getList().size();i++) {
				Object obj =  page.getList().get(i);
				PoXT_XTCSB pojo=(PoXT_XTCSB) obj;
				
				resultList.add(pojo);
			}
		}
		result.setList(resultList);
		
		return result;
	}

	@Override
	public Page querybblbxx(ExtMap<String, Object> params) {
		// TODO Auto-generated method stub
		int str=params.getInteger("ywbblb");
		String sql="select distinct"+                
                " poxt_yhxxb3_.YHXM as YHXM0, "+               
                " poxt_yhxxb4_.YHXM as YHXM1_, "+              
                " poxt_ywbbm0_.BBMC as x2_0_, "+
                " poxt_ywbbm0_.JLSJ as x3_0_, "+             
                " poxt_ywbbm0_.XGSJ as x5_0_, "+
                " poxt_ywbbm0_.YWBBLB, "+
                " poxt_ywbbm0_.YWBBID "+
  " from XT_YWBBMBXXB poxt_ywbbm0_,XT_JSYWBBQXB poxt_jsywb1_, XT_YHJSXXB  poxt_yhjsx2_,XT_YHXXB   poxt_yhxxb3_,XT_YHXXB poxt_yhxxb4_"+
 " where (poxt_ywbbm0_.JLRID = poxt_yhxxb3_.YHID) "+
   " and (poxt_ywbbm0_.YWBBID = poxt_jsywb1_.YWBBID(+)) "+
   " and (poxt_jsywb1_.JSID = poxt_yhjsx2_.JSID(+)) "+
   " and (poxt_ywbbm0_.XGRID = poxt_yhxxb4_.YHID(+)) "+
   " and (poxt_ywbbm0_.YWBBLB = "+str+") "+
 " order by poxt_ywbbm0_.YWBBLB, poxt_ywbbm0_.BBMC";
		Page result = new Page();
		List l = super.executeSqlQuery(sql, null);
		List<Voywbbwhxx> list=new ArrayList<Voywbbwhxx>();
		BASE64Encoder encode=new BASE64Encoder();
		
		for(Object obj:l){
			Object[] objs = (Object[])obj;
			Voywbbwhxx vo=new Voywbbwhxx();
			vo.setCreateName(objs[0].toString());
			vo.setBbName(objs[2].toString());
			vo.setUpdateName(objs[1]==null?" ":objs[1].toString());
			vo.setCreateDate(objs[3]==null?" ":objs[3].toString());
			vo.setUpdateDate(objs[4]==null?" ":objs[4].toString());
			vo.setYwbblb(objs[5]==null?" ":objs[5].toString());
			vo.setYwbbid(objs[6]==null?" ":objs[6].toString());
			PoXT_YWBBMBXXB poXtywbbb = super.get(PoXT_YWBBMBXXB.class,Long.valueOf(objs[6].toString()));
			if(poXtywbbb.getBbmb()!=null){
				String bbmb=null;
				try {
					bbmb = new String(poXtywbbb.getBbmb(),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				vo.setYwbbmb(bbmb);
			}
			list.add(vo);
		}
		result.setList(list);
		return result;
	}



	@Override
	public Page getbbxx(ExtMap<String, Object> params) {
		Page result = new Page();
		List<PoXT_YWBBMBXXB> resultList = new ArrayList<PoXT_YWBBMBXXB>();
		
		Page page = super.getPageRecords("/conf/segment/common", "getbbxx", params);
		if(page.getList() != null && page.getList().size() > 0) {
			for(int i=0;i<page.getList().size();i++) {
				Object obj =  page.getList().get(i);
				PoXT_YWBBMBXXB pojo=(PoXT_YWBBMBXXB) obj;
				
				resultList.add(pojo);
			}
		}
		result.setList(resultList);
		
		return result;
	}
	
	
	@Override
	public Page delbblbxx(ExtMap<String, Object> params) {
		Page page=getbbxx(params);
		Page result = new Page();
		List<PoXT_YWBBMBXXB> list=(List<PoXT_YWBBMBXXB>) page.getList();
		if(page.getList() != null && page.getList().size() > 0){			
				Object obj=page.getList().get(0);
				try {
					super.delete(obj);
					list.remove(0);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
			
		}
		result.setList(list);
		return result;
	}

	@Override
	public List addJwzrq(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjwzrqService.setXtjwzrqxx(vo);
		
	}

	@Override
	public List delJwzrq(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjwzrqService.setXtjwzrqscxx(vo);
		
	}

	@Override
	public Page getXzjdInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		VoPage vopage = new VoPage();
		vopage.setPageindex(params.getInteger("pageIndex"));
		vopage.setPagesize(params.getInteger("pageSize"));
		return fromQueryResultToPage(xtxzjdService.getXtxzjdxx(vo,"",vopage));
	}

	@Override
	public List modifyXzjd(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtxzjdService.setXtxzjdxgxx(vo);
	}

	@Override
	public List addbblbxx(ExtMap<String, Object> params) {
		// TODO Auto-generated method stub
		/*VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtbbService.setXtywbbxx(vo);*/
		
		PoXT_YWBBMBXXB pojo=new PoXT_YWBBMBXXB();
		int bblb=params.getInteger("bblb");
		String bbmc=params.getString("bbmc");
		Long jlrid=this.getUser().getYhid();
		String ywbbmb=params.getString("bbmbcontent");
		String jlsj=DateHelper.formateDate("yyyyMMddHHmmss");
		pojo.setYwbbid((Long) Xt_ywbbxxbDao.getId());
		pojo.setBbmc(bbmc);
		pojo.setJlrid(jlrid);
		pojo.setYwbblb(bblb+"");
		pojo.setJlsj(jlsj);
		pojo.setBbmb(ywbbmb== null ? null : ywbbmb.getBytes());
		super.create(pojo);
		return null;
		
	}

	@Override
	public List addXzjd(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtxzjdService.setXtxzjdxx(vo);
	}

	@Override
	public List delXzjd(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtxzjdService.setXtxzjdscxx(vo);
	}

	@Override
	public Page getJwhxxInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		VoPage vopage = new VoPage();
		vopage.setPageindex(params.getInteger("pageIndex"));
		vopage.setPagesize(params.getInteger("pageSize"));
		return fromQueryResultToPage(xtjwhService.getXtjwhxx(vo,"",vopage));
	}

	@Override
	public List modifyJwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjwhService.setXtjwhxgxx(vo);
	}

	@Override
	public List addJwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjwhService.setXtjwhxx(vo);
	}

	@Override
	public List delJwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjwhService.setXtjwhscxx(vo);
	}

	@Override
	public Page getJlxByJwhdm(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		StringBuffer sqltemp = new StringBuffer();
		if(params.getString("qybz") != null&&params.getString("jwhdm") != null) {
			sqltemp.append(" XT_JLXJWHDZXXB.qybz = '").append(params.getString("qybz")).append("'").append(" and XT_JLXJWHDZXXB.jwhdm = '").append(params.getString("jwhdm")).append("'");
		}
		if(params.getString("qybz") == null&&params.getString("jwhdm") != null) {
			sqltemp.append(" XT_JLXJWHDZXXB.jwhdm = '").append(params.getString("jwhdm")).append("'");
		}
		if(params.getString("jlxdm") != null) {
			sqltemp.append("and XT_JLXJWHDZXXB.jlxdm = '").append(params.getString("jlxdm")).append("'");
		}
		VoPage vopage = new VoPage();
		vopage.setPageindex(params.getInteger("pageIndex"));
		vopage.setPagesize(params.getInteger("pageSize"));
		return fromQueryResultToPage(xtjlxjwhdzService.getXtjlxjwhdzxx(sqltemp.toString(),vopage));
	}

	@Override
	public List modifyjlx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjlxjwhdzService.setXtjlxjwhdzxgxx(vo);
	}

	@Override
	public List addJlx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjlxjwhdzService.setXtjlxjwhdzxx(vo);
	}

	@Override
	public List addJlxxx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjlxService.setXtjlxxx(vo);
	}

	@Override
	public List updatebblbxx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtbbService.setXtywbbxgxx(vo);
	}

	@Override
	public Page queryzsbblb(ExtMap<String, Object> params) {
		Page result = new Page();
		List<PoXT_XTCSB> resultList = new ArrayList<PoXT_XTCSB>();
		
		Page page = super.getPageRecords("/conf/segment/common", "queryXtcsxxByCslb", params);
		if(page.getList() != null && page.getList().size() > 0) {
			for(int i=0;i<page.getList().size();i++) {
				Object obj =  page.getList().get(i);
				PoXT_XTCSB pojo=(PoXT_XTCSB) obj;
				
				resultList.add(pojo);
			}
		}
		result.setList(resultList);
		return result;
	}

	@Override
	public Page queryzsbblbxx(ExtMap<String, Object> params) {
		int str=params.getInteger("zsbblb");
		String sql="select distinct"+                
                " poxt_yhxxb3_.YHXM as YHXM0, "+               
                " poxt_yhxxb4_.YHXM as YHXM1_, "+              
                " poxt_zsbbm0_.BBMBMC as x2_0_, "+
                " poxt_zsbbm0_.JLSJ as x3_0_, "+             
                " poxt_zsbbm0_.XGSJ as x5_0_, "+
                " poxt_zsbbm0_.ZSBBLB, "+
                " poxt_zsbbm0_.ZSBBMBID "+
  " from XT_ZSBBMBXXB poxt_zsbbm0_,XT_JSZSBBQXB poxt_jszsb1_, XT_YHJSXXB  poxt_yhjsx2_,XT_YHXXB   poxt_yhxxb3_,XT_YHXXB poxt_yhxxb4_"+
 " where (poxt_zsbbm0_.SCRID = poxt_yhxxb3_.YHID) "+
   " and (poxt_zsbbm0_.ZSBBMBID = poxt_jszsb1_.ZSBBMBID(+)) "+
   " and (poxt_jszsb1_.JSID = poxt_yhjsx2_.JSID(+)) "+
   " and (poxt_zsbbm0_.XGRID = poxt_yhxxb4_.YHID(+)) "+
   " and (poxt_zsbbm0_.ZSBBLB = "+str+") "+
 " order by poxt_zsbbm0_.ZSBBLB, poxt_zsbbm0_.BBMBMC";
		Page result = new Page();
		List l = super.executeSqlQuery(sql, null);
		List<Voywbbwhxx> list=new ArrayList<Voywbbwhxx>();
		for(Object obj:l){
			Object[] objs = (Object[])obj;
			Voywbbwhxx vo=new Voywbbwhxx();
			vo.setCreateName(objs[0].toString());
			vo.setBbName(objs[2].toString());
			vo.setUpdateName(objs[1]==null?" ":objs[1].toString());
			vo.setCreateDate(objs[3]==null?" ":objs[3].toString());
			vo.setUpdateDate(objs[4]==null?" ":objs[4].toString());
			vo.setYwbblb(objs[5]==null?" ":objs[5].toString());
			vo.setYwbbid(objs[6]==null?" ":objs[6].toString());
			PoXT_ZSBBMBXXB poXtzsbbmbb = super.get(PoXT_ZSBBMBXXB.class,Long.valueOf(objs[6].toString()));
			if(poXtzsbbmbb.getBbmb()!=null){
				String zsbbmb=null;
				try {
					zsbbmb = new String(poXtzsbbmbb.getBbmb(),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				vo.setYwbbmb(zsbbmb);
				
			}
			list.add(vo);
		}
		result.setList(list);
		return result;
	}

	@Override
	public List addzsbbmbxx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtbbService.setXtzsbbmbxx(vo);
	}

	@Override
	public List updatezsbbmbxx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtbbService.setXtzsbbmbxgxx(vo);
	}

	@Override
	public int delzsbbmbxx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtbbService.setXtzsbbmbscxx(vo);
	}

	@Override
	public Page getJlxxxInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		VoPage vopage = new VoPage();
		vopage.setPageindex(params.getInteger("pageIndex"));
		vopage.setPagesize(params.getInteger("pageSize"));
		VoQueryResult queryResult = xtjlxService.getXtjlxxx(vo,"",vopage);
		return fromQueryResultToPage(queryResult);
	}

	@Override
	public List modifyJlxxx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjlxService.setXtjlxxgxx(vo);
	}

	/*public static String byte2File(byte[] bfile,String filePath,String fileName){
		BufferedOutputStream bos=null;
		FileOutputStream fos=null;
		File file=null;
		filePath="D:\\bbmb";
        String path=filePath+File.separator+fileName;
		try{
			File dir=new File(filePath);
			if(!dir.exists() && !dir.isDirectory()){//判断文件目录是否存在  
				dir.mkdirs();  
            }
			//path.replace("//", File.separator);
			file=new File(filePath+File.separator+fileName);
			fos=new FileOutputStream(file);
			bos=new BufferedOutputStream(fos);
			bos.write(bfile);
			bos.write("\r\n".getBytes());
			bos.flush();
		} 
		catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();  
        }
		finally{
			try{
				if(bos != null){
					bos.close(); 
				}
				if(fos != null){
					fos.close();
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();  
			}
		}
		return path;
    }*/



	@Override
	public Page queryzsbbxx(ExtMap<String, Object> params) {
		String kssj=params.getString("kssj");
		String jssj=params.getString("jssj");
		//String zsbbmbid=params.getString("zsbbmbid");
		String where="";
		if(null!=kssj&&!kssj.equals("")){
			where+=" and (substr(poxt_zsbbx0_.JLSJ, 1, 8) >= "+kssj+") ";
		}
		if(null!=jssj&&!jssj.equals("")){
			where+=" and (substr(poxt_zsbbx0_.JLSJ, 1, 8) <= "+jssj+") ";
		}
		String sql="select distinct poxt_yhxxb4_.YHXM     as YHXM0_, "+
                " poxt_yhxxb5_.YHXM     as YHXM1_, "+
                " poxt_zsbbx0_.ZSBBID   as zsbbid, "+
                " poxt_zsbbx0_.ZSBBMBID as zsbbmbid, "+
                " poxt_zsbbx0_.BBMC     as bbmc, "+
                " poxt_zsbbx0_.JLSJ     as jlsj, "+
                " poxt_zsbbx0_.SCRID    as scrid, "+
                " poxt_zsbbx0_.XGSJ     as xgsj, "+
                " poxt_zsbbx0_.XGRID    as xgrid, "+
                " poxt_zsbbm3_.ZSBBLB   as zsbblb, "+
               " poxt_zsbbm3_.BBMBMC   as bbmbmc "+

  " from XT_ZSBBXXB   poxt_zsbbx0_, "+
       " XT_JSZSBBQXB poxt_jszsb1_, "+
       " XT_YHJSXXB   poxt_yhjsx2_, "+
       " XT_ZSBBMBXXB poxt_zsbbm3_, "+
       " XT_YHXXB     poxt_yhxxb4_, "+
       " XT_YHXXB     poxt_yhxxb5_ "+
 " where (poxt_zsbbx0_.ZSBBMBID = poxt_zsbbm3_.ZSBBMBID) "+
   " and (poxt_zsbbx0_.SCRID = poxt_yhxxb4_.YHID) "+
   " and (poxt_zsbbx0_.XGRID = poxt_yhxxb5_.YHID(+)) "+
   " and (poxt_zsbbm3_.ZSBBMBID = poxt_jszsb1_.ZSBBMBID(+)) "+
   " and (poxt_jszsb1_.JSID = poxt_yhjsx2_.JSID(+)) "+
   //" and (poxt_zsbbx0_.ZSBBMBID = 3407000001000000004) "+
   //" and (substr(poxt_zsbbx0_.JLSJ, 1, 8) >= "+kssj+") "+
   //" and (substr(poxt_zsbbx0_.JLSJ, 1, 8) <= "+jssj+") "+
   //" and ((poxt_yhjsx2_.YHID = null) or (poxt_zsbbx0_.SCRID = null)) "+
 " order by poxt_zsbbx0_.ZSBBID, poxt_zsbbx0_.BBMC";
		Page result = new Page();
		List l = super.executeSqlQuery(sql, null);
		List<VoXtzsbbxx> list=new ArrayList<VoXtzsbbxx>();
		for(Object obj:l){
			Object[] objs = (Object[])obj;
			VoXtzsbbxx vo=new VoXtzsbbxx();
			vo.setBbmc(objs[4]==null?" ":objs[4].toString());
			vo.setJlsj(objs[5]==null?" ":objs[5].toString());
			vo.setScryhxm(objs[0]==null?" ":objs[0].toString());
			vo.setXgryhxm(objs[1]==null?" ":objs[1].toString());
			vo.setXgsj(objs[7]==null?" ":objs[7].toString());
			vo.setZsbbid(Long.valueOf(objs[2].toString()));			
			list.add(vo);
		}
		result.setList(list);
		return result;
		
	}

	@Override
	public List delJlxxx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjlxService.setXtjlxscxx(vo);
	}

	@Override
	public List addzsbbxx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtbbService.setXtzsbbxx(vo);
	}

	@Override
	public List updatezsbbxx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);    
		return xtbbService.setXtzsbbxgxx(vo);
	}

	@Override
	public int delzsbbxx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtbbService.setXtzsbbscxx(vo);
	}

	private Page fromQueryResultToPage(VoQueryResult queryResult) {
		Page p = new Page();
		p.setList(queryResult.getPagelist());
		p.setTotalCount((int)queryResult.getRecordcount());
		return p;
	}

	@Override
	public Page getJlxjwhdzInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		StringBuffer sqltemp = new StringBuffer();
		if(params.getString("qybz") != null) {
			sqltemp.append(" XT_JLXJWHDZXXB.qybz = '").append(params.getString("qybz")).append("'");
		}
		if(params.getString("jwhdm") != null) {
			sqltemp.append("and XT_JLXJWHDZXXB.jwhdm = '").append(params.getString("jwhdm")).append("'");
		}
		if(params.getString("jlxdm") != null) {
			sqltemp.append("and XT_JLXJWHDZXXB.jlxdm = '").append(params.getString("jlxdm")).append("'");
		}
		VoPage vopage = new VoPage();
		vopage.setPageindex(params.getInteger("pageIndex"));
		vopage.setPagesize(params.getInteger("pageSize"));
		return fromQueryResultToPage(xtjlxjwhdzService.getXtjlxjwhdzxx(sqltemp.toString(),vopage));
	}

	@Override
	public List modifyJlxjwhdz(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjlxjwhdzService.setXtjlxjwhdzxgxx(vo);
	}

	@Override
	public List addJlxjwhdz(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjlxjwhdzService.setXtjlxjwhdzxx(vo);
	}

	@Override
	public List delJlxjwhdz(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjlxjwhdzService.setXtjlxjwhdzscxx(vo);
	}

	@Override
	public List resumeXzqhDm(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtxzqhService.setXtxzqhxgxx(vo);
	}

	@Override
	public List resumeDwDm(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtdwxxService.setXtdwxxResume(vo);
	}

	@Override
	public List resumeJwzrq(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjwzrqService.setXtjwzrqxgxx(vo);
	}

	@Override
	public List resumeXzjd(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtxzjdService.setXtxzjdxgxx(vo);
	}

	@Override
	public List resumeJwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjwhService.setXtjwhxgxx(vo);
	}

	@Override
	public List resumeJlxxx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjlxService.setXtjlxxgxx(vo);
	}

	@Override
	public List resumeJlxjwhdz(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjlxjwhdzService.setXtjlxjwhdzxgxx(vo);
	}	

	@Override
	public Page getKzcsInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		List list = xtkzcsService.getXtkzcsxx(vo);
		Page p = new Page();
//		p.setList(list);
//		p.setTotalCount(list.size());
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		int fromIndex = (pageIndex-1)*pageSize;
		int endIndex = pageIndex*pageSize;
		p.setList(list.subList(fromIndex,list.size()-endIndex> 0? endIndex:list.size()));//取分页所需的数据
		p.setTotalCount(list.size());
		return p ;
	}

	@Override
	public List modifyKzcs(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtkzcsService.setXtkzcsxgxx(vo);
	}

	@Override
	public List getSpmbInfo(ExtMap<String, Object> params) {
		StringBuffer sqltemp = new StringBuffer();
		sqltemp.append("select spmbxxb.spmbid  spmbid,spmbxxb.mbmc  mbmc,spmbxxb.mbdj  mbdj,xtcsba.mc mbdjmc,yhxxba.yhxm  cjryhxm,spmbxxb.cjsj  cjsj,yhxxbb.yhxm  xgryhxm,spmbxxb.xgsj  xgsj,spmbxxb.dqsys  dqsys,xtcsbab.mc qybzmc"
				+ " from XT_SPMBXXB spmbxxb ,XT_YHXXB yhxxba,XT_YHXXB yhxxbb ,XT_XTCSB xtcsba,XT_XTCSB xtcsbab")
		.append(" where spmbxxb.mbdj = xtcsba.dm(+) and xtcsba.cslb(+) = '9021' and spmbxxb.qybz= xtcsbab.dm(+) and xtcsbab.cslb(+) = '9002' ")
		.append(" and spmbxxb.cjrid = yhxxba.yhid and spmbxxb.xgrid = yhxxbb.yhid(+) ");
		if(params.getString("qybz") != null) {
			sqltemp.append(" and  spmbxxb.qybz = '").append(params.getString("qybz")).append("'");
		}
		List list = super.executeSqlQueryMapList(sqltemp.toString(), null);
		return list;
//		VarVO vo  = new VarVO();
//		vo.setVarmap(params);
//		List list = xtspmbService.getXtspmbxx(vo);
//		//List list = xtsplService.getXtsplxx(vo);
//		Page p = new Page();
//		p.setList(list);
//		p.setTotalCount(list.size());
//		return p ;
	}

	@Override
	public Page getSpdzInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		List list = xtspdzService.getXtspdzxx(vo);
		Page p = new Page();
		p.setList(list);
		p.setTotalCount(list.size());
		return p ;
	}

	@Override
	public Page getMbsplcInfo(ExtMap<String, Object> params) {
//		VarVO vo  = new VarVO();
//		vo.setVarmap(params);
//		List list = xtsplService.getXtsplxxNew(vo);
//		Page p = new Page();
//		p.setList(list);
//		p.setTotalCount(list.size());
//		return p ;
		return super.getPageRecords("/conf/segment/common", "queryMbsplInfo", params);
	}

	@Override
	public List querypcs(ExtMap<String, Object> params) {
		String dm=this.getUserInfo().getDwdm();
		String bbmc=params.getString("ywbbmc");		
		Long yhid=this.getUserInfo().getYhid();
		String sqlstr=params.getString("sqlstr");
		String type=params.getString("tjtype");
		if(type.equals("ssxq")){
			dm=dm.substring(0, 4);//分局
		}
		StringBuffer buffer=new StringBuffer();
		String sql="select * from xt_jsxxb t where jsid='"+yhid+"' and jsmc='超级用户'";
		List l=super.executeSqlQuery(sql,null);
		
		 int indexStart=sqlstr.indexOf("S");
		 String f_dm=sqlstr.substring(indexStart, 8);
		 int mcIndex=sqlstr.indexOf("MC");
		 int indexEnd=sqlstr.indexOf("=");
		 String  sqls=sqlstr.substring(mcIndex, indexEnd+1).replace('6', '4');
		if(l.size()>0){
			if(type.equals("ssxq")){			 
				 String sql_DM=" SUBSTR(DM,1,6), ";
				int indexend2=sqlstr.indexOf("AND");
				String and=sqlstr.substring(indexend2).trim();
				buffer.append(f_dm+sql_DM+sqls+dm+"\t"+and);
			}else{
				buffer.append("SELECT dm,mc FROM XT_DWXXB");
			}
			
		}else{
				
				if(type.equals("ssxq")){
					  String sql_DM=" SUBSTR(DM,1,6) ";
				      int indexend2=sqlstr.indexOf("AND");
					  String and=sqlstr.substring(indexend2).trim();
					  buffer.append(f_dm+sql_DM+sqls+dm+"\t"+and);
					
				}
				if(type.equals("pcs")){
					
					buffer.append(f_dm+" DM, "+sqls+dm);
				}
					
		}
		
	
		
		List list = super.executeSqlQuery(buffer.toString(), null);
			List fjList=new ArrayList();
			if(list.size()>0){
			for(Object obj:list){
				Object[] objs=(Object[]) obj;
				if(type.equals("ssxq")){
					if(objs[1].toString().contains("分局")){
						fjList.add(objs);
					}
				}else{
					if(!objs[1].toString().contains("分局")){
						fjList.add(objs);
					}
				}
				
			}
	}
			return fjList;
		
	}
	
	@Override
	public List querybbsc(ExtMap<String, Object> params){
		String procName=params.getString("procName");
		List list=new ArrayList();
		if(procName.contains("&")){
			String [] procArry=procName.split("&");
			for(int i=0;i<procArry.length;i++){
				List proclist=queryProc(procArry[i]);
				list.add(proclist);
			}
		}else{
			list=queryProc(procName);
		}
		//return null;
		return list;
		
	}
	

	
	public List queryProc(String procName) {
		//String procName=params.getString("procName");
		CallProc p=new CallProc();
		p.setProcname(procName);
		List<CallParameter> pa=new ArrayList<CallParameter>();		
		
		OutCallParameter p4 = new OutCallParameter();
		p4.setParameterIndex(1);
		p4.setParameterName("result");
		p4.setValueType(ParameterValueType.CURSOR);
		pa.add(p4);
		
		p.setParams(pa);
		super.executeProcedure(p);
		Map<String, List<?>> map=p.getReturnData().getResultmap();
		List l=map.get("result");
		return l;
	}


	@Override
	public List addSpmb(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtspmbService.setXtspmbxx(vo);
	}

	@Override
	public List modifySpmb(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtspmbService.setXtspmbxgxx(vo);
	}

	@Override
	public List resumeSpmb(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtspmbService.setXtspmbxgxx(vo);
	}

	@Override
	public List forbitSpmb(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtspmbService.setXtspmbscxx(vo);
	}

	@Override
	public List addSpdz(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtspdzService.setXtspdzxx(vo);
	}

	@Override
	public List modifySpdz(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtspdzService.setXtspdzxgxx(vo);
	}

	@Override
	public List deleteSpzd(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtspdzService.setXtspdzscxx(vo);
	}

	@Override
	public List resumeSpdz(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtspdzService.setXtspdzxgxx(vo);
	}




	@Override
	public List addSpdzl(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtsplService.setXtsplxx(vo);
	}

	@Override
	public int removeSpdzl(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtsplService.setXtsplscxx(vo);
	}

	@Override
	public int removeAllSpdzl(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtsplService.setXtspmbsplscxx(vo);
	}

	@Override
	public Page getGgyxipsetInfo(ExtMap<String, Object> params) {
		StringBuffer strBufHql = new StringBuffer();
	    strBufHql.append(
	        "select yhxxba.yhxm yhxm,yhxxba.yhdlm yhdlm ,yhipxxxb.ipyxid ipyxid,yhipxxxb.ipdz ipdz,yhxxbb.yhxm cjrxm,yhipxxxb.cjsj cjsj FROM XT_YHIPYXXXB yhipxxxb, ")
	        .append("XT_YHXXB yhxxba,XT_YHXXB yhxxbb  ")
	        .append(" where yhipxxxb.yhid = yhxxba.yhid(+)")
	        .append(" and yhipxxxb.cjrid = yhxxbb.yhid");
	    String sIpyxid = params.getString("ipyxid");
	    String sYhxm = params.getString("yhxm");
	    String sYhid = params.getString("yhid");
		if ( (sIpyxid != null) && ! ("".equals(sIpyxid))) {
		      strBufHql.append(" and  yhipxxxb.ipyxid =").append(sIpyxid);
	    }
	    if ( (sYhxm != null) && ! ("".equals(sYhxm))) {
	      strBufHql.append(" and  yhxxba.yhxm ='").append(sYhxm).append("'");
	    }
	    if ( (sYhid != null) && ! ("".equals(sYhid))) {
	      strBufHql.append(" and  yhipxxxb.yhid =").append(sYhid);
	    }
	    strBufHql.append(" order by yhipxxxb.ipyxid");
		List list = super.executeSqlQueryMapList(strBufHql.toString(), null,params.getInteger("pageIndex"),params.getInteger("pageSize"));
		Page p = new Page();
//		int pageIndex = params.getInteger("pageIndex");
//		int pageSize = params.getInteger("pageSize");
//		int fromIndex = (pageIndex-1)*pageSize;
//		int endIndex = pageIndex*pageSize;
//		p.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
		p.setList(list);
		p.setTotalCount(list.size());
		return p ;
//		VarVO vo  = new VarVO();
//		vo.setVarmap(params);
//		VoPage vopage = new VoPage();
//		vopage.setPageindex(params.getInteger("pageIndex"));
//		vopage.setPagesize(params.getInteger("pageSize"));
//		List list =  xtyhipyxService.getXtyhipyxxx(vo);
//		Page p = new Page();
//		p.setList(list);
//		p.setTotalCount(list.size());
//		return p ;
		
	}

	@Override
	public List addGgyxip(ExtMap<String, Object> params) {
//		String[] ipdzArray =params.getString("ipdzArray").split(",");
//		for(int i=0;i<ipdzArray.length;i++) {
//			Map map = new HashMap();
//			map.put("yhid", this.getUser().getYhid());
//			map.put("ipdz", ipdzArray[i]);
//			VarVO vo  = new VarVO();
//			vo.setVarmap(map);
//			xtyhipyxService.setXtyhipyxxxNew(vo);
//		}
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtyhipyxService.setXtyhipyxxxNew(vo);
	}

	@Override
	public List modifyGgyxip(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtyhipyxService.setXtyhipyxxgxx(vo);
	}

	@Override
	public int delGgyxip(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtyhipyxService.setXtyhipyxscxx(vo);
	}

	@Override
	public Page getBssqwhInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		VoPage voPage = new VoPage();
		VoQueryResult voQueryResult = xtbssqService.getXtbssqxx(vo, "",voPage);
		Page p = new Page();
		p.setList(voQueryResult.getPagelist());
		p.setTotalCount((int)voQueryResult.getRecordcount());
		return p;
	}

	@Override
	public List addBssqwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtbssqService.setXtbssqxxNew(vo);
	}

	@Override
	public List modifyBssqwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtbssqService.setXtbssqxgxx(vo);
	}

	@Override
	public List delBssqwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtbssqService.setXtbssqscxx(vo);
	}

	@Override
	public List resumeBssqwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtbssqService.setXtbssqxgxx(vo);
	}

	@Override
	public Page getQyszwhInfo(ExtMap<String, Object> params) {
		//获取参数信息
	    String sQhdm = params.getString("qhdm");
	    String sQybz = params.getString("qybz");
		 StringBuffer strBufHql = new StringBuffer();
		    strBufHql.append("select qyszb.qyid qyid, qyszb.qhdma qhdma,xzqhba.mc qhdmamc,qyszb.qhdmb qhdmb ")
		    .append(" ,xzqhbb.mc qhdmbmc,qyszb.cjsj cjsj,qyszb.cjrid cjrid,yhxxba.yhxm cjrxm,qyszb.xgsj xgsj,")
		    .append(" qyszb.xgrid xgrid,yhxxbb.yhxm xgrxm,qyszb.qybz qybz from XT_QYSZB qyszb,XT_XZQHB xzqhba,")
		    .append(" XT_XZQHB xzqhbb,XT_YHXXB yhxxba,XT_YHXXB yhxxbb where qyszb.qhdma = xzqhba.dm and ")
		    .append(" qyszb.qhdmb = xzqhbb.dm and qyszb.cjrid = yhxxba.yhid and qyszb.xgrid = yhxxbb.yhid(+) ");
	    if ( (sQhdm != null) && ! ("".equals(sQhdm))) {
	        strBufHql.append(" and  (qyszb.qhdma ='").append(sQhdm).append("'")
	            .append(" or qyszb.qhdmb ='").append(sQhdm).append("')");
	    }
	    if ( (sQybz != null) && ! ("".equals(sQybz))) {
	    	strBufHql.append(" and  qyszb.qybz ='").append(sQybz).append("'");
	    } 
	    Page p = new Page();
	    List list = super.executeSqlQueryMapList(strBufHql.toString(), null);
//	    List list = executeSqlQuery(strBufHql.toString(), new Object[] {},
//	    		params.getInteger("pageIndex"),params.getInteger("pageSize"));
	    p.setList(list);
		p.setTotalCount(list.size());
		return p ;
	}

	@Override
	public List addQyszwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtqyszService.setXtqyszxx(vo);
	}

	@Override
	public List modifyQyszwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtqyszService.setXtqyszxgxx(vo);
	}

	@Override
	public List delQyszwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtqyszService.setXtqyszscxx(vo);
	}

	@Override
	public List resumeQyszwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtqyszService.setXtqyszxgxx(vo);
	}

	@Override
	public Page getHhxlwhInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		List list = xthhxlService.getXthhxlxx(vo);
		Page p = new Page();
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		int fromIndex = (pageIndex-1)*pageSize;
		int endIndex = pageIndex*pageSize;
		p.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
		p.setTotalCount(list.size());
		return  p;
	}

	@Override
	public List addHhxlwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xthhxlService.setXthhxlxx(vo);
	}

	@Override
	public List modifyHhxlwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xthhxlService.setXthhxlxgxx(vo);
	}

	@Override
	public int delHhxlwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xthhxlService.setXthhxlscxx(vo);
	}

	@Override
	public Page getSlhxlbwhInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		List list = xtslhxlService.getXtslhxlxx(vo);
		Page p = new Page();
//		p.setList(list);
//		p.setTotalCount(list.size());
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		int fromIndex = (pageIndex-1)*pageSize;
		int endIndex = pageIndex*pageSize;
		p.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
		p.setTotalCount(list.size());
		return  p;
		
	}

	@Override
	public List addSlhxlbwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtslhxlService.setXtslhxlxx(vo);
	}

	@Override
	public List modifySlhxlbwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtslhxlService.setXtslhxlxgxx(vo);
	}

	@Override
	public int delSlhxlbwh(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtslhxlService.setXtslhxlscxx(vo);
	}

	@Override
	public Page getLnwsdwhInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		List list = xtlnwsdService.getXtlnwsdxx(vo);
		Page p = new Page();
//		p.setList(list);
//		p.setTotalCount(list.size());
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		int fromIndex = (pageIndex-1)*pageSize;
		int endIndex = pageIndex*pageSize;
		p.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
		p.setTotalCount(list.size());
		return  p;
	}

	@Override
	public List addLnwsdwhInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtlnwsdService.setXtlnwsdxx(vo);
	}

	@Override
	public List modifyLnwsdwhInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtlnwsdService.setXtlnwsdxgxx(vo);
	}

	@Override
	public int delLnwsdwhInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtlnwsdService.setXtlnwsdscxx(vo);
	}

	@Override
	public Page getBgdykzInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		List list = xtbgkzService.getXtbgdykzxx(vo);
		Page p = new Page();
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		int fromIndex = (pageIndex-1)*pageSize;
		int endIndex = pageIndex*pageSize;
		p.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
		p.setTotalCount(list.size());
		return  p;
	}

	@Override
	public List modifyBgdykzInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtbgkzService.setXtbgdykzxgxx(vo);
	}

	@Override
	public Page getYwblxzwhInfo(ExtMap<String, Object> params) {
		//获取参数信息
	    String sXzywlx = params.getString("xzywlx");
	    String sQybz = params.getString("qybz");
		 StringBuffer strBufHql = new StringBuffer();
		    strBufHql.append("select ywblxzxxb.xzxxid  xzxxid,ywblxzxxb.xzmc xzmc,ywblxzxxb.xzbds xzbds,")
		    .append(" yhxxba.yhxm cjrxm, ywblxzxxb.spmbid spmbid,ywblxzxxb.cjsj cjsj,yhxxbb.yhxm xgrxm, ")
		    .append(" ywblxzxxb.xgsj xgsj,spmbxxb.mbmc mbmc,ywblxzxxb.qybz qybz,ywblxzxxb.xzzt xzzt")
		    .append(" from HJXZ_YWBLXZXXB ywblxzxxb,XT_YHXXB yhxxba,XT_YHXXB yhxxbb,XT_SPMBXXB spmbxxb")
		    .append(" where ywblxzxxb.cjrid = yhxxba.yhid and ywblxzxxb.xgrid = yhxxbb.yhid(+) ")
		    .append(" and ywblxzxxb.spmbid = spmbxxb.spmbid(+) ");
	    if ( (sXzywlx != null) && ! ("".equals(sXzywlx))) {
	    	strBufHql.append(" and ywblxzxxb.xzywlx ='").append(sXzywlx).append("'");
	    }
	    if ( (sQybz != null) && ! ("".equals(sQybz))) {
	    	strBufHql.append(" and  ywblxzxxb.qybz ='").append(sQybz).append("'");
	    } 
	    Page p = new Page();
	    List list = super.executeSqlQueryMapList(strBufHql.toString(), null);
	    p.setList(list);
		p.setTotalCount(list.size());
		return p ;
	}

	@Override
	public List addYwblxzwhInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtywblxzService.setXtywblxzxx(vo);
	}

	@Override
	public List modifyYwblxzwhInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtywblxzService.setXtywblxzxgxx(vo);
	}

	@Override
	public List delYwblxzwhInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtywblxzService.setXtywblxzscxx(vo);
	}

	@Override
	public Page getXtywbllxInfo(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryXtcsxxByCslb", params);
	}

	@Override
	public List resumeYwblxzwhInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtywblxzService.setXtywblxzxgxx(vo);
	}
	@Override
	public Page getYwqxInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		List list = xtywqxService.getXtywqxxx(vo);
		Page p = new Page();
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		int fromIndex = (pageIndex-1)*pageSize;
		int endIndex = pageIndex*pageSize;
		p.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
		p.setTotalCount(list.size());
		return  p;
	}

	@Override
	public List modifyYwqxInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtywqxService.setXtywqxxgxx(vo);
	}

	@Override
	public Page getUserInfo(ExtMap<String, Object> params) {
		//获取参数信息
		 StringBuffer strBufHql = new StringBuffer();
		    strBufHql.append("select yhxxb.yhid yhid,yhxxb.yhxm yhxm,yhxxb.yhdlm yhdlm from XT_YHXXB yhxxb ,XT_DWXXB dwxxb ,XT_XTCSB xtcsba ,XT_XTCSB xtcsbb,")
		    .append(" XT_XTCSB xtcsbc,XT_XTCSB xtcsbd where yhxxb.dwdm(+) = dwxxb.dm and yhxxb.yhxb = xtcsba.dm(+)  and xtcsba.cslb(+) ='8003' ")
		    .append(" and yhxxb.yhzw = xtcsbb.dm(+) and xtcsbb.cslb(+) ='9009' and yhxxb.yhmj = xtcsbc.dm(+) and xtcsbc.cslb(+) ='9010' and ")
		    .append(" yhxxb.yhzt = xtcsbd.dm(+) and xtcsbd.cslb(+) ='9007' and yhxxb.yhzt<>'2' order by yhxxb.yhdlm");
	    Page p = new Page();
	    List list = super.executeSqlQueryMapList(strBufHql.toString(), null);
	    p.setList(list);
		p.setTotalCount(list.size());
		return p ;
	}

	@Override
	public Page getYhsjfwInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		List list = xtyhService.getXtyhsjfwxx(vo);
		Page p = new Page();
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		int fromIndex = (pageIndex-1)*pageSize;
		int endIndex = pageIndex*pageSize;
		p.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
		p.setTotalCount(list.size());
		return  p;
	}

	@Override
	public List addYhsjfwInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		List list = xtyhService.setXtyhsjfwxx(vo);
		return list;
	}

	@Override
	public int delYhsjfwInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtyhService.setXtyhsjfwscxx(vo);
	}

	@Override
	public Page getYhdtqxInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		List list = xtyhService.getXtyhdtqxxx(vo);
		Page p = new Page();
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		int fromIndex = (pageIndex-1)*pageSize;
		int endIndex = pageIndex*pageSize;
		p.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
		p.setTotalCount(list.size());
		return  p;
	}

	@Override
	public List addYhdtqxInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtyhService.setXtyhdtqxxx(vo);
	}

	@Override
	public int delYhdtqxInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtyhService.setXtyhdtqxscxx(vo);
	}

	@Override
	public Page querypcs_ws(ExtMap<String, Object> params) {
		String sql="SELECT dm,mc FROM XT_DWXXB";
		List list = super.executeSqlQuery(sql, null);
		List<PoXT_DWXXB> plist=new ArrayList<PoXT_DWXXB>();
		for(Object objs:list){
			Object[] obj=(Object[]) objs;
			PoXT_DWXXB po=new PoXT_DWXXB();
			po.setDm(obj[0].toString());
			po.setMc(obj[1].toString());
			plist.add(po);
		}
		Page page=new Page();
		page.setList(plist);
		page.setTotalCount(plist.size());
		return page;
	}
	
	public Map getwsd(String dwdm){
		String sql="select poxt_xzqhb1_.DM,"+
			       " poxt_lnwsd0_.DWDM, "+
			       " poxt_lnwsd0_.KSD, "+
			       " poxt_lnwsd0_.JSD, "+
			       " poxt_lnwsd0_.QYRQ,poxt_dwxxb2_.MC "+
			  " from XT_LNWSDB poxt_lnwsd0_, XT_XZQHB poxt_xzqhb1_, XT_DWXXB poxt_dwxxb2_ "+
			 " where (poxt_lnwsd0_.QHDM = poxt_xzqhb1_.DM) "+
			   " and (poxt_lnwsd0_.DWDM = poxt_dwxxb2_.DM) "+
			   " and (poxt_lnwsd0_.DWDM = "+dwdm+") "+
			 " order by poxt_lnwsd0_.QHDM,poxt_lnwsd0_.DWDM,"+
			  "poxt_lnwsd0_.QYRQ,poxt_lnwsd0_.KSD";
					List list = super.executeSqlQuery(sql, null);
					Map map=new HashMap();
					String ksd=null;
					String jsd=null;
					String dm=null;
					for(Object objs:list){
						Object[] obj=(Object[]) objs;
						dm=obj[0].toString();
						ksd=obj[2].toString();
						jsd=obj[3].toString();
						map.put("ksd", ksd);
						map.put("jsd", jsd);
						map.put("dm", dm);
					}
		return map;
		
	}

	@Override
	public Page gewslist(ExtMap<String, Object> params) {
		String dwdm=params.getString("dwdm");
		String mc=params.getString("dwmc");
		Integer sex=params.getInteger("sex");
		String month=params.getString("csrq");
		String sql="select poxt_xzqhb1_.DM,"+
       " poxt_lnwsd0_.DWDM, "+
       " poxt_lnwsd0_.KSD, "+
       " poxt_lnwsd0_.JSD, "+
       " poxt_lnwsd0_.QYRQ,poxt_dwxxb2_.MC "+
  " from XT_LNWSDB poxt_lnwsd0_, XT_XZQHB poxt_xzqhb1_, XT_DWXXB poxt_dwxxb2_ "+
 " where (poxt_lnwsd0_.QHDM = poxt_xzqhb1_.DM) "+
   " and (poxt_lnwsd0_.DWDM = poxt_dwxxb2_.DM) "+
   " and (poxt_lnwsd0_.DWDM = "+dwdm+") "+
 " order by poxt_lnwsd0_.QHDM,poxt_lnwsd0_.DWDM,"+
  "poxt_lnwsd0_.QYRQ,poxt_lnwsd0_.KSD";
		List list = super.executeSqlQuery(sql, null);
		String ksd=null;
		String jsd=null;
		String dm=null;
		for(Object objs:list){
			Object[] obj=(Object[]) objs;
			dm=obj[0].toString();
			ksd=obj[2].toString();
			jsd=obj[3].toString();
		}
		
		String csrq=(dm==null?null:dm.toString())+(month==null?null:month.toString());
		String sql2="select pohjyw_gms0_.FPID      as FPID,"+
               "pohjyw_gms0_.RYID      as RYID,"+
               "pohjyw_gms0_.XM        as XM,"+
               "pohjyw_gms0_.XB        as XB,"+
               "pohjyw_gms0_.CSRQ      as CSRQ,"+
               "pohjyw_gms0_.GMSFHM    as GMSFHM,"+
               "pohjyw_gms0_.DWDM      as DWDM,"+
               "pohjyw_gms0_.SXH       as SXH,"+
               "pohjyw_gms0_.CZLB      as CZLB,"+
               "pohjyw_gms0_.HJYWID    as HJYWID,"+
               "pohjyw_gms0_.SBSJ      as SBSJ,"+
               "pohjyw_gms0_.SBRYXM    as SBRYXM,"+
               "pohjyw_gms0_.SBRGMSFHM as SBRGMSFHM,"+
               "pohjyw_gms0_.SLSJ      as SLSJ,"+
               "pohjyw_gms0_.SLDW      as SLDW,"+
               " pohjyw_gms0_.SLRID     as SLRID "+
          " from HJYW_GMSFHMSXMFPXXB pohjyw_gms0_ "+
         " where (1 = 1) "+
           " and (((gmsfhm like '"+csrq+"%')) and "+
               " ((((sxh >= "+ksd+")) and ((sxh <= "+jsd+")))) and ((xb = "+sex+")) and (1 = 1))";
		List list2 = super.executeSqlQuery(sql2, null);
		List<PoHJYW_GMSFHMSXMFPXXB> wslist=new ArrayList<PoHJYW_GMSFHMSXMFPXXB>();
		for(Object objs:list2){
			Object []obj=(Object[]) objs;
			PoHJYW_GMSFHMSXMFPXXB po=new PoHJYW_GMSFHMSXMFPXXB();
			po.setCsrq(obj[4]==null?null:obj[4].toString());
			po.setSxh(obj[7]==null?null:obj[7].toString());
			if(null!=obj[3]){
				if(obj[3].toString().equals("1")){
					po.setXb("男");
				}
				if(obj[3].toString().equals("2")){
					po.setXb("女");
				}
			}
			
			po.setXm(obj[2]==null?null:obj[2].toString());
			po.setGmsfhm(obj[5]==null?null:obj[5].toString());
			po.setSldw(mc);
			wslist.add(po);
		}
		Page page=new Page();
		page.setList(wslist);
		page.setTotalCount(wslist.size());
		return page;
	}

	@Override
	public Page queryZxyh(ExtMap<String, Object> params) {
		String zhfwsj ="";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); 
		zhfwsj = sdf.format(date);
		//System.out.println(sdf.format(date));
		Page p = new Page();
		List<VoZxyhgl> jlxList =new ArrayList<VoZxyhgl>();
		List<?> list = super.getObjectListByHql("/conf/segment/common", "queryZxyh", params);
		for(Object o:list){
			VoZxyhgl zxyhgl = new VoZxyhgl();
			Object[] objs = (Object[])o;
			PoXT_YHHHXXB  yhhhxxb = (PoXT_YHHHXXB)objs[0];
			PoXT_YHXXB yhxxb = (PoXT_YHXXB)objs[1];
			zxyhgl.setJyh(yhxxb.getJyh());
			zxyhgl.setKhdbb(yhhhxxb.getKhdbb());
			zxyhgl.setYhdlm(yhxxb.getYhdlm());
			zxyhgl.setYhhhid(yhhhxxb.getYhhhid());
			zxyhgl.setYhip(yhhhxxb.getYhip());
			zxyhgl.setYhxb(yhxxb.getYhxb());
			zxyhgl.setYhxm(yhxxb.getYhxm());
			zxyhgl.setYhzt(yhxxb.getYhzt());
			zxyhgl.setZhfwsj(zhfwsj);
			jlxList.add(zxyhgl);
			if(!CommonUtil.isEmpty(yhxxb.getDwdm())) {
				String dwHql ="from "+PoXT_DWXXB.class.getName()+" where qybz='1' and dm='"+yhxxb.getDwdm()+"'";
				List dwList=super.findAllByHQL(dwHql);
				if(dwList.size()>0) {
					PoXT_DWXXB dwxxb = (PoXT_DWXXB) dwList.get(0);
					zxyhgl.setDwmc(dwxxb.getMc());
				}
			}
			if(!CommonUtil.isEmpty(yhxxb.getYhzw())) {
				String StrHql ="from "+PoXT_XTCSB.class.getName()+" where cslb='9009' and dm='"+yhxxb.getYhzw()+"'";
				List xt_xtcsbList=super.findAllByHQL(StrHql);
				if(xt_xtcsbList.size()>0) {
					PoXT_XTCSB xt_xtcsb = (PoXT_XTCSB) xt_xtcsbList.get(0);
					zxyhgl.setYhzw(xt_xtcsb.getMc());
				}
			}
		}
		p.setList(jlxList);
		return p;
	}

	@Override
	public int delYhhhid(ExtMap<String, Object> params) {
		PoXT_YHHHXXB  yhhhxxb = new PoXT_YHHHXXB();
		yhhhxxb = super.get(PoXT_YHHHXXB.class,Long.valueOf(params.
		          getString("yhhhid")));
		int nReturn = PublicConstant.XT_BZ_ERROR;

	    //执行HQL语句
	    try {
	    	super.delete(yhhhxxb);
	    }
	    catch (DAOException ex) {
	      throw ex;
	    }
	    catch (ServiceException ex) {
	      throw ex;
	    }
	    catch (Exception ex) {
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
	    return nReturn;
		
	}
	@Override
	public PoXT_DWXXB getpcsxx(String dm) {
		PoXT_DWXXB po=super.get(PoXT_DWXXB.class, dm);
		return po;
	}

	@Override
	public Page getYwxx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryHjls_hjtwlsb", params);
	}

	@Override
	public Page queryJs(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		List list = xtjsService.getXtjsxx(vo);
		Page p = new Page();
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		int fromIndex = (pageIndex-1)*pageSize;
		int endIndex = pageIndex*pageSize;
		p.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
		p.setTotalCount(list.size());
		return  p;
	}

	@Override
	public List addJs(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjsService.setXtjsxx(vo);
	}

	@Override
	public List modifyJs(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjsService.setXtjsxgxx(vo);
	}

	@Override
	public int delJs(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtjsService.setXtjsscxx(vo);
	}


	@Override
	public Page querycldxx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryClddy", params);	
		
	}


	@Override
	public Page queryYh(ExtMap<String, Object> params) {
		
		//获取参数信息
		 StringBuffer strBufHql = new StringBuffer();
		    strBufHql.append("select yhxxb.dlkl dlkl,yhxxb.yhid yhid,yhxxb.yhdlm yhdlm,yhxxb.jyh jyh,dwxxb.mc dwmc,yhxxb.dwdm dwdm,yhxxb.yhxm yhxm,yhxxb.yhxb yhxb,xtcsba.mc yhxbmc,yhxxb.yhzw yhzw ")
		    .append(" ,yhxxb.yhmj yhmj,yhxxb.czmj czmj,yhxxb.yhzt yhzt,yhxxb.gmsfhm gmsfhm,dwxxb.qhdm qhdm from XT_YHXXB yhxxb ,XT_DWXXB dwxxb ,XT_XTCSB xtcsba ,XT_XTCSB xtcsbb,")
		    .append(" XT_XTCSB xtcsbc,XT_XTCSB xtcsbd where yhxxb.dwdm(+) = dwxxb.dm and yhxxb.yhxb = xtcsba.dm(+)  and xtcsba.cslb(+) ='8003' ")
		    .append(" and yhxxb.yhzw = xtcsbb.dm(+) and xtcsbb.cslb(+) ='9009' and yhxxb.yhmj = xtcsbc.dm(+) and xtcsbc.cslb(+) ='9010' and ")
		    .append(" yhxxb.yhzt = xtcsbd.dm(+) and xtcsbd.cslb(+) ='9007' ");
		if(params.getString("qhdm")!=null) {
			strBufHql.append("and dwxxb.qhdm='"+params.getString("qhdm")+"'");
		}
		if(params.getString("qybz").equals("0"))  {
			strBufHql.append("and yhxxb.yhzt='2' order by yhxxb.yhdlm ");
		}else {
			strBufHql.append("and yhxxb.yhzt<>'2' order by yhxxb.yhdlm ");
		}
	    Page p = new Page();
	    List list = super.executeSqlQueryMapList(strBufHql.toString(), null);
	    p.setList(list);
		p.setTotalCount(list.size());
		return p ;
	}

	@Override
	public List addYh(ExtMap<String, Object> params) {
		//个人户籍打印设置表中，插入一条记录 PoPERSON_DY_SET  20190322 记录 后面补 
		Long yhid = 0l;
		PoPERSON_DY_SET poperson_dy_set = super.get(PoPERSON_DY_SET.class,yhid);
		if(poperson_dy_set==null) {
			poperson_dy_set.setBdxx_hjzmsz(PublicConstant.QYBZ_QY);
			poperson_dy_set.setBdyy_hjzmsz(PublicConstant.QYBZ_QY);
			poperson_dy_set.setCsyy_hkbsz(PublicConstant.QYBZ_BQY);
			poperson_dy_set.setDybyqk_hjzmsz(PublicConstant.QYBZ_BQY);
			poperson_dy_set.setDydw_hjzmsz(PublicConstant.QYBZ_QY);
			poperson_dy_set.setDyhh_hjzmsz(PublicConstant.QYBZ_BQY);
			poperson_dy_set.setDyhyzk_hjzmsz(PublicConstant.QYBZ_QY);
			poperson_dy_set.setDywhcd_hjzmsz(PublicConstant.QYBZ_BQY);
			poperson_dy_set.setDyyl_dysz(PublicConstant.QYBZ_QY);
			poperson_dy_set.setDyzp_cbsz(PublicConstant.QYBZ_QY);
			poperson_dy_set.setDyzp_hjzmsz(PublicConstant.QYBZ_QY);
			poperson_dy_set.setHcyxx_hjzmsz(PublicConstant.QYBZ_QY);
			poperson_dy_set.setHkbbm_hkbsz(PublicConstant.QYBZ_BQY);
			poperson_dy_set.setHkbsy_hkbsz(PublicConstant.QYBZ_BQY);
			poperson_dy_set.setJth_syksz(PublicConstant.QYBZ_BQY);
			poperson_dy_set.setJthfshksy_hkbsz(PublicConstant.QYBZ_BQY);
			poperson_dy_set.setJthfshky_hkbsz(PublicConstant.QYBZ_BQY);
			poperson_dy_set.setTcdysz_dysz(PublicConstant.QYBZ_QY);
			poperson_dy_set.setZxryxx_hjzmsz(PublicConstant.QYBZ_QY);
			this.create(poperson_dy_set);
		}
		return null;
	}

	@Override
	public List modifyYh(ExtMap<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delYh(ExtMap<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Page getXtyhdzqxxx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		List list = xtyhService.getXtyhdzqxxx(vo);
		Page p = new Page();
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		int fromIndex = (pageIndex-1)*pageSize;
		int endIndex = pageIndex*pageSize;
		p.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
		p.setTotalCount(list.size());
		return  p;
	}

	@Override
	public Page getXtyhjsxx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		List list = xtyhService.getXtyhjsxx(vo);
		Page p = new Page();
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		int fromIndex = (pageIndex-1)*pageSize;
		int endIndex = pageIndex*pageSize;
		p.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
		p.setTotalCount(list.size());
		return  p;
	}

	@Override
	public Page getXtyhsjfwxx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		List list = xtyhService.getXtyhsjfwxx(vo);
		Page p = new Page();
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		int fromIndex = (pageIndex-1)*pageSize;
		int endIndex = pageIndex*pageSize;
		p.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
		p.setTotalCount(list.size());
		return  p;
	}

	@Override
	public Page getXtyhdtqxxx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		List list = xtyhService.getXtyhdtqxxx(vo);
		Page p = new Page();
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		int fromIndex = (pageIndex-1)*pageSize;
		int endIndex = pageIndex*pageSize;
		p.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
		p.setTotalCount(list.size());
		return  p;
	}

	@Override
	public Page querycbddy(ExtMap<String, Object> params) {
		if(params.getString("fw").equals("2")) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		    Date dt = new Date();
			if(params.getString("sj") != null) {
				String str=params.getString("sj");
				try {
					dt = sdf.parse(str);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		        
			}
			Calendar rightNow = Calendar.getInstance();
	        rightNow.setTime(dt);
	        rightNow.add(Calendar.YEAR,-16);//日期减16年
	        Date dt1=rightNow.getTime();
	        String reStr = sdf.format(dt1);
			params.put("wcnlimit", reStr);
		}
		ParmPlus(params);
		return super.getPageRecords("/conf/segment/common", "queryCbddy", params);	
	}

	@Override
	public Page queryzjfj(ExtMap<String, Object> params) {
		StringBuffer buffer=new StringBuffer("select poww_fjjgr0_.CWH as x0_0_, count(*) as x1_0_ from WW_FJJGRZB poww_fjjgr0_ where (1 = 1)");
		String fjpch=params.getString("fjpch");
		String slh=params.getString("slh");
		String gmsfhm=params.getString("gmsfhm");
		String xm=params.getString("xm");
		String slzt=params.getString("slzt");
		String czrid=params.getString("czrid");
		String czrip=params.getString("czrip");
		String czrdw=params.getString("czrdw");
		String pcs=params.getString("pcs");
		String ssxq=params.getString("ssxq");
		String where = params.getString("where");
		if(StringUtils.isNotEmpty(where)){
			buffer.append(" and "+where+" ");
		}
		if(StringUtils.isNotEmpty(fjpch)){
			buffer.append(" and fjpch="+fjpch+" ");
		}
		if(StringUtils.isNotEmpty(slh)){
			buffer.append(" and slh="+slh+" ");
		}
		if(StringUtils.isNotEmpty(gmsfhm)){
			buffer.append(" and slh="+gmsfhm+" ");
		}
		if(StringUtils.isNotEmpty(xm)){
			buffer.append(" and xm='"+xm+"' ");
		}
		if(params.getInteger("_start_czsj") != null) {
			String start_czsj=params.getInteger("_start_czsj")+"000000";
			
		}
		if(params.getInteger("_end_czsj") != null) {
			String end_czsj=params.getInteger("_end_czsj")+"000000";
		}
		if(StringUtils.isNotEmpty(slzt)){
			buffer.append(" and slzt="+slzt+" ");
		}
		if(StringUtils.isNotEmpty(czrid)){
			buffer.append(" and czrid="+czrid+" ");
		}
		if(StringUtils.isNotEmpty(czrip)){
			buffer.append(" and czrip="+czrip+" ");
		}
		if(StringUtils.isNotEmpty(czrdw)){
			buffer.append(" and czrdw="+czrdw+" ");
		}
		if(StringUtils.isNotEmpty(pcs)){
			buffer.append(" and pcs="+pcs+" ");
		}
		if(StringUtils.isNotEmpty(ssxq)){
			buffer.append(" and ssxq="+ssxq+" ");
		}
		buffer.append(" group by cwh ");
		List list = super.executeSqlQuery(buffer.toString(), null);
		List<VoZjfjtjxx> plist=new ArrayList<VoZjfjtjxx>();
		if(plist.size()>0){
			for(Object objs:list){
				Object[] obj=(Object[]) objs;
				VoZjfjtjxx po=new VoZjfjtjxx();
				po.setCwh(obj[0].toString());
				po.setNum(Integer.parseInt(obj[1].toString()));
				plist.add(po);
			}
		}
		
		Page page=new Page();
		page.setList(plist);
		page.setTotalCount(plist.size());
		return page;
	}

	@Override
	public Page queryTjbbsjb(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "querytjbbsj", params);
	}

	@Override
	public Page addYhdzqx(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		List list = xtyhService.setXtyhdzqxxx(vo);
		Page p = new Page();
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		int fromIndex = (pageIndex-1)*pageSize;
		int endIndex = pageIndex*pageSize;
		p.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
		p.setTotalCount(list.size());
		return  p;
	}

	@Override
	public int delYhdzqxInfo(ExtMap<String, Object> params) {
		VarVO vo  = new VarVO();
		vo.setVarmap(params);
		return xtyhService.setXtyhdzqxscxx(vo);
	}

	@Override
	public Page getzpxx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryzpxx", params);
	}

	@Override
	public Page queryyhdghz(ExtMap<String, Object> params) {
		String cwtype=params.getString("type");
		 Page p = new Page();
		if(cwtype.equals("yhdghz")){
			p= super.getPageRecords("/conf/segment/common", "queryYhdghz", params);
	}
		if(cwtype.equals("qshz")){//缺少户主
			p = super.getPageRecords("/conf/segment/common", "queryQshz", params);
			
			if(p.getList() != null && p.getList().size() > 0) {
				List<VoHxxHqFhxx> listvo = new ArrayList<VoHxxHqFhxx>();
				for(int i=0;i<p.getList().size();i++){
					Object[] obj = (Object[])p.getList().get(i);
					PoHJXX_HXXB hxx = (PoHJXX_HXXB)obj[0];
					PoHJXX_MLPXXXXB mlp =(PoHJXX_MLPXXXXB) obj[1];
					
					 VoHxxHqFhxx voHxxHqFhxx = new VoHxxHqFhxx(hxx,mlp);
					 
					 
					 listvo.add(voHxxHqFhxx);
				}
				
				 p.setList(listvo);
				 p.setTotalCount(listvo.size());
				// return p;
			}
		}
		
		if(cwtype.equals("yrwh")){
			p= super.getPageRecords("/conf/segment/common", "queryYrwh", params);
			}
		
		if(cwtype.equals("yhwd")){
			p= super.getPageRecords("/conf/segment/common", "queryYhwd", params);
			}
			   
		if(cwtype.equals("qszp")){
			p= super.getPageRecords("/conf/segment/common", "queryQszp", params);
			
			}   
		if(cwtype.equals("czryxx")){
			p= super.getPageRecords("/conf/segment/common", "queryCzryxx", params);
			
			} 
		if(cwtype.equals("czhxx")){
		 p= super.getPageRecords("/conf/segment/common", "queryCzhxx", params);
			if(p.getList() != null && p.getList().size() > 0) {
				List<VoHxxHqFhxx> listvo = new ArrayList<VoHxxHqFhxx>();
				for(int i=0;i<p.getList().size();i++){
					Object[] obj = (Object[])p.getList().get(i);
					PoHJXX_HXXB hxx = (PoHJXX_HXXB)obj[0];
					PoHJXX_MLPXXXXB mlp =(PoHJXX_MLPXXXXB) obj[1];
					
					 VoHxxHqFhxx voHxxHqFhxx = new VoHxxHqFhxx(hxx,mlp);
					 
					 
					 listvo.add(voHxxHqFhxx);
				}
				
				 p.setList(listvo);
				 p.setTotalCount(listvo.size());
				// return p;
			}
			}
		
		if(cwtype.equals("czdxx")){
			p= super.getPageRecords("/conf/segment/common", "queryCzdxx", params);
			
			}
		if(cwtype.equals("bczdxx")){
			p= super.getPageRecords("/conf/segment/common", "queryBczdxx", params);
			
			}
		if(cwtype.equals("bczryxx")){
			p= super.getPageRecords("/conf/segment/common", "queryBczryxx", params);
			
			}
		
		if(cwtype.equals("chcx")){
			p= super.getPageRecords("/conf/segment/common", "queryChcx", params);
			
			}
		
		if(cwtype.equals("bczhxx")){
			p= super.getPageRecords("/conf/segment/common", "queryBczhxx", params);
				if(p.getList() != null && p.getList().size() > 0) {
					List<VoHxxHqFhxx> listvo = new ArrayList<VoHxxHqFhxx>();
					for(int i=0;i<p.getList().size();i++){
						Object[] obj = (Object[])p.getList().get(i);
						PoHJXX_HXXB hxx = (PoHJXX_HXXB)obj[0];
						PoHJXX_MLPXXXXB mlp =(PoHJXX_MLPXXXXB) obj[1];
						VoHxxHqFhxx voHxxHqFhxx = new VoHxxHqFhxx(hxx,mlp);
						listvo.add(voHxxHqFhxx);
					}
					p.setList(listvo);
					p.setTotalCount(listvo.size());
					// return p;
				}
			}
			return p ;
		
	}

	@Override
	public List queryDssc(ExtMap<String, Object> param) {
		String tjsj=param.getString("tjsj");
		CallProc proc = new CallProc();
		proc.setProcname("{call RPT_BB.P_BB_JTSJTJ(?,?,?,?)}");
		
		List<CallParameter> params = new ArrayList<CallParameter>();
		
		InCallParameter p1 = new InCallParameter();
		p1.setParameterIndex(1);
		p1.setValueType(ParameterValueType.STRING);
		p1.setParameterValue(tjsj);
		params.add(p1);
		
		InCallParameter p2 = new InCallParameter();
		p2.setParameterIndex(2);
		p2.setValueType(ParameterValueType.STRING);
		p2.setParameterValue(this.getUserInfo().getYhid());
		params.add(p2);
		
		InCallParameter p3 = new InCallParameter();
		p3.setParameterIndex(3);
		p3.setValueType(ParameterValueType.STRING);
		
		p3.setParameterValue(BaseContext.getUser().getIp());
		params.add(p3);
		
		OutCallParameter p4 = new OutCallParameter();
		p4.setParameterIndex(4);
		p4.setParameterName("result");
		p4.setValueType(ParameterValueType.STRING);
		params.add(p4);
		
		proc.setParams(params);
		super.executeProcedure(proc);
		Map<String, List<?>> map=proc.getReturnData().getResultmap();
		List l=map.get("result");
		return l;
	}


	@Override
	public Page querylzbl(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "querylzbl", params);
	}

	@Override
	public Page querylzdy(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "querylzdy", params);
	}


	@Override
	public Page getQxzjjsInfo(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "getQxzjjsInfo", params);
	}

	@Override
	public List saveQxzjjsInfo(ExtMap<String, Object> params) {
		ArrayList lstXtyh = new ArrayList();
		PoZJYW_SLXXB pozjym_slxxb = new PoZJYW_SLXXB();
		PoZJYW_SLXXB rtnpozjym_slxxb = new PoZJYW_SLXXB();
		pozjym_slxxb  = super.get(PoZJYW_SLXXB.class,Long.valueOf(params.getString("nbslid")));
		pozjym_slxxb.setSlzt("80");
		//执行HQL语句
		rtnpozjym_slxxb =     (PoZJYW_SLXXB)super.update(pozjym_slxxb);
	    lstXtyh.add(rtnpozjym_slxxb);
		return lstXtyh;
	}

	@Override
	public Page getQxzjffInfo(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "getQxzjffInfo", params);
	}
	@Override
	public List saveQxzjffInfo(ExtMap<String, Object> params) {
		ArrayList lstXtyh = new ArrayList();
		PoZJYW_SLXXB pozjym_slxxb = new PoZJYW_SLXXB();
		PoZJYW_SLXXB rtnpozjym_slxxb = new PoZJYW_SLXXB();
		pozjym_slxxb  = super.get(PoZJYW_SLXXB.class,Long.valueOf(params.getString("nbslid")));
		pozjym_slxxb.setSlzt("83");
		//执行HQL语句
		rtnpozjym_slxxb =     (PoZJYW_SLXXB)super.update(pozjym_slxxb);
	    lstXtyh.add(rtnpozjym_slxxb);
		return lstXtyh;
	}

	@Override
	public Page getDszjffInfo(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "getDszjffInfo", params);
	}

	@Override
	public List saveDszjffInfo(ExtMap<String, Object> params) {
		ArrayList lstXtyh = new ArrayList();
		PoZJYW_SLXXB pozjym_slxxb = new PoZJYW_SLXXB();
		PoZJYW_SLXXB rtnpozjym_slxxb = new PoZJYW_SLXXB();
		pozjym_slxxb  = super.get(PoZJYW_SLXXB.class,Long.valueOf(params.getString("nbslid")));
		pozjym_slxxb.setSlzt("73");
		//执行HQL语句
		rtnpozjym_slxxb =     (PoZJYW_SLXXB)super.update(pozjym_slxxb);
	    lstXtyh.add(rtnpozjym_slxxb);
		return lstXtyh;
	}

	@Override
	public Page getDblkzxxInfo(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "getDblkzxxInfo", params);
	}

	@Override
	public List modifyDblkzxx(ExtMap<String, Object> params) {
		ArrayList dblkzxx = new ArrayList();
		PoYDZJ_DBLKZ poydzj_dblkz = new PoYDZJ_DBLKZ();
		PoYDZJ_DBLKZ rtnydzj_dblkz = new PoYDZJ_DBLKZ();
		poydzj_dblkz = super.get(PoYDZJ_DBLKZ.class, params.getString("sbddm"));
		poydzj_dblkz.setJbdbl(Long.valueOf(params.getString("jbdbl"))); 
		rtnydzj_dblkz = (PoYDZJ_DBLKZ) super.update(poydzj_dblkz);
		dblkzxx.add(rtnydzj_dblkz);
		return dblkzxx;
	}

	@Override
	public List addDblkzxx(ExtMap<String, Object> params) {
		ArrayList dblkzxx = new ArrayList();
		PoYDZJ_DBLKZ poydzj_dblkz = new PoYDZJ_DBLKZ();
		PoYDZJ_DBLKZ rtnydzj_dblkz = new PoYDZJ_DBLKZ();
		try {
			BeanUtils.populate(poydzj_dblkz, params);
			poydzj_dblkz.setYdbl(0l);
			Date date = new Date();
			SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMddHHmmss");
			poydzj_dblkz.setCshsj(sdf.format(date));
			rtnydzj_dblkz = (PoYDZJ_DBLKZ) super.create(poydzj_dblkz);
			dblkzxx.add(rtnydzj_dblkz);
		} catch (DAOException ex) {
		      throw ex;
	    }
	    catch (ServiceException ex) {
	      throw ex;
	    }
	    catch (Exception ex) {
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex.getCause());
	    }
		
		return dblkzxx;
	}

	@Override
	public int delDblkzxx(ExtMap<String, Object> params) {
		ArrayList dblkzxx = new ArrayList();
		PoYDZJ_DBLKZ poydzj_dblkz = new PoYDZJ_DBLKZ();
		int nReturn = PublicConstant.XT_BZ_ERROR;
		poydzj_dblkz = super.get(PoYDZJ_DBLKZ.class, params.getString("sbddm"));
		try {
			super.delete(poydzj_dblkz);
			nReturn = PublicConstant.XT_BZ_SUCC;
		}catch (DAOException ex) {
		      throw ex;
	    }
	    catch (ServiceException ex) {
	      throw ex;
	    }
	    catch (Exception ex) {
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
		
		return nReturn;
	}

	@Override
	public Long addYdbzslxx(ExtMap<String, Object> params) {
		String s3 = params.getString("voYdzjsbxx");
		VoYdzjsbxx voSbjbxx = null;
		if(CommonUtil.isNotEmpty(s3)){
			voSbjbxx = JSONUtil.getJsonData(s3, "yyyyMMdd", VoYdzjsbxx.class);
		}
		VoYdzjsbxx[] temp = new VoYdzjsbxx[1];
		temp[0] = voSbjbxx;
		return zj2Service.processYdzjslyw(temp);
	}

	@Override
	public Page queryYdbzxx(ExtMap<String, Object> params) {
		PoXT_YHXXB yhxxb = this.getUser();
		PoXT_YHJSXXB poxt_yhjsxxb = super.get(PoXT_YHJSXXB.class,yhxxb.getYhid());
		if(poxt_yhjsxxb!=null&&poxt_yhjsxxb.getJsid()!=null) {
			PoXT_JSXXB poxt_jsxxb = super.get(PoXT_JSXXB.class,poxt_yhjsxxb.getJsid());
			if(poxt_jsxxb.getJsmc().equals("超级用户")) {
				params.put("admin", '1');
				params.put("sbddm", yhxxb.getDwdm().substring(0, 4));
			}
		}else {
			params.put("sbddm", yhxxb.getDwdm());
		}
		return super.getPageRecords("/conf/segment/common", "queryYdbzxx", params);
	}
	@Override
	public Page querylztj(ExtMap<String, Object> params) {
		StringBuffer buffer=new StringBuffer();
		String tjlx=params.getString("tjlx");
		String  ssxq=params.getString("ssxq");
		String  pcs=params.getString("pcs");
		String  xzjd=params.getString("xzjd");
	    if(tjlx.equals("ssxq")){
	    	buffer.append("select ssxq, count(case when dybz=0 then 1 end) wdy,"+
				"count(case when dybz=1 then 1 end) ydy,"+
				"count(case when dybz=2 then 1 end) zf,"+
				"count(*) from LSSFZ_SLXXB where 1=1 ");
	    }
	    if(tjlx.equals("pcs")){
	    	buffer.append("select pcs, count(case when dybz=0 then 1 end) wdy,"+
				"count(case when dybz=1 then 1 end) ydy,"+
				"count(case when dybz=2 then 1 end) zf,"+
				"count(*) from LSSFZ_SLXXB where 1=1 ");
	    }
	    if(tjlx.equals("xzjd")){
	    	buffer.append("select xzjd, count(case when dybz=0 then 1 end) wdy,"+
				"count(case when dybz=1 then 1 end) ydy,"+
				"count(case when dybz=2 then 1 end) zf,"+
				"count(*) from LSSFZ_SLXXB where 1=1 ");
	    }
	    if(tjlx.equals("jcwh")){
	    	buffer.append("select jcwh, count(case when dybz=0 then 1 end) wdy,"+
				"count(case when dybz=1 then 1 end) ydy,"+
				"count(case when dybz=2 then 1 end) zf,"+
				"count(*) from LSSFZ_SLXXB where 1=1 ");
	    }
	    if(null!=ssxq&&ssxq!=""){
	    	buffer.append(" and ssxq="+ssxq+" ");
	    }
	    if(null!=pcs&&pcs!=""){
	    	buffer.append(" and pcs="+pcs+" ");
	    }
	    if(null!=xzjd&&xzjd!=""){
	    	buffer.append(" and xzjd="+xzjd+" ");
	    }
	    if(tjlx.equals("ssxq")){
	    	buffer.append(" group by ssxq ");
	    }
	    if(tjlx.equals("pcs")){
	    	buffer.append(" group by pcs ");
	    }
	    if(tjlx.equals("xzjd")){
	    	buffer.append(" group by xzjd ");
	    }
	    if(tjlx.equals("jcwh")){
	    	buffer.append(" group by jcwh ");
	    }
		List list = super.executeSqlQuery(buffer.toString(), null);
		List<VoLztjxx> plist=new ArrayList<VoLztjxx>();
		long wdy_zj=0;
		long ydy_zj=0;
		long zf_zj=0;
		long num_zj=0;
			for(Object objs:list){
				Object[] obj=(Object[]) objs;
				VoLztjxx vo=new VoLztjxx();
				vo.setDwxx(obj[0].toString());
				vo.setWdy(Long.valueOf(obj[1].toString()));
				vo.setYdy(Long.valueOf(obj[2].toString()));
				vo.setZf(Long.valueOf(obj[3].toString()));
				vo.setNum(Long.valueOf(obj[4].toString()));
				wdy_zj+=Long.valueOf(obj[1].toString());
				ydy_zj+=Long.valueOf(obj[2].toString());
				zf_zj+=Long.valueOf(obj[3].toString());
				num_zj+=Long.valueOf(obj[4].toString());
				plist.add(vo);
			}
			if(list.size()>0){
				VoLztjxx vo=new VoLztjxx();
				vo.setDwxx("sum");
				vo.setNum(num_zj);
				vo.setWdy(wdy_zj);
				vo.setYdy(ydy_zj);
				vo.setZf(zf_zj);
				plist.add(vo);
			}
			
		Page page=new Page();
//		page.setPageIndex(0);
//		page.setPageSize(0);
		page.setList(plist);
		page.setTotalCount(plist.size());
		return page;
	}

	@Override
	public List xxzf(ExtMap<String, Object> params) {
		PoYDZJ_SBXXB sbxxb = new PoYDZJ_SBXXB();
		ArrayList lstXtyh = new ArrayList();
		 try {
			 sbxxb  = super.get(PoYDZJ_SBXXB.class,Long.valueOf(
					 params.getString("sbxxid")));
		      if (sbxxb == null) {
		        return null;
		      }
		      sbxxb.setTbbz(1L);
		      sbxxb.setSlzt("98"); //变动类型
		      PoYDZJ_SBXXB rtnpoXTJLXJWHDZB =      (PoYDZJ_SBXXB)super.update(sbxxb);
		      lstXtyh.add(rtnpoXTJLXJWHDZB);
		    }
		    catch (DAOException ex) {
		      throw ex;
		    }
		    catch (ServiceException ex) {
		      throw ex;
		    }
		    catch (Exception ex) {
		      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
		    }
		return lstXtyh;
	}

	@Override
	public Page queryYdbzlzxx(ExtMap<String, Object> params) {
		PoXT_YHXXB yhxxb = this.getUser();
		params.put("sbddm", yhxxb.getDwdm());
		return super.getPageRecords("/conf/segment/common", "queryYdbzlzxx", params);
	}

	@Override
	public List saveLz(ExtMap<String, Object> params) {
		VoYdzjlqhqxx voYdzjlqhqxx = new VoYdzjlqhqxx();
		voYdzjlqhqxx.setSbxxid(Long.valueOf(params.getString("sbxxid")));
		voYdzjlqhqxx.setLzrdh(params.getString("lzrdh"));//params.getString("sbxxid")
		voYdzjlqhqxx.setLzrgmsfhm(params.getString("lzrgmsfhm"));
		voYdzjlqhqxx.setLzrxm(params.getString("lzrxm"));
		//voYdzjlqhqxx.setYwfhsbxxid(ywfhsbxxid);
		VoYdzjlqhqxx[] temp = new VoYdzjlqhqxx[1];
		temp[0]=voYdzjlqhqxx;
		VoYdzjlqhqxx[] voYdzjlqhqxxList = zj2Service.processYdzjlqffyw(temp).getYdzjlqhqxx();
		return Arrays.asList(voYdzjlqhqxxList);
	}

	@Override
	public Page queryZjslxx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryZjslxx", params);
	}

	@Override
	public VoZjslywfhxx saveZjsl(ExtMap<String, Object> params) {
		VoZjslxx[] voZjsjxx = new VoZjslxx[1];
		VoZjslxx temp = new VoZjslxx();
		try {
			BeanUtils.copyProperties(temp, params);
			voZjsjxx[0] = temp;
		} catch (DAOException ex) {
		      throw ex;
	    }
	    catch (ServiceException ex) {
	      throw ex;
	    }
	    catch (Exception ex) {
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
		return zj2Service.processZjslyw(voZjsjxx);
	}

	@Override
	public Page queryslxx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryslxx", params);
	}

	@Override
	public Page queryzjcx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryzjcx", params);
	}

	@Override
	public Page queryLsZjslxx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryLsZjslxx", params);
	}

	@Override
	public Page queryZjjsxx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryZjjsxx", params);
	}
	@Override
	public List saveZjjsxx(ExtMap<String, Object> params) {
		ArrayList lstXtyh = new ArrayList();
		PoZJYW_SLXXB pozjym_slxxb = new PoZJYW_SLXXB();
		PoZJYW_SLXXB rtnpozjym_slxxb = new PoZJYW_SLXXB();
		pozjym_slxxb  = super.get(PoZJYW_SLXXB.class,Long.valueOf(params.getString("nbslid")));
		pozjym_slxxb.setSlzt("83");
		//执行HQL语句
		rtnpozjym_slxxb =     (PoZJYW_SLXXB)super.update(pozjym_slxxb);
	    lstXtyh.add(rtnpozjym_slxxb);
		return lstXtyh;
	}

	@Override
	public VoZjsjywfhxx zjsjsave(ExtMap<String, Object> params) {
		VoZjsjxx[] voZjsjxx = new VoZjsjxx[1];
		VoZjsjxx temp = new VoZjsjxx();
		try {
			BeanUtils.copyProperties(temp, params);
			voZjsjxx[0] = temp;
		} catch (DAOException ex) {
		      throw ex;
	    }
	    catch (ServiceException ex) {
	      throw ex;
	    }
	    catch (Exception ex) {
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
		return zj2Service.processZjsjyw(voZjsjxx);
	}

	@Override
	public Page queryZjlqxx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryZjlqxx", params);
	}

	@Override
	public VoZjlqffywfhxx saveZjlqxx(ExtMap<String, Object> params) {
		ExtMap<String, Object> params1 = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		VoZjlqffxx[] voZjlqxx=new VoZjlqffxx[1];
		VoZjlqffxx voZjlqxxpo=new VoZjlqffxx();
		try {
			BeanUtils.copyProperties(voZjlqxxpo, params);
		} catch (DAOException ex) {
		      throw ex;
	    }
	    catch (ServiceException ex) {
	      throw ex;
	    }
	    catch (Exception ex) {
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
		voZjlqxx[0]=voZjlqxxpo;
		return zj2Service.processZjlqffyw(voZjlqxx);
	}

	@Override
	public void delBbzslid(ExtMap<String, Object> params) {
		Long lBzslid = params.getLong("bzslid");
		zj1Service.processZjslscyw(lBzslid);
	}

	@Override
	public void saveDysz(ExtMap<String, Object> params) {
		PoPERSON_DY_SET poperson_dy_set = super.get(PoPERSON_DY_SET.class,this.getUser().getYhid());
		try {
			BeanUtils.copyProperties(poperson_dy_set, params);
		} catch (DAOException ex) {
		      throw ex;
	    }
	    catch (ServiceException ex) {
	      throw ex;
	    }
	    catch (Exception ex) {
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
		super.update(poperson_dy_set);
	}

	@Override
	public VoYdzZjslywfhxx saveZjbl(ExtMap<String, Object> params) {
		VoYdzZjslxx[] voydzzjslxx=new VoYdzZjslxx[1];
		VoYdzZjslxx voydzzjslxxpo=new VoYdzZjslxx();
		try {
			BeanUtils.copyProperties(voydzzjslxxpo, params);
		} catch (DAOException ex) {
		      throw ex;
	    }
	    catch (ServiceException ex) {
	      throw ex;
	    }
	    catch (Exception ex) {
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
		voydzzjslxx[0]=voydzzjslxxpo;
		return zj1Service.processZjslyw(voydzzjslxx);
	}

	@Override
	public Page checkslxx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "checkslxx", params);
	}

	@Override
	public List lssfzSlyw(ExtMap<String, Object> params) {
		CdsVO psCdsVo[] =  new CdsVO[1];
		CdsVO cdsvo =  new CdsVO();
		Map newmap = new HashMap<>();
		newmap.put("ryid", params.getString("ryid"));
		cdsvo.setNewmap(newmap);
		psCdsVo[0]=cdsvo;
		return lssfzService.LssfzSlyw(psCdsVo);
	}

	@Override
	public VoMessageRtxx fsxx(ExtMap<String, Object> params) {
		VoMessagexx voMessagexx = new VoMessagexx();
		try {
			BeanUtils.copyProperties(voMessagexx, params);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return messageService.processFsxx(params);
	}

	@Override
	public Page queryXx(ExtMap<String, Object> params) {
		if(params.getString("jssj_start") != null&&params.getString("jssj_start").length()==8) {
			params.put("jssj_start", params.getString("jssj_start")+"000000");
		}
		if(params.getString("jssj_end") != null&&params.getString("jssj_end").length()==8) {
			params.put("jssj_end", params.getString("jssj_end")+"235959");
		}
		return super.getPageRecords("/conf/segment/common", "queryXx", params);
	}

	@Override
	public Page queryZjxhxx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryZjxhxx", params);
	}

	@Override
	public List saveZjxhxx(ExtMap<String, Object> params) {
		VoZjxhxx voZjxhxx = null;
		voZjxhxx = JSONUtil.fromJSON(params.toString(), VoZjxhxx.class);
		VoZjxhxx[] temp = new VoZjxhxx[1];
		temp[0] = voZjxhxx;
		return Arrays.asList(zj2Service.processZjxhyw(temp));
	}

	@Override
	public Page dzwhxxcx(ExtMap<String, Object> params) {
		if(params.getString("mlph") != null) {
			params.put("mlphToDBC", CommonUtil.ToDBC(params.getString("mlph")));
			params.put("mlphToSBC", CommonUtil.ToSBC(params.getString("mlph")));
		}
		if(params.getString("mlxz") != null) {
			params.put("mlxzToDBC", CommonUtil.ToDBC(params.getString("mlxz")));
			params.put("mlxzToSBC", CommonUtil.ToSBC(params.getString("mlxz")));
		}
		return super.getPageRecords("/conf/segment/common", "dzwhxxcx", params);
	}

	@Override
	public List searchJlxByJwh(ExtMap<String, Object> params) {
		return  super.getPageRecords("/conf/segment/common", "searchJlxByJwh", params).getList();
	}

	@Override
	public VoDzzjywfhxx insertDzwhxx(ExtMap<String, Object> params) {
		VoDzzjxx voDzzjxx[] = new VoDzzjxx[1];
		VoDzzjxx temp = new VoDzzjxx();
	    try {
			BeanUtils.copyProperties(temp, params);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	    voDzzjxx[0] = temp;
		return dzService.processDzzjyw(voDzzjxx);
	}

	@Override
	public List updateDzwhxx(ExtMap<String, Object> params) {
		Long mlpnbid = params.getLong("mlpnbid");
		ArrayList lstXtyh = new ArrayList();
		PoHJXX_MLPXXXXB po  = super.get(PoHJXX_MLPXXXXB.class,mlpnbid);
		PoHJXX_MLPXXXXB rtnPo  = new PoHJXX_MLPXXXXB();
		if (po == null) {
	          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
	                                     "找不到门楼牌信息，保存业务无法完成。", null);
	        }
		try {
			BeanUtils.copyProperties(po, params);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		rtnPo = (PoHJXX_MLPXXXXB) super.update(po);
		lstXtyh.add(rtnPo);
		return lstXtyh;
	}

	@Override
	public VoQhtzywfhxx processQhtzywQbgg(ExtMap<String, Object> params, PoHJXX_MLPXXXXB bgMlpPo, int[] bgFlagarr) {
		if(params.getString("mlph") != null) {
			params.put("mlphToDBC", CommonUtil.ToDBC(params.getString("mlph")));
			params.put("mlphToSBC", CommonUtil.ToSBC(params.getString("mlph")));
		}
		if(params.getString("mlxz") != null) {
			params.put("mlxzToDBC", CommonUtil.ToDBC(params.getString("mlxz")));
			params.put("mlxzToSBC", CommonUtil.ToSBC(params.getString("mlxz")));
		}
		List<PoHJXX_MLPXXXXB> l1 = (List<PoHJXX_MLPXXXXB>) super.getObjectListByHql("/conf/segment/common", "queryMlpxx", params);
		int length = l1.size();
		VoQhtzxx voQhtzxx[] = new VoQhtzxx[length];
		for(int i = 0;i<length;i++) {
			VoQhtzxx tempVoQhtzxx = new VoQhtzxx();
			 try {
					BeanUtils.copyProperties(tempVoQhtzxx, l1.get(i));
					if(bgFlagarr[0]==1) {
						tempVoQhtzxx.setJcwh(bgMlpPo.getJcwh());
					}
					if(bgFlagarr[1]==1) {
						tempVoQhtzxx.setZrq(bgMlpPo.getZrq());
					}
					if(bgFlagarr[2]==1) {
						tempVoQhtzxx.setJlx(bgMlpPo.getJlx());
					}
					if(bgFlagarr[3]==1) {
						tempVoQhtzxx.setMlph(bgMlpPo.getMlph());
					}
					if(bgFlagarr[4]==1) {
						tempVoQhtzxx.setMlxz(bgMlpPo.getMlxz());
					}
					voQhtzxx[i] = tempVoQhtzxx;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
		}
		return dzService.processQhtzywAHTZ(voQhtzxx);
	}

	@Override
	public List queryJwhByssxq(ExtMap<String, Object> params) {
		List<PoXT_JWHXXB> l1 = (List<PoXT_JWHXXB>) super.getObjectListByHql("/conf/segment/common", "queryJwhByssxq", params);
		List<Code> list = new ArrayList<Code>();
		for(Object objx:l1){
			PoXT_JWHXXB qh = (PoXT_JWHXXB)objx;
			
			Code code = new Code();
			code.setCode(qh.getDm());
			code.setName(qh.getMc());
			code.setPyt(qh.getDwdm());
			list.add(code);
			
			if(list.size()>100)
				break;
		}
		return list;
	}

	@Override
	public void saveEdzDysz(ExtMap<String, Object> params) {
		PoDW_DY_SET dw_dy_set = super.get(PoDW_DY_SET.class,this.getUser().getDwdm());
		try {
			BeanUtils.copyProperties(dw_dy_set, params);
			super.update(dw_dy_set);
		} catch (DAOException ex) {
		      throw ex;
	    }
	    catch (ServiceException ex) {
	      throw ex;
	    }
	    catch (Exception ex) {
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
	}

	@Override
	public Page getEdzsllsxxlist(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "getEdzsllsxxlist", params);
	}

	@Override
	public List getEdzsllsxxInfo(ExtMap<String, Object> params) {
		return super.getObjectListByHql("/conf/segment/common", "getEdzsllsxxInfo", params);
	}

	@Override
	public VoZjslzfywfhxx zjslzf(ExtMap<String, Object> params) {
		VoZjslzfxx voZjslzfxx[] = new VoZjslzfxx[1];
		VoZjslzfxx temp = new VoZjslzfxx();
	    try {
			BeanUtils.copyProperties(temp, params);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	    voZjslzfxx[0] = temp;
		return zj2Service.processZjslzfyw(voZjslzfxx);
	}

	@Override
	public VoZjdbzfywfhxx zjdbzf(ExtMap<String, Object> params) {
		VoZjdbzfxx voZjslzfxx[] = new VoZjdbzfxx[1];
		VoZjdbzfxx temp = new VoZjdbzfxx();
	    try {
			BeanUtils.copyProperties(temp, params);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	    voZjslzfxx[0] = temp;
		return zj2Service.processZjdbzfyw(voZjslzfxx);
	}

	@Override
	public Page queryzjgscx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryzjgscx", params);
	}

	@Override
	public Page queryFjshxx(ExtMap<String, Object> params) {
		int ywlimit = this.getDwDySet().getYwlimit();
		if(ywlimit>0) {
			params.put("rowNum",ywlimit);
		}
		return super.getPageRecords("/conf/segment/common", "queryFjshxx", params);
	}

	@Override
	public PoHJXX_ZPLSB queryZplsb(Long zpid) {
		PoHJXX_ZPLSB po = (PoHJXX_ZPLSB) super.getObject("from PoHJXX_ZPLSB where zpid=? ",
				new Long[] { zpid});
		return po;
	}

	@Override
	public Page queryLsshxx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryLsshxx", params);
	}

	@Override
	public VoZjshywfhxx processZjshyw(ExtMap<String, Object> params) {
		PoXT_DWXXB dwxxb = super.get(PoXT_DWXXB.class,this.getUser().getDwdm());
		if(dwxxb != null) {
			String dwjb = dwxxb.getDwjb();
			String dwdm = dwxxb.getDm();
			String gnbh="";
			String shdata = params.getString("shdata");
			VoZjshxx voZjshxxTemp = null;
			List<VoZjshxx> voZjshxxList = new ArrayList<VoZjshxx>();
			if(CommonUtil.isNotEmpty(shdata)){
				TypeToken<List<VoZjshxx>> typeToken = new TypeToken<List<VoZjshxx>>(){};
				voZjshxxList = JSONUtil.getJsonData(typeToken, shdata);
			}
			if(voZjshxxList.size()==0) {
				throw new ServiceException(WSErrCode.ERR_SERVICE_DATAINVALATE,
                        "数据为空无效。", null);
			}
			VoZjshxx voZjshxx[] = new VoZjshxx[voZjshxxList.size()];
			for(int i=0;i<voZjshxxList.size();i++) {
				voZjshxx[i]=voZjshxxList.get(i);
			}
			if(dwdm.endsWith("0000000")) {//7个0表示省厅
				return zj2Service.processStZjshyw(voZjshxx);
			}else {
				if(dwjb.equals("1")) {
					return zj2Service.processDsZjshyw(voZjshxx);
				}else if(dwjb.equals("0")) {
					return zj2Service.processQxZjshyw(voZjshxx);
				}
				return null;
			}
			
		}else {
			return null;
		}
	}

	@Override
	public Page queryFjqfxx(ExtMap<String, Object> params) {
		int ywlimit = this.getDwDySet().getYwlimit();
		if(ywlimit>0) {
			params.put("rowNum",ywlimit);
		}
		return super.getPageRecords("/conf/segment/common", "queryFjqfxx", params);
	}

	@Override
	public VoZjshfhxx[] processZjqfyw(ExtMap<String, Object> params) {
		String shdata = params.getString("shdata");
		VoZjshxx voZjshxxTemp = null;
		List<VoZjslxx> voZjqfxxList = new ArrayList<VoZjslxx>();
		if(CommonUtil.isNotEmpty(shdata)){
			TypeToken<List<VoZjslxx>> typeToken = new TypeToken<List<VoZjslxx>>(){};
			voZjqfxxList = JSONUtil.getJsonData(typeToken, shdata);
		}
		if(voZjqfxxList.size()==0) {
			throw new ServiceException(WSErrCode.ERR_SERVICE_DATAINVALATE,
                    "数据为空无效。", null);
		}
		VoZjslxx voZjslxx[] = new VoZjslxx[voZjqfxxList.size()];
		for(int i=0;i<voZjqfxxList.size();i++) {
			voZjslxx[i]=voZjqfxxList.get(i);
		}
		return zj2Service.processQxZjqfyw(voZjslxx);
	}

	@Override
	public List queryEchartsData(ExtMap<String, Object> params) {
		List list = super.getObjectListByHql("/conf/segment/common", "queryEchartsData", params);
		for(Object obj:list){
			PoECHARTSDATA poECHARTSDATA = (PoECHARTSDATA) obj;
			params.put("qhdmTemp", poECHARTSDATA.getQhdm());
			poECHARTSDATA.setXzqhList((List<PoSSXQQRSFFBB>) super.getObjectListByHql("/conf/segment/common", "querySSXQQRSFFBB", params));
		}
		return list;
	}

	@Override
	public Page querySjshxx(ExtMap<String, Object> params) {
		int ywlimit = this.getDwDySet().getYwlimit();
		if(ywlimit>0) {
			params.put("rowNum",ywlimit);
		}
		return super.getPageRecords("/conf/segment/common", "querySjshxx", params);
	
	}

	@Override
	public Page queryZxqsgx(ExtMap<String, Object> params) {
		String hsql = "";
		String gmsfhm1 = params.getString("gmsfhm1").trim();
		String gmsfhm2 = params.getString("gmsfhm2").trim();
		AuthToken u = HSession.getBaseUser();
		if(u != null) {
			//hsql="select distinct t1.xm as xm1,t1.xb as xb1 ,t1.csrq as csrq1,t1.gmsfhm as gmsfhm1 ,t2.xm as xm2, t2.xb as xb2,t2.csrq as csrq2,t2.gmsfhm as gmsfhm2,t2.yhzgx as zxqsgx,'"+u.getOrganize().getDm()+"' as pcs,t1.rynbid as rynbid from PoHJXX_CZRKJBXXB t1,PoHJXX_CZRKJBXXB t2 where t1.gmsfhm='" + gmsfhm1 + "' and t2.gmsfhm='" + gmsfhm2 + "' and t1.hhnbid=t2.hhnbid and t1.yhzgx in ('01','02') and ((t2.yhzgx >='20' and t2.yhzgx<='26') or  (t2.yhzgx >='30' and t2.yhzgx<='36') or  (t2.yhzgx >='40' and t2.yhzgx<='44') or (t2.yhzgx >='50' and t2.yhzgx<='52') or (t2.yhzgx >='60' and t2.yhzgx<='64')) ";
			hsql="select distinct t1.xm as xm1,t1.xb as xb1 ,t1.csrq as csrq1,t1.gmsfhm as gmsfhm1 ,t2.xm as xm2, t2.xb as xb2,t2.csrq as csrq2,t2.gmsfhm as gmsfhm2,t2.yhzgx as zxqsgx,'"+u.getOrganize().getDm()+"' as pcs,t1.rynbid as rynbid from PoHJXX_CZRKJBXXB t1,PoHJXX_CZRKJBXXB t2 where t1.gmsfhm='" + gmsfhm1 + "' and t2.gmsfhm='" + gmsfhm2 + "' ";

			String where1 ="";
			String where2 ="";
			boolean isAdmin = u.isAdmin();	
			if(!isAdmin) {
				List<PoXT_YHSJFWB> list = u.getSjfw();
				Iterator<PoXT_YHSJFWB> it = list.iterator();
				while(it.hasNext()){
				    PoXT_YHSJFWB x = it.next();
				    if(x.getXqlx().equals(PublicConstant.XQLX_XQW)){
				        it.remove();
				    }
				}
				if(CommonUtil.isEmpty(list)) {
					throw new RuntimeException("此用户没有数据范围，无法查询！");
				}
				where1 = " and ( 0=1 ";
				where2 = " and ( 0=1 ";
				for (PoXT_YHSJFWB o:list) {
					String sjfwstr = o.getSjfw();
					if(CommonUtil.isNotEmpty(sjfwstr)){
						Set<String> set = new HashSet<String>();
						
						String sjfw[] = sjfwstr.split("\\|");
						for(int i = 0;i<sjfw.length;i++){
							String bm = sjfw[i];
							bm = bm.trim();
							if(CommonUtil.isEmpty(bm) || set.contains(bm))
								continue;
							if(sjfw.length==3&&i==2&&bm.length()==12) {
								where1 += " or t1.jcwh= '" + bm + "'";
								where2 += " or t2.jcwh= '" + bm + "'";
							}
							if(sjfw.length==2&&i==1&&bm.length()==9) {
								where1 += " or t1.pcs= '" + bm + "'";
								where2 += " or t2.pcs= '" + bm + "'";
							}
							if(sjfw.length==1&&i==0&&bm.length()==6) {
								where1 += " or t1.ssxq = '" + bm + "'";
								where2 += " or t2.ssxq = '" + bm + "'";
							}
							set.add(bm);
						}
					}
				}
				where1 += ")";
				where2 += ")";
			}
			params.put("where1",where1);
			params.put("where2",where1);
			hsql=hsql+where1+where2;
			List list= super.findAllByHQL(hsql);
			Page p = new Page();
			List<VoZxqsgx> listTemp = new ArrayList<>();
			if(list.size()>0) {
				Object[] objs = (Object[]) list.get(0);
				VoZxqsgx vo = new VoZxqsgx();
				vo.setXm1(objs[0]==null?" ":objs[0].toString());
				vo.setXb1(objs[1]==null?" ":objs[1].toString());
				vo.setCsrq1(objs[2]==null?" ":objs[2].toString());
				vo.setGmsfhm1(objs[3]==null?" ":objs[3].toString());
				vo.setXm2(objs[4]==null?" ":objs[4].toString());
				vo.setXb2(objs[5]==null?" ":objs[5].toString());
				vo.setCsrq2(objs[6]==null?" ":objs[6].toString());
				vo.setGmsfhm2(objs[7]==null?" ":objs[7].toString());
				vo.setZxqsgx(objs[8]==null?" ":params.getString("zsqsgx")==null?"":params.getString("zsqsgx").trim());
				vo.setPcs(this.getUser().getDwdm());
				vo.setRynbid(objs[10]==null?" ":objs[10].toString());
				List<?> seq = this.executeSqlQuery("select VoZxqsgx.nextval from dual", new Object[]{});
				vo.setZxqsgxid(Long.parseLong(seq.get(0).toString()));
				listTemp.add(vo);
			}
			p.setList(listTemp);
			p.setPageSize(listTemp.size());
			return p;
		}
		return null;
	}

	@Override
	public Page querySjslxx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "querySjslxx", params);
	}

	@Override
	public Page queryFjslxx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryFjslxx", params);
	}

	@Override
	public List querySsxqqrsffbbData(ExtMap<String, Object> params) {
		List list = this.executeSqlQuery("select qcdsdm ,qcdsmc,sum(COUNT) from SSXQQRSFFBB group by qcdsdm,qcdsmc order by sum(COUNT) desc ", new Object[]{});
		//super.getObjectListByHql("/conf/segment/common", "querySSXQQRSFFBB", params);
		return list;
	}

	@Override
	public Page getWdjhkcx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryWdjhkcx", params);
	}

	@Override
	public Page getFhcx(ExtMap<String, Object> params) {
		String gmsfhm = "";
		String xm = "";
		Page p = super.getPageRecords("/conf/segment/common", "queryFhcx", params);
		Page p1 = new Page();
		List<PoHJXX_CZRKJBXXB> listTemp1 = (List<PoHJXX_CZRKJBXXB>) p.getList();
		List<VoFh> listTemp2 = new ArrayList<>();
		if(listTemp1.size()<=1) {
			VoFh vofh = new VoFh();
			vofh.setGmsfhm(params.getString("gmsfhm"));
			vofh.setXm("");
			vofh.setIsfh("2");
			listTemp2.add(vofh);
			p1.setList(listTemp2);
			p1.setPageSize(listTemp2.size());
			return p1;
		}else {
			PoHJXX_CZRKJBXXB hjxx_czrkjbxxb = listTemp1.get(0);
			VoFh vofh = new VoFh();
			vofh.setGmsfhm(hjxx_czrkjbxxb.getGmsfhm());
			vofh.setXm(hjxx_czrkjbxxb.getXm());
			vofh.setIsfh("1");
			listTemp2.add(vofh);
			p1.setList(listTemp2);
			p1.setPageSize(listTemp2.size());
			return p1;
		}
	}

	@Override
	public Page queryGmsftyrxzhc(ExtMap<String, Object> params) {

		String hsql = "";
		String gmsfhm1 = params.getString("gmsfhm1").trim();
		String gmsfhm2 = params.getString("gmsfhm2").trim();
		AuthToken u = HSession.getBaseUser();
		if(u != null) {
			hsql="from "+PoHJYW_BGGZXXB.class.getName()+" where bggzxm='公民身份号码' and  ((bggzqnr ='"+gmsfhm1+"' and bggzhnr ='"+gmsfhm2+"') or (bggzqnr ='"+gmsfhm2+"' and bggzhnr ='"+gmsfhm1+"'))";
			List list= super.findAllByHQL(hsql);
			
			Page p = new Page();
			List<VoGmsftyr> listTemp = new ArrayList<>();
			if(list.size()>0) {
				PoHJYW_BGGZXXB hjyw_bggzxxb =  (PoHJYW_BGGZXXB) list.get(0);
				String StrHqlById ="from "+PoHJXX_CZRKJBXXB.class.getName()+" where  rynbid='"+hjyw_bggzxxb.getRynbid()+"'";
				List hjxx_czrkjbxxbList = super.findAllByHQL(StrHqlById);
				for (int i = 0;i<list.size();i++) {
					VoGmsftyr vo = new VoGmsftyr();
					vo.setGmsfhm1(gmsfhm1);
					vo.setGmsfhm2(gmsfhm2);
					if(hjxx_czrkjbxxbList.size()>0) {
						PoHJXX_CZRKJBXXB czrkjbxxb = (PoHJXX_CZRKJBXXB) hjxx_czrkjbxxbList.get(0);
						vo.setXm1(czrkjbxxb.getXm());
						vo.setRynbid(czrkjbxxb.getRynbid()+"");
					}
					vo.setIstyr("1");
					
					listTemp.add(vo);
				}
			}
			p.setList(listTemp);
			p.setPageSize(listTemp.size());
			return p;
		}
		return null;
	
	}

	@Override
	public Page queryJssdls(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryJssdls", params);
	}

	@Override
	public String queryProjectTree(ExtMap<String, Object> params) {
		return null;
	}

	@Override
	public List<TreeNode> queryYsqJSTree(ExtMap<String, Object> params) {

		List<TreeNode> list = new ArrayList<TreeNode>();
		
		String dqbm = params.getString("dqbm");
		String jsid = params.getString("jsid");
		
		Session session = null;
		try{
			
			TreeNode gnd = new TreeNode();
			gnd.setText("功能点权限");
			gnd.setLeaf(false);
			gnd.setExpanded(true);
			gnd.setChecked(null);
			gnd.setChildren(new ArrayList<TreeNode>());
			gnd.setIcon("images/folder.gif");
			String hql = "select gnid from XT_JSGNQXB where jsid=?";
			List existsList = executeSqlQuery(hql,new Object[]{jsid});
			Set<String> set = new HashSet<String>();
			for(Object o:existsList){
				if(o==null) continue;
				
				set.add(o.toString());
			}
			
			hql = "from PoXT_XTGNB t  order by gnbh";
			List alllist = findAllByHQL(hql,null);
			TreeNode parent = null;
			for(Object obj: alllist){
				PoXT_XTGNB c = (PoXT_XTGNB)obj;
				
				TreeNode child = new TreeNode();
				child.setChecked(set.contains(c.getGnid()));
				child.setText(c.getGnmc());
				child.setLeaf(true);
				child.setGnb(c);
				
				gnd.getChildren().add(child);
			}
			
			TreeNode cd = new TreeNode();
			cd.setText("菜单权限");
			cd.setLeaf(false);
			cd.setExpanded(true);
			cd.setChecked(null);
			cd.setChildren(new ArrayList<TreeNode>());
			cd.setIcon("images/folder.gif");
			
			hql = "select gncdid from XT_JSCDQXB t where jsid=?";
			existsList = executeSqlQuery(hql,new Object[]{jsid});
			set = new HashSet<String>();
			for(Object o:existsList){
				set.add(o.toString());
			}
			
			hql = "from PoXT_XTGNCDB t order by gncdid";
			alllist = findAllByHQL(hql,null);
			parent = null;
			for(Object obj: alllist){
				PoXT_XTGNCDB c = (PoXT_XTGNCDB)obj;
	
				if("0".equals(c.getCdcc())){
					if(parent!=null){
						parent.setExpanded(true);
						if(parent.getGncdb().getCdbz().equals("1")){
							parent.setLeaf(true);
							parent.setChecked(false);
						}else{
							parent.setLeaf(false);
							parent.setChecked(null);
							parent.setIcon("images/folder.gif");
						}
						
						if(parent.isLeaf())
							parent.setChecked(set.contains(String.valueOf(parent.getGncdb().getGncdid())));
						
						cd.getChildren().add(parent);
					}
					
					//根菜单
					parent = new TreeNode();
					parent.setChecked(null);
					parent.setChildren(new ArrayList<TreeNode>());
					parent.setText(c.getCdmc());
					parent.setGncdb(c);
				}else{
					//叶子菜单
					TreeNode child = new TreeNode();
					child.setChecked(set.contains(String.valueOf(c.getGncdid())));
					child.setText(c.getCdmc());
					child.setLeaf(true);
					child.setGncdb(c);
					
					if(parent!=null)
						parent.getChildren().add(child);
					else
						cd.getChildren().add(child);
				}
			}
			
//			if(parent!=null){
//				parent.setExpanded(true);
//				if(parent.getGncdb().getCdbz().equals("1")){
//					parent.setLeaf(true);
//					parent.setChecked(false);
//				}else{
//					parent.setLeaf(false);
//					parent.setChecked(null);
//					parent.setIcon("images/folder.gif");
//				}
//				
//				if(parent.isLeaf())
//					parent.setChecked(set.contains(parent.getGncdb().getGncdid()));
//				
//				cd.getChildren().add(parent);
//			}
//			
//			TreeNode bb = new TreeNode();
//			bb.setText("报表权限");
//			bb.setLeaf(false);
//			bb.setExpanded(true);
//			bb.setChecked(null);
//			bb.setChildren(new ArrayList<TreeNode>());
//			bb.setIcon("images/folder.gif");
//			
//			//select * from xt_ywbbmbxxb t order by ywbblb
//			//select * from xt_jsywbbqxb t
//			
//			TreeNode zsbb = new TreeNode();
//			zsbb.setText("制式报表");
//			zsbb.setLeaf(true);
//			zsbb.setChecked(false);
//			
//			hql = "select ywbbid from XT_JSYWBBQXB  where jsid=?";
//			existsList = executeSqlQuery(hql,new Object[]{jsid});
//			set = new HashSet<String>();
//			for(Object o:existsList){
//				set.add(o.toString());
//			}
//			
//			hql = "from PoXT_YWBBMBXXB t  order by ywbblb";
//			alllist = findAllByHQL(hql,null);
//			parent = null;
//			for(Object obj: alllist){
//				PoXT_YWBBMBXXB c = (PoXT_YWBBMBXXB)obj;
//				
//				TreeNode child = new TreeNode();
//				child.setChecked(false);
//				child.setText(c.getBbmc());
//				child.setLeaf(true);
//				child.setYwmb(c);
//				
//				if(set.contains(c.getYwbbid()))
//					child.setChecked(true);
//				
//				bb.getChildren().add(child);
//			}
			
			list.add(cd);
//			list.add(gnd);
//			list.add(bb);
		}catch (DAOException ex) {
		      //事务回滚
		      throw ex;
	    }
	    catch (ServiceException ex) {
	      //事务回滚
	      throw ex;
	    }
	    catch (DataAccessException ex) {
	      //事务回滚
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
	    catch (Exception ex) {
	      //事务回滚
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
		
		return list;
	
	}

	@Override
	public void saveJsFunc(ExtMap<String, Object> params) throws
    ServiceException,
    DAOException{
		PojoInfo Xt_jscdqxbDao = DAOFactory.createXT_JSCDQXBDAO();
		PojoInfo Xt_jsgnqxbDao = DAOFactory.createXT_JSGNQXBDAO();
		PojoInfo Xt_jsywbbqxbDao = DAOFactory.createXT_JSYWBBQXBDAO();
		PoXT_YHXXB loginu = BaseContext.getBaseUser().getUser();
		if(loginu==null)
			throw new ServiceException("错误，你的登录已经超时，请重新登陆！");
		
		Long gncdid = params.getLong("gncdid");	//XtJscdqxb
		Long gnid = params.getLong("gnid");		//XtJsgnqxb
		Long ywbbid = params.getLong("ywbbid");	//XtJsywbbqxb
		Long jsid = params.getLong("jsid");
		
		Transaction tran = null;
		try{
			
			PoXT_JSXXB js = super.get(PoXT_JSXXB.class, jsid);
			if(js==null)
				throw new ServiceException("角色不存在！");
			
			String optype =  (String)params.getString("optype");	
			if(CommonUtil.isEmpty(String.valueOf(gncdid)) && CommonUtil.isEmpty(String.valueOf(gnid)) && CommonUtil.isEmpty(String.valueOf(ywbbid)))
				throw new ServiceException("未知功能！");
			
			String hsql  = null;
			Object[] params1 = null;
			
			long seg = 0;
			if(optype.equals("add")){
				if(CommonUtil.isNotEmpty(String.valueOf(gncdid))){
					seg = (Long) Xt_jscdqxbDao.getId();
					PoXT_JSCDQXB gx = new PoXT_JSCDQXB();
					gx.setDqbm(js.getDqbm());
					gx.setGncdid(gncdid);
					gx.setJscdid(seg);
					gx.setJsid(jsid);
					super.create(gx);
				}else if(CommonUtil.isNotEmpty(String.valueOf(gnid))){
					seg = (Long)Xt_jsgnqxbDao.getId();
					PoXT_JSGNQXB gx = new PoXT_JSGNQXB();
					gx.setDqbm(js.getDqbm());
					gx.setGnid(gnid);
					gx.setJsgnid(seg);
					gx.setJsid(jsid);
					super.create(gx);
				}else{
					seg = (Long)Xt_jsywbbqxbDao.getId();
					PoXT_JSYWBBQXB gx = new PoXT_JSYWBBQXB();
					gx.setDqbm(js.getDqbm());
					gx.setJsid(jsid);
					gx.setYwbbid(ywbbid);
					gx.setYwbbqxid(seg);
					super.create(gx);
				}
			}else{
				if(CommonUtil.isNotEmpty(String.valueOf(gncdid))){
					hsql = "from PoXT_JSCDQXB where jsid=? and gncdid=?";
					params1 = new Object[]{jsid,gncdid};
				}else if(CommonUtil.isNotEmpty(String.valueOf(gnid))){
					hsql = "from PoXT_JSGNQXB where jsid=? and gnid=?";
					params1 = new Object[]{jsid,gnid};
				}else{
					hsql = "from PoXT_JSYWBBQXB where jsid=? and ywbbid=?";
					params1 = new Object[]{jsid,ywbbid};
				}
				
				Object obj = this.getObject( hsql, params1);
				if(obj!=null){
					super.delete(obj);
				}
			}
			
		}catch (DAOException ex) {
		      //事务回滚
		      throw ex;
	    }
	    catch (ServiceException ex) {
	      //事务回滚
	      throw ex;
	    }
	    catch (DataAccessException ex) {
	      //事务回滚
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
	    catch (Exception ex) {
	      //事务回滚
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
	
		
	}

	@Override
	public void deleteJs(ExtMap<String, Object> params) {
		String ids = params.getString("ids");
		String[] id = ids.split(",");
		for(int i=0;i<id.length;i++){
			if(CommonUtil.isNotEmpty(id[i])){
				PoXT_JSXXB r = super.get(PoXT_JSXXB.class, Long.parseLong(id[i]));
				if(r!=null){
					String sql = "from PoXT_YHJSXXB where jsid=?";
					Object obj = this.getObject( sql, new Object[]{r.getJsid()});
					if(obj!=null){
						super.delete(obj);
					}
					
					sql = "from PoXT_JSCDQXB where jsid=?";
					obj = this.getObject( sql, new Object[]{r.getJsid()});
					if(obj!=null){
						super.delete(obj);
					}
					
					sql = "from PoXT_JSGNQXB where jsid=?";
					obj = this.getObject( sql, new Object[]{r.getJsid()});
					if(obj!=null){
						super.delete(obj);
					}
					
					sql = "from PoXT_JSYWBBQXB where jsid=?";
					obj = this.getObject( sql, new Object[]{r.getJsid()});
					if(obj!=null){
						super.delete(obj);
					}
					
					sql = "from PoXT_JSZSBBQXB where jsid=?";
					obj = this.getObject( sql, new Object[]{r.getJsid()});
					if(obj!=null){
						super.delete(obj);
					}
					super.delete(r);
				}
			}
		}
	
		
	}

	@Override
	public PoXT_JSXXB saveJs(ExtMap<String, Object> params) {
		PojoInfo Xt_jsxxbDao = DAOFactory.createXT_JSXXBDAO();
		String jsid = params.getString("jsid");
		String jsmc = params.getString("jsmc");
		String ms = params.getString("ms");
		String dqbm = params.getString("dqbm");
		
		PoXT_JSXXB js = null;
	
		if(CommonUtil.isNotEmpty(jsid)){
			js = super.get(PoXT_JSXXB.class, Long.parseLong(jsid));
			if(js==null)
				throw new ServiceException("此角色不存在！");
			
			js.setJsmc(jsmc);
			js.setMs(ms);
			super.update(js);
		}else{
			js = new PoXT_JSXXB();
			js.setDqbm(dqbm.substring(0,4));
			js.setJsmc(jsmc);
			js.setMs(ms);
			
			long seq = (Long)Xt_jsxxbDao.getId();
			js.setJsid(seq);
			super.create(js);
		}

		return js;
	}

	@Override
	public Page queryFxjsktj(ExtMap<String, Object> params) {
		PoXT_YHXXB dqyh = this.getUser();
		String yhdwdm = dqyh.getDwdm();
		StringBuffer sqlQ = new StringBuffer();//要显示的部分值拼接
		StringBuffer sqlQT = new StringBuffer();//要显示的部分值拼接--统计
		StringBuffer sql = new StringBuffer();//中间查询语句主体
		StringBuffer sqlT = new StringBuffer();//查询条件拼接
		StringBuffer sqlG = new StringBuffer();//查询条件分组拼接
		String dwdm = params.getString("dwdm");//查询单位代码
		String sffs = params.getString("sffs");
		Boolean pcsFlag =false;
		//直接查询，根据当前登录人dwdm查询其下属所有数据
		String xzFlag = params.getString("xzFlag");//用于点击下属单位，下钻，
		if(CommonUtil.isNotEmpty(dwdm)) {
			if(dwdm.endsWith("00000")) {//五个0为市局用户
				pcsFlag =false;
				sqlQ.append(" select t.qhdm||'000',sum(t.slzl),sum(t.xjje),sum(t.xjbs),sum(t.fxjje),sum(t.fxjbs),sum(t.yx),sum(t.zf),sum(t.yjkje),sum(t.sjkje),sffs,sum(t.blje),sum(t.zfje) ");
				sqlT.append(" and qhdm like '").append(dwdm.subSequence(0, 4)).append("%' and qhdm<>'").append(dwdm.subSequence(0, 6)).append("' ");
				sqlG.append(" group by t.qhdm,t.sffs ");
			}else if(dwdm.endsWith("000")) {//三个0为分局用户
				pcsFlag =false;
				sqlQ.append(" select t.dwdm,sum(t.slzl),sum(t.xjje),sum(t.xjbs),sum(t.fxjje),sum(t.fxjbs),sum(t.yx),sum(t.zf),sum(t.yjkje),sum(t.sjkje),sffs,sum(t.blje),sum(t.zfje) ");
				sqlT.append(" and qhdm = '").append(dwdm.subSequence(0, 6)).append("'");
				sqlG.append(" group by t.dwdm,t.sffs ");
			}else {//派出所用户
				pcsFlag =true;
				sqlQ.append(" select t.dwdm,sum(t.slzl),sum(t.xjje),sum(t.xjbs),sum(t.fxjje),sum(t.fxjbs),sum(t.yx),sum(t.zf),sum(t.yjkje),sum(t.sjkje),sffs,sum(t.blje),sum(t.zfje) ");
				sqlT.append(" and dwdm = '").append(dwdm).append("'");
				sqlG.append(" group by t.dwdm,t.sffs ");
			}
		}else {
			if(yhdwdm.endsWith("00000")) {//五个0为市局用户
				pcsFlag =false;
				sqlQ.append(" select t.qhdm||'000',sum(t.slzl),sum(t.xjje),sum(t.xjbs),sum(t.fxjje),sum(t.fxjbs),sum(t.yx),sum(t.zf),sum(t.yjkje),sum(t.sjkje),sffs,sum(t.blje),sum(t.zfje) ");
				sqlT.append(" and qhdm like '").append(yhdwdm.subSequence(0, 4)).append("%' and qhdm<>'").append(yhdwdm.subSequence(0, 6)).append("' ");
				sqlG.append(" group by t.qhdm,t.sffs ");
			}else if(yhdwdm.endsWith("000")) {//三个0为分局用户
				pcsFlag =false;
				sqlQ.append(" select t.dwdm,sum(t.slzl),sum(t.xjje),sum(t.xjbs),sum(t.fxjje),sum(t.fxjbs),sum(t.yx),sum(t.zf),sum(t.yjkje),sum(t.sjkje),sffs,sum(t.blje),sum(t.zfje) ");
				sqlT.append(" and qhdm = '").append(yhdwdm.subSequence(0, 6)).append("'");
				sqlG.append(" group by t.dwdm,t.sffs ");
			}else {//派出所用户
				pcsFlag =true;
				sqlQ.append(" select t.dwdm,sum(t.slzl),sum(t.xjje),sum(t.xjbs),sum(t.fxjje),sum(t.fxjbs),sum(t.yx),sum(t.zf),sum(t.yjkje),sum(t.sjkje),sffs,sum(t.blje),sum(t.zfje) ");
				sqlT.append(" and dwdm = '").append(yhdwdm).append("'");
				sqlG.append(" group by t.dwdm,t.sffs ");
			}
		}
		
		
		String sffsT = CommonUtil.isEmpty(sffs)?"3":sffs;
		
		//sqlQ.append(sql);
		
		sqlQT.append(" select '合计',sum(t.slzl),sum(t.xjje),sum(t.xjbs),sum(t.fxjje),sum(t.fxjbs),sum(t.yx),sum(t.zf),sum(t.yjkje),sum(t.sjkje),'").append(sffsT).append("',sum(t.blje),sum(t.zfje) ");
		sql.append(" from (select dwdm,sum(1) as slzl,(case when sffs = '1' then sum(je) else 0 end) as xjje,(case when ")
		.append(" sffs = '1' then sum(1) else 0 end) as xjbs,(case when sffs = '0' then sum(je) else 0 end) as fxjje ")
		.append(" ,(case when sffs = '0' then sum(1) else 0 end) as fxjbs,(case when ((bzxjfyy = '4' and sffs = '1') or sffs = '0'  or sffs = '2') then sum(1) else 0 end) as yx ")
		.append(" ,(case when (bzxjfyy <> '4' and sffs = '1' ) then sum(1) else 0 end) as zf,(case when (bzxjfyy = '4' and sffs = '1' ) then sum(je) else 0 end) as yjkje")
		.append(" ,(case when (jfflag = '1' and bzxjfyy = '4' and sffs = '1' ) then sum(je) else 0 end) as sjkje,sffs,qhdm  ")
		//20210108新增办理金额  作废金额
		.append(" ,(case when ((bzxjfyy = '4' and sffs = '1') or sffs = '0') then sum(je) else 0 end) as blje ,(case when (bzxjfyy <> '4' and sffs = '1' ) then sum(je) else 0 end) as zfje ")
		.append(" from SFXXB where dylb = '03' ");
		//应缴款  非在线支付，且 不在线缴费原因为4现金支付
		//实缴款  非在线支付，且 不在线缴费原因为4现金支付     同时缴费标志jfflag为1已缴费
		if(CommonUtil.isNotBlank(sqlT.toString())) {
			sql.append(sqlT);
		}
		if(CommonUtil.isNotEmpty(sffs)) {
			if(sffs.equals("1")) {
				sql.append(" and  bzxjfyy is not null ");
			}
			sql.append(" and sffs='"+sffs+"' ");
		}
//		if(CommonUtil.isNotEmpty(dwdm)) {
//			if(dwdm.endsWith("00000")) {//五个0为市局
//				sql.append(" and qhdm like '").append(dwdm.subSequence(0, 4)).append("%' and qhdm<>'").append(yhdwdm.subSequence(0, 6)).append("' ");
//
//				//sql.append(" and dwdm like '"+dwdm.substring(0,4)+"%' ");
//			}else if(dwdm.endsWith("000")) {//三个0为分局用户
//				sql.append(" and qhdm = '").append(dwdm.subSequence(0, 6)).append("'");
//
////				sql.append(" and dwdm like '"+dwdm.substring(0,6)+"%' ");
//			}else {//派出所
//				sql.append(" and dwdm = '"+params.getString("dwdm")+"' ");
//			}
//			
//		}
		if(CommonUtil.isNotBlank(params.getString("_start_dysj"))) {
			sql.append(" and dysj >= '"+params.getString("_start_dysj")+"000000' ");
		}
		if(CommonUtil.isNotBlank(params.getString("_end_dysj"))) {
			sql.append(" and dysj <= '"+params.getString("_end_dysj")+"235959' ");
		}
		if(CommonUtil.isNotBlank(params.getString("czrid"))) {
			sql.append(" and czyid = '"+params.getString("czrid")+"' ");
		}
		sql.append(" group by dwdm, sffs, bzxjfyy,jfflag,qhdm) t  ");
		sqlQ.append(sql).append(sqlG);
		//union all 统计查询语句
		if(!pcsFlag) {
			sqlQ.append(" union all ").append(sqlQT).append(sql);
		}
		List<?> fxjsk  = super.executeSqlQuery(sqlQ.toString(), new Object[]{});
		List<VoFxjsfxxb> list = new ArrayList<>();
		for(Object obj:fxjsk){
			Object[] objs = (Object[])obj;
			VoFxjsfxxb voFxjsfxxb = new VoFxjsfxxb();
			voFxjsfxxb.setDwdm(objs[0]==null?" ":objs[0].toString());
			voFxjsfxxb.setSlzl(Integer.parseInt(objs[1]==null?"0":objs[1].toString()));
			voFxjsfxxb.setXjje(Integer.parseInt(objs[2]==null?"0":objs[2].toString()));
			voFxjsfxxb.setXjbs(Integer.parseInt(objs[3]==null?"0":objs[3].toString()));
			voFxjsfxxb.setFxjje(Integer.parseInt(objs[4]==null?"0":objs[4].toString()));
			voFxjsfxxb.setFxjbs(Integer.parseInt(objs[5]==null?"0":objs[5].toString()));
			voFxjsfxxb.setHkbysy(Integer.parseInt(objs[6]==null?"0":objs[6].toString()));
			voFxjsfxxb.setHkbzf(Integer.parseInt(objs[7]==null?"0":objs[7].toString()));
			voFxjsfxxb.setYjkje(Integer.parseInt(objs[8]==null?"0":objs[8].toString()));
			voFxjsfxxb.setSjkje(Integer.parseInt(objs[9]==null?"0":objs[9].toString()));
			voFxjsfxxb.setSffs(objs[10]==null?" ":objs[10].toString());
			voFxjsfxxb.setBlje(objs[11]==null?" ":objs[11].toString());
			voFxjsfxxb.setZfje(objs[12]==null?" ":objs[12].toString());
			list.add(voFxjsfxxb);
		}
		Page page = new Page();
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		int fromIndex = (pageIndex-1)*pageSize;
		int endIndex = pageIndex*pageSize;
		page.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
		page.setTotalCount(list.size());
		return page;
	}

	@Override
	public Page queryFxjsktjInfo(ExtMap<String, Object> params) {
		String dwdm = params.getString("dwdm");
		PoXT_DWXXB xt_dwxxb = super.get(PoXT_DWXXB.class,dwdm);
		if(xt_dwxxb!=null) {
			params.put("dwjb",xt_dwxxb.getDwjb());
			if(dwdm.endsWith("00000")) {
				params.put("dwjb",'2');
			}
		}else{
			throw new RuntimeException("查询单位已失效！");
		}
		return super.getPageRecords("/conf/segment/common", "queryFxjsktjInfo", params);
	}

	@Override
	public List queryQcclxxDaochu(ExtMap<String, Object> params) {

		
		List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().getYhid().toString(), PublicConstant.GNBH_HJ_QCCLXXHQ);
		    if (sjfwList != null) {
		      StringBuffer sjfwHQL = new StringBuffer();
		      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
		        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
		        //居(村)委会
		        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
		          sjfwHQL.append(sjfwHQL.length() > 0 ?
		                         "or a.jcwh_q='" + voXtsjfw.getSjfw().trim() +
		                         "' " :
		                         "a.jcwh_q='" + voXtsjfw.getSjfw().trim() +
		                         "' ");
		        }
		        //派出所
		        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
		          sjfwHQL.append(sjfwHQL.length() > 0 ?
		                         "or a.pcs_q='" + voXtsjfw.getSjfw().trim() +
		                         "' " :
		                         "a.pcs_q='" + voXtsjfw.getSjfw().trim() +
		                         "' ");
		        }
		        //行政区划
		        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
		          sjfwHQL.append(sjfwHQL.length() > 0 ?
		                         "or a.ssxq_q='" + voXtsjfw.getSjfw().trim() +
		                         "' " :
		                         "a.ssxq_q='" +
		                         voXtsjfw.getSjfw().trim() +
		                         "' ");
		        }
		      } //for(int i = 0; i < sjfwList.size(); i++)
		      if (sjfwHQL.length() > 0) {
		    	  params.put("sjfw", "and ( " + sjfwHQL.toString() + ") ");
//		        strFromHQL.append("and ( ")
//		            .append(sjfwHQL.toString())
//		            .append(") ");
		      }
		    }
		
		if(params.containsKey("_start_slsj")) {
			params.put("_start_slsj", params.getString("_start_slsj") + "000000");
		}
		    
		if(params.containsKey("_end_slsj")) {
			params.put("_end_slsj", params.getString("_end_slsj") + "235959");
		}
		return super.getObjectListByHql("/conf/segment/common", params.getString("config_key"), params);
	
	}

	@Override
	public List queryXxxxDaochu(ExtMap<String, Object> params) {

		if(params.getString("_start_slsj") != null&&params.getString("_start_slsj").length()==8) {
			params.put("_start_slsj", params.getString("_start_slsj")+"000000");
		}
		if(params.getString("_end_slsj") != null&&params.getString("_end_slsj").length()==8) {
			params.put("_end_slsj", params.getString("_end_slsj")+"000000");
		}
		if(params.getString("_start_zxsj") != null&&params.getString("_start_zxsj").length()==8) {
			params.put("_start_zxsj", params.getString("_start_zxsj")+"000000");
		}
		if(params.getString("_end_zxsj") != null&&params.getString("_end_zxsj").length()==8) {
			params.put("_end_zxsj", params.getString("_end_zxsj")+"000000");
		}
		return super.getObjectListByHql("/conf/segment/common", params.getString("config_key"), params);
	}

	@Override
	public void saveGgUrlTrans(ExtMap<String, Object> params) {
		long l1 = System.currentTimeMillis();
		String strCzkssj = StringUtils.formateDateTime();
		String str = "";
		String log_code = params.getString("log_code");
		String gmsfzh = params.getString("gmsfzh");
		String bh = params.getString("bh");
		if(CommonUtil.isNotEmpty(gmsfzh)) {
			str =str+" gmsfzh:"+gmsfzh;
		};
		if(CommonUtil.isNotEmpty(bh)) {
			str =str+" bh:"+bh;
		}
		commonService.saveRzxx(HSession.getBaseUser(), log_code, str, l1, strCzkssj);
	}
	@Override
	public Menu queryRzsTree(ExtMap<String, Object> params) {
		Long rynbid = params.getLong("rynbid");
		String ryid = params.getString("ryid");
		MenuData  data = new MenuData();
		Menu root = new Menu();
		root.setExpanded(false);
		root.setLeaf(false);
		root.setText("人信息");
		data.setUrl("rzs(0,"+rynbid+","+ryid+")");//type
		root.setData(data);
		if(rynbid==null||CommonUtil.isEmpty(ryid)) {
			return root;
		}
		PoV_HJXX_CZRKJBXXB  pohjxx_czrkjbxxb = super.get(PoV_HJXX_CZRKJBXXB.class,rynbid);
		String hql = " from PoV_HJXX_CZRKJBXXB where ryid = '"+ryid+"'";
	    @SuppressWarnings("unchecked")
		List<PoV_HJXX_CZRKJBXXB> rsjlist = (List<PoV_HJXX_CZRKJBXXB>)super.getObjectListByHql(hql);
		
		List<Menu> munuList = new ArrayList<Menu>();
		Menu parent1 = new Menu();
		parent1.setExpanded(false);
		parent1.setLeaf(false);
		parent1.setText("常住信息：测试(最新："+pohjxx_czrkjbxxb.getQysj()+")");
		MenuData  data1 = new MenuData();
		data1.setUrl("rzs(1,"+rynbid+","+ryid+")");
		parent1.setData(data1);
		
		Menu parent2 = new Menu();
		parent2.setExpanded(false);
		parent2.setLeaf(false);
		parent2.setText("证件信息");
		MenuData  data2 = new MenuData();
		data2.setUrl("rzs(2,"+rynbid+","+ryid+")");
		parent2.setData(data2);
		
		Menu parent3 = new Menu();
		parent3.setExpanded(false);
		parent3.setLeaf(false);
		parent3.setText("社会关系");
		MenuData  data3 = new MenuData();
		data3.setUrl("rzs(3,"+rynbid+","+ryid+")");
		parent3.setData(data3);
		
		Menu parent4 = new Menu();
		parent4.setExpanded(false);
		parent4.setLeaf(false);
		parent4.setText("其他住址");
		MenuData  data4 = new MenuData();
		data4.setUrl("rzs(4,"+rynbid+","+ryid+")");
		parent4.setData(data4);
		
		Menu parent5 = new Menu();
		parent5.setExpanded(false);
		parent5.setLeaf(false);
		parent5.setText("人事件");
		MenuData  data5 = new MenuData();
		data5.setUrl("rzs(5,"+rynbid+","+ryid+")");
		parent5.setData(data5);
		
		List<Menu> rsjchildList = new ArrayList<Menu>();
		for(int i=0;i<rsjlist.size();i++) {
			PoV_HJXX_CZRKJBXXB rsj = rsjlist.get(i);
			Menu rsjchild = new Menu();
			rsjchild.setExpanded(false);
			rsjchild.setLeaf(false);
			rsjchild.setText(rsj.getQysj());
			MenuData  data6 = new MenuData();
			data6.setUrl("rzs(6,"+rsj.getRynbid()+","+rsj.getRyid()+")");
			rsjchild.setData(data6);
			List<Menu> rsjchildsecondList = new ArrayList<Menu>();
			Menu rsjsecondchild = new Menu();
			rsjsecondchild.setExpanded(false);
			rsjsecondchild.setLeaf(false);
			rsjsecondchild.setText("发生后人信息："+rsj.getXm()+"(最新："+rsj.getQysj()+" "+getXTCSB("1053",rsj.getYwnr())+" )");
			Menu rsjsecondchild2 = new Menu();
			rsjsecondchild2.setExpanded(false);
			rsjsecondchild2.setLeaf(false);
			rsjsecondchild2.setText("相关人员");
			rsjchildsecondList.add(rsjsecondchild);
			rsjchildsecondList.add(rsjsecondchild2);
			rsjchild.setChildren(rsjchildsecondList);
			rsjchildList.add(rsjchild);
		}
		parent5.setChildren(rsjchildList);
		Menu parent6 = new Menu();
		parent6.setExpanded(false);
		parent6.setLeaf(false);
		parent6.setText("户信息");
		MenuData  data7 = new MenuData();
		data7.setUrl("rzs(6,"+rynbid+","+ryid+","+pohjxx_czrkjbxxb.getHhnbid()+")");
		parent6.setData(data7);
		munuList.add(parent1);
		munuList.add(parent2);
		munuList.add(parent3);
		munuList.add(parent4);
		munuList.add(parent5);
		munuList.add(parent6);
		
		root.setChildren(munuList);
	    return root;
	}
protected PoXT_XTCSB getXTCSB(String cslb, String dm) throws ServiceException,
     DAOException {

   PoXT_XTCSB poXtcsb = null;
   String strHQL = null;

   try {
     PojoInfo xt_xtcsbDAO = DAOFactory.createXT_XTCSBDAO();

     strHQL = "from PoXT_XTCSB where cslb='" + cslb +
         "' and trim(dm)='" + dm + "' ";
     List xtcsbList = super.getObjectListByHql(strHQL);
     if (xtcsbList != null && xtcsbList.size() > 0) {
       poXtcsb = (PoXT_XTCSB) xtcsbList.get(0);
     }
   }
   catch (DAOException ex) {
     throw ex;
   }
   catch (Exception ex) {
     throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
   }

   return poXtcsb;
 }
	@Override
	public Page queryJttdHxx(ExtMap<String, Object> params) {
		return getJttdList(params,"1");
	}

	public Page getJttdList(ExtMap<String, Object> params,String type) {
		Page result = new Page();
		StringBuffer sql = new StringBuffer();
//		sql.append(" select a.hh,a.hhid,a.hhnbid, a.hlx,b.pcs,b.jlx,b.jcwh, a.jttdbz,b.mlph,b.mlxz from ")
//				.append(" HJXX_HXXB  a , HJXX_MLPXXXXB b where 1 = 1 ")
//				.append("and a.mlpnbid = b.mlpnbid and a.jlbz = '1' and a.cxbz = '0' ");
		sql.append(" select a.hh,a.hhid,a.hhnbid, a.hlx,b.ssxq,b.pcs,b.xzjd,b.jlx,b.jcwh, a.jttdbz,b.mlph,b.mlxz,a.slrid,a.slsj,a.sldw from ")
		.append(" (select t1.hh,t1.hhid,t1.hhnbid,t1.hlx,t1.jttdbz,t2.slrid,t2.slsj,t2.sldw,t1.mlpnbid from ")
		.append(" HJXX_HXXB t1 left join JTTDLSB t2 on t1.jttdlsbid = t2.jttdlsbid where t1.jlbz = '1' ");

		if(params.getString("hh") != null) {
			sql.append(" and t1.hh = '"+params.getString("hh")+"'");
		}
		if(params.getString("hlx") != null) {
			sql.append(" and t1.hlx = '"+params.getString("hlx")+"'");
		}
		if(params.getString("jttdbz") != null) {
			sql.append(" and t1.jttdbz = '"+params.getString("jttdbz")+"'");
		}
		if(params.getString("slrid") != null) {
			sql.append(" and t2.slrid = '"+params.getString("slrid")+"'");
		}
		if(params.getString("sldw") != null) {
			sql.append(" and t2.sldw = '"+params.getString("sldw")+"'");
		}
		if(params.getString("_start_slsj") != null) {
			sql.append(" and t2.slsj >= '"+params.getString("_start_slsj")+"'");
		}
		if(params.getString("_end_slsj") != null) {
			sql.append(" and t2.slsj <= '"+params.getString("_end_slsj")+"'");
		}
		
		sql.append(" and t1.cxbz = '0' and t1.hhzt='0' ) a, HJXX_MLPXXXXB b where 1 = 1 and a.mlpnbid = b.mlpnbid ");
		if(params.getString("jlx") != null) {
			sql.append(" and b.jlx = '"+params.getString("jlx")+"'");
		}
		if(params.getString("jcwh") != null) {
			sql.append(" and b.jcwh = '"+params.getString("jcwh")+"'");
		}
		if(params.getString("ssxq") != null) {
			sql.append(" and b.ssxq = '"+params.getString("ssxq")+"'");
		}
		if(params.getString("pcs") != null) {
			sql.append(" and b.pcs = '"+params.getString("pcs")+"'");
		}
		if(params.getString("mlph") != null) {
			sql.append(" and (b.mlph like '%"+CommonUtil.ToDBC(params.getString("mlph"))+"%' or b.mlph like '%"+CommonUtil.ToSBC(params.getString("mlph"))+"%') ");
		}
		if(params.getString("mlxz") != null) {
			sql.append(" and (b.mlxz like '%"+CommonUtil.ToDBC(params.getString("mlxz"))+"%' or b.mlxz like '%"+CommonUtil.ToSBC(params.getString("mlxz"))+"%') ");
		}
		List list1 =  this.executeSqlQueryMapList(sql.toString(), null);
		if(type=="1") {
			int pageIndex = params.getInteger("pageIndex");
			int pageSize = params.getInteger("pageSize");
			int fromIndex = (pageIndex-1) * pageSize;
			int endIndex = pageIndex*pageSize;
//			List list =  this.executeSqlQueryMapList(sql.toString(), null,pageIndex,pageSize);
			result.setList(list1.subList(fromIndex,list1.size()-endIndex> 0? endIndex:list1.size()));
			result.setTotalCount(list1.size());
			return result;
		}else if(type=="2") {
			result.setList(list1);
			result.setTotalCount(list1.size());
			return result;
		}else if(type=="3") {
			List<VoHbsxx> list = new ArrayList<>();
			for(Object obj:list1){
				HashMap<String, String> objs = (HashMap<String, String>) obj;
				VoHbsxx vo=new VoHbsxx();
				vo.setHh(objs.get("hh"));
				vo.setHhid(Long.valueOf(objs.get("hhid")));
				vo.setHhnbid(Long.valueOf(objs.get("hhnbid")));
				vo.setHlx(objs.get("hlx")==null?"":objs.get("hlx"));
				vo.setPcs(objs.get("pcs")==null?"":objs.get("pcs"));
				vo.setXzjd(objs.get("xzjd")==null?"":objs.get("xzjd"));
				vo.setJlx(objs.get("jlx")== null?"":objs.get("jlx"));
				vo.setJcwh(objs.get("jcwh")==null?"":objs.get("jcwh")); 
				vo.setJttdbz(objs.get("jttdbz")==null?"":objs.get("jttdbz"));
				vo.setMlph(objs.get("mlph")==null?"":objs.get("mlph"));
				vo.setMlxz(objs.get("mlxz")==null?"":objs.get("mlxz"));
				vo.setSlrid(Long.valueOf(objs.get("slrid")==null?"0":objs.get("slrid")));
				vo.setSlsj(objs.get("slsj")==null?"":objs.get("slsj"));
				vo.setSldw(objs.get("sldw")==null?"":objs.get("sldw"));
				list.add(vo);
			}
			result.setList(list);
			result.setTotalCount(list.size());
			return result;
		}
		return null;
	}
	@Override
	public void updateJttdbz2(ExtMap<String, Object> params) {
		AuthToken u = HSession.getBaseUser();
		String jttdbz = params.getString("jttdbzType");
		Page result = getJttdList(params,"2");
		for(int i=0;i<result.getList().size();i++) {
			Map map = (Map) result.getList().get(i);
			Long jttdlsbid = (Long) JTTDLSBDAO.getId();
			Long hhnbid = Long.valueOf(String.valueOf(map.get("hhnbid"))); 
			PoJTTDLSB jttdlsb = new PoJTTDLSB();
			PoHJXX_HXXB hjxx_hxxb = this.get(PoHJXX_HXXB.class, hhnbid);
			if(CommonUtil.isNotEmpty(hjxx_hxxb.getJttdbz())&&hjxx_hxxb.getJttdbz().equals(jttdbz)) {
				 continue;
			}
			jttdlsb.setBgh(jttdbz);
			jttdlsb.setBgq(hjxx_hxxb.getJttdbz());
			jttdlsb.setHh((String)map.get("hh"));
			jttdlsb.setHhid(Long.valueOf(String.valueOf(map.get("hhid"))));
			jttdlsb.setHhnbid(hhnbid);
			jttdlsb.setJttdlsbid(jttdlsbid);
			jttdlsb.setSldw(u.getOrganize().getDm());
			jttdlsb.setSlrid(u.getUser().getYhid());
			jttdlsb.setSlsj(StringUtils.getServiceTime());
			super.create(jttdlsb);
			hjxx_hxxb.setJttdbz(jttdbz);
			hjxx_hxxb.setJttdlsbid(jttdlsbid);
			super.update(hjxx_hxxb);
		}
	}
	@Override
	public void updateJttdbz1(ExtMap<String, Object> params) {
		AuthToken u = HSession.getBaseUser();
		Long hhnbid = params.getLong("hhnbid");
		String jttdbz = params.getString("jttdbz");
		PoJTTDLSB jttdlsb = new PoJTTDLSB();
		PoHJXX_HXXB hjxx_hxxb = this.get(PoHJXX_HXXB.class, hhnbid);
		Long jttdlsbid = (Long) JTTDLSBDAO.getId();
		try {
			BeanUtils.copyProperties(jttdlsb,hjxx_hxxb);
			jttdlsb.setJttdlsbid(jttdlsbid);
			jttdlsb.setBgh(jttdbz);
			jttdlsb.setBgq(hjxx_hxxb.getJttdbz());
			jttdlsb.setSldw(u.getOrganize().getDm());
			jttdlsb.setSlrid(u.getUser().getYhid());
			jttdlsb.setSlsj(StringUtils.getServiceTime());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		hjxx_hxxb.setJttdbz(jttdbz);
		hjxx_hxxb.setJttdlsbid(jttdlsbid);
		super.update(hjxx_hxxb);
		super.create(jttdlsb);
	}

	@Override
	public Page queryQydjRy(ExtMap<String, Object> params) {
		String gmsfhm = params.getString("gmsfhm");
		if(gmsfhm!=null) {
			Page p = new Page();
			List list = super.getObjectListByHql(" from PoHJXX_CZRKJBXXB where ryzt = '0' and cxbz = '0' and jlbz = '1' and gmsfhm= '"+gmsfhm+"'");
			if(list.size()==0) {
				p.setList(list);
				return p;
			}else {
				PoHJXX_CZRKJBXXB czrkjbxxb = (PoHJXX_CZRKJBXXB) list.get(0);
				params.put("hhnbid", czrkjbxxb.getHhnbid());
			}
		}
		if(params.getString("mlph") != null) {
			params.put("mlphToDBC", CommonUtil.ToDBC(params.getString("mlph")));
			params.put("mlphToSBC", CommonUtil.ToSBC(params.getString("mlph")));
		}
		if(params.getString("mlxz") != null) {
			params.put("mlxzToDBC", CommonUtil.ToDBC(params.getString("mlxz")));
			params.put("mlxzToSBC", CommonUtil.ToSBC(params.getString("mlxz")));
		}
		return super.getPageRecords("/conf/segment/common", "queryQydjRy", params);
	}

	@Override
	public void updateDjzt1(ExtMap<String, Object> params) {
		AuthToken u = HSession.getBaseUser();
		Long rynbid = params.getLong("rynbid");
		String djzt = params.getString("djzt");
		PoHJXX_CZRKJBXXB hjxx_czrkjbxxb = this.get(PoHJXX_CZRKJBXXB.class, rynbid);
		hjxx_czrkjbxxb.setDjzt(djzt);
		super.update(hjxx_czrkjbxxb);
	}

	@Override
	public void updateDjzt2(ExtMap<String, Object> params) {
		String djzt = params.getString("djztType");
		if(params.getString("mlph") != null) {
			params.put("mlphToDBC", CommonUtil.ToDBC(params.getString("mlph")));
			params.put("mlphToSBC", CommonUtil.ToSBC(params.getString("mlph")));
		}
		if(params.getString("mlxz") != null) {
			params.put("mlxzToDBC", CommonUtil.ToDBC(params.getString("mlxz")));
			params.put("mlxzToSBC", CommonUtil.ToSBC(params.getString("mlxz")));
		}
		List<PoHJXX_CZRKJBXXB> list = (List<PoHJXX_CZRKJBXXB>) super.getObjectListByHql("/conf/segment/common", "queryQydjRy", params);
		for(int i=0;i<list.size();i++) {
			PoHJXX_CZRKJBXXB hjxx_czrkjbxxb = list.get(i);
			hjxx_czrkjbxxb.setDjzt(djzt);
			super.update(hjxx_czrkjbxxb);
		}
	}

	@Override
	public Page queryjwhhzhqm(ExtMap<String, Object> params) {
		Page p = super.getPageRecords("/conf/segment/common", "queryjwhhzhqm", params);
		return p;
	}

	@Override
	public Page queryZmXx(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", params.getString("config_key"), params);
	
	}

	@Override
	public Page queryJttdHxxDaochu(ExtMap<String, Object> params) {
		Page p = new Page();
		List<VoHbsxx> list = new ArrayList<>();
		List l = getJttdList(params,"3").getList();
		p.setList(l);
		p.setTotalCount(l.size());
		return p;
	}

	@Override
	public Page getHjzmls(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryHjzmlsList", params);
	}

	@Override
	public Page querySjkfjgl(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "querySjkfjList", params);
	}
	
	@Override
	public Page querySjkxx(ExtMap<String, Object> params) {
		
//		StringBuffer sql = new StringBuffer();//查询条件分组拼接
//		String dwdm = params.getString("dwdm");//查询单位代码
//		String sffs = params.getString("sffs");
//		
//		PoXT_YHXXB dqyh = this.getUser();
//		String yhdwdm = dqyh.getDwdm();
//		
//		sql.append(" select dwdm,sum(je),jkrid,jksj ")
//		.append(" from SFXXB where dylb = '03'");
//		if(CommonUtil.isNotEmpty(params.getString("dwdm"))){
//			sql.append(" and dwdm = '").append(params.getString("dwdm")+"'");
//		}else {
//			if(yhdwdm.endsWith("00000")) {//五个0为市局用户
//				sql.append(" and qhdm like '").append(yhdwdm.subSequence(0, 4)).append("%' and qhdm<>'").append(yhdwdm.subSequence(0, 6)).append("' ");
//			}else if(yhdwdm.endsWith("000")) {//三个0为分局用户
//				sql.append(" and qhdm = '").append(yhdwdm.subSequence(0, 6)).append("'");
//			}else {//派出所用户
//				sql.append(" and dwdm = '").append(yhdwdm).append("'");
//			}
//		}
//		sql.append(" and jfflag =1 and sffs = '1' and bzxjfyy = '4' ");
//		//jksj_start jksj_end
//		if(CommonUtil.isNotEmpty(params.getString("jkyid"))){
//			sql.append(" and jkrid = '").append(params.getString("jkyid")+"' ");
//		}
//		if(CommonUtil.isNotEmpty(params.getString("jksj_start"))){
//			sql.append(" and jksj>='"+params.getString("jksj_start")+"' ");
//		}
//		if(CommonUtil.isNotEmpty(params.getString("jksj_end"))){
//			sql.append(" and jksj<='"+params.getString("jksj_end")+"' ");
//		}
//		sql.append(" group by dwdm,jkrid,jksj ");
//		List<?> fxjsk  = super.executeSqlQuery(sql.toString(), new Object[]{});
//		List<VoSjkxx> list = new ArrayList<>();
//		for(Object obj:fxjsk){
//			Object[] objs = (Object[])obj;
//			VoSjkxx vosjkxx = new VoSjkxx();
//			vosjkxx.setDwdm(objs[0]==null?" ":objs[0].toString());
//			vosjkxx.setJkje(objs[1]==null?" ":objs[1].toString());
//			vosjkxx.setJkyid(objs[2]==null?" ":objs[2].toString());
//			vosjkxx.setJkrq(objs[3]==null?" ":objs[3].toString());
//			list.add(vosjkxx);
//		}
//		Page page = new Page();
//		int pageIndex = params.getInteger("pageIndex");
//		int pageSize = params.getInteger("pageSize");
//		int fromIndex = (pageIndex-1)*pageSize;
//		int endIndex = pageIndex*pageSize;
//		page.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
//		page.setTotalCount(list.size());
//		return page;

		PoXT_YHXXB dqyh = this.getUser();
		String yhdwdm = dqyh.getDwdm();
		StringBuffer sqlQ = new StringBuffer();//要显示的部分值拼接
		StringBuffer sqlQT = new StringBuffer();//要显示的部分值拼接--统计
		StringBuffer sql = new StringBuffer();//中间查询语句主体
		StringBuffer sqlT = new StringBuffer();//查询条件拼接
		StringBuffer sqlG = new StringBuffer();//查询条件分组拼接
		String dwdm = params.getString("dwdm");//查询单位代码
		String sffs = params.getString("sffs");
		Boolean pcsFlag =false;
		//直接查询，根据当前登录人dwdm查询其下属所有数据
		if(CommonUtil.isNotEmpty(dwdm)) {
			if(dwdm.endsWith("00000")) {//五个0为市局用户
				pcsFlag =false;
				sqlQ.append(" select distinct t.qhdm||'000',sum(t.yingjkje) over(partition by t.qhdm ) ,sum(t.yijkje) over(partition by t.qhdm )");
				sqlT.append(" and qhdm like '").append(dwdm.subSequence(0, 4)).append("%' and qhdm<>'").append(dwdm.subSequence(0, 6)).append("' ");
				sqlG.append(" group by t.qhdm,t.yingjkje,t.yijkje ");
			}else if(dwdm.endsWith("000")) {//三个0为分局用户
				pcsFlag =false;
				sqlQ.append(" select distinct t.dwdm,sum(t.yingjkje) over(partition by t.dwdm ) ,sum(t.yijkje) over(partition by t.dwdm )");
				sqlT.append(" and qhdm = '").append(dwdm.subSequence(0, 6)).append("'");
				sqlG.append(" group by t.dwdm,t.yingjkje,t.yijkje ");
			}else {//派出所用户
				pcsFlag =true;
				sqlQ.append(" select distinct t.dwdm,sum(t.yingjkje) over(partition by t.dwdm ),sum(t.yijkje) over(partition by t.dwdm ) ");
				sqlT.append(" and dwdm = '").append(dwdm).append("'");
				sqlG.append(" group by t.dwdm,t.yingjkje,t.yijkje ");
			}
		}else {
			if(yhdwdm.endsWith("00000")) {//五个0为市局用户
				pcsFlag =false;
				sqlQ.append(" select distinct t.qhdm||'000',sum(t.yingjkje) over(partition by t.qhdm ) ,sum(t.yijkje) over(partition by t.qhdm )");
				sqlT.append(" and qhdm like '").append(yhdwdm.subSequence(0, 4)).append("%' and qhdm<>'").append(yhdwdm.subSequence(0, 6)).append("' ");
				sqlG.append(" group by t.qhdm,t.yingjkje,t.yijkje ");
			}else if(yhdwdm.endsWith("000")) {//三个0为分局用户
				pcsFlag =false;
				sqlQ.append(" select distinct t.dwdm,sum(t.yingjkje) over(partition by t.dwdm ),sum(t.yijkje) over(partition by t.dwdm ) ");
				sqlT.append(" and qhdm = '").append(yhdwdm.subSequence(0, 6)).append("'");
				sqlG.append(" group by t.dwdm,t.yingjkje,t.yijkje ");
			}else {//派出所用户
				pcsFlag =true;
				sqlQ.append(" select distinct t.dwdm,sum(t.yingjkje) over(partition by t.dwdm ),sum(t.yijkje) over(partition by t.dwdm ) ");
				sqlT.append(" and dwdm = '").append(yhdwdm).append("'");
				sqlG.append(" group by t.dwdm,t.yingjkje,t.yijkje ");
			}
		}
		sqlQT.append(" select '合计',sum(t.yingjkje),sum(t.yijkje) ");
		sql.append(" from (select dwdm,(case when (bzxjfyy = '4' and sffs = '1') then sum(je) else 0 end) as yingjkje,(case when (jfflag = '1' and bzxjfyy = '4' and sffs = '1' ) then sum(je) else 0 end) as yijkje ,qhdm from SFXXB where dylb = '03'  ");
		//应缴款  非在线支付，且 不在线缴费原因为4现金支付
		//实缴款  非在线支付，且 不在线缴费原因为4现金支付     同时缴费标志jfflag为1已缴费
		if(CommonUtil.isNotBlank(sqlT.toString())) {
			sql.append(sqlT);
		}
		sql.append(" and  bzxjfyy is not null ").append(" and sffs='1' and bzxjfyy = '4' ");
//		if(CommonUtil.isNotEmpty(dwdm)) {
//			if(dwdm.endsWith("00000")) {//五个0为市局
//				sql.append(" and qhdm like '").append(dwdm.subSequence(0, 4)).append("%' and qhdm<>'").append(dwdm.subSequence(0, 6)).append("' ");
//
//				//sql.append(" and dwdm like '"+dwdm.substring(0,4)+"%' ");
//			}else if(dwdm.endsWith("000")) {//三个0为分局用户
//				sql.append(" and qhdm = '").append(dwdm.subSequence(0, 6)).append("'");
//
////				sql.append(" and dwdm like '"+dwdm.substring(0,6)+"%' ");
//			}else {//派出所
//				sql.append(" and dwdm = '"+params.getString("dwdm")+"' ");
//			}
//			
//		}
		if(CommonUtil.isNotBlank(params.getString("jksj_start"))) {
			sql.append(" and jksj >= '"+params.getString("jksj_start")+"' ");
		}
		if(CommonUtil.isNotBlank(params.getString("jksj_end"))) {
			sql.append(" and jksj <= '"+params.getString("jksj_end")+"' ");
		}
		if(CommonUtil.isNotBlank(params.getString("jkyid"))) {
			sql.append(" and jkrid = '"+params.getString("jkyid")+"' ");
		}
		sql.append(" group by dwdm,qhdm,jfflag,bzxjfyy,sffs) t  ");
		sqlQ.append(sql).append(sqlG);
		//union all 统计查询语句
		if(!pcsFlag) {
			sqlQ.append(" union all ").append(sqlQT).append(sql);
		}
		List<?> fxjsk  = super.executeSqlQuery(sqlQ.toString(), new Object[]{});
		List<VoSjkxx> list = new ArrayList<>();
		for(Object obj:fxjsk){
			Object[] objs = (Object[])obj;
			VoSjkxx vosjkxx = new VoSjkxx();
			vosjkxx.setDwdm(objs[0]==null?" ":objs[0].toString());
			vosjkxx.setYingjkje(objs[1]==null?" ":objs[1].toString());
			vosjkxx.setYijkje(objs[2]==null?" ":objs[2].toString());
			list.add(vosjkxx);
		}
		Page page = new Page();
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		int fromIndex = (pageIndex-1)*pageSize;
		int endIndex = pageIndex*pageSize;
		page.setList(list.subList(fromIndex,list.size()-endIndex>0? endIndex:list.size()));//取分页所需的数据
		page.setTotalCount(list.size());
		return page;
	
	}

	@Override
	public void updateSfxxbSfzt(ExtMap<String, Object> params) {
		Long dlrid= this.getUser().getYhid();
		String str=this.getUser().getDwdm();
		int end=str.lastIndexOf("000");
		if(end==str.length()-3){
			String sfxxbidList = params.getString("sfxxbidTemp");
			String czsj = StringUtils.getServiceTime();
			String[] sfxxbidArray = sfxxbidList.split(",");
			List<PoSFJFFJB> l = new ArrayList<>();
			for (String sfxxbid : sfxxbidArray) {  
				PoSFXXB sfxxb= new PoSFXXB();
				String sql = " from PoSFXXB where sfxxbid = '"+sfxxbid+"'";
				List<PoSFXXB> sfxxbList = (List<PoSFXXB>) super.getObjectListByHql(sql);
				if(sfxxbList.size()>0) {
					sfxxb = sfxxbList.get(0);
					sfxxb.setShzt("1");
					sfxxb.setShsj(czsj);
					sfxxb.setShrid(dlrid);
					sfxxb.setXgcs(1);//修改次数为1，就不让修改了20210202
					super.update(sfxxb);
				}
		     }
		}else {
			throw new RuntimeException("此用户没有数据范围，无法操作，必须为分县局用户！");
		}
	}

	@Override
	public Page queryFxjshqf(ExtMap<String, Object> params) {
		PoXT_YHXXB dqyh = this.getUser();
		String yhdwdm = dqyh.getDwdm();
		String dwdm = params.getString("dwdm");//查询单位代码
		if(CommonUtil.isNotEmpty(dwdm)) {
			if(dwdm.endsWith("00000")) {//五个0为市局用户
				throw new RuntimeException("只可以查询分县局单位或派出所！");
			}else if(dwdm.endsWith("000")) {//三个0为分局用户
				//查全分局，将dwdm字段置为空，按qhdm查
				params.put("dwdm", null);
				params.put("qhdm", dwdm.substring(0,6));
			}
		}else {
			if(yhdwdm.endsWith("00000")) {//五个0为市局用户
				throw new RuntimeException("只有分县局单位用户才有权限！");
			}else if(yhdwdm.endsWith("000")) {//三个0为分局用户
				
			}else {
				throw new RuntimeException("只有分县局单位用户才有权限！");
			}
		}
		return super.getPageRecords("/conf/segment/common", "queryFxjshqfList", params);
	}

}
