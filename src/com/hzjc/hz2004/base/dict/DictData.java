package com.hzjc.hz2004.base.dict;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.hzjc.hz2004.appbase.SysParam;
import com.hzjc.hz2004.base.SystemConfig;
import com.hzjc.hz2004.base.bean.Code;
import com.hzjc.hz2004.base.bean.SysCode;
import com.hzjc.hz2004.base.bean.SysCodetype;
import com.hzjc.hz2004.base.bean.SysOrganizeInfo;
import com.hzjc.hz2004.base.sort.EntitySorter;
import com.hzjc.hz2004.po.PoXT_XTCSB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.ObjectUtil;
import com.hzjc.hz2004.util.QxglUtils;

/**
 * 缓冲字典数据
 * @author sh
 *
 */
public class DictData {
	static Logger logger = Logger.getLogger(DictData.class);
	
	static public Map<String,String> mapHz = new HashMap<String,String>();
	
	/**
	 * 搜索用
	 */
	static public Map<String,List<SysCode>> mapList = new HashMap<String,List<SysCode>>();
	
	static public List<SysCode> getSysCodeList(String spacename,String visiontype){
		if(CommonUtil.isEmpty(spacename))
			spacename = SYS_SPACENAME;
		
		String key = spacename + "_" + visiontype;
		return mapList.get(key);
	}
	
	static public List<SysCode> getSysCodeList(String visiontype){
		String key = SYS_SPACENAME + "_" + visiontype;
		return mapList.get(key);
	}
	
	//缓存用户：KEY=用户名，VALUE=QxxtYonghu
	static private final Map<String,PoXT_YHXXB> mapZzjy = new HashMap<String,PoXT_YHXXB>();
	
	//缓存身份证号和警员的关系
	static private final Map<String,String> mapZzjySfzh = new HashMap<String,String>();
	
	/**
	 * 依据身份证号获取用户
	 * @param sfzh
	 * @return
	 */
	static public PoXT_YHXXB getZzjyBySfzh(String sfzh){
		String usercode = mapZzjySfzh.get(sfzh);
		if(usercode==null)
			return null;
		
		return mapZzjy.get(usercode);
	}
	
	/**
	 * 依据用户登录名，获取用户对象QxxtYonghu
	 * @param usercode	用户登录名称
	 * @return
	 */
	static public PoXT_YHXXB getZzjy(String usercode){
		if(mapZzjy.size()==0){
			try{
				DictData.reLoad(null);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return mapZzjy.get(usercode);
	}
	
	static public java.util.Collection<PoXT_YHXXB> getAllSysUserInfo(){
		return mapZzjy.values();
	}
	
	//缓存部门和直接用户：KEY=部门编码，VALUE=List<用户列表>
	static private final Map<String,List<String>> mapZzjyByzzjg = new HashMap<String,List<String>>();
	
	//缓存用户和部门：KEY=用户，VALUE=List<部门编码>
	static private final Map<String,List<String>> mapZzjyByzzjg2 = new HashMap<String,List<String>>();
	
	/**
	 * 获取该用户的部门列表
	 * @param usercode
	 * @return
	 */
	static public List<SysOrganizeInfo> getZzjgListByYhbm(String usercode){
		List<SysOrganizeInfo> list = new ArrayList<SysOrganizeInfo>();
		List<String> mylist = mapZzjyByzzjg2.get(usercode);
		for(String dw :mylist){
			SysOrganizeInfo zz = getZzjg(dw);
			if(zz!=null) {
				list.add(zz);
			}
		}
		return list;
	}
	
	/**
	 * 依据单位编码，获取该单位下面的直属警员的警员编码列表。
	 * @param dwbm	单位编码。
	 * @return
	 */
	static public List<String> getZzjyList(String dwbm){
		List<String> result = mapZzjyByzzjg.get(dwbm);
		if(result == null) {
			result = new ArrayList<String>();
		}
		
		return result;
	}
	
	//缓存组织机构:KEY=单位代码，VALUE=QxxtZuz
	static private final Map<String,SysOrganizeInfo> mapZzjg = new HashMap<String,SysOrganizeInfo>();
	
	/**
	 * 依据单位编码，获取单位对象QxxtZuz
	 * @param dwbm
	 * @return
	 */
	public static SysOrganizeInfo getZzjg(String dwbm){
		return mapZzjg.get(dwbm);
	}
	
	static public java.util.Collection<SysOrganizeInfo> getAllSysOrganizeInfo(){
		return mapZzjg.values();
	}
	
	/**
	 * 依据单位编码列表获取层次码列表
	 * @param codes
	 * @return
	 */
	public static String getZzjgCcmsByDwbm(String codes){
		String[] s = codes.split(",");
		String ccm = null;
		for(int i=0;i<s.length;i++){
			SysOrganizeInfo zz = DictData.getZzjg(s[i]);
			if(zz==null)
				continue;
			
			if(CommonUtil.isEmpty(ccm))
				ccm = "";
			else
				ccm += ",";
		
			ccm += zz.getCcm();
		}
		return ccm;
	}
	
	/**
	 * 依据警员编码，获取层次码列表
	 * @param codes
	 * @return
	 */
	public static String getZzjgCcmsByZzjyBm(String codes){
		String[] s = codes.split(",");
		String ccm = null;
		for(int i=0;i<s.length;i++){
			PoXT_YHXXB jy = DictData.getZzjy(s[i]);
			if(jy==null)
				continue;
			
			SysOrganizeInfo zz = DictData.getZzjg(jy.getDwdm());
			if(zz==null)
				continue;
			
			if(ccm==null) ccm = "";
			if(CommonUtil.isEmpty(ccm)) ccm += ",";
			
			ccm += zz.getCcm();
		}
		return ccm;
	}
	
	/**
	 * 依据单位层次码，获取单位对象QxxtZuz
	 * @param ccm
	 * @return
	 */
	public static SysOrganizeInfo getZzjgByCcm(String ccm){
		String dwbm = getZzjgBm(ccm);
		if(dwbm==null) {
			return null;
		}
		
		return getZzjg(dwbm);
	}
	
	//缓存组织机构和层次码关系：KEY=层次码，VALUE=层次码
	static private final Map<String,String> mapZzjgCcmToDm = new HashMap<String,String>();
	
	/**
	 * 依据单位层次码，获取单位编码
	 * @param ccm
	 * @return
	 */
	public static String getZzjgBm(String ccm){
		return mapZzjgCcmToDm.get(ccm);
	}
	
	//缓存单位层次码和直接下属的关系：KEY=层次码，VALUE=之间下属层次码列表
	static private final Map<String,List<String>> mapZzjgCcmToDmList = new HashMap<String,List<String>>();
	
	//获取指定单位类型的单位
	static public List<SysOrganizeInfo> getZzjgListByDwlx(String dwlx){
	    List<SysOrganizeInfo> list = new ArrayList<SysOrganizeInfo>();
	    for(SysOrganizeInfo z:mapZzjg.values()){
			;
	    }
	    return list;
	}
	
	/**
	 * 依据层次码，获取该单位的直属单位层次码列表。
	 * @param ccm
	 * @return
	 */
	public static List<String> getZzjgList(String ccm){
		List<String> result = mapZzjgCcmToDmList.get(ccm);
		
		if(result == null) {
			result = new ArrayList<String>();
		}
		
		return result;
	}
	
	public static List<String> getZzjgListByDwbm(String dwbm){
		SysOrganizeInfo zzjg = getZzjg(dwbm);
		if(zzjg==null) {
			return new ArrayList<String>();
		}
		
		List<String> result =  mapZzjgCcmToDmList.get(zzjg.getCcm());
		
		if(result == null) {
			result = new ArrayList<String>();
		}
		
		return result;
	}
	
	//根单位代码
	static private String rootDwccm = null;
	
	/**
	 * 获取更单位的层次码
	 * @return
	 */
	public static String getRootDwccm(){
		return rootDwccm;
	}
	
	//默认字典名字空间
	static final public String SYS_SPACENAME = "_SYS_SPACENAME_";
	
	//存储父亲字典树：Map<SP+VISIONTYPE,Map<P_CODETYPE,List<QxxtXtzdRoot>>>
	static final private Map<String,Map<String,List<SysCodetype>>> mapCodeTypeList = new HashMap<String,Map<String,List<SysCodetype>>>();
	
	/**
	 * 获取系统字典指定visiontype的根字典MAP，KEY=P_CODETYPE
	 * @param visiontype
	 * @return
	 */
	static public Map<String,List<SysCodetype>> getSysCodetypeMap(String visiontype){
		return getSysCodetypeMap(SYS_SPACENAME,visiontype);
	}
	
	/**
	 * 获取指定名字空间spacename的字典的指定visiontype的根字典MAP，KEY=P_CODETYPE
	 * @param spacename
	 * @param visiontype
	 * @return
	 */
	static public Map<String,List<SysCodetype>> getSysCodetypeMap(String spacename,String visiontype){
		String key = spacename + "_" + visiontype;
		Map<String,List<SysCodetype>> result = mapCodeTypeList.get(key);
		
		if(result == null) {
			result = new HashMap<String, List<SysCodetype>>();
		}
		
		return result;
	}
	
	//KEY=SP+VISIONTYPE+CODETYPE,VALUE=SysCodetype
	static final private Map<String,SysCodetype> mapCodeType = new HashMap<String,SysCodetype>();
	
	/**
	 * 获取系统字典的指定visiontype和codetype的确定根字典对象
	 * @param visiontype
	 * @param codetype
	 * @return
	 */
	static public SysCodetype getSysCodetype(String visiontype,String codetype){
		return getSysCodetype(SYS_SPACENAME,visiontype,codetype);
	}
	
	/**
	 * 获取指定spacename名字空间字典的指定visiontype和codetype的确定根字典对象
	 * @param spacename
	 * @param visiontype
	 * @param codetype
	 * @return
	 */
	static public SysCodetype getSysCodetype(String spacename,String visiontype,String codetype){
		String key = spacename + "_" + visiontype + "_" + codetype;
		return mapCodeType.get(key);
	}
	
	//存储叶子树：MAP<SP+VISIONTYPE,MAP<CODETYPE,LIST<QxxtXtzdCode>>>
	static final private Map<String,Map<String,List<SysCode>>> mapCodeList = new HashMap<String,Map<String,List<SysCode>>>();
    
    /**
     * 根据字典名称和代码获取相应的代码名称（对本系统字典,zzjgjy字典的翻译）
     * @param spacename
     * @param visionType
     * @param code
     * @return
     */
    public static String getCodeName(String spacename, String visionType, String code) {
    	List<Code> list = getCodeList(spacename,visionType,code);
    	String strResult = null;
    	for(Code c:list){
    		if(strResult==null) strResult = c.getName();
    		else strResult += "," + c.getName();
    	}
    	return strResult==null?"":strResult;
    }
    
    /**
     * 返回指定字典编码对象Code
     * @param spacename
     * @param visionType
     * @param code
     * @return
     */
    public static List<Code> getCodeList(String spacename, String visionType, String code) {
    	List<Code> list = new ArrayList<Code>();
    	if(code==null)
    		return list;
    	
    	String[] codes = code.split(",");
    	
    	for(int i=0;i<codes.length;i++){
    		String tempcode = codes[i];
    		if(CommonUtil.isEmpty(tempcode))
    			continue;
    		
    		Code c = new Code();
    		c.setCode(tempcode);
    		c.setName(tempcode);
    		list.add(c);
    		
        	if ("DW".equals(visionType) || "ZZJG".equals(visionType)) {
        		SysOrganizeInfo zzjg = DictData.getZzjg(tempcode);
        		if (zzjg != null) {
        			c.setName(zzjg.getMc());
        			c.setPyt(zzjg.getZwpy());
        		}
        	} else if ("RY".equals(visionType) || "ZZJY".equals(visionType)) {
        		PoXT_YHXXB zzjy = DictData.getZzjy(tempcode);
        		if (zzjy != null) {
        			c.setName(zzjy.getYhxm());
        			c.setPyt(zzjy.getYhmj());
        		}
        	} else {// 本系统字典
        		SysCode sysCode = DictData.getSysCode(spacename, visionType, tempcode);
        		if (sysCode != null){
        			c.setName(sysCode.getCodename());
        			c.setPyt(sysCode.getPyt());
        		}
        	}
    	}
    	
    	return list;
    }
    
    /**
     * 获取默认名字空间的指定字典名称
     * @param visionType
     * @param code
     * @return
     */
    public static String getCodeName(String visionType, String code) {
    	return getCodeName(SYS_SPACENAME,visionType,code);
    }
    
	/**
	 * 获取系统字典的指定visiontype的叶子字典MAP,KEY=CODETYPE
	 * @param visiontype
	 * @return
	 */
	static public Map<String,List<SysCode>> getCodeMap(String visiontype){
		return getCodeMap(SYS_SPACENAME,visiontype);
	}
	
	/**
	 * 获取指定spacename名字空间字典的指定visiontype的叶子字典MAP,KEY=CODETYPE
	 * @param spacename
	 * @param visiontype
	 * @return
	 */
	static public Map<String,List<SysCode>> getCodeMap(String spacename,String visiontype){
		String key = spacename + "_" + visiontype;
		Map<String,List<SysCode>> result = mapCodeList.get(key);
		
		if(result == null) {
			result = new HashMap<String, List<SysCode>>();
		}
		
		return result;
	}
	
	//KEY=SP+VISIONTYPE+CODE,VALUE=SysCode
	static final private Map<String,SysCode> mapCode = new HashMap<String,SysCode>();
	
	/**
	 * 获取系统字典的指定visiontype和code的叶子字典
	 * @param visiontype
	 * @param code
	 * @return
	 */
	static public SysCode getSysCode(String visiontype,String code){
		return getSysCode(SYS_SPACENAME,visiontype,code);
	}
	
	/**
	 * 获取指定spacename名字空间字典的指定visiontype和code的叶子字典
	 * @param spacename
	 * @param visiontype
	 * @param code
	 * @return
	 */
	static public SysCode getSysCode(String spacename,String visiontype,String code){
		if(CommonUtil.isEmpty(spacename))
			spacename = SYS_SPACENAME;
		
		String key = spacename + "_" + visiontype + "_" + code;
		return mapCode.get(key);
	}
	
	//缓存许可证:KEY=项目,VALUE=许可证内容
	static private Map<String,String> mapXkz = new HashMap<String,String>();
	
	/**
	 * 获取指定项目的许可证内容
	 * @param xmbm
	 * @return
	 */
	static public String getXkz(String xmbm){
		return mapXkz.get(xmbm);
	}
	
	/**
	 * 重载组织结构
	 * @param conn
	 */
	static public void reLoadZzjg(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet res = null;
		//是否需要关闭连接
		boolean isclose = true;

		List<SysCode> jglist = new ArrayList<SysCode>();
		mapList.put(SYS_SPACENAME + "_DW", jglist);
		
		try{
			if(conn==null){
				conn = QxglUtils.getQxxtConnection();
				if(conn==null)
					throw new Exception("字典库连接异常" );
			}else{
				isclose = false;
			}
			
			stmt = conn.createStatement();	
			
			//缓存组织机构
			String sql = "select * from xt_dwxxb  order by dwjgdm ASC";
			res = stmt.executeQuery(sql);
			//缓存组织机构:KEY=单位代码，VALUE=SysOrganizeInfo
			Map<String,SysOrganizeInfo> mapZzjg_Temp = new HashMap<String,SysOrganizeInfo>();
			//缓存组织机构和层次码关系：KEY=层次码，VALUE=层次码
			Map<String,String> mapZzjgCcmToDm_Temp = new HashMap<String,String>();
			//缓存单位层次码和直接下属的关系：KEY=层次码，VALUE=之间下属层次码列表
			Map<String,List<String>> mapZzjgCcmToDmList_Temp = new HashMap<String,List<String>>();
			while(res.next()){
				SysOrganizeInfo zz = ObjectUtil.copyInfo(SysOrganizeInfo.class, null, res);
				String dm = zz.getDm();
				String ccm = zz.getDwjgdm();
				
				//市局：前4位编码 + 00000000
				if(ccm.endsWith("00000000"))
					ccm = ccm.substring(0,4);
				else if(ccm.endsWith("000000")){
					//县/区分局：4位市局编码 + 2位置分局编码 + 000000
					ccm = ccm.substring(0, 6);
				}else{
					ccm = ccm.substring(0,9);
				}
				zz.setCcm(ccm);
				
				//根单位
				if(rootDwccm==null){
					rootDwccm = "34";
					
					SysOrganizeInfo root = new SysOrganizeInfo();
					root.setMc("省厅");
					root.setDwjgdm("34000000000000");
					root.setCcm(rootDwccm);
					root.setDm("34000000000");
					
					mapZzjgCcmToDm_Temp.put(root.getCcm(),root.getDwjgdm());
					mapZzjg_Temp.put(root.getDwjgdm(), root);
				}
				
				mapZzjg_Temp.put(dm, zz);
				mapZzjgCcmToDm_Temp.put(ccm,dm);
				
				String parentccm = ccm;
				if(ccm.length()==4){
					parentccm = rootDwccm;
				}else{
					if(ccm.length()==6){
						parentccm = ccm.substring(0,4);
					}else{
						parentccm = ccm.substring(0,6);
					}
				}
				
				List<String> dwlist= mapZzjgCcmToDmList_Temp.get(parentccm);
				if(dwlist==null){
					dwlist = new ArrayList<String>();
					mapZzjgCcmToDmList_Temp.put(parentccm, dwlist);
				}
				dwlist.add(ccm);

				//放到树上用于搜索
				SysCode code = new SysCode();
				code.setCode(dm);
				code.setCodename(zz.getDwjgdm()+zz.getZwpy());
				jglist.add(code);
			}
			mapZzjgCcmToDmList_Temp.get(rootDwccm);
			synchronized(mapZzjg_Temp){
				mapZzjg.clear();
				mapZzjg.putAll(mapZzjg_Temp);
				mapZzjg_Temp.clear();
				mapZzjg_Temp = null;
			}
			synchronized(mapZzjgCcmToDm){
				mapZzjgCcmToDm.clear();
				mapZzjgCcmToDm.putAll(mapZzjgCcmToDm_Temp);
				mapZzjgCcmToDm_Temp.clear();
				mapZzjgCcmToDm_Temp = null;
			}
			synchronized(mapZzjgCcmToDmList){
				mapZzjgCcmToDmList.clear();
				mapZzjgCcmToDmList.putAll(mapZzjgCcmToDmList_Temp);
				mapZzjgCcmToDmList_Temp.clear();
				mapZzjgCcmToDmList_Temp = null;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if(res!=null) try{res.close();}catch(Exception e){;}
			res = null;
			
			if(stmt!=null) try{stmt.close();}catch(Exception e){;}
			stmt = null;
			
			if(isclose){
				if(conn!=null) try{conn.close();}catch(Exception e){;}
				conn = null;
			}
		}
	}
	
	/**
	 * 重载警员
	 * @param conn
	 */
	static public void reLoadZzjy(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet res = null;
		//是否需要关闭连接
		boolean isclose = true;
		try{
			if(conn==null){
				conn = QxglUtils.getQxxtConnection();
				if(conn==null)
					throw new Exception("字典库连接异常");
			}else{
				isclose = false;
			}
			
			List<SysCode> jglist = new ArrayList<SysCode>();
			mapList.put(SYS_SPACENAME + "_RY", jglist);
			
			stmt = conn.createStatement();			
			String sql = "select * from XT_YHXXB where yhzt='0' order by dwdm asc";
			res = stmt.executeQuery(sql);
			//缓存用户：KEY=用户名，VALUE=QxxtYonghu
			Map<String,PoXT_YHXXB> mapZzjy_Temp = new HashMap<String,PoXT_YHXXB>();
			//缓存身份证号和警员的关系:KEY=身份证号，VALUE=用户名
			Map<String,String> mapZzjySfzh_Temp = new HashMap<String,String>();
			
			
			Map<String,List<String>> mapZzjyByzzjg_Temp = new HashMap<String,List<String>>();
			
			while(res.next()){
				PoXT_YHXXB yh = ObjectUtil.copyInfo(PoXT_YHXXB.class, null, res);
				String yhm = yh.getYhdlm();
				String dwdm = yh.getDwdm();
				
				if(!mapZzjg.containsKey(dwdm)){
					logger.error("警告：用户" + yhm + "分配的单位" + dwdm + "不存在，或者单位被注销！");
					continue;
				}
				
				List<String> rylist = mapZzjyByzzjg_Temp.get(yh.getDwdm());
				if(rylist==null){
					rylist = new ArrayList<String>();
					mapZzjyByzzjg_Temp.put(yh.getDwdm(), rylist);
				}
				rylist.add(yh.getYhid().toString());
				
				String path = mapZzjg.get(dwdm).getMc();
				
				mapZzjy_Temp.put(yh.getYhid().toString(),  yh);
				mapZzjySfzh_Temp.put(yh.getGmsfhm(), yhm);
				
				//搜索
				SysCode code = new SysCode();
				code.setCode(yhm);
				code.setCodename(path + "\\" + yh.getYhxm());
				jglist.add(code);
			}
			res.close();			
			synchronized(mapZzjy){
				mapZzjy.clear();
				mapZzjy.putAll(mapZzjy_Temp);
				mapZzjy_Temp.clear();
				mapZzjy_Temp = null;
			}
			synchronized(mapZzjySfzh){
				mapZzjySfzh.clear();
				mapZzjySfzh.putAll(mapZzjySfzh_Temp);
				mapZzjySfzh_Temp.clear();
				mapZzjySfzh_Temp = null;
			}
			synchronized(mapZzjyByzzjg){
				mapZzjyByzzjg.clear();
				mapZzjyByzzjg.putAll(mapZzjyByzzjg_Temp);
				mapZzjyByzzjg_Temp.clear();
				mapZzjyByzzjg_Temp = null;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if(res!=null) try{res.close();}catch(Exception e){;}
			res = null;
			
			if(stmt!=null) try{stmt.close();}catch(Exception e){;}
			stmt = null;
			
			if(isclose){
				if(conn!=null) try{conn.close();}catch(Exception e){;}
				conn = null;
			}
		}
	}
	
	/**
	 * 重载字典
	 * @param conn
	 */
	static public void reLoadDictcode(Connection conn2) throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		
		//是否需要关闭连接
		boolean isclose = true;
		try{
			//dict.jdbcname=qxgl
			//如果指定了独立的sys_code,sys_codetype数据来源，哪么独立加载
			String name = SystemConfig.getJdbcConfig("dict.jdbcname");
			if(CommonUtil.isEmpty(name)){
				conn = conn2;
			}else{
				conn = QxglUtils.getQxxtConnection(name);
			}
			
			if(conn==null){
				conn = QxglUtils.getQxxtConnection();
				if(conn==null)
					throw new Exception("字典库连接异常");
			}else{
				if(CommonUtil.isEmpty(name))
					isclose = false;
			}
			
			stmt = conn.createStatement();
			
			String sql = "select * from xt_xtcsb t where cslb='9999'";
			res = stmt.executeQuery(sql);
			
			Map<String,List<SysCode>> mapList_Temp = new HashMap<String,List<SysCode>>();
			Map<String,Map<String,List<SysCodetype>>> mapCodeTypeList_Temp = new HashMap<String,Map<String,List<SysCodetype>>>();
			Map<String,SysCodetype> mapCodeType_Temp = new HashMap<String,SysCodetype>();
			
			Map<String,PoXT_XTCSB> csMap = new HashMap<String,PoXT_XTCSB>();
			
			while(res.next()){
				PoXT_XTCSB cs = ObjectUtil.copyInfo(PoXT_XTCSB.class, null,res);
				csMap.put(cs.getDm().trim(), cs);
				
				SysCodetype zd = new SysCodetype();
				zd.setPyt(cs.getZwpy());
				zd.setCodetype(cs.getKzbze());
				zd.setVisiontype(cs.getKzbze());
				
				String visiontype = zd.getVisiontype();
				String codetype = zd.getCodetype();
				String pCodetype = zd.getPCodetype();
				
				//名字空间
				String spacename = zd.getSpacename();
				if(CommonUtil.isEmpty(spacename))
					spacename = SYS_SPACENAME;				
				String key = spacename + "_" + visiontype + "_" + codetype;
				mapCodeType_Temp.put(key, zd);
				
				key = spacename + "_" + visiontype;
				Map<String,List<SysCodetype>> mapZd = mapCodeTypeList_Temp.get(key);
				if(mapZd==null){
					mapZd = new HashMap<String,List<SysCodetype>>();
					mapCodeTypeList_Temp.put(key, mapZd);
				}
				
				List<SysCodetype> listZd = mapZd.get(pCodetype);
				if(listZd==null){
					listZd = new ArrayList<SysCodetype>();
					mapZd.put(pCodetype, listZd);
				}
				listZd.add(zd);
				
				//将SysCodeType也作为字典的一部分进行搜索
				List<SysCode> l = mapList_Temp.get(key);
				if(l==null){
					l = new ArrayList<SysCode>();
					mapList_Temp.put(key, l);
				}
				
				SysCode code = new SysCode();
				code.setPyt(zd.getPyt());
				code.setCodename(zd.getCodetypename());
				code.setCode(zd.getCodetype());
				l.add(code);
			}
			res.close();
			synchronized(mapCodeTypeList){
				mapCodeTypeList.clear();
				mapCodeTypeList.putAll(mapCodeTypeList_Temp);
				mapCodeTypeList_Temp.clear();
				mapCodeTypeList_Temp = null;
			}
			synchronized(mapCodeType){
				mapCodeType.clear();
				mapCodeType.putAll(mapCodeType_Temp);
				mapCodeType_Temp.clear();
				mapCodeType_Temp = null;
			}
			res.close();
			
			//存储叶子树：MAP<SP+VISIONTYPE,MAP<CODETYPE,LIST<SysCode>>>
			Map<String,Map<String,List<SysCode>>> mapCodeList_Temp = new HashMap<String,Map<String,List<SysCode>>>();
			//KEY=SP+VISIONTYPE+CODE,VALUE=SysCode
			Map<String,SysCode> mapCode_Temp = new HashMap<String,SysCode>();
			sql = "select * from xt_xtcsb t where cslb<>'9999' order by cslb,dm asc";
			
			mapList_Temp.clear();
			res = stmt.executeQuery(sql);
			while(res.next()){
				PoXT_XTCSB cs = ObjectUtil.copyInfo(PoXT_XTCSB.class, null,res);
				cs.getCslb();
				
				if(csMap.get(cs.getCslb())==null)
					continue;
				
				SysCode zd = new SysCode();
				zd.setCode(cs.getDm().trim());
				zd.setCodename(cs.getMc());
				zd.setPyt(cs.getZwpy());
				zd.setCodetype(csMap.get(cs.getCslb()).getKzbze());
				zd.setVisiontype(csMap.get(cs.getCslb()).getKzbze());
				
				String visiontype = zd.getVisiontype();
				String codetype = zd.getCodetype();
				String code = zd.getCode();	
				
				//名字空间
				String spacename = zd.getSpacename();
				if(CommonUtil.isEmpty(spacename))
					spacename = SYS_SPACENAME;
				
				String key = spacename + "_" + visiontype + "_" + code;
				mapCode_Temp.put(key,zd);
				
				if(visiontype==null){
					continue;
				}
				
				key = spacename + "_" + visiontype;
				
				Map<String,List<SysCode>> zdMap = mapCodeList_Temp.get(key);
				if(zdMap==null){
					zdMap = new HashMap<String,List<SysCode>>();
					mapCodeList_Temp.put(key,zdMap);
				}
				List<SysCode> zdList = zdMap.get(codetype);
				if(zdList==null){
					zdList = new ArrayList<SysCode>();
					zdMap.put(codetype,zdList);
				}
				zdList.add(zd);

				List<SysCode> l = mapList_Temp.get(key);
				if(l==null){
					l = new ArrayList<SysCode>();
					mapList_Temp.put(key, l);
				}
				l.add(zd);
			}
			
			if(mapCodeList_Temp.containsKey("_SYS_SPACENAME__CS_YHZW")){
				List<SysCode> l = mapCodeList_Temp.get("_SYS_SPACENAME__CS_YHZW").values().iterator().next();
				//特殊排序下
				java.util.Collections.sort(l,new EntitySorter(true));
				mapList_Temp.put("_SYS_SPACENAME__CS_YHZW", l);
			}
			
			//_SYS_SPACENAME__CS_XB
			res.close();
			
			synchronized(mapCodeList){
				mapCodeList.clear();
				mapCodeList.putAll(mapCodeList_Temp);
				mapCodeList_Temp.clear();
				mapCodeList_Temp = null;
			}
			synchronized(mapCode){
				mapCode.clear();
				mapCode.putAll(mapCode_Temp);
				mapCode_Temp.clear();
				mapCode_Temp = null;
			}
			synchronized(mapList){
				mapList.putAll(mapList_Temp);
				mapList_Temp.clear();
				mapList_Temp = null;
			}
			
			/*
			//汉语拼音，旅馆业需求
			sql = "select * from hzpydzb";
			res = stmt.executeQuery(sql);
			Map<String,String> mapHz_temp = new HashMap<String,String>();
			while(res.next()){
				String hz = res.getString("hz");
				String py = res.getString("py");
				mapHz_temp.put(hz,py);
			}
			synchronized(mapHz){
				mapHz.clear();
				mapHz.putAll(mapHz_temp);
			}
			
			//获取系统配置表数据
			String xmbm = SystemConfig.getSystemConfig("xmbm");
			if(CommonUtil.isNotEmpty(xmbm)){
				sql = "select * from sys_xtpzb where xmbm='" + xmbm + "' or xmbm='*'  or xmbm is null";
				res = stmt.executeQuery(sql);
				Map map = new HashMap();
				while(res.next()){
					String pzkey = res.getString("PZKEY");
					String pzz = res.getString("PZZ");
					map.put(pzkey, pzz);
				}
				res.close();
				SystemConfig.loadMap(map);
			}
			*/
			
			res = null;
			DictData.getCodeMap("_SYS_SPACENAME_", "CS_YHZW");
			stmt.close();
			stmt = null;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if(res!=null) try{res.close();}catch(Exception e){;}
			res = null;
			
			if(stmt!=null) try{stmt.close();}catch(Exception e){;}
			stmt = null;
			
			if(isclose){
				if(conn!=null) try{conn.close();}catch(Exception e){;}
				conn = null;
			}
		}
	}
	
    /**
     * 根据单位代码获取上级单位列表（包括自己本身）使用6位层次
     * 
     * @param cod
     *                单位代码
     * @return
     */
    public static List<SysOrganizeInfo> getSysOrganizeInfoInfos(String deptCode) {
    	List<SysOrganizeInfo> list = new LinkedList<SysOrganizeInfo>();
    	SysOrganizeInfo m = getZzjg(deptCode);
    	if (m != null) {
    		list.add(m);
    		String detail_code = m.getCcm();
    		while (detail_code.length() >= 6) {
    			detail_code = detail_code.substring(0, detail_code.length() - 6);
    			if (detail_code.length() < 1) {
    				break;
    			}
    			
    			SysOrganizeInfo m2 = getZzjgByCcm(detail_code);
    			if (m2 != null) {
    				list.add(m2);
    			}
    		}
    	}
    	return list;
    }
    
    /**
     * 指定单位所属顶级单位(省厅或地市).
     * 
     * @param deptCode
     * @return
     */
    public static SysOrganizeInfo getTopDeptByDeptCode(String deptCode) {
    	SysOrganizeInfo zzjg = null;
    	List<SysOrganizeInfo> list = getSysOrganizeInfoInfos(deptCode);
    	if (list != null && list.size() > 0) {
    		for (Iterator<SysOrganizeInfo> iter = list.iterator(); iter.hasNext();) {
    			//SysOrganizeInfo temp = iter.next();
    			//if (Constants.DWLX_ST.equals(temp.getDwlx())
    			//		|| Constants.DWLX_SJ.equals(temp.getDwlx())) {
    			//	zzjg = temp;
    			//	break;
    			//}
    		}
    	}
    	return zzjg;
    }
    
	/**
	 * 全部重载字典
	 */
	static public Connection reLoad(Connection conn) throws Exception{
		//是否需要关闭连接
		boolean isclose = true;
		try{
			if(conn==null){
				conn = QxglUtils.getQxxtConnection();
				if(conn==null)
					throw new Exception("字典库连接异常!");
			}else{
				isclose = false;
			}

			//reLoadZzjg(conn);			//组织机构
			//reLoadZzjy(conn);			//警员和警员
			reLoadDictcode(conn);	//字典
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if(isclose){
				if(conn!=null) try{conn.close();}catch(Exception e){;}
				conn = null;
			}
		}
		
		return conn;
	}
	
	static public String getOrganizePath(String deptCode){
		SysOrganizeInfo info = DictData.getZzjg(deptCode);
		if(info.getCcm().length()==4){
			return info.getMc();
		}else{
			String str = info.getMc();
			String ccm = info.getCcm();
			while(ccm.length()>4){
				if(ccm.length()==6)
					ccm = ccm.substring(0,4);
				else
					ccm = ccm.substring(0,6);
				
				SysOrganizeInfo i = DictData.getZzjgByCcm(ccm);
				if(i==null)
					str = ccm + "/" + str;
				else
					str = i.getMc() + "/" + str;
			}
			return str;
		}
	}
}
