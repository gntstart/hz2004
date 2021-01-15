package com.hzjc.hz2004.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.persistence.GeneratedValue;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.ServiceImpl;
import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.base.bean.SysCode;
import com.hzjc.hz2004.base.dict.DictData;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.base.sqltemplate.sql.SqlParam;
import com.hzjc.hz2004.base.sqltemplate.sql.SqlParse;
import com.hzjc.hz2004.bean.CheckQrspBean;
import com.hzjc.hz2004.constant.PublicConstant;
import com.hzjc.hz2004.dao.DAOFactory;
import com.hzjc.hz2004.dao.PojoInfo;
import com.hzjc.hz2004.po.PoSFXXB;
//import com.hzjc.hz2004.po.PoUPLOAD_TEMP;
import com.hzjc.hz2004.po.PoHJSP_HJSPZB;
import com.hzjc.hz2004.po.PoHJSP_ZQZXXB;
import com.hzjc.hz2004.po.PoHJXX_CZRKJBXXB;
import com.hzjc.hz2004.po.PoHJXX_DYXXB;
import com.hzjc.hz2004.po.PoHJXX_HXXB;
import com.hzjc.hz2004.po.PoHJXX_JWHZPLSB;
import com.hzjc.hz2004.po.PoHJXX_MLPXXXXB;
import com.hzjc.hz2004.po.PoHJXX_RHFLXXB;
import com.hzjc.hz2004.po.PoHJXX_RYZPXXB;
import com.hzjc.hz2004.po.PoPERSON_DY_SET;
import com.hzjc.hz2004.po.PoSFJFFJB;
import com.hzjc.hz2004.po.PoXT_DWXXB;
import com.hzjc.hz2004.po.PoXT_JLXXXB;
import com.hzjc.hz2004.po.PoXT_JWHXXB;
import com.hzjc.hz2004.po.PoXT_XTGNB;
import com.hzjc.hz2004.po.PoXT_XTKZCSB;
import com.hzjc.hz2004.po.PoXT_XTRZB;
import com.hzjc.hz2004.po.PoXT_XZQHB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.service.CommonService;
import com.hzjc.hz2004.service.DictService;
import com.hzjc.hz2004.service.HjService;
import com.hzjc.hz2004.service.MessageService;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.DateHelper;
import com.hzjc.util.FileUtils;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoBb;
import com.hzjc.hz2004.vo.VoHxxHqFhxx;
import com.hzjc.hz2004.vo.VoMessageRtxx;
import com.hzjc.hz2004.vo.VoMessagexx;
import com.hzjc.hz2004.vo.VoQyzdyxxHqFhxx;
import com.hzjc.hz2004.vo.VoRhflxx;
import com.hzjc.hz2004.vo.VoRhflywfhxx;
import com.hzjc.hz2004.vo.VoSfxxb;
import com.hzjc.hz2004.vo.VoZqzxx;
import com.hzjc.util.StringUtils;
import com.hzjc.wsstruts.common.ID;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.WSErrCode;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service(value = "commonService")
public class CommonServiceImpl extends ServiceImpl implements CommonService {
	@Autowired
	private HjService hjService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private DictService dictService;
	@Resource
	private QueryService queryService;
	public Connection getConnection(){
		return super.getConnection();
	}
	
	public Session getSession(){
		return super.getCurrentSession();
	}
	
	public List<?> queryAll(String hql){
		return super.getObjectListByHql(hql);
	}
	
	public  Serializable getId(Class<?> entityClass) throws DAOException{
		return super.getIdBySequence(entityClass);
	}
	
	public  Serializable getId(ID id) throws DAOException{
		return super.getIdBySequence(id);
	}
	
	public Date getSjksj() throws DAOException {
		return super.getSjksj();
	}
	
	public void insertObject(Object obj){
		super.create(obj);
	}
	
	public void deleteObject(Object obj){
		super.delete(obj);
	}
	
	public void updateObject(Object obj){
		super.update(obj);
	}
	
	public <T> T getByID(final Class<T> entityClass, final Serializable id){
		return super.get(entityClass, id);
	}
	
	public void refreshObject(Object obj, LockOptions lockMode) throws DAOException{
		super.refresh(obj, lockMode);
	}
	
	public List<?> queryLock(String hsql){
		return super.getObjectListByHqlLock(hsql, new Object[]{});
	}
	
	public Page queryPoHJXX_CZRKJBXXB(ExtMap<String,Object> params){
		//{jccz=1, tokey=571148c3528e216f4f1a7f53183e89de, xm=王红, pageIndex=1, dqbm=3407, authToken=com.hzjc.hz2004.base.login.AuthToken@2ea92b05, pageSize=20, hhnbid=3407000001000387376, kdq_gmsfhm=340222197910132643, yhdlm=HZADMIN}
		if(params.containsKey("jqcx") && params.get("jqcx").equals("1")){
			return super.getPageRecords("/conf/segment/common", "queryPoHJXX_CZRKJBXXB_JCCZ", params);
		}
		
		//不查具体户
		if(!params.containsKey("hhnbid") && "1".equals(params.getString("pageIndex"))){
			Page p = super.getPageRecords("/conf/segment/common", "queryPoHJXX_CZRKJBXXB_NEW", params);
			return p;
		}
		
		//查询具体户
		String pageSize = params.getString("pageSize");

		//第一页（只查询身份证和户主）
		Page p1 = super.getPageRecords("/conf/segment/common", "queryPoHJXX_CZRKJBXXB_NEW", params);
			
		//第二页，不查身份证和户主
		params.put("pageSize", ( (Integer.parseInt(pageSize) - p1.getList().size()) + ""));
		//特殊参数，不查询户主和身份证匹配的人
		params.put("_more", "1");
		
		Page p2 = super.getPageRecords("/conf/segment/common", "queryPoHJXX_CZRKJBXXB_NEW", params);
			
		List<Object> relist = new ArrayList<Object>();
		relist.addAll(p1.getList());
		relist.addAll(p2.getList());
		p2.setList(relist);
		p2.setTotalCount(relist.size());
		return p2;
	}
	
	public Page queryPoHJXX_LODOP(ExtMap<String,Object> params){
		String type=params.get("type").toString();
		if(type.equals("1")) {
			params.put("pageSize", 99999);
			return super.getPageRecordsOflodop("/conf/segment/common", "queryLodopf", params);

		}else if(type.equals("2")) {
			return super.getPageRecordsOflodop("/conf/segment/common", "queryLodops", params);
		}else if(type.equals("3")) {
			return super.getPageRecordsOflodop("/conf/segment/common", "queryLodopMore", params);
		}else if(type.equals("4")) {//取不到户号的情况下，只有rynbid全户查询
			return super.getPageRecordsOflodop("/conf/segment/common", "queryLodopfByRynbid", params);
		}else if(type.equals("5")) {//全户变更
			return super.getPageRecordsOflodop("/conf/segment/common", "queryLodopQfBg", params);
		}else if(type.equals("6")) {//死亡注销业务，显示新户主
			return super.getPageRecordsOflodop("/conf/segment/common", "queryLodopHz", params);
		}
		return null;
	}
	public Page queryPoHJXX_HXXB(ExtMap<String,Object> params){
		return super.getPageRecords("/conf/segment/common", "queryPoHJXX_HXXB", params);
	}
	
	public Page queryPoHJXX_RHFLXXB(ExtMap<String,Object> params){
		return super.getPageRecords("/conf/segment/common", "queryRhflxx", params);
	}
	
	public Page queryPageByConf(ExtMap<String,Object> params){
		return super.getPageRecords("/conf/segment/common", params.getString("config_key"), params);
	}
	
	/**
	 * 查询户籍审批信息
	 * @param params
	 * @return
	 */
	public Page queryPoHJSP_HJSPSQB(ExtMap<String,Object> params){
		return super.getPageRecords("/conf/segment/common", "queryPoHJSP_HJSPSQB", params);
	}
	
	/**
	 * 查询户籍审批子信息
	 * @param params
	 * @return
	 */
	public List<?> queryPoHJSP_HJSPZB(ExtMap<String,Object> params){
		return super.getObjectListByHql("/conf/segment/common", "queryPoHJSP_HJSPZB", params);
	}

	@Override
	public List<?> queryPoHZ_ZJ_SB(ExtMap<String, Object> params) {
		
		SqlParse sqlParse = new SqlParse("/conf/segment/common", "queryPoXT_XTKZCSB");
		sqlParse.bind(params);
		
		SqlParam sqlParam = sqlParse.parse();
		
		PoXT_XTKZCSB xtcs = (PoXT_XTKZCSB) super.getObject(sqlParam.getSql(), sqlParam.getParamsArrays());
		
		if(xtcs != null) {
			xtcs.getKzz();
		}

		return null;
	}
	
	public Object getDzxz(ExtMap<String, Object> params){
		//GetDzxz(TClientDataSet(wsCdsRyxx),'ssxq','jlx','mlph','mlxz','pcs','zrq','xzjd','jcwh','1009');
        String ssxq = params.getString("ssxq");
        String jlx = params.getString("jlx");
        String mlph = params.getString("mlph");
        String mlxz = params.getString("mlxz");
        String pcs = params.getString("pcs");
        //String zrq = params.getString("zrq");
        //String xzjd = params.getString("xzjd");
        //String jcwh = params.getString("jcwh");
        
        String xz = "";
        PoXT_XZQHB qh = super.get(PoXT_XZQHB.class, ssxq);
        if(qh!=null)
        	xz = qh.getMc();
        
        if(CommonUtil.isNotEmpty(jlx)){
        	PoXT_JLXXXB jlxxxb = super.get(PoXT_JLXXXB.class, jlx);
        	if(jlxxxb!=null){
        		xz += jlxxxb.getMc();
        	}
        }
        
        if(CommonUtil.isNotEmpty(mlph)){
        	xz += mlph;
        }
        
        if(CommonUtil.isNotEmpty(mlxz)){
        	xz += mlxz;
        }
        
        Map<String,String> data = new HashMap<String,String>();
        data.put("xxdz", xz);
        
        if(CommonUtil.isNotEmpty(pcs)){
        	PoXT_DWXXB dw = super.get(PoXT_DWXXB.class, pcs);
        	if(dw!=null){
        		data.put("pcsmc", dw.getMc());
        	}
        }
        
		return data;
	}
	
	public CheckQrspBean checkQrspdjyw(ExtMap<String, Object> params){
		if(!params.containsKey("sfzh") || !params.containsKey("xm"))
			throw new ServiceException("参数错误！");
		
		CheckQrspBean bean = new CheckQrspBean();
		
		String s[] = params.getString("sfzh").split(",");
		String xm[] = params.getString("xm").split(",");
		if(s.length!=xm.length)
			throw new ServiceException("参数错误！");
		
		for(int i=0;i<s.length;i++){
			if(CommonUtil.isEmpty(s[i]))
				continue;
			
			bean.getXmMap().put(s[i], xm[i]);
		}
		
		for(String sfzh: s){
			if(CommonUtil.isEmpty(sfzh))
				continue;
			
			String hsql = "from PoHJXX_CZRKJBXXB where gmsfhm=? and jlbz='1' and cxbz='0' order by qysj desc";
			List list = super.getObjectListByHql(hsql, new Object[]{sfzh});
			if(CommonUtil.isNotEmpty(list)){
				bean.getChMap().put(sfzh, list);
			}
			
			hsql = "from PoHJSP_HJSPSQB where gmsfhm=? and spjg='1' and lsbz='0'";
			List list2 = super.getObjectListByHql(hsql, new Object[]{sfzh});
			if(CommonUtil.isNotEmpty(list2)){
				bean.getSpMap().put(sfzh, list2);
			}
		}
		
		return bean;
	}
	@GeneratedValue
	public void saveHjxxDyxxb(PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb, String lodopId,String zjbh,String yznf) {
		PojoInfo  xt_slhxlbDAO = DAOFactory.createHJXX_DYXXBDAO(); //受理号序列表DAO
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar = Calendar.getInstance();
		String slsj = df.format(calendar.getTime());
		
		//打印信息表插入打印记录
		PoHJXX_DYXXB pohjxx_dyxxb = new PoHJXX_DYXXB();
		Long id = (Long) xt_slhxlbDAO.getId();
		pohjxx_dyxxb.setDyid(id);
		pohjxx_dyxxb.setRyid(vohjxx_czrkjbxxb.getRyid());
		pohjxx_dyxxb.setRynbid(vohjxx_czrkjbxxb.getRynbid());
		pohjxx_dyxxb.setGmsfhm(vohjxx_czrkjbxxb.getGmsfhm());
		pohjxx_dyxxb.setXm(vohjxx_czrkjbxxb.getXm());
		pohjxx_dyxxb.setPcs(vohjxx_czrkjbxxb.getPcs());
		pohjxx_dyxxb.setSsxq(vohjxx_czrkjbxxb.getSsxq());
		//类别 常表  01  常表背面  02  户口本首页 03  户口本04  户籍证明  05  迁移证    06  准迁证    07  死亡证明   08   临时身份证      09  临时居民身份证申领登记表     10  二代证申领登记表   11
		if(lodopId.equals("czrkdjbq")||lodopId.equals("czrkdjbthf")||lodopId.equals("czrkdjbq")) {//常住人口登记表打印
			pohjxx_dyxxb.setDylb("01");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("jmhkb_sy")) {//居民户口簿首页
			pohjxx_dyxxb.setDylb("03");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getHh());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("jmhkb_bm")) {//户口簿背面
			pohjxx_dyxxb.setDylb("04");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getHh());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("jmhkb_jthfs_sy")) {//户口簿集体户首页
			pohjxx_dyxxb.setDylb("04");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getHh());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("jmhkb_ny")) {//户口簿内页
			pohjxx_dyxxb.setDylb("04");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getHh());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("hjzm")) {//户籍证明
			pohjxx_dyxxb.setDylb("05");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("djksyb")) {//登记卡索引表
			pohjxx_dyxxb.setDylb("04");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getHh());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("zdydy")) {//自定义打印
			pohjxx_dyxxb.setDylb("");//待定   
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("zqz")) {//准迁证打印
			pohjxx_dyxxb.setDylb("07");//准迁证
			pohjxx_dyxxb.setZjbh(zjbh);//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("qyz1")||lodopId.equals("qyz2")) {//迁移证打印
			pohjxx_dyxxb.setDylb("06");//待定   
			pohjxx_dyxxb.setZjbh(zjbh);//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("hjblspb")) {//户籍补录审批表打印
			pohjxx_dyxxb.setDylb("12");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("hjscspb")) {//户籍补录审批表打印
			pohjxx_dyxxb.setDylb("13");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("swzxzm")) {//死亡注销证明打印
			pohjxx_dyxxb.setDylb("08");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("hjzmx")) {//变更审批表
			pohjxx_dyxxb.setDylb("14");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("wdjhk")) {//当事人未登记户口的证明出具
			pohjxx_dyxxb.setDylb("15");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("fhzm")) {//分户证明
			pohjxx_dyxxb.setDylb("16");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("bggzxmzm")) {//户口登记项目内容变更更正证明
			pohjxx_dyxxb.setDylb("17");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("gatdjzxzm")) {//香港、澳门、台湾定居注销户口证明
			pohjxx_dyxxb.setDylb("18");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("gwdjzxzm")) {//国外定居注销户口证明
			pohjxx_dyxxb.setDylb("19");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("sftyrxzhczm")) {//公民是否同一人的协助核查证明
			pohjxx_dyxxb.setDylb("20");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("qczxzmly")||lodopId.equals("hkzxzm")) {//迁出注销证明
			pohjxx_dyxxb.setDylb("21");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("zxqsgxzm")) {//直系亲属关系证明打印
			pohjxx_dyxxb.setDylb("22");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//户口簿放户号，其他放身份证号
		}else if(lodopId.equals("cbqdqcwb")||lodopId.equals("cbtdqcwb")) {//全程网办常表打印
			pohjxx_dyxxb.setDylb("23");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//户口簿放户号，其他放身份证号
		}
		pohjxx_dyxxb.setSlsj(slsj);//受理时间  20180702112346
		pohjxx_dyxxb.setSldw(this.getUserInfo().getDwdm());//受理单位  340700000
		pohjxx_dyxxb.setSlrid(this.getUserInfo().getYhid());//受理人id  3407000001000000001   
		pohjxx_dyxxb.setCzip(BaseContext.getUser().getIp());
		pohjxx_dyxxb.setYznf(yznf);//印证年份
		pohjxx_dyxxb.setMlpnbid(vohjxx_czrkjbxxb.getMlpnbid());//门楼牌内部ID  3407000001000250366
		pohjxx_dyxxb.setJlx(vohjxx_czrkjbxxb.getJlx());//街路巷   340702002060
		pohjxx_dyxxb.setMlph(vohjxx_czrkjbxxb.getMlph());//门楼牌号    274号
		pohjxx_dyxxb.setMlxz(vohjxx_czrkjbxxb.getMlxz());//门楼详址  ８０栋付１０６号
		pohjxx_dyxxb.setZrq(vohjxx_czrkjbxxb.getZrq());//责任区  
		pohjxx_dyxxb.setXzjd(vohjxx_czrkjbxxb.getXzjd());//乡镇街道  340702005
		pohjxx_dyxxb.setJcwh(vohjxx_czrkjbxxb.getJcwh());//居（村）委会    340702006005  
		SpringContextHolder.getCommonService().insertObject(pohjxx_dyxxb);
	}

	@Override
	public List queryZqzList(ExtMap<String, Object> params) {
		List poHJSP_ZQZXXBList = super.getPageRecordsOflodop("/conf/segment/common", "zqzList", params).getList();
		
		PoHJSP_ZQZXXB poHJSP_ZQZXXB = new PoHJSP_ZQZXXB();
		List list = new ArrayList<>();
		for(int i=0;i<poHJSP_ZQZXXBList.size();i++) {
			String hql="";
			
			poHJSP_ZQZXXB = (PoHJSP_ZQZXXB) poHJSP_ZQZXXBList.get(i);
			int arrayLength=1;
			if(poHJSP_ZQZXXB.getQyrxm2()!=null&&poHJSP_ZQZXXB.getQyrxm3()==null&&poHJSP_ZQZXXB.getQyrxm4()==null) {
				arrayLength=2;
			}else if(poHJSP_ZQZXXB.getQyrxm2()!=null&&poHJSP_ZQZXXB.getQyrxm3()!=null&&poHJSP_ZQZXXB.getQyrxm4()==null) {
				arrayLength=3;
			}else if(poHJSP_ZQZXXB.getQyrxm2()!=null&&poHJSP_ZQZXXB.getQyrxm3()!=null&&poHJSP_ZQZXXB.getQyrxm4()!=null) {
				arrayLength=4;
			}
			for(int y=0;y<arrayLength;y++) {
				VoZqzxx vozqzxx = new VoZqzxx();
				String temp = "";
				if(y==0) {
					temp = poHJSP_ZQZXXB.getQyrgmsfhm1();
				}else if(y==1) {
					temp = poHJSP_ZQZXXB.getQyrgmsfhm2();
				}else if(y==2) {
					temp = poHJSP_ZQZXXB.getQyrgmsfhm3();
				}else if(y==3) {
					temp = poHJSP_ZQZXXB.getQyrgmsfhm4();
				}
				hql ="from "+PoHJSP_HJSPZB.class.getName()+" where  spywid='"+poHJSP_ZQZXXB.getSpywid()+"' and gmsfhm='"+temp+"'";
				List voHjsp_zqzxxb = super.findAllByHQL(hql);
				try {
					BeanUtils.copyProperties(vozqzxx, voHjsp_zqzxxb.get(0));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				vozqzxx.setZqid(poHJSP_ZQZXXB.getZqid());
				list.add(vozqzxx);
			}
		}
		return list;
	}

	@Override
	public VoQyzdyxxHqFhxx queryQyzList(ExtMap<String, Object> params) {
		Long hjywid = params.getLong("hjywid");
		//hjService.queryQyzdyxx(hjywid);
		return hjService.queryQyzdyxx(hjywid);
	}	
	
	@Override
	public List<?> queryXt_bssqb(ExtMap<String, Object> params) {
		return super.getObjectListByHql("from PoXT_BSSQB where qybz='1'");
	}
	
	public List<?> getZp(ExtMap<String, Object> params){
		Long zpid = params.getLong("zpid");
		String hql = "from PoHJXX_RYZPXXB where zpid=?";
		return super.getObjectListByHql(hql, new Object[]{zpid});
	}
	
	public Page queryKDQHjspyw(ExtMap<String,Object> params){
		return super.getPageRecords("/conf/segment/common", "queryKDQHjspyw", params);
	}
	
	public VoHxxHqFhxx queryHxx(ExtMap<String, Object> params){
		List<?> list = super.getObjectListByHql("/conf/segment/common", "queryCommonHxx", params);
		if(list!=null && list.size()==1){
			Object[] obj = (Object[])list.get(0);
			PoHJXX_HXXB a = (PoHJXX_HXXB)obj[0];
			PoHJXX_MLPXXXXB b = (PoHJXX_MLPXXXXB)obj[1];
			
			VoHxxHqFhxx fh = new VoHxxHqFhxx(a,b);
			return fh;
		}
		
		return null;
	}

	@Override
	public Page queryPoHJXX_CZRKJBXXB1(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryPoHJXX_CZRKJBXXB5", params);
	}

	@Override
	public Page queryPoHJXX_CZRKJBXXB6(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryPoHJXX_CZRKJBXXB6", params);
	}
	@Override
	public List<?> getYdZp(ExtMap<String, Object> params) {
		Long zpid = params.getLong("zpid");
		String hql = "from PoHJXX_ZPLSB where zpid=?";
		return super.getObjectListByHql(hql, new Object[]{zpid});
	}

	@Override
	public Page queryMessage(ExtMap<String, Object> params) {
		// TODO Auto-generated method stub
		return super.getPageRecords("/conf/segment/common", "queryMessage", params);
//		if(params.containsKey("jqcx") && params.get("jqcx").equals("1")){
//			return super.getPageRecords("/conf/segment/common", "queryMessage", params);
//		}else {
//			return null;
//		}
	}

	@Override
	public VoMessageRtxx xxhf(ExtMap<String, Object> params) {
		VoMessagexx voMessagexx = new VoMessagexx();
		try {
			BeanUtils.copyProperties(voMessagexx, params);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return messageService.processXxhf(voMessagexx);
	}

	@Override
	public VoMessagexx deleteMessage(ExtMap<String, Object> params) {
		Long messageid = params.getLong("messageid");
		return messageService.deleteMessage(messageid);
	}

	@Override
	public VoMessagexx updateMessage(ExtMap<String, Object> params) {
		Long messageid = params.getLong("messageid");
		return messageService.updateMessage(messageid);
	}

	@Override
	public List<?> checkUnReadMessage(ExtMap<String, Object> params) {
		Long jsryhid = params.getLong("jsryhid");
		String dwdm = params.getString("dwdm");
		return messageService.checkUnReadMessage(jsryhid,dwdm);
		//return super.getPageRecords("/conf/segment/common", "checkUnReadMessage", params);
	}

	@Override
	public Page queryRhfl(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryRhfl", params);
	}

	@Override
	public VoRhflywfhxx addRhfl(ExtMap<String, Object> params) {
		String rhflxx = params.getString("VoRhflywfhxx");
		VoRhflxx voRhflxx[] = null;
		if(CommonUtil.isNotEmpty(rhflxx)){
			TypeToken<List<VoRhflxx>> typeToken = new TypeToken<List<VoRhflxx>>(){};
			List<VoRhflxx> list  = JSONUtil.getJsonData(typeToken, rhflxx);
			voRhflxx = list.toArray(new VoRhflxx[]{});
		}
		return hjService.processRhflyw(voRhflxx);
	}

	@Override
	public PoHJXX_RHFLXXB deleteRhfl(ExtMap<String, Object> params) {
		String rhflid = params.getString("rhflid");
		if(CommonUtil.isNotEmpty(rhflid)) {
			PoHJXX_RHFLXXB poHjxx_rhfkxxb= super.get(PoHJXX_RHFLXXB.class,
					Long.parseLong(rhflid));
			super.delete(poHjxx_rhfkxxb);
			return poHjxx_rhfkxxb;
		}
		return null;
	}
	
	//功能ＭＡＰ
	static private Map<String,String> gnMap = new HashMap<>();
	  
	public void saveRzxx(AuthToken user, String logcode, String logstr, long startTime, String startDate){
		 if(gnMap.size()==0){
		        synchronized(gnMap){
		        		if(gnMap.size()==0) {
		        			List<?> xtgnb = super.findAllByHQL("from PoXT_XTGNB");
		        			for(java.util.Iterator<?> it=xtgnb.iterator();it.hasNext();){
		        				PoXT_XTGNB gn = (PoXT_XTGNB)it.next();
		        				gnMap.put(gn.getGnbh(),gn.getGnmc());
		        			}
		        	}
		        }
		        gnMap.put("F1041","删除人员照片");
		 }
		 
		String lognameString = gnMap.get(logcode);
		if(CommonUtil.isEmpty(lognameString))
			return;
		
		PoXT_XTRZB rz = new PoXT_XTRZB();
        if(logstr.getBytes().length>2000){
              byte[] buff = new byte[2000];
              System.arraycopy(logstr.getBytes(),0,buff,0,2000);
              logstr = new String(buff);
         }
        
         rz.setRznr(logstr);
         String strCzJssj = StringUtils.formateDateTime();
         rz.setCzkssj(startDate);
         rz.setCzjssj(strCzJssj);
         rz.setCzyid(user.getUser().getYhid());
         rz.setYwbz(logcode);
         rz.setResource_name(lognameString);
         rz.setKhdip(user.getIp());
         rz.setRzlx(PublicConstant.RZLX_XTCZ);
         rz.setZxsj(new Long(System.currentTimeMillis()-startTime));
         rz.setOrganization_id(user.getUser().getDwdm());
         rz.setOrganization(user.getOrganize().getMc());
         rz.setCzyxm(user.getUser().getYhdlm());
         rz.setOperate_result("1");
         rz.setResource_type("1");
         
         if(rz.getResource_name()!=null){
             if (rz.getResource_name().startsWith("删除") ||
            		 rz.getResource_name().endsWith("删除")) {
            	 rz.setOperate_type("4");
             }
             else if (rz.getResource_name().indexOf("查询") >= 0) {
            	 rz.setOperate_type("1");
             }
             if (rz.getResource_name().startsWith("新增") ||
            		 rz.getResource_name().endsWith("新增")
                 || rz.getResource_name().startsWith("增加") ||
                 rz.getResource_name().endsWith("增加")) {
            	 rz.setOperate_type("2");
             }
           }
           if(rz.getYwbz()!=null && rz.getYwbz().equals("F9084")){
        	   rz.setOperate_type("0");
           }
           
		Long rzid = (Long) super.getIdBySequence(PoXT_XTRZB.class);
		rz.setRzid(rzid);
		
		super.create(rz);
	}

	@Override
	public PoSFXXB bjfyySave(ExtMap<String, Object> params) {
		String type = params.getString("type");
		String bzxjfyy = params.getString("bzxjfyy");       // bzxjfyy
		Long sfxxbid = params.getLong("sfxxbid"); 
		PoSFXXB sfxxb= super.get(PoSFXXB.class, sfxxbid);
		if(type.equals("bzx")) {
			sfxxb.setBzxjfyy(bzxjfyy);
		}
		sfxxb.setJfflag("0");
		super.update(sfxxb);
		return sfxxb;
	}

	@Override
	public PoSFXXB insertSfxxb(VoSfxxb vosfxxb) {
		PoSFXXB sfxxb= new PoSFXXB();
		try {
			String dylbValue = vosfxxb.getDylb();
			if(CommonUtil.isEmpty(dylbValue)) {
				throw new ServiceException("打印参数为空！");
			}
			if(vosfxxb.getJe()==null) {
				throw new ServiceException("打印金额为空！");
			}
			BeanUtils.copyProperties(sfxxb, vosfxxb);
			if(dylbValue.equals("jmhkb_sy")) {
				sfxxb.setDylb("03");
			}else if(dylbValue.equals("zqz")) {
				sfxxb.setDylb("07");
			}else if(dylbValue.equals("qyz1")||dylbValue.equals("qyz2")) {
				sfxxb.setDylb("06");
			}
			PoXT_YHXXB xt_yhxxb = this.getUser();
			sfxxb.setDwdm(xt_yhxxb.getDwdm());
			sfxxb.setDysj(DateHelper.formateDate("yyyyMMddHHmmss"));
			sfxxb.setCzyid(xt_yhxxb.getYhid());
			PoXT_DWXXB dwxxb = super.get(PoXT_DWXXB.class,xt_yhxxb.getDwdm());
			sfxxb.setQhdm(dwxxb.getQhdm());
			Long sfid = (Long) super.getIdBySequence(PoSFXXB.class);
			sfxxb.setSfxxbid(sfid);
			super.create(sfxxb);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return sfxxb;
	}

	@Override
	public int queryDycsByhh(ExtMap<String, Object> params) {
		String rynbid = params.getString("rynbid");
		if(CommonUtil.isNotEmpty(rynbid)) {
			PoHJXX_CZRKJBXXB hjxx_czrkjbxxb = super.get(PoHJXX_CZRKJBXXB.class, Long.parseLong(rynbid));
			String hhnbid = hjxx_czrkjbxxb.getHhnbid()+"";
			if(CommonUtil.isNotEmpty(hhnbid)) {
				String sql = "select count(*) from PoHJXX_CZRKJBXXB_DYLS where hhnbid='" + hhnbid + "'";
				return Integer.parseInt(String.valueOf(super.getCount(sql)));
			}else {
				throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
	                    "hhnbid不能为空", null);
			}
		}else {
			throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                    "rynbid不能为空", null);
		}
		
		
	}

	@Override
	public PoPERSON_DY_SET getPersonDyset(ExtMap<String, Object> params) {
		String sql = " from PoPERSON_DY_SET where yhid='" + this.getUser().getYhid() + "'";
		return (PoPERSON_DY_SET) super.getObject(sql, null);
	}

	@Override
	public Page validHhid(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "validHhid", params);
	}

	@Override
	public PoHJXX_JWHZPLSB uploadZp(MultipartHttpServletRequest params) throws IOException {
		PoHJXX_JWHZPLSB jwhzplsb= new PoHJXX_JWHZPLSB();
		MultipartFile logoFile = params.getFile("logoFile");
		String zpType = params.getParameter("zpType");
		String jwhdm = params.getParameter("dm");
		PoXT_JWHXXB jwhxxb = super.get(PoXT_JWHXXB.class, jwhdm);
		String sql = " from PoHJXX_JWHZPLSB where jwhdm = '"+jwhdm+"' and zpType = '"+zpType+"' ";
		List<PoHJXX_JWHZPLSB> jwhzplsbList = (List<PoHJXX_JWHZPLSB>) super.getObjectListByHql(sql);
		FileInputStream inputStream;
		try {
//			inputStream = new FileInputStream(realPath);
//			int i = inputStream.available();
//			//byte数组用于存放图片字节数据
//			byte[] buff = new byte[i];
//			 byte[] imageByte;
//			 File file1 = new File(realPath);//需要转换成二进制字节流的文件的绝对路径
//			 FileInputStream fls = new FileInputStream(file1);
//			 imageByte = new byte[ (int) file1.length() ];
			 
			 
			if(jwhzplsbList.size()==0) {
				PojoInfo  hjxx_jwhzplsbDAO = DAOFactory.createHJXX_JWHZPLSBDAO();
				Long id = (Long) hjxx_jwhzplsbDAO.getId();
				jwhzplsb.setJwhdm(jwhdm);
				jwhzplsb.setJwhzpid(id);
				jwhzplsb.setCzsj(StringUtils.getServiceTime());
				jwhzplsb.setCzrid(this.getUser().getYhid());
				jwhzplsb.setZpType(zpType);
				jwhzplsb.setZp(logoFile.getBytes());
				super.create(jwhzplsb);
				if(zpType.equals("1")) {
					jwhxxb.setHzzpid(id);
				}else if(zpType.equals("2")) {
					jwhxxb.setQmzpid(id);
				}
				jwhxxb.setBdsj(StringUtils.getServiceTime());
				jwhxxb.setCzrid(this.getUser().getYhid());
			}else {
				jwhzplsb = jwhzplsbList.get(0);
				jwhzplsb.setCzsj(StringUtils.getServiceTime());
				jwhzplsb.setCzrid(this.getUser().getYhid());
				jwhzplsb.setZp(logoFile.getBytes());
				jwhxxb.setBdsj(StringUtils.getServiceTime());
				jwhxxb.setCzrid(this.getUser().getYhid());
				super.update(jwhzplsb);
			}
			super.update(jwhxxb);
//			fls.read(imageByte);
//			fls.close();
			return null;
		} catch (FileNotFoundException e) {
			throw e;
			//e.printStackTrace();
		} catch (IOException e) {
			throw e;
//			e.printStackTrace();
		}
	}

	@Override
	public VoBb checkDjJth(ExtMap<String, Object> params) {
		VoBb vobb = new VoBb();
		PoHJXX_HXXB hxxb = new PoHJXX_HXXB();
		Long hhnbid = params.getLong("hhnbid");
		Long hhid = params.getLong("hhid");
		String gmsfhm = params.getString("gmsfhm");
		StringBuffer hqlbuffer = new StringBuffer();
		if(CommonUtil.isNotEmpty(params.getString("hhnbid"))) {
			hxxb = super.get(PoHJXX_HXXB.class, hhnbid);
			vobb.setJttdbz(hxxb.getJttdbz());
			hqlbuffer.append("from PoHJXX_CZRKJBXXB where ryzt='0' and cxbz='0' and jlbz='1'");
			if(CommonUtil.isNotEmpty(params.getString("gmsfhm"))) {
				hqlbuffer.append("  and gmsfhm='"+gmsfhm+"'");
			}
			if(CommonUtil.isNotEmpty(params.getString("hhnbid"))) {
				hqlbuffer.append("  and hhnbid='"+hhnbid+"'");
			}
		}else if(CommonUtil.isNotEmpty(params.getString("hhid"))) {
			String hql = "from PoHJXX_HXXB where  cxbz='0' and jlbz='1' and hhid ='"+hhid+"'";
			List<PoHJXX_HXXB> listhxx = (List<PoHJXX_HXXB>) queryAll(hql);
			if(listhxx.size()>0) {
				hxxb = listhxx.get(0);
				vobb.setJttdbz(hxxb.getJttdbz());
			}
			hqlbuffer.append("from PoHJXX_CZRKJBXXB where ryzt='0' and cxbz='0' and jlbz='1'");
			if(CommonUtil.isNotEmpty(params.getString("hhid"))) {
				hqlbuffer.append("  and hhid='"+hhid+"'");
			}
		}
		List<PoHJXX_CZRKJBXXB> list = (List<PoHJXX_CZRKJBXXB>) queryAll(hqlbuffer.toString());
		if(list.size()>0) {
			PoHJXX_CZRKJBXXB hjxx_czrkjbxxb = list.get(0);
			vobb.setDjzt(hjxx_czrkjbxxb.getDjzt());//hjxx_czrkjbxxb.getDjzt()
		}
		return vobb;
	}

//	@Override
//	public PoUPLOAD_TEMP commonUploadZp(MultipartHttpServletRequest params) throws IOException{
//		PoUPLOAD_TEMP uploadTemp = new PoUPLOAD_TEMP();
//		MultipartFile logoFile = params.getFile("logoFile");
//		try {
//			PojoInfo  upload_tempDAO = DAOFactory.createUPLOAD_TEMPDAO();
//			Long id = (Long) upload_tempDAO.getId();
//			uploadTemp.setUploadzpid(id);
//			uploadTemp.setCzsj(StringUtils.getServiceTime());
//			uploadTemp.setCzrid(this.getUser().getYhid());
//			uploadTemp.setZp(logoFile.getBytes());
//			super.create(uploadTemp);
//			return null;
//		} catch (FileNotFoundException e) {
//			throw e;
//		} catch (IOException e) {
//			throw e;
//		}
//	
//	}

	@Override
	public void downZp(
			HttpServletRequest request,
			HttpServletResponse response,ExtMap<String, Object> params) throws IOException,	ServletException{
//		try {
//			String savePath = params.getString("path");
//			Long ryid = params.getLong("ryid");
//			String sql = " from PoHJXX_RYZPXXB where ryid = '"+ryid+"'";
//			List<PoHJXX_RYZPXXB> hjxx_ryzpxxbList = (List<PoHJXX_RYZPXXB>) super.getObjectListByHql(sql);
//			for(int i = 0;i<hjxx_ryzpxxbList.size();i++) {
//				PoHJXX_RYZPXXB  ryzpxxb = hjxx_ryzpxxbList.get(i);
//				InputStream is = new ByteArrayInputStream(ryzpxxb.getZp());  
//				// 1K的数据缓冲  
//		        byte[] bs = new byte[1024];  
//		        // 读取到的数据长度  
//		        int len;  
//		        // 输出的文件流  
//		       File sf=new File(savePath);  
//		       if(!sf.exists()){  
//		           sf.mkdirs();  
//		       }  
//		       // 获取图片的扩展名  filename  1_li1325169021.jpg
//		       String extensionName = "png";//filename.substring(filename.lastIndexOf(".") +     1);
//		       String bianhao =hjxx_ryzpxxbList.size()>1?(1+i)+"":"";
//		       // 新的图片文件名 = 编号 +"."图片扩展名
//		       String newFileName = ryzpxxb.getGmsfhm()+bianhao+"." + extensionName;
//		       OutputStream os = new FileOutputStream(sf.getPath()+"\\"+newFileName);  
//		        // 开始读取  
//		        while ((len = is.read(bs)) != -1) {  
//		          os.write(bs, 0, len);  
//		        }  
//		        // 完毕，关闭所有链接  
//		        os.close();  
//		        is.close();  
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return;
		
//		
//		InputStream inputstream = new FileInputStream("c:/test.doc");
//		ServletOutputStream servletoutputstream;
//      //设置文件下载信息
//        String filename = 
//   			new String("aaa.doc".getBytes(),"iso-8859-1");
//   		rep.setHeader("Content-Disposition",
//   					"attachment;filename=\"" + filename + "\"");
//   		rep.setHeader("path",
//					"c:/"+filename);
//   		servletoutputstream = rep.getOutputStream();
//		try {
//			byte abyte0[] = new byte[2048];
//			for (int i = inputstream.read(abyte0); i != -1; 
//				i = inputstream.read(abyte0))
//				servletoutputstream.write(abyte0, 0, i);
//				
//			servletoutputstream.flush();
//		} catch (IOException _ex) {
//			rep.getOutputStream().println(_ex.toString());
//			return;
//		} finally {				
//			inputstream.close();
//			servletoutputstream.close();
//		}
		
		
//		String fileName = params.getString("fileName");
//		String savePath = params.getString("path");
//		response.setContentType("application/octet-stream");
//		fileName = URLDecoder.decode(fileName, "utf-8");
//		// 清空response
//		response.reset();
//		// 设置response的Header
//		response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"),"iso8859-1"));
//		try{
//			Long zpid = params.getLong("zpid");
//			PoHJXX_RYZPXXB hjxx_ryzpxxb  = super.get(PoHJXX_RYZPXXB.class, zpid);
//		//以流的形式下载文件
//		InputStream fis = new ByteArrayInputStream(hjxx_ryzpxxb.getZp());
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//		toClient.write(buffer);
//		toClient.flush();
//		toClient.close();
//		}catch(Exception e){
//		e.printStackTrace();
//		}
		zipFile("d:/temp/",params);
		return;
	}
	
	private void zipFile(String sourcePath,ExtMap<String, Object> params){
        /**创建一个临时压缩文件，
         * 我们会把文件流全部注入到这个文件中
         * 这里的文件你可以自定义是.rar还是.zip*/
        FileOutputStream fos = null;  
        ZipOutputStream zos = null;  
        //String path = "c:/guidang.zip";//这种写法是错误的，之前理解的有问题，因为是在本机测试，
        //windows系统存在c盘，而我们项目的服务器是Linux的，致使识别不了。可以将临时文件保存到一个临时路径文件夹tempPath下
        String path = "tempPath.zip";
        File sourceFile = new File(sourcePath);
        File zipFile = new File(path);
        try {
            if (!zipFile.exists()){   
                zipFile.createNewFile();   
            }
            if (!sourceFile.exists()){   
            	sourceFile.mkdir();  
            }
			Long ryid = params.getLong("ryid");
			String sql = " from PoHJXX_RYZPXXB where ryid = '"+ryid+"'";
			List<PoHJXX_RYZPXXB> hjxx_ryzpxxbList = (List<PoHJXX_RYZPXXB>) super.getObjectListByHql(sql);
			for(int i = 0;i<hjxx_ryzpxxbList.size();i++) {
				PoHJXX_RYZPXXB  ryzpxxb = hjxx_ryzpxxbList.get(i);
				InputStream is = new ByteArrayInputStream(ryzpxxb.getZp());  
				// 1K的数据缓冲  
		        byte[] bs = new byte[1024];  
		        // 读取到的数据长度  
		        int len;  
		        // 输出的文件流  
		       // 获取图片的扩展名  filename  1_li1325169021.jpg
		       String extensionName = "png";//filename.substring(filename.lastIndexOf(".") +     1);
		       String bianhao =hjxx_ryzpxxbList.size()>1?(1+i)+"":"";
		       // 新的图片文件名 = 编号 +"."图片扩展名
		       String newFileName = ryzpxxb.getXm()+ryzpxxb.getGmsfhm()+bianhao+"." + extensionName;
		       OutputStream os = new FileOutputStream(sourceFile.getPath()+"\\"+newFileName);  
		        // 开始读取  
		        while ((len = is.read(bs)) != -1) {  
		          os.write(bs, 0, len);  
		        }  
		        // 完毕，关闭所有链接  
		        os.close();  
		        is.close();  				
			}
            //创建文件输出流
            fos = new FileOutputStream(zipFile);   
            /**打包的方法我们会用到ZipOutputStream这样一个输出流,
             * 所以这里我们把输出流转换一下*/
            zos = new ZipOutputStream(fos);
            writeZip(sourceFile, "", zos);
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);  
        } finally{  
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
            	e.printStackTrace();
                //log.error("创建ZIP文件失败",e);
            }
            this.downLoad(path);
            //删除临时文件
            if (zipFile.exists()) {
                zipFile.delete();
            }
            if (sourceFile.exists()) {
            	if (sourceFile.isDirectory()) {
                    String[] children = sourceFile.list();//递归删除目录中的子目录下
                    for (int i=0; i<children.length; i++) {
                    	(new  File(sourceFile, children[i])).delete();
                    }
                }
            }
            sourceFile.delete();
        }
    }
	private static void writeZip(File file, String parentPath, ZipOutputStream zos) {
        if(file.exists()){
            if(file.isDirectory()){//处理文件夹
                parentPath+=file.getName()+File.separator;
                File [] files=file.listFiles();
                for(File f:files){
                    writeZip(f, parentPath, zos);
                }
            }else{

                FileInputStream fis=null;
                try {
                    fis=new FileInputStream(file);
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());
                    zos.putNextEntry(ze);
                    byte [] content=new byte[1024];
                    int len;
                    while((len=fis.read(content))!=-1){
                        zos.write(content,0,len);
                        zos.flush();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                     //关闭流  
                    try {  
                        if(null != fis) fis.close();  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                        throw new RuntimeException(e);  
                    } 
                }
            }
        }
    } 
	public void downLoad(String filePath){

        HttpServletResponse response = BaseContext.getContext().getResponse();

        BufferedInputStream bis=null;
        BufferedOutputStream  bos=null;
        try{
             String filename=filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
             response.setContentType("image/jpeg");
             response.setCharacterEncoding("gbk");
             response.setHeader("Content-Disposition","filename="+new String(filename.getBytes("gbk"),"iso8859-1"));
             bis =new BufferedInputStream(new FileInputStream(filePath));
             bos=new BufferedOutputStream(response.getOutputStream()); 
             byte[] buff = new byte[2048];
             int bytesread;
             while(-1 != (bytesread = bis.read(buff, 0, buff.length))) {
              bos.write(buff,0,bytesread);
             }
        }catch(Exception e){
             e.printStackTrace();
        }finally {
         if (bis != null)
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
         if (bos != null)
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	@Override
	public void downExcelZip(HttpServletRequest req,HttpServletResponse rep, 
			ExtMap<String, Object> params) {
		String filename = "";
		String FileDate = DateHelper.formateDate("yyyyMMdd");
		try {
			
			String type = params.getString("type");
			if(type==null||type.equals("undefined")) {
				throw new  Exception("参数错误！");
			}else {
				ExtMap<String, Object> sqlParam = (ExtMap<String, Object>)BaseContext.getContext().getSession().getAttribute(type);
				Page p = new Page();
				if(type.equals("queryCzrk")) {//常住人口
					p = queryService.queryRkxx(sqlParam);
				}else if(type.equals("queryQydjRy")) {//区域冻结
					p = queryService.queryQydjRy(sqlParam);
				}else if(type.equals("getRkxx")) {//人口基本信息
					p = queryService.queryRkxx(sqlParam);
				}else if(type.equals("getHxx")) {//户信息
					p = queryService.getHxx(sqlParam);
				}else if(type.equals("queryMlpxxcx")) {//门楼牌信息
					p = queryService.getMlpxx(sqlParam);
				}else if(type.equals("queryThry")) {//同户人员信息
					p = queryService.queryThry(sqlParam);
				}else if(type.equals("xzdcx")) {//现住地信息
					p = queryService.queryXxxx(sqlParam);
				}else if(type.equals("qrdjcx")) {//迁入登记信息
					p = queryService.queryQrxx(sqlParam);
				}else if(type.equals("qczxcx")) {//迁出注销信息
					p = queryService.queryQcxx(sqlParam);
				}else if(type.equals("csdjcx")) {//出生登记信息
					p = queryService.queryCsdjxx(sqlParam);
				}else if(type.equals("swzxcx")) {//死亡注销信息
					p = queryService.getSwzxxx(sqlParam);
				}else if(type.equals("zzbdcx")) {//住址变动信息
					p = queryService.getZzbdxx(sqlParam);
				}else if(type.equals("bggzcx")) {//变更更正信息
					p = queryService.getBggzxx(sqlParam);
				}else if(type.equals("hbbgcx")) {//户别变更信息
					p = queryService.getHbbgxx(sqlParam);
				}else if(type.equals("hjblcx")) {//户籍补录信息
					p = queryService.getHjblxx(sqlParam);
				}else if(type.equals("hjsccx")) {//户籍删除信息
					p = queryService.getHjscxx(sqlParam);
				}else if(type.equals("hcybdcx")) {//户成员变动信息
					p = queryService.getHcybdxx(sqlParam);
				}else if(type.equals("bdqkcx")) {//变动情况信息
					p = queryService.getBdqkxx(sqlParam);
				}else if(type.equals("dycx")) {//打印信息
					p = queryService.getDyxx(sqlParam);
				}else if(type.equals("edzslcx")) {//二代证受理信息
					p = queryService.getEdzslxx(sqlParam);
				}else if(type.equals("sjcx")) {//收交信息
					p = queryService.getSjxx(sqlParam);
				}else if(type.equals("yscx")) {//验收信息
					p = queryService.getYsxx(sqlParam);
				}else if(type.equals("lqffcx")) {//领取发放信息
					p = queryService.getLqffxx(sqlParam);
				}else if(type.equals("gscx")) {//挂失信息
					p = queryService.getGsxx(sqlParam);
				}else if(type.equals("tdcx")) {//投递信息
					p = queryService.getTdxx(sqlParam);
				}else if(type.equals("zzfkcx")) {//制证反馈信息
					p = queryService.getZzfkxx(sqlParam);
				}else if(type.equals("zlfkcx")) {//质量反馈信息
					p = queryService.getZlfkxx(sqlParam);
				}else if(type.equals("ffjgcx")) {//分检结果信息
					p = queryService.getFjjgxx(sqlParam);
				}else if(type.equals("ffjscx")) {//分发接收信息
					p = queryService.getFfjsxx(sqlParam);
				}else if(type.equals("sfzcx")) {//身份证信息
					p = queryService.getSfzxx(sqlParam);
				}else if(type.equals("chcx")) {//重号信息
					p = queryService.getChxx(sqlParam);
				}else if(type.equals("sfhfpcx")) {//身份号分配信息
					p = queryService.getSfhfpxx(sqlParam);
				}else if(type.equals("qyzcx")) {//迁移证信息
					p = queryService.getQyzxx(sqlParam);
				}else if(type.equals("zqzcx")) {//准迁证信息
					p = queryService.getZqzxx(sqlParam);
				}else if(type.equals("ydzslcx")) {//一代证受理信息
					p = queryService.getYdzslxx(sqlParam);
				}else if(type.equals("shcx")) {//审核信息
					p = queryService.getShxx(sqlParam);
				}else if(type.equals("xhcx")) {//销毁信息
					p = queryService.getXhxx(sqlParam);
				}else if(type.equals("qrspcx")) {//迁入审批
					p = queryService.queryQrsp(sqlParam);
				}else if(type.equals("bgspcx")) {//变更审批
					p = queryService.queryBgsp(sqlParam);
				}else if(type.equals("hbbgspcx")) {//户别变更审批
					p = queryService.queryHbbgsp(sqlParam);
				}else if(type.equals("hjblspcx")) {//户籍补录审批
					p = queryService.queryQydjRy(sqlParam);
				}else if(type.equals("hjscspcx")) {//户籍删除审批
					p = queryService.queryHjscsp(sqlParam);
				}else if(type.equals("pzlogcx")) {//拍照日志信息
					p = queryService.queryPzxx(sqlParam);
				}else if(type.equals("pzlogcx_ycl")) {//已处理拍照信息
					p = queryService.queryPzxxycl(sqlParam);
				}else if(type.equals("sfzxxcx")) {//临证查询
					p = queryService.querySfzxxcx(sqlParam);
				}else if(type.equals("czyxxcx")) {//操作员信息
					p = queryService.queryczyxxcx(sqlParam);
				}else if(type.equals("zjadresscx")) {//追加住址
					p = queryService.queryzjAdresscx(sqlParam);
				}else if(type.equals("zzzjrzcx")) {//日志信息
					p = queryService.queryrzxxcx(sqlParam);
				}else if(type.equals("tfbdxxcx")) {//逃犯比对信息
					p = queryService.querytfbdcx(sqlParam);
				}else if(type.equals("queryJssdls")) {//解锁锁定历史信息
					p = queryService.queryJssdls(sqlParam);
				}else if(type.equals("queryQcclxx")) {//迁出处理
					p = queryService.queryQcclxx(sqlParam);
				}else if(type.equals("queryFxjsktj")) {//非现金收款统计查询
					p = queryService.queryFxjsktj(sqlParam);
				}else if(type.equals("fxjsktjcx")) {//非现金收款详细查询
					p = queryService.queryFxjsktjInfo(sqlParam);
				}else if(type.equals("querysjkfjglxxcx")){//实缴款附件管理
					p = queryService.querySjkfjgl(sqlParam);
				}else if(type.equals("querySjkxxgl")){//实缴款信息管理
					p = queryService.querySjkxx(sqlParam);
				}else if(type.equals("queryCsxx")||type.equals("queryHbbgxx")||type.equals("queryQcxx")
						||type.equals("queryQrxx")||type.equals("querySjblxx")||type.equals("querySjscxx")||
						type.equals("querySwxx")||type.equals("queryZzbdxx")) {//四变信息
					p = queryService.queryXxxx(sqlParam);
				}
				     
				filename = type+FileDate+".xls.zip";	
				String zipname = filename.substring(0,filename.length()-4);
				rep.setHeader("Content-Disposition", "attachment;filename=\"" +  new String(filename.getBytes("gb2312"), "ISO8859-1" ) + "\"");

				org.apache.tools.zip.ZipOutputStream out = new org.apache.tools.zip.ZipOutputStream(rep.getOutputStream());
				org.apache.tools.zip.ZipEntry zipEntry = new org.apache.tools.zip.ZipEntry(zipname);
				
				out.putNextEntry(zipEntry);
				out.setEncoding("GBK");
				out.setComment("人口数据导出，金铖科技有限公司。");
				
				String[] shuxing = sqlParam.getString("shuxing").split(",");
				String[] header = java.net.URLDecoder.decode(sqlParam.getString("header"), "UTF-8").split(",");
				JSONObject zdyValueKey = (JSONObject) JSONObject.parse(sqlParam.getString("zdyValueKey"));
				importXlsfile(req,out,p.getList(),shuxing,header,zdyValueKey,type);
				out.flush();
				out.close();
			}
			return;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return; 
		}
		
	}
	
	public  void importXlsfile(HttpServletRequest req,
			OutputStream buffout, List<?> l, String[] shuxing, String[] header, JSONObject zdyValueKey,String type) throws ServletException, IOException {
		boolean sbFlag = (type.equals("queryCsxx")||type.equals("queryHbbgxx")||type.equals("queryQcxx")
				||type.equals("queryQrxx")||type.equals("querySjblxx")||type.equals("querySjscxx")||
				type.equals("querySwxx")||type.equals("queryZzbdxx"))?true:false; 
		try{
			jxl.WorkbookSettings a = new jxl.WorkbookSettings();
			InputStream is = null;
			is = FileUtils.class.getResourceAsStream("/conf/daochu.xls");
			//ByteArrayOutputStream buffout = new ByteArrayOutputStream();
			Workbook rwb = Workbook.getWorkbook(is);
			WritableWorkbook wwb = Workbook.createWorkbook(buffout, rwb, a);
			WritableSheet ws = wwb.getSheet(0);
			//4：设置titles
			//5:单元格
			Label label=null;
			if(sbFlag) {
				String zibiaoti ="";
				if(type.equals("queryCsxx")) {
					zibiaoti = "出生";
				}else if(type.equals("queryHbbgxx")) {
					zibiaoti = "户别变更";
				}else if(type.equals("queryQcxx")) {
					zibiaoti = "迁出";
				}else if(type.equals("queryQrxx")) {
					zibiaoti = "迁入";
				}else if(type.equals("querySjblxx")) {
					zibiaoti = "数据补录";
				}else if(type.equals("querySjscxx")) {
					zibiaoti = "数据删除";
				}else if(type.equals("querySwxx")) {
					zibiaoti = "死亡";
				}else if(type.equals("queryZzbdxx")) {
					zibiaoti = "住址变动";
				}
				//子标题
				label=new Label(1,0,zibiaoti);
			    ws.addCell(label);
			}
			//6:给第一行设置列名
			for(int i=0;i<header.length;i++){
			    //x,y,第一行的列名
			    label=new Label(i,sbFlag?1:0,header[i]);
			    //7：添加单元格
			    ws.addCell(label);
			}
			int i =0;
			
			for(int j = 0;j<l.size();j++){
				Map<String, String> map = new HashMap<String,String>();
				Object u = l.get(j);
				Class c1= u.getClass();
				Field[] fields1  = c1.getDeclaredFields();
				for(Field f : fields1) {
					// 获取原来的访问控制权限 
					boolean accessFlag = f.isAccessible(); 
					// 修改访问控制权限 
					f.setAccessible(true); 
					map.put(f.getName(),f.get(u)!=null?f.get(u).toString():"");
				}
				for(int k = 0;k<shuxing.length;k++) { 
					String dict = zdyValueKey.getString(shuxing[k]);
					if(CommonUtil.isNotEmpty(zdyValueKey.getString(shuxing[k]))&&CommonUtil.isNotEmpty(map.get(shuxing[k]))) {
						//字典包含cs或CS开头，从字典表查询出的缓存map中获取值
						if(zdyValueKey.getString(shuxing[k]).startsWith("cs")||zdyValueKey.getString(shuxing[k]).startsWith("CS")) {
							jxl.write.Label labelC = new Label(k,sbFlag?(j+2):(j+1),DictData.getCodeName(zdyValueKey.getString(shuxing[k]).toUpperCase(), map.get(shuxing[k])));
							ws.addCell(labelC);
						}else {//需要二次查询的，得从dictService中前台搜索框中用到的查询查询出字典值
							SysCode dictSysCode = dictService.getRemoteDictItem(zdyValueKey.getString(shuxing[k]).toUpperCase(), map.get(shuxing[k]));
							if(dictSysCode!=null) {
								jxl.write.Label labelC = new Label(k,sbFlag?(j+2):(j+1),dictSysCode.getCodename());
								ws.addCell(labelC);
							}else if(map.get(shuxing[k])!=null){
								jxl.write.Label labelC = new Label(k,sbFlag?(j+2):(j+1),map.get(shuxing[k]));
								ws.addCell(labelC);
							}
							
						}
					}else {
						//无需字典翻译，直接从对象中取值
						if(header[k].equals("年龄")) {//计算年龄 dateToAge
							jxl.write.Label labelC = new Label(k,sbFlag?(j+2):(j+1),StringUtils.dateToAgeDay(map.get(shuxing[k])));
							ws.addCell(labelC);
						}else {
							jxl.write.Label labelC = new Label(k,sbFlag?(j+2):(j+1),map.get(shuxing[k]));
							ws.addCell(labelC);
						}
						
					}
					
				}
				//i++;
			}

			wwb.write();
			wwb.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}

	@Override
	public void uploadQdZp(MultipartHttpServletRequest params) {
		
		MultipartFile logoFile = params.getFile("logoFile");
		String pzType = params.getParameter("pzType");//1 收费清单 2缴费清单
		String sfxxbidList = params.getParameter("sfxxbidList");
		String czsj = StringUtils.getServiceTime();
		String[] sfxxbidArray = sfxxbidList.split(",");
		List<PoSFJFFJB> l = new ArrayList<>();
		for (String sfxxbid : sfxxbidArray) {  
			PoSFJFFJB sfjffjb= new PoSFJFFJB();
			String sql = " from PoSFJFFJB where sfxxbid = '"+sfxxbid+"'";
			List<PoSFJFFJB> sfjffjbList = (List<PoSFJFFJB>) super.getObjectListByHql(sql);
			try {
				createOrupdateSfjfxxb(sfjffjb, logoFile, pzType, sfxxbid, sfjffjbList,czsj);
			} catch (IOException e) {
				e.printStackTrace();
			}
	     }
	}

	public void createOrupdateSfjfxxb(PoSFJFFJB sfjffjb, MultipartFile logoFile, String pzType, String sfxxbid,
			List<PoSFJFFJB> sfjffjbList,String czsj) throws IOException {
		/*
		 * 没有满足的记录，则插入
		 */
		if(sfjffjbList.size()==0) {
			PojoInfo  hjxx_jwhzplsbDAO = DAOFactory.createSFJFFJBDAO();
			Long id = (Long) hjxx_jwhzplsbDAO.getId();
			sfjffjb.setCzsj(czsj);
			sfjffjb.setCzrid(this.getUser().getYhid());
			if(pzType.equals("1")) {
				sfjffjb.setSfqd(logoFile.getBytes());
			}else if(pzType.equals("2")) {
				sfjffjb.setJfqd(logoFile.getBytes());
				updateSfxxb(sfxxbid,czsj);
			}
			sfjffjb.setSfjffjbid(id);
			sfjffjb.setSfxxbid(Long.valueOf(sfxxbid));
			super.create(sfjffjb);
		}else {
			/*
			 * 存在符合条件的记录，则更新
			 */
			sfjffjb = sfjffjbList.get(0);
			sfjffjb.setGxsj(czsj);
			sfjffjb.setGxrid(this.getUser().getYhid());
			if(pzType.equals("1")) {
				sfjffjb.setSfqd(logoFile.getBytes());
			}else if(pzType.equals("2")) {
				sfjffjb.setJfqd(logoFile.getBytes());
				updateSfxxb(sfxxbid,czsj);
			}
			super.update(sfjffjb);
		}
	}

	/**
	 * @param sfxxbid
	 * @param time 
	 */
	private void updateSfxxb(String sfxxbid, String time) {
		PoSFXXB sfxxb = super.get(PoSFXXB.class, Long.parseLong(sfxxbid));
		sfxxb.setJfflag("1");
		sfxxb.setJkrid(this.getUser().getYhid());
		sfxxb.setJksj(time);
		
	}
}
