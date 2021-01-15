package com.hzjc.hz2004.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.base.dict.DictData;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.common.HjCommon;
import com.hzjc.hz2004.constant.HjConstant;
import com.hzjc.hz2004.constant.PublicConstant;
import com.hzjc.hz2004.constant.ZjConstant;
import com.hzjc.hz2004.po.PoDW_DY_SET;
import com.hzjc.hz2004.po.PoHJSP_BGSPZB;
import com.hzjc.hz2004.po.PoHJSP_HJBLSPSQB;
import com.hzjc.hz2004.po.PoHJSP_HJSCSPSQB;
import com.hzjc.hz2004.po.PoHJSP_HJSPZB;
import com.hzjc.hz2004.po.PoHJSP_QYZXXB;
import com.hzjc.hz2004.po.PoHJSP_ZQZXXB;
import com.hzjc.hz2004.po.PoHJTJ_RYBDXXB;
import com.hzjc.hz2004.po.PoHJXX_CZRKJBXXB;
import com.hzjc.hz2004.po.PoHJXX_CZRKJBXXB_DYLS;
import com.hzjc.hz2004.po.PoHJXX_HXXB;
import com.hzjc.hz2004.po.PoHJXX_MLPXXXXB;
import com.hzjc.hz2004.po.PoHJXX_RYZPXXB;
import com.hzjc.hz2004.po.PoHJXX_ZPLSB;
import com.hzjc.hz2004.po.PoHJYW_BGGZXXB;
import com.hzjc.hz2004.po.PoHJYW_HCYBDXXB;
import com.hzjc.hz2004.po.PoHJYW_HJBLXXB;
import com.hzjc.hz2004.po.PoHJYW_QCCLXXB;
import com.hzjc.hz2004.po.PoHJYW_QCZXXXB;
import com.hzjc.hz2004.po.PoHJYW_SWZXXXB;
import com.hzjc.hz2004.po.PoHZ_ZJ_SB;
import com.hzjc.hz2004.po.PoLODOP;
import com.hzjc.hz2004.po.PoLSSFZ_SLXXB;
import com.hzjc.hz2004.po.PoPERSON_DY_SET;
import com.hzjc.hz2004.po.PoV_HJSPBGGZ;
import com.hzjc.hz2004.po.PoV_HJSPHBBG;
import com.hzjc.hz2004.po.PoV_HJ_BGGZXXB;
import com.hzjc.hz2004.po.PoV_ZJ_YDZSLXXB;
import com.hzjc.hz2004.po.PoXT_DWXXB;
import com.hzjc.hz2004.po.PoXT_JLXXXB;
import com.hzjc.hz2004.po.PoXT_JWHXXB;
import com.hzjc.hz2004.po.PoXT_XTCSB;
import com.hzjc.hz2004.po.PoXT_XTKZCSB;
import com.hzjc.hz2004.po.PoXT_XZJDXXB;
import com.hzjc.hz2004.po.PoXT_XZQHB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.po.PoYDZJ_SBXXB;
import com.hzjc.hz2004.po.PoYDZJ_SDZP;
import com.hzjc.hz2004.po.PoZJYW_SLXXB;
import com.hzjc.hz2004.service.CommonService;
import com.hzjc.hz2004.service.HjService;
import com.hzjc.hz2004.service.LodopService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.vo.VoHjdyHqxx;
import com.hzjc.hz2004.vo.VoQyzbhhtxx;
import com.hzjc.wsstruts.dao.hibernate.DefaultDAO;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.WSErrCode;

import sun.misc.BASE64Encoder;

/**
 * 户籍业务类
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */

@Service
public class LodopServiceImpl
    extends HjCommon
    implements LodopService {
	@Autowired
	private HjService hjService;
  //日志处理
  protected static Log _log = LogFactory.getLog(LodopServiceImpl.class);
@Override
public String processLodop(String id, String lodopId,String jsonStr,String index,String baseUrl){
	List<?> obj2 = super.findAllByHQL("from PoXT_XTKZCSB a where a.kzlb='10027'");
	String kzz = "";//控制值，用来区分不同时区，调用不同的模板
	if (obj2 != null && obj2.size() > 0) {
		PoXT_XTKZCSB pz = (PoXT_XTKZCSB) obj2.get(0);
		kzz = pz.getKzz();
	}
	
	if(lodopId.equals("czrkdjb")) {//常驻人口登记表
		if(CommonUtil.isNotEmpty(kzz)) {
			if(kzz.equals("1")) {
				lodopId="czrkdjbq";
			}else if(kzz.equals("2")) {
				lodopId="czrkdjbthf";
			}else {
				lodopId="czrkdjbq";
			}
		}else {
			lodopId="czrkdjbthf";
		}
		return queryCzrkdjbinfo(id, lodopId, jsonStr,baseUrl);
	}else if(lodopId.equals("jmhkb_dycs")||lodopId.equals("jmhkb_sy")||lodopId.equals("jmhkb_bm")||lodopId.equals("jmhkb_jthfs_sy")||lodopId.equals("jmhkb_jthfshky")||lodopId.equals("jmhkb_csyy")||lodopId.equals("jmhkb_ny")) {//居民户口薄
		int i=0;
		if(lodopId.equals("jmhkb_bm")) {
			i=Integer.parseInt(index);
		}
		return queryJmhkbinfo(id, lodopId, jsonStr,i,index,baseUrl);
	}else if(lodopId.equals("hjzm")||lodopId.equals("hjzmx")){//户籍证明
		return queryHjzminfo(id, lodopId, jsonStr,index);
	}else if(lodopId.equals("hjzmNew")){//户籍证明
		return queryHjzmNewinfo(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("hkxzzm")){//户口性质证明
		return queryHkxzzminfo(id, lodopId, jsonStr,index);
	}else if(lodopId.equals("djksyb")){//登记卡索引表
		return queryDjksybinfo(id, lodopId, jsonStr,Integer.parseInt(index));
	}else if(lodopId.equals("zdydy")) {//自定义打印
		if(index.equals("czrkdjbqt")||index.equals("czrkdjbq")||index.equals("czrkdjbtbb")||index.equals("czrkdjbt")||index.equals("cbtdqcwb")||index.equals("cbqdqcwb")) {//
			return queryCzrkdjbinfo(id, index, jsonStr,baseUrl);
		}else if(index.equals("czrkdjbbmzd")||index.equals("czrkdjbbmtd")||index.equals("czrkdjbbmtdnv")||index.equals("czrkdjbbmtdnan")) {
			return queryZdydyinfo(id, index, jsonStr);
		}
	}else if(lodopId.equals("zqz")) {//准迁证打印
		return queryZqzdyinfo(id, lodopId, jsonStr,index);
	}else if(lodopId.equals("qyz1")||lodopId.equals("qyz2")) {//迁移证打印
		return queryQyzdyinfo(lodopId, jsonStr,index,baseUrl);
	}else if(lodopId.equals("rkxx")) {//迁移证打印
		return queryRkxxinfo(lodopId,id);
	}else if(lodopId.equals("hjblspb")) {//户籍补录审批表
		return queryHjblspbinfo(lodopId,id,jsonStr);
	}else if(lodopId.equals("hjscspb")) {//户籍删除审批表
		return queryHjscspbinfo(lodopId,id);
	}else if(lodopId.equals("swzxzm")) {//死亡注销证明
		return querySwzxzminfo(lodopId,id,jsonStr);
	}else if(lodopId.equals("czrkdjbyhybdy")) {//一户一表打印-常住人口登记表
		return queryCzrkdjbyhybdyinfo(id,lodopId,jsonStr,index,6);
	}else if(lodopId.equals("czrkhddjbyhybdy")) {//一户一表打印-常住人口登记表
		return queryCzrkdjbyhybdyinfo(id,lodopId,jsonStr,index,3);
	}else if(lodopId.equals("cbtaoda")||lodopId.equals("cbzhengda")) {//常表成批打印-常表套打  常表整打
		if(lodopId.equals("cbzhengda")) {
			lodopId="czrkdjbq";
		}else if(lodopId.equals("cbtaoda")){
			lodopId="czrkdjbthf";
		}else {
			lodopId="czrkdjbthf";
		}
		return queryCzrkdjbinfo(id, lodopId, "{\"printset\":{\"printset_3\":{\"checked\":true,\"disabled\":false}}}",baseUrl);
	}else if(lodopId.equals("bgspb")) {//变更审批打印
		return queryBgspbinfo(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("cld")) {//催领单打印
		return queryCldinfo(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("tzd")) {//通知单打印
		return queryTzdinfo(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("cbd")) {//催办单打印
		return queryCbdinfo(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("wts")||lodopId.equals("sldjb")) {//委托书打印
		return queryWtsinfo(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("fafd")) {//证件发放单打印
		return queryZjfafdinfo(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("fenfd")) {//证件分发单打印
		return queryZjfafdinfo(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("ydslsldjb")) {//申领登记表打印
		return queryYdslsldjbinfo(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("jmsfzsldjb")) {//居民身份证受理登记表
		return queryJmsfzsldjbinfo(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("gmsfzsfd")) {//受理单
		return queryGmsfzsfdinfo(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("dkdyzd")||lodopId.equals("dkdytd")) {//底卡打印
		return queryDkdyinfo(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("kzjsx")) {// 快证介绍信
		return queryKzjsxinfo(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("lsjmsfzsldjb")) {//受理单
		return queryLsjmsfzsldjbinfo(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("cbqc")) {//常表迁出打印
		return queryCbqcinfo(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("cg")) {//存根打印
		return queryCginfo(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("zxqsgxzm")) {//直系亲属关系证明打印
		return queryZxqsgxzm(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("hkzxzm")) {//户口注销证明打印
		
		if(CommonUtil.isNotEmpty(kzz)) {
			if(kzz.equals("1")) {
				lodopId="hkzxzm";
			}else if(kzz.equals("2")) {
				lodopId="qczxzmly";
			}else {
				lodopId="hkzxzm";
			}
		}else {
			lodopId="hkzxzm";
		}
		if(lodopId.equals("qczxzmly")) {
			return queryQczxzmly(lodopId,jsonStr,id,index);
		}else {
			return queryHkzxzm(lodopId,jsonStr,id,index);
		}
		
	}else if(lodopId.equals("wdjhk")) {//当事人未登记户口的证明出具
		return queryWdjhk(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("fhzm")) {//分户证明
		return queryFhzm(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("bggzxmzm")) {//户口登记项目内容变更更正证明
		return queryBggzxmzm(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("gatdjzxzm")) {//香港、澳门、台湾定居注销户口证明
		return queryGatGwdjzxzm(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("gwdjzxzm")) {//国外定居注销户口证明
		return queryGatGwdjzxzm(lodopId,jsonStr,id,index);
	}else if(lodopId.equals("sftyrxzhczm")) {//公民是否同一人的协助核查证明
		return querySftyrxzhczm(lodopId,jsonStr,id,index);
	}
	
	return "";

	
}

private String queryHjzmNewinfo(String lodopId, String jsonStr, String id, String index) {

	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	JSONObject jo = JSONObject.parseObject(jsonStr);
	//根据传入ryid获得表中数据
	String StrHqlById="";
	String gmsfhm = jo.getString("gmsfhm");
	PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb =null;
	//VoHJXX_CZRKJBXXB    PoHJXX_CZRKJBXXB
	if(CommonUtil.isNotEmpty(gmsfhm)) {
		StrHqlById ="from "+PoHJXX_CZRKJBXXB.class.getName()+" where  gmsfhm='"+gmsfhm+"' and ryzt = '0' and cxbz = '0' and jlbz = '1' ";
		List hjxx_czrkjbxxbList = super.findAllByHQL(StrHqlById);
		if(hjxx_czrkjbxxbList.size()==0||hjxx_czrkjbxxbList.size()>1) {
			//返回前台，当前人数据库中未能查询到合适数据
			return "当前人数据库中未能查询到合适数据";
		}else {
			vohjxx_czrkjbxxb=(PoHJXX_CZRKJBXXB) hjxx_czrkjbxxbList.get(0);
			//查询套打表中数据
			String strHQL ="";
			String strHxxhql="";
			PoLODOP poldop = null;
			PoHJXX_HXXB pohjxx_hxxb =null;
			String result = "";
			String xbTran="";//性别
			String mzTran="";//名族  
			String addressTran="";
			String hbTran ="";//户类型转换后的值
			String yhzgxTran="";//与户主关系
			String csdssxqTran="";//出生地
			String hyzkTran ="";// 婚姻状况
			String jhryjhgxTran="";
			String jhrejhgxTran="";
			String jgssxqTran ="";//籍贯
			String byzkTran="";//兵役状况
			String whcdTran="";//文化程度
			String bdlxTran = "";//变动类型
			String bdyyTran = "";//变动原因
			String bdsxTran = "";//变动省县
			String bdyzTran = "";//变动原址
			String pcsTran="";
			String jcwhTran="";
			String hzxmTran ="";//户主姓名
			String xxTran="";//血型
			String cszqfrqTran ="";//公民出生证签发日期
			//String yxqxqsrqTran ="";//居民身份证签发日期
			String hsqlTran = "";//
			String hyqlTran ="";
			String hssxqqlTran ="";
			String hslbzTran="";
			String hylbzTran="";
			String hsssqlbzTran="";
			String qcrqTran = "";
			String qczxlbTran = "";
			int count=0;
			strHQL = "from " + PoLODOP.class.getName() + "" +
					" where lodopId = 'hjzmx' and zxbz = '0' ";
			strHxxhql="from "+PoHJXX_HXXB.class.getName()+" where hhnbid ='"+vohjxx_czrkjbxxb.getHhnbid()+"'";
			 List lodopList =super.findAllByHQL(strHQL);
			 List pohjxx_hxxbList=super.findAllByHQL(strHxxhql);
		     if (lodopList != null && lodopList.size() ==1&&pohjxx_hxxbList != null && pohjxx_hxxbList.size() ==1) {
		    	 poldop = (PoLODOP) lodopList.get(0);
		    	 pohjxx_hxxb = (PoHJXX_HXXB) pohjxx_hxxbList.get(0);
		    	 hbTran=getMcByClsb("1005", vohjxx_czrkjbxxb.getHb());
		    	 hzxmTran =getHzxm(vohjxx_czrkjbxxb);
		    	 yhzgxTran =getMcByClsb("8006", vohjxx_czrkjbxxb.getYhzgx());
		    	 xbTran=getMcByClsb("8003", vohjxx_czrkjbxxb.getXb());
		    	 hyzkTran=getMcByClsb("8007", vohjxx_czrkjbxxb.getHyzk());
		    	 mzTran=getMcByClsb("8001", vohjxx_czrkjbxxb.getMz());
		    	 whcdTran =getMcByClsb("8002", vohjxx_czrkjbxxb.getWhcd());
		    	 byzkTran =getMcByClsb("1046", vohjxx_czrkjbxxb.getByzk());
		    	 xxTran=getMcByClsb("1044", vohjxx_czrkjbxxb.getXx());
		    	 csdssxqTran = getCsdInfo(vohjxx_czrkjbxxb.getCsdssxq());
		    	 jgssxqTran= getCsdInfo(vohjxx_czrkjbxxb.getJgssxq());
		    	 //cslb 1005    户别        8003 性别       8001   民族     8006  与户主关系     8007  婚姻状况  8002 文化程度   1046 兵役状况     1044血型
		    	 //地址获取
		    	 addressTran = getAddressByQhdm(vohjxx_czrkjbxxb);
		    	 hyqlTran = getMcByClsb("1020", vohjxx_czrkjbxxb.getHyql());
		    	 hssxqqlTran = getCsdInfo(vohjxx_czrkjbxxb.getHssxqql());
		    	cszqfrqTran=transStringToDateformatter(vohjxx_czrkjbxxb.getCszqfrq(),1);
		    	//yxqxqsrqTran = transStringToDateformatter(vohjxx_czrkjbxxb.getYxqxqsrq(),1);
		    	hsqlTran =transStringToDateformatter(vohjxx_czrkjbxxb.getHsql(),2);
		    	hslbzTran=transStringToDateformatter(vohjxx_czrkjbxxb.getHslbz(),2);
				hylbzTran=getMcByClsb("1056", vohjxx_czrkjbxxb.getHylbz());
				hsssqlbzTran=getCsdInfo(vohjxx_czrkjbxxb.getHsssqlbz());
				qcrqTran = transStringToDateformatter(vohjxx_czrkjbxxb.getQcrq(),2);
				qczxlbTran = getMcByClsb("1026", vohjxx_czrkjbxxb.getQczxlb());
		    	pcsTran = getPcs(vohjxx_czrkjbxxb.getPcs());
		    	jcwhTran = getJcwh(vohjxx_czrkjbxxb.getJcwh());
		    	jhryjhgxTran =getMcByClsb("8006", vohjxx_czrkjbxxb.getJhryjhgx());
				jhrejhgxTran =getMcByClsb("8006", vohjxx_czrkjbxxb.getJhrejhgx());
		 		result += poldop.getNr();
		 		result = result.replace("{zp}", getZp(vohjxx_czrkjbxxb));
				
		 		List familyMember =getFamilyMember(vohjxx_czrkjbxxb.getHhnbid(),2);
		 		count= familyMember.size();
		 		if(count>0){
		 			result = result.replace("{hcylb}", "户成员信息");
		 			result = result.replace("{xmtitle1}", "姓名");
		 			result = result.replace("{xbtitle1}", "性别");
		 			result = result.replace("{jtgxtitle1}", "家庭关系");
		 			result = result.replace("{xmtitle2}", "姓名");
		 			result = result.replace("{xbtitle2}", "性别");
		 			result = result.replace("{jtgxtitle2}", "家庭关系");
		 			
		 			result = result.replace("{gongyou}", "共有");
		 			result = result.replace("{ren1}", "人");
		 			result = result.replace("{daying}", "打印");
		 			result = result.replace("{ren2}", "人");
		 			result = result.replace("{qrycount}", count+"");
		 			if(count>10) {
		 				result = result.replace("{recordcount}", 10+"");
		 			}else {
		 				result = result.replace("{recordcount}", count+"");
		 			}
		 			int temp =0;
		 			for(int i=0;i<familyMember.size();i++) {
		 				PoHJXX_CZRKJBXXB family =new PoHJXX_CZRKJBXXB();
		 				family=(PoHJXX_CZRKJBXXB) familyMember.get(i);
		 				//if(family.getRynbid().longValue()!=Long.parseLong(id)) {
		 					temp=temp+1;
		 					result = result.replace("{xm"+temp+"}", CommonUtil.isEmpty(family.getXm())?"":family.getXm());
		 					result = result.replace("{xb"+temp+"}", getMcByClsb("8003", family.getXb()));
		 					result = result.replace("{yhzgx"+temp+"}",getMcByClsb("8006", family.getYhzgx()));
		 				//}
		 			}
		 			//十个家庭成员列表不满足人数的为空
		 			for(int i=temp+1;i<=10;i++){
		 				result = result.replace("{xm"+i+"}","");
	 					result = result.replace("{xb"+i+"}", "");
	 					result = result.replace("{yhzgx"+i+"}","");
		 			}
		 		}else {
		 			result = result.replace("{hcylb}", "");
		 			result = result.replace("{xmtitle1}", "");
		 			result = result.replace("{xbtitle1}", "");
		 			result = result.replace("{jtgxtitle1}", "");
		 			result = result.replace("{xmtitle2}", "");
		 			result = result.replace("{xbtitle2}", "");
		 			result = result.replace("{jtgxtitle2}", "");
		 			
		 			result = result.replace("{gongyou}", "");
		 			result = result.replace("{ren1}", "");
		 			result = result.replace("{daying}", "");
		 			result = result.replace("{ren2}", "");
		 			result = result.replace("{qrycount}", "");
		 			result = result.replace("{recordcount}", "");
		 			//十个家庭成员列表都置为空
		 			for(int i=1;i<=10;i++){
		 				result = result.replace("{xm"+i+"}","");
	 					result = result.replace("{xb"+i+"}", "");
	 					result = result.replace("{yhzgx"+i+"}","");
		 			}
		 		}
		 		result = result.replace("{hh}", CommonUtil.isEmpty(vohjxx_czrkjbxxb.getHh())?"":vohjxx_czrkjbxxb.getHh());
		 		result = result.replace("{hyzk}", hyzkTran);

	 			//printset_14  打印变动原因 
		 			String rybdStrHql ="from "+PoHJTJ_RYBDXXB.class.getName()+" where 1=1 and rzjs='1' and ryid='"+vohjxx_czrkjbxxb.getRyid()+"' and rynbid ='"+vohjxx_czrkjbxxb.getRynbid()+"' ";
		 			List hjtj_rybdxxbList = super.findAllByHQL(rybdStrHql);
					PoHJTJ_RYBDXXB bd_hjtj_rybdxxb = new PoHJTJ_RYBDXXB();
					if(hjtj_rybdxxbList.size()>0) {
						bd_hjtj_rybdxxb = (PoHJTJ_RYBDXXB) hjtj_rybdxxbList.get(0);
						result = result.replace("{bdyy}",getMcByClsb("1056", vohjxx_czrkjbxxb.getHyql()));
						
					}else {
						result = result.replace("{bdyy}", "");
					}
		 		result = result.replace("{bdlx}", getMcByClsb("1053",vohjxx_czrkjbxxb.getBdfw()));
	 			result = result.replace("{bdrq}", transStringToDateformatter(vohjxx_czrkjbxxb.getHsql(),4));
	 			result = result.replace("{bdyy}", getMcByClsb("1056", vohjxx_czrkjbxxb.getHyql()));
	 			if(vohjxx_czrkjbxxb.getHssxqql()!=null) {
	 				String xz = "";
	 		        PoXT_XZQHB qh = super.get(PoXT_XZQHB.class, vohjxx_czrkjbxxb.getHssxqql());
	 		        if(qh!=null)
	 		        	xz = qh.getMc();
	 				result = result.replace("{bdsx}", xz);
	 			}else {
	 				result = result.replace("{bdsx}", "");
	 			}
	 			if(vohjxx_czrkjbxxb.getHxzql()!=null) {
	 				result = result.replace("{bdyz}", vohjxx_czrkjbxxb.getHxzql());
	 			}else {
	 				result = result.replace("{bdyz}","");
	 			}

	 			result = result.replace("{bdlx}", "");
	 			result = result.replace("{bdrq}", "");
	 			result = result.replace("{bdyy}", "");
	 			result = result.replace("{bdsx}", "");
	 			result = result.replace("{bdyz}", "");
	 			result = result.replace("{byzk}", byzkTran);
	 			result = result.replace("{whcd}", whcdTran);
	 			result = result.replace("{xm}", CommonUtil.isEmpty(vohjxx_czrkjbxxb.getXm())?"":vohjxx_czrkjbxxb.getXm());
	 			result = result.replace("{xb}",xbTran);
	 			result = result.replace("{mz}",mzTran);
	 			result = result.replace("{csrq}", CommonUtil.isEmpty(vohjxx_czrkjbxxb.getCsrq())?"":vohjxx_czrkjbxxb.getCsrq());
	 			result = result.replace("{address}",addressTran);
	 			result = result.replace("{gmsfhm}",CommonUtil.isEmpty(vohjxx_czrkjbxxb.getGmsfhm())?"":vohjxx_czrkjbxxb.getGmsfhm());
	 			result = result.replace("{zp}",getZp(vohjxx_czrkjbxxb));
	 			result = result.replace("{pcs}", pcsTran);
	 			result = result.replace("{hb}", hbTran);
	 			result = result.replace("{hzxm}", hzxmTran);
	 			result = result.replace("{yhzgx}", yhzgxTran);
	 			result = result.replace("{xx}", xxTran);
	 			result = result.replace("{csdssxq}", csdssxqTran);
	 			result = result.replace("{cszqfrq}", cszqfrqTran);
	 			result = result.replace("{jgssxq}", jgssxqTran);
	 			result = result.replace("{hsql}", hsqlTran);
	 			result = result.replace("{jhryjhgx}", jhryjhgxTran);
	 			result = result.replace("{jhrejhgx}", jhrejhgxTran);
	 			result = result.replace("{hyql}", hyqlTran);
	 			result = result.replace("{hssxqql}", hssxqqlTran);
	 			result = result.replace("{hslbz}", hslbzTran);
	 			result = result.replace("{hylbz}", hylbzTran);
	 			result = result.replace("{hsssqlbz}", hsssqlbzTran);
	 			result = result.replace("{qcrq}", qcrqTran);
	 			result = result.replace("{qczxlb}", qczxlbTran);
	 			result = result.replace("{jcwh}", jcwhTran);
	 			result = result.replace("{dlm}", this.getUserInfo().getYhxm());
	 			result = result.replace("{bdlx}", "");
	 			result = result.replace("{bdrq}", "");
	 			result = result.replace("{bdsx}", "");
	 			result = result.replace("{bdyz}", "");
	 			Date date = new Date();
				result = result.replace("{yyyy}",  String.format("%tY", date));
				result = result.replace("{mm}", String.format("%tm", date));
				result = result.replace("{dd}",String.format("%td", date));
	 			result = result.replace("{dyrq}", String.format("%tY", date)+"年"+String.format("%tm", date)+"月"+String.format("%td", date)+"日");
	 			result = result.replace("{yxts}",this.getPersonDySet().getYxts()+"天");
	 			result = generrateFromObj(vohjxx_czrkjbxxb, result);
	 			hqsjDao.saveHjxxDyxxb(vohjxx_czrkjbxxb,"hjzmx",null,String.format("%tY", new Date()));
	 			String hzywid = jo.getString("hzywid");
	 			if(CommonUtil.isNotEmpty(hzywid)) {
	 				updateHz_zj_sb(hzywid);
	 			}
		     }
		     return result;
		}		
	}else {
		return "查询用户，表中不存在";
	}

}

private String queryQczxzmly(String lodopId, String jsonStr, String id, String index) {
	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//查询套打表中数据
	String strHQL ="";
	PoLODOP poldop = null;
	String result = "";
	PoHJYW_QCZXXXB hjyw_qczxxxb = new PoHJYW_QCZXXXB();
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(lodopList.size()==0) {
		 result ="符合条件的模板不存在！";
	 }else if(lodopList.size()>1){
		 result="符合条件的模板数目不止一条！";
	 }else if(lodopList.size()==1){
		 poldop = (PoLODOP) lodopList.get(0);
		 result += poldop.getNr();
		 JSONObject json = JSONObject.parseObject(jsonStr);
		 String qczxid = json.getString("qczxid");	
		 String rynbid = json.getString("rynbid");
		 if(CommonUtil.isNotEmpty(qczxid)) {
			 hjyw_qczxxxb =super.get(PoHJYW_QCZXXXB.class, Long.parseLong(qczxid));
		 }else {
			String hql="from "+PoHJYW_QCZXXXB.class.getName()+" where  rynbid='"+rynbid+"'";
			List hjyw_qczxxbList = super.findAllByHQL(hql);
			if(hjyw_qczxxbList.size()>0) {
				hjyw_qczxxxb = (PoHJYW_QCZXXXB) hjyw_qczxxbList.get(0);
			}else {
				return null;
			}
		 }
		 if(hjyw_qczxxxb!=null) {
			 PoHJXX_CZRKJBXXB hjxx_czrkjbxxb = super.get(PoHJXX_CZRKJBXXB.class, hjyw_qczxxxb.getRynbid());
			 PoHJTJ_RYBDXXB hjtj_rybdxxb = super.get(PoHJTJ_RYBDXXB.class, hjyw_qczxxxb.getRynbid());
			 String lxdh = this.getDwDySet().getPcslxdh();
			 if(CommonUtil.isNotEmpty(hjyw_qczxxxb.getCsrq())) {
				 String csrqTemp = hjyw_qczxxxb.getCsrq();
				 if(csrqTemp.length()>=8) {
					 result = result.replace("{csrr}",transStringToDateformatter(hjyw_qczxxxb.getCsrq(),3));
				 }
				 
			 }else {
				 result = result.replace("{csrq}","");
			 }
			 result = result.replace("{cym}",CommonUtil.isEmpty(hjxx_czrkjbxxb.getCym())?"无":hjxx_czrkjbxxb.getCym());
			 result = result.replace("{hyzk}",getMcByClsb("8007", hjxx_czrkjbxxb.getHyzk()));
			 result = result.replace("{xb}",getMcByClsb("8003", hjyw_qczxxxb.getXb()));
			 result = result.replace("{mz}",getMcByClsb("8001", hjxx_czrkjbxxb.getMz()));
			 result = result.replace("{hlx}",getMcByClsb("1007", hjxx_czrkjbxxb.getHlx()));
			 if(hjxx_czrkjbxxb!=null) {
				 result = result.replace("{yzz}",getAddressByQhdm(hjxx_czrkjbxxb));
			 }else {
				 result = result.replace("{yzz}","");
			 }
			 result = result.replace("{jg}",getCsdInfo(hjxx_czrkjbxxb.getJgssxq()));
			 result = result.replace("{pcs}",CommonUtil.isEmpty(hjyw_qczxxxb.getPcs())?"":getPcs(hjyw_qczxxxb.getPcs()));
			 if(CommonUtil.isNotEmpty(hjyw_qczxxxb.getQclb())){
				 result = result.replace("{qcyy}",getMcByClsb("1019", hjyw_qczxxxb.getQclb()));
			 }else {
				 result = result.replace("{qcyy}","");
			 }
			 if(CommonUtil.isNotEmpty(hjyw_qczxxxb.getQcrq())){
				 result = result.replace("{qcrq}",transStringToDateformatter(hjyw_qczxxxb.getQcrq(),3));
			 }else {
				 result = result.replace("{qcrq}","");
			 }
			 if(CommonUtil.isNotEmpty(hjyw_qczxxxb.getBz())) {
					result = result.replace("{bz}",hjyw_qczxxxb.getBz());
			 }else {
					if(CommonUtil.isNotEmpty(hjxx_czrkjbxxb.getZjlb())) {
						String zjlbTran = getMcByClsb("5012", hjxx_czrkjbxxb.getZjlb());
						String qfjgTran = CommonUtil.isNotEmpty(hjxx_czrkjbxxb.getQfjg())?hjxx_czrkjbxxb.getQfjg():"";
						String bzTran = "("+hjxx_czrkjbxxb.getXm()+"已办理"+zjlbTran+", 签发机关："+qfjgTran+", 签发日期："+hjxx_czrkjbxxb.getYxqxqsrq()+"。)";
						result = result.replace("{bz}",bzTran);
					}else {
						result = result.replace("{bz}","");
					}
			 }
			 String hzywid = json.getString("hzywid");
			 String ywlb = json.getString("ywlb");
			 if(CommonUtil.isNotEmpty(ywlb)&&ywlb.equals("24")&&CommonUtil.isNotEmpty(hzywid)) {
				 updateHz_zj_sb(hzywid);
			 }
			 SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
			 Date date = new Date();
			 String temDate = sdf.format(date);
			 result = result.replace("{dyrq}",transStringToDateformatter(temDate,3));
			 hqsjDao.saveHjxxDyxxb(hjxx_czrkjbxxb,lodopId,null,String.format("%tY", new Date()));
			 result = generrateFromObj(hjxx_czrkjbxxb, result);
		 }
	 }
	 return result;
}

private String querySftyrxzhczm(String lodopId, String jsonStr, String id, String index) {
	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//查询套打表中数据
	String strHQL ="";
	PoLODOP poldop = null;
	String result = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(lodopList.size()==0) {
		 result ="符合条件的模板不存在！";
	 }else if(lodopList.size()>1){
		 result="符合条件的模板数目不止一条！";
	 }else if(lodopList.size()==1){
		 poldop = (PoLODOP) lodopList.get(0);
		 result += poldop.getNr();
		 JSONObject json = JSONObject.parseObject(jsonStr);
		 result = result.replace("{xm1}",CommonUtil.isEmpty(json.getString("xm1"))?"":json.getString("xm1"));
		 result = result.replace("{gmsfhm1}",CommonUtil.isEmpty(json.getString("gmsfhm1"))?"":json.getString("gmsfhm1"));
		 result = result.replace("{gmsfhm2}",CommonUtil.isEmpty(json.getString("gmsfhm2"))?"":json.getString("gmsfhm2"));
		 result = result.replace("{pcs}",getPcs(this.getUser().getDwdm()));
		 SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
		 Date date = new Date();
		 String temDate = sdf.format(date);
		 result = result.replace("{dyrq}",transStringToDateformatter(temDate,3));
		 if(CommonUtil.isNotEmpty(json.getString("hzywid"))) {
			 updateHz_zj_sb(json.getString("hzywid"));
		 }
		 PoHJXX_CZRKJBXXB hjxx_czrkjbxxb = super.get(PoHJXX_CZRKJBXXB.class, json.getLong("rynbid"));
		 hqsjDao.saveHjxxDyxxb(hjxx_czrkjbxxb,lodopId,null,String.format("%tY", new Date()));
	 }
	return result;
}

private String queryGatGwdjzxzm(String lodopId, String jsonStr, String id, String index) {
	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//查询套打表中数据
	String strHQL ="";
	String StrHqlById ="";
	PoLODOP poldop = null;
	String result = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(lodopList.size()==0) {
		 result ="符合条件的模板不存在！";
	 }else if(lodopList.size()>1){
		 result="符合条件的模板数目不止一条！";
	 }else if(lodopList.size()==1){
		 poldop = (PoLODOP) lodopList.get(0);
		 result += poldop.getNr();
		 StrHqlById ="from "+PoHJXX_CZRKJBXXB.class.getName()+" where 1=1  and rynbid ='"+id+"' order by rynbid ";
		 List hjxx_czrkjbxxbList = super.findAllByHQL(StrHqlById);
		 if(hjxx_czrkjbxxbList.size()>0) {
			 PoHJXX_CZRKJBXXB hjxx_czrkjbxxb = (PoHJXX_CZRKJBXXB) hjxx_czrkjbxxbList.get(0);
			 if(CommonUtil.isNotEmpty(hjxx_czrkjbxxb.getCsrq())) {
				 String csrqTemp = hjxx_czrkjbxxb.getCsrq();
				 if(csrqTemp.length()>=8) {
					 result = result.replace("{yyyy}",csrqTemp.substring(0,4));
					 result = result.replace("{mm}",csrqTemp.substring(4,6));
					 result = result.replace("{dd}",csrqTemp.substring(6,8)); 
				 }
				 
			 }else {
				 result = result.replace("{yyyy}","");
				 result = result.replace("{mm}","");
				 result = result.replace("{dd}","");
			 }
			 result = result.replace("{xb}",getMcByClsb("8003", hjxx_czrkjbxxb.getXb()));
			 result = result.replace("{address}",getAddressByQhdm(hjxx_czrkjbxxb));
			 result = result.replace("{pcs}",CommonUtil.isEmpty(hjxx_czrkjbxxb.getPcs())?"":getPcs(hjxx_czrkjbxxb.getPcs()));
			 PoHJYW_QCZXXXB hjyw_qczxxxb = new PoHJYW_QCZXXXB();
			 if(CommonUtil.isNotEmpty(index)) {
				 hjyw_qczxxxb =super.get(PoHJYW_QCZXXXB.class, Long.parseLong(index));
				 
//				 result = result.replace("{zxyy}","");
			 }else{
				 String StrHqlByRynbId ="from "+PoHJYW_QCZXXXB.class.getName()+" where 1=1  and rynbid ='"+hjxx_czrkjbxxb.getRynbid()+"' ";
				 List hjyw_qczxxxbList = super.findAllByHQL(StrHqlByRynbId);
				 if(hjyw_qczxxxbList.size()>0) {
					 hjyw_qczxxxb = (PoHJYW_QCZXXXB) hjyw_qczxxxbList.get(0);
				 }
			 }
			 if(hjyw_qczxxxb!=null) {
				 if(CommonUtil.isNotEmpty(hjyw_qczxxxb.getQwdssxq())) {
					 PoXT_XZQHB qh = super.get(PoXT_XZQHB.class, hjyw_qczxxxb.getQwdssxq());
					 if(qh!=null)
					 result = result.replace("{zxyy}",qh.getMc());
				 }else {
					 result = result.replace("{zxyy}","");
				 }
			 }else {
				 result = result.replace("{zxyy}",""); 
			 }
			
			 SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
			 Date date = new Date();
			 String temDate = sdf.format(date);
			 result = result.replace("{dyrq}",transStringToDateformatter(temDate,3));
			
			 result = generrateFromObj(hjxx_czrkjbxxb, result);
			 JSONObject json = JSONObject.parseObject(jsonStr);
			 if(CommonUtil.isNotEmpty(json.getString("hzywid"))) {
				 updateHz_zj_sb(json.getString("hzywid"));
			 }
			 hqsjDao.saveHjxxDyxxb(hjxx_czrkjbxxb,lodopId,null,String.format("%tY", new Date()));
		 }
	 }
	return result;
}

private String queryBggzxmzm(String lodopId, String jsonStr, String id, String index) {
	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//根据传入ryid获得表中数据
	String StrHqlById="";
	String bggzxm ="";
	
	if(!id.isEmpty()) {
		StrHqlById ="from "+PoHJYW_BGGZXXB.class.getName()+" where  bggzid in ("+id+")";
		List hjyw_bggzxxbList = super.findAllByHQL(StrHqlById);
		if(hjyw_bggzxxbList.size()==0) {
			//返回前台，当前人数据库中未能查询到合适数据
			return "当前人数据库中未能查询到合适数据";
		}else {
			
			//查询套打表中数据
			String strHQL ="";
			PoLODOP poldop = null;
			String result = "";
			strHQL = "from " + PoLODOP.class.getName() + "" +
					" where lodopId = '"+lodopId+"' and zxbz = '0' ";
			 List lodopList =super.findAllByHQL(strHQL);
		     if (lodopList != null && lodopList.size() ==1) {
		    	 poldop = (PoLODOP) lodopList.get(0);
		 		result += poldop.getNr();
		 		for(int i = 0;i<hjyw_bggzxxbList.size();i++) {
		 			PoHJYW_BGGZXXB hjyw_bggzxxb=(PoHJYW_BGGZXXB) hjyw_bggzxxbList.get(i);
		 			String bggzrq = CommonUtil.isNotEmpty(hjyw_bggzxxb.getBggzrq())?hjyw_bggzxxb.getBggzrq():"";
		 			String bggqnr = CommonUtil.isNotEmpty(hjyw_bggzxxb.getBggzqnr())?hjyw_bggzxxb.getBggzqnr():"无";
		 			String bgghnr = CommonUtil.isNotEmpty(hjyw_bggzxxb.getBggzhnr())?hjyw_bggzxxb.getBggzhnr():"无";
		 			if(hjyw_bggzxxb.getBggzxm().equals("照片")) {
		 				bggzxm += bggzrq+"照片进行了变更\\n";
		 			}else {
		 				bggzxm += bggzrq+hjyw_bggzxxb.getBggzxm()+"从"+bggqnr+"变更到"+bgghnr+"\\n";
		 			}
		 		}
		 		if(CommonUtil.isNotEmpty(bggzxm)) {
		 			result = result.replace("{bgnr}",bggzxm.substring(0, bggzxm.length()-2));
		 		}else {
		 			result = result.replace("{bgnr}","");
		 		}
		 		if(hjyw_bggzxxbList.size()>0) {
		 			PoHJYW_BGGZXXB hjyw_bggzxxb0=(PoHJYW_BGGZXXB) hjyw_bggzxxbList.get(0);
		 			Long rynbid = hjyw_bggzxxb0.getRynbid();
		 			StrHqlById ="from "+PoHJXX_CZRKJBXXB.class.getName()+" where  rynbid='"+rynbid+"'";
		 			List<PoHJXX_CZRKJBXXB> hjxx_czrkjbxxbList = (List<PoHJXX_CZRKJBXXB>) super.findAllByHQL(StrHqlById);
		 			if(hjxx_czrkjbxxbList.size()>0) {
		 				result = result.replace("{xm}",hjxx_czrkjbxxbList.get(0).getXm());
			 			result = result.replace("{gmsfhm}",hjxx_czrkjbxxbList.get(0).getGmsfhm());
			 			if(CommonUtil.isNotEmpty(hjxx_czrkjbxxbList.get(0).getDhhm())) {
			 				result = result.replace("{dhhm}",hjxx_czrkjbxxbList.get(0).getDhhm());
			 			}else {
			 				result = result.replace("{dhhm}","");
			 			}
			 			hqsjDao.saveHjxxDyxxb(hjxx_czrkjbxxbList.get(0),lodopId,null,String.format("%tY", new Date()));
		 			}else {
		 				result = result.replace("{xm}","");
			 			result = result.replace("{gmsfhm}","");
			 			result = result.replace("{dhhm}","");
		 			}
		 		}else {
		 			result = result.replace("{xm}","");
		 			result = result.replace("{gmsfhm}","");
		 			result = result.replace("{dhhm}","");
		 		}
		 		if(CommonUtil.isNotEmpty(index)) {
					 updateHz_zj_sb(index);
				}
		     }
		     return result;
		}		
	}else {
		return "查询用户，表中不存在";
	}
}

private String queryFhzm(String lodopId, String jsonStr, String id, String index) {
	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//查询套打表中数据
	String strHQL ="";
	PoLODOP poldop = null;
	String result = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
     if (lodopList != null && lodopList.size() ==1) {
    	 poldop = (PoLODOP) lodopList.get(0);
 		result += poldop.getNr();
 		JSONObject json = JSONObject.parseObject(jsonStr);
		result = result.replace("{xm}",CommonUtil.isEmpty(json.getString("xm"))?"":json.getString("xm"));
		result = result.replace("{gmsfhm}",CommonUtil.isEmpty(json.getString("gmsfhm"))?"":json.getString("gmsfhm"));
		String StrHqlById ="from "+PoHJXX_CZRKJBXXB.class.getName()+" where 1=1  and gmsfhm ='"+json.getString("gmsfhm")+"' order by rynbid desc ";
		List hjxx_czrkjbxxbList = super.findAllByHQL(StrHqlById);
		int size = hjxx_czrkjbxxbList.size();
		if(size>1) {
			
		}
		result = result.replace("{pcs}",getPcs(this.getUser().getDwdm()));
		 SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
		 Date date = new Date();
		 String temDate = sdf.format(date);
		 result = result.replace("{dyrq}",transStringToDateformatter(temDate,3));
		 if(CommonUtil.isNotEmpty(json.getString("hzywid"))) {
			 updateHz_zj_sb(json.getString("hzywid"));
		 }
		 hqsjDao.saveHjxxDyxxb((PoHJXX_CZRKJBXXB)hjxx_czrkjbxxbList.get(0),lodopId,null,String.format("%tY", new Date()));
     }
     return result;
}

private String queryWdjhk(String lodopId, String jsonStr, String id, String index) {
	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//查询套打表中数据
	String strHQL ="";
	PoLODOP poldop = null;
	String result = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
     if (lodopList != null && lodopList.size() ==1) {
    	 poldop = (PoLODOP) lodopList.get(0);
 		 result += poldop.getNr();
 		 JSONObject json = JSONObject.parseObject(jsonStr);
		 result = result.replace("{xm}",CommonUtil.isEmpty(json.getString("xm"))?"":json.getString("xm"));
		 result = result.replace("{gmsfhm}",CommonUtil.isEmpty(json.getString("gmsfhm"))?"":json.getString("gmsfhm"));
		 result = result.replace("{pcs}",getPcs(this.getUser().getDwdm()));
		 SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
		 Date date = new Date();
		 String temDate = sdf.format(date);
		 result = result.replace("{dyrq}",transStringToDateformatter(temDate,3));
		 if(CommonUtil.isNotEmpty(json.getString("hzywid"))) {
			 updateHz_zj_sb(json.getString("hzywid"));
		 }
		 PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb = new PoHJXX_CZRKJBXXB();
		 vohjxx_czrkjbxxb.setGmsfhm(json.getString("gmsfhm"));
		 vohjxx_czrkjbxxb.setXm(json.getString("xm")+"");
		 vohjxx_czrkjbxxb.setPcs(this.getUser().getDwdm());
		 hqsjDao.saveHjxxDyxxb(vohjxx_czrkjbxxb,lodopId,null,String.format("%tY", new Date()));
     }
     return result;
}

private String queryHkzxzm(String lodopId, String jsonStr, String id, String index) {
	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//查询套打表中数据
	String strHQL ="";
	String StrHqlById ="";
	PoLODOP poldop = null;
	String result = "";
	String slsj = "";
	String slsjyyyy = "";
	String slsjmm = "";
	String slsjdd = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(lodopList.size()==0) {
		 result ="符合条件的模板不存在！";
	 }else if(lodopList.size()>1){
		 result="符合条件的模板数目不止一条！";
	 }else if(lodopList.size()==1){
		 poldop = (PoLODOP) lodopList.get(0);
		 result += poldop.getNr();
		 StrHqlById ="from "+PoHJXX_CZRKJBXXB.class.getName()+" where 1=1  and rynbid ='"+id+"' order by rynbid ";
		 List hjxx_czrkjbxxbList = super.findAllByHQL(StrHqlById);
		 if(hjxx_czrkjbxxbList.size()>0) {
			 PoHJXX_CZRKJBXXB hjxx_czrkjbxxb = (PoHJXX_CZRKJBXXB) hjxx_czrkjbxxbList.get(0);
			 if(CommonUtil.isNotEmpty(hjxx_czrkjbxxb.getCsrq())) {
				 String csrqTemp = hjxx_czrkjbxxb.getCsrq();
				 if(csrqTemp.length()>=8) {
					 result = result.replace("{yyyy}",csrqTemp.substring(0,4));
					 result = result.replace("{mm}",csrqTemp.substring(4,6));
					 result = result.replace("{dd}",csrqTemp.substring(6,8)); 
				 }
				 
			 }else {
				 result = result.replace("{yyyy}","");
				 result = result.replace("{mm}","");
				 result = result.replace("{dd}","");
			 }
			 result = result.replace("{xb}",getMcByClsb("8003", hjxx_czrkjbxxb.getXb()));
			 result = result.replace("{address}",getAddressByQhdm(hjxx_czrkjbxxb));
			 result = result.replace("{pcs}",CommonUtil.isEmpty(hjxx_czrkjbxxb.getPcs())?"":getPcs(hjxx_czrkjbxxb.getPcs()));
			 JSONObject json = JSONObject.parseObject(jsonStr);
			 String hzywid = json.getString("hzywid");
			 String ywlb = json.getString("ywlb");
			 String fromPage = json.getString("fromPage");
			 if(CommonUtil.isNotEmpty(fromPage)&&fromPage.equals("rkxx")) {
				 String qczxlb = getMcByClsb("1019", hjxx_czrkjbxxb.getQczxlb());
				 result = result.replace("{zxyy}",CommonUtil.isNotEmpty(qczxlb)?qczxlb:"");
			 }else {
				 if(CommonUtil.isNotEmpty(hjxx_czrkjbxxb.getRyzt())){
					 if(HjConstant.RYZT_SW.equals(hjxx_czrkjbxxb.getRyzt())) {
						 result = result.replace("{zxyy}","死亡");
					 }else if(HjConstant.RYZT_QC.equals(hjxx_czrkjbxxb.getRyzt())){
						 result = result.replace("{zxyy}","迁出");
					 }else if(HjConstant.RYZT_FBY.equals(hjxx_czrkjbxxb.getRyzt())){
						 result = result.replace("{zxyy}","服兵役");
					 }else if(HjConstant.RYZT_CGJDJ.equals(hjxx_czrkjbxxb.getRyzt())){
						 result = result.replace("{zxyy}","出国境定居");
					 }else if(HjConstant.RYZT_SZ.equals(hjxx_czrkjbxxb.getRyzt())){
						 result = result.replace("{zxyy}","失踪");
					 }else if(HjConstant.RYZT_SC.equals(hjxx_czrkjbxxb.getRyzt())){
						 result = result.replace("{zxyy}","删除");
					 }else if(HjConstant.RYZT_ZX.equals(hjxx_czrkjbxxb.getRyzt())){
						 result = result.replace("{zxyy}","注销(用于冲销)");
					 }else if(HjConstant.RYZT_QT.equals(hjxx_czrkjbxxb.getRyzt())){
						 result = result.replace("{zxyy}","其他");
					 }else {
						 result = result.replace("{zxyy}","未知原因");
					 }
				 }else {
					 result = result.replace("{zxyy}","");
				 }
			 }
			 
			 if(CommonUtil.isNotEmpty(ywlb)&&ywlb.equals("24")&&CommonUtil.isNotEmpty(hzywid)) {
				 updateHz_zj_sb(hzywid);
			 }
			 SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
			 Date date = new Date();
			 String temDate = sdf.format(date);
			 result = result.replace("{dyrq}",transStringToDateformatter(temDate,3));
			 hqsjDao.saveHjxxDyxxb(hjxx_czrkjbxxb,lodopId,null,String.format("%tY", new Date()));
			 result = generrateFromObj(hjxx_czrkjbxxb, result);
		 }
	 }
	return result;
}

private String queryZxqsgxzm(String lodopId, String jsonStr, String id, String index) {
	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//查询套打表中数据
	String strHQL ="";
	String StrHqlById ="";
	PoLODOP poldop = null;
	String result = "";
	String slsj = "";
	String slsjyyyy = "";
	String slsjmm = "";
	String slsjdd = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(lodopList.size()==0) {
		 result ="符合条件的模板不存在！";
	 }else if(lodopList.size()>1){
		 result="符合条件的模板数目不止一条！";
	 }else if(lodopList.size()==1){
		 poldop = (PoLODOP) lodopList.get(0);
		 result += poldop.getNr();
		 JSONObject json = JSONObject.parseObject(jsonStr);
		 result = result.replace("{zxqsgxid}",CommonUtil.isEmpty(json.getString("zxqsgxid"))?"":json.getString("zxqsgxid"));
		 result = result.replace("{xm1}",CommonUtil.isEmpty(json.getString("xm1"))?"":json.getString("xm1"));
		 result = result.replace("{xb1}",CommonUtil.isEmpty(json.getString("xb1"))?"":DictData.getCodeName("CS_XB", json.getString("xb1")));
		 result = result.replace("{gmsfhm1}",CommonUtil.isEmpty(json.getString("gmsfhm1"))?"":json.getString("gmsfhm1"));
		 result = result.replace("{xm2}",CommonUtil.isEmpty(json.getString("xm2"))?"":json.getString("xm2"));
		 result = result.replace("{xb2}",CommonUtil.isEmpty(json.getString("xb2"))?"":DictData.getCodeName("CS_XB", json.getString("xb2")));
		 result = result.replace("{gmsfhm2}",CommonUtil.isEmpty(json.getString("gmsfhm2"))?"":json.getString("gmsfhm2"));
        
		 if(CommonUtil.isNotEmpty(json.getString("csrq1"))&&json.getString("csrq1").length()>=8) {
			 String csrq1 = json.getString("csrq1");
			 result = result.replace("{yyyy1}",csrq1.substring(0, 4));
			 result = result.replace("{mm1}",csrq1.substring(4, 6));
			 result = result.replace("{dd1}",csrq1.substring(6, 8));
		 }
		 if(CommonUtil.isNotEmpty(json.getString("csrq2"))&&json.getString("csrq2").length()>=8) {
			 String csrq2 = json.getString("csrq2");
			 result = result.replace("{yyyy2}",csrq2.substring(0, 4));
			 result = result.replace("{mm2}",csrq2.substring(4, 6));
			 result = result.replace("{dd2}",csrq2.substring(6, 8));
		 }
		 result = result.replace("{gx}",CommonUtil.isEmpty(json.getString("zxqsgx"))?"":DictData.getCodeName("CS_JTGX", json.getString("zxqsgx")));//CS_JTGX
		 result = result.replace("{pcs}",CommonUtil.isEmpty(json.getString("pcs"))?"":getPcs(json.getString("pcs")));
		 SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
		 Date date = new Date();
		 String temDate = sdf.format(date);
		 result = result.replace("{dyrq}",transStringToDateformatter(temDate,3));
		 if(CommonUtil.isNotEmpty(json.getString("ywlb"))&&json.getString("ywlb").equals("15")&&CommonUtil.isNotEmpty(json.getString("hzywid"))) {
			 updateHz_zj_sb(json.getString("hzywid"));
		 }
		 PoHJXX_CZRKJBXXB hjxx_czrkjbxxb = super.get(PoHJXX_CZRKJBXXB.class, json.getLong("rynbid"));
		 hqsjDao.saveHjxxDyxxb(hjxx_czrkjbxxb,lodopId,null,String.format("%tY", new Date()));
		 //transStringToDateformatter(vohjxx_czrkjbxxb.getCsrq(),3);
	 }
	return result;
}

private void updateHz_zj_sb(String hzywid) {
  List list = super.findAllByHQL("from PoHZ_ZJ_SB a where a.id='" + hzywid.trim()+"'");
  if(list.size()>0){
    PoHZ_ZJ_SB sb = (PoHZ_ZJ_SB)list.get(0);
    sb.setClbs("1");
    sb.setClsj(new java.sql.Timestamp(new Date().getTime()));
    sb.setBlrsfz(this.getUser().getGmsfhm());
    super.update(sb);
  }
	
}

private String queryCginfo(String lodopId, String jsonStr, String id, String index) {
	//查询套打表中数据
	String strHQL ="";
	String StrHqlById ="";
	PoLODOP poldop = null;
	String result = "";
	String slsj = "";
	String slsjyyyy = "";
	String slsjmm = "";
	String slsjdd = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(lodopList.size()==0) {
		 result ="符合条件的模板不存在！";
	 }else if(lodopList.size()>1){
		 result="符合条件的模板数目不止一条！";
	 }else if(lodopList.size()==1){
		 poldop = (PoLODOP) lodopList.get(0);
		 result += poldop.getNr();
		 JSONObject json = JSONObject.parseObject(jsonStr);
         //StringBuilder 再次转回String 替换高度完毕
		 StrHqlById =" from "+PoHJTJ_RYBDXXB.class.getName()+" a where 1=1  and  hjywid ='"+json.getString("hjywid")+"' order by rybdid ";
		 List hjtj_rybdxxbList = super.findAllByHQL(StrHqlById);
		 if(hjtj_rybdxxbList.size()==0) {
			 throw new ServiceException("查询变动信息无记录!");
		 }
		 Map<String, PoHJTJ_RYBDXXB> map = new HashMap<String,PoHJTJ_RYBDXXB>();
		 int mapLength = 0;
		 for(int i = 0;i<hjtj_rybdxxbList.size();i++) {
			PoHJTJ_RYBDXXB hjtj_rybdxxb = (PoHJTJ_RYBDXXB) hjtj_rybdxxbList.get(i);
			if(!map.containsKey(hjtj_rybdxxb.getXm())) {
				map.put(hjtj_rybdxxb.getXm(),hjtj_rybdxxb);
				result = result.replace("{xm"+(mapLength+1)+"}",CommonUtil.isEmpty(hjtj_rybdxxb.getXm())?"":hjtj_rybdxxb.getXm());
				result = result.replace("{xb"+(mapLength+1)+"}",getMcByClsb("8003", hjtj_rybdxxb.getXb()));
				result = result.replace("{gmsfhm"+(mapLength+1)+"}",CommonUtil.isEmpty(hjtj_rybdxxb.getGmsfhm())?"":hjtj_rybdxxb.getGmsfhm());
				mapLength++;
			}
		 }
		 if(hjtj_rybdxxbList.size()>0) {
			 PoHJTJ_RYBDXXB hjtj_rybdxxb = (PoHJTJ_RYBDXXB) hjtj_rybdxxbList.get(0);
			 PoHJYW_QCCLXXB hjyw_qcclxxb = super.get(PoHJYW_QCCLXXB.class, json.getLong("qcclid")); 
			 hjyw_qcclxxb.setClbz("2");
			 super.update(hjyw_qcclxxb);
			 if(hjtj_rybdxxbList.size()>0) {
					int temp =map.size();
					for(int i =temp+1;i<=4;i++) {
						result = result.replace("{xm"+i+"}","");
						result = result.replace("{xb"+i+"}","");
						result = result.replace("{gmsfhm"+i+"}","");
					}
			}
			 result = result.replace("{qyyy}",getMcByClsb("1056", hjtj_rybdxxb.getBdyy()));
			 result = result.replace("{yzz}",getHylbzTemp3(hjtj_rybdxxb));
			 result = result.replace("{qwzz}",getHylbzTemp4(hjtj_rybdxxb));
			 result = result.replace("{qyrq}",transStringToDateformatter(hjtj_rybdxxb.getBdrq(),1));
			 result = result.replace("{qcfpcs}",getPcs(hjyw_qcclxxb.getPcs_q()));
			 result = result.replace("{qcfjwh}",getJcwh(hjyw_qcclxxb.getJcwh_q()));
			 result = result.replace("{qrfpcs}",getPcs(hjyw_qcclxxb.getPcs_h()));
			 result = result.replace("{qrfjwh}",getJcwh(hjyw_qcclxxb.getJcwh_h()));
			 result = generrateFromObj(hjtj_rybdxxb, result);
		 }
	 }
	return result;
}

private String queryCbqcinfo(String lodopId, String jsonStr, String id, String index) {
	//查询套打表中数据
	String strHQL ="";
	String StrHqlById ="";
	PoLODOP poldop = null;
	String result = "";
	String slsj = "";
	String slsjyyyy = "";
	String slsjmm = "";
	String slsjdd = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(lodopList.size()==0) {
		 result ="符合条件的模板不存在！";
	 }else if(lodopList.size()>1){
		 result="符合条件的模板数目不止一条！";
	 }else if(lodopList.size()==1){
		 poldop = (PoLODOP) lodopList.get(0);
		 result += poldop.getNr();
		 JSONObject json = JSONObject.parseObject(jsonStr);
         //StringBuilder 再次转回String 替换高度完毕
		 StrHqlById ="from "+PoHJTJ_RYBDXXB.class.getName()+" where 1=1 and gmsfhm='"+json.getString("gmsfhm")+"' and hjywid ='"+json.getString("hjywid")+"' ";
		 List hjtj_rybdxxbList = super.findAllByHQL(StrHqlById);
		 if(hjtj_rybdxxbList.size()>0) {
			PoHJTJ_RYBDXXB hjtj_rybdxxb = (PoHJTJ_RYBDXXB) hjtj_rybdxxbList.get(0);
			PoHJYW_QCCLXXB hjyw_qcclxxb = super.get(PoHJYW_QCCLXXB.class, json.getLong("qcclid")); 
			hjyw_qcclxxb.setClbz("2");
			super.update(hjyw_qcclxxb);
			slsj = hjtj_rybdxxb.getSlsj();
			if(slsj!=null&&slsj.length()>=8) {
				slsjyyyy =slsj.substring(0, 4);
				slsjmm =slsj.substring(4, 6);
				slsjdd =slsj.substring(6, 8);
	    	 }else {
	    		 slsjyyyy ="";
	    		 slsjmm ="";
	    		 slsjdd ="";
	    	 }
			 result = result.replace("{slsjyyyy}",slsjyyyy);
			 result = result.replace("{slsjmm}",slsjmm);
			 result = result.replace("{slsjdd}",slsjdd);
			 result = result.replace("{hyqwhz}",getHylbzTemp2(hjtj_rybdxxb));
			 
			 result = generrateFromObj(hjtj_rybdxxb, result);
		 }
	 }
	return result;
}

private String queryLsjmsfzsldjbinfo(String lodopId, String jsonStr, String id, String index) {
	//查询套打表中数据
	String strHQL ="";
	String StrHqlById ="";
	PoLODOP poldop = null;
	String czsjTran = "";
	String result = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(lodopList.size()==0) {
		 result ="符合条件的模板不存在！";
	 }else if(lodopList.size()>1){
		 result="符合条件的模板数目不止一条！";
	 }else if(lodopList.size()==1){
		 poldop = (PoLODOP) lodopList.get(0);
		 result += poldop.getNr();
		 JSONObject json = JSONObject.parseObject(jsonStr);
         //StringBuilder 再次转回String 替换高度完毕
		 StrHqlById ="from "+PoLSSFZ_SLXXB.class.getName()+" where 1=1 and ryid='"+id+"' order by czsj desc ";
		 List zj_ydzslxxbList = super.findAllByHQL(StrHqlById);
		 if(zj_ydzslxxbList.size()>0) {
			 PoLSSFZ_SLXXB lssfz_slxxb = (PoLSSFZ_SLXXB) zj_ydzslxxbList.get(0);
			 PoXT_YHXXB yhxxb=super.get(PoXT_YHXXB.class,lssfz_slxxb.getCzyid());
			 PoHJXX_CZRKJBXXB czrk = new PoHJXX_CZRKJBXXB();
			 try {
				BeanUtils.copyProperties(czrk, lssfz_slxxb);
			 } catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			 }
			 result = result.replace("{address}",getAddressByQhdm(czrk));
			 result = result.replace("{xb}",getMcByClsb("8003", lssfz_slxxb.getXb()));
			 result = result.replace("{mz}",getMcByClsb("8001", lssfz_slxxb.getMz()));
			 result = result.replace("{zp}", getZp(super.get(PoHJXX_CZRKJBXXB.class,json.getLong("rynbid"))));
			 result = result.replace("{jbr}", CommonUtil.isEmpty(yhxxb.getYhxm())?"":yhxxb.getYhxm());
			 czsjTran = lssfz_slxxb.getCzsj();
			 result = result.replace("{jbsj}", transStringToDateformatter(czsjTran,3));
			 result = generrateFromObj(lssfz_slxxb, result);
		 }
	 }
	return result;
}

private String queryKzjsxinfo(String lodopId, String jsonStr, String id, String index) {
	//查询套打表中数据
	String strHQL ="";
	String StrHqlById ="";
	PoLODOP poldop = null;
	String result = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(lodopList.size()==0) {
		 result ="符合条件的模板不存在！";
	 }else if(lodopList.size()>1){
		 result="符合条件的模板数目不止一条！";
	 }else if(lodopList.size()==1){
		 poldop = (PoLODOP) lodopList.get(0);
		 result += poldop.getNr();
		 JSONObject json = JSONObject.parseObject(jsonStr);
         //StringBuilder 再次转回String 替换高度完毕
		 StrHqlById ="from "+PoV_ZJ_YDZSLXXB.class.getName()+" where 1=1 and bzslid='"+id+"'";
		 List zj_ydzslxxbList = super.findAllByHQL(StrHqlById);
		 if(zj_ydzslxxbList.size()>0) {
			 PoV_ZJ_YDZSLXXB ydzj_sbxxb = (PoV_ZJ_YDZSLXXB) zj_ydzslxxbList.get(0);
			 PoHJXX_CZRKJBXXB czrkjPo=super.get(PoHJXX_CZRKJBXXB.class,ydzj_sbxxb.getRynbid());
			 result = result.replace("{xb}",getMcByClsb("8003", ydzj_sbxxb.getXb()));
			 result = result.replace("{mz}",getMcByClsb("8001", ydzj_sbxxb.getMz()));
			 String csrq = czrkjPo.getCsrq();
			 if(csrq!=null&&csrq.length()==8) {
				 result = result.replace("{csrq}",csrq.substring(0, 4)+"年"+csrq.substring(4,6)+"月"+csrq.substring(6, 8)+"日");
			 }else {
				 result = result.replace("{csrq}","");
			 }
			 result = result.replace("{zz}",getAddressByQhdm(czrkjPo));
			 String qfrq = ydzj_sbxxb.getQfrq();
			 if(qfrq!=null&&qfrq.length()==8) {
				 result = result.replace("{qfrq}",qfrq.substring(0, 4)+"年"+qfrq.substring(4,6)+"月"+qfrq.substring(6, 8)+"日");
			 }else {
				 result = result.replace("{qfrq}","");
			 }
			 int yxqxTran = 0;
			 if(czrkjPo.getYxqxqsrq() != null&&czrkjPo.getYxqxjzrq()!= null) {
				 yxqxTran = Integer.parseInt(czrkjPo.getYxqxjzrq().substring(0,4))-Integer.parseInt(czrkjPo.getYxqxqsrq().substring(0, 4));
			 }
			 if(yxqxTran==0) {
				 result = result.replace("{yxqx}",""); 
			 }else {
				 result = result.replace("{yxqx}",yxqxTran+"年");
			 }
			 
			 result = result.replace("{bzyy}",getMcByClsb("5002", ydzj_sbxxb.getBzyy()));
			 result = result.replace("{bh}",CommonUtil.isEmpty(czrkjPo.getGmsfhm())?"":czrkjPo.getGmsfhm());
			 SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
			 Date date = new Date();
			 String temDate = sdf.format(date);
			 result = result.replace("{zp}", getZp(czrkjPo));
			 result = result.replace("{yyyy}", temDate.substring(0, 4));
			 result = result.replace("{mm}", temDate.substring(4, 6));
			 result = result.replace("{dd}", temDate.substring(6,8));
			 result = generrateFromObj(ydzj_sbxxb, result);
		 }
	 }
	return result;
}

private String queryDkdyinfo(String lodopId, String jsonStr, String id, String index) {
	//查询套打表中数据
	String strHQL ="";
	String StrHqlById ="";
	PoLODOP poldop = null;
	String result = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(lodopList.size()==0) {
		 result ="符合条件的模板不存在！";
	 }else if(lodopList.size()>1){
		 result="符合条件的模板数目不止一条！";
	 }else if(lodopList.size()==1){
		 poldop = (PoLODOP) lodopList.get(0);
		 result += poldop.getNr();
		 JSONObject json = JSONObject.parseObject(jsonStr);
		 String heightTemp = json.getString("pageHeight");
		 String widthtemp  = json.getString("pageWidth");
		 boolean zzzh = json.getBoolean("zzzh");
         int secondIndex = getIndex(2,result);
         int thirdIndex = getIndex(3,result);
         StringBuilder result1 = new StringBuilder(result);
         if(widthtemp!=null) {
        	 result1.replace(secondIndex+1, thirdIndex,widthtemp);//先替换宽度
        	 result = result1.toString();
        	//StringBuilder 再次转回String 替换了高度字符串，还要重新查找插入高度的位置
         }
         if(heightTemp!=null) {
        	 thirdIndex = getIndex(3,result);
             int fourIndex = getIndex(4,result);
             result1=new StringBuilder(result);
             result1.replace(thirdIndex+1, fourIndex,heightTemp); 
             result = result1.toString();
         }
         if(zzzh) {//折行宽度设置小一点，就换行了
        	 int dzIndex = result.indexOf("{dz}");
        	 result1=new StringBuilder(result);
        	 result1.replace(dzIndex-8,dzIndex-5,"130");
        	 result = result1.toString();
         }
         //StringBuilder 再次转回String 替换高度完毕
		 StrHqlById ="from "+PoV_ZJ_YDZSLXXB.class.getName()+" where 1=1 and bzslid='"+id+"'";
		 List zj_ydzslxxbList = super.findAllByHQL(StrHqlById);
		 if(zj_ydzslxxbList.size()>0) {
			 PoV_ZJ_YDZSLXXB ydzj_sbxxb = (PoV_ZJ_YDZSLXXB) zj_ydzslxxbList.get(0);
			 result = result.replace("{xb}",getMcByClsb("8003", ydzj_sbxxb.getXb()));
			 result = result.replace("{mz}",getMcByClsb("8001", ydzj_sbxxb.getMz()));
			 String csrq = ydzj_sbxxb.getCsrq();
			 if(csrq!=null&&csrq.length()>=8) {
				 result = result.replace("{csrqy}",csrq.substring(0, 4));
				 result = result.replace("{csrqm}",csrq.substring(4, 6));
				 result = result.replace("{csrqd}",csrq.substring(6, 8));
			 }else {
				 result = result.replace("{csrqy}","");
				 result = result.replace("{csrqm}","");
				 result = result.replace("{csrqd}",""); 
			 }
			 result = result.replace("{dz}",ydzj_sbxxb.getZz1()+ydzj_sbxxb.getZz2());
			 String qfrq = ydzj_sbxxb.getQfrq();
			 if(qfrq!=null&&qfrq.length()>=8) {
				 result = result.replace("{qfrqy}",qfrq.substring(0, 4));
				 result = result.replace("{qfrqm}",qfrq.substring(4, 6));
				 result = result.replace("{qfrqd}",qfrq.substring(6, 8));
			 }else {
				 result = result.replace("{qfrqy}","");
				 result = result.replace("{qfrqm}","");
				 result = result.replace("{qfrqd}",""); 
			 }
			 result = result.replace("{yxqx}",getMcByClsb("5000", ydzj_sbxxb.getYxqx()));
			 result = result.replace("{gmsfhm}",CommonUtil.isEmpty(ydzj_sbxxb.getGmsfhm())?"":ydzj_sbxxb.getGmsfhm());
			 String mlpnbid = ydzj_sbxxb.getMlpnbid().toString();	
			 if(mlpnbid!=null) {
				 result = result.replace("{mlpqz}",CommonUtil.isEmpty(mlpnbid)?"":mlpnbid.substring(0, 9)); 
			 }
			 result = generrateFromObj(ydzj_sbxxb, result);
		 }
	 }
	return result;


}

private String queryBgspbinfo(String lodopId,String jsonStr, String spywid, String index) {
	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//根据传入ryid获得表中数据
	String StrHqlById="";
	PoV_HJSPBGGZ hjspbggz =null;
	//VoHJXX_CZRKJBXXB    PoHJXX_CZRKJBXXB
	if(!spywid.isEmpty()) {
		//select V_HJSPBGGZ  from PoV_HJSPBGGZ as V_HJSPBGGZ where 1=1 and 
		StrHqlById ="select V_HJSPBGGZ  from PoV_HJSPBGGZ as V_HJSPBGGZ where 1=1 and  spywid='"+spywid+"'";
		List hjspbggzList = super.findAllByHQL(StrHqlById);
		if(hjspbggzList.size()==0||hjspbggzList.size()>1) {
			//返回前台，当前人数据库中未能查询到合适数据
			return "当前人数据库中未能查询到合适数据";
		}else {
			hjspbggz=(PoV_HJSPBGGZ) hjspbggzList.get(0);
			//查询套打表中数据
			String strHQL ="";
			PoLODOP poldop = null;
			String result = "";
			String xbTran="";//性别
			String mzTran="";//名族  
			String addressTran="";
			strHQL = "from " + PoLODOP.class.getName() + "" +
					" where lodopId = '"+lodopId+"' and zxbz = '0' ";
			
			
			 List lodopList =super.findAllByHQL(strHQL);
		     if (lodopList != null && lodopList.size() ==1) {
		    	 poldop = (PoLODOP) lodopList.get(0);
		    	 xbTran=getMcByClsb("8003", hjspbggz.getXb());
		    	 mzTran=getMcByClsb("8001", hjspbggz.getMz());
		    	 //cslb 1005    户别        8003 性别       8001   民族     8006  与户主关系     8007  婚姻状况  8002 文化程度   1046 兵役状况     1044血型
		    	 //地址获取
		    	 PoHJXX_CZRKJBXXB  poczrk = new PoHJXX_CZRKJBXXB();
		    	 poczrk.setSsxq(hjspbggz.getSsxq());
		    	 poczrk.setJlx(hjspbggz.getJlx());
		    	 poczrk.setXzjd(hjspbggz.getXzjd());
		    	 poczrk.setMlph(hjspbggz.getMlph());
		    	 poczrk.setMlxz(hjspbggz.getMlxz());
		    	 addressTran = getAddressByQhdm(poczrk);
		 		result += poldop.getNr();
		 			result = result.replace("{xb}", xbTran);
		 			result = result.replace("{mz}", mzTran);
		 			//result = result.replace("{csrq}", csrqTran);
		 			result = result.replace("{zz}", addressTran);
		 			result = result.replace("{dlm}", CommonUtil.isEmpty(this.getUserInfo().getYhxm())?"":this.getUserInfo().getYhxm());
		 			JSONObject json = JSONObject.parseObject(jsonStr);
		 			if(json!=null) {
		 				result = result.replace("{bggzyy}", CommonUtil.isEmpty(json.getString("bggzxm"))?"":json.getString("bggzxm"));
			 			result = result.replace("{bggzhxm}", CommonUtil.isEmpty(json.getString("bghnr"))?"":json.getString("bghnr"));
		 			}else {
		 				String StrBgzbHql = "from "+PoHJSP_BGSPZB.class.getName()+" where spywid='"+hjspbggz.getSpywid()+"'";
		 				 List hjsp_bgspzbList =super.findAllByHQL(StrBgzbHql);
		 				 if(hjsp_bgspzbList.size()>0) {
		 					PoHJSP_BGSPZB pohjsp_bgspzb = (PoHJSP_BGSPZB) hjsp_bgspzbList.get(0);
		 					result = result.replace("{bggzyy}",pohjsp_bgspzb.getBggzxm());
		 					result = result.replace("{bggzhxm}",pohjsp_bgspzb.getBghnr());
		 				 }
		 			}
		 			Class c1= hjspbggz.getClass();
		 			Field[] fields1  = c1.getDeclaredFields();
		 			for(Field f : fields1) {
		 				// 获取原来的访问控制权限 
		 				boolean accessFlag = f.isAccessible(); 
		 				// 修改访问控制权限 
		 				f.setAccessible(true); 
		 				// 获取在对象f中属性fields[i]对应的对象中的变量  
		 				Object o;
		 				try {
							o = f.get(hjspbggz);
			 				String tempName =f.getName();
			 				if(result.contains(tempName)&&(o!=null)) {
			 					
			 					try {
			 						System.out.println(tempName+":"+o.toString());
									result = result.replace("{"+tempName+"}", o.toString());
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								}
			 				}else {
			 					result = result.replace("{"+tempName+"}","");
			 				}
						} catch (IllegalArgumentException e1) {
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							e1.printStackTrace();
						} 
		 				// 恢复访问控制权限  
		 		        f.setAccessible(accessFlag); 

		 			}
		 			PoHJXX_CZRKJBXXB vohjxx_czrk = new PoHJXX_CZRKJBXXB();
		 			vohjxx_czrk.setRyid(hjspbggz.getRyid());
		 			vohjxx_czrk.setRynbid(hjspbggz.getRynbid());
		 			vohjxx_czrk.setGmsfhm(hjspbggz.getGmsfhm());
		 			vohjxx_czrk.setXm(hjspbggz.getXm());
		 			vohjxx_czrk.setPcs(hjspbggz.getPcs());
		 			vohjxx_czrk.setSsxq(hjspbggz.getSsxq());
		 			vohjxx_czrk.setMlpnbid(hjspbggz.getMlpnbid());
		 			vohjxx_czrk.setJlx(hjspbggz.getJlx());
		 			vohjxx_czrk.setMlph(hjspbggz.getMlph());
		 			vohjxx_czrk.setMlxz(hjspbggz.getMlxz());
		 			vohjxx_czrk.setZrq(hjspbggz.getZrq());
		 			vohjxx_czrk.setXzjd(hjspbggz.getXzjd());
		 			vohjxx_czrk.setJcwh(hjspbggz.getJcwh());
		 			//hqsjDao.saveHjxxDyxxb(vohjxx_czrk,lodopId,null,String.format("%tY", new Date()));
		 	
		     }
		     return result;
		}		
	}else {
		return "查询用户，表中不存在";
	}
}
private String queryCzrkhddjbyhybdyinfo(String id, String lodopId, String jsonStr, String index) {
	String result = "continue";
	PoLODOP poldop = null;
	JSONObject json = JSONObject.parseObject(jsonStr);
	String hhid = json.getString("hhid");
	String pcs = json.getString("pcs");
	Long hhnbid = json.getLong("hhnbid");
	String hh = json.getString("hh");
	Integer limit = json.getInteger("limit");
	List familyMember =getFamilyMember(hhnbid,1);
	if(familyMember.size()>=limit) {
		return "next";
	}
	String strHQL1 = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	List lodopList =super.findAllByHQL(strHQL1);
	PoHJXX_HXXB pohxx = getHxx(pcs,hhnbid);
	PoHJXX_MLPXXXXB pomlp = getMlp(pcs,pohxx.getMlpnbid());
	if (lodopList != null && lodopList.size() ==1) {
		int tmp1 =(Integer.parseInt(index))*3;
		int tmp2 =(1+Integer.parseInt(index))*3;
		poldop = (PoLODOP) lodopList.get(0);
		if(familyMember.size()>tmp2) {
			result += poldop.getNr();
		}else if(familyMember.size()<=6||(familyMember.size()>tmp1&&familyMember.size()<=tmp2)) {
			result = poldop.getNr();
		}
		
		String hbTran ="";
		String dwjwhTran = "";
		String xzjdjlxTran = "";
		
		hbTran = getMcByClsb("1005", pohxx.getJhlb());
		PoXT_DWXXB dw =  new PoXT_DWXXB();
		PoXT_JWHXXB jwh =  new PoXT_JWHXXB();
		PoXT_JLXXXB jlx =  new PoXT_JLXXXB();
		if(pomlp.getPcs()!=null&&CommonUtil.isNotEmpty(pomlp.getPcs())) {
			dw =  super.get(PoXT_DWXXB.class, pomlp.getPcs());
		}
		if(pomlp.getJcwh()!=null&&CommonUtil.isNotEmpty(pomlp.getJcwh())) {
			jwh =  super.get(PoXT_JWHXXB.class, pomlp.getJcwh());
		}
		if(pomlp.getJlx()!=null&&CommonUtil.isNotEmpty(pomlp.getJlx())) {
			jlx =  super.get(PoXT_JLXXXB.class, pomlp.getJlx());
		}
		
        if(dw!=null)
        	dwjwhTran=dwjwhTran+dw.getMc();
        if(jwh!=null)
        	dwjwhTran=dwjwhTran+jwh.getMc();
        if(jlx!=null)
        	xzjdjlxTran=xzjdjlxTran+jlx.getMc();
        if(pomlp.getMlph()!=null)
        	xzjdjlxTran=xzjdjlxTran+pomlp.getMlph();
        if(pomlp.getMlxz()!=null)
        	xzjdjlxTran=xzjdjlxTran+pomlp.getMlxz();
		result = result.replace("{hh}", pohxx.getHh()+"("+familyMember.size()+")");
		result = result.replace("{hb}", hbTran);
		result = result.replace("{dwjwh}", dwjwhTran);
		result = result.replace("{xzjdjlx}", xzjdjlxTran);
		for(int i=tmp1;i<tmp2;i++) {
			for(int j=0;j<3&&(i+j)<familyMember.size();j++) {
				PoHJXX_CZRKJBXXB czrkobj = ((PoHJXX_CZRKJBXXB)familyMember.get(i+j));
				String cymTran ="";
				String sgTran ="";
				String zyTran = "";
				String fwcsTran = "";
				String hshdhyqlbzTran = "";
				String hshdhyzxhkTran = "";
				String qtzzTran = "";
				if(czrkobj.getCym()!=null) {
					cymTran = czrkobj.getCym();
				}
				if(czrkobj.getSg()!=null) {
					sgTran = czrkobj.getSg();
				}
				if(czrkobj.getZy()!=null) {
					zyTran = czrkobj.getZy();
				}
				if(czrkobj.getFwcs()!=null) {
					fwcsTran = czrkobj.getFwcs();
				}
				hshdhyqlbzTran = getHylbzTemp1(czrkobj);
				if(czrkobj.getZxsj() != null) {
					hshdhyzxhkTran = hshdhyzxhkTran+czrkobj.getZxsj();
				}
				if(czrkobj.getQtzz()!=null) {
					qtzzTran = czrkobj.getQtzz();
				}
				hshdhyzxhkTran = getMcByClsb("1021", czrkobj.getSwzxlb());
				result = result.replace("{yhzgx"+(j+1)+"}", getMcByClsb("8006", czrkobj.getYhzgx()));
				result = result.replace("{xm"+(j+1)+"}", czrkobj.getXm());
				result = result.replace("{xb"+(j+1)+"}", getMcByClsb("8003", czrkobj.getXb()));
				result = result.replace("{mz"+(j+1)+"}", getMcByClsb("8001", czrkobj.getMz()));
				result = result.replace("{csrq"+(j+1)+"}", czrkobj.getCsrq());
				result = result.replace("{gmsfhm"+(j+1)+"}", czrkobj.getGmsfhm());
				result = result.replace("{cym"+(j+1)+"}", cymTran);
				result = result.replace("{csd"+(j+1)+"}", getCsdInfo(czrkobj.getCsdssxq()));
				result = result.replace("{jg"+(j+1)+"}", getCsdInfo(czrkobj.getJgssxq()));
				result = result.replace("{whcd"+(j+1)+"}", getMcByClsb("8002", czrkobj.getWhcd()));
				result = result.replace("{hyzk"+(j+1)+"}", getMcByClsb("8007", czrkobj.getHyzk()));
				result = result.replace("{byzk"+(j+1)+"}", getMcByClsb("1046", czrkobj.getByzk()));
				result = result.replace("{sg"+(j+1)+"}", sgTran);
				result = result.replace("{zy"+(j+1)+"}", zyTran);
				result = result.replace("{fwcs"+(j+1)+"}", fwcsTran);
				result = result.replace("{hshdhyqlbz"+(j+1)+"}", hshdhyqlbzTran);
				result = result.replace("{hshdhyzxhk"+(j+1)+"}", hshdhyzxhkTran);
				result = result.replace("{qtzz"+(j+1)+"}", qtzzTran);
			}
			for(int j=0;j<6&&(i+j)>=familyMember.size();j++) {
				result = result.replace("{yhzgx"+(j+1)+"}", "");
				result = result.replace("{xm"+(j+1)+"}", "");
				result = result.replace("{xb"+(j+1)+"}", "");
				result = result.replace("{mz"+(j+1)+"}", "");
				result = result.replace("{csrq"+(j+1)+"}", "");
				result = result.replace("{gmsfhm"+(j+1)+"}", "");
				result = result.replace("{cym"+(j+1)+"}", "");
				result = result.replace("{csd"+(j+1)+"}", "");
				result = result.replace("{jg"+(j+1)+"}", "");
				result = result.replace("{whcd"+(j+1)+"}", "");
				result = result.replace("{hyzk"+(j+1)+"}", "");
				result = result.replace("{byzk"+(j+1)+"}", "");
				result = result.replace("{sg"+(j+1)+"}", "");
				result = result.replace("{zy"+(j+1)+"}", "");
				result = result.replace("{fwcs"+(j+1)+"}", "");
				result = result.replace("{hshdhyqlbz"+(j+1)+"}", "");
				result = result.replace("{hshdhyzxhk"+(j+1)+"}", "");
				result = result.replace("{qtzz"+(j+1)+"}", "");
			}
		}
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		result = result.replace("{dyrq}", transStringToDateformatter(sdf.format(date),1));
	}
	return result;
}
private String queryCzrkdjbyhybdyinfo(String id, String lodopId, String jsonStr, String index,int num) {
	String result = "continue";
	PoLODOP poldop = null;
	JSONObject json = JSONObject.parseObject(jsonStr);
	String hhid = json.getString("hhid");
	String pcs = json.getString("pcs");
	Long hhnbid = json.getLong("hhnbid");
	String hh = json.getString("hh");
	Integer limit = json.getInteger("limit");
	List familyMember =getFamilyMember(hhnbid,1);
	if(familyMember.size()>=limit||familyMember.size()==0) {
		return "next";
	}
	String strHQL1 = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	List lodopList =super.findAllByHQL(strHQL1);
	PoHJXX_HXXB pohxx = getHxx(pcs,hhnbid);
	PoHJXX_MLPXXXXB pomlp = getMlp(pcs,pohxx.getMlpnbid());
	if (lodopList != null && lodopList.size() ==1) {
		int tmp1 =(Integer.parseInt(index))*num;
		int tmp2 =(1+Integer.parseInt(index))*num;
		poldop = (PoLODOP) lodopList.get(0);
		if(familyMember.size()>tmp2) {
			result += poldop.getNr();
		}else if(familyMember.size()<=num||(familyMember.size()>tmp1&&familyMember.size()<=tmp2)) {
			result = poldop.getNr();
		}
		
		String hbTran ="";
		String dwjwhTran = "";
		String xzjdjlxTran = "";
		
		hbTran = getMcByClsb("1005", pohxx.getJhlb());
		PoXT_DWXXB dw =  super.get(PoXT_DWXXB.class, pomlp.getPcs());
		PoXT_JWHXXB jwh =  super.get(PoXT_JWHXXB.class, pomlp.getJcwh());
		PoXT_JLXXXB jlx =  super.get(PoXT_JLXXXB.class, pomlp.getJlx());
		
        if(dw!=null)
        	dwjwhTran=dwjwhTran+dw.getMc();
        if(jwh!=null)
        	dwjwhTran=dwjwhTran+jwh.getMc();
        if(jlx!=null)
        	xzjdjlxTran=xzjdjlxTran+jlx.getMc();
        if(pomlp.getMlph()!=null)
        	xzjdjlxTran=xzjdjlxTran+pomlp.getMlph();
        if(pomlp.getMlxz()!=null)
        	xzjdjlxTran=xzjdjlxTran+pomlp.getMlxz();
		result = result.replace("{hh}", pohxx.getHh()+"("+familyMember.size()+")");
		result = result.replace("{hb}", hbTran);
		result = result.replace("{dwjwh}", dwjwhTran);
		result = result.replace("{xzjdjlx}", xzjdjlxTran);
		for(int i=tmp1;i<tmp2;i++) {
			for(int j=0;j<num&&(i+j)<familyMember.size();j++) {
				PoHJXX_CZRKJBXXB czrkobj = ((PoHJXX_CZRKJBXXB)familyMember.get(i+j));
				String cymTran ="";
				String sgTran ="";
				String zyTran = "";
				String fwcsTran = "";
				String hshdhyqlbzTran = "";
				String hshdhyzxhkTran = "";
				String qtzzTran = "";
				if(czrkobj.getCym()!=null) {
					cymTran = czrkobj.getCym();
				}
				if(czrkobj.getSg()!=null) {
					sgTran = czrkobj.getSg();
				}
				if(czrkobj.getZy()!=null) {
					zyTran = czrkobj.getZy();
				}
				if(czrkobj.getFwcs()!=null) {
					fwcsTran = czrkobj.getFwcs();
				}
				hshdhyqlbzTran = getHylbzTemp1(czrkobj);
				if(czrkobj.getZxsj() != null) {
					hshdhyzxhkTran = hshdhyzxhkTran+czrkobj.getZxsj();
				}
				if(czrkobj.getQtzz()!=null) {
					qtzzTran = czrkobj.getQtzz();
				}
				hshdhyzxhkTran = getMcByClsb("1021", czrkobj.getSwzxlb());
				result = result.replace("{yhzgx"+(j+1)+"}", getMcByClsb("8006", czrkobj.getYhzgx()));
				result = result.replace("{xm"+(j+1)+"}", CommonUtil.isEmpty(czrkobj.getXm())?"":czrkobj.getXm());
				result = result.replace("{xb"+(j+1)+"}", getMcByClsb("8003", czrkobj.getXb()));
				result = result.replace("{mz"+(j+1)+"}", getMcByClsb("8001", czrkobj.getMz()));
				result = result.replace("{csrq"+(j+1)+"}", CommonUtil.isEmpty(czrkobj.getCsrq())?"":czrkobj.getCsrq());
				result = result.replace("{gmsfhm"+(j+1)+"}", CommonUtil.isEmpty(czrkobj.getGmsfhm())?"":czrkobj.getGmsfhm());
				result = result.replace("{cym"+(j+1)+"}", cymTran);
				result = result.replace("{csd"+(j+1)+"}", getCsdInfo(czrkobj.getCsdssxq()));
				result = result.replace("{jg"+(j+1)+"}", getCsdInfo(czrkobj.getJgssxq()));
				result = result.replace("{whcd"+(j+1)+"}", getMcByClsb("8002", czrkobj.getWhcd()));
				result = result.replace("{hyzk"+(j+1)+"}", getMcByClsb("8007", czrkobj.getHyzk()));
				result = result.replace("{byzk"+(j+1)+"}", getMcByClsb("1046", czrkobj.getByzk()));
				result = result.replace("{sg"+(j+1)+"}", sgTran);
				result = result.replace("{zy"+(j+1)+"}", zyTran);
				result = result.replace("{fwcs"+(j+1)+"}", fwcsTran);
				result = result.replace("{hshdhyqlbz"+(j+1)+"}", hshdhyqlbzTran);
				result = result.replace("{hshdhyzxhk"+(j+1)+"}", hshdhyzxhkTran);
				result = result.replace("{qtzz"+(j+1)+"}", qtzzTran);
			}
			for(int j=0;j<num&&(i+j)>=familyMember.size();j++) {
				result = result.replace("{yhzgx"+(j+1)+"}", "");
				result = result.replace("{xm"+(j+1)+"}", "");
				result = result.replace("{xb"+(j+1)+"}", "");
				result = result.replace("{mz"+(j+1)+"}", "");
				result = result.replace("{csrq"+(j+1)+"}", "");
				result = result.replace("{gmsfhm"+(j+1)+"}", "");
				result = result.replace("{cym"+(j+1)+"}", "");
				result = result.replace("{csd"+(j+1)+"}", "");
				result = result.replace("{jg"+(j+1)+"}", "");
				result = result.replace("{whcd"+(j+1)+"}", "");
				result = result.replace("{hyzk"+(j+1)+"}", "");
				result = result.replace("{byzk"+(j+1)+"}", "");
				result = result.replace("{sg"+(j+1)+"}", "");
				result = result.replace("{zy"+(j+1)+"}", "");
				result = result.replace("{fwcs"+(j+1)+"}", "");
				result = result.replace("{hshdhyqlbz"+(j+1)+"}", "");
				result = result.replace("{hshdhyzxhk"+(j+1)+"}", "");
				result = result.replace("{qtzz"+(j+1)+"}", "");
			}
		}
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		result = result.replace("{dyrq}", transStringToDateformatter(sdf.format(date),1));
	}
	return result;
}
private PoHJXX_MLPXXXXB getMlp(String pcs, Long long1) {
	String sql1 = " from "+PoHJXX_MLPXXXXB.class.getName() +" where pcs='"+pcs+"' and mlpnbid='"+long1+"'";
	List Mlpxxb = super.findAllByHQL(sql1);
	if(Mlpxxb.size()>0) {
		return (PoHJXX_MLPXXXXB) Mlpxxb.get(0);
	}
	return null;
}
private PoHJXX_HXXB getHxx(String pcs, Long hhnbid) {
	//String sql1 = " from "+PoHJXX_MLPXXXXB.class.getName() +" where pcs='"+pcs+"'";
	//List Mlpxxb = super.findAllByHQL(sql1);
	String sql2 = " from "+PoHJXX_HXXB.class.getName() +" where hhnbid='"+hhnbid+"' and jlbz = '1' and cxbz = '0' ";
	List hxxList =super.findAllByHQL(sql2);
	if(hxxList.size()>0) {
		return (PoHJXX_HXXB) hxxList.get(0);
	}else {
		return null;
	}
	
}
public String queryRkxxinfo(String lodopId, String id) {

	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//根据传入ryid获得表中数据
	String StrHqlById="";
	PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb =null;
	//VoHJXX_CZRKJBXXB    PoHJXX_CZRKJBXXB
	if(!id.isEmpty()) {
		StrHqlById ="from "+PoHJXX_CZRKJBXXB.class.getName()+" where  rynbid='"+id+"'";
		List hjxx_czrkjbxxbList = super.findAllByHQL(StrHqlById);
		if(hjxx_czrkjbxxbList.size()==0||hjxx_czrkjbxxbList.size()>1) {
			//返回前台，当前人数据库中未能查询到合适数据
			return "当前人数据库中未能查询到合适数据";
		}else {
			vohjxx_czrkjbxxb=(PoHJXX_CZRKJBXXB) hjxx_czrkjbxxbList.get(0);
			//查询套打表中数据
			String strHQL ="";
			String strHxxhql="";
			PoLODOP poldop = null;
			PoHJXX_HXXB pohjxx_hxxb =null;
			String result = "";
			String hlxTran ="";//户类型转换后的值
			String hzxmTran ="";//户主姓名
			String yhzgxTran="";//与户主关系
			String xbTran="";//性别
			String hyzkTran ="";// 婚姻状况
			String mzTran="";//名族  
			String whcdTran="";//文化程度
			String byzkTran="";//兵役状况
			String xxTran="";//血型
			String csrqTran="";//出生日期
			String csdssxqTran="";//出生地
			String cszqfrqTran ="";//公民出生证签发日期
			String jgssxqTran ="";//籍贯
			String yxqxqsrqTran ="";//居民身份证签发日期
			String hsqlTran = "";//
			String addressTran="";
			String hyqlTran ="";
			String hssxqqlTran ="";
			String hslbzTran="";
			String hylbzTran="";
			String hsssqlbzTran="";
			String qcrqTran = "";
			String qczxlbTran = "";
			String djrqTran = "";
			String hbTran="";
			String pcsTran="";
			String ssqxTran="";
			String xzjdTran="";
			String jhryjhgxTran="";
			String jhrejhgxTran="";
			String jlxTran="";
			String jwhTran="";
			strHQL = "from " + PoLODOP.class.getName() + "" +
					" where lodopId = '"+lodopId+"' and zxbz = '0' ";
			strHxxhql="from "+PoHJXX_HXXB.class.getName()+" where hhnbid ='"+vohjxx_czrkjbxxb.getHhnbid()+"'";
			
			
			 List lodopList =super.findAllByHQL(strHQL);
			 List pohjxx_hxxbList=super.findAllByHQL(strHxxhql);
		     if (lodopList != null && lodopList.size() ==1&&pohjxx_hxxbList != null && pohjxx_hxxbList.size() ==1) {
		    	 poldop = (PoLODOP) lodopList.get(0);
		    	 pohjxx_hxxb = (PoHJXX_HXXB) pohjxx_hxxbList.get(0);
		    	 hlxTran=getMcByClsb("1007", pohjxx_hxxb.getHlx());
		    	 hzxmTran =getHzxm(vohjxx_czrkjbxxb);
		    	 yhzgxTran =getMcByClsb("8006", vohjxx_czrkjbxxb.getYhzgx());
		    	 xbTran=getMcByClsb("8003", vohjxx_czrkjbxxb.getXb());
		    	 hyzkTran=getMcByClsb("8007", vohjxx_czrkjbxxb.getHyzk());
		    	 mzTran=getMcByClsb("8001", vohjxx_czrkjbxxb.getMz());
		    	 whcdTran =getMcByClsb("8002", vohjxx_czrkjbxxb.getWhcd());
		    	 byzkTran =getMcByClsb("1046", vohjxx_czrkjbxxb.getByzk());
		    	 xxTran=getMcByClsb("1044", vohjxx_czrkjbxxb.getXx());
		    	 csdssxqTran = getCsdInfo(vohjxx_czrkjbxxb.getCsdssxq());
		    	 jgssxqTran= getCsdInfo(vohjxx_czrkjbxxb.getJgssxq());
		    	 //cslb 1005    户别        8003 性别       8001   民族     8006  与户主关系     8007  婚姻状况  8002 文化程度   1046 兵役状况     1044血型
		    	 //地址获取
		    	 addressTran = getAddressByQhdm(vohjxx_czrkjbxxb);
		    	 String mlph=vohjxx_czrkjbxxb.getMlph();
		    	 hyqlTran = getMcByClsb("1056", vohjxx_czrkjbxxb.getHyql());
		    	 hssxqqlTran = getCsdInfo(vohjxx_czrkjbxxb.getHssxqql());
		    	csrqTran=transStringToDateformatter(vohjxx_czrkjbxxb.getCsrq(),2);
		    	cszqfrqTran=transStringToDateformatter(vohjxx_czrkjbxxb.getCszqfrq(),1);
		    	yxqxqsrqTran = transStringToDateformatter(vohjxx_czrkjbxxb.getYxqxqsrq(),1);
		    	hsqlTran =transStringToDateformatter(vohjxx_czrkjbxxb.getHsql(),2);
		    	hslbzTran=transStringToDateformatter(vohjxx_czrkjbxxb.getHslbz(),2);
				hylbzTran=getMcByClsb("1056", vohjxx_czrkjbxxb.getHylbz());
				if(vohjxx_czrkjbxxb.getHxzql()!=null) {
					hsssqlbzTran= vohjxx_czrkjbxxb.getHxzql();
				}else {
					hsssqlbzTran="";
				}
				qcrqTran = transStringToDateformatter(vohjxx_czrkjbxxb.getQcrq(),2);
				qczxlbTran = getMcByClsb("1026", vohjxx_czrkjbxxb.getQczxlb());
				djrqTran = vohjxx_czrkjbxxb.getJssj();
				hbTran = getMcByClsb("1005", vohjxx_czrkjbxxb.getHb());
				pcsTran = getPcs(vohjxx_czrkjbxxb.getPcs());
				ssqxTran =getCsdInfo(vohjxx_czrkjbxxb.getSsxq());
				xzjdTran = getXzjdInfo(vohjxx_czrkjbxxb.getXzjd());
				jhryjhgxTran =getMcByClsb("8006", vohjxx_czrkjbxxb.getJhryjhgx());
				jhrejhgxTran =getMcByClsb("8006", vohjxx_czrkjbxxb.getJhrejhgx());
				jlxTran = getJlxInfo(vohjxx_czrkjbxxb.getJlx());
				jwhTran =getjwhInfo(vohjxx_czrkjbxxb.getJcwh());
		 		result += poldop.getNr();
	 			result = result.replace("{hlx}", hlxTran);
	 			result = result.replace("{hzxm}", hzxmTran);
	 			result = result.replace("{yhzgx}", yhzgxTran);
	 			result = result.replace("{xb}", xbTran);
	 			result = result.replace("{hyzk}", hyzkTran);
	 			result = result.replace("{mz}", mzTran);
	 			result = result.replace("{whcd}", whcdTran);
	 			result = result.replace("{byzk}", byzkTran);
	 			result = result.replace("{xx}", xxTran);
	 			result = result.replace("{csrq}", csrqTran);
	 			result = result.replace("{csdssxq}", csdssxqTran);
	 			result = result.replace("{cszqfrq}", cszqfrqTran);
	 			result = result.replace("{jg}", jgssxqTran);
	 			result = result.replace("{hsql}", hsqlTran);
	 			result = result.replace("{yxqxqsrq}", yxqxqsrqTran);
	 			result = result.replace("{address}", addressTran);
	 			result = result.replace("{hyql}", hyqlTran);
	 			result = result.replace("{hssxqql}", hssxqqlTran);
	 			result = result.replace("{hslbz}", hslbzTran);
	 			result = result.replace("{hylbz}", hylbzTran);
	 			result = result.replace("{hsssqlbz}", hsssqlbzTran);
	 			result = result.replace("{qcrq}", qcrqTran);
	 			result = result.replace("{qczxlb}", qczxlbTran);
	 			result = result.replace("{hb}", hbTran);
	 			result = result.replace("{pcs}", pcsTran);
	 			result = result.replace("{jhryjhgx}", jhryjhgxTran);
	 			result = result.replace("{jhrejhgx}", jhrejhgxTran);
	 			result = result.replace("{ssxq}", ssqxTran);
	 			result = result.replace("{xzjd}", xzjdTran);
	 			result = result.replace("{jlx}", jlxTran);
	 			result = result.replace("{jwh}", jwhTran);
	 			result = result.replace("{zp}", getZp(vohjxx_czrkjbxxb));
	 			Date date = new Date();
				result = result.replace("{yyyy}",  String.format("%tY", date));
				result = result.replace("{mm}", String.format("%tm", date));
				result = result.replace("{dd}",String.format("%td", date));
				result = result.replace("{dlm}", this.getUserInfo().getYhxm());
				result = generrateFromObj(vohjxx_czrkjbxxb, result);
	 			hqsjDao.saveHjxxDyxxb(vohjxx_czrkjbxxb,lodopId,null,String.format("%tY", new Date()));
		 	
		     }
		     return result;
		}		
	}else {
		return "查询用户，表中不存在";
	}

}
public String queryHjblspbinfo(String lodopId, String id,String jsonStr) {

	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//根据传入ryid获得表中数据
	String StrHqlById="";
	PoHJYW_HJBLXXB vohjyw_hjblxxb =new PoHJYW_HJBLXXB();
	PoHJSP_HJBLSPSQB pohjsp_hjblspsqb = new PoHJSP_HJBLSPSQB();
	JSONObject json = JSONObject.parseObject(jsonStr);
	String hjblid = json.getString("hjblid");
	String spywid = json.getString("spywid");	
	List hjyw_hjblList = new ArrayList<>();
	if(CommonUtil.isNotEmpty(hjblid)&&CommonUtil.isEmpty(spywid)) {
		//增加标志位 1,2，用来区分审批查询和业务办理完
		if(id.equals("1")){
			StrHqlById ="from "+PoHJYW_HJBLXXB.class.getName()+" where  hjblid='"+json.getString("hjblid")+"'";
		}else if(id.equals("2")){
			StrHqlById ="from "+PoHJYW_HJBLXXB.class.getName()+" where  hjywid='"+json.getString("hjywid")+"' and rynbid='"+json.getString("rynbid")+"'";
		}
		hjyw_hjblList = super.findAllByHQL(StrHqlById);
	}else if(CommonUtil.isEmpty(hjblid)&&CommonUtil.isNotEmpty(spywid)) {
		pohjsp_hjblspsqb = super.get(PoHJSP_HJBLSPSQB.class, json.getLong("spywid"));
	}
	if(pohjsp_hjblspsqb != null) {
		try {
			BeanUtils.copyProperties(vohjyw_hjblxxb, pohjsp_hjblspsqb);
			vohjyw_hjblxxb.setSsxq(pohjsp_hjblspsqb.getSsssxq());
			vohjyw_hjblxxb.setXzjd(pohjsp_hjblspsqb.getSsxzjd());
			vohjyw_hjblxxb.setJlx(pohjsp_hjblspsqb.getSsjlx());
			vohjyw_hjblxxb.setMlph(pohjsp_hjblspsqb.getSsmlph());
			vohjyw_hjblxxb.setMlxz(pohjsp_hjblspsqb.getSsxz());
			vohjyw_hjblxxb.setHjbllb(pohjsp_hjblspsqb.getBlyy());
		 } catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		 }
	}else {
		vohjyw_hjblxxb=(PoHJYW_HJBLXXB) hjyw_hjblList.get(0);
	}
	
	//查询套打表中数据
	String strHQL ="";
	PoLODOP poldop = null;
	String result = "";
	String xbTran="";//性别
	String mzTran="";//名族  
	String csrqTran="";//出生日期
	String addressTran="";
	String hjbllbTran = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	
	 List lodopList =super.findAllByHQL(strHQL);
     if (lodopList != null && lodopList.size() ==1) {
    	 poldop = (PoLODOP) lodopList.get(0);
    	 xbTran=getMcByClsb("8003", vohjyw_hjblxxb.getXb());
    	 mzTran=getMcByClsb("8001", vohjyw_hjblxxb.getMz());
    	 hjbllbTran=getMcByClsb("1024", vohjyw_hjblxxb.getHjbllb());
    	 //cslb 1005    户别        8003 性别       8001   民族     8006  与户主关系     8007  婚姻状况  8002 文化程度   1046 兵役状况     1044血型
    	 //地址获取
    	 PoHJXX_CZRKJBXXB  poczrk = new PoHJXX_CZRKJBXXB();
    	 poczrk.setSsxq(vohjyw_hjblxxb.getSsxq());
    	 poczrk.setJlx(vohjyw_hjblxxb.getJlx());
    	 poczrk.setXzjd(vohjyw_hjblxxb.getXzjd());
    	 poczrk.setMlph(vohjyw_hjblxxb.getMlph());
    	 poczrk.setMlxz(vohjyw_hjblxxb.getMlxz());
    	 addressTran = getAddressByQhdm(poczrk);
    	csrqTran=vohjyw_hjblxxb.getCsrq();
 		result += poldop.getNr();
 			result = result.replace("{xb}", xbTran);
 			result = result.replace("{mz}", mzTran);
 			//result = result.replace("{csrq}", csrqTran);
 			result = result.replace("{zz}", addressTran);
 			result = result.replace("{dlm}", CommonUtil.isEmpty(this.getUserInfo().getYhxm())?"":this.getUserInfo().getYhxm());
 			result = result.replace("{blyy}", hjbllbTran);
 			Class c1= vohjyw_hjblxxb.getClass();
 			Field[] fields1  = c1.getDeclaredFields();
 			for(Field f : fields1) {
 				// 获取原来的访问控制权限 
 				boolean accessFlag = f.isAccessible(); 
 				// 修改访问控制权限 
 				f.setAccessible(true); 
 				// 获取在对象f中属性fields[i]对应的对象中的变量  
 				Object o;
 				try {
					o = f.get(vohjyw_hjblxxb);
	 				String tempName =f.getName();
	 				if(result.contains(tempName)&&(o!=null)) {
	 					
	 					try {
	 						System.out.println(tempName+":"+o.toString());
							result = result.replace("{"+tempName+"}", o.toString());
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						}
	 				}else {
	 					result = result.replace("{"+tempName+"}","");
	 				}
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} 
 				// 恢复访问控制权限  
 		        f.setAccessible(accessFlag); 

 			}
 			PoHJXX_CZRKJBXXB vohjxx_czrk = new PoHJXX_CZRKJBXXB();
 			vohjxx_czrk.setRyid(vohjyw_hjblxxb.getRyid());
 			vohjxx_czrk.setRynbid(vohjyw_hjblxxb.getRynbid());
 			vohjxx_czrk.setGmsfhm(vohjyw_hjblxxb.getGmsfhm());
 			vohjxx_czrk.setXm(vohjyw_hjblxxb.getXm());
 			vohjxx_czrk.setPcs(vohjyw_hjblxxb.getPcs());
 			vohjxx_czrk.setSsxq(vohjyw_hjblxxb.getSsxq());
 			vohjxx_czrk.setMlpnbid(vohjyw_hjblxxb.getMlpnbid());
 			vohjxx_czrk.setJlx(vohjyw_hjblxxb.getJlx());
 			vohjxx_czrk.setMlph(vohjyw_hjblxxb.getMlph());
 			vohjxx_czrk.setMlxz(vohjyw_hjblxxb.getMlxz());
 			vohjxx_czrk.setZrq(vohjyw_hjblxxb.getZrq());
 			vohjxx_czrk.setXzjd(vohjyw_hjblxxb.getXzjd());
 			vohjxx_czrk.setJcwh(vohjyw_hjblxxb.getJcwh());
 			hqsjDao.saveHjxxDyxxb(vohjxx_czrk,lodopId,null,String.format("%tY", new Date()));
 	
     }
     return result;
	

}
public String queryHjscspbinfo(String lodopId, String id) {
	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//根据传入ryid获得表中数据
	String StrHqlById="";
	PoHJSP_HJSCSPSQB vohjyw_hjscxxb =null;
	//VoHJXX_CZRKJBXXB    PoHJXX_CZRKJBXXB
	if(!id.isEmpty()) {
		StrHqlById ="from "+PoHJSP_HJSCSPSQB.class.getName()+" where  spywid='"+id+"'";
		List hjyw_hjscList = super.findAllByHQL(StrHqlById);
		if(hjyw_hjscList.size()==0||hjyw_hjscList.size()>1) {
			//返回前台，当前人数据库中未能查询到合适数据
			return "当前人数据库中未能查询到合适数据";
		}else {
			vohjyw_hjscxxb=(PoHJSP_HJSCSPSQB) hjyw_hjscList.get(0);
			//查询套打表中数据
			String strHQL ="";
			String strHQLByBscryid ="";
			PoLODOP poldop = null;
			String result = "";
			String xbTran="";//性别
			String mzTran="";//名族  
			String csrqTran="";//出生日期
			String addressTran="";
			String scyyTran = "";
			String strHQLBy ="";
			PoHJXX_CZRKJBXXB voczrk =null;
			strHQLByBscryid = "from " + PoHJXX_CZRKJBXXB.class.getName() + "" +
					" where ryid = '"+vohjyw_hjscxxb.getBscryid()+"' and jlbz = '1' and cxbz= '0' ";
			List czrkList =super.findAllByHQL(strHQLByBscryid);
			voczrk = (PoHJXX_CZRKJBXXB) czrkList.get(0);
			strHQL = "from " + PoLODOP.class.getName() + "" +
					" where lodopId = '"+lodopId+"' and zxbz = '0' ";
			
			
			 List lodopList =super.findAllByHQL(strHQL);
		     if (lodopList != null && lodopList.size() ==1) {
		    	 poldop = (PoLODOP) lodopList.get(0);
		    	 xbTran=getMcByClsb("8003", voczrk.getXb());
		    	 mzTran=getMcByClsb("8001", voczrk.getMz());
		    	 scyyTran=getMcByClsb("1025", vohjyw_hjscxxb.getCsyy());
		    	 //cslb 1005    户别        8003 性别       8001   民族     8006  与户主关系     8007  婚姻状况  8002 文化程度   1046 兵役状况     1044血型
		    	 //地址获取
		    	 PoHJXX_CZRKJBXXB  poczrk = new PoHJXX_CZRKJBXXB();
		    	 poczrk.setSsxq(voczrk.getSsxq());
		    	 poczrk.setJlx(voczrk.getJlx());
		    	 poczrk.setXzjd(voczrk.getXzjd());
		    	 poczrk.setMlph(voczrk.getMlph());
		    	 poczrk.setMlxz(voczrk.getMlxz());
		    	 addressTran = getAddressByQhdm(poczrk);
		    	csrqTran=voczrk.getCsrq();
		 		result += poldop.getNr();
		 			result = result.replace("{xb}", xbTran);
		 			result = result.replace("{mz}", mzTran);
		 			//result = result.replace("{csrq}", csrqTran);
		 			result = result.replace("{zz}", addressTran);
		 			result = result.replace("{dlm}", CommonUtil.isEmpty(this.getUserInfo().getYhxm())?"":this.getUserInfo().getYhxm());
		 			result = result.replace("{scyy}", scyyTran);
		 			Class c1= voczrk.getClass();
		 			Field[] fields1  = c1.getDeclaredFields();
		 			for(Field f : fields1) {
		 				// 获取原来的访问控制权限 
		 				boolean accessFlag = f.isAccessible(); 
		 				// 修改访问控制权限 
		 				f.setAccessible(true); 
		 				// 获取在对象f中属性fields[i]对应的对象中的变量  
		 				Object o;
		 				try {
							o = f.get(voczrk);
			 				String tempName =f.getName();
			 				if(result.contains(tempName)&&(o!=null)) {
			 					
			 					try {
			 						System.out.println(tempName+":"+o.toString());
									result = result.replace("{"+tempName+"}", o.toString());
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								}
			 				}else {
			 					result = result.replace("{"+tempName+"}","");
			 				}
						} catch (IllegalArgumentException e1) {
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							e1.printStackTrace();
						} 
		 				// 恢复访问控制权限  
		 		        f.setAccessible(accessFlag); 

		 			}
		 			hqsjDao.saveHjxxDyxxb(voczrk,lodopId,null,String.format("%tY", new Date()));
		 	
		     }
		     return result;
		}		
	}else {
		return "查询用户，表中不存在";
	}

}
public String querySwzxzminfo(String lodopId, String id,String jsonStr) {

	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//根据传入ryid获得表中数据
	String StrHqlById="";
	List<PoHJYW_SWZXXXB> hjyw_swzxList = new  ArrayList<PoHJYW_SWZXXXB>();
	PoHJYW_SWZXXXB vohjyw_swzxxxb =null;
	JSONObject json = JSONObject.parseObject(jsonStr);
	//VoHJXX_CZRKJBXXB    PoHJXX_CZRKJBXXB
	if(!CommonUtil.isBlank(id)) {
		StrHqlById ="from "+PoHJYW_SWZXXXB.class.getName()+" where  swzxid='"+id+"'";
		
	}else {
		StrHqlById ="from "+PoHJYW_SWZXXXB.class.getName()+" where  rynbid='"+json.getString("rynbid")+"' order by slsj desc ";
	}
	hjyw_swzxList = (List<PoHJYW_SWZXXXB>) super.findAllByHQL(StrHqlById);
	if(hjyw_swzxList.size()==0) {
		//返回前台，当前人数据库中未能查询到合适数据
		return "当前人数据库中未能查询到合适数据";
	}else {
		vohjyw_swzxxxb=(PoHJYW_SWZXXXB) hjyw_swzxList.get(0);
		//查询套打表中数据
		String strHQL ="";
		PoLODOP poldop = null;
		String result = "";
		String swzxlbTran = "";
		String pcsTran ="";
		String zxrqTran ="";
		strHQL = "from " + PoLODOP.class.getName() + "" +
				" where lodopId = '"+lodopId+"' and zxbz = '0' ";
		 List lodopList =super.findAllByHQL(strHQL);
	     if (lodopList != null && lodopList.size() ==1) {
	    	 poldop = (PoLODOP) lodopList.get(0);
	    	 swzxlbTran=getMcByClsb("1021", vohjyw_swzxxxb.getSwzxlb());
	    	 if(vohjyw_swzxxxb.getSlsj()!=null&&vohjyw_swzxxxb.getSlsj().length()>=8) {
	    		 zxrqTran=vohjyw_swzxxxb.getSlsj().substring(0, 8);
	    	 }else {
	    		 zxrqTran="";
	    	 }
	    	 //cslb 1005    户别        8003 性别       8001   民族     8006  与户主关系     8007  婚姻状况  8002 文化程度   1046 兵役状况     1044血型
	    	 //地址获取
	    	pcsTran = getPcs(vohjyw_swzxxxb.getPcs());
	 		result += poldop.getNr();
 			result = result.replace("{swzxlb}", swzxlbTran);
 			
 			result = result.replace("{zxrq}", transStringToDateformatter(zxrqTran,3));
 			result = result.replace("{pcs}", pcsTran);
 			Date date = new Date();
	 		result = result.replace("{yyyy}",  String.format("%tY", date));
			result = result.replace("{mm}", String.format("%tm", date));
			result = result.replace("{dd}",String.format("%td", date));
 			Class c1= vohjyw_swzxxxb.getClass();
 			Field[] fields1  = c1.getDeclaredFields();
 			for(Field f : fields1) {
 				// 获取原来的访问控制权限 
 				boolean accessFlag = f.isAccessible(); 
 				// 修改访问控制权限 
 				f.setAccessible(true); 
 				// 获取在对象f中属性fields[i]对应的对象中的变量  
 				Object o;
 				try {
					o = f.get(vohjyw_swzxxxb);
	 				String tempName =f.getName();
	 				String tempFirst =upperCase(tempName);
	 				if(result.contains(tempName)&&(o!=null)) {
	 					
	 					try {
	 						System.out.println(tempName+":"+o.toString());
							result = result.replace("{"+tempName+"}", o.toString());
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						}
	 				}else {
	 					result = result.replace("{"+tempName+"}","");
	 				}
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} 
 				// 恢复访问控制权限  
 		        f.setAccessible(accessFlag); 

 			}
 			PoHJXX_CZRKJBXXB vohjxx_czrk = new PoHJXX_CZRKJBXXB();
 			vohjxx_czrk.setRyid(vohjyw_swzxxxb.getRyid());
 			vohjxx_czrk.setRynbid(vohjyw_swzxxxb.getRynbid());
 			vohjxx_czrk.setGmsfhm(vohjyw_swzxxxb.getGmsfhm());
 			vohjxx_czrk.setXm(vohjyw_swzxxxb.getXm());
 			vohjxx_czrk.setPcs(vohjyw_swzxxxb.getPcs());
 			vohjxx_czrk.setSsxq(vohjyw_swzxxxb.getSsxq());
 			vohjxx_czrk.setMlpnbid(vohjyw_swzxxxb.getMlpnbid());
 			vohjxx_czrk.setJlx(vohjyw_swzxxxb.getJlx());
 			vohjxx_czrk.setMlph(vohjyw_swzxxxb.getMlph());
 			vohjxx_czrk.setMlxz(vohjyw_swzxxxb.getMlxz());
 			vohjxx_czrk.setZrq(vohjyw_swzxxxb.getZrq());
 			vohjxx_czrk.setXzjd(vohjyw_swzxxxb.getXzjd());
 			vohjxx_czrk.setJcwh(vohjyw_swzxxxb.getJcwh());
 			hqsjDao.saveHjxxDyxxb(vohjxx_czrk,lodopId,null,String.format("%tY", new Date()));
 			
 			if(CommonUtil.isNotEmpty(json.getString("ywlb"))&&json.getString("ywlb").equals("14")&&CommonUtil.isNotEmpty(json.getString("hzywid"))) {
 				 updateHz_zj_sb(json.getString("hzywid"));
 			}
	     }
	     return result;
	}	
}
public String queryHkxzzminfo(String id, String lodopId, String jsonStr,String index) {

	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//根据传入ryid获得表中数据
	String StrHqlById="";
	PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb =null;
	//VoHJXX_CZRKJBXXB    PoHJXX_CZRKJBXXB
	if(!id.isEmpty()) {
		StrHqlById ="from "+PoHJXX_CZRKJBXXB.class.getName()+" where  rynbid='"+id+"'";
		List hjxx_czrkjbxxbList = super.findAllByHQL(StrHqlById);
		if(hjxx_czrkjbxxbList.size()==0||hjxx_czrkjbxxbList.size()>1) {
			//返回前台，当前人数据库中未能查询到合适数据
			return "当前人数据库中未能查询到合适数据";
		}else {
			vohjxx_czrkjbxxb=(PoHJXX_CZRKJBXXB) hjxx_czrkjbxxbList.get(0);
						
			//查询套打表中数据
			String strHQL ="";
			String strHxxhql="";
			
			PoLODOP poldop = null;
			
			PoHJXX_HXXB pohjxx_hxxb =null;
			String result = "";
			String hlxTran ="";//户类型转换后的值
			String xbTran="";//性别
			String pcsTran="";
			String yhkxzmcTran="";
			String yhkxzsjTran="";
			strHQL = "from " + PoLODOP.class.getName() + "" +
					" where lodopId = '"+lodopId+"' and zxbz = '0' ";
			strHxxhql="from "+PoHJXX_HXXB.class.getName()+" where hhnbid ='"+vohjxx_czrkjbxxb.getHhnbid()+"'";
			
			 List lodopList =super.findAllByHQL(strHQL);
			 List pohjxx_hxxbList=super.findAllByHQL(strHxxhql);
		     if (lodopList != null && lodopList.size() ==1&&pohjxx_hxxbList != null && pohjxx_hxxbList.size() ==1) {
		    	 poldop = (PoLODOP) lodopList.get(0);
		    	 pohjxx_hxxb = (PoHJXX_HXXB) pohjxx_hxxbList.get(0);
		    	 hlxTran=getMcByClsb("1005", vohjxx_czrkjbxxb.getHb());
		    	 xbTran=getMcByClsb("8003", vohjxx_czrkjbxxb.getXb());
		    	 //cslb 1005    户别        8003 性别       8001   民族     8006  与户主关系     8007  婚姻状况  8002 文化程度   1046 兵役状况     1044血型
		    	 //地址获取
		    	pcsTran = getPcs(vohjxx_czrkjbxxb.getPcs());
		    	if(vohjxx_czrkjbxxb.getYhkxzmc()!=null) {
		    		yhkxzmcTran = vohjxx_czrkjbxxb.getYhkxzmc();
		    	}else {
		    		yhkxzmcTran ="";
		    	}
		    	if(vohjxx_czrkjbxxb.getYhkxzsj()!=null) {
		    		yhkxzsjTran = transStringToDateformatter(vohjxx_czrkjbxxb.getYhkxzsj(),3);
		    	}
		    	
		 		result += poldop.getNr();
		 			result = result.replace("{hlx}", hlxTran);
		 			result = result.replace("{xb}", xbTran);
		 			result = result.replace("{yhkxzmc}", yhkxzmcTran);
		 			result = result.replace("{yhkxzsj}", yhkxzsjTran);
		 			result = result.replace("{pcs}", pcsTran);
		 			Date date = new Date();
			 		result = result.replace("{yyyymmdd}",(new SimpleDateFormat("yyyy年MM月dd日"/*你想要的格式属*/)).format(date));
		 			result = generrateFromObj(vohjxx_czrkjbxxb, result);
		 			hqsjDao.saveHjxxDyxxb(vohjxx_czrkjbxxb,lodopId,null,String.format("%tY", new Date()));
		 		//}
		 			if(lodopId.equals("hkxzzm")&&CommonUtil.isNotEmpty(index)&&!index.equals("null")) {
		 				JSONObject hzywjo = JSONObject.parseObject(index);
		 				String hzywid = hzywjo.getString("hzywid");
		 				String ywlb = hzywjo.getString("ywlb");
		 				if(CommonUtil.isNotEmpty(ywlb)&&ywlb.equals("25")&&CommonUtil.isNotEmpty(hzywid)) {
		 					 updateHz_zj_sb(hzywid);
		 				}
		 			}
		     }
		     return result;
		}		
	}else {
		return "查询用户，表中不存在";
	}

}
public String queryQyzdyinfo(String lodopId, String jsonStr,String index,String baseUrl) {
	VoQyzbhhtxx[] voQyzbhhtxxs1 = new VoQyzbhhtxx[1];
	VoHjdyHqxx[] voHjdyHqxx1 = new VoHjdyHqxx[1];
	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	String hql2="";
	String hql3="";
	String strHQL="";
	PoHJXX_CZRKJBXXB hjxx_czrkjbxxb=null;
	PoHJYW_QCZXXXB hjyw_qczxxxb =null;
	String addressTran="";
	String cymTran="";
	String csdTran="";
	String jgxzTran="";
	String zy1Tran ="";
	String csrqTran="";
	String qwzzTran="";
	String pcsTran="";
	String jwhTran="";
	String hbTran="";
	Date date = new Date();
	Date date1 = new Date();
	SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
	Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DAY_OF_MONTH, +30);
    date1 = calendar.getTime();
	PoLODOP poldop = new PoLODOP();
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+"qyz"+"' and zxbz = '0' ";
	//strHxxhql="from "+PoHJXX_HXXB.class.getName()+" where hhnbid ='"+vohjxx_czrkjbxxb.getHhnbid()+"'";
	List lodopList =super.findAllByHQL(strHQL);
	String result = "";
	if (lodopList != null && lodopList.size() ==1) {
		poldop = (PoLODOP) lodopList.get(0);
		result += poldop.getNr();
		String yznf = "";
		if(lodopId.equals("qyz1")) {
			 JSONObject json1 = JSONObject.parseObject(jsonStr);
			 String rynbid = json1.getString("rynbid");
			 String hjywid = json1.getString("hjywid");
			//更新迁出注销信息表的sbrjtgx字段 add by zjm 20191203
			 Long qczxid = json1.getLong("qczxid");
			 updateHjywQczxxbSbrjtgx(qczxid,"01"); 
			
			 yznf = json1.getString("yznf");
			 String qyzbh = index;

		     voQyzbhhtxxs1[0] =  new VoQyzbhhtxx();
		     voQyzbhhtxxs1[0].setDysx(1l);
		     voQyzbhhtxxs1[0].setHjywid(Long.parseLong(hjywid));
		     voQyzbhhtxxs1[0].setQfrq(sdf.format(date));
		     voQyzbhhtxxs1[0].setQyzbh(qyzbh);
		     voQyzbhhtxxs1[0].setRynbid(Long.parseLong(rynbid));
		     voQyzbhhtxxs1[0].setYczrgx("01");//一人一证时  持证人为本人
			 voQyzbhhtxxs1[0].setYxqxjzrq(sdf.format(date1));
			 voQyzbhhtxxs1[0].setYznf(yznf);
			 hql3="from "+PoHJYW_QCZXXXB.class.getName()+" where  rynbid='"+rynbid+"' and hjywid = '"+hjywid+"'";
				List hjyw_qczxxbList = super.findAllByHQL(hql3);
				if(hjyw_qczxxbList.size()==1) {
					hjyw_qczxxxb = (PoHJYW_QCZXXXB) hjyw_qczxxbList.get(0);
					if(hjyw_qczxxxb.getYwlx().equals("2")) {
						result = result.replace("{ywlx}","★全户迁移★");
					}else {
						result = result.replace("{ywlx}", "");
					}
					
					hbTran = getMcByClsb("1005", hjyw_qczxxxb.getHb());
					if(hjyw_qczxxxb.getQwdssxq()!=null) {
						qwzzTran=getCsdInfo(hjyw_qczxxxb.getQwdssxq());
						if(hjyw_qczxxxb.getQwdxz()!=null) {
							qwzzTran = qwzzTran+hjyw_qczxxxb.getQwdxz();
						}
					}else {
						qwzzTran="";
					}
				}
				hql2="from "+PoHJXX_CZRKJBXXB.class.getName()+" where  rynbid='"+rynbid+"'";
				List hjxx_czrkjbxxbList = super.findAllByHQL(hql2);
				hjxx_czrkjbxxb = (PoHJXX_CZRKJBXXB) hjxx_czrkjbxxbList.get(0);
				addressTran=getAddressByQhdm(hjxx_czrkjbxxb);
		    	 pcsTran = getPcs(hjxx_czrkjbxxb.getPcs());
		    	 if(hjxx_czrkjbxxb.getJcwh()!=null) {
		    		 jwhTran =getJcwh(hjxx_czrkjbxxb.getJcwh());
		    		 int tempCxfldm =Integer.parseInt(getjwhCxfldm(hjxx_czrkjbxxb.getJcwh()));
		    		 if(tempCxfldm>=100&&tempCxfldm<200) {
		    			 addressTran=addressTran+"("+"城镇"+")";
		    		 }else if(tempCxfldm>=200&&tempCxfldm<300){
		    			 addressTran=addressTran+"("+"乡村"+")";
		    		 }
		    	 }
		    	 zy1Tran = hjxx_czrkjbxxb.getZy();
				 if(zy1Tran!=null) {
		    		 result = result.replace("{zy1}",zy1Tran);
		    	 }else {
		    		 result = result.replace("{zy1}","");
		    	 }
				 result = result.replace("{hb}",hbTran);
				 result = result.replace("{xm1}",CommonUtil.isEmpty(hjxx_czrkjbxxb.getXm())?"":hjxx_czrkjbxxb.getXm());
		    	 result = result.replace("{xb1}", getMcByClsb("8003", hjxx_czrkjbxxb.getXb()));
		    	// modify by zjm 20190625 迁移证备注显示
				//迁移证的备注信息取值逻辑：1、如果bz有值，就取bz；2、如果没值，取hjxx_czrk表里的xm+zjlb+qfjg+yxqxqsrq这4个字段的拼接；3、如果zjlb字段是空的就不要自动拼接了，如果其他字段空的对应内容就空着；4、如果zjlb和bz都是空的，页面上就显示为空即可
				if(CommonUtil.isNotEmpty(hjyw_qczxxxb.getBz())) {
					result = result.replace("{bz}",hjyw_qczxxxb.getBz());
				}else {
					if(CommonUtil.isNotEmpty(hjxx_czrkjbxxb.getZjlb())) {
						String zjlbTran = getMcByClsb("5012", hjxx_czrkjbxxb.getZjlb());
						String qfjgTran = CommonUtil.isNotEmpty(hjxx_czrkjbxxb.getQfjg())?hjxx_czrkjbxxb.getQfjg():"";
						String bzTran = "("+hjxx_czrkjbxxb.getXm()+"已办理"+zjlbTran+", 签发机关："+qfjgTran+", 签发日期："+hjxx_czrkjbxxb.getYxqxqsrq()+"。)";
						result = result.replace("{bz}",bzTran);
					}else {
						result = result.replace("{bz}","");
					}
				}
		    	 
				 result = result.replace("{gmsfhm1}",CommonUtil.isEmpty(hjxx_czrkjbxxb.getGmsfhm())?"":hjxx_czrkjbxxb.getGmsfhm());
				 result = result.replace("{yhzgx1}","持证人");
				 result = result.replace("{qyyy}",getMcByClsb("1019", hjxx_czrkjbxxb.getQczxlb()));
				 result = result.replace("{qyyy1}",getMcByClsb("1019", hjxx_czrkjbxxb.getQczxlb()));
				 result = result.replace("{pcs}",pcsTran);
				 result = result.replace("{jwh}",jwhTran);
				cymTran=hjxx_czrkjbxxb.getCym();
				if(cymTran!=null) {
					result = result.replace("{cym1}",cymTran);
				}else {
					result = result.replace("{cym1}","");
				}
				result = result.replace("{mz1}",getMcByClsb("8001", hjxx_czrkjbxxb.getMz()));
				csdTran=hjxx_czrkjbxxb.getCsdssxq();
				if(csdTran!=null) {
					result = result.replace("{csd1}",getCsdInfo(csdTran));
				}else {
					result = result.replace("{csd1}","");
				}
				jgxzTran=getCsdInfo(hjxx_czrkjbxxb.getJgssxq());
				if(jgxzTran!=null) {
					result = result.replace("{jgssxq1}",jgxzTran);
				}else {
					result = result.replace("{jgssxq1}","");
				} 
				result = result.replace("{gmsfhm1}",CommonUtil.isEmpty(hjxx_czrkjbxxb.getGmsfhm())?"":hjxx_czrkjbxxb.getGmsfhm());
				result = result.replace("{qyzbh}",index);
				result = result.replace("{yzz}",addressTran);
				result = result.replace("{qwzz}",qwzzTran);
				result = result.replace("{dlm}",CommonUtil.isEmpty(this.getUserInfo().getYhxm())?"":this.getUserInfo().getYhxm());
				csrqTran=hjxx_czrkjbxxb.getCsrq();
				if(csrqTran!=null&&csrqTran.length()>=8) {
					result = result.replace("{yyyy1}", hjxx_czrkjbxxb.getCsrq().substring(0, 4));
					result = result.replace("{mm1}", hjxx_czrkjbxxb.getCsrq().substring(4, 6));
					result = result.replace("{dd1}", hjxx_czrkjbxxb.getCsrq().substring(6, 8));
				}else {
					result = result.replace("{yyyy1}","");
					result = result.replace("{mm1}", "");
					result = result.replace("{dd1}", "");
				}
		 		result = result.replace("{yyyy5}",  String.format("%tY", date));
				result = result.replace("{mm5}", String.format("%tm", date));
				result = result.replace("{dd5}",String.format("%td", date));
		        result = result.replace("{yyyy6}",  String.format("%tY", date1));
				result = result.replace("{mm6}", String.format("%tm", date1));
				result = result.replace("{dd6}",String.format("%td", date1));
				result = result.replace("{hyzk1}",getMcByClsb("8007", hjxx_czrkjbxxb.getHyzk()));
				result = result.replace("{whcd1}",getMcByClsb("8002", hjxx_czrkjbxxb.getWhcd()));
				for(int i1=2;i1<=4;i1++) {
					result = result.replace("{xm"+i1+"}","");
					result = result.replace("{xb"+i1+"}","");
					result = result.replace("{gmsfhm"+i1+"}","");
					result = result.replace("{yhzgx"+i1+"}","以下空白");
					result = result.replace("{cym"+i1+"}","");
					result = result.replace("{mz"+i1+"}","");
					result = result.replace("{yyyy"+i1+"}","");
					result = result.replace("{mm"+i1+"}","");
					result = result.replace("{dd"+i1+"}","");
					result = result.replace("{csd"+i1+"}","");
					result = result.replace("{jgssxq"+i1+"}","");
					result = result.replace("{whcd"+i1+"}","");
					result = result.replace("{zy"+i1+"}","");
					result = result.replace("{hyzk"+i1+"}","");
					result = result.replace("{gmsfhm"+i1+"}","");
					result = result.replace("{qyyy"+i1+"}","");
				}
				result = generrateFromObj(hjxx_czrkjbxxb, result);
				PoHJSP_QYZXXB hjsp_qyzxxb = new PoHJSP_QYZXXB();
//				PoHJSP_QYZXXB hjsp_qyzxxb = new PoHJSP_QYZXXB();
//				hjsp_qyzxxb.setBz(bz);
//	 			Timestamp d = new Timestamp(System.currentTimeMillis()); 
//	 			hjsp_qyzxxb.setHhnbid(vohjxx_czrkjbxxb.getHhnbid());
//	 			hjsp_qyzxxb.setDyyh(this.getUserInfo().getYhxm());
//	 			hjsp_qyzxxb.setDysj(d);
//	 			hqsjDao.insertObject(hjsp_qyzxxb);
				//hqsjDao.saveHjxxDyxxb(hjxx_czrkjbxxb,lodopId,json1.getString("qyzbh"),json1.getString("yznf"));
				voHjdyHqxx1[0] =  new VoHjdyHqxx();
				voHjdyHqxx1[0].setDylb("06");
				voHjdyHqxx1[0].setRynbid(Long.parseLong(rynbid));
				voHjdyHqxx1[0].setYznf(yznf);
				voHjdyHqxx1[0].setZjbh(qyzbh);
				hjService.processQyzbhhtyw(voQyzbhhtxxs1);
				hjService.processDyxxbcyw(voHjdyHqxx1);
		}else if(lodopId.equals("qyz2")){
			String qyzbh="";
			String rynbid ="";
			String hjywid="";
			String bzTran=""; 
			JSONArray json = JSONObject.parseArray(jsonStr);
			if(json.size()>0) {
				JSONObject jUser1 = json.getJSONObject(0);
				hql3="from "+PoHJYW_QCZXXXB.class.getName()+" where  rynbid='"+jUser1.getString("rynbid")+"' and hjywid = '"+jUser1.getString("hjywid")+"'";
				List hjyw_qczxxbList = super.findAllByHQL(hql3);
				if(hjyw_qczxxbList.size()==1) {
					hjyw_qczxxxb = (PoHJYW_QCZXXXB) hjyw_qczxxbList.get(0);
					if(hjyw_qczxxxb.getYwlx().equals("2")) {
						result = result.replace("{ywlx}", "★全户迁移★");
					}else {
						result = result.replace("{ywlx}", "");
					}
					hbTran = getMcByClsb("1005", hjyw_qczxxxb.getHb());
					if(hjyw_qczxxxb.getQwdssxq()!=null) {
						qwzzTran=getCsdInfo(hjyw_qczxxxb.getQwdssxq());
						if(hjyw_qczxxxb.getQwdxz()!=null) {
							qwzzTran = qwzzTran+hjyw_qczxxxb.getQwdxz();
						}
					}else {
						qwzzTran="";
					}
				}
				//hjyw_qczxxxb.setQyzbh("皖"+jUser1.getString("qyzbh"));
				//super.update(hjyw_qczxxxb);
			}
			result = result.replace("{tubiao}", baseUrl+"/rkxt/images/dunpai.bmp");
			VoQyzbhhtxx[] voQyzbhhtxxs2  = new VoQyzbhhtxx[json.size()];
			VoHjdyHqxx[] voHjdyHqxx2 = new VoHjdyHqxx[json.size()];
			for(int i =0;i<json.size();i++) {
				JSONObject jUser = json.getJSONObject(i);
				rynbid =jUser.getString("rynbid");
				yznf = jUser.getString("yznf");
				qyzbh = index;
				hjywid = jUser.getString("hjywid");
				//更新迁出注销信息表的sbrjtgx字段 add by zjm 20191203
				Long qczxid = jUser.getLong("qczxid");
				updateHjywQczxxbSbrjtgx(qczxid,jUser.getString("sbrjtgx")); 
				voQyzbhhtxxs2[i] =  new VoQyzbhhtxx();
				voQyzbhhtxxs2[i].setDysx(new Long(i+1));//new Long(temp)
				voQyzbhhtxxs2[i].setHjywid(Long.parseLong(hjywid));
				voQyzbhhtxxs2[i].setQfrq(sdf.format(date));
				voQyzbhhtxxs2[i].setQyzbh(qyzbh);
				voQyzbhhtxxs2[i].setRynbid(Long.parseLong(rynbid));
				voQyzbhhtxxs2[i].setYczrgx(jUser.getString("sbrjtgx"));//一人一证时  持证人为本人
				voQyzbhhtxxs2[i].setYxqxjzrq(sdf.format(date1));
				voQyzbhhtxxs2[i].setYznf(yznf);
				voHjdyHqxx2[i] = new VoHjdyHqxx();
				voHjdyHqxx2[i].setDylb("06");
				voHjdyHqxx2[i].setRynbid(Long.parseLong(rynbid));
				voHjdyHqxx2[i].setYznf(yznf);
				voHjdyHqxx2[i].setZjbh(qyzbh);
				hql2="from "+PoHJXX_CZRKJBXXB.class.getName()+" where  rynbid='"+rynbid+"'";
				List hjxx_czrkjbxxbList = super.findAllByHQL(hql2);
				hjxx_czrkjbxxb = (PoHJXX_CZRKJBXXB) hjxx_czrkjbxxbList.get(0);
				
				result = result.replace("{hb}",hbTran);
				result = result.replace("{xm"+(i+1)+"}",CommonUtil.isEmpty(hjxx_czrkjbxxb.getXm())?"":hjxx_czrkjbxxb.getXm());
				result = result.replace("{xb"+(i+1)+"}",getMcByClsb("8003", hjxx_czrkjbxxb.getXb()));
				result = result.replace("{gmsfhm"+(i+1)+"}",CommonUtil.isEmpty(hjxx_czrkjbxxb.getGmsfhm())?"":hjxx_czrkjbxxb.getGmsfhm());
				result = result.replace("{yhzgx"+(i+1)+"}",getMcByClsb("8006",jUser.getString("sbrjtgx") ));
				addressTran=getAddressByQhdm(hjxx_czrkjbxxb);
		    	 pcsTran = getPcs(hjxx_czrkjbxxb.getPcs());
		    	 if(hjxx_czrkjbxxb.getJcwh()!=null) {
		    		 jwhTran =getJcwh(hjxx_czrkjbxxb.getJcwh());
		    		 int tempCxfldm =Integer.parseInt(getjwhCxfldm(hjxx_czrkjbxxb.getJcwh()));
		    		 if(tempCxfldm>=100&&tempCxfldm<200) {
		    			 addressTran=addressTran+"("+"城镇"+")";
		    		 }else if(tempCxfldm>=200&&tempCxfldm<300){
		    			 addressTran=addressTran+"("+"乡村"+")";
		    		 }
		    	 }
				cymTran=hjxx_czrkjbxxb.getCym();
				if(cymTran!=null) {
					result = result.replace("{cym"+(i+1)+"}",cymTran);
				}else {
					result = result.replace("{cym"+(i+1)+"}","");
				}
				result = result.replace("{mz"+(i+1)+"}",getMcByClsb("8001", hjxx_czrkjbxxb.getMz()));
				if(hjxx_czrkjbxxb.getZy()!=null) {
					result = result.replace("{zy"+(i+1)+"}",hjxx_czrkjbxxb.getZy());
				}else {
					result = result.replace("{zy"+(i+1)+"}","");
				}
				csrqTran=hjxx_czrkjbxxb.getCsrq();
				if(csrqTran!=null&&csrqTran.length()>=0) {
					result = result.replace("{yyyy"+(i+1)+"}",hjxx_czrkjbxxb.getCsrq().substring(0, 4));
					result = result.replace("{mm"+(i+1)+"}",hjxx_czrkjbxxb.getCsrq().substring(4, 6));
					result = result.replace("{dd"+(i+1)+"}",hjxx_czrkjbxxb.getCsrq().substring(6, 8));
				}else {
					result = result.replace("{yyyy"+(i+1)+"}","");
					result = result.replace("{mm"+(i+1)+"}","");
					result = result.replace("{dd"+(i+1)+"}","");
				}
				csdTran=hjxx_czrkjbxxb.getCsdssxq();
				if(csdTran!=null) {
					result = result.replace("{csd"+(i+1)+"}",getCsdInfo(csdTran));
				}else {
					result = result.replace("{csd"+(i+1)+"}","");
				}
				jgxzTran=getCsdInfo(hjxx_czrkjbxxb.getJgssxq());
				if(jgxzTran!=null) {
					result = result.replace("{jgssxq"+(i+1)+"}",jgxzTran);
				}else {
					result = result.replace("{jgssxq"+(i+1)+"}","");
				}
				result = result.replace("{whcd"+(i+1)+"}",getMcByClsb("8002", hjxx_czrkjbxxb.getWhcd()));
				result = result.replace("{hyzk"+(i+1)+"}",getMcByClsb("8007", hjxx_czrkjbxxb.getHyzk()));
				result = result.replace("{qyyy"+(i+1)+"}",getMcByClsb("1019", hjxx_czrkjbxxb.getQczxlb()));//getMcByClsb("9035", hjxx_czrkjbxxb.getQyldyy())
				if(CommonUtil.isNotEmpty(hjxx_czrkjbxxb.getZjlb())) {
					String zjlbTran = getMcByClsb("5012", hjxx_czrkjbxxb.getZjlb());
					String qfjgTran = CommonUtil.isNotEmpty(hjxx_czrkjbxxb.getQfjg())?hjxx_czrkjbxxb.getQfjg():"";
					bzTran =bzTran +"("+hjxx_czrkjbxxb.getXm()+"已办理"+zjlbTran+", 签发机关："+qfjgTran+", 签发日期："+hjxx_czrkjbxxb.getYxqxqsrq()+"。)";
				}
			}
			result = result.replace("{qyzbh}",CommonUtil.isEmpty(qyzbh)?"":qyzbh);
			result = result.replace("{pcs}",pcsTran);
			result = result.replace("{jwh}",jwhTran);
			result = result.replace("{qyyy}",getMcByClsb("1019", hjxx_czrkjbxxb.getQczxlb()));
			// modify by zjm 20190625 迁移证备注显示
			//迁移证的备注信息取值逻辑：1、如果bz有值，就取bz；2、如果没值，取hjxx_czrk表里的xm+zjlb+qfjg+yxqxqsrq这4个字段的拼接；3、如果zjlb字段是空的就不要自动拼接了，如果其他字段空的对应内容就空着；4、如果zjlb和bz都是空的，页面上就显示为空即可
			if(hjyw_qczxxxb!=null&&CommonUtil.isNotEmpty(hjyw_qczxxxb.getBz())) {
				result = result.replace("{bz}",hjyw_qczxxxb.getBz());
			}else {
				result = result.replace("{bz}",bzTran);
			}
			result = result.replace("{yzz}",addressTran);
			result = result.replace("{qwzz}",qwzzTran);
			result = result.replace("{dlm}",CommonUtil.isEmpty(this.getUserInfo().getYhxm())?"":this.getUserInfo().getYhxm());
			qwzzTran=hjxx_czrkjbxxb.getQwdxz();
			if(qwzzTran!=null) {
				result = result.replace("{qwzz}",qwzzTran);
			}else {
				result = result.replace("{qwzz}","");
			}
			result = result.replace("{yyyy5}",  String.format("%tY", date));
			result = result.replace("{mm5}", String.format("%tm", date));
			result = result.replace("{dd5}",String.format("%td", date));
			result = result.replace("{yyyy6}",  String.format("%tY", date1));
			result = result.replace("{mm6}", String.format("%tm", date1));
			result = result.replace("{dd6}",String.format("%td", date1));
			result = result.replace("{hyzk}",getMcByClsb("8007", hjxx_czrkjbxxb.getHyzk()));
			result = result.replace("{whcd}",getMcByClsb("8002", hjxx_czrkjbxxb.getWhcd()));
			for(int i1=json.size()+1;i1<=4;i1++) {
				result = result.replace("{xm"+i1+"}","");
				result = result.replace("{xb"+i1+"}","");
				result = result.replace("{gmsfhm"+i1+"}","");
				result = result.replace("{yhzgx"+i1+"}","以下空白");
				result = result.replace("{cym"+i1+"}","");
				result = result.replace("{mz"+i1+"}","");
				result = result.replace("{yyyy"+i1+"}","");
				result = result.replace("{mm"+i1+"}","");
				result = result.replace("{dd"+i1+"}","");
				result = result.replace("{csd"+i1+"}","");
				result = result.replace("{jgssxq"+i1+"}","");
				result = result.replace("{whcd"+i1+"}","");
				result = result.replace("{zy"+i1+"}","");
				result = result.replace("{hyzk"+i1+"}","");
				result = result.replace("{gmsfhm"+i1+"}","");
				result = result.replace("{qyyy"+i1+"}","");
			}
			result = generrateFromObj(hjxx_czrkjbxxb, result);
			hjService.processQyzbhhtyw(voQyzbhhtxxs2);
			hjService.processDyxxbcyw(voHjdyHqxx2);
			//hqsjDao.saveHjxxDyxxb(hjxx_czrkjbxxb,lodopId,qyzbh,yznf);
		}
		// add by zjm 20190703 迁移证打印完毕后,将新的个人打印设置表qyzbh字段更新为新的迁移证编号
		PoPERSON_DY_SET poperson_dy_set = super.get(PoPERSON_DY_SET.class, this.getUser().getYhid());
		poperson_dy_set.setQyzbh(index);
		poperson_dy_set.setYznf(yznf);
		super.update(poperson_dy_set);
		return  result;
	}
	return result;

}
private void updateHjywQczxxbSbrjtgx(Long qczxid, String string) {
	 PoHJYW_QCZXXXB HJYW_QCZXXXBQyz1 = super.get(PoHJYW_QCZXXXB.class, qczxid);
	 HJYW_QCZXXXBQyz1.setSbrjtgx(string);
	 super.update(HJYW_QCZXXXBQyz1);
}

public String queryZqzdyinfo(String id, String lodopId, String jsonStr, String index) {
	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	String hql1="";
	String hql2="";
	PoHJSP_ZQZXXB poHjsp_zqzxxb = null;
	PoLODOP poldop = new PoLODOP();
	String result = "";
	JSONArray json = JSONObject.parseArray(id);
	String zqid="";
	String spsqzid = "";
	String spywid = "";
	for(int i=0;i<json.size();i++) {
		JSONObject jUser = json.getJSONObject(i);
		zqid=jUser.getString("zqid");
		spsqzid = jUser.getString("spsqzid");
		spywid = jUser.getString("spywid");
		String sjc = null; //省简称
		//得到省简称
		sjc = this.getXTKZCS(PublicConstant.XTKZCS_XTSYSJC);
		if (sjc == null) {
			sjc = "";
		}
		//根据zqid 更新HJSP_ZQZXXB 表
		hql1 ="from "+PoHJSP_ZQZXXB.class.getName()+" where  zqid='"+zqid+"'";
		List voHjsp_zqzxxb = super.findAllByHQL(hql1);
		if(voHjsp_zqzxxb.size()>0) {
			poHjsp_zqzxxb =(PoHJSP_ZQZXXB) voHjsp_zqzxxb.get(0);
			//poHjsp_zqzxxb.setZqzjlx(jsonStr);
			poHjsp_zqzxxb.setZjbh(sjc+index);
			Date date = new Date();
			SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
			Calendar calendar = Calendar.getInstance();
			date = calendar.getTime();
			poHjsp_zqzxxb.setQfrq(sdf.format(date));
			poHjsp_zqzxxb.setZqzjlx(jsonStr);
			super.update(poHjsp_zqzxxb);
		}
		//根据spsqzid更新HJSP_HJSPZB表
		hql2 ="from " + PoHJSP_HJSPZB.class.getName() + "" +" where spsqzid = '"+spsqzid+"'";;
		List poHjsp_hjspzb = super.findAllByHQL(hql2);
		if(poHjsp_hjspzb.size()>0) {
			PoHJSP_HJSPZB po = (PoHJSP_HJSPZB) poHjsp_hjspzb.get(0);
			po.setZjbh(sjc+index);
			super.update(po);
		}
		
	}
	
	if(!spywid.isEmpty()) {
		hql1 ="from "+PoHJSP_ZQZXXB.class.getName()+" where  spywid='"+spywid+"' and zqid = '"+zqid+"'";
		List voHjsp_zqzxxb = super.findAllByHQL(hql1);
		if(voHjsp_zqzxxb.size()>0) {
			poHjsp_zqzxxb =(PoHJSP_ZQZXXB) voHjsp_zqzxxb.get(0);
			String hql3 = "from " + PoLODOP.class.getName() + "" +
					" where lodopId = '"+lodopId+"' and zxbz = '0' ";
			List lodopList =super.findAllByHQL(hql3);
			if (lodopList != null && lodopList.size() ==1) {
				poldop = (PoLODOP) lodopList.get(0);
				result += poldop.getNr();
				String xb1Tran =getMcByClsb("8003", poHjsp_zqzxxb.getQyrxb1());
				String xb2Tran =getMcByClsb("8003", poHjsp_zqzxxb.getQyrxb2());
				String xb3Tran =getMcByClsb("8003", poHjsp_zqzxxb.getQyrxb3());
				String xb4Tran =getMcByClsb("8003", poHjsp_zqzxxb.getQyrxb4());
				String qyrysqrgx1 =getMcByClsb("8006", poHjsp_zqzxxb.getQyrysqrgx1()); 
				String qyrysqrgx2 =getMcByClsb("8006", poHjsp_zqzxxb.getQyrysqrgx2()); 
				String qyrysqrgx3 =getMcByClsb("8006", poHjsp_zqzxxb.getQyrysqrgx3()); 
				String qyrysqrgx4 =getMcByClsb("8006", poHjsp_zqzxxb.getQyrysqrgx4()); 
				String zqyyTran =getMcByClsb("1056", poHjsp_zqzxxb.getZqyy()); 
				String qrdhkdjjgTran  =getPcs(poHjsp_zqzxxb.getQrdhkdjjg());
				Date date = new Date();
		 		result = result.replace("{yyyy}",  String.format("%tY", date));
	 			result = result.replace("{mm}", String.format("%tm", date));
	 			result = result.replace("{dd}",String.format("%td", date));
	 			result = result.replace("{qyrxb1}",xb1Tran);
	 			result = result.replace("{qyrxb2}",xb2Tran);
	 			result = result.replace("{qyrxb3}",xb3Tran);
	 			result = result.replace("{qyrxb4}",xb4Tran);
	 			result = result.replace("{qyrysqrgx1}",qyrysqrgx1);
	 			result = result.replace("{qyrysqrgx2}",qyrysqrgx2);
	 			result = result.replace("{qyrysqrgx3}",qyrysqrgx3);
	 			result = result.replace("{qyrysqrgx4}",qyrysqrgx4);
	 			result = result.replace("{zqyy}",zqyyTran);
	 			result = result.replace("{zqzbh}",index);
	 			result = result.replace("{qrdhkdjjg}",qrdhkdjjgTran);
				Class c1= poHjsp_zqzxxb.getClass();
	 			Field[] fields1  = c1.getDeclaredFields();
	 			for(Field f : fields1) {
	 				// 获取原来的访问控制权限 
	 				boolean accessFlag = f.isAccessible(); 
	 				// 修改访问控制权限 
	 				f.setAccessible(true); 
	 				// 获取在对象f中属性fields[i]对应的对象中的变量  
	 				Object o;
	 				try {
						o = f.get(poHjsp_zqzxxb);
		 				String tempName =f.getName();
		 				String tempFirst =upperCase(tempName);
		 				if(result.contains(tempName)&&(o!=null)) {
		 					
		 					try {
		 						System.out.println(tempName+":"+o.toString());
								result = result.replace("{"+tempName+"}", o.toString());
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		 				}else {
		 					result = result.replace("{"+tempName+"}","");
		 				}
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					} 
	 				// 恢复访问控制权限  
	 		        f.setAccessible(accessFlag); 

	 			}
	 			String StrHqlById ="from "+PoHJXX_CZRKJBXXB.class.getName()+" where  gmsfhm='"+poHjsp_zqzxxb.getSqrgmsfhm()+"'";
	 			List hjxx_czrkjbxxbList = super.findAllByHQL(StrHqlById);
	 			PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb =null;
	 			if(hjxx_czrkjbxxbList.size()>0) {
	 				vohjxx_czrkjbxxb=(PoHJXX_CZRKJBXXB) hjxx_czrkjbxxbList.get(0);
	 				
	 				hqsjDao.saveHjxxDyxxb(vohjxx_czrkjbxxb,lodopId,index, String.format("%tY", date));
	 			}
	 			// add by zjm 20190703 准迁证打印完毕后,将新的个人打印设置表zqzbh字段更新为新的准迁证编号
	 			PoPERSON_DY_SET poperson_dy_set = super.get(PoPERSON_DY_SET.class, this.getUser().getYhid());
	 			poperson_dy_set.setZqzbh(index);
	 			super.update(poperson_dy_set);
				return  result;
			}
		}
	}else {
		return "查询准迁证号，表中不存在";
	}
	return result;
}
public String queryCzrkdjbinfo(String id, String lodopId, String jsonStr,String baseurl){
	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//根据传入ryid获得表中数据
	String StrHqlById="";
	PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb =null;
	//VoHJXX_CZRKJBXXB    PoHJXX_CZRKJBXXB
	if(!id.isEmpty()) {
		StrHqlById ="from "+PoHJXX_CZRKJBXXB.class.getName()+" where  rynbid='"+id+"'";
		List hjxx_czrkjbxxbList = super.findAllByHQL(StrHqlById);
		if(hjxx_czrkjbxxbList.size()==0||hjxx_czrkjbxxbList.size()>1) {
			//返回前台，当前人数据库中未能查询到合适数据
			return "当前人数据库中未能查询到合适数据";
		}else {
			vohjxx_czrkjbxxb=(PoHJXX_CZRKJBXXB) hjxx_czrkjbxxbList.get(0);
			//查询套打表中数据
			String strHQL ="";
			String strHxxhql="";
			PoLODOP poldop = null;
			PoHJXX_HXXB pohjxx_hxxb =null;
			String result = "";
			String hlxTran ="";//户类型转换后的值
			String hbTran ="";
			String hzxmTran ="";//户主姓名
			String yhzgxTran="";//与户主关系
			String xbTran="";//性别
			String hyzkTran ="";// 婚姻状况
			String mzTran="";//名族  
			String whcdTran="";//文化程度
			String byzkTran="";//兵役状况
			String xxTran="";//血型
			String csrqyyyyTran="";//出生日期 yyyy
			String csrqmm1Tran="";//出生日期 mm1
			String csrqddTran="";//出生日期 dd
			String csrqhhTran="";//出生日期 hh
			String csrqmm2Tran="";//出生日期 mm2
			String csdssxqTran="";//出生地
			String cszqfrqTran ="";//公民出生证签发日期
			String jgssxqTran ="";//籍贯
			String yxqxqsrqTran ="";//居民身份证签发日期
			String hsqlTran="";
			String hsqlyyyyTran = "";
			String hsqlmmTran = "";//
			String hsqlddTran = "";//
			String addressTran="";
			String hyqlTran ="";
			String hdqlbsTran ="";
			String hssxqqlTran ="";
			String hslbzTran="";
			String hslbzyyyyTran="";
			String hslbzmmTran="";
			String hslbzddTran="";
			String hylbzTran="";
			String hsssqlbzTran="";
			String qcrqTran = "";
			String qcrqyyyyTran = "";
			String qcrqmmTran = "";
			String qcrqddTran = "";
			String qczxlbTran = "";
			String hshyqwhdTran ="";
			String swrqTran = "";
			String swrqyyyyTran = "";
			String swrqmmTran = "";
			String swrqddTran = "";
			String swzxlbTran = "";
			String pcsTran = "";
			String jcwhTran = "";
			String zjxyTran ="";
			String jhryjhgxTran="";
			String jhrejhgxTran="";
			strHQL = "from " + PoLODOP.class.getName() + "" +
					" where lodopId = '"+lodopId+"' and zxbz = '0' ";
			strHxxhql="from "+PoHJXX_HXXB.class.getName()+" where hhnbid ='"+vohjxx_czrkjbxxb.getHhnbid()+"'";
			
			
			 List lodopList =super.findAllByHQL(strHQL);
			 List pohjxx_hxxbList=super.findAllByHQL(strHxxhql);
		     if (lodopList != null && lodopList.size() ==1&&pohjxx_hxxbList != null && pohjxx_hxxbList.size() ==1) {
		    	 poldop = (PoLODOP) lodopList.get(0);
		    	 pohjxx_hxxb = (PoHJXX_HXXB) pohjxx_hxxbList.get(0);
		    	 hlxTran=getMcByClsb("1005", vohjxx_czrkjbxxb.getHb());
		    	 hzxmTran =getHzxm(vohjxx_czrkjbxxb);
		    	 hbTran = getMcByClsb("1005", vohjxx_czrkjbxxb.getHb());
		    	 yhzgxTran =getMcByClsb("8006", vohjxx_czrkjbxxb.getYhzgx());
		    	 xbTran=getMcByClsb("8003", vohjxx_czrkjbxxb.getXb());
		    	 hyzkTran=getMcByClsb("8007", vohjxx_czrkjbxxb.getHyzk());
		    	 mzTran=getMcByClsb("8001", vohjxx_czrkjbxxb.getMz());
		    	 whcdTran =getMcByClsb("8002", vohjxx_czrkjbxxb.getWhcd());
		    	 byzkTran =getMcByClsb("1046", vohjxx_czrkjbxxb.getByzk());
		    	 xxTran=getMcByClsb("1044", vohjxx_czrkjbxxb.getXx());
		    	 csdssxqTran = getCsdInfo(vohjxx_czrkjbxxb.getCsdssxq());
		    	 jgssxqTran= getCsdInfo(vohjxx_czrkjbxxb.getJgssxq());
		    	 zjxyTran=getMcByClsb("1045", vohjxx_czrkjbxxb.getZjxy());
		    	 //cslb 1005    户别        8003 性别       8001   民族     8006  与户主关系     8007  婚姻状况  8002 文化程度   1046 兵役状况     1044血型
		    	 //地址获取
		    	 jhryjhgxTran =getMcByClsb("8006", vohjxx_czrkjbxxb.getJhryjhgx());
				 jhrejhgxTran =getMcByClsb("8006", vohjxx_czrkjbxxb.getJhrejhgx());
		    	 addressTran = getAddressByQhdm(vohjxx_czrkjbxxb);
		    	 hyqlTran = getMcByClsb("1020", vohjxx_czrkjbxxb.getHyql());
//		    	 if(vohjxx_czrkjbxxb.getHyql()!=null) {
//		    		 hdqlbsTran = "因"+getMcByClsb("1020", vohjxx_czrkjbxxb.getHyql())+"从"+ getCsdInfo(vohjxx_czrkjbxxb.getHssxqql());
//		    		 if(vohjxx_czrkjbxxb.getHxzql()!=null) {
//		    			 hdqlbsTran = hdqlbsTran+vohjxx_czrkjbxxb.getHxzql()+"迁来";
//		    		 }else {
//		    			 hdqlbsTran = hdqlbsTran+"迁来";
//		    		 }
//		    		 
//		    	 }else {
//		    		 hdqlbsTran="";
//		    	 }
		    	 hdqlbsTran=getMcByHyql(vohjxx_czrkjbxxb,1);
		    	 //hssxqqlTran = getCsdInfo(vohjxx_czrkjbxxb.getHssxqql())+vohjxx_czrkjbxxb.getHxzql();
		    	 String csrqTran =vohjxx_czrkjbxxb.getCsrq();
		    	 String cssjTran =vohjxx_czrkjbxxb.getCssj(); 
		    	 if(csrqTran!=null&&csrqTran.length()==8) {
		    		 csrqyyyyTran =csrqTran.substring(0, 4);
		    		 csrqmm1Tran =csrqTran.substring(4, 6);
		    		 csrqddTran =csrqTran.substring(6, 8);
		    	 }else {
		    		 csrqyyyyTran ="";
		    		 csrqmm1Tran ="";
		    		 csrqddTran ="";
		    	 }
		    	 if(cssjTran!=null&&cssjTran.length()==6) {
		    		 csrqhhTran = cssjTran.substring(0, 2);
		    		 csrqmm2Tran = cssjTran.substring(2, 4);
		    	 }else {
		    		 csrqhhTran = "";
		    		 csrqmm2Tran = "";
		    	 }
		    	//temDate.substring(0, 4)+"年"+temDate.substring(4, 6)+"月"+temDate.substring(6, 8)+"日"+" 时"+" 分"
		    	cszqfrqTran=transStringToDateformatter(vohjxx_czrkjbxxb.getCszqfrq(),1);
		    	yxqxqsrqTran = transStringToDateformatter(vohjxx_czrkjbxxb.getYxqxqsrq(),1);
		    	hsqlTran =vohjxx_czrkjbxxb.getHsql();
		    	if(hsqlTran!=null&&hsqlTran.length()==8) {
		    		hsqlyyyyTran =hsqlTran.substring(0, 4);
		    		hsqlmmTran =hsqlTran.substring(4, 6);
		    		hsqlddTran =hsqlTran.substring(6, 8);
		    	 }else {
		    		hsqlyyyyTran ="";
		    		hsqlmmTran ="";
		    		hsqlddTran ="";
		    	 }		
		    	hslbzTran=vohjxx_czrkjbxxb.getHslbz();
		    	if(hslbzTran!=null&&hslbzTran.length()==8) {
		    		hslbzyyyyTran =hslbzTran.substring(0, 4);
		    		hslbzmmTran =hslbzTran.substring(4, 6);
		    		hslbzddTran =hslbzTran.substring(6, 8);
		    	 }else {
		    		 hslbzyyyyTran ="";
		    		 hslbzmmTran ="";
		    		 hslbzddTran ="";
		    	 }	
				
				hsssqlbzTran=getCsdInfo(vohjxx_czrkjbxxb.getHsssqlbz());
				if(CommonUtil.isNotEmpty(vohjxx_czrkjbxxb.getHylbz())&&(vohjxx_czrkjbxxb.getHylbz().equals("0101")||vohjxx_czrkjbxxb.getHylbz().equals("0108")||vohjxx_czrkjbxxb.getHylbz().equals("0109"))){
					hylbzTran = getMcByClsb("1022", vohjxx_czrkjbxxb.getHylbz());
				}else {
					hylbzTran = getHylbzTemp(vohjxx_czrkjbxxb);
				}
				
				qcrqTran = vohjxx_czrkjbxxb.getQcrq();
				if(qcrqTran!=null&&qcrqTran.length()==8) {
					qcrqyyyyTran =qcrqTran.substring(0, 4);
					qcrqmmTran =qcrqTran.substring(4, 6);
					qcrqddTran =qcrqTran.substring(6, 8);
		    	 }else {
		    		 qcrqyyyyTran ="";
		    		 qcrqmmTran ="";
		    		 qcrqddTran ="";
		    	 }
				qczxlbTran = getMcByClsb("1019", vohjxx_czrkjbxxb.getQczxlb());
				if(vohjxx_czrkjbxxb.getQcrq()!=null&&CommonUtil.isNotBlank(vohjxx_czrkjbxxb.getQcrq())&&vohjxx_czrkjbxxb.getQcrq().length()>0&&vohjxx_czrkjbxxb.getQczxlb()!=null) {
					if(qczxlbTran!=null) {
						hshyqwhdTran = "因"+qczxlbTran+"迁往";
						PoXT_XZQHB xzqhb = new PoXT_XZQHB();
						if(vohjxx_czrkjbxxb.getQwdssxq()!=null) {
							 xzqhb = super.get(PoXT_XZQHB.class, vohjxx_czrkjbxxb.getQwdssxq());
						   
						}
						if(vohjxx_czrkjbxxb.getQwdxz()!=null) {
						     if(xzqhb!=null) {
						    	 if(vohjxx_czrkjbxxb.getQwdxz().contains(xzqhb.getMc())) {
						    		 hshyqwhdTran =hshyqwhdTran +vohjxx_czrkjbxxb.getQwdxz(); 
						    	 }else {
						    		 hshyqwhdTran =hshyqwhdTran +xzqhb.getMc()+vohjxx_czrkjbxxb.getQwdxz();
						    	 }
						     }
							hshyqwhdTran =hshyqwhdTran +vohjxx_czrkjbxxb.getQwdxz();
						}
						
					}else {
						hshyqwhdTran = "因"+qczxlbTran+"迁往"+"";
					}
					
				}else {
					hshyqwhdTran = "";
				}
				
				swrqTran = vohjxx_czrkjbxxb.getSwzxrq();// modify by zjm 20190712 死亡日期应该为swzxrq，而不是zxsj或者swrq
				if(swrqTran!=null&&swrqTran.length()==8) {
					swrqyyyyTran =swrqTran.substring(0, 4);
					swrqmmTran =swrqTran.substring(4, 6);
					swrqddTran =swrqTran.substring(6, 8);
		    	 }else {
		    		 swrqyyyyTran ="";
		    		 swrqmmTran ="";
		    		 swrqddTran ="";
		    	 }
				swzxlbTran = getMcByClsb("1021", vohjxx_czrkjbxxb.getSwzxlb());
				if(swzxlbTran!=null&&swzxlbTran!="") {
					/**
						2018.09.30
						修改	刁杰
						如果死亡原因最后有死亡,则不手动拼接"死亡"
					 */
					if(swzxlbTran.lastIndexOf("死亡") > 0) {
						swzxlbTran ="因"+swzxlbTran;
					}else {
						swzxlbTran ="因"+swzxlbTran+"死亡";
					}
				}
				pcsTran = getPcs(vohjxx_czrkjbxxb.getPcs());
				jcwhTran =getJcwh(vohjxx_czrkjbxxb.getJcwh());
		 		result += poldop.getNr();
		 		JSONObject jo = JSONObject.parseObject(jsonStr);
		 		JSONObject jo1 = JSONObject.parseObject(jo.getString("printset"));
		 		JSONObject jo2 = JSONObject.parseObject(jo1.getString("printset_3"));
		 		if((jo2.getString("checked")).equalsIgnoreCase("true")) {
		 			result = result.replace("{zp}",getZp(vohjxx_czrkjbxxb));
		 		}else {
		 			result = result.replace("{zp}","");
		 		}
		 			result = result.replace("{hlx}", hlxTran);
		 			result = result.replace("{hb}", hbTran);
		 			result = result.replace("{hzxm}", hzxmTran);
		 			result = result.replace("{yhzgx}", yhzgxTran);
		 			result = result.replace("{xb}", xbTran);
		 			result = result.replace("{hyzk}", hyzkTran);
		 			result = result.replace("{mz}", mzTran);
		 			result = result.replace("{whcd}", whcdTran);
		 			result = result.replace("{byzk}", byzkTran);
		 			result = result.replace("{xx}", xxTran);
		 			result = result.replace("{csrqyyyy}", csrqyyyyTran);
		 			result = result.replace("{csrqmm1}", csrqmm1Tran);
		 			result = result.replace("{csrqdd}", csrqddTran);
		 			result = result.replace("{csrqhh}", csrqhhTran);
		 			result = result.replace("{csrqmm2}", csrqmm2Tran);
		 			result = result.replace("{csdssxq}", csdssxqTran);
		 			result = result.replace("{jhryjhgx}", jhryjhgxTran);
		 			result = result.replace("{jhrejhgx}", jhrejhgxTran);
		 			result = result.replace("{cszqfrq}", cszqfrqTran);
		 			result = result.replace("{address}", addressTran);
		 			result = result.replace("{jgssxq}", jgssxqTran);
		 			result = result.replace("{zjxy}", zjxyTran);
		 			result = result.replace("{yxqxqsrq}", yxqxqsrqTran);
		 			result = result.replace("{hsqlyyy}", hsqlyyyyTran);
		 			result = result.replace("{hsqlmm}", hsqlmmTran);
		 			result = result.replace("{hsqldd}", hsqlddTran);
		 			result = result.replace("{hdqlbs}", hdqlbsTran);
		 			result = result.replace("{hyql}", hyqlTran);
		 			result = result.replace("{hssxqql}", hssxqqlTran);
		 			result = result.replace("{hslbzyyyy}", hslbzyyyyTran);
		 			result = result.replace("{hslbzmm}", hslbzmmTran);
		 			result = result.replace("{hslbzdd}", hslbzddTran);
		 			result = result.replace("{hyhdlbz}", hylbzTran);
		 			result = result.replace("{qcrqyyyy}", qcrqyyyyTran);
		 			result = result.replace("{qcrqmm}", qcrqmmTran);
		 			result = result.replace("{qcrqdd}", qcrqddTran);
		 			result = result.replace("{hshyqwhd}", hshyqwhdTran);
		 			
		 			result = result.replace("{swrqyyyy}", swrqyyyyTran);
		 			result = result.replace("{swrqmm}", swrqmmTran);
		 			result = result.replace("{swrqdd}", swrqddTran);
		 			result = result.replace("{swzxlb}", swzxlbTran);
		 			result = result.replace("{dlm}", CommonUtil.isEmpty(this.getUserInfo().getYhxm())?"":this.getUserInfo().getYhxm());
		 			Date date = new Date();
					result = result.replace("{yyyy}",  String.format("%tY", date));
					result = result.replace("{mm}", String.format("%tm", date));
					result = result.replace("{dd}",String.format("%td", date));
					result = result.replace("{pcs}", pcsTran);
					result = result.replace("{jcwh}", jcwhTran);
					result = result.replace("{tubiao}", baseurl+"/rkxt/images/dunpai.bmp");
		 			result = generrateFromObj(vohjxx_czrkjbxxb, result);
		 			hqsjDao.saveHjxxDyxxb(vohjxx_czrkjbxxb,lodopId,null,String.format("%tY", new Date()));
		     }
		     return result;
		}		
	}else {
		return "查询用户，表中不存在";
	}
}
public String getHylbzTemp(PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb) {
	String result="";
	String hy="";
	String fromAddress="";
	hy=getMcByClsb("1056", vohjxx_czrkjbxxb.getHylbz());
	PoXT_XZQHB qh = new PoXT_XZQHB();
	if(vohjxx_czrkjbxxb.getHsssqlbz()!=null&&CommonUtil.isNotEmpty(vohjxx_czrkjbxxb.getHsssqlbz().trim())) {
		qh = super.get(PoXT_XZQHB.class, vohjxx_czrkjbxxb.getHsssqlbz());
		if(qh!=null)
			fromAddress = qh.getMc();
	}
	if(vohjxx_czrkjbxxb.getHxzlbz()!=null&&CommonUtil.isNotEmpty(vohjxx_czrkjbxxb.getHxzlbz().trim())) {
		if(vohjxx_czrkjbxxb.getHxzlbz().indexOf("安徽省")>=0) {
			fromAddress = vohjxx_czrkjbxxb.getHxzlbz();
		}else {
			fromAddress = fromAddress+vohjxx_czrkjbxxb.getHxzlbz();
		}
		
	}
	if((vohjxx_czrkjbxxb.getHslbz()!=null&&CommonUtil.isNotEmpty(vohjxx_czrkjbxxb.getHslbz().trim()))&&hy!=null&&CommonUtil.isNotEmpty(hy.trim())) {
		result="因"+hy+"从"+fromAddress+"迁来";
	}
	return result;
}
public String getHylbzTemp1(PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb) {
	String result="";
	if(vohjxx_czrkjbxxb.getHslbz()!=null&&vohjxx_czrkjbxxb.getHxzlbz()!=null&&vohjxx_czrkjbxxb.getHxzlbz()!=null) {
		result = result+vohjxx_czrkjbxxb.getHslbz();
		PoXT_XZQHB qh = super.get(PoXT_XZQHB.class, vohjxx_czrkjbxxb.getHsssqlbz());
        if(qh!=null)
        	result = result+qh.getMc();
        if(vohjxx_czrkjbxxb.getHxzlbz().indexOf(qh.getMc())>=0) {
            result = vohjxx_czrkjbxxb.getHxzlbz()+getMcByClsb("1056", vohjxx_czrkjbxxb.getHylbz());
        }else {
        	result = result+vohjxx_czrkjbxxb.getHxzlbz()+getMcByClsb("1056", vohjxx_czrkjbxxb.getHylbz());
        }
	}
	return result;
}
public String getHylbzTemp2(PoHJTJ_RYBDXXB vohjtj_rybdxxb) {
	String hy="";
	String fromAddress="";
	hy=getMcByClsb("1056", vohjtj_rybdxxb.getBdyy());
	PoXT_XZQHB qh = new PoXT_XZQHB();
	PoXT_JWHXXB jwh =  new PoXT_JWHXXB();
	PoXT_JLXXXB jlx =  new PoXT_JLXXXB();
	if(vohjtj_rybdxxb.getBdhssxq()!=null) {
		qh = super.get(PoXT_XZQHB.class, vohjtj_rybdxxb.getBdhssxq());
        if(qh!=null)
        	fromAddress = fromAddress+qh.getMc();
	}
	if(vohjtj_rybdxxb.getBdhjcwh()!=null) {
		jwh =  super.get(PoXT_JWHXXB.class, vohjtj_rybdxxb.getBdhjcwh());
	    if(jwh!=null)
	    	fromAddress = fromAddress+jwh.getMc();
	}
		
	if(vohjtj_rybdxxb.getBdhjlx()!=null) {
		jlx =  super.get(PoXT_JLXXXB.class, vohjtj_rybdxxb.getBdhjlx());
		if(jlx!=null)
			fromAddress = fromAddress+jlx.getMc();
	}
    if(vohjtj_rybdxxb.getBdhmlph()!=null) {
    	fromAddress=fromAddress+vohjtj_rybdxxb.getBdhmlph();
    }
    if(vohjtj_rybdxxb.getBdhmlxz()!=null) {//modify by zjm 2020716 当详细地址包含省市县区街路巷等的时候，直接取详细地址
    	if(vohjtj_rybdxxb.getBdhmlxz().contains(qh.getMc())) {
    		fromAddress=vohjtj_rybdxxb.getBdhmlxz();
    	}else {
    		fromAddress=fromAddress+vohjtj_rybdxxb.getBdhmlxz();
    	}
    }
	return "因"+hy+"迁往"+fromAddress;
}
public String getHylbzTemp3(PoHJTJ_RYBDXXB vohjtj_rybdxxb) {
	String fromAddress="";
	PoXT_JWHXXB jwh =  new PoXT_JWHXXB();
	PoXT_JLXXXB jlx =  new PoXT_JLXXXB();
	if(CommonUtil.isNotEmpty(vohjtj_rybdxxb.getBdqjlx()))
		jlx =  super.get(PoXT_JLXXXB.class, vohjtj_rybdxxb.getBdqjlx());
	    if(CommonUtil.isNotEmpty(jlx.getMc()))
	    	fromAddress = fromAddress+jlx.getMc();
    if(CommonUtil.isNotEmpty(vohjtj_rybdxxb.getBdqmlph()))
    	fromAddress=fromAddress+vohjtj_rybdxxb.getBdqmlph();
    if(CommonUtil.isNotEmpty(vohjtj_rybdxxb.getBdqmlxz()))
    	fromAddress=fromAddress+vohjtj_rybdxxb.getBdqmlxz();
	return fromAddress;
}
public String getHylbzTemp4(PoHJTJ_RYBDXXB vohjtj_rybdxxb) {
	String fromAddress="";
	PoXT_JWHXXB jwh =  new PoXT_JWHXXB();
	PoXT_JLXXXB jlx =  new PoXT_JLXXXB();
	if(vohjtj_rybdxxb.getBdhjlx()!=null)
		jlx =  super.get(PoXT_JLXXXB.class, vohjtj_rybdxxb.getBdhjlx());
	    if(jlx.getMc()!=null)
	    	fromAddress = fromAddress+jlx.getMc();
    if(vohjtj_rybdxxb.getBdhmlph()!=null)
    	fromAddress=fromAddress+vohjtj_rybdxxb.getBdhmlph();
    if(vohjtj_rybdxxb.getBdhmlxz()!=null)
    	fromAddress=fromAddress+vohjtj_rybdxxb.getBdhmlxz();
	return fromAddress;
}
public String getZp(PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb) {
	String tempZp="";
	if(vohjxx_czrkjbxxb.getZpid()!=null) {
		String sql= "from " + PoHJXX_RYZPXXB.class.getName() + "" +
				" where zpid = '"+vohjxx_czrkjbxxb.getZpid()+"' ";
		 List hjxx_zplsbList =super.findAllByHQL(sql);
		 if(hjxx_zplsbList.size()>0) {
			 PoHJXX_RYZPXXB hjxx_zplsb = new PoHJXX_RYZPXXB();
			hjxx_zplsb= (PoHJXX_RYZPXXB) hjxx_zplsbList.get(0);
			BASE64Encoder encode = new BASE64Encoder();
			String base64 = encode.encode(hjxx_zplsb.getZp());
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		    Matcher m = p.matcher(base64);
			//System.out.println(dest);
		    
		    tempZp="data:image/jpg;base64,"+m.replaceAll("");
		 }
	}
	return tempZp;
}

private String getHzxm(PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb) {
	String hgxType=vohjxx_czrkjbxxb.getYhzgx();
	if("01".equals(hgxType)||"02".equals(hgxType)||"03".equals(hgxType)) {
		return vohjxx_czrkjbxxb.getXm();
	}else {
		//modify by 20190711 修改获取户主姓名方法，按ryzt升序 按rynbid降序获取同一人员状态下，rynbid大的那个作为户主
		String hql = "from "+PoHJXX_CZRKJBXXB.class.getName()+" where  hhnbid='"+vohjxx_czrkjbxxb.getHhnbid()+"' and yhzgx in ('01','02','03') and jlbz='1' and cxbz='0' order by ryzt asc, gxsj desc ";
		//String hql = "from "+PoHJXX_CZRKJBXXB.class.getName()+" where  hhnbid='"+vohjxx_czrkjbxxb.getHhnbid()+"' and yhzgx in ('01','02','03') and ryzt='0' and jlbz='1' and cxbz='0' order by rynbid desc ";
		List hjxx_czrkjbxxbList = super.findAllByHQL(hql);
		if(hjxx_czrkjbxxbList.size()>0) {
			return ((PoHJXX_CZRKJBXXB)hjxx_czrkjbxxbList.get(0)).getXm();
		}
	}
	return "";
}
public String getMcByHyql(PoHJXX_CZRKJBXXB po,int type) {
//	1022        01% 
//	1020        03%  
//	1019        04%  
//	1026        05%          
//	1023        06%
	String StrHql="";
	PoXT_XTCSB xt_xtcsb = null;
	int a[]={1022,1020,1019,1026,1023};
	PoXT_XZQHB xt_xzqxxb = null;
	if(po.getHyql() != null) {
		for(int i=0;i<a.length;i++) {
			StrHql ="from "+PoXT_XTCSB.class.getName()+" where cslb='"+a[i]+"' and dm='"+po.getHyql()+"'";
			List xt_xtcsbList=super.findAllByHQL(StrHql);
			if(xt_xtcsbList.size()>0) {
				xt_xtcsb = (PoXT_XTCSB) xt_xtcsbList.get(0);
				if(i==0) {
					return xt_xtcsb.getMc();
				}else {
					String hssxqql="";
					String result="";
					if(!CommonUtil.isEmpty(po.getHssxqql())){
						String hqlXt_xzqxxb ="from "+PoXT_XZQHB.class.getName()+" where dm='"+po.getHssxqql()+"'";
						List xt_xzqxxbList =super.findAllByHQL(hqlXt_xzqxxb);
						if(xt_xzqxxbList.size()>0) {
							xt_xzqxxb = (PoXT_XZQHB) xt_xzqxxbList.get(0);
							if(xt_xzqxxb.getMc()!=null) {
								hssxqql=xt_xzqxxb.getMc();
							}
						 }
					}
					if(type==1) {
						if(!CommonUtil.isEmpty(po.getHxzql())&&!CommonUtil.isEmpty(po.getHxzql().trim())) {
							if(po.getHxzql().contains(hssxqql)) {
								result="因"+xt_xtcsb.getMc()+"从"+po.getHxzql()+"迁来";
							}else {
								result="因"+xt_xtcsb.getMc()+"从"+hssxqql+po.getHxzql()+"迁来";
							}
							
						}else {
							result="因"+xt_xtcsb.getMc()+"从"+hssxqql+"迁来";
						}
					}else {
						if(!CommonUtil.isEmpty(po.getHxzql())&&!CommonUtil.isEmpty(po.getHxzql().trim())) {
							if(po.getHxzql().contains(hssxqql)) {
								result="从"+po.getHxzql()+"迁来";
							}else {
								result="从"+hssxqql+po.getHxzql()+"迁来";
							}
							
						}else {
							result="从"+hssxqql+"迁来";
						}
					}
					
					return result;
				}
				
				
			}
		}
	}
	if(po.getHyql()!=null&&CommonUtil.isNotEmpty(po.getHyql().trim())&&po.getHxzql()!=null&&CommonUtil.isNotEmpty(po.getHxzql().trim())) {
		return "因"+po.getHyql()+"从"+po.getHxzql()+"迁来";
	}else if((po.getHyql()==null||CommonUtil.isEmpty(po.getHyql().trim()))){
		if(po.getHssxqql()!=null&&CommonUtil.isNotEmpty(po.getHssxqql().trim())){
			xt_xzqxxb = super.get(PoXT_XZQHB.class, po.getHssxqql());
			if(po.getHxzql()!=null&&CommonUtil.isNotEmpty(po.getHxzql().trim())) {
				return "因从"+(xt_xzqxxb!=null?xt_xzqxxb.getMc():"")+po.getHxzql()+"迁来";      
			}else {
				return "因从"+(xt_xzqxxb!=null?xt_xzqxxb.getMc():"")+"迁来";
			}
		}else if(po.getHxzql()!=null&&CommonUtil.isNotEmpty(po.getHxzql().trim())) {
			return "从"+po.getHxzql()+"迁来";
		}else {
			return "";
		}
		
	}else {
		return "";
	}
	
}
public String getMcByClsb(String temCslb, String dm) {
	//婚姻状况为未婚(10)时,不显示未婚   2018/10/08
//	if(temCslb.equals("8007")&&(dm!=null&&dm.equals("10"))) {
//		return "";	
//	}
	String strXt_xtcsb="";
	PoXT_XTCSB xt_xtcsb = null;
	strXt_xtcsb ="from "+PoXT_XTCSB.class.getName()+" where cslb='"+temCslb+"' and dm='"+dm+"'";
	 List xt_xtcsbList=super.findAllByHQL(strXt_xtcsb);
	 if(xt_xtcsbList.size()==0) {
		 return "";
	 }
	 xt_xtcsb = (PoXT_XTCSB) xt_xtcsbList.get(0);
	return xt_xtcsb.getMc();
}
public String getCsdInfo(String qhdm) {
	String csd="";
	PoXT_XZQHB xzqhb = null;
	String hql_Csd="from "+PoXT_XZQHB.class.getName()+" where dm='"+qhdm+"'";
	List xt_xzqhdList = super.findAllByHQL(hql_Csd);
	if(xt_xzqhdList.size()==1) {
		xzqhb = (PoXT_XZQHB) xt_xzqhdList.get(0);
		csd = xzqhb.getMc();
	 }
	return csd;
}
public String getJlxInfo(String qhdm) {
	String csd="";
	PoXT_JLXXXB xzqhb = null;
	String hql_Csd="from "+PoXT_JLXXXB.class.getName()+" where dm='"+qhdm+"'";
	List xt_jlxList = super.findAllByHQL(hql_Csd);
	if(xt_jlxList.size()==1) {
		xzqhb = (PoXT_JLXXXB) xt_jlxList.get(0);
		csd = xzqhb.getMc();
	 }
	return csd;
}
public String getjwhInfo(String qhdm) {
	String csd="";
	PoXT_JWHXXB xzqhb = null;
	String hql_Csd="from "+PoXT_JWHXXB.class.getName()+" where dm='"+qhdm+"'";
	List xt_jwhList = super.findAllByHQL(hql_Csd);
	if(xt_jwhList.size()==1) {
		xzqhb = (PoXT_JWHXXB) xt_jwhList.get(0);
		csd = xzqhb.getMc();
	 }
	return csd;
}
public String getjwhCxfldm(String qhdm) {
	String cxfldm="";
	PoXT_JWHXXB xzqhb = null;
	String hql_Csd="from "+PoXT_JWHXXB.class.getName()+" where dm='"+qhdm+"'";
	List xt_jwhList = super.findAllByHQL(hql_Csd);
	if(xt_jwhList.size()==1) {
		xzqhb = (PoXT_JWHXXB) xt_jwhList.get(0);
		cxfldm = xzqhb.getCxfldm();
	 }
	return cxfldm;
}
public String getXzjdInfo(String zxjdkey) {
	String xzjd="";
	PoXT_XZJDXXB xzqhb = null;
	String hql_Csd="from "+PoXT_XZJDXXB.class.getName()+" where dm='"+zxjdkey+"'";
	List xt_xzjdList = super.findAllByHQL(hql_Csd);
	if(xt_xzjdList.size()==1) {
		xzqhb = (PoXT_XZJDXXB) xt_xzjdList.get(0);
		xzjd = xzqhb.getMc();
	 }
	return xzjd;
}
public String getAddressByQhdm(PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb) {
	//String qhdm, String jlx, String xzjd
	//详细地址:是单位信息表备注bz，乡镇街道信息表吧备注bz，街路巷信息表名称mc，拼接起来  MLPH  MLXZ
	String dwxxbTemp ="";
	String xzjdxxbTemp ="";
	String jlxxxbTemp ="";
	String mlphTran="";
	String mlxzTran = "";
	String result="";
	String resultTemp="";
	PoXT_XZQHB xt_xzqxxb = null;
	PoXT_XZJDXXB xt_xzjdxxb =null;
	PoXT_JLXXXB xt_jlxxxb =null;
	String hqlXt_xzqxxb ="from "+PoXT_XZQHB.class.getName()+" where dm='"+vohjxx_czrkjbxxb.getSsxq()+"'";
	String hqlXt_xzjdxxb ="from "+PoXT_XZJDXXB.class.getName()+" where  dm='"+vohjxx_czrkjbxxb.getXzjd()+"'";//qhdm='"+vohjxx_czrkjbxxb.getSsxq()+"' and
	String hqlXt_jlxxxb ="from "+PoXT_JLXXXB.class.getName()+" where  dm='"+vohjxx_czrkjbxxb.getJlx()+"'";
	List xt_xzqxxbList =super.findAllByHQL(hqlXt_xzqxxb);
	List xt_xzjdxxbList =super.findAllByHQL(hqlXt_xzjdxxb);
	List xt_jlxxxbList =super.findAllByHQL(hqlXt_jlxxxb);
	if(xt_xzqxxbList.size()>0) {
		xt_xzqxxb = (PoXT_XZQHB) xt_xzqxxbList.get(0);
		if(xt_xzqxxb.getMc()!=null) {
			dwxxbTemp = xt_xzqxxb.getMc();
		}
	 }
	if(xt_xzjdxxbList.size()>0) {
		xt_xzjdxxb=(PoXT_XZJDXXB) xt_xzjdxxbList.get(0);
		if(xt_xzjdxxb.getBz()!=null) {
			xzjdxxbTemp = xt_xzjdxxb.getBz();
		}
	 }
	if(xt_jlxxxbList.size()>0) {
		xt_jlxxxb = (PoXT_JLXXXB) xt_jlxxxbList.get(0);
		if(xt_jlxxxb.getMc()!=null) {
			jlxxxbTemp = xt_jlxxxb.getMc();
		}
	 }
	
	 if(vohjxx_czrkjbxxb.getMlph()!=null) {
		 mlphTran=vohjxx_czrkjbxxb.getMlph();
	 }
	 if(vohjxx_czrkjbxxb.getMlxz()!=null) {
		 mlxzTran= vohjxx_czrkjbxxb.getMlxz();
	 }
	 resultTemp=dwxxbTemp+xzjdxxbTemp+jlxxxbTemp+mlphTran+mlxzTran;
	 if(resultTemp.length()>=35) {
		 result = resultTemp.substring(0, 35);
	 }else {
		 result = resultTemp;
	 }
	return result;
}
/**
 * @param id
 * @param lodopId
 * @param jsonStr
 * @param j
 * @return
 */
public String queryJmhkbinfo(String id, String lodopId, String jsonStr, int j,String index,String baseUrl) {
	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	DefaultDAO dao = new DefaultDAO();
	//根据传入ryid获得表中数据
	String StrHqlById="";
	PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb =null;
	//VoHJXX_CZRKJBXXB    PoHJXX_CZRKJBXXB
	if(!id.isEmpty()) {
		StrHqlById ="from "+PoHJXX_CZRKJBXXB.class.getName()+" where  rynbid='"+id+"'";
		List hjxx_czrkjbxxbList = super.findAllByHQL(StrHqlById);
		if(hjxx_czrkjbxxbList.size()==0||hjxx_czrkjbxxbList.size()>1) {
			//返回前台，当前人数据库中未能查询到合适数据
			return "当前人数据库中未能查询到合适数据";
		}else {
			vohjxx_czrkjbxxb=(PoHJXX_CZRKJBXXB) hjxx_czrkjbxxbList.get(0);
			if(lodopId.equals("jmhkb_sy")) {
				PoHJXX_CZRKJBXXB_DYLS hjxx_czrkjbxxb = new PoHJXX_CZRKJBXXB_DYLS();
	 			// 常住人口打印历史表 插入打印记录
	 			Timestamp d = new Timestamp(System.currentTimeMillis()); 
	 			hjxx_czrkjbxxb.setHhnbid(vohjxx_czrkjbxxb.getHhnbid());
	 			hjxx_czrkjbxxb.setDyyh(this.getUserInfo().getYhdlm());
	 			hjxx_czrkjbxxb.setDysj(d);
	 			hqsjDao.insertObject(hjxx_czrkjbxxb);
			}
			//查询套打表中数据
			String strHQL ="";
			String strHxxhql="";
			PoLODOP poldop = null;
			PoHJXX_HXXB pohjxx_hxxb =null;
			String result = "";
			String hlxTran ="";//户类型转换后的值
			String hzxmTran ="";//户主姓名
			String addressTran="";
			String pcsTran="";
			String jcwhTran="";
			String yhzgxTran="";//与户主关系
			String xbTran="";//性别
			String csdssxqTran="";//出生地
			String mzTran="";//名族  
			String jgssxqTran ="";//籍贯
			String csrqTran="";//出生日期
			String xxTran="";//血型
			String whcdTran="";//文化程度
			String hyzkTran ="";// 婚姻状况
			String byzkTran="";//兵役状况
			String hsqlTran = "";//
			String hyqlTran ="";
			String hssxqqlTran ="";
			String hslbzTran="";
			String hylbzTran="";
			String hsssqlbzTran="";
			String gxsjTran ="";
			String qfsjTran ="";
			String zjxyTran ="";
			strHQL = "from " + PoLODOP.class.getName() + "" +
					" where lodopId = '"+lodopId+"' and zxbz = '0' ";
			strHxxhql="from "+PoHJXX_HXXB.class.getName()+" where hhnbid ='"+vohjxx_czrkjbxxb.getHhnbid()+"'";
			 List lodopList =super.findAllByHQL(strHQL);
			 List pohjxx_hxxbList=super.findAllByHQL(strHxxhql);
		     if (lodopList != null && lodopList.size() ==1&&pohjxx_hxxbList != null && pohjxx_hxxbList.size() ==1) {
		    	 poldop = (PoLODOP) lodopList.get(0);
		    	 pohjxx_hxxb = (PoHJXX_HXXB) pohjxx_hxxbList.get(0);
		    	 hlxTran=getMcByClsb("1007", vohjxx_czrkjbxxb.getHlx());
		    	 hzxmTran =getHzxm(vohjxx_czrkjbxxb);
		    	 pcsTran = getPcs(vohjxx_czrkjbxxb.getPcs());
		    	 jcwhTran = getJcwh(vohjxx_czrkjbxxb.getJcwh());
		    	 yhzgxTran =getMcByClsb("8006", vohjxx_czrkjbxxb.getYhzgx());
		    	 xbTran=getMcByClsb("8003", vohjxx_czrkjbxxb.getXb());
		    	 csdssxqTran = getCsdInfo(vohjxx_czrkjbxxb.getCsdssxq());
		    	 mzTran=getMcByClsb("8001", vohjxx_czrkjbxxb.getMz());
		    	 jgssxqTran= getCsdInfo(vohjxx_czrkjbxxb.getJgssxq());
		    	 csrqTran=transStringToDateformatter(vohjxx_czrkjbxxb.getCsrq(),3);
		    	 xxTran=getMcByClsb("1044", vohjxx_czrkjbxxb.getXx());
		    	 whcdTran =getMcByClsb("8002", vohjxx_czrkjbxxb.getWhcd());
		    	 hyzkTran=getMcByClsb("8007", vohjxx_czrkjbxxb.getHyzk());
		    	 if(vohjxx_czrkjbxxb.getHyzk()!=null) {
		    		 if("10".equals(vohjxx_czrkjbxxb.getHyzk())) {
		    			 /**
		    				2018.09.30
		    				修改	刁杰
		    				婚姻状况为未婚(10)时,不显示未婚
		    			  */
		    			 hyzkTran="";
		    		 }else {
		    			 hyzkTran=getMcByClsb("8007", vohjxx_czrkjbxxb.getHyzk());
		    		 }
		    	 }else {
		    		 hyzkTran="";
		    	 }
		    	 byzkTran =getMcByClsb("1046", vohjxx_czrkjbxxb.getByzk());
		    	 hsqlTran =transStringToDateformatter(vohjxx_czrkjbxxb.getHsql(),2);
		    	 hyqlTran = getMcByClsb("1020", vohjxx_czrkjbxxb.getHyql());
		    	 hssxqqlTran = getMcByHyql(vohjxx_czrkjbxxb,2);
//		    	 String fromAddress ="";
//		    	 if(vohjxx_czrkjbxxb.getHssxqql()!=null) {
//	    	        PoXT_XZQHB qh = super.get(PoXT_XZQHB.class, vohjxx_czrkjbxxb.getHssxqql());
//	    	        if(qh!=null)
//	    	        	fromAddress = fromAddress+qh.getMc();
//	    		}
//	    		if(vohjxx_czrkjbxxb.getHxzql()!=null) {
//	    			fromAddress = fromAddress+vohjxx_czrkjbxxb.getHxzql();
//	    		}
//	    		if(vohjxx_czrkjbxxb.getHslbz()!=null) {
//	    			hssxqqlTran="从"+fromAddress+"迁来";
//	    		}
		    		
		    		
		    	 hslbzTran=transStringToDateformatter(vohjxx_czrkjbxxb.getHslbz(),2);
		    	 if(CommonUtil.isNotEmpty(vohjxx_czrkjbxxb.getHylbz())&&(vohjxx_czrkjbxxb.getHylbz().equals("0101")||vohjxx_czrkjbxxb.getHylbz().equals("0108")||vohjxx_czrkjbxxb.getHylbz().equals("0109"))){
		    		 hylbzTran = getMcByClsb("1022", vohjxx_czrkjbxxb.getHylbz());
		    	 }else {
		    		 hylbzTran = getMcByClsb("1056", vohjxx_czrkjbxxb.getHylbz());
		    	 }
				 if(vohjxx_czrkjbxxb.getHxzlbz()!=null&&CommonUtil.isNotEmpty(vohjxx_czrkjbxxb.getHxzlbz().trim())) {
					 if(vohjxx_czrkjbxxb.getHsssqlbz()!=null&&CommonUtil.isNotEmpty(vohjxx_czrkjbxxb.getHsssqlbz().trim())) {
						 PoXT_XZQHB xzqhb = super.get(PoXT_XZQHB.class, vohjxx_czrkjbxxb.getHsssqlbz());
						 String xzqhMc =xzqhb!=null?xzqhb.getMc():"";
						 if(vohjxx_czrkjbxxb.getHxzlbz().indexOf("安徽省")>=0) {
							 hsssqlbzTran="从"+vohjxx_czrkjbxxb.getHxzlbz()+"迁来";
						 }else {
							 hsssqlbzTran="从"+xzqhMc+vohjxx_czrkjbxxb.getHxzlbz()+"迁来";
						 }
					 }else {
						 //hsssqlbzTran="从"+super.get(PoXT_XZQHB.class, vohjxx_czrkjbxxb.getHsssqlbz()).getMc()+"迁来";
						 hsssqlbzTran="从"+vohjxx_czrkjbxxb.getHxzlbz()+"迁来";
					 }
					 
				 }else {
					 if(vohjxx_czrkjbxxb.getHsssqlbz()!=null&&CommonUtil.isNotEmpty(vohjxx_czrkjbxxb.getHsssqlbz().trim())) {
						 PoXT_XZQHB xzqhb = super.get(PoXT_XZQHB.class, vohjxx_czrkjbxxb.getHsssqlbz());
						 hsssqlbzTran="从"+xzqhb!=null?xzqhb.getMc():""+"迁来";
					 }else {
						 hsssqlbzTran="";
					 }
				 }
		    	 zjxyTran=getMcByClsb("1045", vohjxx_czrkjbxxb.getZjxy());
		    	 //cslb 1005    户别        8003 性别       8001   民族     8006  与户主关系     8007  婚姻状况  8002 文化程度   1046 兵役状况     1044血型
		    	 //地址获取
		    	 addressTran = getAddressByQhdm(vohjxx_czrkjbxxb);
		    	 if(addressTran!=null&&vohjxx_czrkjbxxb.getHslbz()!=null&&CommonUtil.isNotEmpty(vohjxx_czrkjbxxb.getHslbz().trim())) {
		    		 hylbzTran="从"+addressTran+"迁来";
		    	 }
		    	 
		    	//hylbzTran=getMcByClsb("1056", vohjxx_czrkjbxxb.getHylbz());
				gxsjTran = transStringToDateformatter(vohjxx_czrkjbxxb.getYxqxqsrq(),3);
				qfsjTran = vohjxx_czrkjbxxb.getGxsj();
		 		result += poldop.getNr();
		 		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		 		Date date = new Date();
		 		result = result.replace("{yyyy}",  String.format("%tY", date));
	 			result = result.replace("{mm}", String.format("%tm", date));
	 			result = result.replace("{dd}",String.format("%td", date));
		 		JSONObject jo = JSONObject.parseObject(jsonStr);
		 		JSONObject jo1 = JSONObject.parseObject(jo.getString("printset"));
		 		JSONObject jo5=new JSONObject();
		 		JSONObject jo6=new JSONObject();
		 		JSONObject jo7=new JSONObject();
		 		JSONObject jo8=new JSONObject();
		 		JSONObject jo9=new JSONObject();
		 		//"jmhkb_sy"  "jmhkb_bm"  "jmhkb_jthfs_sy"   "jmhkb_jthfshky"   "jmhkb_csyy"
		 		if(lodopId.equals("jmhkb_sy")) {
		 			jo5 = JSONObject.parseObject(jo1.getString("printset_5"));
		 		}else if(lodopId.equals("jmhkb_bm")) {
		 			jo6 = JSONObject.parseObject(jo1.getString("printset_6"));
		 		}else if(lodopId.equals("jmhkb_jthfs_sy")) {
		 			jo7 = JSONObject.parseObject(jo1.getString("printset_7"));
		 		}else if(lodopId.equals("jmhkb_jthfshky")) {
		 			jo8 = JSONObject.parseObject(jo1.getString("printset_8"));
		 		}else if(lodopId.equals("jmhkb_csyy")) {
		 			jo9 = JSONObject.parseObject(jo1.getString("printset_9"));
		 		}
		 		
		 		if(lodopId.equals("jmhkb_ny")) {
		 			hlxTran=getMcByClsb("1005", vohjxx_czrkjbxxb.getHb());
		 			result = result.replace("{yhzgx}", yhzgxTran);
		 			result = result.replace("{hlx}", hlxTran);
					result = result.replace("{xb}", xbTran);
					result = result.replace("{csdssxq}", csdssxqTran);
					result = result.replace("{mz}", mzTran);
					result = result.replace("{jgssxq}", jgssxqTran);
		 			result = result.replace("{csrq}", csrqTran);
					result = result.replace("{zjxy}", zjxyTran);
					result = result.replace("{xx}", xxTran);
		 			result = result.replace("{whcd}", whcdTran);
		 			result = result.replace("{hyzk}", hyzkTran);
		 			result = result.replace("{byzk}", byzkTran);
		 			result = result.replace("{pcs}", pcsTran);
					result = result.replace("{hsql}", hsqlTran);
					result = result.replace("{hssxqql}", hssxqqlTran);
					result = result.replace("{hslbz}", hslbzTran);
		 			result = result.replace("{hsssqlbz}", hsssqlbzTran);
		 			result = result.replace("{dlm}", CommonUtil.isEmpty(this.getUserInfo().getYhxm())?"":this.getUserInfo().getYhxm());
		 			result = result.replace("{tubiao}", baseUrl+"/rkxt/images/dunpai.bmp");
		 			result = generrateFromObj(vohjxx_czrkjbxxb, result);
		 			hqsjDao.saveHjxxDyxxb(vohjxx_czrkjbxxb,lodopId,null,String.format("%tY", date));
		 			if(CommonUtil.isNotEmpty(index)&&!index.equals("null")) {
		 				JSONObject hzywjo = JSONObject.parseObject(index);
		 				String hzywid = hzywjo.getString("hzywid");
		 				String ywlb = hzywjo.getString("ywlb");
		 				if(CommonUtil.isNotEmpty(ywlb)&&ywlb.equals("10")&&CommonUtil.isNotEmpty(hzywid)) {
		 					 updateHz_zj_sb(hzywid);
		 				}
		 			}
		 			
		 		}else if(lodopId.equals("jmhkb_sy")&&(jo5.getString("checked")).equalsIgnoreCase("true")) {
		 			String hql = "select cast(max(a.dysj)as timestamp),count(a.hhnbid) from HJXX_CZRKJBXXB_DYLS a where a.hhnbid="+vohjxx_czrkjbxxb.getHhnbid();
		 			List list = super.executeSqlQuery(hql, new Object[] {});
		 			String sdate="";
		 			String count="";
					if(list.size()>0){
						Object[] obj = (Object[])list.get(0);
						count = obj[1].toString();
						java.util.Date dd = (java.util.Date)obj[0];
						
						java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy年MM月dd日 HH点mm分");
						sdate = format.format(new Date());
					}
		 			result = result.replace("{hlx}", hlxTran);
		 			result = result.replace("{hzxm}", hzxmTran);
		 			result = result.replace("{address}", addressTran);
		 			result = result.replace("{pcs}", pcsTran);
		 			result = result.replace("{jcwh}", jcwhTran);
		 			result = result.replace("{dlm}",CommonUtil.isEmpty(this.getUserInfo().getYhxm())?"":this.getUserInfo().getYhxm());
		 			result = result.replace("{count}", count);
		 			result = result.replace("{dysj}", sdate);
		 			result = result.replace("{tubiao}", baseUrl+"/rkxt/images/dunpai.bmp");
		 			result = generrateFromObj(vohjxx_czrkjbxxb, result);
		 			hqsjDao.saveHjxxDyxxb(vohjxx_czrkjbxxb,lodopId,null,String.format("%tY", date));
		 		
		 		}else if(lodopId.equals("jmhkb_bm")&&(jo6.getString("checked")).equalsIgnoreCase("true")) {
		 			for(int i=1;i<j;i++) {
		 				result = result.replace("{bdhzz"+(i)+"}", "");
		 				result = result.replace("{bdrq"+(i)+"}", "");
		 				result = result.replace("{dlm"+(i)+"}", "");
		 			}
		 			
		 			String hxzlbzTran = "";
		 			if(vohjxx_czrkjbxxb.getHxzlbz()!=null) {
		 				hxzlbzTran =vohjxx_czrkjbxxb.getHxzlbz();
		 			}
		 			result = result.replace("{bdhzz"+(j)+"}",addressTran);
	 				result = result.replace("{bdrq"+(j)+"}",String.format("%tY", date)+String.format("%tm", date)+String.format("%td", date));
	 				result = result.replace("{dlm"+(j)+"}",CommonUtil.isEmpty(this.getUserInfo().getYhxm())?"":this.getUserInfo().getYhxm());
		 			for(int i=j-1;i<9;i++) {
		 				result = result.replace("{bdhzz"+(i+1)+"}", "");
		 				result = result.replace("{bdrq"+(i+1)+"}", "");
		 				result = result.replace("{dlm"+(i+1)+"}", "");
		 			}
		 			result = generrateFromObj(vohjxx_czrkjbxxb, result);
		 			hqsjDao.saveHjxxDyxxb(vohjxx_czrkjbxxb,lodopId,null,String.format("%tY", date));
		 		
		 		}else if(lodopId.equals("jmhkb_jthfs_sy")&&(jo7.getString("checked")).equalsIgnoreCase("true")) {
		 			result = result.replace("{hlx}", hlxTran);
		 			result = result.replace("{hzxm}", hzxmTran);
		 			result = result.replace("{address}", addressTran);
		 			result = result.replace("{pcs}", pcsTran);
		 			result = result.replace("{jcwh}", jcwhTran);
		 			result = result.replace("{dlm}",CommonUtil.isEmpty(this.getUserInfo().getYhxm())?"":this.getUserInfo().getYhxm());
		 			result = generrateFromObj(vohjxx_czrkjbxxb, result);
		 			hqsjDao.saveHjxxDyxxb(vohjxx_czrkjbxxb,lodopId,null,String.format("%tY", date));
		 		}else if(lodopId.equals("jmhkb_jthfshky")&&(jo8.getString("checked")).equalsIgnoreCase("true")) {
		 			result = result.replace("{hlx}", hlxTran);
		 			result = result.replace("{hzxm}", hzxmTran);
		 			result = result.replace("{yhzgx}", yhzgxTran);
		 			result = result.replace("{xb}", xbTran);
		 			result = result.replace("{csdssxq}", csdssxqTran);
		 			result = result.replace("{mz}", mzTran);
		 			result = result.replace("{jgssxq}", jgssxqTran);
		 			result = result.replace("{csrq}", csrqTran);
		 			result = result.replace("{xx}", xxTran);
		 			result = result.replace("{whcd}", whcdTran);
		 			result = result.replace("{hyzk}", hyzkTran);
		 			result = result.replace("{byzk}", byzkTran);
		 			result = result.replace("{address}", addressTran);
		 			result = result.replace("{gxsj}", gxsjTran);
		 			result = result.replace("{pcs}", pcsTran);
		 			result = result.replace("{jcwh}", jcwhTran);
		 			result = result.replace("{hsql}", hsqlTran);
		 			result = result.replace("{hyql}", hyqlTran);
		 			result = result.replace("{hssxqql}", hssxqqlTran);
		 			result = result.replace("{hslbz}", hslbzTran);
		 			result = result.replace("{hylbz}", hylbzTran);
		 			result = result.replace("{hsssqlbz}", hsssqlbzTran);
		 			for(int i=0;i<9;i++) {
		 				result = result.replace("{bdhzz"+(i+1)+"}", "");
		 				result = result.replace("{bdrq"+(i+1)+"}", "");
		 			}
		 			result = generrateFromObj(vohjxx_czrkjbxxb, result);
		 			hqsjDao.saveHjxxDyxxb(vohjxx_czrkjbxxb,lodopId,null,String.format("%tY", date));
		 		}
		     }
		     return result;
		}		
	}else {
		return "查询用户，表中不存在";
	}
}
public String generrateFromObj(Object vohjxx_czrkjbxxb, String result) {
	Class c1= vohjxx_czrkjbxxb.getClass();
	Field[] fields1  = c1.getDeclaredFields();
	for(Field f : fields1) {
		// 获取原来的访问控制权限 
		boolean accessFlag = f.isAccessible(); 
		// 修改访问控制权限 
		f.setAccessible(true); 
		// 获取在对象f中属性fields[i]对应的对象中的变量  
		Object o;
		try {
			o = f.get(vohjxx_czrkjbxxb);
			String tempName =f.getName();
			if(result.contains(tempName)&&(o!=null)) {
				
				try {
					System.out.println(tempName+":"+o.toString());
					result = result.replace("{"+tempName+"}", o.toString());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}else {
					result = result.replace("{"+tempName+"}", "");
			}
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} 
		// 恢复访问控制权限  
	    f.setAccessible(accessFlag); 
	}
	return result;
}
public String queryHjzminfo(String id, String lodopId, String jsonStr,String index) {
	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//根据传入ryid获得表中数据
	String StrHqlById="";
	PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb =null;
	//VoHJXX_CZRKJBXXB    PoHJXX_CZRKJBXXB
	if(!id.isEmpty()) {
		StrHqlById ="from "+PoHJXX_CZRKJBXXB.class.getName()+" where  rynbid='"+id+"'";
		List hjxx_czrkjbxxbList = super.findAllByHQL(StrHqlById);
		if(hjxx_czrkjbxxbList.size()==0||hjxx_czrkjbxxbList.size()>1) {
			//返回前台，当前人数据库中未能查询到合适数据
			return "当前人数据库中未能查询到合适数据";
		}else {
			vohjxx_czrkjbxxb=(PoHJXX_CZRKJBXXB) hjxx_czrkjbxxbList.get(0);
			//查询套打表中数据
			String strHQL ="";
			String strHxxhql="";
			PoLODOP poldop = null;
			PoHJXX_HXXB pohjxx_hxxb =null;
			String result = "";
			String hbTran ="";//户类型转换后的值
			String hzxmTran ="";//户主姓名
			String yhzgxTran="";//与户主关系
			String xbTran="";//性别
			String hyzkTran ="";// 婚姻状况
			String mzTran="";//名族  
			String whcdTran="";//文化程度
			String byzkTran="";//兵役状况
			String xxTran="";//血型
			String csrqTran="";//出生日期
			String csdssxqTran="";//出生地
			String cszqfrqTran ="";//公民出生证签发日期
			String jgssxqTran ="";//籍贯
			//String yxqxqsrqTran ="";//居民身份证签发日期
			String hsqlTran = "";//
			String addressTran="";
			String hyqlTran ="";
			String hssxqqlTran ="";
			String hslbzTran="";
			String hylbzTran="";
			String hsssqlbzTran="";
			String qcrqTran = "";
			String qczxlbTran = "";
			String pcsTran="";
			String jcwhTran="";
			String jhryjhgxTran="";
			String jhrejhgxTran="";
			String yhkxzmcTran="";
			int count=0;
			strHQL = "from " + PoLODOP.class.getName() + "" +
					" where lodopId = '"+lodopId+"' and zxbz = '0' ";
			strHxxhql="from "+PoHJXX_HXXB.class.getName()+" where hhnbid ='"+vohjxx_czrkjbxxb.getHhnbid()+"'";
			
			
			 List lodopList =super.findAllByHQL(strHQL);
			 List pohjxx_hxxbList=super.findAllByHQL(strHxxhql);
		     if (lodopList != null && lodopList.size() ==1&&pohjxx_hxxbList != null && pohjxx_hxxbList.size() ==1) {
		    	 poldop = (PoLODOP) lodopList.get(0);
		    	 pohjxx_hxxb = (PoHJXX_HXXB) pohjxx_hxxbList.get(0);
		    	 hbTran=getMcByClsb("1005", vohjxx_czrkjbxxb.getHb());
		    	 hzxmTran =getHzxm(vohjxx_czrkjbxxb);
		    	 yhzgxTran =getMcByClsb("8006", vohjxx_czrkjbxxb.getYhzgx());
		    	 xbTran=getMcByClsb("8003", vohjxx_czrkjbxxb.getXb());
		    	 hyzkTran=getMcByClsb("8007", vohjxx_czrkjbxxb.getHyzk());
		    	 mzTran=getMcByClsb("8001", vohjxx_czrkjbxxb.getMz());
		    	 whcdTran =getMcByClsb("8002", vohjxx_czrkjbxxb.getWhcd());
		    	 byzkTran =getMcByClsb("1046", vohjxx_czrkjbxxb.getByzk());
		    	 xxTran=getMcByClsb("1044", vohjxx_czrkjbxxb.getXx());
		    	 csdssxqTran = getCsdInfo(vohjxx_czrkjbxxb.getCsdssxq());
		    	 jgssxqTran= getCsdInfo(vohjxx_czrkjbxxb.getJgssxq());
		    	 //cslb 1005    户别        8003 性别       8001   民族     8006  与户主关系     8007  婚姻状况  8002 文化程度   1046 兵役状况     1044血型
		    	 //地址获取
		    	 addressTran = getAddressByQhdm(vohjxx_czrkjbxxb);
		    	 hyqlTran = getMcByClsb("1020", vohjxx_czrkjbxxb.getHyql());
		    	 hssxqqlTran = getCsdInfo(vohjxx_czrkjbxxb.getHssxqql());
		    	csrqTran=transStringToDateformatter(vohjxx_czrkjbxxb.getCsrq(),0);
		    	cszqfrqTran=transStringToDateformatter(vohjxx_czrkjbxxb.getCszqfrq(),1);
		    	//yxqxqsrqTran = transStringToDateformatter(vohjxx_czrkjbxxb.getYxqxqsrq(),1);
		    	hsqlTran =transStringToDateformatter(vohjxx_czrkjbxxb.getHsql(),2);
		    	hslbzTran=transStringToDateformatter(vohjxx_czrkjbxxb.getHslbz(),2);
				hylbzTran=getMcByClsb("1056", vohjxx_czrkjbxxb.getHylbz());
				hsssqlbzTran=getCsdInfo(vohjxx_czrkjbxxb.getHsssqlbz());
				qcrqTran = transStringToDateformatter(vohjxx_czrkjbxxb.getQcrq(),2);
				qczxlbTran = getMcByClsb("1026", vohjxx_czrkjbxxb.getQczxlb());
		    	pcsTran = getPcs(vohjxx_czrkjbxxb.getPcs());
		    	jcwhTran = getJcwh(vohjxx_czrkjbxxb.getJcwh());
		    	jhryjhgxTran =getMcByClsb("8006", vohjxx_czrkjbxxb.getJhryjhgx());
				jhrejhgxTran =getMcByClsb("8006", vohjxx_czrkjbxxb.getJhrejhgx());
		 		result += poldop.getNr();
		 		JSONObject jo = JSONObject.parseObject(jsonStr);
		 		JSONObject jo1 = JSONObject.parseObject(jo.getString("printset"));
		 		//printset_10   打印照片
		 		JSONObject jo10 = JSONObject.parseObject(jo1.getString("printset_10"));
		 		if(lodopId.equalsIgnoreCase("hjzm")&&jo10.getString("checked").equalsIgnoreCase("true")){
		 			result = result.replace("{zp}", getZp(vohjxx_czrkjbxxb));
		 		}else if(lodopId.equalsIgnoreCase("hjzm")&&jo10.getString("checked").equalsIgnoreCase("false")){
		 			result = result.replace("{zp}", "");
		 		}
		 		//printset_11   打印单位 
		 		JSONObject jo11 = JSONObject.parseObject(jo1.getString("printset_11"));
		 		if(lodopId.equalsIgnoreCase("hjzm")&&jo11.getString("checked").equalsIgnoreCase("true")){
		 			String fwcs =vohjxx_czrkjbxxb.getFwcs();
		 			if(fwcs==null) {
		 				result = result.replace("{fwcs}", "");
		 			}else {
		 				result = result.replace("{fwcs}",fwcs);
		 			}
		 			result = result.replace("{fwcs}", vohjxx_czrkjbxxb.getFwcs()+"");
		 		}else if(lodopId.equalsIgnoreCase("hjzm")&&jo11.getString("checked").equalsIgnoreCase("false")){
		 			result = result.replace("{fwcs}", "");
		 		}
				
		 		//printset_12   打印户成员信息
		 		JSONObject jo12 = JSONObject.parseObject(jo1.getString("printset_12"));
		 		//printset_18 打印注销人员信息	
		 		JSONObject jo18 = JSONObject.parseObject(jo1.getString("printset_18"));
		 		int type =1;
		 		if(jo18.getString("checked").equalsIgnoreCase("true")){
		 			type =2;
		 		}
		 		List familyMember =getFamilyMember(vohjxx_czrkjbxxb.getHhnbid(),type);
		 		count= familyMember.size();
		 		if(lodopId.equalsIgnoreCase("hjzm")&&jo12.getString("checked").equalsIgnoreCase("true")&&count>0){
		 			result = result.replace("{hcylb}", "户成员信息");
		 			result = result.replace("{xmtitle1}", "姓名");
		 			result = result.replace("{xbtitle1}", "性别");
		 			result = result.replace("{jtgxtitle1}", "家庭关系");
		 			result = result.replace("{xmtitle2}", "姓名");
		 			result = result.replace("{xbtitle2}", "性别");
		 			result = result.replace("{jtgxtitle2}", "家庭关系");
		 			
		 			result = result.replace("{gongyou}", "共有");
		 			result = result.replace("{ren1}", "人");
		 			result = result.replace("{daying}", "打印");
		 			result = result.replace("{ren2}", "人");
		 			result = result.replace("{qrycount}", count+"");
		 			if(count>10) {
		 				result = result.replace("{recordcount}", 10+"");
		 			}else {
		 				result = result.replace("{recordcount}", count+"");
		 			}
		 			int temp =0;
		 			for(int i=0;i<familyMember.size();i++) {
		 				PoHJXX_CZRKJBXXB family =new PoHJXX_CZRKJBXXB();
		 				family=(PoHJXX_CZRKJBXXB) familyMember.get(i);
		 				//if(family.getRynbid().longValue()!=Long.parseLong(id)) {
		 					temp=temp+1;
		 					result = result.replace("{xm"+temp+"}", CommonUtil.isEmpty(family.getXm())?"":family.getXm());
		 					result = result.replace("{xb"+temp+"}", getMcByClsb("8003", family.getXb()));
		 					result = result.replace("{yhzgx"+temp+"}",getMcByClsb("8006", family.getYhzgx()));
		 				//}
		 			}
		 			//十个家庭成员列表不满足人数的为空
		 			for(int i=temp+1;i<=10;i++){
		 				result = result.replace("{xm"+i+"}","");
	 					result = result.replace("{xb"+i+"}", "");
	 					result = result.replace("{yhzgx"+i+"}","");
		 			}
		 		}else {
		 			result = result.replace("{hcylb}", "");
		 			result = result.replace("{xmtitle1}", "");
		 			result = result.replace("{xbtitle1}", "");
		 			result = result.replace("{jtgxtitle1}", "");
		 			result = result.replace("{xmtitle2}", "");
		 			result = result.replace("{xbtitle2}", "");
		 			result = result.replace("{jtgxtitle2}", "");
		 			
		 			result = result.replace("{gongyou}", "");
		 			result = result.replace("{ren1}", "");
		 			result = result.replace("{daying}", "");
		 			result = result.replace("{ren2}", "");
		 			result = result.replace("{qrycount}", "");
		 			result = result.replace("{recordcount}", "");
		 			//十个家庭成员列表都置为空
		 			for(int i=1;i<=10;i++){
		 				result = result.replace("{xm"+i+"}","");
	 					result = result.replace("{xb"+i+"}", "");
	 					result = result.replace("{yhzgx"+i+"}","");
		 			}
		 		}
		 		//printset_13   打印户号
		 		JSONObject jo13 = JSONObject.parseObject(jo1.getString("printset_13"));
		 		if(lodopId.equalsIgnoreCase("hjzm")&&jo13.getString("checked").equalsIgnoreCase("true")){
		 			result = result.replace("{hh}", CommonUtil.isEmpty(vohjxx_czrkjbxxb.getHh())?"":vohjxx_czrkjbxxb.getHh());
		 		}else if(lodopId.equalsIgnoreCase("hjzm")&&jo13.getString("checked").equalsIgnoreCase("false")) {
		 			result = result.replace("{hh}", "");
		 		}
		 		//printset_15 打印婚姻状况
		 		JSONObject jo15 = JSONObject.parseObject(jo1.getString("printset_15"));
		 		if(lodopId.equalsIgnoreCase("hjzm")&&jo15.getString("checked").equalsIgnoreCase("true")){
		 			result = result.replace("{hyzk}", hyzkTran);
		 		}else if(lodopId.equalsIgnoreCase("hjzm")&&jo15.getString("checked").equalsIgnoreCase("false")){
		 			result = result.replace("{hyzk}", "");
		 		}
		 		//printset_16  打印变动信息
		 		JSONObject jo16 = JSONObject.parseObject(jo1.getString("printset_16"));
		 		if(lodopId.equalsIgnoreCase("hjzm")&&jo16.getString("checked").equalsIgnoreCase("true")){
		 			//printset_14  打印变动原因 
			 		JSONObject jo14 = JSONObject.parseObject(jo1.getString("printset_14"));
			 		if((jo14.getString("checked")).equalsIgnoreCase("true")){
			 			String rybdStrHql ="from "+PoHJTJ_RYBDXXB.class.getName()+" where 1=1 and rzjs='1' and ryid='"+vohjxx_czrkjbxxb.getRyid()+"' and rynbid ='"+vohjxx_czrkjbxxb.getRynbid()+"' ";
			 			List hjtj_rybdxxbList = super.findAllByHQL(rybdStrHql);
						PoHJTJ_RYBDXXB bd_hjtj_rybdxxb = new PoHJTJ_RYBDXXB();
						if(hjtj_rybdxxbList.size()>0) {
							bd_hjtj_rybdxxb = (PoHJTJ_RYBDXXB) hjtj_rybdxxbList.get(0);
							result = result.replace("{bdyy}",getMcByClsb("1056", vohjxx_czrkjbxxb.getHyql()));
							
						}else {
							result = result.replace("{bdyy}", "");
						}
			 		}else if((jo14.getString("checked")).equalsIgnoreCase("false")) {
			 			result = result.replace("{bdyy}", "");
			 		}
			 		result = result.replace("{bdlx}", getMcByClsb("1053",vohjxx_czrkjbxxb.getBdfw()));
		 			result = result.replace("{bdrq}", transStringToDateformatter(vohjxx_czrkjbxxb.getHsql(),4));
		 			result = result.replace("{bdyy}", getMcByClsb("1056", vohjxx_czrkjbxxb.getHyql()));
		 			if(vohjxx_czrkjbxxb.getHssxqql()!=null) {
		 				String xz = "";
		 		        PoXT_XZQHB qh = super.get(PoXT_XZQHB.class, vohjxx_czrkjbxxb.getHssxqql());
		 		        if(qh!=null)
		 		        	xz = qh.getMc();
		 				result = result.replace("{bdsx}", xz);
		 			}else {
		 				result = result.replace("{bdsx}", "");
		 			}
		 			if(vohjxx_czrkjbxxb.getHxzql()!=null) {
		 				result = result.replace("{bdyz}", vohjxx_czrkjbxxb.getHxzql());
		 			}else {
		 				result = result.replace("{bdyz}","");
		 			}
		 			
		 		}else if(lodopId.equalsIgnoreCase("hjzm")&&jo16.getString("checked").equalsIgnoreCase("false")){
		 			result = result.replace("{bdlx}", "");
		 			result = result.replace("{bdrq}", "");
		 			result = result.replace("{bdyy}", "");
		 			result = result.replace("{bdsx}", "");
		 			result = result.replace("{bdyz}", "");
		 		}
		 		//printset_17 打印兵役情况
		 		JSONObject jo17 = JSONObject.parseObject(jo1.getString("printset_17"));
		 		if(lodopId.equalsIgnoreCase("hjzm")&&jo17.getString("checked").equalsIgnoreCase("true")){
		 			result = result.replace("{byzk}", byzkTran);
		 		}else if(lodopId.equalsIgnoreCase("hjzm")&&jo17.getString("checked").equalsIgnoreCase("false")) {
		 			result = result.replace("{byzk}", "");
		 		}
		        //printset_19 打印文化程度
		 		JSONObject jo19 = JSONObject.parseObject(jo1.getString("printset_19"));
		 		if(lodopId.equalsIgnoreCase("hjzm")&&jo19.getString("checked").equalsIgnoreCase("true")){
		 			result = result.replace("{whcd}", whcdTran);
		 		}else if(lodopId.equalsIgnoreCase("hjzm")&&jo19.getString("checked").equalsIgnoreCase("false")) {
		 			result = result.replace("{whcd}", "");
		 		}
//		 		if(vohjxx_czrkjbxxb.getYhkxzmc()!=null) {
//		 			yhkxzmcTran=vohjxx_czrkjbxxb.getYhkxzmc();
//		 		}else {
//		 			yhkxzmcTran="";
//		 		}
		 		JSONObject jo20 = JSONObject.parseObject(jo1.getString("printset_20"));
		 		if(lodopId.equalsIgnoreCase("hjzmx")&&jo20.getString("checked").equalsIgnoreCase("true")){
		 			result = result.replace("{xm}", CommonUtil.isEmpty(vohjxx_czrkjbxxb.getXm())?"":vohjxx_czrkjbxxb.getXm());
		 		}else if(lodopId.equalsIgnoreCase("hjzmx")&&jo20.getString("checked").equalsIgnoreCase("false")){
		 			result = result.replace("{xm}","*");
		 		}
		 		JSONObject jo21 = JSONObject.parseObject(jo1.getString("printset_21"));
		 		if(lodopId.equalsIgnoreCase("hjzmx")&&jo21.getString("checked").equalsIgnoreCase("true")){
		 			result = result.replace("{xb}",xbTran);
		 		}else if(lodopId.equalsIgnoreCase("hjzmx")&&jo21.getString("checked").equalsIgnoreCase("false")){
		 			result = result.replace("{xb}","*");
		 		}else {
		 			result = result.replace("{xb}", xbTran);
		 		}
		 		JSONObject jo22 = JSONObject.parseObject(jo1.getString("printset_22"));
		 		if(lodopId.equalsIgnoreCase("hjzmx")&&jo22.getString("checked").equalsIgnoreCase("true")){
		 			result = result.replace("{mz}",mzTran);
		 		}else if(lodopId.equalsIgnoreCase("hjzmx")&&jo22.getString("checked").equalsIgnoreCase("false")){
		 			result = result.replace("{mz}","*");
		 		}else {
		 			result = result.replace("{mz}", mzTran);
		 		}
		 		JSONObject jo23 = JSONObject.parseObject(jo1.getString("printset_23"));
		 		if(lodopId.equalsIgnoreCase("hjzmx")&&jo23.getString("checked").equalsIgnoreCase("true")){
		 			result = result.replace("{csrq}", CommonUtil.isEmpty(vohjxx_czrkjbxxb.getCsrq())?"":vohjxx_czrkjbxxb.getCsrq());
		 		}else if(lodopId.equalsIgnoreCase("hjzmx")&&jo23.getString("checked").equalsIgnoreCase("false")){
		 			result = result.replace("{csrq}","*");
		 		}else {
		 			result = result.replace("{csrq}", CommonUtil.isEmpty(vohjxx_czrkjbxxb.getCsrq())?"":vohjxx_czrkjbxxb.getCsrq());
		 		}
		 		JSONObject jo24 = JSONObject.parseObject(jo1.getString("printset_24"));
		 		if(lodopId.equalsIgnoreCase("hjzmx")&&jo24.getString("checked").equalsIgnoreCase("true")){
		 			result = result.replace("{address}",addressTran);
		 		}else if(lodopId.equalsIgnoreCase("hjzmx")&&jo24.getString("checked").equalsIgnoreCase("false")){
		 			result = result.replace("{address}","*");
		 		}else {
		 			result = result.replace("{address}", addressTran);
		 		}
		 		JSONObject jo25 = JSONObject.parseObject(jo1.getString("printset_25"));
		 		if(lodopId.equalsIgnoreCase("hjzmx")&&jo25.getString("checked").equalsIgnoreCase("true")){
		 			result = result.replace("{gmsfhm}",CommonUtil.isEmpty(vohjxx_czrkjbxxb.getGmsfhm())?"":vohjxx_czrkjbxxb.getGmsfhm());
		 		}else if(lodopId.equalsIgnoreCase("hjzmx")&&jo25.getString("checked").equalsIgnoreCase("false")){
		 			result = result.replace("{gmsfhm}","*");
		 		}else {
		 			result = result.replace("{gmsfhm}", CommonUtil.isEmpty(vohjxx_czrkjbxxb.getGmsfhm())?"":vohjxx_czrkjbxxb.getGmsfhm());
		 		}
		 		JSONObject jo26 = JSONObject.parseObject(jo1.getString("printset_26"));
		 		if(lodopId.equalsIgnoreCase("hjzmx")&&jo26.getString("checked").equalsIgnoreCase("true")){
		 			result = result.replace("{zp}",getZp(vohjxx_czrkjbxxb));
		 		}else if(lodopId.equalsIgnoreCase("hjzmx")&&jo26.getString("checked").equalsIgnoreCase("false")){
		 			result = result.replace("{zp}","");
		 		}
		 		JSONObject jo27 = JSONObject.parseObject(jo1.getString("printset_27"));
		 		if(lodopId.equalsIgnoreCase("hjzmx")&&jo27.getString("checked").equalsIgnoreCase("true")){
		 			result = result.replace("{pcs}", pcsTran);
		 		}else if(lodopId.equalsIgnoreCase("hjzmx")&&jo27.getString("checked").equalsIgnoreCase("false")){
		 			result = result.replace("{pcs}","*");
		 		}else {
		 			result = result.replace("{pcs}", pcsTran);
		 		}
	 			result = result.replace("{hb}", hbTran);
	 			result = result.replace("{hzxm}", hzxmTran);
	 			result = result.replace("{yhzgx}", yhzgxTran);
	 			result = result.replace("{xx}", xxTran);
	 			result = result.replace("{csdssxq}", csdssxqTran);
	 			result = result.replace("{cszqfrq}", cszqfrqTran);
	 			result = result.replace("{jgssxq}", jgssxqTran);
	 			result = result.replace("{hsql}", hsqlTran);
	 			result = result.replace("{jhryjhgx}", jhryjhgxTran);
	 			result = result.replace("{jhrejhgx}", jhrejhgxTran);
	 			result = result.replace("{hyql}", hyqlTran);
	 			result = result.replace("{hssxqql}", hssxqqlTran);
	 			result = result.replace("{hslbz}", hslbzTran);
	 			result = result.replace("{hylbz}", hylbzTran);
	 			result = result.replace("{hsssqlbz}", hsssqlbzTran);
	 			result = result.replace("{qcrq}", qcrqTran);
	 			result = result.replace("{qczxlb}", qczxlbTran);
	 			result = result.replace("{jcwh}", jcwhTran);
	 			
	 			//result = result.replace("{yhkxzmc}", yhkxzmcTran);
	 			result = result.replace("{dlm}", this.getUserInfo().getYhxm());
	 			result = result.replace("{bdlx}", "");
	 			result = result.replace("{bdrq}", "");
	 			result = result.replace("{bdsx}", "");
	 			result = result.replace("{bdyz}", "");
	 			Date date = new Date();
				result = result.replace("{yyyy}",  String.format("%tY", date));
				result = result.replace("{mm}", String.format("%tm", date));
				result = result.replace("{dd}",String.format("%td", date));
	 			result = result.replace("{dyrq}", String.format("%tY", date)+"年"+String.format("%tm", date)+"月"+String.format("%td", date)+"日");
	 			result = result.replace("{yxts}",this.getPersonDySet().getYxts()+"天");
	 			result = generrateFromObj(vohjxx_czrkjbxxb, result);
	 			hqsjDao.saveHjxxDyxxb(vohjxx_czrkjbxxb,lodopId,null,String.format("%tY", new Date()));
	 			if((lodopId.equals("hjzm")||lodopId.equals("hjzmx"))&&CommonUtil.isNotEmpty(index)&&!index.equals("null")) {
	 				JSONObject hzywjo = JSONObject.parseObject(index);
	 				String hzywid = hzywjo.getString("hzywid");
	 				String ywlb = hzywjo.getString("ywlb");
	 				if(CommonUtil.isNotEmpty(ywlb)&&ywlb.equals("13")&&CommonUtil.isNotEmpty(hzywid)) {
	 					 updateHz_zj_sb(hzywid);
	 				}
	 			}
		     }
		     return result;
		}		
	}else {
		return "查询用户，表中不存在";
	}
}

public String queryDjksybinfo(String id, String lodopId, String jsonStr, int index) {
	CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
	//int  index =5;
	//根据传入ryid获得表中数据
	String StrHqlById="";
	PoHJYW_HCYBDXXB vohjyw_hcybdxxb =null;
	String[] arrayList;
	//VoHJXX_CZRKJBXXB    PoHJXX_CZRKJBXXB
	String strHQL ="";
	PoLODOP poldop = null;
	String result = "";
	PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb;
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if (lodopList != null && lodopList.size()==1 ) {
		 poldop = (PoLODOP) lodopList.get(0);
		 result += poldop.getNr();
			if(!id.isEmpty()) {
				//id="3407000001000698351,3407000001000698306";
				arrayList= id.split(";");
				if(arrayList.length>0) {
					int changdu = arrayList.length;
					if(index>1) {
						for(int i=0;i<index-1;i++) {
							result = result.replace("{xm"+(i+1)+"}", "");
							result = result.replace("{xb"+(i+1)+"}", "");
							result = result.replace("{csrq"+(i+1)+"}", "");
							result = result.replace("{hkxz"+(i+1)+"}", "");
							result = result.replace("{rkbdqk"+(i+1)+"}","");
						}
					}
					for(int i=index-1;i<index-1+changdu;i++) {
						String StrHql ="from "+PoHJXX_CZRKJBXXB.class.getName()+" where  rynbid='"+arrayList[i-index+1]+"'";
						List hjxx_czrkjbxxbList = super.findAllByHQL(StrHql);
						if(hjxx_czrkjbxxbList.size()>0) {
							vohjxx_czrkjbxxb=(PoHJXX_CZRKJBXXB) hjxx_czrkjbxxbList.get(0);
							result = result.replace("{xm"+(i+1)+"}", CommonUtil.isEmpty(vohjxx_czrkjbxxb.getXm())?"":vohjxx_czrkjbxxb.getXm());
							result = result.replace("{xb"+(i+1)+"}", getMcByClsb("8003", vohjxx_czrkjbxxb.getXb()));
							result = result.replace("{csrq"+(i+1)+"}", transStringToDateformatter(vohjxx_czrkjbxxb.getCsrq(),2));
							result = result.replace("{hkxz"+(i+1)+"}", getMcByClsb("1007", vohjxx_czrkjbxxb.getHlx()));
							hqsjDao.saveHjxxDyxxb(vohjxx_czrkjbxxb,lodopId,null,String.format("%tY", new Date()));
						}else {
							result = result.replace("{xm"+(i+1)+"}", "");
							result = result.replace("{xb"+(i+1)+"}", "");
							result = result.replace("{csrq"+(i+1)+"}", "");
							result = result.replace("{hkxz"+(i+1)+"}", "");
						}
						StrHqlById ="from "+PoHJYW_HCYBDXXB.class.getName()+" where  rynbid='"+arrayList[i-index+1]+"'";
						List hjyw_hcybdxxbList = super.findAllByHQL(StrHqlById);
						
						if(hjyw_hcybdxxbList.size()>0) {
							vohjyw_hcybdxxb=(PoHJYW_HCYBDXXB) hjyw_hcybdxxbList.get(0);
							result = result.replace("{rkbdqk"+(i+1)+"}",getMcByClsb("1056", vohjyw_hcybdxxb.getHcybdlb()));
						}else {
							result = result.replace("{rkbdqk"+(i+1)+"}","");
						}
					}
					if(index<9) {
						for(int i=index-1+changdu;i<9;i++) {
							result = result.replace("{xm"+(i+1)+"}", "");
							result = result.replace("{xb"+(i+1)+"}", "");
							result = result.replace("{csrq"+(i+1)+"}", "");
							result = result.replace("{hkxz"+(i+1)+"}", "");
							result = result.replace("{rkbdqk"+(i+1)+"}","");
						}
					}
					return result;
				}
			}else {
				return "查询用户，表中不存在";
			}		 
	 }
	return result;
}
public String queryZdydyinfo(String id, String lodopId, String jsonStr) {
	//查询套打表中数据
	String strHQL ="";
	PoLODOP poldop = null;
	String result = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	//根据传入ryid获得表中数据
	String StrHqlById="";
	if(!id.isEmpty()) {
		Long zpid = 0l;
		PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb = super.get(PoHJXX_CZRKJBXXB.class, Long.parseLong(id));
		if(vohjxx_czrkjbxxb != null) {
			List list = super.getObjectListByHql("from PoV_HJ_BGGZXXB where ryid='"+vohjxx_czrkjbxxb.getRyid()+"' order by bggzrq desc, BGGZID");
			
			if(lodopList != null && lodopList.size() > 0) {
	    	poldop = (PoLODOP) lodopList.get(0);
	 		result += poldop.getNr();
			if(list.size()>0) {
				PoV_HJ_BGGZXXB bggzxxb = new PoV_HJ_BGGZXXB();
				for(int j =0;j<list.size();j++) {
					bggzxxb = (PoV_HJ_BGGZXXB) list.get(j);
					String bggzxm = "";
					String bggzhnr = "";
					String bggzrq = "";
					String xm ="";
					if(bggzxxb.getBggzxm()!=null) {
						bggzxm = bggzxxb.getBggzxm();
					}
					if(bggzxxb.getBggzhnr()!=null) {
						bggzhnr = bggzxxb.getBggzhnr();
					}
					if(bggzxxb.getBggzrq()!=null) {
						bggzrq = bggzxxb.getBggzrq();
					}
					if(bggzxxb.getSbryxm()!=null) {
						xm = bggzxxb.getSbryxm();
					}
					if(bggzxxb.getBggzhnr()!=null) {
						bggzhnr = bggzxxb.getBggzhnr();
					}
					String czr = this.getUserInfo().getYhxm();
 					result = result.replace("{bggzxm"+(j+1)+"}", bggzxm);
	 				result = result.replace("{bggzhnr"+(j+1)+"}", bggzhnr);
	 				result = result.replace("{bggzrq"+(j+1)+"}", bggzrq);
	 				result = result.replace("{xm"+(j+1)+"}", xm);
	 				result = result.replace("{czr"+(j+1)+"}", CommonUtil.isEmpty(czr)?"":czr);
				}
 				for(int i =list.size()+1;i<=8;i++) {
					result = result.replace("{bggzxm"+i+"}", "");
 				result = result.replace("{bggzhnr"+i+"}", "");
 				result = result.replace("{bggzrq"+i+"}", "");
 				result = result.replace("{xm"+i+"}", "");
 				result = result.replace("{czr"+i+"}", "");
				}
 				
			}else {
				for(int i =1;i<=8;i++) {
	 				result = result.replace("{bggzxm"+i+"}", "");
	 				result = result.replace("{bggzhnr"+i+"}", "");
	 				result = result.replace("{bggzrq"+i+"}", "");
	 				result = result.replace("{xm"+i+"}", "");
	 				result = result.replace("{czr"+i+"}", "");
	 			}
			}
			result = result.replace("{zp}", getZp(vohjxx_czrkjbxxb));
		}				
			
		}
		
//	     if (lodopList != null && lodopList.size() > 0) {
//	    	 poldop = (PoLODOP) lodopList.get(0);
//	 		result += poldop.getNr();
//	 		if(spywid.isEmpty()) {
//	 			for(int i =1;i<=8;i++) {
//	 				result = result.replace("{xm"+i+"}", "");
//	 				result = result.replace("{bghz"+i+"}", "");
//	 				result = result.replace("{bgrq"+i+"}", "");
//	 				result = result.replace("{sbr"+i+"}", "");
//	 				result = result.replace("{djr"+i+"}", "");
//	 			}
//	 			result = result.replace("{zp}", "");
//	 		}else {
//	 			//String strHQL1 ="select new PoHJSP_BGSPZB(a.bgzid,a.spywid,b.zdhy,a.bgqnr,a.bghnr,a.bglb,a.bgrq)  from "+PoHJSP_BGSPZB.class.getName()+"a,"+PoXT_XTCSB.class+" b where a.bggzxm =b.zdmc   a.spywid='"+spywid+"'";
//	 			ExtMap<String, Object> params = new ExtMap();
//	 			params.put("spywid", spywid);
//	 			params.put("pageIndex", 1);
//	 			params.put("start", 0);
//	 			params.put("start", 0);
//	 			params.put("pageSize", 0);
//	 			//{xm=shy, pageIndex=1, authToken=com.hzjc.hz2004.base.login.AuthToken@cc91912, start=0, limit=20, pageSize=20, ryfw=0}
//	 			List bgspzbList=super.getObjectListByHql("select new PoHJSP_BGSPZB(a.bgzid,a.spywid,b.zdhy,a.bgqnr,a.bghnr,a.bglb,a.bgrq)  from PoHJSP_BGSPZB a , PoXT_SJZDB as b where upper(a.bggzxm) = upper(b.zdmc) and a.spywid='"+spywid+"'");
//	 			//List bgspzbList =(List) super.getPageRecords("/conf/segment/common", "queryBgspzb", params);
//	 			if(bgspzbList != null && bgspzbList.size() > 0) {
//	 				//PoXT_YHXXB qh = super.get(PoXT_YHXXB.class, djrid);
//	 				for(int i =0;i<bgspzbList.size();i++) {
//	 					PoHJSP_BGSPZB vobgzb = (PoHJSP_BGSPZB) bgspzbList.get(i);
//	 					result = result.replace("{xm"+(i+1)+"}", 	.isEmpty(vobgzb.getBggzxm())?"":vobgzb.getBggzxm());
//		 				result = result.replace("{bghz"+(i+1)+"}", CommonUtil.isEmpty(vobgzb.getBghnr())?"":vobgzb.getBghnr());
//		 				result = result.replace("{bgrq"+(i+1)+"}", CommonUtil.isEmpty(vobgzb.getBgrq())?"":vobgzb.getBgrq());
//		 				result = result.replace("{sbr"+(i+1)+"}", sbrtyxm);
//		 				result = result.replace("{djr"+(i+1)+"}", djrname);
//	 				}
//	 				for(int i =bgspzbList.size()+1;i<=8;i++) {
//	 					result = result.replace("{xm"+i+"}", "");
//		 				result = result.replace("{bghz"+i+"}", "");
//		 				result = result.replace("{bgrq"+i+"}", "");
//		 				result = result.replace("{sbr"+i+"}", "");
//		 				result = result.replace("{djr"+i+"}", "");
//	 				}
//	 				PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb = new PoHJXX_CZRKJBXXB();
//	 				vohjxx_czrkjbxxb.setZpid(zpid);
//	 				result = result.replace("{zp}", getZp(vohjxx_czrkjbxxb));
//	 			}else {
//	 				for(int i =1;i<=8;i++) {
//		 				result = result.replace("{xm"+i+"}", "");
//		 				result = result.replace("{bghz"+i+"}", "");
//		 				result = result.replace("{bgrq"+i+"}", "");
//		 				result = result.replace("{sbr"+i+"}", "");
//		 				result = result.replace("{djr"+i+"}", "");
//		 			}
//	 				result = result.replace("{zp}", "");
//	 			}
//	 		}
//	     }
	     return result;
	}else {
		return "查询用户，表中不存在";
	}
}
private String queryTzdinfo(String lodopId, String jsonStr, String id, String index) {
	//查询套打表中数据
	String strHQL ="";
	String StrHqlById ="";
	PoLODOP poldop = null;
	String result = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(!id.isEmpty()) {
		 StrHqlById ="from "+PoHJXX_CZRKJBXXB.class.getName()+" where jlbz = '1' and "
		 		+ " cxbz = '0' and ryzt = '0' and rynbid='"+id+"'";
		 List zjyw_slxxbList = super.findAllByHQL(StrHqlById);
		 if(lodopList.size()==0) {
			 result ="符合条件的模板不存在！";
		 }else if(lodopList.size()>1){
			 result="符合条件的模板数目不止一条！";
		 }else if(zjyw_slxxbList.size()>0){
			 PoHJXX_CZRKJBXXB  czrkjbxxb =  (PoHJXX_CZRKJBXXB) zjyw_slxxbList.get(0);
			 poldop = (PoLODOP) lodopList.get(0);
			 result += poldop.getNr();
			 String pcsTran = getPcs(czrkjbxxb.getPcs());
			 result = result.replace("{pcs}",pcsTran);
			 result = result.replace("{xb}", DictData.getCodeName("CS_XB", czrkjbxxb.getXb()));
			 result = result.replace("{mz}", DictData.getCodeName("CS_MZ", czrkjbxxb.getMz()));
			 SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
			 Date date = new Date();
			 result = result.replace("{dyrq}", transStringToDateformatter(sdf.format(date),3));
			 result = generrateFromObj(czrkjbxxb, result);
		 }
	 }
	return result;
}
private String queryCldinfo(String lodopId, String jsonStr, String id, String index) {
	//查询套打表中数据
	String strHQL ="";
	String StrHqlById ="";
	PoLODOP poldop = null;
	String result = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(!id.isEmpty()) {
		 StrHqlById ="from "+PoZJYW_SLXXB.class.getName()+" where nbslid='"+id+"'";
		 List zjyw_slxxbList = super.findAllByHQL(StrHqlById);
		 if(lodopList.size()==0) {
			 result ="符合条件的模板不存在！";
		 }else if(lodopList.size()>1){
			 result="符合条件的模板数目不止一条！";
		 }else if(zjyw_slxxbList.size()>0){
			 PoZJYW_SLXXB  zjyw =  (PoZJYW_SLXXB) zjyw_slxxbList.get(0);
			 poldop = (PoLODOP) lodopList.get(0);
			 result += poldop.getNr();
			 String pcsTran = getPcs(zjyw.getPcs());
			 result = result.replace("{pcs}", pcsTran);
			 SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
			 Date date = new Date();
			 result = result.replace("{dyrq}", transStringToDateformatter(sdf.format(date),3));
			 result = generrateFromObj(zjyw, result);
			 zjyw.setSlzt(ZjConstant.ZJ_BLBZ_2ID_YDYCLT);
			 super.update(zjyw);
		 }
	 }
	return result;
}
private String queryCbdinfo(String lodopId, String jsonStr, String id, String index) {
	//查询套打表中数据
	String strHQL ="";
	String StrHqlById ="";
	PoLODOP poldop = null;
	String result = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(!id.isEmpty()) {
		 StrHqlById ="from "+PoHJXX_CZRKJBXXB.class.getName()+" where jlbz = '1' and "
		 		+ " cxbz = '0' and ryzt = '0' and rynbid='"+id+"'";
		 List zjyw_slxxbList = super.findAllByHQL(StrHqlById);
		 if(lodopList.size()==0) {
			 result ="符合条件的模板不存在！";
		 }else if(lodopList.size()>1){
			 result="符合条件的模板数目不止一条！";
		 }else if(zjyw_slxxbList.size()>0){
			 PoHJXX_CZRKJBXXB  czrkjbxxb =  (PoHJXX_CZRKJBXXB) zjyw_slxxbList.get(0);
			 poldop = (PoLODOP) lodopList.get(0);
			 result += poldop.getNr();
			 JSONObject jo = JSONObject.parseObject(jsonStr);
			 String pcsTran = getPcs(czrkjbxxb.getPcs());
			 result = result.replace("{pcs}",pcsTran);
			 result = result.replace("{xb}", DictData.getCodeName("CS_XB", czrkjbxxb.getXb()));
			 result = result.replace("{mz}", DictData.getCodeName("CS_MZ", czrkjbxxb.getMz()));
			 result = result.replace("{bzsj}",CommonUtil.isEmpty(jo.getString("bzsj"))?"":jo.getString("bzsj"));
			 result = result.replace("{bzdd}",CommonUtil.isEmpty(jo.getString("bzdd"))?"":jo.getString("bzdd"));
			 result = result.replace("{lxdh}",CommonUtil.isEmpty(jo.getString("lxdh"))?"":jo.getString("lxdh"));
			 result = result.replace("{dlm}",CommonUtil.isEmpty(this.getUserInfo().getYhxm())?"":this.getUserInfo().getYhxm());
			 SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
			 Date date = new Date();
			 result = result.replace("{dyrq}", transStringToDateformatter(sdf.format(date),3));
			 result = generrateFromObj(czrkjbxxb, result);
		 }
	 }
	return result;
}
private String queryWtsinfo(String lodopId, String jsonStr, String id, String index) {
	//查询套打表中数据
	String strHQL ="";
	String StrHqlById ="";
	PoLODOP poldop = null;
	String result = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(lodopList.size()==0) {
		 result ="符合条件的模板不存在！";
	 }else if(lodopList.size()>1){
		 result="符合条件的模板数目不止一条！";
	 }else if(lodopList.size()==1){
		 poldop = (PoLODOP) lodopList.get(0);
		 result += poldop.getNr();
	 }
	return result;
}
private String queryZjfafdinfo(String lodopId, String jsonStr, String id, String index) {
	//查询套打表中数据
	String strHQL ="";
	String StrHqlById ="";
	PoLODOP poldop = null;
	String result = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(lodopList.size()==0) {
		 result ="符合条件的模板不存在！";
	 }else if(lodopList.size()>1){
		 result="符合条件的模板数目不止一条！";
	 }else if(lodopList.size()==1){
		 poldop = (PoLODOP) lodopList.get(0);
		 result += poldop.getNr();
		 JSONObject jo = JSONObject.parseObject(jsonStr);
		 result = result.replace("{num}",CommonUtil.isEmpty(jo.getString("num"))?"":jo.getString("num"));
		 result = result.replace("{fjpch}",CommonUtil.isEmpty(jo.getString("fjpch"))?"":jo.getString("fjpch"));
		 result = result.replace("{ssxq}",CommonUtil.isEmpty(jo.getString("ssxq"))?"":jo.getString("ssxq"));
		 SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
		 Date date = new Date();
		 result = result.replace("{dyrq}", transStringToDateformatter(sdf.format(date),3));
	 }
	return result;
}
private String queryYdslsldjbinfo(String lodopId, String jsonStr, String id, String index) {

	//查询套打表中数据
	String strHQL ="";
	String StrHqlById ="";
	PoLODOP poldop = null;
	String result = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(lodopList.size()==0) {
		 result ="符合条件的模板不存在！";
	 }else if(lodopList.size()>1){
		 result="符合条件的模板数目不止一条！";
	 }else if(lodopList.size()==1){
		 poldop = (PoLODOP) lodopList.get(0);
		 result += poldop.getNr();
		 StrHqlById ="from "+PoYDZJ_SBXXB.class.getName()+" where 1=1 and sbxxid='"+id+"'";
		 List ydzj_sbxxbList = super.findAllByHQL(StrHqlById);
		 if(ydzj_sbxxbList.size()>0) {
			 PoYDZJ_SBXXB ydzj_sbxxb = (PoYDZJ_SBXXB) ydzj_sbxxbList.get(0);
			 result = result.replace("{xb}",getMcByClsb("8003", ydzj_sbxxb.getXb()));
			 result = result.replace("{mz}",getMcByClsb("8001", ydzj_sbxxb.getMz()));
			 result = result.replace("{slyy}",getMcByClsb("5006", ydzj_sbxxb.getSlyy()));
			 result = result.replace("{lzfs}",getMcByClsb("5009", ydzj_sbxxb.getLzfs()));
			 PoDW_DY_SET edzdysz = super.get(PoDW_DY_SET.class,this.getUserInfo().getDwdm());
			 //add by zjm 20190425 领证日期和联系电话取值
			 if(edzdysz!=null) {
				 result = result.replace("{lxdh}",CommonUtil.isEmpty(edzdysz.getLxdh_ydbz())?"":edzdysz.getLxdh_ydbz());
				 result = result.replace("{lzrq}",CommonUtil.isEmpty(edzdysz.getLzrq_ydbz())?"":edzdysz.getLzrq_ydbz());
			 }else {
				 result = result.replace("{lxdh}","");
				 result = result.replace("{lzrq}","");
			 }
			 String tempZp="";
			if(ydzj_sbxxb.getZpid()!=null) {
				String sql= "from " + PoYDZJ_SDZP.class.getName() + "" +
						" where zpid = '"+ydzj_sbxxb.getZpid()+"' ";
				 List hjxx_zplsbList =super.findAllByHQL(sql);
				 if(hjxx_zplsbList.size()>0) {
					 PoYDZJ_SDZP hjxx_zplsb = new PoYDZJ_SDZP();
					hjxx_zplsb= (PoYDZJ_SDZP) hjxx_zplsbList.get(0);
					if(hjxx_zplsb.getSdzp()!=null) {
						BASE64Encoder encode = new BASE64Encoder();
						String base64 = encode.encode(hjxx_zplsb.getSdzp());
						Pattern p = Pattern.compile("\\s*|\t|\r|\n");
					    Matcher m = p.matcher(base64);
					    tempZp="data:image/jpg;base64,"+m.replaceAll("");
					}
				 }
			}
			result = result.replace("{zp}",tempZp);
			 result = generrateFromObj(ydzj_sbxxb, result);
		 }
	 }
	return result;

}
private String queryJmsfzsldjbinfo(String lodopId, String jsonStr, String id, String index) {

	//查询套打表中数据
	String strHQL ="";
	String StrHqlById ="";
	PoLODOP poldop = null;
	String result = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(lodopList.size()==0) {
		 result ="符合条件的模板不存在！";
	 }else if(lodopList.size()>1){
		 result="符合条件的模板数目不止一条！";
	 }else if(lodopList.size()==1){
		 poldop = (PoLODOP) lodopList.get(0);
		 result += poldop.getNr();
		 StrHqlById ="from "+PoZJYW_SLXXB.class.getName()+" where 1=1 and rynbid='"+id+"' order by czsj desc";
		 List zjyw_slxxbList = super.findAllByHQL(StrHqlById);
		 if(zjyw_slxxbList.size()>0) {
			 PoZJYW_SLXXB ydzj_sbxxb = (PoZJYW_SLXXB) zjyw_slxxbList.get(0);
			 result = result.replace("{dwdm}",getPcs(ydzj_sbxxb.getDwdm()));
			 result = result.replace("{xb}",getMcByClsb("8003", ydzj_sbxxb.getXb()));
			 result = result.replace("{mz}",getMcByClsb("8001", ydzj_sbxxb.getMz()));
			 result = result.replace("{slyy}",getMcByClsb("5006", ydzj_sbxxb.getSlyy()));
			 result = result.replace("{cbr}",CommonUtil.isEmpty(this.getUserInfo().getYhxm())?"":this.getUserInfo().getYhxm());
			 result = result.replace("{zzlx}",getMcByClsb("5008", ydzj_sbxxb.getZzlx()));
			 result = result.replace("{lqfs}",getMcByClsb("5009", ydzj_sbxxb.getLqfs()));
			 PoDW_DY_SET edzdysz = super.get(PoDW_DY_SET.class,this.getUserInfo().getDwdm());
			 //add by zjm 20190425 领证日期和联系电话取值
			 if(edzdysz!=null) {
				 result = result.replace("{lxdh}",CommonUtil.isEmpty(edzdysz.getPcslxdh())?"":edzdysz.getPcslxdh());
				 if(StringUtils.equals(ydzj_sbxxb.getZzlx(), "1")) {
					 result = result.replace("{lzrq}",CommonUtil.isEmpty(edzdysz.getMzlzrq())?"":edzdysz.getMzlzrq());
				 }else if(StringUtils.equals(ydzj_sbxxb.getZzlx(), "2")) {
					 result = result.replace("{lzrq}",CommonUtil.isEmpty(edzdysz.getKzlzrq())?"":edzdysz.getKzlzrq());
				 }else {
					 result = result.replace("{lzrq}","");
				 }
				
			 }else {
				 result = result.replace("{lxdh}","");
				 result = result.replace("{lzrq}","");
			 }
			 
			 String tempZp="";
			if(ydzj_sbxxb.getZpid()!=null) {
				String sql= "from " + PoHJXX_RYZPXXB.class.getName() + "" +
						" where zpid = '"+ydzj_sbxxb.getZpid()+"' ";
				 List hjxx_zplsbList =super.findAllByHQL(sql);
				 if(hjxx_zplsbList.size()>0) {
					 PoHJXX_RYZPXXB hjxx_zplsb = new PoHJXX_RYZPXXB();
					hjxx_zplsb= (PoHJXX_RYZPXXB) hjxx_zplsbList.get(0);
					BASE64Encoder encode = new BASE64Encoder();
					String base64 = encode.encode(hjxx_zplsb.getZp());
					Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				    Matcher m = p.matcher(base64);
				    tempZp="data:image/jpg;base64,"+m.replaceAll("");
				 }
			}
			result = result.replace("{zp}",tempZp);
			 result = generrateFromObj(ydzj_sbxxb, result);
		 }
	 }
	return result;

}
private String queryGmsfzsfdinfo(String lodopId, String jsonStr, String id, String index) {

	//查询套打表中数据
	String strHQL ="";
	String StrHqlById ="";
	PoLODOP poldop = null;
	String result = "";
	strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if(lodopList.size()==0) {
		 result ="符合条件的模板不存在！";
	 }else if(lodopList.size()>1){
		 result="符合条件的模板数目不止一条！";
	 }else if(lodopList.size()==1){
		 poldop = (PoLODOP) lodopList.get(0);
		 result += poldop.getNr();
		 StrHqlById ="from "+PoZJYW_SLXXB.class.getName()+" where 1=1 and rynbid='"+id+"' order by czsj desc";
		 List zjyw_slxxbList = super.findAllByHQL(StrHqlById);
		 if(zjyw_slxxbList.size()>0) {
			 PoZJYW_SLXXB ydzj_sbxxb = (PoZJYW_SLXXB) zjyw_slxxbList.get(0);
			 result = result.replace("{pcs}",getPcs(ydzj_sbxxb.getDwdm()));
			 Date parse = null;
			try {
				parse = new SimpleDateFormat("yyyyMMdd").parse(ydzj_sbxxb.getCzsj().substring(0, 8));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			 String dateString = new SimpleDateFormat("yyyy-MM-dd").format(parse);
			 result = result.replace("{czsj}",dateString);
			 result = generrateFromObj(ydzj_sbxxb, result);
		 }
	 }
	return result;

}
private List getFamilyMember(Long hhnbid,int type) {
	if(hhnbid!=null) {
		StringBuffer hql = new StringBuffer();
		hql.append("from "+PoHJXX_CZRKJBXXB.class.getName()+" where  hhnbid='"+hhnbid+"'");
		if(type==1) {
			hql.append(" and ryzt = '0' and cxbz='0' and jlbz='1' ");
		}else {
			hql.append(" and ryzt = '0' ");
		}
		
		//hql.append(" and jlbz='1' and cxbz = '0'  order by yhzgx ,rynbid "); 
		List hjxx_czrkjbxxbList = super.findAllByHQL(hql.toString());
		return hjxx_czrkjbxxbList;
	}
	return null;
}
public String getZpInfo(Long zpid) {
	if(zpid != null) {
		String hql="from "+PoHJXX_ZPLSB.class.getName()+" where zpid='"+zpid+"'";
		List hjxx_zplsbList=super.findAllByHQL(hql);
		if(hjxx_zplsbList.size()==1) {
			PoHJXX_ZPLSB hjxx_zplsb = (PoHJXX_ZPLSB) hjxx_zplsbList.get(0);
			return hjxx_zplsb.getZp().toString();
		}
	}
	return "";
}
public String getPcs(String pcs) {
	if(CommonUtil.isNotEmpty(pcs)) {
		String hql="from "+PoXT_DWXXB.class.getName()+" where dm='"+pcs+"'";
		List xt_dwxxbList = super.findAllByHQL(hql);
		if(xt_dwxxbList.size()==1) {
			PoXT_DWXXB xt_dwxxb = (PoXT_DWXXB) xt_dwxxbList.get(0);
			return xt_dwxxb.getMc();
		}else {
			return pcs;
		}
	}
	return "";
}
public String getJcwh(String jcwh) {
	if(CommonUtil.isNotEmpty(jcwh)) {
		String hql="from "+PoXT_JWHXXB.class.getName()+" where dm='"+jcwh+"'";
		List xt_jwhList = super.findAllByHQL(hql);
		if(xt_jwhList.size()==1) {
			PoXT_JWHXXB xt_jwhxxb = (PoXT_JWHXXB) xt_jwhList.get(0);
			return xt_jwhxxb.getMc();
		}else {
			return jcwh;
		}
	}
	return "";
}
public String upperCase(String str) {  
    return str.substring(0, 1).toUpperCase() + str.substring(1);  
}
public String transStringToDateformatter(String temDate,int type) {
	if(CommonUtil.isNotEmpty(temDate)) {
		temDate = temDate.trim();
	}
	if(type==0) {
		 if(temDate!=null&&temDate.length()>=8) {
			return temDate.substring(0, 4)+"年"+temDate.substring(4, 6)+"月"+temDate.substring(6, 8)+"日"+" 时"+" 分";
		 }
	}else if(type==1) {
		 if(temDate!=null&&temDate.length()>=8) {
			 return temDate.substring(0, 4)+"/"+temDate.substring(4, 6)+"/"+temDate.substring(6,8);
		 }		
	}else if(type==2) {
		 if(temDate!=null&&temDate.length()>=8) {
			 return temDate.substring(0, 4)+""+temDate.substring(4, 6)+""+temDate.substring(6,8);
		 }		
	}else if(type==3) {
		 if(temDate!=null&&temDate.length()>=8) {
			 return temDate.substring(0, 4)+"年"+temDate.substring(4, 6)+"月"+temDate.substring(6, 8)+"日";
		 }		
	}else if(type==4) {
		 if(temDate!=null&&temDate.length()==8) {
			 return temDate;
		 }		
	}

	return "";
}
@Override
public String processValidZqzbh(String spywid, String zqzbh) {
	String hql ="from "+PoHJSP_ZQZXXB.class.getName()+" where  zjbh='皖"+zqzbh+"'";
	List zqzxxb = super.findAllByHQL(hql);
	return zqzxxb.size()+"";
}
@Override
public String processValidQyzbh(String qyzbh, String yznf) {
	String hql ="from "+PoHJSP_QYZXXB.class.getName()+" where  qyzbh='"+qyzbh+"' and yznf = '"+yznf+"'";
	List qyzxxb = super.findAllByHQL(hql);
	return qyzxxb.size()+"";
}
@Override
public String queryLodopById(String lodopId, String id) {
	String result ="";
	PoLODOP poldop = new PoLODOP();
	String strHQL = "from " + PoLODOP.class.getName() + "" +
			" where lodopId = '"+lodopId+"' and id = '"+id+"' and zxbz = '0' ";
	 List lodopList =super.findAllByHQL(strHQL);
	 if (lodopList != null && lodopList.size() ==1) {
		 poldop = (PoLODOP) lodopList.get(0);
		 result = poldop.getNr();
	 }
	return result;
}
public int getIndex(int index,String targString) {
	int count = 0;
	int number = 0;
	int result = 0;
	Pattern pattern = Pattern.compile(",");  
    Matcher findMatcher = pattern.matcher(targString);
	while(findMatcher.find()) {  
        number++;  
       if(number == index){//当“,”第二次出现时停止  
    	   result  = findMatcher.start();
       }
     }
	return result;
}
}
